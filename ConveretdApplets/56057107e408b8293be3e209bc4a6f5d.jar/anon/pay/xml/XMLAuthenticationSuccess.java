// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

public class XMLAuthenticationSuccess
{
    private static final byte[] XML_AUTH_SUCCESS;
    
    public static byte[] getXMLByteArray() {
        return XMLAuthenticationSuccess.XML_AUTH_SUCCESS;
    }
    
    static {
        XML_AUTH_SUCCESS = "<?xml version=\"1.0\" ?><Authentication>Success</Authentication>".getBytes();
    }
}
