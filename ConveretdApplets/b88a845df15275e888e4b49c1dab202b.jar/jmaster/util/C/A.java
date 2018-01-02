// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.C;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class A
{
    protected Class B;
    protected List A;
    
    public A(final Class b) {
        this.A = new ArrayList();
        if (b == null) {
            throw new NullPointerException("Null listener class not allowed");
        }
        this.B = b;
    }
    
    public Iterator A() {
        return this.A.iterator();
    }
    
    public int C() {
        return this.A.size();
    }
    
    public boolean B(final Object o) {
        return this.A.contains(o);
    }
    
    public Object A(final int n) {
        return this.A.get(n);
    }
    
    public void C(final Object o) {
        if (o == null) {
            throw new NullPointerException("Null listener not allowed");
        }
        if (!this.B.isInstance(o)) {
            throw new IllegalArgumentException("Given listener (" + o + ") is not instance of (" + this.B + ")");
        }
        if (this.A.contains(o)) {
            throw new IllegalArgumentException("Given listener (" + o + ") already registered");
        }
        synchronized (this.A) {
            this.A.add(o);
        }
    }
    
    public Object A(final Object o) {
        Object o2 = null;
        synchronized (this.A) {
            if (this.A.remove(o)) {
                o2 = o;
            }
        }
        return o2;
    }
    
    public void A(final String s, final Object[] array) {
        this.A(s, null, array);
    }
    
    public void A(final String s, final Class[] array, final Object[] array2) {
        Method method = null;
        if (array == null) {
            final Method[] methods = this.B.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals(s)) {
                    method = methods[i];
                    break;
                }
            }
        }
        else {
            try {
                method = this.B.getMethod(s, (Class[])array);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        for (final Object next : this.A) {
            try {
                method.invoke(next, array2);
            }
            catch (Exception ex2) {
                throw new RuntimeException(ex2);
            }
        }
    }
    
    public void B() {
        this.A.clear();
    }
}
