// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Random;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;

public final class ee
{
    public File q;
    private File w;
    public FileOutputStream q;
    
    public final void q() {
        try {
            this.q.close();
        }
        catch (IOException ex) {}
        this.q = null;
        if (this.q != null && this.q != this.w) {
            if (this.w.exists()) {
                this.w.delete();
            }
            this.q.renameTo(this.w);
        }
        this.q = null;
    }
    
    public final void finalize() {
        if (this.q != null) {
            try {
                this.q.close();
            }
            catch (IOException ex) {}
        }
        if (this.q != null) {
            this.q.delete();
        }
        this.q = null;
        this.q = null;
    }
    
    public ee(final File file, final File file2) {
        this.w = file;
        if (file.exists()) {
            if (!file2.exists()) {
                file2.mkdirs();
            }
            final Random random = new Random();
            do {
                this.q = new File(file2, Long.toHexString(random.nextLong()) + ".temp");
            } while (this.q.exists());
            return;
        }
        this.q = file;
    }
}
