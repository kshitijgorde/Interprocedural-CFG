// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client;

import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.Container;
import java.util.Hashtable;
import jchatbox.client.local.Communication;
import jchatbox.client.util.ChatException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import jchatbox.client.util.Debug;
import jchatbox.client.util.Conf;
import jchatbox.client.gui.DBApplet;

public class jcbApplet extends DBApplet
{
    private boolean _$6085;
    private LoginPanel _$6086;
    private int _$495;
    private int _$6087;
    
    public String getParameter(final String s, final String s2) {
        return this._$6085 ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public jcbApplet() {
        this._$6085 = false;
        this._$6086 = null;
        this._$495 = -1;
        this._$6087 = -1;
    }
    
    public void init() {
        try {
            Conf.URL = this.getParameter("serverurl", String.valueOf(String.valueOf(this.getCodeBase().toString())).concat("../xml_connector/processor.jsp"));
            Conf.SKINPATH = this.getParameter("skin", ".");
            this._$495 = Integer.parseInt(this.getParameter("refresh", "-1"));
            this._$6087 = Integer.parseInt(this.getParameter("timeout", "-1")) * 1000;
            Debug.LEVEL = Integer.parseInt(this.getParameter("loglevel", "0"));
            this._$619(0, this.getClass().getName(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getAppletInfo()))).append("2.5").append("(").append(Debug.LEVEL).append(")"))));
            this._$619(1, this.getClass().getName(), "Connecting to : ".concat(String.valueOf(String.valueOf(Conf.URL))));
            Conf.APPLET_WIDTH = this.size().width;
            Conf.APPLET_HEIGHT = this.size().height;
            Conf.loadSkin(new URL(this.getDocumentBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Conf.SKINPATH))).append("/").append(Conf.XMLFILE)))));
            final MediaTracker mediaTracker = new MediaTracker(this);
            if (Conf.LOGINPANEL_IMAGE != null) {
                mediaTracker.addImage(Conf.LOGINPANEL_PICTURE = this.getImage(this.getDocumentBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Conf.SKINPATH))).append("/").append(Conf.LOGINPANEL_IMAGE)))), 0);
            }
            if (Conf.CHATPANEL_IMAGE != null) {
                mediaTracker.addImage(Conf.CHATPANEL_PICTURE = this.getImage(this.getDocumentBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Conf.SKINPATH))).append("/").append(Conf.CHATPANEL_IMAGE)))), 0);
            }
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this._$503();
        }
        catch (ChatException ex2) {
            this._$619(0, this.getClass().getName(), "Init Error:".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
            if (this._$6086 != null) {
                this._$6086.setErrorMsg(ex2.getMessage());
            }
        }
        catch (Exception ex3) {
            this._$619(0, this.getClass().getName(), "Init Error:".concat(String.valueOf(String.valueOf(ex3.getMessage()))));
            if (this._$6086 != null) {
                this._$6086.setErrorMsg(Conf.INVALIDRESPONSE);
            }
            this.showStatus("Cannot run jChatBox Applet");
        }
    }
    
    private void _$503() throws Exception, ChatException {
        final Communication instance = Communication.getInstance(Conf.URL);
        instance.setNonBlockingMode(this._$6087);
        try {
            if (Conf.checkMultilanguage()) {
                final Vector doLanguages = instance.doLanguages();
                if (doLanguages != null && doLanguages.size() > 0) {
                    Conf.RESOURCES = new Hashtable();
                    Conf.DEFAULTLANGUAGE = doLanguages.elementAt(0);
                    for (int i = 1; i < doLanguages.size(); ++i) {
                        final String s = doLanguages.elementAt(i);
                        Conf.RESOURCES.put(s, instance.doResources(s));
                        this._$619(2, this.getClass().getName(), String.valueOf(String.valueOf(s)).concat(" resource loaded"));
                    }
                }
                Conf.loadResources(Conf.DEFAULTLANGUAGE);
            }
        }
        catch (Exception ex) {
            this._$619(0, this.getClass().getName(), "Resources Error:".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
        this._$6086 = new LoginPanel(this);
        this.setLayout(null);
        this.add(this._$6086);
        this._$6086.setLocation(0, 0);
        this._$6086.setSize(Conf.APPLET_WIDTH, Conf.APPLET_HEIGHT);
        this._$619(2, this.getClass().getName(), "AWT Init OK");
        this._$6086.setRooms(instance.doManager());
        this._$6086.updateChatRooms();
    }
    
    public void start() {
        this._$619(2, this.getClass().getName(), "Start called");
    }
    
    public void stop() {
        this._$619(2, this.getClass().getName(), "Stop called");
        final ChatPanel chatPanel = this._$6086.getChatPanel();
        if (chatPanel != null) {
            chatPanel.performLogout();
            chatPanel.setRefreshRate(-1);
            this._$619(2, this.getClass().getName(), "Force logout");
        }
    }
    
    public void destroy() {
        this._$619(2, this.getClass().getName(), "Stop called");
    }
    
    public String getAppletInfo() {
        return "jChatBox Client Applet ";
    }
    
    public int getRefreshRate() {
        return this._$495;
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
