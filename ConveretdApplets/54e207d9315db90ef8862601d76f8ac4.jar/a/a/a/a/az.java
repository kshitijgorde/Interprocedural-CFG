// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class az
{
    static final char[] if;
    static final char[] a;
    
    static {
        if = new char[] { 't', 'r', 'u', 'e' };
        a = new char[] { 'f', 'a', 'l', 's', 'e' };
    }
    
    boolean a(final bi bi, final bi bi2, final boolean b) {
        if (b) {
            if (bi.char < bi2.char) {
                return false;
            }
            switch (bi.char) {
                case 5: {
                    if (bi2.char == 5) {
                        bi.goto = bi2.goto;
                        bi.a = bi2.a;
                        break;
                    }
                    return false;
                }
                case 4: {
                    switch (bi2.char) {
                        case 4: {
                            bi.int = i.a(bi2.int, bi.int);
                            break;
                        }
                        case 3: {
                            bi.int = Double.toString(bi2.else).toCharArray();
                            break;
                        }
                        case 2: {
                            bi.int = Long.toString(bi2.case).toCharArray();
                            break;
                        }
                        case 1: {
                            if (bi2.long) {
                                bi.int = az.if;
                                break;
                            }
                            bi.int = az.a;
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (bi2.char) {
                        case 3: {
                            bi.else = bi2.else;
                            break;
                        }
                        case 2: {
                            bi.else = bi2.case;
                            break;
                        }
                        case 1: {
                            if (bi2.long) {
                                bi.else = 1.0;
                                break;
                            }
                            bi.else = 0.0;
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (bi2.char) {
                        case 2: {
                            bi.case = bi2.case;
                            break;
                        }
                        case 1: {
                            if (bi2.long) {
                                bi.case = 1L;
                                break;
                            }
                            bi.case = 0L;
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    bi.long = bi2.long;
                    break;
                }
            }
        }
        else {
            switch (bi.char = bi2.char) {
                case 4: {
                    bi.int = i.a(bi2.int, bi.int);
                    break;
                }
                case 3: {
                    bi.else = bi2.else;
                    break;
                }
                case 2: {
                    bi.case = bi2.case;
                    break;
                }
                case 1: {
                    bi.long = bi2.long;
                    break;
                }
                case 5: {
                    bi.goto = bi2.goto;
                    bi.a = bi2.a;
                    break;
                }
            }
        }
        return true;
    }
    
    static final boolean a(final bi bi) {
        switch (bi.char) {
            case 1: {
                return bi.long;
            }
            case 2: {
                return bi.case == 0L;
            }
            case 3: {
                return bi.else == 0.0;
            }
            case 4: {
                return i.do(bi.int, az.if) == 0;
            }
            default: {
                return false;
            }
        }
    }
    
    static final int if(final bi bi) {
        switch (bi.char) {
            case 1: {
                if (bi.long) {
                    return 0;
                }
                return 1;
            }
            case 2: {
                return (int)bi.case;
            }
            case 3: {
                return (int)bi.else;
            }
            case 4: {
                return (int)a(bi.int);
            }
            default: {
                return 0;
            }
        }
    }
    
    static final float do(final bi bi) {
        switch (bi.char) {
            case 1: {
                if (bi.long) {
                    return 0.0f;
                }
                return 1.0f;
            }
            case 2: {
                return bi.case;
            }
            case 3: {
                return (float)bi.else;
            }
            case 4: {
                return a(bi.int);
            }
            default: {
                return 0.0f;
            }
        }
    }
    
    static final float a(final char[] array) {
        float n = 0.0f;
        int n2 = 0;
        int n3 = 1;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == '\0') {
                return n / n3;
            }
            if (array[i] >= '0' && array[i] <= '9') {
                n = n * 10.0f + array[i] - 48.0f;
                if (n2 != 0) {
                    n3 *= 10;
                }
            }
            else if (array[i] == '.') {
                n2 = 1;
            }
        }
        return n / n3;
    }
    
    static final String for(final bi bi) {
        if (bi == null) {
            return "";
        }
        switch (bi.char) {
            case 1: {
                return new StringBuffer().append(bi.long).toString();
            }
            case 2: {
                return new StringBuffer().append(bi.case).toString();
            }
            case 3: {
                return new StringBuffer().append((float)bi.else).toString();
            }
            case 4: {
                return new String(bi.int, 0, i.a(bi.int));
            }
            default: {
                return "";
            }
        }
    }
}
