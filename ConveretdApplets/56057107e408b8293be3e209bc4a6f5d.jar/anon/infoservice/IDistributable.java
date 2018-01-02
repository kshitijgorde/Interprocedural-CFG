// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public interface IDistributable
{
    public static final String FIELD_HTTP_REQUEST_STRING = "HTTP_REQUEST_STRING";
    public static final String FIELD_HTTP_SERIALS_REQUEST_STRING = "HTTP_SERIALS_REQUEST_STRING";
    
    String getId();
    
    String getPostFile();
    
    int getPostEncoding();
    
    byte[] getPostData();
}
