// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class IntegerIO
{
    public static String toBinaryString(final long value, final int bits) {
        final StringBuilder text = new StringBuilder();
        text.append(Long.toBinaryString(value));
        while (text.length() < bits) {
            text.insert(0, "0");
        }
        while (text.length() > bits) {
            text.delete(0, 1);
        }
        return text.toString();
    }
    
    public static String toHexString(final long value, final int bytes) {
        final StringBuilder text = new StringBuilder();
        text.append(Long.toHexString(value));
        while (text.length() < bytes) {
            text.insert(0, "0");
        }
        while (text.length() > bytes) {
            text.delete(0, 1);
        }
        return text.toString().toUpperCase();
    }
    
    public static long valueOfInteger(final String input) throws NumberFormatException {
        final int N = input.length();
        if (N == 0) {
            throw new NumberFormatException("Input Must Have At Least One Character");
        }
        if (N > 2 && input.substring(0, 2).equals("0x")) {
            return Long.parseLong(input.substring(2, N), 16);
        }
        if (N > 1 && input.substring(N - 1, N).equalsIgnoreCase("b")) {
            return Long.parseLong(input.substring(0, N - 1), 2);
        }
        return Long.parseLong(input);
    }
    
    public static void main(final String[] args) {
        System.out.println(toHexString(-4L, 4));
        System.out.println(valueOfInteger("0x123"));
    }
}
