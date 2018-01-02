// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d;

import java.io.PrintStream;

public class f extends Exception
{
    Throwable a;
    private static String z;
    
    public f() {
    }
    
    public f(final String s) {
        super(s);
    }
    
    public f(final String s, final Throwable a) {
        this(s);
        this.a = a;
    }
    
    public f(final Throwable a) {
        this();
        this.a = a;
    }
    
    public void printStackTrace() {
        super.printStackTrace(System.err);
        if (this.a != null) {
            System.err.print(f.z);
            this.a.printStackTrace(System.err);
        }
    }
    
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.a != null) {
            printStream.print(f.z);
            this.a.printStackTrace(printStream);
        }
    }
    
    static {
        final char[] charArray = "Y;k\u000e\u001b~z|\u0004D:".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u001a';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = '\u001e';
                    break;
                }
                case 3: {
                    c2 = '}';
                    break;
                }
                default: {
                    c2 = '~';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        f.z = new String(charArray).intern();
    }
}
