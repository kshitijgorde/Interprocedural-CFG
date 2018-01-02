// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

public class WhoItem
{
    public String nom;
    public String region;
    public String chan;
    public String dist;
    public int sexe;
    public int age;
    
    WhoItem(final String nom, final int sexe, final int age, final String region, final String chan, final String dist) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.region = region;
        this.chan = chan;
        this.dist = dist;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String getChan() {
        return this.chan;
    }
    
    public String getDist() {
        return this.dist;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    public int getSexe() {
        return this.sexe;
    }
    
    public void setDist(final String dist) {
        this.dist = dist;
    }
    
    public void setNom(final String nom) {
        this.nom = nom;
    }
}
