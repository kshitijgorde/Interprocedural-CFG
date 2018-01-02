import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Timeline extends Canvas implements Runnable
{
    private Finditapplet fa;
    static Thread messageThread;
    Screen sc;
    Findit frame;
    Graphics gIm;
    Image im;
    Graphics gIm2;
    Image im2;
    Graphics gIm3;
    Image im3;
    static boolean pause;
    static int score;
    int antoon;
    static int k;
    boolean running;
    static boolean timeup;
    Color fontkleur;
    Color timekleur;
    int difficulty;
    int red;
    int green;
    int blue;
    
    public Timeline(final Screen sc, final Finditapplet fa, final Findit frame) {
        this.running = true;
        this.sc = sc;
        this.fa = fa;
        this.frame = frame;
        this.difficulty = ((fa.getParameter("difficulty(1-10)") == null) ? 2 : Integer.parseInt(fa.getParameter("difficulty(1-10)")));
        this.antoon = this.difficulty;
        this.red = ((fa.getParameter("fontred") == null) ? 255 : Integer.parseInt(fa.getParameter("fontred")));
        this.green = ((fa.getParameter("fontgreen") == null) ? 255 : Integer.parseInt(fa.getParameter("fontgreen")));
        this.blue = ((fa.getParameter("fontblue") == null) ? 0 : Integer.parseInt(fa.getParameter("fontblue")));
        this.fontkleur = new Color(this.red, this.green, this.blue);
        this.red = ((fa.getParameter("timered") == null) ? 255 : Integer.parseInt(fa.getParameter("timered")));
        this.green = ((fa.getParameter("timegreen") == null) ? 0 : Integer.parseInt(fa.getParameter("timegreen")));
        this.blue = ((fa.getParameter("timeblue") == null) ? 0 : Integer.parseInt(fa.getParameter("timeblue")));
        this.timekleur = new Color(this.red, this.green, this.blue);
        if (Timeline.messageThread == null) {
            (Timeline.messageThread = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.running) {
            Timeline.k -= 2 * this.antoon / 3;
            if (Timeline.k < 0) {
                Timeline.timeup = true;
                this.sc.repaint();
                Timeline.messageThread.suspend();
                this.running = false;
            }
            try {
                Thread.sleep(400L);
            }
            catch (InterruptedException ex) {
                System.exit(1);
            }
            this.repaint();
        }
    }
    
    public void volgende() {
        if (this.sc.succes) {
            final Screen sc = this.sc;
            ++sc.level;
            this.Clear();
            this.sc.herFill();
            this.sc.repaint();
            ++this.antoon;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.getSize();
        this.im = this.createImage(350, 100);
        (this.gIm = this.im.getGraphics()).clearRect(0, 0, 350, 100);
        this.im2 = this.createImage(200, 50);
        (this.gIm2 = this.im2.getGraphics()).clearRect(0, 0, 200, 50);
        this.im3 = this.createImage(350, 50);
        (this.gIm3 = this.im3.getGraphics()).clearRect(0, 0, 350, 50);
        graphics.drawImage(this.im3, 150, 35, this);
        for (int i = 0; i < 5; ++i) {
            this.gIm2.drawImage(this.sc.wrong, 10 + i * 30, 10, null);
        }
        for (int j = 0; j < this.sc.klop; ++j) {
            this.gIm2.drawImage(this.sc.right, 10 + j * 30, 10, null);
        }
        if (this.im2 != null) {
            graphics.drawImage(this.im2, 500, 80, this);
        }
        if (this.sc.klop == 5) {
            Timeline.messageThread.suspend();
            this.running = false;
            this.gIm3.setFont(new Font("Impact", 0, 30));
            this.gIm3.setColor(this.fontkleur);
            this.gIm3.drawString("You made it! Score: " + Timeline.score, 0, 30);
            graphics.drawImage(this.im3, 150, 35, this);
        }
        if (!Timeline.timeup) {
            this.gIm.setColor(this.timekleur);
            this.gIm.fillRoundRect(10, 10, Timeline.k, 30, 20, 20);
            if (this.im != null) {
                graphics.drawImage(this.im, 50, 80, this);
            }
        }
        if (Timeline.timeup) {
            this.gIm3.setFont(new Font("Impact", 0, 30));
            this.gIm3.setColor(this.fontkleur);
            this.gIm3.drawString("Game Over! Score: " + Timeline.score, 0, 30);
            graphics.drawImage(this.im3, 150, 35, this);
        }
    }
    
    public void Clear() {
        for (int i = 0; i < 10; ++i) {
            this.sc.fault[i] = 6;
        }
        this.sc.klop = 0;
        this.sc.faulttemp = 5;
        Timeline.k = 300;
        this.running = true;
        Timeline.timeup = false;
        Timeline.messageThread.resume();
        this.sc.succes = false;
    }
    
    public void stopff() {
        if (!Timeline.pause) {
            Timeline.pause = true;
            Timeline.messageThread.suspend();
            this.frame.pauze.setLabel("Resume");
            this.sc.repaint();
            return;
        }
        Timeline.pause = false;
        Timeline.messageThread.resume();
        this.frame.pauze.setLabel("Pause");
        this.sc.repaint();
    }
    
    public void opnieuw() {
        this.sc.succes = true;
        this.sc.level = 0;
        this.volgende();
        this.sc.succes = false;
        this.frame.next.setLabel("Next");
        this.running = true;
        Timeline.score = 0;
        this.antoon = 2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        Timeline.score = 2;
        Timeline.k = 300;
    }
}
