// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.beans.PropertyDescriptor;
import java.beans.EventSetDescriptor;
import java.lang.reflect.Method;
import java.beans.FeatureDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;

public class SwapButtonBeanInfo extends SimpleBeanInfo
{
    public MethodDescriptor actionactionPerformed_javaawteventActionEventMethodEventDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = Class.forName("java.awt.event.ActionListener").getMethod("actionPerformed", Class.forName("java.awt.event.ActionEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(Class.forName("java.awt.event.ActionListener"), "actionPerformed", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("e");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("action.actionPerformed(java.awt.event.ActionEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public EventSetDescriptor actionEventSetDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            try {
                final MethodDescriptor[] array = { this.actionactionPerformed_javaawteventActionEventMethodEventDescriptor() };
                Method method;
                try {
                    method = getBeanClass().getMethod("addActionListener", Class.forName("java.awt.event.ActionListener"));
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "addActionListener", 1);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("removeActionListener", Class.forName("java.awt.event.ActionListener"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "removeActionListener", 1);
                }
                featureDescriptor = new EventSetDescriptor("action", Class.forName("java.awt.event.ActionListener"), array, method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                featureDescriptor = new EventSetDescriptor(getBeanClass(), "action", Class.forName("java.awt.event.ActionListener"), new String[] { "actionPerformed" }, "addActionListener", "removeActionListener");
            }
            featureDescriptor.setDisplayName("actionPerformed");
            featureDescriptor.setShortDescription("The button was pressed");
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return (EventSetDescriptor)featureDescriptor;
    }
    
    public static String getBeanClassName() {
        return "com.magelang.SwapButton";
    }
    
    private void handleException(final Throwable t) {
    }
    
    public static Class getBeanClass() {
        return Class.forName("com.magelang.splitter.SwapButton");
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            return new MethodDescriptor[0];
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[] { this.actionEventSetDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public static Method findMethod(final Class clazz, final String s, final int n) {
        try {
            final Method[] methods = clazz.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                final Method method = methods[i];
                if (method.getParameterTypes().length == n && method.getName().equals(s)) {
                    return method;
                }
            }
        }
        catch (Throwable t) {
            return null;
        }
        return null;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[0];
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
}
