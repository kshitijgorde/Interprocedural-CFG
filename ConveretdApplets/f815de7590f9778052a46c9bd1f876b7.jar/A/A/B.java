// 
// Decompiled by Procyon v0.5.30
// 

package A.A;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class B
{
    private Hashtable A;
    private String B;
    private Vector C;
    
    public D I(final String s) {
        return this.A.get(s);
    }
    
    public boolean A(final String s) {
        return this.A.containsKey(s);
    }
    
    public String H(final String s) {
        return this.I(s).A();
    }
    
    private Enumeration B() {
        return this.C.elements();
    }
    
    public Enumeration G(final String s) {
        final Vector<B> vector = new Vector<B>();
        final Enumeration b = this.B();
        while (b.hasMoreElements()) {
            final B nextElement = b.nextElement();
            if (nextElement instanceof B && nextElement.D().equals(s)) {
                vector.addElement(nextElement);
            }
        }
        return vector.elements();
    }
    
    public Enumeration C() {
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration b = this.B();
        while (b.hasMoreElements()) {
            final Object nextElement = b.nextElement();
            if (nextElement instanceof B) {
                vector.addElement(nextElement);
            }
        }
        return vector.elements();
    }
    
    public boolean D(final String s) {
        return this.B(s) != null;
    }
    
    public B B(final String s) {
        final Enumeration b = this.B();
        while (b.hasMoreElements()) {
            final B nextElement = b.nextElement();
            if (nextElement instanceof B && nextElement.D().equals(s)) {
                return nextElement;
            }
        }
        return null;
    }
    
    public String D() {
        final int index = this.B.indexOf(58);
        return (index > -1) ? this.B.substring(index + 1) : this.B;
    }
    
    public String A() {
        return this.E().trim();
    }
    
    public void E(final String s) {
        this.A((Object)C(s));
    }
    
    public String E() {
        final StringBuffer sb = new StringBuffer();
        final Enumeration b = this.B();
        while (b.hasMoreElements()) {
            sb.append(b.nextElement().toString());
        }
        return sb.toString();
    }
    
    public static String C(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "&'\"<>", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() == 1) {
                final char char1 = nextToken.charAt(0);
                if (char1 == '\'' || char1 == '\"' || char1 == '<' || char1 == '>' || char1 == '&') {
                    sb.append("&#" + (char1 + '\0') + ";");
                    continue;
                }
            }
            sb.append(nextToken);
        }
        try {
            return new String(sb.toString().getBytes("UTF8"), "UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public String toString() {
        String s = "<" + this.B;
        final Enumeration<String> keys = this.A.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            s = s + " " + s2 + "=\"" + C(this.I(s2).A()) + "\"";
        }
        return s + ">" + this.E() + "</" + this.B + ">";
    }
    
    public B C(final String s, final String s2) {
        final B b = new B(s);
        if (s2 != null) {
            b.E(s2);
        }
        this.A(b);
        return b;
    }
    
    public void B(final String s, final String s2) {
        if (s2 != null) {
            this.A.put(s, new D(s, s2));
        }
    }
    
    public void A(final String s, final int n) {
        this.B(s, String.valueOf(n));
    }
    
    public void A(final String s, final double n) {
        this.B(s, Double.toString(n));
    }
    
    public void A(final String s, final boolean b) {
        this.B(s, String.valueOf(b));
    }
    
    public void A(final String s, final Color color) {
        if (color == null) {
            return;
        }
        this.B(s, "#" + this.A(color.getRed()) + this.A(color.getGreen()) + this.A(color.getBlue()));
    }
    
    private String A(final int n) {
        if (n < 16) {
            return "0" + Integer.toHexString(n).toUpperCase();
        }
        return Integer.toHexString(n).toUpperCase();
    }
    
    public void A(final Object o) {
        this.C.addElement(o);
    }
    
    public B(final String b) {
        this.A = new Hashtable();
        this.C = new Vector();
        this.B = b;
    }
    
    public B A(final String s, final String s2) {
        final Enumeration g = this.G(s);
        while (g.hasMoreElements()) {
            final B b = g.nextElement();
            if (b.A("id") && b.H("id").equals(s2)) {
                return b;
            }
        }
        throw new IllegalArgumentException("There is no element with this name,id = " + s + "," + s2);
    }
    
    public boolean F(final String s) {
        return this.B.equals(s);
    }
}
