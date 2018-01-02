import java.awt.Point;
import java.util.Random;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class Computer extends Player
{
    private int initialRow;
    private int initialCol;
    private int lastRow;
    private int lastCol;
    private int thisRow;
    private int thisCol;
    private int nextRow;
    private int nextCol;
    private boolean attacking;
    
    public Computer() {
        this.initialRow = -1;
        this.initialCol = -1;
        this.lastRow = -1;
        this.lastCol = -1;
        this.thisRow = -1;
        this.thisCol = -1;
        this.nextRow = -1;
        this.nextCol = -1;
    }
    
    public void searchAround() {
        final Random randNum = new Random(new Date().getTime());
        do {
            final int temp = (int)(randNum.nextDouble() * 4.0);
            switch (temp) {
                default: {
                    continue;
                }
                case 0: {
                    this.nextRow = this.initialRow - 1;
                    this.nextCol = this.initialCol;
                    if (this.nextRow >= 0) {
                        continue;
                    }
                    this.nextRow = this.initialRow;
                    continue;
                }
                case 1: {
                    this.nextRow = this.initialRow + 1;
                    this.nextCol = this.initialCol;
                    if (this.nextRow < 10) {
                        continue;
                    }
                    this.nextRow = this.initialRow;
                    continue;
                }
                case 2: {
                    this.nextCol = this.initialCol - 1;
                    this.nextRow = this.initialRow;
                    if (this.nextCol >= 0) {
                        continue;
                    }
                    this.nextCol = this.initialCol;
                    continue;
                }
                case 3: {
                    this.nextCol = this.initialCol + 1;
                    this.nextRow = this.initialRow;
                    if (this.nextCol < 10) {
                        continue;
                    }
                    this.nextCol = this.initialCol;
                    continue;
                }
            }
        } while (super.hisOcean.getSquare(this.nextRow, this.nextCol) != ' ');
    }
    
    public Point getShot(final Point topLeft, final int sizeSquare) {
        final Random randNum = new Random(new Date().getTime());
        this.lastRow = this.thisRow;
        this.lastCol = this.thisCol;
        this.thisRow = this.nextRow;
        this.thisCol = this.nextCol;
        if (this.thisRow == -1 && this.thisCol == -1) {
            do {
                this.thisRow = (int)(randNum.nextDouble() * 10.0);
                this.thisCol = (int)(randNum.nextDouble() * 10.0);
            } while (super.hisOcean.getSquare(this.thisRow, this.thisCol) != ' ');
        }
        return new Point(this.thisRow, this.thisCol);
    }
    
    public void getResponse(final char response, final boolean shipSunk) {
        final Random randNum = new Random(new Date().getTime());
        if (shipSunk) {
            this.initialRow = -1;
            this.initialCol = -1;
            final int around = 0;
            do {
                this.nextRow = (int)(randNum.nextDouble() * 10.0);
                this.nextCol = (int)(randNum.nextDouble() * 10.0);
            } while (super.hisOcean.getSquare(this.nextRow, this.nextCol) != ' ');
            this.attacking = false;
            return;
        }
        if (this.attacking) {
            if (response == 'h') {
                this.nextRow = this.thisRow + (this.thisRow - this.lastRow);
                this.nextCol = this.thisCol + (this.thisCol - this.lastCol);
                if (this.nextRow < 0 || this.nextRow >= 10 || this.nextCol < 0 || this.nextCol >= 10) {
                    this.nextRow = this.initialRow + (this.lastRow - this.thisRow);
                    this.nextCol = this.initialCol + (this.lastCol - this.thisCol);
                    this.thisRow = this.initialRow;
                    this.thisCol = this.initialCol;
                }
                else if (super.hisOcean.getSquare(this.nextRow, this.nextCol) != ' ') {
                    this.nextRow = this.initialRow + (this.lastRow - this.thisRow);
                    this.nextCol = this.initialCol + (this.lastCol - this.thisCol);
                    this.thisRow = this.initialRow;
                    this.thisCol = this.initialCol;
                }
            }
            else if (this.lastRow == this.initialRow && this.lastCol == this.initialCol) {
                this.thisRow = this.initialRow;
                this.thisCol = this.initialCol;
                this.searchAround();
            }
            else {
                this.nextRow = this.initialRow + (this.lastRow - this.thisRow);
                this.nextCol = this.initialCol + (this.lastCol - this.thisCol);
                this.thisRow = this.initialRow;
                this.thisCol = this.initialCol;
            }
        }
        else if (response == 'h') {
            this.initialRow = this.thisRow;
            this.initialCol = this.thisCol;
            this.searchAround();
            this.attacking = true;
        }
        else {
            do {
                this.nextRow = (int)(randNum.nextDouble() * 10.0);
                this.nextCol = (int)(randNum.nextDouble() * 10.0);
            } while (super.hisOcean.getSquare(this.nextRow, this.nextCol) != ' ');
        }
    }
    
    public void placeShip(final Point topLeft, final int sizeSquare, final Ship theShip) {
        final Random randNum = new Random(new Date().getTime());
        int iRow = -1;
        int iCol = -1;
        boolean bIsHoriz = false;
        do {
            iRow = (int)(randNum.nextDouble() * 10.0);
            iCol = (int)(randNum.nextDouble() * 10.0);
            bIsHoriz = ((int)(randNum.nextDouble() * 2.0) == 1);
        } while (!theShip.placeShip(super.myOcean, iRow, iCol, bIsHoriz));
    }
}
