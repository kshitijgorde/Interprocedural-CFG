// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

public class SGraphFrame extends Frame
{
    BorderLayout borderLayout1;
    SGraph graph;
    
    public SGraphFrame() {
        this.borderLayout1 = new BorderLayout();
        this.graph = null;
        this.graph = new SGraph();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setSize(400, 300);
        this.setLocation((int)(300 * Math.random()), (int)(300 * Math.random()));
        this.setTitle(this.getClass().getName());
    }
    
    public SGraphFrame(final SGraph graph) {
        this.borderLayout1 = new BorderLayout();
        this.graph = null;
        this.graph = graph;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setLayout(this.borderLayout1);
        this.add(this.graph, "Center");
        this.setSize(300, 200);
        this.setLocation((int)(300 * Math.random()), (int)(300 * Math.random()));
        this.setTitle(this.getClass().getName());
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.graph.setSampleData(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent windowEvent) {
                SGraphFrame.this.this_windowOpened(windowEvent);
            }
            
            public void windowClosing(final WindowEvent windowEvent) {
                SGraphFrame.this.this_windowClosing(windowEvent);
            }
            
            public void windowActivated(final WindowEvent windowEvent) {
                SGraphFrame.this.this_windowActivated(windowEvent);
            }
            
            public void windowDeiconified(final WindowEvent windowEvent) {
                SGraphFrame.this.this_windowDeiconified(windowEvent);
            }
        });
        this.add(this.graph, "Center");
    }
    
    void this_windowOpened(final WindowEvent windowEvent) {
        if (this.graph != null) {
            this.graph.startPaintThread();
        }
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        if (this.graph != null) {
            this.graph.destroy();
        }
        this.setVisible(false);
    }
    
    void this_windowActivated(final WindowEvent windowEvent) {
        if (this.graph != null) {
            this.graph.startPaintThread();
        }
    }
    
    void this_windowDeiconified(final WindowEvent windowEvent) {
        if (this.graph != null) {
            this.graph.startPaintThread();
        }
    }
}
