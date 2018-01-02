// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.nicklist;

import java.awt.SystemColor;
import java.awt.Color;

public class NickItem
{
    protected int modes;
    protected String nick;
    protected int sexe;
    protected String loc;
    protected int age;
    private Color textbg;
    private Color textfg;
    private Color selbg;
    private Color selfg;
    public boolean item_bullet;
    private int index;
    private Color foreground;
    private Color background;
    
    public NickItem() {
        this.textbg = SystemColor.text;
        this.textfg = SystemColor.textText;
        this.selbg = Color.white;
        this.selfg = SystemColor.textHighlightText;
    }
    
    public NickItem(final String nick, final int sexe, final String loc, final int age) {
        this.textbg = SystemColor.text;
        this.textfg = SystemColor.textText;
        this.selbg = Color.white;
        this.selfg = SystemColor.textHighlightText;
        this.setNick(nick);
        this.setSexe(sexe);
        this.setLoc(loc);
        this.setAge(age);
    }
    
    public int getAge() {
        return this.age;
    }
    
    public Color getBackground() {
        return this.background;
    }
    
    public boolean getBullet() {
        return this.item_bullet;
    }
    
    public Color getForeground() {
        return this.foreground;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public String getLoc() {
        return this.loc;
    }
    
    public int getModes() {
        return this.modes;
    }
    
    public String getNick() {
        return this.nick;
    }
    
    public Color getSelectedBackground() {
        return this.selbg;
    }
    
    public Color getSelectedForeground() {
        return this.selfg;
    }
    
    public int getSexe() {
        return this.sexe;
    }
    
    public Color getTextBackground() {
        return this.textbg;
    }
    
    public Color getTextForeground() {
        return this.textfg;
    }
    
    public void resetMode(final int n) {
        this.modes &= ~n;
    }
    
    public void setAge(final int age) {
        this.age = age;
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public void setForeground(final Color foreground) {
        this.foreground = foreground;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public void setLoc(final String loc) {
        this.loc = loc;
    }
    
    public void setMode(final int n) {
        this.modes |= n;
    }
    
    public void setModes(final int modes) {
        this.modes = modes;
    }
    
    public void setNick(final String nick) {
        this.nick = nick;
    }
    
    public void setSelectedBackground(final Color selbg) {
        this.selbg = selbg;
    }
    
    public void setSelectedForeground(final Color selfg) {
        this.selfg = selfg;
    }
    
    public void setSexe(final int sexe) {
        this.sexe = sexe;
    }
    
    public void setTextBackground(final Color textbg) {
        this.textbg = textbg;
    }
    
    public void setTextForeground(final Color textfg) {
        this.textfg = textfg;
    }
}
