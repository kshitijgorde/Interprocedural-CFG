// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import java.util.Enumeration;
import Go.ListOfGroups;
import Go.Group;
import Go.GoPosition;

public class AtariGoEstimatorCho extends BasicGoEstimator
{
    public int checkPositionState(final GoPosition position) {
        if (position.getNrBlackPrisoners() == 0 && position.getNrWhitePrisoners() == 0) {
            return 100;
        }
        if (position.getNrBlackPrisoners() > 0 && position.getNrWhitePrisoners() == 0) {
            return -1;
        }
        if (position.getNrBlackPrisoners() == 0 && position.getNrWhitePrisoners() > 0) {
            return 1;
        }
        System.out.println("WRONG: checkPositionState: both black and white stones captured!");
        System.exit(1);
        return -10001;
    }
    
    protected float heuristicPositionEstimation(final GoPosition position, final int color) {
        return this.aggresiveGlobalPositionEstimation(position, color);
    }
    
    protected float balancedPositionEstimation(final GoPosition position, final int color) {
        ListOfGroups myGroups = null;
        ListOfGroups oppGroups = null;
        if (color == 1) {
            myGroups = position.getBlackGroups();
            oppGroups = position.getWhiteGroups();
        }
        else if (color == -1) {
            myGroups = position.getWhiteGroups();
            oppGroups = position.getBlackGroups();
        }
        else {
            System.out.println(String.valueOf("Unknown color: ").concat(String.valueOf(color)));
            System.exit(1);
        }
        int myMinimum = Integer.MAX_VALUE;
        final Enumeration myEnum = myGroups.getElements();
        while (myEnum.hasMoreElements()) {
            final int myCurrent = myEnum.nextElement().nrLiberties();
            if (myCurrent < myMinimum) {
                myMinimum = myCurrent;
            }
        }
        int oppMinimum = Integer.MAX_VALUE;
        final Enumeration oppEnum = oppGroups.getElements();
        while (oppEnum.hasMoreElements()) {
            final int oppCurrent = oppEnum.nextElement().nrLiberties();
            if (oppCurrent < oppMinimum) {
                oppMinimum = oppCurrent;
            }
        }
        return myMinimum - oppMinimum;
    }
    
    protected float aggresiveMinPositionEstimation(final GoPosition position, final int color) {
        ListOfGroups oppGroups = null;
        if (color == 1) {
            oppGroups = position.getWhiteGroups();
        }
        else if (color == -1) {
            oppGroups = position.getBlackGroups();
        }
        else {
            System.out.println(String.valueOf("Unknown color: ").concat(String.valueOf(color)));
            System.exit(1);
        }
        int oppMinimum = Integer.MAX_VALUE;
        final Enumeration oppEnum = oppGroups.getElements();
        while (oppEnum.hasMoreElements()) {
            final int oppCurrent = oppEnum.nextElement().nrLiberties();
            if (oppCurrent < oppMinimum) {
                oppMinimum = oppCurrent;
            }
        }
        return 1.0f / oppMinimum;
    }
    
    protected float defensiveMinPositionEstimation(final GoPosition position, final int color) {
        ListOfGroups ownGroups = null;
        if (color == 1) {
            ownGroups = position.getBlackGroups();
        }
        else if (color == -1) {
            ownGroups = position.getWhiteGroups();
        }
        else {
            System.out.println(String.valueOf("Unknown color: ").concat(String.valueOf(color)));
            System.exit(1);
        }
        int ownMinimum = Integer.MAX_VALUE;
        final Enumeration oppEnum = ownGroups.getElements();
        while (oppEnum.hasMoreElements()) {
            final int ownCurrent = oppEnum.nextElement().nrLiberties();
            if (ownCurrent < ownMinimum) {
                ownMinimum = ownCurrent;
            }
        }
        return ownMinimum;
    }
    
    protected float aggresiveGlobalPositionEstimation(final GoPosition position, final int color) {
        ListOfGroups oppGroups = null;
        if (color == 1) {
            oppGroups = position.getWhiteGroups();
        }
        else if (color == -1) {
            oppGroups = position.getBlackGroups();
        }
        else {
            System.out.println(String.valueOf("Unknown color: ").concat(String.valueOf(color)));
            System.exit(1);
        }
        int result = 0;
        final Enumeration oppEnum = oppGroups.getElements();
        while (oppEnum.hasMoreElements()) {
            result += oppEnum.nextElement().nrLiberties();
        }
        if (result == 0) {
            return 0.0f;
        }
        return 1.0f / result;
    }
}
