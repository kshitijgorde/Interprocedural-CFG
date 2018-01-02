// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel
{
    private MandelbrotDisplay display;
    private JScrollPane scroller;
    private StatusBar statusBar;
    
    public MandelbrotPanel() {
        this(true);
    }
    
    public MandelbrotPanel(final boolean b) {
        this.setLayout(new BorderLayout(2, 2));
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.display = new MandelbrotDisplay();
        if (this.display.getImageSize() == null) {
            this.add(this.display, "Center");
        }
        else {
            this.add(this.scroller = new JScrollPane(this.display), "Center");
        }
        if (b) {
            this.add(this.statusBar = new StatusBar(this.display), "South");
        }
        this.display.addPropertyChangeListener("mb_property_size", new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                if (propertyChangeEvent.getPropertyName() == "mb_property_size") {
                    final Dimension dimension = (Dimension)propertyChangeEvent.getOldValue();
                    final Dimension dimension2 = (Dimension)propertyChangeEvent.getNewValue();
                    if (dimension2 == null == (dimension == null)) {
                        return;
                    }
                    if (dimension2 == null) {
                        MandelbrotPanel.this.remove(MandelbrotPanel.this.scroller);
                        MandelbrotPanel.this.scroller = null;
                        MandelbrotPanel.this.add(MandelbrotPanel.this.display, "Center");
                        MandelbrotPanel.this.validate();
                    }
                    else {
                        MandelbrotPanel.this.remove(MandelbrotPanel.this.display);
                        MandelbrotPanel.this.scroller = new JScrollPane(MandelbrotPanel.this.display);
                        MandelbrotPanel.this.add(MandelbrotPanel.this.scroller, "Center");
                        MandelbrotPanel.this.validate();
                    }
                }
            }
        });
    }
    
    public StatusBar getStatusBar() {
        return this.statusBar;
    }
    
    public MandelbrotDisplay getDisplay() {
        return this.display;
    }
    
    public void closing() {
        this.display.closing();
    }
}
