import java.awt.Insets;
import java.awt.Component;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class colorpicker extends Applet implements MouseListener, MouseMotionListener, AdjustmentListener
{
    CanvasHS chs;
    CanvasB cb;
    TextField tf_hc;
    Scrollbar sb_r;
    Scrollbar sb_g;
    Scrollbar sb_b;
    Label l_r;
    Label l_g;
    Label l_b;
    Canvas cc;
    String StrJSvar;
    
    private void processEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof CanvasHS) {
            this.cb.refresh(this.chs.getColorAt(mouseEvent.getX(), mouseEvent.getY()));
            this.cb.repaint();
            final Color color = this.cb.getColor();
            this.sb_r.setValue(color.getRed());
            this.sb_g.setValue(color.getGreen());
            this.sb_b.setValue(color.getBlue());
            this.updateText(color);
            this.cc.setBackground(color);
        }
        if (mouseEvent.getComponent() instanceof CanvasB) {
            final Color color2 = this.cb.getColorAt(mouseEvent.getX(), mouseEvent.getY());
            this.sb_r.setValue(color2.getRed());
            this.sb_g.setValue(color2.getGreen());
            this.sb_b.setValue(color2.getBlue());
            this.updateText(color2);
            this.cb.setCursor(mouseEvent.getY());
            this.cb.repaint();
            this.cc.setBackground(color2);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.processEvent(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.processEvent(mouseEvent);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Color background = new Color(this.sb_r.getValue(), this.sb_g.getValue(), this.sb_b.getValue());
        this.updateText(background);
        this.cb.setCursor(this.cb.size().height / 2);
        this.cb.refresh(background);
        this.cc.setBackground(background);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public colorpicker() {
        this.chs = new CanvasHS();
        this.cb = new CanvasB();
        this.tf_hc = new TextField("#808080", 6);
        this.sb_r = new Scrollbar(0, 127, 20, 0, 275);
        this.sb_g = new Scrollbar(0, 127, 20, 0, 275);
        this.sb_b = new Scrollbar(0, 127, 20, 0, 275);
        this.l_r = new Label("127", 2);
        this.l_g = new Label("127", 2);
        this.l_b = new Label("127", 2);
        this.cc = new Canvas();
        this.StrJSvar = "color";
    }
    
    public final void updateText(final Color color) {
        final String string = "000000" + Integer.toHexString(color.getRGB() & 0xFFFFFF).toUpperCase();
        final String string2 = "#" + string.substring(string.length() - 6);
        this.tf_hc.setText(string2);
        this.l_r.setText(Integer.toString(color.getRed()));
        this.l_g.setText(Integer.toString(color.getGreen()));
        this.l_b.setText(Integer.toString(color.getBlue()));
        try {
            JSObject.getWindow((Applet)this).setMember(this.StrJSvar, (Object)string2);
        }
        catch (JSException ex) {
            System.out.println(((Throwable)ex).toString());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus("Visit NavSurf.com at http://navsurf.com for more freeware applets");
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private void retrieveParameters() {
        if (this.getParameter("variable") != null) {
            this.StrJSvar = this.getParameter("variable");
        }
    }
    
    public void init() {
        this.retrieveParameters();
        this.setLayout(new BorderLayout());
        this.setFont(new Font("Arial", 1, 12));
        this.setBackground(Color.white);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel(new FlowLayout(1, 20, 20));
        final Panel panel2 = new Panel(new FlowLayout(1, 10, 0));
        final Panel panel3 = new Panel(gridBagLayout);
        final Canvas canvas = new Canvas();
        final Canvas canvas2 = new Canvas();
        final Canvas canvas3 = new Canvas();
        canvas.resize(20, 20);
        canvas.setBackground(Color.red);
        canvas2.resize(20, 20);
        canvas2.setBackground(Color.green);
        canvas3.resize(20, 20);
        canvas3.setBackground(Color.blue);
        this.chs.addMouseMotionListener(this);
        this.chs.addMouseListener(this);
        this.cb.addMouseMotionListener(this);
        this.cb.addMouseListener(this);
        panel.add(this.chs);
        panel.add(this.cb);
        this.cc.resize(50, 50);
        this.cc.setBackground(Color.gray);
        this.sb_r.addAdjustmentListener(this);
        this.sb_g.addAdjustmentListener(this);
        this.sb_b.addAdjustmentListener(this);
        this.add(panel, "North");
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        panel3.add(this.l_r, gridBagConstraints);
        panel3.add(canvas, gridBagConstraints);
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.gridwidth = 0;
        panel3.add(this.sb_r, gridBagConstraints);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridwidth = 1;
        panel3.add(this.l_g, gridBagConstraints);
        panel3.add(canvas2, gridBagConstraints);
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.gridwidth = 0;
        panel3.add(this.sb_g, gridBagConstraints);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridwidth = 1;
        panel3.add(this.l_b, gridBagConstraints);
        panel3.add(canvas3, gridBagConstraints);
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.gridwidth = 0;
        panel3.add(this.sb_b, gridBagConstraints);
        panel2.add(new Label("Hex Code"));
        panel2.add(this.tf_hc);
        panel2.add(this.cc);
        this.add(panel3, "Center");
        this.add(panel2, "South");
    }
}
