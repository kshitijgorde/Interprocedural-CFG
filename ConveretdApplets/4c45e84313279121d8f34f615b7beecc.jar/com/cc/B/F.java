// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.B;

import java.io.IOException;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.URL;
import com.cc.C.B;

public class F
{
    public static final int L = 0;
    public static final int I = 1;
    public static final int J = 2;
    public static final int M = 3;
    public static final int F = 4;
    public static final int H = 5;
    public static final int K = 6;
    public static final int C = 7;
    public static final int B = 8;
    public int E;
    public B G;
    public String D;
    public D A;
    
    public F(final int e) {
        this.G = new B();
        this.E = e;
    }
    
    public static F B() {
        return new F(6);
    }
    
    public static F A() {
        return new F(2);
    }
    
    public static F C() {
        return new F(3);
    }
    
    public static F A(final E e, final String d) {
        final F f = new F(0);
        f.G.A(e);
        f.D = d;
        return f;
    }
    
    public static F A(final E e, final E e2) {
        final F f = new F(1);
        f.G.A(e2);
        f.G.A(e);
        return f;
    }
    
    public static F A(final D d, final D a) {
        final F f = new F(5);
        f.A = a;
        f.G.A(a.J());
        f.G.A(d.J());
        return f;
    }
    
    public static F A(final D a) {
        final F f = new F(4);
        f.A = a;
        f.G.A(a.J());
        return f;
    }
    
    public static String A(final String s) throws IOException {
        BufferedReader bufferedReader = null;
        final int index = s.indexOf(63);
        try {
            final URLConnection openConnection = new URL(s.substring(0, index)).openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
            dataOutputStream.writeBytes(s.substring(index + 1));
            dataOutputStream.flush();
            dataOutputStream.close();
            bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while (null != (line = bufferedReader.readLine())) {
                line.toLowerCase();
                final int index2 = line.indexOf("url=");
                if (index2 > -1) {
                    return line.substring(index2 + "url=".length());
                }
            }
            return null;
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
