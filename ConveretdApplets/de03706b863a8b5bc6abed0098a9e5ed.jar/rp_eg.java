import java.awt.Color;
import java.util.Vector;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.Stack;
import java.io.Writer;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_eg
{
    private Writer a;
    private Stack a;
    private static String a;
    
    public rp_eg(final OutputStream outputStream) {
        this.a = new Stack();
        (this.a = new OutputStreamWriter(outputStream)).write("<?xml version=\"1.0\"?>" + rp_eg.a);
    }
    
    public final void a(final String s, final Vector vector) {
        this.a.write("<" + s);
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                this.a.write(" " + vector.elementAt(i));
            }
        }
        this.a.write(">" + rp_eg.a);
        this.a.push(s);
    }
    
    public final void a() {
        this.a.write("</" + this.a.pop() + ">" + rp_eg.a);
        this.a.flush();
    }
    
    public final void a(final String s, final Vector vector, final String s2) {
        this.a.write("<" + s);
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                this.a.write(" " + vector.elementAt(i));
            }
        }
        this.a.write(">" + a(s2) + "</" + s + ">" + rp_eg.a);
        this.a.flush();
    }
    
    public final void a(final String s, final Color color) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(a("r", "" + color.getRed()));
        vector.addElement(a("g", "" + color.getGreen()));
        vector.addElement(a("b", "" + color.getBlue()));
        this.a(s, vector);
        this.a();
    }
    
    public static String a(final String s, final String s2) {
        return s + "=\"" + a(s2) + "\"";
    }
    
    private static String a(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        final char[] charArray = s.toCharArray();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charArray.length; ++i) {
            switch (charArray[i]) {
                case '<': {
                    sb.append("&lt;");
                    break;
                }
                case '>': {
                    sb.append("&gt;");
                    break;
                }
                case '&': {
                    sb.append("&amp;");
                    break;
                }
                case '\'': {
                    sb.append("&#39;");
                    break;
                }
                case '\"': {
                    sb.append("&quot;");
                    break;
                }
                case '\\': {
                    sb.append("&#92;");
                    break;
                }
                case '\u0085': {
                    sb.append("&#133;");
                    break;
                }
                default: {
                    sb.append(charArray[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    static {
        rp_eg.a = "\n";
    }
}
