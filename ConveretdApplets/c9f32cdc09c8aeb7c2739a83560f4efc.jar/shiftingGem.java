// 
// Decompiled by Procyon v0.5.30
// 

public class shiftingGem
{
    int showX;
    int showY;
    int destinationX;
    int destinationY;
    int gemNumber;
    boolean inPlace;
    int ss;
    
    public shiftingGem(final int n, final int n2, final int destinationX, final int destinationY, final int gemNumber) {
        this.ss = 5;
        this.showX = n * 30;
        this.showY = n2 * 30;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.gemNumber = gemNumber;
        if (gemNumber == -1) {
            System.out.println("HEY!!!" + n + "," + n2 + " - " + destinationX + "," + destinationY);
        }
    }
    
    public shiftingGem(final int n, final int n2, final int n3, final int n4, final int n5, final int ss) {
        this(n, n2, n3, n4, n5);
        this.ss = ss;
    }
    
    public void shiftPiece() {
        if (this.showY > this.destinationY * 30) {
            this.showY -= this.ss;
        }
        if (this.showY < this.destinationY * 30) {
            this.showY += this.ss;
        }
        if (this.showX > this.destinationX * 30) {
            this.showX -= this.ss;
        }
        if (this.showX < this.destinationX * 30) {
            this.showX += this.ss;
        }
        if (this.showX == this.destinationX * 30 && this.showY == this.destinationY * 30) {
            this.inPlace = true;
        }
    }
}
