import java.awt.image.ImageObserver;
import java.util.Random;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BIText extends Applet implements Runnable, MouseListener
{
    String text;
    Color fgColor;
    Color black;
    int speed;
    int denseness;
    Font defaultFont;
    FontMetrics fm;
    Thread runner;
    Image workspace;
    Graphics offscreen;
    Button vButton;
    
    public BIText() {
        this.fgColor = Color.green;
        this.black = Color.black;
        this.speed = 40;
        this.denseness = 40;
        this.vButton = new Button("applet by virtig01");
    }
    
    public void init() {
        this.showStatus("BIText loading ...");
        this.addMouseListener(this);
        this.vButton.setForeground(this.fgColor);
        this.vButton.setBackground(this.black);
        this.add(this.vButton);
        final String parameter = this.getParameter("text");
        this.text = ((parameter != null) ? parameter : "virtig01");
        final String parameter2 = this.getParameter("beamcolor");
        if (parameter2 != null) {
            this.fgColor = new Color(Integer.parseInt(parameter2, 16));
        }
        final String parameter3 = this.getParameter("density");
        if (parameter3 != null) {
            this.denseness = Integer.parseInt(parameter3);
        }
        this.defaultFont = new Font("Helvetica", 1, 35);
        this.fm = this.getFontMetrics(this.defaultFont);
        this.workspace = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.workspace.getGraphics();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.showStatus("");
        try {
            this.getAppletContext().showDocument(new URL("http://applets.virtig01.cjb.net/BIText/index.html"), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.runner = null;
        this.vButton.setVisible(true);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.start();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.offscreen.setColor(this.black);
        this.offscreen.fillRect(0, 0, width, height);
        this.offscreen.setColor(this.fgColor);
        final int n = width / 2 - this.fm.stringWidth(this.text) / 2;
        final int n2 = height / 2 + this.fm.getHeight() / 4;
        final Random random = new Random();
        final float[] array = new float[this.denseness];
        for (int i = 0; i < this.denseness; ++i) {
            array[i] = random.nextFloat();
        }
        for (int j = 0; j < this.denseness; ++j) {
            this.offscreen.drawLine(width / 2, height - 2, (int)(array[j] * width), 20);
        }
        this.offscreen.setColor(this.black);
        this.offscreen.setFont(this.defaultFont);
        this.offscreen.drawString(this.text, n, n2);
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        this.vButton.setVisible(false);
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
