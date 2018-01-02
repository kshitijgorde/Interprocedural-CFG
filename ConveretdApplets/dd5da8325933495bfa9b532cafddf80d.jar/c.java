// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private final int a;
    private final int b;
    private final int c;
    private String[] d;
    private String[] e;
    private d f;
    
    public c(final d f) {
        this.a = 256;
        this.b = 101;
        this.c = 139;
        this.d = new String[256];
        this.e = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        this.f = f;
        int n = 0;
        while (true) {
            Label_0189: {
                if (!p.dJ) {
                    break Label_0189;
                }
                this.d[n] = this.e[n / 16] + this.e[n % 16];
                ++n;
            }
            if (n >= 256) {
                return;
            }
            continue;
        }
    }
    
    public String a(final String s, final String s2) {
        final boolean dj = p.dJ;
        final int[] array = new int[256];
        final int[] array2 = new int[256];
        int n = 0;
        int n2 = 0;
        final int length = s2.length();
        int n3 = 0;
        int i = 0;
        while (true) {
            while (true) {
                Label_0073: {
                    if (!dj) {
                        break Label_0073;
                    }
                    final int[] array3 = array;
                    final int n4 = i;
                    array3[n4] = n4;
                    array2[i] = s2.charAt(n3);
                    ++n3;
                    if (n3 == length) {
                        n3 = 0;
                    }
                    ++i;
                }
                if (i < 256) {
                    continue;
                }
                break;
            }
            i = 0;
            n3 = 0;
            if (dj) {
                continue;
            }
            break;
        }
    Label_0136_Outer:
        while (true) {
            while (true) {
                Label_0139: {
                    if (!dj) {
                        break Label_0139;
                    }
                    n3 = (n3 + array[i] + array2[i]) % 256;
                    final int n5 = array[i];
                    array[i] = array[n3];
                    array[n3] = n5;
                    ++i;
                }
                if (i >= 256) {
                    final int length2;
                    final char[] array4 = new char[length2 = s.length()];
                    i = 0;
                    if (dj) {
                        if (dj) {
                            continue;
                        }
                    }
                    while (i < length2) {
                        n = (n + 1) % 256;
                        n2 = (n2 + array[n]) % 256;
                        final int n6 = array[n];
                        array[n] = array[n2];
                        array[n2] = n6;
                        array4[i] = (char)(s.charAt(i) ^ array[(array[n] + array[n2]) % 256]);
                        ++i;
                    }
                    return new String(array4);
                }
                break;
            }
            continue Label_0136_Outer;
        }
    }
    
    public String a(final String s, final boolean b) {
        final boolean dj = p.dJ;
        String s2 = "";
        int n = 0;
        while (true) {
            while (true) {
                Label_0121: {
                    if (!dj) {
                        break Label_0121;
                    }
                    if (b) {
                        final int n2 = n % 3;
                        int n3 = 0;
                        while (true) {
                            Label_0067: {
                                if (!dj) {
                                    break Label_0067;
                                }
                                s2 += this.d[i.a(0, 255)];
                                ++n3;
                            }
                            if (n3 < n2) {
                                continue;
                            }
                            break;
                        }
                    }
                    final String s3;
                    s2 += this.d[(s3.charAt(n) + 'e') % '\u0100' ^ '\u008b'];
                    ++n;
                }
                if (n < s.length()) {
                    continue;
                }
                break;
            }
            final String s3 = s2;
            if (!dj) {
                return s3;
            }
            continue;
        }
    }
    
    public String a(final String s) {
        return this.a(s, true);
    }
    
    public String b(final String s, final boolean b) {
        final boolean dj = p.dJ;
        String string = "";
        final char[] array = { '\0' };
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0114: {
                    if (!dj) {
                        break Label_0114;
                    }
                    if (b) {
                        n += n2 % 3 << 1;
                    }
                    final int n3 = n;
                    s.substring(n3, n3 + 2);
                    final String s2;
                    array[0] = (char)(((Integer.parseInt(s2, 16) ^ 0x8B) + 256 - 101) % 256);
                    string += new String(array);
                    n += 2;
                    ++n2;
                }
                if (n < s.length()) {
                    continue;
                }
                break;
            }
            final String s2 = string;
            if (!dj) {
                return s2;
            }
            continue;
        }
    }
    
    public String b(final String s) {
        return this.b(s, true);
    }
}
