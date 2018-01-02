// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.entities;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EntifyStrings
{
    public static String entifyHTML(final String text) {
        return entifyHTMLorXML(text, false);
    }
    
    public static String entifyXML(final String text) {
        return entifyHTMLorXML(text, true);
    }
    
    public static String toHTMLEntity(final char c) {
        final String result = charToHTMLEntity(c);
        return (result == null) ? String.valueOf(c) : result;
    }
    
    public static String toXMLEntity(final char c) {
        final String result = charToXMLEntity(c);
        return (result == null) ? String.valueOf(c) : result;
    }
    
    private static String charToHTMLEntity(final char c) {
        switch (c) {
            default: {
                if (c < '\u007f') {
                    return null;
                }
                return "&#" + Integer.toString(c) + ";";
            }
            case '\"': {
                return "&quot;";
            }
            case '&': {
                return "&amp;";
            }
            case '<': {
                return "&lt;";
            }
            case '>': {
                return "&gt;";
            }
            case ' ': {
                return "&nbsp;";
            }
            case '¡': {
                return "&iexcl;";
            }
            case '¢': {
                return "&cent;";
            }
            case '£': {
                return "&pound;";
            }
            case '¤': {
                return "&curren;";
            }
            case '¥': {
                return "&yen;";
            }
            case '¦': {
                return "&brvbar;";
            }
            case '§': {
                return "&sect;";
            }
            case '¨': {
                return "&uml;";
            }
            case '©': {
                return "&copy;";
            }
            case 'ª': {
                return "&ordf;";
            }
            case '«': {
                return "&laquo;";
            }
            case '¬': {
                return "&not;";
            }
            case '\u00ad': {
                return "&shy;";
            }
            case '®': {
                return "&reg;";
            }
            case '¯': {
                return "&macr;";
            }
            case '°': {
                return "&deg;";
            }
            case '±': {
                return "&plusmn;";
            }
            case '²': {
                return "&sup2;";
            }
            case '³': {
                return "&sup3;";
            }
            case '´': {
                return "&acute;";
            }
            case 'µ': {
                return "&micro;";
            }
            case '¶': {
                return "&para;";
            }
            case '·': {
                return "&middot;";
            }
            case '¸': {
                return "&cedil;";
            }
            case '¹': {
                return "&sup1;";
            }
            case 'º': {
                return "&ordm;";
            }
            case '»': {
                return "&raquo;";
            }
            case '¼': {
                return "&frac14;";
            }
            case '½': {
                return "&frac12;";
            }
            case '¾': {
                return "&frac34;";
            }
            case '¿': {
                return "&iquest;";
            }
            case '\u00c0': {
                return "&Agrave;";
            }
            case '\u00c1': {
                return "&Aacute;";
            }
            case '\u00c2': {
                return "&Acirc;";
            }
            case '\u00c3': {
                return "&Atilde;";
            }
            case '\u00c4': {
                return "&Auml;";
            }
            case '\u00c5': {
                return "&Aring;";
            }
            case '\u00c6': {
                return "&AElig;";
            }
            case '\u00c7': {
                return "&Ccedil;";
            }
            case '\u00c8': {
                return "&Egrave;";
            }
            case '\u00c9': {
                return "&Eacute;";
            }
            case '\u00ca': {
                return "&Ecirc;";
            }
            case '\u00cb': {
                return "&Euml;";
            }
            case '\u00cc': {
                return "&Igrave;";
            }
            case '\u00cd': {
                return "&Iacute;";
            }
            case '\u00ce': {
                return "&Icirc;";
            }
            case '\u00cf': {
                return "&Iuml;";
            }
            case '\u00d0': {
                return "&ETH;";
            }
            case '\u00d1': {
                return "&Ntilde;";
            }
            case '\u00d2': {
                return "&Ograve;";
            }
            case '\u00d3': {
                return "&Oacute;";
            }
            case '\u00d4': {
                return "&Ocirc;";
            }
            case '\u00d5': {
                return "&Otilde;";
            }
            case '\u00d6': {
                return "&Ouml;";
            }
            case '\u00d7': {
                return "&times;";
            }
            case '\u00d8': {
                return "&Oslash;";
            }
            case '\u00d9': {
                return "&Ugrave;";
            }
            case '\u00da': {
                return "&Uacute;";
            }
            case '\u00db': {
                return "&Ucirc;";
            }
            case '\u00dc': {
                return "&Uuml;";
            }
            case '\u00dd': {
                return "&Yacute;";
            }
            case '\u00de': {
                return "&THORN;";
            }
            case '\u00df': {
                return "&szlig;";
            }
            case '\u00e0': {
                return "&agrave;";
            }
            case '\u00e1': {
                return "&aacute;";
            }
            case '\u00e2': {
                return "&acirc;";
            }
            case '\u00e3': {
                return "&atilde;";
            }
            case '\u00e4': {
                return "&auml;";
            }
            case '\u00e5': {
                return "&aring;";
            }
            case '\u00e6': {
                return "&aelig;";
            }
            case '\u00e7': {
                return "&ccedil;";
            }
            case '\u00e8': {
                return "&egrave;";
            }
            case '\u00e9': {
                return "&eacute;";
            }
            case '\u00ea': {
                return "&ecirc;";
            }
            case '\u00eb': {
                return "&euml;";
            }
            case '\u00ec': {
                return "&igrave;";
            }
            case '\u00ed': {
                return "&iacute;";
            }
            case '\u00ee': {
                return "&icirc;";
            }
            case '\u00ef': {
                return "&iuml;";
            }
            case '\u00f0': {
                return "&eth;";
            }
            case '\u00f1': {
                return "&ntilde;";
            }
            case '\u00f2': {
                return "&ograve;";
            }
            case '\u00f3': {
                return "&oacute;";
            }
            case '\u00f4': {
                return "&ocirc;";
            }
            case '\u00f5': {
                return "&otilde;";
            }
            case '\u00f6': {
                return "&ouml;";
            }
            case '\u00f7': {
                return "&divide;";
            }
            case '\u00f8': {
                return "&oslash;";
            }
            case '\u00f9': {
                return "&ugrave;";
            }
            case '\u00fa': {
                return "&uacute;";
            }
            case '\u00fb': {
                return "&ucirc;";
            }
            case '\u00fc': {
                return "&uuml;";
            }
            case '\u00fd': {
                return "&yacute;";
            }
            case '\u00fe': {
                return "&thorn;";
            }
            case '\u00ff': {
                return "&yuml;";
            }
            case '\u0152': {
                return "&OElig;";
            }
            case '\u0153': {
                return "&oelig;";
            }
            case '\u0160': {
                return "&Scaron;";
            }
            case '\u0161': {
                return "&scaron;";
            }
            case '\u0178': {
                return "&Yuml;";
            }
            case '\u0192': {
                return "&fnof;";
            }
            case '\u02c6': {
                return "&circ;";
            }
            case '\u02dc': {
                return "&tilde;";
            }
            case '\u0391': {
                return "&Alpha;";
            }
            case '\u0392': {
                return "&Beta;";
            }
            case '\u0393': {
                return "&Gamma;";
            }
            case '\u0394': {
                return "&Delta;";
            }
            case '\u0395': {
                return "&Epsilon;";
            }
            case '\u0396': {
                return "&Zeta;";
            }
            case '\u0397': {
                return "&Eta;";
            }
            case '\u0398': {
                return "&Theta;";
            }
            case '\u0399': {
                return "&Iota;";
            }
            case '\u039a': {
                return "&Kappa;";
            }
            case '\u039b': {
                return "&Lambda;";
            }
            case '\u039c': {
                return "&Mu;";
            }
            case '\u039d': {
                return "&Nu;";
            }
            case '\u039e': {
                return "&Xi;";
            }
            case '\u039f': {
                return "&Omicron;";
            }
            case '\u03a0': {
                return "&Pi;";
            }
            case '\u03a1': {
                return "&Rho;";
            }
            case '\u03a3': {
                return "&Sigma;";
            }
            case '\u03a4': {
                return "&Tau;";
            }
            case '\u03a5': {
                return "&Upsilon;";
            }
            case '\u03a6': {
                return "&Phi;";
            }
            case '\u03a7': {
                return "&Chi;";
            }
            case '\u03a8': {
                return "&Psi;";
            }
            case '\u03a9': {
                return "&Omega;";
            }
            case '\u03b1': {
                return "&alpha;";
            }
            case '\u03b2': {
                return "&beta;";
            }
            case '\u03b3': {
                return "&gamma;";
            }
            case '\u03b4': {
                return "&delta;";
            }
            case '\u03b5': {
                return "&epsilon;";
            }
            case '\u03b6': {
                return "&zeta;";
            }
            case '\u03b7': {
                return "&eta;";
            }
            case '\u03b8': {
                return "&theta;";
            }
            case '\u03b9': {
                return "&iota;";
            }
            case '\u03ba': {
                return "&kappa;";
            }
            case '\u03bb': {
                return "&lambda;";
            }
            case '\u03bc': {
                return "&mu;";
            }
            case '\u03bd': {
                return "&nu;";
            }
            case '\u03be': {
                return "&xi;";
            }
            case '\u03bf': {
                return "&omicron;";
            }
            case '\u03c0': {
                return "&pi;";
            }
            case '\u03c1': {
                return "&rho;";
            }
            case '\u03c2': {
                return "&sigmaf;";
            }
            case '\u03c3': {
                return "&sigma;";
            }
            case '\u03c4': {
                return "&tau;";
            }
            case '\u03c5': {
                return "&upsilon;";
            }
            case '\u03c6': {
                return "&phi;";
            }
            case '\u03c7': {
                return "&chi;";
            }
            case '\u03c8': {
                return "&psi;";
            }
            case '\u03c9': {
                return "&omega;";
            }
            case '\u03d1': {
                return "&thetasym;";
            }
            case '\u03d2': {
                return "&upsih;";
            }
            case '\u03d6': {
                return "&piv;";
            }
            case '\u2002': {
                return "&ensp;";
            }
            case '\u2003': {
                return "&emsp;";
            }
            case '\u2009': {
                return "&thinsp;";
            }
            case '\u200c': {
                return "&zwnj;";
            }
            case '\u200d': {
                return "&zwj;";
            }
            case '\u200e': {
                return "&lrm;";
            }
            case '\u200f': {
                return "&rlm;";
            }
            case '\u2013': {
                return "&ndash;";
            }
            case '\u2014': {
                return "&mdash;";
            }
            case '\u2018': {
                return "&lsquo;";
            }
            case '\u2019': {
                return "&rsquo;";
            }
            case '\u201a': {
                return "&sbquo;";
            }
            case '\u201c': {
                return "&ldquo;";
            }
            case '\u201d': {
                return "&rdquo;";
            }
            case '\u201e': {
                return "&bdquo;";
            }
            case '\u2020': {
                return "&dagger;";
            }
            case '\u2021': {
                return "&Dagger;";
            }
            case '\u2022': {
                return "&bull;";
            }
            case '\u2026': {
                return "&hellip;";
            }
            case '\u2030': {
                return "&permil;";
            }
            case '\u2032': {
                return "&prime;";
            }
            case '\u2033': {
                return "&Prime;";
            }
            case '\u2039': {
                return "&lsaquo;";
            }
            case '\u203a': {
                return "&rsaquo;";
            }
            case '\u203e': {
                return "&oline;";
            }
            case '\u2044': {
                return "&frasl;";
            }
            case '\u20ac': {
                return "&euro;";
            }
            case '\u2111': {
                return "&image;";
            }
            case '\u2118': {
                return "&weierp;";
            }
            case '\u211c': {
                return "&real;";
            }
            case '\u2122': {
                return "&trade;";
            }
            case '\u2135': {
                return "&alefsym;";
            }
            case '\u2190': {
                return "&larr;";
            }
            case '\u2191': {
                return "&uarr;";
            }
            case '\u2192': {
                return "&rarr;";
            }
            case '\u2193': {
                return "&darr;";
            }
            case '\u2194': {
                return "&harr;";
            }
            case '\u21b5': {
                return "&crarr;";
            }
            case '\u21d0': {
                return "&lArr;";
            }
            case '\u21d1': {
                return "&uArr;";
            }
            case '\u21d2': {
                return "&rArr;";
            }
            case '\u21d3': {
                return "&dArr;";
            }
            case '\u21d4': {
                return "&hArr;";
            }
            case '\u2200': {
                return "&forall;";
            }
            case '\u2202': {
                return "&part;";
            }
            case '\u2203': {
                return "&exist;";
            }
            case '\u2205': {
                return "&empty;";
            }
            case '\u2207': {
                return "&nabla;";
            }
            case '\u2208': {
                return "&isin;";
            }
            case '\u2209': {
                return "&notin;";
            }
            case '\u220b': {
                return "&ni;";
            }
            case '\u220f': {
                return "&prod;";
            }
            case '\u2211': {
                return "&sum;";
            }
            case '\u2212': {
                return "&minus;";
            }
            case '\u2217': {
                return "&lowast;";
            }
            case '\u221a': {
                return "&radic;";
            }
            case '\u221d': {
                return "&prop;";
            }
            case '\u221e': {
                return "&infin;";
            }
            case '\u2220': {
                return "&ang;";
            }
            case '\u2227': {
                return "&and;";
            }
            case '\u2228': {
                return "&or;";
            }
            case '\u2229': {
                return "&cap;";
            }
            case '\u222a': {
                return "&cup;";
            }
            case '\u222b': {
                return "&int;";
            }
            case '\u2234': {
                return "&there4;";
            }
            case '\u223c': {
                return "&sim;";
            }
            case '\u2245': {
                return "&cong;";
            }
            case '\u2248': {
                return "&asymp;";
            }
            case '\u2260': {
                return "&ne;";
            }
            case '\u2261': {
                return "&equiv;";
            }
            case '\u2264': {
                return "&le;";
            }
            case '\u2265': {
                return "&ge;";
            }
            case '\u2282': {
                return "&sub;";
            }
            case '\u2283': {
                return "&sup;";
            }
            case '\u2284': {
                return "&nsub;";
            }
            case '\u2286': {
                return "&sube;";
            }
            case '\u2287': {
                return "&supe;";
            }
            case '\u2295': {
                return "&oplus;";
            }
            case '\u2297': {
                return "&otimes;";
            }
            case '\u22a5': {
                return "&perp;";
            }
            case '\u22c5': {
                return "&sdot;";
            }
            case '\u2308': {
                return "&lceil;";
            }
            case '\u2309': {
                return "&rceil;";
            }
            case '\u230a': {
                return "&lfloor;";
            }
            case '\u230b': {
                return "&rfloor;";
            }
            case '\u2329': {
                return "&lang;";
            }
            case '\u232a': {
                return "&rang;";
            }
            case '\u25ca': {
                return "&loz;";
            }
            case '\u2660': {
                return "&spades;";
            }
            case '\u2663': {
                return "&clubs;";
            }
            case '\u2665': {
                return "&hearts;";
            }
            case '\u2666': {
                return "&diams;";
            }
        }
    }
    
    private static String charToXMLEntity(final char c) {
        switch (c) {
            default: {
                if (c < '\u007f') {
                    return null;
                }
                return "&#" + Integer.toString(c) + ";";
            }
            case '\"': {
                return "&quot;";
            }
            case '&': {
                return "&amp;";
            }
            case '<': {
                return "&lt;";
            }
            case '>': {
                return "&gt;";
            }
            case ' ': {
                return "&nbsp; /* &#x01; nbsp */;";
            }
        }
    }
    
    private static String entifyHTMLorXML(final String text, final boolean xmlStyle) {
        if (text == null) {
            return null;
        }
        final int originalTextLength = text.length();
        final StringBuilder sb = new StringBuilder(originalTextLength * 110 / 100);
        int charsToAppend = 0;
        for (int i = 0; i < originalTextLength; ++i) {
            final char c = text.charAt(i);
            final String entity = xmlStyle ? charToXMLEntity(c) : charToHTMLEntity(c);
            if (entity == null) {
                ++charsToAppend;
            }
            else {
                if (charsToAppend != 0) {
                    sb.append(text.substring(i - charsToAppend, i));
                    charsToAppend = 0;
                }
                sb.append(entity);
            }
        }
        if (charsToAppend != 0) {
            sb.append(text.substring(originalTextLength - charsToAppend, originalTextLength));
        }
        return (sb.length() == originalTextLength) ? text : sb.toString();
    }
}
