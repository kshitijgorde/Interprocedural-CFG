// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.chanlist;

import irc.EIRC;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class ChannelItemRenderer extends JPanel implements ListCellRenderer
{
    ChannelItemMyComp comp;
    
    public ChannelItemRenderer() {
        this.comp = new ChannelItemMyComp();
        this.setLayout(new GridLayout(1, 1));
        this.comp.setPreferredSize(new Dimension(0, 20));
        this.setForeground(Color.decode("0x007F00"));
        this.add(this.comp);
    }
    
    @Override
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final ChannelItem channelItem = (ChannelItem)o;
        this.comp.setNomSalon(channelItem.getTag());
        this.comp.setNbSalon("" + channelItem.getUsers());
        this.comp.setIsmyregion(channelItem.isIsmyregion());
        this.comp.setOpaque(false);
        if (!channelItem.getTag().startsWith("#")) {
            this.setOpaque(true);
            this.comp.setBackground(EIRC.mainbg);
            this.setBackground(EIRC.mainbg);
        }
        else if (b) {
            this.setOpaque(true);
            this.comp.setBackground(Color.decode("0xADD8E6"));
            this.setBackground(Color.decode("0xADD8E6"));
        }
        else {
            this.setOpaque(false);
        }
        return this;
    }
}
