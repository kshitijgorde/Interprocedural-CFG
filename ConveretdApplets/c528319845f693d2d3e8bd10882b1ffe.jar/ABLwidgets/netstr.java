// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.net.URL;

public class netstr
{
    public StringBuffer a;
    private URL b;
    private String c;
    private boolean d;
    
    public netstr(final URL b) {
        this.d = false;
        this.b = b;
        this.a = new StringBuffer(4096);
    }
    
    public String a(final String s, final String s2) {
        String s3 = "";
        final byte[] array = new byte[8192];
        int n = 0;
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.b.openStream());
            int i;
            do {
                i = bufferedInputStream.read(array, 0, array.length);
                if (i != -1) {
                    n += i;
                    String s4;
                    if (s2 == null) {
                        s4 = new String(array, 0, i);
                    }
                    else {
                        s4 = new String(array, 0, i, s2);
                    }
                    this.a.append(s4);
                }
            } while (i != -1);
            bufferedInputStream.close();
        }
        catch (FileNotFoundException ex3) {
            if (s == null) {
                abljem.b("netstr: file not found=" + this.c);
                return null;
            }
            return s;
        }
        catch (UnsupportedEncodingException ex4) {
            s3 = "Unsupported encoding=" + s2;
        }
        catch (IOException ex) {
            s3 = ex.toString();
        }
        catch (Exception ex2) {
            s3 = ex2.toString();
        }
        if (!s3.equals("")) {
            abljem.b("netstr: URL=" + this.c + " err=" + s3);
        }
        return this.a.toString();
    }
    
    public byte[] a() {
        String s = "";
        final byte[] array = new byte[8192];
        byte[] array2 = new byte[0];
        int n = 0;
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.b.openStream());
            int i;
            do {
                i = bufferedInputStream.read(array, 0, array.length);
                if (i != -1) {
                    final int n2 = n;
                    n += i;
                    final byte[] array3 = new byte[n];
                    System.arraycopy(array2, 0, array3, 0, n2);
                    System.arraycopy(array, 0, array3, n2, i);
                    array2 = array3;
                }
            } while (i != -1);
            bufferedInputStream.close();
        }
        catch (FileNotFoundException ex3) {
            abljem.b("netstr: file not found=" + this.c);
            return null;
        }
        catch (IOException ex) {
            s = ex.toString();
        }
        catch (Exception ex2) {
            s = ex2.toString();
        }
        if (!s.equals("")) {
            abljem.b("netstr: URL=" + this.c + " err=" + s);
        }
        return array2;
    }
}
