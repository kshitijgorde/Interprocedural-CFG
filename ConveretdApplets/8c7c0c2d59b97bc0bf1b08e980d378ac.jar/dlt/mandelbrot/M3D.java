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
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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
import javax.swing.JFrame;

public class M3D extends JFrame implements ChangeListener, AdjustmentListener, MouseListener, c, dlt.mandelbrot.a
{
    public d goto;
    g b;
    f for;
    e do;
    private JButton a;
    private JSlider e;
    private JSlider try;
    private JLabel d;
    private JLabel c;
    private JLabel void;
    private JLabel null;
    private JLabel long;
    private JPanel new;
    private JPanel int;
    private JPanel if;
    private JTabbedPane case;
    private JSlider char;
    private JSlider byte;
    private JButton f;
    private JSlider else;
    
    public M3D() {
        this.a();
        (this.goto = new d()).if(this);
        (this.b = new g()).a(this);
        this.if.add(this.b, new a.a.b.a.a(200, 10, 200, 30));
        this.a.addMouseListener(this);
        this.f.addMouseListener(this);
        this.else.addChangeListener(this);
        this.enableEvents(1L);
        (this.for = new f(this)).setBackground(new Color(255, 255, 255));
        this.new.add(this.for, "Center");
        this.setSize(430, 500);
        this.do = new e(this.new.getWidth(), this.new.getHeight(), this);
        this.goto.if(this.do);
        this.do.setBackground(new Color(255, 255, 255));
        this.case.addTab("3D", this.do);
        this.case.setSelectedComponent(this.new);
        this.show();
        this.b.a();
        this.a.setEnabled(false);
        this.doPlot();
    }
    
