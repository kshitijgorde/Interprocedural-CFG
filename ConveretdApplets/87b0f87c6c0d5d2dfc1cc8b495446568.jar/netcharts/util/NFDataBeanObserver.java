// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Hashtable;
import java.util.Vector;

public interface NFDataBeanObserver
{
    void dataBeanLoadData(final Object p0, final Object p1, final String p2, final Vector p3) throws Exception;
    
    void dataBeanLoadParams(final Object p0, final Object p1, final Object p2) throws Exception;
    
    void dataBeanUserInput(final Object p0, final Object p1, final String p2) throws Exception;
    
    void dataBeanFailure(final Object p0, final String p1);
    
    void dataBeanRequestInitialParamsLoadedNotification(final Object p0);
    
    Hashtable dataBeanGetExtraParams(final Object p0);
}
