// 
// Decompiled by Procyon v0.5.30
// 

package medusa.conflabelapplet;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.ToolTipManager;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JApplet;
import medusa.graph.Graph;
import medusa.MedusaSettings;
import medusa.applet.ClickLinker;
import medusa.display.SamplePanel;

public class ConfLabelAppletPanel extends SamplePanel
{
    ClickLinker clickLinker;
    
    public ConfLabelAppletPanel(final MedusaSettings ms, final ConfLabelApplet confLabelApplet, final String s, final String s2) {
        super(ms);
        this.graph = new Graph();
        this.clickLinker = new ClickLinker(s, s2, confLabelApplet);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setEdgeLen(100.0);
        this.addMouseListener(this);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        this.setToolTipText("");
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame();
        final ConfLabelAppletPanel confLabelAppletPanel = new ConfLabelAppletPanel(new MedusaSettings(), null, null, null);
        final Graph graph = new Graph();
        graph.defaultGraph();
        confLabelAppletPanel.setGraph(graph);
        frame.getContentPane().add(confLabelAppletPanel);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
