// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Node
{
    protected Hashtable a;
    protected int b;
    protected Shout3DViewer c;
    protected final BooleanField d;
    private static int e;
    protected int f;
    private String g;
    
    public String getDEFName() {
        return this.g;
    }
    
    public String getFieldName(final Field field) throws Shout3DException {
        final Enumeration<String> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final String nextElement = keys.nextElement();
            if (this.a.get(nextElement).equals(field)) {
                return nextElement;
            }
        }
        throw new Shout3DException("Attempt to get the name of a Field not contained by this Node: " + this.getClass());
    }
    
    public String getTypeName() {
        final String name = this.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }
    
    public int getNumFields() {
        return this.b;
    }
    
    public Node() {
        this.a = new Hashtable();
        this.b = 0;
        this.d = new BooleanField(this, "changed", 0, true);
        this.f = 0;
        this.g = null;
        this.f = ++Node.e;
    }
    
    protected void a(final Field field, final String s) {
        if (this.a.put(s, field) == null) {
            ++this.b;
        }
    }
    
    public void setViewer(final Shout3DViewer c) {
        this.c = c;
    }
    
    public Shout3DViewer getViewer() {
        return this.c;
    }
    
    protected void a(final g g) {
    }
    
    public Node[] a(final Node[] array) {
        return array;
    }
    
    static {
        Node.e = 0;
    }
    
    public Field getField(final String s) throws Shout3DException {
        final Field field;
        if ((field = this.a.get(s)) == null) {
            throw new Shout3DException("Attempt to get non-existent Field: " + this.getClass());
        }
        return field;
    }
    
    public Field getFieldByIndex(final int n) throws Shout3DException {
        if (n < 0 || n >= this.a.size()) {
            throw new Shout3DException("Attempt to get Field with index " + n + " out of range: " + this.getClass());
        }
        final Enumeration<Object> keys = this.a.keys();
        Object o = keys.nextElement();
        for (int i = 0; i < n; ++i) {
            o = keys.nextElement();
        }
        return (Field)this.a.get(o);
    }
    
    public void b(final g g) {
    }
    
    public boolean isOfType(final String s) {
        Serializable s2 = this.getClass();
        while (true) {
            final String name = ((Class)s2).getName();
            final String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            if (substring.equals(s)) {
                return true;
            }
            if (substring.equals("Node")) {
                return false;
            }
            s2 = ((Class<? extends Node>)s2).getSuperclass();
        }
    }
    
    public void setDEFName(final String g) {
        this.g = g;
    }
}
