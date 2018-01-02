// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class D extends RuntimeException
{
    final /* synthetic */ f a;
    
    public D(final f a) {
        this.a = a;
        super("TBBlockingQueue.enqueue has reached timeout");
    }
}
