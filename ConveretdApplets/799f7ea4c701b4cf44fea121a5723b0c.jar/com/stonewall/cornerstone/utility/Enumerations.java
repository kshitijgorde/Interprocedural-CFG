// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;

public class Enumerations
{
    public static <T> List<T> list(final Enumeration<T> en) {
        final List<T> result = new ArrayList<T>();
        while (en.hasMoreElements()) {
            result.add(en.nextElement());
        }
        return result;
    }
}
