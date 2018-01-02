// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

public class Negate implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        Boolean b = Boolean.valueOf(args[1]);
        b = !b;
        return b.toString();
    }
}
