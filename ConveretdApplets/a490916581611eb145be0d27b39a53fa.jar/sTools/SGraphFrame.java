// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.Frame;

public class SGraphFrame extends Frame
{
    BorderLayout borderLayout1;
    SGraph graph;
    
    public SGraphFrame() {
        this.borderLayout1 = new BorderLayout();
        this.graph = new SGraph();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setSize(400, 300);
        this.setLocation((int)(300 * Math.random()), (int)(300 * Math.random()));
        this.addWindowListener(new 1());
        this.setTitle(this.getClass().getName());
    }
    
    public SGraphFrame(final SGraph graph) {
        this.borderLayout1 = new BorderLayout();
        this.graph = new SGraph();
        this.graph = graph;
        this.setLayout(this.borderLayout1);
        this.add(this.graph, "Center");
        this.setSize(300, 200);
        this.setLocation((int)(300 * Math.random()), (int)(300 * Math.random()));
        this.addWindowListener(new 2());
        this.setTitle(this.getClass().getName());
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.graph.setSampleData(false);
        this.add(this.graph, "Center");
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            SGraphFrame.this.setVisible(false);
            SGraphFrame.this.dispose();
        }
    }
    
    class 2 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            SGraphFrame.this.setVisible(false);
            SGraphFrame.this.dispose();
        }
    }
}
