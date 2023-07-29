package com.falsepattern.rple.internal.client.lightmap;

import lombok.experimental.UtilityClass;
import org.lwjgl.opengl.GL13;

@UtilityClass
public final class LightMapConstants {
    public static final int LIGHT_MAP_1D_SIZE = 16;
    public static final int LIGHT_MAP_2D_SIZE = LIGHT_MAP_1D_SIZE * LIGHT_MAP_1D_SIZE;

    public static final int R_LIGHT_MAP_COLOR_BIT_MASK = 0xFF00FFFF;
    public static final int G_LIGHT_MAP_COLOR_BIT_MASK = 0xFFFF00FF;
    public static final int B_LIGHT_MAP_COLOR_BIT_MASK = 0xFFFFFF00;

    public static final int R_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING = GL13.GL_TEXTURE1;
    public static final int G_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING = GL13.GL_TEXTURE2;
    public static final int B_LIGHT_MAP_FIXED_TEXTURE_UNIT_BINDING = GL13.GL_TEXTURE3;

    private static final int OPTIFINE_MAX_RESERVED_TEXTURE_SAMPLER = 40;
    private static final int R_LIGHT_MAP_SHADER_TEXTURE_SAMPLER_BINDING = OPTIFINE_MAX_RESERVED_TEXTURE_SAMPLER + 1;
    private static final int G_LIGHT_MAP_SHADER_TEXTURE_SAMPLER_BINDING = OPTIFINE_MAX_RESERVED_TEXTURE_SAMPLER + 2;
    private static final int B_LIGHT_MAP_SHADER_TEXTURE_SAMPLER_BINDING = OPTIFINE_MAX_RESERVED_TEXTURE_SAMPLER + 3;

    private static final int R_LIGHT_MAP_FIXED_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE1;
    private static final int G_LIGHT_MAP_FIXED_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE2;
    private static final int B_LIGHT_MAP_FIXED_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE3;

    private static final int R_LIGHT_MAP_SHADER_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE1;
    private static final int G_LIGHT_MAP_SHADER_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE6;
    private static final int B_LIGHT_MAP_SHADER_TEXTURE_COORDS_BINDING = GL13.GL_TEXTURE7;
}
