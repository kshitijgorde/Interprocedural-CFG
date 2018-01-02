import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonStar extends Applet implements Runnable
{
    private static final int WIDTH = 450;
    private static final int HEIGHT = 320;
    private static final int TOTALLOADING = 9;
    private Image gbuffer;
    private Graphics gbuf;
    private Thread runner;
    private boolean ready;
    public String errorString;
    public boolean registered;
    public Color[] colors;
    private String loadingString;
    private int indexLoading;
    private Color backgroundColor;
    private Color textColor;
    private Color barColor;
    private Color textBarColor;
    public MSGame mSGame;
    public MSMenu mSMenu;
    public MSController mSController;
    public MSNewGame mSNewGame;
    public MSHelp mSHelp;
    public MSConfig mSConfig;
    public MSCustomGame mSCustomGame;
    
    public MoonStar() {
        this.ready = false;
        this.registered = false;
        this.colors = new Color[10];
        this.indexLoading = 0;
    }
    
    public void init() {
        System.out.println("\nMoonStar Version 2.0");
        System.out.println("********************\n");
        String host = this.getDocumentBase().getHost();
        if (host == null) {
            host = "";
        }
        this.registered = host.equalsIgnoreCase("www.realapplets.com");
        System.out.println(" Unregistered applet, written by Geoffrey from RealApplets");
        System.out.println(" Get your applets from http://www.realapplets.com");
        System.out.println(" Removal of add without registration is punishable in a court of law.\n");
        final Dimension size = this.getSize();
        if (size.width != 450 || size.height != 320) {
            this.errorString = "Width not 450 or height not 320.";
            System.out.println(this.errorString);
            return;
        }
        this.loadingString = this.getParameter("TagLoading");
        if (this.loadingString == null) {
            this.loadingString = "";
        }
        try {
            this.setBackground(this.colors[0] = new Color(Integer.parseInt(this.getParameter("BackgroundColor"), 16)));
            this.colors[1] = new Color(Integer.parseInt(this.getParameter("LoadingTextColor"), 16));
            this.colors[2] = new Color(Integer.parseInt(this.getParameter("BarColor"), 16));
            this.colors[3] = new Color(Integer.parseInt(this.getParameter("BarTextColor"), 16));
            this.colors[4] = new Color(Integer.parseInt(this.getParameter("ButtonTextColorOut"), 16));
            this.colors[5] = new Color(Integer.parseInt(this.getParameter("ButtonTextColorOver"), 16));
            this.colors[6] = new Color(Integer.parseInt(this.getParameter("GameTextColor"), 16));
            this.colors[7] = new Color(Integer.parseInt(this.getParameter("MenuScoreColor"), 16));
            this.colors[8] = new Color(Integer.parseInt(this.getParameter("MenuLevelColor"), 16));
            this.colors[9] = new Color(Integer.parseInt(this.getParameter("MenuTypeColor"), 16));
        }
        catch (Exception ex) {
            this.errorString = "One of the Color parameters is wrong";
            System.out.println(this.errorString + "\n" + ex);
            this.repaint();
        }
        (this.runner = new Thread(this)).start();
    }
    
    public void run() {
        this.setLayout(null);
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            final Image image = this.getImage(this.getCodeBase(), "button.jpg");
            mediaTracker.addImage(image, 0);
            final Image image2 = this.getImage(this.getCodeBase(), "menubackground.jpg");
            mediaTracker.addImage(image2, 1);
            final Image image3 = this.getImage(this.getCodeBase(), "newgamebackground.jpg");
            mediaTracker.addImage(image3, 2);
            final Image image4 = this.getImage(this.getCodeBase(), "helpbackground.jpg");
            mediaTracker.addImage(image4, 3);
            final Image image5 = this.getImage(this.getCodeBase(), "customgamebackground.jpg");
            mediaTracker.addImage(image5, 4);
            final Image image6 = this.getImage(this.getCodeBase(), "gamebackground.jpg");
            mediaTracker.addImage(image6, 5);
            final Image image7 = this.getImage(this.getCodeBase(), "campaignbackground.jpg");
            mediaTracker.addImage(image7, 6);
            final Image image8 = this.getImage(this.getCodeBase(), "campaignforeground.jpg");
            mediaTracker.addImage(image8, 7);
            final Image image9 = this.getImage(this.getCodeBase(), "blocks.jpg");
            mediaTracker.addImage(image9, 8);
            mediaTracker.waitForID(0);
            ++this.indexLoading;
            this.repaint();
            MSButton.init(this, image, this.colors[4], this.colors[5]);
            mediaTracker.waitForID(1);
            ++this.indexLoading;
            this.repaint();
            final String[] array = new String[this.registered ? 5 : 6];
            array[0] = this.getParameter("TagResumeGame");
            array[1] = this.getParameter("TagPauzeGame");
            array[2] = this.getParameter("TagNewGame");
            array[3] = this.getParameter("TagHelp");
            array[4] = this.getParameter("TagConfigureKeys");
            if (!this.registered) {
                array[5] = "Real Applets";
            }
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    array[i] = "";
                }
            }
            String parameter = this.getParameter("TagWelcomeMessage");
            if (parameter == null) {
                parameter = "";
            }
            (this.mSMenu = new MSMenu(this, image2, array, parameter)).setBounds(0, 0, 130, 320);
            mediaTracker.waitForID(2);
            ++this.indexLoading;
            this.repaint();
            final String[] array2 = { this.getParameter("TagArcade"), this.getParameter("TagCampaign"), this.getParameter("TagCustom") };
            for (int j = 0; j < array2.length; ++j) {
                if (array2[j] == null) {
                    array2[j] = "";
                }
            }
            (this.mSNewGame = new MSNewGame(this, array2, image3)).setBounds(130, 0, 320, 320);
            mediaTracker.waitForID(3);
            ++this.indexLoading;
            this.repaint();
            (this.mSHelp = new MSHelp(this, image4)).setBounds(130, 0, 320, 320);
            mediaTracker.waitForID(4);
            ++this.indexLoading;
            this.repaint();
            String parameter2 = this.getParameter("TagCustomGameStart");
            if (parameter2 == null) {
                parameter2 = "";
            }
            (this.mSCustomGame = new MSCustomGame(this, parameter2, image5)).setBounds(130, 0, 320, 320);
            mediaTracker.waitForID(5);
            ++this.indexLoading;
            this.repaint();
            mediaTracker.waitForID(6);
            ++this.indexLoading;
            this.repaint();
            mediaTracker.waitForID(7);
            ++this.indexLoading;
            this.repaint();
            mediaTracker.waitForID(8);
            ++this.indexLoading;
            this.repaint();
            String parameter3 = this.getParameter("TagChangeKeys");
            if (parameter3 == null) {
                parameter3 = "";
            }
            String parameter4 = this.getParameter("TagSaveKeys");
            if (parameter4 == null) {
                parameter4 = "";
            }
            String parameter5 = this.getParameter("TagConfigureMessage");
            if (parameter5 == null) {
                parameter5 = "";
            }
            final String[] array3 = { this.getParameter("TagLEFT"), this.getParameter("TagUP"), this.getParameter("TagRIGHT"), this.getParameter("TagDOWN"), this.getParameter("TagPAUSE") };
            for (int k = 0; k < array3.length; ++k) {
                if (array3[k] == null) {
                    array3[k] = "";
                }
            }
            (this.mSConfig = new MSConfig(this, parameter3, parameter4, parameter5, array3, image6)).setBounds(130, 0, 320, 320);
            String parameter6 = this.getParameter("TagGamePause");
            if (parameter6 == null) {
                parameter6 = "";
            }
            String parameter7 = this.getParameter("TagGameOver");
            if (parameter7 == null) {
                parameter7 = "";
            }
            (this.mSGame = new MSGame(this, parameter6, parameter7, image6, image9, image7, image8)).setBounds(130, 0, 320, 320);
            this.mSHelp.add();
            this.mSMenu.add();
        }
        catch (Exception ex) {
            this.errorString = "Error loading one of the pictures.";
            System.out.println(this.errorString + "\n" + ex);
            this.repaint();
            return;
        }
        this.runner = null;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.errorString != null) {
            gbuf.setColor(Color.white);
            gbuf.fillRect(0, 0, 450, 320);
            gbuf.setColor(Color.black);
            gbuf.setFont(new Font("Ariel", 0, 16));
            gbuf.drawString(this.errorString, 10, 20);
        }
        else {
            if (this.gbuffer == null) {
                try {
                    this.gbuffer = this.createImage(450, 320);
                    this.gbuf = this.gbuffer.getGraphics();
                }
                catch (Exception ex) {
                    this.gbuf = gbuf;
                    this.gbuffer = null;
                }
            }
            this.gbuf.setColor(this.colors[0]);
            this.gbuf.fillRect(0, 0, 450, 320);
            if (this.runner != null) {
                this.gbuf.setFont(new Font("Ariel", 0, 16));
                this.gbuf.setColor(this.colors[1]);
                this.gbuf.drawString(this.loadingString, 10, 22);
                if (!this.registered) {
                    this.gbuf.drawString("Unregistered applet from RealApplets.com", 10, 98);
                }
                this.gbuf.setFont(new Font("Ariel", 1, 20));
                this.gbuf.setColor(this.colors[2]);
                this.gbuf.drawRect(10, 36, 429, 39);
                this.gbuf.drawRect(11, 37, 427, 37);
                this.gbuf.drawRect(12, 38, 425, 35);
                if (this.indexLoading > 0) {
                    this.gbuf.fillRect(14, 40, 422 * this.indexLoading / 9, 32);
                }
                this.gbuf.setColor(this.colors[3]);
                final String string = 100 * this.indexLoading / 9 + "%";
                this.gbuf.drawString(string, (450 - this.gbuf.getFontMetrics().stringWidth(string)) / 2, 66);
            }
            if (this.gbuffer != null) {
                gbuf.drawImage(this.gbuffer, 0, 0, 450, 320, this);
            }
        }
    }
}
