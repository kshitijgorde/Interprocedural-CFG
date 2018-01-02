import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class board extends Applet
{
    public final int IMAGES = 10;
    URL url;
    Image[] tiles;
    int[][] board;
    int[][] animate;
    int[][][] levels;
    int temp;
    int cx;
    int cy;
    int chpx;
    int chpy;
    int ret;
    int s;
    int cdx;
    int cdy;
    int cddy;
    int cddx;
    boolean moving;
    boolean drawentireboard;
    GameEngine ge;
    ani anim;
    
    public void drawBoard(final Graphics graphics) {
        if (this.drawentireboard) {
            this.ge.guiRender = true;
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(0, 0, 480, 320);
            for (int i = 0; i < 12; ++i) {
                for (int j = 0; j < 16; ++j) {
                    graphics.drawImage(this.tiles[0], j * 40, i * 40, this);
                }
            }
            for (int k = 0; k < 8; ++k) {
                for (int l = 0; l < 12; ++l) {
                    final int n = k * 40;
                    final int n2 = l * 40;
                    if (l + 2 == this.chpx && k + 2 == this.chpy && this.ret == 5) {
                        graphics.setColor(new Color(0, 255, 0));
                        graphics.drawRect(n2 - 1, n - 1, 41, 41);
                        if (this.anim.isFlipping()) {
                            continue;
                        }
                    }
                    switch (this.board[k + 2][l + 2]) {
                        case 2: {
                            graphics.drawImage(this.tiles[2], n2, n, this);
                            break;
                        }
                        case 3: {
                            graphics.drawImage(this.tiles[3], n2, n, this);
                            break;
                        }
                        case 4: {
                            graphics.drawImage(this.tiles[4], n2, n, this);
                            break;
                        }
                        case 5: {
                            graphics.drawImage(this.tiles[5], n2, n, this);
                            break;
                        }
                        case 6: {
                            graphics.drawImage(this.tiles[6], n2, n, this);
                            break;
                        }
                        case 9: {
                            graphics.drawImage(this.tiles[7], n2, n, this);
                            break;
                        }
                    }
                }
                this.drawentireboard = false;
            }
            if (this.ge.drawMov) {
                graphics.drawImage(this.tiles[9], this.cx * 40, this.cy * 40, this);
            }
        }
        this.anim.draw(graphics);
    }
    
    public void win() {
    }
    
    public board(final URL url, final GameEngine ge) {
        this.tiles = new Image[10];
        this.board = new int[12][16];
        this.animate = new int[12][16];
        this.levels = new int[15][8][12];
        this.drawentireboard = true;
        this.anim = new ani();
        this.ge = ge;
        this.url = url;
        this.readLevels(ge.theMother);
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                this.board[i][j] = 9;
            }
        }
    }
    
    public void loadlevel(final int n) {
        this.cx = 1;
        this.cy = 1;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 12; ++j) {
                this.temp = this.levels[n][i][j];
                if (this.temp == 1) {
                    this.board[i + 2][j + 2] = 0;
                    this.cy = i;
                    this.cx = j;
                }
                else {
                    this.board[i + 2][j + 2] = this.temp;
                }
            }
        }
    }
    
    public int movep(final int cdx, final int cdy) {
        this.ge.theMother.tap.play();
        int cx = this.cx;
        int cy = this.cy;
        this.cx += 2;
        this.cy += 2;
        final int n = this.board[this.cy + cdy][this.cx + cdx];
        final int n2 = this.board[this.cy + cdy * 2][this.cx + cdx * 2];
        if (n == 6) {
            this.anim.setHole(cx + cdx, cy + cdy, 9);
            this.ge.drawMov = false;
            this.ge.sb.set("You fell into a hole and died", 200);
            this.drawentireboard = true;
            this.ge.sb.needsdraw = true;
            return 1;
        }
        if (n != 9) {
            if (n2 == n && n != 6) {
                if (n != 0 && n != 9) {
                    this.ge.theMother.pop.play();
                }
                if (n == 2) {
                    this.board[this.cy + cdy][this.cx + cdx] = 0;
                    this.board[this.cy + cdy * 2][this.cx + cdx * 2] = 9;
                    this.anim.setTri(cx + cdx * 2, cy + cdy * 2, 2);
                }
                else if (n == 5) {
                    this.ret = 5;
                    this.chpx = this.cx + cdx * 2;
                    this.chpy = this.cy + cdy * 2;
                    this.board[this.cy + cdy][this.cx + cdx] = 0;
                }
                else {
                    this.board[this.cy + cdy][this.cx + cdx] = 0;
                    this.board[this.cy + cdy * 2][this.cx + cdx * 2] = 0;
                }
            }
            else if (n2 == 6) {
                this.board[this.cy + cdy][this.cx + cdx] = 0;
                if (n == 4) {
                    this.board[this.cy + cdy * 2][this.cx + cdx * 2] = 0;
                }
                else if (n != 0) {
                    this.anim.setHole(cx + cdx * 2, cy + cdy * 2, n);
                }
            }
            else {
                if (n != 0 && n != 6 && n2 == 0) {
                    this.board[this.cy + cdy][this.cx + cdx] = 0;
                    this.board[this.cy + cdy * 2][this.cx + cdx * 2] = n;
                }
                if (n != 0 && n != 6 && n != 9 && n2 != 0 && n2 != 6 && n2 != 9) {
                    this.ge.sb.set("These pegs Do not match.", 200);
                }
            }
            if (this.board[this.cy + cdy][this.cx + cdx] == 0) {
                this.cx -= 2;
                this.cy -= 2;
                cx = this.cx + cdx;
                cy = this.cy + cdy;
                this.cdx = cdx;
                this.cdy = cdy;
                this.cx += 2;
                this.cy += 2;
            }
        }
        this.cx = cx;
        this.cy = cy;
        this.drawentireboard = true;
        this.ge.sb.needsdraw = true;
        return this.ret;
    }
    
    public int getAt(final int n, final int n2) {
        return this.board[n][n2];
    }
    
    public void setAt(final int n, final int n2, final int n3) {
        this.board[n][n2] = n3;
    }
    
    public void readLevels(final Applet applet) {
        String string = "";
        try {
            final InputStream openStream = this.url.openStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string += line;
            }
            openStream.close();
        }
        catch (IOException ex2) {}
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        char char1 = ' ';
        for (int i = 0; i < 1574; ++i) {
            try {
                char1 = string.charAt(i);
            }
            catch (Exception ex3) {
                System.out.println("oh nos! an exception");
            }
            switch (char1) {
                case 124: {
                    ++n2;
                    break;
                }
                case 126: {
                    ++n;
                    n2 = 0;
                    break;
                }
                default: {
                    this.levels[n][n2][n3] = Integer.parseInt(String.valueOf(char1));
                    if (++n3 > 11) {
                        n3 = 0;
                        break;
                    }
                    break;
                }
            }
        }
        this.tiles[0] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PEGSfloor.gif");
        this.tiles[1] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PegsMagic.GIF");
        this.tiles[2] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PegsTriangle.GIF");
        this.tiles[3] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PegsCircle.GIF");
        this.tiles[4] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PegsSquare.gif");
        this.tiles[5] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PegsMagic.GIF");
        this.tiles[6] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PEGSPit2.GIF");
        this.tiles[7] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PEGSwall.GIF");
        this.tiles[9] = this.ge.theMother.getImage(this.ge.theMother.getCodeBase(), "PEGSPegPusher.GIF");
        this.anim.setTiles(this.tiles);
        final MediaTracker mediaTracker = new MediaTracker(applet);
        for (int j = 0; j < 8; ++j) {
            mediaTracker.addImage(this.tiles[j], 0);
        }
        mediaTracker.addImage(this.tiles[9], 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println("e:" + ex);
        }
    }
    
    public boolean checkWin() {
        for (int i = 2; i < 14; ++i) {
            for (int j = 2; j < 10; ++j) {
                if (this.board[j][i] != 9 && this.board[j][i] != 6 && this.board[j][i] != 0 && this.board[j][i] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void cp(final int n, final int n2) {
        this.anim.setFlip(this.chpy - 2, this.chpx - 2, n, n2);
        this.board[this.chpy][this.chpx] = n;
    }
    
    public void toPlay() {
        this.ret = 0;
    }
}
