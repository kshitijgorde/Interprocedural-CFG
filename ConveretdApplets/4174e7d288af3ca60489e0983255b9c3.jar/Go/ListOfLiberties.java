// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Enumeration;
import java.util.Vector;

public class ListOfLiberties
{
    private Vector hiddenList;
    
    public ListOfLiberties() {
        this.hiddenList = new Vector();
    }
    
    public ListOfLiberties(final Vector hiddenList) {
        this.hiddenList = hiddenList;
    }
    
    public boolean isNone() {
        return this.hiddenList.size() == 0;
    }
    
    public boolean contain(final Group libertyToSearch) {
        return this.hiddenList.contains(libertyToSearch);
    }
    
    public ListOfLiberties(final ListOfLiberties copyFrom) {
        this.hiddenList = new Vector();
        this.hiddenList = copyFrom.hiddenList;
    }
    
    public void add(final GobanLocation toAdd) {
        if (!this.hiddenList.contains(toAdd)) {
            this.hiddenList.addElement(toAdd);
        }
    }
    
    public int length() {
        return this.hiddenList.size();
    }
    
    public GobanLocation getElementAt(final int index) {
        return this.hiddenList.elementAt(index);
    }
    
    public GobanLocation first() {
        return this.hiddenList.firstElement();
    }
    
    public void deleteElementAt(final int index) {
        this.hiddenList.removeElementAt(index);
    }
    
    public void deleteElement(final GobanLocation libertyToDelete) {
        this.hiddenList.removeElement(libertyToDelete);
    }
    
    public Enumeration getElements() {
        return this.hiddenList.elements();
    }
    
    public void appendList(final ListOfLiberties listToAppend) {
        final Enumeration e = listToAppend.getElements();
        while (e.hasMoreElements()) {
            final GobanLocation currentLiberty = e.nextElement();
            this.add(currentLiberty);
        }
    }
    
    public void removeAllElements() {
        this.hiddenList.removeAllElements();
    }
    
    public synchronized Object clone() {
        return new ListOfLiberties((Vector)this.hiddenList.clone());
    }
    
    public String toString() {
        return this.hiddenList.toString();
    }
}
