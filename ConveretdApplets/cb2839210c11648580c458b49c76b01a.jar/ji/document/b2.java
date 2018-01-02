// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import java.io.File;
import java.util.Vector;

public class b2
{
    private Vector a;
    private String b;
    
    public b2() {
        this.a = new Vector();
    }
    
    public void a(final gm gm) {
        this.a.add(gm);
    }
    
    public gm[] a() {
        return this.a.toArray(new gm[this.a.size()]);
    }
    
    public void a(final String b) {
        if (b.endsWith(File.separator)) {
            this.b = b;
        }
        else {
            this.b = String.valueOf(String.valueOf(b)).concat(String.valueOf(String.valueOf(File.separator)));
        }
    }
    
    public String b() {
        return this.b;
    }
    
    public void c() {
        this.a.removeAllElements();
    }
}
