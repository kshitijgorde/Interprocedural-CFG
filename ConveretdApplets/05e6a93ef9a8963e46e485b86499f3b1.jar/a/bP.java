// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import netscape.security.PrivilegeManager;
import java.awt.Color;

public class bP
{
    public aX q;
    public Color q;
    public Color w;
    public boolean q;
    public boolean w;
    
    public static boolean q() {
        try {
            if (cs.q == 0) {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
                PrivilegeManager.enablePrivilege("UniversalFileAccess");
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public bP() {
    }
    
    public bP(final bf bf, final bf bf2, final aX q) {
        this.q = Color.black;
        this.w = Color.white;
        this.q = true;
        this.w = false;
        this.q = q;
    }
    
    public int q() {
        if (this.q instanceof l) {
            return ((l)this.q).y - 1;
        }
        return 0;
    }
    
    public int w() {
        if (this.q instanceof l) {
            return ((l)this.q).u;
        }
        return 0;
    }
    
    public Image q() {
        if (this.q instanceof as) {
            return ((as)this.q).q;
        }
        return null;
    }
}