// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public abstract class g implements cb, z
{
    private int a;
    public static boolean b;
    private static String[] z;
    
    synchronized void a() {
        ++this.a;
    }
    
    synchronized void b() {
        final int a = this.a - 1;
        this.a = a;
        if (a == 0) {
            this.f();
        }
        if (this.a < 0) {
            throw new RuntimeException(g.z[2] + this + g.z[0]);
        }
    }
    
    public void f() {
        throw new RuntimeException(g.z[3] + this + g.z[0]);
    }
    
    public void g() {
        this.a = 0;
    }
    
    public void h() {
        if (this.a != 0) {
            throw new RuntimeException(g.z[1] + this + g.z[0]);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "KF".toCharArray();
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
                            c2 = 'k';
                            break;
                        }
                        case 1: {
                            c2 = 'g';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = '>';
                            break;
                        }
                        default: {
                            c2 = '^';
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
        final char[] charArray2 = "?\u000f\u0018M~\u000e\u0011\u0014P*K\u0001\u001eL~\u0019\u0002\u0012G=\u0007\u000e\u001fY~\u0002\u0014QR1\b\f\u0014Z~\t\u001eQ_0K\u0006\u0015_.\u001f\u0002\u0003\u0004".toCharArray();
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
                            c4 = 'k';
                            break;
                        }
                        case 1: {
                            c4 = 'g';
                            break;
                        }
                        case 2: {
                            c4 = 'q';
                            break;
                        }
                        case 3: {
                            c4 = '>';
                            break;
                        }
                        default: {
                            c4 = '^';
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
        final char[] charArray3 = "?\u000f\u0018M~\u000e\u0011\u0014P*K\u000e\u0002\u001e?\u0007\u0015\u0014_:\u0012G\u0004P2\u0004\u0004\u001a[:Q".toCharArray();
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
                            c6 = 'k';
                            break;
                        }
                        case 1: {
                            c6 = 'g';
                            break;
                        }
                        case 2: {
                            c6 = 'q';
                            break;
                        }
                        case 3: {
                            c6 = '>';
                            break;
                        }
                        default: {
                            c6 = '^';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "?\u000f\u0018M~\u0002\u0014Q_0K\u0006\u0013M*\u0019\u0006\u0012J~\b\u000b\u0010M-GG\u0012_0K\t\u001eJ~\u000f\u0002\u001d[*\u000e]".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'k';
                            break;
                        }
                        case 1: {
                            c8 = 'g';
                            break;
                        }
                        case 2: {
                            c8 = 'q';
                            break;
                        }
                        case 3: {
                            c8 = '>';
                            break;
                        }
                        default: {
                            c8 = '^';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                g.z = z;
                return;
            }
            continue;
        }
    }
}
