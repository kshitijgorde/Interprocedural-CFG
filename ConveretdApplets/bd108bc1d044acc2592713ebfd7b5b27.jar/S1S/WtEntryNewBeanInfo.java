// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.beans.EventSetDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class WtEntryNewBeanInfo extends SimpleBeanInfo
{
    static Class class$java$awt$event$TextEvent;
    static Class class$java$beans$PropertyChangeListener;
    static Class class$S1S$WtEntryNew;
    static Class class$java$lang$String;
    static Class class$array1$$java$lang$String;
    static Class class$java$beans$PropertyChangeEvent;
    
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
    
    public PropertyDescriptor armTextPropertyDescriptor() {
        PropertyDescriptor aDescriptor = null;
        try {
            try {
                Method aGetMethod = null;
                try {
                    final Class[] aGetMethodParameterTypes = new Class[0];
                    aGetMethod = getBeanClass().getMethod("getArmText", (Class[])aGetMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    aGetMethod = findMethod(getBeanClass(), "getArmText", 0);
                }
                Method aSetMethod = null;
                try {
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$lang$String;
                    if ((class$java$lang$String = WtEntryNewBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntryNewBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$lang$String;
                    final Class[] aSetMethodParameterTypes = array;
                    aSetMethod = getBeanClass().getMethod("setArmText", (Class[])aSetMethodParameterTypes);
                }
                catch (Throwable exception2) {
                    this.handleException(exception2);
                    aSetMethod = findMethod(getBeanClass(), "setArmText", 1);
                }
                aDescriptor = new PropertyDescriptor("armText", aGetMethod, aSetMethod);
            }
            catch (Throwable exception3) {
                this.handleException(exception3);
                aDescriptor = new PropertyDescriptor("armText", getBeanClass());
            }
            aDescriptor.setBound(true);
        }
        catch (Throwable exception3) {
            this.handleException(exception3);
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
    
    public MethodDescriptor getArmTextMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getArmText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getArmText", 0);
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
        Class class$S1S$WtEntryNew;
        if ((class$S1S$WtEntryNew = WtEntryNewBeanInfo.class$S1S$WtEntryNew) == null) {
            try {
                class$S1S$WtEntryNew = (WtEntryNewBeanInfo.class$S1S$WtEntryNew = Class.forName("S1S.WtEntryNew"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        return class$S1S$WtEntryNew;
    }
    
    public static String getBeanClassName() {
        return "S1S.WtEntryNew";
    }
    
    public MethodDescriptor getDescTextMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getDescText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getDescText", 0);
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
            final MethodDescriptor[] aDescriptorList = { this.getAppletInfoMethodDescriptor(), this.getArmTextMethodDescriptor(), this.getDescTextMethodDescriptor(), this.initMethodDescriptor(), this.main_javalangString__MethodDescriptor(), this.setArmText_javalangStringMethodDescriptor(), this.setDescText_javalangStringMethodDescriptor(), this.textValueChanged_javaawteventTextEventMethodDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyDescriptor[] aDescriptorList = { this.appletInfoPropertyDescriptor(), this.armTextPropertyDescriptor(), this.sLocArmPropertyDescriptor(), this.sLocWeightPropertyDescriptor() };
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
    
    public MethodDescriptor main_javalangString__MethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$array1$$java$lang$String;
                if ((class$array1$$java$lang$String = WtEntryNewBeanInfo.class$array1$$java$lang$String) == null) {
                    try {
                        class$array1$$java$lang$String = (WtEntryNewBeanInfo.class$array1$$java$lang$String = Class.forName("[Ljava.lang.String;"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$array1$$java$lang$String;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("main", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "main", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("args");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("main(java.lang.String[])");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
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
                    if ((class$java$beans$PropertyChangeListener = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                    if ((class$java$beans$PropertyChangeListener2 = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener2 = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener3 = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener3 = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener4 = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener4 = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeEvent = WtEntryNewBeanInfo.class$java$beans$PropertyChangeEvent) == null) {
                    try {
                        class$java$beans$PropertyChangeEvent = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeEvent = Class.forName("java.beans.PropertyChangeEvent"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$beans$PropertyChangeEvent;
                final Class[] aParameterTypes = array;
                Class class$java$beans$PropertyChangeListener;
                if ((class$java$beans$PropertyChangeListener = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener2 = WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener2 = (WtEntryNewBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
            aDescriptor.setShortDescription("propertyChange.propertyChange(java.beans.PropertyChangeEvent)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor setArmText_javalangStringMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$lang$String;
                if ((class$java$lang$String = WtEntryNewBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntryNewBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$lang$String;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setArmText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setArmText", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("arg1");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("setArmText(java.lang.String)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor setDescText_javalangStringMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$lang$String;
                if ((class$java$lang$String = WtEntryNewBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntryNewBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$lang$String;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setDescText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setDescText", 1);
            }
            try {
                final ParameterDescriptor aParameterDescriptor1 = new ParameterDescriptor();
                aParameterDescriptor1.setName("arg1");
                aParameterDescriptor1.setDisplayName("arg1");
                final ParameterDescriptor[] aParameterDescriptors = { aParameterDescriptor1 };
                aDescriptor = new MethodDescriptor(aMethod, aParameterDescriptors);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aDescriptor = new MethodDescriptor(aMethod);
            }
            aDescriptor.setShortDescription("setDescText(java.lang.String)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public PropertyDescriptor sLocArmPropertyDescriptor() {
        PropertyDescriptor aDescriptor = null;
        try {
            try {
                Method aGetMethod = null;
                try {
                    final Class[] aGetMethodParameterTypes = new Class[0];
                    aGetMethod = getBeanClass().getMethod("getSLocArm", (Class[])aGetMethodParameterTypes);
                }
                catch (Throwable exception) {
                    this.handleException(exception);
                    aGetMethod = findMethod(getBeanClass(), "getSLocArm", 0);
                }
                Method aSetMethod = null;
                try {
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$lang$String;
                    if ((class$java$lang$String = WtEntryNewBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntryNewBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$lang$String;
                    final Class[] aSetMethodParameterTypes = array;
                    aSetMethod = getBeanClass().getMethod("setSLocArm", (Class[])aSetMethodParameterTypes);
                }
                catch (Throwable exception2) {
                    this.handleException(exception2);
                    aSetMethod = findMethod(getBeanClass(), "setSLocArm", 1);
                }
                aDescriptor = new PropertyDescriptor("sLocArm", aGetMethod, aSetMethod);
            }
            catch (Throwable exception3) {
                this.handleException(exception3);
                aDescriptor = new PropertyDescriptor("sLocArm", getBeanClass());
            }
            aDescriptor.setBound(true);
        }
        catch (Throwable exception3) {
            this.handleException(exception3);
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
                    if ((class$java$lang$String = WtEntryNewBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntryNewBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
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
                if ((class$java$awt$event$TextEvent = WtEntryNewBeanInfo.class$java$awt$event$TextEvent) == null) {
                    try {
                        class$java$awt$event$TextEvent = (WtEntryNewBeanInfo.class$java$awt$event$TextEvent = Class.forName("java.awt.event.TextEvent"));
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
