// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.nicklist;

import java.util.Enumeration;
import java.util.Vector;
import irc.com.nick.NickInfos;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import irc.channels.components.PopupInfos;
import irc.channels.ChannelWindow;
import javax.swing.JList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JScrollPane;

public class NickList extends JScrollPane implements MouseMotionListener, MouseListener
{
    public JList nicklist;
    private ChannelWindow cw;
    PopupInfos popupinfos;
    
    public NickList(final ChannelWindow cw) {
        this.setVerticalScrollBarPolicy(20);
        this.setHorizontalScrollBarPolicy(30);
        this.cw = cw;
        (this.nicklist = new JList()).setCellRenderer(new NickitemRenderer());
        this.getViewport().add(this.nicklist);
        this.popupinfos = new PopupInfos("");
        this.nicklist.addMouseMotionListener(this);
        this.nicklist.addMouseListener(this);
        this.nicklist.add(this.popupinfos);
        this.nicklist.setAutoscrolls(true);
        this.nicklist.setBackground(Color.WHITE);
    }
    
    public void free() {
        this.nicklist.removeAll();
        this.nicklist = null;
        this.cw = null;
        this.popupinfos = null;
    }
    
    public JList getNicklist() {
        return this.nicklist;
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.cw.revenir();
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.popupinfos.setVisible(false);
        this.nicklist.clearSelection();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.cw.revenir();
        synchronized (this.nicklist) {
            final int locationToIndex = this.nicklist.locationToIndex(mouseEvent.getPoint());
            if (locationToIndex != -1 && this.nicklist.getModel().getSize() > locationToIndex) {
                final NickItem nickItem = this.nicklist.getModel().getElementAt(locationToIndex);
                if (nickItem != null) {
                    this.nicklist.setSelectedIndex(this.nicklist.locationToIndex(mouseEvent.getPoint()));
                    if (NickInfos.getLocation(nickItem.getNick()).equals("Robot de Logs") || NickInfos.getLocation(nickItem.getNick()).equals("Robot de Gestion") || NickInfos.getLocation(nickItem.getNick()).equals("Robot de Protection") || NickInfos.getLocation(nickItem.getNick()).equals("Robot BotServ") || NickInfos.getLocation(nickItem.getNick()).equals("Gardien des Jeux") || NickInfos.getLocation(nickItem.getNick()).equals("Robot de jeux") || NickInfos.getLocation(nickItem.getNick()).equals("Robot de Stats")) {
                        this.popupinfos.setText(NickInfos.getLocation(nickItem.getNick()));
                        if (NickInfos.getSex(nickItem.getNick()) == 1) {
                            this.popupinfos.setBackground(Color.decode("0x0099FF"));
                        }
                        else {
                            this.popupinfos.setBackground(Color.decode("0xFF00FF"));
                        }
                        this.popupinfos.setVisible(true);
                        this.popupinfos.setLocation(mouseEvent.getX() + 10, mouseEvent.getY() - 5);
                    }
                    else {
                        String s = " F ";
                        if (NickInfos.getSex(nickItem.getNick()) == 1) {
                            s = " H ";
                        }
                        String string = " ";
                        if (NickInfos.getAway(nickItem.getNick()) != null) {
                            string += NickInfos.getAway(nickItem.getNick());
                        }
                        if (NickInfos.isNosex(nickItem.getNick()) == 1) {
                            this.popupinfos.setText(NickInfos.getAge(nickItem.getNick()) + s + NickInfos.getLocation(nickItem.getNick()) + " [No Sex]" + string);
                        }
                        else {
                            this.popupinfos.setText(NickInfos.getAge(nickItem.getNick()) + s + NickInfos.getLocation(nickItem.getNick()) + string);
                        }
                        if (NickInfos.getSex(nickItem.getNick()) == 1) {
                            this.popupinfos.setBackground(Color.decode("0x0099FF"));
                        }
                        else {
                            this.popupinfos.setBackground(Color.decode("0xFF00FF"));
                        }
                        this.popupinfos.setVisible(true);
                        this.popupinfos.setLocation(mouseEvent.getX() + 10, mouseEvent.getY() - 5);
                    }
                }
            }
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void refresh(final NickListVector nickListVector) {
        final Vector<Object> listData = new Vector<Object>();
        final Enumeration<Object> elements = nickListVector.elements();
        while (elements.hasMoreElements()) {
            try {
                listData.add(elements.nextElement());
            }
            catch (Exception ex) {
                System.out.println("erreru de copie");
            }
        }
        this.nicklist.setListData(listData);
        this.nicklist.repaint();
    }
    
    public void setNickList(final Vector listData) {
        this.nicklist.setListData(listData);
    }
}
