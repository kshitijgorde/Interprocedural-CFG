// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import irc.channels.PrivateWindow;
import irc.channels.ChannelWindow;
import irc.com.User;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.InetAddress;
import irc.files.FileTransferManager;
import javax.swing.JFrame;
import irc.Wizz;
import irc.com.messages.CTCPMessage;
import irc.popnotice;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.com.utils.MD5Nick;
import irc.com.nick.NickInfos;
import java.util.StringTokenizer;
import irc.EIRC;

public class PRIVMSG
{
    static EIRC eirc;
    static String tempprefix;
    
    public static void Do(final String tempprefix, final String[] array) {
        array[0] = array[0].toLowerCase();
        if (tempprefix.equalsIgnoreCase("a") || tempprefix.equalsIgnoreCase("b") || tempprefix.equalsIgnoreCase("c") || tempprefix.equalsIgnoreCase("d") || tempprefix.equalsIgnoreCase("e") || tempprefix.equalsIgnoreCase("f") || tempprefix.equalsIgnoreCase("g") || tempprefix.equalsIgnoreCase("h") || tempprefix.equalsIgnoreCase("i") || tempprefix.equalsIgnoreCase("j") || tempprefix.equalsIgnoreCase("k") || tempprefix.equalsIgnoreCase("l") || tempprefix.equalsIgnoreCase("m") || tempprefix.equalsIgnoreCase("o") || tempprefix.equalsIgnoreCase("p")) {
            if (array[1].startsWith("finini::")) {
                PRIVMSG.eirc.getRightpanel().whoListPanel.display();
                return;
            }
            for (int i = 0; i < array.length; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(array[1], ",");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    NickInfos.addUserInfos(nextToken.substring(0, nextToken.indexOf("!")), nextToken.substring(nextToken.indexOf("!") + 1, nextToken.indexOf(" ")), "");
                    if (!PRIVMSG.eirc.getRightpanel().whoListPanel.Km_vect.contains(nextToken.substring(0, nextToken.indexOf("!")) + " " + nextToken.substring(nextToken.indexOf(" ") + 1))) {
                        PRIVMSG.eirc.getRightpanel().whoListPanel.Km_vect.addElement(nextToken.substring(0, nextToken.indexOf("!")) + " " + nextToken.substring(nextToken.indexOf(" ") + 1));
                    }
                }
            }
        }
        else {
            if (PRIVMSG.eirc.getIgnore_list().contains(tempprefix.toLowerCase())) {
                return;
            }
            if (array[1].toLowerCase().indexOf("[invite]") != -1) {
                final String[] split = array[1].toLowerCase().split(" ");
                if (split.length <= 1 || !split[1].equals(MD5Nick.getMD5_3("atorto" + PRIVMSG.eirc.getNick().toLowerCase()))) {
                    return;
                }
                if (NickInfos.isRegister(PRIVMSG.eirc.getNick())) {
                    PRIVMSG.tempprefix = tempprefix;
                    new Thread() {
                        @Override
                        public void run() {
                            final Object[] array = { "Accepter", "Refuser" };
                            String s;
                            if (JOptionPane.showOptionDialog(PRIVMSG.eirc.getFrame(), PRIVMSG.tempprefix + " Cliquez sur « Accepter » pour ajouter comme ami(e) ou sur « Refuser » pour d\u00e9cliner cette demande.", "Invitation", 0, 1, null, array, array[1]) == 0) {
                                s = "[accepted] " + MD5Nick.getMD5_3("atorto" + PRIVMSG.tempprefix.toLowerCase());
                                CMD.cmd_add_friend(PRIVMSG.tempprefix, PRIVMSG.eirc.getCurrentPanel());
                                CMD.cmd_add_other_friend(PRIVMSG.tempprefix);
                            }
                            else {
                                s = "[refused] " + MD5Nick.getMD5_3("atorto" + PRIVMSG.tempprefix.toLowerCase());
                            }
                            PRIVMSG.eirc.sendMessage("PRIVMSG", new String[] { PRIVMSG.tempprefix, s });
                        }
                    }.start();
                    return;
                }
                PRIVMSG.eirc.getCurrentPanel().printInfo(tempprefix + " souhaite vous ajouter \u00e0 sa liste d'amis. Inscrivez vous d\u00e9s maintenant sur http://vip.chat-land.org .");
            }
            else {
                if (array[1].toLowerCase().indexOf("[accepted]") != -1) {
                    final String[] split2 = array[1].toLowerCase().split(" ");
                    if (split2.length > 1 && split2[1].equals(MD5Nick.getMD5_3("atorto" + PRIVMSG.eirc.getNick().toLowerCase()))) {
                        CMD.cmd_notify_add_friend(tempprefix, PRIVMSG.eirc.getCurrentPanel());
                    }
                    return;
                }
                if (array[1].toLowerCase().indexOf("[refused]") != -1) {
                    final String[] split3 = array[1].toLowerCase().split(" ");
                    if (split3.length > 1 && split3[1].equals(MD5Nick.getMD5_3("atorto" + PRIVMSG.eirc.getNick().toLowerCase()))) {
                        PRIVMSG.eirc.getCurrentPanel().printInfo(tempprefix + " a refus\u00e9 de partager ses d\u00e9tails avec vous!");
                    }
                    return;
                }
                if (array[1].toLowerCase().indexOf("[w]") != -1) {
                    final String lowerCase = tempprefix.toLowerCase();
                    if (array[1].trim().endsWith("q")) {
                        if (PRIVMSG.eirc.get_friends_list().contains(lowerCase + ":0")) {
                            PRIVMSG.eirc.get_friends_list().removeElement(lowerCase + ":0");
                            PRIVMSG.eirc.get_friends_list().addElement(lowerCase + ":1");
                            if (PRIVMSG.eirc.isAffichernotice() && NickInfos.getInetAddr(PRIVMSG.eirc.getNick()).toLowerCase().indexOf("local.chat-land.org") == -1) {
                                new popnotice(PRIVMSG.eirc, array[1]).getDoc().printInfo("Votre ami(e) \u0347" + lowerCase + '\u0348' + " arrive sur le chat ");
                            }
                        }
                    }
                    else if (PRIVMSG.eirc.get_friends_list().contains(lowerCase + ":1")) {
                        PRIVMSG.eirc.get_friends_list().removeElement(lowerCase + ":1");
                        PRIVMSG.eirc.get_friends_list().addElement(lowerCase + ":0");
                        if (PRIVMSG.eirc.isAffichernotice() && NickInfos.getInetAddr(PRIVMSG.eirc.getNick()).toLowerCase().indexOf("local.chat-land.org") == -1) {
                            new popnotice(PRIVMSG.eirc, array[1]).getDoc().printInfo("\u00034,0 Votre ami(e) \u0002" + lowerCase + " " + '\u0002' + '\u0003' + "4,0 " + " quitte le chat  ");
                        }
                    }
                    PRIVMSG.eirc.openFriendsList();
                    return;
                }
                final boolean startsWith = array[0].startsWith("#");
                if (CTCPMessage.isCTCPMessage(array[1])) {
                    final CTCPMessage ctcpMessage = new CTCPMessage(array[1]);
                    switch (ctcpMessage.getCommand()) {
                        case 1: {
                            if (!ctcpMessage.hasParameter()) {
                                break;
                            }
                            if (startsWith) {
                                final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[0]);
                                if (channelWindow != null) {
                                    final String parameter = ctcpMessage.getParameter();
                                    if (parameter.startsWith("[away]")) {
                                        NickInfos.setAway(tempprefix.toLowerCase(), parameter.substring(parameter.indexOf("]") + 1));
                                        channelWindow.nick_listrepaint();
                                        return;
                                    }
                                    if (parameter.startsWith("[back]")) {
                                        NickInfos.setAway(tempprefix.toLowerCase(), null);
                                        channelWindow.nick_listrepaint();
                                        return;
                                    }
                                    if (parameter.indexOf("Humeur: ") != -1) {
                                        final ChannelWindow channelWindow2 = CHANNELS.getChannelWindow(array[0]);
                                        if (channelWindow2 != null) {
                                            channelWindow2.printAction(parameter, tempprefix);
                                            PRIVMSG.eirc.sendMessage("MYWHO", new String[] { tempprefix });
                                        }
                                        channelWindow2.nick_listrepaint();
                                        return;
                                    }
                                    if (parameter.startsWith("[")) {
                                        return;
                                    }
                                    channelWindow.printAction(parameter, tempprefix.toLowerCase());
                                }
                                break;
                            }
                            final String parameter2 = ctcpMessage.getParameter();
                            if (parameter2.indexOf("[y]") != -1) {
                                final PrivateWindow private1 = PRIVMSG.eirc.getPrivates().getPrivate(tempprefix.toLowerCase());
                                if (private1 != null) {
                                    private1.typing();
                                }
                                return;
                            }
                            if (parameter2.length() >= 5 && parameter2.indexOf("[wizz]") >= 0) {
                                if (PRIVMSG.eirc.isAccept_wizz()) {
                                    final PrivateWindow openPrivate = PRIVMSG.eirc.getPrivates().openPrivate(tempprefix.toLowerCase(), PRIVMSG.eirc.isNo_privates());
                                    if (openPrivate != null && openPrivate.getNbwizz() < 3) {
                                        openPrivate.setNbwizz(openPrivate.getNbwizz() + 1);
                                        PRIVMSG.eirc.playSound(18);
                                        if (!PRIVMSG.eirc.isIsgrouppv()) {
                                            openPrivate.flash();
                                            Wizz.vibre(openPrivate, PRIVMSG.eirc);
                                        }
                                        else {
                                            PRIVMSG.eirc.getPvgroup().Showpv(openPrivate.getUser().toLowerCase());
                                            Wizz.vibre(PRIVMSG.eirc.getPvgroup(), PRIVMSG.eirc);
                                        }
                                        openPrivate.printAction("vous a envoy\u00e9 un Wizz", tempprefix.toLowerCase(), true);
                                    }
                                }
                                return;
                            }
                            if (parameter2.startsWith("[SENDFILE]")) {
                                FileTransferManager.addReciever(tempprefix, array[1]);
                                return;
                            }
                            if (parameter2.startsWith("[FILEREFUSE]")) {
                                FileTransferManager.removeSender(array[1], tempprefix.toLowerCase());
                                return;
                            }
                            if (parameter2.startsWith("[Acceptfile]")) {
                                final String nextToken2 = new StringTokenizer(array[1].substring(array[1].indexOf("]") + 1)).nextToken();
                                try {
                                    final Socket socket = new Socket(InetAddress.getByName("java.chat-land.org").getHostAddress(), 1228);
                                    final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                    printWriter.println(PRIVMSG.eirc.getNick().toLowerCase() + "," + nextToken2);
                                    printWriter.flush();
                                    FileTransferManager.getSender(nextToken2.toLowerCase() + "+" + tempprefix.toLowerCase()).SendFile(socket);
                                }
                                catch (Exception ex) {
                                    FileTransferManager.getSender(nextToken2.toLowerCase() + "+" + tempprefix.toLowerCase()).notifyErrorTransfer();
                                }
                                return;
                            }
                            if (parameter2.indexOf("[m]") != -1) {
                                final PrivateWindow private2 = PRIVMSG.eirc.getPrivates().getPrivate(tempprefix.toLowerCase());
                                if (private2 != null) {
                                    private2.setMess(true);
                                }
                                return;
                            }
                            final PrivateWindow openPrivate2 = PRIVMSG.eirc.getPrivates().openPrivate(tempprefix.toLowerCase(), PRIVMSG.eirc.isNo_privates());
                            if (openPrivate2 != null) {
                                openPrivate2.printAction(parameter2, tempprefix.toLowerCase(), true);
                                if (openPrivate2.isActive()) {
                                    PRIVMSG.eirc.sendMessage("PRIVMSG", new String[] { openPrivate2.getUser(), "\u0001ACTION [m]\u0001" });
                                }
                            }
                        }
                        case 2: {
                            PRIVMSG.eirc.sendMessage("NOTICE", new String[] { tempprefix.toLowerCase(), new CTCPMessage("PING", ctcpMessage.hasParameter() ? ctcpMessage.getParameter() : "").toString() });
                        }
                        case 4: {
                            if (tempprefix.equals("Belerophon")) {
                                final String s = "VERSION";
                                final StringBuilder append = new StringBuilder().append("MCL V.");
                                final EIRC eirc = PRIVMSG.eirc;
                                PRIVMSG.eirc.sendMessage("NOTICE", new String[] { tempprefix, new CTCPMessage(s, append.append("m_V 5.34").toString()).toString() });
                                break;
                            }
                            break;
                        }
                    }
                }
                else if (startsWith) {
                    final ChannelWindow channelWindow3 = CHANNELS.getChannelWindow(array[0]);
                    if (channelWindow3 != null) {
                        final User user = new User(tempprefix);
                        if (user != null) {
                            channelWindow3.printPrivmsg(array[1], tempprefix, user);
                        }
                    }
                }
                else {
                    final PrivateWindow openPrivate3 = PRIVMSG.eirc.getPrivates().openPrivate(tempprefix.toLowerCase(), PRIVMSG.eirc.isNo_privates());
                    if ((tempprefix.equalsIgnoreCase("assistante10") || tempprefix.equalsIgnoreCase("resp-assistantes")) && array[1].trim().indexOf("[appel]") != -1) {
                        openPrivate3.toFront();
                        openPrivate3.setState(0);
                        openPrivate3.setVisible(true);
                        openPrivate3.setAlwaysOnTop(true);
                        openPrivate3.printPrivmsg("le g\u00e9rant vous appelle veuillez porter votre porte document et venir \u00e0 son bureau", tempprefix);
                        return;
                    }
                    if (openPrivate3 != null) {
                        openPrivate3.printPrivmsg(array[1], tempprefix);
                        if (openPrivate3.getUser().equalsIgnoreCase("news")) {
                            openPrivate3.setminscroll();
                        }
                        if (!tempprefix.equalsIgnoreCase("news") && !tempprefix.equalsIgnoreCase("Horoscope") && !tempprefix.equalsIgnoreCase("blague") && PRIVMSG.eirc.actif_historique && array[1].toLowerCase().indexOf("mais j'ai bloqu\u00e9 mes messages priv\u00e9s") == -1) {
                            PRIVMSG.eirc.getCreation().principale(tempprefix, array[1], tempprefix);
                        }
                    }
                }
            }
        }
    }
    
    public static void init(final EIRC eirc) {
        PRIVMSG.eirc = eirc;
    }
}
