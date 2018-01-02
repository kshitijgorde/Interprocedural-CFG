// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.io.DataInput;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutput;
import java.util.Vector;
import java.io.DataOutputStream;
import java.io.RandomAccessFile;

public class cC
{
    private ee q;
    private RandomAccessFile q;
    private DataOutputStream q;
    private Vector q;
    private es q;
    
    public final synchronized void q(final es es) {
        this.q.addElement(es);
    }
    
    public final synchronized es q(final int n) {
        return this.q.elementAt(n);
    }
    
    public final synchronized int q() {
        return this.q.size();
    }
    
    public final synchronized void q() {
        if (this.q != null) {
            try {
                this.q.q(this.q);
                for (int q = this.q(), i = 0; i < q; ++i) {
                    this.q(i).q(this.q);
                }
                return;
            }
            finally {
                this.q.q();
                this.q = null;
            }
        }
        if (this.q != null) {
            this.q.close();
            this.q = null;
        }
    }
    
    public cC(final File file, final File file2, final boolean b) {
        this.q = new Vector();
        if (b) {
            this.q = new ee(file, file2);
            final ee q;
            if ((q = this.q).q == null) {
                q.q = new FileOutputStream(q.q);
            }
            this.q = new DataOutputStream(q.q);
            (this.q = new es(67840, 1)).q(0, 0, 288233675760336898L);
            return;
        }
        this.q = new RandomAccessFile(file, "r");
        try {
            if (this.q.readInt() == 67840) {
                this.q.seek(0L);
                this.q = new es(this.q);
                while (this.q.getFilePointer() < this.q.length()) {
                    this.q(new es(this.q));
                }
                return;
            }
            throw new IllegalArgumentException("Not a recognized settings file.");
        }
        catch (IOException ex) {
            System.out.println(file);
            throw ex;
        }
        finally {
            this.q();
        }
    }
    
    public cC() {
    }
}
