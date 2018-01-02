// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.util.Properties;
import java.util.Hashtable;
import java.awt.Color;

public class MsgOptions
{
    private static final String FONT = "font";
    private static final String FSTYLE = "fstyle";
    private static final String COLOR = "color";
    private static final String V_I = "I";
    private static final String V_B = "B";
    private static final String V_BI = "BI";
    public String fontname;
    public boolean fontBold;
    public boolean fontItalic;
    public Color color;
    
    public MsgOptions() {
        this.fontname = null;
        this.fontBold = false;
        this.fontItalic = false;
    }
    
    public boolean isBI() {
        return this.fontBold && this.fontItalic;
    }
    
    public String getOps() {
        final Hashtable hashtable = new Hashtable<String, String>();
        if (this.fontname != null) {
            hashtable.put("font", this.fontname);
        }
        if (this.fontBold && this.fontItalic) {
            hashtable.put("fstyle", "BI");
        }
        else if (this.fontBold) {
            hashtable.put("fstyle", "B");
        }
        else if (this.fontItalic) {
            hashtable.put("fstyle", "I");
        }
        if (this.color != null) {
            hashtable.put("color", "" + this.color.getRGB());
        }
        if (hashtable.size() == 0) {
            return null;
        }
        return StringUtil.genAttributes(hashtable);
    }
    
    public void setOps(final String s) {
        final Properties decAttributes = StringUtil.decAttributes(s);
        if (decAttributes == null) {
            return;
        }
        this.fontname = ((Hashtable<K, String>)decAttributes).get("font");
        final String s2 = ((Hashtable<K, String>)decAttributes).get("fstyle");
        this.fontBold = false;
        this.fontItalic = false;
        if (s2 != null) {
            if (s2.equalsIgnoreCase("I")) {
                this.fontItalic = true;
            }
            else if (s2.equalsIgnoreCase("B")) {
                this.fontBold = true;
            }
            else if (s2.equalsIgnoreCase("BI")) {
                this.fontBold = true;
                this.fontItalic = true;
            }
        }
        this.color = null;
        final String s3 = ((Hashtable<K, String>)decAttributes).get("color");
        if (s3 != null) {
            this.color = new Color(WindowUtil.parseInt(s3, 0));
        }
    }
}
