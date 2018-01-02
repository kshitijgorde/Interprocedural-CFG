// 
// Decompiled by Procyon v0.5.30
// 

public class gea
{
    private static final String Qoa = "name";
    private static final String Roa = "description";
    private static final String Soa = "param";
    String name;
    String Na;
    String Oa;
    String Pa;
    
    public gea() {
        this.name = "";
        this.Na = "";
        this.Oa = "";
        this.Pa = "";
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
    
    public gea(String string) {
        this.name = "";
        this.Na = "";
        this.Oa = "";
        this.Pa = "";
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
        this.Pa = string.substring(index + s.length(), lastIndex);
        if (this.Pa.indexOf("//") >= 0) {
            this.Pa = "";
        }
        string = string.substring(0, index) + string.substring(lastIndex + s2.length());
        this.name = b(string, "name");
        this.D();
        this.Na = b(string, "description");
        this.E();
        this.Oa = "";
        for (int i = 0; i < 3; ++i) {
            final String b = b(string, "param" + String.valueOf(i + 1));
            if (b.length() > 0) {
                this.Oa = this.Oa + b + ",";
            }
        }
        this.F();
    }
    
    private void D() {
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
    
    private void E() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.Na.length(); ++i) {
            final char char1 = this.Na.charAt(i);
            if (char1 == ' ' || char1 == '_' || char1 == '%' || char1 == '-' || char1 == '.' || char1 == ':' || char1 == '(' || char1 == ')' || char1 == '&' || Character.isLetterOrDigit(char1)) {
                sb.append(this.Na.charAt(i));
            }
            if (sb.length() >= 40) {
                break;
            }
        }
        this.Na = sb.toString();
    }
    
    private void F() {
        final StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < this.Oa.length(); ++i) {
            final char char1 = this.Oa.charAt(i);
            if (char1 == ',' || Character.isDigit(char1)) {
                sb.append(this.Oa.charAt(i));
            }
        }
        final try try1 = new try(",");
        try1.l(sb.toString());
        final StringBuffer sb2 = new StringBuffer("");
        for (int j = 0; j < try1.g(); ++j) {
            final String a = try1.a(j);
            if (a != null && a.length() > 0) {
                sb2.append(a);
            }
            sb2.append(",");
        }
        this.Oa = sb2.toString();
    }
    
    public gea(final String name, final String na, final String oa, final String pa) {
        this.name = "";
        this.Na = "";
        this.Oa = "";
        this.Pa = "";
        this.name = name;
        this.Na = na;
        this.Oa = oa;
        this.Pa = pa;
    }
    
    public String j() {
        return this.Pa;
    }
    
    public String i() {
        return this.Na;
    }
    
    public int[] b() {
        return a(this.Oa);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void i(final String pa) {
        this.Pa = pa;
    }
    
    public void g(final String na) {
        this.Na = na;
        this.E();
    }
    
    public void h(final String oa) {
        this.Oa = oa;
        this.F();
    }
    
    public void setName(final String name) {
        this.name = name;
        this.D();
    }
    
    private static int[] a(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final try try1 = new try(",");
        try1.l(s);
        if (try1.g() == 0) {
            return null;
        }
        final int[] array = new int[Math.min(try1.g(), 3)];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(try1.a(i));
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
        sb.append(this.Na);
        sb.append("</");
        sb.append("description");
        sb.append(">\n");
        final int[] a = a(this.Oa);
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
        sb.append(this.Pa);
        sb.append("]]>\n");
        return sb.toString();
    }
}
