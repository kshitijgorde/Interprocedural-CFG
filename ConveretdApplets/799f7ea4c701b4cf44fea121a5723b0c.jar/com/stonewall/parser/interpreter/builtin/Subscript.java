// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Tuple;
import com.stonewall.parser.Function;

class Subscript implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String text = args[1];
        int index = Integer.valueOf(args[2]);
        final String[] tokens = Tuple.split(text);
        if (Tuple.delimiters(text) > 0 && index < tokens.length) {
            return tokens[index].trim();
        }
        return text.substring(index, ++index);
    }
}
