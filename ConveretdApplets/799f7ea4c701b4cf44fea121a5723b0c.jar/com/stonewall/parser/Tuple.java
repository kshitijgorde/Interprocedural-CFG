// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.util.concurrent.locks.Lock;

public class Tuple
{
    public static char[] delimiter;
    private static Lock lock;
    private static Pattern p;
    
    static {
        Tuple.delimiter = new char[] { '\u0003' };
        Tuple.lock = new ReentrantLock();
        Tuple.p = Pattern.compile(new String(Tuple.delimiter));
    }
    
    public static boolean isTuple(final String s) {
        return delimiters(s) > 0;
    }
    
    public static String join(final String[] list) {
        final StringBuilder sb = new StringBuilder();
        for (final String s : list) {
            sb.append(s);
            sb.append(Tuple.delimiter);
        }
        return sb.toString();
    }
    
    public static String join(final Collection<String> list) {
        final StringBuilder sb = new StringBuilder();
        for (final String s : list) {
            sb.append(s);
            sb.append(Tuple.delimiter);
        }
        return sb.toString();
    }
    
    public static String[] split(final String s) {
        Tuple.lock.lock();
        try {
            return Tuple.p.split(s);
        }
        finally {
            Tuple.lock.unlock();
        }
    }
    
    public static int delimiters(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == Tuple.delimiter[0]) {
                ++n;
            }
        }
        return n;
    }
}
