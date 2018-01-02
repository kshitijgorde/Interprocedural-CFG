import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ClientUserList
{
    final int \u00da = 10;
    final int \u00db = 5;
    int \u00dc;
    ClientUser[] \u00dd;
    Color[] \u00de;
    Color[] \u00df;
    String[] \u00e0;
    
    public ClientUserList() {
        this.\u00dc = -1;
        this.\u00da();
        this.\u00db();
    }
    
    void \u00da() {
        this.\u00df = new Color[5];
        this.\u00de = new Color[5];
        this.\u00e0 = new String[5];
        this.\u00df[0] = new Color(51, 0, 204);
        this.\u00df[1] = new Color(51, 153, 0);
        this.\u00df[2] = new Color(255, 102, 0);
        this.\u00df[3] = new Color(153, 102, 255);
        this.\u00df[4] = new Color(153, 153, 102);
        this.\u00de[0] = new Color(161, 133, 243);
        this.\u00de[1] = new Color(161, 216, 133);
        this.\u00de[2] = new Color(255, 188, 133);
        this.\u00de[3] = new Color(216, 188, 255);
        this.\u00de[4] = new Color(216, 216, 188);
        this.\u00e0[0] = "Blue";
        this.\u00e0[1] = "Green";
        this.\u00e0[2] = "Orange";
        this.\u00e0[3] = "Purple";
        this.\u00e0[4] = "Khaki";
    }
    
    void \u00db() {
        this.\u00dd = new ClientUser[10];
        for (int i = 0; i < 10; ++i) {
            this.\u00dd[i] = new ClientUser(this.\u00de[i % 5], this.\u00df[i % 5], "", this.\u00e0[i % 5]);
        }
    }
    
    boolean addUser(final int uid, final int n, final String s) {
        this.\u00dd[n].setUID(uid);
        this.\u00dd[n].\u00d8();
        this.\u00dd[n].\u00d4(s);
        return true;
    }
    
    boolean addUser(final int n, final String s) {
        for (int i = 0; i <= this.\u00dc; ++i) {
            if (n == this.\u00dd[i].\u00d0()) {
                this.\u00dd[i].\u00d8();
                return true;
            }
        }
        for (int j = 0; j <= this.\u00dc; ++j) {
            if (this.\u00dd[j].\u00d9()) {
                this.addUser(n, j, s);
                return true;
            }
        }
        this.addUser(n, this.\u00dc + 1, s);
        ++this.\u00dc;
        return true;
    }
    
    public void clearChecked() {
        for (int i = 0; i <= this.\u00dc; ++i) {
            this.\u00dd[i].resetChecked();
        }
    }
    
    void clearList() {
        System.out.println("Clearing userlist");
        int \u00fc = -1;
        for (int i = 0; i <= this.\u00dc; ++i) {
            if (this.\u00dd[i].checked()) {
                \u00fc = i;
            }
            else {
                this.removeUser(i);
            }
        }
        this.\u00dc = \u00fc;
    }
    
    void \u00dc() {
        for (int i = 0; i < 10; ++i) {
            this.removeUser(i);
        }
    }
    
    void removeUser(final int n) {
        if (!this.\u00dd[n].\u00d9()) {
            this.\u00dd[n].\u00cf();
            this.\u00dd[n].resetChecked();
            this.\u00dd[n].\u00d5();
        }
    }
    
    int \u00d0(final String s) {
        for (int i = 0; i <= this.\u00dc; ++i) {
            if (this.\u00dd[i].\u00d3().equals(s)) {
                return this.\u00dd[i].\u00d0();
            }
        }
        return -1;
    }
}
