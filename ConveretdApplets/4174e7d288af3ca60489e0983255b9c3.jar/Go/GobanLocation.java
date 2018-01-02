// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Enumeration;

public class GobanLocation implements GoConstants
{
    private GoPosition thePosition;
    private Group group;
    public int x;
    public int y;
    public int state;
    public ListOfLiberties liberties;
    
    public GobanLocation(final int State) {
        this.state = State;
        this.liberties = new ListOfLiberties();
    }
    
    public GobanLocation(final GobanLocation location) {
        this.x = location.x;
        this.y = location.y;
        this.state = location.state;
        this.thePosition = location.thePosition;
        this.liberties = new ListOfLiberties();
    }
    
    public GobanLocation(final int X, final int Y, final int State, final GoPosition currentPosition) {
        this.x = X;
        this.y = Y;
        this.state = State;
        this.thePosition = currentPosition;
        this.liberties = new ListOfLiberties();
    }
    
    public GobanLocation(final Move theMove, final GoPosition currentPosition) {
        this.x = theMove.x;
        this.y = theMove.y;
        this.state = theMove.color;
        this.thePosition = currentPosition;
        this.liberties = new ListOfLiberties();
    }
    
    public void setGroup(final Group group) {
        this.group = group;
    }
    
    public Group getGroup() {
        return this.group;
    }
    
    public boolean equals(final Object anObject) {
        if (anObject instanceof GobanLocation) {
            final GobanLocation currentLocation = (GobanLocation)anObject;
            if (this.x == currentLocation.x && this.y == currentLocation.y) {
                return true;
            }
        }
        return false;
    }
    
    public void computeLiberties() {
        final ListOfLiberties listOfNeighbors = this.getNeighborsList();
        final Enumeration e = listOfNeighbors.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation currentNeighbor = e.nextElement();
            this.processLiberty(currentNeighbor.x, currentNeighbor.y);
        }
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("(").concat(String.valueOf(this.x))).concat(String.valueOf(","))).concat(String.valueOf(this.y))).concat(String.valueOf(";"))).concat(String.valueOf(this.state))).concat(String.valueOf(")"));
    }
    
    public ListOfGroups getNeighborEnemies() {
        final ListOfLiberties listOfNeighbors = this.getNeighborsList();
        final ListOfGroups listOfEnemyGroups = new ListOfGroups();
        final Enumeration e = listOfNeighbors.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation currentNeighbor = e.nextElement();
            if (currentNeighbor.state != 0 || currentNeighbor.state != this.state) {
                listOfEnemyGroups.add(currentNeighbor.getGroup());
            }
        }
        return listOfEnemyGroups;
    }
    
    private ListOfLiberties getNeighborsList() {
        final ListOfLiberties listOfNeighbors = new ListOfLiberties();
        if (this.x > 0) {
            listOfNeighbors.add(this.thePosition.goban[this.x - 1][this.y]);
        }
        if (this.y > 0) {
            listOfNeighbors.add(this.thePosition.goban[this.x][this.y - 1]);
        }
        if (this.x < this.thePosition.getSize() - 1) {
            listOfNeighbors.add(this.thePosition.goban[this.x + 1][this.y]);
        }
        if (this.y < this.thePosition.getSize() - 1) {
            listOfNeighbors.add(this.thePosition.goban[this.x][this.y + 1]);
        }
        return listOfNeighbors;
    }
    
    private void processLiberty(final int currentX, final int currentY) {
        final GobanLocation currentNeighbor = this.thePosition.goban[currentX][currentY];
        if (currentNeighbor.state == 0) {
            this.liberties.add(currentNeighbor);
        }
    }
}
