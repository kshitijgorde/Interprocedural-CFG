import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class fea
{
    private static final String Toa = "indicator";
    
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
        final Vector<gea> vector = new Vector<gea>();
        if (s == null || s.length() == 0) {
            return vector;
        }
        final try try1 = new try("<indicator>");
        try1.l(s);
        for (int i = 0; i < try1.g(); ++i) {
            vector.addElement(new gea(try1.a(i)));
        }
        return vector;
    }
}
