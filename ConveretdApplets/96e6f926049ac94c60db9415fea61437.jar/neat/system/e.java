// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class e
{
    protected Class a;
    protected int b;
    private int c;
    public static boolean d;
    private static String[] z;
    
    public synchronized Object a() {
        ++this.c;
        try {
            return this.a.newInstance();
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(e.z[0] + ex.getMessage());
        }
        catch (InstantiationException ex2) {
            throw new RuntimeException(e.z[2] + ex2.getMessage());
        }
        catch (NoSuchMethodError noSuchMethodError) {
            throw new RuntimeException(e.z[1] + noSuchMethodError.getMessage());
        }
    }
    
    public char[] a(final int n) {
        return new char[n];
    }
    
    public int[] b(final int n) {
        return new int[n];
    }
    
    public Object[] c(final int n) {
        return new Object[n];
    }
    
    public Object[][] d(final int n) {
        return new Object[n][];
    }
    
    public Class[] e(final int n) {
        return new Class[n];
    }
    
    public void a(final char[] array) {
    }
    
    public void a(final int[] array) {
    }
    
    public void a(final Object[] array) {
    }
    
    public e(final Class a) {
        this.a = a;
        this.c = 0;
        this.b = 0;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "^:9nkv:\u0014hor%\u0010sor&!bcyl".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = 'V';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = '\u000b';
                            break;
                        }
                        default: {
                            c2 = '\f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Y9\u0006~o\u007f\u001b0\u007fdx2\u0010y~x$o".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0017';
                            break;
                        }
                        case 1: {
                            c4 = 'V';
                            break;
                        }
                        case 2: {
                            c4 = 'U';
                            break;
                        }
                        case 3: {
                            c4 = '\u000b';
                            break;
                        }
                        default: {
                            c4 = '\f';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "^8&\u007fmy\"<jx~9;Ntt3%\u007fex8o".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0017';
                            break;
                        }
                        case 1: {
                            c6 = 'V';
                            break;
                        }
                        case 2: {
                            c6 = 'U';
                            break;
                        }
                        case 3: {
                            c6 = '\u000b';
                            break;
                        }
                        default: {
                            c6 = '\f';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                e.z = z;
                return;
            }
            continue;
        }
    }
}
