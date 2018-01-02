import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class clsSprite
{
    String name;
    int imagewidth;
    int imageheight;
    int imagex;
    int imagey;
    static final int iMaxPaths = 6;
    static final int iMaxFramesDead = 20;
    int iNumFramesDead;
    boolean isDead;
    int iSpriteSpeed;
    int iThisDistance;
    int transparentpixel;
    static Image[] frames;
    static Image[] frames2;
    static Image imgDeathState;
    static boolean[][] hitarray1;
    static final int[] angles;
    int[][] PathsX;
    int[][] PathsY;
    int[][] Facings;
    int iCurrentPath;
    int iLastPointVisited;
    int iNumPaths;
    int frame;
    boolean drawme;
    
    static {
        angles = new int[] { 90, 45, 360, 315, 270, 225, 180, 135 };
    }
    
    public clsSprite(final String name, final Image[] spriteimages, final Image[] spriteimages2, final boolean[][] hitarray1, final Image deathState, final int imagewidth, final int imageheight) {
        this.imagex = -999;
        this.imagey = -999;
        this.iNumFramesDead = 0;
        this.isDead = false;
        this.iSpriteSpeed = 0;
        this.iThisDistance = 0;
        this.transparentpixel = 0;
        this.PathsX = new int[6][];
        this.PathsY = new int[6][];
        this.Facings = new int[6][];
        this.iCurrentPath = 0;
        this.iLastPointVisited = 0;
        this.iNumPaths = 0;
        this.name = name;
        this.imagewidth = imagewidth;
        this.imageheight = imageheight;
        if (clsSprite.frames == null) {
            clsSprite.frames = spriteimages;
            clsSprite.frames2 = spriteimages2;
            clsSprite.imgDeathState = deathState;
            clsSprite.hitarray1 = hitarray1;
        }
    }
    
    public int Fast_Distance_2D(final int x1, final int x2, final int y1, final int y2) {
        int x3 = x1 - x2;
        int y3 = y1 - y2;
        x3 = Math.abs(x3);
        y3 = Math.abs(y3);
        final int mn = Math.min(x3, y3);
        return x3 + y3 - (mn >> 1) - (mn >> 2) + (mn >> 4);
    }
    
    public boolean closeEnough(final int iDistance, final int x1, final int y1, final int x2, final int y2) {
        final int dist = this.Fast_Distance_2D(x1, x2, y1, y2);
        this.iThisDistance = dist;
        return dist <= iDistance;
    }
    
    public boolean collided(final int x, final int y, final int tickcount) {
        int x2 = 0;
        int y2 = 0;
        final int pixel = 0;
        if (x >= this.imagex && x <= this.imagex + this.imagewidth && y >= this.imagey && y <= this.imagey + this.imageheight) {
            x2 = x - this.imagex;
            y2 = y - this.imagey;
            final int offset = x2 + y2 * this.imagewidth;
            if (offset < clsSprite.hitarray1[this.frame].length && clsSprite.hitarray1[this.frame][offset]) {
                return true;
            }
        }
        return false;
    }
    
    public void drawFrame(final Graphics g, final int tickcount, final Applet app) {
        if (tickcount % 2 == 0) {
            g.drawImage(clsSprite.frames[this.frame], this.imagex, this.imagey, app);
        }
        else {
            g.drawImage(clsSprite.frames2[this.frame], this.imagex, this.imagey, app);
        }
    }
    
    public void drawFrameDead(final Graphics g, final Applet app) {
        g.drawImage(clsSprite.imgDeathState, this.imagex, this.imagey, app);
    }
    
    public boolean isDead() {
        return this.isDead;
    }
    
    public void loadNextPath() {
        if (this.iCurrentPath + 1 == this.iNumPaths) {
            this.iCurrentPath = 0;
        }
        else {
            ++this.iCurrentPath;
        }
        this.imagex = -999;
        this.imagey = -999;
        this.iLastPointVisited = 0;
    }
    
    public boolean loadPath(final int[] pathX, final int[] pathY, final int[] facings) {
        if (this.iNumPaths + 1 > 6) {
            System.out.println("ERROR iMaxPaths of " + 6 + " exceeded!");
            return false;
        }
        this.PathsX[this.iNumPaths] = pathX;
        this.PathsY[this.iNumPaths] = pathY;
        this.Facings[this.iNumPaths] = facings;
        ++this.iNumPaths;
        return true;
    }
    
    public void moveAlongPath() {
        int iDestination = this.iLastPointVisited + 1;
        if (this.imagex == -999 && this.imagey == -999) {
            this.imagex = this.PathsX[this.iCurrentPath][this.iLastPointVisited];
            this.imagey = this.PathsY[this.iCurrentPath][this.iLastPointVisited];
            this.setFrame(this.Facings[this.iCurrentPath][this.iLastPointVisited]);
        }
        else if (this.closeEnough(1, this.imagex, this.imagey, this.PathsX[this.iCurrentPath][iDestination], this.PathsY[this.iCurrentPath][iDestination])) {
            this.iLastPointVisited = iDestination;
            if (++iDestination == this.PathsX[this.iCurrentPath].length) {
                this.loadNextPath();
            }
            else {
                this.setFrame(this.Facings[this.iCurrentPath][this.iLastPointVisited]);
            }
        }
        else if (this.iSpriteSpeed > this.iThisDistance) {
            this.moveForward(this.iThisDistance);
        }
        else {
            this.moveForward(this.iSpriteSpeed);
        }
    }
    
    public void moveForward(final int iDistance) {
        final double angle = clsSprite.angles[this.frame];
        final double degreeradian = 57.295;
        final int dx = (int)Math.round(iDistance * Math.cos(angle / degreeradian));
        final int dy = (int)Math.round(-iDistance * Math.sin(angle / degreeradian));
        this.imagex += dx;
        this.imagey += dy;
    }
    
    public void randomizePaths() {
        final int[] arrflag = new int[6];
        int i = 0;
        int num = 0;
        final int[][] OldPathsX = new int[6][];
        final int[][] OldPathsY = new int[6][];
        final int[][] OldFacings = new int[6][];
        System.arraycopy(this.PathsX, 0, OldPathsX, 0, 6);
        System.arraycopy(this.PathsY, 0, OldPathsY, 0, 6);
        System.arraycopy(this.Facings, 0, OldFacings, 0, 6);
        for (i = 0; i < 6; ++i) {
            arrflag[i] = 0;
        }
        for (i = 0; i < 6; ++i) {
            for (boolean set = false; !set; set = true) {
                num = (short)Math.floor(Math.random() * 6.0);
                if (arrflag[num] == 0) {
                    arrflag[num] = 1;
                    this.PathsX[num] = OldPathsX[i];
                    this.PathsY[num] = OldPathsY[i];
                    this.Facings[num] = OldFacings[i];
                }
            }
            boolean set = false;
        }
    }
    
    public void rotateLeft() {
        this.frame = (this.frame - 1) % clsSprite.frames.length;
        if (this.frame == -1) {
            this.frame = clsSprite.frames.length - 1;
        }
    }
    
    public void rotateRight() {
        this.frame = (this.frame + 1) % clsSprite.frames.length;
    }
    
    public void setDead() {
        this.isDead = true;
        this.iNumFramesDead = 0;
    }
    
    public void setDraw(final boolean drawme) {
        this.drawme = drawme;
    }
    
    public void setFrame(final int frame) {
        this.frame = frame;
    }
    
    public void setPos(final int x, final int y) {
        this.imagex = x;
        this.imagey = y;
    }
    
    public void setSpeed(final int iSpeed) {
        this.iSpriteSpeed = iSpeed;
    }
    
    public void stillDead() {
        ++this.iNumFramesDead;
        if (this.iNumFramesDead >= 20) {
            this.isDead = false;
            this.iNumFramesDead = 0;
            this.loadNextPath();
        }
    }
}
