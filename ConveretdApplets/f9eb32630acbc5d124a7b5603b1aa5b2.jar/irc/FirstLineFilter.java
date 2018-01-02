// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import irc.dcc.prv.DCCFileHandler;
import java.io.File;
import irc.dcc.prv.DCCChatServer;
import java.util.Date;
import java.util.Locale;

class FirstLineFilter
{
    private IRCServer _server;
    private IRCConfiguration _ircConfiguration;
    private ServerManager _mgr;
    
    public FirstLineFilter(final IRCServer server, final ServerManager mgr, final IRCConfiguration ircConfiguration) {
        this._ircConfiguration = ircConfiguration;
        this._mgr = mgr;
        this._server = server;
    }
    
    public void release() {
        this._mgr = null;
        this._server = null;
    }
    
    public boolean performFromChannelMessage(final String s, final String s2, String s3) {
        if (!s3.startsWith("\u0001")) {
            return false;
        }
        s3 = s3.substring(1);
        s3 = s3.substring(0, s3.length() - 1);
        String substring = "";
        final int index = s3.indexOf(32);
        String s4;
        if (index == -1) {
            s4 = s3.toLowerCase(Locale.ENGLISH);
        }
        else {
            s4 = s3.substring(0, index).toLowerCase(Locale.ENGLISH);
            substring = s3.substring(index + 1);
        }
        if (s4.equals("action")) {
            final Channel channel = this._server.getChannel(s, false);
            if (channel != null) {
                channel.action(s2, substring);
            }
        }
        else if (s4.equals("sound")) {
            this._ircConfiguration.getAudioConfiguration().play(substring);
            this._server.sendStatusMessage("\u0002\u00034[" + s2 + " " + s4.toUpperCase(Locale.ENGLISH) + "]");
        }
        return true;
    }
    
    public boolean performFromNickMessage(final String s, String s2) {
        if (!s2.startsWith("\u0001")) {
            return false;
        }
        s2 = s2.substring(1);
        s2 = s2.substring(0, s2.length() - 1);
        String substring = "";
        final int index = s2.indexOf(32);
        String s3;
        if (index == -1) {
            s3 = s2.toLowerCase(Locale.ENGLISH);
        }
        else {
            s3 = s2.substring(0, index).toLowerCase(Locale.ENGLISH);
            substring = s2.substring(index + 1);
        }
        boolean b = true;
        if (s3.equals("action")) {
            b = false;
            final Query query = this._server.getQuery(s, false);
            if (query != null) {
                query.action(s, substring);
            }
        }
        else if (s3.equals("version")) {
            this._server.execute("NOTICE " + s + " :\u0001VERSION " + ("PJIRC " + this._ircConfiguration.getVersion()) + "\u0001");
        }
        else if (s3.equals("ping")) {
            this._server.execute("NOTICE " + s + " :\u0001PING " + substring + "\u0001");
        }
        else if (s3.equals("time")) {
            this._server.execute("NOTICE " + s + " :\u0001TIME " + new Date().toString() + "\u0001");
        }
        else if (s3.equals("finger")) {
            this._server.execute("NOTICE " + s + " :\u0001FINGER " + this._ircConfiguration.getS("fingerreply") + "\u0001");
        }
        else if (s3.equals("userinfo")) {
            this._server.execute("NOTICE " + s + " :\u0001USERINFO " + this._ircConfiguration.getS("userinforeply") + "\u0001");
        }
        else if (s3.equals("clientinfo")) {
            this._server.execute("NOTICE " + s + " :\u0001CLIENTINFO " + "This client is a Java application supporting the following CTCP tags : ACTION VERSION PING TIME FINGER USERINFO CLIENTINFO SOUND DCC" + "\u0001");
        }
        else if (s3.equals("sound")) {
            this._ircConfiguration.getAudioConfiguration().play(substring);
        }
        else if (s3.equals("dcc")) {
            final String[] string = new StringParser().parseString(substring.toLowerCase(Locale.ENGLISH));
            if (string.length >= 2) {
                if (string[0].equals("chat") && string[1].equals("chat") && this._ircConfiguration.getB("allowdccchat") && string.length >= 4) {
                    boolean b2 = false;
                    final Object[] specialRequest = this._server.specialRequest("DCCChatRequest", new Object[] { s });
                    for (int i = 0; i < specialRequest.length; ++i) {
                        if (specialRequest[i]) {
                            b2 = true;
                        }
                    }
                    if (b2) {
                        try {
                            final DCCChatServer dccChatServer = new DCCChatServer(this._ircConfiguration, this._server.getNick(), s);
                            dccChatServer.openActive(string[2], string[3]);
                            this._mgr.newServer(dccChatServer, false);
                        }
                        catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }
                if (string[0].equals("send") && this._ircConfiguration.getB("allowdccfile") && string.length >= 5) {
                    final String s4 = string[1];
                    final String s5 = string[2];
                    final String s6 = string[3];
                    final String s7 = string[4];
                    final Object[] specialRequest2 = this._server.specialRequest("DCCFileRequest", new Object[] { s, s4, new Integer(s7) });
                    File file = null;
                    if (specialRequest2.length > 0) {
                        file = (File)specialRequest2[0];
                    }
                    if (file != null) {
                        try {
                            final DCCFileHandler dccFileHandler = new DCCFileHandler(this._ircConfiguration, s, file);
                            dccFileHandler.receive(s5, s6, s7);
                            this._mgr.newServer(dccFileHandler, false);
                        }
                        catch (Throwable t2) {
                            t2.printStackTrace();
                        }
                    }
                }
            }
        }
        if (b) {
            this._server.sendStatusMessage("\u0002\u00034[" + s + " " + s3.toUpperCase(Locale.ENGLISH) + "]");
        }
        return true;
    }
    
    public boolean performFromNotice(final String s, String s2) {
        if (!s2.startsWith("\u0001")) {
            return false;
        }
        s2 = s2.substring(1);
        s2 = s2.substring(0, s2.length() - 1);
        String substring = "";
        final int index = s2.indexOf(32);
        String s3;
        if (index == -1) {
            s3 = s2.toLowerCase(Locale.ENGLISH);
        }
        else {
            s3 = s2.substring(0, index).toLowerCase(Locale.ENGLISH);
            substring = s2.substring(index + 1);
        }
        final Source defaultSource = this._server.getDefaultSource();
        if (s3.equals("ping")) {
            final long n = new Date().getTime() - new Long(substring);
            if (defaultSource != null) {
                defaultSource.report("\u0002\u00034" + this._ircConfiguration.getText(266, s, n / 1000.0 + ""));
            }
            return true;
        }
        if (defaultSource != null) {
            defaultSource.report("\u0002\u00034[" + s + " " + s3.toUpperCase(Locale.ENGLISH) + " reply] : " + substring);
        }
        return true;
    }
}
