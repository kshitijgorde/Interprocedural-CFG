// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Hash implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]);
        }
        final String s = sb.toString();
        return Integer.toHexString(s.hashCode()).toUpperCase();
    }
}
