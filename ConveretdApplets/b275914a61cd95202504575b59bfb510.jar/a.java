import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

// 
// Decompiled by Procyon v0.5.30
// 

class a
{
    private int b;
    private final /* synthetic */ HorizontalProTrial a;
    
    private void a(final String s) {
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
            for (int i = streamTokenizer.nextToken(); i != 10; i = streamTokenizer.nextToken()) {
                if (i == -1) {
                    break;
                }
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
            final b b = new b(this.a, sval3, HorizontalProTrial.b(this.a), this.b);
            if (sval != null) {
                b.a(sval, sval2);
            }
            HorizontalProTrial.a(this.a).addElement(b);
            HorizontalProTrial.a(this.a, HorizontalProTrial.b(this.a) + b.b(b));
        }
        else if (n == 2 && sval != null) {
            final b b2 = new b(this.a, this.a.b(sval), HorizontalProTrial.b(this.a), this.b);
            HorizontalProTrial.a(this.a).addElement(b2);
            HorizontalProTrial.a(this.a, HorizontalProTrial.b(this.a) + b.b(b2));
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
            final b b = new b(this.a, s, HorizontalProTrial.b(this.a), this.b);
            HorizontalProTrial.a(this.a).addElement(b);
            HorizontalProTrial.a(this.a, HorizontalProTrial.b(this.a) + b.b(b));
        }
    }
    
    a(final HorizontalProTrial a, final int n, final int b, String substring) {
        this.a = a;
        this.b = 0;
        this.b = b;
        HorizontalProTrial.a(a, n);
        while (substring.length() > 0) {
            final String b2 = this.b(substring);
            if (b2 != null) {
                if (substring.indexOf(b2) > 0) {
                    this.c(substring.substring(0, substring.indexOf(b2)));
                }
                this.a(b2);
                substring = substring.substring(substring.indexOf(b2) + b2.length());
            }
            else {
                this.c(substring);
                substring = "";
            }
        }
    }
}
