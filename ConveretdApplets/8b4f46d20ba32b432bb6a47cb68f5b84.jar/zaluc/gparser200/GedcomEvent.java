// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import zaluc.utils.SortableHandle;
import zaluc.utils.SortableObject;

class GedcomEvent implements SortableObject
{
    public SortableHandle listHandle;
    private String type;
    private String value;
    private GedcomDate date;
    private String place;
    
    public GedcomEvent() {
        this.listHandle = new SortableHandle(this);
    }
    
    public GedcomEvent(final GedcomEvent gedcomEvent) {
        this.listHandle = new SortableHandle(this);
        if (gedcomEvent.type != null) {
            this.type = new String(gedcomEvent.type);
        }
        if (gedcomEvent.value != null) {
            this.value = new String(gedcomEvent.value);
        }
        if (gedcomEvent.date != null) {
            this.date = new GedcomDate(gedcomEvent.date);
        }
        if (gedcomEvent.place != null) {
            this.place = new String(gedcomEvent.place);
        }
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setDate(final String s) {
        this.date = new GedcomDate(s);
    }
    
    public void setPlace(final String place) {
        this.place = place;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getDate() {
        if (this.date != null) {
            return this.date.toString();
        }
        return null;
    }
    
    public boolean hasYear() {
        return this.date != null && this.date.hasYear();
    }
    
    public int getYear() {
        if (this.date != null) {
            return this.date.getYear();
        }
        return 0;
    }
    
    public String getPlace() {
        return this.place;
    }
    
    public int compareTo(final SortableObject sortableObject, final int n) {
        final GedcomEvent gedcomEvent = (GedcomEvent)sortableObject;
        if (this.date != null) {
            if (gedcomEvent != null) {
                return this.date.compareTo(gedcomEvent.date);
            }
            return -1;
        }
        else {
            if (gedcomEvent.date != null) {
                return -1;
            }
            return 0;
        }
    }
    
    public String toString() {
        String s = "";
        if (this.value != null) {
            s = String.valueOf(s) + this.value;
        }
        if (this.date != null) {
            if (this.value != null) {
                s = String.valueOf(s) + ", ";
            }
            s = String.valueOf(s) + this.getDate();
        }
        if (this.place != null) {
            if (this.value != null || this.date != null) {
                s = String.valueOf(s) + ", ";
            }
            s = String.valueOf(s) + this.place;
        }
        if (this.type != null) {
            if (this.value != null || this.date != null || this.place != null) {
                s = String.valueOf(this.type) + ": " + s;
            }
            else {
                s = this.type;
            }
        }
        if (s.length() == 0) {
            s = null;
        }
        return s;
    }
}
