// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

class Statistic
{
    String name;
    long count;
    long duration;
    long longest;
    
    Statistic(final String name) {
        this.count = 0L;
        this.duration = 0L;
        this.longest = 0L;
        this.name = name;
    }
    
    void executed(final long tm) {
        ++this.count;
        this.duration += tm;
        if (tm > this.longest) {
            this.longest = tm;
        }
    }
    
    float avg() {
        return (this.count == 0L) ? 0L : (this.duration / this.count);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append("(" + this.name + ") ");
        sb.append("count=" + this.count);
        sb.append("/duraton=" + this.duration + " (ms)");
        sb.append("/avg=" + this.avg() + " (ms)");
        sb.append("/longest=" + this.longest + " (ms)");
        sb.append(']');
        return sb.toString();
    }
}
