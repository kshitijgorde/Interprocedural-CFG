// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.Date;
import irc.com.utils.RFC1459;
import irc.com.nick.NickInfos;
import java.util.MissingResourceException;
import irc.com.commands.Commands;
import javax.swing.text.BadLocationException;
import java.util.Enumeration;
import irc.channels.ChannelWindow;
import irc.com.utils.MyVector;
import java.text.MessageFormat;
import irc.com.utils.MD5Nick;
import irc.com.utils.MySQL;
import irc.main;
import irc.channels.textarea.NewTextDocument;
import irc.EIRC;

public class CMD
{
    static EIRC eirc;
    
    public static void cmd_add_friend(String lowerCase, final NewTextDocument newTextDocument) {
        lowerCase = lowerCase.toLowerCase();
        if (!CMD.eirc.get_friends_list().contains(lowerCase + ":0") && !CMD.eirc.get_friends_list().contains(lowerCase + ":1")) {
            CMD.eirc.get_friends_list().addElement(lowerCase + ":0");
            CMD.eirc.sendCommand("watch +" + lowerCase, null);
            CMD.eirc.sendMessage("NOTICE", new String[] { "#applet-amis", "add: " + lowerCase });
            final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/amis/insertion-securiser.php");
            mySQL.addParam("a", CMD.eirc.getNick().toLowerCase());
            mySQL.addParam("b", MD5Nick.getMD5_2(CMD.eirc.getNick().toLowerCase()).substring(4, 14));
            mySQL.addParam("c", lowerCase);
            mySQL.addParam("d", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(lowerCase)).substring(4, 14));
            mySQL.execute();
            newTextDocument.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s39"), lowerCase));
        }
    }
    
    public static void cmd_add_other_friend(final String s) {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/amis/insertion-securiser.php");
        mySQL.addParam("a", s);
        mySQL.addParam("b", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s)).substring(4, 14));
        mySQL.addParam("c", CMD.eirc.getNick().toLowerCase());
        mySQL.addParam("d", MD5Nick.getMD5_2(CMD.eirc.getNick().toLowerCase()).substring(4, 14));
        mySQL.execute();
    }
    
    public static void cmd_ame(final MyVector myVector) {
        if (myVector == null) {
            return;
        }
        synchronized (CHANNELS.channels) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex) {}
                    final String[] array = new String[myVector.size() + 1];
                    int n = 1;
                    final Enumeration elements = myVector.elements();
                    while (elements.hasMoreElements()) {
                        array[n] = elements.nextElement();
                        ++n;
                    }
                    array[0] = CMD.eirc.getChanneljoindefault();
                    array[1] = "\u0001ACTION " + array[1] + "\u0001";
                    CMD.eirc.sendMessage("PRIVMSG", array);
                    final Enumeration<ChannelWindow> elements2 = (Enumeration<ChannelWindow>)CHANNELS.channels.elements();
                    while (elements2.hasMoreElements()) {
                        final ChannelWindow channelWindow = elements2.nextElement();
                        array[0] = channelWindow.getTag().toLowerCase();
                        int n2 = 1;
                        final Enumeration elements3 = myVector.elements();
                        while (elements3.hasMoreElements()) {
                            array[n2] = elements3.nextElement();
                            ++n2;
                        }
                        if (!array[1].startsWith("[away]")) {
                            if (!array[1].startsWith("[back]")) {
                                if (array[1].startsWith("[huu]")) {
                                    channelWindow.printAction("Humeur: " + array[1].substring(5), CMD.eirc.getUsednick());
                                }
                            }
                        }
                        channelWindow.nick_listrepaint();
                        array[1] = "\u0001ACTION " + array[1] + "\u0001";
                        CMD.eirc.sendMessage("PRIVMSG", array);
                    }
                }
            }.start();
        }
    }
    
    public static void cmd_amsg(final String[] array) {
        synchronized (CHANNELS.channels) {
            final Enumeration<String> keys = CHANNELS.channels.keys();
            while (keys.hasMoreElements()) {
                array[0] = keys.nextElement();
                CMD.eirc.sendMessage("PRIVMSG", array);
            }
        }
    }
    
    public static void cmd_clear(final NewTextDocument newTextDocument) {
        try {
            newTextDocument.remove(0, newTextDocument.getLength());
        }
        catch (BadLocationException ex) {}
    }
    
    public static void cmd_cmd(final MyVector myVector, final NewTextDocument newTextDocument) {
        if (!(newTextDocument instanceof NewTextDocument)) {
            newTextDocument.printError(Resources.getStringEirc("eirc.s23"));
            return;
        }
        myVector.insertElementAt(newTextDocument.getChanneltag(), 0);
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[1] = "\u0001ACTION " + array[1] + "\u0001";
        CMD.eirc.sendMessage("PRIVMSG", array);
    }
    
    public static void cmd_ctcp(final MyVector myVector) {
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[1] = "\u0001" + array[1] + "\u0001";
        CMD.eirc.sendMessage("PRIVMSG", array);
    }
    
    public static void cmd_eirc(final NewTextDocument newTextDocument) {
    }
    
    public static void cmd_ghost(final MyVector myVector) {
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        CMD.eirc.getIrc_services().killGhost(array[0], array[1]);
    }
    
    public static void cmd_help(final MyVector myVector, final NewTextDocument newTextDocument) {
        if (myVector.size() == 0) {
            final Enumeration<String> keys = CMD.eirc.getUser_commands().getKeys();
            while (keys.hasMoreElements()) {
                newTextDocument.printInfo(keys.nextElement());
            }
            return;
        }
        String tag = (String)myVector.elementAt(0);
        try {
            tag = Commands.getCommand(CMD.eirc.getUser_commands(), tag).getTag();
        }
        catch (MissingResourceException ex) {}
        final String lowerCase = tag.toLowerCase();
        final String help = Resources.getHelp(lowerCase.concat(".short"));
        final String help2 = Resources.getHelp(lowerCase.concat(".long"));
        if (help != null && help2 != null) {
            newTextDocument.printInfo(help);
            newTextDocument.printInfo(help2);
        }
        else {
            newTextDocument.printError(MessageFormat.format(Resources.getStringEirc("eirc.no_help"), tag));
        }
    }
    
    public static void cmd_humeur(final String s) {
        final String user = NickInfos.getUser(CMD.eirc.getUsednick());
        if (user.length() >= 10) {
            final String string = user.substring(0, 7) + s + user.substring(9);
            if (NickInfos.getUser(CMD.eirc.getUsednick()).equals(string)) {
                return;
            }
            CMD.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string });
            NickInfos.updateInfos(CMD.eirc.getUsednick(), string, NickInfos.getInetAddr(CMD.eirc.getUsednick()));
            CMD.eirc.setUsername(string);
        }
    }
    
    public static void cmd_join(final MyVector myVector) {
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[0] = array[0].toLowerCase();
        if (!array[0].startsWith("#")) {
            array[0] = '#' + array[0];
        }
        CMD.eirc.playSound(CMD.eirc.getSound_join());
        CMD.eirc.sendMessage("JOIN", array);
    }
    
    public static void cmd_kban(final MyVector myVector, final NewTextDocument newTextDocument) {
        int n = 0;
        String channeltag = newTextDocument.getChanneltag();
        if (myVector.size() > 2) {
            channeltag = (String)myVector.elementAt(n++);
        }
        final String s = (String)myVector.elementAt(n++);
        String s2 = "";
        if (n == myVector.size() - 1) {
            s2 = (String)myVector.elementAt(n);
        }
        if (CHANNELS.getChannelWindow(channeltag) == null) {
            newTextDocument.printError(Resources.getStringEirc("eirc.s24"));
            return;
        }
        String s3 = s;
        final String inetAddr = NickInfos.getInetAddr(s);
        final String user = NickInfos.getUser(s);
        if (inetAddr == null) {
            if (user != null) {
                s3 = "*!" + user + "@*";
            }
        }
        else {
            s3 = "*!*@" + inetAddr;
        }
        CMD.eirc.sendCommand("MODE " + channeltag + " +b " + s3, newTextDocument);
        CMD.eirc.sendCommand("KICK " + channeltag + " " + s + " " + s2, newTextDocument);
    }
    
    public static void cmd_me(final MyVector myVector, final NewTextDocument newTextDocument) {
        myVector.insertElementAt(newTextDocument.getChanneltag(), 0);
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        newTextDocument.printAction(array[1], CMD.eirc.getNick());
        array[1] = "\u0001ACTION " + array[1] + "\u0001";
        CMD.eirc.sendMessage("PRIVMSG", array);
    }
    
    public static void cmd_msg(final MyVector myVector) {
        cmd_query(myVector);
    }
    
    public static void cmd_nick(final MyVector myVector) {
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[0] = RFC1459.filterString(array[0]);
        CMD.eirc.sendMessage("NICK", array);
        final EIRC eirc = CMD.eirc;
        final String[] array2 = { "#applet-idd", null };
        final int n = 1;
        final StringBuilder append = new StringBuilder().append(array[0]).append(":").append(CMD.eirc.getRealname()).append(":");
        final EIRC eirc2 = CMD.eirc;
        array2[n] = append.append(EIRC.IDD).toString();
        CMD.eirc.sendMessage("NOTICE", array2);
    }
    
    public static void cmd_notice(final MyVector myVector, final NewTextDocument newTextDocument) {
        if (myVector.size() == 2) {
            final String[] array = { (String)myVector.elementAt(0), (String)myVector.elementAt(1) };
            CMD.eirc.sendMessage("NOTICE", array);
            newTextDocument.printNotice(array[1], CMD.eirc.getNick());
        }
    }
    
    public static void cmd_notify_add_friend(String lowerCase, final NewTextDocument newTextDocument) {
        lowerCase = lowerCase.toLowerCase();
        if (!CMD.eirc.get_friends_list().contains(lowerCase + ":0") && !CMD.eirc.get_friends_list().contains(lowerCase + ":1")) {
            CMD.eirc.get_friends_list().addElement(lowerCase + ":0");
            CMD.eirc.sendCommand("watch +" + lowerCase, null);
            CMD.eirc.sendMessage("NOTICE", new String[] { "#applet-amis", "add: " + lowerCase });
            newTextDocument.printInfo(MessageFormat.format("Votre ami(e) {0} a bien \u00e9t\u00e9 ajout\u00e9 \u00e0 votre liste d''amis.", lowerCase));
        }
    }
    
    public static void cmd_part(final MyVector myVector, final NewTextDocument newTextDocument) {
        if (0 == myVector.size()) {
            myVector.insertElementAt(newTextDocument.getChanneltag(), 0);
        }
        CMD.eirc.playSound(CMD.eirc.getSound_part());
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[0] = array[0].toLowerCase();
        if (!array[0].startsWith("#")) {
            array[0] = '#' + array[0];
        }
        CHANNELS.CloseChannel(array[0]);
    }
    
    public static void cmd_partall() {
        CHANNELS.closeChannels();
    }
    
    public static void cmd_pingtime(final MyVector myVector) {
        myVector.addElement("\u0001PING " + new Date().getTime() + "\u0001");
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        CMD.eirc.sendMessage("PRIVMSG", array);
    }
    
    public static void cmd_query(final MyVector myVector) {
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        array[0] = array[0].toLowerCase();
        if (myVector.size() == 2) {
            CMD.eirc.sendMessage("PRIVMSG", array);
        }
        if (!array[0].startsWith("#")) {
            CMD.eirc.getPrivates().openPrivate(array[0], 1);
            CMD.eirc.getChat_panel().ShowPrivate(array[0]);
            if (myVector.size() == 2) {
                CMD.eirc.getPrivates().getPrivate(array[0]).printPrivmsg(array[1], CMD.eirc.getUsednick());
            }
        }
    }
    
    public static void cmd_quit(final MyVector myVector) {
        CMD.eirc.setQuit_message("Quitter");
        final String[] array = { (0 == myVector.size()) ? CMD.eirc.getQuit_message() : myVector.elementAt(0) };
        CMD.eirc.setQuit_sent(true);
        CMD.eirc.sendMessage("QUIT", array);
    }
    
    public static void cmd_quote(final MyVector myVector) {
        CMD.eirc.sendMessage((String)myVector.elementAt(0), new String[0]);
    }
    
    public static void cmd_server(final MyVector myVector) {
        final String s = (String)myVector.elementAt(0);
        if (myVector.size() > 1) {
            try {
                CMD.eirc.connect(s, Integer.parseInt((String)myVector.elementAt(1)));
            }
            catch (NumberFormatException ex) {
                CMD.eirc.connect(s, 6667);
            }
        }
        else {
            CMD.eirc.connect(s, 6667);
        }
    }
    
    public static void init(final EIRC eirc) {
        CMD.eirc = eirc;
    }
}
