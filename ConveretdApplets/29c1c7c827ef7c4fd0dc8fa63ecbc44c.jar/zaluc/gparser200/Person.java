// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.text.DecimalFormat;
import java.io.IOException;
import java.util.Enumeration;
import zaluc.utils.SortableVector;
import zaluc.utils.SortableHandle;
import zaluc.utils.SortableObject;

class Person implements SortableObject
{
    public static final int unknown = 0;
    public static final int male = 1;
    public static final int female = 2;
    public static final int compareAlphabetically = 1;
    public static final int compareByAge = 2;
    public Person next;
    public SortableHandle mainListHandle;
    public SortableHandle familyListHandle;
    public int id;
    public boolean includeDetails;
    public String firstName;
    public String lastName;
    public String title;
    public String nameSuffix;
    public String fullName;
    public GedcomEvent birth;
    public GedcomEvent death;
    private SortableVector events;
    private String lifeDates;
    public int sex;
    public boolean hide;
    public Person father;
    public Person mother;
    public Family childOfFamily;
    public SortableVector families;
    public int preferredFamily;
    private String details;
    public boolean written;
    
    public void addNote(final String s) {
    }
    
    public void addFamily(final Family family) {
        if (this.sex == 1) {
            this.families.addElement(family.husbandsListHandle);
            return;
        }
        this.families.addElement(family.wifesListHandle);
    }
    
    public void addEvent(final GedcomEvent gedcomEvent) {
        if (this.events == null) {
            this.events = new SortableVector(10, 10, 0);
        }
        this.events.addElement(gedcomEvent.listHandle);
    }
    
    public GedcomEvent getEvent(final int n) {
        if (n < this.events.size()) {
            return (GedcomEvent)this.events.elementAt(n).getContainer();
        }
        return null;
    }
    
    public void sortEvents() {
        if (this.events != null) {
            this.events.sort();
        }
    }
    
    public String getDetails() {
        if (this.details == null && this.events != null) {
            this.sortEvents();
            for (int size = this.events.size(), i = 0; i < size; ++i) {
                final String string = this.getEvent(i).toString();
                if (string != null) {
                    if (this.details == null) {
                        this.details = string;
                    }
                    else {
                        this.details = String.valueOf(this.details) + "\n" + string;
                    }
                }
            }
        }
        return this.details;
    }
    
    public String getLifeDates() {
        if (this.lifeDates == null) {
            if (this.birth != null && this.birth.hasYear() && (this.death == null || !this.death.hasYear())) {
                this.lifeDates = "b. " + this.birth.getYear();
            }
            else if (this.death != null && this.death.hasYear() && (this.birth == null || !this.birth.hasYear())) {
                this.lifeDates = "d. " + this.death.getYear();
            }
            else if (this.birth != null && this.birth.hasYear() && this.death != null && this.death.hasYear()) {
                this.lifeDates = String.valueOf(this.birth.getYear()) + " - " + this.death.getYear();
            }
        }
        return this.lifeDates;
    }
    
