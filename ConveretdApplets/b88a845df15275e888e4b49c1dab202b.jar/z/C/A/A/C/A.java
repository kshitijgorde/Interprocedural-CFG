// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.C;

import java.io.File;
import z.C.A.A.B.J;
import z.C.A.A.B.A.G;
import z.C.A.A.B.A.N;
import z.C.A.A.B.F;
import java.io.FileFilter;
import java.io.FilenameFilter;

public abstract class A implements FilenameFilter, FileFilter
{
    F C;
    N B;
    G A;
    
    A(final F c, final N b, final String s) {
        this.C = c;
        this.B = b;
        this.A(s);
    }
    
    A(final F c, final N b, final String s, final int n) {
        this.C = c;
        this.B = b;
        this.A(s, n);
    }
    
    A(final F f, final N n) {
        this(f, n, "");
    }
    
    public void A(final String s) throws J {
        this.A = this.C.A(s);
    }
    
    public void A(final String s, final int n) throws J {
        this.A = this.C.A(s, n);
    }
    
    public boolean accept(final File file, final String s) {
        synchronized (this.B) {
            return this.B.A(s, this.A);
        }
    }
    
    public boolean accept(final File file) {
        synchronized (this.B) {
            return this.B.A(file.getName(), this.A);
        }
    }
}
