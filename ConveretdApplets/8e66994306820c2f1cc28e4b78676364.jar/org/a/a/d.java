// 
// Decompiled by Procyon v0.5.30
// 

package org.a.a;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class d
{
    private Hashtable a;
    
    public d() {
        this.a = new Hashtable();
    }
    
    public void a(final c c) {
        final String d = c.d();
        final String c2 = c.c();
        final String a = c.a();
        final Hashtable<Object, Hashtable> hashtable = this.a.get(d);
        if (hashtable == null) {
            final Hashtable<String, c> hashtable2 = new Hashtable<String, c>();
            hashtable2.put(a, c);
            final Hashtable<String, Hashtable<String, c>> hashtable3 = new Hashtable<String, Hashtable<String, c>>();
            hashtable3.put(c2, hashtable2);
            this.a.put(d, hashtable3);
        }
        else {
            final Hashtable<String, c> hashtable4 = hashtable.get(c2);
            if (hashtable4 == null) {
                final Hashtable<String, c> hashtable5 = new Hashtable<String, c>();
                hashtable5.put(a, c);
                hashtable.put(c2, hashtable5);
            }
            else {
                hashtable4.put(a, c);
            }
        }
    }
    
    public Vector a(final String s, final String s2) {
        final Vector<c> vector = new Vector<c>();
        final Hashtable<String, Hashtable<String, Hashtable<String, Hashtable>>> hashtable = this.a.get(s);
        if (hashtable != null) {
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s3 = keys.nextElement();
                if (s2.length() >= s3.length() && s3.equalsIgnoreCase(s2.substring(0, s3.length()))) {
                    final Hashtable<String, Hashtable<String, Hashtable>> hashtable2 = hashtable.get(s3);
                    if (hashtable == null) {
                        continue;
                    }
                    final Enumeration<Hashtable<String, Hashtable>> elements = hashtable2.elements();
                    while (elements.hasMoreElements()) {
                        vector.addElement((c)elements.nextElement());
                    }
                }
            }
        }
        return vector;
    }
}
