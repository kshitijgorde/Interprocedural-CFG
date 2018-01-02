// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import Go.GobanLocation;
import Go.ListOfLiberties;
import java.util.Enumeration;
import Go.ListOfGroups;
import Go.Group;
import Go.Move;
import Go.GoPosition;

public class BasicAtariGoStrategy
{
    public static Move tryBasicMove(final GoPosition goPosition) {
        Move theMove = null;
        if ((theMove = tryCapture(goPosition)) != null) {
            return theMove;
        }
        if ((theMove = tryDefend(goPosition)) != null) {
            return theMove;
        }
        return theMove;
    }
    
    public static Move tryCapture(final GoPosition goPosition) {
        ListOfGroups listOfNeighborEnemies;
        if (goPosition.turn() == 1) {
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
                return new Move(location.x, location.y, goPosition.turn());
            }
        }
        return null;
    }
    
    public static Move tryDefend(final GoPosition goPosition) {
        ListOfGroups listOfNeighborFriends = new ListOfGroups();
        if (goPosition.turn() == -1) {
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
                final Move theMove = new Move(location.x, location.y, goPosition.turn());
                if (goPosition.isLegalMove(theMove)) {
                    return theMove;
                }
                continue;
            }
        }
        return null;
    }
    
    public static Enumeration contactMoves(final GoPosition goPosition) {
        final ListOfLiberties result = new ListOfLiberties();
        final Enumeration whiteGroups = goPosition.getWhiteGroups().getElements();
        final Enumeration blackGroups = goPosition.getBlackGroups().getElements();
        while (blackGroups.hasMoreElements()) {
            result.appendList(blackGroups.nextElement().liberties);
        }
        while (whiteGroups.hasMoreElements()) {
            result.appendList(whiteGroups.nextElement().liberties);
        }
        return filterSelfAtari(goPosition, filterLegalMoves(goPosition, result)).getElements();
    }
    
    public static Enumeration extremeAttackDefenseMoves(final GoPosition goPosition) {
        final ListOfLiberties result = new ListOfLiberties();
        final Move bestMove = null;
        Group currentWeakest = null;
        Group currentGroup = null;
        int currentNrLiberties = 1000;
        final int bestNrliberties = -1;
        ListOfGroups listOfEnemies;
        ListOfGroups listOfFriends;
        if (goPosition.turn() == 1) {
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
        if (currentWeakest != null) {
            result.appendList(currentWeakest.liberties);
        }
        currentWeakest = null;
        while (friends.hasMoreElements()) {
            currentGroup = friends.nextElement();
            final ListOfLiberties libertiesList = currentGroup.liberties;
            if (libertiesList.length() < currentNrLiberties) {
                currentNrLiberties = libertiesList.length();
                currentWeakest = currentGroup;
            }
        }
        if (currentWeakest != null) {
            result.appendList(currentWeakest.liberties);
        }
        return filterSelfAtari(goPosition, filterLegalMoves(goPosition, result)).getElements();
    }
    
    private static ListOfLiberties filterLegalMoves(final GoPosition goPosition, final ListOfLiberties list) {
        final ListOfLiberties moves = new ListOfLiberties();
        final int nextColor = goPosition.turn();
        final Enumeration e = list.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation location = e.nextElement();
            final Move currentMove = new Move(location.x, location.y, nextColor);
            if (goPosition.isLegalMove(currentMove)) {
                moves.add(new GobanLocation(location));
            }
        }
        return moves;
    }
    
    private static ListOfLiberties filterSelfAtari(final GoPosition goPosition, final ListOfLiberties list) {
        final ListOfLiberties moves = new ListOfLiberties();
        final int nextColor = goPosition.turn();
        final Enumeration e = list.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation location = e.nextElement();
            final Move currentMove = new Move(location.x, location.y, nextColor);
            if (!goPosition.isSelfAtariMove(currentMove)) {
                moves.add(new GobanLocation(location));
            }
        }
        return moves;
    }
}
