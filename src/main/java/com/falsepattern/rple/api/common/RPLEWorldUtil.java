/*
 * Copyright (c) 2023 FalsePattern, Ven
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/
 * or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

package com.falsepattern.rple.api.common;

import com.falsepattern.lumina.api.lighting.LightType;
import com.falsepattern.rple.api.common.color.ColorChannel;
import lombok.val;
import net.minecraft.world.IBlockAccess;

import static com.falsepattern.rple.api.common.RPLEColorUtil.*;
import static com.falsepattern.rple.api.common.color.ColorChannel.*;
import static com.falsepattern.rple.internal.common.world.RPLEWorldRoot.rple$getWorldRootFromBlockAccess;

public final class RPLEWorldUtil {
    private RPLEWorldUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static int getChannelLightValue(IBlockAccess world,
                                           ColorChannel channel,
                                           int posX,
                                           int posY,
                                           int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return channel.componentFromColor(errorColor());
        return worldRoot.rple$world(channel).lumi$getLightValue(posX, posY, posZ);
    }

    public static int getChannelLightValue(IBlockAccess world,
                                           ColorChannel channel,
                                           LightType lightType,
                                           int posX,
                                           int posY,
                                           int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return channel.componentFromColor(errorColor());
        return worldRoot.rple$world(channel).lumi$getLightValue(lightType, posX, posY, posZ);
    }

    public static int getChannelBlockLightValue(IBlockAccess world,
                                                ColorChannel channel,
                                                int posX,
                                                int posY,
                                                int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return channel.componentFromColor(errorColor());
        return worldRoot.rple$world(channel).lumi$getBlockLightValue(posX, posY, posZ);
    }

    public static int getChannelSkyLightValue(IBlockAccess world, ColorChannel channel, int posX, int posY, int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return channel.componentFromColor(errorColor());
        return worldRoot.rple$world(channel).lumi$getSkyLightValue(posX, posY, posZ);
    }

    public static int getGreyscaleLightValue(IBlockAccess world,
                                             int posX,
                                             int posY,
                                             int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return COLOR_MIN;

        val redLightValue = worldRoot.rple$world(RED_CHANNEL).lumi$getLightValue(posX, posY, posZ);
        val greenLightValue = worldRoot.rple$world(GREEN_CHANNEL).lumi$getLightValue(posX, posY, posZ);
        val blueLightValue = worldRoot.rple$world(BLUE_CHANNEL).lumi$getLightValue(posX, posY, posZ);
        return maxColorComponent(redLightValue, greenLightValue, blueLightValue);
    }

    public static int getGreyscaleLightValue(IBlockAccess world,
                                             LightType lightType,
                                             int posX,
                                             int posY,
                                             int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return COLOR_MIN;

        val redLightValue = worldRoot.rple$world(RED_CHANNEL).lumi$getLightValue(lightType, posX, posY, posZ);
        val greenLightValue = worldRoot.rple$world(GREEN_CHANNEL).lumi$getLightValue(lightType, posX, posY, posZ);
        val blueLightValue = worldRoot.rple$world(BLUE_CHANNEL).lumi$getLightValue(lightType, posX, posY, posZ);
        return maxColorComponent(redLightValue, greenLightValue, blueLightValue);
    }

    public static int getGreyscaleBlockLightValue(IBlockAccess world,
                                                  int posX,
                                                  int posY,
                                                  int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return COLOR_MIN;

        val redLightValue = worldRoot.rple$world(RED_CHANNEL).lumi$getBlockLightValue(posX, posY, posZ);
        val greenLightValue = worldRoot.rple$world(GREEN_CHANNEL).lumi$getBlockLightValue(posX, posY, posZ);
        val blueLightValue = worldRoot.rple$world(BLUE_CHANNEL).lumi$getBlockLightValue(posX, posY, posZ);
        return maxColorComponent(redLightValue, greenLightValue, blueLightValue);
    }

    public static int getGreyscaleSkyLightValue(IBlockAccess world,
                                                int posX,
                                                int posY,
                                                int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return COLOR_MIN;

        val redLightValue = worldRoot.rple$world(RED_CHANNEL).lumi$getSkyLightValue(posX, posY, posZ);
        val greenLightValue = worldRoot.rple$world(GREEN_CHANNEL).lumi$getSkyLightValue(posX, posY, posZ);
        val blueLightValue = worldRoot.rple$world(BLUE_CHANNEL).lumi$getSkyLightValue(posX, posY, posZ);
        return maxColorComponent(redLightValue, greenLightValue, blueLightValue);
    }

    public static int getChannelLightOpacity(IBlockAccess world, ColorChannel channel, int posX, int posY, int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return channel.componentFromColor(errorColor());
        return worldRoot.rple$world(channel).lumi$getBlockOpacity(posX, posY, posZ);
    }

    public static int getGreyscaleLightOpacity(IBlockAccess world,
                                               int posX,
                                               int posY,
                                               int posZ) {
        val worldRoot = rple$getWorldRootFromBlockAccess(world);
        if (worldRoot == null)
            return COLOR_MIN;

        val redLightOpacity = worldRoot.rple$world(RED_CHANNEL).lumi$getBlockOpacity(posX, posY, posZ);
        val greenLightOpacity = worldRoot.rple$world(GREEN_CHANNEL).lumi$getBlockOpacity(posX, posY, posZ);
        val blueLightOpacity = worldRoot.rple$world(BLUE_CHANNEL).lumi$getBlockOpacity(posX, posY, posZ);
        return minColorComponent(redLightOpacity, greenLightOpacity, blueLightOpacity);
    }
}
