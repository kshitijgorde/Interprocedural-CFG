// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.messages;

import java.text.ParseException;

public class MircMessage extends Message
{
    public static final char BB = '\u0001';
    public static final char BOLD = '\u0002';
    public static final char COLOR = '\u0003';
    public static final char BELL = '\u0007';
    public static final char RESET = '\u000f';
    public static final char ITALIC = '\u0014';
    public static final char REVERSE = '\u0016';
    public static final char UNDERLINE = '\u001f';
    public static final char HYPERLINKSTART = '\u0345';
    public static final char HYPERLINKEND = '\u0346';
    public static final char NICKSTART = '\u0347';
    public static final char NICKEND = '\u0348';
    public static final char NICKSTART1 = '\u034a';
    public static final char NICKEND1 = '\u034b';
    public static final char SEX = '¡';
    public static final char ANG = '¢';
    public static final char FOU = '£';
    public static final char FETE = '¤';
    public static final char AMOUREUX = '¥';
    public static final char ARF = '¦';
    public static final char BOULET = '§';
    public static final char BETE = '¨';
    public static final char BISOU = '©';
    public static final char BOF = 'ª';
    public static final char BOB = '¬';
    public static final char CASSE = '\u00ad';
    public static final char CLASS = '®';
    public static final char CLINDOIEL = '¯';
    public static final char COLERE = '°';
    public static final char COEURS = '±';
    public static final char CADEAU = '²';
    public static final char CAFE = '³';
    public static final char CHUT = '¶';
    public static final char COEURDIABLE = '¼';
    public static final char COEURFLECHE = '½';
    public static final char COEUR = '¾';
    public static final char DIABLE = '¿';
    public static final char DSL = '\u00c4';
    public static final char DODO = '\u00c5';
    public static final char ETOILE = '\u00c6';
    public static final char FROID = '\u00c7';
    public static final char FATIGUE = '\u00c8';
    public static final char FATIGUE1 = '\u00c9';
    public static final char FRIMEUR = '\u00ca';
    public static final char FOOT = '\u00cb';
    public static final char GATEAU = '\u00d0';
    public static final char GR_ROSE = '\u00d1';
    public static final char HOO = '\u00d5';
    public static final char HUM = '\u00d6';
    public static final char HAPPY = '\u00de';
    public static final char HEIN = '\u00df';
    public static final char HIHI = '\u00e6';
    public static final char KISS = '\u00eb';
    public static final char LANGUE = '\u00ec';
    public static final char LUNE = '\u00f0';
    public static final char MORT = '\u00f1';
    public static final char MUSIC = '\u00f6';
    public static final char MIAM = '\u00f8';
    public static final char MAISEUH = '\u00fc';
    public static final char OEIL = '\u00fd';
    public static final char OUPS = '\u00fe';
    public static final char PHONE = '\u0192';
    public static final char PLEURE = '\u017d';
    public static final char QUOI = '\u0178';
    public static final char RIRE = '\u0161';
    public static final char ROUGIEFILLE = '\u0152';
    public static final char ROUGIT = '\u0153';
    public static final char ROSE = '\u00ff';
    public static final char ROUGIEF = '\u007f';
    public static final char REGISTER = '\u00cc';
    public static final char SHIT = '´';
    public static final char SIFFLE = 'µ';
    public static final char SOULE = '¸';
    public static final char TIRELANGUE = '¹';
    public static final char WAZA = 'º';
    public static final char VI = '\u00c0';
    public static final char BAFFE = '\u00c1';
    public static final char CHEESE = '\u00c3';
    public static final char STAR = '\u0349';
    public static final char LOOL1 = '\u034c';
    public static final char BOX1 = '\u0350';
    public static final char SEX1 = '\u0351';
    public static final char SIFFLE1 = '\u034f';
    
    public static String attrDecode(final String s) {
        if (s == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if ('%' != charArray[i] || i == charArray.length - 1) {
                sb.append(charArray[i]);
            }
            else {
                ++i;
                char c = '\0';
                switch (charArray[i]) {
                    case 'B': {
                        c = '\u0002';
                        break;
                    }
                    case 'C': {
                        c = '\u0003';
                        break;
                    }
                    case 'A': {
                        c = '\u0007';
                        break;
                    }
                    case 'O': {
                        c = '\u000f';
                        break;
                    }
                    case 'I': {
                        c = '\u0014';
                        break;
                    }
                    case 'R': {
                        c = '\u0016';
                        break;
                    }
                    case 'U': {
                        c = '\u001f';
                        break;
                    }
                    case '%': {
                        c = '%';
                        break;
                    }
                    default: {
                        sb.append('%');
                        c = charArray[i];
                        break;
                    }
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public static String attrEncode(final char c) {
        char c2 = '\0';
        switch (c) {
            case '\u0002': {
                c2 = 'B';
                break;
            }
            case '\u0003': {
                c2 = 'C';
                break;
            }
            case '\u0007': {
                c2 = 'A';
                break;
            }
            case '\u000f': {
                c2 = 'O';
                break;
            }
            case '\u0014': {
                c2 = 'I';
                break;
            }
            case '\u0016': {
                c2 = 'R';
                break;
            }
            case '\u001f': {
                c2 = 'U';
                break;
            }
            default: {
                return String.valueOf(c);
            }
        }
        return "%" + c2;
    }
    
    public static String attrEncode(final String s) {
        final StringBuffer sb = new StringBuffer();
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            sb.append(attrEncode(charArray[i]));
        }
        return sb.toString();
    }
    
    public static String filterMircAttributes(final String s) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int i = s.length();
        final char[] array = new char[i];
        while (i > 0) {
            final char char1 = s.charAt(n2);
            if ((n4 != 0 && Character.isDigit(char1) && n < 2) || (n4 != 0 && char1 == ',' && n < 3)) {
                ++n;
                if (char1 == ',') {
                    n = 0;
                }
            }
            else {
                if (n4 != 0) {
                    n4 = 0;
                }
                switch (char1) {
                    case '\u0003': {
                        n4 = 1;
                        n = 0;
                        break;
                    }
                    case '\u0001':
                    case '\u0002':
                    case '\u0007':
                    case '\u000f':
                    case '\u0014':
                    case '\u0016':
                    case '\u001f': {
                        break;
                    }
                    default: {
                        array[n3] = char1;
                        ++n3;
                        break;
                    }
                }
            }
            ++n2;
            --i;
        }
        return String.valueOf(array, 0, n3);
    }
    
    public MircMessage(final String s) throws ParseException {
        super(s);
        this.attrDecodeParameters();
    }
    
    public MircMessage(final String s, final String s2, final String[] array) {
        super(s, s2, array);
        this.attrDecodeParameters();
    }
    
    public MircMessage(final String s, final String[] array) {
        this("", s, array);
    }
    
    private void attrDecodeParameters() {
        for (int i = 0; i < this.parameters.length; ++i) {
            this.parameters[i] = attrDecode(this.parameters[i]);
        }
    }
    
    @Override
    public String[] getParameters() {
        if (this.parameters == null) {
            return null;
        }
        final String[] array = new String[this.parameters.length];
        for (int i = 0; i < this.parameters.length; ++i) {
            array[i] = filterMircAttributes(this.parameters[i]);
        }
        return array;
    }
}
