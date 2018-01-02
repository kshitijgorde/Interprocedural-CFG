import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnapApplet extends BaseBannerApplet implements ActionListener, MouseListener
{
    private TextField h;
    private Button i;
    private Checkbox j;
    private Checkbox k;
    private CheckboxGroup l;
    private Vector m;
    private Image n;
    private Graphics o;
    private Color p;
    private Color q;
    private Color r;
    private Color s;
    private String t;
    private boolean u;
    private boolean v;
    
    public SnapApplet() {
        this.p = SnapApplet.b;
        this.q = Color.white;
        this.r = Color.black;
        this.s = SnapApplet.a;
        this.v = true;
    }
    
    public void init() {
        boolean booleanValue = true;
        super.init();
        this.setLayout(null);
        try {
            if (this.getParameter("symbol") != null) {
                this.t = this.getParameter("symbol");
            }
            else {
                this.t = "APTD";
            }
            if (this.getParameter("isEnabled") != null) {
                booleanValue = new Boolean(this.getParameter("isEnabled"));
            }
            if (this.getParameter("bgColor") != null) {
                this.p = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fgColor") != null) {
                this.q = Color.decode(this.getParameter("fgColor"));
            }
            if (this.getParameter("frameColor") != null) {
                this.s = Color.decode(this.getParameter("frameColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.r = Color.decode(this.getParameter("fontColor"));
            }
            if (this.getParameter("link") != null) {
                this.v = !this.getParameter("link").equalsIgnoreCase("false");
            }
            if (this.getParameter("showSelector") != null) {
                this.u = !this.getParameter("showSelector").equalsIgnoreCase("false");
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <SNAP> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        if (this.v) {
            this.addMouseListener(this);
        }
        final boolean startsWith = this.t.startsWith("CA;");
        this.l = new CheckboxGroup();
        this.j = new Checkbox("CA", this.l, startsWith);
        this.k = new Checkbox("US", this.l, !startsWith);
        (this.h = new TextField()).setText(this.t);
        this.h.setBounds(this.getSize().width - 140, 5, 75, 20);
        this.h.addActionListener(this);
        (this.i = new Button("Quote")).setBounds(this.getSize().width - 55, 5, 50, 20);
        this.i.addActionListener(this);
        this.j.setForeground(this.p);
        this.j.setBackground(this.s);
        this.j.setFont(new Font("Dialog", 0, 9));
        this.j.setBounds(this.getSize().width - 180, 16, 45, 12);
        this.k.setForeground(this.p);
        this.k.setBackground(this.s);
        this.k.setFont(new Font("Dialog", 0, 9));
        this.k.setBounds(this.getSize().width - 180, 3, 45, 12);
        if (booleanValue) {
            this.add(this.h);
            this.add(this.i);
            if (this.u) {
                this.add(this.j);
                this.add(this.k);
            }
        }
        this.a(new String[] { this.t });
    }
    
    public final synchronized void a() {
        this.m = super.g;
        if (this.n == null) {
            this.n = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.o = this.n.getGraphics()).setColor(this.s);
        this.o.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.o.setColor(this.p);
        this.o.fillRect(5, 32, this.getSize().width - 10, this.getSize().height - 49);
        this.o.setColor(this.s);
        this.o.fillRect(0, 59, this.getSize().width, 1);
        this.o.fillRect(0, 86, this.getSize().width, 1);
        this.o.fillRect(125, 2, 1, this.getSize().height);
        this.o.fillRect(247, 2, 1, this.getSize().height);
        this.o.fillRect(370, 2, 1, this.getSize().height);
        this.o.fillRect(370, 2, 1, this.getSize().height);
        this.o.setFont(new Font("sansserif", 0, 10));
        this.o.setColor(Color.darkGray);
        this.o.drawString("Last Trade", 8, 42);
        this.o.drawString("Change (%)", 128, 42);
        this.o.drawString("Bid", 250, 42);
        this.o.drawString("Ask", 373, 42);
        this.o.drawString("Day's Range", 8, 69);
        this.o.drawString("Previous Close", 128, 69);
        this.o.drawString("Open", 250, 69);
        this.o.drawString("Volume", 373, 69);
        this.o.drawString("52-wk Range", 8, 96);
        this.o.drawString("Earnings/Dividends", 128, 96);
        this.o.drawString("P/E Ratio", 250, 96);
        this.o.drawString("Market Cap.", 373, 96);
        this.o.setColor(this.q);
        this.o.drawString(this.t + " Streaming Snap Quote by AlphaTrade.com", 8, 10);
        this.o.drawString("Quotes delayed at least 15 min.", 8, this.getSize().height - 5);
        this.o.setFont(new Font("sansserif", 0, 12));
        try {
            if (this.m != null && !this.m.isEmpty()) {
                for (int i = 0; i < this.m.size(); ++i) {
                    final String[] array = this.m.elementAt(i);
                    this.o.setColor(this.q);
                    this.o.drawString(array[23] + " : " + array[16], 8, 25);
                    this.o.setColor(this.r);
                    if (array[1].startsWith("-") || array[1].startsWith("+")) {
                        this.o.drawString(array[1].substring(1), 8, 57);
                    }
                    else {
                        this.o.drawString(array[1], 8, 57);
                    }
                    this.o.drawString(array[2] + " (" + array[21] + ")", 128, 57);
                    this.o.drawString(array[3], 250, 57);
                    this.o.drawString(array[4], 373, 57);
                    this.o.drawString(array[7] + " - " + array[6], 8, 84);
                    this.o.drawString(array[9], 128, 84);
                    this.o.drawString(array[5], 250, 84);
                    this.o.drawString(array[8], 373, 84);
                    this.o.drawString(array[13] + " - " + array[14], 8, 111);
                    this.o.drawString(array[12] + " / " + array[10], 128, 111);
                    this.o.drawString(array[11], 250, 111);
                    this.o.drawString(array[18], 373, 111);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <SNAP> - ERROR DRAWING BUFFER: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Cursor cursor = this.getCursor();
        this.setCursor(new Cursor(3));
        try {
            this.t = this.h.getText().toUpperCase();
            this.h.setText(this.t);
            this.a(this.a(this.t));
            this.a();
            this.repaint();
            this.h.selectAll();
        }
        catch (Exception ex) {
            System.err.println("LYNX <SNAP> - ERROR FINDING SYMBOL: " + ex);
        }
        finally {
            this.setCursor(cursor);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", ""), "_blank");
        }
        catch (Exception ex) {
            System.err.println("LYNX <SNAP> - ERROR FINDING URL: " + ex);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.n != null) {
            graphics.drawImage(this.n, 0, 0, null);
        }
        this.h.repaint();
        this.i.repaint();
    }
    
    private String[] a(String s) {
        if (this.u) {
            if (this.l.getSelectedCheckbox() == this.j) {
                if (!s.startsWith("CA;")) {
                    s = "CA;" + s;
                    this.t = s;
                    this.h.setText(s);
                }
            }
            else if (s.startsWith("CA;")) {
                s = s.substring("CA;".length());
                this.t = s;
                this.h.setText(s);
            }
        }
        return new String[] { s };
    }
}
