// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.io.File;

public final class j
{
    private File a;
    private boolean b;
    private Map c;
    
    public j(String o) {
        this.c = new ConcurrentHashMap();
        try {
            this.a = o.a((String)(o = o), ".s");
            this = this;
            try {
                if (!this.a.exists()) {
                    this.c = new ConcurrentHashMap();
                    return;
                }
                o = new FileInputStream(this.a);
                try {
                    this.c = this.a((InputStream)o);
                }
                finally {
                    ((FileInputStream)o).close();
                }
            }
            catch (IOException ex) {
                throw new RuntimeException("Failed to read state in file: " + this.a, ex);
            }
        }
        catch (Exception ex2) {
            f.a(ex2);
        }
    }
    
    public static boolean a(String s) {
        return o.a(s = s, ".s").delete();
    }
    
    public final void a(String s, final Serializable s2) {
        if (s2 == null) {
            final j j = this;
            s = s;
            this = j;
            if (j.c.remove(s) != null) {
                this.b = true;
            }
            return;
        }
        if (this.c.containsKey(s) && this.c.get(s).equals(s2)) {
            return;
        }
        this.c.put(s, s2);
        this.b = true;
    }
    
    public final void a(final boolean b) {
        if (!this.b) {
            return;
        }
        this.a();
        this.b = false;
    }
    
    private Map a(final InputStream inputStream) {
        try {
            return (Map)new ObjectInputStream(new GZIPInputStream(inputStream)).readObject();
        }
        catch (Exception ex) {
            throw new RuntimeException("Failed to read state in file: " + this.a, ex);
        }
    }
    
    private synchronized void a() {
        final File file = new File(this.a.getPath() + ".tmp");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            final ObjectOutputStream objectOutputStream;
            (objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(fileOutputStream))).writeObject(this.c);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception ex) {
            throw new RuntimeException("Failed to write state in file: " + file, ex);
        }
        if (this.a.exists() && !this.a.delete()) {
            throw new RuntimeException("Failed to delete existing state: " + this.a);
        }
        if (!file.renameTo(this.a)) {
            throw new RuntimeException("Failed to rename tmp to real state file: " + this.a);
        }
    }
}
