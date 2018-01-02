// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.util.EventObject;

public class StateChangeEvent extends EventObject
{
    public static final int READY = 0;
    public static final int START = 1;
    public static final int REMOTE = 2;
    public static final int LOCAL = 3;
    public static final int ERRORED = 4;
    public static final int FINISHED = 5;
    public static final int STOPPED = 6;
    public int thecurrentstate;
    
    public StateChangeEvent(final Object o, final int thecurrentstate) {
        super(o);
        this.thecurrentstate = 0;
        this.thecurrentstate = thecurrentstate;
    }
}
