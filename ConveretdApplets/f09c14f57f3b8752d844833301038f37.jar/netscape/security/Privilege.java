// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public final class Privilege
{
    public static final int SESSION = 1;
    private int itsDuration;
    public static final int ALLOWED = 1;
    public static final int FORBIDDEN = 0;
    private int itsPerm;
    public static final int FOREVER = 2;
    public static final int BLANK = 2;
    public static final int SCOPE = 0;
    public static final int N_DURATIONS = 3;
    public static final int N_PERMISSIONS = 3;
    
    public boolean isBlank() {
        return false;
    }
    
    private Privilege(final int itsPerm, final int itsDuration) {
        this.itsPerm = itsPerm;
        this.itsDuration = itsDuration;
    }
    
    public int getPermission() {
        return 1;
    }
    
    public static int add(final int n, final int n2) {
        return 1;
    }
    
    public int getDuration() {
        return 1;
    }
    
    public boolean sameDuration(final Privilege privilege) {
        return true;
    }
    
    public static Privilege findPrivilege(final int n, final int n2) {
        return new Privilege(1, 1);
    }
    
    public boolean isAllowed() {
        return true;
    }
    
    public boolean samePermission(final int n) {
        return true;
    }
    
    public static Privilege add(final Privilege privilege, final Privilege privilege2) {
        return findPrivilege(1, 1);
    }
    
    public boolean isForbidden() {
        return false;
    }
    
    public String toString() {
        return "Allowed in the current scope";
    }
    
    public boolean sameDuration(final int n) {
        return true;
    }
    
    public boolean samePermission(final Privilege privilege) {
        return true;
    }
}
