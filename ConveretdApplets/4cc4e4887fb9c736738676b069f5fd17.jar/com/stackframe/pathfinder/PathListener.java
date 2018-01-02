// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.EventListener;

public interface PathListener<T extends Node> extends EventListener
{
    void considered(final PathEvent<T> p0);
}
