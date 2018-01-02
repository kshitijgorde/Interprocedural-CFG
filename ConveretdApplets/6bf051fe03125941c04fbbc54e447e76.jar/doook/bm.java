// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

class bm
{
    private int aw;
    private final aH c;
    
    private void a(final String s) {
        boolean b = false;
        String sval = null;
        String sval2 = null;
        String sval3 = null;
        StreamTokenizer streamTokenizer = null;
        try {
            streamTokenizer = new StreamTokenizer(new StringReader(s));
        }
        catch (Exception ex) {
            System.err.println("Error [2]: " + ex);
            System.exit(1);
        }
        streamTokenizer.quoteChar(39);
        try {
            for (int n = streamTokenizer.nextToken(); n != 10 && n != -1; n = streamTokenizer.nextToken()) {
                final String sval4 = streamTokenizer.sval;
                if (sval4 != null) {
                    if (sval4.equalsIgnoreCase("href") || sval4.equalsIgnoreCase("src")) {
                        if (streamTokenizer.nextToken() == 61) {
                            streamTokenizer.nextToken();
                            sval = streamTokenizer.sval;
                        }
                    }
                    else if (sval4.equalsIgnoreCase("target")) {
                        if (streamTokenizer.nextToken() == 61) {
                            streamTokenizer.nextToken();
                            sval2 = streamTokenizer.sval;
                        }
                    }
                    else if (sval4.equalsIgnoreCase("a")) {
                        b = true;
                    }
                    else if (sval4.equalsIgnoreCase("value") && streamTokenizer.nextToken() == 61) {
                        streamTokenizer.nextToken();
                        sval3 = streamTokenizer.sval;
                    }
                }
            }
        }
        catch (IOException ex2) {
            System.err.println("Error [3]: " + ex2);
        }
        if (b && sval3 != null) {
            final bl bl = new bl(this.c, sval3, aH.a(this.c), this.aw);
            if (sval != null) {
                bl.a(sval, sval2);
            }
            aH.a(this.c).addElement(bl);
            aH.a(this.c, aH.a(this.c) + doook.bl.a(bl));
        }
    }
    
    private String e(final String s) {
        if (s.indexOf("<") >= 0 && s.indexOf(">") > 0) {
            return s.substring(s.indexOf("<"), s.indexOf(">") + 1);
        }
        return null;
    }
    
    private void e(final String s) {
        if (s != null) {
            final bl bl = new bl(this.c, s, aH.a(this.c), this.aw);
            aH.a(this.c).addElement(bl);
            aH.a(this.c, aH.a(this.c) + doook.bl.a(bl));
        }
    }
    
    bm(final aH c, final int n, final int aw, String substring) {
        this.c = c;
        this.aw = aw;
        aH.a(c, n);
        while (substring.length() > 0) {
            final String e = this.e(substring);
            if (e != null) {
                if (substring.indexOf(e) > 0) {
                    this.e(substring.substring(0, substring.indexOf(e)));
                }
                this.a(e);
                substring = substring.substring(substring.indexOf(e) + e.length());
            }
            else {
                this.e(substring);
                substring = "";
            }
        }
    }
}
