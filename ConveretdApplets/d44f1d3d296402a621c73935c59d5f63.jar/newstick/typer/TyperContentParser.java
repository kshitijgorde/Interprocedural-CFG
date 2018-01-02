// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import newstick.content.ContentParserBase;

public class TyperContentParser extends ContentParserBase
{
    private Vector texts;
    private IFilledContentAsync filledAsync;
    private URL url;
    public String errorMessage;
    private String[] news;
    
    public void reset() {
        this.texts.removeAllElements();
    }
    
    public TyperContentParser(final IFilledContentAsync filledAsync, final URL url) {
        this.errorMessage = "error";
        this.filledAsync = filledAsync;
        this.url = url;
        this.setFieldCount(3);
        this.texts = new Vector();
    }
    
    protected void onField(final String s, final int n, final int n2) {
        if (this.news == null) {
            this.news = new String[this.getFieldCount()];
        }
        this.news[n2] = s;
    }
    
    public void onIOException(final IOException ex) {
        this.texts.addElement(new String[] { null, this.errorMessage, null });
        this.filledAsync.endFillContent(this.url, this.texts);
    }
    
    public void afterReading() {
        this.filledAsync.endFillContent(this.url, this.texts);
    }
    
    protected void onLine(final String s, final int n) {
        this.texts.addElement(this.news);
        this.news = null;
    }
    
    public URL getUrl() {
        return this.url;
    }
}
