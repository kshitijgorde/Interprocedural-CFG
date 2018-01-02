// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.util.Vector;

public class ScoreManager
{
    public int score;
    private Vector objectsToNotify;
    
    public ScoreManager() {
        this.objectsToNotify = new Vector();
    }
    
    public void addToScore(final int n) {
        this.score += n;
        this.checkForBonus(n);
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    public void subtractFromScore(final int n) {
        this.score -= n;
    }
    
    public void registerForBonusNotification(final Object o, final int n) {
        final Vector<Integer> vector = new Vector<Integer>(3);
        vector.addElement((Integer)o);
        vector.addElement(new Integer(n));
        vector.addElement(new Integer(0));
        this.objectsToNotify.addElement(vector);
    }
    
    private void checkForBonus(final int n) {
        for (int i = 0; i < this.objectsToNotify.size(); ++i) {
            final Vector<Integer> vector = this.objectsToNotify.elementAt(i);
            vector.setElementAt(new Integer(vector.elementAt(2) + n), 2);
            final Integer n2 = vector.elementAt(2);
            final Integer n3 = vector.elementAt(1);
            if (n2 >= n3) {
                final BonusHandler bonusHandler = (BonusHandler)vector.elementAt(0);
                vector.setElementAt(new Integer(n2 - n3), 2);
                bonusHandler.didAchieveBonus();
            }
        }
    }
}
