import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Board
{
    public static final int SIZE = 10;
    public static final char SHIP = 's';
    public static final char HIT = 'h';
    public static final char MISS = 'm';
    public static final char WATER = ' ';
    private char[][] m_archGrid;
    public Point m_topLeft;
    public int m_width;
    
    Board() {
        this.m_archGrid = new char[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.m_archGrid[i][j] = ' ';
            }
        }
    }
    
    public void markBoard(final int iRow, final int iCol, final char chStatus) {
        this.m_archGrid[iRow][iCol] = chStatus;
    }
    
    public char getSquare(final int iRow, final int iCol) {
        return this.m_archGrid[iRow][iCol];
    }
    
    public void setDimensions(final Point topLeft, final int width) {
        this.m_topLeft = topLeft;
        this.m_width = width;
    }
}
