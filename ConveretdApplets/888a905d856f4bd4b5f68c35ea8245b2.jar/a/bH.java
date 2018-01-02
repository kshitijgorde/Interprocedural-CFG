// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.io.DataInput;
import java.io.File;
import java.io.DataOutput;
import java.util.Vector;
import java.io.RandomAccessFile;

public class bH
{
    public br q;
    public RandomAccessFile q;
    public Vector q;
    public dI q;
    
    public bH() {
    }
    
    public static void q(final dy[] array) {
        for (int i = 1; i < array.length; ++i) {
            dy dy;
            int n;
            for (dy = array[i], n = i; n > 0 && array[n - 1].q(dy) >= 0; --n) {
                array[n] = array[n - 1];
            }
            array[n] = dy;
        }
    }
    
    public synchronized void q(final dI di) {
        this.q.addElement(di);
    }
    
    public synchronized dI q(final int n) {
        return this.q.elementAt(n);
    }
    
    public synchronized int q() {
        return this.q.size();
    }
    
    public synchronized void q() {
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
    
    public bH(final File file, final File file2, final boolean b) {
        this.q = new Vector();
        if (b) {
            this.q = new br(file, file2);
            final br q;
            if ((q = this.q).q == null) {
                q.q = new RandomAccessFile(q.q, "rw");
            }
            this.q = q.q;
            (this.q = new dI(67840, 1)).q(0, 0, 288233675760336898L);
            return;
        }
        this.q = new RandomAccessFile(file, "r");
        try {
            if (this.q.readInt() == 67840) {
                this.q.seek(0L);
                this.q = new dI(this.q);
                while (this.q.getFilePointer() < this.q.length()) {
                    this.q(new dI(this.q));
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
}
