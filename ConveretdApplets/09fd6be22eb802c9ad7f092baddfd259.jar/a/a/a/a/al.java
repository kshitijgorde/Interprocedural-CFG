// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class al
{
    static final char[] if;
    static final char[] a;
    
    static {
        if = new char[] { 't', 'r', 'u', 'e' };
        a = new char[] { 'f', 'a', 'l', 's', 'e' };
    }
    
    boolean a(final a3 a3, final a3 a4, final boolean b) {
        if (b) {
            if (a3.char < a4.char) {
                return false;
            }
            switch (a3.char) {
                case 5: {
                    if (a4.char == 5) {
                        a3.goto = a4.goto;
                        a3.a = a4.a;
                        break;
                    }
                    return false;
                }
                case 4: {
                    switch (a4.char) {
                        case 4: {
                            a3.int = g.a(a4.int, a3.int);
                            break;
                        }
                        case 3: {
                            a3.int = Double.toString(a4.else).toCharArray();
                            break;
                        }
                        case 2: {
                            a3.int = Long.toString(a4.case).toCharArray();
                            break;
                        }
                        case 1: {
                            if (a4.long) {
                                a3.int = al.if;
                                break;
                            }
                            a3.int = al.a;
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    switch (a4.char) {
                        case 3: {
                            a3.else = a4.else;
                            break;
                        }
                        case 2: {
                            a3.else = a4.case;
                            break;
                        }
                        case 1: {
                            if (a4.long) {
                                a3.else = 1.0;
                                break;
                            }
                            a3.else = 0.0;
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (a4.char) {
                        case 2: {
                            a3.case = a4.case;
                            break;
                        }
                        case 1: {
                            if (a4.long) {
                                a3.case = 1L;
                                break;
                            }
                            a3.case = 0L;
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    a3.long = a4.long;
                    break;
                }
            }
        }
        else {
            switch (a3.char = a4.char) {
                case 4: {
                    a3.int = g.a(a4.int, a3.int);
                    break;
                }
                case 3: {
                    a3.else = a4.else;
                    break;
                }
                case 2: {
                    a3.case = a4.case;
                    break;
                }
                case 1: {
                    a3.long = a4.long;
                    break;
                }
                case 5: {
                    a3.goto = a4.goto;
                    a3.a = a4.a;
                    break;
                }
            }
        }
        return true;
    }
    
    static final boolean a(final a3 a3) {
        switch (a3.char) {
            case 1: {
                return a3.long;
            }
            case 2: {
                return a3.case == 0L;
            }
            case 3: {
                return a3.else == 0.0;
            }
            case 4: {
                return g.do(a3.int, al.if) == 0;
            }
            default: {
                return false;
            }
        }
    }
    
    static final int if(final a3 a3) {
        switch (a3.char) {
            case 1: {
                if (a3.long) {
                    return 0;
                }
                return 1;
            }
            case 2: {
                return (int)a3.case;
            }
            case 3: {
                return (int)a3.else;
            }
            case 4: {
                return (int)a(a3.int);
            }
            default: {
                return 0;
            }
        }
    }
    
    static final float do(final a3 a3) {
        switch (a3.char) {
            case 1: {
                if (a3.long) {
                    return 0.0f;
                }
                return 1.0f;
            }
            case 2: {
                return a3.case;
            }
            case 3: {
                return (float)a3.else;
            }
            case 4: {
                return a(a3.int);
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
    
    static final String for(final a3 a3) {
        if (a3 == null) {
            return "";
        }
        switch (a3.char) {
            case 1: {
                return new StringBuffer().append(a3.long).toString();
            }
            case 2: {
                return new StringBuffer().append(a3.case).toString();
            }
            case 3: {
                return new StringBuffer().append((float)a3.else).toString();
            }
            case 4: {
                return new String(a3.int, 0, g.a(a3.int));
            }
            default: {
                return "";
            }
        }
    }
}
