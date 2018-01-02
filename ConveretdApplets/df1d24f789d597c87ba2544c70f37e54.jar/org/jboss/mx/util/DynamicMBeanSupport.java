// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.MBeanInfo;
import javax.management.AttributeList;
import javax.management.InvalidAttributeValueException;
import javax.management.Attribute;
import javax.management.ReflectionException;
import javax.management.MBeanException;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;

public class DynamicMBeanSupport implements DynamicMBean
{
    public Object getAttribute(final String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return null;
    }
    
    public void setAttribute(final Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
    }
    
    public AttributeList getAttributes(final String[] attributes) {
        return null;
    }
    
    public AttributeList setAttributes(final AttributeList attributes) {
        return null;
    }
    
    public Object invoke(final String actionName, final Object[] params, final String[] signature) throws MBeanException, ReflectionException {
        return null;
    }
    
    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
