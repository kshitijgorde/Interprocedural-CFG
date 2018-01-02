import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Event;
import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class treasureHunt extends Applet
{
    Button newGame;
    BorderLayout theLayout;
    final int SIZE = 10;
    final int width = 20;
    char[][] theBoard;
    Image dug;
    Image snake;
    Image treasure;
    boolean gameOver;
    int lastRow;
    int lastCol;
    
    public void paint(final Graphics graphics) {
        final Point point = new Point((this.size().width - 200) / 2, (this.size().height - 200) / 2);
        graphics.setColor(new Color(0, 164, 0));
        int n = 0;
        do {
            int n2 = 0;
            do {
                if (this.theBoard[n][n2] == 'D') {
                    graphics.drawImage(this.dug, point.x + n2 * 20, point.y + n * 20, 20, 20, this);
                }
                else {
                    graphics.fillRect(point.x + n2 * 20, point.y + n * 20, 20, 20);
                }
            } while (++n2 < 10);
        } while (++n < 10);
        graphics.setColor(Color.black);
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                graphics.drawRect(point.x + n4 * 20, point.y + n3 * 20, 20, 20);
            } while (++n4 < 10);
        } while (++n3 < 10);
        final Font font = new Font("ComicSansMs", 3, 24);
        graphics.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        if (this.lastRow != -1) {
            if (this.theBoard[this.lastRow][this.lastCol] == 'S') {
                final String s = new String("YOU LOSE!!!");
                graphics.drawImage(this.snake, point.x + this.lastCol * 20, point.y + this.lastRow * 20, 20, 20, this);
                graphics.drawString(s, (this.size().width - fontMetrics.stringWidth(s)) / 2, fontMetrics.getHeight());
                return;
            }
            if (this.theBoard[this.lastRow][this.lastCol] == 'T') {
                final String s2 = new String("YOU WIN!!!");
                graphics.drawImage(this.treasure, point.x + this.lastCol * 20, point.y + this.lastRow * 20, 20, 20, this);
                graphics.drawString(s2, (this.size().width - fontMetrics.stringWidth(s2)) / 2, fontMetrics.getHeight());
            }
        }
        else {
            final Font font2 = new Font("ComicSansMs", 2, 12);
            graphics.setFont(font2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            final String s3 = new String("Click any square to dig...");
            graphics.drawImage(this.treasure, point.x + this.lastCol * 20, point.y + this.lastRow * 20, 20, 20, this);
            graphics.drawString(s3, (this.size().width - fontMetrics2.stringWidth(s3)) / 2, fontMetrics2.getHeight());
        }
    }
    
    public void repaint(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public treasureHunt() {
        this.newGame = new Button("New Game");
        this.theLayout = new BorderLayout();
        this.theBoard = new char[10][10];
        this.lastRow = -1;
        this.lastCol = -1;
    }
    
    private void checkLocation(int n, int n2) {
        final Point point = new Point((this.size().width - 200) / 2, (this.size().height - 200) / 2);
        n -= point.x;
        n2 -= point.y;
        final int lastCol = n / 20;
        final int lastRow = n2 / 20;
        if (this.theBoard[lastRow][lastCol] == '\0') {
            this.theBoard[lastRow][lastCol] = 'D';
        }
        else if (this.theBoard[lastRow][lastCol] == 'S' || this.theBoard[lastRow][lastCol] == 'T') {
            this.lastRow = lastRow;
            this.lastCol = lastCol;
            this.gameOver = true;
        }
        if (this.theBoard[lastRow][lastCol] == 'S') {
            this.play(this.getDocumentBase(), "snake.au");
        }
        else if (this.theBoard[lastRow][lastCol] == 'T') {
            this.play(this.getDocumentBase(), "win.au");
        }
        this.repaint();
    }
    
    private void startGame() {
        int n = 0;
        do {
            int n2 = 0;
            do {
                this.theBoard[n][n2] = '\0';
            } while (++n2 < 10);
        } while (++n < 10);
        final int n3 = 10;
        final Random random = new Random();
        int n4;
        int n5;
        do {
            n4 = (int)(random.nextDouble() * 9.0);
            n5 = (int)(random.nextDouble() * 9.0);
        } while (this.theBoard[n4][n5] != '\0');
        this.theBoard[n4][n5] = 'T';
        this.theBoard[n4 + 1][n5] = 'T';
        this.theBoard[n4][n5 + 1] = 'T';
        this.theBoard[n4 + 1][n5 + 1] = 'T';
        for (int i = 0; i < n3; ++i) {
            int n6;
            int n7;
            do {
                n6 = (int)(random.nextDouble() * 10.0);
                n7 = (int)(random.nextDouble() * 10.0);
            } while (this.theBoard[n6][n7] != '\0');
            this.theBoard[n6][n7] = 'S';
        }
        this.gameOver = false;
        this.lastRow = -1;
        this.lastCol = -1;
        this.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.startGame();
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final Point point = new Point((this.size().width - 200) / 2, (this.size().height - 200) / 2);
        if (n > point.x && n < point.x + 200 && n2 > point.y && n2 < point.y + 200 && !this.gameOver) {
            this.checkLocation(n, n2);
            return true;
        }
        return false;
    }
    
    public void init() {
        this.setBackground(Color.white);
        this.setLayout(this.theLayout);
        this.add("South", this.newGame);
        this.dug = this.getImage(this.getCodeBase(), "dug.gif");
        this.snake = this.getImage(this.getCodeBase(), "snake.gif");
        this.treasure = this.getImage(this.getCodeBase(), "treasure.gif");
        this.startGame();
    }
}
