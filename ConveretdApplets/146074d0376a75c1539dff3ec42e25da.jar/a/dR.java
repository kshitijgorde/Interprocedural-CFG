// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public class dR
{
    public Font q;
    public Font w;
    public Font e;
    public Font r;
    public Font t;
    
    public dR(final Font q) {
        this.q = q;
        this.w = new Font(this.q.getFamily(), 0, this.q.getSize());
        this.e = new Font(this.q.getFamily(), 2, this.q.getSize());
        this.r = new Font(this.q.getFamily(), 1, this.q.getSize());
        this.t = new Font(this.q.getFamily(), 3, this.q.getSize());
    }
    
    public static String q(final char c) {
        return "[" + c + ']';
    }
    
    private static String w(final char c) {
        return "[/" + c + ']';
    }
    
    public static String q(final String s, final int n) {
        switch (n) {
            case 0: {
                return s;
            }
            case 2: {
                return q('i') + " " + s + " " + w('i');
            }
            case 1: {
                return q('b') + " " + s + " " + w('b');
            }
            case 3: {
                return q('i') + q('b') + " " + s + " " + w('b') + w('i');
            }
            default: {
                return s;
            }
        }
    }
    
    public static String q(String s, final char c) {
        if (s.indexOf(q(c)) >= 0) {
            final int index = s.indexOf(q(c));
            final int index2;
            if ((index2 = (s = s.substring(0, index) + s.substring(index + q(c).length(), s.length())).indexOf(w(c))) >= 0) {
                s = s.substring(0, index2) + s.substring(index2 + w(c).length(), s.length());
            }
        }
        return s.trim();
    }
    
    public dR() {
    }
}
