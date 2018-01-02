import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01Intro extends Canvas implements Runnable
{
    public static final int ANIMATION_SCENES = 2;
    public static final int[] SCENE_FRAMES;
    public static final int[] SCENE_FRAME_LENGTH;
    Frame parent;
    Thread th;
    int animationScene;
    int animationFrame;
    Image offScreenImage;
    Graphics offScreenGr;
    Image bslogo;
    
    public JavaMiner102V01Intro(final Image bslogo) {
        this.th = new Thread(this);
        this.setBackground(Color.black);
        this.bslogo = bslogo;
        this.animationScene = 0;
        this.animationFrame = 0;
    }
    
    public void update(final Graphics graphics) {
        if (this.offScreenImage == null) {
            this.initDoubleBuffering();
        }
        switch (this.animationScene) {
            case 0: {
                this.offScreenGr.setColor(Color.black);
                this.offScreenGr.fillRect(0, 0, 640, 480);
                this.offScreenGr.setColor(Color.green);
                final Font font = new Font("Helvetica", 1, 72);
                final Graphics offScreenGr = this.offScreenGr;
                offScreenGr.setFont(font);
                offScreenGr.drawString("Burgsoft", (640 - this.getFontMetrics(font).stringWidth("Burgsoft")) / 2, 150);
                final Font font2 = new Font("Helvetica", 2, 48);
                final Graphics offScreenGr2 = this.offScreenGr;
                offScreenGr2.setFont(font2);
                offScreenGr2.drawString("proudly presents", (640 - this.getFontMetrics(font2).stringWidth("proudly presents")) / 2, 250);
                break;
            }
            case 1: {
                this.offScreenGr.setColor(Color.black);
                this.offScreenGr.fillRect(0, 0, 640, 480);
                final Font font3 = new Font("TimesRoman", 2, 48);
                this.offScreenGr.setColor(Color.green);
                final int n = 480 - this.animationFrame;
                final Graphics offScreenGr3 = this.offScreenGr;
                offScreenGr3.setFont(font3);
                offScreenGr3.drawString("JavaMiner", (640 - this.getFontMetrics(font3).stringWidth("JavaMiner")) / 2, n);
                final Font font4 = new Font("Courier", 1, 32);
                final int n2 = 570 - this.animationFrame;
                final Graphics offScreenGr4 = this.offScreenGr;
                offScreenGr4.setFont(font4);
                offScreenGr4.drawString("A Burgsoft Arcade Game", (640 - this.getFontMetrics(font4).stringWidth("A Burgsoft Arcade Game")) / 2, n2);
                final Font font5 = new Font("Dialog", 0, 24);
                final int n3 = 650 - this.animationFrame;
                final Graphics offScreenGr5 = this.offScreenGr;
                offScreenGr5.setFont(font5);
                offScreenGr5.drawString("Copyright (c) 1999-2000 - All rights reserved.", (640 - this.getFontMetrics(font5).stringWidth("Copyright (c) 1999-2000 - All rights reserved.")) / 2, n3);
                this.offScreenGr.drawImage(this.bslogo, 270, 670 - this.animationFrame, this);
                final int n4 = 800 - this.animationFrame;
                final Graphics offScreenGr6 = this.offScreenGr;
                offScreenGr6.setFont(font5);
                offScreenGr6.drawString("www.burgsoft.de", (640 - this.getFontMetrics(font5).stringWidth("www.burgsoft.de")) / 2, n4);
                break;
            }
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreenImage == null) {
            this.initDoubleBuffering();
        }
        graphics.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    private void initDoubleBuffering() {
        this.offScreenImage = this.createImage(640, 480);
        this.offScreenGr = this.offScreenImage.getGraphics();
        this.repaint();
    }
    
    private void resetBackground(final Color color) {
        this.offScreenGr.setColor(color);
        this.offScreenGr.fillRect(0, 0, 640, 480);
    }
    
    private void drawStringCentered(final Font font, final String s, final int n, final Graphics graphics) {
        graphics.setFont(font);
        graphics.drawString(s, (640 - this.getFontMetrics(font).stringWidth(s)) / 2, n);
    }
    
    public void run() {
        while (true) {
            for (int i = 0; i < 2; ++i) {
                this.animationScene = i;
                for (int j = 0; j < JavaMiner102V01Intro.SCENE_FRAMES[this.animationScene]; ++j) {
                    this.animationFrame = j;
                    this.repaint();
                    try {
                        Thread.sleep(JavaMiner102V01Intro.SCENE_FRAME_LENGTH[this.animationScene]);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
    }
    
    public void startIntro() {
        this.th.start();
    }
    
    public void stopIntro() {
        this.th.stop();
    }
    
    static {
        SCENE_FRAMES = new int[] { 1, 800 };
        SCENE_FRAME_LENGTH = new int[] { 3000, 15 };
    }
}
