// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.StringUtil;
import java.util.Vector;

public class RankBasis
{
    public Vector speakerList;
    public Vector moderatorList;
    private Vector adminList;
    private Vector bcasterList;
    private static final String RK_BCASTER = "A";
    private static final String RK_SPEAKER = "B";
    private static final String RK_MODERATOR = "C";
    private static final String RK_ADMIN = "D";
    private static final String RK_REGULAR = "E";
    private Config paraConf;
    private SessionEnclosure userSession;
    
    public RankBasis(final Config paraConf, final SessionEnclosure userSession) {
        this.paraConf = paraConf;
        this.userSession = userSession;
        this.initData();
    }
    
    public void shutdown() {
        this.initData();
    }
    
    public void clearMod() {
        this.speakerList.removeAllElements();
        this.moderatorList.removeAllElements();
    }
    
    public void setAdmins(final String s) {
        this.setRoles(this.adminList, s, true);
        this.printLists();
        this.userSession.chatView.vwRefreshUsers();
    }
    
    public int compareUsers(final String s, final String s2) {
        final int compareTo = this.getRank(s).compareTo(this.getRank(s2));
        if (compareTo != 0) {
            return compareTo;
        }
        final int compareTo2 = s.toLowerCase().compareTo(s2.toLowerCase());
        if (compareTo2 != 0) {
            return compareTo2;
        }
        return s.compareTo(s2);
    }
    
    public void setSpeakers(final String s) {
        this.setRoles(this.speakerList, s, true);
        this.userSession.chatView.vwRefreshUsers();
        this.printLists();
    }
    
    public void setModerators(final String s) {
        this.setRoles(this.moderatorList, s, true);
        this.userSession.chatView.vwRefreshUsers();
        this.printLists();
    }
    
    public void setBcasters(final String s) {
        this.setRoles(this.bcasterList, s, true);
        this.userSession.chatView.vwRefreshUsers();
        this.printLists();
    }
    
    public boolean isRegular(final String s) {
        return this.getRank(s).equals("E");
    }
    
    public synchronized boolean isAdmin(final String s) {
        return this.adminList.contains(s);
    }
    
    public synchronized boolean isBcaster(final String s) {
        return this.bcasterList.contains(s);
    }
    
    public synchronized boolean isMod(final String s) {
        return this.moderatorList.contains(s);
    }
    
    public synchronized boolean isSpk(final String s) {
        return this.speakerList.contains(s);
    }
    
    public synchronized String[] moderatorNames() {
        final int size = this.moderatorList.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (String)this.moderatorList.elementAt(i);
        }
        return array;
    }
    
    public synchronized String[] speakerNames() {
        final int size = this.speakerList.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (String)this.speakerList.elementAt(i);
        }
        return array;
    }
    
    private void removeFromLists(final String s) {
        this.speakerList.removeElement(s);
        this.moderatorList.removeElement(s);
        this.adminList.removeElement(s);
    }
    
    private void setRoles(final Vector vector, final String s, final boolean b) {
        if (b) {
            vector.removeAllElements();
        }
        if (StringUtil.isEmpty(s)) {
            return;
        }
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int i = 0; i < splitString.length; ++i) {
            final String s2 = splitString[i];
            if (!vector.contains(s2)) {
                vector.addElement(s2);
            }
        }
    }
    
    private String getRank(final String s) {
        this.printLists();
        if (this.bcasterList.contains(s)) {
            return "A";
        }
        if (this.speakerList.contains(s)) {
            return "B";
        }
        if (this.moderatorList.contains(s)) {
            return "C";
        }
        if (this.adminList.contains(s)) {
            return "D";
        }
        return "E";
    }
    
    private void initData() {
        this.speakerList = new Vector(2);
        this.moderatorList = new Vector(2);
        this.adminList = new Vector(4);
        this.bcasterList = new Vector(4);
    }
    
    private void printLists() {
        this.paraConf.printer().print("speakers:" + this.speakerList);
        this.paraConf.printer().print("moderators:" + this.moderatorList);
        this.paraConf.printer().print("admins:" + this.adminList);
        this.paraConf.printer().print("bcasters:" + this.bcasterList);
    }
}
