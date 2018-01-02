import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LongBall2_1 extends Applet implements Runnable, MouseListener, MouseMotionListener, ActionListener, ItemListener
{
    int width;
    int height;
    Thread me;
    MediaTracker tracker;
    Image field;
    Image[] bats;
    int imageCount;
    Image pitchScreen;
    Image offI;
    Graphics offG;
    AudioClip[] sounds;
    int counter;
    Pitch pitch;
    Hit hit;
    Bat bat;
    Color green;
    Color brown;
    Font smallFont;
    Font mediumFont;
    Font averageFont;
    Font largeFont;
    Font bigFont;
    Cursor crosshair;
    Cursor hourglass;
    Cursor normal;
    Choice difficulty;
    Button startB;
    Checkbox pitch1;
    Checkbox pitch2;
    Checkbox pitch3;
    Checkbox pitch4;
    Checkbox pitch5;
    CheckboxGroup pitches;
    Panel pitchP;
    Checkbox sound;
    Label homeruns;
    Label outs;
    Checkbox left;
    Checkbox right;
    CheckboxGroup stance;
    Checkbox lock;
    Choice counterSpeed;
    Panel controlP;
    int stage;
    String message;
    int[] mouse;
    boolean swung;
    int[] swing;
    int[] max;
    
    public final void init() {
        this.width = 400;
        this.height = 400;
        this.tracker = new MediaTracker(this);
        this.bats = new Image[8];
        this.sounds = new AudioClip[6];
        this.offI = this.createImage(this.width, this.height);
        this.offG = this.offI.getGraphics();
        this.setLayout(null);
        (this.difficulty = new Choice()).setForeground(Color.black);
        this.difficulty.setBackground(Color.white);
        this.difficulty.setFont(this.smallFont);
        this.difficulty.addItem("Easy");
        this.difficulty.addItem("Medium");
        this.difficulty.addItem("Hard");
        this.difficulty.select(1);
        this.difficulty.setBounds(100, 320, 100, 20);
        this.add(this.difficulty);
        (this.startB = new Button("Play")).setForeground(Color.black);
        this.startB.setBackground(Color.green);
        this.startB.setFont(this.smallFont);
        this.startB.setBounds(240, 300, 60, 40);
        this.startB.setEnabled(false);
        this.startB.setActionCommand("s");
        this.startB.addActionListener(this);
        this.add(this.startB);
        this.pitches = new CheckboxGroup();
        (this.pitch1 = new Checkbox("Random", this.pitches, true)).setBounds(5, 10, 70, 20);
        (this.pitch2 = new Checkbox("Fast", this.pitches, false)).setBounds(85, 10, 70, 20);
        (this.pitch3 = new Checkbox("Slow", this.pitches, false)).setBounds(165, 10, 70, 20);
        (this.pitch4 = new Checkbox("Curve", this.pitches, false)).setBounds(245, 10, 70, 20);
        (this.pitch5 = new Checkbox("Knuckle", this.pitches, false)).setBounds(325, 10, 70, 20);
        (this.pitchP = new Panel()).setLayout(null);
        this.pitchP.setBackground(Color.black);
        this.pitchP.setForeground(Color.white);
        this.pitchP.setFont(this.smallFont);
        this.pitchP.setBounds(0, 0, 400, 40);
        this.pitchP.add(this.pitch1);
        this.pitchP.add(this.pitch2);
        this.pitchP.add(this.pitch3);
        this.pitchP.add(this.pitch4);
        this.pitchP.add(this.pitch5);
        final Label label = new Label("Home Runs");
        label.setForeground(Color.green);
        label.setBounds(10, 0, 80, 20);
        (this.homeruns = new Label("0")).setFont(this.mediumFont);
        this.homeruns.setBounds(40, 20, 30, 20);
        final Label label2 = new Label("Outs Left");
        label2.setForeground(Color.red);
        label2.setBounds(100, 0, 60, 20);
        (this.outs = new Label("10")).setFont(this.mediumFont);
        this.outs.setBounds(120, 20, 30, 20);
        this.stance = new CheckboxGroup();
        (this.right = new Checkbox("Right", this.stance, true)).setBounds(170, 0, 60, 20);
        this.right.addItemListener(this);
        (this.left = new Checkbox("Left", this.stance, false)).setBounds(170, 20, 60, 20);
        this.left.addItemListener(this);
        (this.sound = new Checkbox("Sound")).setState(true);
        this.sound.setBounds(230, 20, 70, 20);
        (this.lock = new Checkbox("Auto Aim")).setBounds(230, 0, 70, 20);
        this.lock.addItemListener(this);
        final Label label3 = new Label("Timer Speed");
        label3.setBounds(310, 0, 80, 20);
        (this.counterSpeed = new Choice()).addItem("Slow");
        this.counterSpeed.addItem("Medium");
        this.counterSpeed.addItem("Fast");
        this.counterSpeed.setBounds(310, 20, 80, 20);
        this.counterSpeed.setForeground(Color.black);
        this.counterSpeed.select(1);
        (this.controlP = new Panel()).setLayout(null);
        this.controlP.setBounds(0, 360, this.width, 40);
        this.controlP.setFont(this.smallFont);
        this.controlP.setBackground(Color.black);
        this.controlP.setForeground(Color.white);
        this.controlP.add(label);
        this.controlP.add(this.homeruns);
        this.controlP.add(label2);
        this.controlP.add(this.outs);
        this.controlP.add(this.right);
        this.controlP.add(this.left);
        this.controlP.add(this.sound);
        this.controlP.add(this.lock);
        this.controlP.add(label3);
        this.controlP.add(this.counterSpeed);
        this.createPitchScreen();
        this.counter = 0;
        this.pitch = new Pitch();
        this.hit = new Hit();
        (this.bat = new Bat(this)).start();
        this.stage = 0;
        this.message = null;
        this.mouse = new int[2];
        this.swung = false;
        this.swing = new int[5];
        this.max = new int[3];
        this.imageCount = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public final void createPitchScreen() {
        this.pitchScreen = this.createImage(this.width, this.height);
        final Graphics graphics = this.pitchScreen.getGraphics();
        graphics.setColor(this.green);
        graphics.fillRect(0, 70, 400, 250);
        graphics.setColor(this.brown);
        graphics.fillRect(0, 300, 400, 20);
        graphics.fillOval(-100, 150, 600, 20);
        graphics.setColor(this.green);
        graphics.fillArc(0, 160, 400, 20, 0, 180);
        graphics.setColor(Color.black);
        for (int i = 0; i < 25; i += 2) {
            graphics.drawLine(175, i + 151, 225, i + 151);
            graphics.drawLine(175, i + 175, 225, i + 175);
            graphics.drawLine(200, i + 125, 225, i + 125);
            graphics.drawLine(i + 175, 150, i + 175, 210);
            graphics.drawLine(i + 201, 125, i + 201, 210);
        }
        graphics.setColor(Color.lightGray);
        graphics.fillRect(175, 150, 2, 60);
        graphics.fillRect(225, 125, 2, 85);
        graphics.fillRect(200, 125, 25, 2);
        graphics.fillRect(200, 125, 2, 25);
        graphics.fillRect(175, 150, 27, 2);
        graphics.setColor(this.brown);
        graphics.fillArc(150, 200, 100, 20, 0, 180);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 400, 50);
        graphics.setColor(Color.black);
        final Random random = new Random();
        for (int j = 0; j < 1000; ++j) {
            graphics.fillOval(Math.abs(random.nextInt() % 120), Math.abs(random.nextInt() % 50), 4, 4);
            graphics.fillOval(Math.abs(random.nextInt() % 120) + 280, Math.abs(random.nextInt() % 50), 4, 4);
        }
        graphics.setColor(this.brown);
        graphics.fillRect(100, 50, 200, 20);
        graphics.setColor(Color.yellow);
        graphics.fillRect(0, 50, 100, 20);
        graphics.setFont(new Font("Verdana", 1, 10));
        graphics.drawString("www.javaplayground.com", (400 - graphics.getFontMetrics().stringWidth("www.javaplayground.com")) / 2, 68);
        graphics.setColor(Color.red);
        graphics.fillRect(300, 50, 100, 20);
        graphics.drawString("Java Playground", (400 - graphics.getFontMetrics().stringWidth("Java Playground")) / 2, 58);
        graphics.setColor(Color.black);
        for (int k = 100; k < 400; k += 100) {
            graphics.drawLine(k, 50, k, 69);
        }
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        array[0] = 100;
        array[1] = 300;
        array[2] = 280;
        array[3] = 120;
        array[4] = array[0];
        array2[1] = (array2[0] = 0);
        array2[3] = (array2[2] = 50);
        array2[4] = array2[0];
        graphics.setColor(Color.cyan);
        graphics.fillPolygon(array, array2, 5);
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        this.me = null;
    }
    
    public final void animate(final int n) {
        try {
            this.repaint();
            Thread.sleep(n);
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void playSound(final int n) {
        if (this.sound.getState()) {
            this.sounds[n].play();
        }
    }
    
    public final void run() {
        try {
            this.animate(0);
            this.showStatus("Loading Images & Sounds...");
            this.imageCount = 0;
            while (this.imageCount < this.bats.length) {
                this.animate(0);
                this.bats[this.imageCount] = this.getImage(this.getDocumentBase(), "images/bat" + Integer.toString(this.imageCount + 1) + ".gif");
                this.tracker.addImage(this.bats[this.imageCount], this.imageCount);
                this.tracker.waitForAll();
                while (!this.tracker.checkAll()) {}
                ++this.imageCount;
            }
            ++this.imageCount;
            this.animate(0);
            this.field = this.getImage(this.getDocumentBase(), "images/field.gif");
            this.tracker.addImage(this.field, 1);
            this.tracker.waitForAll();
            while (!this.tracker.checkAll()) {}
            ++this.imageCount;
            this.animate(0);
            (this.sounds[0] = this.getAudioClip(this.getDocumentBase(), "sounds/start.au")).stop();
            ++this.imageCount;
            this.animate(0);
            (this.sounds[1] = this.getAudioClip(this.getDocumentBase(), "sounds/strike.au")).stop();
            ++this.imageCount;
            this.animate(0);
            (this.sounds[2] = this.getAudioClip(this.getDocumentBase(), "sounds/hit.au")).stop();
            ++this.imageCount;
            this.animate(0);
            (this.sounds[3] = this.getAudioClip(this.getDocumentBase(), "sounds/out.au")).stop();
            ++this.imageCount;
            this.animate(0);
            (this.sounds[4] = this.getAudioClip(this.getDocumentBase(), "sounds/homerun.au")).stop();
            ++this.imageCount;
            this.animate(0);
            (this.sounds[5] = this.getAudioClip(this.getDocumentBase(), "sounds/play.au")).stop();
            this.animate(0);
            this.startB.setEnabled(true);
            this.showStatus("Long Ball 3 - www.javaplayground.com");
            while (this.me != null) {
                this.animate(0);
                this.playSound(0);
                while (this.stage == 0) {
                    Thread.sleep(100L);
                }
                this.playSound(5);
                while (Integer.parseInt(this.outs.getText()) > 0) {
                    this.setCursor(this.crosshair);
                    this.stage = 1;
                    this.pitch.reset();
                    this.counter = 3;
                    while (this.counter > 0) {
                        this.animate((3 - this.counterSpeed.getSelectedIndex()) * 500);
                        --this.counter;
                    }
                    this.counter = 0;
                    this.repaint();
                    this.startPitch();
                    this.pitch.size = 0;
                    while (this.pitch.size < 40) {
                        this.pitch.increment();
                        this.animate(this.pitch.getSpeed());
                        final Pitch pitch = this.pitch;
                        pitch.size += 4;
                    }
                    this.pitch.size = 0;
                    this.animate(500);
                    if (this.swung) {
                        if (this.checkHit()) {
                            this.playSound(2);
                            this.stage = 3;
                            this.setCursor(this.hourglass);
                            for (int i = 0; i < 20; ++i) {
                                this.hit.increment(i / 10);
                                this.animate(this.hit.getSpeed());
                            }
                            if (this.hit.getY() < 140 && !this.hit.isHomerun()) {
                                this.hit.increment(20);
                            }
                            if (this.hit.isHomerun()) {
                                this.playSound(4);
                                this.homeruns.setText(Integer.toString(Integer.parseInt(this.homeruns.getText()) + 1));
                                this.displayMessage("Home Run");
                                this.displayMessage(String.valueOf(Integer.toString(this.hit.getDistance())) + " ft.");
                            }
                            else {
                                this.playSound(3);
                                this.outs.setText(Integer.toString(Integer.parseInt(this.outs.getText()) - 1));
                                this.displayMessage("Out!");
                            }
                        }
                        else {
                            this.playSound(1);
                            this.outs.setText(Integer.toString(Integer.parseInt(this.outs.getText()) - 1));
                            this.stage = 2;
                            this.displayMessage("Strike!");
                        }
                    }
                }
                this.displayMessage("Score: " + this.homeruns.getText());
                this.displayMessage("Score: " + this.homeruns.getText());
                if (Integer.parseInt(this.homeruns.getText()) > this.max[this.difficulty.getSelectedIndex()]) {
                    this.max[this.difficulty.getSelectedIndex()] = Integer.parseInt(this.homeruns.getText());
                }
                this.setCursor(this.normal);
                this.startScreen();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void displayMessage(final String message) {
        this.message = message;
        this.animate(2000);
        this.message = null;
        this.animate(0);
    }
    
    public final void drawShadowText(final String s, final int n, final int n2, final int n3, final Color color) {
        this.offG.setColor(Color.black);
        this.offG.drawString(s, n + n3, n2 + n3);
        this.offG.setColor(color);
        this.offG.drawString(s, n, n2);
    }
    
    public final void drawTitleScreen() {
        if (this.imageCount > 8) {
            this.offG.drawImage(this.field, 0, 0, this.width, this.height, null);
        }
        this.offG.setFont(this.largeFont);
        this.drawShadowText("Long Ball", (this.width - this.offG.getFontMetrics().stringWidth("Long Ball")) / 2, 120, 5, Color.white);
        this.offG.setFont(this.averageFont);
        this.offG.setColor(Color.red);
        this.offG.drawString("v2.1", this.width - this.offG.getFontMetrics().stringWidth("v2.1") - 10, 140);
        this.drawShadowText("-High Scores-", (this.width - this.offG.getFontMetrics().stringWidth("-High Scores-")) / 2, 180, 2, Color.white);
        this.offG.setFont(this.mediumFont);
        this.drawShadowText("Easy: " + Integer.toString(this.max[0]), (this.width - this.offG.getFontMetrics().stringWidth("Easy: " + Integer.toString(this.max[0]))) / 2, 210, 2, Color.green);
        this.drawShadowText("Medium: " + Integer.toString(this.max[1]), (this.width - this.offG.getFontMetrics().stringWidth("Medium: " + Integer.toString(this.max[1]))) / 2, 240, 2, Color.yellow);
        this.drawShadowText("Hard: " + Integer.toString(this.max[2]), (this.width - this.offG.getFontMetrics().stringWidth("Hard: " + Integer.toString(this.max[2]))) / 2, 270, 2, Color.red);
        if (this.imageCount < 15) {
            this.offG.setColor(Color.green);
            this.offG.drawRect(50, 10, 300, 20);
            this.offG.fillRect(50, 10, (this.imageCount + 1) * 20, 20);
            this.offG.setFont(this.smallFont);
            this.offG.setColor(Color.black);
            this.offG.drawString("Loading Images & Sounds...", (this.width - this.offG.getFontMetrics().stringWidth("Loading Images & Sounds...")) / 2, 25);
        }
        this.offG.setColor(Color.white);
        this.offG.setFont(this.smallFont);
        this.offG.drawString("Select Difficulty:", 100, 315);
        this.drawShadowText("by John Morris ©1999 - www.javaplayground.com", (this.width - this.offG.getFontMetrics().stringWidth("by John Morris ©1999 - www.javaplayground.com")) / 2, 380, 2, Color.yellow);
    }
    
    public final void drawCount() {
        Color color = Color.red;
        switch (this.counter) {
            case 2: {
                color = Color.yellow;
                break;
            }
            case 1: {
                color = Color.green;
                break;
            }
        }
        this.offG.setFont(this.bigFont);
        this.drawShadowText(Integer.toString(this.counter), (this.width - this.offG.getFontMetrics().stringWidth(Integer.toString(this.counter))) / 2, 300, 10, color);
    }
    
    public final void drawPitch() {
        if (this.pitch.getX() > 150 - (2 - this.difficulty.getSelectedIndex()) * 50 && this.pitch.getY() > 100 - (2 - this.difficulty.getSelectedIndex()) * 25 && this.pitch.getX() + this.pitch.getSize() < 150 - (2 - this.difficulty.getSelectedIndex()) * 50 + 100 + (2 - this.difficulty.getSelectedIndex()) * 100 && this.pitch.getY() + this.pitch.getSize() < 100 - (2 - this.difficulty.getSelectedIndex()) * 25 + 200 + (2 - this.difficulty.getSelectedIndex()) * 50) {
            this.offG.setColor(Color.green);
        }
        else {
            this.offG.setColor(Color.red);
        }
        this.offG.drawRect(150 - (2 - this.difficulty.getSelectedIndex()) * 50, 100 - (2 - this.difficulty.getSelectedIndex()) * 25, 100 + (2 - this.difficulty.getSelectedIndex()) * 100, 200 + (2 - this.difficulty.getSelectedIndex()) * 50);
        this.offG.drawRect(149 - (2 - this.difficulty.getSelectedIndex()) * 50, 99 - (2 - this.difficulty.getSelectedIndex()) * 25, 100 + (2 - this.difficulty.getSelectedIndex()) * 100 + 2, 200 + (2 - this.difficulty.getSelectedIndex()) * 50 + 2);
        this.offG.setColor(Color.white);
        this.offG.fillOval(this.pitch.getX() - this.pitch.getSize() / 2, this.pitch.getY() - this.pitch.getSize() / 2, this.pitch.getSize(), this.pitch.getSize());
        this.offG.setColor(Color.black);
        this.offG.drawOval(this.pitch.getX() - this.pitch.getSize() / 2, this.pitch.getY() - this.pitch.getSize() / 2, this.pitch.getSize(), this.pitch.getSize());
        if (this.lock.getState() && this.pitch.getSize() > 0) {
            this.offG.setColor(Color.red);
            this.offG.drawOval(this.pitch.getX() - this.pitch.getSize(), this.pitch.getY() - this.pitch.getSize(), 2 * this.pitch.getSize(), 2 * this.pitch.getSize());
            this.offG.drawLine(this.pitch.getX() - this.pitch.getSize(), this.pitch.getY(), this.pitch.getX() + this.pitch.getSize(), this.pitch.getY());
            this.offG.drawLine(this.pitch.getX(), this.pitch.getY() - this.pitch.getSize(), this.pitch.getX(), this.pitch.getY() + this.pitch.getSize());
        }
    }
    
    public final void drawHitScreen() {
        this.offG.drawImage(this.field, this.hit.getFieldX(), this.hit.getFieldY(), 1000, 1000, null);
        this.offG.setColor(Color.white);
        this.offG.fillOval(this.hit.getX() - this.hit.getSize() / 2, this.hit.getY() - this.hit.getSize() / 2, this.hit.getSize(), this.hit.getSize());
        this.offG.setColor(Color.black);
        this.offG.drawOval(this.hit.getX() - this.hit.getSize() / 2, this.hit.getY() - this.hit.getSize() / 2, this.hit.getSize(), this.hit.getSize());
    }
    
    public final void drawBat() {
        int n;
        int n2;
        if (this.lock.getState()) {
            n = this.pitch.getX();
            n2 = this.pitch.getY();
        }
        else {
            n = this.bat.getX();
            n2 = this.bat.getY();
        }
        if (this.stance.getSelectedCheckbox() == this.left) {
            switch (this.bat.getPic()) {
                case 1: {
                    this.offG.drawImage(this.bats[5], n + this.bats[4].getWidth(null) - this.bats[5].getWidth(null), n2, null);
                }
                case 2: {
                    this.offG.drawImage(this.bats[6], n + this.bats[4].getWidth(null) - this.bats[6].getWidth(null), n2 - this.bats[6].getHeight(null) / 2, null);
                }
                case 3: {
                    this.offG.drawImage(this.bats[7], n + this.bats[4].getWidth(null) - this.bats[7].getWidth(null), n2 - this.bats[7].getHeight(null), null);
                }
                default: {
                    this.offG.drawImage(this.bats[4], n + this.bats[4].getWidth(null) / 2, n2 - this.bats[4].getHeight(null) + 20, null);
                }
            }
        }
        else {
            switch (this.bat.getPic()) {
                case 1: {
                    this.offG.drawImage(this.bats[1], n - this.bats[0].getWidth(null), n2, null);
                }
                case 2: {
                    this.offG.drawImage(this.bats[2], n - this.bats[0].getWidth(null), n2 - this.bats[2].getHeight(null) / 2, null);
                }
                case 3: {
                    this.offG.drawImage(this.bats[3], n - this.bats[0].getWidth(null), n2 - this.bats[3].getHeight(null), null);
                }
                default: {
                    this.offG.drawImage(this.bats[0], n - 3 * this.bats[0].getWidth(null) / 2, n2 - this.bats[0].getHeight(null) + 20, null);
                }
            }
        }
    }
    
    public final void draw() {
        switch (this.stage) {
            case 0: {
                this.drawTitleScreen();
                break;
            }
            case 1: {
                this.offG.drawImage(this.pitchScreen, 0, 40, null);
                this.drawBat();
                if (this.counter > 0) {
                    this.drawCount();
                    break;
                }
                break;
            }
            case 2: {
                this.offG.drawImage(this.pitchScreen, 0, 40, null);
                this.drawPitch();
                this.drawBat();
                break;
            }
            case 3: {
                this.drawHitScreen();
                break;
            }
        }
        if (this.message != null) {
            this.offG.setFont(this.largeFont);
            this.drawShadowText(this.message, (this.width - this.offG.getFontMetrics().stringWidth(this.message)) / 2, 200, 5, Color.blue);
        }
    }
    
    public final void paint(final Graphics graphics) {
        this.draw();
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void startScreen() {
        this.stage = 0;
        this.remove(this.pitchP);
        this.remove(this.controlP);
        this.add(this.startB);
        this.add(this.difficulty);
    }
    
    public final void startPitch() {
        this.stage = 2;
        this.swung = false;
        int n = 0;
        final Checkbox selectedCheckbox = this.pitches.getSelectedCheckbox();
        if (selectedCheckbox == this.pitch2) {
            n = 1;
        }
        else if (selectedCheckbox == this.pitch3) {
            n = 2;
        }
        else if (selectedCheckbox == this.pitch4) {
            n = 3;
        }
        else if (selectedCheckbox == this.pitch5) {
            n = 4;
        }
        this.pitch.setPitch(n, this.difficulty.getSelectedIndex());
    }
    
    public final boolean checkHit() {
        if (this.swing[4] < 32 - (2 - this.difficulty.getSelectedIndex()) * 4) {
            return false;
        }
        if (this.swing[0] > this.swing[2] && this.swing[1] > this.swing[3] && this.swing[0] < this.swing[2] + this.swing[4] && this.swing[1] < this.swing[3] + this.swing[4]) {
            this.hit.setHit(5);
            return true;
        }
        if (this.swing[0] > this.swing[2] - this.swing[4] / 2 && this.swing[1] > this.swing[3] - this.swing[4] / 2 && this.swing[0] < this.swing[2] + this.swing[4] + this.swing[4] / 2 && this.swing[1] < this.swing[3] + this.swing[4] + this.swing[4] / 2) {
            this.hit.setHit(4);
            return true;
        }
        if (this.swing[0] > this.swing[2] - this.swing[4] && this.swing[1] > this.swing[3] - this.swing[4] && this.swing[0] < this.swing[2] + this.swing[4] + this.swing[4] && this.swing[1] < this.swing[3] + this.swing[4] + this.swing[4]) {
            this.hit.setHit(3);
            return true;
        }
        return false;
    }
    
    public final void startGame() {
        this.outs.setText("10");
        this.homeruns.setText("0");
        this.remove(this.difficulty);
        this.remove(this.startB);
        this.add(this.pitchP);
        this.add(this.controlP);
        this.stage = 1;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand().charAt(0)) {
            case 's': {
                this.startGame();
            }
            default: {}
        }
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final Checkbox checkbox = (Checkbox)itemEvent.getItemSelectable();
        if (checkbox == this.left || checkbox == this.right || checkbox == this.lock) {
            this.animate(0);
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.stage == 1 || this.stage == 2) {
            this.bat.go();
            if (this.lock.getState()) {
                this.swing[0] = this.pitch.getX() + this.pitch.getSize() / 2;
                this.swing[1] = this.pitch.getY() + this.pitch.getSize() / 2;
            }
            else {
                this.swing[0] = mouseEvent.getX();
                this.swing[1] = mouseEvent.getY();
            }
            this.swing[2] = this.pitch.getX();
            this.swing[3] = this.pitch.getY();
            this.swing[4] = this.pitch.getSize();
            this.swung = true;
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (this.stage == 1 || this.stage == 2) {
            this.bat.setCoords(mouseEvent.getX(), mouseEvent.getY());
            this.animate(0);
        }
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public LongBall2_1() {
        this.green = new Color(0, 128, 0);
        this.brown = new Color(128, 64, 0);
        this.smallFont = new Font("Verdana", 1, 12);
        this.mediumFont = new Font("Verdana", 1, 20);
        this.averageFont = new Font("Verdana", 1, 40);
        this.largeFont = new Font("Verdana", 1, 80);
        this.bigFont = new Font("Verdana", 1, 300);
        this.crosshair = Cursor.getPredefinedCursor(1);
        this.hourglass = Cursor.getPredefinedCursor(3);
        this.normal = Cursor.getPredefinedCursor(0);
    }
}
