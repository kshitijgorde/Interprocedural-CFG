// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.JPanel;
import anon.infoservice.MixCascade;
import anon.AnonServerDescription;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionListener;
import logging.LogHolder;
import logging.LogType;

public class ConsoleJAPMainView implements IJAPMainView
{
    public int addStatusMsg(final String s, final int n, final boolean b) {
        LogHolder.log(1, LogType.MISC, s);
        return 0;
    }
    
    public int addStatusMsg(final String s, final int n, final boolean b, final ActionListener actionListener) {
        LogHolder.log(1, LogType.MISC, s);
        return 0;
    }
    
    public void doClickOnCascadeChooser() {
    }
    
    public void updateValues(final boolean b) {
    }
    
    public void showConfigDialog() {
    }
    
    public void showConfigDialog(final String s, final Object o) {
    }
    
    public void setVisible(final boolean b) {
        System.out.println("Type 'exit' to quit or 'save' to save the configuration.");
        while (true) {
            String line = null;
            try {
                line = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            catch (Throwable t) {}
            if (line == null) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
            }
            else {
                if (line.equals("exit")) {
                    break;
                }
                if (line.equals("save")) {
                    System.out.println("Saving configuration...");
                    if (!JAPController.getInstance().saveConfigFile()) {
                        System.out.println("Configuration saved!");
                    }
                    else {
                        System.out.println("Error while saving configuration!");
                    }
                }
                System.out.println("Type 'exit' to quit or 'save' to save the configuration.");
            }
        }
        JAPController.goodBye(true);
    }
    
    public void channelsChanged(final int n) {
    }
    
    public void packetMixed(final long n) {
    }
    
    public void dataChainErrorSignaled() {
        LogHolder.log(1, LogType.NET, "Disconnected because the service proxy is not working!");
    }
    
    public void disconnected() {
        LogHolder.log(1, LogType.NET, "Disconnected!");
    }
    
    public void connectionError() {
        LogHolder.log(1, LogType.NET, "Disconnected because of connection error!");
    }
    
    public void connecting(final AnonServerDescription anonServerDescription) {
        if (anonServerDescription instanceof MixCascade) {
            final MixCascade mixCascade = (MixCascade)anonServerDescription;
            LogHolder.log(1, LogType.NET, "Connecting to " + mixCascade.getId() + "(" + mixCascade.getMixNames() + ")" + "...");
        }
        else {
            LogHolder.log(1, LogType.NET, "Connecting...");
        }
    }
    
    public void connectionEstablished(final AnonServerDescription anonServerDescription) {
        if (anonServerDescription instanceof MixCascade) {
            final MixCascade mixCascade = (MixCascade)anonServerDescription;
            LogHolder.log(1, LogType.NET, "Connected to " + mixCascade.getId() + "(" + mixCascade.getMixNames() + ")" + "!");
        }
        else {
            LogHolder.log(1, LogType.NET, "Connected!");
        }
    }
    
    public void create(final boolean b) {
    }
    
    public void disableSetAnonMode() {
    }
    
    public void onUpdateValues() {
    }
    
    public JPanel getMainPanel() {
        return null;
    }
    
    public void registerViewIconified(final JAPViewIconified japViewIconified) {
    }
    
    public JAPViewIconified getViewIconified() {
        return null;
    }
    
    public void removeStatusMsg(final int n) {
    }
    
    public void transferedBytes(final long n, final int n2) {
    }
}
