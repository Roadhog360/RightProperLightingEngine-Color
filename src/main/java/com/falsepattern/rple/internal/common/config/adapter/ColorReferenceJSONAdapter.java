/*
 * Copyright (c) 2023 FalsePattern, Ven
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/
 * or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

package com.falsepattern.rple.internal.common.config.adapter;

import com.falsepattern.rple.internal.common.config.container.ColorReference;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.NoArgsConstructor;
import lombok.val;

import static com.falsepattern.rple.internal.common.config.ColorConfigLoader.colorConfigGSON;
import static com.falsepattern.rple.internal.common.config.ColorConfigLoader.logParsingError;

@NoArgsConstructor
public final class ColorReferenceJSONAdapter extends TypeAdapter<ColorReference> {
    @Override
    public void write(JsonWriter out, ColorReference value) {
        colorConfigGSON().toJson(value.toString(), String.class, out);
    }

    @Override
    public ColorReference read(JsonReader in) {
        val color = colorConfigGSON().<String>fromJson(in, String.class);
        val colorReference = new ColorReference(color);
        if (!colorReference.isValid())
            logParsingError("Invalid color reference: {}", color);
        return colorReference;
    }
}
