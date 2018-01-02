// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Identifier
{
    public static String generate(final Random random, final int length) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i = sb.length()) {
            sb.append(Radix.convert(random.nextLong(), 36).toUpperCase());
        }
        sb.setLength(length);
        return sb.toString();
    }
}
