// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public class RoomItem
{
    public String name;
    public int count;
    public boolean secondary;
    public boolean locked;
    private static final String MARKER = "*";
    public static boolean denoteUserCreated;
    public static boolean deleteEmpty;
    public static boolean sortByCount;
    
    public RoomItem(final String name, final int count) {
        this.name = null;
        this.count = 0;
        this.secondary = false;
        this.locked = false;
        this.name = name;
        if (this.name.startsWith("*")) {
            this.secondary = true;
            if (this.name.length() == "*".length()) {
                this.name = "";
            }
            this.name = this.name.substring("*".length());
        }
        this.count = count;
    }
    
    public boolean equals(final RoomItem roomItem) {
        return sameRoom(this.name, roomItem.name);
    }
    
    public static boolean sameRoom(final String s, final String s2) {
        return s.equals(s2);
    }
    
    public String encodeSimple() {
        String s = this.name;
        if (this.secondary) {
            s = "*" + s;
        }
        return s + " " + "(" + this.count + ")";
    }
    
    public static String decodeSimple(String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("*")) {
            if (s.length() == "*".length()) {
                return "";
            }
            s = s.substring("*".length());
        }
        final int index = s.indexOf(32);
        if (index < 0) {
            return s;
        }
        s = s.substring(0, index);
        return s;
    }
    
    public static String simpleForm(String substring) {
        if (substring == null) {
            return null;
        }
        if (substring.startsWith("*")) {
            if (substring.length() == "*".length()) {
                return "";
            }
            substring = substring.substring("*".length());
        }
        return substring;
    }
    
    public boolean isRemovable() {
        return this.count == 0 && (this.secondary || RoomItem.deleteEmpty);
    }
    
    public int compareToRoom(final RoomItem roomItem) {
        final int compareTo = this.name.toLowerCase().compareTo(roomItem.name.toLowerCase());
        if (!RoomItem.denoteUserCreated) {
            return compareTo;
        }
        if (this.secondary) {
            if (roomItem.secondary) {
                return compareTo;
            }
            return 2;
        }
        else {
            if (roomItem.secondary) {
                return -1;
            }
            return compareTo;
        }
    }
    
    public int compareInTreeView(final RoomItem roomItem) {
        final int compareTo = this.name.toLowerCase().compareTo(roomItem.name.toLowerCase());
        if (compareTo == 0) {
            return 0;
        }
        if (!RoomItem.denoteUserCreated) {
            if (RoomItem.sortByCount) {
                return this.compareCount(roomItem);
            }
            return compareTo;
        }
        else if (this.secondary) {
            if (!roomItem.secondary) {
                return 1;
            }
            if (!RoomItem.sortByCount) {
                return compareTo;
            }
            final int compareCount = this.compareCount(roomItem);
            if (compareCount != 0) {
                return compareCount;
            }
            return compareTo;
        }
        else {
            if (roomItem.secondary) {
                return -1;
            }
            if (!RoomItem.sortByCount) {
                return compareTo;
            }
            final int compareCount2 = this.compareCount(roomItem);
            if (compareCount2 != 0) {
                return compareCount2;
            }
            return compareTo;
        }
    }
    
    private int compareCount(final RoomItem roomItem) {
        if (this.count > roomItem.count) {
            return -1;
        }
        if (this.count == roomItem.count) {
            return 0;
        }
        return 1;
    }
    
    public String toString() {
        return "name=" + this.name + " count=" + this.count + " secondary=" + this.secondary + " locked=" + this.locked + " denoteUserCreated=" + RoomItem.denoteUserCreated;
    }
    
    static {
        RoomItem.denoteUserCreated = true;
        RoomItem.deleteEmpty = false;
        RoomItem.sortByCount = true;
    }
}
