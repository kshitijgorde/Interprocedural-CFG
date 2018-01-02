import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Component;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Invaders extends Applet implements HotSpotListener
{
    protected Data data;
    protected PlayField field;
    protected InfoScreen info;
    public String file;
    public String dir;
    int loadCount;
    int totalFiles;
    int loadPercent;
    private Graphics frameBuffer;
    private Image buffer;
    protected boolean doHighScore;
    private MediaTracker mt;
    private String companyname;
    private Color bgColor;
    protected int picW;
    protected int picH;
    protected Dimension dim;
    private boolean loadFlag;
    protected AudioClip shootS;
    protected AudioClip startS;
    protected AudioClip dataS;
    protected AudioClip dieS;
    protected AudioClip explodeS;
    protected AudioClip endS;
    private int soundCount;
    private int totalSoundFiles;
    boolean loadSoundFlag;
    boolean running;
    boolean second;
    boolean loaded;
    boolean startFlag;
    boolean storyFlag;
    boolean helpFlag;
    private HotSpot hs1;
    private HotSpot hs2;
    private HotSpot hs3;
    private HotSpot hs4;
    private HotSpot sound1;
    private HotSpot sound2;
    boolean soundWait;
    private Font textfont;
    private Font titlefont;
    private StoryThread sThread;
    protected boolean sound;
    protected boolean registered;
    String urlparam;
    private LoadAnim la;
    
    public Invaders() {
        this.file = "rahighsc";
        this.dir = "/unreg";
        this.doHighScore = false;
        this.companyname = "Your-name-here";
        this.loadFlag = true;
        this.loadSoundFlag = true;
        this.running = false;
        this.loaded = false;
        this.startFlag = false;
        this.soundWait = true;
        this.registered = false;
        this.urlparam = "Http://www.yourwebsite.com";
    }
    
    private void addSpots(final boolean b) {
        if (b) {
            this.add(this.hs1);
            this.add(this.hs2);
            this.add(this.hs3);
        }
        else {
            this.remove(this.hs1);
            this.remove(this.hs2);
            this.remove(this.hs3);
        }
    }
    
    public void destroy() {
        this.field.stopActions();
        this.field.kill();
        this.field.mainThread = null;
    }
    
    public void gameOver() {
        this.field.stopActions();
        this.field.kill();
        this.remove(this.field);
        this.remove(this.info);
        this.field = null;
        this.info = null;
        this.second = true;
        this.running = false;
        this.addSpots(true);
        this.startFlag = true;
        this.repaint();
    }
    
    public Image getEmptyImage(final int n, final int n2) {
        return this.createImage(n, n2);
    }
    
    public void hotSpotEvent(final HotSpot hotSpot) {
        if (hotSpot == this.sound1) {
            this.soundWait = false;
            this.remove(this.sound1);
            this.remove(this.sound2);
            this.sound1 = null;
            this.sound2 = null;
            this.sound = true;
        }
        else if (hotSpot == this.sound2) {
            this.soundWait = false;
            this.remove(this.sound1);
            this.remove(this.sound2);
            this.sound1 = null;
            this.sound2 = null;
            this.sound = false;
        }
        if (hotSpot == this.hs1) {
            this.killThread();
            this.startup();
            this.helpFlag = false;
            this.storyFlag = false;
            this.running = true;
        }
        else if (hotSpot == this.hs2) {
            this.startFlag = false;
            this.killThread();
            this.helpFlag = false;
            this.storyFlag = true;
        }
        else if (hotSpot == this.hs3) {
            this.startFlag = false;
            this.helpFlag = true;
            this.killThread();
            this.storyFlag = false;
        }
        else if (hotSpot == this.hs4) {
            try {
                this.getAppletContext().showDocument(new URL(this.urlparam), "_blank");
            }
            catch (Exception ex) {}
        }
        this.repaint();
    }
    
    public void init() {
        this.setLayout(null);
        (this.la = new LoadAnim(this)).setBounds(500, 10, 20, 20);
        this.add(this.la);
        this.la.startUp();
        this.urlparam = this.getParameter("Company_url");
        this.companyname = this.getParameter("Company_name");
        if (this.getParameter("highscores").toUpperCase().equals("YES")) {
            this.doHighScore = true;
        }
        if (this.registered) {
            this.file = this.getParameter("highscore_file");
        }
        if (this.registered) {
            this.dir = this.getParameter("userdir");
        }
        if (!this.registered) {
            this.urlparam = "Http://www.realapplets.com";
        }
        if (!this.registered) {
            this.companyname = "RealApplets";
        }
        this.textfont = new Font("Comic Sans MS", 1, 12);
        this.titlefont = new Font("Comic Sans MS", 1, 40);
        this.data = new Data(this);
        this.sound1 = new HotSpot(this);
        this.sound2 = new HotSpot(this);
        this.sound1.setBounds(50, 100, 40, 30);
        this.sound2.setBounds(100, 100, 40, 30);
        this.sound1.setMessage("Yes", 1);
        this.sound1.setMessage("Yes", 2);
        this.sound1.setMessage("Yes", 3);
        this.sound2.setMessage("No", 1);
        this.sound2.setMessage("No", 2);
        this.sound2.setMessage("No", 3);
        this.sound1.setBackground(Color.orange, 2);
        this.sound1.setBackground(Color.yellow, 1);
        this.sound2.setBackground(Color.yellow, 1);
        this.sound2.setBackground(Color.orange, 2);
        this.add(this.sound1);
        this.add(this.sound2);
        this.info = new InfoScreen(this.data);
        this.totalFiles = 32;
        this.totalSoundFiles = 6;
        this.dim = this.getSize();
        this.buffer = this.getEmptyImage(this.dim.width, this.dim.height);
        this.frameBuffer = this.buffer.getGraphics();
        this.mt = new MediaTracker(this);
        this.bgColor = new Color(0, 0, 0);
        (this.field = new PlayField(this, this.data)).setBounds(20, 10, 400, 300);
        this.info.setBounds(420, 10, 100, 300);
        this.data.initData();
        this.loaded = false;
    }
    
    private void initButtons() {
        (this.hs1 = new HotSpot(this)).setBounds(75, 290, 80, 25);
        this.hs1.noText(true);
        this.hs1.setImage(this.data.bt1c, 1);
        this.hs1.setImage(this.data.bt2c, 2);
        this.hs1.setImage(this.data.bt3c, 3);
        (this.hs2 = new HotSpot(this)).setBounds(185, 290, 80, 25);
        this.hs2.noText(true);
        this.hs2.setImage(this.data.bt1b, 1);
        this.hs2.setImage(this.data.bt2b, 2);
        this.hs2.setImage(this.data.bt3b, 3);
        (this.hs3 = new HotSpot(this)).setBounds(295, 290, 80, 25);
        this.hs3.noText(true);
        this.hs3.setImage(this.data.bt1a, 1);
        this.hs3.setImage(this.data.bt2a, 2);
        this.hs3.setImage(this.data.bt3a, 3);
        (this.hs4 = new HotSpot(this)).setBounds(449, 265, 80, 40);
        this.hs4.noText(true);
        this.hs4.setImage(this.data.real1, 1);
        this.hs4.setImage(this.data.real2, 2);
        this.hs4.setImage(this.data.real3, 3);
        this.add(this.hs4);
        this.addSpots(true);
    }
    
    public void killThread() {
        if (this.sThread != null) {
            this.sThread.Trunning = false;
            this.sThread = null;
        }
    }
    
    public Image loadImage(final String s) {
        final Image image = this.getImage(this.getCodeBase(), s);
        this.mt.addImage(image, 0);
        try {
            this.mt.waitForAll(0L);
        }
        catch (Exception ex) {
            System.out.println("Error loading " + s);
        }
        ++this.loadCount;
        this.picW = image.getWidth(this);
        this.picH = image.getHeight(this);
        if (this.loadCount == this.totalFiles - 1 && this.loadFlag) {
            this.loadFlag = false;
            this.repaint();
        }
        return image;
    }
    
    public AudioClip loadSound(final String s) {
        return this.getAudioClip(this.getCodeBase(), String.valueOf(s) + ".au");
    }
    
    public void paint(final Graphics graphics) {
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.setFont(this.textfont);
        this.frameBuffer.fillRect(0, 0, 560, 360);
        if (!this.soundWait) {
            if (this.loaded) {
                this.frameBuffer.drawImage(this.data.bgimage, 0, 0, this);
            }
            if (this.loadFlag) {
                this.paintLoad();
            }
            else if (this.loadSoundFlag) {
                this.paintSoundLoad();
            }
            else if (this.startFlag) {
                this.paintStartScreen();
            }
            else if (this.helpFlag) {
                this.paintHelpScreen();
            }
            else if (this.storyFlag) {
                this.paintStoryScreen();
            }
        }
        else {
            this.frameBuffer.setColor(Color.yellow);
            this.frameBuffer.drawString("Do you want Sound?", 50, 50);
            this.frameBuffer.drawString("No Sound will load and play faster", 50, 65);
            this.frameBuffer.drawString("on slower machines.", 50, 80);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    private void paintHelpScreen() {
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.drawImage(this.data.getGraphic("alien1aG"), 80, 30, this);
        this.frameBuffer.drawImage(this.data.getGraphic("alien2aG"), 80, 70, this);
        this.frameBuffer.drawImage(this.data.getGraphic("alien3aG"), 80, 110, this);
        this.frameBuffer.drawImage(this.data.getGraphic("alien4aG"), 80, 150, this);
        this.frameBuffer.setColor(Color.yellow);
        this.frameBuffer.drawString("Basic alien with a bad youth", 130, 40);
        this.frameBuffer.drawString("Not as friendly as he looks", 130, 80);
        this.frameBuffer.drawString("This one aims at you (oh my...)", 130, 120);
        this.frameBuffer.drawString("Stay out of its way!", 130, 160);
        this.frameBuffer.drawString("Use LEFT - RIGHT arrow keys to move", 100, 190);
        this.frameBuffer.drawString("Space to fire and ENTER to confirm", 100, 210);
        this.frameBuffer.drawString("Shoot", 100, 230);
        this.frameBuffer.drawImage(this.data.getGraphic("bonus1G"), 138, 219, this);
        this.frameBuffer.drawString("'s for extra firepower", 160, 230);
        this.frameBuffer.drawString("Gather 3", 100, 250);
        this.frameBuffer.drawImage(this.data.getGraphic("bonus1G"), 150, 239, this);
        this.frameBuffer.drawString("'s for an extra life", 170, 250);
    }
    
    private void paintLoad() {
        this.loadPercent = (this.loadCount + 1) * 100 / this.totalFiles;
        if (this.loadPercent > 100) {
            this.loadPercent = 100;
        }
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.fillRect(0, 0, 560, 360);
        this.frameBuffer.setColor(Color.white);
        this.frameBuffer.drawString("RealInvaders by RealApplets.com", 60, 50);
        this.frameBuffer.drawString("Loading Files...", 60, 80);
        this.frameBuffer.fillRect(102, 122, 2 * this.loadPercent, 40);
        this.frameBuffer.setColor(Color.gray);
        this.frameBuffer.fillRect(100, 120, 2 * this.loadPercent, 40);
        if (this.registered) {
            this.frameBuffer.drawString("Registered to " + this.companyname, 60, 230);
        }
        else {
            this.frameBuffer.drawString("UNREGISTERED VERSION", 60, 230);
            this.frameBuffer.drawString("Custom HighScore lists in registered version", 60, 290);
        }
        this.frameBuffer.drawString("Release 1.5", 60, 250);
        this.frameBuffer.drawString("Real-time Highscores", 60, 270);
        this.frameBuffer.setColor(Color.white);
        this.frameBuffer.drawString(this.loadPercent + "%", 150, 140);
        this.frameBuffer.setColor(this.bgColor);
        this.data.loadGraphics(this.loadCount + 1);
    }
    
    private void paintSoundLoad() {
        this.loadPercent = (this.soundCount + 1) * 100 / this.totalSoundFiles;
        if (this.loadPercent > 100) {
            this.loadPercent = 100;
        }
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.fillRect(0, 0, 560, 360);
        this.frameBuffer.setColor(Color.white);
        this.frameBuffer.drawString("RealInvaders by RealApplets.com", 60, 50);
        this.frameBuffer.drawString("Loading Sound Files...", 60, 80);
        this.frameBuffer.fillRect(102, 122, 2 * this.loadPercent, 40);
        this.frameBuffer.setColor(Color.gray);
        this.frameBuffer.fillRect(100, 120, 2 * this.loadPercent, 40);
        if (this.registered) {
            this.frameBuffer.drawString("Registered to " + this.companyname, 60, 230);
        }
        else {
            this.frameBuffer.drawString("UNREGISTERED VERSION", 60, 230);
            this.frameBuffer.drawString("Custom HighScore lists in registered version", 60, 290);
        }
        this.frameBuffer.drawString("Release 1.5", 60, 250);
        this.frameBuffer.drawString("Real-time Highscores", 60, 270);
        this.frameBuffer.setColor(Color.white);
        this.frameBuffer.drawString(this.loadPercent + "%", 150, 140);
        this.frameBuffer.setColor(this.bgColor);
        this.preloadSound(this.soundCount);
    }
    
    private void paintStartScreen() {
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.drawImage(this.data.getGraphic("logoG"), 100, 50, this);
        this.frameBuffer.setColor(Color.yellow);
    }
    
    private void paintStoryScreen() {
        if (this.sThread == null) {
            (this.sThread = new StoryThread()).start();
        }
        this.frameBuffer.setColor(Color.black);
        this.frameBuffer.drawImage(this.data.bgimage, 0, 0, this);
        this.frameBuffer.setFont(this.titlefont);
        this.frameBuffer.setColor(Color.gray);
        this.frameBuffer.drawString("Story", 150, 150);
        this.frameBuffer.setFont(this.textfont);
        this.frameBuffer.setColor(Color.yellow);
        if (300 - this.sThread.getK() > 30 && 300 - this.sThread.getK() < 280) {
            this.frameBuffer.drawString("Story", 50, 300 - this.sThread.getK());
        }
        if (300 - this.sThread.getK() + 20 > 30 && 300 - this.sThread.getK() + 20 < 280) {
            this.frameBuffer.drawString("2345 - Taxes are low, RealAppplets is the largest", 50, 300 - this.sThread.getK() + 20);
        }
        if (300 - this.sThread.getK() + 40 > 30 && 300 - this.sThread.getK() + 40 < 280) {
            this.frameBuffer.drawString("software firm and MacDonald's stopped making burgers.", 50, 300 - this.sThread.getK() + 40);
        }
        if (300 - this.sThread.getK() + 60 > 30 && 300 - this.sThread.getK() + 60 < 280) {
            this.frameBuffer.drawString("Oh, and MicroSoftians have invaded Earth (again)", 50, 300 - this.sThread.getK() + 60);
        }
        if (300 - this.sThread.getK() + 80 > 30 && 300 - this.sThread.getK() + 80 < 280) {
            this.frameBuffer.drawString("and kidnapped the princess.", 50, 300 - this.sThread.getK() + 80);
        }
        if (300 - this.sThread.getK() + 100 > 30 && 300 - this.sThread.getK() + 100 < 280) {
            this.frameBuffer.drawString("Okay the princess bit is not true, but they must be stopped", 50, 300 - this.sThread.getK() + 100);
        }
        if (300 - this.sThread.getK() + 120 > 30 && 300 - this.sThread.getK() + 120 < 280) {
            this.frameBuffer.drawString("anyway. Your mission is to stop Bill XIV, you are the last", 50, 300 - this.sThread.getK() + 120);
        }
        if (300 - this.sThread.getK() + 140 > 30 && 300 - this.sThread.getK() + 140 < 280) {
            this.frameBuffer.drawString("survivor (except from Thorvalds XIII, who had a headache and", 50, 300 - this.sThread.getK() + 140);
        }
        if (300 - this.sThread.getK() + 160 > 30 && 300 - this.sThread.getK() + 160 < 280) {
            this.frameBuffer.drawString("refused the job)", 50, 300 - this.sThread.getK() + 160);
        }
        if (300 - this.sThread.getK() + 180 > 30 && 300 - this.sThread.getK() + 180 < 280) {
            this.frameBuffer.drawString("Save The Earth! That would be nice...", 50, 300 - this.sThread.getK() + 180);
        }
        if (300 - this.sThread.getK() + 200 > 30 && 300 - this.sThread.getK() + 200 < 280) {
            this.frameBuffer.drawString("If that's not enough for you, you can always go to", 50, 300 - this.sThread.getK() + 200);
        }
        if (300 - this.sThread.getK() + 220 > 30 && 300 - this.sThread.getK() + 220 < 280) {
            this.frameBuffer.drawString(String.valueOf(this.companyname) + " to look for other challenges.", 50, 300 - this.sThread.getK() + 220);
        }
        if (300 - this.sThread.getK() + 240 > 30 && 300 - this.sThread.getK() + 240 < 280) {
            this.frameBuffer.drawString("That would be nice too!", 50, 300 - this.sThread.getK() + 240);
        }
        if (300 - this.sThread.getK() + 280 > 30 && 300 - this.sThread.getK() + 280 < 280) {
            this.frameBuffer.drawString("THE END", 50, 300 - this.sThread.getK() + 280);
        }
        this.frameBuffer.setColor(Color.black);
    }
    
    public void preloadSound(final int n) {
        String s = "a";
        if (this.sound) {
            s = "";
        }
        if (n == 0) {
            this.shootS = this.loadSound("shootS" + s);
        }
        else if (n == 1) {
            this.startS = this.loadSound("startS" + s);
        }
        else if (n == 2) {
            this.dieS = this.loadSound("die1S" + s);
        }
        else if (n == 3) {
            this.explodeS = this.loadSound("explodeS" + s);
        }
        else if (n == 4) {
            this.dataS = this.loadSound("dataS" + s);
        }
        else if (n == 5) {
            this.endS = this.loadSound("endS" + s);
        }
        else {
            System.out.println("Starting up " + n);
            this.loadSoundFlag = false;
            this.loaded = true;
            this.startFlag = true;
            this.initButtons();
            this.dataS.play();
            this.la.stop();
            this.remove(this.la);
        }
        System.out.println("Loaded " + n);
        ++this.soundCount;
        this.repaint();
    }
    
    public void startup() {
        if (this.second) {
            this.data.reset();
            this.info = new InfoScreen(this.data);
            this.field = new PlayField(this, this.data);
            this.data.initData();
        }
        this.addSpots(false);
        this.startFlag = false;
        this.loadFlag = false;
        this.field.setBounds(35, 20, 400, 300);
        this.info.setBounds(455, 45, 75, 260);
        this.add(this.field);
        this.add(this.info);
        this.field.startActions();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class StoryThread extends Thread
    {
        int k;
        boolean Trunning;
        
        StoryThread() {
            this.k = 20;
            this.Trunning = true;
        }
        
        public int getK() {
            return this.k;
        }
        
        public void run() {
            while (this.Trunning) {
                ++this.k;
                Invaders.this.repaint();
                if (this.k > 540) {
                    this.Trunning = false;
                }
                try {
                    Thread.sleep(40L);
                }
                catch (Exception ex) {}
            }
        }
    }
}
