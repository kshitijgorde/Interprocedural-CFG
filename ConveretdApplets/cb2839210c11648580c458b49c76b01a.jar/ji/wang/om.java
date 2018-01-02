// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;

class om implements ht
{
    public String a;
    
    public om(final ac ac, final String s) {
        try {
            char c = (char)ac.h();
            final StringBuffer sb = new StringBuffer();
            while (c != '\0') {
                sb.append(c);
                c = (char)ac.h();
            }
            this.a = sb.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation String: ".concat(String.valueOf(String.valueOf(this.a))));
        }
    }
    
    public void a() {
        this.a = null;
    }
    
    public int b() {
        return this.a.length();
    }
    
    public String c() {
        return "";
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final byte[] bytes = this.a.getBytes();
        ac.b(bytes);
        return bytes.length;
    }
}
