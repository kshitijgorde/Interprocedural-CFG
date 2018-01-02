import java.net.MalformedURLException;
import java.applet.Applet;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.KeyEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class GameEngine
{
    public static final int SPLASH = 0;
    public static final int PLAY = 1;
    public static final int NICE_PEGING = 2;
    public static final int INIT = 3;
    public static final int LOAD = 4;
    public static final int WIN = 5;
    public static final int DELAY = 7;
    public static final int LEVELEDIT = 9;
    public static final int CHOOSE = 6;
    public static final int INSTRUCTIONS = 10;
    public static final int LEVELNUM = 14;
    nInput i;
    nButton nBinstructions;
    nButton nBplay;
    nButton nBcode;
    nTextArea txtInstructions;
    boolean checkinput;
    int level;
    int delayAfterState;
    int delayTime;
    int delayElapsedTime;
    int GameState;
    Pegs theMother;
    board b;
    boolean drawMov;
    boolean guiRender;
    public statusBar sb;
    int cp;
    KeyEvent k;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    boolean space;
    boolean plus;
    boolean minus;
    boolean backspace;
    
    public GameEngine(final URL url, final Pegs theMother, final Graphics graphics) {
        this.i = new nInput();
        this.level = 0;
        this.drawMov = true;
        this.guiRender = true;
        this.sb = new statusBar();
        this.cp = 0;
        this.theMother = theMother;
        this.GameState = 3;
        this.b = new board(url, this);
        this.init(graphics);
    }
    
    public void init(final Graphics graphics) {
        this.nBinstructions = new nButton(0, 350, 100, 14, "Instructions", this.i, graphics.getFontMetrics());
        this.nBplay = new nButton(0, 350, 100, 14, "Play", this.i, graphics.getFontMetrics());
        this.nBcode = new nButton(279, 350, 200, 14, "Code by Argonide, Art by BH4", this.i, graphics.getFontMetrics());
        (this.txtInstructions = new nTextArea(0, 0, 479, 350, graphics.getFontMetrics(), this.theMother)).addRow("", "Instructions: Use the arrow keys to move the Peg Pusher. To win you must clear the board of all pegs. Pegs include: the circle, the triangle(when pushed into another triangle it becomes a wall), the square(when pushed into a pit it will fill the pit making a clear space), and the magic peg(when pushed into another magic peg it make a new peg of your choice, pick the new peg using the up and down keys, space to confirm). Unless otherwise noted, when two pegs of the same type are pushed into each other, both pegs will be removed. Pegs will also be removed if you push them into a pit. Press backspace at any time to restart the current level.  ");
        this.txtInstructions.addRow("", "");
        this.txtInstructions.addRow("", "");
        this.txtInstructions.addRow("", "Pegs is based on game available for graphing calculators made by Detached Solutions(DetachedSolutions.com).  It is part of 'puzzPack'.");
    }
    
    public void Cycle(final Graphics graphics) {
        switch (this.GameState) {
            case 3: {
                this.GameState = 0;
                break;
            }
            case 0: {
                this.changeState(4);
                break;
            }
            case 4: {
                if (this.level == 15) {
                    this.sb.set("You have won, have a nice life...... hint:+ and - change levels up and down", 500);
                    this.level = 0;
                    this.changeStateDelay(500, 4);
                    break;
                }
                this.changeState(1);
                break;
            }
            case 1: {
                if (this.checkinput) {
                    this.processInput();
                    this.checkinput = false;
                }
                this.b.drawBoard(graphics);
                this.sb.draw(graphics);
                if (this.guiRender) {
                    this.guiRend(graphics);
                    break;
                }
                break;
            }
            case 6: {
                this.b.drawBoard(graphics);
                this.sb.draw(graphics);
                if (this.checkinput) {
                    this.chooseInput();
                    this.checkinput = false;
                    break;
                }
                break;
            }
            case 5: {
                this.sb.set("Nice Pegging", 120);
                this.b.win();
                this.changeStateDelay(120, 4);
                break;
            }
            case 7: {
                this.b.drawBoard(graphics);
                this.sb.draw(graphics);
                ++this.delayElapsedTime;
                if (this.guiRender) {
                    this.guiRend(graphics);
                }
                if (this.delayElapsedTime == this.delayTime) {
                    this.delayElapsedTime = 0;
                    this.changeState(this.delayAfterState);
                    break;
                }
                break;
            }
            case 9: {
                this.editInput();
                this.b.drawBoard(graphics);
                break;
            }
            case 10: {
                if (this.guiRender) {
                    this.guiRend(graphics);
                    break;
                }
                break;
            }
        }
    }
    
    public void guiRend(final Graphics graphics) {
        if (this.nBcode.update()) {
            try {
                this.theMother.getAppletContext().showDocument(new URL("http://www.s89785877.onlinehome.us/"), "_top");
            }
            catch (MalformedURLException ex) {
                System.out.println(ex);
            }
        }
        this.nBcode.render(graphics);
        this.guiRender = false;
        if (this.GameState == 10) {
            this.nBplay.render(graphics);
            if (this.nBplay.update()) {
                this.GameState = 1;
                this.b.drawentireboard = true;
                this.sb.needsdraw = true;
                this.guiRender = true;
            }
        }
        else {
            this.nBinstructions.render(graphics);
            if (this.nBinstructions.update()) {
                this.txtInstructions.render(graphics);
                this.GameState = 10;
                this.guiRender = true;
            }
        }
    }
    
    public void changeStateDelay(final int delayTime, final int delayAfterState) {
        this.delayTime = delayTime;
        this.delayElapsedTime = 0;
        this.delayAfterState = delayAfterState;
        this.GameState = 7;
    }
    
    public void changeState(final int gameState) {
        switch (gameState) {
            case 4: {
                if (this.level != 15) {
                    this.b.loadlevel(this.level);
                    this.drawMov = true;
                    break;
                }
                break;
            }
            case 1: {
                this.b.toPlay();
                break;
            }
            case 5: {
                ++this.level;
                break;
            }
            case 6: {
                this.sb.set("Up and Down to change piece, space to select", 300);
                this.cp = 5;
                break;
            }
        }
        this.b.drawentireboard = true;
        this.sb.needsdraw = true;
        this.guiRender = true;
        this.GameState = gameState;
    }
    
    public int GetGameState() {
        return this.GameState;
    }
    
    public void getBlah(final KeyEvent k) {
        this.k = k;
        if (this.k.getKeyCode() == 37) {
            this.left = true;
        }
        if (this.k.getKeyCode() == 39) {
            this.right = true;
        }
        if (this.k.getKeyCode() == 38) {
            this.up = true;
        }
        if (this.k.getKeyCode() == 40) {
            this.down = true;
        }
        if (this.k.getKeyCode() == 32) {
            this.space = true;
        }
        if (this.k.getKeyCode() == 8) {
            this.backspace = true;
        }
        if (this.k.getKeyChar() == '-') {
            this.minus = true;
        }
        if (this.k.getKeyChar() == '+') {
            this.plus = true;
        }
        this.checkinput = true;
    }
    
    public void editInput() {
        if (this.up) {}
        if (this.down) {}
    }
    
    public void processInput() {
        if (this.plus) {
            ++this.level;
            this.changeState(4);
        }
        if (this.minus && this.level > 0) {
            --this.level;
            this.changeState(4);
        }
        if (this.backspace) {
            this.changeState(4);
        }
        if (this.up) {
            this.iproc(this.b.movep(0, -1));
        }
        else if (this.down) {
            this.iproc(this.b.movep(0, 1));
        }
        else if (this.left) {
            this.iproc(this.b.movep(-1, 0));
        }
        else if (this.right) {
            this.iproc(this.b.movep(1, 0));
        }
        final boolean up = false;
        this.minus = up;
        this.plus = up;
        this.minus = up;
        this.backspace = up;
        this.space = up;
        this.right = up;
        this.left = up;
        this.down = up;
        this.up = up;
    }
    
    public void changeStateChoose() {
        this.cp = 2;
    }
    
    public void iproc(final int n) {
        switch (n) {
            case 0: {
                if (this.b.checkWin()) {
                    this.changeState(5);
                    break;
                }
                break;
            }
            case 1: {
                this.changeStateDelay(200, 4);
                break;
            }
            case 5: {
                this.changeState(6);
                break;
            }
        }
    }
    
    public void chooseInput() {
        if (this.up) {
            this.theMother.rot.play();
            final int cp = this.cp;
            ++this.cp;
            if (this.cp == 6) {
                this.cp = 2;
            }
            this.b.cp(this.cp, cp);
        }
        if (this.down) {
            this.theMother.rot.play();
            final int cp2 = this.cp;
            --this.cp;
            if (this.cp == 1) {
                this.cp = 5;
            }
            this.b.cp(this.cp, cp2);
        }
        if (this.space) {
            this.changeState(1);
        }
        final boolean up = false;
        this.space = up;
        this.right = up;
        this.left = up;
        this.down = up;
        this.up = up;
    }
    
    public void setMouse(final int n, final int n2) {
        this.i.setMouseCoord(n, n2);
        this.guiRender = true;
    }
    
    public void setMousem(final boolean b) {
        this.i.setmDown(b);
        this.guiRender = true;
    }
}
