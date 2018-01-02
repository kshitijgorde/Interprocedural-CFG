// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import irc.com.messages.MircMessage;
import irc.com.nick.NickInfos;
import irc.channels.textarea.NewTextDocument;
import java.util.Enumeration;
import irc.channels.PrivateWindow;
import java.util.Hashtable;
import irc.EIRC;

public class PRIVATES
{
    EIRC eirc;
    public Hashtable privates;
    
    public PRIVATES(final EIRC eirc) {
        this.privates = new Hashtable();
        this.eirc = eirc;
        this.privates = new Hashtable();
    }
    
    public void closeAll() {
        final Enumeration<PrivateWindow> elements = this.privates.elements();
        while (elements.hasMoreElements()) {
            final PrivateWindow privateWindow = elements.nextElement();
            if (privateWindow != null) {
                try {
                    if (this.eirc.isIsgrouppv()) {
                        this.eirc.getPvgroup().closepv(privateWindow.getUser());
                    }
                    this.privates.remove(privateWindow.getUser().toLowerCase());
                    privateWindow.disposePrivate();
                    privateWindow.dispose();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void ClosePrivate(final String s) {
        this.eirc.sendCommand("watch -" + s, null);
        if (this.eirc.isIsgrouppv()) {
            this.eirc.getPvgroup().closepv(s);
        }
        final PrivateWindow privateWindow = this.privates.get(s.toLowerCase());
        this.privates.remove(s.toLowerCase());
        privateWindow.free();
        privateWindow.dispose();
    }
    
    public PrivateWindow getPrivate(final String s) {
        return this.privates.get(s.toLowerCase());
    }
    
    public PrivateWindow openPrivate(final String s, final boolean b) {
        final PrivateWindow private1 = this.getPrivate(s);
        if (private1 != null) {
            return private1;
        }
        if (!s.toLowerCase().equalsIgnoreCase(this.eirc.getChat_panel().getCurrentname().toLowerCase()) && !this.eirc.isNo_privates()) {
            this.eirc.playSound(this.eirc.getSound_prvmsg());
        }
        if (!b) {
            return this.openPrivate(s, 0);
        }
        final String inetAddr = NickInfos.getInetAddr(s);
        if (this.eirc.is_a_friend(s) || (inetAddr != null && inetAddr.indexOf("chat-land.org") != -1)) {
            return this.openPrivate(s, 0);
        }
        if (this.eirc.getUsernomore().indexOf(s.toLowerCase()) == -1) {
            this.eirc.setUsernomore(this.eirc.getUsernomore() + s.toLowerCase() + "/");
            this.eirc.sendMessage("PRIVMSG", new String[] { s.toLowerCase(), MircMessage.attrEncode('\u0003') + "4" + Resources.getStringEirc("pv.no_more") });
        }
        return null;
    }
    
    public PrivateWindow openPrivate(final String s, final int n) {
        final PrivateWindow private1 = this.getPrivate(s);
        if (private1 != null) {
            return private1;
        }
        NickInfos.setTalk(s.toLowerCase());
        final PrivateWindow privateWindow = new PrivateWindow(this.eirc, s);
        this.privates.put(s.toLowerCase(), privateWindow);
        if (!this.eirc.isIsgrouppv()) {
            privateWindow.setState(1);
            privateWindow.setVisible(true);
        }
        else {
            privateWindow.setVisible(false);
            this.eirc.getPvgroup().addpv(privateWindow);
        }
        privateWindow.colorForeground(EIRC.mainfg);
        privateWindow.colorBackground(EIRC.mainbg);
        final String location = NickInfos.getLocation(s);
        if (location != null && location.equals("Jocker")) {
            NickInfos.getLocation(this.eirc.getNick());
        }
        return privateWindow;
    }
}
