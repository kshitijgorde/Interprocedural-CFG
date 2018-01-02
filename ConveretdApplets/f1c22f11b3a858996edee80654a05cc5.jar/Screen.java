import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Screen extends Canvas implements MouseListener
{
    int[] xpos;
    int[] ypos;
    boolean succes;
    URL url;
    int iqptint;
    boolean load;
    private Iqapplet iq;
    private Iqquiz frame;
    int xpo;
    int ypo;
    Image img1;
    Image img2;
    Image img3;
    Image img4;
    Image img5;
    Image img6;
    Image img7;
    Font questions;
    Font answers;
    Font title;
    Font letters;
    double iqpt;
    
    public Screen(final Iqapplet iq, final Iqquiz frame) {
        this.xpos = new int[5];
        this.ypos = new int[5];
        this.succes = false;
        this.iq = iq;
        this.load = true;
        this.repaint();
        this.frame = frame;
        this.addMouseListener(this);
        this.url = iq.getDocumentBase();
        this.fetchimages();
        this.load = false;
        this.questions = new Font("Verdana", 1, 16);
        this.answers = new Font("Verdana", 0, 16);
        this.title = new Font("Verdana", 1, 40);
        this.letters = new Font("Verdana", 0, 20);
    }
    
    public void fetchimages() {
        this.img1 = this.iq.getImage(this.iq.getDocumentBase(), "img1.gif");
        this.img2 = this.iq.getImage(this.iq.getDocumentBase(), "img2.gif");
        this.img3 = this.iq.getImage(this.iq.getDocumentBase(), "img3.gif");
        this.img4 = this.iq.getImage(this.iq.getDocumentBase(), "img4.gif");
        this.img5 = this.iq.getImage(this.iq.getDocumentBase(), "img5.gif");
        this.img6 = this.iq.getImage(this.iq.getDocumentBase(), "img6.gif");
        this.img7 = this.iq.getImage(this.iq.getDocumentBase(), "img7.gif");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.img1, 0);
        mediaTracker.addImage(this.img2, 0);
        mediaTracker.addImage(this.img3, 0);
        mediaTracker.addImage(this.img4, 0);
        mediaTracker.addImage(this.img5, 0);
        mediaTracker.addImage(this.img6, 0);
        mediaTracker.addImage(this.img7, 0);
        try {
            mediaTracker.waitForAll(0L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.xpo = mouseEvent.getX();
        this.ypo = mouseEvent.getY();
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void paint(final Graphics graphics) {
        if (this.load) {
            graphics.setColor(Color.red);
            graphics.drawString("Loading Pictures", 50, 50);
        }
        if (this.frame.stoppressed) {
            graphics.setColor(Color.white);
            graphics.setFont(this.title);
            graphics.drawString("Good answers: " + this.frame.good, 50, 50);
            this.iqpt = this.frame.good / 1.4 * 2.0 * 10.0;
            this.iqptint = (int)this.iqpt;
            graphics.drawString("Your IQ: " + this.iqptint, 50, 100);
            graphics.setFont(this.letters);
            graphics.drawString("Remember that this is not a real IQ test!", 50, 150);
            graphics.drawString("The result is just for fun.", 50, 200);
        }
        if (!this.frame.stoppressed) {
            if (!this.frame.pause) {
                if (this.frame.question == 0) {
                    graphics.setFont(this.title);
                    graphics.setColor(Color.white);
                    graphics.drawString("Iq Quiz V1.0", 100, 100);
                    graphics.setFont(this.letters);
                    graphics.drawString("Made By Bavo Bruylandt", 80, 150);
                    graphics.drawString("Created for http://homemadejava.com", 80, 200);
                    if (this.iq.reg) {
                        graphics.drawString("Registered to " + this.iq.companyname, 80, 250);
                    }
                    else {
                        graphics.drawString("Unregistered Version", 80, 250);
                    }
                }
                if (this.frame.question == 1) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the next number?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("2, 4, 6, 8, .", 50, 90);
                }
                if (this.frame.question == 2) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What word doesn't fit?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("1. Diamond", 50, 90);
                    graphics.drawString("2. Exotic", 50, 130);
                    graphics.drawString("3. Friend", 50, 170);
                    graphics.drawString("4. Natural", 50, 210);
                    graphics.drawString("5. Generous", 50, 250);
                }
                if (this.frame.question == 3) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the next number?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("2, 5, 10, 17, 26, .", 50, 90);
                }
                if (this.frame.question == 4) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What figure matches these?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img1, 50, 60, this);
                }
                if (this.frame.question == 5) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What animal doesn't match?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("1. Shark", 50, 90);
                    graphics.drawString("2. Whale", 50, 130);
                    graphics.drawString("3. Cod", 50, 170);
                    graphics.drawString("4. Tunny", 50, 210);
                    graphics.drawString("5. Goldfish", 50, 250);
                }
                if (this.frame.question == 6) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the word?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("IO(FASHION)SA", 50, 90);
                    graphics.drawString("LO(S..L..W)AW", 50, 130);
                }
                if (this.frame.question == 7) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What word is not a componist?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("1. ZOTRAM", 50, 90);
                    graphics.drawString("2. SATSURS", 50, 130);
                    graphics.drawString("3. DILVIAV", 50, 170);
                    graphics.drawString("4. MALOSO", 50, 210);
                }
                if (this.frame.question == 8) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the word?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("SURPRISE(RULE)ELEPHANT", 50, 90);
                    graphics.drawString("CONFIRM(....)DESTINY", 50, 130);
                }
                if (this.frame.question == 9) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("Image1 relates to image2 as image3 relates to", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img2, 50, 60, this);
                }
                if (this.frame.question == 10) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What are the next letters?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawString("G  E  K  O  .", 50, 90);
                    graphics.drawString("A  I  J  M  .", 50, 130);
                }
                if (this.frame.question == 11) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the missing number?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img4, 50, 60, this);
                }
                if (this.frame.question == 12) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the missing figure?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img5, 50, 60, this);
                }
                if (this.frame.question == 13) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What number should be next?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img6, 50, 60, this);
                }
                if (this.frame.question == 14) {
                    graphics.setFont(this.questions);
                    graphics.setColor(Color.white);
                    graphics.drawString("What is the missing number?", 50, 40);
                    graphics.setFont(this.answers);
                    graphics.drawImage(this.img7, 50, 60, this);
                }
                if (this.frame.question > 0 && !this.frame.answer[this.frame.question].equals("")) {
                    graphics.setColor(Color.white);
                    graphics.drawString("You answered " + this.frame.answer[this.frame.question], 50, 292);
                }
            }
            graphics.setFont(new Font("Impact", 0, 10));
            graphics.setColor(Color.red);
        }
        if (this.frame.pause) {
            graphics.setColor(Color.white);
            graphics.setFont(this.title);
            graphics.drawString("Paused", 50, 50);
        }
        if (this.succes) {}
    }
}
