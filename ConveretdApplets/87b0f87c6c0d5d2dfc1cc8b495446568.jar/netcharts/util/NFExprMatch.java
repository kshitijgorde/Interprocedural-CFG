// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFExprMatch
{
    public static boolean exprMatch(final String s, final String s2) {
        return exprMatch(s.toCharArray(), s2.toCharArray());
    }
    
    public static boolean exprMatch(final char[] array, final char[] array2) {
        final char[] array3 = new char[array.length + 1];
        final char[] array4 = new char[array2.length + 1];
        System.arraycopy(array, 0, array3, 0, array.length);
        array3[array.length] = '\0';
        System.arraycopy(array2, 0, array4, 0, array2.length);
        array4[array2.length] = '\0';
        return a(array3, 0, array4, 0);
    }
    
    private static boolean a(final char[] array, int n, final char[] array2, int n2) {
        while (array[n] != '\0') {
            Label_0261: {
                switch (array[n]) {
                    case '\\': {
                        ++n;
                        break;
                    }
                    case '?': {
                        if (array2[n2] == '\0') {
                            return false;
                        }
                        break Label_0261;
                    }
                    case '*': {
                        while (array[n] == '*') {
                            ++n;
                        }
                        if (array[n] == '\0') {
                            return true;
                        }
                        while (array2[n2] != '\0') {
                            if (a(array, n, array2, n2)) {
                                return true;
                            }
                            ++n2;
                        }
                        return false;
                    }
                    case '[': {
                        ++n;
                        boolean b;
                        if (array[n] != '^') {
                            b = false;
                        }
                        else {
                            b = true;
                            ++n;
                        }
                        boolean b2 = false;
                        while (array[n] != ']') {
                            if (array[n] == '\0') {
                                return false;
                            }
                            final char c = array[n];
                            ++n;
                            char c2;
                            if (array[n] != '-') {
                                c2 = c;
                            }
                            else {
                                ++n;
                                c2 = array[n];
                                ++n;
                                if (c2 == ']' || c2 == '\0') {
                                    return false;
                                }
                            }
                            if (array2[n2] >= c && array2[n2] <= c2) {
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2 || b) {
                            return false;
                        }
                        while (array[n] != ']') {
                            if (array[n] == '\0') {
                                return false;
                            }
                            ++n;
                        }
                        break Label_0261;
                    }
                }
                if (array2[n2] != array[n]) {
                    return false;
                }
            }
            ++n2;
            ++n;
        }
        return array2[n2] == '\0';
    }
}
