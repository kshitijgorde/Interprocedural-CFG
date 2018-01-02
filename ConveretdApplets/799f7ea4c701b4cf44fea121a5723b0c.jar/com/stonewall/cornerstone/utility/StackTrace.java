// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.BitSet;

public class StackTrace
{
    private final int skip;
    private final BitSet filter;
    private final StackTraceElement[] trace;
    static final int Depth = 4;
    static final int Skip = 3;
    
    public StackTrace(final int skip) {
        this.filter = new BitSet();
        this.skip = 3 + skip;
        this.trace = Thread.currentThread().getStackTrace();
    }
    
    public StackTrace() {
        this(0);
    }
    
    @Override
    public String toString() {
        return this.toString(4);
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    public StackTrace filter(final String regex) {
        this.filter.clear();
        final Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < this.trace.length; ++i) {
            final String s = this.trace[i].toString();
            final Matcher matcher = pattern.matcher(s);
            final boolean flag = matcher.find();
            this.filter.set(i, flag);
        }
        return this;
    }
    
    public String toString(final int depth) {
        final StringBuilder sb = new StringBuilder();
        final List<StackTraceElement> filtered = new ArrayList<StackTraceElement>();
        for (int i = 0; i < this.trace.length; ++i) {
            if (this.filter.get(i)) {
                filtered.add(this.trace[i]);
            }
        }
        for (int i = this.skip; i < filtered.size() && i < this.skip + depth; ++i) {
            sb.append('\t');
            sb.append(filtered.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public String frameAt(final int n) {
        if (n > 0 && n < this.trace.length) {
            return this.trace[n].toString();
        }
        return null;
    }
    
    public int length() {
        return this.trace.length;
    }
}
