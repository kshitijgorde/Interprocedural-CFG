// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.io.IOException;
import java.util.Enumeration;
import zaluc.utils.SortableHandle;
import java.util.Vector;
import zaluc.utils.SortableVector;

class PeopleList
{
    private SortableVector peopleVect;
    private Vector familyVect;
    private int familyCount;
    
    public PeopleList() {
        this.peopleVect = new SortableVector(100, 100, 1);
        this.familyVect = new Vector(100, 100);
    }
    
    public void setPerson(final Person person, final int n) {
        if (n >= this.peopleVect.size()) {
            this.peopleVect.setSize(n + 1);
        }
        this.peopleVect.setElementAt(person.mainListHandle, n);
    }
    
    public void setFamily(final Family family) {
        this.familyVect.addElement(family);
        family.index = this.familyCount++;
    }
    
    public Person getPerson(final int n) {
        if (n < this.peopleVect.size() && this.peopleVect.elementAt(n) != null) {
            return (Person)this.peopleVect.elementAt(n).getContainer();
        }
        return null;
    }
    
    public Family getFamily(final int n) {
        if (n < this.familyVect.size()) {
            return this.familyVect.elementAt(n);
        }
        return null;
    }
    
    public void sort() {
        this.peopleVect.sort();
        for (int i = 0; i < this.familyCount; ++i) {
            final Family family;
            if ((family = this.getFamily(i)) != null) {
                family.children.sort();
            }
        }
        for (int j = 0; j < this.peopleVect.size(); ++j) {
            final Person person;
            if ((person = this.getPerson(j)) != null) {
                person.families.sort();
            }
        }
    }
    
    public int getCount() {
        return this.peopleVect.size();
    }
    
    public void writeFrom(final Person person, final Record record) throws IOException {
        final int size = this.peopleVect.size();
        for (int i = 0; i < size; ++i) {
            final Person person2;
            if ((person2 = this.getPerson(i)) != null) {
                person2.written = false;
            }
        }
        for (int j = 0; j < this.familyCount; ++j) {
            final Family family;
            if ((family = this.getFamily(j)) != null) {
                family.written = false;
            }
        }
        record.write(1, size);
        record.write(2, this.familyCount);
        Person append = person;
        Person next = person;
        Person append2 = person;
        Person next2 = person;
        next.next = null;
        next2.next = null;
        while (next != null || next2 != null) {
            if (next != null) {
                if (!next.written) {
                    append = append.append(next.father).append(next.mother);
                    next.write(record);
                }
                next = next.next;
            }
            if (next2 != null) {
                if (!next2.written) {
                    final Enumeration<Object> elements = next2.families.elements();
                    while (elements.hasMoreElements()) {
                        final Family family2 = elements.nextElement();
                        family2.write(record);
                        if (family2.mother != null) {
                            family2.mother.write(record);
                        }
                        if (family2.father != null) {
                            family2.father.write(record);
                        }
                        final Enumeration<Object> elements2 = family2.children.elements();
                        while (elements2.hasMoreElements()) {
                            append2 = append2.append(elements2.nextElement());
                        }
                    }
                    next2.write(record);
                }
                next2 = next2.next;
            }
        }
        for (int k = 0; k < size; ++k) {
            final Person person3;
            if ((person3 = this.getPerson(k)) != null) {
                person3.write(record);
            }
        }
        for (int l = 0; l < this.familyCount; ++l) {
            final Family family3;
            if ((family3 = this.getFamily(l)) != null) {
                family3.write(record);
            }
        }
    }
}
