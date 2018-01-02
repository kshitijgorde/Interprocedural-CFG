// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.StringTokenizer;
import irc.managers.Resources;
import java.text.MessageFormat;

public class IRCServices
{
    private EIRC eirc;
    private static int NICKS;
    private static int CHANS;
    private static int MEMOS;
    private static int BOTS;
    private static int HELP;
    private static int OPER;
    private static int HOSTS;
    private static int SERV_NUM;
    private static String[] serv;
    private static String eggs;
    private static final MessageFormat reg_str;
    private static final MessageFormat ident_str;
    private static final MessageFormat ghost_str;
    
    public IRCServices(final EIRC eirc) {
        this.eirc = eirc;
        IRCServices.eggs = "";
    }
    
    public void identifyNick(final String s) {
        this.eirc.sendMessage("PRIVMSG", new String[] { IRCServices.serv[IRCServices.NICKS], IRCServices.ident_str.format(new Object[] { s }) });
    }
    
    public boolean isIdPrompt(final String s, final String s2) {
        final String stringEirc = Resources.getStringEirc("services.ns.prompt");
        if (stringEirc == null) {
            throw new RuntimeException("resource services.ns.prompt is null");
        }
        return s2.indexOf(stringEirc) >= 0 && s.equals(IRCServices.serv[IRCServices.NICKS]);
    }
    
    public boolean isNickGhosted(final String s, final String s2) {
        return s.equals("NickServ") && s2.equals("L'utilisateur fant\u00f4me utilisant votre pseudo a \u00e9t\u00e9 d\u00e9connect\u00e9.");
    }
    
    public boolean isNickReleased(final String s, final String s2) {
        return s.equals("NickServ") && s2.equals("La tutelle des Services sur votre pseudo a \u00e9t\u00e9 enlev\u00e9e.");
    }
    
    public boolean isPassBad(final String s, final String s2) {
        return s2.indexOf(Resources.getStringEirc("services.ns.loginbad")) >= 0 && s.equals(IRCServices.serv[IRCServices.NICKS]);
    }
    
    public boolean isPassOk(final String s, final String s2) {
        return s2.indexOf(Resources.getStringEirc("services.ns.loginok")) >= 0 && s.equals(IRCServices.serv[IRCServices.NICKS]);
    }
    
    public boolean isService(final String s) {
        for (int i = 0; i < IRCServices.SERV_NUM; ++i) {
            if (s.equalsIgnoreCase(IRCServices.serv[i])) {
                return true;
            }
        }
        if (IRCServices.eggs != null && !IRCServices.eggs.equals("")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(IRCServices.eggs, ",");
            while (stringTokenizer.hasMoreTokens()) {
                if (s.equalsIgnoreCase(stringTokenizer.nextToken())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void killGhost(final String s, final String s2) {
        if (s2 == null || s2.equals("") || IRCServices.serv[IRCServices.NICKS] == null || IRCServices.serv[IRCServices.NICKS].equals("")) {
            return;
        }
        this.eirc.sendMessage("PRIVMSG", new String[] { IRCServices.serv[IRCServices.NICKS], IRCServices.ghost_str.format(new Object[] { s, s2 }) });
    }
    
    public void op() {
        if (IRCServices.serv[IRCServices.CHANS] == null || IRCServices.serv[IRCServices.CHANS].equals("")) {
            return;
        }
        this.eirc.sendMessage("PRIVMSG", new String[] { IRCServices.serv[IRCServices.CHANS], Resources.getStringEirc("services.cs.op") });
    }
    
    public void registerNick(final String s, final String s2) {
        if (s == null || s.equals("") || IRCServices.serv[IRCServices.NICKS] == null || IRCServices.serv[IRCServices.NICKS].equals("")) {
            return;
        }
        this.eirc.sendMessage("PRIVMSG", new String[] { IRCServices.serv[IRCServices.NICKS], IRCServices.reg_str.format(new Object[] { s, s2 }) });
    }
    
    static {
        IRCServices.NICKS = 0;
        IRCServices.CHANS = 1;
        IRCServices.MEMOS = 2;
        IRCServices.BOTS = 3;
        IRCServices.HELP = 4;
        IRCServices.OPER = 5;
        IRCServices.HOSTS = 6;
        IRCServices.SERV_NUM = 7;
        IRCServices.serv = new String[] { "NickServ", "ChanServ", "MemoServ", "BotServ", "HelpServ", "OperServ", "HostServ" };
        reg_str = new MessageFormat(Resources.getStringEirc("services.ns.reg"));
        ident_str = new MessageFormat(Resources.getStringEirc("services.ns.ident"));
        ghost_str = new MessageFormat(Resources.getStringEirc("services.ns.ghost"));
    }
}
