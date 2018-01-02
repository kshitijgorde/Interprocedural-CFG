import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

// 
// Decompiled by Procyon v0.5.30
// 

class a
{
    private final /* synthetic */ HorizontalPro a;
    private int b;
    
    private void a(final String s) {
        if (s != null) {
            final b b = new b(this.a, s, HorizontalPro.b(this.a), this.b);
            HorizontalPro.a(this.a).addElement(b);
            HorizontalPro.a(this.a, HorizontalPro.b(this.a) + b.c(b));
        }
    }
    
    private void b(final String s) {
        int n = 0;
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
            for (int n2 = streamTokenizer.nextToken(); n2 != 10 && n2 != -1; n2 = streamTokenizer.nextToken()) {
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
                        n = 1;
                    }
                    else if (sval4.equalsIgnoreCase("img")) {
                        n = 2;
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
        if (n == 1 && sval3 != null) {
            final b b = new b(this.a, sval3, HorizontalPro.b(this.a), this.b);
            if (sval != null) {
                b.a(sval, sval2);
            }
            HorizontalPro.a(this.a).addElement(b);
            HorizontalPro.a(this.a, HorizontalPro.b(this.a) + b.c(b));
        }
        else if (n == 2 && sval != null) {
            final b b2 = new b(this.a, this.a.b(sval), HorizontalPro.b(this.a), this.b);
            HorizontalPro.a(this.a).addElement(b2);
            HorizontalPro.a(this.a, HorizontalPro.b(this.a) + b.c(b2));
        }
    }
    
    private String c(final String s) {
        if (s.indexOf("<") >= 0 && s.indexOf(">") > 0) {
            return s.substring(s.indexOf("<"), s.indexOf(">") + 1);
        }
        return null;
    }
    
    a(final HorizontalPro a, final int n, final int b, String substring) {
        this.a = a;
        this.b = 0;
        this.b = b;
        HorizontalPro.a(a, n);
        while (substring.length() > 0) {
            final String c = this.c(substring);
            if (c != null) {
                if (substring.indexOf(c) > 0) {
                    this.a(substring.substring(0, substring.indexOf(c)));
                }
                this.b(c);
                substring = substring.substring(substring.indexOf(c) + c.length());
            }
            else {
                this.a(substring);
                substring = "";
            }
        }
    }
}
