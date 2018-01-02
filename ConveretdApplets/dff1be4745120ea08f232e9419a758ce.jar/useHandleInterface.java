import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public interface useHandleInterface
{
    void setSymbol(final String p0);
    
    String getCookie();
    
    void setCookie(final String p0);
    
    void getQuoteFromServer();
    
    Vector getDataVector();
    
    void dorepainting();
    
    void openLookup();
    
    void reloaddata();
    
    void showSymbolDetail(final String p0);
    
    void showBrowser(final String p0, final String p1);
}
