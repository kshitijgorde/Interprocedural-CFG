// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.applet;

import java.awt.Font;
import java.awt.Color;
import org.xidget.layout.Margins;
import javax.swing.JApplet;
import org.xidget.IXidget;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.swing.feature.SwingContainerWidgetFeature;

public class JAppletWidgetFeature extends SwingContainerWidgetFeature implements ITitleFeature
{
    public JAppletWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setDefaultBounds(final float n, final float n2, final float n3, final float n4, final boolean b) {
        super.setDefaultBounds(n, n2, n3, n4, b);
        final JApplet applet = this.xidget.getFeature(JApplet.class);
        applet.setLocation(Math.round(n), Math.round(n2));
        applet.setSize(Math.round(n3), Math.round(n4));
    }
    
    @Override
    public void setTitle(final String name) {
        this.xidget.getFeature(JApplet.class).setName(name);
    }
    
    @Override
    public void setOutsideMargins(final Margins margins) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Margins getOutsideMargins() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setVisible(final boolean b) {
    }
    
    @Override
    public boolean getVisible() {
        return this.xidget.getFeature(JApplet.class).isVisible();
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        this.xidget.getFeature(JApplet.class).setEnabled(enabled);
    }
    
    @Override
    public void setTooltip(final String s) {
    }
    
    @Override
    public void setBackground(final int n) {
        this.xidget.getFeature(JApplet.class).setBackground(new Color(n));
    }
    
    @Override
    public void setForeground(final int n) {
        this.xidget.getFeature(JApplet.class).setForeground(new Color(n));
    }
    
    public void setFont(String string) {
        if (string.contains(",")) {
            final String[] split = string.split("\\s*,\\s*");
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; ++i) {
                if (i > 0) {
                    sb.append('-');
                }
                sb.append(split[i]);
            }
            string = sb.toString();
        }
        final JApplet applet = this.xidget.getFeature(JApplet.class);
        final Font font = applet.getFont();
        applet.setFont(new Font(string, font.getStyle(), font.getSize()));
    }
    
    public void setFontSize(final double n) {
        final JApplet applet = this.xidget.getFeature(JApplet.class);
        applet.setFont(applet.getFont().deriveFont((float)n));
    }
    
    public void setFontStyle(final String s) {
        final JApplet applet = this.xidget.getFeature(JApplet.class);
        final Font font = applet.getFont();
        int n = 0;
        if (s.equals("italic") || s.equals("italics")) {
            n = 2;
        }
        if (s.equals("bold")) {
            n = 1;
        }
        applet.setFont(font.deriveFont(n));
    }
    
    @Override
    public String toString() {
        return this.xidget.toString();
    }
}
