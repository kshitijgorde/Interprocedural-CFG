// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Locale;
import logging.LogType;
import logging.Log;
import logging.LogHolder;
import gui.GUIUtils;
import anon.util.JAPMessages;

public final class JAPiPAQ
{
    private JAPNewView view;
    private JAPController m_controller;
    
    public void startJAP(final String s) {
        if (!JAPMessages.init("JAPMessages")) {
            GUIUtils.exitWithNoMessagesError("MixConfigMessages");
        }
        JAPModel.getInstance().setSmallDisplay(true);
        this.m_controller = JAPController.getInstance();
        LogHolder.setLogInstance(JAPDebug.getInstance());
        JAPDebug.getInstance().setLogType(LogType.NET + LogType.GUI + LogType.THREAD + LogType.MISC);
        JAPDebug.getInstance().setLogLevel(4);
        this.m_controller.loadConfigFile(s, null);
        (this.view = new JAPNewView(JAPModel.getInstance().getProgramName(), this.m_controller)).create(false);
        this.m_controller.addJAPObserver(this.view);
        this.m_controller.setView(this.view, new ConsoleSplash());
        this.m_controller.initialRun(null, 0);
        this.view.setSize(240, 300);
        this.view.setLocation(0, 0);
        this.view.setResizable(false);
        this.view.setVisible(true);
    }
    
    public void setLocale(final Locale locale) {
        JAPMessages.setLocale(locale);
    }
    
    public static void main(final String[] array) {
        new JAPiPAQ().startJAP(null);
    }
}
