import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSMenu extends Panel implements MenuListener
{
    private static final int WIDTH = 130;
    private static final int HEIGHT = 320;
    private Image gbuffer;
    private Graphics gbuf;
    private MoonStar moonStar;
    private Image background;
    private MSButton[] buttons;
    private int score;
    private int level;
    public int state;
    private String gameType;
    private String highscoresFile;
    private boolean useHighscores;
    
    public MSMenu(final MoonStar moonStar, final Image background, final String[] array, final String gameType) {
        this.score = 0;
        this.level = 0;
        this.state = 3;
        this.useHighscores = false;
        this.moonStar = moonStar;
        this.background = background;
        this.gameType = gameType;
        this.setLayout(null);
        this.setBackground(moonStar.colors[0]);
        this.highscoresFile = moonStar.getParameter("UseHighscores");
        if (this.highscoresFile != null && (this.highscoresFile.equalsIgnoreCase("Yes") || this.highscoresFile.equalsIgnoreCase("True"))) {
            this.useHighscores = true;
        }
        this.highscoresFile = moonStar.getParameter("HighscoresFile");
        if (this.highscoresFile == null || this.highscoresFile.equals("")) {
            this.highscoresFile = "default";
        }
        this.highscoresFile = this.highscoresFile.toLowerCase();
        this.buttons = new MSButton[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.buttons[i] = new MSButton(this, i, array[i]);
            if (i <= 1) {
                this.buttons[i].setBounds(10, 320 - 30 * (array.length - 1), 110, 20);
            }
            else {
                this.buttons[i].setBounds(10, 320 - 30 * (array.length - i), 110, 20);
                this.add(this.buttons[i]);
            }
        }
    }
    
    public void setGameType(final String gameType, final int level) {
        this.gameType = gameType;
        this.score = 0;
        this.level = level;
        this.repaint();
    }
    
    public void addScore(final int n) {
        this.score += n;
        this.repaint();
    }
    
    public void addLevel() {
        ++this.level;
        this.repaint();
    }
    
    public void sendHighScore(final boolean b) {
        if (this.moonStar.registered && this.useHighscores) {
            final HighScoreTool highScoreTool = new HighScoreTool(this.moonStar);
            highScoreTool.setScore(this.score);
            highScoreTool.setLevel(this.level);
            String s = "_cam";
            if (b) {
                s = "_arc";
            }
            highScoreTool.setFile(this.highscoresFile + s);
            highScoreTool.setDir("/moonstar");
            if (b) {
                highScoreTool.setApplet("HSMoonStarArcade");
            }
            else {
                highScoreTool.setApplet("HSMoonStarCampaign");
            }
            highScoreTool.doHigh();
        }
    }
    
    public void add() {
        this.moonStar.add(this);
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i].repaint();
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(130, 320);
                this.gbuf = this.gbuffer.getGraphics();
            }
            catch (Exception ex) {
                this.gbuf = gbuf;
                this.gbuffer = null;
            }
        }
        this.gbuf.drawImage(this.background, 0, 0, 130, 320, this);
        this.gbuf.setFont(new Font("Ariel", 0, 19));
        this.gbuf.setColor(this.moonStar.colors[7]);
        final FontMetrics fontMetrics = this.gbuf.getFontMetrics();
        final String string = this.score / 1000000 + "." + this.score / 100000 % 10 + this.score / 10000 % 10 + this.score / 1000 % 10 + "." + this.score / 100 % 10 + this.score / 10 % 10 + this.score % 10;
        this.gbuf.drawString(string, (130 - fontMetrics.stringWidth(string)) / 2, 37);
        this.gbuf.setFont(new Font("Ariel", 1, 14));
        this.gbuf.setColor(this.moonStar.colors[8]);
        final FontMetrics fontMetrics2 = this.gbuf.getFontMetrics();
        final String string2 = Integer.toString((this.level > 0) ? this.level : 1);
        this.gbuf.drawString(string2, 130 - fontMetrics2.stringWidth(string2) / 2 - 25, 75);
        this.gbuf.setFont(new Font("Ariel", 0, 14));
        this.gbuf.setColor(this.moonStar.colors[9]);
        this.gbuf.drawString(this.gameType, (130 - this.gbuf.getFontMetrics().stringWidth(this.gameType)) / 2, 105);
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, 130, 320, this);
        }
    }
    
    public synchronized boolean actionPerformed(final int n) {
        switch (n) {
            case 0: {
                boolean b = false;
                if (this.moonStar.mSController != null && this.state != 9) {
                    this.remove(this.buttons[0]);
                    b = true;
                }
                this.removeCurrent();
                this.add(this.buttons[1]);
                this.buttons[1].repaint();
                this.moonStar.mSGame.add();
                this.moonStar.mSGame.resume();
                this.state = 0;
                return b;
            }
            case 1: {
                if (this.state == 0) {
                    this.pauseGame();
                    this.state = 1;
                }
                return true;
            }
            case 2: {
                if (this.state == 0) {
                    this.pauseGame();
                }
                this.removeCurrent();
                this.moonStar.mSNewGame.add();
                this.state = 2;
                return false;
            }
            case 3: {
                if (this.state == 0) {
                    this.pauseGame();
                }
                this.removeCurrent();
                this.moonStar.mSHelp.add();
                this.state = 3;
                return false;
            }
            case 4: {
                if (this.state == 0) {
                    this.pauseGame();
                }
                this.removeCurrent();
                this.moonStar.mSConfig.add();
                this.state = 4;
                return false;
            }
            case 5: {
                try {
                    this.moonStar.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
                }
                catch (MalformedURLException ex) {}
                if (this.state == 0) {
                    this.pauseGame();
                    this.state = 1;
                }
                return false;
            }
            case 6: {
                if (this.state == 0) {
                    this.remove(this.buttons[1]);
                    this.state = 1;
                }
                else {
                    this.remove(this.buttons[0]);
                }
                return false;
            }
            case 7: {
                if (this.state == 0) {
                    this.pauseGame();
                }
                this.removeCurrent();
                this.moonStar.mSCustomGame.add();
                this.state = 7;
                return false;
            }
            case 8: {
                this.remove(this.buttons[1]);
                this.remove(this.buttons[2]);
                this.remove(this.buttons[3]);
                this.remove(this.buttons[4]);
                this.state = 8;
                return false;
            }
            case 9: {
                this.add(this.buttons[2]);
                this.buttons[2].repaint();
                this.add(this.buttons[3]);
                this.buttons[3].repaint();
                this.add(this.buttons[4]);
                this.buttons[4].repaint();
                this.state = 9;
                return false;
            }
            default: {
                return false;
            }
        }
    }
    
    private void pauseGame() {
        this.remove(this.buttons[1]);
        this.add(this.buttons[0]);
        this.buttons[0].repaint();
        this.moonStar.mSGame.pause();
    }
    
    private void removeCurrent() {
        switch (this.state) {
            case 0:
            case 1:
            case 8: {
                this.moonStar.mSGame.remove();
                break;
            }
            case 2: {
                this.moonStar.mSNewGame.remove();
                break;
            }
            case 3: {
                this.moonStar.mSHelp.remove();
                break;
            }
            case 4: {
                this.moonStar.mSConfig.remove();
                break;
            }
            case 7: {
                this.moonStar.mSCustomGame.remove();
                break;
            }
        }
    }
}
