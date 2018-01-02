// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import irc.com.nick.NickInfos;
import irc.managers.CMD;
import irc.com.utils.MyVector;
import java.util.TimerTask;

public class DetecteAbsent extends TimerTask
{
    EIRC eirc;
    
    public DetecteAbsent(final EIRC eirc) {
        this.eirc = eirc;
    }
    
    @Override
    public void run() {
        if (this.eirc.getAbsent() <= this.eirc.getAway_delay() * 60) {
            this.eirc.setAbsent(this.eirc.getAbsent() + 1);
        }
        if (!this.eirc.isIs_away() && !this.eirc.isIsInactive() && this.eirc.isAuto_away() && this.eirc.getAbsent() > this.eirc.getAway_delay() * 60) {
            final String[] array = { "Absent(e)" };
            this.eirc.sendMessage("AWAY", array);
            final MyVector myVector = new MyVector(2);
            myVector.addElement("[away] " + array[0]);
            CMD.cmd_ame(myVector);
            NickInfos.setAway(this.eirc.getNick(), array[0]);
            this.eirc.isAway();
            this.eirc.setIsInactive(true);
        }
    }
}
