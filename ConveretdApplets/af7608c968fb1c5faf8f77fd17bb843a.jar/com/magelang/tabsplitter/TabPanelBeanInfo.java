// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.beans.IndexedPropertyDescriptor;
import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.EventSetDescriptor;
import java.lang.reflect.Method;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;

public class TabPanelBeanInfo extends SimpleBeanInfo
{
    public MethodDescriptor getTabText_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabText", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabText", 1);
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
    
    public MethodDescriptor setFirstVisible_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setFirstVisible", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setFirstVisible", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("value");
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
    
    public MethodDescriptor getSelectedNameMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getSelectedName", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getSelectedName", 0);
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
    
    public EventSetDescriptor tabSelectionEventSetDescriptor() {
        EventSetDescriptor eventSetDescriptor = null;
        try {
            try {
                final MethodDescriptor[] array = { this.tabSelectiontabSelected_commagelangtabsplitterTabSelectionEventMethodEventDescriptor() };
                Method method;
                try {
                    method = getBeanClass().getMethod("addTabSelectionListener", Class.forName("com.magelang.tabsplitter.TabSelectionListener"));
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "addTabSelectionListener", 1);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("removeTabSelectionListener", Class.forName("com.magelang.tabsplitter.TabSelectionListener"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "removeTabSelectionListener", 1);
                }
                eventSetDescriptor = new EventSetDescriptor("tabSelection", Class.forName("com.magelang.tabsplitter.TabSelectionListener"), array, method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                eventSetDescriptor = new EventSetDescriptor(getBeanClass(), "tabSelection", Class.forName("com.magelang.tabsplitter.TabSelectionListener"), new String[] { "tabSelected" }, "addTabSelectionListener", "removeTabSelectionListener");
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return eventSetDescriptor;
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
    
    public MethodDescriptor shiftLeftMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("shiftLeft", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "shiftLeft", 0);
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
    
    public MethodDescriptor update_javaawtGraphicsMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("update", Class.forName("java.awt.Graphics"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "update", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("g");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("update(java.awt.Graphics)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
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
            return new PropertyDescriptor[] { this.borderColorPropertyDescriptor(), this.firstVisiblePropertyDescriptor(), this.fontPropertyDescriptor(), this.insetsPropertyDescriptor(), this.selectedNamePropertyDescriptor(), this.selectedTabNumPropertyDescriptor(), this.tabBackgroundPropertyDescriptor(), this.tabColorPropertyDescriptor(), this.tabColorsPropertyDescriptor(), this.tabTextPropertyDescriptor(), this.visibleComponentNumPropertyDescriptor(), this.visibleComponentPropertyDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor tabSelectiontabSelected_commagelangtabsplitterTabSelectionEventMethodEventDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = Class.forName("com.magelang.tabsplitter.TabSelectionListener").getMethod("tabSelected", Class.forName("com.magelang.tabsplitter.TabSelectionEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(Class.forName("com.magelang.tabsplitter.TabSelectionListener"), "tabSelected", 1);
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
            featureDescriptor.setDisplayName("tabSelected(com.magelang.tabsplitter.TabSelectionEvent)");
            featureDescriptor.setShortDescription("tabSelection.tabSelected(com.magelang.tabsplitter.TabSelectionEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
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
    
    public MethodDescriptor getFirstVisibleMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getFirstVisible", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getFirstVisible", 0);
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
    
    public MethodDescriptor getTabBackgroundMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabBackground", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabBackground", 0);
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
    
    public PropertyDescriptor tabBackgroundPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getTabBackground", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getTabBackground", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setTabBackground", Class.forName("java.awt.Color"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setTabBackground", 1);
                }
                propertyDescriptor = new PropertyDescriptor("tabBackground", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("tabBackground", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
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
    
    public MethodDescriptor setSelectedTabNum_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setSelectedTabNum", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setSelectedTabNum", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("num");
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
    
    public MethodDescriptor setTabColors_javaawtColor__MethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabColors", Class.forName("[Ljava.awt.Color;"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabColors", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("tabColors");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabColors(java.awt.Color[])");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor determineTabTextMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("determineTabText", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "determineTabText", 0);
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
    
    public static String getBeanClassName() {
        return "com.magelang.tabsplitter.TabPanel";
    }
    
    public static Class getBeanClass() {
        return Class.forName("com.magelang.tabsplitter.TabPanel");
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
    
    public MethodDescriptor getInsetsMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getInsets", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getInsets", 0);
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
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[] { this.tabSelectionEventSetDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
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
    
    public PropertyDescriptor insetsPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getInsets", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getInsets", 0);
                }
                propertyDescriptor = new PropertyDescriptor("insets", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("insets", getBeanClass());
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public PropertyDescriptor firstVisiblePropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getFirstVisible", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getFirstVisible", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setFirstVisible", Integer.TYPE);
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setFirstVisible", 1);
                }
                propertyDescriptor = new PropertyDescriptor("firstVisible", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("firstVisible", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor nextMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("next", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "next", 0);
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
    
    public MethodDescriptor getSelectedTabNumMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getSelectedTabNum", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getSelectedTabNum", 0);
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
    
    public MethodDescriptor removeTabSelectionListener_commagelangtabsplitterTabSelectionListenerMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("removeTabSelectionListener", Class.forName("com.magelang.tabsplitter.TabSelectionListener"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "removeTabSelectionListener", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("newListener");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("removeTabSelectionListener(com.magelang.tabsplitter.TabSelectionListener)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor getTabTextMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabText", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabText", 0);
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
    
    public MethodDescriptor getTabColors_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabColors", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabColors", 1);
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
    
    public MethodDescriptor setTabColors_int_javaawtColorMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabColors", Integer.TYPE, Class.forName("java.awt.Color"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabColors", 2);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("index");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("tabColor");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabColors(int, java.awt.Color)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor tabColorPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getTabColor", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getTabColor", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setTabColor", Class.forName("java.awt.Color"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setTabColor", 1);
                }
                propertyDescriptor = new PropertyDescriptor("tabColor", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("tabColor", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor shiftRightMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("shiftRight", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "shiftRight", 0);
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
    
    public IndexedPropertyDescriptor tabColorsPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getTabColors", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getTabColors", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setTabColors", Class.forName("[Ljava.awt.Color;"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setTabColors", 1);
                }
                Method method3;
                try {
                    method3 = getBeanClass().getMethod("getTabColors", Integer.TYPE);
                }
                catch (Throwable t3) {
                    this.handleException(t3);
                    method3 = findMethod(getBeanClass(), "getTabColors", 1);
                }
                Method method4;
                try {
                    method4 = getBeanClass().getMethod("setTabColors", Integer.TYPE, Class.forName("java.awt.Color"));
                }
                catch (Throwable t4) {
                    this.handleException(t4);
                    method4 = findMethod(getBeanClass(), "setTabColors", 2);
                }
                propertyDescriptor = new IndexedPropertyDescriptor("tabColors", method, method2, method3, method4);
            }
            catch (Throwable t5) {
                this.handleException(t5);
                propertyDescriptor = new IndexedPropertyDescriptor("tabColors", getBeanClass());
            }
            propertyDescriptor.setPropertyEditorClass(Class.forName("com.magelang.tabsplitter.TabColorEditor"));
        }
        catch (Throwable t6) {
            this.handleException(t6);
        }
        return (IndexedPropertyDescriptor)propertyDescriptor;
    }
    
    public MethodDescriptor showPhysicalTab_intMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("showPhysicalTab", Integer.TYPE);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "showPhysicalTab", 1);
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
    
    public MethodDescriptor setBorderColor_javaawtColorMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setBorderColor", Class.forName("java.awt.Color"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setBorderColor", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("borderColor");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setBorderColor(java.awt.Color)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor paint_javaawtGraphicsMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("paint", Class.forName("java.awt.Graphics"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "paint", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("g");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("paint(java.awt.Graphics)");
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
    
    public IndexedPropertyDescriptor tabTextPropertyDescriptor() {
        IndexedPropertyDescriptor indexedPropertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getTabText", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getTabText", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setTabText", Class.forName("[Ljava.lang.String;"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setTabText", 1);
                }
                Method method3;
                try {
                    method3 = getBeanClass().getMethod("getTabText", Integer.TYPE);
                }
                catch (Throwable t3) {
                    this.handleException(t3);
                    method3 = findMethod(getBeanClass(), "getTabText", 1);
                }
                Method method4;
                try {
                    method4 = getBeanClass().getMethod("setTabText", Integer.TYPE, Class.forName("java.lang.String"));
                }
                catch (Throwable t4) {
                    this.handleException(t4);
                    method4 = findMethod(getBeanClass(), "setTabText", 2);
                }
                indexedPropertyDescriptor = new IndexedPropertyDescriptor("tabText", method, method2, method3, method4);
            }
            catch (Throwable t5) {
                this.handleException(t5);
                indexedPropertyDescriptor = new IndexedPropertyDescriptor("tabText", getBeanClass());
            }
        }
        catch (Throwable t6) {
            this.handleException(t6);
        }
        return indexedPropertyDescriptor;
    }
    
    public MethodDescriptor mouseClicked_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseClicked", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseClicked", 1);
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
            featureDescriptor.setShortDescription("mouseClicked(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    private void handleException(final Throwable t) {
    }
    
    public MethodDescriptor mouseEntered_javaawteventMouseEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("mouseEntered", Class.forName("java.awt.event.MouseEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "mouseEntered", 1);
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
            featureDescriptor.setShortDescription("mouseEntered(java.awt.event.MouseEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor setTabText_javalangString__MethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabText", Class.forName("[Ljava.lang.String;"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabText", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("tabText");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabText(java.lang.String[])");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
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
    
    public MethodDescriptor setTabText_int_javalangStringMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabText", Integer.TYPE, Class.forName("java.lang.String"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabText", 2);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("index");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("tabText");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabText(int, java.lang.String)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor setTabColor_javaawtColorMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabColor", Class.forName("java.awt.Color"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabColor", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("color");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabColor(java.awt.Color)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor previousMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("previous", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "previous", 0);
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
    
    public MethodDescriptor getTabColorMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabColor", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabColor", 0);
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
    
    public PropertyDescriptor borderColorPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getBorderColor", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getBorderColor", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setBorderColor", Class.forName("java.awt.Color"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setBorderColor", 1);
                }
                propertyDescriptor = new PropertyDescriptor("borderColor", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("borderColor", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor getTabColorsMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTabColors", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTabColors", 0);
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
    
    public PropertyDescriptor selectedNamePropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getSelectedName", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getSelectedName", 0);
                }
                propertyDescriptor = new PropertyDescriptor("selectedName", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("selectedName", getBeanClass());
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public PropertyDescriptor selectedTabNumPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getSelectedTabNum", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getSelectedTabNum", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setSelectedTabNum", Integer.TYPE);
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setSelectedTabNum", 1);
                }
                propertyDescriptor = new PropertyDescriptor("selectedTabNum", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("selectedTabNum", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor addTabSelectionListener_commagelangtabsplitterTabSelectionListenerMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("addTabSelectionListener", Class.forName("com.magelang.tabsplitter.TabSelectionListener"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "addTabSelectionListener", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("newListener");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("addTabSelectionListener(com.magelang.tabsplitter.TabSelectionListener)");
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
    
    public MethodDescriptor setTabBackground_javaawtColorMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setTabBackground", Class.forName("java.awt.Color"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setTabBackground", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("color");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setTabBackground(java.awt.Color)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            return new MethodDescriptor[] { this.actionPerformed_javaawteventActionEventMethodDescriptor(), this.addTabSelectionListener_commagelangtabsplitterTabSelectionListenerMethodDescriptor(), this.determineTabTextMethodDescriptor(), this.getBorderColorMethodDescriptor(), this.getFirstVisibleMethodDescriptor(), this.getInsetsMethodDescriptor(), this.getSelectedNameMethodDescriptor(), this.getSelectedTabNumMethodDescriptor(), this.getTabBackgroundMethodDescriptor(), this.getTabColorMethodDescriptor(), this.getTabColors_intMethodDescriptor(), this.getTabColorsMethodDescriptor(), this.getTabText_intMethodDescriptor(), this.getTabTextMethodDescriptor(), this.getVisibleComponentMethodDescriptor(), this.getVisibleComponentNumMethodDescriptor(), this.mouseClicked_javaawteventMouseEventMethodDescriptor(), this.mouseEntered_javaawteventMouseEventMethodDescriptor(), this.mouseExited_javaawteventMouseEventMethodDescriptor(), this.mousePressed_javaawteventMouseEventMethodDescriptor(), this.mouseReleased_javaawteventMouseEventMethodDescriptor(), this.nextMethodDescriptor(), this.paint_javaawtGraphicsMethodDescriptor(), this.previousMethodDescriptor(), this.remove_intMethodDescriptor(), this.remove_javaawtComponentMethodDescriptor(), this.removeAllMethodDescriptor(), this.removeTabSelectionListener_commagelangtabsplitterTabSelectionListenerMethodDescriptor(), this.setBorderColor_javaawtColorMethodDescriptor(), this.setFirstVisible_intMethodDescriptor(), this.setFont_javaawtFontMethodDescriptor(), this.setSelectedTabNum_intMethodDescriptor(), this.setTabBackground_javaawtColorMethodDescriptor(), this.setTabColor_javaawtColorMethodDescriptor(), this.setTabColors_int_javaawtColorMethodDescriptor(), this.setTabColors_javaawtColor__MethodDescriptor(), this.setTabText_int_javalangStringMethodDescriptor(), this.setTabText_javalangString__MethodDescriptor(), this.shiftLeftMethodDescriptor(), this.shiftRightMethodDescriptor(), this.show_intMethodDescriptor(), this.show_javaawtComponentMethodDescriptor(), this.show_javalangStringMethodDescriptor(), this.showPhysicalTab_intMethodDescriptor(), this.update_javaawtGraphicsMethodDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor getBorderColorMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getBorderColor", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getBorderColor", 0);
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
}
