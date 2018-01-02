// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.F.A.A.A;

import java.io.FilterOutputStream;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

public class A extends ZipOutputStream
{
    static long D;
    static int A;
    static int C;
    private _A B;
    
    public A(final OutputStream outputStream) {
        super(outputStream);
        final _A a = new _A(outputStream);
        this.B = a;
        this.out = a;
    }
    
    public void finish() throws IOException {
        this.closeEntry();
        this.B.C = true;
        this.B.D = 0L;
        this.B.B = -1L;
        super.finish();
    }
    
    public void putNextEntry(final ZipEntry zipEntry) throws IOException {
        this.closeEntry();
        this.B.D = 0L;
        this.B.B = jmaster.util.F.A.A.A.A.C;
        super.putNextEntry(zipEntry);
    }
    
    static {
        jmaster.util.F.A.A.A.A.D = 1347092738L;
        jmaster.util.F.A.A.A.A.A = 5;
        jmaster.util.F.A.A.A.A.C = 7;
    }
    
    class _A extends FilterOutputStream
    {
        long D;
        long B;
        boolean C;
        int A;
        
        public _A(final OutputStream outputStream) {
            super(outputStream);
            this.D = 0L;
            this.B = -1L;
            this.C = false;
            this.A = 0;
        }
        
        public void write(final int n) throws IOException {
            if (this.D == this.B) {
                this.out.write(n | 0x8);
            }
            else {
                this.out.write(n);
            }
            ++this.D;
            if (this.C) {
                this.A <<= 8;
                this.A |= n;
                if (this.A == jmaster.util.F.A.A.A.A.D) {
                    this.D = 0L;
                    this.B = jmaster.util.F.A.A.A.A.A;
                }
            }
        }
    }
}
