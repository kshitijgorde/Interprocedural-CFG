// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.beans.PropertyDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.beans.SimpleBeanInfo;

public class WtEntrySpinBeanInfo extends SimpleBeanInfo
{
    static Class class$S1S$WtEntrySpin;
    static Class class$java$beans$PropertyChangeListener;
    static Class class$java$awt$Color;
    static Class class$java$beans$PropertyChangeEvent;
    static Class class$array1$$java$lang$String;
    static Class class$java$lang$String;
    
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
    
    public MethodDescriptor getArmBackgroundMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getArmBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getArmBackground", 0);
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
    
    public MethodDescriptor getArmEditableMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getArmEditable", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getArmEditable", 0);
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
        Class class$S1S$WtEntrySpin;
        if ((class$S1S$WtEntrySpin = WtEntrySpinBeanInfo.class$S1S$WtEntrySpin) == null) {
            try {
                class$S1S$WtEntrySpin = (WtEntrySpinBeanInfo.class$S1S$WtEntrySpin = Class.forName("S1S.WtEntrySpin"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        return class$S1S$WtEntrySpin;
    }
    
    public static String getBeanClassName() {
        return "S1S.WtEntrySpin";
    }
    
    public MethodDescriptor getDescBackgroundMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getDescBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getDescBackground", 0);
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
            final MethodDescriptor[] aDescriptorList = { this.getArmBackgroundMethodDescriptor(), this.getArmEditableMethodDescriptor(), this.getArmTextMethodDescriptor(), this.getDescBackgroundMethodDescriptor(), this.getDescTextMethodDescriptor(), this.getWeightBackgroundMethodDescriptor(), this.getWeightIncrementTextMethodDescriptor(), this.getWeightTextField1BackgroundMethodDescriptor(), this.main_javalangString__MethodDescriptor(), this.setArmBackground_javaawtColorMethodDescriptor(), this.setArmEditable_booleanMethodDescriptor(), this.setArmText_javalangStringMethodDescriptor(), this.setDescBackground_javaawtColorMethodDescriptor(), this.setDescText_javalangStringMethodDescriptor(), this.setWeightBackground_javaawtColorMethodDescriptor(), this.setWeightIncrementText_javalangStringMethodDescriptor(), this.setWeightTextField1Background_javaawtColorMethodDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyDescriptor[] aDescriptorList = { this.sLocArmPropertyDescriptor(), this.sLocWeightPropertyDescriptor() };
            return aDescriptorList;
        }
        catch (Throwable exception) {
            this.handleException(exception);
            return null;
        }
    }
    
    public MethodDescriptor getWeightBackgroundMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getWeightBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getWeightBackground", 0);
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
    
    public MethodDescriptor getWeightIncrementTextMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getWeightIncrementText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getWeightIncrementText", 0);
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
    
    public MethodDescriptor getWeightTextField1BackgroundMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = new Class[0];
                aMethod = getBeanClass().getMethod("getWeightTextField1Background", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "getWeightTextField1Background", 0);
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
    
    private void handleException(final Throwable exception) {
    }
    
    public MethodDescriptor main_javalangString__MethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$array1$$java$lang$String;
                if ((class$array1$$java$lang$String = WtEntrySpinBeanInfo.class$array1$$java$lang$String) == null) {
                    try {
                        class$array1$$java$lang$String = (WtEntrySpinBeanInfo.class$array1$$java$lang$String = Class.forName("[Ljava.lang.String;"));
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
                    if ((class$java$beans$PropertyChangeListener = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                    if ((class$java$beans$PropertyChangeListener2 = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                        try {
                            class$java$beans$PropertyChangeListener2 = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener3 = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener3 = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener4 = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener4 = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeEvent = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeEvent) == null) {
                    try {
                        class$java$beans$PropertyChangeEvent = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeEvent = Class.forName("java.beans.PropertyChangeEvent"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$beans$PropertyChangeEvent;
                final Class[] aParameterTypes = array;
                Class class$java$beans$PropertyChangeListener;
                if ((class$java$beans$PropertyChangeListener = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
                if ((class$java$beans$PropertyChangeListener2 = WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener) == null) {
                    try {
                        class$java$beans$PropertyChangeListener2 = (WtEntrySpinBeanInfo.class$java$beans$PropertyChangeListener = Class.forName("java.beans.PropertyChangeListener"));
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
    
    public MethodDescriptor setArmBackground_javaawtColorMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$awt$Color;
                if ((class$java$awt$Color = WtEntrySpinBeanInfo.class$java$awt$Color) == null) {
                    try {
                        class$java$awt$Color = (WtEntrySpinBeanInfo.class$java$awt$Color = Class.forName("java.awt.Color"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$awt$Color;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setArmBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setArmBackground", 1);
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
            aDescriptor.setShortDescription("setArmBackground(java.awt.Color)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor setArmEditable_booleanMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] aParameterTypes = { Boolean.TYPE };
                aMethod = getBeanClass().getMethod("setArmEditable", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setArmEditable", 1);
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
                if ((class$java$lang$String = WtEntrySpinBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntrySpinBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
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
    
    public MethodDescriptor setDescBackground_javaawtColorMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$awt$Color;
                if ((class$java$awt$Color = WtEntrySpinBeanInfo.class$java$awt$Color) == null) {
                    try {
                        class$java$awt$Color = (WtEntrySpinBeanInfo.class$java$awt$Color = Class.forName("java.awt.Color"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$awt$Color;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setDescBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setDescBackground", 1);
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
            aDescriptor.setShortDescription("setDescBackground(java.awt.Color)");
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
                if ((class$java$lang$String = WtEntrySpinBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntrySpinBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
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
    
    public MethodDescriptor setWeightBackground_javaawtColorMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$awt$Color;
                if ((class$java$awt$Color = WtEntrySpinBeanInfo.class$java$awt$Color) == null) {
                    try {
                        class$java$awt$Color = (WtEntrySpinBeanInfo.class$java$awt$Color = Class.forName("java.awt.Color"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$awt$Color;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setWeightBackground", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setWeightBackground", 1);
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
            aDescriptor.setShortDescription("setWeightBackground(java.awt.Color)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor setWeightIncrementText_javalangStringMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$lang$String;
                if ((class$java$lang$String = WtEntrySpinBeanInfo.class$java$lang$String) == null) {
                    try {
                        class$java$lang$String = (WtEntrySpinBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$lang$String;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setWeightIncrementText", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setWeightIncrementText", 1);
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
            aDescriptor.setShortDescription("setWeightIncrementText(java.lang.String)");
        }
        catch (Throwable exception2) {
            this.handleException(exception2);
        }
        return aDescriptor;
    }
    
    public MethodDescriptor setWeightTextField1Background_javaawtColorMethodDescriptor() {
        MethodDescriptor aDescriptor = null;
        try {
            Method aMethod = null;
            try {
                final Class[] array = { null };
                final int n = 0;
                Class class$java$awt$Color;
                if ((class$java$awt$Color = WtEntrySpinBeanInfo.class$java$awt$Color) == null) {
                    try {
                        class$java$awt$Color = (WtEntrySpinBeanInfo.class$java$awt$Color = Class.forName("java.awt.Color"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array[n] = class$java$awt$Color;
                final Class[] aParameterTypes = array;
                aMethod = getBeanClass().getMethod("setWeightTextField1Background", (Class[])aParameterTypes);
            }
            catch (Throwable exception) {
                this.handleException(exception);
                aMethod = findMethod(getBeanClass(), "setWeightTextField1Background", 1);
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
            aDescriptor.setShortDescription("setWeightTextField1Background(java.awt.Color)");
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
                    if ((class$java$lang$String = WtEntrySpinBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntrySpinBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
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
                    if ((class$java$lang$String = WtEntrySpinBeanInfo.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (WtEntrySpinBeanInfo.class$java$lang$String = Class.forName("java.lang.String"));
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
}
