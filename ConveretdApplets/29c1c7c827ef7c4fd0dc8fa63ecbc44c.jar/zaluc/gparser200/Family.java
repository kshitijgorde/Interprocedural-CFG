// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.io.IOException;
import java.util.Enumeration;
import zaluc.utils.SortableVector;
import zaluc.utils.SortableHandle;
import zaluc.utils.SortableObject;

class Family implements SortableObject
{
    int id;
    SortableHandle wifesListHandle;
    SortableHandle husbandsListHandle;
    GedcomEvent marriage;
    Person father;
    Person mother;
    SortableVector children;
    int index;
    public boolean written;
    
    public void addChild(final Person person) {
        this.children.addElement(person.familyListHandle);
    }
    
    public void write(final Record record) throws IOException {
        if (!this.written) {
            this.written = true;
            record.write(15, this.index);
            if (this.father != null) {
                record.write(12, this.father.mainListHandle.getIndex());
            }
            if (this.mother != null) {
                record.write(13, this.mother.mainListHandle.getIndex());
            }
            final Enumeration<SortableHandle> elements = this.children.elements();
            while (elements.hasMoreElements()) {
                record.write(16, ((Person)elements.nextElement().getContainer()).mainListHandle.getIndex());
            }
        }
    }
    
    public int compareTo(final SortableObject sortableObject, final int n) {
        final Family family = (Family)sortableObject;
        final GedcomEvent comparableEvent = this.getComparableEvent();
        GedcomEvent comparableEvent2;
        if (family != null) {
            comparableEvent2 = family.getComparableEvent();
        }
        else {
            comparableEvent2 = null;
        }
        int compareTo;
        if (comparableEvent != null) {
            compareTo = comparableEvent.compareTo(comparableEvent2, n);
        }
        else if (comparableEvent2 != null) {
            compareTo = 1;
        }
        else {
            compareTo = 0;
        }
        return compareTo;
    }
    
    private GedcomEvent getComparableEvent() {
        if (this.marriage != null && this.marriage.getDate() != null) {
            return this.marriage;
        }
        final Enumeration<SortableHandle> elements = this.children.elements();
        while (elements.hasMoreElements()) {
            final Person person = (Person)elements.nextElement().getContainer();
            if (person != null && person.birth != null && person.birth.getDate() != null) {
                return person.birth;
            }
        }
        return null;
    }
    
    Family() {
        this.wifesListHandle = new SortableHandle(this);
        this.husbandsListHandle = new SortableHandle(this);
        this.children = new SortableVector(6, 6, 2);
        this.written = false;
    }
}
