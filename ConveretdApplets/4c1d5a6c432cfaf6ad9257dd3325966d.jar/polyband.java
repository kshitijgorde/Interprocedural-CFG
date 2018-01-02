import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class polyband extends Applet implements MouseListener, ActionListener
{
    BandCanvas bc;
    Button b;
    Vector urls;
    String target;
    int delay;
    
    public void init() {
        final Dimension size = this.getSize();
        this.setLayout(null);
        (this.b = new Button("?")).addActionListener(this);
        this.b.setBounds(size.width - 12, size.height - 12, 12, 12);
        this.add(this.b);
        this.bc = new BandCanvas();
        final String parameter = this.getParameter("delay");
        if (parameter != null) {
            try {
                this.delay = Integer.parseInt(parameter);
            }
            catch (Exception ex2) {}
        }
        this.bc.setDelay(this.delay);
        if (this.getParameter("stretch") != null) {
            this.bc.setStretch(true);
        }
        final String parameter2 = this.getParameter("target");
        if (parameter2 != null) {
            this.target = parameter2;
        }
        final String parameter3 = this.getParameter("file");
        if (parameter3 == null) {
            return;
        }
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(this.getURL(parameter3).openStream())).readLine()) != null) {
                if (line.length() != 0) {
                    final int index = line.indexOf(",");
                    if (index == -1) {
                        this.bc.addImage(this.getImage(this.getCodeBase(), line));
                        this.urls.addElement(null);
                    }
                    else {
                        this.bc.addImage(this.getImage(this.getCodeBase(), line.substring(0, index)));
                        this.urls.addElement(new URL(line.substring(index + 1)));
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Reading file " + (Object)null + ", exception " + ex);
        }
        this.bc.addMorpher(new SquareMorpher());
        this.bc.addMorpher(new ImageTranslater(-1));
        this.bc.addMorpher(new PageOpenLR());
        this.bc.addMorpher(new ImageTranslaterLR(-1));
        this.bc.addMorpher(new SquareMorpher2());
        this.bc.addMorpher(new ImageTranslater(1));
        this.bc.addMorpher(new PageOpen());
        this.bc.addMorpher(new ImageTranslaterLR(1));
        this.bc.setSize(size);
        this.bc.addMouseListener(this);
        this.add(this.bc);
        this.repaint();
        this.bc.start();
    }
    
    public URL getURL(final String s) {
        try {
            if (s.startsWith("http")) {
                return new URL(s);
            }
            return new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {
            System.out.println("getURL " + s + ":" + ex);
            return null;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final URL url = this.urls.elementAt(this.bc.iimg);
        if (url != null) {
            this.getAppletContext().showDocument(url, this.target);
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
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.chez.com/ccaissotti"), "_new");
        }
        catch (Exception ex) {}
    }
    
    public polyband() {
        this.urls = new Vector(10);
        this.target = "_new";
        this.delay = 5;
    }
}
