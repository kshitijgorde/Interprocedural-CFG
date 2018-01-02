// 
// Decompiled by Procyon v0.5.30
// 

public class bj
{
    private static final String n = "name";
    private static final String o = "description";
    private static final String p = "param";
    String name;
    String Ta;
    String Ua;
    String Va;
    
    public bj() {
        this.name = "";
        this.Ta = "";
        this.Ua = "";
        this.Va = "";
    }
    
    private static String b(final String s, final String s2) {
        if (s == null || s2 == null) {
            return "";
        }
        final String string = "<" + s2 + ">";
        final String string2 = "</" + s2 + ">";
        final int index = s.indexOf(string);
        final int index2 = s.indexOf(string2);
        if (index == -1 || index2 == -1 || index >= index2) {
            return "";
        }
        return s.substring(index + string.length(), index2);
    }
    
    public bj(String string) {
        this.name = "";
        this.Ta = "";
        this.Ua = "";
        this.Va = "";
        if (string == null || string.length() == 0) {
            return;
        }
        final String s = "<![CDATA[";
        final String s2 = "]]>";
        final int index = string.indexOf(s);
        final int lastIndex = string.lastIndexOf(s2);
        if (index == -1 || lastIndex == -1 || index >= lastIndex) {
            return;
        }
        this.Va = string.substring(index + s.length(), lastIndex);
        if (this.Va.indexOf("//") >= 0) {
            this.Va = "";
        }
        string = string.substring(0, index) + string.substring(lastIndex + s2.length());
        this.name = b(string, "name");
        this.g();
        this.Ta = b(string, "description");
        this.U();
        this.Ua = "";
        for (int i = 0; i < 3; ++i) {
            final String b = b(string, "param" + String.valueOf(i + 1));
            if (b.length() > 0) {
                this.Ua = this.Ua + b + ",";
            }
        }
        this.V();
    }
    
    private void g() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.name.length(); ++i) {
            final char char1 = this.name.charAt(i);
            if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9') || char1 == '%' || char1 == '&') {
                sb.append(char1);
            }
            if (sb.length() >= 6) {
                break;
            }
        }
        this.name = sb.toString();
    }
    
    private void U() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.Ta.length(); ++i) {
            final char char1 = this.Ta.charAt(i);
            if (char1 == ' ' || char1 == '_' || char1 == '%' || char1 == '-' || char1 == '.' || char1 == ':' || char1 == '(' || char1 == ')' || char1 == '&' || Character.isLetterOrDigit(char1)) {
                sb.append(this.Ta.charAt(i));
            }
            if (sb.length() >= 40) {
                break;
            }
        }
        this.Ta = sb.toString();
    }
    
    private void V() {
        final StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < this.Ua.length(); ++i) {
            final char char1 = this.Ua.charAt(i);
            if (char1 == ',' || Character.isDigit(char1)) {
                sb.append(this.Ua.charAt(i));
            }
        }
        final u u = new u(",");
        u.m(sb.toString());
        final StringBuffer sb2 = new StringBuffer("");
        for (int j = 0; j < u.a(); ++j) {
            final String b = u.b(j);
            if (b != null && b.length() > 0) {
                sb2.append(b);
            }
            sb2.append(",");
        }
        this.Ua = sb2.toString();
    }
    
    public bj(final String name, final String ta, final String ua, final String va) {
        this.name = "";
        this.Ta = "";
        this.Ua = "";
        this.Va = "";
        this.name = name;
        this.Ta = ta;
        this.Ua = ua;
        this.Va = va;
    }
    
    public String m() {
        return this.Va;
    }
    
    public String l() {
        return this.Ta;
    }
    
    public int[] _() {
        return a(this.Ua);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void j(final String va) {
        this.Va = va;
    }
    
    public void h(final String ta) {
        this.Ta = ta;
        this.U();
    }
    
    public void i(final String ua) {
        this.Ua = ua;
        this.V();
    }
    
    public void setName(final String name) {
        this.name = name;
        this.g();
    }
    
    private static int[] a(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final u u = new u(",");
        u.m(s);
        if (u.a() == 0) {
            return null;
        }
        final int[] array = new int[Math.min(u.a(), 3)];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(u.b(i));
                if (array[i] < 2 || array[i] > 999) {
                    b = true;
                    break;
                }
            }
        }
        catch (Exception ex) {
            b = true;
        }
        if (b) {
            return null;
        }
        return array;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(512);
        sb.append("<");
        sb.append("name");
        sb.append(">");
        sb.append(this.name);
        sb.append("</");
        sb.append("name");
        sb.append(">\n");
        sb.append("<");
        sb.append("description");
        sb.append(">");
        sb.append(this.Ta);
        sb.append("</");
        sb.append("description");
        sb.append(">\n");
        final int[] a = a(this.Ua);
        if (a != null) {
            for (int i = 0; i < a.length; ++i) {
                sb.append("<");
                sb.append("param");
                sb.append(i + 1);
                sb.append(">");
                sb.append(a[i]);
                sb.append("</");
                sb.append("param");
                sb.append(i + 1);
                sb.append(">");
            }
        }
        sb.append("\n");
        sb.append("<![CDATA[");
        sb.append(this.Va);
        sb.append("]]>\n");
        return sb.toString();
    }
}
