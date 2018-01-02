// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.util.Vector;
import pclient.shd.RoomItem;
import com.pchat.sc.StringUtil;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import java.awt.List;

public class RoomList extends List implements ActionListener
{
    private long lastRefreshTime;
    private BaseChat pChat;
    
    public RoomList(final int n, final BaseChat pChat) {
        super(n, false);
        this.lastRefreshTime = 0L;
        this.pChat = pChat;
        final Font font = new Font("Dialog", 0, 10);
        if (font != null) {
            this.setFont(font);
        }
        this.addActionListener(this);
    }
    
    public void process(final int n, final String[] array) {
        switch (n) {
            case 300: {
                this.refreshRoomList();
                break;
            }
            case 200: {
                this.roamServerRoomList();
                break;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this) {
            this.clickedItem();
        }
    }
    
    public void refreshRoomList() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastRefreshTime < 900L) {
            this.lastRefreshTime = currentTimeMillis;
            System.out.println("warn #9483. room refresh");
            return;
        }
        this.lastRefreshTime = currentTimeMillis;
        this.pChat.chatModel.cmQueryList();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(92, 240);
    }
    
    private void clickedItem() {
        final String selectedItem = this.getSelectedItem();
        if (StringUtil.isEmpty(selectedItem)) {
            return;
        }
        final String decodeSimple = RoomItem.decodeSimple(selectedItem);
        if (StringUtil.isEmpty(decodeSimple)) {
            return;
        }
        this.pChat.parentComp.connRoamSwitch(decodeSimple);
    }
    
    private void roamServerRoomList() {
        this.removeAll();
        final Vector roamRooms = this.pChat.parentComp.roamRooms;
        for (int i = 0; i < roamRooms.size(); ++i) {
            this.add(roamRooms.elementAt(i).encodeSimple());
        }
    }
}
