import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Controller extends Panel implements ActionListener, AdjustmentListener
{
    private Scrollbar sizer;
    private Label sizerLbl;
    private int arg;
    public Canvas c;
    public palCanvas pal;
    private WindCanvas c2;
    
    public Controller() {
        (this.sizer = new Scrollbar(0, 35, 0, 1, 50)).setBlockIncrement(2);
        this.sizer.addAdjustmentListener(this);
        this.sizerLbl = new Label("SIZE: 35");
        final Panel panel = new Panel();
        panel.add(this.sizerLbl);
        panel.add(this.sizer);
        panel.setLayout(new GridLayout(2, 2));
        this.setLayout(new GridLayout(2, 2));
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        (this.pal = new palCanvas()).setSize(100, 60);
        (this.c = new Canvas()).setBackground(new Color(0, 100, 254));
        (this.c2 = new WindCanvas()).setBackground(Color.white);
        this.c2.setSize(60, 60);
        panel2.add(this.pal);
        panel2.add(this.c2);
        panel2.add(panel);
        this.addButton(panel3, "REPLACE COLOR IN PALLETE");
        this.add(panel2);
        this.add(panel3);
        this.pal.addMouseListener(new palListener());
        this.pal.addMouseMotionListener(new palMoveListener());
    }
    
    public void refresh() {
        this.c.setBackground(ColoringBook.col);
        this.c.repaint();
        this.c2.repaint();
        this.setBackground(ColoringBook.bgColor);
    }
    
    public void paint(final Graphics graphics) {
        this.c.setBackground(ColoringBook.col);
        this.c.repaint();
    }
    
    public void addButton(final Container container, final String s) {
        final Button button = new Button(s);
        container.add(button);
        button.addActionListener(this);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final int value = this.sizer.getValue();
        this.sizerLbl.setText("SIZE: " + value);
        ColoringBook.penSize = value;
        this.c.setBackground(ColoringBook.col);
        this.c.repaint();
        this.c2.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        actionEvent.getActionCommand();
        ColoringBook.setPallet();
        this.repaint();
    }
}
