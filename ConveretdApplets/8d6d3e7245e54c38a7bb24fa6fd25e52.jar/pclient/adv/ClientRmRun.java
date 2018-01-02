// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import pclient.shd.RoomItem;
import java.util.Vector;

public class ClientRmRun implements Runnable
{
    private LoginPanel loginPanel;
    
    public ClientRmRun(final LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
    
    public void run() {
        final Vector load = LoginPanel.load(this.loginPanel.appletChat);
        if (load == null) {
            return;
        }
        final RoomItem[] rooms = LoginPanel.parseRooms(load, this.loginPanel.appletChat);
        if (rooms == null) {
            return;
        }
        this.loginPanel.setRoomList(rooms, this.loginPanel.appletChat.paraConf.get("Net.Room"));
    }
}
