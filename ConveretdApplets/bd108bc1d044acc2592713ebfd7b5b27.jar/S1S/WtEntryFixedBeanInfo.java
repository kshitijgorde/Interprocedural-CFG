// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;

public class WtEntryFixedBeanInfo extends SimpleBeanInfo
{
    static Class class$java$lang$Object;
    static Class class$java$awt$event$TextEvent;
    static Class class$java$beans$PropertyChangeListener;
    static Class class$S1S$PropertyChangeListener;
    static Class class$S1S$WtEntryFixed;
    static Class class$java$lang$String;
    static Class class$java$beans$PropertyChangeEvent;
    static Class class$S1S$PropertyChangeEvent;
    
    public MethodDescriptor addPropertyChangeListener_javabeansPropertyChangeListenerMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$beans$PropertyChangeListener;
                if ((class$java$beans$PropertyChangeListener = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$beans$PropertyChangeListener;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("addPropertyChangeListener", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "addPropertyChangeListener", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("listener");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("addPropertyChangeListener(java.beans.PropertyChangeListener)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor addPropertyChangeListener_S1SPropertyChangeListenerMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$S1S$PropertyChangeListener;
                if ((class$S1S$PropertyChangeListener = WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener) == null) {
                    try {
                        class$S1S$PropertyChangeListener = (WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener = Class.forName("S1S.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$S1S$PropertyChangeListener;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("addPropertyChangeListener", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "addPropertyChangeListener", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("listener");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("addPropertyChangeListener(java.beans.PropertyChangeListener)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public PropertyDescriptor appletInfoPropertyDescriptor() {
        PropertyDescriptor aDescriptor = null;
        try {
            try {
                Method aGetMethod = null;
                try {
                    final Class[] aGetMethodParameterTypes = new Class[0];
                    aGetMethod = getBeanClass().getMethod("getAppletInfo", (Class[])aGetMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    aGetMethod = findMethod(getBeanClass(), "getAppletInfo", 0);
                }
                final Method aSetMethod = null;
                aDescriptor = new PropertyDescriptor("appletInfo", aGetMethod, aSetMethod);
            }
            catch (Throwable exception2) {
                this.handleException(exception2);
                aDescriptor = new PropertyDescriptor("appletInfo", getBeanClass());
            }
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public static Method findMethod(final Class aClass, final String methodName, final int parameterCount) {
        try {
            final Method[] methods = aClass.getMethods();
            for (int index = 0; index < methods.length; ++index) {
                final Method method = methods[index];
                if (method.getParameterTypes().length == parameterCount && method.getName().equals(methodName)) {
                    return method;
                }
            }
        }
        catch (Throwable t) {
            return null;
        }
        return null;
    }
    
    public MethodDescriptor firePropertyChange_javalangString_javalangObject_javalangObjectMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = new Class[3];
                final int n = 0;
                Class class$java$lang$String;
                if ((class$java$lang$String = WtEntryFixedBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntryFixedBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$lang$String;
                final int n2 = 1;
                Class class$java$lang$Object;
                if ((class$java$lang$Object = WtEntryFixedBeanInfo.class$java$lang$Object) == null) {
                    try {
                        class$java$lang$Object = (WtEntryFixedBeanInfo.class$java$lang$Object = Class.forName("java.lang.Object"));
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new NoClassDefFoundError(ex2.getMessage());
                    }
                }
                array[n2] = class$java$lang$Object;
                final int n3 = 2;
                Class class$java$lang$Object2;
                if ((class$java$lang$Object2 = WtEntryFixedBeanInfo.class$java$lang$Object) == null) {
                    try {
                        class$java$lang$Object2 = (WtEntryFixedBeanInfo.class$java$lang$Object = Class.forName("java.lang.Object"));
                    }
                    catch (ClassNotFoundException ex3) {
                        throw new NoClassDefFoundError(ex3.getMessage());
                    }
                }
                array[n3] = class$java$lang$Object2;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("firePropertyChange", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "firePropertyChange", 3);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("propertyName");
                final ParameterDescriptor aParameterDescriptor2 = new ParameterDescriptor();
                aParameterDescriptor2.setName("arg2");
                aParameterDescriptor2.setDisplayName("oldValue");
                final ParameterDescriptor aParameterDescriptor3 = new ParameterDescriptor();
                aParameterDescriptor3.setName("arg3");
                aParameterDescriptor3.setDisplayName("newValue");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1, aParameterDescriptor2, aParameterDescriptor3 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor getAppletInfoMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getAppletInfo", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getAppletInfo", 0);
            }
            try {
                final ParameterDescriptor[] aParameterDescriptors = new ParameterDescriptor[0];
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public static Class getBeanClass() {
        Class class$S1S$WtEntryFixed;
        if ((class$S1S$WtEntryFixed = WtEntryFixedBeanInfo.class$S1S$WtEntryFixed) == null) {
            try {
                class$S1S$WtEntryFixed = (WtEntryFixedBeanInfo.class$S1S$WtEntryFixed = Class.forName("S1S.WtEntryFixed"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        return class$S1S$WtEntryFixed;
    }
    
    public static String getBeanClassName() {
        return "S1S.WtEntryFixed";
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            final EventSetDescriptor[] aDescriptorList = { this.propertyChangeEventSetDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            final MethodDescriptor[] aDescriptorList = { this.addPropertyChangeListener_S1SPropertyChangeListenerMethodDescriptor(), this.getAppletInfoMethodDescriptor(), this.initMethodDescriptor(), this.textValueChanged_javaawteventTextEventMethodDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyDescriptor[] aDescriptorList = { this.appletInfoPropertyDescriptor(), this.sLocWeightPropertyDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public MethodDescriptor initMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("init", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "init", 0);
            }
            try {
                final ParameterDescriptor[] aParameterDescriptors = new ParameterDescriptor[0];
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public PropertyDescriptor momentTextPropertyDescriptor() {
        PropertyDescriptor aDescriptor = null;
        try {
            try {
                Method aGetMethod = null;
                try {
                    final Class[] aGetMethodParameterTypes = new Class[0];
                    aGetMethod = getBeanClass().getMethod("getMomentText", (Class[])aGetMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    aGetMethod = findMethod(getBeanClass(), "getMomentText", 0);
                }
                Method aSetMethod = null;
                try {
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$lang$String;
                    if ((class$java$lang$String = WtEntryFixedBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntryFixedBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$lang$String;
                    final Class[] aSetMethodParameterTypes = array;
                    aSetMethod = getBeanClass().getMethod("setMomentText", (Class[])aSetMethodParameterTypes);
                }
                catch (Throwable exception2) {
                    this.handleException(exception2);
                    aSetMethod = findMethod(getBeanClass(), "setMomentText", 1);
                }
                aDescriptor = new PropertyDescriptor("momentText", aGetMethod, aSetMethod);
            }
            catch (Throwable exception3) {
                this.handleException(exception3);
                aDescriptor = new PropertyDescriptor("momentText", getBeanClass());
            }
        }
        catch (Throwable exception3) {
            this.handleException(exception3);
        }
        return aDescriptor;
    }
    
    public EventSetDescriptor propertyChangeEventSetDescriptor() {
        EventSetDescriptor aDescriptor = null;
        try {
            try {
                final MethodDescriptor[] eventMethodDescriptors = { this.propertyChangepropertyChange_javabeansPropertyChangeEventMethodEventDescriptor() };
                Method anAddMethod = null;
                try {
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$beans$PropertyChangeListener;
                    if ((class$java$beans$PropertyChangeListener = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$beans$PropertyChangeListener;
                    final Class[] anAddMethodParameterTypes = array;
                    anAddMethod = getBeanClass().getMethod("addPropertyChangeListener", (Class[])anAddMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    anAddMethod = findMethod(getBeanClass(), "addPropertyChangeListener", 1);
                }
                Method aRemoveMethod = null;
                try {
                    final Class[] array2 = { null };
                    final int n2 = 0;
                    Class class$java$beans$PropertyChangeListener2;
                    if ((class$java$beans$PropertyChangeListener2 = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener2 = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                        }
                        catch (ClassNotFoundException ex2) {
                            throw new NoClassDefFoundError(ex2.getMessage());
                        }
                    }
                    array2[n2] = class$java$beans$PropertyChangeListener2;
                    final Class[] aRemoveMethodParameterTypes = array2;
                    aRemoveMethod = getBeanClass().getMethod("removePropertyChangeListener", (Class[])aRemoveMethodParameterTypes);
                }
                catch (Throwable exception2) {
                    this.handleException(exception2);
                    aRemoveMethod = findMethod(getBeanClass(), "removePropertyChangeListener", 1);
                }
                final String s = "propertyChange";
                Class class$java$beans$PropertyChangeListener3;
                if ((class$java$beans$PropertyChangeListener3 = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener3 = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex3) {
                        throw new NoClassDefFoundError(ex3.getMessage());
                    }
                }
                aDescriptor = new EventSetDescriptor(s, class$java$beans$PropertyChangeListener3, eventMethodDescriptors, anAddMethod, aRemoveMethod);
            }
            catch (Throwable exception3) {
                this.handleException(exception3);
                final String[] eventMethodNames = { "propertyChange" };
                final Class beanClass = getBeanClass();
                final String s2 = "propertyChange";
                Class class$java$beans$PropertyChangeListener4;
                if ((class$java$beans$PropertyChangeListener4 = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener4 = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex4) {
                        throw new NoClassDefFoundError(ex4.getMessage());
                    }
                }
                aDescriptor = new EventSetDescriptor(beanClass, s2, class$java$beans$PropertyChangeListener4, eventMethodNames, "addPropertyChangeListener", "removePropertyChangeListener");
            }
        }
        catch (Throwable exception3) {
            this.handleException(exception3);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor propertyChangepropertyChange_javabeansPropertyChangeEventMethodEventDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$beans$PropertyChangeEvent;
                if ((class$java$beans$PropertyChangeEvent = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeEvent) == null) {
                    try {
                        class$java$beans$PropertyChangeEvent = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeEvent = Class.forName("java.beans.PropertyChangeEvent"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$beans$PropertyChangeEvent;
                final Class[] aParameterTypes = array;
                Class class$java$beans$PropertyChangeListener;
                if ((class$java$beans$PropertyChangeListener = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new NoClassDefFoundError(ex2.getMessage());
                    }
                }
                aMethod = class$java$beans$PropertyChangeListener.getMethod("propertyChange", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                Class class$java$beans$PropertyChangeListener2;
                if ((class$java$beans$PropertyChangeListener2 = WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener2 = (WtEntryFixedBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex3) {
                        throw new NoClassDefFoundError(ex3.getMessage());
                    }
                }
                aMethod = findMethod(class$java$beans$PropertyChangeListener2, "propertyChange", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("evt");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setDisplayName("propertyChange(java.beans.PropertyChangeEvent)");
            aDescriptor.setShortDescription("propertyChange(java.beans.PropertyChangeEvent)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor propertyChangeupdTotals_javalangObjectMethodEventDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$S1S$PropertyChangeEvent;
                if ((class$S1S$PropertyChangeEvent = WtEntryFixedBeanInfo.class$S1S$PropertyChangeEvent) == null) {
                    try {
                        class$S1S$PropertyChangeEvent = (WtEntryFixedBeanInfo.class$S1S$PropertyChangeEvent = Class.forName("S1S.PropertyChangeEvent"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$S1S$PropertyChangeEvent;
                final Class[] aParameterTypes = array;
                Class class$S1S$PropertyChangeListener;
                if ((class$S1S$PropertyChangeListener = WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener) == null) {
                    try {
                        class$S1S$PropertyChangeListener = (WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener = Class.forName("S1S.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new NoClassDefFoundError(ex2.getMessage());
                    }
                }
                aMethod = class$S1S$PropertyChangeListener.getMethod("updTotals", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                Class class$S1S$PropertyChangeListener2;
                if ((class$S1S$PropertyChangeListener2 = WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener) == null) {
                    try {
                        class$S1S$PropertyChangeListener2 = (WtEntryFixedBeanInfo.class$S1S$PropertyChangeListener = Class.forName("S1S.PropertyChangeListener"));
                    }
                    catch (ClassNotFoundException ex3) {
                        throw new NoClassDefFoundError(ex3.getMessage());
                    }
                }
                aMethod = findMethod(class$S1S$PropertyChangeListener2, "updTotals", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("event");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("propertyChange.updTotals(java.lang.Object)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public PropertyDescriptor sLocWeightPropertyDescriptor() {
        PropertyDescriptor aDescriptor = null;
        try {
            try {
                Method aGetMethod = null;
                try {
                    final Class[] aGetMethodParameterTypes = new Class[0];
                    aGetMethod = getBeanClass().getMethod("getSLocWeight", (Class[])aGetMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    aGetMethod = findMethod(getBeanClass(), "getSLocWeight", 0);
                }
                Method aSetMethod = null;
                try {
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$lang$String;
                    if ((class$java$lang$String = WtEntryFixedBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntryFixedBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$lang$String;
                    final Class[] aSetMethodParameterTypes = array;
                    aSetMethod = getBeanClass().getMethod("setSLocWeight", (Class[])aSetMethodParameterTypes);
                }
                catch (Throwable exception2) {
                    this.handleException(exception2);
                    aSetMethod = findMethod(getBeanClass(), "setSLocWeight", 1);
                }
                aDescriptor = new PropertyDescriptor("sLocWeight", aGetMethod, aSetMethod);
            }
            catch (Throwable exception3) {
                this.handleException(exception3);
                aDescriptor = new PropertyDescriptor("sLocWeight", getBeanClass());
            }
            aDescriptor.setBound(true);
        }
        catch (Throwable exception3) {
            this.handleException(exception3);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor textValueChanged_javaawteventTextEventMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$awt$event$TextEvent;
                if ((class$java$awt$event$TextEvent = WtEntryFixedBeanInfo.class$java$awt$event$TextEvent) == null) {
                    try {
                        class$java$awt$event$TextEvent = (WtEntryFixedBeanInfo.class$java$awt$event$TextEvent = Class.forName("java.awt.event.TextEvent"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$awt$event$TextEvent;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("textValueChanged", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "textValueChanged", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("e");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("textValueChanged(java.awt.event.TextEvent)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
}
