// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Vector;
import java.util.Enumeration;

public class GoPosition implements GoConstants, Cloneable
{
    public GobanLocation[][] goban;
    private int size;
    private ListOfGroups blackGroups;
    private ListOfGroups whiteGroups;
    private Move lastMove;
    private int nrBlackPrisoners;
    private int nrWhitePrisoners;
    
    public GoPosition(final int size) {
        this.nrBlackPrisoners = 0;
        this.nrWhitePrisoners = 0;
        this.size = size;
        this.goban = new GobanLocation[size][size];
        this.blackGroups = new ListOfGroups();
        this.whiteGroups = new ListOfGroups();
        this.setGoban(size);
        this.lastMove = new Move(-1);
    }
    
    public Object cloneCopy() throws CloneNotSupportedException {
        final ListOfStones changedLocations = new ListOfStones();
        final GoPosition clonedGoPosition = new GoPosition(this.size);
        for (int i = 0; i < this.size; ++i) {
            for (int k = 0; k < this.size; ++k) {
                final GobanLocation currentPoint = clonedGoPosition.goban[i][k];
                currentPoint.state = this.goban[i][k].state;
                if (currentPoint.state != 0) {
                    clonedGoPosition.setNewMove(currentPoint, changedLocations);
                }
            }
        }
        clonedGoPosition.nrBlackPrisoners = this.nrBlackPrisoners;
        clonedGoPosition.nrWhitePrisoners = this.nrWhitePrisoners;
        return clonedGoPosition;
    }
    
    public void copyFrom(final GoPosition source) {
        for (int i = 0; i < this.size; ++i) {
            for (int k = 0; k < this.size; ++k) {
                this.goban[i][k].state = source.goban[i][k].state;
            }
        }
        this.nrBlackPrisoners = source.nrBlackPrisoners;
        this.nrWhitePrisoners = source.nrWhitePrisoners;
    }
    
    public boolean isLegalMove(final Move lastMove) {
        final int x = lastMove.x;
        final int y = lastMove.y;
        final int color = lastMove.color;
        if (this.goban[x][y].state != 0) {
            return false;
        }
        final GobanLocation lastMoveLocation = new GobanLocation(x, y, color, this);
        if (this.countLiberties(x, y) > 0) {
            return true;
        }
        final ListOfGroups listOfNeighborFriends = new ListOfGroups();
        final ListOfGroups listOfNeighborEnemies = new ListOfGroups();
        this.visitNewStoneNeighbors(lastMoveLocation, listOfNeighborFriends, listOfNeighborEnemies);
        final Enumeration enemies = listOfNeighborEnemies.getElements();
        while (enemies.hasMoreElements()) {
            if (enemies.nextElement().liberties.length() <= 1) {
                return true;
            }
        }
        int newOwnGroupNrLiberties = 0;
        final Enumeration friends = listOfNeighborFriends.getElements();
        while (friends.hasMoreElements()) {
            newOwnGroupNrLiberties += friends.nextElement().liberties.length();
            --newOwnGroupNrLiberties;
        }
        return newOwnGroupNrLiberties > 0;
    }
    
    public boolean isSelfAtariMove(final Move lastMove) {
        final int x = lastMove.x;
        final int y = lastMove.y;
        final int color = lastMove.color;
        final GobanLocation lastMoveLocation = new GobanLocation(x, y, color, this);
        if (this.countLiberties(x, y) > 1) {
            return false;
        }
        final ListOfGroups listOfNeighborFriends = new ListOfGroups();
        final ListOfGroups listOfNeighborEnemies = new ListOfGroups();
        this.visitNewStoneNeighbors(lastMoveLocation, listOfNeighborFriends, listOfNeighborEnemies);
        final Enumeration enemies = listOfNeighborEnemies.getElements();
        while (enemies.hasMoreElements()) {
            if (enemies.nextElement().liberties.length() <= 1) {
                return false;
            }
        }
        int newOwnGroupNrLiberties = 0;
        final Enumeration friends = listOfNeighborFriends.getElements();
        while (friends.hasMoreElements()) {
            newOwnGroupNrLiberties += friends.nextElement().liberties.length();
            --newOwnGroupNrLiberties;
        }
        return newOwnGroupNrLiberties <= 1;
    }
    
    public GobanLocation newMove(final Move theNewMove) {
        this.goban[theNewMove.x][theNewMove.y].state = theNewMove.color;
        return this.goban[theNewMove.x][theNewMove.y];
    }
    
    public GobanLocation addNewStone(final int x, final int y, final int color) {
        this.goban[x][y].state = color;
        return this.goban[x][y];
    }
    
