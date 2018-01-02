// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Decode implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        for (int i = 2; i < args.length - 1; ++i) {
            if (args[1].equals(args[i])) {
                return args[++i];
            }
        }
        return args[args.length - 1];
    }
}
