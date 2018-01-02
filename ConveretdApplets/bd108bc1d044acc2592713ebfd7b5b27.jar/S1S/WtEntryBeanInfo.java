// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.beans.FeatureDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.SimpleBeanInfo;

public class WtEntryBeanInfo extends SimpleBeanInfo
{
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[] { this.propertyChangeEventSetDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public PropertyDescriptor momentTextPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getMomentText", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getMomentText", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setMomentText", Class.forName("java.lang.String"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setMomentText", 1);
                }
                propertyDescriptor = new PropertyDescriptor("momentText", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("momentText", getBeanClass());
            }
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor getAppletInfoMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getAppletInfo", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getAppletInfo", 0);
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
    
    public MethodDescriptor getSLocArmMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("getSLocArm", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "getSLocArm", 0);
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
    
    public MethodDescriptor addPropertyChangeListener_S1SPropertyChangeListenerMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("addPropertyChangeListener", Class.forName("S1S.PropertyChangeListener"));
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
    
    public MethodDescriptor firePropertyChange_javalangString_javalangObject_javalangObjectMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("firePropertyChange", Class.forName("java.lang.String"), Class.forName("java.lang.Object"), Class.forName("java.lang.Object"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "firePropertyChange", 3);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("propertyName");
                final ParameterDescriptor parameterDescriptor2 = new ParameterDescriptor();
                parameterDescriptor2.setName("arg2");
                parameterDescriptor2.setDisplayName("oldValue");
                final ParameterDescriptor parameterDescriptor3 = new ParameterDescriptor();
                parameterDescriptor3.setName("arg3");
                parameterDescriptor3.setDisplayName("newValue");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor, parameterDescriptor2, parameterDescriptor3 });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
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
    
    public static String getBeanClassName() {
        return "S1S.WtEntryFixed";
    }
    
    public static Class getBeanClass() {
        return Class.forName("S1S.WtEntryFixed");
    }
    
    public PropertyDescriptor appletInfoPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getAppletInfo", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getAppletInfo", 0);
                }
                propertyDescriptor = new PropertyDescriptor("appletInfo", method, null);
            }
            catch (Throwable t2) {
                this.handleException(t2);
                propertyDescriptor = new PropertyDescriptor("appletInfo", getBeanClass());
            }
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return propertyDescriptor;
    }
    
    public MethodDescriptor textValueChanged_javaawteventTextEventMethodDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("textValueChanged", Class.forName("java.awt.event.TextEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "textValueChanged", 1);
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
            featureDescriptor.setShortDescription("textValueChanged(java.awt.event.TextEvent)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] { this.appletInfoPropertyDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    private void handleException(final Throwable t) {
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
    
    public MethodDescriptor propertyChangeupdTotals_javalangObjectMethodEventDescriptor() {
        FeatureDescriptor featureDescriptor = null;
        try {
            Method method;
            try {
                method = Class.forName("S1S.PropertyChangeListener").getMethod("updTotals", Class.forName("S1S.PropertyChangeEvent"));
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(Class.forName("S1S.PropertyChangeListener"), "updTotals", 1);
            }
            try {
                final ParameterDescriptor parameterDescriptor = new ParameterDescriptor();
                parameterDescriptor.setName("arg1");
                parameterDescriptor.setDisplayName("event");
                featureDescriptor = new MethodDescriptor(method, new ParameterDescriptor[] { parameterDescriptor });
            }
            catch (Throwable t2) {
                this.handleException(t2);
                featureDescriptor = new MethodDescriptor(method);
            }
            featureDescriptor.setShortDescription("propertyChange.updTotals(java.lang.Object)");
        }
        catch (Throwable t3) {
            this.handleException(t3);
        }
        return (MethodDescriptor)featureDescriptor;
    }
    
    public MethodDescriptor initMethodDescriptor() {
        MethodDescriptor methodDescriptor = null;
        try {
            Method method;
            try {
                method = getBeanClass().getMethod("init", (Class[])new Class[0]);
            }
            catch (Throwable t) {
                this.handleException(t);
                method = findMethod(getBeanClass(), "init", 0);
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
            return new MethodDescriptor[] { this.addPropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor(), this.firePropertyChange_javalangString_javalangObject_javalangObjectMethodDescriptor(), this.getAppletInfoMethodDescriptor(), this.initMethodDescriptor(), this.textValueChanged_javaawteventTextEventMethodDescriptor() };
        }
        catch (Throwable t) {
            this.handleException(t);
            return null;
        }
    }
    
    public PropertyDescriptor armTextPropertyDescriptor() {
        PropertyDescriptor propertyDescriptor = null;
        try {
            try {
                Method method;
                try {
                    method = getBeanClass().getMethod("getArmText", (Class[])new Class[0]);
                }
                catch (Throwable t) {
                    this.handleException(t);
                    method = findMethod(getBeanClass(), "getArmText", 0);
                }
                Method method2;
                try {
                    method2 = getBeanClass().getMethod("setArmText", Class.forName("java.lang.String"));
                }
                catch (Throwable t2) {
                    this.handleException(t2);
                    method2 = findMethod(getBeanClass(), "setArmText", 1);
                }
                propertyDescriptor = new PropertyDescriptor("armText", method, method2);
            }
            catch (Throwable t3) {
                this.handleException(t3);
                propertyDescriptor = new PropertyDescriptor("armText", getBeanClass());
            }
            propertyDescriptor.setBound(true);
        }
        catch (Throwable t4) {
            this.handleException(t4);
        }
        return propertyDescriptor;
    }
}
