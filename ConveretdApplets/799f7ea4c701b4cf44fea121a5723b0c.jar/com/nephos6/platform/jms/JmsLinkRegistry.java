// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.jms;

import java.util.Collections;
import java.util.HashMap;
import com.stonewall.cornerstone.xidget.JmsLink;
import java.util.Map;

public class JmsLinkRegistry
{
    private static Map<String, JmsLink> map;
    
    static {
        JmsLinkRegistry.map = Collections.synchronizedMap(new HashMap<String, JmsLink>());
    }
    
    public static void register(final JmsLink link) {
        JmsLinkRegistry.map.put(link.getIncomingQueue(), link);
        JmsLinkRegistry.map.put(link.getOutgoingQueue(), link);
    }
    
    public static JmsLink getLinkByQueue(final String queue) {
        return JmsLinkRegistry.map.get(queue);
    }
}
