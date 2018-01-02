// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.d;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public final class b extends e
{
    private Hashtable d;
    private e[] e;
    private boolean f;
    public boolean a;
    private boolean g;
    
    public b() {
        this.d = new Hashtable();
        this.f = false;
        this.a = false;
        this.g = false;
    }
    
    public b(final a a) {
        this.d = new Hashtable();
        this.f = false;
        this.a = false;
        this.g = false;
        this.a(a);
    }
    
    private void a(final a a) {
        final Vector<e> vector = new Vector<e>();
        a.a(60);
        final int nextToken;
        if ((nextToken = a.nextToken()) == 63) {
            this.g = true;
        }
        else if (nextToken == 47) {
            this.f = true;
        }
        else {
            a.pushBack();
        }
        final int nextToken2;
        if ((nextToken2 = a.nextToken()) == -3) {
            this.b = a.sval;
            if (!this.f) {
                while (a.b() != 62 && a.b() != 63 && a.b() != 47) {
                    final e e = new e();
                    a.a(-3);
                    e.b = a.sval;
                    a.a(61);
                    a.a(34);
                    e.c = a.sval;
                    this.d.put(e.b, e.c);
                }
                int n = 0;
                if (a.b(63)) {
                    if (!this.g) {
                        throw new RuntimeException("Encountered XML prolog '?' character in XML Tag for " + this);
                    }
                    a.a(63);
                    a.a(62);
                }
                else if (a.b(47)) {
                    a.a(47);
                    a.a(62);
                    n = 1;
                }
                else if (a.b(62)) {
                    a.a(62);
                }
                while (n == 0 && a.ttype != -1) {
                    final int b = a.b();
                    new e();
                    e e2;
                    if (b == 60) {
                        final b b2 = (b)(e2 = new b(a));
                        if (b2.f) {
                            if (!b2.b.equals(this.b)) {
                                throw new RuntimeException("Found a closure tag [" + b2.b + "], but does not match open tag [" + this.b + "]");
                            }
                            n = 1;
                        }
                        else {
                            this.a = true;
                        }
                    }
                    else {
                        (e2 = new e()).c = a.c();
                    }
                    if (n == 0) {
                        vector.addElement(e2);
                    }
                }
                if (n == 0 && !this.g) {
                    throw new RuntimeException("No closure tag found for tag <" + this.b + ">");
                }
                this.e = a(vector);
            }
            return;
        }
        throw new RuntimeException("XML data error: Unexpected XML token encountered [" + nextToken2 + "].  Expected tag name or '/' closure");
    }
    
    public final String a() {
        if (this.e.length == 0) {
            return null;
        }
        if (this.a) {
            throw new RuntimeException("Unable to return content for tag <" + this.b + "> simply, as children are present.");
        }
        if (this.e.length > 1) {
            throw new RuntimeException("Unable to return content for tag <" + this.b + "> simply, as complex content is present.");
        }
        return this.e[0].c;
    }
    
    public final float b() {
        return Float.valueOf(this.a());
    }
    
    public final String a(final String s) {
        if (!this.d.containsKey(s)) {
            throw new RuntimeException("Requested XML Attribute [" + s + "] does not exist in Tag <" + this.b + ">");
        }
        return this.d.get(s);
    }
    
    public final float b(final String s) {
        return Float.valueOf(this.a(s));
    }
    
    public final b[] c(final String s) {
        final Vector<e> vector = new Vector<e>();
        for (int i = 0; i < this.e.length; ++i) {
            if (this.e[i].b.equals(s)) {
                vector.addElement(this.e[i]);
            }
        }
        return b(vector);
    }
    
    public final b d(final String s) {
        final b[] c;
        if ((c = this.c(s)).length != 1) {
            return null;
        }
        return c[0];
    }
    
    private static e[] a(final Vector vector) {
        final int size;
        final e[] array = new e[size = vector.size()];
        for (int i = 0; i < size; ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    private static b[] b(final Vector vector) {
        final int size;
        final b[] array = new b[size = vector.size()];
        for (int i = 0; i < size; ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public final String toString() {
        return this.a(0);
    }
    
    public final String a(final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += "    ";
        }
        final String s = string;
        String s2 = "";
        if (this.g) {
            s2 = "?";
        }
        final String string2 = s + "<" + s2 + this.b + this.c() + s2;
        String s4;
        if (this.e != null && this.e.length != 0) {
            String s3 = string2 + ">";
            if (this.a) {
                s3 += "\n";
            }
            for (int j = 0; j < this.e.length; ++j) {
                s3 += this.e[j].a(n + 1);
            }
            if (this.a) {
                s3 += string;
            }
            s4 = s3 + "</" + this.b + ">\n";
        }
        else {
            s4 = string2 + "/>\n";
        }
        return s4;
    }
    
    private String c() {
        String string = "";
        if (this.d != null) {
            final Enumeration<String> keys = this.d.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                string = string + " " + s + "=" + '\"' + (String)this.d.get(s) + '\"';
            }
        }
        return string;
    }
}
