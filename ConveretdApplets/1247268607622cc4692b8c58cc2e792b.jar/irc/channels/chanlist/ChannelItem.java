// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.chanlist;

public class ChannelItem
{
    private String tag;
    private int users;
    private boolean adulte;
    private boolean ismyregion;
    
    public ChannelItem(final String tag, final int users, final boolean adulte, final boolean ismyregion) {
        this.tag = tag;
        this.users = users;
        this.adulte = adulte;
        this.ismyregion = ismyregion;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public int getUsers() {
        return this.users;
    }
    
    public boolean isAdulte() {
        return this.adulte;
    }
    
    public boolean isIsmyregion() {
        return this.ismyregion;
    }
    
    public void setAdulte(final boolean adulte) {
        this.adulte = adulte;
    }
    
    public void setIsmyregion(final boolean ismyregion) {
        this.ismyregion = ismyregion;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public void setUsers(final int users) {
        this.users = users;
    }
}
