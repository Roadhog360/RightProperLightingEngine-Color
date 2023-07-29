/*
 * Copyright (c) 2023 FalsePattern, Ven
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/
 * or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

package com.falsepattern.rple.internal.mixin.mixins.client;

import com.falsepattern.falsetweaks.api.triangulator.VertexAPI;
import com.falsepattern.rple.internal.client.lightmap.LightMapConstants;
import com.falsepattern.rple.internal.client.lightmap.LightMapPipeline;
import com.falsepattern.rple.internal.client.render.CookieMonster;
import com.falsepattern.rple.internal.client.render.TessellatorBrightnessHelper;
import com.falsepattern.rple.internal.client.render.VertexConstants;
import com.falsepattern.rple.internal.mixin.interfaces.ITessellatorMixin;
import lombok.val;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(Tessellator.class)
public abstract class TessellatorMixin implements ITessellatorMixin {
    @Shadow
    private int[] rawBuffer;
    @Shadow
    private boolean hasBrightness;
    @Shadow
    private int rawBufferIndex;

    private long rple$packedBrightness;

    @Redirect(method = "draw",
              at = @At(value = "FIELD",
                       target = "Lnet/minecraft/client/renderer/Tessellator;hasBrightness:Z",
                       opcode = Opcodes.GETFIELD,
                       ordinal = 0),
              require = 1)
    private boolean enable(Tessellator tess) {
        if (hasBrightness) {
            LightMapPipeline.lightMapPipeline().prepare();
            enableLightMapTexture(tess, VertexConstants.getRedIndexNoShader() * 2, LightMapConstants.R_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
            enableLightMapTexture(tess, VertexConstants.getGreenIndexNoShader() * 2, LightMapConstants.G_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
            enableLightMapTexture(tess, VertexConstants.getBlueIndexNoShader() * 2, LightMapConstants.B_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
        }
        return false;
    }

    @Redirect(method = "draw",
              at = @At(value = "FIELD",
                       target = "Lnet/minecraft/client/renderer/Tessellator;hasBrightness:Z",
                       opcode = Opcodes.GETFIELD,
                       ordinal = 1),
              require = 1)
    private boolean disable(Tessellator instance) {
        if (hasBrightness) {
            disableLightMapTexture(LightMapConstants.R_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
            disableLightMapTexture(LightMapConstants.G_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
            disableLightMapTexture(LightMapConstants.B_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING);
        }
        return false;
    }

    @Redirect(method = "addVertex",
              at = @At(value = "FIELD",
                       target = "Lnet/minecraft/client/renderer/Tessellator;hasBrightness:Z",
                       opcode = Opcodes.GETFIELD),
              require = 1)
    private boolean customColor(Tessellator instance) {
        if (hasBrightness) {
            rawBuffer[rawBufferIndex + VertexConstants.getRedIndexNoShader()] = TessellatorBrightnessHelper.getTessBrightnessRed(rple$packedBrightness);
            rawBuffer[rawBufferIndex + VertexConstants.getGreenIndexNoShader()] = TessellatorBrightnessHelper.getTessBrightnessGreen(rple$packedBrightness);
            rawBuffer[rawBufferIndex + VertexConstants.getBlueIndexNoShader()] = TessellatorBrightnessHelper.getTessBrightnessBlue(rple$packedBrightness);
        }
        return false;
    }

    /**
     * @author FalsePattern
     * @reason Colorize
     */
    @Overwrite
    public void setBrightness(int brightness) {
        rple$packedBrightness(CookieMonster.cookieToPackedLong(brightness));
    }

    @Override
    public void rple$packedBrightness(long packedBrightness) {
        hasBrightness = true;
        rple$packedBrightness = packedBrightness;
    }

    @Override
    public long rple$packedBrightness() {
        return rple$packedBrightness;
    }

    private static void enableLightMapTexture(Tessellator tess, int position, int unit) {
        val shortBuffer = ((ITessellatorMixin) tess).rple$shortBuffer();
        OpenGlHelper.setClientActiveTexture(unit);
        shortBuffer.position(position);
        GL11.glTexCoordPointer(2, VertexAPI.recomputeVertexInfo(8, 4), shortBuffer);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    private static void disableLightMapTexture(int unit) {
        OpenGlHelper.setClientActiveTexture(unit);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
