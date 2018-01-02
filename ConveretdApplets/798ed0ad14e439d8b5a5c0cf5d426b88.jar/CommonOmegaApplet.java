import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.io.DataInputStream;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.applet.AppletStub;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CommonOmegaApplet extends JApplet implements AppletStub, WirisAppletInterface
{
    CommonOmegaPanel actionPerformed;
    public Object activeFormula;
    
    public void init() {
        this.actionPerformed = new CommonOmegaPanel(this);
        this.getContentPane().add(this.actionPerformed);
        this.actionPerformed.init();
        this.activeFormula = this.actionPerformed.activeFormula;
    }
    
    public final void start() {
        this.actionPerformed.start();
    }
    
    public final void stop() {
        this.actionPerformed.stop();
    }
    
    public final void destroy() {
        this.actionPerformed.destroy();
        super.destroy();
    }
    
    public final void appletResize(final int n, final int n2) {
    }
    
    public final String getAppletInfo() {
        return this.actionPerformed.getAppletInfo();
    }
    
    public final String[][] getParameterInfo() {
        return this.actionPerformed.getParameterInfo();
    }
    
    public boolean isCalculating() {
        return this.actionPerformed.isCalculating();
    }
    
    public byte[] getImageBytes(final String s) {
        return this.actionPerformed.getImageBytes(s);
    }
    
    public String getXML() {
        return this.actionPerformed.getXML();
    }
    
    public void setXML(final String xml) {
        this.actionPerformed.setXML(xml);
    }
    
    public void setLaTeX(final String laTeX) {
        this.actionPerformed.setLaTeX(laTeX);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.actionPerformed.actionPerformed(actionEvent);
    }
    
    public void setGraphicsCreator(final Class graphicsCreator) {
        this.actionPerformed.setGraphicsCreator(graphicsCreator);
    }
    
    public int[] getCaret() {
        return this.actionPerformed.getCaret();
    }
    
    public void setCaret(final int[] caret) {
        this.actionPerformed.setCaret(caret);
    }
    
    public void clearAllPlotters() {
        this.actionPerformed.clearAllPlotters();
    }
    
    public void setLoadedPlotterPanels(final Vector loadedPlotterPanels) {
        this.actionPerformed.setLoadedPlotterPanels(loadedPlotterPanels);
    }
    
    public void addPlotterFrameListener(final Object o) {
        this.actionPerformed.addPlotterFrameListener(o);
    }
    
    public Component getPlotterPanel(final DataInputStream dataInputStream) {
        return this.actionPerformed.getPlotterPanel(dataInputStream);
    }
    
    public void exchangeProperties(final Hashtable hashtable, final int n) {
        this.actionPerformed.exchangeProperties(hashtable, n);
    }
    
    public void updatePlotterFrame() {
        this.actionPerformed.updatePlotterFrame();
    }
    
    public String getImageBase64(final String s) {
        return this.actionPerformed.getImageBase64(s);
    }
    
    public String getSelection() {
        return this.actionPerformed.getSelection();
    }
    
    public boolean isLoading() {
        return this.actionPerformed.isLoading();
    }
    
    public Object getActiveFormula() {
        return this.actionPerformed.getActiveFormula();
    }
    
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.actionPerformed.getPropertyChangeSupport();
    }
}
