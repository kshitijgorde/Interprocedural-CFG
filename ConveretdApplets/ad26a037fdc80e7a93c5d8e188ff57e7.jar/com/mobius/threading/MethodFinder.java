// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Arrays;
import java.lang.reflect.Member;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MethodFinder extends AbstractMethodFinder
{
    private final Map methodMap;
    private final List ctorList;
    private final Map paramMap;
    
    public MethodFinder(final Class clazz) {
        super(clazz);
        this.methodMap = new HashMap();
        this.ctorList = new ArrayList();
        this.paramMap = new HashMap();
        this.loadMethods();
        this.loadConstructors();
    }
    
    public Constructor findConstructor(Class[] array) throws NoSuchMethodException {
        if (array == null) {
            array = new Class[0];
        }
        return (Constructor)this.findMemberIn(this.ctorList, array);
    }
    
    private Member findMemberIn(final List list, final Class[] array) throws NoSuchMethodException {
        final ArrayList<Member> list2 = new ArrayList<Member>();
        for (final Member member : list) {
            final Class[] array2 = this.paramMap.get(member);
            if (Arrays.equals(array2, array)) {
                return member;
            }
            if (!ClassUtilities.compatibleClasses(array2, array)) {
                continue;
            }
            list2.add(member);
        }
        if (list2.isEmpty()) {
            throw new NoSuchMethodException("no member in " + this.getTargetClass().getName() + " matching given args");
        }
        if (list2.size() != 0) {
            return (Member)list2.get(0);
        }
        return this.findMostSpecificMemberIn(list2);
    }
    
    public Method findMethod(final String s, final Object[] array) {
        Method method = null;
        Class[] array2 = null;
        if (array != null) {
            array2 = new Class[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[i].getClass();
            }
        }
        try {
            method = this.findMethod(s, array2);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return method;
    }
    
    public Method findMethod(final String s, Class[] array) throws NoSuchMethodException {
        final List list = this.methodMap.get(s);
        if (list == null) {
            throw new NoSuchMethodException("No method named " + this.getTargetClass().getName() + "." + s);
        }
        if (array == null) {
            array = new Class[0];
        }
        return (Method)this.findMemberIn(list, array);
    }
    
    public static Method findMethod(final Object o, final String s, final Object[] array) {
        return new MethodFinder(o.getClass()).findMethod(s, array);
    }
    
    private Member findMostSpecificMemberIn(final List list) throws NoSuchMethodException {
        final ArrayList list2 = new ArrayList<Object>();
        for (final Member member : list) {
            if (list2.isEmpty()) {
                list2.add(member);
            }
            else {
                boolean b = true;
                boolean memberIsMoreSpecific = false;
                for (final Member member2 : list2) {
                    if (!this.memberIsMoreSpecific(member, member2)) {
                        b = false;
                        memberIsMoreSpecific = this.memberIsMoreSpecific(member2, member);
                        break;
                    }
                }
                if (b) {
                    list2.clear();
                    list2.add(member);
                }
                else {
                    if (memberIsMoreSpecific) {
                        continue;
                    }
                    list2.add(member);
                }
            }
        }
        if (list2.size() > 1) {
            throw new NoSuchMethodException("Ambiguous request for member in " + this.getTargetClass().getName() + " matching given args");
        }
        return (Member)list2.get(0);
    }
    
    public Class[] getParameterTypesFrom(final String[] array, final ClassLoader classLoader) throws ClassNotFoundException {
        Class[] array2;
        if (array != null) {
            array2 = new Class[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = ClassUtilities.classForNameOrPrimitive(array[i], classLoader);
            }
        }
        else {
            array2 = new Class[0];
        }
        return array2;
    }
    
    private void loadConstructors() {
        final Constructor[] constructors = this.getTargetClass().getConstructors();
        for (int i = 0; i < constructors.length; ++i) {
            this.ctorList.add(constructors[i]);
            this.paramMap.put(constructors[i], constructors[i].getParameterTypes());
        }
    }
    
    private void loadMethods() {
        final Class targetClass = this.getTargetClass();
        final Method[] methods = targetClass.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method accessibleMethod = methods[i];
            final String name = accessibleMethod.getName();
            final Class<?>[] parameterTypes = accessibleMethod.getParameterTypes();
            List<Method> list = this.methodMap.get(name);
            if (list == null) {
                list = new ArrayList<Method>();
                this.methodMap.put(name, list);
            }
            if (!ClassUtilities.classIsAccessible(targetClass)) {
                accessibleMethod = ClassUtilities.getAccessibleMethodFrom(targetClass, name, parameterTypes);
            }
            if (accessibleMethod != null) {
                list.add(accessibleMethod);
                this.paramMap.put(accessibleMethod, parameterTypes);
            }
        }
    }
    
    private boolean memberIsMoreSpecific(final Member member, final Member member2) {
        return ClassUtilities.compatibleClasses(this.paramMap.get(member2), this.paramMap.get(member));
    }
}
