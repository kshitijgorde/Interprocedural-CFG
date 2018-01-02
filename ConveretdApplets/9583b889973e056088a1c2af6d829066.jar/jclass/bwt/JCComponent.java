// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCEnvironment;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import jclass.util.JCUtilConverter;
import jclass.util.JCFile;
import java.awt.Component;
import java.applet.Applet;
import jclass.util.JCConverter;
import jclass.base.TransientComponent;

public abstract class JCComponent extends TransientComponent
{
    public static final String version;
    public static final String about = "JClass BWT by KL Group (www.klg.com)";
    static JCConverter conv;
    
    public JCComponent() {
        this(null, null);
    }
    
    public JCComponent(final Applet applet, final String name) {
        super.applet = applet;
        this.setName(name);
        if (this.getClass().getName().equals("jclass.bwt.JCComponent")) {
            this.getParameters(applet);
        }
    }
    
    public String getAbout() {
        return "JClass BWT by KL Group (www.klg.com)";
    }
    
    public void setAbout(final String s) {
    }
    
    public String getVersion() {
        return JCComponent.version;
    }
    
    public void setVersion(final String s) {
    }
    
    String getParam(final String s) {
        return JCComponent.conv.getParam(super.applet, this, this.getName(), s);
    }
    
    protected void getParameters() {
        ComponentConverter.getParams(this);
    }
    
    public void getParameters(final Applet applet) {
        this.getParameters(applet, null);
    }
    
    public void getParameters(final Applet applet, String param) {
        super.applet = applet;
        if (param == null) {
            param = this.getParam("paramFile");
        }
        if (param != null) {
            JCUtilConverter.setParamSource(this, JCFile.read(applet, param));
        }
        if (param != null || this.getAppletContext() != null) {
            this.getParameters();
            if (this.getPeer() != null) {
                this.addNotify();
                this.repaint();
            }
        }
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public static void setConverter(final JCConverter conv) {
        JCComponent.conv = conv;
    }
    
    public static JCConverter getConverter() {
        return JCComponent.conv;
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
    }
    
    public int getShadowThickness() {
        return this.getBorderThickness();
    }
    
    public void setHighlightThickness(final int highlightThickness) {
        super.setHighlightThickness(highlightThickness);
    }
    
    public void setHighlightColor(final Color highlightColor) {
        super.setHighlightColor(highlightColor);
    }
    
    public void setInsets(final Insets insets) {
        super.setInsets(insets);
    }
    
    public void setCursor(final int n) {
        BWTUtil.setCursor(this, n);
    }
    
    public void setDoubleBuffer(final boolean doubleBuffer) {
        super.setDoubleBuffer(doubleBuffer);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        TransientComponent.mouse_down_event_timestamp = event.when;
        if (event.when - TransientComponent.popdown_event_timestamp < 50L && JCEnvironment.getOS() != 1) {
            return true;
        }
        if (BWTUtil.getMouseButton(event) == 1 && this.isFocusTraversable()) {
            this.requestFocus();
        }
        return false;
    }
    
    public void requestFocus() {
        super.requestFocus();
        if (this.isFocusTraversable()) {
            this.handleEvent(new Event(this, 1004, null));
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.key == 1004 || event.key == 1006) {
            Focus.previousFocus(this);
        }
        else if (event.key == 1005 || event.key == 1007) {
            Focus.nextFocus(this);
        }
        return false;
    }
    
    static {
        version = JCVersion.getVersionString();
        JCComponent.conv = new JCConverter();
    }
}
