// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

class bv
{
    private int e;
    private final aE c;
    
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
            final aQ aq = new aQ(this.c, sval3, aE.a(this.c), this.e);
            if (sval != null) {
                aq.a(sval, sval2);
            }
            aE.a(this.c).addElement(aq);
            aE.a(this.c, aE.a(this.c) + aQ.a(aq));
        }
    }
    
    private String b(final String s) {
        if (s.indexOf("<") >= 0 && s.indexOf(">") > 0) {
            return s.substring(s.indexOf("<"), s.indexOf(">") + 1);
        }
        return null;
    }
    
    private void c(final String s) {
        if (s != null) {
            final aQ aq = new aQ(this.c, s, aE.a(this.c), this.e);
            aE.a(this.c).addElement(aq);
            aE.a(this.c, aE.a(this.c) + aQ.a(aq));
        }
    }
    
    bv(final aE c, final int n, final int e, String substring) {
        this.c = c;
        this.e = e;
        aE.a(c, n);
        while (substring.length() > 0) {
            final String b = this.b(substring);
            if (b != null) {
                if (substring.indexOf(b) > 0) {
                    this.c(substring.substring(0, substring.indexOf(b)));
                }
                this.a(b);
                substring = substring.substring(substring.indexOf(b) + b.length());
            }
            else {
                this.c(substring);
                substring = "";
            }
        }
    }
}
