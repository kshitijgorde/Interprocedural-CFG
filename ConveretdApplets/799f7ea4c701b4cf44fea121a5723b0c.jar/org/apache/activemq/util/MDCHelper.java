// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.util.Hashtable;
import org.slf4j.MDC;
import java.util.Map;

public class MDCHelper
{
    public static Map getCopyOfContextMap() {
        Map map = MDC.getCopyOfContextMap();
        if (map == null) {
            map = new Hashtable();
        }
        return map;
    }
    
    public static void setContextMap(Map map) {
        if (map == null) {
            map = new Hashtable();
        }
        MDC.setContextMap(map);
    }
}
