// 
// Decompiled by Procyon v0.5.30
// 

package medusa.applet;

import medusa.graph.Node;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.ToolTipManager;
import javax.swing.JApplet;
import medusa.graph.Graph;
import medusa.MedusaSettings;
import java.awt.Color;
import medusa.display.BasicGraphPanel;

public class MedusaAppletPanel extends BasicGraphPanel
{
    MedusaLite parent;
    private ClickLinker clickLinker;
    private Color basicEdgeColor;
    private Color fontColor;
    
    public void setBackgroundColor(final Color background) {
        this.setBackground(background);
    }
    
    public void setBasicEdgeColor(final Color basicEdgeColor) {
        this.basicEdgeColor = basicEdgeColor;
    }
    
    public void setFontColor(final Color fontColor) {
        this.fontColor = fontColor;
    }
    
    public Color getFontColor() {
        return this.fontColor;
    }
    
    public MedusaAppletPanel(final MedusaSettings stringSettings, final MedusaLite parent, final String s, final String s2) {
        this.basicEdgeColor = Color.gray;
        this.fontColor = Color.black;
        this.stop();
        this.graph = new Graph();
        this.parent = parent;
        this.clickLinker = new ClickLinker(s, s2, parent);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setEdgeLen(100.0);
        this.setStringSettings(stringSettings);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        this.setToolTipText("");
    }
    
    public MedusaAppletPanel(final MedusaSettings medusaSettings, final MedusaLite medusaLite) {
        this(medusaSettings, medusaLite, null, null);
    }
    
    public MedusaAppletPanel() {
        this.basicEdgeColor = Color.gray;
        this.fontColor = Color.black;
        this.setPreferredSize(new Dimension(600, 600));
        this.setOpaque(true);
        this.setStringSettings(new MedusaSettings());
    }
    
    public void start() {
        if (this.relaxThread == null) {
            this.relaxThread = new Thread(this);
        }
        this.relaxThread.start();
    }
    
    public void stop() {
        if (this.relaxThread != null) {
            this.relaxThread = null;
        }
    }
    
    public void handlePressLeftButton(final MouseEvent mouseEvent) {
        if (mouseEvent.isShiftDown() & this.clickLinker.isActive()) {
            final Node closest = this.getClosest(mouseEvent);
            if (closest != null) {
                this.clickLinker.linkOut(closest.getLabel());
            }
            mouseEvent.consume();
            return;
        }
        super.handlePressLeftButton(mouseEvent);
    }
}
