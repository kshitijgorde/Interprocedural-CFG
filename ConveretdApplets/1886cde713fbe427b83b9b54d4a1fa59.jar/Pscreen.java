import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pscreen extends Canvas implements MouseListener
{
    int xclick;
    int yclick;
    int ID;
    private Image picture1;
    private Image picture2;
    private Image psBuff;
    private Graphics psBuffG;
    private FindIt2Frame fi2f;
    Color bgColor;
    boolean hotspot;
    boolean imagesLoaded;
    Level lvl;
    int rectX;
    int rectY;
    int rectW;
    int rectH;
    Color rightColor;
    Color missedColor;
    int xpos;
    int ypos;
    int psCount;
    
    public Pscreen(final FindIt2Frame fi2f, final Level lvl, final int id) {
        this.fi2f = fi2f;
        this.ID = id;
        this.lvl = lvl;
        this.setBackground(this.bgColor = fi2f.bgColor);
        this.addMouseListener(this);
        this.rightColor = new Color(0, 255, 0);
        this.missedColor = new Color(255, 0, 0);
        this.psBuff = fi2f.getEmptyImage(fi2f.psWidth, fi2f.psHeight);
        this.psBuffG = this.psBuff.getGraphics();
        final int n = -100;
        this.yclick = n;
        this.xclick = n;
    }
    
    private void check(final int n, final int n2) {
        if (!(this.hotspot = this.lvl.check(n, n2))) {
            this.fi2f.tm.badClick();
        }
        this.fi2f.updateScreens();
    }
    
    public void getNewPictures(final int n) {
        this.picture1 = this.lvl.getLevelImage(n);
        this.imagesLoaded = true;
        final FindIt2Frame fi2f = this.fi2f;
        ++fi2f.psLoaded;
        if (this.fi2f.psLoaded == 2) {
            this.fi2f.psLoaded = 0;
            this.fi2f.tm.timeUp = false;
            this.fi2f.info.infoRun = true;
            this.fi2f.updateScreens();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.fi2f.gamePaused) {
            this.xpos = mouseEvent.getX();
            this.ypos = mouseEvent.getY();
            if (this.fi2f.nextFlag) {
                this.fi2f.initLoad();
            }
            else if (!this.fi2f.goFlag && !this.fi2f.startFlag && !this.fi2f.splashFlag && this.imagesLoaded && !this.fi2f.gamePaused) {
                this.check(this.xpos, this.ypos);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.psBuffG.setColor(this.bgColor);
        this.psBuffG.fillRect(0, 0, this.fi2f.psWidth, this.fi2f.psHeight);
        this.psBuffG.setColor(this.bgColor);
        if (!this.fi2f.gamePaused) {
            if (this.imagesLoaded) {
                if (this.fi2f.nextFlag && this.ID == 2) {
                    this.psBuffG.drawImage(this.fi2f.nextIm, 60, 80, this);
                }
                else {
                    this.psBuffG.drawImage(this.picture1, this.lvl.getOffsetX(1), this.lvl.getOffsetY(1), this);
                    for (int i = 0; i < 5; ++i) {
                        this.psBuffG.setColor(this.rightColor);
                        if (this.lvl.fault[i]) {
                            this.psBuffG.drawRoundRect(this.lvl.getDetails(i, 'x'), this.lvl.getDetails(i, 'y'), this.lvl.getDetails(i, 'w'), this.lvl.getDetails(i, 'h'), 20, 20);
                        }
                    }
                    if (!this.hotspot) {
                        this.psBuffG.setColor(this.missedColor);
                        this.psBuffG.drawRect(this.lvl.xclicked - 5, this.lvl.yclicked - 5, 10, 10);
                    }
                    this.hotspot = true;
                }
            }
            if (!this.imagesLoaded) {
                this.psBuffG.setColor(Color.white);
                this.psBuffG.setFont(new Font("Arial", 1, 20));
                this.psBuffG.drawString("Get Ready for Level " + (this.lvl.getLevel() + 1), 20, 50);
                this.psBuffG.setFont(new Font("Arial", 2, 12));
                this.psBuffG.drawString("Loading Images...", 50, 100);
                this.psCount = 1;
            }
        }
        else {
            this.psBuffG.setColor(Color.white);
            this.psBuffG.setFont(new Font("Arial", 1, 20));
            this.psBuffG.drawString("Game Paused", 20, 50);
            this.psBuffG.setFont(new Font("Arial", 2, 12));
            this.psBuffG.drawString("Press Resume to continue", 20, 80);
            this.psBuffG.drawString("Current Level: " + this.lvl.getLevel(), 50, 120);
            if (this.ID == 1) {
                this.psBuffG.drawString("Current Picture: " + this.lvl.rightPic + " (" + this.lvl.randLevel + ")", 50, 140);
            }
            if (this.ID == 2) {
                this.psBuffG.drawString("Current Picture: " + this.lvl.wrongPic + " (" + this.lvl.randLevel + ")", 50, 140);
            }
            this.psBuffG.drawString("Current Score: " + this.fi2f.getScore(), 50, 160);
            this.psBuffG.drawString("Number of Pictures " + this.fi2f.numberOfPictures, 50, 180);
            this.psBuffG.drawString("Number of Levels " + this.fi2f.numberOfLevels, 50, 200);
        }
        this.psBuffG.setFont(new Font("Arial", 2, 10));
        this.psBuffG.setColor(Color.white);
        this.psBuffG.drawString("Limited Unregistered Version", 5, 235);
        graphics.drawImage(this.psBuff, 0, 0, this);
    }
    
    public void resetData() {
        this.imagesLoaded = false;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
