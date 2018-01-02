// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.File;

final class h implements Runnable
{
    private m a;
    private byte[] b;
    private int c;
    private long d;
    private int e;
    private /* synthetic */ l f;
    
    h(final l f, final m a) {
        this.f = f;
        this.e = 3;
        this.c = 0;
        this.a = a;
        this.d = System.currentTimeMillis();
        final Thread thread;
        (thread = new Thread(this, "TileDataSource-Fetcher")).setDaemon(true);
        thread.start();
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof h) {
            return ((h)o).a.equals(this.a);
        }
        return super.equals(o);
    }
    
    public final int hashCode() {
        return this.a.hashCode();
    }
    
    private void a(final File file) {
        if (this.f.h) {
            System.out.println("Fetch from file system begun for tile: " + this.a);
        }
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            final byte[] array = new byte[4096];
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = fileInputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            this.b = byteArrayOutputStream.toByteArray();
            this.c = 1;
            if (this.f.h) {
                System.out.println("Fetch from file system completed for tile: " + this.a.toString());
            }
            this.f.c(this.a);
            try {
                fileInputStream.close();
            }
            catch (IOException ex) {}
            try {
                byteArrayOutputStream.close();
            }
            catch (IOException ex2) {}
        }
        catch (IOException ex3) {
            this.b = null;
            this.c = 2;
            if (this.f.h) {
                System.out.println("Fetch from file system failed for tile: " + this.a.toString());
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }
                catch (IOException ex4) {}
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                }
                catch (IOException ex5) {}
            }
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }
                catch (IOException ex6) {}
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                }
                catch (IOException ex7) {}
            }
        }
        final h h;
        if (h.b != null && h.b.length == 0) {
            h.b = null;
            h.c = 2;
            if (h.f.h) {
                System.out.println("Fetch from file system failed for tile: " + h.a.toString());
            }
        }
    }
    
    private void b(final File file) {
        if (this.f.h) {
            System.out.println("Fetch from web started for tile: " + this.a.toString());
        }
        int n = 1;
        do {
            final String string = this.f.f + "/" + this.a.a();
            if (this.f.h) {
                System.out.println("Fetching using URL=" + string);
            }
            try {
                this.b = this.f.b.a(string, 1222318800000L);
            }
            catch (Exception ex) {
                System.err.println("COM.NextBus.HttMapClient.TileDataSource: Caught " + ex + ".");
                this.b = null;
            }
            if (this.b != null) {
                this.c = 1;
            }
            ++n;
        } while (this.c != 1 && n <= this.e);
        if (this.c != 1) {
            this.c = 2;
        }
        if (file != null && this.c == 1) {
            FileOutputStream fileOutputStream = null;
            try {
                (fileOutputStream = new FileOutputStream(file)).write(this.b);
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {
                file.delete();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException ex4) {}
                }
            }
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException ex5) {}
                }
            }
        }
        final h h;
        if (h.c == 1) {
            if (h.f.h) {
                System.out.println("Fetch completed for tile: " + h.a.toString());
            }
        }
        else if (h.f.h) {
            System.out.println("Fetch failed for tile: " + h.a.toString());
        }
        if (h.c == 1) {
            h.f.c(h.a);
        }
    }
    
    public final void run() {
        File file = null;
        if (this.f.a != null) {
            file = new File(this.f.a, this.a.toString());
        }
        if (file != null && file.exists()) {
            this.a(file);
            return;
        }
        this.b(file);
    }
}
