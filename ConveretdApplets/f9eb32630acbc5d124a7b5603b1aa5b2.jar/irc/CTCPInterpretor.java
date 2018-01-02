// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import irc.dcc.prv.DCCFileHandler;
import java.io.File;
import irc.dcc.prv.DCCChatServer;
import java.util.Date;
import java.util.Locale;

public class CTCPInterpretor extends BasicInterpretor
{
    protected ServerManager _mgr;
    
    public CTCPInterpretor(final IRCConfiguration ircConfiguration, final Interpretor interpretor, final ServerManager mgr) {
        super(ircConfiguration, interpretor);
        this._mgr = mgr;
    }
    
    private void send(final Server server, final String s, final String s2) {
        server.say(s, "\u0001" + s2 + "\u0001");
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        try {
            if (s.equals("ctcp")) {
                this.test(s, array, 1);
                if (array[1].toLowerCase(Locale.ENGLISH).equals("ping")) {
                    this.test(s, array, 2);
                    this.send(source.getServer(), array[2], "PING " + new Date().getTime());
                }
                else if (array[1].toLowerCase(Locale.ENGLISH).equals("action")) {
                    this.test(s, array, 2);
                    if (source.talkable()) {
                        this.send(source.getServer(), source.getName(), "ACTION " + array2[2]);
                        source.action(source.getServer().getNick(), array2[2]);
                    }
                    else {
                        source.report(this.getText(1));
                    }
                }
                else if (array[1].toLowerCase(Locale.ENGLISH).equals("dcc")) {
                    this.test(s, array, 2);
                    if (array[2].toLowerCase(Locale.ENGLISH).equals("chat") && super._ircConfiguration.getB("allowdccchat")) {
                        this.test(s, array, 3);
                        final Server server = source.getServer();
                        final String s2 = array[3];
                        try {
                            final DCCChatServer dccChatServer = new DCCChatServer(super._ircConfiguration, server.getNick(), s2);
                            final String openPassive = dccChatServer.openPassive();
                            if (openPassive.length() == 0) {
                                dccChatServer.sendStatusMessage(this.getText(265));
                            }
                            else {
                                this.send(server, s2, "DCC CHAT chat " + openPassive);
                            }
                            this._mgr.newServer(dccChatServer, false);
                        }
                        catch (Throwable t) {
                            super._ircConfiguration.internalError("dcc chat error", t, "plouf@pjirc.com");
                        }
                    }
                    else if (array[2].toLowerCase(Locale.ENGLISH).equals("send") && super._ircConfiguration.getB("allowdccfile")) {
                        String s3 = null;
                        this.test(s, array, 3);
                        if (array.length > 4) {
                            s3 = array2[4];
                        }
                        final Server server2 = source.getServer();
                        final String s4 = array[3];
                        try {
                            File loadFile;
                            if (s3 != null) {
                                loadFile = new File(s3);
                            }
                            else {
                                loadFile = super._ircConfiguration.getSecurityProvider().getLoadFile("Send file");
                            }
                            final DCCFileHandler dccFileHandler = new DCCFileHandler(super._ircConfiguration, s4, loadFile);
                            final char c = '\"';
                            String s5 = loadFile.getName();
                            if (s5.indexOf(" ") != -1) {
                                s5 = c + s5 + c;
                            }
                            this.send(server2, s4, "DCC SEND " + (s5 + " " + dccFileHandler.send()));
                            this._mgr.newServer(dccFileHandler, false);
                        }
                        catch (Throwable t2) {
                            super._ircConfiguration.internalError("dcc send error", t2, "plouf@pjirc.com");
                        }
                    }
                    else {
                        source.report(this.getText(2, array[2]));
                    }
                }
                else if (array[1].toLowerCase(Locale.ENGLISH).equals("raw")) {
                    this.test(s, array, 3);
                    this.send(source.getServer(), array[2], array2[3]);
                }
                else if (array[1].toLowerCase(Locale.ENGLISH).equals("sound")) {
                    this.test(s, array, 2);
                    this.send(source.getServer(), source.getName(), "SOUND " + array[2]);
                }
                else {
                    this.test(s, array, 2);
                    this.send(source.getServer(), array[2], array[1]);
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