    private void a() {
        this.case = new JTabbedPane();
        this.int = new JPanel();
        this.byte = new JSlider();
        this.c = new JLabel();
        this.char = new JSlider();
        this.void = new JLabel();
        this.e = new JSlider();
        this.null = new JLabel();
        this.try = new JSlider();
        this.long = new JLabel();
        this.new = new JPanel();
        this.if = new JPanel();
        this.a = new JButton();
        this.else = new JSlider();
        this.d = new JLabel();
        this.f = new JButton();
        this.setTitle("Mandelbrot 3D");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                M3D.this.a(windowEvent);
            }
        });
        this.case.setBackground(new Color(204, 204, 204));
        this.case.setBorder(new EtchedBorder());
        this.int.setLayout(new b());
        this.int.addComponentListener(new ComponentAdapter() {
            public void componentHidden(final ComponentEvent componentEvent) {
                M3D.this.a(componentEvent);
            }
        });
        this.byte.setMajorTickSpacing(1);
        this.byte.setMaximum(10);
        this.byte.setMinimum(1);
        this.byte.setPaintLabels(true);
        this.byte.setPaintTicks(true);
        this.byte.setSnapToTicks(true);
        this.byte.setValue(5);
        this.int.add(this.byte, new a.a.b.a.a(110, 10, 280, 50));
        this.c.setHorizontalAlignment(4);
        this.c.setText("Height Multiplier");
        this.int.add(this.c, new a.a.b.a.a(0, 10, 100, 20));
        this.char.setMajorTickSpacing(100);
        this.char.setMaximum(1000);
        this.char.setMinimum(100);
        this.char.setMinorTickSpacing(50);
        this.char.setPaintLabels(true);
        this.char.setPaintTicks(true);
        this.char.setSnapToTicks(true);
        this.int.add(this.char, new a.a.b.a.a(110, 80, 280, 50));
        this.void.setHorizontalAlignment(4);
        this.void.setText("Height Modulus");
        this.int.add(this.void, new a.a.b.a.a(0, 80, 100, 20));
        this.e.setMajorTickSpacing(10);
        this.e.setMinimum(10);
        this.e.setMinorTickSpacing(5);
        this.e.setPaintLabels(true);
        this.e.setPaintTicks(true);
        this.e.setSnapToTicks(true);
        this.e.setValue(20);
        this.int.add(this.e, new a.a.b.a.a(110, 150, 280, 50));
        this.null.setHorizontalAlignment(4);
        this.null.setText("Cell Size");
        this.int.add(this.null, new a.a.b.a.a(0, 150, 100, 20));
        this.try.setMajorTickSpacing(10);
        this.try.setMinimum(20);
        this.try.setMinorTickSpacing(5);
        this.try.setPaintLabels(true);
        this.try.setPaintTicks(true);
        this.try.setSnapToTicks(true);
        this.try.setValue(60);
        this.int.add(this.try, new a.a.b.a.a(110, 220, 280, 50));
        this.long.setHorizontalAlignment(4);
        this.long.setText("Grid Size");
        this.int.add(this.long, new a.a.b.a.a(0, 220, 100, 20));
        this.case.addTab("Settings", this.int);
        this.new.setLayout(new BorderLayout());
        this.new.setBackground(new Color(0, 0, 0));
        this.new.setForeground(new Color(204, 204, 255));
        this.if.setLayout(new b());
        this.if.setPreferredSize(new Dimension(370, 50));
        this.a.setText("Plot");
        this.a.setMargin(new Insets(2, 2, 2, 2));
        this.if.add(this.a, new a.a.b.a.a(0, 0, 50, 50));
        this.else.setMajorTickSpacing(1);
        this.else.setMaximum(10);
        this.else.setMinimum(1);
        this.else.setPaintTicks(true);
        this.else.setSnapToTicks(true);
        this.else.setValue(1);
        this.else.setBorder(new EtchedBorder());
        this.if.add(this.else, new a.a.b.a.a(100, 0, 90, 50));
        this.if.add(this.d, new a.a.b.a.a(10, 520, 100, 20));
        this.f.setText("Reset");
        this.f.setMargin(new Insets(2, 2, 2, 2));
        this.if.add(this.f, new a.a.b.a.a(50, 0, 50, 50));
        this.new.add(this.if, "North");
        this.case.addTab("Mandelbrot", this.new);
        this.getContentPane().add(this.case, "Center");
        this.pack();
    }
    
    private void a(final ComponentEvent componentEvent) {
        if (this.goto != null) {
            this.goto.else();
        }
    }
    
    private void a(final WindowEvent windowEvent) {
        System.exit(0);
    }
    
    public static void main(final String[] array) {
        new M3D();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.a.setEnabled(true);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.a.setEnabled(true);
    }
    
    public void processComponentEvent(final ComponentEvent componentEvent) {
        switch (componentEvent.getID()) {
            case 101: {
                if (this.for.isVisible()) {
                    this.a.setEnabled(true);
                    this.for.repaint();
                    break;
                }
                break;
            }
            case 100: {}
        }
        super.processComponentEvent(componentEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.a.isEnabled() && mouseEvent.getSource() == this.a) {
            this.a.setEnabled(false);
            this.doPlot();
        }
        else if (mouseEvent.getSource() == this.f) {
            this.a.setEnabled(false);
            this.doReset();
        }
    }
    
    public int getDimension() {
        int n;
        if (this.for.getWidth() > this.for.getHeight()) {
            n = this.for.getHeight();
        }
        else {
            n = this.for.getWidth();
        }
        return n;
    }
    
    public void doMagnify(final int n, final int n2) {
        if (this.for.isVisible() && !this.goto.byte()) {
            this.goto.try();
            this.goto.long();
            this.goto.a(this.for.getWidth(), this.for.getHeight(), n, n2);
            new Thread(new a()).start();
        }
    }
    
    public void doReset() {
        if (this.for.isVisible()) {
            this.goto.try();
            this.goto.long();
            this.goto.a();
            new Thread(new a()).start();
        }
    }
    
    public void doBack() {
        if (this.for.isVisible() && !this.goto.byte()) {
            this.goto.try();
            this.goto.long();
            if (this.goto.case()) {
                new Thread(new a()).start();
            }
        }
    }
    
    public void doPlot() {
        if (this.for.isVisible()) {
            this.goto.try();
            this.goto.long();
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
        this.for.a(this.goto.do(this.getDimension()));
        this.for.repaint();
    }
    
    public void disablePlotButton() {
        this.a.setEnabled(false);
    }
    
    public int getModulus() {
        return this.char.getValue();
    }
    
    public int getMultiplier() {
        return this.byte.getValue();
    }
    
    public int getCellSize() {
        return this.e.getValue();
    }
    
    public int getGridSize() {
        return this.try.getValue();
    }
    
    class a implements Runnable
    {
        public void run() {
            M3D.this.do.E = M3D.this.b.if();
            M3D.this.goto.a(M3D.this.b.if());
            M3D.this.goto.a(M3D.this.else.getValue());
            M3D.this.goto.if(M3D.this.getDimension());
        }
    }
}
