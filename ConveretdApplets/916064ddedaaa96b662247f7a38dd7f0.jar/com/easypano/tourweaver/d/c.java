// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import java.net.URLConnection;
import java.io.File;
import com.easypano.tourweaver.a.e;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import com.easypano.tourweaver.b.a;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

public class c implements Runnable, h
{
    private Vector a;
    private Vector b;
    private Hashtable c;
    private g d;
    private InputStream e;
    private Object f;
    private int g;
    private Thread h;
    d i;
    private static String[] z;
    
    public c(final g d) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Hashtable();
        this.e = null;
        this.f = null;
        this.g = -1;
        this.h = null;
        this.i = null;
        this.d = d;
        (this.h = new Thread(this)).setPriority(5);
        this.b();
        if (com.easypano.tourweaver.b.a.o != 0) {
            com.easypano.tourweaver.d.i.u = !u;
        }
    }
    
    public synchronized void a(final String s) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int i = 0;
        while (i < this.a.size()) {
            final URL url = this.a.elementAt(i);
            if (!u) {
                if (s.equals(url.toString())) {
                    try {
                        this.e.close();
                        this.i.a();
                    }
                    catch (Exception ex) {
                        System.out.println(com.easypano.tourweaver.d.c.z[6] + ex);
                    }
                    final boolean contains = this.a.contains(url);
                    Object element = null;
                    Label_0146: {
                        if (!u) {
                            if (!contains) {
                                break Label_0146;
                            }
                            this.a.removeElementAt(i);
                        }
                        element = this.b.elementAt(i);
                        this.b.removeElementAt(i);
                    }
                    this.a.addElement(this.f);
                    this.b.addElement(new Integer(this.g));
                    if (!u) {
                        if (!contains) {
                            break;
                        }
                        this.a.addElement(url);
                        this.b.addElement(element);
                    }
                    if (!u) {
                        break;
                    }
                }
                ++i;
            }
            if (u) {
                break;
            }
        }
    }
    
    private void b() {
        final j j = new j(this.e, 2);
        this.c.put(com.easypano.tourweaver.d.c.z[3], j);
        j.a(this);
        final i i = new i(this.e);
        this.c.put(com.easypano.tourweaver.d.c.z[4], i);
        i.a(this);
    }
    
    public synchronized void a(final Object o, final int n) {
    }
    
    public synchronized void b(final Object o, final int n) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n2 = 0;
        try {
            c c = this;
            Label_0125: {
                final int available;
                Label_0124: {
                    if (!u) {
                        if (this.e != null) {
                            available = this.e.available();
                            if (u) {
                                break Label_0124;
                            }
                            if (available != 0) {
                                this.a.addElement(this.f);
                                this.b.addElement(new Integer(this.g));
                                this.a.addElement(o);
                                this.b.addElement(new Integer(n));
                                n2 = 1;
                                this.e.close();
                                if (!u) {
                                    break Label_0125;
                                }
                            }
                        }
                        this.a.addElement(o);
                        c = this;
                    }
                    c.b.addElement(new Integer(n));
                }
                n2 = available;
            }
        }
        catch (IOException ex) {
            Label_0162: {
                if (!u) {
                    if (n2 != 0) {
                        break Label_0162;
                    }
                    this.a.addElement(o);
                }
                this.b.addElement(new Integer(n));
            }
            System.out.println(com.easypano.tourweaver.d.c.z[7]);
        }
        final Thread h = this.h;
        Label_0229: {
            if (!u) {
                if (h != null) {
                    final Thread h2 = this.h;
                    if (u) {
                        break Label_0229;
                    }
                    if (!h2.isInterrupted()) {
                        final Thread h3 = this.h;
                        if (u) {
                            break Label_0229;
                        }
                        if (h3.isAlive()) {
                            return;
                        }
                    }
                }
                this.h = new Thread(this);
            }
        }
        h.start();
    }
    
    public synchronized void c(final Object o, final int n) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        System.out.println(com.easypano.tourweaver.d.c.z[5] + o);
        c c = this;
        if (!u) {
            if (!this.a.contains(o)) {
                this.a.insertElementAt(o, 0);
                this.b.insertElementAt(new Integer(n), 0);
            }
            c = this;
        }
        final Thread h = c.h;
        Label_0124: {
            if (!u) {
                if (h != null) {
                    final Thread h2 = this.h;
                    if (u) {
                        break Label_0124;
                    }
                    if (!h2.isInterrupted()) {
                        final Thread h3 = this.h;
                        if (u) {
                            break Label_0124;
                        }
                        if (h3.isAlive()) {
                            return;
                        }
                    }
                }
                this.h = new Thread(this);
            }
        }
        h.start();
    }
    
    public boolean c() {
        return false;
    }
    
    public void d() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        final Thread h;
        final Thread thread = h = this.h;
        final Thread h2;
        Label_0044: {
            if (!u) {
                if (thread != null) {
                    h2 = this.h;
                    if (u) {
                        break Label_0044;
                    }
                    if (!h2.isAlive()) {
                        this.h.start();
                        if (!u) {
                            return;
                        }
                    }
                }
                final Thread h3 = this.h;
            }
        }
        if (!u) {
            if (thread != null) {
                return;
            }
            this.h = new Thread(this);
        }
        h2.start();
    }
    
    public void run() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n = 0;
        while (true) {
            while (this.h != null) {
                final i i;
                final Thread thread = (Thread)(i = (i)this.h);
                if (u || u) {
                    final i j = i;
                    if (!u) {
                        if (j != null) {
                            j.f();
                            this.c.remove(com.easypano.tourweaver.d.c.z[4]);
                        }
                    }
                    return;
                }
                if (!thread.isInterrupted()) {
                    int n2;
                    final boolean b = (n2 = (this.a.isEmpty() ? 1 : 0)) != 0;
                    c c = null;
                    c c2 = null;
                    c c3 = null;
                    Label_0188: {
                        if (!u) {
                            if (b) {
                                try {
                                    Thread.sleep(1000L);
                                    ++n;
                                }
                                catch (Exception ex2) {}
                                if (n < 6) {
                                    continue;
                                }
                                if (!u) {
                                    break;
                                }
                            }
                            n = 0;
                            this.f = this.a.lastElement();
                            this.a.removeElementAt(this.a.size() - 1);
                            this.g = this.b.elementAt(this.b.size() - 1);
                            this.b.removeElementAt(this.b.size() - 1);
                            c = this;
                            c2 = this;
                            c3 = this;
                            if (u) {
                                break Label_0188;
                            }
                            n2 = this.g;
                        }
                        if (n2 == 5) {
                            this.d.a(this.f, "");
                            if (!u) {
                                continue;
                            }
                        }
                        this.e = this.a(this.f);
                        c = this;
                        c2 = this;
                        c3 = this;
                    }
                    if (!u) {
                        if (c3.e == null) {
                            System.out.println(com.easypano.tourweaver.d.c.z[8] + this.f.toString());
                            if (!u) {
                                continue;
                            }
                        }
                        c = this;
                        c2 = this;
                    }
                    Label_0478: {
                        Label_0424: {
                            Label_0376: {
                                Label_0328: {
                                    if (!u) {
                                        switch (c2.g) {
                                            case 2: {
                                                (this.i = this.c.get(com.easypano.tourweaver.d.c.z[3])).a(this.e);
                                                c = this;
                                                break;
                                            }
                                            case 4: {
                                                break Label_0328;
                                            }
                                            case 3: {
                                                break Label_0376;
                                            }
                                            case 1: {
                                                break Label_0424;
                                            }
                                        }
                                    }
                                    ((j)c.i).a(0);
                                    if (!u) {
                                        break Label_0478;
                                    }
                                }
                                (this.i = this.c.get(com.easypano.tourweaver.d.c.z[3])).a(this.e);
                                ((j)this.i).a(2);
                                if (!u) {
                                    break Label_0478;
                                }
                            }
                            (this.i = this.c.get(com.easypano.tourweaver.d.c.z[3])).a(this.e);
                            ((j)this.i).a(1);
                            if (!u) {
                                break Label_0478;
                            }
                        }
                        (this.i = this.c.get(com.easypano.tourweaver.d.c.z[4])).a(this.e);
                        if (!u) {
                            break Label_0478;
                        }
                        System.out.println(com.easypano.tourweaver.d.c.z[11]);
                        if (!u) {
                            continue;
                        }
                        try {
                            this.i.b();
                            continue;
                        }
                        catch (IOException ex3) {
                            try {
                                final Thread h = this.h;
                                Thread.sleep(50L);
                            }
                            catch (Exception ex4) {}
                            System.out.println(com.easypano.tourweaver.d.c.z[9] + this.f.toString());
                            continue;
                        }
                        catch (Exception ex) {
                            try {
                                final Thread h2 = this.h;
                                Thread.sleep(50L);
                            }
                            catch (Exception ex5) {}
                            System.out.println(com.easypano.tourweaver.d.c.z[10] + ex);
                            ex.printStackTrace();
                            if (!u) {
                                continue;
                            }
                        }
                    }
                    break;
                }
                break;
            }
            i i = this.c.get(com.easypano.tourweaver.d.c.z[4]);
            continue;
        }
    }
    
    private InputStream a(final Object o) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        Object o2 = o;
        if (!u) {
            if (o instanceof InputStream) {
                o2 = o;
            }
            else {
                InputStream inputStream = null;
                int n = 0;
                while (true) {
                    while (inputStream == null) {
                        final int n2 = n;
                        if (u) {
                            if (n2 != 0) {
                                try {
                                    inputStream = new FileInputStream(o.toString());
                                }
                                catch (FileNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            return inputStream;
                        }
                        if (n2 >= 2) {
                            break;
                        }
                        Object o3 = o;
                        if (!u) {
                            if (!(o instanceof URL) && !u) {
                                break;
                            }
                            o3 = o;
                        }
                        URL f = (URL)o3;
                        if (n == 1) {
                            f = com.easypano.tourweaver.a.e.f(com.easypano.tourweaver.a.e.g(f.getFile()));
                        }
                        try {
                            final URLConnection openConnection = f.openConnection();
                            openConnection.setUseCaches(true);
                            inputStream = openConnection.getInputStream();
                        }
                        catch (IOException ex2) {
                            System.out.println(com.easypano.tourweaver.d.c.z[1] + n);
                        }
                        catch (NullPointerException ex3) {}
                        catch (Exception ex4) {
                            System.out.println(com.easypano.tourweaver.d.c.z[0] + f.toString());
                        }
                        ++n;
                        if (u) {
                            break;
                        }
                    }
                    InputStream inputStream2;
                    InputStream inputStream4;
                    final InputStream inputStream3 = inputStream4 = (inputStream2 = inputStream);
                    if (!u) {
                        if (inputStream3 == null) {
                            inputStream = this.getClass().getResourceAsStream(o.toString());
                        }
                        inputStream2 = (inputStream4 = inputStream);
                    }
                    if (u) {
                        return inputStream2;
                    }
                    if (inputStream4 == null) {
                        final boolean b = o instanceof File;
                        continue;
                    }
                    break;
                }
                return inputStream;
            }
        }
        return (InputStream)o2;
    }
    
    public void a(final com.easypano.tourweaver.a.a a) {
        this.d.a(a);
    }
    
    public void a(final byte[] array) {
        this.d.b(array, this.f.toString());
    }
    
    public void a(final byte[] array, final String s) {
        this.d.a(array, s);
    }
    
    public void a(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.d.a(this.f.toString(), array, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public void a() {
        this.d.e();
    }
    
    public void e() {
        try {
            this.e.close();
            this.h.join(10L);
        }
        catch (Exception ex) {
            System.out.println(com.easypano.tourweaver.d.c.z[2]);
        }
        this.h = null;
        this.a.removeAllElements();
        this.b.removeAllElements();
    }
    
    static {
        final String[] z = new String[12];
        final int n = 0;
        final char[] charArray = "p7/|y'02~$".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0007';
                            break;
                        }
                        case 1: {
                            c2 = 'E';
                            break;
                        }
                        case 2: {
                            c2 = '@';
                            break;
                        }
                        case 3: {
                            c2 = '\u0012';
                            break;
                        }
                        default: {
                            c2 = '\u001e';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "S-%2zh2.~qf!`fvu !v>a$)~{ce4}>h5%|>r7,2}h+.w}s,.u2'7%fl~\u007f`".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0007';
                            break;
                        }
                        case 1: {
                            c4 = 'E';
                            break;
                        }
                        case 2: {
                            c4 = '@';
                            break;
                        }
                        case 3: {
                            c4 = '\u0012';
                            break;
                        }
                        default: {
                            c4 = '\u001e';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "D)/a{'\u00065`Wi55f?&d`ajh5`vqp+\u0014zlb$$".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0007';
                            break;
                        }
                        case 1: {
                            c6 = 'E';
                            break;
                        }
                        case 2: {
                            c6 = '@';
                            break;
                        }
                        case 3: {
                            c6 = '\u0012';
                            break;
                        }
                        default: {
                            c6 = '\u001e';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "},0Fqh)".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0007';
                            break;
                        }
                        case 1: {
                            c8 = 'E';
                            break;
                        }
                        case 2: {
                            c8 = '@';
                            break;
                        }
                        case 3: {
                            c8 = '\u0012';
                            break;
                        }
                        default: {
                            c8 = '\u001e';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "q1\u0014}qk".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0007';
                            break;
                        }
                        case 1: {
                            c10 = 'E';
                            break;
                        }
                        case 2: {
                            c10 = '@';
                            break;
                        }
                        case 3: {
                            c10 = '\u0012';
                            break;
                        }
                        default: {
                            c10 = '\u001e';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "f!$2lb6`".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0007';
                            break;
                        }
                        case 1: {
                            c12 = 'E';
                            break;
                        }
                        case 2: {
                            c12 = '@';
                            break;
                        }
                        case 3: {
                            c12 = '\u0012';
                            break;
                        }
                        default: {
                            c12 = '\u001e';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "p7/|y'2({rbe3wjO,'z>".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0007';
                            break;
                        }
                        case 1: {
                            c14 = 'E';
                            break;
                        }
                        case 2: {
                            c14 = '@';
                            break;
                        }
                        case 3: {
                            c14 = '\u0012';
                            break;
                        }
                        default: {
                            c14 = '\u001e';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u6dfc\u52e5\u9a98\u4f0a\u5156\u7ea0\u8d01\u6ed0\u65e4\u516d\u95ea\u6b26\u4e4b\u8f6f\u8d5a\u6e97\u5974\u8d65".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0007';
                            break;
                        }
                        case 1: {
                            c16 = 'E';
                            break;
                        }
                        case 2: {
                            c16 = '@';
                            break;
                        }
                        case 3: {
                            c16 = '\u0012';
                            break;
                        }
                        default: {
                            c16 = '\u001e';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u6254\u5f45\u7f11\u7ece\u6d5f\u5936\u8d60lglke}2".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0007';
                            break;
                        }
                        case 1: {
                            c18 = 'E';
                            break;
                        }
                        case 2: {
                            c18 = '@';
                            break;
                        }
                        case 3: {
                            c18 = '\u0012';
                            break;
                        }
                        default: {
                            c18 = '\u001e';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u4e0c\u8f38\u8d04\u6e82\u65e8\u51fd\u73f5\u9559\u8bfd>r7,2#'".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\u0007';
                            break;
                        }
                        case 1: {
                            c20 = 'E';
                            break;
                        }
                        case 2: {
                            c20 = '@';
                            break;
                        }
                        case 3: {
                            c20 = '\u0012';
                            break;
                        }
                        default: {
                            c20 = '\u001e';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "p7/|y',.2lr+`".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '\u0007';
                            break;
                        }
                        case 1: {
                            c22 = 'E';
                            break;
                        }
                        case 2: {
                            c22 = '@';
                            break;
                        }
                        case 3: {
                            c22 = '\u0012';
                            break;
                        }
                        default: {
                            c22 = '\u001e';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u672d\u77a0\u7c3b\u5799\u769a\u8d43\u6ed5l\u6cb3\u6717\u76ff\u5ed1\u5da5\u5165\u4e15\u8f7a".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '\u0007';
                            break;
                        }
                        case 1: {
                            c24 = 'E';
                            break;
                        }
                        case 2: {
                            c24 = '@';
                            break;
                        }
                        case 3: {
                            c24 = '\u0012';
                            break;
                        }
                        default: {
                            c24 = '\u001e';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 <= n48) {
                z[n45] = new String(charArray12).intern();
                c.z = z;
                return;
            }
            continue;
        }
    }
}
