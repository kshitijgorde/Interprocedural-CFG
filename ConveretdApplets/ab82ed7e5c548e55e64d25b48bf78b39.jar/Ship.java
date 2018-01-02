// 
// Decompiled by Procyon v0.5.30
// 

public class Ship
{
    private int m_iRow;
    private int m_iCol;
    private int m_iLength;
    private boolean m_bIsHoriz;
    private String m_sName;
    
    Ship(final int iLength, final String sName) {
        this.m_iLength = iLength;
        this.m_sName = sName;
    }
    
    public boolean hasSunk(final Board field) {
        for (int i = 0; i < this.m_iLength; ++i) {
            if (this.m_bIsHoriz) {
                if (field.getSquare(this.m_iRow, this.m_iCol + i) == 's') {
                    return false;
                }
            }
            else if (field.getSquare(this.m_iRow + i, this.m_iCol) == 's') {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        return this.m_sName;
    }
    
    public boolean placeShip(final Board field, final int iRow, final int iCol, final boolean bIsHoriz) {
        if (iRow < 0 || iCol < 0) {
            return false;
        }
        for (int i = 0; i < this.m_iLength; ++i) {
            if (bIsHoriz) {
                if (iCol + i >= 10) {
                    return false;
                }
                if (field.getSquare(iRow, iCol + i) != ' ') {
                    return false;
                }
                if (iRow - 1 >= 0 && field.getSquare(iRow - 1, iCol + i) != ' ') {
                    return false;
                }
                if (iRow + 1 < 10 && field.getSquare(iRow + 1, iCol + i) != ' ') {
                    return false;
                }
            }
            else {
                if (iRow + i >= 10) {
                    return false;
                }
                if (field.getSquare(iRow + i, iCol) != ' ') {
                    return false;
                }
                if (iCol - 1 >= 0 && field.getSquare(iRow + i, iCol - 1) != ' ') {
                    return false;
                }
                if (iCol + 1 < 10 && field.getSquare(iRow + i, iCol + 1) != ' ') {
                    return false;
                }
            }
        }
        if (bIsHoriz) {
            if (iCol - 1 >= 0 && field.getSquare(iRow, iCol - 1) != ' ') {
                return false;
            }
            if (iCol + this.m_iLength < 10 && field.getSquare(iRow, iCol + this.m_iLength) != ' ') {
                return false;
            }
        }
        else {
            if (iRow - 1 >= 0 && field.getSquare(iRow - 1, iCol) != ' ') {
                return false;
            }
            if (iRow + this.m_iLength < 10 && field.getSquare(iRow + this.m_iLength, iCol) != ' ') {
                return false;
            }
        }
        this.m_iRow = iRow;
        this.m_iCol = iCol;
        this.m_bIsHoriz = bIsHoriz;
        for (int i = 0; i < this.m_iLength; ++i) {
            if (bIsHoriz) {
                field.markBoard(iRow, iCol + i, 's');
            }
            else {
                field.markBoard(iRow + i, iCol, 's');
            }
        }
        return true;
    }
    
    public int getLength() {
        return this.m_iLength;
    }
    
    public boolean hasSquare(final int iRow, final int iCol) {
        if (this.m_bIsHoriz) {
            if (this.m_iRow != iRow) {
                return false;
            }
            final int iMaxCol = this.m_iCol + this.m_iLength;
            return iCol >= this.m_iCol && iCol < iMaxCol;
        }
        else {
            if (this.m_iCol != iCol) {
                return false;
            }
            final int iMaxRow = this.m_iRow + this.m_iLength;
            return iRow >= this.m_iRow && iRow < iMaxRow;
        }
    }
}
