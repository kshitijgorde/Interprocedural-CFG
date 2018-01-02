// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.util.Vector;

class Person
{
    public static final int male = 1;
    public static final int female = 2;
    private Globals globals;
    private PeopleList plist;
    public boolean isBlank;
    public int id;
    public int index;
    public boolean hidden;
    public String firstName;
    public String lastName;
    public String title;
    public String nameSuffix;
    public String fullName;
    public String details;
    public String lifeDates;
    public int sex;
    public int father;
    public int mother;
    public Vector families;
    public Person next;
    public boolean isComplete;
    public boolean drawMe;
    
    public Person(final Globals globals, final PeopleList plist, final int index) {
        this.isBlank = false;
        this.hidden = false;
        this.families = new Vector(3, 3);
        this.isComplete = false;
        this.drawMe = false;
        this.globals = globals;
        this.plist = plist;
        this.index = index;
        final int n = -1;
        this.mother = n;
        this.father = n;
        this.sex = 1;
    }
    
    public void addFamily(final int n) {
        this.families.addElement(new Integer(n));
    }
    
    public Family getFamily(final int n) {
        Family family = null;
        if (this.families != null) {
            try {
                final Integer n2 = this.families.elementAt(n);
                if (n2 != null) {
                    family = this.plist.getFamily(n2);
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        return family;
    }
    
    public void complete() {
        if (this.firstName != null && this.lastName != null) {
            this.fullName = String.valueOf(this.firstName) + " " + this.lastName;
        }
        else if (this.firstName == null && this.lastName != null) {
            this.fullName = this.lastName;
        }
        else if (this.firstName != null && this.lastName == null) {
            this.fullName = this.firstName;
        }
        else {
            this.fullName = "no name";
        }
        if (this.details != null) {
            this.details = String.valueOf(this.fullName) + "\n" + this.details;
        }
        else if (this.lifeDates != null) {
            this.details = String.valueOf(this.fullName) + "\n" + this.lifeDates;
        }
        else {
            this.details = this.fullName;
        }
        this.isComplete = true;
    }
    
    public boolean shouldDrawChildren() {
        if (!this.drawMe) {
            return true;
        }
        Family family;
        for (int n = 0; (family = this.getFamily(n)) != null; ++n) {
            Person person;
            if (this.sex == 1) {
                person = this.plist.getPerson(family.mother);
            }
            else {
                person = this.plist.getPerson(family.father);
            }
            if (person != null && !person.drawMe) {
                return true;
            }
            Person child;
            for (int n2 = 0; (child = family.getChild(n2)) != null; ++n2) {
                if (child.shouldDrawChildren()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean shouldDrawParents() {
        final Person person;
        final Person person2;
        return !this.drawMe || ((person = this.plist.getPerson(this.father)) != null && person.shouldDrawParents()) || ((person2 = this.plist.getPerson(this.mother)) != null && person2.shouldDrawParents());
    }
}
