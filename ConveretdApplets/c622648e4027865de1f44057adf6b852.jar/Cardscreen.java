import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cardscreen extends Canvas implements Runnable, MouseListener
{
    boolean sound;
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    Card card7;
    Card card8;
    Card card9;
    Card card10;
    Card card11;
    Card card12;
    Random rg;
    String drip;
    Image gamo;
    Image welldone;
    int shaky1;
    int shaky2;
    int shaky3;
    boolean gameover;
    int[] temp;
    boolean hidetemp;
    boolean start;
    boolean wrong;
    int level;
    int successes;
    int fieldcheck;
    int minus;
    int k;
    int score;
    int xpo;
    int ypo;
    boolean easy;
    int value1;
    int value2;
    int field1;
    boolean perfect;
    int field2;
    Thread messageThread;
    Image img1;
    Image img2;
    Image img3;
    Image img4;
    Image img5;
    Image img6;
    Image img7;
    Image img8;
    Image img9;
    Image img10;
    Image img11;
    Image img12;
    Image img13;
    Image[] imtemp;
    private Memoryapplet mem;
    String se_right;
    String se_clicked;
    String se_timeup;
    String se_wrong;
    
    public Cardscreen(final Memoryapplet mem, final String s) {
        this.sound = true;
        this.card1 = new Card();
        this.card2 = new Card();
        this.card3 = new Card();
        this.card4 = new Card();
        this.card5 = new Card();
        this.card6 = new Card();
        this.card7 = new Card();
        this.card8 = new Card();
        this.card9 = new Card();
        this.card10 = new Card();
        this.card11 = new Card();
        this.card12 = new Card();
        this.rg = new Random();
        this.gameover = false;
        this.temp = new int[13];
        this.hidetemp = false;
        this.start = false;
        this.wrong = true;
        this.level = 1;
        this.successes = 0;
        this.k = 360;
        this.score = 0;
        this.easy = true;
        this.value1 = 0;
        this.value2 = 0;
        this.field1 = 0;
        this.perfect = true;
        this.field2 = 1000;
        this.imtemp = new Image[6];
        Time.geof = true;
        if (this.messageThread == null) {
            (this.messageThread = new Thread(this)).start();
        }
        if (this.easy) {
            this.minus = 3;
        }
        else {
            this.minus = 6;
        }
        this.setCursor(new Cursor(3));
        this.mem = mem;
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.se_right = mem.getParameter("se_right");
        this.se_clicked = mem.getParameter("se_clicked");
        this.se_timeup = mem.getParameter("se_timeup");
        this.se_wrong = mem.getParameter("se_wrong");
        this.welldone = mem.getImage(mem.getDocumentBase(), "welldone.gif");
        this.gamo = mem.getImage(mem.getDocumentBase(), "gamo.jpg");
        if (s == "start1") {
            this.imtemp[0] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card1"));
            this.imtemp[1] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card2"));
            this.imtemp[2] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card3"));
            this.imtemp[3] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card4"));
            this.imtemp[4] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card5"));
            this.imtemp[5] = mem.getImage(mem.getDocumentBase(), mem.getParameter("card6"));
        }
        if (s == "start2") {
            this.imtemp[0] = mem.getImage(mem.getDocumentBase(), "img1a.gif");
            this.imtemp[1] = mem.getImage(mem.getDocumentBase(), "img2a.gif");
            this.imtemp[2] = mem.getImage(mem.getDocumentBase(), "img3a.gif");
            this.imtemp[3] = mem.getImage(mem.getDocumentBase(), "img4a.gif");
            this.imtemp[4] = mem.getImage(mem.getDocumentBase(), "img5a.gif");
            this.imtemp[5] = mem.getImage(mem.getDocumentBase(), "img6a.gif");
        }
        if (s == "start3") {
            this.imtemp[0] = mem.getImage(mem.getDocumentBase(), "img1b.gif");
            this.imtemp[1] = mem.getImage(mem.getDocumentBase(), "img2b.gif");
            this.imtemp[2] = mem.getImage(mem.getDocumentBase(), "img3b.gif");
            this.imtemp[3] = mem.getImage(mem.getDocumentBase(), "img4b.gif");
            this.imtemp[4] = mem.getImage(mem.getDocumentBase(), "img5b.gif");
            this.imtemp[5] = mem.getImage(mem.getDocumentBase(), "img6b.gif");
        }
        this.img13 = mem.getImage(mem.getDocumentBase(), "img7.gif");
        mediaTracker.addImage(this.gamo, 0);
        mediaTracker.addImage(this.imtemp[0], 0);
        mediaTracker.addImage(this.imtemp[1], 0);
        mediaTracker.addImage(this.imtemp[2], 0);
        mediaTracker.addImage(this.imtemp[3], 0);
        mediaTracker.addImage(this.imtemp[4], 0);
        mediaTracker.addImage(this.imtemp[5], 0);
        mediaTracker.addImage(this.welldone, 0);
        try {
            mediaTracker.waitForAll(0L);
        }
        catch (InterruptedException ex) {}
        mem.getDocumentBase();
        this.addMouseListener(this);
        this.randomize1();
        this.randomize2();
        this.setValues();
        this.colors();
        this.quickshow();
        this.start = true;
        this.messageThread.resume();
    }
    
    public void changecards(final String s) {
        if (s == "start1") {
            this.imtemp[0] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card1"));
            this.imtemp[1] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card2"));
            this.imtemp[2] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card3"));
            this.imtemp[3] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card4"));
            this.imtemp[4] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card5"));
            this.imtemp[5] = this.mem.getImage(this.mem.getDocumentBase(), this.mem.getParameter("card6"));
        }
        if (s == "start2") {
            this.imtemp[0] = this.mem.getImage(this.mem.getDocumentBase(), "img1a.gif");
            this.imtemp[1] = this.mem.getImage(this.mem.getDocumentBase(), "img2a.gif");
            this.imtemp[2] = this.mem.getImage(this.mem.getDocumentBase(), "img3a.gif");
            this.imtemp[3] = this.mem.getImage(this.mem.getDocumentBase(), "img4a.gif");
            this.imtemp[4] = this.mem.getImage(this.mem.getDocumentBase(), "img5a.gif");
            this.imtemp[5] = this.mem.getImage(this.mem.getDocumentBase(), "img6a.gif");
        }
        if (s == "start3") {
            this.imtemp[0] = this.mem.getImage(this.mem.getDocumentBase(), "img1b.gif");
            this.imtemp[1] = this.mem.getImage(this.mem.getDocumentBase(), "img2b.gif");
            this.imtemp[2] = this.mem.getImage(this.mem.getDocumentBase(), "img3b.gif");
            this.imtemp[3] = this.mem.getImage(this.mem.getDocumentBase(), "img4b.gif");
            this.imtemp[4] = this.mem.getImage(this.mem.getDocumentBase(), "img5b.gif");
            this.imtemp[5] = this.mem.getImage(this.mem.getDocumentBase(), "img6b.gif");
        }
    }
    
    public void checkduo() {
        if (this.value1 == this.value2 && this.field1 != this.field2) {
            if (this.sound) {
                this.playsound(this.se_right);
            }
            ++this.successes;
            this.score += 20;
            if (this.field1 == 1) {
                this.card1.showcard = true;
            }
            if (this.field1 == 2) {
                this.card2.showcard = true;
            }
            if (this.field1 == 3) {
                this.card3.showcard = true;
            }
            if (this.field1 == 4) {
                this.card4.showcard = true;
            }
            if (this.field1 == 5) {
                this.card5.showcard = true;
            }
            if (this.field1 == 6) {
                this.card6.showcard = true;
            }
            if (this.field1 == 7) {
                this.card7.showcard = true;
            }
            if (this.field1 == 8) {
                this.card8.showcard = true;
            }
            if (this.field1 == 9) {
                this.card9.showcard = true;
            }
            if (this.field1 == 10) {
                this.card10.showcard = true;
            }
            if (this.field1 == 11) {
                this.card11.showcard = true;
            }
            if (this.field1 == 12) {
                this.card12.showcard = true;
            }
            if (this.field2 == 1) {
                this.card1.showcard = true;
            }
            if (this.field2 == 2) {
                this.card2.showcard = true;
            }
            if (this.field2 == 3) {
                this.card3.showcard = true;
            }
            if (this.field2 == 4) {
                this.card4.showcard = true;
            }
            if (this.field2 == 5) {
                this.card5.showcard = true;
            }
            if (this.field2 == 6) {
                this.card6.showcard = true;
            }
            if (this.field2 == 7) {
                this.card7.showcard = true;
            }
            if (this.field2 == 8) {
                this.card8.showcard = true;
            }
            if (this.field2 == 9) {
                this.card9.showcard = true;
            }
            if (this.field2 == 10) {
                this.card10.showcard = true;
            }
            if (this.field2 == 11) {
                this.card11.showcard = true;
            }
            if (this.field2 == 12) {
                this.card12.showcard = true;
            }
            if (this.successes == 6) {
                this.score += this.k;
                final Graphics graphics = this.getGraphics();
                if (this.perfect) {
                    this.score += 200;
                }
                graphics.setFont(new Font("Verdana", 1, 30));
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 400, 400);
                graphics.setColor(Color.red);
                graphics.drawImage(this.welldone, 80, 50, this);
                if (this.perfect) {
                    graphics.drawString("Perfect Bonus : 200 pts.", 45, 300);
                }
                graphics.drawString("Get Ready For Level " + (this.level + 1), 50, 250);
                graphics.drawString("Score : " + this.score, 110, 200);
                this.repaint();
                try {
                    Thread.sleep(3000L);
                }
                catch (InterruptedException ex) {}
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 400, 400);
                this.refill();
            }
            this.wrong = false;
        }
        else {
            this.perfect = false;
            if (this.sound) {
                this.playsound(this.se_wrong);
            }
        }
    }
    
    public void checkout1(final int n) {
        for (int i = 1; i < 7; ++i) {
            if (this.temp[n] == this.temp[i] && i != n) {
                this.temp[n] = this.rg.randomInt(1, 6);
                this.checkout1(n);
            }
        }
    }
    
    public void checkout2(final int n) {
        for (int i = 7; i < 13; ++i) {
            if (this.temp[n] == this.temp[i] && i != n) {
                this.temp[n] = this.rg.randomInt(1, 6);
                this.checkout2(n);
            }
        }
    }
    
    public void colors() {
        for (int i = 1; i < 7; ++i) {
            if (this.card1.cardvalue == i) {
                this.img1 = this.imtemp[i - 1];
            }
        }
        for (int j = 1; j < 7; ++j) {
            if (this.card2.cardvalue == j) {
                this.img2 = this.imtemp[j - 1];
            }
        }
        for (int k = 1; k < 7; ++k) {
            if (this.card3.cardvalue == k) {
                this.img3 = this.imtemp[k - 1];
            }
        }
        for (int l = 1; l < 7; ++l) {
            if (this.card4.cardvalue == l) {
                this.img4 = this.imtemp[l - 1];
            }
        }
        for (int n = 1; n < 7; ++n) {
            if (this.card5.cardvalue == n) {
                this.img5 = this.imtemp[n - 1];
            }
        }
        for (int n2 = 1; n2 < 7; ++n2) {
            if (this.card6.cardvalue == n2) {
                this.img6 = this.imtemp[n2 - 1];
            }
        }
        for (int n3 = 1; n3 < 7; ++n3) {
            if (this.card7.cardvalue == n3) {
                this.img7 = this.imtemp[n3 - 1];
            }
        }
        for (int n4 = 1; n4 < 7; ++n4) {
            if (this.card8.cardvalue == n4) {
                this.img8 = this.imtemp[n4 - 1];
            }
        }
        for (int n5 = 1; n5 < 7; ++n5) {
            if (this.card9.cardvalue == n5) {
                this.img9 = this.imtemp[n5 - 1];
            }
        }
        for (int n6 = 1; n6 < 7; ++n6) {
            if (this.card10.cardvalue == n6) {
                this.img10 = this.imtemp[n6 - 1];
            }
        }
        for (int n7 = 1; n7 < 7; ++n7) {
            if (this.card11.cardvalue == n7) {
                this.img11 = this.imtemp[n7 - 1];
            }
        }
        for (int n8 = 1; n8 < 7; ++n8) {
            if (this.card12.cardvalue == n8) {
                this.img12 = this.imtemp[n8 - 1];
            }
        }
    }
    
    public void gameover() {
        this.gameover = true;
    }
    
    public int getField(final int n, final int n2) {
        int n3 = 0;
        for (int i = 1; i < 5; ++i) {
            ++n3;
            if (n > i * 70 && n < i * 70 + 50 && n2 > 50 && n2 < 110) {
                return n3;
            }
        }
        for (int j = 1; j < 5; ++j) {
            ++n3;
            if (n > j * 70 && n < j * 70 + 50 && n2 > 150 && n2 < 210) {
                return n3;
            }
        }
        for (int k = 1; k < 5; ++k) {
            ++n3;
            if (n > k * 70 && n < k * 70 + 50 && n2 > 250 && n2 < 310) {
                return n3;
            }
        }
        return ++n3;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.gameover) {
            this.restart();
        }
        if (!this.start && !this.hidetemp) {
            this.xpo = mouseEvent.getX();
            this.ypo = mouseEvent.getY();
            this.fieldcheck = this.getField(this.xpo, this.ypo);
            if (this.fieldcheck != 13) {
                if (this.field1 == 0) {
                    if (this.sound) {
                        this.playsound(this.se_clicked);
                    }
                    this.field1 = this.getField(this.xpo, this.ypo);
                    if (this.field1 == 1) {
                        this.card1.showtemp = true;
                        this.value1 = this.card1.cardvalue;
                    }
                    if (this.field1 == 2) {
                        this.card2.showtemp = true;
                        this.value1 = this.card2.cardvalue;
                    }
                    if (this.field1 == 3) {
                        this.card3.showtemp = true;
                        this.value1 = this.card3.cardvalue;
                    }
                    if (this.field1 == 4) {
                        this.card4.showtemp = true;
                        this.value1 = this.card4.cardvalue;
                    }
                    if (this.field1 == 5) {
                        this.card5.showtemp = true;
                        this.value1 = this.card5.cardvalue;
                    }
                    if (this.field1 == 6) {
                        this.card6.showtemp = true;
                        this.value1 = this.card6.cardvalue;
                    }
                    if (this.field1 == 7) {
                        this.card7.showtemp = true;
                        this.value1 = this.card7.cardvalue;
                    }
                    if (this.field1 == 8) {
                        this.card8.showtemp = true;
                        this.value1 = this.card8.cardvalue;
                    }
                    if (this.field1 == 9) {
                        this.card9.showtemp = true;
                        this.value1 = this.card9.cardvalue;
                    }
                    if (this.field1 == 10) {
                        this.card10.showtemp = true;
                        this.value1 = this.card10.cardvalue;
                    }
                    if (this.field1 == 11) {
                        this.card11.showtemp = true;
                        this.value1 = this.card11.cardvalue;
                    }
                    if (this.field1 == 12) {
                        this.card12.showtemp = true;
                        this.value1 = this.card12.cardvalue;
                    }
                }
                else {
                    this.field2 = this.getField(this.xpo, this.ypo);
                    if (this.field2 == 1) {
                        this.card1.showtemp = true;
                        this.value2 = this.card1.cardvalue;
                    }
                    if (this.field2 == 2) {
                        this.card2.showtemp = true;
                        this.value2 = this.card2.cardvalue;
                    }
                    if (this.field2 == 3) {
                        this.card3.showtemp = true;
                        this.value2 = this.card3.cardvalue;
                    }
                    if (this.field2 == 4) {
                        this.card4.showtemp = true;
                        this.value2 = this.card4.cardvalue;
                    }
                    if (this.field2 == 5) {
                        this.card5.showtemp = true;
                        this.value2 = this.card5.cardvalue;
                    }
                    if (this.field2 == 6) {
                        this.card6.showtemp = true;
                        this.value2 = this.card6.cardvalue;
                    }
                    if (this.field2 == 7) {
                        this.card7.showtemp = true;
                        this.value2 = this.card7.cardvalue;
                    }
                    if (this.field2 == 8) {
                        this.card8.showtemp = true;
                        this.value2 = this.card8.cardvalue;
                    }
                    if (this.field2 == 9) {
                        this.card9.showtemp = true;
                        this.value2 = this.card9.cardvalue;
                    }
                    if (this.field2 == 10) {
                        this.card10.showtemp = true;
                        this.value2 = this.card10.cardvalue;
                    }
                    if (this.field2 == 11) {
                        this.card11.showtemp = true;
                        this.value2 = this.card11.cardvalue;
                    }
                    if (this.field2 == 12) {
                        this.card12.showtemp = true;
                        this.value2 = this.card12.cardvalue;
                    }
                    this.hidetemp = true;
                    this.checkduo();
                    this.messageThread.resume();
                    this.field1 = 0;
                }
            }
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.getSize();
        if (this.k > 0) {
            if (this.card1.showcard) {
                graphics.drawImage(this.img1, this.card1.cardxco, this.card1.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card1.cardxco, this.card1.cardyco, this);
            }
            if (this.card2.showcard) {
                graphics.drawImage(this.img2, this.card2.cardxco, this.card2.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card2.cardxco, this.card2.cardyco, this);
            }
            if (this.card3.showcard) {
                graphics.drawImage(this.img3, this.card3.cardxco, this.card3.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card3.cardxco, this.card3.cardyco, this);
            }
            if (this.card4.showcard) {
                graphics.drawImage(this.img4, this.card4.cardxco, this.card4.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card4.cardxco, this.card4.cardyco, this);
            }
            if (this.card5.showcard) {
                graphics.drawImage(this.img5, this.card5.cardxco, this.card5.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card5.cardxco, this.card5.cardyco, this);
            }
            if (this.card6.showcard) {
                graphics.drawImage(this.img6, this.card6.cardxco, this.card6.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card6.cardxco, this.card6.cardyco, this);
            }
            if (this.card7.showcard) {
                graphics.drawImage(this.img7, this.card7.cardxco, this.card7.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card7.cardxco, this.card7.cardyco, this);
            }
            if (this.card8.showcard) {
                graphics.drawImage(this.img8, this.card8.cardxco, this.card8.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card8.cardxco, this.card8.cardyco, this);
            }
            if (this.card9.showcard) {
                graphics.drawImage(this.img9, this.card9.cardxco, this.card9.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card9.cardxco, this.card9.cardyco, this);
            }
            if (this.card10.showcard) {
                graphics.drawImage(this.img10, this.card10.cardxco, this.card10.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card10.cardxco, this.card10.cardyco, this);
            }
            if (this.card11.showcard) {
                graphics.drawImage(this.img11, this.card11.cardxco, this.card11.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card11.cardxco, this.card11.cardyco, this);
            }
            if (this.card12.showcard) {
                graphics.drawImage(this.img12, this.card12.cardxco, this.card12.cardyco, this);
            }
            else {
                graphics.drawImage(this.img13, this.card12.cardxco, this.card12.cardyco, this);
            }
            if (this.card1.showtemp) {
                graphics.drawImage(this.img1, this.card1.cardxco, this.card3.cardyco, this);
            }
            if (this.card2.showtemp) {
                graphics.drawImage(this.img2, this.card2.cardxco, this.card2.cardyco, this);
            }
            if (this.card3.showtemp) {
                graphics.drawImage(this.img3, this.card3.cardxco, this.card3.cardyco, this);
            }
            if (this.card4.showtemp) {
                graphics.drawImage(this.img4, this.card4.cardxco, this.card4.cardyco, this);
            }
            if (this.card5.showtemp) {
                graphics.drawImage(this.img5, this.card5.cardxco, this.card5.cardyco, this);
            }
            if (this.card6.showtemp) {
                graphics.drawImage(this.img6, this.card6.cardxco, this.card6.cardyco, this);
            }
            if (this.card7.showtemp) {
                graphics.drawImage(this.img7, this.card7.cardxco, this.card7.cardyco, this);
            }
            if (this.card8.showtemp) {
                graphics.drawImage(this.img8, this.card8.cardxco, this.card8.cardyco, this);
            }
            if (this.card9.showtemp) {
                graphics.drawImage(this.img9, this.card9.cardxco, this.card9.cardyco, this);
            }
            if (this.card10.showtemp) {
                graphics.drawImage(this.img10, this.card10.cardxco, this.card10.cardyco, this);
            }
            if (this.card11.showtemp) {
                graphics.drawImage(this.img11, this.card11.cardxco, this.card11.cardyco, this);
            }
            if (this.card12.showtemp) {
                graphics.drawImage(this.img12, this.card12.cardxco, this.card12.cardyco, this);
            }
        }
        else if (this.k < 0) {
            if (this.sound) {
                this.playsound(this.se_timeup);
            }
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 400, 400);
            graphics.drawImage(this.gamo, 100, 50, this);
            graphics.setColor(Color.green);
            graphics.drawString("Click To Restart", 170, 250);
            graphics.drawString("Final Score " + this.score, 175, 300);
            this.gameover();
            try {
                Thread.sleep(4000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void playsound(final String s) {
        this.mem.play(this.mem.getDocumentBase(), s);
    }
    
    public void quickshow() {
        this.hidetemp = true;
        this.start = true;
        this.card1.showtemp = true;
        this.card2.showtemp = true;
        this.card3.showtemp = true;
        this.card4.showtemp = true;
        this.card5.showtemp = true;
        this.card6.showtemp = true;
        this.card7.showtemp = true;
        this.card8.showtemp = true;
        this.card9.showtemp = true;
        this.card10.showtemp = true;
        this.card11.showtemp = true;
        this.card12.showtemp = true;
    }
    
    public void randomize1() {
        for (int i = 1; i < 7; ++i) {
            this.temp[i] = this.rg.randomInt(1, 6);
            this.checkout1(i);
        }
    }
    
    public void randomize2() {
        for (int i = 7; i < 13; ++i) {
            this.temp[i] = this.rg.randomInt(1, 6);
            this.checkout2(i);
        }
    }
    
    public void refill() {
        Time.geof = true;
        this.setCursor(new Cursor(3));
        ++this.level;
        this.k = 360;
        this.perfect = true;
        ++this.minus;
        this.successes = 0;
        for (int i = 0; i < 13; ++i) {
            this.temp[i] = 0;
        }
        Time.timeup = false;
        this.randomize1();
        this.randomize2();
        this.shaky1 = this.rg.randomInt(1, 6);
        this.shaky2 = this.rg.randomInt(7, 12);
        this.shaky3 = this.temp[this.shaky1];
        this.temp[this.shaky1] = this.temp[this.shaky2];
        this.temp[this.shaky2] = this.shaky3;
        this.setValues();
        this.colors();
        this.messageThread.suspend();
        this.quickshow();
        this.repaint();
    }
    
    public void restart() {
        Time.geof = true;
        final Graphics graphics = this.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 500, 500);
        this.level = 1;
        this.k = 360;
        this.perfect = true;
        if (this.easy) {
            this.minus = 3;
        }
        else {
            this.minus = 6;
        }
        this.score = 0;
        this.successes = 0;
        for (int i = 0; i < 13; ++i) {
            this.temp[i] = 0;
        }
        Time.timeup = false;
        this.randomize1();
        this.randomize2();
        this.shaky1 = this.rg.randomInt(1, 6);
        this.shaky2 = this.rg.randomInt(7, 12);
        this.shaky3 = this.temp[this.shaky1];
        this.temp[this.shaky1] = this.temp[this.shaky2];
        this.temp[this.shaky2] = this.shaky3;
        this.setValues();
        this.colors();
        this.messageThread.suspend();
        this.quickshow();
        this.repaint();
        this.gameover = false;
        this.messageThread.resume();
    }
    
    public void run() {
        while (true) {
            if (this.k < 0) {
                this.repaint();
            }
            if (this.hidetemp) {
                this.card1.showtemp = false;
                this.card2.showtemp = false;
                this.card3.showtemp = false;
                this.card4.showtemp = false;
                this.card5.showtemp = false;
                this.card6.showtemp = false;
                this.card7.showtemp = false;
                this.card8.showtemp = false;
                this.card9.showtemp = false;
                this.card10.showtemp = false;
                this.card11.showtemp = false;
                this.card12.showtemp = false;
                this.hidetemp = false;
            }
            if (this.start) {
                try {
                    Thread.sleep(4000L);
                }
                catch (InterruptedException ex) {}
            }
            this.setCursor(new Cursor(12));
            this.repaint();
            this.start = false;
            this.messageThread.suspend();
            try {
                Thread.sleep(400L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void setValues() {
        this.card1.cardvalue = this.temp[1];
        this.card2.cardvalue = this.temp[2];
        this.card1.cardxco = 70;
        this.card1.cardyco = 50;
        this.card2.cardxco = 140;
        this.card2.cardyco = 50;
        this.card1.showcard = false;
        this.card2.showcard = false;
        this.card3.cardvalue = this.temp[3];
        this.card4.cardvalue = this.temp[4];
        this.card3.cardxco = 210;
        this.card3.cardyco = 50;
        this.card4.cardxco = 280;
        this.card4.cardyco = 50;
        this.card3.showcard = false;
        this.card4.showcard = false;
        this.card5.cardvalue = this.temp[5];
        this.card6.cardvalue = this.temp[6];
        this.card5.cardxco = 70;
        this.card5.cardyco = 150;
        this.card6.cardxco = 140;
        this.card6.cardyco = 150;
        this.card5.showcard = false;
        this.card6.showcard = false;
        this.card7.cardvalue = this.temp[7];
        this.card8.cardvalue = this.temp[8];
        this.card7.cardxco = 210;
        this.card7.cardyco = 150;
        this.card8.cardxco = 280;
        this.card8.cardyco = 150;
        this.card7.showcard = false;
        this.card8.showcard = false;
        this.card9.cardvalue = this.temp[9];
        this.card10.cardvalue = this.temp[10];
        this.card9.cardxco = 70;
        this.card9.cardyco = 250;
        this.card10.cardxco = 140;
        this.card10.cardyco = 250;
        this.card9.showcard = false;
        this.card10.showcard = false;
        this.card11.cardvalue = this.temp[11];
        this.card12.cardvalue = this.temp[12];
        this.card11.cardxco = 210;
        this.card11.cardyco = 250;
        this.card12.cardxco = 280;
        this.card12.cardyco = 250;
        this.card11.showcard = false;
        this.card12.showcard = false;
        this.card1.showtemp = false;
        this.card2.showtemp = false;
        this.card3.showtemp = false;
        this.card4.showtemp = false;
        this.card5.showtemp = false;
        this.card6.showtemp = false;
        this.card7.showtemp = false;
        this.card8.showtemp = false;
        this.card9.showtemp = false;
        this.card10.showtemp = false;
        this.card11.showtemp = false;
        this.card12.showtemp = false;
    }
    
    public void stop() {
        this.messageThread.stop();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
