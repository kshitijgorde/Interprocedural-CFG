// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.util.Hashtable;

public final class aH
{
    private static Byte a;
    private static Byte b;
    private static Byte c;
    private static Byte d;
    private static Byte e;
    private static Byte f;
    private static Byte g;
    private static Byte h;
    private static Byte i;
    private static Byte j;
    private Hashtable k;
    
    public aH() {
        this(new Hashtable());
    }
    
    public aH(final Object o) {
        this.k = (Hashtable)o;
    }
    
    public final String a() {
        return this.k.get(aH.g);
    }
    
    public final String b() {
        return this.k.get(aH.f);
    }
    
    public final String c() {
        return this.k.get(aH.a);
    }
    
    public final String d() {
        return this.k.get(aH.b);
    }
    
    public final String e() {
        return this.k.get(aH.h);
    }
    
    public final long f() {
        final Long n;
        if ((n = this.k.get(aH.e)) != null) {
            return n;
        }
        return -1L;
    }
    
    public final boolean g() {
        return this.k.get(aH.i) != null;
    }
    
    public final boolean h() {
        final Boolean b;
        return (b = this.k.get(aH.c)) != null && b;
    }
    
    public final boolean i() {
        final Boolean b;
        return (b = this.k.get(aH.d)) != null && b;
    }
    
    public final void a(final boolean b) {
        this.k.put(aH.d, b);
    }
    
    public final B j() {
        final Hashtable value;
        if ((value = this.k.get(aH.i)) != null) {
            return new B(new bE(value));
        }
        return null;
    }
    
    public final int k() {
        if (!this.g()) {
            return 0;
        }
        final B j = this.j();
        return j.i() / j.g();
    }
    
    public final String toString() {
        final Enumeration<Byte> keys = this.k.keys();
        final StringBuffer append = new StringBuffer("MessageHeaders: ").append("\n");
        while (keys.hasMoreElements()) {
            final Byte b;
            switch (b = keys.nextElement()) {
                case 1: {
                    append.append("SUBJECT: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 2: {
                    append.append("AUTHOR: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 3: {
                    append.append("HAS_AUDIO: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 4: {
                    append.append("HAS_TEXT: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 5: {
                    append.append("HAS_ATTACHMENT: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 6: {
                    append.append("HAS_CHILDREN: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 7: {
                    append.append("POSTED DATE: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 8: {
                    append.append("REFERENCE: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 9: {
                    append.append("MESSAGE_ID: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 10: {
                    append.append("URL: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 11: {
                    append.append("AUDIO_HEADERS: ").append(this.k.get(b)).append("\n");
                    continue;
                }
                case 12: {
                    append.append("POSITION: ").append(this.k.get(b)).append("\n");
                    continue;
                }
            }
        }
        return append.toString();
    }
    
    public final int l() {
        final Integer n;
        if ((n = this.k.get(aH.j)) != null) {
            return n;
        }
        return -1;
    }
    
    static {
        aH.a = new Byte((byte)1);
        aH.b = new Byte((byte)2);
        new Byte((byte)3);
        aH.c = new Byte((byte)4);
        new Byte((byte)5);
        aH.d = new Byte((byte)6);
        aH.e = new Byte((byte)7);
        aH.f = new Byte((byte)8);
        aH.g = new Byte((byte)9);
        aH.h = new Byte((byte)10);
        aH.i = new Byte((byte)11);
        aH.j = new Byte((byte)12);
    }
}
