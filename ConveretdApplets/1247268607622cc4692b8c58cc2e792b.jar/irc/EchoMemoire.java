// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import irc.managers.CHANNELS;

public class EchoMemoire extends Thread
{
    float max;
    float total;
    float free;
    String _total;
    String _max;
    String _free;
    String _used;
    String _usedpercent;
    String _freepercent;
    EIRC eirc;
    Runtime r;
    
    public EchoMemoire(final EIRC eirc) {
        this._usedpercent = "";
        this._freepercent = "";
        this.r = Runtime.getRuntime();
        this.eirc = eirc;
        this.setPriority(1);
        this.start();
    }
    
    @Override
    public void run() {
        while (true) {
            this.r.gc();
            this.send();
            this.eirc.getAccueil();
            if (!main.istest) {
                try {
                    Thread.sleep(300000L);
                }
                catch (InterruptedException ex) {}
            }
            else {
                try {
                    Thread.sleep(60000L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public void send() {
        this.free = this.r.freeMemory() / 1000000.0f;
        this.total = this.r.totalMemory() / 1000000.0f;
        this.max = this.r.maxMemory() / 1000000.0f;
        this._free = "" + this.free;
        this._total = "" + this.total;
        this._max = "" + this.max;
        this._used = "" + (this.total - this.free);
        String s = "Degrouper";
        String s2 = "Degrouper";
        if (this.eirc.isIsgroupchannel()) {
            s = "Grouper";
        }
        if (this.eirc.isIsgrouppv()) {
            s2 = "Grouper";
        }
        this.eirc.sendMessage("PRIVMSG", new String[] { "#messenger-m", "\u000312Utilis\u00e9e:" + this._used.substring(0, 4) + " Mo " + '\u0003' + "14 Libre:" + this._free.substring(0, 4) + " Mo " + '\u0003' + "10Allou\u00e9e:" + this._total.substring(0, 4) + "Mo " + '\u0003' + "6Max:" + this._max.substring(0, 5) + " Mo" + '\u0003' + "3 Test : Salon " + CHANNELS.channels.size() + "   " + this.eirc.getChannelgroup().gettail() + " : " + '\u0003' + "4 " + s + " , " + '\u0003' + "5 PV : " + this.eirc.getPrivates().privates.size() + " " + this.eirc.getPvgroup().gettail() + '\u0003' + "6 " + s2 + " V " + "V 5.34" + " " + '\u0003' });
        if (main.istest) {
            final EIRC eirc = this.eirc;
            EIRC.memoire = "Utilis\u00e9e:" + this._used.substring(0, 4) + "Mo " + " Libre:" + this._free.substring(0, 4) + "Mo " + "Allou\u00e9e:" + this._total.substring(0, 4) + "Mo " + "Max:" + this._max.substring(0, 5) + "Mo";
        }
        this.eirc.repaint();
    }
}
