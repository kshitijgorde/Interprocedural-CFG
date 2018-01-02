import java.awt.Event;
import java.util.Random;
import java.util.Date;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BattleShip extends Applet
{
    Player player1;
    Computer player2;
    private static int status;
    private static final int BEFOREGAME = -1;
    private static final int PLACINGSHIPS = -2;
    private static final int INPROGRESS = -3;
    private static final int GAMEOVER = -4;
    String sMessage1;
    String sMessage2;
    int hits1;
    int miss1;
    int hits2;
    int miss2;
    private int numShipsPlaced;
    Image offscreenImage;
    Graphics offscreen;
    
    public void paint(final Graphics screen) {
        this.getAppletContext().showStatus("BattleShip ©2000 by Branton Boehm, homemadejava.com");
        this.offscreen.setColor(Color.darkGray);
        this.offscreen.fillRect(0, 0, 450, 225);
        this.offscreen.setColor(Color.blue);
        this.offscreen.fillRect(15, 15, 150, 150);
        this.offscreen.fillRect(180, 15, 150, 150);
        this.offscreen.setColor(Color.black);
        for (int row = 0; row < 10; ++row) {
            for (int col = 0; col < 10; ++col) {
                this.offscreen.drawRect(15 + 15 * col, 15 + 15 * row, 15, 15);
                this.offscreen.drawRect(180 + 15 * col, 15 + 15 * row, 15, 15);
            }
        }
        this.offscreen.setColor(Color.lightGray);
        Font theFont = new Font("Helvetica", 1, 14);
        FontMetrics fm = this.getFontMetrics(theFont);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString("Your Water", (150 - fm.stringWidth("Your Water")) / 2 + 15, 13);
        this.offscreen.drawString("Opponent's Water", (150 - fm.stringWidth("Opponent's Water")) / 2 + 180, 13);
        this.offscreen.drawString("Statistics", (90 - fm.stringWidth("Statistics")) / 2 + 345, 13);
        this.offscreen.setColor(Color.lightGray);
        this.offscreen.fillRect(345, 180, 90, 30);
        this.offscreen.setColor(Color.black);
        this.offscreen.drawRect(345, 180, 90, 30);
        theFont = new Font("Helvetica", 1, 16);
        fm = this.getFontMetrics(theFont);
        this.offscreen.setFont(theFont);
        this.offscreen.setColor(Color.blue);
        this.offscreen.drawString("New Game", (90 - fm.stringWidth("New Game")) / 2 + 345, 200);
        this.offscreen.setColor(Color.white);
        this.offscreen.fillRect(345, 15, 90, 150);
        this.offscreen.setColor(Color.black);
        this.offscreen.drawRect(345, 15, 90, 150);
        theFont = new Font("Helvetica", 2, 10);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString("You", 350, 25);
        theFont = new Font("Helvetica", 0, 10);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString("Misses:", 360, 35);
        this.offscreen.drawString(Integer.toString(this.miss1), 410, 35);
        this.offscreen.drawString("Hits:", 360, 45);
        this.offscreen.drawString(Integer.toString(this.hits1), 410, 45);
        this.offscreen.drawString("Sinks:", 360, 55);
        this.offscreen.drawString(Integer.toString(5 - this.player2.fleet.size()), 410, 55);
        theFont = new Font("Helvetica", 2, 10);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString("Opponent", 350, 75);
        theFont = new Font("Helvetica", 0, 10);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString("Misses:", 360, 85);
        this.offscreen.drawString(Integer.toString(this.miss2), 410, 85);
        this.offscreen.drawString("Hits:", 360, 95);
        this.offscreen.drawString(Integer.toString(this.hits2), 410, 95);
        this.offscreen.drawString("Sinks:", 360, 105);
        this.offscreen.drawString(Integer.toString(5 - this.player1.fleet.size()), 410, 105);
        this.offscreen.setColor(Color.lightGray);
        for (int row = 0; row < 10; ++row) {
            for (int col = 0; col < 10; ++col) {
                if (this.player1.myOcean.getSquare(row, col) == 's') {
                    this.offscreen.fillRect(16 + 15 * col, 16 + 15 * row, 14, 14);
                }
            }
        }
        for (int row = 0; row < 10; ++row) {
            for (int col = 0; col < 10; ++col) {
                if (this.player1.hisOcean.getSquare(row, col) == 'h') {
                    this.offscreen.setColor(Color.red);
                    this.offscreen.fillOval(184 + 15 * col, 19 + 15 * row, 9, 9);
                }
                if (this.player1.hisOcean.getSquare(row, col) == 'm') {
                    this.offscreen.setColor(Color.white);
                    this.offscreen.fillOval(184 + 15 * col, 19 + 15 * row, 9, 9);
                }
                if (this.player1.myOcean.getSquare(row, col) == 'h') {
                    this.offscreen.setColor(Color.lightGray);
                    this.offscreen.fillRect(16 + 15 * col, 16 + 15 * row, 14, 14);
                    this.offscreen.setColor(Color.red);
                    this.offscreen.fillOval(19 + 15 * col, 19 + 15 * row, 9, 9);
                }
                if (this.player1.myOcean.getSquare(row, col) == 'm') {
                    this.offscreen.setColor(Color.blue);
                    this.offscreen.fillRect(16 + 15 * col, 16 + 15 * row, 14, 14);
                    this.offscreen.setColor(Color.white);
                    this.offscreen.fillOval(19 + 15 * col, 19 + 15 * row, 9, 9);
                }
            }
        }
        this.offscreen.setColor(Color.white);
        this.offscreen.fillRect(15, 180, 315, 30);
        this.offscreen.setColor(Color.black);
        this.offscreen.drawRect(15, 180, 315, 30);
        theFont = new Font("Helvetica", 0, 12);
        fm = this.getFontMetrics(theFont);
        this.offscreen.setFont(theFont);
        this.offscreen.drawString(this.sMessage1, (315 - fm.stringWidth(this.sMessage1)) / 2 + 15, 193);
        this.offscreen.drawString(this.sMessage2, (315 - fm.stringWidth(this.sMessage2)) / 2 + 15, 208);
        screen.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    private Point getRowCol(final Point topLeft, final int sizeSquare, final int x, final int y) {
        int row = -1;
        int col = -1;
        if (x < topLeft.x || x > topLeft.x + 10 * sizeSquare) {
            col = -1;
        }
        if (y < topLeft.y || y > topLeft.y + 10 * sizeSquare) {
            row = -1;
        }
        for (int i = 0; i < 10; ++i) {
            if (x > topLeft.x + sizeSquare * i && x < topLeft.x + sizeSquare * (i + 1)) {
                col = i;
            }
            if (y > topLeft.y + sizeSquare * i && y < topLeft.y + sizeSquare * (i + 1)) {
                row = i;
            }
        }
        return new Point(row, col);
    }
    
    public void update(final Graphics screen) {
        this.paint(screen);
    }
    
    void startGame() {
        this.player1 = new Player();
        this.player2 = new Computer();
        this.numShipsPlaced = 0;
        this.hits1 = 0;
        this.miss1 = 0;
        this.hits2 = 0;
        this.miss2 = 0;
        this.player1.myOcean.setDimensions(new Point(15, 15), 15);
        this.player1.hisOcean.setDimensions(new Point(180, 15), 15);
        this.player2.myOcean.setDimensions(new Point(180, 15), 15);
        this.player2.hisOcean.setDimensions(new Point(15, 15), 15);
        final Random randNum = new Random(new Date().getTime());
        for (int i = 0; i < this.player2.fleet.size(); ++i) {
            int row;
            int col;
            boolean isHoriz;
            do {
                row = (int)(randNum.nextDouble() * 10.0);
                col = (int)(randNum.nextDouble() * 10.0);
                isHoriz = ((int)(randNum.nextDouble() * 2.0) == 1);
            } while (!this.player2.fleet.elementAt(i).placeShip(this.player2.myOcean, row, col, isHoriz));
        }
        BattleShip.status = -2;
        this.sMessage1 = "Placing your " + this.player1.fleet.elementAt(this.numShipsPlaced).getName() + ".";
        this.sMessage2 = "Left click for horizontal. Right click for vertical.";
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (x > 345 && x < 435 && y > 180 && y < 210) {
            this.startGame();
            this.repaint();
            return true;
        }
        int row = -1;
        int col = -1;
        Point combo = new Point(row, col);
        switch (BattleShip.status) {
            case -2: {
                combo = this.getRowCol(this.player1.myOcean.m_topLeft, this.player1.myOcean.m_width, x, y);
                row = combo.x;
                col = combo.y;
                final boolean isHoriz = !evt.metaDown();
                if (this.player1.fleet.elementAt(this.numShipsPlaced).placeShip(this.player1.myOcean, row, col, isHoriz)) {
                    ++this.numShipsPlaced;
                }
                if (this.numShipsPlaced == this.player1.fleet.size()) {
                    BattleShip.status = -3;
                    this.sMessage1 = "Your turn.  Shoot at opponent";
                    this.sMessage2 = "";
                    this.repaint();
                    return true;
                }
                this.sMessage1 = "Placing your " + this.player1.fleet.elementAt(this.numShipsPlaced).getName() + ".";
                this.sMessage2 = "Left click for horizontal. Right click for vertical.";
                this.repaint();
                return true;
            }
            case -3: {
                combo = this.getRowCol(this.player1.hisOcean.m_topLeft, this.player1.hisOcean.m_width, x, y);
                row = combo.x;
                col = combo.y;
                if (row == -1 || col == -1) {
                    return true;
                }
                int numShips = this.player2.fleet.size();
                char result = this.player2.checkShot(row, col);
                if (result == '*') {
                    return true;
                }
                this.player1.hisOcean.markBoard(row, col, result);
                if (result == 'h') {
                    this.sMessage1 = "Your shot was a HIT!";
                    ++this.hits1;
                }
                else {
                    this.sMessage1 = "Your shot was a MISS.";
                    ++this.miss1;
                }
                if (numShips != this.player2.fleet.size()) {
                    this.sMessage1 += " You SUNK a ship!";
                }
                if (this.player2.fleet.size() == 0) {
                    BattleShip.status = -4;
                    this.sMessage1 = "YOU WIN!!!";
                    this.sMessage2 = "Click \"New Game\" play again.";
                    this.repaint();
                    return true;
                }
                this.repaint();
                combo = this.player2.getShot(this.player2.myOcean.m_topLeft, this.player2.myOcean.m_width);
                row = combo.x;
                col = combo.y;
                numShips = this.player1.fleet.size();
                result = this.player1.checkShot(row, col);
                this.player2.hisOcean.markBoard(row, col, result);
                boolean wasSunk = false;
                if (numShips != this.player1.fleet.size()) {
                    wasSunk = true;
                }
                this.player2.getResponse(result, wasSunk);
                if (result == 'h') {
                    this.sMessage2 = "The computer HIT you!";
                    ++this.hits2;
                }
                else {
                    this.sMessage2 = "The computer MISSED you.";
                    ++this.miss2;
                }
                if (this.player1.fleet.size() == 0) {
                    BattleShip.status = -4;
                    this.sMessage1 = "You lost.";
                    this.sMessage2 = "Click \"New Game\" play again.";
                    this.repaint();
                    return true;
                }
                this.repaint();
                return true;
            }
            case -4: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void init() {
        this.offscreenImage = this.createImage(this.size().width, this.size().height);
        this.offscreen = this.offscreenImage.getGraphics();
        this.startGame();
    }
}
