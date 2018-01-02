// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B;

import z.C.A.A.B.A.H;
import z.C.A.A.B.A.G;
import z.C.A.A.B.A.L;
import z.C.A.A.B.A.R;

public final class I implements R
{
    public static final int V = 0;
    public static final int U = 1;
    public static final int S = 2;
    public static final int R = 4;
    public static final int Q = 8;
    private L T;
    
    private static boolean H(final char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '^' || c == '$' || c == '.' || c == '{' || c == '}' || c == '\\';
    }
    
    private static boolean G(final char c) {
        return c == '*' || c == '?' || c == '[' || c == ']';
    }
    
    public static String B(final char[] array, final int n) {
        final StringBuffer sb = new StringBuffer(2 * array.length);
        int n2 = 0;
        final boolean b = (n & 0x4) != 0x0;
        final boolean b2 = (n & 0x2) != 0x0;
        for (int i = 0; i < array.length; ++i) {
            switch (array[i]) {
                case '*': {
                    if (n2 != 0) {
                        sb.append('*');
                        break;
                    }
                    if (b2) {
                        sb.append(".+");
                        break;
                    }
                    sb.append(".*");
                    break;
                }
                case '?': {
                    if (n2 != 0) {
                        sb.append('?');
                        break;
                    }
                    if (b) {
                        sb.append(".?");
                        break;
                    }
                    sb.append('.');
                    break;
                }
                case '[': {
                    n2 = 1;
                    sb.append(array[i]);
                    if (i + 1 >= array.length) {
                        break;
                    }
                    switch (array[i + 1]) {
                        case '!':
                        case '^': {
                            sb.append('^');
                            ++i;
                            continue;
                        }
                        case ']': {
                            sb.append(']');
                            ++i;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
                case ']': {
                    n2 = 0;
                    sb.append(array[i]);
                    break;
                }
                case '\\': {
                    sb.append('\\');
                    if (i == array.length - 1) {
                        sb.append('\\');
                        break;
                    }
                    if (G(array[i + 1])) {
                        sb.append(array[++i]);
                        break;
                    }
                    sb.append('\\');
                    break;
                }
                default: {
                    if (n2 == 0 && H(array[i])) {
                        sb.append('\\');
                    }
                    sb.append(array[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public I() {
        this.T = new L();
    }
    
    public G A(final char[] array, final int n) throws H {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((n & 0x8) != 0x0) {
            n2 |= 0x8000;
        }
        return this.T.A(B(array, n), n2);
    }
    
    public G A(final char[] array) throws H {
        return this.A(array, 0);
    }
    
    public G A(final String s) throws H {
        return this.A(s.toCharArray(), 0);
    }
    
    public G A(final String s, final int n) throws H {
        return this.A(s.toCharArray(), n);
    }
}
