// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.event.ActionEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Image;
import javax.swing.JEditorPane;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class EgnskortOversigt_DTK25 extends JPanel implements ActionListener, MouseMotionListener
{
    static final long serialVersionUID = 0L;
    private JEditorPane mapUsage;
    private Image image;
    Point upper;
    Point lower;
    Point point;
    
    public EgnskortOversigt_DTK25(final String servicename) {
        this.point = new Point();
        this.setLayout(new FlowLayout(2));
        System.out.println("EgnskortOversigt_DTK25's servicename is " + servicename);
        try {
            final URL mapUrl = new URL(String.valueOf(Constant.imageURL) + servicename + ".png");
            (this.image = ImageIO.read(mapUrl)).flush();
        }
        catch (MalformedURLException e) {
            System.err.println("URL location to image is incorrect");
        }
        catch (IOException e2) {
            System.err.println("Error in reading the image.");
        }
        (this.mapUsage = this.createEditorPane(CResourceManager.instance().getLocale())).setContentType("text/html; charset=ISO-8859-1");
        this.mapUsage.setPreferredSize(new Dimension(300, 600));
        this.mapUsage.setBackground(new Color(238, 238, 238));
        this.mapUsage.setEditable(false);
        this.mapUsage.setFocusable(false);
        this.addMouseMotionListener(this);
        this.add(this.mapUsage);
    }
    
    private JEditorPane createEditorPane(final String locale) {
        final JEditorPane editorPane = new JEditorPane();
        URL helpURL = null;
        editorPane.setEditable(false);
        try {
            helpURL = new URL(String.valueOf(Constant.base) + "/java/" + locale + "_egnskort25.html");
        }
        catch (MalformedURLException e) {
            System.err.println("createEditorPane threw an exception: " + e.getMessage());
        }
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            }
            catch (IOException e2) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        }
        else {
            System.err.println("Couldn't find file: " + locale + "_egnskort25.html");
        }
        return editorPane;
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.point.x = e.getX();
        this.point.y = e.getY();
        final Egn egn = EgnskortCoordinates.detectRectangularRegion(this.point);
        if (egn != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.drawBox(egn.getUpperPoint(), egn.getLowerPoint());
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void drawBox(final Point upper, final Point lower) {
        this.upper = upper;
        this.lower = lower;
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, 516, 600, this);
        }
        if (this.upper != null && this.lower != null) {
            g.setColor(Color.BLACK);
            ((Graphics2D)g).setStroke(new BasicStroke(2.0f));
            final int width = this.lower.x - this.upper.x;
            final int height = this.lower.y - this.upper.y;
            g.drawRect(this.upper.x, this.upper.y, width, height);
        }
    }
    
    public void actionPerformed(final ActionEvent arg0) {
        if (Constant.debugMode) {
            System.out.println("Entered actionPerformed in EgnskortOversigt_DTK25");
        }
    }
}
