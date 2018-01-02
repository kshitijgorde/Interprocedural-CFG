// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import irc.com.utils.MySQL;

class MonThread extends Thread
{
    String lien;
    
    public MonThread() {
        this.setPriority(1);
    }
    
    @Override
    public void run() {
        new MySQL(this.lien).execute();
    }
    
    public void setLien(final String lien) {
        this.lien = lien;
    }
}
