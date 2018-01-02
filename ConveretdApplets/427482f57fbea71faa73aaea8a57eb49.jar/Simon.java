import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Simon extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    Thread runner;
    Point colortopleft;
    int colorradius;
    Point whitetopleft;
    int whiteradius;
    String sequence;
    String input;
    int inputindex;
    int topscore;
    boolean bluelighted;
    boolean redlighted;
    boolean greenlighted;
    boolean yellowlighted;
    AudioClip blueaudio;
    AudioClip redaudio;
    AudioClip greenaudio;
    AudioClip yellowaudio;
    AudioClip gameoveraudio;
    long sleeptime;
    boolean clickable;
    boolean gameover;
    int linkx;
    int linky;
    Image offscreenImage;
    Graphics offscreen;
    
    public void stop() {
        this.runner.stop();
    }
    
    private void updateBlue(final Graphics screen, final boolean lighted) {
        if (lighted) {
            screen.setColor(new Color(0, 0, 255));
        }
        else {
            screen.setColor(new Color(0, 0, 150));
        }
        screen.fillArc(this.colortopleft.x, this.colortopleft.y, 2 * this.colorradius, 2 * this.colorradius, 0, 90);
        screen.setColor(this.getBackground());
        screen.fillArc(this.whitetopleft.x, this.whitetopleft.y, 2 * this.whiteradius, 2 * this.whiteradius, 0, 90);
    }
    
    public void mouseClicked(final MouseEvent me) {
        if (this.gameover && this.clickable) {
            this.clickable = false;
        }
        else if (this.gameover && !this.clickable) {
            this.newGame();
        }
        if (me.getX() > this.linkx && me.getY() < this.linky) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public void mousePressed(final MouseEvent me) {
        if (this.clickable && !this.gameover) {
            final char clickcolor = this.getColorOfClick(me);
            if (clickcolor == this.sequence.charAt(this.inputindex)) {
                switch (clickcolor) {
                    case 'b': {
                        this.bluelighted = true;
                        this.blueaudio.play();
                        break;
                    }
                    case 'r': {
                        this.redlighted = true;
                        this.redaudio.play();
                        break;
                    }
                    case 'g': {
                        this.greenlighted = true;
                        this.greenaudio.play();
                        break;
                    }
                    case 'y': {
                        this.yellowlighted = true;
                        this.yellowaudio.play();
                        break;
                    }
                }
                this.repaint();
                ++this.inputindex;
                if (this.inputindex == this.sequence.length()) {
                    this.runner.stop();
                    this.inputindex = 0;
                    this.addToSequence();
                    if (this.sleeptime > 125L) {
                        this.sleeptime -= 20L;
                    }
                    this.showSequence();
                }
            }
            else if (clickcolor != '*') {
                this.runner.stop();
                this.gameOver();
                this.clickable = true;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent me) {
    }
    
    private void updateColors(final Graphics screen) {
        this.updateBlue(screen, this.bluelighted);
        this.updateGreen(screen, this.greenlighted);
        this.updateRed(screen, this.redlighted);
        this.updateYellow(screen, this.yellowlighted);
        this.drawLines(screen);
    }
    
    public void paint(final Graphics screen) {
        this.updateColors(this.offscreen);
        this.showScores(this.offscreen);
        if (this.gameover) {
            this.showGameOver(this.offscreen);
        }
        this.drawLink(this.offscreen);
        screen.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void mouseReleased(final MouseEvent me) {
        if (!this.gameover) {
            this.setLights(false);
        }
    }
    
    public void mouseMoved(final MouseEvent me) {
        if (me.getX() > this.linkx && me.getY() < this.linky) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(new Cursor(0));
        }
    }
    
    private void updateGreen(final Graphics screen, final boolean lighted) {
        if (lighted) {
            screen.setColor(new Color(0, 255, 0));
        }
        else {
            screen.setColor(new Color(0, 150, 0));
        }
        screen.fillArc(this.colortopleft.x, this.colortopleft.y, 2 * this.colorradius, 2 * this.colorradius, 180, 90);
        screen.setColor(this.getBackground());
        screen.fillArc(this.whitetopleft.x, this.whitetopleft.y, 2 * this.whiteradius, 2 * this.whiteradius, 180, 90);
    }
    
    public Simon() {
        this.input = new String();
    }
    
    private void newGame() {
        this.sequence = new String();
        this.addToSequence();
        this.inputindex = 0;
        this.input = new String();
        this.sleeptime = 500L;
        this.gameover = false;
        this.showSequence();
    }
    
    private void showGameOver(final Graphics screen) {
        screen.setColor(Color.gray);
        screen.fill3DRect((this.getSize().width - this.colorradius) / 2, this.colortopleft.y + this.colorradius - this.whiteradius, this.colorradius, 2 * this.whiteradius, true);
        Font f = new Font("sanserif", 1, 16);
        FontMetrics fm = this.getFontMetrics(f);
        screen.setFont(f);
        screen.setColor(Color.white);
        final String go = "GAME OVER";
        screen.drawString(go, (this.getSize().width - fm.stringWidth(go)) / 2, this.colortopleft.y + this.colorradius - this.whiteradius + fm.getHeight() + 3);
        f = new Font("sanserif", 2, 12);
        fm = this.getFontMetrics(f);
        screen.setFont(f);
        final String cts = "Click To Start";
        screen.drawString(cts, (this.getSize().width - fm.stringWidth(cts)) / 2, this.colortopleft.y + this.colorradius + this.whiteradius - 3);
    }
    
    private void drawLines(final Graphics screen) {
        screen.setColor(this.getForeground());
        screen.drawLine(this.colortopleft.x, this.colortopleft.y + this.colorradius, this.colortopleft.x + this.colorradius - this.whiteradius, this.colortopleft.y + this.colorradius);
        screen.drawLine(this.colortopleft.x + this.colorradius + this.whiteradius, this.colortopleft.y + this.colorradius, this.colortopleft.x + 2 * this.colorradius, this.colortopleft.y + this.colorradius);
        screen.drawLine(this.colortopleft.x + this.colorradius, this.colortopleft.y, this.colortopleft.x + this.colorradius, this.colortopleft.y + this.colorradius - this.whiteradius);
        screen.drawLine(this.colortopleft.x + this.colorradius, this.colortopleft.y + this.colorradius + this.whiteradius, this.colortopleft.x + this.colorradius, this.colortopleft.y + 2 * this.colorradius);
        screen.drawOval(this.colortopleft.x, this.colortopleft.y, 2 * this.colorradius, 2 * this.colorradius);
        screen.drawOval(this.whitetopleft.x, this.whitetopleft.y, 2 * this.whiteradius, 2 * this.whiteradius);
    }
    
    private void addToSequence() {
        final Random r = new Random(System.currentTimeMillis());
        final int num = (int)(r.nextDouble() * 4.0);
        switch (num) {
            case 0: {
                this.sequence += "r";
                break;
            }
            case 1: {
                this.sequence += "g";
                break;
            }
            case 2: {
                this.sequence += "b";
                break;
            }
            case 3: {
                this.sequence += "y";
                break;
            }
        }
    }
    
    private void setLights(final boolean flag) {
        this.yellowlighted = flag;
        this.greenlighted = flag;
        this.redlighted = flag;
        this.bluelighted = flag;
        this.repaint();
    }
    
    private char getColorOfClick(final MouseEvent me) {
        final int distance = (int)Math.sqrt(Math.pow(me.getX() - (this.colortopleft.x + this.colorradius), 2.0) + Math.pow(me.getY() - (this.colortopleft.y + this.colorradius), 2.0));
        if (distance >= this.colorradius || distance <= this.whiteradius) {
            return '*';
        }
        if (me.getX() > this.colortopleft.x + this.colorradius) {
            if (me.getY() > this.colortopleft.y + this.colorradius) {
                return 'y';
            }
            return 'b';
        }
        else {
            if (me.getY() > this.colortopleft.y + this.colorradius) {
                return 'g';
            }
            return 'r';
        }
    }
    
    public void update(final Graphics screen) {
        this.paint(screen);
    }
    
    public void mouseEntered(final MouseEvent me) {
        this.setCursor(new Cursor(0));
    }
    
    public void mouseExited(final MouseEvent me) {
    }
    
    public void start() {
    }
    
    private void gameOver() {
        if (this.sequence.length() - 1 > this.topscore) {
            this.topscore = this.sequence.length() - 1;
        }
        this.setLights(true);
        this.gameover = true;
        this.clickable = false;
        this.gameoveraudio.play();
    }
    
    private void updateYellow(final Graphics screen, final boolean lighted) {
        if (lighted) {
            screen.setColor(new Color(255, 255, 0));
        }
        else {
            screen.setColor(new Color(150, 150, 0));
        }
        screen.fillArc(this.colortopleft.x, this.colortopleft.y, 2 * this.colorradius, 2 * this.colorradius, 270, 90);
        screen.setColor(this.getBackground());
        screen.fillArc(this.whitetopleft.x, this.whitetopleft.y, 2 * this.whiteradius, 2 * this.whiteradius, 270, 90);
    }
    
    private void showScores(final Graphics screen) {
        final Font f = new Font("sanserif", 0, 12);
        final FontMetrics fm = this.getFontMetrics(f);
        screen.setFont(f);
        screen.setColor(this.getForeground());
        String curscore;
        if (this.sequence != null && this.sequence.length() > 0) {
            curscore = "Score: " + Integer.toString(this.sequence.length() - 1);
        }
        else {
            curscore = "Score: 0";
        }
        final String highscore = "High Score: " + Integer.toString(this.topscore);
        screen.clearRect(0, this.colortopleft.y + 2 * this.colorradius + 10 - fm.getHeight(), this.getSize().width / 3, fm.getHeight());
        screen.drawString(curscore, this.colortopleft.x, this.colortopleft.y + 2 * this.colorradius + 7);
        screen.clearRect(this.getSize().width * 2 / 3, this.colortopleft.y + 2 * this.colorradius + 10 - fm.getHeight(), this.getSize().width / 3, fm.getHeight() + 2);
        screen.drawString(highscore, this.getSize().width - 10 - fm.stringWidth(highscore), this.colortopleft.y + 2 * this.colorradius + 7);
    }
    
    public void run() {
        this.clickable = false;
        final Thread thisThread = Thread.currentThread();
        while (this.runner == thisThread) {
            try {
                Thread.sleep(500L);
                for (int i = 0; i < this.sequence.length(); ++i) {
                    final boolean b = false;
                    this.yellowlighted = b;
                    this.greenlighted = b;
                    this.redlighted = b;
                    this.bluelighted = b;
                    switch (this.sequence.charAt(i)) {
                        case 'b': {
                            this.bluelighted = true;
                            this.blueaudio.play();
                            break;
                        }
                        case 'r': {
                            this.redlighted = true;
                            this.redaudio.play();
                            break;
                        }
                        case 'g': {
                            this.greenlighted = true;
                            this.greenaudio.play();
                            break;
                        }
                        case 'y': {
                            this.yellowlighted = true;
                            this.yellowaudio.play();
                            break;
                        }
                    }
                    this.repaint();
                    Thread.sleep(this.sleeptime);
                    this.setLights(false);
                    Thread.sleep(25L);
                }
                this.clickable = true;
                Thread.sleep(this.sequence.length() * this.sleeptime * 3L);
                this.clickable = false;
                this.gameOver();
                this.runner.stop();
            }
            catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void init() {
        String color = this.getParameter("bgcolor");
        Color temp;
        if (color == null) {
            temp = Color.black;
        }
        else {
            temp = this.stringToColor(color);
        }
        this.setBackground(temp);
        color = this.getParameter("fontcolor");
        if (color == null) {
            temp = Color.white;
        }
        else {
            temp = this.stringToColor(color);
        }
        this.setForeground(temp);
        this.blueaudio = this.getAudioClip(this.getCodeBase(), "blue.au");
        this.redaudio = this.getAudioClip(this.getCodeBase(), "red.au");
        this.greenaudio = this.getAudioClip(this.getCodeBase(), "green.au");
        this.yellowaudio = this.getAudioClip(this.getCodeBase(), "yellow.au");
        this.gameoveraudio = this.getAudioClip(this.getCodeBase(), "lose.au");
        this.offscreenImage = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.offscreenImage.getGraphics();
        this.topscore = 0;
        this.setLights(true);
        this.gameover = true;
        this.clickable = false;
        this.colortopleft = new Point(10, 10);
        this.colorradius = (this.getSize().width - 20) / 2;
        this.whiteradius = this.colorradius / 5;
        this.whitetopleft = new Point(this.getSize().width / 2 - this.whiteradius, this.getSize().width / 2 - this.whiteradius);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    private void updateRed(final Graphics screen, final boolean lighted) {
        if (lighted) {
            screen.setColor(new Color(255, 0, 0));
        }
        else {
            screen.setColor(new Color(150, 0, 0));
        }
        screen.fillArc(this.colortopleft.x, this.colortopleft.y, 2 * this.colorradius, 2 * this.colorradius, 90, 90);
        screen.setColor(this.getBackground());
        screen.fillArc(this.whitetopleft.x, this.whitetopleft.y, 2 * this.whiteradius, 2 * this.whiteradius, 90, 90);
    }
    
    private void drawLink(final Graphics screen) {
        final Font linkFont = new Font("sanserif", 0, 12);
        screen.setFont(linkFont);
        final FontMetrics fm = screen.getFontMetrics();
        final String link = "www.realapplets.com";
        this.linkx = this.getSize().width - fm.stringWidth(link) - 2;
        this.linky = fm.getHeight() + 4;
        screen.setColor(this.getBackground());
        screen.fillRect(this.linkx, 0, this.getSize().width, this.linky);
        screen.setColor(this.getForeground());
        screen.drawString(link, this.linkx + 1, this.linky - 3);
        screen.drawLine(this.linkx, this.linky - 2, this.getSize().width, this.linky - 2);
    }
    
    private Color stringToColor(final String paramValue) {
        final int red = Integer.decode("0x" + paramValue.substring(0, 2));
        final int green = Integer.decode("0x" + paramValue.substring(2, 4));
        final int blue = Integer.decode("0x" + paramValue.substring(4, 6));
        return new Color(red, green, blue);
    }
    
    private void showSequence() {
        (this.runner = new Thread(this)).start();
    }
}
