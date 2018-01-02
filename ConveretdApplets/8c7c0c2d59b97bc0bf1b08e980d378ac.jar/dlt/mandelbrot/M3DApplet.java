// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import javax.swing.event.ChangeEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.LayoutManager;
import a.a.b.a.b;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import javax.swing.event.ChangeListener;
import javax.swing.JApplet;

public class M3DApplet extends JApplet implements ChangeListener, AdjustmentListener, MouseListener, c, dlt.mandelbrot.a
{
    public d r;
    g v;
    f j;
    e i;
    private JButton g;
    private JSlider A;
    private JSlider m;
    private JLabel z;
    private JLabel w;
    private JLabel u;
    private JLabel t;
    private JLabel s;
    private JPanel l;
    private JPanel k;
    private JPanel h;
    private JTabbedPane o;
    private JSlider p;
    private JSlider n;
    private JButton B;
    private JSlider q;
    
    public void init() {
        this.if();
        (this.r = new d()).if(this);
        (this.v = new g()).a(this);
        this.h.add(this.v, new a.a.b.a.a(200, 10, 200, 30));
        this.g.addMouseListener(this);
        this.B.addMouseListener(this);
        this.q.addChangeListener(this);
        this.enableEvents(1L);
        (this.j = new f(this)).setBackground(new Color(255, 255, 255));
        this.l.add(this.j, "Center");
        this.setSize(430, 500);
        this.i = new e(this.l.getWidth(), this.l.getHeight(), this);
        this.r.if(this.i);
        this.i.setBackground(new Color(255, 255, 255));
        this.o.addTab("3D", this.i);
        this.o.setSelectedComponent(this.l);
        this.g.setEnabled(false);
        this.doPlot();
    }
    
    private void if() {
        this.o = new JTabbedPane();
        this.k = new JPanel();
        this.n = new JSlider();
        this.w = new JLabel();
        this.p = new JSlider();
        this.u = new JLabel();
        this.A = new JSlider();
        this.t = new JLabel();
        this.m = new JSlider();
        this.s = new JLabel();
        this.l = new JPanel();
        this.h = new JPanel();
        this.g = new JButton();
        this.q = new JSlider();
        this.z = new JLabel();
        this.B = new JButton();
        this.o.setBackground(new Color(204, 204, 204));
        this.o.setBorder(new EtchedBorder());
        this.k.setLayout(new b());
        this.k.addComponentListener(new ComponentAdapter() {
            public void componentHidden(final ComponentEvent componentEvent) {
                M3DApplet.this.if(componentEvent);
            }
        });
        this.n.setMajorTickSpacing(1);
        this.n.setMaximum(10);
        this.n.setMinimum(1);
        this.n.setPaintLabels(true);
        this.n.setPaintTicks(true);
        this.n.setSnapToTicks(true);
        this.n.setValue(5);
        this.k.add(this.n, new a.a.b.a.a(110, 10, 280, 50));
        this.w.setHorizontalAlignment(4);
        this.w.setText("Height Multiplier");
        this.k.add(this.w, new a.a.b.a.a(0, 10, 100, 20));
        this.p.setMajorTickSpacing(100);
        this.p.setMaximum(1000);
        this.p.setMinimum(100);
        this.p.setMinorTickSpacing(50);
        this.p.setPaintLabels(true);
        this.p.setPaintTicks(true);
        this.p.setSnapToTicks(true);
        this.k.add(this.p, new a.a.b.a.a(110, 80, 280, 50));
        this.u.setHorizontalAlignment(4);
        this.u.setText("Height Modulus");
        this.k.add(this.u, new a.a.b.a.a(0, 80, 100, 20));
        this.A.setMajorTickSpacing(10);
        this.A.setMinimum(10);
        this.A.setMinorTickSpacing(5);
        this.A.setPaintLabels(true);
        this.A.setPaintTicks(true);
        this.A.setValue(20);
        this.A.setSnapToTicks(true);
        this.k.add(this.A, new a.a.b.a.a(110, 150, 280, 50));
        this.t.setHorizontalAlignment(4);
        this.t.setText("Cell Size");
        this.k.add(this.t, new a.a.b.a.a(0, 150, 100, 20));
        this.m.setMajorTickSpacing(10);
        this.m.setMinimum(20);
        this.m.setMinorTickSpacing(5);
        this.m.setPaintLabels(true);
        this.m.setPaintTicks(true);
        this.m.setValue(60);
        this.m.setSnapToTicks(true);
        this.k.add(this.m, new a.a.b.a.a(110, 220, 280, 50));
        this.s.setHorizontalAlignment(4);
        this.s.setText("Grid Size");
        this.k.add(this.s, new a.a.b.a.a(0, 220, 100, 20));
        this.o.addTab("Settings", this.k);
        this.l.setLayout(new BorderLayout());
        this.l.setBackground(new Color(0, 0, 0));
        this.l.setForeground(new Color(204, 204, 255));
        this.h.setLayout(new b());
        this.h.setPreferredSize(new Dimension(370, 50));
        this.g.setText("Plot");
        this.g.setMargin(new Insets(2, 2, 2, 2));
        this.h.add(this.g, new a.a.b.a.a(0, 0, 50, 50));
        this.q.setMajorTickSpacing(1);
        this.q.setMaximum(10);
        this.q.setMinimum(1);
        this.q.setPaintTicks(true);
        this.q.setSnapToTicks(true);
        this.q.setValue(1);
        this.q.setBorder(new EtchedBorder());
        this.h.add(this.q, new a.a.b.a.a(100, 0, 90, 50));
        this.h.add(this.z, new a.a.b.a.a(10, 520, 100, 20));
        this.B.setText("Reset");
        this.B.setMargin(new Insets(2, 2, 2, 2));
        this.h.add(this.B, new a.a.b.a.a(50, 0, 50, 50));
        this.l.add(this.h, "North");
        this.o.addTab("Mandelbrot", this.l);
        this.getContentPane().add(this.o, "Center");
    }
    