    public void setSize(final int Size) {
        this.size = Size;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setNewMove(final GobanLocation stone, final ListOfStones changedLocations) {
        final ListOfGroups listOfNeighborFriends = new ListOfGroups();
        final ListOfGroups listOfNeighborEnemies = new ListOfGroups();
        this.visitNewStoneNeighbors(stone, listOfNeighborFriends, listOfNeighborEnemies);
        this.removeNeighborsLiberty(stone, listOfNeighborFriends, listOfNeighborEnemies);
        this.updateStonesFriendGroups(stone, listOfNeighborFriends);
        this.updateStonesEnemyGroups(stone, listOfNeighborEnemies, changedLocations);
        this.setLastMove(new Move(stone.x, stone.y, stone.state));
    }
    
    public GoPosition positionAfterMove(final Move stone) {
        GoPosition newPosition = null;
        try {
            newPosition = (GoPosition)this.cloneCopy();
            newPosition.setLastMove(stone);
            if (!stone.isPass()) {
                final ListOfStones changedLocations = new ListOfStones();
                newPosition.goban[stone.x][stone.y].state = stone.color;
                newPosition.setNewMove(newPosition.goban[stone.x][stone.y], changedLocations);
            }
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(String.valueOf("Problems in GoPosition.positionAfterMove: ").concat(String.valueOf(ex)));
            System.exit(1);
        }
        return newPosition;
    }
    
    public Enumeration legalMoves() {
        final Vector moves = new Vector();
        final int currentColor = this.turn();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                final Move currentMove = new Move(i, j, currentColor);
                if (this.isLegalMove(currentMove)) {
                    moves.addElement(currentMove);
                }
            }
        }
        return moves.elements();
    }
    
    public Enumeration legalMoves_GL() {
        final Vector moves = new Vector();
        final int currentColor = this.turn();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                final Move currentMove = new Move(i, j, currentColor);
                if (this.isLegalMove(currentMove)) {
                    moves.addElement(this.goban[i][j]);
                }
            }
        }
        return moves.elements();
    }
    
    public ListOfGroups getBlackGroups() {
        return this.blackGroups;
    }
    
    public ListOfGroups getWhiteGroups() {
        return this.whiteGroups;
    }
    
    public void setBlackGroups(final ListOfGroups newBlackGroups) {
        this.blackGroups = newBlackGroups;
    }
    
    public void setWhiteGroups(final ListOfGroups newWhiteGroups) {
        this.whiteGroups = newWhiteGroups;
    }
    
    public boolean isEmpty(final int x, final int y) {
        return this.goban[x][y].state == 0;
    }
    
    public String toString() {
        final StringBuffer answer = new StringBuffer();
        for (int i = 0; i < this.size; ++i) {
            answer.append("\n");
            for (int k = 0; k < this.size; ++k) {
                answer.append(String.valueOf(" ").concat(String.valueOf(this.goban[k][i].state)));
            }
        }
        return new String(answer);
    }
    
    public int countLiberties(final int x, final int y) {
        int count = 0;
        if (x > 0 && this.goban[x - 1][y].state == 0) {
            ++count;
        }
        if (x < this.size - 1 && this.goban[x + 1][y].state == 0) {
            ++count;
        }
        if (y > 0 && this.goban[x][y - 1].state == 0) {
            ++count;
        }
        if (y < this.size - 1 && this.goban[x][y + 1].state == 0) {
            ++count;
        }
        return count;
    }
    
    public Move getLastMove() {
        return this.lastMove;
    }
    
    public void setLastMove(final Move move) {
        this.lastMove.x = move.x;
        this.lastMove.y = move.y;
        this.lastMove.color = move.color;
    }
    
    public int turn() {
        if (this.lastMove.color == 1) {
            return -1;
        }
        return 1;
    }
    
    public int getNrBlackPrisoners() {
        return this.nrBlackPrisoners;
    }
    
    public int getNrWhitePrisoners() {
        return this.nrWhitePrisoners;
    }
    
    public boolean sameAs(final GoPosition somePosition) {
        for (int i = 0; i < this.size; ++i) {
            for (int k = 0; k < this.size; ++k) {
                if (this.goban[i][k].state != somePosition.goban[i][k].state) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void setGoban(final int Size) {
        this.size = Size;
        for (int i = 0; i < this.size; ++i) {
            for (int k = 0; k < this.size; ++k) {
                this.goban[i][k] = new GobanLocation(i, k, 0, this);
            }
        }
        for (int j = 0; j < this.size; ++j) {
            for (int l = 0; l < this.size; ++l) {
                this.goban[j][l].computeLiberties();
            }
        }
    }
    
    private void processNeighbor(final GobanLocation stone, final int currentX, final int currentY, final ListOfGroups listOfNeighborFriends, final ListOfGroups listOfNeighborEnemies) {
        if (this.goban[currentX][currentY].state == 0) {
            stone.liberties.add(this.goban[currentX][currentY]);
            this.goban[currentX][currentY].liberties.deleteElement(stone);
            return;
        }
        if (this.goban[currentX][currentY].state == stone.state) {
            listOfNeighborFriends.add(this.goban[currentX][currentY].getGroup());
        }
        else {
            listOfNeighborEnemies.add(this.goban[currentX][currentY].getGroup());
        }
    }
    
    private void removeNeighborsLiberty(final GobanLocation stone, final ListOfGroups listOfNeighborFriends, final ListOfGroups listOfNeighborEnemies) {
        Enumeration e = listOfNeighborFriends.getElements();
        while (e.hasMoreElements()) {
            final Group currentGroup = e.nextElement();
            currentGroup.liberties.deleteElement(stone);
        }
        e = listOfNeighborEnemies.getElements();
        while (e.hasMoreElements()) {
            final Group currentGroup = e.nextElement();
            currentGroup.liberties.deleteElement(stone);
        }
    }
    
    private void updateStonesFriendGroups(final GobanLocation stone, final ListOfGroups listOfNeighborFriends) {
        if (listOfNeighborFriends.length() == 0) {
            this.newBornGroup(stone);
        }
        else if (listOfNeighborFriends.length() == 1) {
            this.addStoneToGroup(stone, listOfNeighborFriends.first());
        }
        else {
            this.connectMoreGroups(stone, listOfNeighborFriends);
        }
    }
    
    private void updateStonesEnemyGroups(final GobanLocation stone, final ListOfGroups listOfNeighborEnemies, final ListOfStones changedLocations) {
        final Enumeration e = listOfNeighborEnemies.getElements();
        while (e.hasMoreElements()) {
            final Group currentGroup = e.nextElement();
            if (currentGroup.hasNoLiberty()) {
                if (currentGroup.color == 1) {
                    this.nrBlackPrisoners += currentGroup.nrStones();
                }
                else {
                    this.nrWhitePrisoners += currentGroup.nrStones();
                }
                changedLocations.appendList(currentGroup.stones);
                final Enumeration stonesList = currentGroup.stones.getElements();
                while (stonesList.hasMoreElements()) {
                    final GobanLocation currentStone = stonesList.nextElement();
                    final Enumeration enemyList = currentStone.getNeighborEnemies().getElements();
                    while (enemyList.hasMoreElements()) {
                        final Group currentEnemy = enemyList.nextElement();
                        currentEnemy.liberties.add(currentStone);
                    }
                    currentStone.state = 0;
                    if (currentGroup.color == 1) {
                        this.blackGroups.deleteElement(currentGroup);
                    }
                    else {
                        if (currentGroup.color != -1) {
                            continue;
                        }
                        this.whiteGroups.deleteElement(currentGroup);
                    }
                }
            }
        }
    }
    
    public void visitNewStoneNeighbors(final GobanLocation stone, final ListOfGroups listOfNeighborFriends, final ListOfGroups listOfNeighborEnemies) {
        if (stone.x > 0) {
            final int currentX = stone.x - 1;
            final int currentY = stone.y;
            this.processNeighbor(stone, currentX, currentY, listOfNeighborFriends, listOfNeighborEnemies);
        }
        if (stone.y > 0) {
            final int currentX = stone.x;
            final int currentY = stone.y - 1;
            this.processNeighbor(stone, currentX, currentY, listOfNeighborFriends, listOfNeighborEnemies);
        }
        if (stone.x < this.size - 1) {
            final int currentX = stone.x + 1;
            final int currentY = stone.y;
            this.processNeighbor(stone, currentX, currentY, listOfNeighborFriends, listOfNeighborEnemies);
        }
        if (stone.y < this.size - 1) {
            final int currentX = stone.x;
            final int currentY = stone.y + 1;
            this.processNeighbor(stone, currentX, currentY, listOfNeighborFriends, listOfNeighborEnemies);
        }
    }
    
    private void newBornGroup(final GobanLocation stone) {
        final Group newGroup = new Group(stone);
        stone.setGroup(newGroup);
        if (newGroup.color == 1) {
            this.blackGroups.add(newGroup);
        }
        else {
            this.whiteGroups.add(newGroup);
        }
    }
    
    private void addStoneToGroup(final GobanLocation stone, final Group theGroup) {
        theGroup.stones.add(stone);
        stone.setGroup(theGroup);
        theGroup.liberties.appendList(stone.liberties);
    }
    
    private void connectMoreGroups(final GobanLocation stone, final ListOfGroups listOfNeighborFriends) {
        final Group theGroup = listOfNeighborFriends.first();
        final Enumeration e = listOfNeighborFriends.getElements();
        e.nextElement();
        while (e.hasMoreElements()) {
            final Group oneMoreGroup = e.nextElement();
            oneMoreGroup.stones.setGroup(theGroup);
            theGroup.liberties.appendList(oneMoreGroup.liberties);
            theGroup.stones.appendList(oneMoreGroup.stones);
            if (stone.state == 1) {
                this.blackGroups.deleteElement(oneMoreGroup);
            }
            else {
                this.whiteGroups.deleteElement(oneMoreGroup);
            }
        }
        this.addStoneToGroup(stone, theGroup);
    }
}
