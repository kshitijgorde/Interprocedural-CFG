// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Function;

class Subnet implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        int result = 0;
        final String[] octets = args[1].split("\\.");
        for (int i = 0; i < octets.length; ++i) {
            result += Integer.bitCount(new Integer(octets[i]));
        }
        return String.valueOf(result);
    }
}
