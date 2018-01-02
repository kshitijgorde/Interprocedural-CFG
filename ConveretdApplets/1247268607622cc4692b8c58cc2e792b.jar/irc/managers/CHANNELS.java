// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.StringTokenizer;
import java.text.MessageFormat;
import java.util.Enumeration;
import irc.channels.ChannelWindow;
import java.util.Hashtable;
import irc.EIRC;

public class CHANNELS
{
    static EIRC eirc;
    public static Hashtable channels;
    
    public static void CloseChannel(final String s) {
        String s2 = s.trim();
        if (!s2.startsWith("#")) {
            s2 = "#" + s2;
        }
        CHANNELS.eirc.sendMessage("PART", new String[] { s2.toLowerCase() });
        final ChannelWindow channelWindow = CHANNELS.channels.get(s2.toLowerCase());
        if (channelWindow != null) {
            if (CHANNELS.eirc.isIsgroupchannel()) {
                CHANNELS.eirc.getChannelgroup().closechannel(s2.toLowerCase());
            }
            channelWindow.disposeChannel();
            CHANNELS.channels.remove(s2.toLowerCase());
            channelWindow.setVisible(false);
        }
    }
    
    public static void closeChannels() {
        synchronized (CHANNELS.channels) {
            final String[] array = { "" };
            final Enumeration<ChannelWindow> elements = (Enumeration<ChannelWindow>)CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                final ChannelWindow channelWindow = elements.nextElement();
                if (CHANNELS.eirc.isIsgroupchannel()) {
                    CHANNELS.eirc.getChannelgroup().closechannel(channelWindow.getTag());
                }
                if (channelWindow != null) {
                    if (array[0].equals("")) {
                        final StringBuilder sb = new StringBuilder();
                        final String[] array2 = array;
                        final int n = 0;
                        array2[n] = sb.append(array2[n]).append(channelWindow.getTag()).toString();
                    }
                    else {
                        final StringBuilder sb2 = new StringBuilder();
                        final String[] array3 = array;
                        final int n2 = 0;
                        array3[n2] = sb2.append(array3[n2]).append(",").append(channelWindow.getTag()).toString();
                    }
                    channelWindow.disposeChannel();
                }
            }
            CHANNELS.eirc.sendMessage("PART", array);
        }
    }
    
    public static void free() {
        CHANNELS.channels.clear();
    }
    
    public static ChannelWindow getChannelWindow(final String s) {
        return CHANNELS.channels.get(s.toLowerCase());
    }
    
    public static void init(final EIRC eirc) {
        CHANNELS.eirc = eirc;
    }
    
    public static void join(final String s, final String[] array) {
        if (CHANNELS.eirc.get_friends_list().contains(s.toLowerCase() + ":1")) {
            synchronized (CHANNELS.channels) {
                final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                while (elements.hasMoreElements()) {
                    if (elements.nextElement().getTag().equals(array[0])) {
                        if (getChannelWindow(array[0]) == null) {
                            continue;
                        }
                        CHANNELS.eirc.getCurrentPanel().printInfo("\u00038,4\u0002 *notice* \u0002 \u00031,0 - \u00036Votre ami " + s + " a rejoint le salon " + array[0]);
                    }
                }
            }
        }
        if (CHANNELS.eirc.getUsednick().equalsIgnoreCase(s)) {
            if (array[0].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[0].substring(1)) != -1) {
                return;
            }
            openChannel(array[0].toLowerCase());
        }
        else {
            if (array[0].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[0].substring(1)) != -1) {
                CHANNELS.eirc.getRightpanel().whoListPanel.add(s);
                return;
            }
            final ChannelWindow channelWindow = getChannelWindow(array[0]);
            if (channelWindow != null) {
                channelWindow.add(s);
            }
            if (CHANNELS.eirc.isSee_join()) {
                final ChannelWindow channelWindow2 = getChannelWindow(array[0]);
                if (channelWindow2 == null) {
                    return;
                }
                channelWindow2.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s6"), s));
                if (CHANNELS.eirc.getChat_panel().getCurrentname().equalsIgnoreCase(array[0])) {
                    CHANNELS.eirc.playSound(CHANNELS.eirc.getSound_join());
                }
            }
        }
        if (getChannelWindow(array[0]) != null) {
            CHANNELS.eirc.updateChanTitle(getChannelWindow(array[0]));
        }
    }
    
    public static void joinChannels(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s.toLowerCase(), ",");
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            if (getChannelWindow(nextToken) == null) {
                CHANNELS.eirc.sendMessage("JOIN", new String[] { nextToken });
            }
        }
    }
    
    public static void kick(final String s, final String[] array) {
        final String s2 = (array[2] != null) ? array[2] : "";
        if (CHANNELS.eirc.getUsednick().equalsIgnoreCase(array[1])) {
            CloseChannel(array[0].toLowerCase());
            CHANNELS.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s10"), array[0], s, s2));
            CHANNELS.eirc.playSound(CHANNELS.eirc.getSound_kick());
        }
        else {
            if (array[0].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[0].substring(1)) != -1) {
                CHANNELS.eirc.getRightpanel().whoListPanel.remove(s);
                return;
            }
            final ChannelWindow channelWindow = getChannelWindow(array[0]);
            if (channelWindow != null) {
                channelWindow.remove(array[1]);
                channelWindow.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s11"), array[1], s, s2));
                if (CHANNELS.eirc.getChat_panel().getCurrentname().equalsIgnoreCase(array[0])) {
                    CHANNELS.eirc.playSound(CHANNELS.eirc.getSound_kick());
                }
            }
        }
    }
    
    public static void openChannel(final String s) {
        String s2 = s.trim();
        if (!s2.startsWith("#")) {
            s2 = "#" + s2;
        }
        if (CHANNELS.channels.containsKey(s2.toLowerCase())) {
            return;
        }
        final ChannelWindow channelWindow = new ChannelWindow(CHANNELS.eirc, s2.toLowerCase());
        channelWindow.colorForeground(EIRC.mainfg);
        channelWindow.colorBackground(EIRC.mainbg);
        CHANNELS.channels.put(s2.toLowerCase(), channelWindow);
        CHANNELS.eirc.getChat_panel().addChannel(s2.toLowerCase());
        CHANNELS.eirc.sendMessage("MODE", new String[] { s2.toLowerCase() });
        if (s2.startsWith("#")) {
            s2.substring(1);
        }
    }
    
    public static void part(final String s, final String[] array) {
        if (CHANNELS.eirc.get_friends_list().contains(s.toLowerCase() + ":1")) {
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement().getTag().equals(array[0])) {
                    try {
                        CHANNELS.eirc.getCurrentPanel().printInfo("\u00038,4\u0002 *notice* \u0002 \u00031,0 - \u00036Votre ami " + s + " a quitt\u00e9 le salon " + array[0]);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (CHANNELS.eirc.getUsednick().equalsIgnoreCase(s)) {
            CloseChannel(array[0].toLowerCase());
            return;
        }
        if (array[0].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[0].substring(1)) != -1) {
            CHANNELS.eirc.getRightpanel().whoListPanel.remove(s);
            return;
        }
        final ChannelWindow channelWindow = getChannelWindow(array[0]);
        if (channelWindow != null) {
            channelWindow.remove(s);
        }
        if (CHANNELS.eirc.isSee_join()) {
            final ChannelWindow channelWindow2 = getChannelWindow(array[0]);
            if (channelWindow2 == null) {
                return;
            }
            String s2 = "";
            if (array.length >= 2) {
                s2 = array[1];
            }
            channelWindow2.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s8"), s, s2));
            if (CHANNELS.eirc.getChat_panel().getCurrentname().equals(array[0].toLowerCase())) {
                CHANNELS.eirc.playSound(CHANNELS.eirc.getSound_part());
            }
        }
        if (getChannelWindow(array[0]) != null) {
            CHANNELS.eirc.updateChanTitle(getChannelWindow(array[0]));
        }
    }
    
    static {
        CHANNELS.channels = new Hashtable();
    }
}
