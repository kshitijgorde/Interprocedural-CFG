// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.List;
import java.util.EventObject;

public abstract class PathEvent<T extends Node> extends EventObject
{
    protected PathEvent(final Object source) {
        super(source);
    }
    
    public abstract List<T> getPath();
}
