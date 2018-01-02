import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class BoardMap extends Applet
{
    private byte[][] planOfBoard;
    private final byte FreeSpace = 99;
    
    public BoardMap() {
        this.planOfBoard = new byte[80][];
        for (int i = 0; i < 80; ++i) {
            this.planOfBoard[i] = new byte[80];
        }
        this.clearBoard();
    }
    
    public void clearBoard() {
        for (int i = 0; i < 80; ++i) {
            for (int j = 0; j < 80; ++j) {
                this.planOfBoard[i][j] = 99;
            }
        }
    }
    
    public void putWay(final int n, final int n2, final byte b) {
        this.planOfBoard[n / 4 - 10][n2 / 4 - 10] = b;
    }
    
    public byte getWay(final int n, final int n2) {
        return this.planOfBoard[n / 4 - 10][n2 / 4 - 10];
    }
    
    public void putFood(final int n, final int n2, final byte b) {
        this.planOfBoard[n][n2] = b;
    }
    
    public byte getFood(final int n, final int n2) {
        return this.planOfBoard[n][n2];
    }
}
