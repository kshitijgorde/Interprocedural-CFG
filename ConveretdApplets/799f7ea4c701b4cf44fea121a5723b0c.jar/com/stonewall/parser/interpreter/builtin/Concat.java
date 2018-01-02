// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Concat implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]);
        }
        return sb.toString();
    }
}
