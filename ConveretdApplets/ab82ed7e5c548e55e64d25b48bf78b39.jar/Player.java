import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Player
{
    Board myOcean;
    Board hisOcean;
    Vector fleet;
    
    Player() {
        this.myOcean = new Board();
        this.hisOcean = new Board();
        this.fleet = new Vector();
        final Ship ship0 = new Ship(5, "Carrier");
        final Ship ship2 = new Ship(4, "Battleship");
        final Ship ship3 = new Ship(3, "Submarine");
        final Ship ship4 = new Ship(3, "Cruiser");
        final Ship ship5 = new Ship(2, "Destroyer");
        this.fleet.addElement(ship0);
        this.fleet.addElement(ship2);
        this.fleet.addElement(ship3);
        this.fleet.addElement(ship4);
        this.fleet.addElement(ship5);
    }
    
    public char checkShot(final int iRow, final int iCol) {
        if (this.myOcean.getSquare(iRow, iCol) == 's') {
            this.myOcean.markBoard(iRow, iCol, 'h');
            for (int i = 0; i < this.fleet.size(); ++i) {
                if (this.fleet.elementAt(i).hasSquare(iRow, iCol) && this.fleet.elementAt(i).hasSunk(this.myOcean)) {
                    this.fleet.removeElementAt(i);
                }
            }
            return 'h';
        }
        if (this.myOcean.getSquare(iRow, iCol) == ' ') {
            this.myOcean.markBoard(iRow, iCol, 'm');
            return 'm';
        }
        return '*';
    }
}
