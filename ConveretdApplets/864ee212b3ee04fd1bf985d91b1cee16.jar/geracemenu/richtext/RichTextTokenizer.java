// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Color;
import geracemenu.util.Utilities;
import java.util.Enumeration;

public class RichTextTokenizer implements Enumeration
{
    protected TextStyle defaultTextStyle;
    protected TLStreamTokenizer streamTokenizer;
    
    public void finalize() {
        this.streamTokenizer = null;
    }
    
    public boolean hasMoreTokens() {
        return this.streamTokenizer.hasMoreTokens();
    }
    
    public Text nextToken() {
        String nextToken = this.streamTokenizer.nextToken();
        switch (this.streamTokenizer.ttype()) {
            case 0: {
                final TLTagParser tlTagParser = new TLTagParser(new TLTagTokenizer(nextToken));
                tlTagParser.parseTag();
                if (tlTagParser.ttype() == 0) {
                    this.defaultTextStyle = tlTagParser.generateTextStyle();
                    break;
                }
                return tlTagParser.generateRichTextToken();
            }
            case 2: {
                switch (nextToken.charAt(0)) {
                    case 'n': {
                        nextToken = "\n";
                        break;
                    }
                    case 'r': {
                        nextToken = "\r";
                        break;
                    }
                    case 't': {
                        nextToken = "\t";
                        break;
                    }
                }
                return new Text(nextToken, this.defaultTextStyle);
            }
            case 1: {
                return new Text(nextToken, this.defaultTextStyle);
            }
        }
        return null;
    }
    
    public boolean hasMoreElements() {
        return this.hasMoreTokens();
    }
    
    public Object nextElement() {
        return this.nextToken();
    }
    
    public RichTextTokenizer(final String s) {
        this.defaultTextStyle = new TextStyle(Utilities.getSystemFont("Helvetica"), 0, 12, Color.black, false, Color.lightGray, 2);
        this.streamTokenizer = new TLStreamTokenizer(s);
        TLTagParser.flushTextStyleProperties();
    }
}
