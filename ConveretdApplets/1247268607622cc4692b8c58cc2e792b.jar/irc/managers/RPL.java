// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import irc.com.messages.MircMessage;
import irc.popnotice;
import irc.com.nick.NickInfos;
import java.text.MessageFormat;
import java.util.StringTokenizer;
import irc.channels.ChannelWindow;
import java.util.Date;
import irc.EIRC;

public class RPL
{
    static EIRC eirc;
    
    public static void init(final EIRC eirc) {
        RPL.eirc = eirc;
    }
    
    public static void RPL_ADMINEMAIL(final String s, final String[] array) {
    }
    
    public static void RPL_ADMINLOC1(final String s, final String[] array) {
    }
    
    public static void RPL_ADMINLOC2(final String s, final String[] array) {
    }
    
    public static void RPL_ADMINME(final String s, final String[] array) {
    }
    
    public static void RPL_ALIST(final String s, final String[] array) {
    }
    
    public static void RPL_AWAY(final String s, final String[] array) {
    }
    
    public static void RPL_BANLIST(final String s, final String[] array) {
        RPL.eirc.listAdd(array[2], array[3], new Date(Long.parseLong(array[4]) * 1000L));
    }
    
    public static void RPL_CHANNELMODEIS(final String s, final String[] array) {
        final String[] array2 = new String[array.length - 3];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = array[i + 3];
        }
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow != null) {
            channelWindow.setModes(array[2], array2);
        }
    }
    
    public static void RPL_CLOSEEND(final String s, final String[] array) {
    }
    
    public static void RPL_CLOSING(final String s, final String[] array) {
    }
    
    public static void RPL_CREATED(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + array[i] + " ";
            }
            RPL.eirc.getCurrentPanel().printInfo('\u0002' + string);
        }
    }
    
    public static void RPL_CREATIONTIME(final String s, final String[] array) {
    }
    
    public static void RPL_DCCINFO(final String s, final String[] array) {
    }
    
    public static void RPL_DCCLIST(final String s, final String[] array) {
    }
    
    public static void RPL_DCCSTATUS(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFALIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFBANLIST(final String s, final String[] array) {
        RPL.eirc.listEnd(array[1], 'b');
    }
    
    public static void RPL_ENDOFDCCLIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFEXLIST(final String s, final String[] array) {
        RPL.eirc.listEnd(array[1], 'e');
    }
    
    public static void RPL_ENDOFINFO(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFINVEXLIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFINVITELIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFLINKS(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFMOTD(final String s, final String[] array) {
        RPL.eirc.pos_login();
    }
    
    public static void RPL_ENDOFNAMES(final String s, final String[] array) {
        if (array[1].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[1].substring(1)) != -1) {
            RPL.eirc.getRightpanel().whoListPanel.enableReorder();
        }
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow != null) {
            channelWindow.enableReorder();
        }
    }
    
    public static void RPL_ENDOFQLIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFRULES(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFSILELIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFSTATS(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFUSERS(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFWATCHLIST(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFWHO(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFWHOIS(final String s, final String[] array) {
    }
    
    public static void RPL_ENDOFWHOWAS(final String s, final String[] array) {
    }
    
    public static void RPL_EXLIST(final String s, final String[] array) {
        RPL.eirc.listAdd(array[2], array[3], new Date(Long.parseLong(array[4]) * 1000L));
    }
    
    public static void RPL_GLOBALUSERS(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1]);
        }
    }
    
    public static void RPL_HELPFWD(final String s, final String[] array) {
    }
    
    public static void RPL_HELPIGN(final String s, final String[] array) {
    }
    
    public static void RPL_INFO(final String s, final String[] array) {
    }
    
    public static void RPL_INFOSTART(final String s, final String[] array) {
    }
    
    public static void RPL_INVEXLIST(final String s, final String[] array) {
    }
    
    public static void RPL_INVITELIST(final String s, final String[] array) {
    }
    
    public static void RPL_INVITING(final String s, final String[] array) {
    }
    
    public static void RPL_ISON(final String s, final String[] array) {
        final String s2 = array[1];
        if (s2.length() == 0) {
            RPL.eirc.getCurrentPanel().printInfo(Resources.getStringEirc("eirc.isoff"));
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ");
            while (stringTokenizer.hasMoreTokens()) {
                RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.ison"), stringTokenizer.nextToken()));
            }
        }
    }
    
    public static void RPL_ISUPPORT(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + array[i] + " ";
            }
            RPL.eirc.getCurrentPanel().printInfo('\u0002' + string);
        }
    }
    
    public static void RPL_LINKS(final String s, final String[] array) {
    }
    
    public static void RPL_LIST(final String s, final String[] array) {
        if (array[1].length() == 2 && "abcdefghijklmnopqrstuvwxyz123456".indexOf(array[1].substring(1).toLowerCase()) != -1) {
            return;
        }
        if (!array[1].equals("*") && !array[1].toLowerCase().startsWith("#applet")) {
            boolean b = false;
            if (array.length > 3 && array[3].startsWith("[") && array[3].indexOf("]") != -1 && array[3].substring(0, array[3].indexOf("]")).indexOf("C") != -1) {
                b = true;
            }
            RPL.eirc.getRightpanel().chanListPanel.listAdd(array[1], Integer.parseInt(array[2]), b);
        }
    }
    
    public static void RPL_LISTEND(final String s, final String[] array) {
        RPL.eirc.getRightpanel().chanListPanel.listEnd();
    }
    
    public static void RPL_LISTSTART(final String s, final String[] array) {
        RPL.eirc.getRightpanel().chanListPanel.listStart();
    }
    
    public static void RPL_LISTSYNTAX(final String s, final String[] array) {
    }
    
    public static void RPL_LOCALUSERS(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1]);
        }
    }
    
    public static void RPL_LOGOFF(final String s, final String[] array) {
        final String lowerCase = array[1].toLowerCase();
        if (RPL.eirc.get_friends_list().contains(lowerCase + ":1")) {
            RPL.eirc.get_friends_list().removeElement(lowerCase + ":1");
            RPL.eirc.get_friends_list().addElement(lowerCase + ":0");
            final Object[] array2 = { new Date(Long.parseLong(array[4]) * 1000L) };
            if (RPL.eirc.isAffichernotice() && NickInfos.getInetAddr(RPL.eirc.getNick()).toLowerCase().indexOf("local.chat-land.org") == -1) {
                new popnotice(RPL.eirc, array[1]).getDoc().printInfo("\u00034,0 Votre ami(e) \u0002" + array[1] + " " + '\u0002' + '\u0003' + "4,0 " + " quitte le chat  " + MessageFormat.format(Resources.getStringEirc("eirc.time"), array2));
            }
            RPL.eirc.openFriendsList();
        }
        if (RPL.eirc.getPrivates().privates.containsKey(array[1].toLowerCase())) {
            RPL.eirc.getPrivates().getPrivate(array[1].toLowerCase()).getDoc().printWarning('\u0002' + array[1] + " est hors ligne.");
        }
    }
    
    public static void RPL_LOGON(final String s, final String[] array) {
        final Date date = new Date(Long.parseLong(array[4]) * 1000L);
        final String lowerCase = array[1].toLowerCase();
        NickInfos.updateInfos(array[1], array[2], array[3]);
        if (RPL.eirc.get_friends_list().contains(lowerCase + ":0")) {
            RPL.eirc.get_friends_list().removeElement(lowerCase + ":0");
            RPL.eirc.get_friends_list().addElement(lowerCase + ":1");
            final Object[] array2 = { date };
            if (RPL.eirc.isAffichernotice() && NickInfos.getInetAddr(RPL.eirc.getNick()).toLowerCase().indexOf("local.chat-land.org") == -1) {
                new popnotice(RPL.eirc, array[1]).getDoc().printInfo("Votre ami(e) \u0347" + array[1] + '\u0348' + " arrive sur le chat " + MessageFormat.format(Resources.getStringEirc("eirc.time"), array2));
            }
            RPL.eirc.openFriendsList();
        }
        if (RPL.eirc.getPrivates().privates.containsKey(array[1].toLowerCase())) {
            RPL.eirc.getPrivates().getPrivate(array[1].toLowerCase()).getDoc().printWarning('\u0002' + array[1] + " est en ligne.");
        }
    }
    
    public static void RPL_LUSERCHANNELS(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1] + " " + array[2]);
        }
    }
    
    public static void RPL_LUSERCLIENT(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1]);
        }
    }
    
    public static void RPL_LUSERME(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1]);
        }
    }
    
    public static void RPL_LUSEROP(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1] + " " + array[2]);
        }
    }
    
    public static void RPL_LUSERUNKNOWN(final String s, final String[] array) {
    }
    
    public static void RPL_MAP(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            String s2 = "";
            for (int i = 0; i < array[1].length(); ++i) {
                if (array[1].charAt(i) == ' ') {
                    s2 += "-";
                }
                else {
                    s2 += array[1].charAt(i);
                }
            }
            RPL.eirc.getCurrentPanel().printInfo(s2);
        }
    }
    
    public static void RPL_MAPEND(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            RPL.eirc.getCurrentPanel().printInfo(array[1]);
        }
    }
    
    public static void RPL_MAPMORE(final String s, final String[] array) {
    }
    
    public static void RPL_MOTD(final String s, final String[] array) {
    }
    
    public static void RPL_MOTDSTART(final String s, final String[] array) {
    }
    
    public static void RPL_MPERSO(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.645"), array[1], array[2]));
    }
    
    public static void RPL_MYINFO(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + array[i] + " ";
            }
            RPL.eirc.getCurrentPanel().printInfo('\u0002' + string);
        }
        RPL.eirc.lancermessenger();
    }
    
    public static void RPL_MYPORTIS(final String s, final String[] array) {
    }
    
    public static void RPL_MYWHOREPLY(final String s, final String[] array) {
    }
    
    public static void RPL_NAMREPLY(final String s, final String[] array) {
        if (array[2].length() == 2) {
            if ("abcdefghijklmnopqrstuvwxyz123456".indexOf(array[2].substring(1)) != -1) {
                final StringTokenizer stringTokenizer = new StringTokenizer(array[3], " ");
                for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                    final String nextToken = stringTokenizer.nextToken();
                    String s3;
                    final String s2 = s3 = nextToken.substring(0, nextToken.indexOf("!"));
                    final String substring = nextToken.substring(nextToken.indexOf("!") + 1);
                    if (s2.charAt(0) == '~' || s2.charAt(0) == '&' || s2.charAt(0) == '@' || s2.charAt(0) == '%' || s2.charAt(0) == '+') {
                        s3 = s2.substring(1);
                    }
                    NickInfos.updateInfos(s3, substring, "");
                    RPL.eirc.getRightpanel().whoListPanel.add(s2);
                }
            }
            return;
        }
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[2]);
        if (channelWindow == null) {
            return;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(array[3], " ");
        for (int countTokens2 = stringTokenizer2.countTokens(), j = 0; j < countTokens2; ++j) {
            final String nextToken2 = stringTokenizer2.nextToken();
            String s5;
            final String s4 = s5 = nextToken2.substring(0, nextToken2.indexOf("!"));
            final String substring2 = nextToken2.substring(nextToken2.indexOf("!") + 1);
            if (s4.charAt(0) == '~' || s4.charAt(0) == '&' || s4.charAt(0) == '@' || s4.charAt(0) == '%' || s4.charAt(0) == '+') {
                s5 = s4.substring(1);
            }
            NickInfos.updateInfos(s5, substring2, "");
            channelWindow.add(s4);
        }
    }
    
    public static void RPL_NOTOPIC(final String s, final String[] array) {
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow != null) {
            channelWindow.setTopic("");
        }
        final ChannelWindow channelWindow2 = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow2 == null) {
            RPL.eirc.getCurrentPanel();
        }
        else if (channelWindow2 instanceof ChannelWindow) {
            channelWindow2.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s37"), array[1]));
        }
    }
    
    public static void RPL_NOUSERS(final String s, final String[] array) {
    }
    
    public static void RPL_NOWAWAY(final String s, final String[] array) {
        RPL.eirc.setIs_away(true);
        RPL.eirc.getControl_menu().switch_away_button();
    }
    
    public static void RPL_NOWMPERSO(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(array[1]);
    }
    
    public static void RPL_NOWOFF(final String s, final String[] array) {
        final String lowerCase = array[1].toLowerCase();
        if (RPL.eirc.get_friends_list().contains(lowerCase + ":1")) {
            RPL.eirc.get_friends_list().removeElement(lowerCase + ":1");
            RPL.eirc.get_friends_list().addElement(lowerCase + ":0");
            RPL.eirc.openFriendsList();
        }
    }
    
    public static void RPL_NOWON(final String s, final String[] array) {
        NickInfos.updateInfos(array[1], array[2], array[3]);
        final String lowerCase = array[1].toLowerCase();
        if (RPL.eirc.get_friends_list().contains(lowerCase + ":0")) {
            RPL.eirc.get_friends_list().removeElement(lowerCase + ":0");
            RPL.eirc.get_friends_list().addElement(lowerCase + ":1");
            RPL.eirc.openFriendsList();
        }
    }
    
    public static void RPL_QLIST(final String s, final String[] array) {
    }
    
    public static void RPL_REDIR(final String s, final String[] array) {
    }
    
    public static void RPL_REHASHING(final String s, final String[] array) {
    }
    
    public static void RPL_REMOTEISUPPORT(final String s, final String[] array) {
    }
    
    public static void RPL_RULES(final String s, final String[] array) {
    }
    
    public static void RPL_RULESSTART(final String s, final String[] array) {
    }
    
    public static void RPL_SILELIST(final String s, final String[] array) {
    }
    
    public static void RPL_SNOMASK(final String s, final String[] array) {
    }
    
    public static void RPL_SPAMCMDFWD(final String s, final String[] array) {
    }
    
    public static void RPL_SQLINE_NICK(final String s, final String[] array) {
    }
    
    public static void RPL_STATSBANVER(final String s, final String[] array) {
    }
    
    public static void RPL_STATSBLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSCLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSCOMMANDS(final String s, final String[] array) {
    }
    
    public static void RPL_STATSCONN(final String s, final String[] array) {
    }
    
    public static void RPL_STATSDLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSELINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSEXCEPTTKL(final String s, final String[] array) {
    }
    
    public static void RPL_STATSGLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSHELP(final String s, final String[] array) {
    }
    
    public static void RPL_STATSHLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSILINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSKLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSLLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSNLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSOLDNLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSOLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSQLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSSLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSSPAMF(final String s, final String[] array) {
    }
    
    public static void RPL_STATSTLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSULINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSUPTIME(final String s, final String[] array) {
    }
    
    public static void RPL_STATSVLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSXLINE(final String s, final String[] array) {
    }
    
    public static void RPL_STATSYLINE(final String s, final String[] array) {
    }
    
    public static void RPL_SUMMONING(final String s, final String[] array) {
    }
    
    public static void RPL_TIME(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.391"), array[1], array[2]));
    }
    
    public static void RPL_TOPIC(final String s, final String[] array) {
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow != null) {
            channelWindow.setTopic(MircMessage.filterMircAttributes(array[2]));
        }
        final ChannelWindow channelWindow2 = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow2 == null) {
            return;
        }
        if (channelWindow2 instanceof ChannelWindow) {
            channelWindow2.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s38"), array[1], array[2]));
        }
    }
    
    public static void RPL_TOPICWHOTIME(final String s, final String[] array) {
        final Date date = new Date(Long.parseLong(array[3]) * 1000L);
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[1]);
        if (channelWindow == null) {
            return;
        }
        if (channelWindow instanceof ChannelWindow) {
            channelWindow.printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s35"), array[2], date));
        }
    }
    
    public static void RPL_TRACECLASS(final String s, final String[] array) {
    }
    
    public static void RPL_TRACECONNECTING(final String s, final String[] array) {
    }
    
    public static void RPL_TRACEHANDSHAKE(final String s, final String[] array) {
    }
    
    public static void RPL_TRACELINK(final String s, final String[] array) {
    }
    
    public static void RPL_TRACELOG(final String s, final String[] array) {
    }
    
    public static void RPL_TRACENEWTYPE(final String s, final String[] array) {
    }
    
    public static void RPL_TRACEOPERATOR(final String s, final String[] array) {
    }
    
    public static void RPL_TRACESERVER(final String s, final String[] array) {
    }
    
    public static void RPL_TRACESERVICE(final String s, final String[] array) {
    }
    
    public static void RPL_TRACEUNKNOWN(final String s, final String[] array) {
    }
    
    public static void RPL_TRACEUSER(final String s, final String[] array) {
    }
    
    public static void RPL_UMODEIS(final String s, final String[] array) {
    }
    
    public static void RPL_UNAWAY(final String s, final String[] array) {
    }
    
    public static void RPL_UNMPERSO(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(array[1]);
    }
    
    public static void RPL_USERHOST(final String s, final String[] array) {
        final String s2 = array[1];
        if (s2.length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf("=");
                String s3 = nextToken.substring(0, index);
                if (s3.endsWith("*")) {
                    s3 = s3.substring(0, s3.length() - 1);
                    RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s16"), s3));
                }
                if (nextToken.charAt(index + 1) == '-') {
                    RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.302.away"), s3));
                }
                RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.302.host"), s3, nextToken.substring(index + 2)));
            }
        }
    }
    
    public static void RPL_USERIP(final String s, final String[] array) {
    }
    
    public static void RPL_USERS(final String s, final String[] array) {
    }
    
    public static void RPL_USERSSTART(final String s, final String[] array) {
    }
    
    public static void RPL_VERSION(final String s, final String[] array) {
    }
    
    public static void RPL_WATCHLIST(final String s, final String[] array) {
    }
    
    public static void RPL_WATCHOFF(final String s, final String[] array) {
    }
    
    public static void RPL_WATCHSTAT(final String s, final String[] array) {
    }
    
    public static void RPL_WELCOME(final String s, final String[] array) {
        if (RPL.eirc.isKillmyghost()) {
            RPL.eirc.KillMyGhost();
            RPL.eirc.setKillmyghost(false);
        }
        if (RPL.eirc.isShowall()) {
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + array[i] + " ";
            }
            RPL.eirc.getCurrentPanel().printInfo('\u0002' + string);
        }
    }
    
    public static void RPL_WHOISBOT(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.335"), array[1], array[2]));
    }
    
    public static void RPL_WHOISCHANNELS(final String s, final String[] array) {
        String string = "";
        final String s2 = "#a,#b,#c,#d,#e,#f,#g,#h,#i,#j,#k,#l,#m,#n,#o,#p,#q,#r,#s,#t,#u,#v,#w,#x,#y,#z,#1,#2,#3,#4,#5,#6";
        final StringTokenizer stringTokenizer = new StringTokenizer(array[2], " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (s2.indexOf(nextToken.toLowerCase()) == -1) {
                string = string + nextToken + " ";
            }
        }
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s18"), array[1], string));
    }
    
    public static void RPL_WHOISHELPOP(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.310"), array[1], array[2]));
    }
    
    public static void RPL_WHOISHOST(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.378"), array[1], array[2]));
    }
    
    public static void RPL_WHOISIDLE(final String s, final String[] array) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        try {
            n = Integer.parseInt(array[2]) / 3600;
            n2 = Integer.parseInt(array[2]) % 3600 / 60;
            n3 = Integer.parseInt(array[2]) % 3600 % 60;
        }
        catch (NumberFormatException ex) {}
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.317"), array[1], new Integer(n), new Integer(n2), new Integer(n3)));
        if (array.length >= 4) {
            RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.317.signon"), array[1], new Date(Long.parseLong(array[3]) * 1000L)));
        }
    }
    
    public static void RPL_WHOISMODES(final String s, final String[] array) {
    }
    
    public static void RPL_WHOISOPERATOR(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s16"), array[1]));
    }
    
    public static void RPL_WHOISREGNICK(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s27"), array[1]));
    }
    
    public static void RPL_WHOISSECURE(final String s, final String[] array) {
    }
    
    public static void RPL_WHOISSERVER(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s15"), array[1], array[2], array[3]));
    }
    
    public static void RPL_WHOISSPECIAL(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.320"), array[1], array[2]));
    }
    
    public static void RPL_WHOISUSER(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.s13"), array[1], array[2], array[3]));
    }
    
    public static void RPL_WHOREPLY(final String s, final String[] array) {
    }
    
    public static void RPL_WHOWASUSER(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(MessageFormat.format(Resources.getStringEirc("eirc.314"), array[1], array[2], array[3], array[5]));
    }
    
    public static void RPL_YOUREOPER(final String s, final String[] array) {
        RPL.eirc.getCurrentPanel().printInfo(Resources.getStringEirc("eirc.381"));
    }
    
    public static void RPL_YOURHOST(final String s, final String[] array) {
        if (RPL.eirc.isShowall()) {
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + array[i] + " ";
            }
            RPL.eirc.getCurrentPanel().printInfo('\u0002' + string);
        }
    }
    
    public static void topic(final String s, final String[] array) {
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(array[0]);
        if (channelWindow == null) {
            return;
        }
        channelWindow.setTopic(MircMessage.filterMircAttributes(array[1]));
        channelWindow.getDoc().printNotice("Le topic du salon " + array[0] + " est maintenant " + array[1], "Chat-land");
    }
}
