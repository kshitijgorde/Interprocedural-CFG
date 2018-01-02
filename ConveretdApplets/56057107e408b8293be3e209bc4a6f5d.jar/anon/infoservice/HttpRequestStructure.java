// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;

public final class HttpRequestStructure
{
    public static final int HTTP_COMMAND_GET = 0;
    public static final int HTTP_COMMAND_POST = 1;
    private int m_httpCommand;
    private String m_httpFileName;
    private Document m_postDocument;
    
    private HttpRequestStructure(final int httpCommand, final String httpFileName, final Document postDocument) {
        this.m_httpCommand = httpCommand;
        this.m_httpFileName = httpFileName;
        this.m_postDocument = postDocument;
    }
    
    public static HttpRequestStructure createGetRequest(final String s) {
        return new HttpRequestStructure(0, s, null);
    }
    
    public static HttpRequestStructure createPostRequest(final String s, final Document document) {
        return new HttpRequestStructure(1, s, document);
    }
    
    public int getRequestCommand() {
        return this.m_httpCommand;
    }
    
    public String getRequestFileName() {
        return this.m_httpFileName;
    }
    
    public Document getRequestPostDocument() {
        return this.m_postDocument;
    }
}
