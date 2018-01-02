// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel;

public final class g
{
    private String a;
    private FileChannel b;
    private FileLock c;
    
    public g(final String a) {
        this.a = a;
    }
    
    public final boolean a() {
        String s = "";
        try {
            if (!(s = System.getProperty("java.io.tmpdir")).endsWith(File.separator)) {
                s += File.separator;
            }
            s = s + o.a() + "_" + this.a + ".lck";
            this.b = new RandomAccessFile(new File(s), "rw").getChannel();
            this.c = this.b.tryLock();
            if (this.c != null) {
                f.b("Acquired lock for path: " + s + " (name: " + this.a + ")");
                return true;
            }
            f.c("Failed to get lock for path: " + s + " (name: " + this.a + ")");
        }
        catch (Exception ex) {
            f.a("Failed to get lock for path: " + s + " (name: " + this.a + ")", ex);
        }
        this.b();
        return false;
    }
    
    public final void b() {
        f.b("Releasing lock with name: " + this.a);
        try {
            if (this.c != null) {
                this.c.release();
            }
            if (this.b != null) {
                this.b.close();
            }
        }
        catch (IOException ex) {
            f.c("Failed to release lock with name: " + this.a);
        }
    }
}
