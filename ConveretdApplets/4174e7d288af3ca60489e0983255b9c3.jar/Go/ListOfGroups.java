// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.Enumeration;
import java.util.Vector;

public class ListOfGroups
{
    private Vector hiddenList;
    
    public ListOfGroups() {
        this.hiddenList = new Vector();
    }
    
    ListOfGroups(final Vector groupsList) {
        this.hiddenList = groupsList;
    }
    
    public synchronized Object clone() {
        return new ListOfGroups((Vector)this.hiddenList.clone());
    }
    
    public boolean contain(final Group groupToSearch) {
        return this.hiddenList.contains(groupToSearch);
    }
    
    public void add(final Group toAdd) {
        if (!this.hiddenList.contains(toAdd)) {
            this.hiddenList.addElement(toAdd);
        }
    }
    
    public int length() {
        return this.hiddenList.size();
    }
    
    public Group first() {
        return this.hiddenList.firstElement();
    }
    
    public Group getElementAt(final int index) {
        return this.hiddenList.elementAt(index);
    }
    
    public void deleteElementAt(final int index) {
        this.hiddenList.removeElementAt(index);
    }
    
    public void deleteElement(final Group groupToDelete) {
        this.hiddenList.removeElement(groupToDelete);
    }
    
    public Enumeration getElements() {
        return this.hiddenList.elements();
    }
    
    public String toString() {
        return this.hiddenList.toString();
    }
}
