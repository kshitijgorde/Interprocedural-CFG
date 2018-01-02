// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Tuple;
import com.stonewall.parser.Function;

class Length implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String[] array = Tuple.split(args[1]);
        final int len = (array.length > 1) ? array.length : args[1].length();
        return String.valueOf(len);
    }
}
