// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Substring implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String s = args[1];
        final int start = Integer.valueOf(args[2]);
        int end = s.length();
        if (args.length > 3 && args[3] != null) {
            end = Integer.valueOf(args[3]);
        }
        if (end < 0) {
            end += s.length();
        }
        return s.substring(start, end);
    }
}
