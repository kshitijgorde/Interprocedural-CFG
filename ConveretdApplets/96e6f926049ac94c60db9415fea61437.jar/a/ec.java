// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;
import neat.kb;
import neat.db;

public class ec extends db
{
    public static transient kb t;
    public static final kb COMMAND__T;
    public static transient kb get;
    public static final kb COMMAND__GET;
    public static transient fc set;
    public static final kb COMMAND__SET;
    public static final kb COMMAND__SET__VARIABLE;
    public static final kb COMMAND__SET__OTHER_VARIABLE;
    public static final kb COMMAND__SET__VALUE;
    public static transient fc add;
    public static final kb COMMAND__ADD;
    public static final kb COMMAND__ADD__VARIABLE;
    public static final kb COMMAND__ADD__OTHER_VARIABLE;
    public static final kb COMMAND__ADD__VALUE;
    public static transient fc count;
    public static final kb COMMAND__COUNT;
    public static final kb COMMAND__COUNT__VARIABLE;
    public static final kb COMMAND__COUNT__OTHER_VARIABLE;
    public static final kb COMMAND__COUNT__VALUE;
    public static final kb COMMAND__COUNT__OPERATION;
    public static transient kb label;
    public static final kb COMMAND__LABEL;
    public static transient kb go;
    public static final kb COMMAND__GO;
    public static transient kb call;
    public static final kb COMMAND__CALL;
    public static transient kb callReturn;
    public static final kb COMMAND__CALL_RETURN;
    public static transient fc ifgo;
    public static final kb COMMAND__IF;
    public static final kb COMMAND__IF__VARIABLE;
    public static final kb COMMAND__IF__EQUALS;
    public static final kb COMMAND__IF__FIT;
    public static final kb COMMAND__IF__GO;
    public static transient kb end;
    public static final kb COMMAND__END;
    public static final kb VARIABLE__BUTTON__TRUE;
    public static final kb VARIABLE__BUTTON__FALSE;
    private static f h;
    private static /* synthetic */ Class i;
    public static int j;
    private static String z;
    
    public void b() {
        ec.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ec.h.a();
    }
    
    public static ec a() {
        return (ec)ec.h.a();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ec.z = z(z("-VBy"));
        COMMAND__T = kb.a("t");
        COMMAND__GET = kb.a(z(z("+\u001dS")));
        COMMAND__SET = kb.a(z(z("?\u001dS")));
        COMMAND__SET__VARIABLE = kb.a(z(z(":\u0019Us\u0003.\u0014B")));
        COMMAND__SET__OTHER_VARIABLE = kb.a(z(z("#\fO\u007f\u0010")));
        COMMAND__SET__VALUE = kb.a(z(z(":\u0019Ko\u0007")));
        COMMAND__ADD = kb.a(z(z("-\u001cC")));
        COMMAND__ADD__VARIABLE = kb.a(z(z(":\u0019Us\u0003.\u0014B")));
        COMMAND__ADD__OTHER_VARIABLE = kb.a(z(z("#\fO\u007f\u0010")));
        COMMAND__ADD__VALUE = kb.a(z(z(":\u0019Ko\u0007")));
        COMMAND__COUNT = kb.a(z(z("/\u0017Rt\u0016")));
        COMMAND__COUNT__VARIABLE = kb.a(z(z(":\u0019Us\u0003.\u0014B")));
        COMMAND__COUNT__OTHER_VARIABLE = kb.a(z(z("#\fO\u007f\u0010")));
        COMMAND__COUNT__VALUE = kb.a(z(z(":\u0019Ko\u0007")));
        COMMAND__COUNT__OPERATION = kb.a(z(z("#\b")));
        COMMAND__LABEL = kb.a(z(z(" \u0019E\u007f\u000e")));
        COMMAND__GO = kb.a(z(z("+\u0017")));
        COMMAND__CALL = kb.a(z(z("/\u0019Kv")));
        COMMAND__CALL_RETURN = kb.a(z(z("/\u0019Kv0)\fRh\f")));
        COMMAND__IF = kb.a(z(z("%\u001e@u")));
        COMMAND__IF__VARIABLE = kb.a(z(z(":\u0019Us\u0003.\u0014B")));
        COMMAND__IF__EQUALS = kb.a(z(z(")\tR{\u000e?")));
        COMMAND__IF__FIT = kb.a(z(z("*\u0011S")));
        COMMAND__IF__GO = kb.a(z(z("+\u0017")));
        COMMAND__END = kb.a(z(z(")\u0016C")));
        VARIABLE__BUTTON__TRUE = kb.a(z(z("8\nR\u007f")));
        VARIABLE__BUTTON__FALSE = kb.a(z(z("*\u0019Ki\u0007")));
        ec.h = new f((ec.i != null) ? ec.i : (ec.i = b(ec.z)));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'b';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'L';
                            break;
                        }
                        case 1: {
                            c2 = 'x';
                            break;
                        }
                        case 2: {
                            c2 = '\'';
                            break;
                        }
                        case 3: {
                            c2 = '\u001a';
                            break;
                        }
                        default: {
                            c2 = 'b';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
