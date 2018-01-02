// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class Language
{
    private static Language m_lan;
    private int[] m_charorder;
    private boolean m_bEnglish;
    private String m_sBiggestChar;
    private String[] m_aStopWords;
    private String[] m_aStems;
    private Hashtable m_hUToC;
    
    private String getBiggestCh() {
        if (this.m_sBiggestChar == null) {
            if (this.m_charorder == null) {
                this.m_sBiggestChar = String.valueOf('\u00ff');
            }
            else {
                int n = 0;
                int n2 = 0;
                do {
                    if (this.m_charorder[n2] > n) {
                        n = this.m_charorder[n2];
                    }
                } while (++n2 <= 255);
                this.m_sBiggestChar = String.valueOf((char)n);
            }
        }
        return this.m_sBiggestChar;
    }
    
    private boolean isStopW(final String s) {
        if (this.m_aStopWords == null) {
            final Vector stopWords = ResourceLib.getStopWords();
            if (stopWords != null) {
                this.m_aStopWords = new String[stopWords.size()];
                int n = 0;
                final Enumeration<String> elements = stopWords.elements();
                while (elements.hasMoreElements()) {
                    this.m_aStopWords[n++] = elements.nextElement();
                }
            }
        }
        if (this.m_aStopWords != null) {
            final int length = this.m_aStopWords.length;
            int i = 0;
            int n2 = length - 1;
            boolean b = false;
            while (i <= n2) {
                final int n3 = i + n2 >> 1;
                final String s2 = this.m_aStopWords[n3];
                if (compare(s, s2) > 0) {
                    i = ((i == n3) ? (n3 + 1) : n3);
                }
                else {
                    if (compare(s, s2) >= 0) {
                        b = true;
                        break;
                    }
                    n2 = ((n2 == n3) ? (n3 - 1) : n3);
                }
            }
            return b;
        }
        return false;
    }
    
    private Language() {
        this.m_bEnglish = true;
        this.m_sBiggestChar = null;
        this.m_hUToC = new Hashtable();
        final String getRes = ResourceLib.GetRes("langorder");
        if (ResourceLib.GetRes("English").toLowerCase().compareTo("true") == 0) {
            this.m_bEnglish = true;
        }
        else {
            this.m_bEnglish = false;
        }
        if (getRes != null && getRes.length() > 0) {
            this.m_charorder = new int[256];
            int n = 0;
            int i = getRes.indexOf(44, n);
            int n2 = 0;
            while (i != -1) {
                final String substring = getRes.substring(n, i);
                if (substring != null) {
                    this.m_charorder[n2] = Integer.parseInt(substring);
                    ++n2;
                }
                n = i + 1;
                i = getRes.indexOf(44, n);
            }
        }
        this.m_hUToC.put(new Integer(8364), new Integer(128));
        this.m_hUToC.put(new Integer(8218), new Integer(130));
        this.m_hUToC.put(new Integer(402), new Integer(131));
        this.m_hUToC.put(new Integer(8222), new Integer(132));
        this.m_hUToC.put(new Integer(8230), new Integer(133));
        this.m_hUToC.put(new Integer(8224), new Integer(134));
        this.m_hUToC.put(new Integer(8225), new Integer(135));
        this.m_hUToC.put(new Integer(710), new Integer(136));
        this.m_hUToC.put(new Integer(8240), new Integer(137));
        this.m_hUToC.put(new Integer(352), new Integer(138));
        this.m_hUToC.put(new Integer(8249), new Integer(139));
        this.m_hUToC.put(new Integer(338), new Integer(140));
        this.m_hUToC.put(new Integer(381), new Integer(142));
        this.m_hUToC.put(new Integer(8216), new Integer(145));
        this.m_hUToC.put(new Integer(8217), new Integer(146));
        this.m_hUToC.put(new Integer(8220), new Integer(147));
        this.m_hUToC.put(new Integer(8221), new Integer(148));
        this.m_hUToC.put(new Integer(8226), new Integer(149));
        this.m_hUToC.put(new Integer(8211), new Integer(150));
        this.m_hUToC.put(new Integer(8212), new Integer(151));
        this.m_hUToC.put(new Integer(732), new Integer(152));
        this.m_hUToC.put(new Integer(8482), new Integer(153));
        this.m_hUToC.put(new Integer(353), new Integer(154));
        this.m_hUToC.put(new Integer(8250), new Integer(155));
        this.m_hUToC.put(new Integer(339), new Integer(156));
        this.m_hUToC.put(new Integer(382), new Integer(158));
        this.m_hUToC.put(new Integer(376), new Integer(159));
    }
    
    public static String getStem(final String s) {
        if (Language.m_lan == null) {
            Language.m_lan = new Language();
        }
        return Language.m_lan.getStemString(s);
    }
    
    private int compareString(final String s, final String s2) {
        try {
            if (this.m_charorder == null) {
                return s.toLowerCase().compareTo(s2.toLowerCase());
            }
            for (int n = 0; n < s.length() && n < s2.length(); ++n) {
                if (this.m_charorder[this.getCharCode(s, n)] < this.m_charorder[this.getCharCode(s2, n)]) {
                    return -1;
                }
                if (this.m_charorder[this.getCharCode(s, n)] > this.m_charorder[this.getCharCode(s2, n)]) {
                    return 1;
                }
            }
            if (s.length() < s2.length()) {
                return -1;
            }
            if (s.length() > s2.length()) {
                return 1;
            }
            return 0;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    private String getStemString(String lowerCase) {
        if (this.m_aStems == null) {
            final Vector stems = ResourceLib.getStems();
            if (stems != null) {
                this.m_aStems = new String[stems.size()];
                int n = 0;
                final Enumeration<String> elements = stems.elements();
                while (elements.hasMoreElements()) {
                    this.m_aStems[n++] = elements.nextElement();
                }
            }
        }
        if (this.m_aStems == null) {
            return lowerCase;
        }
        final int length = this.m_aStems.length;
        lowerCase = lowerCase.toLowerCase();
        for (int i = 0; i < length; ++i) {
            if (this.m_aStems[i].length() < lowerCase.length() - 1) {
                final int lastIndex = lowerCase.lastIndexOf(this.m_aStems[i]);
                if (lastIndex > 0 && lowerCase.substring(lastIndex).compareTo(this.m_aStems[i]) == 0) {
                    final String s = lowerCase;
                    String s2;
                    if (lastIndex >= 2 && lowerCase.charAt(lastIndex - 2) == lowerCase.charAt(lastIndex - 1)) {
                        s2 = s.substring(0, lastIndex - 1);
                    }
                    else {
                        s2 = s.substring(0, lastIndex);
                    }
                    return s2;
                }
            }
        }
        return lowerCase;
    }
    
    public static String getBiggestChar() {
        if (Language.m_lan == null) {
            Language.m_lan = new Language();
        }
        return Language.m_lan.getBiggestCh();
    }
    
    static {
        Language.m_lan = null;
    }
    
    public static int compare(final String s, final String s2) {
        if (Language.m_lan == null) {
            Language.m_lan = new Language();
        }
        return Language.m_lan.compareString(s, s2);
    }
    
    private int getCharCode(final String s, final int n) {
        int n2 = s.charAt(n);
        if (n2 >= 256) {
            n2 = (int)this.m_hUToC.get(new Integer(n2));
        }
        return n2;
    }
    
    public static boolean isStopWord(final String s) {
        if (Language.m_lan == null) {
            Language.m_lan = new Language();
        }
        return Language.m_lan.isStopW(s);
    }
}
