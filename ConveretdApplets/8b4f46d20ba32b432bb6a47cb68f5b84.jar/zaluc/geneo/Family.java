// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.util.Vector;

class Family
{
    Globals globals;
    PeopleList plist;
    int index;
    boolean isComplete;
    int father;
    int mother;
    boolean hasChildren;
    Vector children;
    
    public Family(final Globals globals, final PeopleList plist, final int index) {
        this.globals = globals;
        this.plist = plist;
        this.index = index;
        this.isComplete = false;
        final int n = -1;
        this.mother = n;
        this.father = n;
        this.hasChildren = false;
        this.children = new Vector(6, 6);
    }
    
    public void complete() {
        this.isComplete = true;
    }
    
    public void addChild(final int n) {
        this.children.addElement(new Integer(n));
        this.hasChildren = true;
    }
    
    public Person getChild(final int n) {
        Person person = null;
        if (this.children != null) {
            try {
                final Integer n2 = this.children.elementAt(n);
                if (n2 != null) {
                    person = this.plist.getPerson(n2);
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        return person;
    }
}
