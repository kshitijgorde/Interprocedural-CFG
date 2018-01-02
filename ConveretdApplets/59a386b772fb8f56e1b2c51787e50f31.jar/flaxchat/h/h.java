// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import java.io.BufferedWriter;
import flaxchat.f.f;
import flaxchat.e.r;

public class h extends r
{
    private e b;
    private f c;
    private static String[] z;
    
    h(final e b, final f c) {
        this.b = b;
        this.c = c;
        this.setName(String.valueOf(this.getClass()) + h.z[2]);
    }
    
    static void a(final e e, final BufferedWriter bufferedWriter, String a) {
        a = a(a, 512);
        synchronized (bufferedWriter) {
            try {
                bufferedWriter.write(String.valueOf(a) + h.z[0]);
                bufferedWriter.flush();
                e.h(h.z[1] + a);
            }
            catch (Exception ex) {}
        }
    }
    
    public static String a(String substring, final int n) {
        if (substring.length() > n) {
            substring = substring.substring(0, n);
        }
        return substring;
    }
    
    public void run() {
        final boolean y = e.y;
        try {
            while (true) {
                Label_0050: {
                    if (!y) {
                        break Label_0050;
                    }
                    Thread.sleep(this.b.j());
                    final String s = (String)this.c.a();
                    if (s != null) {
                        this.b.e(s);
                        if (!y) {
                            break Label_0050;
                        }
                    }
                    super.a = false;
                }
                if (super.a) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    continue;
                }
                break;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    static {
        h.z = new String[] { z(z("\u0010T")), z(z("#`\t")), z(z("0\n_G\u0004|:")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'a';
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
                    c2 = '\u001d';
                    break;
                }
                case 1: {
                    c2 = '^';
                    break;
                }
                case 2: {
                    c2 = '7';
                    break;
                }
                case 3: {
                    c2 = '5';
                    break;
                }
                default: {
                    c2 = 'a';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
