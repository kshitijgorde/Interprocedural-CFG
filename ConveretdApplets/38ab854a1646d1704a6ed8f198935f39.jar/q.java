import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import java.awt.Container;
import javax.swing.JToolBar;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends JToolBar
{
    o do;
    Container a;
    JInternalFrame try;
    Action new;
    Action int;
    Action for;
    Action if;
    
    public q(final o do1, final Container a, final int n, final int n2) {
        super(do1.toString());
        this.new = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Smooth");
                this.putValue("Name", "Smooth");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final o a = l.a(q.this.do, 3);
                final Point location = q.this.getLocation();
                final q q = new q(a, q.this.a, location.x + 30, location.y + 30);
                q.this.a();
            }
        };
        this.int = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Graph");
                this.putValue("Name", "Graph");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                q.this.a(new m(250, q.this.do, Color.red), q.this.do.do());
                q.this.a();
            }
        };
        this.for = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Gauge");
                this.putValue("Name", "Gauge");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                q.this.a(new y(q.this.do, Color.red), q.this.do.do());
                q.this.a();
            }
        };
        this.if = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Label");
                this.putValue("Name", "Label");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                q.this.a(new p(q.this.do), q.this.do.do());
                q.this.a();
            }
        };
        this.do = do1;
        this.a = a;
        this.setFloatable(false);
        this.add(this.int);
        this.add(this.for);
        this.add(this.if);
        this.add(Box.createVerticalStrut(10));
        this.add(this.new);
        this.try = new JInternalFrame("Monitors for " + do1.do(), false, true, false, false);
        this.try.getContentPane().add(this);
        a.add(this.try);
        this.try.pack();
        this.try.setLocation(n, n2);
        this.try.setVisible(true);
    }
    
    public void a() {
        this.try.setVisible(false);
    }
    
    public void a(final Component component, final String toolTipText) {
        final JInternalFrame internalFrame = new JInternalFrame(toolTipText);
        internalFrame.setResizable(true);
        internalFrame.setClosable(true);
        internalFrame.setMaximizable(true);
        internalFrame.setToolTipText(toolTipText);
        internalFrame.getContentPane().add(component);
        this.a.add(internalFrame);
        internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), 1));
        internalFrame.pack();
        internalFrame.setLocation(50, 50);
        internalFrame.setVisible(true);
    }
}
