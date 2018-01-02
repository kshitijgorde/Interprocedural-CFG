// 
// Decompiled by Procyon v0.5.30
// 

package newstick.content;

import java.net.URL;
import java.io.IOException;
import java.util.StringTokenizer;

public abstract class ContentParserBase implements IContentParser
{
    protected static final String SEPARATOR = "|";
    private StringTokenizer tokenizer;
    private int readCount;
    private int fieldCount;
    
    public abstract void reset();
    
    public void setFieldCount(final int fieldCount) {
        this.fieldCount = fieldCount;
    }
    
    public int getFieldCount() {
        return this.fieldCount;
    }
    
    public void beforeReading() {
        this.readCount = 0;
    }
    
    protected abstract void onField(final String p0, final int p1, final int p2);
    
    public abstract void onIOException(final IOException p0);
    
    public void afterReading() {
    }
    
    protected abstract void onLine(final String p0, final int p1);
    
    public boolean onRead(final String s) {
        this.tokenizer = new StringTokenizer(s, "|");
        int i;
        for (i = 0; i < this.fieldCount && this.tokenizer.hasMoreTokens(); ++i) {
            String nextToken = this.tokenizer.nextToken();
            if (nextToken.trim().length() == 0) {
                nextToken = "";
            }
            this.onField(nextToken, this.readCount, i);
        }
        while (i < this.fieldCount) {
            this.onField(null, this.readCount, i);
            ++i;
        }
        this.onLine(s, this.readCount);
        ++this.readCount;
        return true;
    }
    
    public abstract URL getUrl();
}
