// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.awt.Color;

public class PrefDef
{
    public Color userColor;
    public Color selfColor;
    public Color adminColor;
    public Color modColor;
    public Color spkColor;
    public Color userName;
    public Color selfName;
    public String selfSuffix;
    public String otherSuffix;
    public Color textColor;
    public Color systemColor;
    public Color localColor;
    public Color linkColor;
    public Color questionColor;
    public Color answerColor;
    public boolean link;
    
    public PrefDef(final Config val) {
        this.userColor = Color.black;
        this.selfColor = Color.blue;
        this.adminColor = new Color(0, 124, 64);
        this.modColor = new Color(255, 124, 0);
        this.spkColor = new Color(0, 0, 128);
        this.userName = Color.black;
        this.selfName = Color.blue;
        this.selfSuffix = ":";
        this.otherSuffix = ":";
        this.textColor = Color.black;
        this.systemColor = Color.red;
        this.localColor = Color.black;
        this.linkColor = Color.blue;
        this.questionColor = new Color(0, 51, 0);
        this.answerColor = new Color(0, 0, 102);
        this.link = true;
        this.setVal(val);
    }
    
    public void setVal(final Config config) {
    }
}
