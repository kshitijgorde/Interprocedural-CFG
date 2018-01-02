// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.editors;

import java.beans.EventSetDescriptor;
import java.beans.FeatureDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ColorSelectorBeanInfo extends SimpleBeanInfo
{
    public PropertyDescriptor javaInitializationStringPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getJavaInitializationString", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getJavaInitializationString", 0);
                }
                propertyDescriptor = new PropertyDescriptor("javaInitializationString", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("javaInitializationString", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public PropertyDescriptor selectedColorPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getSelectedColor", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getSelectedColor", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setSelectedColor", Class.forName("java.awt.Color"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setSelectedColor", 1);
                }
                propertyDescriptor = new PropertyDescriptor("selectedColor", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("selectedColor", getBeanClass());
            }
            propertyDescriptor.setBound(true);
            propertyDescriptor.setDisplayName("selected color");
            propertyDescriptor.setShortDescription("The color that the user selected");
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor addPropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("addPropertyChangeListener", Class.forName("java.beans.PropertyChangeListener"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "addPropertyChangeListener", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("listener");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("addPropertyChangeListener(java.beans.PropertyChangeListener)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public EventSetDescriptor propertyChangeEventSetDescriptor() {
        EventSetDescriptor eventSetDescriptor = null;
        try {
            try {
                final MethodDescriptor[] array = { this.propertyChangepropertyChange_javabeansPropertyChangeEventMethodEventDescriptor() };
                Method method;
                try {
                    method = getBeanClass().getMethod("addPropertyChangeListener", Class.forName("java.beans.PropertyChangeListener"));
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "addPropertyChangeListener", 1);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("removePropertyChangeListener", Class.forName("java.beans.PropertyChangeListener"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "removePropertyChangeListener", 1);
                }
                eventSetDescriptor = new EventSetDescriptor("propertyChange", Class.forName("java.beans.PropertyChangeListener"), array, method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                eventSetDescriptor = new EventSetDescriptor(getBeanClass(), "propertyChange", Class.forName("java.beans.PropertyChangeListener"), new String[] { "propertyChange" }, "addPropertyChangeListener", "removePropertyChangeListener");
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return eventSetDescriptor;
    }
    
    public MethodDescriptor getTagsMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getTags", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getTags", 0);
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
            return new EventSetDescriptor[] { this.propertyChangeEventSetDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public PropertyDescriptor customEditorPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getCustomEditor", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getCustomEditor", 0);
                }
                propertyDescriptor = new PropertyDescriptor("customEditor", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("customEditor", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor getAsTextMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getAsText", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getAsText", 0);
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
    
    public MethodDescriptor setAsText_javalangStringMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setAsText", Class.forName("java.lang.String"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setAsText", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("text");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setAsText(java.lang.String)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] { this.asTextPropertyDescriptor(), this.customEditorPropertyDescriptor(), this.javaInitializationStringPropertyDescriptor(), this.paintablePropertyDescriptor(), this.selectedColorPropertyDescriptor(), this.tagsPropertyDescriptor(), this.valuePropertyDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor removePropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("removePropertyChangeListener", Class.forName("java.beans.PropertyChangeListener"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "removePropertyChangeListener", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("listener");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("removePropertyChangeListener(java.beans.PropertyChangeListener)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor isPaintableMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("isPaintable", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "isPaintable", 0);
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
    
    public MethodDescriptor getValueMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getValue", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getValue", 0);
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
    
    public MethodDescriptor getJavaInitializationStringMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getJavaInitializationString", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getJavaInitializationString", 0);
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
    
    public PropertyDescriptor tagsPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getTags", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getTags", 0);
                }
                propertyDescriptor = new PropertyDescriptor("tags", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("tags", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor paintValue_javaawtGraphics_javaawtRectangleMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("paintValue", Class.forName("java.awt.Graphics"), Class.forName("java.awt.Rectangle"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "paintValue", 2);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("gfx");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("box");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("paintValue(java.awt.Graphics, java.awt.Rectangle)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor propertyChangepropertyChange_javabeansPropertyChangeEventMethodEventDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = Class.forName("java.beans.PropertyChangeListener").getMethod("propertyChange", Class.forName("java.beans.PropertyChangeEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(Class.forName("java.beans.PropertyChangeListener"), "propertyChange", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("evt");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setDisplayName("propertyChange(java.beans.PropertyChangeEvent)");
            featureDescriptor.setShortDescription("propertyChange(java.beans.PropertyChangeEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor paintablePropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("isPaintable", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "isPaintable", 0);
                }
                propertyDescriptor = new PropertyDescriptor("paintable", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("paintable", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public static String getBeanClassName() {
        return "com.magelang.ColorSelector";
    }
    
    public MethodDescriptor getCustomEditorMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getCustomEditor", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getCustomEditor", 0);
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
    
    public MethodDescriptor supportsCustomEditorMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("supportsCustomEditor", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "supportsCustomEditor", 0);
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
    
    public static Class getBeanClass() {
        return Class.forName("com.magelang.editors.ColorSelector");
    }
    
    public PropertyDescriptor valuePropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getValue", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getValue", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setValue", Class.forName("java.lang.Object"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setValue", 1);
                }
                propertyDescriptor = new PropertyDescriptor("value", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("value", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            return new MethodDescriptor[] { this.addPropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor(), this.getAsTextMethodDescriptor(), this.getCustomEditorMethodDescriptor(), this.getJavaInitializationStringMethodDescriptor(), this.getTagsMethodDescriptor(), this.getValueMethodDescriptor(), this.isPaintableMethodDescriptor(), this.paintValue_javaawtGraphics_javaawtRectangleMethodDescriptor(), this.removePropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor(), this.setAsText_javalangStringMethodDescriptor(), this.setValue_javalangObjectMethodDescriptor(), this.supportsCustomEditorMethodDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public MethodDescriptor setValue_javalangObjectMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("setValue", Class.forName("java.lang.Object"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "setValue", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("value");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("setValue(java.lang.Object)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor asTextPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getAsText", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getAsText", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setAsText", Class.forName("java.lang.String"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setAsText", 1);
                }
                propertyDescriptor = new PropertyDescriptor("asText", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("asText", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
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
    
    private void handleException(final Throwable t) {
    }
}
