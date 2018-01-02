// 
// Decompiled by Procyon v0.5.30
// 

class OthelloPlayer
{
    Othello parent;
    int i;
    int j;
    
    OthelloPlayer(final Othello parent) {
        this.parent = parent;
    }
    
    public void decide() {
        if (this.parent.checkAll(this.parent.turn)) {
            do {
                this.i = (int)(Math.random() * 8.0);
                this.j = (int)(Math.random() * 8.0);
            } while (!this.parent.checkStone(this.i, this.j, this.parent.turn));
            this.parent.turnStone(this.i, this.j, this.parent.turn);
        }
        this.parent.turn = -this.parent.turn;
    }
}
