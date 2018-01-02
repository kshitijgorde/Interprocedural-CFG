import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ClientUser
{
    final int \u00cf = -1;
    int \u00d0;
    Color \u00d1;
    Color \u00d2;
    String \u00d3;
    String \u00d4;
    boolean checked;
    
    public ClientUser(final Color \u00f1, final Color \u00f2, final String \u00f4, final String \u00f3) {
        this.\u00d0 = -1;
        this.\u00d1 = \u00f1;
        this.\u00d2 = \u00f2;
        this.\u00d4 = \u00f4;
        this.\u00d3 = \u00f3;
        this.checked = false;
    }
    
    public void setUID(final int \u00f0) {
        this.\u00d0 = \u00f0;
    }
    
    void \u00cf() {
        this.\u00d0 = -1;
    }
    
    int \u00d0() {
        return this.\u00d0;
    }
    
    Color \u00d1() {
        return this.\u00d2;
    }
    
    Color \u00d2() {
        return this.\u00d1;
    }
    
    String \u00d3() {
        return this.\u00d4;
    }
    
    void \u00d4(final String \u00f4) {
        this.\u00d4 = \u00f4;
    }
    
    void \u00d5() {
        this.\u00d4 = "";
    }
    
    String \u00d6() {
        return this.\u00d3;
    }
    
    void \u00d8() {
        this.checked = true;
    }
    
    void resetChecked() {
        this.checked = false;
    }
    
    boolean checked() {
        return this.checked;
    }
    
    boolean \u00d9() {
        return this.\u00d0 == -1;
    }
}
