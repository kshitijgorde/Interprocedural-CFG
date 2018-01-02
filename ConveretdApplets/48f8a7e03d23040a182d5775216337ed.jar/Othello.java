import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Othello extends Applet implements Runnable
{
    final int BLACK = 1;
    final int WHITE = -1;
    final int EMPTY = 0;
    final int WIDTH = 480;
    final int SPACE = 80;
    private final int UPPER = 0;
    private final int LOWER = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;
    private final int UPPERLEFT = 4;
    private final int UPPERRIGHT = 5;
    private final int LOWERRIGHT = 6;
    private final int LOWERLEFT = 7;
    boolean[] direction;
    public int turn;
    protected int[][] stone;
    protected int counter_black;
    protected int counter_white;
    int audioColumn;
    int audioRow;
    OthelloPlayer computer;
    
    public void init() {
        this.stone = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                this.stone[i][j] = 0;
            }
        }
        this.stone[3][3] = 1;
        this.stone[4][3] = -1;
        this.stone[3][4] = -1;
        this.stone[4][4] = 1;
        this.countStone();
        this.turn = 1;
        for (int k = 1; k <= 8; ++k) {
            this.getAudioClip(this.getCodeBase(), "audio/" + k + ".au");
        }
        this.getAudioClip(this.getCodeBase(), "audio/black.au");
        this.getAudioClip(this.getCodeBase(), "audio/white.au");
        this.computer = new OthelloPlayer(this);
    }
    
    public void paint(final Graphics graphics) {
        this.drawBoard(graphics);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (this.stone[i][j] != 0) {
                    this.drawStone(i, j, graphics);
                }
            }
        }
        this.drawTurn(graphics);
        this.drawCountStone(graphics);
        if (this.counter_black + this.counter_white == 64) {
            this.showWinner(graphics);
        }
    }
    
    public void drawBoard(final Graphics graphics) {
        this.setBackground(Color.green);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, 0, 480);
        graphics.drawLine(480, 0, 480, 480);
        graphics.drawLine(0, 0, 480, 0);
        graphics.drawLine(0, 480, 480, 480);
        for (int i = 1; i < 8; ++i) {
            graphics.drawLine(480 * i / 8, 0, 480 * i / 8, 480);
            graphics.drawLine(0, 480 * i / 8, 480, 480 * i / 8);
        }
    }
    
    public void drawStone(final int n, final int n2, final Graphics graphics) {
        if (this.stone[n][n2] == 1) {
            graphics.setColor(Color.black);
        }
        else if (this.stone[n][n2] == -1) {
            graphics.setColor(Color.white);
        }
        graphics.fillOval(n * 480 / 8 + 10, n2 * 480 / 8 + 10, 40, 40);
    }
    
    void drawTurn(final Graphics graphics) {
        final String s = "Black";
        final String s2 = "White";
        final String s3 = " player's turn";
        graphics.setColor(Color.blue);
        if (this.turn == 1) {
            graphics.drawString(s + s3, 240, 515);
            graphics.setColor(Color.black);
            this.showStatus("Your turn!");
        }
        else {
            graphics.drawString(s2 + s3, 240, 515);
            graphics.setColor(Color.white);
            this.showStatus("Compurter's turn!");
        }
        graphics.fill3DRect(220, 500, 20, 20, true);
    }
    
    void showWinner(final Graphics graphics) {
        graphics.clearRect(0, 0, 481, 481);
        if (this.counter_black > this.counter_white) {
            for (int i = 0; i < 255; ++i) {
                graphics.fillRect(0, 0, 481, 481);
                graphics.drawString("You", 150, 100);
                graphics.drawString("Won!", 130, 200);
            }
            return;
        }
        if (this.counter_black < this.counter_white) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 481, 481);
            for (int j = 255; j >= 0; --j) {
                graphics.drawString("You", 150, 100);
                graphics.drawString("lost...", 75, 200);
            }
            return;
        }
        graphics.drawString("Draw", 100, 100);
        graphics.drawString("Game", 100, 200);
    }
    
    void countStone() {
        this.counter_black = 0;
        this.counter_white = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (this.stone[i][j] == 1) {
                    ++this.counter_black;
                }
                if (this.stone[i][j] == -1) {
                    ++this.counter_white;
                }
            }
        }
        if (this.counter_black + this.counter_white == 64) {
            this.endGame();
            this.init();
        }
    }
    
    public void endGame() {
        this.repaint();
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex) {}
    }
    
    void drawCountStone(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fill3DRect(495, 130, 30, 20, false);
        graphics.fill3DRect(495, 190, 30, 20, false);
        graphics.fill3DRect(485, 160, 20, 20, true);
        graphics.setColor(Color.black);
        graphics.fill3DRect(485, 100, 20, 20, true);
        graphics.drawString("Black", 510, 115);
        graphics.drawString("White", 510, 175);
        graphics.drawString(Integer.toString(this.counter_black), 500, 145);
        graphics.drawString(Integer.toString(this.counter_white), 500, 205);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int n3 = n / 60;
        final int n4 = n2 / 60;
        if (this.turn == 1 && this.checkStone(n3, n4, this.turn)) {
            this.turnStone(n3, n4, this.turn);
            this.playAudio(n3, n4);
            this.turn = -this.turn;
            this.countStone();
            this.update(this.getGraphics());
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
        }
        if (this.turn == -1) {
            this.computer.decide();
            this.countStone();
            this.update(this.getGraphics());
        }
        return true;
    }
    
    public void playAudio(final int n, final int n2) {
        this.audioColumn = n + 1;
        this.audioRow = n2 + 1;
        new Thread(this).start();
    }
    
    public void run() {
        this.play(this.getCodeBase(), "audio/" + this.audioColumn + ".au");
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex) {}
        this.play(this.getCodeBase(), "audio/" + this.audioRow + ".au");
    }
    
    public boolean checkAll(final int n) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (this.checkStone(i, j, n)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkStone(final int n, final int n2, final int n3) {
        for (int i = 0; i < 8; ++i) {
            this.direction[i] = false;
        }
        if (this.stone[n][n2] != 0) {
            return false;
        }
        if (n > 1 && this.stone[n - 1][n2] == -n3) {
            int n4;
            for (n4 = n - 2; n4 > 0 && this.stone[n4][n2] == -n3; --n4) {}
            if (this.stone[n4][n2] == n3) {
                this.direction[3] = true;
            }
        }
        if (n < 6 && this.stone[n + 1][n2] == -n3) {
            int n5;
            for (n5 = n + 2; n5 < 7 && this.stone[n5][n2] == -n3; ++n5) {}
            if (this.stone[n5][n2] == n3) {
                this.direction[2] = true;
            }
        }
        if (n2 > 1 && this.stone[n][n2 - 1] == -n3) {
            int n6;
            for (n6 = n2 - 2; n6 > 0 && this.stone[n][n6] == -n3; --n6) {}
            if (this.stone[n][n6] == n3) {
                this.direction[0] = true;
            }
        }
        if (n2 < 6 && this.stone[n][n2 + 1] == -n3) {
            int n7;
            for (n7 = n2 + 2; n7 < 7 && this.stone[n][n7] == -n3; ++n7) {}
            if (this.stone[n][n7] == n3) {
                this.direction[1] = true;
            }
        }
        if (n > 1 && n2 > 1 && this.stone[n - 1][n2 - 1] == -n3) {
            int n8;
            int n9;
            for (n8 = n - 2, n9 = n2 - 2; n8 > 0 && n9 > 0 && this.stone[n8][n9] == -n3; --n8, --n9) {}
            if (this.stone[n8][n9] == n3) {
                this.direction[4] = true;
            }
        }
        if (n < 6 && n2 > 1 && this.stone[n + 1][n2 - 1] == -n3) {
            int n10;
            int n11;
            for (n10 = n + 2, n11 = n2 - 2; n10 < 7 && n11 > 0 && this.stone[n10][n11] == -n3; ++n10, --n11) {}
            if (this.stone[n10][n11] == n3) {
                this.direction[5] = true;
            }
        }
        if (n < 6 && n2 < 6 && this.stone[n + 1][n2 + 1] == -n3) {
            int n12;
            int n13;
            for (n12 = n + 2, n13 = n2 + 2; n12 < 7 && n13 < 7 && this.stone[n12][n13] == -n3; ++n12, ++n13) {}
            if (this.stone[n12][n13] == n3) {
                this.direction[6] = true;
            }
        }
        if (n > 1 && n2 < 6 && this.stone[n - 1][n2 + 1] == -n3) {
            int n14;
            int n15;
            for (n14 = n - 2, n15 = n2 + 2; n14 > 0 && n15 < 7 && this.stone[n14][n15] == -n3; --n14, ++n15) {}
            if (this.stone[n14][n15] == n3) {
                this.direction[7] = true;
            }
        }
        for (int j = 0; j < 8; ++j) {
            if (this.direction[j]) {
                return true;
            }
        }
        return false;
    }
    
    public void turnStone(final int n, final int n2, final int n3) {
        this.stone[n][n2] = n3;
        if (this.direction[3]) {
            for (int n4 = n - 1; this.stone[n4][n2] != n3; --n4) {
                this.stone[n4][n2] = -this.stone[n4][n2];
            }
        }
        if (this.direction[2]) {
            for (int n5 = n + 1; this.stone[n5][n2] != n3; ++n5) {
                this.stone[n5][n2] = -this.stone[n5][n2];
            }
        }
        if (this.direction[0]) {
            for (int n6 = n2 - 1; this.stone[n][n6] != n3; --n6) {
                this.stone[n][n6] = -this.stone[n][n6];
            }
        }
        if (this.direction[1]) {
            for (int n7 = n2 + 1; this.stone[n][n7] != n3; ++n7) {
                this.stone[n][n7] = -this.stone[n][n7];
            }
        }
        if (this.direction[4]) {
            for (int n8 = n - 1, n9 = n2 - 1; this.stone[n8][n9] != n3; --n8, --n9) {
                this.stone[n8][n9] = -this.stone[n8][n9];
            }
        }
        if (this.direction[5]) {
            for (int n10 = n + 1, n11 = n2 - 1; this.stone[n10][n11] != n3; ++n10, --n11) {
                this.stone[n10][n11] = -this.stone[n10][n11];
            }
        }
        if (this.direction[6]) {
            for (int n12 = n + 1, n13 = n2 + 1; this.stone[n12][n13] != n3; ++n12, ++n13) {
                this.stone[n12][n13] = -this.stone[n12][n13];
            }
        }
        if (this.direction[7]) {
            for (int n14 = n - 1, n15 = n2 + 1; this.stone[n14][n15] != n3; --n14, ++n15) {
                this.stone[n14][n15] = -this.stone[n14][n15];
            }
        }
    }
    
    public Othello() {
        this.direction = (boolean[])new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
