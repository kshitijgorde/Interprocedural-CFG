import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AStockChart extends Applet implements ActionListener
{
    GridBagConstraints c;
    MonoValueChart vc;
    StockDisplayer sd;
    MonoValueChart vv;
    VolumeDisplayer vd;
    Panel p;
    Button b;
    public tipProducer tp;
    
    public void init() {
        Font font = this.getFont();
        this.setLayout(new GridBagLayout());
        final String parameter = this.getParameter("type");
        if (parameter != null && parameter.toLowerCase().equals("c")) {
            this.sd = new CandleDisplayer();
        }
        else {
            this.sd = new ChartDisplayer();
        }
        final String parameter2 = this.getParameter("fontname");
        if (parameter2 != null) {
            font = new Font(parameter2, font.getStyle(), font.getSize());
        }
        final String parameter3 = this.getParameter("fontsize");
        try {
            if (parameter3 != null) {
                font = new Font(font.getName(), font.getStyle(), Integer.parseInt(parameter3));
            }
        }
        catch (Exception ex) {
            System.out.println("fontsize :" + ex);
        }
        final String parameter4 = this.getParameter("values");
        if (parameter4 != null) {
            this.sd.fromString(parameter4, true);
        }
        final String parameter5 = this.getParameter("file");
        if (parameter5 != null) {
            try {
                this.sd.fromReader(new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), parameter5).openStream())), true);
            }
            catch (Exception ex2) {
                System.out.println("Reading file " + parameter5 + ", exception " + ex2);
            }
        }
        this.c.fill = 1;
        this.c.weightx = 1.0;
        this.c.gridwidth = 0;
        this.c.weighty = 1.0;
        this.add(new Label("Values"), this.c);
        (this.vc = new MonoValueChart()).setNbPoint(10);
        this.vc.setMin(0);
        this.vc.setBorder(8);
        this.vc.setFont(font);
        (this.vv = new MonoValueChart()).setNbPoint(10);
        this.vv.setBorder(8);
        this.vv.setFont(font);
        final String parameter6 = this.getParameter("nbpoint");
        if (parameter6 != null) {
            try {
                final int intValue = new Integer(parameter6);
                this.vc.setNbPoint(intValue);
                this.vv.setNbPoint(intValue);
            }
            catch (Exception ex3) {
                System.out.println("nbpoint [" + parameter6 + "] :" + ex3);
            }
        }
        this.vc.setMVCDisplayer(this.sd);
        this.c.weighty = 5.0;
        this.add(this.vc, this.c);
        this.c.weighty = 1.0;
        this.add(new Label("Volume"), this.c);
        (this.vd = new VolumeDisplayer()).fromStockDisplayer(this.sd);
        this.vv.setLinkMode(1);
        this.vv.setMin(0);
        this.vv.setMVCDisplayer(this.vd);
        this.c.weighty = 2.0;
        this.add(this.vv, this.c);
        final String parameter7 = this.getParameter("img");
        try {
            if (parameter7 != null) {
                this.vc.setBackImage(this.getImage(this.getDocumentBase(), parameter7));
                this.vv.setBackImage(this.getImage(this.getDocumentBase(), parameter7));
            }
        }
        catch (Exception ex4) {
            System.out.println("Load image : " + ex4);
        }
        (this.p = new Panel()).setLayout(new FlowLayout());
        (this.b = new Button("<")).setActionCommand("<");
        this.b.addActionListener(this);
        this.p.add(this.b);
        (this.b = new Button(">")).setActionCommand(">");
        this.b.addActionListener(this);
        this.p.add(this.b);
        (this.b = new Button("None")).setActionCommand("n");
        this.b.addActionListener(this);
        this.p.add(this.b);
        (this.b = new Button("Line")).setActionCommand("l");
        this.b.addActionListener(this);
        this.p.add(this.b);
        (this.b = new Button("Fill")).setActionCommand("f");
        this.b.addActionListener(this);
        this.p.add(this.b);
        (this.b = new Button("?")).setActionCommand("?");
        this.b.addActionListener(this);
        this.p.add(this.b);
        this.c.weighty = 1.0;
        this.add(this.p, this.c);
        this.doLayout();
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand().charAt(0)) {
            case '<': {
                this.vc.moveLeft(1);
                this.vv.moveLeft(1);
            }
            case '>': {
                this.vc.moveRight(1);
                this.vv.moveRight(1);
            }
            case 'n': {
                this.vc.setLinkMode(0);
            }
            case 'l': {
                this.vc.setLinkMode(3);
            }
            case 'f': {
                this.vc.setLinkMode(2);
            }
            case '?': {
                try {
                    this.getAppletContext().showDocument(new URL("http://www.chez.com/ccaissotti/java/stockcha.htm"), "_top");
                    return;
                }
                catch (Exception ex) {
                    return;
                }
                break;
            }
        }
    }
    
    public AStockChart() {
        this.c = new GridBagConstraints();
    }
}
