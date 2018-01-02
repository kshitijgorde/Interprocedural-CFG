// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import a.fc;
import neat.kb;
import a.ec;

public class ld extends ec
{
    public static transient kb font;
    public static final kb COMMAND__FONT;
    public static transient kb fontIndex;
    public static final kb COMMAND__FONT_INDEX;
    public static transient kb show;
    public static final kb COMMAND__SHOW;
    public static final kb COMMAND__SHOW__ON;
    public static final kb COMMAND__SHOW__OFF;
    public static transient kb wait;
    public static final kb COMMAND__WAIT;
    public static transient fc clip;
    public static final kb COMMAND__CLIP;
    public static final kb COMMAND__CLIP__WIDTH;
    public static final kb COMMAND__CLIP__HEIGHT;
    public static transient fc button;
    public static final kb COMMAND__BUTTON;
    public static final kb COMMAND__BUTTON__SIZE;
    public static final kb COMMAND__BUTTON__LEFT;
    public static final kb COMMAND__BUTTON__LEFT_VARIABLE;
    public static final kb COMMAND__BUTTON__LEFT_TEXT;
    public static final kb COMMAND__BUTTON__RIGHT;
    public static final kb COMMAND__BUTTON__RIGHT_VARIABLE;
    public static final kb COMMAND__BUTTON__RIGHT_TEXT;
    public static final kb COMMAND__BUTTON__CENTER;
    public static final kb COMMAND__BUTTON__CENTER_VARIABLE;
    public static final kb COMMAND__BUTTON__CENTER_TEXT;
    public static final kb VARIABLE__BUTTON__CLOSE_DEFAULT;
    public static transient fc line;
    public static final kb COMMAND__LINE;
    public static final kb COMMAND__LINE__NAME;
    public static final kb COMMAND__LINE__TEXT;
    public static final kb COMMAND__LINE__TEXT_VARIABLE;
    public static final kb COMMAND__LINE__IS_TEXT;
    public static final kb COMMAND__LINE__TYPE;
    public static final kb COMMAND__LINE__NORMAL_TEXT;
    public static final kb COMMAND__LINE__ACTIVE_TEXT;
    public static final kb COMMAND__LINE__X;
    public static final kb COMMAND__LINE__Y;
    public static final kb COMMAND__LINE__CLIP_OFFSET;
    public static final kb COMMAND__LINE__FONT;
    public static final kb COMMAND__LINE__PRIORITY;
    public static final kb COMMAND__LINE__SPRITE;
    public static final kb COMMAND__LINE__SPRITE_ANIM;
    public static final kb COMMAND__LINE__SPRITE_HAS_POS;
    public static final kb COMMAND__LINE__BUTTON_SIZE;
    public static final kb COMMAND__LINE__IS_BUTTON_BODY;
    public static transient kb sprite;
    public static final kb COMMAND__SPRITE;
    public static transient kb clear;
    public static final kb COMMAND__CLEAR;
    public static transient kb getScore;
    public static final kb COMMAND__GET_SCORE;
    public static transient fc offset;
    public static final kb COMMAND__OFFSET;
    public static final kb COMMAND__OFFSET__X;
    public static final kb COMMAND__OFFSET__Y;
    public static transient kb bg;
    public static final kb COMMAND__BG;
    public static final kb COMMAND__LINE__TYPE__TEXT;
    public static final kb COMMAND__LINE__TYPE__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__VARIABLE__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__SELECT;
    public static final kb COMMAND__LINE__TYPE__SELECT__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__SELECT__VALUE;
    public static final kb COMMAND__LINE__TYPE__CHECKBOX;
    public static final kb COMMAND__LINE__TYPE__CHECKBOX__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__RADIO;
    public static final kb COMMAND__LINE__TYPE__RADIO__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__VALUE;
    public static final kb COMMAND__LINE__TYPE__VALUE__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__VALUE__CHANGE;
    public static final kb COMMAND__LINE__TYPE__VALUE__MIN;
    public static final kb COMMAND__LINE__TYPE__VALUE__MAX;
    public static final kb COMMAND__LINE__TYPE__VALUE__PREFIX;
    public static final kb COMMAND__LINE__TYPE__VALUE__EXT;
    public static final kb COMMAND__LINE__TYPE__VALUE__IS_LOOPED;
    public static final kb COMMAND__LINE__TYPE__INPUT;
    public static final kb COMMAND__LINE__TYPE__INPUT__VARIABLE;
    public static final kb COMMAND__LINE__TYPE__INPUT__IS_FOCUS;
    public static final kb COMMAND__LINE__TYPE__INPUT__WIDTH;
    public static final kb COMMAND__LINE__TYPE__INPUT__PIXEL_WIDTH;
    public static final kb COMMAND__LINE__TYPE__INPUT__ARE_SMALL_LETTERS;
    public static final kb COMMAND__LINE__TYPE__INPUT__ARE_BIG_LETTERS;
    public static final kb COMMAND__LINE__TYPE__INPUT__ARE_DIGITS;
    public static final kb COMMAND__LINE__TYPE__INPUT__IS_SPACE;
    public static final kb COMMAND__LINE__TYPE__INPUT__ARE_SPECIALS;
    public static final kb COMMAND__LINE__TYPE__INPUT__ARE_NICK_AND_EMAIL_SPECIALS;
    public static final kb COMMAND__LINE__TYPE__INPUT__ENTER_VARIABLE;
    public static final kb COMMAND__LINE__TYPE__INPUT__CURSOR_ON;
    public static final kb COMMAND__LINE__TYPE__INPUT__CURSOR_OFF;
    private static f k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public void b() {
        ld.k.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ld.k.a();
    }
    
