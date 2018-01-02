// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.scheduler;

import org.xmodel.IModelObject;
import java.util.Map;

public interface IScheduledJob
{
    void run();
    
    void setData(final Map<String, IModelObject> p0);
    
    Map<String, IModelObject> getData();
    
    String getName();
}
