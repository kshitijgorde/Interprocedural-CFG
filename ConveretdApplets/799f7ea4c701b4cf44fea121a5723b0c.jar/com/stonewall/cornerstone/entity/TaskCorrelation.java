// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

public class TaskCorrelation
{
    public final String tid;
    public final String suffix;
    
    public TaskCorrelation(final String tid, final String suffix) {
        this.tid = tid;
        this.suffix = suffix;
    }
    
    public TaskCorrelation(final String s) {
        int marker = s.indexOf(45);
        if (marker != -1) {
            this.tid = s.substring(0, marker++);
            this.suffix = s.substring(marker);
        }
        else {
            this.tid = s;
            this.suffix = "";
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.tid) + "-" + this.suffix;
    }
}
