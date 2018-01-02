import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class PentominosBoardCanvas extends Panel
{
    int[] board;
    PentominosSolver owner;
    Color[] pieceColor;
    
    PentominosBoardCanvas(final int[] board, final PentominosSolver owner) {
        this.board = board;
        this.owner = owner;
        this.MakeColors();
    }
    
    void MakeColors() {
        (this.pieceColor = new Color[13])[0] = Color.white;
        this.pieceColor[1] = new Color(200, 0, 0);
        this.pieceColor[2] = new Color(150, 150, 255);
        this.pieceColor[3] = new Color(0, 200, 200);
        this.pieceColor[4] = new Color(255, 150, 255);
        this.pieceColor[5] = new Color(0, 200, 0);
        this.pieceColor[6] = new Color(150, 255, 255);
        this.pieceColor[7] = new Color(200, 200, 0);
        this.pieceColor[8] = new Color(0, 0, 200);
        this.pieceColor[9] = new Color(255, 150, 150);
        this.pieceColor[10] = new Color(200, 0, 200);
        this.pieceColor[11] = new Color(255, 255, 150);
        this.pieceColor[12] = new Color(150, 255, 150);
    }
    
    public synchronized void paint(final Graphics graphics) {
        final int n = 8 * (this.size().width / 8) + 1;
        final int n2 = 8 * (this.size().height / 8) + 1;
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        for (int i = 0; i <= 8; ++i) {
            final int n3 = i * ((n - 1) / 8);
            graphics.drawLine(n3, 0, n3, n2 - 1);
        }
        for (int j = 0; j <= 8; ++j) {
            final int n4 = j * ((n2 - 1) / 8);
            graphics.drawLine(0, n4, n - 1, n4);
        }
        for (int k = 1; k <= 8; ++k) {
            final int n5 = (k - 1) * ((n2 - 1) / 8);
            for (int l = 1; l <= 8; ++l) {
                final int n6 = (l - 1) * ((n - 1) / 8);
                if (this.board[10 * k + l] == -1) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(this.pieceColor[this.board[10 * k + l]]);
                }
                graphics.fillRect(n6 + 1, n5 + 1, (n - 1) / 8 - 1, (n2 - 1) / 8 - 1);
            }
        }
        graphics.setColor(color);
    }
    
    synchronized void putSquare(final Graphics graphics, final int n, final int n2) {
        final int n3 = 8 * (this.size().width / 8) + 1;
        final int n4 = 8 * (this.size().height / 8) + 1;
        final int n5 = (n2 % 10 - 1) * (n3 - 1) / 8;
        final int n6 = (n2 / 10 - 1) * (n4 - 1) / 8;
        graphics.setColor(this.pieceColor[n]);
        graphics.fillRect(n5 + 1, n6 + 1, (n3 - 1) / 8 - 1, (n4 - 1) / 8 - 1);
        graphics.setColor(Color.black);
        this.board[n2] = n;
    }
    
    synchronized void clearSquare(final Graphics graphics, final int n) {
        final int n2 = 8 * (this.size().width / 8) + 1;
        final int n3 = 8 * (this.size().height / 8) + 1;
        final int n4 = (n % 10 - 1) * (n2 - 1) / 8;
        final int n5 = (n / 10 - 1) * (n3 - 1) / 8;
        graphics.setColor(this.pieceColor[0]);
        graphics.fillRect(n4 + 1, n5 + 1, (n2 - 1) / 8 - 1, (n3 - 1) / 8 - 1);
        graphics.setColor(Color.black);
        this.board[n] = 0;
    }
    
    synchronized void blackenSquare(final Graphics graphics, final int n) {
        final int n2 = 8 * (this.size().width / 8) + 1;
        final int n3 = 8 * (this.size().height / 8) + 1;
        graphics.fillRect((n % 10 - 1) * (n2 - 1) / 8, (n / 10 - 1) * (n3 - 1) / 8, (n2 - 1) / 8, (n3 - 1) / 8);
        this.board[n] = -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = 8 * (this.size().width / 8) + 1;
        final int n4 = n2 / ((8 * (this.size().height / 8) + 1 - 1) / 8) + 1;
        final int n5 = n / ((n3 - 1) / 8) + 1;
        if (n4 > 0 && n4 < 9 && n5 > 0 && n5 < 9) {
            this.owner.doBoardClick(10 * n4 + n5);
        }
        return true;
    }
    
    public Dimension minimumSize() {
        return new Dimension(160, 160);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
