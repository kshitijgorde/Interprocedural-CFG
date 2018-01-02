// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.nick;

import java.awt.Image;

public class NickInfoBean
{
    private int age;
    private String inetaddres;
    private String nick;
    private String location;
    private boolean register;
    private String humeur;
    private String ident;
    private int nosex;
    private boolean ignored;
    private int sexe;
    private String away;
    private String locationcode;
    private String chan;
    private Image avatar;
    private Image bigavatar;
    private boolean tof;
    private boolean cam;
    private boolean nomessage;
    
    public NickInfoBean() {
        this.humeur = null;
        this.away = null;
        this.avatar = null;
        this.bigavatar = null;
        this.tof = false;
        this.cam = false;
        this.nomessage = false;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public Image getAvatar() {
        return this.avatar;
    }
    
    public String getAway() {
        return this.away;
    }
    
    public Image getBigavatar() {
        return this.bigavatar;
    }
    
    public String getChan() {
        return this.chan;
    }
    
    public String getHumeur() {
        return this.humeur;
    }
    
    public String getIdent() {
        return this.ident;
    }
    
    public String getInetaddres() {
        return this.inetaddres;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getLocationcode() {
        return this.locationcode;
    }
    
    public String getNick() {
        return this.nick;
    }
    
    public int getSexe() {
        return this.sexe;
    }
    
    public boolean isCam() {
        return this.cam;
    }
    
    public boolean isIgnored() {
        return this.ignored;
    }
    
    public boolean isNomessage() {
        return this.nomessage;
    }
    
    public int isNosex() {
        return this.nosex;
    }
    
    public boolean isRegister() {
        return this.register;
    }
    
    public boolean isTof() {
        return this.tof;
    }
    
    public void setAge(final int age) {
        this.age = age;
    }
    
    public void setAvatar(final Image avatar) {
        this.avatar = avatar;
    }
    
    public void setAway(final String away) {
        this.away = away;
    }
    
    public void setBigavatar(final Image bigavatar) {
        this.bigavatar = bigavatar;
    }
    
    public void setCam(final boolean cam) {
        this.cam = cam;
    }
    
    public void setChan(final String chan) {
        this.chan = chan;
    }
    
    public void setHumeur(final String humeur) {
        this.humeur = humeur;
    }
    
    public void setIdent(final String ident) {
        this.ident = ident;
    }
    
    public void setIgnored(final boolean ignored) {
        this.ignored = ignored;
    }
    
    public void setInetaddres(final String inetaddres) {
        this.inetaddres = inetaddres;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public void setLocationcode(final String locationcode) {
        this.locationcode = locationcode;
    }
    
    public void setNick(final String nick) {
        this.nick = nick;
    }
    
    public void setNomessage(final boolean nomessage) {
        this.nomessage = nomessage;
    }
    
    public void setNosex(final int nosex) {
        this.nosex = nosex;
    }
    
    public void setRegister(final boolean register) {
        this.register = register;
    }
    
    public void setSexe(final int sexe) {
        this.sexe = sexe;
    }
    
    public void setTof(final boolean tof) {
        this.tof = tof;
    }
}
