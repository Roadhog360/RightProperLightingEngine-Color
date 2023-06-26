/*
 * Copyright (c) 2023 FalsePattern, Ven
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/
 * or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

package com.falsepattern.rple.internal.common.storage;

import com.falsepattern.lumina.api.ILumiWorld;
import com.falsepattern.lumina.api.ILumiWorldProvider;
import com.falsepattern.rple.api.color.ColorChannel;
import lombok.AllArgsConstructor;
import lombok.val;
import net.minecraft.world.World;

@AllArgsConstructor
public final class ColoredWorldProvider implements ILumiWorldProvider {
    private final ColorChannel channel;

    @Override
    public ILumiWorld getWorld(World world) {
        val carrierWorld = (ColoredCarrierWorld) world;
        return carrierWorld.coloredWorld(channel);
    }
}
