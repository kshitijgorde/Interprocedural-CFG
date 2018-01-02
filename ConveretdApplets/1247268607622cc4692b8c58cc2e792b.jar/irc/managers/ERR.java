// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.Random;
import irc.channels.PrivateWindow;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.channels.ChannelWindow;
import java.text.MessageFormat;
import irc.EIRC;

public class ERR
{
    static EIRC eirc;
    
    public static void ERR_ALREADYREGISTRED(final String s, final String[] array) {
    }
    
    public static void ERR_ATTACKDENY(final String s, final String[] array) {
    }
    
    public static void ERR_BADCHANMASK(final String s, final String[] array) {
    }
    
    public static void ERR_BADCHANNELKEY(final String s, final String[] array) {
    }
    
    public static void ERR_BANLISTFULL(final String s, final String[] array) {
    }
    
    public static void ERR_BANNEDFROMCHAN(final String s, final String[] array) {
        ERR.eirc.getCurrentPanel().printError("Vous ne pouvez pas joindre le salon " + array[1] + " vous \u00eates Banni.");
    }
    
    public static void ERR_BANNICKCHANGE(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOT_INVITE_AT_CHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOT_JOIN_CHANNEL_ADMINONLY(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOT_JOIN_CHANNEL_IRCOPONLY(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOTCHANGECHANMODE(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOTDOCOMMAND(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOTKNOCK(final String s, final String[] array) {
    }
    
    public static void ERR_CANNOTSENDTOCHAN(final String s, final String[] array) {
        final Object[] array2 = { array[2] };
        if (array[2].trim().toLowerCase().indexOf("you are banned") != -1) {
            ERR.eirc.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.404") + " du salon " + array[1], array2));
        }
    }
    
    public static void ERR_CANTKILLSERVER(final String s, final String[] array) {
    }
    
    public static void ERR_CHANNELISFULL(final String s, final String[] array) {
    }
    
    public static void ERR_CHANOPRIVSNEEDED(final String s, final String[] array) {
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow != null) {
            channelWindow.getDoc().printError("Vous n'\u00eates pas animateur sur le salon " + array[1] + " pour le devenir tapez cette commande : /nm postule animateur ou gamer ou helper #salon motivation ");
        }
        else {
            ERR.eirc.getCurrentPanel().printError("Vous n'\u00eates pas animateur sur le salon " + array[1] + " pour le devenir tapez cette commande : /nm postule animateur ou gamer ou helper #salon motivation ");
        }
    }
    
    public static void ERR_CHANOWNPRIVNEEDED(final String s, final String[] array) {
    }
    
    public static void ERR_DISABLED(final String s, final String[] array) {
    }
    
    public static void ERR_ERRONEUSNICKNAME(final String s, final String[] array) {
        ERR.eirc.SendErrorToMaster(s, array);
        JOptionPane.showMessageDialog(ERR.eirc.getAccueil().Application, "Votre pseudo est erron\u00e9 veuillez en saisir un autre.");
        ERR.eirc.setReallydisconnected(true);
        ERR.eirc.logout();
    }
    
    public static void ERR_FILEERROR(final String s, final String[] array) {
    }
    
    public static void ERR_HOSTILENAME(final String s, final String[] array) {
    }
    
    public static void ERR_HTMDISABLED(final String s, final String[] array) {
    }
    
    public static void ERR_INVITEONLYCHAN(final String s, final String[] array) {
    }
    
    public static void ERR_KEYSET(final String s, final String[] array) {
    }
    
    public static void ERR_KILLDENY(final String s, final String[] array) {
    }
    
    public static void ERR_LINKCHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_LINKFAIL(final String s, final String[] array) {
    }
    
    public static void ERR_LINKSET(final String s, final String[] array) {
    }
    
    public static void ERR_LISTSYNTAX(final String s, final String[] array) {
    }
    
    public static void ERR_MYNOSUCHNICK(final String s, final String[] array) {
        final String s2 = "No such nick/channel";
        if (array[1].startsWith("#")) {
            return;
        }
        if (array[1].equalsIgnoreCase("test2")) {
            final String[] array2 = { "GoodLife", "Bot sajoin java est absent" };
            ERR.eirc.sendMessage("PRIVMSG", array2);
            array2[0] = "lesly";
            ERR.eirc.sendMessage("PRIVMSG", array2);
            return;
        }
        if (array[1].equalsIgnoreCase("goodlife") || array[1].equalsIgnoreCase("lesly")) {
            return;
        }
        if (array[1].equalsIgnoreCase("a")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "b", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("b")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "c", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("c")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "d", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("d")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "e", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("e")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "f", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("f")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "g", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("g")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "h", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("h")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "i", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("i")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "j", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("j")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "k", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("k")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "l", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("l")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "m", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("m")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "n", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("n")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "o", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("o")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "p", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[1].equalsIgnoreCase("p")) {
            ERR.eirc.getRightpanel().whoListPanel.Km_vect.removeAllElements();
            ERR.eirc.sendMessage("PRIVMSG", new String[] { "a", array[2].substring(s2.length()).trim() });
            return;
        }
        if (array[2].indexOf("No such nick/channel") != -1 && (array[2].indexOf("[y]") != -1 || array[2].indexOf("[PhotoChange]") != -1 || array[2].indexOf("[mp]") != -1 || array[2].indexOf("[accepted]") != -1 || array[2].indexOf("[refused]") != -1 || array[2].indexOf("[wizz]") != -1 || array[2].indexOf("[b]") != -1 || array[2].indexOf("[w]") != -1 || array[2].indexOf("[m]") != -1)) {
            return;
        }
        final PrivateWindow openPrivate = ERR.eirc.getPrivates().openPrivate(array[1].toLowerCase(), false);
        if (openPrivate.isConnecter()) {
            openPrivate.printPrivmsg(array[1] + " n'est pas sur le chat ou il a rencontr\u00e9 des probl\u00e9mes de connexion", "Chat-land");
            openPrivate.printPrivmsg("Gr\u00e2ce au service de la messagerie diff\u00e9r\u00e9e de chat-land,votre correspondant recevera vos messages lors de sa prochaine connexion .", "Chat-land");
            openPrivate.setConnecter(false);
        }
        if (array.length > 2 && array[2].indexOf("No such nick/channel") != -1 && array[2].indexOf("[y]") == -1 && array[2].indexOf("[PhotoChange]") == -1 && array[2].indexOf("[mp]") == -1 && array[2].indexOf("[accepted]") == -1 && array[2].indexOf("[refused]") == -1 && array[2].indexOf("[wizz]") == -1 && array[2].indexOf("[b]") == -1 && array[2].indexOf("[w]") == -1 && array[2].indexOf("[m]") == -1) {
            CONF.SetMessaged(ERR.eirc.getNick(), array[1], array[2].substring(array[2].indexOf("No such nick/channel") + 20));
        }
        if (array[2].indexOf("[PhotoChange]") != -1) {
            return;
        }
        if (array[2].indexOf("[mp]") != -1) {
            return;
        }
    }
    
    public static void ERR_NCHANGETOOFAST(final String s, final String[] array) {
    }
    
    public static void ERR_NEEDMOREPARAMS(final String s, final String[] array) {
    }
    
    public static void ERR_NEEDPONG(final String s, final String[] array) {
    }
    
    public static void ERR_NEEDREGGEDNICK(final String s, final String[] array) {
        ERR.eirc.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.477"), array[1]));
    }
    
    public static void ERR_NICKCOLLISION(final String s, final String[] array) {
    }
    
    public static void ERR_NICKNAMEINUSE(final String s, final String[] array) {
        new Thread() {
            @Override
            public void run() {
                final EIRC eirc = ERR.eirc;
                String s = null;
                Label_0044: {
                    if (EIRC.IDD != null) {
                        final EIRC eirc2 = ERR.eirc;
                        if (EIRC.IDD.length() >= 8) {
                            final EIRC eirc3 = ERR.eirc;
                            s = EIRC.IDD.substring(0, 8);
                            break Label_0044;
                        }
                    }
                    s = getRandom8CH();
                }
                final String s2 = s;
                final String[] array = { "Ghost-" + ERR.eirc.getRealnick() + "-" + s2 };
                ERR.eirc.setUsednick("Ghost-" + ERR.eirc.getRealnick() + "-" + s2);
                ERR.eirc.sendMessage("NICK", array);
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
                ERR.eirc.setKillmyghost(true);
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex2) {}
                final String[] array2 = { "NickServ", "ghost " + ERR.eirc.getRealnick() + " " + ERR.eirc.getNicksrv_pass() };
                final String[] array3 = { "NickServ", "release " + ERR.eirc.getRealnick() + " " + ERR.eirc.getNicksrv_pass() };
                ERR.eirc.sendMessage("PRIVMSG", array2);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex3) {}
                ERR.eirc.sendMessage("PRIVMSG", array3);
            }
        }.start();
    }
    
    public static void ERR_NOADMININFO(final String s, final String[] array) {
    }
    
    public static void ERR_NOCTCP(final String s, final String[] array) {
    }
    
    public static void ERR_NOHIDING(final String s, final String[] array) {
    }
    
    public static void ERR_NOLOGIN(final String s, final String[] array) {
    }
    
    public static void ERR_NOMOTD(final String s, final String[] array) {
    }
    
    public static void ERR_NONICKCHANGE(final String s, final String[] array) {
        ERR.eirc.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.nickchange"), array[1]));
    }
    
    public static void ERR_NONICKNAMEGIVEN(final String s, final String[] array) {
    }
    
    public static void ERR_NONONREG(final String s, final String[] array) {
    }
    
    public static void ERR_NOOPERHOST(final String s, final String[] array) {
        ERR.eirc.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.491"), array[1]));
    }
    
    public static void ERR_NOOPERMOTD(final String s, final String[] array) {
    }
    
    public static void ERR_NOORIGIN(final String s, final String[] array) {
    }
    
    public static void ERR_NOPERMFORHOST(final String s, final String[] array) {
    }
    
    public static void ERR_NOPRIVILEGES(final String s, final String[] array) {
    }
    
    public static void ERR_NORECIPIENT(final String s, final String[] array) {
    }
    
    public static void ERR_NORULES(final String s, final String[] array) {
    }
    
    public static void ERR_NOSUCHCHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_NOSUCHNICK(final String s, final String[] array) {
        final PrivateWindow private1 = ERR.eirc.getPrivates().getPrivate(array[1].toLowerCase());
        if (private1 != null) {
            private1.getDoc().printWarning('\u0002' + array[1] + " est hors ligne.");
        }
    }
    
    public static void ERR_NOSUCHSERVER(final String s, final String[] array) {
    }
    
    public static void ERR_NOSWEAR(final String s, final String[] array) {
    }
    
    public static void ERR_NOTEXTTOSEND(final String s, final String[] array) {
    }
    
    public static void ERR_NOTFORHALFOPS(final String s, final String[] array) {
    }
    
    public static void ERR_NOTFORUSERS(final String s, final String[] array) {
    }
    
    public static void ERR_NOTONCHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_NOTOPLEVEL(final String s, final String[] array) {
    }
    
    public static void ERR_NOTREGISTERED(final String s, final String[] array) {
    }
    
    public static void ERR_NUMERICERR(final String s, final String[] array) {
    }
    
    public static void ERR_ONLYSERVERSCANCHANGE(final String s, final String[] array) {
    }
    
    public static void ERR_OPERSPVERIFY(final String s, final String[] array) {
    }
    
    public static void ERR_PASSWDMISMATCH(final String s, final String[] array) {
    }
    
    public static void ERR_SECUREONLYCHAN(final String s, final String[] array) {
    }
    
    public static void ERR_SERVICESDOWN(final String s, final String[] array) {
    }
    
    public static void ERR_SILELISTFULL(final String s, final String[] array) {
    }
    
    public static void ERR_SUMMONDISABLED(final String s, final String[] array) {
    }
    
    public static void ERR_TARGETTOOFAST(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYAWAY(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYCHANNELS(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYDCC(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYJOINS(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYTARGETS(final String s, final String[] array) {
    }
    
    public static void ERR_TOOMANYWATCH(final String s, final String[] array) {
    }
    
    public static void ERR_UMODEUNKNOWNFLAG(final String s, final String[] array) {
    }
    
    public static void ERR_UNKNOWNCOMMAND(final String s, final String[] array) {
    }
    
    public static void ERR_UNKNOWNMODE(final String s, final String[] array) {
    }
    
    public static void ERR_USERNOTINCHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_USERONCHANNEL(final String s, final String[] array) {
    }
    
    public static void ERR_USERSDISABLED(final String s, final String[] array) {
    }
    
    public static void ERR_USERSDONTMATCH(final String s, final String[] array) {
    }
    
    public static void ERR_WASNOSUCHNICK(final String s, final String[] array) {
        ERR.eirc.getCurrentPanel().printError(array[1] + " n'\u00e9tait pas sur le chat");
    }
    
    public static void ERR_WHOLIMEXCEED(final String s, final String[] array) {
    }
    
    public static void ERR_WHOSYNTAX(final String s, final String[] array) {
    }
    
    public static void ERR_WILDTOPLEVEL(final String s, final String[] array) {
    }
    
    public static void ERR_YOUREBANNEDCREEP(final String s, final String[] array) {
    }
    
    private static String getRandom8CH() {
        final String string = new Long(new Random().nextLong()).toString();
        if (string.length() >= 8) {
            return string.substring(0, 7);
        }
        return string;
    }
    
    public static void init(final EIRC eirc) {
        ERR.eirc = eirc;
    }
}
