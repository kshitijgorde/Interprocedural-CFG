// 
// Decompiled by Procyon v0.5.30
// 

public interface x
{
    public static final t a = new t(array2[2]);
    public static final t b = new t(x.a + array2[0]);
    public static final t c = new t(x.a + array2[3]);
    public static final t d = new t(x.a + array2[4]);
    public static final t e = new t(x.a + array2[1]);
    
    default static {
        final String[] array = new String[5];
        final int n = 0;
        final char[] charArray = "WZ".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c2 = charArray[n3];
            char c3 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c3 = 'y';
                    break;
                }
                case 1: {
                    c3 = 'k';
                    break;
                }
                case 2: {
                    c3 = '[';
                    break;
                }
                case 3: {
                    c3 = 'n';
                    break;
                }
                default: {
                    c3 = '\u0003';
                    break;
                }
            }
            charArray[n3] = (char)(c2 ^ c3);
        }
        array[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "W_".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c4 = charArray2[n6];
            char c5 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c5 = 'y';
                    break;
                }
                case 1: {
                    c5 = 'k';
                    break;
                }
                case 2: {
                    c5 = '[';
                    break;
                }
                case 3: {
                    c5 = 'n';
                    break;
                }
                default: {
                    c5 = '\u0003';
                    break;
                }
            }
            charArray2[n6] = (char)(c4 ^ c5);
        }
        array[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "HEh@5WZu[-LEc@2".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c6 = charArray3[n9];
            char c7 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c7 = 'y';
                    break;
                }
                case 1: {
                    c7 = 'k';
                    break;
                }
                case 2: {
                    c7 = '[';
                    break;
                }
                case 3: {
                    c7 = 'n';
                    break;
                }
                default: {
                    c7 = '\u0003';
                    break;
                }
            }
            charArray3[n9] = (char)(c6 ^ c7);
        }
        array[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "WY".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c8 = charArray4[n12];
            char c9 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c9 = 'y';
                    break;
                }
                case 1: {
                    c9 = 'k';
                    break;
                }
                case 2: {
                    c9 = '[';
                    break;
                }
                case 3: {
                    c9 = 'n';
                    break;
                }
                default: {
                    c9 = '\u0003';
                    break;
                }
            }
            charArray4[n12] = (char)(c8 ^ c9);
        }
        array[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "WX".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c10 = charArray5[n15];
            char c11 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c11 = 'y';
                    break;
                }
                case 1: {
                    c11 = 'k';
                    break;
                }
                case 2: {
                    c11 = '[';
                    break;
                }
                case 3: {
                    c11 = 'n';
                    break;
                }
                default: {
                    c11 = '\u0003';
                    break;
                }
            }
            charArray5[n15] = (char)(c10 ^ c11);
        }
        array[n13] = new String(charArray5).intern();
        final String[] array2 = array;
    }
}