    private void if(final ComponentEvent componentEvent) {
        if (this.r != null) {
            this.r.else();
        }
    }
    
    public static void main(final String[] array) {
        new M3D();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.g.setEnabled(true);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.g.setEnabled(true);
    }
    
    public void processComponentEvent(final ComponentEvent componentEvent) {
        switch (componentEvent.getID()) {
            case 101: {
                if (this.j.isVisible()) {
                    this.g.setEnabled(true);
                    this.j.repaint();
                    break;
                }
                break;
            }
            case 102: {
                this.v.a();
                break;
            }
        }
        super.processComponentEvent(componentEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.g.isEnabled() && mouseEvent.getSource() == this.g) {
            this.g.setEnabled(false);
            this.doPlot();
        }
        else if (mouseEvent.getSource() == this.B) {
            this.g.setEnabled(false);
            this.doReset();
        }
    }
    
    public int getDimension() {
        int n;
        if (this.j.getWidth() > this.j.getHeight()) {
            n = this.j.getHeight();
        }
        else {
            n = this.j.getWidth();
        }
        return n;
    }
    
    public void doMagnify(final int n, final int n2) {
        if (this.j.isVisible() && !this.r.byte()) {
            this.r.try();
            this.r.long();
            this.r.a(this.j.getWidth(), this.j.getHeight(), n, n2);
            new Thread(new a()).start();
        }
    }
    
    public void doReset() {
        if (this.j.isVisible()) {
            this.r.try();
            this.r.long();
            this.r.a();
            new Thread(new a()).start();
        }
    }
    
    public void doBack() {
        if (this.j.isVisible() && !this.r.byte()) {
            this.r.try();
            this.r.long();
            if (this.r.case()) {
                new Thread(new a()).start();
            }
        }
    }
    
    public void doPlot() {
        if (this.j.isVisible()) {
            this.r.try();
            this.r.long();
            new Thread(new a()).start();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void plotted(final int[][] array) {
        this.j.a(this.r.do(this.getDimension()));
        this.j.repaint();
    }
    
    public void disablePlotButton() {
        this.g.setEnabled(false);
    }
    
    public int getModulus() {
        return this.p.getValue();
    }
    
    public int getMultiplier() {
        return this.n.getValue();
    }
    
    public int getCellSize() {
        return this.A.getValue();
    }
    
    public int getGridSize() {
        return this.m.getValue();
    }
    
    class a implements Runnable
    {
        public void run() {
            M3DApplet.this.i.E = M3DApplet.this.v.if();
            M3DApplet.this.r.a(M3DApplet.this.v.if());
            M3DApplet.this.r.a(M3DApplet.this.q.getValue());
            M3DApplet.this.r.if(M3DApplet.this.getDimension());
        }
    }
}
