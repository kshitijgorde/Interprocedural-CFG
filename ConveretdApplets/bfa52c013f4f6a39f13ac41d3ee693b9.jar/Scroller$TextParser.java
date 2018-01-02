import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

// 
// Decompiled by Procyon v0.5.30
// 

class Scroller$TextParser
{
    private final Scroller \u00d0;
    
    Scroller$TextParser(final Scroller scroller, final int n, String substring) {
        this.\u00d0 = scroller;
        Scroller.\u00c3(this.\u00d0 = scroller, n);
        while (substring.length() > 0) {
            final String \u00f6 = this.\u00d6(substring);
            if (\u00f6 != null) {
                this.\u00d9(substring.substring(0, substring.indexOf(\u00f6)));
                this.\u00d8(\u00f6);
                substring = substring.substring(substring.indexOf(\u00f6) + \u00f6.length());
            }
            else {
                this.\u00d9(substring);
                substring = "";
            }
        }
    }
    
    private String \u00d6(final String s) {
        if (s.indexOf("<") > 0 && s.indexOf(">") > 0) {
            return s.substring(s.indexOf("<"), s.indexOf(">") + 1);
        }
        return null;
    }
    
    private void \u00d8(final String s) {
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
            final Scroller$Section scroller$Section = new Scroller$Section(this.\u00d0, sval3, Scroller.\u00c2(this.\u00d0));
            if (sval != null) {
                scroller$Section.setAsLink(sval, sval2);
            }
            Scroller.\u00c4(this.\u00d0).addElement(scroller$Section);
            Scroller.\u00c3(this.\u00d0, Scroller.\u00c2(this.\u00d0) + Scroller$Section.\u00c3(scroller$Section));
            return;
        }
        if (n == 2 && sval != null) {
            final Scroller$Section scroller$Section2 = new Scroller$Section(this.\u00d0, this.\u00d0.loadImage(sval), Scroller.\u00c2(this.\u00d0));
            Scroller.\u00c4(this.\u00d0).addElement(scroller$Section2);
            Scroller.\u00c3(this.\u00d0, Scroller.\u00c2(this.\u00d0) + Scroller$Section.\u00c3(scroller$Section2));
        }
    }
    
    private void \u00d9(final String s) {
        if (s != null) {
            final Scroller$Section scroller$Section = new Scroller$Section(this.\u00d0, s, Scroller.\u00c2(this.\u00d0));
            Scroller.\u00c4(this.\u00d0).addElement(scroller$Section);
            Scroller.\u00c3(this.\u00d0, Scroller.\u00c2(this.\u00d0) + Scroller$Section.\u00c3(scroller$Section));
        }
    }
}
