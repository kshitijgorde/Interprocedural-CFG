// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.text.MessageFormat;
import irc.Alert;
import irc.com.nick.NickInfos;
import irc.com.commands.ServerCommands;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.com.messages.Message;
import irc.EIRC;

public class PROCESS
{
    static EIRC eirc;
    
    public static void init(final EIRC eirc) {
        PROCESS.eirc = eirc;
    }
    
    public static void processMessage(final Message message) {
        try {
            final String lowerCase = message.getCommand().toLowerCase();
            final String prefix = message.getPrefix();
            final String[] parameters = message.getParameters();
            try {
                if ("You have joined too many channels".equals(parameters[2])) {
                    if (parameters[1].length() == 2) {
                        PROCESS.eirc.sendMessage("PRIVMSG", new String[] { "test2", parameters[1].substring(1) });
                        return;
                    }
                    JOptionPane.showMessageDialog(PROCESS.eirc.getFrame(), "Vous \u00eates d\u00e9j\u00e0 sur " + CHANNELS.channels.size() + " salons, quitter un salon pour  pouvoir en joindre un autre.", "Chat-Land.org", 0);
                }
            }
            catch (Exception ex2) {}
            final Integer n = ServerCommands.getCommands().get(lowerCase);
            String substring = prefix;
            final int index = prefix.indexOf(33);
            final int index2 = prefix.indexOf(64);
            if (index != -1) {
                substring = prefix.substring(0, index);
                if (index2 > index) {
                    NickInfos.updateInfos(substring, prefix.substring(index + 1, index2), prefix.substring(index2 + 1));
                }
            }
            if (n == null) {
                PROCESS.eirc.nulls(substring, parameters);
                return;
            }
            switch (n) {
                case -1: {
                    PROCESS.eirc.sendMessage("PONG", new String[] { parameters[0] });
                }
                case -2: {
                    PROCESS.eirc.nick(substring, parameters);
                }
                case -3: {
                    CHANNELS.join(substring, parameters);
                }
                case -4: {
                    PROCESS.eirc.modes(substring, parameters);
                }
                case -5: {
                    CHANNELS.part(substring, parameters);
                }
                case -6: {
                    PROCESS.eirc.quit(substring, parameters);
                }
                case -7: {
                    CHANNELS.kick(substring, parameters);
                }
                case -8: {
                    RPL.topic(substring, parameters);
                }
                case -9: {
                    if (PROCESS.eirc.isPrefixIgnored(substring)) {
                        return;
                    }
                    PRIVMSG.Do(substring, parameters);
                }
                case -10: {
                    PROCESS.eirc.notice(substring, parameters);
                }
                case -11: {
                    if (!parameters[0].startsWith("Closing Link:")) {
                        PROCESS.eirc.getAccueil().Application.setVisible(true);
                        PROCESS.eirc.getAccueil().Application.setState(0);
                        PROCESS.eirc.getAccueil().Application.toFront();
                        System.out.println("aaaaaaaaa2");
                        new Alert(PROCESS.eirc, parameters[0]);
                    }
                    else if (parameters[0].indexOf("User has been banned from chat-land (") != -1) {
                        final String substring2 = parameters[0].substring(parameters[0].indexOf("User has been banned from chat-land (") + 37, parameters[0].length() - 2);
                        PROCESS.eirc.getAccueil().Application.setVisible(true);
                        PROCESS.eirc.getAccueil().Application.setState(0);
                        PROCESS.eirc.getAccueil().Application.toFront();
                        new Alert(PROCESS.eirc, substring2);
                    }
                    else if (parameters[0].indexOf("User has been permanently banned from chat-land") != -1) {
                        final String substring3 = parameters[0].substring(parameters[0].indexOf("User has been permanently banned from chat-land") + 49, parameters[0].length() - 2);
                        PROCESS.eirc.getAccueil().Application.setVisible(true);
                        PROCESS.eirc.getAccueil().Application.setState(0);
                        PROCESS.eirc.getAccueil().Application.toFront();
                        new Alert(PROCESS.eirc, substring3);
                    }
                    PROCESS.eirc.setReallydisconnected(true);
                    PROCESS.eirc.logout();
                }
                case -12: {
                    PROCESS.eirc.getCurrentPanel().appendNickInfo(parameters[0], substring);
                }
                case -13: {
                    if (PROCESS.eirc.isSee_invite()) {
                        PROCESS.eirc.getCurrentPanel().printError(MessageFormat.format(Resources.getStringEirc("eirc.invitate"), parameters[1], substring));
                    }
                }
                case 1: {
                    RPL.RPL_WELCOME(substring, parameters);
                }
                case 2: {
                    RPL.RPL_YOURHOST(substring, parameters);
                }
                case 3: {
                    RPL.RPL_CREATED(substring, parameters);
                }
                case 4: {
                    RPL.RPL_MYINFO(substring, parameters);
                }
                case 5: {
                    RPL.RPL_ISUPPORT(substring, parameters);
                }
                case 6: {
                    RPL.RPL_MAP(substring, parameters);
                }
                case 7: {
                    RPL.RPL_MAPEND(substring, parameters);
                }
                case 8: {
                    RPL.RPL_SNOMASK(substring, parameters);
                }
                case 10: {
                    RPL.RPL_REDIR(substring, parameters);
                }
                case 105: {
                    RPL.RPL_REMOTEISUPPORT(substring, parameters);
                }
                case 200: {
                    RPL.RPL_TRACELINK(substring, parameters);
                }
                case 201: {
                    RPL.RPL_TRACECONNECTING(substring, parameters);
                }
                case 202: {
                    RPL.RPL_TRACEHANDSHAKE(substring, parameters);
                }
                case 203: {
                    RPL.RPL_TRACEUNKNOWN(substring, parameters);
                }
                case 204: {
                    RPL.RPL_TRACEOPERATOR(substring, parameters);
                }
                case 205: {
                    RPL.RPL_TRACEUSER(substring, parameters);
                }
                case 206: {
                    RPL.RPL_TRACESERVER(substring, parameters);
                }
                case 207: {
                    RPL.RPL_TRACESERVICE(substring, parameters);
                }
                case 208: {
                    RPL.RPL_TRACENEWTYPE(substring, parameters);
                }
                case 209: {
                    RPL.RPL_TRACECLASS(substring, parameters);
                }
                case 210: {
                    RPL.RPL_STATSHELP(substring, parameters);
                }
                case 212: {
                    RPL.RPL_STATSCOMMANDS(substring, parameters);
                }
                case 213: {
                    RPL.RPL_STATSCLINE(substring, parameters);
                }
                case 214: {
                    RPL.RPL_STATSOLDNLINE(substring, parameters);
                }
                case 215: {
                    RPL.RPL_STATSILINE(substring, parameters);
                }
                case 216: {
                    RPL.RPL_STATSKLINE(substring, parameters);
                }
                case 217: {
                    RPL.RPL_STATSQLINE(substring, parameters);
                }
                case 218: {
                    RPL.RPL_STATSYLINE(substring, parameters);
                }
                case 219: {
                    RPL.RPL_ENDOFSTATS(substring, parameters);
                }
                case 220: {
                    RPL.RPL_STATSBLINE(substring, parameters);
                }
                case 221: {
                    RPL.RPL_UMODEIS(substring, parameters);
                }
                case 222: {
                    RPL.RPL_SQLINE_NICK(substring, parameters);
                }
                case 223: {
                    RPL.RPL_STATSGLINE(substring, parameters);
                }
                case 224: {
                    RPL.RPL_STATSTLINE(substring, parameters);
                }
                case 225: {
                    RPL.RPL_STATSELINE(substring, parameters);
                }
                case 226: {
                    RPL.RPL_STATSNLINE(substring, parameters);
                }
                case 227: {
                    RPL.RPL_STATSVLINE(substring, parameters);
                }
                case 228: {
                    RPL.RPL_STATSBANVER(substring, parameters);
                }
                case 229: {
                    RPL.RPL_STATSSPAMF(substring, parameters);
                }
                case 230: {
                    RPL.RPL_STATSEXCEPTTKL(substring, parameters);
                }
                case 232: {
                    RPL.RPL_RULES(substring, parameters);
                }
                case 241: {
                    RPL.RPL_STATSLLINE(substring, parameters);
                }
                case 242: {
                    RPL.RPL_STATSUPTIME(substring, parameters);
                }
                case 243: {
                    RPL.RPL_STATSOLINE(substring, parameters);
                }
                case 244: {
                    RPL.RPL_STATSHLINE(substring, parameters);
                }
                case 245: {
                    RPL.RPL_STATSSLINE(substring, parameters);
                }
                case 247: {
                    RPL.RPL_STATSXLINE(substring, parameters);
                }
                case 248: {
                    RPL.RPL_STATSULINE(substring, parameters);
                }
                case 250: {
                    RPL.RPL_STATSCONN(substring, parameters);
                }
                case 251: {
                    RPL.RPL_LUSERCLIENT(substring, parameters);
                }
                case 252: {
                    RPL.RPL_LUSEROP(substring, parameters);
                }
                case 253: {
                    RPL.RPL_LUSERUNKNOWN(substring, parameters);
                }
                case 254: {
                    RPL.RPL_LUSERCHANNELS(substring, parameters);
                }
                case 255: {
                    RPL.RPL_LUSERME(substring, parameters);
                }
                case 256: {
                    RPL.RPL_ADMINME(substring, parameters);
                }
                case 257: {
                    RPL.RPL_ADMINLOC1(substring, parameters);
                }
                case 258: {
                    RPL.RPL_ADMINLOC2(substring, parameters);
                }
                case 259: {
                    RPL.RPL_ADMINEMAIL(substring, parameters);
                }
                case 261: {
                    RPL.RPL_TRACELOG(substring, parameters);
                }
                case 265: {
                    RPL.RPL_LOCALUSERS(substring, parameters);
                }
                case 266: {
                    RPL.RPL_GLOBALUSERS(substring, parameters);
                }
                case 271: {
                    RPL.RPL_SILELIST(substring, parameters);
                }
                case 272: {
                    RPL.RPL_ENDOFSILELIST(substring, parameters);
                }
                case 275: {
                    RPL.RPL_STATSDLINE(substring, parameters);
                }
                case 294: {
                    RPL.RPL_HELPFWD(substring, parameters);
                }
                case 295: {
                    RPL.RPL_HELPIGN(substring, parameters);
                }
                case 301: {
                    RPL.RPL_AWAY(substring, parameters);
                }
                case 302: {
                    RPL.RPL_USERHOST(substring, parameters);
                }
                case 303: {
                    RPL.RPL_ISON(substring, parameters);
                }
                case 305: {
                    RPL.RPL_UNAWAY(substring, parameters);
                }
                case 306: {
                    RPL.RPL_NOWAWAY(substring, parameters);
                }
                case 307: {
                    RPL.RPL_WHOISREGNICK(substring, parameters);
                }
                case 308: {
                    RPL.RPL_RULESSTART(substring, parameters);
                }
                case 309: {
                    RPL.RPL_ENDOFRULES(substring, parameters);
                }
                case 310: {
                    RPL.RPL_WHOISHELPOP(substring, parameters);
                }
                case 311: {
                    RPL.RPL_WHOISUSER(substring, parameters);
                }
                case 312: {
                    RPL.RPL_WHOISSERVER(substring, parameters);
                }
                case 313: {
                    RPL.RPL_WHOISOPERATOR(substring, parameters);
                }
                case 314: {
                    RPL.RPL_WHOWASUSER(substring, parameters);
                }
                case 315: {
                    RPL.RPL_ENDOFWHO(substring, parameters);
                }
                case 317: {
                    RPL.RPL_WHOISIDLE(substring, parameters);
                }
                case 318: {
                    RPL.RPL_ENDOFWHOIS(substring, parameters);
                }
                case 319: {
                    RPL.RPL_WHOISCHANNELS(substring, parameters);
                }
                case 320: {
                    RPL.RPL_WHOISSPECIAL(substring, parameters);
                }
                case 321: {
                    RPL.RPL_LISTSTART(substring, parameters);
                }
                case 322: {
                    RPL.RPL_LIST(substring, parameters);
                }
                case 323: {
                    RPL.RPL_LISTEND(substring, parameters);
                }
                case 324: {
                    RPL.RPL_CHANNELMODEIS(substring, parameters);
                }
                case 329: {
                    RPL.RPL_CREATIONTIME(substring, parameters);
                }
                case 331: {
                    RPL.RPL_NOTOPIC(substring, parameters);
                }
                case 332: {
                    RPL.RPL_TOPIC(substring, parameters);
                }
                case 333: {
                    RPL.RPL_TOPICWHOTIME(substring, parameters);
                }
                case 334: {
                    RPL.RPL_LISTSYNTAX(substring, parameters);
                }
                case 335: {
                    RPL.RPL_WHOISBOT(substring, parameters);
                }
                case 336: {
                    RPL.RPL_INVITELIST(substring, parameters);
                }
                case 337: {
                    RPL.RPL_ENDOFINVITELIST(substring, parameters);
                }
                case 340: {
                    RPL.RPL_USERIP(substring, parameters);
                }
                case 341: {
                    RPL.RPL_INVITING(substring, parameters);
                }
                case 342: {
                    RPL.RPL_SUMMONING(substring, parameters);
                }
                case 346: {
                    RPL.RPL_INVEXLIST(substring, parameters);
                }
                case 347: {
                    RPL.RPL_ENDOFINVEXLIST(substring, parameters);
                }
                case 348: {
                    RPL.RPL_EXLIST(substring, parameters);
                }
                case 349: {
                    RPL.RPL_ENDOFEXLIST(substring, parameters);
                }
                case 351: {
                    RPL.RPL_VERSION(substring, parameters);
                }
                case 352: {
                    RPL.RPL_WHOREPLY(substring, parameters);
                }
                case 353: {
                    RPL.RPL_NAMREPLY(substring, parameters);
                }
                case 362: {
                    RPL.RPL_CLOSING(substring, parameters);
                }
                case 363: {
                    RPL.RPL_CLOSEEND(substring, parameters);
                }
                case 364: {
                    RPL.RPL_LINKS(substring, parameters);
                }
                case 365: {
                    RPL.RPL_ENDOFLINKS(substring, parameters);
                }
                case 366: {
                    RPL.RPL_ENDOFNAMES(substring, parameters);
                }
                case 367: {
                    RPL.RPL_BANLIST(substring, parameters);
                }
                case 368: {
                    RPL.RPL_ENDOFBANLIST(substring, parameters);
                }
                case 369: {
                    RPL.RPL_ENDOFWHOWAS(substring, parameters);
                }
                case 371: {
                    RPL.RPL_INFO(substring, parameters);
                }
                case 372: {
                    RPL.RPL_MOTD(substring, parameters);
                }
                case 373: {
                    RPL.RPL_INFOSTART(substring, parameters);
                }
                case 374: {
                    RPL.RPL_ENDOFINFO(substring, parameters);
                }
                case 375: {
                    RPL.RPL_MOTDSTART(substring, parameters);
                }
                case 376: {
                    RPL.RPL_ENDOFMOTD(substring, parameters);
                }
                case 378: {
                    RPL.RPL_WHOISHOST(substring, parameters);
                }
                case 379: {
                    RPL.RPL_WHOISMODES(substring, parameters);
                }
                case 381: {
                    RPL.RPL_YOUREOPER(substring, parameters);
                }
                case 382: {
                    RPL.RPL_REHASHING(substring, parameters);
                }
                case 384: {
                    RPL.RPL_MYPORTIS(substring, parameters);
                }
                case 386: {
                    RPL.RPL_QLIST(substring, parameters);
                }
                case 387: {
                    RPL.RPL_ENDOFQLIST(substring, parameters);
                }
                case 388: {
                    RPL.RPL_ALIST(substring, parameters);
                }
                case 389: {
                    RPL.RPL_ENDOFALIST(substring, parameters);
                }
                case 391: {
                    RPL.RPL_TIME(substring, parameters);
                }
                case 392: {
                    RPL.RPL_USERSSTART(substring, parameters);
                }
                case 393: {
                    RPL.RPL_USERS(substring, parameters);
                }
                case 394: {
                    RPL.RPL_ENDOFUSERS(substring, parameters);
                }
                case 395: {
                    RPL.RPL_NOUSERS(substring, parameters);
                }
                case 600: {
                    RPL.RPL_LOGON(substring, parameters);
                }
                case 601: {
                    RPL.RPL_LOGOFF(substring, parameters);
                }
                case 602: {
                    RPL.RPL_WATCHOFF(substring, parameters);
                }
                case 603: {
                    RPL.RPL_WATCHSTAT(substring, parameters);
                }
                case 604: {
                    RPL.RPL_NOWON(substring, parameters);
                }
                case 605: {
                    RPL.RPL_NOWOFF(substring, parameters);
                }
                case 606: {
                    RPL.RPL_WATCHLIST(substring, parameters);
                }
                case 607: {
                    RPL.RPL_ENDOFWATCHLIST(substring, parameters);
                }
                case 610: {
                    RPL.RPL_MAPMORE(substring, parameters);
                }
                case 617: {
                    RPL.RPL_DCCSTATUS(substring, parameters);
                }
                case 618: {
                    RPL.RPL_DCCLIST(substring, parameters);
                }
                case 619: {
                    RPL.RPL_ENDOFDCCLIST(substring, parameters);
                }
                case 620: {
                    RPL.RPL_DCCINFO(substring, parameters);
                }
                case 643: {
                    RPL.RPL_NOWMPERSO(substring, parameters);
                }
                case 644: {
                    RPL.RPL_UNMPERSO(substring, parameters);
                }
                case 645: {
                    RPL.RPL_MPERSO(substring, parameters);
                }
                case 646: {
                    RPL.RPL_MYWHOREPLY(substring, parameters);
                }
                case 659: {
                    RPL.RPL_SPAMCMDFWD(substring, parameters);
                }
                case 671: {
                    RPL.RPL_WHOISSECURE(substring, parameters);
                }
                case 399: {
                    ERR.ERR_MYNOSUCHNICK(substring, parameters);
                }
                case 401: {
                    ERR.ERR_NOSUCHNICK(substring, parameters);
                }
                case 402: {
                    ERR.ERR_NOSUCHSERVER(substring, parameters);
                }
                case 403: {
                    ERR.ERR_NOSUCHCHANNEL(substring, parameters);
                }
                case 404: {
                    ERR.ERR_CANNOTSENDTOCHAN(substring, parameters);
                }
                case 405: {
                    ERR.ERR_TOOMANYCHANNELS(substring, parameters);
                }
                case 406: {
                    ERR.ERR_WASNOSUCHNICK(substring, parameters);
                }
                case 407: {
                    ERR.ERR_TOOMANYTARGETS(substring, parameters);
                }
                case 409: {
                    ERR.ERR_NOORIGIN(substring, parameters);
                }
                case 411: {
                    ERR.ERR_NORECIPIENT(substring, parameters);
                }
                case 412: {
                    ERR.ERR_NOTEXTTOSEND(substring, parameters);
                }
                case 413: {
                    ERR.ERR_NOTOPLEVEL(substring, parameters);
                }
                case 414: {
                    ERR.ERR_WILDTOPLEVEL(substring, parameters);
                }
                case 421: {
                    ERR.ERR_UNKNOWNCOMMAND(substring, parameters);
                }
                case 422: {
                    ERR.ERR_NOMOTD(substring, parameters);
                }
                case 423: {
                    ERR.ERR_NOADMININFO(substring, parameters);
                }
                case 424: {
                    ERR.ERR_FILEERROR(substring, parameters);
                }
                case 425: {
                    ERR.ERR_NOOPERMOTD(substring, parameters);
                }
                case 429: {
                    ERR.ERR_TOOMANYAWAY(substring, parameters);
                }
                case 431: {
                    ERR.ERR_NONICKNAMEGIVEN(substring, parameters);
                }
                case 432: {
                    ERR.ERR_ERRONEUSNICKNAME(substring, parameters);
                }
                case 433: {
                    ERR.ERR_NICKNAMEINUSE(substring, parameters);
                }
                case 434: {
                    ERR.ERR_NORULES(substring, parameters);
                }
                case 436: {
                    ERR.ERR_NICKCOLLISION(substring, parameters);
                }
                case 437: {
                    ERR.ERR_BANNICKCHANGE(substring, parameters);
                }
                case 438: {
                    ERR.ERR_NCHANGETOOFAST(substring, parameters);
                }
                case 439: {
                    ERR.ERR_TARGETTOOFAST(substring, parameters);
                }
                case 440: {
                    ERR.ERR_SERVICESDOWN(substring, parameters);
                }
                case 441: {
                    ERR.ERR_USERNOTINCHANNEL(substring, parameters);
                }
                case 442: {
                    ERR.ERR_NOTONCHANNEL(substring, parameters);
                }
                case 443: {
                    ERR.ERR_USERONCHANNEL(substring, parameters);
                }
                case 444: {
                    ERR.ERR_NOLOGIN(substring, parameters);
                }
                case 445: {
                    ERR.ERR_SUMMONDISABLED(substring, parameters);
                }
                case 446: {
                    ERR.ERR_USERSDISABLED(substring, parameters);
                }
                case 447: {
                    ERR.ERR_NONICKCHANGE(substring, parameters);
                }
                case 451: {
                    ERR.ERR_NOTREGISTERED(substring, parameters);
                }
                case 455: {
                    ERR.ERR_HOSTILENAME(substring, parameters);
                }
                case 459: {
                    ERR.ERR_NOHIDING(substring, parameters);
                }
                case 460: {
                    ERR.ERR_NOTFORHALFOPS(substring, parameters);
                }
                case 461: {
                    ERR.ERR_NEEDMOREPARAMS(substring, parameters);
                }
                case 462: {
                    ERR.ERR_ALREADYREGISTRED(substring, parameters);
                }
                case 463: {
                    ERR.ERR_NOPERMFORHOST(substring, parameters);
                }
                case 464: {
                    ERR.ERR_PASSWDMISMATCH(substring, parameters);
                }
                case 465: {
                    ERR.ERR_YOUREBANNEDCREEP(substring, parameters);
                }
                case 467: {
                    ERR.ERR_KEYSET(substring, parameters);
                }
                case 468: {
                    ERR.ERR_ONLYSERVERSCANCHANGE(substring, parameters);
                }
                case 469: {
                    ERR.ERR_LINKSET(substring, parameters);
                }
                case 470: {
                    ERR.ERR_LINKCHANNEL(substring, parameters);
                }
                case 471: {
                    ERR.ERR_CHANNELISFULL(substring, parameters);
                }
                case 472: {
                    ERR.ERR_UNKNOWNMODE(substring, parameters);
                }
                case 473: {
                    ERR.ERR_INVITEONLYCHAN(substring, parameters);
                }
                case 474: {
                    ERR.ERR_BANNEDFROMCHAN(substring, parameters);
                }
                case 475: {
                    ERR.ERR_BADCHANNELKEY(substring, parameters);
                }
                case 476: {
                    ERR.ERR_BADCHANMASK(substring, parameters);
                }
                case 477: {
                    ERR.ERR_NEEDREGGEDNICK(substring, parameters);
                }
                case 478: {
                    ERR.ERR_BANLISTFULL(substring, parameters);
                }
                case 479: {
                    ERR.ERR_LINKFAIL(substring, parameters);
                }
                case 480: {
                    ERR.ERR_CANNOTKNOCK(substring, parameters);
                }
                case 481: {
                    ERR.ERR_NOPRIVILEGES(substring, parameters);
                }
                case 482: {
                    ERR.ERR_CHANOPRIVSNEEDED(substring, parameters);
                }
                case 483: {
                    ERR.ERR_CANTKILLSERVER(substring, parameters);
                }
                case 484: {
                    ERR.ERR_ATTACKDENY(substring, parameters);
                }
                case 485: {
                    ERR.ERR_KILLDENY(substring, parameters);
                }
                case 486: {
                    ERR.ERR_NONONREG(substring, parameters);
                }
                case 487: {
                    ERR.ERR_NOTFORUSERS(substring, parameters);
                }
                case 488: {
                    ERR.ERR_HTMDISABLED(substring, parameters);
                }
                case 489: {
                    ERR.ERR_SECUREONLYCHAN(substring, parameters);
                }
                case 490: {
                    ERR.ERR_NOSWEAR(substring, parameters);
                }
                case 491: {
                    ERR.ERR_NOOPERHOST(substring, parameters);
                }
                case 492: {
                    ERR.ERR_NOCTCP(substring, parameters);
                }
                case 499: {
                    ERR.ERR_CHANOWNPRIVNEEDED(substring, parameters);
                }
                case 500: {
                    ERR.ERR_TOOMANYJOINS(substring, parameters);
                }
                case 501: {
                    ERR.ERR_UMODEUNKNOWNFLAG(substring, parameters);
                }
                case 502: {
                    ERR.ERR_USERSDONTMATCH(substring, parameters);
                }
                case 511: {
                    ERR.ERR_SILELISTFULL(substring, parameters);
                }
                case 512: {
                    ERR.ERR_TOOMANYWATCH(substring, parameters);
                }
                case 513: {
                    ERR.ERR_NEEDPONG(substring, parameters);
                }
                case 514: {
                    ERR.ERR_TOOMANYDCC(substring, parameters);
                }
                case 517: {
                    ERR.ERR_DISABLED(substring, parameters);
                }
                case 518: {
                    ERR.ERR_CANNOT_INVITE_AT_CHANNEL(substring, parameters);
                }
                case 519: {
                    ERR.ERR_CANNOT_JOIN_CHANNEL_ADMINONLY(substring, parameters);
                }
                case 520: {
                    ERR.ERR_CANNOT_JOIN_CHANNEL_IRCOPONLY(substring, parameters);
                }
                case 521: {
                    ERR.ERR_LISTSYNTAX(substring, parameters);
                }
                case 522: {
                    ERR.ERR_WHOSYNTAX(substring, parameters);
                }
                case 523: {
                    ERR.ERR_WHOLIMEXCEED(substring, parameters);
                }
                case 524: {
                    ERR.ERR_OPERSPVERIFY(substring, parameters);
                }
                case 972: {
                    ERR.ERR_CANNOTDOCOMMAND(substring, parameters);
                }
                case 974: {
                    ERR.ERR_CANNOTCHANGECHANMODE(substring, parameters);
                }
                case 999: {
                    ERR.ERR_NUMERICERR(substring, parameters);
                }
                default: {
                    message.close();
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
