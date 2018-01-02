// 
// Decompiled by Procyon v0.5.30
// 

package org.a.a.a;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import org.a.d.c.b;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import org.a.a.c;
import org.a.a.d;
import java.util.Vector;
import java.util.Hashtable;
import org.a.a.e;

public class a implements e, Runnable
{
    private String a;
    private String b;
    private byte[] c;
    private Hashtable d;
    private int e;
    private String f;
    private int[] g;
    private int h;
    private Hashtable[] i;
    private Vector[] j;
    private d k;
    private boolean[] l;
    private int m;
    private int[] n;
    private Exception[] o;
    private String[] p;
    private int q;
    private ThreadGroup r;
    private Object s;
    private static String[] z;
    
    public a(final String a, final String s, final int e, final String f) {
        this.g = new int[1];
        this.i = new Hashtable[1];
        this.j = new Vector[1];
        this.l = new boolean[1];
        this.n = new int[1];
        this.o = new Exception[1];
        this.p = new String[1];
        this.q = 512;
        this.s = new Integer(0);
        this.a = a;
        this.e = e;
        this.f = f;
        this.b(s);
        this.i[0] = new Hashtable();
        this.j[0] = new Vector();
        this.k = new d();
        this.g[0] = -1;
        this.p[0] = "";
        this.m = 75;
        this.n[0] = 0;
        this.h = 2500;
        this.o[0] = null;
    }
    
    public a(final String s, final String s2, final int n, final String s3, final ThreadGroup r) {
        this(s, s2, n, s3);
        this.r = r;
    }
    
    private void a(final String b) {
        synchronized (this.s) {
            if (b != null) {
                this.b = b;
            }
            else {
                this.b = new String("");
            }
        }
    }
    
    private void a(final byte[] c) {
        synchronized (this.s) {
            if (c != null) {
                this.c = c;
            }
            else {
                this.c = new byte[0];
            }
        }
    }
    
    public void b(final String s) {
        synchronized (this.s) {
            this.d = this.c(s);
        }
    }
    
