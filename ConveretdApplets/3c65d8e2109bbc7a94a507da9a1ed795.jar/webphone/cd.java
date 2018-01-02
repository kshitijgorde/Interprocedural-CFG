// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.OutputStream;
import java.io.FilterInputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class cd extends Thread
{
    aw do;
    String a;
    String if;
    int for;
    
    public cd(final String a, final String if1, final aw do1, final int for1) {
        this.do = null;
        this.a = "";
        this.if = "";
        this.for = 3;
        this.do = do1;
        this.a = a;
        this.if = if1;
        this.for = for1;
    }
    
    public void run() {
        try {
            if (this.a.length() < 1) {
                return;
            }
            this.setPriority(1);
            FilterInputStream filterInputStream = null;
            OutputStream outputStream = null;
            final InputStream inputStream = null;
            try {
                String s = this.do.dx;
                final int index = s.indexOf("FILENAME");
                if (index > 0) {
                    s = s.substring(0, index) + this.if + s.substring(index + 8);
                }
                this.do.a(4, "EVENT, ftp upload to: " + s + " file: " + this.a);
                filterInputStream = new BufferedInputStream(new FileInputStream(this.a));
                outputStream = new URL(s).openConnection().getOutputStream();
                int n = 0;
                final byte[] array = new byte[2048];
                int read;
                while ((read = filterInputStream.read(array)) != -1) {
                    outputStream.write(array, 0, read);
                    n += read;
                }
                this.do.a(4, "EVENT, ftp upload finished successfully " + this.do.c(n) + " bytes");
            }
            catch (Exception ex) {
                this.do.a(2, "ftp uplopad error1", ex);
            }
            try {
                if (filterInputStream != null) {
                    ((BufferedInputStream)filterInputStream).close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (Exception ex2) {
                this.do.a(4, "ftp uplopad error3", ex2);
            }
        }
        catch (Exception ex3) {
            this.do.a(2, "ftp uplopad error4", ex3);
        }
        try {
            if (this.for == 2) {
                this.do.b(this.a);
            }
        }
        catch (Exception ex4) {
            this.do.a(2, "ftp uplopad error5", ex4);
        }
    }
}
