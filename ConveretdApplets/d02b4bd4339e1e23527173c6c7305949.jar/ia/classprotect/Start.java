// 
// Decompiled by Procyon v0.5.30
// 

package ia.classprotect;

public class Start
{
    static /* synthetic */ Class a;
    private static final String[] z;
    
    public static void main(final String[] array) throws Exception {
        final b b = new b();
        ClassProtect classProtect = null;
        Class a = null;
        Label_0048: {
            Label_0029: {
                try {
                    if (!b.a()) {
                        return;
                    }
                    classProtect = new(ia.classprotect.ClassProtect.class);
                    final Class clazz = Start.a;
                    if (clazz == null) {
                        break Label_0029;
                    }
                    break Label_0029;
                }
                catch (Throwable t) {
                    throw t;
                }
                try {
                    classProtect = new(ia.classprotect.ClassProtect.class);
                    final Class clazz = Start.a;
                    if (clazz == null) {
                        a = (Start.a = a(Start.z[1]));
                        break Label_0048;
                    }
                }
                catch (Throwable t2) {
                    throw t2;
                }
            }
            a = Start.a;
        }
        new ClassProtect(a.getClassLoader());
        final ClassProtect classProtect2 = classProtect;
        final String s = Start.z[0];
        Class<?> forName = null;
        try {
            forName = Class.forName(s, true, classProtect2);
        }
        catch (Throwable t3) {
            System.exit(1);
        }
        forName.getDeclaredMethod(Start.z[2], array.getClass()).invoke(null, array);
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z2 = new String[3];
        final int n = 0;
        final char[] charArray = "$kST".toCharArray();
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
                            c2 = 'M';
                            break;
                        }
                        case 1: {
                            c2 = '\n';
                            break;
                        }
                        case 2: {
                            c2 = '}';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = '?';
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
        z2[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "$kS_S,y\u000eLM\"~\u0018_KcY\t]M9".toCharArray();
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
                            c4 = 'M';
                            break;
                        }
                        case 1: {
                            c4 = '\n';
                            break;
                        }
                        case 2: {
                            c4 = '}';
                            break;
                        }
                        case 3: {
                            c4 = '<';
                            break;
                        }
                        default: {
                            c4 = '?';
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
        z2[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = " k\u0014R".toCharArray();
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
                            c6 = 'M';
                            break;
                        }
                        case 1: {
                            c6 = '\n';
                            break;
                        }
                        case 2: {
                            c6 = '}';
                            break;
                        }
                        case 3: {
                            c6 = '<';
                            break;
                        }
                        default: {
                            c6 = '?';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z2[n9] = new String(charArray3).intern();
                z = z2;
                return;
            }
            continue;
        }
    }
}
