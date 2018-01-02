// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class Hex
{
    public static final String hex2(final int n) {
        if (n < 16) {
            return "0" + Integer.toString(n, 16);
        }
        return Integer.toString(n, 16);
    }
}
