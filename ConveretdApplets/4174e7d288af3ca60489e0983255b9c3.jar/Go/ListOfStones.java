// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Enumeration;
import java.util.Vector;

public class ListOfStones
{
    private Vector hiddenList;
    
    public ListOfStones() {
        this.hiddenList = new Vector();
    }
    
    public ListOfStones(final Vector hiddenList) {
        this.hiddenList = hiddenList;
    }
    
    public boolean contain(final GobanLocation stoneToSearch) {
        return this.hiddenList.contains(stoneToSearch);
    }
    
    public void add(final GobanLocation toAdd) {
        this.hiddenList.addElement(toAdd);
    }
    
    public int length() {
        return this.hiddenList.size();
    }
    
    public GobanLocation first() {
        return this.hiddenList.firstElement();
    }
    
    public GobanLocation getElementAt(final int index) {
        return this.hiddenList.elementAt(index);
    }
    
    public void deleteElementAt(final int index) {
        this.hiddenList.removeElementAt(index);
    }
    
    public void deleteElement(final GobanLocation stoneToDelete) {
        this.hiddenList.removeElement(stoneToDelete);
    }
    
    public void deleteAllElements() {
        this.hiddenList.removeAllElements();
    }
    
    public Enumeration getElements() {
        return this.hiddenList.elements();
    }
    
    public void appendList(final ListOfStones listToAppend) {
        final Enumeration e = listToAppend.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation currentStone = e.nextElement();
            this.add(currentStone);
        }
    }
    
    public void setGroup(final Group groupToSetTo) {
        final Enumeration e = this.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation currentStone = e.nextElement();
            currentStone.setGroup(groupToSetTo);
        }
    }
    
    public synchronized Object clone() {
        return new ListOfStones((Vector)this.hiddenList.clone());
    }
    
    public String toString() {
        return this.hiddenList.toString();
    }
}
