// 
// Decompiled by Procyon v0.5.30
// 

package IO;

import java.net.URLConnection;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Component;

public class romLoader implements Runnable
{
    private int a;
    private String b;
    private byte[] c;
    private int d;
    private Component e;
    private boolean f;
    
    public romLoader(final Component e) {
        this.a = 0;
        this.e = e;
    }
    
    public void run() {
        if (this.f) {
            System.out.println("romLoader : Downloading File...");
        }
        int n;
        if (this.b.toUpperCase().indexOf("ZIP") >= 0) {
            n = 1;
        }
        else if (this.b.toUpperCase().indexOf("JAR") >= 0) {
            n = 1;
        }
        else if (this.b.toUpperCase().indexOf("GZ") >= 0) {
            n = 3;
        }
        else if (this.b.toUpperCase().indexOf("RAR") >= 0) {
            n = 4;
        }
        else {
            n = 0;
        }
        switch (n) {
            case 0: {
                this.b();
                break;
            }
            case 1: {
                this.a();
                break;
            }
            default: {
                System.out.println("romLoader : Compression type not supported");
                System.exit(0);
                break;
            }
        }
        if (this.f) {
            System.out.println("romLoader : Download Finished");
        }
        this.c = this.c;
    }
    
    public void romLoaderFile(final String b) {
        this.d = 0;
        this.c = null;
        this.f = false;
        this.b = b;
    }
    
    public byte[] getRom() {
        return this.c;
    }
    
    public int getStatus() {
        return this.d;
    }
    
    public void setDebug(final int n) {
        if (n == 0) {
            this.f = false;
            return;
        }
        this.f = true;
    }
    
    private void a() {
        try {
            final ZipInputStream zipInputStream;
            this.a = (int)(zipInputStream = new ZipInputStream(new BufferedInputStream(new URL(this.b).openStream()))).getNextEntry().getSize();
            this.a(zipInputStream);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void b() {
        URLConnection openConnection = null;
        InputStream inputStream = null;
        try {
            try {
                openConnection = new URL(this.b).openConnection();
            }
            catch (Exception ex) {
                System.out.println("romLoader Error - Bad URL : " + ex.getMessage());
                System.exit(0);
            }
            this.a = openConnection.getContentLength();
            openConnection.getInputStream().close();
            try {
                inputStream = new DataInputStream(new URL(this.b).openStream());
            }
            catch (Exception ex3) {
                System.out.println("Can't open file");
                System.exit(0);
            }
        }
        catch (Exception ex2) {
            System.out.println("romLoader Error - Problem opening URL : " + ex2.getMessage());
            System.exit(0);
        }
        this.a(inputStream);
    }
    
    private void a(final InputStream inputStream) {
        int n = 0;
        int i = 0;
        try {
            this.c = new byte[this.a];
            while (i != -1) {
                if ((i = inputStream.read(this.c, n, this.c.length - n)) != -1) {
                    if (n >= this.c.length) {
                        final byte[] c = new byte[this.c.length + 32768];
                        for (int j = 0; j < this.c.length; ++j) {
                            c[j] = this.c[j];
                        }
                        this.c = c;
                    }
                    if ((n += i) * 100 / this.a <= this.d) {
                        continue;
                    }
                    this.d = n * 100 / this.a;
                    this.e.repaint();
                }
            }
            final byte[] c2 = new byte[n];
            for (int k = 0; k < n; ++k) {
                c2[k] = this.c[k];
            }
            this.c = c2;
            inputStream.close();
        }
        catch (Exception ex) {
            System.out.println("romLoader Error - File cannot be read or is corrupt " + ex.getMessage());
            System.exit(0);
        }
    }
}
