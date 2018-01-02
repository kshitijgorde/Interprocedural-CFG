// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j;

import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory
{
    static IMarkerFactory markerFactory;
    
    public static Marker getMarker(final String name) {
        return MarkerFactory.markerFactory.getMarker(name);
    }
    
    public static Marker getDetachedMarker(final String name) {
        return MarkerFactory.markerFactory.getDetachedMarker(name);
    }
    
    public static IMarkerFactory getIMarkerFactory() {
        return MarkerFactory.markerFactory;
    }
    
    static {
        try {
            MarkerFactory.markerFactory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
        }
        catch (Exception e) {
            Util.reportFailure("Could not instantiate instance of class [" + StaticMarkerBinder.SINGLETON.getMarkerFactoryClassStr() + "]", e);
        }
    }
}
