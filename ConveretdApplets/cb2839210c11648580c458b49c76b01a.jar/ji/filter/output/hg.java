// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import ji.util.i;
import ji.util.d;
import java.util.Properties;

public class hg extends hh
{
    private int a;
    private int b;
    private int c;
    
    public hg(final String s, final Properties properties) {
        this.a = -1;
        this.b = 0;
        this.c = -1;
        boolean b = false;
        final String property = properties.getProperty("tiffVersion");
        if (!d.by(property) && this.a(property, "6")) {
            b = true;
        }
        final String property2 = properties.getProperty("tiffSaveFormat");
        if (!d.by(property2)) {
            if (this.a(property2, "lzw")) {
                this.a(1);
            }
            else if (this.a(property2, "g4")) {
                this.a(0);
            }
            else if (this.a(property2, "jpeg") || this.a(property2, "jpg")) {
                if (b) {
                    this.a(2);
                }
                else {
                    this.a(3);
                }
            }
        }
        final String property3 = properties.getProperty("tiffMonoSaveFormat");
        if (!d.by(property3)) {
            if (this.a(property3, "lzw")) {
                this.c = 1;
            }
            else if (this.a(property3, "g4")) {
                this.c = 0;
            }
            else if (this.a(property3, "jpeg") || this.a(property3, "jpg")) {
                if (b) {
                    this.c = 2;
                }
                else {
                    this.c = 3;
                }
            }
        }
        final String property4 = properties.getProperty("tiffJPEGWriteQuality");
        if (!d.by(property4)) {
            try {
                this.a = Integer.parseInt(property4);
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    private boolean a(final String s, final String s2) {
        return s.equalsIgnoreCase(s2);
    }
    
    private void a(final int b) {
        this.b = b;
    }
    
    public int a() {
        return this.b;
    }
    
    public int b() {
        if (this.c != -1) {
            return this.c;
        }
        return this.a();
    }
    
    public int c() {
        if (this.a == -1) {
            return i.d(5);
        }
        return this.a;
    }
}
