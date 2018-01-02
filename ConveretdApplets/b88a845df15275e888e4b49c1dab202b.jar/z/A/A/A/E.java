// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public final class E implements Serializable
{
    private final HashMap B;
    private final ArrayList A;
    static /* synthetic */ Class class$z$A$A$A$A;
    
    public E() {
        this.B = new HashMap();
        this.A = new ArrayList();
    }
    
    public Iterator A() {
        return this.A.iterator();
    }
    
    public int B() {
        return this.A.size();
    }
    
    public A B(final Class clazz) {
        if (!((E.class$z$A$A$A$A == null) ? (E.class$z$A$A$A$A = class$("z.A.A.A.A")) : E.class$z$A$A$A$A).isAssignableFrom(clazz)) {
            throw new RuntimeException("Class type passed to getDirectory must be an implementation of com.drew.metadata.Directory");
        }
        if (this.B.containsKey(clazz)) {
            return (A)this.B.get(clazz);
        }
        A instance;
        try {
            instance = clazz.newInstance();
        }
        catch (Exception ex) {
            throw new RuntimeException("Cannot instantiate provided Directory type: " + clazz.toString());
        }
        this.B.put(clazz, instance);
        this.A.add(instance);
        return instance;
    }
    
    public boolean A(final Class clazz) {
        return this.B.containsKey(clazz);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