    public int compareTo(final SortableObject sortableObject, final int n) {
        final Person person = (Person)sortableObject;
        if (n == 1) {
            int compareTo;
            if (this.lastName == null && person.lastName == null) {
                compareTo = 0;
            }
            else if (this.lastName == null) {
                compareTo = 1;
            }
            else if (person.lastName == null) {
                compareTo = -1;
            }
            else {
                compareTo = this.lastName.compareTo(person.lastName);
            }
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2;
            if (this.firstName == null && person.firstName == null) {
                compareTo2 = 0;
            }
            else if (this.firstName == null) {
                compareTo2 = 1;
            }
            else if (person.firstName == null) {
                compareTo2 = -1;
            }
            else {
                compareTo2 = this.firstName.compareTo(person.firstName);
            }
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        int compareTo3;
        if (this.birth == null && (person == null || person.birth == null)) {
            compareTo3 = 0;
        }
        else if (this.birth == null) {
            compareTo3 = 1;
        }
        else if (person == null || person.birth == null) {
            compareTo3 = -1;
        }
        else {
            compareTo3 = this.birth.compareTo(person.birth, n);
        }
        return compareTo3;
    }
    
    public void write(final Record record) throws IOException {
        if (!this.written) {
            this.written = true;
            record.write(3, this.mainListHandle.getIndex());
            record.write(4, this.id);
            record.write(11, this.sex);
            if (this.hide) {
                record.write(17);
            }
            if (this.firstName != null) {
                record.write(5, this.firstName);
            }
            if (this.lastName != null) {
                record.write(6, this.lastName);
            }
            if (this.title != null) {
                record.write(7, this.title);
            }
            if (this.nameSuffix != null) {
                record.write(8, this.nameSuffix);
            }
            if (this.includeDetails) {
                final String details = this.getDetails();
                if (details != null) {
                    record.write(9, details);
                }
            }
            final String lifeDates = this.getLifeDates();
            if (lifeDates != null) {
                record.write(10, lifeDates);
            }
            if (this.father != null) {
                record.write(12, this.father.mainListHandle.getIndex());
            }
            if (this.mother != null) {
                record.write(13, this.mother.mainListHandle.getIndex());
            }
            final Enumeration<SortableHandle> elements = this.families.elements();
            while (elements.hasMoreElements()) {
                record.write(14, ((Family)elements.nextElement().getContainer()).index);
            }
        }
    }
    
    public String toHtml() {
        final DecimalFormat decimalFormat = new DecimalFormat("0000");
        String s = String.valueOf(new StringBuffer("<a href='javascript:launch(").append(this.id).append(");'><img src=../../gifs/smaltree.gif border=0 alt='View tree for: '></a>&nbsp;").append(this.fullName).append(" (").append(this.id).append(")</h3>\n").toString()) + "   <ul>\n";
        if (this.father != null) {
            s = String.valueOf(s) + "      <p>Father: <a href=UHP-" + decimalFormat.format(this.father.id) + ".html>" + this.father.fullName + "</a></p>\n";
        }
        if (this.mother != null) {
            s = String.valueOf(s) + "      <p>Mother: <a href=UHP-" + decimalFormat.format(this.mother.id) + ".html>" + this.mother.fullName + "</a></p>\n";
        }
        final Enumeration<SortableHandle> elements = this.families.elements();
        while (elements.hasMoreElements()) {
            final Family family = (Family)elements.nextElement().getContainer();
            if (this.sex == 1 && family.mother != null) {
                s = String.valueOf(s) + "      <p>Spouse: <a href=UHP-" + decimalFormat.format(family.mother.id) + ".html>" + family.mother.fullName + "</a></p>\n";
            }
            if (this.sex == 2 && family.father != null) {
                s = String.valueOf(s) + "      <p>Spouse: <a href=UHP-" + decimalFormat.format(family.father.id) + ".html>" + family.father.fullName + "</a></p>\n";
            }
            String s2 = String.valueOf(s) + "      <ul>\n";
            final Enumeration<Object> elements2 = family.children.elements();
            while (elements2.hasMoreElements()) {
                final Person person = (Person)elements2.nextElement().getContainer();
                s2 = String.valueOf(s2) + "         <p>Child: <a href=UHP-" + decimalFormat.format(person.id) + ".html>" + person.fullName + "</a></p>\n";
            }
            s = String.valueOf(s2) + "      </ul>\n";
        }
        return String.valueOf(s) + "   </ul>\n";
    }
    
    public Person append(final Person next) {
        if (next != null) {
            this.next = next;
            next.next = null;
            return next;
        }
        return this;
    }
    
    Person() {
        this.mainListHandle = new SortableHandle(this);
        this.familyListHandle = new SortableHandle(this);
        this.includeDetails = true;
        this.hide = false;
        this.families = new SortableVector(3, 3, 0);
        this.preferredFamily = -1;
        this.written = false;
    }
}
