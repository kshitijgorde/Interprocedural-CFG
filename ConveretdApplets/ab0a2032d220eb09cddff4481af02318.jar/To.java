// 
// Decompiled by Procyon v0.5.30
// 

public class To
{
    private static final String mIb = "name";
    private static final String nIb = "description";
    private static final String oIb = "param";
    String name;
    String CDb;
    String DDb;
    String EDb;
    
    public To() {
        this.name = "";
        this.CDb = "";
        this.DDb = "";
        this.EDb = "";
    }
    
    private static String a(final String s, final String s2) {
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
    
    public To(String string) {
        this.name = "";
        this.CDb = "";
        this.DDb = "";
        this.EDb = "";
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
        this.EDb = string.substring(index + s.length(), lastIndex);
        if (this.EDb.indexOf("//") >= 0) {
            this.EDb = "";
        }
        string = string.substring(0, index) + string.substring(lastIndex + s2.length());
        this.name = a(string, "name");
        this.z();
        this.CDb = a(string, "description");
        this.A();
        this.DDb = "";
        for (int i = 0; i < 3; ++i) {
            final String a = a(string, "param" + String.valueOf(i + 1));
            if (a.length() > 0) {
                this.DDb = this.DDb + a + ",";
            }
        }
        this.B();
    }
    
    private void z() {
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
    
    private void A() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.CDb.length(); ++i) {
            final char char1 = this.CDb.charAt(i);
            if (char1 == ' ' || char1 == '_' || char1 == '%' || char1 == '-' || char1 == '.' || char1 == ':' || char1 == '(' || char1 == ')' || char1 == '&' || Character.isLetterOrDigit(char1)) {
                sb.append(this.CDb.charAt(i));
            }
            if (sb.length() >= 40) {
                break;
            }
        }
        this.CDb = sb.toString();
    }
    
    private void B() {
        final StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < this.DDb.length(); ++i) {
            final char char1 = this.DDb.charAt(i);
            if (char1 == ',' || Character.isDigit(char1)) {
                sb.append(this.DDb.charAt(i));
            }
        }
        final d d = new d(",");
        d.e(sb.toString());
        final StringBuffer sb2 = new StringBuffer("");
        for (int j = 0; j < d.z(); ++j) {
            final String _ = d._(j);
            if (_ != null && _.length() > 0) {
                sb2.append(_);
            }
            sb2.append(",");
        }
        this.DDb = sb2.toString();
    }
    
    public To(final String name, final String cDb, final String dDb, final String eDb) {
        this.name = "";
        this.CDb = "";
        this.DDb = "";
        this.EDb = "";
        this.name = name;
        this.CDb = cDb;
        this.DDb = dDb;
        this.EDb = eDb;
    }
    
    public String h() {
        return this.EDb;
    }
    
    public String b() {
        return this.CDb;
    }
    
    public int[] a() {
        return _(this.DDb);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void h(final String eDb) {
        this.EDb = eDb;
    }
    
    public void f(final String cDb) {
        this.CDb = cDb;
        this.A();
    }
    
    public void g(final String dDb) {
        this.DDb = dDb;
        this.B();
    }
    
    public void setName(final String name) {
        this.name = name;
        this.z();
    }
    
    private static int[] _(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final d d = new d(",");
        d.e(s);
        if (d.z() == 0) {
            return null;
        }
        final int[] array = new int[Math.min(d.z(), 3)];
        boolean b = false;
        try {
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(d._(i));
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
        sb.append(this.CDb);
        sb.append("</");
        sb.append("description");
        sb.append(">\n");
        final int[] _ = _(this.DDb);
        if (_ != null) {
            for (int i = 0; i < _.length; ++i) {
                sb.append("<");
                sb.append("param");
                sb.append(i + 1);
                sb.append(">");
                sb.append(_[i]);
                sb.append("</");
                sb.append("param");
                sb.append(i + 1);
                sb.append(">");
            }
        }
        sb.append("\n");
        sb.append("<![CDATA[");
        sb.append(this.EDb);
        sb.append("]]>\n");
        return sb.toString();
    }
}
