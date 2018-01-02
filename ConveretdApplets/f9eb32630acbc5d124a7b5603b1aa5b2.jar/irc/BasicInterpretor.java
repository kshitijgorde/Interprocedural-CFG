// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;

public class BasicInterpretor extends RootInterpretor implements Interpretor
{
    public BasicInterpretor(final IRCConfiguration ircConfiguration) {
        this(ircConfiguration, null);
    }
    
    public BasicInterpretor(final IRCConfiguration ircConfiguration, final Interpretor interpretor) {
        super(ircConfiguration, interpretor);
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        try {
            final Server server = source.getServer();
            if (s.equals("echo")) {
                this.test(s, array, 1);
                source.report(array2[1]);
            }
            else if (s.equals("sleep")) {
                this.test(s, array, 1);
                try {
                    Thread.sleep(new Integer(array[1]));
                }
                catch (Exception ex2) {}
            }
            else if (s.equals("me")) {
                this.test(s, array, 1);
                this.sendString(source, "/ctcp action " + array2[1]);
            }
            else if (s.equals("beep")) {
                super._ircConfiguration.getAudioConfiguration().beep();
            }
            else if (s.equals("play")) {
                this.test(s, array, 1);
                super._ircConfiguration.getAudioConfiguration().play(array[1]);
            }
            else if (s.equals("sound")) {
                this.test(s, array, 1);
                if (source.talkable()) {
                    this.sendString(source, "/ctcp sound " + array2[1]);
                    this.sendString(source, "/play " + array2[1]);
                }
                else {
                    source.report(this.getText(1));
                }
            }
            else if (s.equals("url")) {
                this.test(s, array, 1);
                if (array.length >= 3) {
                    super._ircConfiguration.getURLHandler().openURL(array[1], array[2]);
                }
                else {
                    super._ircConfiguration.getURLHandler().openURL(array[1]);
                }
            }
            else if (s.equals("clear")) {
                source.clear();
            }
            else if (s.equals("leave")) {
                source.leave();
            }
            else if (s.equals("msg")) {
                this.test(s, array, 2);
                boolean b = false;
                final Enumeration sources = server.getSources();
                while (sources.hasMoreElements()) {
                    final Source source2 = sources.nextElement();
                    if (source2.getName().equals(array[1])) {
                        this.say(source2, array2[2]);
                        b = true;
                    }
                }
                if (!b) {
                    server.say(array[1], array2[2]);
                }
            }
            else if (s.equals("ping")) {
                this.test(s, array, 1);
                this.sendString(source, "/ctcp ping " + array2[1]);
            }
            else if (s.equals("dcc")) {
                this.test(s, array, 1);
                this.sendString(source, "/ctcp dcc " + array2[1]);
            }
            else if (s.equals("raw")) {
                server.execute(array2[1]);
            }
            else if (s.equals("version")) {
                source.report("PJIRC v" + super._ircConfiguration.getVersion());
            }
            else if (s.equals("gc")) {
                System.gc();
                source.report(Runtime.getRuntime().freeMemory() + " " + Runtime.getRuntime().totalMemory());
            }
            else if (s.equals("onserver")) {
                this.test(s, array, 2);
                if (array[1].equals(server.getServerName())) {
                    if (array2[2].startsWith("/") && server instanceof IRCServer) {
                        ((IRCServer)server).getStatus().sendString(array2[2]);
                    }
                    else {
                        server.execute(array2[2]);
                    }
                }
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
