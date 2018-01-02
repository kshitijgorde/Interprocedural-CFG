// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.entities;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.mindprod.common15.STA;
import java.util.HashMap;

public class DeEntifyStrings
{
    private static final boolean DEBUGGING = true;
    public static final char UNICODE_NBSP_160_0x0a = ' ';
    public static final int LONGEST_ENTITY;
    public static final int SHORTEST_ENTITY = 4;
    private static final HashMap<String, Character> entityToChar;
    private static String[] spacingTags;
    
    public static char bareHTMLEntityToChar(final String bareEntity, final char howToTranslateNbsp) {
        Character code = DeEntifyStrings.entityToChar.get(bareEntity);
        if (code != null) {
            return code;
        }
        code = DeEntifyStrings.entityToChar.get(bareEntity.toLowerCase());
        if (code != null) {
            return code;
        }
        if ((bareEntity.length() == 4 && bareEntity.equals("nbsp")) || bareEntity.equals("NBSP")) {
            return howToTranslateNbsp;
        }
        if (bareEntity.length() < 2) {
            return '\0';
        }
        try {
            if (bareEntity.charAt(0) != '#') {
                return '\0';
            }
            final char secondChar = bareEntity.charAt(1);
            if (secondChar != 'x' && secondChar != 'X') {
                return (char)Integer.parseInt(bareEntity.substring(1));
            }
            if (bareEntity.length() < 3) {
                return '\0';
            }
            return (char)Integer.parseInt(bareEntity.substring(2), 16);
        }
        catch (NumberFormatException e) {
            return '\0';
        }
    }
    
    public static String deEntifyHTML(final String text, final char translateNbspTo) {
        if (text == null) {
            return null;
        }
        if (text.indexOf(38) < 0) {
            return text;
        }
        final int originalTextLength = text.length();
        final StringBuilder sb = new StringBuilder(originalTextLength);
        for (int i = 0; i < originalTextLength; ++i) {
            final int whereAmp = text.indexOf(38, i);
            if (whereAmp < 0) {
                sb.append(text.substring(i));
                break;
            }
            sb.append(text.substring(i, whereAmp));
            i = whereAmp;
            final String possEntity = text.substring(i + 1, Math.min(i + DeEntifyStrings.LONGEST_ENTITY, text.length()));
            final char t = possBareHTMLEntityWithSemicolonToChar(possEntity, translateNbspTo);
            if (t != '\0') {
                sb.append(t);
                final int whereSemi = possEntity.indexOf(";", 2);
                i += whereSemi + 1;
            }
            else {
                sb.append('&');
            }
        }
        return (sb.length() == originalTextLength) ? text : sb.toString();
    }
    
    public static String deEntifyXML(final String text) {
        return deEntifyHTML(text, ' ');
    }
    
    public static String flattenHTML(final String text, final char translateNbspTo) {
        return deEntifyHTML(stripHTMLTags(text), translateNbspTo);
    }
    
    public static String flattenXML(final String text) {
        return deEntifyXML(stripXMLTags(text));
    }
    
    public static char possEntityToChar(final String possBareEntityWithSemicolon) {
        return possBareHTMLEntityWithSemicolonToChar(possBareEntityWithSemicolon, ' ');
    }
    
    public static String stripHTMLTags(String html) {
        html = stripHTMLTagPairs(html);
        return stripIndividualTags(html);
    }
    
    public static String stripXMLTags(final String xml) {
        return stripIndividualTags(xml);
    }
    
    protected static char possBareHTMLEntityWithSemicolonToChar(final String possBareEntityWithSemicolon, final char translateNbspTo) {
        if (possBareEntityWithSemicolon.length() < 3) {
            return '\0';
        }
        final int whereSemi = possBareEntityWithSemicolon.indexOf(59, 2);
        if (whereSemi < 2) {
            return '\0';
        }
        return bareHTMLEntityToChar(possBareEntityWithSemicolon.substring(0, whereSemi), translateNbspTo);
    }
    
