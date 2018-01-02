import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rocks extends Applet implements Runnable
{
    private Image background;
    private Image cityPic;
    private Image[] rockFrames;
    private Image bufferImage;
    private Image shot;
    private AudioClip[] rockSounds;
    private AudioClip laserSound;
    private MediaTracker tracker;
    private Thread this_thread;
    int xgun;
    int ygun;
    int score;
    int bh;
    int bw;
    int level;
    int shots;
    int blows;
    boolean fire;
    boolean paused;
    boolean running;
    boolean blow;
    Graphics bb;
    Graphics sb;
    private boolean[] city;
    private int[] cityPos;
    public static int maxThreadNumber;
    public static int no_of_rock_frames;
    public static int no_of_rock_sounds;
    public static int no_of_cities;
    public static int screen_area;
    Thread[] rocks;
    SpaceObject[] fos;
    Panel screenPanel;
    Canvas screen;
    Panel controlPanel;
    Label scoreLabel;
    Label theScore;
    Button pause;
    Canvas shotsLeft;
    Label shotsLabel;
    Button control;
    
    public void init() {
        this.setLayout(null);
        this.setSize(400, 450);
        this.setBackground(new Color(0));
        (this.screenPanel = new Panel()).setLayout(null);
        this.screenPanel.setBounds(0, 0, 400, 400);
        this.add(this.screenPanel);
        (this.screen = new Canvas()).setBounds(0, 0, 400, 400);
        this.screen.setBackground(new Color(0));
        this.screenPanel.add(this.screen);
        this.screen.setCursor(new Cursor(1));
        (this.controlPanel = new Panel()).setLayout(null);
        this.controlPanel.setBounds(0, 400, 400, 50);
        this.controlPanel.setBackground(new Color(4210752));
        this.add(this.controlPanel);
        (this.scoreLabel = new Label("Your score:")).setBounds(0, 0, 72, 12);
        this.scoreLabel.setFont(new Font("Helvetica", 1, 12));
        this.scoreLabel.setForeground(new Color(12632256));
        this.controlPanel.add(this.scoreLabel);
        (this.theScore = new Label("0")).setBounds(0, 12, 72, 26);
        this.theScore.setFont(new Font("Helvetica", 0, 12));
        this.theScore.setForeground(new Color(12632256));
        this.controlPanel.add(this.theScore);
        (this.pause = new Button()).setActionCommand("button");
        this.pause.setLabel("Pause");
        this.pause.setBounds(180, 12, 76, 28);
        this.pause.setFont(new Font("Helvetica", 1, 12));
        this.pause.setBackground(new Color(12632256));
        this.controlPanel.add(this.pause);
        (this.shotsLeft = new Canvas()).setBounds(264, 20, 136, 24);
        this.shotsLeft.setFont(new Font("Helvetica", 1, 12));
        this.shotsLeft.setForeground(new Color(12632256));
        this.controlPanel.add(this.shotsLeft);
        (this.shotsLabel = new Label("Shots Left", 2)).setBounds(336, 0, 60, 12);
        this.shotsLabel.setFont(new Font("Helvetica", 1, 12));
        this.shotsLabel.setForeground(new Color(12632256));
        this.controlPanel.add(this.shotsLabel);
        (this.control = new Button()).setActionCommand("button");
        this.control.setLabel("Start the rocks!");
        this.control.setBounds(72, 12, 96, 28);
        this.control.setFont(new Font("Helvetica", 1, 12));
        this.control.setForeground(new Color(0));
        this.control.setBackground(new Color(12632256));
        this.controlPanel.add(this.control);
        this.rockFrames = new Image[Rocks.no_of_rock_frames];
        this.rockSounds = new AudioClip[Rocks.no_of_rock_sounds];
        this.tracker = new MediaTracker(this);
        this.background = this.getImage(this.getDocumentBase(), "images/background.jpg");
        this.tracker.addImage(this.background, 0);
        this.rockFrames[0] = this.getImage(this.getDocumentBase(), "images/exp1.gif");
        this.tracker.addImage(this.rockFrames[0], 1);
        this.rockFrames[1] = this.getImage(this.getDocumentBase(), "images/exp2.gif");
        this.tracker.addImage(this.rockFrames[1], 2);
        this.rockFrames[2] = this.getImage(this.getDocumentBase(), "images/exp3.gif");
        this.tracker.addImage(this.rockFrames[2], 3);
        this.cityPic = this.getImage(this.getDocumentBase(), "images/city.gif");
        this.tracker.addImage(this.cityPic, 4);
        this.shot = this.getImage(this.getDocumentBase(), "images/shot.gif");
        this.tracker.addImage(this.shot, 5);
        this.city = new boolean[Rocks.no_of_cities];
        this.prepareCities();
        this.bw = this.screen.getSize().width;
        this.bh = this.screen.getSize().height;
        this.bufferImage = this.createImage(this.bw, this.bh);
        this.bb = this.bufferImage.getGraphics();
        this.sb = this.screen.getGraphics();
        this.xgun = this.bw / 2;
        this.ygun = this.bh / 2;
        final SymMouse symMouse = new SymMouse(this);
        this.control.addMouseListener((MouseListener)symMouse);
        final SymKey symKey = new SymKey(this);
        this.control.addKeyListener((KeyListener)symKey);
        this.pause.addKeyListener((KeyListener)symKey);
        this.screen.addKeyListener((KeyListener)symKey);
        this.screen.addMouseListener((MouseListener)symMouse);
        this.screen.addMouseMotionListener((MouseMotionListener)new SymMouseMotion(this));
        this.pause.addMouseListener((MouseListener)symMouse);
    }
    
    public void prepareCities() {
        this.cityPos = new int[Rocks.no_of_cities];
        for (int i = 0; i < Rocks.no_of_cities; ++i) {
            this.city[i] = true;
            this.cityPos[i] = Rocks.screen_area / 12 + i * (Rocks.screen_area / 4);
        }
    }
    
    private void verifyImages() {
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            try {
                this.tracker.waitForID(i);
            }
            catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
            System.out.println("Waiting on " + n);
            ++n;
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void start() {
        if (this.this_thread == null) {
            (this.this_thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.this_thread != null) {
            this.this_thread.stop();
            this.this_thread = null;
        }
    }
    
    public final void checkHit() {
        for (int i = 0; i < this.level; ++i) {
            if (this.fos[i] != null) {
                this.fos[i].isAHit(this.xgun, this.ygun);
            }
        }
    }
    
    public final void killSpaceObject(final int n) {
        if (this.fos[n] != null) {
            this.fos[n].alive = false;
            this.fos[n] = null;
            this.rocks[n] = null;
        }
    }
    
    public void killSpaceObjects() {
        for (int i = 0; i < this.level; ++i) {
            this.killSpaceObject(i);
        }
    }
    
    public void blowSpaceObjects() {
        for (int i = 0; i < this.level; ++i) {
            if (this.fos[i] != null && this.blows > 0) {
                this.fos[i].alive = false;
                this.blow = true;
                this.rockSounds[0].play();
            }
        }
        --this.blows;
    }
    
    public final void makeSpaceObject(final int n) {
        if (this.fos[n] == null) {
            this.fos[n] = new Rock(this.rockFrames, this.rockSounds, this.city, this.cityPos, this.screen);
            this.rocks[n] = new Thread(this.fos[n]);
        }
    }
    
    public void makeSpaceObjects() {
        for (int i = 0; i < this.level; ++i) {
            this.makeSpaceObject(i);
        }
    }
    
    public final void pauseSpaceObject(final int n) {
        if (this.rocks[n] != null) {
            this.fos[n].move = !this.fos[n].move;
        }
    }
    
    public void pauseSpaceObjects() {
        for (int i = 0; i < this.level; ++i) {
            this.pauseSpaceObject(i);
        }
    }
    
    public final void startSpaceObject(final int n) {
        if (this.rocks[n] != null) {
            this.rocks[n].start();
        }
    }
    
    public void startSpaceObjects() {
        for (int i = 0; i < this.level; ++i) {
            if (this.rocks[i] != null) {
                this.rocks[i].start();
            }
        }
    }
    
    private boolean checkAllDead() {
        for (int i = 0; i < this.level; ++i) {
            if (this.fos[i].alive) {
                return false;
            }
        }
        return true;
    }
    
    public void run() {
        int n = -1;
        boolean b = false;
        final boolean[] array = new boolean[5];
        final Graphics graphics = this.screen.getGraphics();
        graphics.setColor(Color.gray);
        for (int i = 0; i < 5; ++i) {
            array[i] = false;
        }
        this.showStatus("Loading sounds...");
        this.control.disable();
        this.pause.disable();
        graphics.setFont(new Font("Helvetica", 1, 24));
        graphics.drawString("Loading Sounds...", this.bw / 5, this.bh >> 1);
        graphics.setFont(new Font("Helvetica", 1, 12));
        graphics.drawString("0%", this.bw / 3 - 30, this.bh / 2 + 50);
        graphics.drawString("100%", this.bw / 3 + 120, this.bh / 2 + 50);
        graphics.drawRect(this.bw / 3, this.bh / 2 + 30, 100, 30);
        (this.laserSound = this.getAudioClip(this.getDocumentBase(), "sounds/laser.au")).play();
        graphics.fillRect(this.bw / 3, this.bh / 2 + 30, 33, 30);
        (this.rockSounds[0] = this.getAudioClip(this.getDocumentBase(), "sounds/rockhit.au")).play();
        graphics.fillRect(this.bw / 3, this.bh / 2 + 30, 66, 30);
        (this.rockSounds[1] = this.getAudioClip(this.getDocumentBase(), "sounds/cityhit.au")).play();
        graphics.fillRect(this.bw / 3, this.bh / 2 + 30, 100, 30);
        this.showStatus("Loading Images...");
        try {
            Thread.currentThread();
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        graphics.clearRect(0, 0, this.bw, this.bh);
        while (!b) {
            if (n < 4) {
                ++n;
            }
            else {
                n = 0;
            }
            if (this.tracker.checkID(n, true)) {
                array[n] = true;
            }
            int n2 = 0;
            for (int j = 0; j < 5; ++j) {
                if (array[j]) {
                    ++n2;
                }
            }
            graphics.setFont(new Font("Helvetica", 1, 24));
            graphics.drawString("Loading Images...", this.bw / 5, this.bh >> 1);
            graphics.setFont(new Font("Helvetica", 1, 12));
            graphics.drawString("0%", this.bw / 3 - 30, this.bh / 2 + 50);
            graphics.drawString("100%", this.bw / 3 + 120, this.bh / 2 + 50);
            graphics.drawRect(this.bw / 3, this.bh / 2 + 30, 100, 30);
            graphics.fillRect(this.bw / 3, this.bh / 2 + 30, (int)(n2 / 5.0 * 100.0), 30);
            if (n2 == 5) {
                b = true;
            }
        }
        this.control.enable();
        this.pause.enable();
        this.showStatus("Done");
        this.bb.setFont(new Font("Helvetica", 1, 12));
        final int n3 = this.bw >> 1;
        final Graphics graphics2 = this.shotsLeft.getGraphics();
        try {
            while (true) {
                this.bb.setColor(Color.gray);
                this.bb.clearRect(0, 0, this.bw, this.bh);
                this.bb.drawImage(this.background, 0, 0, this.screen);
                this.bb.drawString("http://freespace.virgin.net/bojan.car", 10, 20);
                this.bb.drawString("Level " + (this.level - 2), 350, 20);
                for (int k = 0; k < Rocks.no_of_cities; ++k) {
                    if (this.city[k]) {
                        this.bb.drawImage(this.cityPic, this.cityPos[k], this.bh - 25, this.screen);
                    }
                }
                if (this.blow) {
                    this.bb.setColor(Color.cyan);
                    this.bb.drawOval(n3 - 50, this.bh - 50, 100, 100);
                    this.bb.drawOval(n3 - 100, this.bh - 100, 200, 200);
                    this.bb.drawOval(n3 - 150, this.bh - 150, 300, 300);
                    this.bb.drawOval(n3 - 200, this.bh - 200, 400, 400);
                    this.bb.drawOval(n3 - 250, this.bh - 250, 500, 500);
                    this.bb.drawOval(n3 - 300, this.bh - 300, 600, 600);
                    this.bb.drawOval(n3 - 350, this.bh - 350, 700, 700);
                    this.bb.drawOval(n3 - 400, this.bh - 400, 800, 800);
                    this.blow = false;
                }
                if (this.fire && this.shots > 0 && !this.paused && this.running) {
                    --this.shots;
                    this.bb.setColor(Color.red);
                    this.bb.drawLine(n3, this.bh, this.xgun, this.ygun);
                    this.bb.setColor(Color.orange);
                    this.bb.drawLine(n3 - 1, this.bh, this.xgun - 1, this.ygun);
                    this.bb.drawLine(n3 + 1, this.bh, this.xgun + 1, this.ygun);
                    this.laserSound.play();
                    this.checkHit();
                    this.fire = false;
                    this.bb.setColor(Color.gray);
                }
                this.bb.setColor(Color.orange);
                this.bb.fillOval(n3 - 15, Rocks.screen_area - 10, 30, 20);
                this.bb.setColor(Color.gray);
                if (this.paused) {
                    this.bb.setFont(new Font("Helvetica", 1, 30));
                    this.bb.drawString("PAUSED...", this.bw / 3, this.bh >> 1);
                    this.bb.setFont(new Font("Helvetica", 1, 12));
                }
                for (int l = 0; l < Rocks.maxThreadNumber; ++l) {
                    if (this.rocks[l] != null) {
                        this.fos[l].paint(this.bb);
                    }
                }
                if (this.running && this.checkAllDead()) {
                    this.control.disable();
                    this.pause.disable();
                    this.killSpaceObjects();
                    this.shots = 5;
                    if (this.level > 5) {
                        ++this.shots;
                    }
                    if (this.level > 10) {
                        ++this.shots;
                    }
                    int n4 = 0;
                    for (int n5 = 0; n5 < Rocks.no_of_cities; ++n5) {
                        if (this.city[n5]) {
                            n4 += 10;
                        }
                    }
                    this.score += n4;
                    this.bb.setFont(new Font("Helvetica", 1, 30));
                    boolean b2 = false;
                    if (n4 == 0) {
                        this.bb.drawString("GAME OVER!!! Score " + this.score, 10, this.bh >> 1);
                        this.killSpaceObjects();
                        this.running = false;
                        this.control.setLabel("Start the rocks!");
                        this.level = 3;
                        this.shots = 5;
                        b2 = true;
                    }
                    else if (this.level < Rocks.maxThreadNumber) {
                        this.bb.drawString("Level " + (this.level - 1), this.bw / 3, this.bh >> 1);
                    }
                    else {
                        this.bb.drawString("YOU WIN!!! Score " + this.score, 20, this.bh >> 1);
                        this.killSpaceObjects();
                        this.running = false;
                        this.control.setLabel("Start the rocks!");
                        this.level = 3;
                        this.shots = 5;
                        b2 = true;
                    }
                    this.bb.setFont(new Font("Helvetica", 1, 12));
                    this.sb.drawImage(this.bufferImage, 0, 0, this.screen);
                    if (b2) {
                        Thread.currentThread();
                        Thread.sleep(10000L);
                    }
                    else {
                        Thread.currentThread();
                        Thread.sleep(3000L);
                    }
                    if (!b2) {
                        ++this.level;
                        this.makeSpaceObjects();
                        this.startSpaceObjects();
                    }
                    else {
                        this.prepareCities();
                    }
                    if ((this.level - 2) % 5 == 0) {
                        this.prepareCities();
                    }
                    this.control.enable();
                    this.pause.enable();
                }
                this.sb.drawImage(this.bufferImage, 0, 0, this.screen);
                graphics2.clearRect(0, 0, 136, 24);
                for (int n6 = 0; n6 < this.shots; ++n6) {
                    graphics2.drawImage(this.shot, 5 + 20 * n6, 0, this.shotsLeft);
                }
                this.theScore.setText(new Integer(this.score).toString());
                Thread.currentThread();
                Thread.sleep(50L);
            }
        }
        catch (InterruptedException ex2) {}
    }
    
    void control_MousePress(final MouseEvent mouseEvent) {
        if (this.control.getLabel().equals("Start the rocks!")) {
            this.level = 3;
            this.score = 0;
            this.shots = 5;
            this.blows = 2;
            this.prepareCities();
            this.makeSpaceObjects();
            this.startSpaceObjects();
            this.running = true;
            this.control.setLabel("Stop");
            return;
        }
        this.killSpaceObjects();
        this.running = false;
        this.control.setLabel("Start the rocks!");
    }
    
    void screen_KeyPress(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 32: {
                this.blowSpaceObjects();
            }
            default: {}
        }
    }
    
    void screen_MousePress(final MouseEvent mouseEvent) {
        this.fire = true;
    }
    
    void screen_MouseMove(final MouseEvent mouseEvent) {
        this.ygun = mouseEvent.getY();
        this.xgun = mouseEvent.getX();
    }
    
    void pause_MousePress(final MouseEvent mouseEvent) {
        if (!this.paused) {
            this.pauseSpaceObjects();
            this.control.disable();
            this.paused = true;
            return;
        }
        this.pauseSpaceObjects();
        this.control.enable();
        this.paused = false;
    }
    
    public Rocks() {
        this.level = 3;
        this.shots = 5;
        this.blows = 2;
        this.fire = false;
        this.paused = false;
        this.running = false;
        this.blow = false;
        this.rocks = new Thread[Rocks.maxThreadNumber];
        this.fos = new SpaceObject[Rocks.maxThreadNumber];
    }
    
    static {
        Rocks.maxThreadNumber = 52;
        Rocks.no_of_rock_frames = 4;
        Rocks.no_of_rock_sounds = 4;
        Rocks.no_of_cities = 4;
        Rocks.screen_area = 400;
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            final Object source = mouseEvent.getSource();
            if (source == this.this$0.control) {
                this.this$0.control_MousePress(mouseEvent);
                return;
            }
            if (source == this.this$0.screen) {
                this.this$0.screen_MousePress(mouseEvent);
                return;
            }
            if (source == this.this$0.pause) {
                this.this$0.pause_MousePress(mouseEvent);
            }
        }
        
        SymMouse(final Rocks rocks) {
            this.this$0 = rocks;
            this.this$0 = rocks;
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            final Object source = keyEvent.getSource();
            if (source == this.this$0.control || source == this.this$0.screen || source == this.this$0.pause) {
                this.this$0.screen_KeyPress(keyEvent);
            }
        }
        
        SymKey(final Rocks rocks) {
            this.this$0 = rocks;
            this.this$0 = rocks;
        }
    }
    
    class SymMouseMotion extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == this.this$0.screen) {
                this.this$0.screen_MouseMove(mouseEvent);
            }
        }
        
        SymMouseMotion(final Rocks rocks) {
            this.this$0 = rocks;
            this.this$0 = rocks;
        }
    }
}
