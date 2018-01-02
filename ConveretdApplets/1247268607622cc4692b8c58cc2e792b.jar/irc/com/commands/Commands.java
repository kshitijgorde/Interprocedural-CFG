// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.commands;

import irc.com.utils.MyVector;
import java.util.StringTokenizer;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;

public class Commands extends ListResourceBundle
{
    private static final Object[][] contents;
    
    public static Command getCommand(final ResourceBundle resourceBundle, final String s) throws MissingResourceException {
        Object o = s.toLowerCase();
        while ((o = resourceBundle.getObject((String)o)) instanceof String) {}
        return (Command)o;
    }
    
    public static String[] parseCommand(final ResourceBundle resourceBundle, final String s) throws MissingResourceException, IllegalArgumentException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final Command command = getCommand(resourceBundle, stringTokenizer.nextToken());
        final String tag = command.getTag();
        final int requiredParameters = command.getRequiredParameters();
        final int acceptedParameters = command.getAcceptedParameters();
        final MyVector myVector = new MyVector();
        myVector.addElement(tag);
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens < requiredParameters) {
            throw new IllegalArgumentException("insuficient parameters");
        }
        for (int i = 0; i < Math.min(acceptedParameters, countTokens) - 1; ++i) {
            myVector.addElement(stringTokenizer.nextToken());
        }
        if (countTokens > 0) {
            myVector.addElement(stringTokenizer.nextToken("").trim());
        }
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        return array;
    }
    
    public Object[][] getContents() {
        return Commands.contents;
    }
    
    static {
        contents = new Object[][] { { "admin", new Command("ADMIN", 0, 1) }, { "away", new Command("AWAY", 0, 1) }, { "mperso", new Command("MPERSO", 0, 1) }, { "connect", new Command("CONNECT", 1, 3) }, { "info", new Command("INFO", 0, 1) }, { "invite", new Command("INVITE", 2) }, { "ison", new Command("ISON", 1) }, { "join", new Command("JOIN", 1, 2) }, { "kick", new Command("KICK", 2, 3) }, { "kill", new Command("KILL", 2) }, { "links", new Command("LINKS", 0, 2) }, { "list", new Command("LIST", 0, 2) }, { "lusers", new Command("LUSERS", 0, 2) }, { "mode", new Command("MODE", 1, 4) }, { "motd", new Command("MOTD", 0, 1) }, { "names", new Command("NAMES", 0, 2) }, { "notice", new Command("NOTICE", 2) }, { "nick", new Command("NICK", 1) }, { "oper", new Command("OPER", 2) }, { "part", new Command("PART", 0, 2) }, { "ping", new Command("PING", 1) }, { "privmsg", new Command("PRIVMSG", 2) }, { "quit", new Command("QUIT", 0, 1) }, { "rehash", new Command("REHASH", 0, 1) }, { "restart", new Command("RESTART", 0) }, { "squit", new Command("SQUIT", 2) }, { "stats", new Command("STATS", 0, 2) }, { "time", new Command("TIME", 0, 1) }, { "topic", new Command("TOPIC", 1, 2) }, { "trace", new Command("TRACE", 0, 1) }, { "userhost", new Command("USERHOST", 1) }, { "version", new Command("VERSION", 0, 1) }, { "who", new Command("WHO", 0, 2) }, { "mywho", new Command("MYWHO", 0, 2) }, { "whois", new Command("WHOIS", 1, 2) }, { "whowas", new Command("WHOWAS", 1, 3) }, { "amsg", new Command("AMSG", 0, 0) }, { "clear", new Command("CLEAR", 0, 0) }, { "ctcp", new Command("CTCP", 2) }, { "eirc", new Command("EIRC", 0) }, { "ghost", new Command("GHOST", 2) }, { "help", new Command("HELP", 0, 1) }, { "ignore", new Command("IGNORE", 0, 1) }, { "kban", new Command("KBAN", 1, 3) }, { "me", new Command("ME", 1) }, { "pingtime", new Command("PINGTIME", 1) }, { "query", new Command("QUERY", 1, 2) }, { "show_profile", new Command("SHOW_PROFILE", 1) }, { "add_friend", new Command("ADD_FRIEND", 2) }, { "quote", new Command("QUOTE", 1) }, { "server", new Command("SERVER", 1, 3) }, { "unignore", new Command("UNIGNORE", 1) }, { "partall", new Command("PARTALL", 0) }, { "msg", "privmsg" }, { "j", "join" }, { "q", "query" }, { "w", "whois" } };
    }
}
