/*
 * Copyright (c) 2023 FalsePattern, Ven
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/
 * or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

package com.falsepattern.rple.internal.common.config.adapter;

import com.falsepattern.rple.internal.common.config.container.BlockReference;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.NoArgsConstructor;
import lombok.val;

import static com.falsepattern.rple.internal.common.config.ColorConfigLoader.colorConfigGSON;
import static com.falsepattern.rple.internal.common.config.ColorConfigLoader.logParsingError;

@NoArgsConstructor
public final class BlockReferenceJSONAdapter extends TypeAdapter<BlockReference> {
    @Override
    public void write(JsonWriter out, BlockReference value) {
        colorConfigGSON().toJson(value.toString(), String.class, out);
    }

    @Override
    public BlockReference read(JsonReader in) {
        val blockID = colorConfigGSON().<String>fromJson(in, String.class);
        val blockReference = new BlockReference(blockID);
        if (!blockReference.isValid())
            logParsingError("Invalid block reference: {}", blockID);
        return blockReference;
    }
}
