// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import a.fc;
import neat.kb;
import a.ec;

public class md extends ec
{
    public static transient kb font;
    public static final kb COMMAND__FONT;
    public static transient kb show;
    public static final kb COMMAND__SHOW;
    public static final kb COMMAND__SHOW__ON;
    public static final kb COMMAND__SHOW__OFF;
    public static final kb COMMAND__SHOW__INTERACT;
    public static final kb COMMAND__SHOW__INTERACT_INSIDE;
    public static transient kb wait;
    public static final kb COMMAND__WAIT;
    public static transient fc scroll;
    public static final kb COMMAND__SCROLL;
    public static final kb COMMAND__SCROLL__TYPE;
    public static final kb COMMAND__SCROLL__TYPE__IN;
    public static final kb COMMAND__SCROLL__TYPE__OUT;
    public static final kb COMMAND__SCROLL__SPEED;
    public static transient fc help;
    public static final kb COMMAND__HELP;
    public static final kb COMMAND__HELP__BUBBLE;
    public static final kb COMMAND__HELP__BUBBLE_RESERVED;
    public static final kb COMMAND__HELP__BUBBLE_SIZE;
    public static final kb COMMAND__HELP__X;
    public static final kb COMMAND__HELP__Y;
    public static transient fc fade;
    public static final kb COMMAND__FADE;
    public static final kb COMMAND__FADE__TYPE;
    public static final kb COMMAND__FADE__TYPE__IN;
    public static final kb COMMAND__FADE__TYPE__OUT;
    public static final kb COMMAND__FADE__TIME;
    public static transient kb forceHelp;
    public static final kb COMMAND__FORCE_HELP;
    private static f k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public void b() {
        md.k.a(this);
    }
    
    public static bb newShadow() {
        return (bb)md.k.a();
    }
    
    public static md a() {
        return (md)md.k.a();
    }
    
    static /* synthetic */ Class c(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        md.z = z(z("8-YYnt)S"));
        COMMAND__FONT = kb.a(z(z("<+YJ")));
        COMMAND__SHOW = kb.a(z(z("),XI")));
        COMMAND__SHOW__ON = kb.a(z(z("5*")));
        COMMAND__SHOW__OFF = kb.a(z(z("5\"Q")));
        COMMAND__SHOW__INTERACT = kb.a(z(z("3*C[s;'C")));
        COMMAND__SHOW__INTERACT_INSIDE = kb.a(z(z("3*C[s;'Cwo)-S[")));
        COMMAND__WAIT = kb.a(z(z("-%^J")));
        COMMAND__SCROLL = kb.a(z(z(")'EQm6")));
        COMMAND__SCROLL__TYPE = kb.a(z(z(".=G[")));
        COMMAND__SCROLL__TYPE__IN = kb.a(z(z("3*")));
        COMMAND__SCROLL__TYPE__OUT = kb.a(z(z("51C")));
        COMMAND__SCROLL__SPEED = kb.a(z(z(")4R[e")));
        COMMAND__HELP = kb.a(z(z("2![N")));
        COMMAND__HELP__BUBBLE = kb.a(z(z("81U\\m?")));
        COMMAND__HELP__BUBBLE_RESERVED = kb.a(z(z("81U\\m?\u0016RMd(2RZ")));
        COMMAND__HELP__BUBBLE_SIZE = kb.a(z(z(")-M[")));
        COMMAND__HELP__X = kb.a("x");
        COMMAND__HELP__Y = kb.a("y");
        COMMAND__FADE = kb.a(z(z("<%S[")));
        COMMAND__FADE__TYPE = kb.a(z(z(".=G[")));
        COMMAND__FADE__TYPE__IN = kb.a(z(z("3*")));
        COMMAND__FADE__TYPE__OUT = kb.a(z(z("51C")));
        COMMAND__FADE__TIME = kb.a(z(z(".-Z[")));
        COMMAND__FORCE_HELP = kb.a(z(z("<+E]d\u0012![N")));
        md.k = new f((md.l != null) ? md.l : (md.l = c(md.z)));
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
        charArray[n] ^= '\u0001';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0085: {
                if (n > 1) {
                    break Label_0085;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'Z';
                            break;
                        }
                        case 1: {
                            c2 = 'D';
                            break;
                        }
                        case 2: {
                            c2 = '7';
                            break;
                        }
                        case 3: {
                            c2 = '>';
                            break;
                        }
                        default: {
                            c2 = '\u0001';
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
