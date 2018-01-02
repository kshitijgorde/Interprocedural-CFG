// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import java.lang.reflect.InvocationTargetException;
import com.pluraprocessing.common.domain.RequestedWork;

public interface IStartPlura
{
    void run(final RequestedWork p0) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;
    
    void stopPluraThreads(final boolean p0, final boolean p1);
    
    void stopComputeInAllPluraThreads();
    
    boolean pluraIsStopped();
}
