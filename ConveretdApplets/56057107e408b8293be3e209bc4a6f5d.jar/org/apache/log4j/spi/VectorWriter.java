// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.spi;

import java.io.Writer;
import java.util.Vector;
import java.io.PrintWriter;

class VectorWriter extends PrintWriter
{
    private Vector v;
    
    VectorWriter() {
        super(new NullWriter());
        this.v = new Vector();
    }
    
    public void print(final Object o) {
        this.v.addElement(o.toString());
    }
    
    public void print(final char[] array) {
        this.v.addElement(new String(array));
    }
    
    public void print(final String s) {
        this.v.addElement(s);
    }
    
    public void println(final Object o) {
        this.v.addElement(o.toString());
    }
    
    public void println(final char[] array) {
        this.v.addElement(new String(array));
    }
    
    public void println(final String s) {
        this.v.addElement(s);
    }
    
    public void write(final char[] array) {
        this.v.addElement(new String(array));
    }
    
    public void write(final char[] array, final int n, final int n2) {
        this.v.addElement(new String(array, n, n2));
    }
    
    public void write(final String s, final int n, final int n2) {
        this.v.addElement(s.substring(n, n + n2));
    }
    
    public void write(final String s) {
        this.v.addElement(s);
    }
    
    public String[] toStringArray() {
        final int size = this.v.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (String)this.v.elementAt(i);
        }
        return array;
    }
}
