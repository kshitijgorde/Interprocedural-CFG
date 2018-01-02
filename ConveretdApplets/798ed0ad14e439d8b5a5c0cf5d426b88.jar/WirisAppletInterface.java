import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.awt.Component;
import java.io.DataInputStream;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

// 
// Decompiled by Procyon v0.5.30
// 

public interface WirisAppletInterface
{
    boolean isCalculating();
    
    byte[] getImageBytes(final String p0) throws Exception;
    
    String getXML();
    
    void setXML(final String p0);
    
    void setLaTeX(final String p0);
    
    void addPropertyChangeListener(final PropertyChangeListener p0);
    
    void removePropertyChangeListener(final PropertyChangeListener p0);
    
    void actionPerformed(final ActionEvent p0);
    
    void setGraphicsCreator(final Class p0);
    
    int[] getCaret();
    
    void setCaret(final int[] p0);
    
    void clearAllPlotters();
    
    void setLoadedPlotterPanels(final Vector p0);
    
    void addPlotterFrameListener(final Object p0);
    
    Component getPlotterPanel(final DataInputStream p0);
    
    void exchangeProperties(final Hashtable p0, final int p1);
    
    void updatePlotterFrame();
    
    String getImageBase64(final String p0) throws Exception;
    
    String getSelection();
    
    boolean isLoading();
    
    Object getActiveFormula();
    
    PropertyChangeSupport getPropertyChangeSupport();
}
