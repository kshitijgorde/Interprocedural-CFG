// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.bench;

import org.jcodings.Encoding;
import org.joni.Regex;
import org.joni.Syntax;
import org.jcodings.specific.ASCIIEncoding;

public abstract class AbstractBench
{
    protected void bench(final String _reg, final String _str, final int warmup, final int times) throws Exception {
        final byte[] reg = _reg.getBytes();
        final byte[] str = _str.getBytes();
        final Regex p = new Regex(reg, 0, reg.length, 0, ASCIIEncoding.INSTANCE, Syntax.DEFAULT);
        System.err.println("::: /" + _reg + "/ =~ \"" + _str + "\", " + warmup + " * " + times + " times");
        for (int j = 0; j < warmup; ++j) {
            final long before = System.currentTimeMillis();
            for (int i = 0; i < times; ++i) {
                p.matcher(str, 0, str.length).search(0, str.length, 0);
            }
            final long time = System.currentTimeMillis() - before;
            System.err.println(":  " + time + "ms");
        }
    }
    
    protected void benchBestOf(final String _reg, final String _str, final int warmup, final int times) throws Exception {
        final byte[] reg = _reg.getBytes();
        final byte[] str = _str.getBytes();
        final Regex p = new Regex(reg, 0, reg.length, 0, ASCIIEncoding.INSTANCE, Syntax.DEFAULT);
        System.err.println("::: /" + _reg + "/ =~ \"" + _str + "\", " + warmup + " * " + times + " times");
        long best = Long.MAX_VALUE;
        for (int j = 0; j < warmup; ++j) {
            final long before = System.currentTimeMillis();
            for (int i = 0; i < times; ++i) {
                p.matcher(str, 0, str.length).search(0, str.length, 0);
            }
            final long time = System.currentTimeMillis() - before;
            if (time < best) {
                best = time;
            }
            System.err.print(".");
        }
        System.err.println(":  " + best + "ms");
    }
}
