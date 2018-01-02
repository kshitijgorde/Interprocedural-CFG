// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.f;

import java.util.Vector;

public class f
{
    private Vector a;
    private static String z;
    
    public f() {
        this.a = new Vector();
    }
    
    public void a(final Object o) {
        synchronized (this.a) {
            this.a.addElement(o);
            this.a.notify();
        }
        // monitorexit(this.a)
    }
    
    public Object a() {
        Object firstElement = null;
        synchronized (this.a) {
            if (this.a.size() == 0) {
                try {
                    this.a.wait();
                }
                catch (InterruptedException ex) {
                    // monitorexit(this.a)
                    return null;
                }
            }
            try {
                firstElement = this.a.firstElement();
                this.a.removeElementAt(0);
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                throw new InternalError(f.z);
            }
        }
        // monitorexit(this.a)
        return firstElement;
    }
    
    static {
        f.z = z(z("6ZN\u000e2\fZW\n`\u0000\u001bD\u000525NH\u001ewDTO\u0001w\u0007O\u0003"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0012';
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
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = ';';
                    break;
                }
                case 2: {
                    c2 = '-';
                    break;
                }
                case 3: {
                    c2 = 'k';
                    break;
                }
                default: {
                    c2 = '\u0012';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
