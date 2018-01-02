// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import java.io.InterruptedIOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.Socket;
import flaxchat.e.r;

public class i extends r
{
    private e b;
    private Socket c;
    private BufferedReader d;
    private BufferedWriter e;
    private boolean f;
    private boolean g;
    private static String[] z;
    
    i(final e b, final Socket c, final BufferedReader d, final BufferedWriter e) {
        this.f = false;
        this.g = false;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.setName(String.valueOf(this.getClass()) + i.z[0]);
    }
    
    void a(final String s) {
        h.a(this.b, this.e, s);
    }
    
    public synchronized void start() {
        this.f = true;
        super.start();
    }
    
    boolean a() {
        return this.f;
    }
    
    public void run() {
        final boolean y = flaxchat.h.e.y;
        try {
            while (true) {
                Label_0078: {
                    if (!y) {
                        break Label_0078;
                    }
                    try {
                        String line;
                        while ((line = this.d.readLine()) != null) {
                            try {
                                this.b(line);
                            }
                            catch (Throwable t) {}
                        }
                        super.a = false;
                    }
                    catch (InterruptedIOException ex) {
                        this.a(i.z[2] + System.currentTimeMillis() / 1000L);
                    }
                }
                if (super.a) {
                    if (!Thread.currentThread().isInterrupted()) {
                        continue;
                    }
                }
                break;
            }
        }
        catch (Exception ex2) {}
        try {
            this.c.close();
        }
        catch (Exception ex3) {}
        if (!this.g) {
            this.b.g(i.z[1]);
            this.f = false;
            this.b.e();
        }
    }
    
    private void b(final String s) {
        this.b.j(s);
    }
    
    public void b() {
        try {
            this.g = true;
            super.a = false;
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
            this.c.close();
        }
        catch (Exception ex2) {}
    }
    
    static {
        i.z = new String[] { z(z("%\u000f\u0017R\u007fi?")), z(z("\"qU\u0000^a(\u001cOtf>\u001cT\u007flu")), z(z("X\u00121g:")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u001a';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\b';
                    break;
                }
                case 1: {
                    c2 = '[';
                    break;
                }
                case 2: {
                    c2 = '\u007f';
                    break;
                }
                case 3: {
                    c2 = ' ';
                    break;
                }
                default: {
                    c2 = '\u001a';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
