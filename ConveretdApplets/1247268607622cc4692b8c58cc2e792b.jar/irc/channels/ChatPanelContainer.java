// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import irc.com.nick.NickInfos;
import java.util.Enumeration;
import irc.managers.CHANNELS;
import javax.swing.JPopupMenu;
import java.util.Hashtable;
import java.awt.CardLayout;
import irc.EIRC;

public class ChatPanelContainer
{
    private EIRC eirc;
    private CardLayout container_layout;
    private Hashtable panels;
    private boolean paneladded;
    private JPopupMenu menuusers;
    private JPopupMenu menuchans;
    private Object current;
    private String currentname;
    private String Pvcur;
    private String command;
    
    public ChatPanelContainer(final EIRC eirc) {
        this.current = null;
        this.currentname = "";
        this.panels = new Hashtable();
        this.eirc = eirc;
    }
    
    public synchronized void addChannel(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        this.ShowChannel(lowerCase);
    }
    
    public void free() {
        this.container_layout = null;
        this.panels.clear();
        this.panels = null;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public Object getCurrent() {
        return this.current;
    }
    
    public String getCurrentname() {
        return this.currentname;
    }
    
    public Hashtable getPanels() {
        return this.panels;
    }
    
    public String getPvcur() {
        return this.Pvcur;
    }
    
    public boolean isPaneladded() {
        return this.paneladded;
    }
    
    public synchronized void Notify(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.startsWith("#")) {
            if (!this.eirc.isIsgroupchannel()) {
                final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                while (elements.hasMoreElements()) {
                    final ChannelWindow channelWindow = elements.nextElement();
                    if (channelWindow.getTag().equalsIgnoreCase(lowerCase)) {
                        channelWindow.flash();
                    }
                }
            }
            else {
                this.eirc.getChannelgroup().Notifychannel(lowerCase);
            }
        }
        else if (!this.eirc.isIsgrouppv()) {
            final Enumeration<PrivateWindow> elements2 = this.eirc.getPrivates().privates.elements();
            while (elements2.hasMoreElements()) {
                final PrivateWindow privateWindow = elements2.nextElement();
                if (privateWindow.getUser().equalsIgnoreCase(lowerCase)) {
                    privateWindow.flash();
                }
            }
        }
        else {
            this.eirc.getPvgroup().Notifypv(lowerCase);
        }
    }
    
    public void setCurrent(final Object current) {
        this.current = current;
    }
    
    public void setCurrentname(final String currentname) {
        this.currentname = currentname;
    }
    
    public void setTitle(final String s, final String s2) {
    }
    
    public synchronized void ShowChannel(final String s) {
        if (!this.eirc.isIsgroupchannel()) {
            CHANNELS.getChannelWindow(s).setVisible(true);
            CHANNELS.getChannelWindow(s).scrollDown();
            CHANNELS.getChannelWindow(s).entryrequestFocus();
        }
        else {
            this.eirc.getChannelgroup().Showchannel(s);
        }
    }
    
    public synchronized void ShowPrivate(String lowerCase) {
        NickInfos.setTalk(lowerCase.toLowerCase());
        lowerCase = lowerCase.toLowerCase();
        try {
            if (!this.eirc.isIsgrouppv()) {
                this.eirc.getPrivates().getPrivate(lowerCase).setVisible(true);
                this.eirc.getPrivates().getPrivate(lowerCase).setState(0);
                this.eirc.getPrivates().getPrivate(lowerCase).toFront();
            }
            else {
                this.eirc.getPvgroup().Showpv(lowerCase);
            }
            this.eirc.getPrivates().getPrivate(lowerCase).scrollDown();
            this.eirc.getPrivates().getPrivate(lowerCase).EntryrequestFocus();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