    private static String preStripIndividualTags(final String html) {
        final StringBuilder sb = new StringBuilder(html.length() * 110 / 100);
        char prevChar = '\0';
        for (int i = 0; i < html.length(); ++i) {
            final char c = html.charAt(i);
            if (c == '<') {
                final int look = html.startsWith("/", i + 1) ? (i + 2) : (i + 1);
                final String[] arr$ = DeEntifyStrings.spacingTags;
                final int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    final String tag = arr$[i$];
                    if (html.startsWith(tag, look)) {
                        if (prevChar > ' ') {
                            sb.append(' ');
                            break;
                        }
                        break;
                    }
                    else {
                        ++i$;
                    }
                }
            }
            sb.append(c);
            prevChar = c;
        }
        return sb.toString();
    }
    
    private static String stripHTMLTagPairs(String s) {
        final String[] arr$;
        final String[] tags = arr$ = new String[] { "applet", "APPLET", "style", "STYLE", "script", "SCRIPT" };
        for (final String tag : arr$) {
            final String beginTag = "<" + tag;
            final String endTag = "</" + tag + ">";
            int begin = 0;
            while (begin < s.length() && (begin = s.indexOf(beginTag, begin)) >= 0) {
                final int end;
                if ((end = s.indexOf(endTag, begin + beginTag.length())) > 0) {
                    s = s.substring(0, begin) + s.substring(end + endTag.length());
                }
                else {
                    s = s.substring(0, begin);
                }
            }
        }
        return s;
    }
    
    private static String stripIndividualTags(String html) {
        html = html.trim();
        html = preStripIndividualTags(html);
        final int numChars = html.length();
        final StringBuilder sb = new StringBuilder(numChars);
        boolean inside = false;
        boolean cleanedAnyWhitespace = false;
        boolean lastCharSpace = false;
        for (int i = 0; i < numChars; ++i) {
            final char c = html.charAt(i);
            switch (c) {
                default: {
                    if (c < ' ') {
                        if (!inside) {
                            lastCharSpace = true;
                            cleanedAnyWhitespace = true;
                            break;
                        }
                        break;
                    }
                    else {
                        if (!inside) {
                            if (lastCharSpace) {
                                sb.append(' ');
                                lastCharSpace = false;
                            }
                            sb.append(c);
                            break;
                        }
                        break;
                    }
                    break;
                }
                case '<': {
                    inside = true;
                    break;
                }
                case '>': {
                    inside = false;
                    break;
                }
                case ' ': {
                    if (!inside) {
                        lastCharSpace = true;
                        break;
                    }
                    break;
                }
                case '\t':
                case '\n':
                case '\r':
                case '\u007f':
                case ' ': {
                    if (!inside) {
                        lastCharSpace = true;
                        cleanedAnyWhitespace = true;
                        break;
                    }
                    break;
                }
            }
        }
        final String result = (cleanedAnyWhitespace || sb.length() != numChars) ? sb.toString() : html;
        return STA.condense(result);
    }
    
    static {
        LONGEST_ENTITY = "&thetasym;".length();
        DeEntifyStrings.spacingTags = new String[] { "tr", "td", "th", "p", "br", "dl", "dt", "li" };
        final String[] entityKeys = { "AElig", "Aacute", "Acirc", "Agrave", "Alpha", "Aring", "Atilde", "Auml", "Beta", "Ccedil", "Chi", "Dagger", "Delta", "ETH", "Eacute", "Ecirc", "Egrave", "Epsilon", "Eta", "Euml", "Gamma", "Iacute", "Icirc", "Igrave", "Iota", "Iuml", "Kappa", "Lambda", "Mu", "Ntilde", "Nu", "OElig", "Oacute", "Ocirc", "Ograve", "Omega", "Omicron", "Oslash", "Otilde", "Ouml", "Phi", "Pi", "Prime", "Psi", "Rho", "Scaron", "Sigma", "THORN", "Tau", "Theta", "Uacute", "Ucirc", "Ugrave", "Upsilon", "Uuml", "Xi", "Yacute", "Yuml", "Zeta", "aacute", "acirc", "acute", "aelig", "agrave", "alefsym", "alpha", "amp", "and", "ang", "aring", "asymp", "atilde", "auml", "bdquo", "beta", "brvbar", "bull", "cap", "ccedil", "cedil", "cent", "chi", "circ", "clubs", "cong", "copy", "crarr", "cup", "curren", "dArr", "dagger", "darr", "deg", "delta", "diams", "divide", "eacute", "ecirc", "egrave", "empty", "emsp", "ensp", "epsilon", "equiv", "eta", "eth", "euml", "euro", "exist", "fnof", "forall", "frac12", "frac14", "frac34", "frasl", "gamma", "ge", "gt", "hArr", "harr", "hearts", "hellip", "iacute", "icirc", "iexcl", "igrave", "image", "infin", "int", "iota", "iquest", "isin", "iuml", "kappa", "lArr", "lambda", "lang", "laquo", "larr", "lceil", "ldquo", "le", "lfloor", "lowast", "loz", "lrm", "lsaquo", "lsquo", "lt", "macr", "mdash", "micro", "middot", "minus", "mu", "nabla", "nbsp", "ndash", "ne", "ni", "not", "notin", "nsub", "ntilde", "nu", "oacute", "ocirc", "oelig", "ograve", "oline", "omega", "omicron", "oplus", "or", "ordf", "ordm", "oslash", "otilde", "otimes", "ouml", "para", "part", "permil", "perp", "phi", "pi", "piv", "plusmn", "pound", "prime", "prod", "prop", "psi", "quot", "rArr", "radic", "rang", "raquo", "rarr", "rceil", "rdquo", "real", "reg", "rfloor", "rho", "rlm", "rsaquo", "rsquo", "sbquo", "scaron", "sdot", "sect", "shy", "sigma", "sigmaf", "sim", "spades", "sub", "sube", "sum", "sup1", "sup2", "sup3", "sup", "supe", "szlig", "tau", "there4", "theta", "thetasym", "thinsp", "thorn", "tilde", "times", "trade", "uArr", "uacute", "uarr", "ucirc", "ugrave", "uml", "upsih", "upsilon", "uuml", "weierp", "xi", "yacute", "yen", "yuml", "zeta", "zwj", "zwnj" };
        final char[] entityValues = { '\u00c6', '\u00c1', '\u00c2', '\u00c0', '\u0391', '\u00c5', '\u00c3', '\u00c4', '\u0392', '\u00c7', '\u03a7', '\u2021', '\u0394', '\u00d0', '\u00c9', '\u00ca', '\u00c8', '\u0395', '\u0397', '\u00cb', '\u0393', '\u00cd', '\u00ce', '\u00cc', '\u0399', '\u00cf', '\u039a', '\u039b', '\u039c', '\u00d1', '\u039d', '\u0152', '\u00d3', '\u00d4', '\u00d2', '\u03a9', '\u039f', '\u00d8', '\u00d5', '\u00d6', '\u03a6', '\u03a0', '\u2033', '\u03a8', '\u03a1', '\u0160', '\u03a3', '\u00de', '\u03a4', '\u0398', '\u00da', '\u00db', '\u00d9', '\u03a5', '\u00dc', '\u039e', '\u00dd', '\u0178', '\u0396', '\u00e1', '\u00e2', '´', '\u00e6', '\u00e0', '\u2135', '\u03b1', '&', '\u2227', '\u2220', '\u00e5', '\u2248', '\u00e3', '\u00e4', '\u201e', '\u03b2', '¦', '\u2022', '\u2229', '\u00e7', '¸', '¢', '\u03c7', '\u02c6', '\u2663', '\u2245', '©', '\u21b5', '\u222a', '¤', '\u21d3', '\u2020', '\u2193', '°', '\u03b4', '\u2666', '\u00f7', '\u00e9', '\u00ea', '\u00e8', '\u2205', '\u2003', '\u2002', '\u03b5', '\u2261', '\u03b7', '\u00f0', '\u00eb', '\u20ac', '\u2203', '\u0192', '\u2200', '½', '¼', '¾', '\u2044', '\u03b3', '\u2265', '>', '\u21d4', '\u2194', '\u2665', '\u2026', '\u00ed', '\u00ee', '¡', '\u00ec', '\u2111', '\u221e', '\u222b', '\u03b9', '¿', '\u2208', '\u00ef', '\u03ba', '\u21d0', '\u03bb', '\u2329', '«', '\u2190', '\u2308', '\u201c', '\u2264', '\u230a', '\u2217', '\u25ca', '\u200e', '\u2039', '\u2018', '<', '¯', '\u2014', 'µ', '·', '\u2212', '\u03bc', '\u2207', ' ', '\u2013', '\u2260', '\u220b', '¬', '\u2209', '\u2284', '\u00f1', '\u03bd', '\u00f3', '\u00f4', '\u0153', '\u00f2', '\u203e', '\u03c9', '\u03bf', '\u2295', '\u2228', 'ª', 'º', '\u00f8', '\u00f5', '\u2297', '\u00f6', '¶', '\u2202', '\u2030', '\u22a5', '\u03c6', '\u03c0', '\u03d6', '±', '£', '\u2032', '\u220f', '\u221d', '\u03c8', '\"', '\u21d2', '\u221a', '\u232a', '»', '\u2192', '\u2309', '\u201d', '\u211c', '®', '\u230b', '\u03c1', '\u200f', '\u203a', '\u2019', '\u201a', '\u0161', '\u22c5', '§', '\u00ad', '\u03c3', '\u03c2', '\u223c', '\u2660', '\u2282', '\u2286', '\u2211', '¹', '²', '³', '\u2283', '\u2287', '\u00df', '\u03c4', '\u2234', '\u03b8', '\u03d1', '\u2009', '\u00fe', '\u02dc', '\u00d7', '\u2122', '\u21d1', '\u00fa', '\u2191', '\u00fb', '\u00f9', '¨', '\u03d2', '\u03c5', '\u00fc', '\u2118', '\u03be', '\u00fd', '¥', '\u00ff', '\u03b6', '\u200d', '\u200c' };
        entityToChar = new HashMap<String, Character>(entityKeys.length * 150 / 100);
        for (int i = 0; i < entityKeys.length; ++i) {
            if (!entityKeys[i].equals("nbsp")) {
                DeEntifyStrings.entityToChar.put(entityKeys[i], entityValues[i]);
            }
            DeEntifyStrings.entityToChar.put("apos", '\'');
        }
    }
}
