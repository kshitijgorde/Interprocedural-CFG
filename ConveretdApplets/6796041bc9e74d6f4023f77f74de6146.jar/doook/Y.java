// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Random;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class Y
{
    private File a;
    private File b;
    private RandomAccessFile a;
    
    public RandomAccessFile a() {
        if (this.a == null) {
            this.a = new RandomAccessFile(this.a, "rw");
        }
        return this.a;
    }
    
    public void c() {
        try {
            this.a.close();
        }
        catch (IOException ex) {}
        this.a = null;
        if (this.a != null && this.a != this.b) {
            if (this.b.exists()) {
                this.b.delete();
            }
            this.a.renameTo(this.b);
        }
        this.a = null;
    }
    
    public void d() {
        if (this.a != null) {
            try {
                this.a.close();
            }
            catch (IOException ex) {}
        }
        if (this.a != null) {
            this.a.delete();
        }
        this.a = null;
        this.a = null;
    }
    
    public void finalize() {
        this.d();
    }
    
    public Y(final File file, final File file2) {
        this.b = file;
        if (file.exists()) {
            if (!file2.exists()) {
                file2.mkdirs();
            }
            final Random random = new Random();
            do {
                this.a = new File(file2, Long.toHexString(random.nextLong()) + ".temp");
            } while (this.a.exists());
        }
        else {
            this.a = file;
        }
    }
}
