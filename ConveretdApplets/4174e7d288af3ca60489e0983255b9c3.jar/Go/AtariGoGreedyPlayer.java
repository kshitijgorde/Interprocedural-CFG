// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import roulette.Roulette;
import java.util.Vector;
import java.util.Enumeration;

public class AtariGoGreedyPlayer extends Player
{
    private final float constantMoveValue = 1.0f;
    
    public AtariGoGreedyPlayer(final int color) {
        super(color);
    }
    
    public void initPlayer() {
    }
    
    public void positionChanged(final GoGameEvent goGameEvent) {
    }
    
    public void gameOver(final GoGameEvent goGameEvent) {
    }
    
    protected Move move(final GoPosition goPosition) {
        Move theMove = null;
        if ((theMove = this.tryCapture(goPosition)) != null) {
            return theMove;
        }
        if ((theMove = this.tryDefend(goPosition)) != null) {
            return theMove;
        }
        if ((theMove = this.attackWeakestGroup(goPosition)) != null) {
            return theMove;
        }
        return this.makeRandomMove(goPosition);
    }
    
    protected Move tryCapture(final GoPosition goPosition) {
        ListOfGroups listOfNeighborEnemies;
        if (super.color == 1) {
            listOfNeighborEnemies = goPosition.getWhiteGroups();
        }
        else {
            listOfNeighborEnemies = goPosition.getBlackGroups();
        }
        final Enumeration enemies = listOfNeighborEnemies.getElements();
        while (enemies.hasMoreElements()) {
            final ListOfLiberties libertiesList = enemies.nextElement().liberties;
            if (libertiesList.length() == 1) {
                final GobanLocation location = libertiesList.getElementAt(0);
                return new Move(location.x, location.y, super.color);
            }
        }
        return null;
    }
    
    protected Move tryDefend(final GoPosition goPosition) {
        ListOfGroups listOfNeighborFriends = new ListOfGroups();
        if (super.color == -1) {
            listOfNeighborFriends = goPosition.getWhiteGroups();
        }
        else {
            listOfNeighborFriends = goPosition.getBlackGroups();
        }
        final Enumeration friends = listOfNeighborFriends.getElements();
        while (friends.hasMoreElements()) {
            final ListOfLiberties libertiesList = friends.nextElement().liberties;
            if (libertiesList.length() == 1) {
                final GobanLocation location = libertiesList.getElementAt(0);
                final Move theMove = new Move(location.x, location.y, super.color);
                if (goPosition.isLegalMove(theMove)) {
                    return theMove;
                }
                continue;
            }
        }
        return null;
    }
    
    protected Move attackWeakestGroup(final GoPosition goPosition) {
        Move bestMove = null;
        Group currentWeakest = null;
        Group currentGroup = null;
        int currentNrLiberties = 1000;
        int bestNrliberties = -1;
        ListOfGroups listOfEnemies;
        ListOfGroups listOfFriends;
        if (super.color == 1) {
            listOfEnemies = goPosition.getWhiteGroups();
            listOfFriends = goPosition.getBlackGroups();
        }
        else {
            listOfEnemies = goPosition.getBlackGroups();
            listOfFriends = goPosition.getWhiteGroups();
        }
        final Enumeration enemies = listOfEnemies.getElements();
        final Enumeration friends = listOfFriends.getElements();
        while (enemies.hasMoreElements()) {
            currentGroup = enemies.nextElement();
            final ListOfLiberties libertiesList = currentGroup.liberties;
            if (libertiesList.length() < currentNrLiberties) {
                currentNrLiberties = libertiesList.length();
                currentWeakest = currentGroup;
            }
        }
        while (friends.hasMoreElements()) {
            currentGroup = friends.nextElement();
            final ListOfLiberties libertiesList = currentGroup.liberties;
            if (libertiesList.length() < currentNrLiberties) {
                currentNrLiberties = libertiesList.length();
                currentWeakest = currentGroup;
            }
        }
        if (currentWeakest == null) {
            return null;
        }
        final ListOfLiberties libertiesList = currentWeakest.liberties;
        for (int i = 0; i < libertiesList.length(); ++i) {
            final GobanLocation location = libertiesList.getElementAt(i);
            final Move theMove = new Move(location.x, location.y, super.color);
            if (goPosition.isLegalMove(theMove) && (currentNrLiberties = this.resultedNrLiberties(goPosition, theMove)) > bestNrliberties) {
                bestMove = new Move(theMove.x, theMove.y, super.color);
                bestNrliberties = currentNrLiberties;
            }
        }
        return bestMove;
    }
    
    private Move makeRandomMove(final GoPosition goPosition) {
        final Vector possibleMoveList = new Vector();
        final Vector possibleMoveValueList = new Vector();
        Move currentMove = null;
        for (int gobanSize = goPosition.getSize(), i = 0; i < gobanSize; ++i) {
            for (int k = 0; k < gobanSize; ++k) {
                if (goPosition.goban[i][k].state == 0) {
                    currentMove = new Move(super.color);
                    currentMove.x = i;
                    currentMove.y = k;
                    if (goPosition.isLegalMove(currentMove)) {
                        goPosition.goban[i][k].state = super.color;
                        final float currentMoveValue = 1.0f;
                        possibleMoveList.addElement(currentMove);
                        possibleMoveValueList.addElement(new Double(currentMoveValue));
                        goPosition.goban[i][k].state = 0;
                    }
                }
            }
        }
        if (possibleMoveList.size() > 0) {
            final int choosenMoveIndex = Roulette.rouletteChooseOne(possibleMoveValueList);
            return possibleMoveList.elementAt(choosenMoveIndex);
        }
        return new Move(-100, -100, super.color);
    }
    
    private int resultedNrLiberties(final GoPosition goPosition, final Move lastMove) {
        final int x = lastMove.x;
        final int y = lastMove.y;
        final int oldColor = goPosition.goban[x][y].state;
        goPosition.goban[x][y].state = lastMove.color;
        int newOwnGroupNrLiberties;
        try {
            final GobanLocation lastMoveLocation = goPosition.goban[x][y];
            final ListOfGroups listOfNeighborFriends = new ListOfGroups();
            final ListOfGroups listOfNeighborEnemies = new ListOfGroups();
            goPosition.visitNewStoneNeighbors(lastMoveLocation, listOfNeighborFriends, listOfNeighborEnemies);
            newOwnGroupNrLiberties = super.game.goPosition.countLiberties(x, y);
            final Enumeration friends = listOfNeighborFriends.getElements();
            while (friends.hasMoreElements()) {
                newOwnGroupNrLiberties += friends.nextElement().liberties.length();
                --newOwnGroupNrLiberties;
            }
        }
        finally {
            goPosition.goban[x][y].state = oldColor;
        }
        return newOwnGroupNrLiberties;
    }
}
