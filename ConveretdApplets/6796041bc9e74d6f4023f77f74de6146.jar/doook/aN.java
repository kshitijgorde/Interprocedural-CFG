// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.Vector;
import java.io.RandomAccessFile;
import java.io.File;

public class aN
{
    private Y a;
    private File b;
    private RandomAccessFile a;
    private Vector a;
    private cD b;
    
    public synchronized void a(final cD cd) {
        this.a.addElement(cd);
    }
    
    public synchronized cD a(final int n) {
        return this.a.elementAt(n);
    }
    
    public synchronized int a() {
        return this.a.size();
    }
    
    public synchronized void c() {
        if (this.a != null) {
            try {
                this.b.a(this.a);
                for (int a = this.a(), i = 0; i < a; ++i) {
                    this.a(i).a(this.a);
                }
            }
            finally {
                this.a.c();
                this.a = null;
            }
        }
        else if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }
    
    public aN(final File b, final File file, final boolean b2) {
        this.a = new Vector();
        this.b = b;
        if (b2) {
            this.a = new Y(b, file);
            this.a = this.a.a();
            (this.b = new cD(67840, 1)).a(0, 0, 288233675760336897L);
        }
        else {
            this.a = new RandomAccessFile(b, "r");
            try {
                if (this.a.readInt() != 67840) {
                    throw new IllegalArgumentException("Not a recognized settings file.");
                }
                this.a.seek(0L);
                this.b = new cD(this.a);
                while (this.a.getFilePointer() < this.a.length()) {
                    this.a(new cD(this.a));
                }
            }
            catch (IOException ex) {
                System.out.println(b);
                throw ex;
            }
            finally {
                this.c();
            }
        }
    }
}
