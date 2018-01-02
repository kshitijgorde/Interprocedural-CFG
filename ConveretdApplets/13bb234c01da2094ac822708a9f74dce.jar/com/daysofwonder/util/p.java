// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class p extends RuntimeException
{
    final /* synthetic */ f a;
    
    public p(final f a) {
        this.a = a;
        super("TBBlockingQueue queueing while the queue has been closed");
    }
}
