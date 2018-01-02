// 
// Decompiled by Procyon v0.5.30
// 

package irc.com;

import java.awt.Color;

public class User
{
    public static final int NORMAL_MASK = 1;
    public static final int OP_MASK = 2;
    public static final int HALFOP_MASK = 4;
    public static final int VOICE_MASK = 8;
    public static final int ADMIN_MASK = 16;
    public static final int OWNER_MASK = 32;
    public static final int AWAY_MASK = 64;
    public static final Color COLOR_OP;
    public static final Color COLOR_HOP;
    public static final Color COLOR_ADMIN;
    public static final Color COLOR_OWNER;
    private String tag;
    private int modes;
    private String awayMess;
    
    public User(final String s) {
        this.modes = Modes.symbolicToMask(s.charAt(0));
        this.tag = Modes.canonicalizeNick(s);
    }
    
    public String getAwayMess() {
        return this.awayMess;
    }
    
    public int getModes() {
        return this.modes;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public boolean isAdmin() {
        return (this.modes & 0x10) != 0x0;
    }
    
    public void isAway(final String awayMess) {
        this.awayMess = awayMess;
        this.modes |= 0x40;
    }
    
    public void isBack() {
        this.awayMess = null;
        this.modes &= 0xFFFFFFBF;
    }
    
    public boolean isHalfOp() {
        return (this.modes & 0x4) != 0x0;
    }
    
    public boolean isOp() {
        return (this.modes & 0x2) != 0x0;
    }
    
    public boolean isOwner() {
        return (this.modes & 0x20) != 0x0;
    }
    
    public boolean isVoiced() {
        return (this.modes & 0x8) != 0x0;
    }
    
    public void setModes(final int modes) {
        this.modes = modes;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    static {
        COLOR_OP = new Color(170, 0, 0);
        COLOR_HOP = new Color(0, 127, 0);
        COLOR_ADMIN = new Color(255, 85, 85);
        COLOR_OWNER = new Color(85, 85, 255);
    }
}
