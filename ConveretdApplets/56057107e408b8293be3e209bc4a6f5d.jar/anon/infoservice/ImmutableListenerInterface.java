// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public interface ImmutableListenerInterface
{
    public static final String PROTOCOL_STR_TYPE_UNKNOWN = "UNKNWON/UNKNOWN";
    public static final int PROTOCOL_TYPE_UNKNOWN = -1;
    public static final String PROTOCOL_STR_TYPE_HTTP = "HTTP/TCP";
    public static final int PROTOCOL_TYPE_HTTP = 1;
    public static final String PROTOCOL_STR_TYPE_HTTPS = "https";
    public static final int PROTOCOL_TYPE_HTTPS = 4;
    public static final String PROTOCOL_STR_TYPE_RAW_UNIX = "RAW/UNIX";
    public static final int PROTOCOL_TYPE_RAW_UNIX = 5;
    public static final String PROTOCOL_STR_TYPE_SOCKS = "socks";
    public static final int PROTOCOL_TYPE_SOCKS = 3;
    public static final String PROTOCOL_STR_TYPE_RAW_TCP = "RAW/TCP";
    public static final int PROTOCOL_TYPE_RAW_TCP = 2;
    
    int getProtocol();
    
    String getHost();
    
    int getPort();
    
    boolean isValid();
}
