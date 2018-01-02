import java.util.Hashtable;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class JMessage implements Serializable
{
    String body;
    String htmlBody;
    String rawHeaders;
    Hashtable headers;
    String uidl;
    int numAtts;
    String[] attNames;
    String[] attUrls;
    int[] attSize;
    boolean[] attIsMessage;
    
    public void setBody(final String body) {
        this.body = body;
    }
    
    public void setHtmlBody(final String htmlBody) {
        this.htmlBody = htmlBody;
    }
    
    public void setRawHeaders(final String rawHeaders) {
        this.rawHeaders = rawHeaders;
    }
    
    public void setHeaders(final Hashtable headers) {
        this.headers = headers;
    }
    
    public void setNumAtts(final int numAtts) {
        this.numAtts = numAtts;
    }
    
    public void setAttNames(final String[] attNames) {
        this.attNames = attNames;
    }
    
    public void setAttUrls(final String[] attUrls) {
        this.attUrls = attUrls;
    }
    
    public void setAttSizes(final int[] attSize) {
        this.attSize = attSize;
    }
    
    public void setUidl(final String uidl) {
        this.uidl = uidl;
    }
    
    public void setAttIsMessage(final boolean[] attIsMessage) {
        this.attIsMessage = attIsMessage;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public String getHtmlBody() {
        return this.htmlBody;
    }
    
    public String getRawHeaders() {
        return this.rawHeaders;
    }
    
    public String getMessageText() {
        return this.body;
    }
    
    public Hashtable getHeaders() {
        return this.headers;
    }
    
    public String getHeaderValue(final String s) {
        return this.headers.get(s);
    }
    
    public String getUidl() {
        return this.uidl;
    }
    
    int getNumAtts() {
        return this.numAtts;
    }
    
    String[] getAttNames() {
        return this.attNames;
    }
    
    String getAttName(final int n) {
        return this.attNames[n];
    }
    
    String getAttUrl(final int n) {
        return this.attUrls[n];
    }
    
    String[] getAttUrls() {
        return this.attUrls;
    }
    
    int[] getAttSizes() {
        return this.attSize;
    }
    
    int getAttSize(final int n) {
        return this.attSize[n];
    }
    
    boolean getAttIsMessage(final int n) {
        return this.attIsMessage[n];
    }
    
    boolean[] getAttIsMessage() {
        return this.attIsMessage;
    }
}
