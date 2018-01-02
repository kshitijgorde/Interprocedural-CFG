// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.awt.Color;

public class UserChoice
{
    public boolean enterSubmit;
    public boolean bold;
    public boolean italic;
    public Color inputColor;
    public String fontName;
    public boolean showJoin;
    public boolean showAvatar;
    public boolean timestamp;
    public boolean doubleSpace;
    public int localFontSize;
    public boolean soundEnterOn;
    public boolean soundExitOn;
    public boolean soundNewOn;
    public String soundEnter;
    public String soundExit;
    public String soundNew;
    public boolean separator;
    
    public UserChoice() {
        this.enterSubmit = true;
        this.bold = false;
        this.italic = false;
        this.inputColor = null;
        this.fontName = null;
        this.showJoin = true;
        this.showAvatar = true;
        this.timestamp = false;
        this.doubleSpace = false;
        this.localFontSize = 12;
        this.soundEnterOn = false;
        this.soundExitOn = false;
        this.soundNewOn = false;
        this.soundEnter = null;
        this.soundExit = null;
        this.soundNew = null;
        this.separator = false;
    }
}
