// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.a;

public class c
{
    public static boolean a;
    private static String[] z;
    
    public static void a(final String s) {
        a(null, s);
    }
    
    public static void a(final Object o, final String s) {
        final int l = e.l;
        if (!c.a) {
            return;
        }
        Object o2 = o;
        Label_0081: {
            if (l == 0) {
                if (o == null) {
                    break Label_0081;
                }
                o2 = o;
            }
            final String name = o2.getClass().getName();
            System.out.println(name.substring(name.lastIndexOf(".") + 1) + c.z[2] + s);
            if (l == 0) {
                return;
            }
        }
        System.out.println(s);
    }
    
    public static void b(final String s) {
        b(null, s);
    }
    
    public static void b(final Object o, final String s) {
        final int l = e.l;
        if (!c.a) {
            return;
        }
        Object o2 = o;
        Label_0079: {
            if (l == 0) {
                if (o == null) {
                    break Label_0079;
                }
                o2 = o;
            }
            final String name = o2.getClass().getName();
            System.err.println(name.substring(name.lastIndexOf(".") + 1) + c.z[2] + s);
            if (l == 0) {
                return;
            }
        }
        System.err.println(s);
    }
    
    public static void c(final String s) {
        c(null, s);
    }
    
    public static void c(final Object o, final String s) {
        final int l = e.l;
        Object o2 = o;
        Label_0072: {
            if (l == 0) {
                if (o == null) {
                    break Label_0072;
                }
                o2 = o;
            }
            final String name = o2.getClass().getName();
            System.err.println(name.substring(name.lastIndexOf(".") + 1) + c.z[2] + s);
            if (l == 0) {
                return;
            }
        }
        System.err.println(s);
    }
    
    public static void a(final Object o) {
        final int l = e.l;
        Object o2 = o;
        Label_0065: {
            if (l == 0) {
                if (o == null) {
                    break Label_0065;
                }
                o2 = o;
            }
            final String name = o2.getClass().getName();
            System.err.println(name.substring(name.lastIndexOf(".") + 1) + c.z[1]);
            if (l == 0) {
                return;
            }
        }
        System.err.println(c.z[0]);
    }
    
    public static String b(final Object o) {
        final String name = o.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "w?\u000ek{\u001b8\f9$\u0013yIqaTp\u00008aU%\u0005'o".toCharArray();
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
                            c2 = ';';
                            break;
                        }
                        case 1: {
                            c2 = 'P';
                            break;
                        }
                        case 2: {
                            c2 = 'i';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = 'A';
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
        final char[] charArray2 = "\u001b}Dfl\u001b\u0018,\u0019\u0004\u001b}Dfl\u001b".toCharArray();
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
                            c4 = ';';
                            break;
                        }
                        case 1: {
                            c4 = 'P';
                            break;
                        }
                        case 2: {
                            c4 = 'i';
                            break;
                        }
                        case 3: {
                            c4 = 'K';
                            break;
                        }
                        default: {
                            c4 = 'A';
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
        final char[] charArray3 = "\u001bjI".toCharArray();
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
                            c6 = ';';
                            break;
                        }
                        case 1: {
                            c6 = 'P';
                            break;
                        }
                        case 2: {
                            c6 = 'i';
                            break;
                        }
                        case 3: {
                            c6 = 'K';
                            break;
                        }
                        default: {
                            c6 = 'A';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                c.z = z;
                c.a = false;
                return;
            }
            continue;
        }
    }
}
