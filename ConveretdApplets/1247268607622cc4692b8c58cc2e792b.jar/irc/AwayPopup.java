// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import javax.swing.event.PopupMenuEvent;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.StringTokenizer;
import javax.swing.JMenuItem;
import java.util.Enumeration;
import irc.channels.ChannelWindow;
import irc.managers.CHANNELS;
import irc.com.nick.NickInfos;
import irc.managers.CMD;
import irc.com.utils.MyVector;
import irc.managers.Resources;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;

public class AwayPopup implements ActionListener, PopupMenuListener
{
    private EIRC eirc;
    JPopupMenu menu;
    
    public AwayPopup(final EIRC eirc) {
        this.menu = new JPopupMenu();
        this.eirc = eirc;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        final String[] array = { "" };
        if (actionEvent.getActionCommand().equals(Resources.getStringEirc("eirc.away_custom"))) {
            array[0] = this.openAwayWin();
        }
        else {
            array[0] = actionEvent.getActionCommand();
        }
        if (array[0] != null && array[0].trim().length() != 0) {
            this.eirc.sendMessage("AWAY", array);
            final MyVector myVector = new MyVector(2);
            myVector.addElement("[away] " + array[0]);
            CMD.cmd_ame(myVector);
            NickInfos.setAway(this.eirc.getUsednick(), array[0]);
            this.eirc.isAway();
            this.eirc.getAccueil().trayIconsetToolTip(array[0]);
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                final ChannelWindow channelWindow = elements.nextElement();
                if (channelWindow != null) {
                    channelWindow.nick_listrepaint();
                }
            }
        }
    }
    
    public JPopupMenu createMenu() {
        this.menu.addPopupMenuListener(this);
        final JMenuItem menuItem = new JMenuItem(Resources.getStringEirc("eirc.away_custom"));
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getStringEirc("eirc.away_list"), "/");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final JMenuItem menuItem2 = new JMenuItem(nextToken);
            menuItem2.setActionCommand(nextToken);
            menuItem2.setForeground(EIRC.mainfg);
            if (nextToken.trim().equals("Occup\u00e9(e).")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_occupee")));
            }
            else if (nextToken.trim().equals("De retour dans une minute.")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_de_retour")));
            }
            else if (nextToken.trim().equals("Absent(e).")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_absent")));
            }
            else if (nextToken.trim().equals("En conversation t\u00e9l\u00e9phonique.")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_telephone")));
            }
            else if (nextToken.trim().equals("Parti(e) manger.")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_manger")));
            }
            else if (nextToken.trim().equals("En plein sommeil.")) {
                menuItem2.setIcon(new ImageIcon(Resources.getImages("icon_sommeil")));
            }
            this.menu.add(menuItem2);
            menuItem2.addActionListener(this);
        }
        final JMenuItem menuItem3 = new JMenuItem(Resources.getStringEirc("eirc.away_custom"));
        this.menu.addSeparator();
        this.menu.add(menuItem3);
        menuItem3.addActionListener(this);
        return this.menu;
    }
    
    public String openAwayWin() {
        final String showInputDialog = JOptionPane.showInputDialog(this.eirc.getFrame(), Resources.getStringEirc("away.prompt"), Resources.getStringEirc("away.title"), 1);
        if (showInputDialog != null) {
            final String s = showInputDialog;
            return (s != null && s.trim().length() != 0) ? s : "Absent(e)";
        }
        return null;
    }
    
    @Override
    public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
    }
    
    @Override
    public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
    }
    
    @Override
    public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
    }
}
