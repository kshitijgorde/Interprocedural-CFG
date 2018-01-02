import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Point;
import javax.swing.JInternalFrame;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Container;
import javax.swing.JToolBar;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends JToolBar
{
    i new;
    Container if;
    l byte;
    Action char;
    Action int;
    Action case;
    Action try;
    Action do;
    Action a;
    Action for;
    
    public v(final i new1, final Container if1, final int n, final int n2) {
        super(new1.toString());
        this.char = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Visit Table");
                this.putValue("Name", "Visits");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final JScrollPane scrollPane = new JScrollPane(new d(v.this.new));
                scrollPane.setPreferredSize(new Dimension(500, 100));
                v.this.a(scrollPane, "Visits for " + v.this.new.a());
            }
        };
        this.int = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Visitor Table");
                this.putValue("Name", "Visitors");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final JScrollPane scrollPane = new JScrollPane(new ad(v.this.new));
                scrollPane.setPreferredSize(new Dimension(500, 100));
                v.this.a(scrollPane, "Visitors for " + v.this.new.a());
            }
        };
        this.case = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Visited Table");
                this.putValue("Name", "Visited");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final JScrollPane scrollPane = new JScrollPane(new f(v.this.new));
                scrollPane.setPreferredSize(new Dimension(500, 100));
                v.this.a(scrollPane, "Visited for " + v.this.new.a());
            }
        };
        this.try = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Filter Toolbar");
                this.putValue("Name", "Filter");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                h.a(v.this.if, v.this);
            }
        };
        this.do = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Bandwidth Monitor");
                this.putValue("Name", "Bandwidth");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                new q(v.this.byte.a(1), v.this.if, 30, 30);
            }
        };
        this.a = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add Hits Monitor");
                this.putValue("Name", "Hits");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                new q(v.this.byte.a(0), v.this.if, 30, 30);
            }
        };
        this.for = new AbstractAction() {
            {
                this.putValue("ShortDescription", "Add HitRate Monitor");
                this.putValue("Name", "HitRate");
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                new q(v.this.byte.a(2), v.this.if, 30, 30);
            }
        };
        this.new = new1;
        this.byte = new l(new1);
        this.if = if1;
        this.setFloatable(false);
        this.add(this.char);
        this.add(this.int);
        this.add(this.case);
        this.add(Box.createRigidArea(new Dimension(20, 20)));
        this.add(this.do);
        this.add(this.a);
        this.add(this.for);
        this.add(Box.createRigidArea(new Dimension(20, 20)));
        this.add(this.try);
        final JInternalFrame internalFrame = new JInternalFrame("Logs for " + new1.a(), false, true, false, false);
        internalFrame.getContentPane().add(this);
        if1.add(internalFrame);
        internalFrame.pack();
        internalFrame.setLocation(n, n2);
        internalFrame.setVisible(true);
    }
    
    public void a(final g g) {
        final i.b b = new i.b(g, this.new);
        final Point location = this.getLocation();
        final v v = new v(b, this.if, location.x + 30, location.y + 30);
    }
    
    public void a(final Component component, final String toolTipText) {
        final JInternalFrame internalFrame = new JInternalFrame(toolTipText);
        internalFrame.setResizable(true);
        internalFrame.setClosable(true);
        internalFrame.setMaximizable(true);
        internalFrame.setToolTipText(toolTipText);
        internalFrame.getContentPane().add(component);
        this.if.add(internalFrame);
        internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), 1));
        internalFrame.pack();
        internalFrame.setLocation(50, 50);
        internalFrame.setVisible(true);
    }
}