    public static ld a() {
        return (ld)ld.k.a();
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
        ld.z = z(z("g\u0003\fEW+\u0006\u0006"));
        COMMAND__FONT = kb.a(z(z("c\u0005\fV")));
        COMMAND__FONT_INDEX = kb.a(z(z("c\u0005\fVqk\u000e\u0007Z")));
        COMMAND__SHOW = kb.a(z(z("v\u0002\rU")));
        COMMAND__SHOW__ON = kb.a(z(z("j\u0004")));
        COMMAND__SHOW__OFF = kb.a(z(z("j\f\u0004")));
        COMMAND__WAIT = kb.a(z(z("r\u000b\u000bV")));
        COMMAND__CLIP = kb.a(z(z("f\u0006\u000bR")));
        COMMAND__CLIP__WIDTH = kb.a(z(z("r\u0003\u0006VP")));
        COMMAND__CLIP__HEIGHT = kb.a(z(z("m\u000f\u000bEPq")));
        COMMAND__BUTTON = kb.a(z(z("g\u001f\u0016VWk")));
        COMMAND__BUTTON__SIZE = kb.a(z(z("v\u0003\u0018G")));
        COMMAND__BUTTON__LEFT = kb.a(z(z("i\u000f\u0004V")));
        COMMAND__BUTTON__LEFT_VARIABLE = kb.a(z(z("i\u000f\u0004Vnd\u0018\u000bCZi\u000f")));
        COMMAND__BUTTON__LEFT_TEXT = kb.a(z(z("i\u000f\u0004Vl`\u0012\u0016")));
        COMMAND__BUTTON__RIGHT = kb.a(z(z("w\u0003\u0005JL")));
        COMMAND__BUTTON__RIGHT_VARIABLE = kb.a(z(z("w\u0003\u0005JLS\u000b\u0010KYg\u0006\u0007")));
        COMMAND__BUTTON__RIGHT_TEXT = kb.a(z(z("w\u0003\u0005JLQ\u000f\u001aV")));
        COMMAND__BUTTON__CENTER = kb.a(z(z("f\u000f\fV]w")));
        COMMAND__BUTTON__CENTER_VARIABLE = kb.a(z(z("f\u000f\fV]w<\u0003PQd\b\u000eG")));
        COMMAND__BUTTON__CENTER_TEXT = kb.a(z(z("f\u000f\fV]w>\u0007ZL")));
        VARIABLE__BUTTON__CLOSE_DEFAULT = kb.a(z(z("g\u001f\u0004DWk)\u000eMK`.\u0007DYp\u0006\u0016")));
        COMMAND__LINE = kb.a(z(z("i\u0003\fG")));
        COMMAND__LINE__NAME = kb.a(z(z("k\u000b\u000fG")));
        COMMAND__LINE__TEXT = kb.a("t");
        COMMAND__LINE__TEXT_VARIABLE = kb.a(z(z("q\u000f\u001aVnd\u0018\u000bCZi\u000f")));
        COMMAND__LINE__IS_TEXT = kb.a(z(z("l\u00196G@q")));
        COMMAND__LINE__TYPE = kb.a(z(z("q\u0013\u0012G")));
        COMMAND__LINE__NORMAL_TEXT = kb.a(z(z("k\u0005\u0010OYi")));
        COMMAND__LINE__ACTIVE_TEXT = kb.a(z(z("d\t\u0016KN`")));
        COMMAND__LINE__X = kb.a("x");
        COMMAND__LINE__Y = kb.a("y");
        COMMAND__LINE__CLIP_OFFSET = kb.a(z(z("f\u0006\u000bRwc\f\u0011GL")));
        COMMAND__LINE__FONT = kb.a(z(z("c\u0005\fV")));
        COMMAND__LINE__PRIORITY = kb.a(z(z("u\u0018\u000bMJl\u001e\u001b")));
        COMMAND__LINE__SPRITE = kb.a(z(z("v\u001a\u0010KL`")));
        COMMAND__LINE__SPRITE_ANIM = kb.a(z(z("v\u001a\u0010KL`+\fKU")));
        COMMAND__LINE__SPRITE_HAS_POS = kb.a(z(z("v\u001a\u0010KL`\"\u0003Qhj\u0019")));
        COMMAND__LINE__BUTTON_SIZE = kb.a(z(z("g\u001f\u0016VWk9\u000bX]")));
        COMMAND__LINE__IS_BUTTON_BODY = kb.a(z(z("l\u0019 WLq\u0005\f`Wa\u0013")));
        COMMAND__SPRITE = kb.a(z(z("v\u001a\u0010KL`")));
        COMMAND__CLEAR = kb.a(z(z("f\u0006\u0007CJ")));
        COMMAND__GET_SCORE = kb.a(z(z("b\u000f\u0016q[j\u0018\u0007")));
        COMMAND__OFFSET = kb.a(z(z("j\f\u0004Q]q")));
        COMMAND__OFFSET__X = kb.a("x");
        COMMAND__OFFSET__Y = kb.a("y");
        COMMAND__BG = kb.a(z(z("g\r")));
        COMMAND__LINE__TYPE__TEXT = kb.a(z(z("q\u000f\u001aV")));
        COMMAND__LINE__TYPE__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__VARIABLE__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__SELECT = kb.a(z(z("v\u000f\u000eG[q")));
        COMMAND__LINE__TYPE__SELECT__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__SELECT__VALUE = kb.a(z(z("s\u000b\u000eW]")));
        COMMAND__LINE__TYPE__CHECKBOX = kb.a(z(z("f\u0002\u0007ASg\u0005\u001a")));
        COMMAND__LINE__TYPE__CHECKBOX__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__RADIO = kb.a(z(z("w\u000b\u0006KW")));
        COMMAND__LINE__TYPE__RADIO__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__VALUE = kb.a(z(z("s\u000b\u000eW]")));
        COMMAND__LINE__TYPE__VALUE__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__VALUE__CHANGE = kb.a(z(z("f\u0002\u0003L_`")));
        COMMAND__LINE__TYPE__VALUE__MIN = kb.a(z(z("h\u0003\f")));
        COMMAND__LINE__TYPE__VALUE__MAX = kb.a(z(z("h\u000b\u001a")));
        COMMAND__LINE__TYPE__VALUE__PREFIX = kb.a(z(z("u\u0018\u0007DQ}")));
        COMMAND__LINE__TYPE__VALUE__EXT = kb.a(z(z("`\u0012\u0016")));
        COMMAND__LINE__TYPE__VALUE__IS_LOOPED = kb.a(z(z("l\u0019.MWu\u000f\u0006")));
        COMMAND__LINE__TYPE__INPUT = kb.a(z(z("l\u0004\u0012WL")));
        COMMAND__LINE__TYPE__INPUT__VARIABLE = kb.a(z(z("s\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__INPUT__IS_FOCUS = kb.a(z(z("l\u0019$M[p\u0019")));
        COMMAND__LINE__TYPE__INPUT__WIDTH = kb.a(z(z("r\u0003\u0006VP")));
        COMMAND__LINE__TYPE__INPUT__PIXEL_WIDTH = kb.a(z(z("u\u0003\u001aGTR\u0003\u0006VP")));
        COMMAND__LINE__TYPE__INPUT__ARE_SMALL_LETTERS = kb.a(z(z("d\u0018\u0007qUd\u0006\u000en]q\u001e\u0007PK")));
        COMMAND__LINE__TYPE__INPUT__ARE_BIG_LETTERS = kb.a(z(z("d\u0018\u0007`Qb&\u0007VL`\u0018\u0011")));
        COMMAND__LINE__TYPE__INPUT__ARE_DIGITS = kb.a(z(z("d\u0018\u0007fQb\u0003\u0016Q")));
        COMMAND__LINE__TYPE__INPUT__IS_SPACE = kb.a(z(z("l\u00191RYf\u000f")));
        COMMAND__LINE__TYPE__INPUT__ARE_SPECIALS = kb.a(z(z("d\u0018\u0007qH`\t\u000bCTv")));
        COMMAND__LINE__TYPE__INPUT__ARE_NICK_AND_EMAIL_SPECIALS = kb.a(z(z("d\u0018\u0007lQf\u0001#L\\@\u0007\u0003KTV\u001a\u0007AQd\u0006\u0011")));
        COMMAND__LINE__TYPE__INPUT__ENTER_VARIABLE = kb.a(z(z("`\u0004\u0016GJS\u000b\u0010KYg\u0006\u0007")));
        COMMAND__LINE__TYPE__INPUT__CURSOR_ON = kb.a(z(z("~K\u0001WJv\u0005\u0010\u0013E")));
        COMMAND__LINE__TYPE__INPUT__CURSOR_OFF = kb.a(z(z("~K\u0001WJv\u0005\u0010\u0010E")));
        ld.k = new f((ld.l != null) ? ld.l : (ld.l = c(ld.z)));
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
        charArray[n] ^= '8';
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
                            c2 = '\u0005';
                            break;
                        }
                        case 1: {
                            c2 = 'j';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = '\"';
                            break;
                        }
                        default: {
                            c2 = '8';
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
