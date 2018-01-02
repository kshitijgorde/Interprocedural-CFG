// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import org.jruby.Ruby;
import java.lang.ref.SoftReference;

public class ParserStats implements ParserStatsMBean
{
    private final SoftReference<Ruby> ruby;
    private AtomicInteger totalParseTime;
    private AtomicLong totalParsedBytes;
    private AtomicInteger totalEvalParses;
    private AtomicInteger totalLoadParses;
    private AtomicInteger totalJRubyModuleParses;
    
    public ParserStats(final Ruby ruby) {
        this.totalParseTime = new AtomicInteger(0);
        this.totalParsedBytes = new AtomicLong(0L);
        this.totalEvalParses = new AtomicInteger(0);
        this.totalLoadParses = new AtomicInteger(0);
        this.totalJRubyModuleParses = new AtomicInteger(0);
        this.ruby = new SoftReference<Ruby>(ruby);
    }
    
    public void addParseTime(final int time) {
        this.totalParseTime.addAndGet(time);
    }
    
    public void addParsedBytes(final int bytes) {
        this.totalParsedBytes.addAndGet(bytes);
    }
    
    public void addEvalParse() {
        this.totalEvalParses.incrementAndGet();
    }
    
    public void addLoadParse() {
        this.totalLoadParses.incrementAndGet();
    }
    
    public void addJRubyModuleParse() {
        this.totalJRubyModuleParses.incrementAndGet();
    }
    
    public double getTotalParseTime() {
        final Ruby runtime = this.ruby.get();
        if (runtime == null) {
            return 0.0;
        }
        return runtime.getParser().getTotalTime() / 1.0E9;
    }
    
    public long getTotalParsedBytes() {
        final Ruby runtime = this.ruby.get();
        if (runtime == null) {
            return 0L;
        }
        return runtime.getParser().getTotalBytes();
    }
    
    public double getParseTimePerKB() {
        final long totalBytes = this.getTotalParsedBytes();
        if (totalBytes == 0L) {
            return 0.0;
        }
        return this.getTotalParseTime() / (totalBytes / 1000.0);
    }
    
    public int getNumberOfEvalParses() {
        return this.totalEvalParses.get();
    }
    
    public int getNumberOfLoadParses() {
        return this.totalLoadParses.get();
    }
}
