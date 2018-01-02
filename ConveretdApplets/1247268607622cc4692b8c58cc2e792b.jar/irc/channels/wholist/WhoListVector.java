// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

import irc.com.nick.NickInfos;
import java.util.Vector;

public class WhoListVector extends Vector
{
    public void add(final String s) {
        this.addElement(new WhoItem(s, NickInfos.getSex(s), NickInfos.getAge(s), NickInfos.getLocation(s), NickInfos.getChan(s), null));
    }
    
    public void remove(final String s) {
        for (int i = 0; i < this.size(); ++i) {
            final WhoItem whoItem = this.elementAt(i);
            if (whoItem.getNom().equalsIgnoreCase(s)) {
                this.removeElement(whoItem);
                break;
            }
        }
    }
    
    public void rename(final String s, final String nom) {
        for (int i = 0; i < this.size(); ++i) {
            final WhoItem whoItem = this.elementAt(i);
            if (whoItem.getNom().equals(s)) {
                whoItem.setNom(nom);
                break;
            }
        }
    }
}