    private Hashtable c(final String s) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        if (s == null) {
            return hashtable;
        }
        int index3;
        for (int i = 0; i < s.length(); i = index3 + 2) {
            String s2 = "";
            final int n = i;
            final int index = s.indexOf(":", n);
            String s3;
            if (index == -1) {
                final int index2 = s.indexOf(org.a.a.a.a.z[6], n);
                if (index2 == -1) {
                    s3 = s.substring(n);
                    index3 = s.length() + 1;
                }
                else {
                    s3 = s.substring(n, index2);
                    index3 = index2;
                }
            }
            else {
                s3 = s.substring(n, index);
                int length;
                if (s.length() <= index + 1) {
                    length = s.length();
                }
                else if (s.charAt(index + 1) == ' ') {
                    length = index + 2;
                }
                else {
                    length = index + 1;
                }
                if (length >= s.length()) {
                    index3 = s.length() + 1;
                }
                else {
                    index3 = s.indexOf(org.a.a.a.a.z[6], length);
                    if (index3 == -1) {
                        index3 = s.length() + 1;
                        s2 = s.substring(length);
                    }
                    else {
                        s2 = s.substring(length, index3);
                    }
                }
            }
            hashtable.put(s3, s2);
        }
        return hashtable;
    }
    
    public String d(final String s) {
        return this.i[0].get(s);
    }
    
    public boolean e(final String s) {
        return this.i[0].containsKey(s);
    }
    
    public String a() {
        return this.p[0];
    }
    
    public void a(final c c) {
        this.k.a(c);
    }
    
    public byte[] b() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < this.j[0].size(); ++i) {
            try {
                byteArrayOutputStream.write((byte[])this.j[0].elementAt(i));
            }
            catch (IOException ex) {}
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public void a(final int h) {
        synchronized (this.s) {
            this.h = h;
        }
    }
    
    public void c() {
    }
    
    public void a(final long n) {
    }
    
    public byte[] a(final String s, final byte[] array) throws org.a.a.b {
        // monitorenter(s2 = this.s)
        try {
            this.a(s);
            this.a(array);
            this.o[0] = null;
            this.l[0] = false;
            b b;
            if (this.r != null) {
                b = new b(this.r, this);
            }
            else {
                b = new b(this);
            }
            b.start();
            if (this.h > -1) {
                this.n[0] = 0;
                while (this.n[0] <= this.h && !this.l[0]) {
                    this.b(this.m);
                    this.n[0] += this.m;
                }
                if (!this.l[0]) {
                    this.c();
                    b.stop();
                    throw new org.a.a.b(org.a.a.a.a.z[2]);
                }
            }
            else {
                while (!this.l[0]) {
                    this.b(this.m);
                }
            }
            if (this.o[0] != null) {
                this.c();
                throw new org.a.a.b(org.a.a.a.a.z[4] + this.o[0].getMessage(), this.o[0]);
            }
            if ((this.a() != null && this.a().equals(org.a.a.a.a.z[3])) || (this.e(org.a.a.a.a.z[5]) && this.d(org.a.a.a.a.z[5]).equalsIgnoreCase(org.a.a.a.a.z[0])) || (this.e(org.a.a.a.a.z[1]) && this.d(org.a.a.a.a.z[1]).equalsIgnoreCase(org.a.a.a.a.z[0]))) {
                this.c();
            }
            return this.b();
        }
        catch (org.a.a.b b3) {
            final org.a.a.b b2 = b3;
            // monitorexit(s2)
            if (b2 != null) {
                throw b2;
            }
            return null;
        }
    }
    
    private void d() throws org.a.a.b {
        try {
            String s = this.b;
            if (s.length() == 0 || this.b.charAt(0) != '/') {
                s = "/" + s;
            }
            final Vector a = this.k.a(this.a, s);
            if (this.f.equals(org.a.a.a.a.z[15]) && this.c != null && this.c.length > 0) {
                s = s + "?" + new String(this.c);
            }
            final URLConnection openConnection = new URL(org.a.a.a.a.z[11], this.a, this.e, s).openConnection();
            openConnection.setDoInput(true);
            if (this.f.equals(org.a.a.a.a.z[8])) {
                this.n[0] = 0;
                openConnection.setDoOutput(true);
                openConnection.setRequestProperty(org.a.a.a.a.z[14], org.a.a.a.a.z[9]);
                openConnection.setRequestProperty(org.a.a.a.a.z[7], new String("" + this.c.length));
            }
            this.n[0] = 0;
            if (this.d != null) {
                final Enumeration<String> keys = (Enumeration<String>)this.d.keys();
                while (keys.hasMoreElements()) {
                    this.n[0] = 0;
                    final String s2 = keys.nextElement();
                    openConnection.setRequestProperty(s2, (String)this.d.get(s2));
                }
            }
            if (a != null && a.size() > 0) {
                final StringBuffer sb = new StringBuffer();
                final c c = a.elementAt(0);
                this.n[0] = 0;
                sb.append(c.a());
                sb.append("=");
                sb.append(c.b());
                for (int i = 1; i < a.size(); ++i) {
                    final c c2 = a.elementAt(i);
                    this.n[0] = 0;
                    sb.append(org.a.a.a.a.z[13]);
                    sb.append(c2.a());
                    sb.append("=");
                    sb.append(c2.b());
                }
                openConnection.setRequestProperty(org.a.a.a.a.z[12], sb.toString());
            }
            this.n[0] = 0;
            if (this.f.equals(org.a.a.a.a.z[8])) {
                this.n[0] = 0;
                final OutputStream outputStream = openConnection.getOutputStream();
                outputStream.write(this.c);
                outputStream.flush();
                outputStream.close();
            }
            this.n[0] = 0;
            openConnection.connect();
            this.n[0] = 0;
            final InputStream inputStream = openConnection.getInputStream();
            this.n[0] = 0;
            this.p[0] = "";
            try {
                this.g[0] = ((HttpURLConnection)openConnection).getResponseCode();
            }
            catch (IOException ex2) {
                this.g[0] = -1;
            }
            catch (ClassCastException ex3) {
                this.g[0] = -1;
            }
            this.i[0] = new Hashtable();
            int n = 0;
            int j = 1;
            while (j != 0) {
                this.n[0] = 0;
                final String headerFieldKey = openConnection.getHeaderFieldKey(n);
                if (headerFieldKey != null) {
                    String headerField = openConnection.getHeaderField(n);
                    ++n;
                    if (headerField == null) {
                        headerField = "";
                    }
                    if (headerFieldKey.equalsIgnoreCase(org.a.a.a.a.z[10])) {
                        this.a(new c(this.a, headerField));
                    }
                    this.i[0].put(headerFieldKey, headerField);
                }
                else if (n == 0) {
                    ++n;
                }
                else {
                    j = 0;
                }
            }
            this.j[0] = new Vector();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[this.q];
            int read = 0;
            while (inputStream.available() >= 0 && read >= 0) {
                read = inputStream.read(array, 0, array.length);
                this.n[0] = 0;
                if (read < 0) {
                    continue;
                }
                if (read == 0) {
                    continue;
                }
                byteArrayOutputStream.write(array, 0, read);
            }
            this.j[0].addElement(byteArrayOutputStream.toByteArray());
            this.n[0] = 0;
            inputStream.close();
        }
        catch (Exception ex) {
            throw new org.a.a.b(ex.getMessage(), ex);
        }
        this.l[0] = true;
    }
    
    public void run() {
        try {
            this.d();
        }
        catch (org.a.a.b b) {
            this.o[0] = b;
            this.l[0] = true;
        }
    }
    
    public void e() {
        this.c();
    }
    
    public void finalize() throws Throwable {
        this.e();
        super.finalize();
    }
    
    private void b(final long n) {
        try {
            Thread.currentThread();
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    static {
        final String[] z = new String[16];
        final int n = 0;
        final char[] charArray = "J\u007f(Ui".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = ')';
                    break;
                }
                case 1: {
                    c2 = '\u0013';
                    break;
                }
                case 2: {
                    c2 = 'G';
                    break;
                }
                case 3: {
                    c2 = '&';
                    break;
                }
                default: {
                    c2 = '\f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "ya(^u\u0004P(HbLp3OcG".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = ')';
                    break;
                }
                case 1: {
                    c4 = '\u0013';
                    break;
                }
                case 2: {
                    c4 = 'G';
                    break;
                }
                case 3: {
                    c4 = '&';
                    break;
                }
                default: {
                    c4 = '\f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "{v6SiZggReDv#\u000bc\\gi".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = ')';
                    break;
                }
                case 1: {
                    c6 = '\u0013';
                    break;
                }
                case 2: {
                    c6 = 'G';
                    break;
                }
                case 3: {
                    c6 = '&';
                    break;
                }
                default: {
                    c6 = '\f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0018=w".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = ')';
                    break;
                }
                case 1: {
                    c8 = '\u0013';
                    break;
                }
                case 2: {
                    c8 = 'G';
                    break;
                }
                case 3: {
                    c8 = '&';
                    break;
                }
                default: {
                    c8 = '\f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "la5I~\td/O`L3*Gg@} \u0006~Lb2C\u007f])g".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = ')';
                    break;
                }
                case 1: {
                    c10 = '\u0013';
                    break;
                }
                case 2: {
                    c10 = 'G';
                    break;
                }
                case 3: {
                    c10 = '&';
                    break;
                }
                default: {
                    c10 = '\f';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "j|)HiJg.Ib".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = ')';
                    break;
                }
                case 1: {
                    c12 = '\u0013';
                    break;
                }
                case 2: {
                    c12 = 'G';
                    break;
                }
                case 3: {
                    c12 = '&';
                    break;
                }
                default: {
                    c12 = '\f';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "$\u0019".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = ')';
                    break;
                }
                case 1: {
                    c14 = '\u0013';
                    break;
                }
                case 2: {
                    c14 = 'G';
                    break;
                }
                case 3: {
                    c14 = '&';
                    break;
                }
                default: {
                    c14 = '\f';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "j|)RiGgjjiGt3N".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = ')';
                    break;
                }
                case 1: {
                    c16 = '\u0013';
                    break;
                }
                case 2: {
                    c16 = 'G';
                    break;
                }
                case 3: {
                    c16 = '&';
                    break;
                }
                default: {
                    c16 = '\f';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "y\\\u0014r".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = ')';
                    break;
                }
                case 1: {
                    c18 = '\u0013';
                    break;
                }
                case 2: {
                    c18 = 'G';
                    break;
                }
                case 3: {
                    c18 = '&';
                    break;
                }
                default: {
                    c18 = '\f';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "Hc7JeJr3OcG<?\u000b{^dj@c[~jS~Ev)EcMv#".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = ')';
                    break;
                }
                case 1: {
                    c20 = '\u0013';
                    break;
                }
                case 2: {
                    c20 = 'G';
                    break;
                }
                case 3: {
                    c20 = '&';
                    break;
                }
                default: {
                    c20 = '\f';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "zv3\u000bOF|,Oi".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = ')';
                    break;
                }
                case 1: {
                    c22 = '\u0013';
                    break;
                }
                case 2: {
                    c22 = 'G';
                    break;
                }
                case 3: {
                    c22 = '&';
                    break;
                }
                default: {
                    c22 = '\f';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "Ag3V".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = ')';
                    break;
                }
                case 1: {
                    c24 = '\u0013';
                    break;
                }
                case 2: {
                    c24 = 'G';
                    break;
                }
                case 3: {
                    c24 = '&';
                    break;
                }
                default: {
                    c24 = '\f';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "j|(MeL".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = ')';
                    break;
                }
                case 1: {
                    c26 = '\u0013';
                    break;
                }
                case 2: {
                    c26 = 'G';
                    break;
                }
                case 3: {
                    c26 = '&';
                    break;
                }
                default: {
                    c26 = '\f';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "\u00123".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = ')';
                    break;
                }
                case 1: {
                    c28 = '\u0013';
                    break;
                }
                case 2: {
                    c28 = 'G';
                    break;
                }
                case 3: {
                    c28 = '&';
                    break;
                }
                default: {
                    c28 = '\f';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "j|)RiGgjruYv".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = ')';
                    break;
                }
                case 1: {
                    c30 = '\u0013';
                    break;
                }
                case 2: {
                    c30 = 'G';
                    break;
                }
                case 3: {
                    c30 = '&';
                    break;
                }
                default: {
                    c30 = '\f';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "nV\u0013".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = ')';
                    break;
                }
                case 1: {
                    c32 = '\u0013';
                    break;
                }
                case 2: {
                    c32 = 'G';
                    break;
                }
                case 3: {
                    c32 = '&';
                    break;
                }
                default: {
                    c32 = '\f';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        a.z = z;
    }
}
