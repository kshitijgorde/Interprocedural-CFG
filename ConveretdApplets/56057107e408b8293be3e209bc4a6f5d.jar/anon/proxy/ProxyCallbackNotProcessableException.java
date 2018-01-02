// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

public class ProxyCallbackNotProcessableException extends Exception
{
    public ProxyCallbackNotProcessableException() {
    }
    
    public ProxyCallbackNotProcessableException(final String s) {
        super(s);
    }
    
    public byte[] getErrorResponse() {
        return this.getMessage().getBytes();
    }
}
