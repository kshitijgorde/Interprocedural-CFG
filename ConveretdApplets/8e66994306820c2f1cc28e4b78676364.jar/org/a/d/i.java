// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d;

import java.util.Vector;

public class i
{
    protected Vector a;
    private static String z;
    
    public i() {
        this.a = new Vector();
    }
    
    public int a() {
        return this.a.size();
    }
    
    public synchronized void a(final Object o) {
        this.a.addElement(o);
    }
    
    public synchronized Object b() throws g {
        if (this.a() <= 0) {
            throw new g(i.z);
        }
        final Object element = this.a.elementAt(0);
        this.a.removeElementAt(0);
        return element;
    }
    
    static {
        final char[] charArray = "*[mq#OGh`/\n".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = '6';
                    break;
                }
                case 2: {
                    c2 = '\u001d';
                    break;
                }
                case 3: {
                    c2 = '\u0005';
                    break;
                }
                default: {
                    c2 = 'Z';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        org.a.d.i.z = new String(charArray).intern();
    }
}
