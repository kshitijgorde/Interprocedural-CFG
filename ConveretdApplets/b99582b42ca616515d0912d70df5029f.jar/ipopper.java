import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.MediaTracker;
import java.awt.PopupMenu;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ipopper extends Applet implements MouseListener, MouseMotionListener, ActionListener
{
    private boolean pf;
    private String T;
    private Image I_MouseOut;
    private Image I_MouseOver;
    private Image I_MenuActive;
    private int ImageWhich;
    private PopupMenu P;
    private MediaTracker M;
    private Hashtable H;
    private int X;
    private int Y;
    private Color bgColor;
    
    public ipopper() {
        this.pf = false;
        this.ImageWhich = 0;
        this.P = new PopupMenu();
        this.M = new MediaTracker(this);
        this.H = new Hashtable();
        this.bgColor = Color.black;
    }
    
    public void init() {
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
                this.pf = true;
            }
            else {
                this.pf = false;
            }
        }
        final String parameter2 = this.getParameter("BackgroundColor");
        if (parameter2 != null) {
            this.bgColor = this.parseC(parameter2, Color.black);
        }
        this.setBackground(this.bgColor);
        final String parameter3 = this.getParameter("Image0");
        if (parameter3 != null) {
            this.M.addImage(this.I_MouseOut = this.getImage(this.getCodeBase(), parameter3), 0);
        }
        final String parameter4 = this.getParameter("Image1");
        if (parameter4 != null) {
            this.M.addImage(this.I_MouseOver = this.getImage(this.getCodeBase(), parameter4), 1);
        }
        String parameter5 = this.getParameter("Image2");
        if (parameter5 != null) {
            this.M.addImage(this.I_MenuActive = this.getImage(this.getCodeBase(), parameter5), 2);
        }
        if (this.getParameter("Target") == null) {
            this.T = "_top";
        }
        else {
            this.T = this.getParameter("Target");
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.add(this.P);
        if (this.getParameter("PopupX") != null) {
            this.X = new Integer(this.getParameter("PopupX"));
        }
        else {
            this.X = 33;
        }
        if (this.getParameter("PopupY") != null) {
            this.Y = new Integer(this.getParameter("PopupY"));
        }
        else {
            this.Y = 33;
        }
        if (!this.pf) {
            parameter5 = "Error: CodeBrain.com Notice?";
            this.P.add(parameter5);
        }
        int n = 0;
        while (this.getParameter(String.valueOf("Item").concat(String.valueOf(n))) != null) {
            String parameter6 = this.getParameter(String.valueOf("Link").concat(String.valueOf(n)));
            if (parameter6 == null) {
                parameter6 = "999";
            }
            final String parameter7 = this.getParameter(String.valueOf("Item").concat(String.valueOf(n++)));
            this.P.add(parameter7);
            if (!parameter5.equals("-")) {
                this.H.put(parameter7, parameter6);
            }
        }
        this.P.addActionListener(this);
        try {
            this.M.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.I_MouseOut != null && this.ImageWhich == 0) {
            graphics.drawImage(this.I_MouseOut, 0, 0, this);
        }
        if (this.I_MouseOver != null && this.ImageWhich == 1) {
            graphics.drawImage(this.I_MouseOver, 0, 0, this);
        }
        if (this.I_MenuActive != null && this.ImageWhich == 2) {
            graphics.drawImage(this.I_MenuActive, 0, 0, this);
        }
    }
    
    private Color parseC(String trim, final Color color) {
        Color color2;
        try {
            trim = trim.replace('#', ' ').trim();
            color2 = new Color(Integer.valueOf(trim.substring(0, 2), 16), Integer.valueOf(trim.substring(2, 4), 16), Integer.valueOf(trim.substring(4, 6), 16));
        }
        catch (Exception ex) {
            color2 = color;
        }
        return color2;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.ImageWhich = 2;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.P.show(this, this.X, this.Y);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.ImageWhich = 0;
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.ImageWhich = 1;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.ImageWhich = 2;
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        URL url;
        try {
            url = new URL(this.getDocumentBase(), this.H.get(actionEvent.getActionCommand()));
        }
        catch (MalformedURLException ex) {
            return;
        }
        this.getAppletContext().showDocument(url, this.T);
    }
}
