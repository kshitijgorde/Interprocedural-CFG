// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;

public class TabSplitterBeanInfo extends SimpleBeanInfo
{
    public MethodDescriptor remove_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("remove", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "remove", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("index");
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public PropertyDescriptor visibleComponentPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getVisibleComponent", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getVisibleComponent", 0);
                }
                propertyDescriptor = new PropertyDescriptor("visibleComponent", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("visibleComponent", getBeanClass());
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor setFont_javaawtFontMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setFont", Class.forName("java.awt.Font"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setFont", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("f");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setFont(java.awt.Font)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor swapOrientationMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("swapOrientation", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "swapOrientation", 0);
            }
            try {
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[0]);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public MethodDescriptor remove_javaawtComponentMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("remove", Class.forName("java.awt.Component"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "remove", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("comp");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("remove(java.awt.Component)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor visibleComponentNumPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getVisibleComponentNum", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getVisibleComponentNum", 0);
                }
                propertyDescriptor = new PropertyDescriptor("visibleComponentNum", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("visibleComponentNum", getBeanClass());
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor getVisibleComponentNumMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getVisibleComponentNum", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getVisibleComponentNum", 0);
            }
            try {
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[0]);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public MethodDescriptor separateTabs_javalangString_javaawtComponent_javalangString_javaawtComponent_commagelangtabsplitterSplitterPanelMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("separateTabs", Class.forName("java.lang.String"), Class.forName("java.awt.Component"), Class.forName("java.lang.String"), Class.forName("java.awt.Component"), Class.forName("com.magelang.tabsplitter.SplitterPanel"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "separateTabs", 5);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("name1");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("comp1");
                final ParameterDescriptor parameterDescriptor3 = new ParameterDescriptor();
                parameterDescriptor3.setName("arg3");
                parameterDescriptor3.setDisplayName("name2");
                final ParameterDescriptor parameterDescriptor4 = new ParameterDescriptor();
                parameterDescriptor4.setName("arg4");
                parameterDescriptor4.setDisplayName("comp2");
                final ParameterDescriptor parameterDescriptor5 = new ParameterDescriptor();
                parameterDescriptor5.setName("arg5");
                parameterDescriptor5.setDisplayName("p");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2, parameterDescriptor3, parameterDescriptor4, parameterDescriptor5 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("separateTabs(java.lang.String, java.awt.Component, java.lang.String, java.awt.Component, com.magelang.tabsplitter.SplitterPanel)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor fontPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getFont", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getFont", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setFont", Class.forName("java.awt.Font"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setFont", 1);
                }
                propertyDescriptor = new PropertyDescriptor("font", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("font", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[0];
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor mouseDragged_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseDragged", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseDragged", 1);
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
            featureDescriptor.setShortDescription("mouseDragged(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public BeanInfo[] getAdditionalBeanInfo() {
        try {
            return new BeanInfo[] { Introspector.getBeanInfo(Class.forName("com.magelang.tabsplitter.TabPanel")) };
        }
        catch (IntrospectionException ex) {
            System.err.println(ex);
            return null;
        }
    }
    
    public MethodDescriptor removeAllMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("removeAll", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "removeAll", 0);
            }
            try {
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[0]);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public MethodDescriptor actionPerformed_javaawteventActionEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("actionPerformed", Class.forName("java.awt.event.ActionEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "actionPerformed", 1);
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
            featureDescriptor.setShortDescription("actionPerformed(java.awt.event.ActionEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor show_javaawtComponentMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("show", Class.forName("java.awt.Component"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "show", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("comp");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("show(java.awt.Component)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] { this.fontPropertyDescriptor(), this.visibleComponentNumPropertyDescriptor(), this.visibleComponentPropertyDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor mouseMoved_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseMoved", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseMoved", 1);
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
            featureDescriptor.setShortDescription("mouseMoved(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor mousePressed_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mousePressed", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mousePressed", 1);
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
            featureDescriptor.setShortDescription("mousePressed(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    private void handleException(final Throwable t) {
    }
    
    public static String getBeanClassName() {
        return "com.magelang.tabsplitter.TabSplitter";
    }
    
    public MethodDescriptor show_javalangStringMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("show", Class.forName("java.lang.String"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "show", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("tabName");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("show(java.lang.String)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor separateTabs_javalangString_javaawtComponent_javalangString_javalangString_javaawtComponent_javalangString_commagelangtabsplitterSplitterPanelMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("separateTabs", Class.forName("java.lang.String"), Class.forName("java.awt.Component"), Class.forName("java.lang.String"), Class.forName("java.lang.String"), Class.forName("java.awt.Component"), Class.forName("java.lang.String"), Class.forName("com.magelang.tabsplitter.SplitterPanel"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "separateTabs", 7);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("name1");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("comp1");
                final ParameterDescriptor parameterDescriptor3 = new ParameterDescriptor();
                parameterDescriptor3.setName("arg3");
                parameterDescriptor3.setDisplayName("exp1");
                final ParameterDescriptor parameterDescriptor4 = new ParameterDescriptor();
                parameterDescriptor4.setName("arg4");
                parameterDescriptor4.setDisplayName("name2");
                final ParameterDescriptor parameterDescriptor5 = new ParameterDescriptor();
                parameterDescriptor5.setName("arg5");
                parameterDescriptor5.setDisplayName("comp2");
                final ParameterDescriptor parameterDescriptor6 = new ParameterDescriptor();
                parameterDescriptor6.setName("arg6");
                parameterDescriptor6.setDisplayName("exp2");
                final ParameterDescriptor parameterDescriptor7 = new ParameterDescriptor();
                parameterDescriptor7.setName("arg7");
                parameterDescriptor7.setDisplayName("p");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2, parameterDescriptor3, parameterDescriptor4, parameterDescriptor5, parameterDescriptor6, parameterDescriptor7 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("separateTabs(java.lang.String, java.awt.Component, java.lang.String, java.lang.String, java.awt.Component, java.lang.String, com.magelang.tabsplitter.SplitterPanel)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public static Class getBeanClass() {
        return Class.forName("com.magelang.tabsplitter.TabSplitter");
    }
    
    public MethodDescriptor getVisibleComponentMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getVisibleComponent", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getVisibleComponent", 0);
            }
            try {
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[0]);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            return new MethodDescriptor[] { this.actionPerformed_javaawteventActionEventMethodDescriptor(), this.getVisibleComponentMethodDescriptor(), this.getVisibleComponentNumMethodDescriptor(), this.mouseDragged_javaawteventMouseEventMethodDescriptor(), this.mouseExited_javaawteventMouseEventMethodDescriptor(), this.mouseMoved_javaawteventMouseEventMethodDescriptor(), this.mousePressed_javaawteventMouseEventMethodDescriptor(), this.mouseReleased_javaawteventMouseEventMethodDescriptor(), this.remove_intMethodDescriptor(), this.remove_javaawtComponentMethodDescriptor(), this.removeAllMethodDescriptor(), this.separateTabs_javalangString_javaawtComponent_javalangString_javalangString_javaawtComponent_javalangString_commagelangtabsplitterSplitterPanelMethodDescriptor(), this.setFont_javaawtFontMethodDescriptor(), this.show_intMethodDescriptor(), this.show_javaawtComponentMethodDescriptor(), this.show_javalangStringMethodDescriptor(), this.swapOrientationMethodDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor show_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("show", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "show", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("n");
                methodDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                methodDescriptor = new MethodDescriptor(method);
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return methodDescriptor;
    }
    
    public MethodDescriptor mouseReleased_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseReleased", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseReleased", 1);
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
            featureDescriptor.setShortDescription("mouseReleased(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor mouseExited_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseExited", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseExited", 1);
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
            featureDescriptor.setShortDescription("mouseExited(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
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
}
