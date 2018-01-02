// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

import org.w3c.dom.Element;

public interface ListenerServiceMBean extends ServiceMBean
{
    public static final String SL_ROOT_ELEMENT = "subscription-list";
    public static final String SL_MBEAN_ELEMENT = "mbean";
    public static final String SL_FILTER_ELEMENT = "filter";
    public static final String SL_NOTIFICATION_ELEMENT = "notification";
    public static final String SL_MBEAN_NAME_ATTRIBUTE = "name";
    public static final String SL_MBEAN_HANDBACK_ATTRIBUTE = "handback";
    public static final String SL_FILTER_FACTORY_ATTRIBUTE = "factory";
    public static final String SL_NOTIFICATION_TYPE_ATTRIBUTE = "type";
    
    void setSubscriptionList(final Element p0);
}
