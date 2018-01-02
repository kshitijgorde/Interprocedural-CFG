// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;

class hx implements ht
{
    public String a;
    
    public hx(final String a) {
        this.a = a;
    }
    
    public hx(final ac ac, final String s) throws Exception {
        char c = (char)ac.h();
        final StringBuffer sb = new StringBuffer();
        try {
            while (c != '\0') {
                sb.append(c);
                c = (char)ac.h();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.a = sb.toString();
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation filename: ".concat(String.valueOf(String.valueOf(this.a))));
        }
    }
    
    public void a() {
        this.a = null;
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final byte[] array = new byte[this.a.length() + 1];
        final byte[] bytes = this.a.getBytes();
        System.arraycopy(bytes, 0, array, 0, bytes.length);
        ac.b(array);
        return array.length;
    }
    
    public int b() {
        return this.a.length() + 1;
    }
    
    public String c() {
        return "OiFilNam";
    }
}
