import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class So
{
    private static final String pIb = "indicator";
    
    public static String _(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return "";
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
    
    public static Vector _(final String s) {
        final Vector<To> vector = new Vector<To>();
        if (s == null || s.length() == 0) {
            return vector;
        }
        final d d = new d("<indicator>");
        d.e(s);
        for (int i = 0; i < d.z(); ++i) {
            vector.addElement(new To(d._(i)));
        }
        return vector;
    }
}
