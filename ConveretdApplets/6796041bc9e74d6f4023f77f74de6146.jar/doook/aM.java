// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

class aM
{
    private int at;
    private final cv c;
    
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
            final aL al = new aL(this.c, sval3, cv.a(this.c), this.at);
            if (sval != null) {
                al.a(sval, sval2);
            }
            cv.a(this.c).addElement(al);
            cv.a(this.c, cv.a(this.c) + aL.a(al));
        }
    }
    
    private String d(final String s) {
        if (s.indexOf("<") >= 0 && s.indexOf(">") > 0) {
            return s.substring(s.indexOf("<"), s.indexOf(">") + 1);
        }
        return null;
    }
    
    private void c(final String s) {
        if (s != null) {
            final aL al = new aL(this.c, s, cv.a(this.c), this.at);
            cv.a(this.c).addElement(al);
            cv.a(this.c, cv.a(this.c) + aL.a(al));
        }
    }
    
    aM(final cv c, final int n, final int at, String substring) {
        this.c = c;
        this.at = at;
        cv.a(c, n);
        while (substring.length() > 0) {
            final String d = this.d(substring);
            if (d != null) {
                if (substring.indexOf(d) > 0) {
                    this.c(substring.substring(0, substring.indexOf(d)));
                }
                this.a(d);
                substring = substring.substring(substring.indexOf(d) + d.length());
            }
            else {
                this.c(substring);
                substring = "";
            }
        }
    }
}
