import java.util.StringTokenizer;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    static String na = "\u0725\u0714\u0714\u0708\u0701\u0710\u0744\u070c\u0705\u0717\u0744\u0705\u0744\u0714\u0716\u070b\u0706\u0708\u0701\u0709\u0748\u0744\u0707\u070b\u070a\u0710\u0705\u0707\u0710\u0744\u0710\u070c\u0701\u0744\u0713\u0701\u0706\u0709\u0705\u0717\u0710\u0701\u0716";
    static String oa = "\u0728\u070b\u0705\u0700\u070d\u070a\u0703\u0748\u0744\u0714\u0708\u0701\u0705\u0717\u0701\u0744\u0713\u0705\u070d\u0710\u074a\u074a\u074a";
    static String pa = "\u0725\u070a\u0744\u0701\u0716\u0716\u070b\u0716\u0744\u070c\u0705\u0717\u0744\u070b\u0707\u0707\u0711\u0716\u0701\u0700";
    private Hashtable qa;
    private static String e = "";
    private static String f = "\u074b\u074b";
    private static String g = "\u0759";
    private static String h = "\u0716\u0701\u0705\u0700\u070d\u070a\u0703\u0744\u0702\u0716\u070b\u0709\u0744";
    private static String i = "\u0744\u070c\u0705\u0717\u0744\u070a\u070b\u0710\u0744\u0717\u0711\u0707\u0707\u0701\u0701\u0700\u0701\u0700";
    private static String j = "\u0717\u0710\u0705\u0710\u0711\u0717\u0706\u073b\u0709\u0717\u0703";
    private static String k = "\u0725\u0714\u0714\u0708\u0701\u0710\u0744\u070c\u0705\u0717\u0744\u0705\u0744\u0714\u0716\u070b\u0706\u0708\u0701\u0709\u0748\u0744\u0707\u070b\u070a\u0710\u0705\u0707\u0710\u0744\u0710\u070c\u0701\u0744\u0713\u0701\u0706\u0709\u0705\u0717\u0710\u0701\u0716";
    private static String t = "\u0708\u070b\u0705\u0700\u070d\u070a\u0703\u073b\u0709\u0717\u0703";
    private static String u = "\u0728\u070b\u0705\u0700\u070d\u070a\u0703\u0748\u0744\u0714\u0708\u0701\u0705\u0717\u0701\u0744\u0713\u0705\u070d\u0710\u074a\u074a\u074a";
    private static String v = "\u0708\u070b\u0705\u0700\u0701\u0716\u0716\u073b\u0709\u0717\u0703";
    private static String w = "\u0725\u070a\u0744\u0701\u0716\u0716\u070b\u0716\u0744\u070c\u0705\u0717\u0744\u070b\u0707\u0707\u0711\u0716\u0701\u0700";
    private static String x = "\u0740";
    private static String y = "\u0744";
    
    public r(final URL url, final String s) {
        this.qa = new Hashtable();
        try {
            String line;
            while ((line = new DataInputStream(new URL(url, s).openStream()).readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(r.e)) {
                    if (trim.startsWith(r.f)) {
                        continue;
                    }
                    final int index = trim.indexOf(r.g);
                    if (index == -1) {
                        continue;
                    }
                    this.qa.put(trim.substring(0, index).toLowerCase().trim(), trim.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.out.println(r.h + s + r.i);
        }
    }
    
    public String _() {
        String k = this.qa.get(r.j);
        if (k == null) {
            k = r.k;
        }
        return k;
    }
    
    public String a() {
        String u = this.qa.get(r.t);
        if (u == null) {
            u = r.u;
        }
        return u;
    }
    
    public String b() {
        String w = this.qa.get(r.v);
        if (w == null) {
            w = r.w;
        }
        return w;
    }
    
    public String j(final String s) {
        String e = this.qa.get(s);
        if (e == null) {
            e = r.e;
        }
        return e;
    }
    
    public String b(final String s, final String[] array) {
        final String s2 = this.qa.get(s);
        String s3;
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            s3 = r.e;
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (n >= array.length) {
                    break;
                }
                final String nextToken = stringTokenizer.nextToken();
                String s4;
                if (nextToken.startsWith(r.x)) {
                    s4 = String.valueOf(s3) + array[n++];
                }
                else {
                    s4 = String.valueOf(s3) + nextToken;
                }
                s3 = String.valueOf(s4) + r.y;
            }
        }
        else {
            s3 = r.e;
        }
        return s3.trim();
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x10764);
        }
        return new String(array);
    }
    
    static {
        r.na = a(r.na);
        r.oa = a(r.oa);
        r.pa = a(r.pa);
        r.e = a(r.e);
        r.f = a(r.f);
        r.g = a(r.g);
        r.h = a(r.h);
        r.i = a(r.i);
        r.j = a(r.j);
        r.k = a(r.k);
        r.t = a(r.t);
        r.u = a(r.u);
        r.v = a(r.v);
        r.w = a(r.w);
        r.x = a(r.x);
        r.y = a(r.y);
    }
}
