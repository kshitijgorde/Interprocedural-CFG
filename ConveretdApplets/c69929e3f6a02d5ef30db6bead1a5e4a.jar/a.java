// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    public static int a;
    public static int b;
    public static int c;
    
    public static void a(int a) {
        final int c = a.c;
        if (a < 0) {
            a += 262144;
        }
        final short[] array = { 0, 1606, 3196, 4756, 6270, 7723, 9102, 10394, 11585, 12665, 13623, 14449, 15137, 15679, 16069, 16305, 16384, 0 };
        if (a >= 262144) {
            a -= 262144;
        }
        a.a = a;
        a.b = a.a;
        int n = 1;
        int n2 = 1;
        Label_0258: {
            if (a > 196608) {
                a.a = 262144 - a;
                n2 = -1;
                a.b -= 196608;
                if (c == 0) {
                    break Label_0258;
                }
            }
            if (a > 131072) {
                a.a = a - 131072;
                n2 = -1;
                a.b = 196608 - a.b;
                n = -1;
                if (c == 0) {
                    break Label_0258;
                }
            }
            if (a > 65536) {
                a.a = 131072 - a;
                a.b -= 65536;
                n = -1;
                if (c == 0) {
                    break Label_0258;
                }
            }
            a.b = 65536 - a.b;
        }
        final int n3 = a.a / 4096;
        final int n4 = a.a - n3 * 4096;
        a.a = array[n3] * (4096 - n4) + array[n3 + 1] * n4;
        a.a /= 256;
        a.a *= n2;
        final int n5 = a.b / 4096;
        final int n6 = a.b - n5 * 4096;
        a.b = array[n5] * (4096 - n6) + array[n5 + 1] * n6;
        a.b /= 256;
        a.b *= n;
    }
}
