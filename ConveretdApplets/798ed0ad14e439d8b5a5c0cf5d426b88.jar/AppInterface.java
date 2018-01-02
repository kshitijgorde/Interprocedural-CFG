import java.net.URL;
import java.awt.Frame;
import java.io.InputStream;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public interface AppInterface extends ActionListener, PropertyChangeListener
{
    void showDocumentNewWindow(final String p0);
    
    void showDocument(final String p0);
    
    Font dialog_font();
    
    String getStringProperty(final String p0, final String p1);
    
    void receiveAndShowDocument(final InputStream p0);
    
    Frame getMainFrame();
    
    URL getDocumentBase();
    
    ResourceProvider getResourceProvider();
}
