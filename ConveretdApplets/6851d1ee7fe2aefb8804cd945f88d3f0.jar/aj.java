import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class aj
{
    private static final String q = "indicator";
    
    public static String b(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return "<indicators></indicators>";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
        sb.append("<indicators>\n");
        for (int i = 0; i < vector.size(); ++i) {
            sb.append("<");
            sb.append("indicator");
            sb.append(">\n");
            sb.append(vector.elementAt(i).toString());
            sb.append("</");
            sb.append("indicator");
            sb.append(">\n");
        }
        sb.append("</indicators>\n");
        return sb.toString();
    }
    
    public static Vector a(final String s) {
        final Vector<bj> vector = new Vector<bj>();
        if (s == null || s.length() == 0) {
            return vector;
        }
        final u u = new u("<indicator>");
        u.m(s);
        for (int i = 0; i < u.a(); ++i) {
            vector.addElement(new bj(u.b(i)));
        }
        return vector;
    }
}
