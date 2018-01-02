import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mindreader extends Applet implements Runnable, ActionListener
{
    private Thread m_Loadcard;
    private Graphics m_Graphics;
    private Image[] m_Images;
    private int m_CurrImage;
    private int m_ImgWidth;
    private int m_ImgHeight;
    private boolean m_AllLoaded;
    private final int NUM_IMAGES = 7;
    private int m_Answer;
    private int m_Guess;
    private Font m_f;
    private Font m_g;
    private int[] m_Add;
    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;
    Button button1;
    Button button2;
    Button button3;
    
    public void stop() {
        if (this.m_Loadcard != null) {
            this.m_Loadcard.stop();
            this.m_Loadcard = null;
        }
    }
    
    public Mindreader() {
        this.m_Loadcard = null;
        this.m_CurrImage = 0;
        this.m_ImgWidth = 0;
        this.m_ImgHeight = 0;
        this.m_AllLoaded = false;
        this.m_Answer = 0;
        this.m_Guess = 0;
        this.m_f = new Font("Arial", 1, 22);
        this.m_g = new Font("Arial", 0, 12);
        this.m_Add = new int[] { 1, 2, 4, 8, 16, 32 };
        this.button1 = new Button("YES");
        this.button2 = new Button("NO");
        this.button3 = new Button("NEW GAME");
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_AllLoaded) {
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics.clearRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            this.displayImage(graphics);
        }
        else {
            graphics.drawString("Loading card images...", 68, 80);
        }
        graphics.setFont(this.m_f);
        graphics.setColor(Color.black);
        graphics.fillRect(12, 8, 235, 28);
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(8, 4, 235, 28, true);
        graphics.setColor(Color.black);
        graphics.drawString("M I N D    R E A D E R", 18, 26);
        graphics.setFont(this.m_g);
        graphics.setColor(Color.black);
        if (this.m_CurrImage < 1) {
            graphics.drawString("Think of a number between 1 and 63.", 15, 155);
            graphics.drawString("A set of six cards will be displayed above.", 15, 170);
            graphics.drawString("After the last card your chosen number", 15, 185);
            graphics.drawString("is revealed.", 15, 200);
        }
        if (this.m_CurrImage == 6) {
            graphics.setFont(this.m_f);
            graphics.setColor(Color.red);
            graphics.drawString(String.valueOf(this.m_Answer), 120, 120);
            graphics.setFont(this.m_g);
            graphics.setColor(Color.black);
            graphics.drawString("Click on NEW GAME...", 15, 280);
            this.button1.setVisible(false);
            this.button2.setVisible(false);
        }
        else {
            graphics.drawString("Is your number shown on card " + String.valueOf(this.m_CurrImage + 1) + " above?", 15, 225);
            graphics.drawString("Click on YES or NO...", 15, 240);
        }
        if (this.m_CurrImage == 6 && this.m_Answer == 0) {
            graphics.setFont(this.m_f);
            graphics.setColor(Color.red);
            graphics.drawString("Gotcha!", 90, 140);
            this.button1.setVisible(false);
            this.button2.setVisible(false);
        }
    }
    
    public void destroy() {
    }
    
    private void displayImage(final Graphics graphics) {
        if (!this.m_AllLoaded) {
            return;
        }
        graphics.drawImage(this.m_Images[this.m_CurrImage], (this.getSize().width - this.m_ImgWidth) / 2, (this.getSize().height - this.m_ImgHeight) / 5, null);
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.offScreenImage == null || size.width != this.offScreenSize.width || size.height != this.offScreenSize.height) {
            this.offScreenImage = this.createImage(size.width, size.height);
            this.offScreenSize = size;
            this.offScreenGraphics = this.offScreenImage.getGraphics();
        }
        this.offScreenGraphics.clearRect(0, 0, size.width, size.height);
        this.paint(this.offScreenGraphics);
        graphics.drawImage(this.offScreenImage, 0, 0, null);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.button1) {
            this.m_Answer += this.m_Add[this.m_Guess];
            ++this.m_CurrImage;
            ++this.m_Guess;
            this.displayImage(this.m_Graphics);
            this.repaint();
        }
        if (source == this.button2) {
            ++this.m_CurrImage;
            ++this.m_Guess;
            this.displayImage(this.m_Graphics);
            this.repaint();
        }
        if (source == this.button3) {
            this.m_CurrImage = 0;
            this.m_Answer = 0;
            this.m_Guess = 0;
            this.button1.setVisible(true);
            this.button2.setVisible(true);
            this.displayImage(this.m_Graphics);
            this.repaint();
        }
    }
    
    public void start() {
        if (this.m_Loadcard == null) {
            (this.m_Loadcard = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: Mind Reader\r\nAuthor: George W Jopling\r\nCopyright© 1998-1999 George W Jopling\r\nCreated with Microsoft Visual J++ Version 6.0";
    }
    
    public void run() {
        this.m_CurrImage = 0;
        if (!this.m_AllLoaded) {
            this.repaint();
            this.m_Graphics = this.getGraphics();
            this.m_Images = new Image[7];
            final MediaTracker mediaTracker = new MediaTracker(this);
            int n = 1;
            do {
                mediaTracker.addImage(this.m_Images[n - 1] = this.getImage(this.getDocumentBase(), "Card" + n + ".gif"), 0);
            } while (++n <= 7);
            try {
                mediaTracker.waitForAll();
                this.m_AllLoaded = !mediaTracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.m_AllLoaded) {
                this.m_Graphics.drawString("Error loading card images!", 68, 100);
                this.button1.setVisible(false);
                this.button2.setVisible(false);
                this.button3.setVisible(false);
                this.stop();
                return;
            }
            this.m_ImgWidth = this.m_Images[0].getWidth(this);
            this.m_ImgHeight = this.m_Images[0].getHeight(this);
        }
        this.repaint();
    }
    
    public void init() {
        this.add(this.button1);
        this.add(this.button2);
        this.add(this.button3);
        this.button1.addActionListener(this);
        this.button2.addActionListener(this);
        this.button3.addActionListener(this);
        this.setLayout(new FlowLayout(1, 20, 260));
        this.setBackground(Color.white);
    }
}
