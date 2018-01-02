// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;

public class IRCInterpretor extends BasicInterpretor
{
    public IRCInterpretor(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        try {
            final IRCServer ircServer = (IRCServer)source.getServer();
            if (s.equals("amsg")) {
                this.test(s, array, 1);
                final Enumeration channels = ircServer.getChannels();
                while (channels.hasMoreElements()) {
                    channels.nextElement().sendString(array2[1]);
                }
            }
            else if (s.equals("ame")) {
                this.test(s, array, 1);
                final Enumeration channels2 = ircServer.getChannels();
                while (channels2.hasMoreElements()) {
                    channels2.nextElement().sendString("/me " + array2[1]);
                }
            }
            else if (s.equals("list")) {
                if (array.length <= 1) {
                    ircServer.execute("LIST");
                }
                else {
                    ircServer.execute("LIST " + array[1]);
                }
            }
            else if (s.equals("topic")) {
                this.test(s, array, 2);
                ircServer.execute("TOPIC " + array[1] + " :" + array2[2]);
            }
            else if (s.equals("away")) {
                if (array.length <= 1) {
                    ircServer.execute("AWAY");
                }
                else {
                    ircServer.execute("AWAY :" + array2[1]);
                }
            }
            else if (s.equals("quit")) {
                if (array.length > 1) {
                    ircServer.execute("QUIT :" + array2[1]);
                }
                else {
                    ircServer.execute("QUIT");
                }
            }
            else if (s.equals("part")) {
                this.test(s, array, 1);
                if (array.length == 2) {
                    ircServer.execute("PART " + array[1]);
                }
                else {
                    ircServer.execute("PART " + array[1] + " :" + array2[2]);
                }
            }
            else if (s.equals("kick")) {
                this.test(s, array, 2);
                if (array.length == 3) {
                    ircServer.execute("KICK " + array[1] + " " + array[2]);
                }
                else {
                    ircServer.execute("KICK " + array[1] + " " + array[2] + " :" + array2[3]);
                }
            }
            else if (s.equals("notice")) {
                this.test(s, array, 2);
                ircServer.execute("NOTICE " + array[1] + " :" + array2[2]);
                source.report("-> -" + array[1] + "- " + array2[2]);
            }
            else if (s.equals("onotice")) {
                this.test(s, array, 2);
                this.sendString(source, "/notice @" + array[1] + " " + array2[2]);
            }
            else if (s.equals("join")) {
                this.test(s, array, 1);
                String string = array[1];
                if (!string.startsWith("#") && !string.startsWith("!") && !string.startsWith("&") && !string.startsWith("+")) {
                    string = '#' + string;
                }
                if (array.length <= 2) {
                    ircServer.execute("JOIN " + string);
                }
                else {
                    ircServer.execute("JOIN " + string + " " + array[2]);
                }
            }
            else if (s.equals("j")) {
                this.sendString(source, "/join " + array2[1]);
            }
            else if (s.equals("query")) {
                this.test(s, array, 1);
                ircServer.getQuery(array[1], true);
            }
            else if (s.equals("ignore")) {
                this.test(s, array, 1);
                if (!ircServer.ignore(array[1])) {
                    ircServer.addIgnore(array[1]);
                    source.report(this.getText(11, array[1]));
                }
            }
            else if (s.equals("unignore")) {
                this.test(s, array, 1);
                if (ircServer.ignore(array[1])) {
                    ircServer.removeIgnore(array[1]);
                    source.report(this.getText(12, array[1]));
                }
            }
            else if (s.equals("server")) {
                this.test(s, array, 1);
                int intValue = 6667;
                String s2 = "";
                if (array.length > 2) {
                    intValue = new Integer(array[2]);
                }
                if (array.length > 3) {
                    s2 = array[3];
                }
                final String s3 = array[1];
                if (ircServer.isConnected()) {
                    ircServer.disconnect();
                }
                ircServer.setServers(new String[] { s3 }, new int[] { intValue }, new String[] { s2 });
                ircServer.connect();
            }
            else if (s.equals("connect")) {
                ircServer.connect();
            }
            else if (s.equals("disconnect")) {
                ircServer.disconnect();
            }
            else {
                super.handleCommand(source, s, array, array2);
            }
        }
        catch (NotEnoughParametersException ex) {
            source.report(this.getText(3, ex.getMessage()));
        }
    }
}
