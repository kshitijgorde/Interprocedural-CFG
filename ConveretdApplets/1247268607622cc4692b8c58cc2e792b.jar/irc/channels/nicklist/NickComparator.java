// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.nicklist;

import irc.com.utils.RFC1459;
import java.text.Collator;
import java.util.Comparator;

public class NickComparator implements Comparator
{
    public static final int SYMBOL_RENDERER = 0;
    public static final int BULLET_RENDERER = 1;
    public static final int SORT_BYNICK = 0;
    public static final int SORT_BYAGE = 1;
    public static final int SORT_BYLOCATION = 2;
    public static final int SORT_BYSEX = 3;
    public static final int SORT_ALPHA = 0;
    public static final int SORT_ALPHA_FAVOR_MODE = 1;
    int sort_method;
    static Collator collator;
    static final int[] SORT_MASKS;
    private static final String SORT_CHARS = "123456";
    int sort;
    int sexe;
    
    public NickComparator(final int sort, final int sexe) {
        this.sort_method = 1;
        NickComparator.collator = RFC1459.getCollator();
        this.sort = sort;
        this.sexe = sexe;
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        final NickItem nickItem = (NickItem)o;
        final NickItem nickItem2 = (NickItem)o2;
        String s;
        String s2;
        if (this.sort == 0) {
            s = nickItem.getNick();
            s2 = nickItem2.getNick();
        }
        else if (this.sort == 1) {
            s = "" + nickItem.getAge();
            s2 = "" + nickItem2.getAge();
        }
        else if (this.sort == 2) {
            s = nickItem.getLoc();
            s2 = nickItem2.getLoc();
        }
        else if (this.sexe == 2) {
            s = "" + nickItem.getSexe();
            s2 = "" + nickItem2.getSexe();
        }
        else {
            s = "" + nickItem2.getSexe();
            s2 = "" + nickItem.getSexe();
        }
        final int modes = nickItem.getModes();
        final int modes2 = nickItem2.getModes();
        switch (this.sort_method) {
            default: {
                return NickComparator.collator.compare(nickItem.getNick(), nickItem2.getNick());
            }
            case 1: {
                int n;
                for (n = 0; n < NickComparator.SORT_MASKS.length && (modes & NickComparator.SORT_MASKS[n]) == 0x0; ++n) {}
                if (n >= NickComparator.SORT_MASKS.length) {
                    n = NickComparator.SORT_MASKS.length - 1;
                }
                final String string = "123456".charAt(n) + s;
                int n2;
                for (n2 = 0; n2 < NickComparator.SORT_MASKS.length && (modes2 & NickComparator.SORT_MASKS[n2]) == 0x0; ++n2) {}
                if (n2 >= NickComparator.SORT_MASKS.length) {
                    n2 = NickComparator.SORT_MASKS.length - 1;
                }
                return NickComparator.collator.compare(string, "123456".charAt(n2) + s2);
            }
        }
    }
    
    static {
        SORT_MASKS = new int[] { 32, 16, 2, 4, 8, 1 };
    }
}
