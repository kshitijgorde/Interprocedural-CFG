// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

final class Q
{
    static final char p = '\0';
    static final char m = '\u0001';
    static final char W = '\u0002';
    static final char q = '\u0003';
    static final char b = '\u0004';
    static final char D = '\u0005';
    static final char h = '\u0006';
    static final char G = '\u0007';
    static final char H = '\b';
    static final char c = '\t';
    static final char V = '\n';
    static final char e = '\u000b';
    static final char u = '\f';
    static final char v = '\r';
    static final char M = '\u000e';
    static final char R = '\u000f';
    static final char P = '\u0010';
    static final char a = '\u0011';
    static final char s = '\u0012';
    static final char S = '\u0013';
    static final char X = '\u0014';
    static final char r = '\u0015';
    static final char C = '\u0016';
    static final char i = '\u0017';
    static final char f = '\u0018';
    static final char x = '\u0019';
    static final char L = '\u001a';
    static final char o = '\u001b';
    static final char z = '\u001c';
    static final char k = '\u001d';
    static final char w = '\u001e';
    static final char B = '\u001f';
    static final char y = ' ';
    static final char d = '!';
    static final char Q = '\"';
    static final char O = '#';
    static final char Z = '$';
    static final char U = '%';
    static final char ª = '&';
    static final char Y = '\'';
    static final char _ = '(';
    static final char t = ')';
    static final char I = '*';
    static final char A = '+';
    static final char K = ',';
    static final char ¤ = '-';
    static final char F = '.';
    static final char j = '/';
    static final char ¢ = '0';
    static final char E = '1';
    static final char T = '2';
    static final char £ = '3';
    static final int[] N;
    static final char[] l;
    static final char[] ¥;
    static final char[] g;
    static final int n = -1;
    static final char J = '\0';
    
    static final int C(final char[] array, final int n) {
        return array[n + 1];
    }
    
    static final char D(final char[] array, final int n) {
        return array[n + 2];
    }
    
    static final char A(final char[] array, final int n) {
        return array[n + 3];
    }
    
    static final int B(final int n) {
        return n + 2;
    }
    
    static final boolean A(final char c, final char[] array, int i) {
        while (i < array.length) {
            if (c == array[i++]) {
                return true;
            }
        }
        return false;
    }
    
    static final int A(final int n) {
        return n + 2;
    }
    
    static final int C(final int n) {
        return n - 2;
    }
    
    static final int B(final char[] array, final int n) {
        if (array == null) {
            return -1;
        }
        final int c = C(array, n);
        if (c == 0) {
            return -1;
        }
        if (array[n] == '\r') {
            return n - c;
        }
        return n + c;
    }
    
    static final boolean A(final char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }
    
    static {
        N = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        l = new char[] { '\0', '\u0001', '\u0001', '\u0001', '\u0004', '\u0004', '\u0004', '\u0007', '\u0007', '\t', '\n', '\n', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u0001', '\f', '\f', '\0', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3' };
        ¥ = new char[] { '\f', '\r', '\u0010', '\u0011', '\n', '\u000b', '\u001a', '\"' };
        g = new char[] { '\u0007', '\b', '\t', '\u0012', '\u0013', '\u0016', '\u0017', '\u0018', '\u0019', '#', '$', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3' };
    }
}
