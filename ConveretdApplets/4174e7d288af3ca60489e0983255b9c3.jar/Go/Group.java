// 
// Decompiled by Procyon v0.5.30
// 

package Go;

public class Group
{
    int color;
    ListOfStones stones;
    public ListOfLiberties liberties;
    
    Group(final GobanLocation stone) {
        this.color = stone.state;
        (this.stones = new ListOfStones()).add(stone);
        this.liberties = new ListOfLiberties(stone.liberties);
    }
    
    Group(final int color, final ListOfStones stones, final ListOfLiberties liberties) {
        this.color = color;
        this.stones = stones;
        this.liberties = liberties;
    }
    
    public boolean hasNoLiberty() {
        return this.liberties.isNone();
    }
    
    public int nrLiberties() {
        return this.liberties.length();
    }
    
    public boolean equals(final Object anObject) {
        if (anObject instanceof Group) {
            final Group anotherGroup = (Group)anObject;
            if (this.stones.contain(anotherGroup.stones.first())) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized Object clone() {
        final ListOfStones clonedStones = (ListOfStones)this.stones.clone();
        final ListOfLiberties clonedLiberties = (ListOfLiberties)this.liberties.clone();
        return new Group(this.color, clonedStones, clonedLiberties);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("[").concat(String.valueOf(this.color))).concat(String.valueOf("; "))).concat(String.valueOf("S="))).concat(String.valueOf(this.stones))).concat(String.valueOf(";L="))).concat(String.valueOf(this.liberties))).concat(String.valueOf("]"));
    }
    
    public int nrStones() {
        return this.stones.length();
    }
}
