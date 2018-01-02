// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.util.Vector;
import java.util.Locale;

public class ConfigurationLoader
{
    private ParameterProvider _provider;
    private URLHandler _handler;
    private ImageLoader _loader;
    private SoundHandler _sound;
    private FileHandler _file;
    
    public ConfigurationLoader(final ParameterProvider provider, final URLHandler handler, final ImageLoader loader, final SoundHandler sound, final FileHandler file) {
        this._provider = provider;
        this._handler = handler;
        this._loader = loader;
        this._sound = sound;
        this._file = file;
    }
    
    public IRCConfiguration loadIRCConfiguration() throws Exception {
        return this.getIRCConfiguration();
    }
    
    public StartupConfiguration loadStartupConfiguration() throws Exception {
        return this.getStartupConfiguration();
    }
    
    private String getParameter(final String s) {
        return this._provider.getParameter(s);
    }
    
    private boolean getBoolean(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        final String trim = parameter.toLowerCase(Locale.ENGLISH).trim();
        return trim.equals("true") || trim.equals("on") || trim.equals("1");
    }
    
    private String getString(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    private int getInt(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return n;
        }
        try {
            return Integer.parseInt(parameter);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private void readBackgroundConfig(final IRCConfiguration ircConfiguration) {
        final StringParser stringParser = new StringParser();
        final String[] array = this.getArray("style:backgroundimage");
        for (int i = 0; i < array.length; ++i) {
            final String[] string = stringParser.parseString(array[i]);
            if (string.length >= 4) {
                final String s = string[0];
                final String s2 = string[1];
                final int intValue = new Integer(string[2]);
                ircConfiguration.setBackgroundImage(s, s2, string[3]);
                ircConfiguration.setBackgroundTiling(s, s2, intValue);
            }
        }
    }
    
    private TextProvider getTextProvider() {
        final String string = this.getString("language", "english");
        final String string2 = this.getString("languageencoding", "");
        final String string3 = this.getString("lngextension", "lng");
        return new FileTextProvider(string + "." + string3, string2, this.getString("backuplanguage", "english") + "." + string3, this.getString("backuplanguageencoding", ""), this._file);
    }
    
    private String[] getArray(final String s) {
        final Vector vector = new Vector<String>();
        int n = 1;
        String parameter;
        do {
            parameter = this.getParameter(s + n);
            if (parameter != null) {
                vector.insertElementAt(parameter, vector.size());
            }
            ++n;
        } while (parameter != null);
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    private void readSmileys(final IRCConfiguration ircConfiguration) {
        final String[] array = this.getArray("style:smiley");
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final int index = s.indexOf(" ");
            if (index != -1) {
                ircConfiguration.addSmiley(s.substring(0, index).trim(), s.substring(index + 1).trim());
            }
        }
    }
    
    private void configureFonts(final IRCConfiguration ircConfiguration) {
        final StringParser stringParser = new StringParser();
        final String[] array = this.getArray("style:sourcefontrule");
        for (int i = 0; i < array.length; ++i) {
            final String[] string = stringParser.parseString(array[i]);
            if (string.length >= 4) {
                final String lowerCase = string[0].toLowerCase(Locale.ENGLISH);
                final String lowerCase2 = string[1].toLowerCase(Locale.ENGLISH);
                String s = string[2].toLowerCase(Locale.ENGLISH);
                if (s.startsWith("'") && s.endsWith("'")) {
                    s = s.substring(1, s.length() - 1);
                }
                ircConfiguration.setFont(lowerCase, lowerCase2, new Font(s, 0, new Integer(string[3].toLowerCase(Locale.ENGLISH))));
            }
        }
    }
    
    private void configureTextColors(final IRCConfiguration ircConfiguration) {
        final String[] array = this.getArray("style:sourcecolorrule");
        for (int i = 0; i < array.length; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(array[i]);
            if (stringTokenizer.hasMoreElements()) {
                final String s = (String)stringTokenizer.nextElement();
                if (stringTokenizer.hasMoreElements()) {
                    final String s2 = (String)stringTokenizer.nextElement();
                    final Color[] array2 = new Color[16];
                    ircConfiguration.loadDefaultColors(array2);
                    while (stringTokenizer.hasMoreElements()) {
                        final String s3 = (String)stringTokenizer.nextElement();
                        final int index = s3.indexOf(61);
                        if (index < 0) {
                            continue;
                        }
                        final String trim = s3.substring(0, index).trim();
                        final String trim2 = s3.substring(index + 1).trim();
                        final int int1 = Integer.parseInt(trim);
                        final Color color = new Color(Integer.parseInt(trim2, 16));
                        if (int1 < 0 || int1 > 15) {
                            continue;
                        }
                        array2[int1] = color;
                    }
                    ircConfiguration.setSourceColor(s, s2, array2);
                }
            }
        }
    }
    
    private void readSound(final IRCConfiguration ircConfiguration) {
        final AudioConfiguration audioConfiguration = ircConfiguration.getAudioConfiguration();
        if (this.getParameter("soundbeep") != null) {
            audioConfiguration.setBeep(this.getParameter("soundbeep"));
        }
        if (this.getParameter("soundquery") != null) {
            audioConfiguration.setQuery(this.getParameter("soundquery"));
        }
        final String[] array = this.getArray("soundword");
        for (int i = 0; i < array.length; ++i) {
            final String trim = array[i].trim();
            final int index = trim.indexOf(32);
            if (index != -1) {
                audioConfiguration.setWord(trim.substring(0, index).trim(), trim.substring(index + 1).trim());
            }
        }
    }
    
    private IRCConfiguration getIRCConfiguration() throws Exception {
        final IRCConfiguration ircConfiguration = new IRCConfiguration(this.getTextProvider(), this._handler, this._loader, this._sound, this._file, this._provider, new PrefixedParameterProvider(this._provider, this.getString("gui", "notprovided") + ":"));
        ircConfiguration.setJoinList(this.getString("authorizedjoinlist", ""));
        ircConfiguration.setLeaveList(this.getString("authorizedleavelist", ""));
        ircConfiguration.setCommandList(this.getString("authorizedcommandlist", ""));
        ircConfiguration.set("style:floatingasl", this.getBoolean("style:floatingasl", false));
        ircConfiguration.set("style:floatingaslalpha", this.getInt("style:floatingaslalpha", 170));
        ircConfiguration.set("style:backgroundimage", this.getBoolean("style:backgroundimage", false));
        ircConfiguration.set("style:bitmapsmileys", this.getBoolean("style:bitmapsmileys", false));
        ircConfiguration.set("style:linespacing", this.getInt("style:linespacing", 0));
        ircConfiguration.set("style:maximumlinecount", this.getInt("style:maximumlinecount", 1024));
        ircConfiguration.set("style:highlightlinks", this.getBoolean("style:highlightlinks", false));
        ircConfiguration.set("aslseparatorstring", this.getString("aslseparatorstring", ""));
        ircConfiguration.set("noasldisplayprefix", this.getString("noasldisplayprefix", ""));
        ircConfiguration.set("quitmessage", this.getString("quitmessage", ""));
        ircConfiguration.set("asl", this.getBoolean("asl", false));
        ircConfiguration.set("aslmale", this.getString("aslmale", "m"));
        ircConfiguration.set("aslfemale", this.getString("aslfemale", "f"));
        ircConfiguration.set("useinfo", this.getBoolean("useinfo", false));
        ircConfiguration.set("coding", this.getInt("coding", 1));
        ircConfiguration.set("userid", this.getString("userid", ""));
        ircConfiguration.set("style:righttoleft", this.getBoolean("style:righttoleft", false));
        ircConfiguration.set("autoconnection", this.getBoolean("autoconnection", true));
        ircConfiguration.set("useidentserver", this.getBoolean("useidentserver", true));
        ircConfiguration.set("multiserver", this.getBoolean("multiserver", false));
        ircConfiguration.set("aslunknown", this.getString("aslunknown", "x"));
        ircConfiguration.set("gui", this.getString("gui", null));
        ircConfiguration.set("fingerreply", this.getString("fingerreply", "A lucky Plouf's IRC user"));
        ircConfiguration.set("userinforeply", this.getString("userinforeply", "A lucky Plouf's IRC user"));
        ircConfiguration.set("allowdccchat", this.getBoolean("allowdccchat", true));
        ircConfiguration.set("allowdccfile", this.getBoolean("allowdccfile", true));
        ircConfiguration.set("disablequeries", this.getBoolean("disablequeries", false));
        ircConfiguration.set("autorejoin", this.getBoolean("autorejoin", false));
        ircConfiguration.setInitialisation(this.getArray("init"));
        this.readBackgroundConfig(ircConfiguration);
        this.readSmileys(ircConfiguration);
        this.configureFonts(ircConfiguration);
        this.configureTextColors(ircConfiguration);
        this.readSound(ircConfiguration);
        return ircConfiguration;
    }
    
    private ServerItem[] readServers(final String host, final int n, final String pass) {
        final Vector vector = new Vector<ServerItem>();
        final ServerItem serverItem = new ServerItem();
        serverItem.host = host;
        serverItem.port = new Integer(n);
        serverItem.pass = pass;
        vector.insertElementAt(serverItem, vector.size());
        final String[] array = this.getArray("alternateserver");
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final int index = s.indexOf(" ");
            if (index >= 0) {
                final String trim = s.substring(0, index).trim();
                String s2 = s.substring(index + 1).trim();
                String trim2 = "";
                final int index2 = s2.indexOf(" ");
                if (index2 >= 0) {
                    trim2 = s2.substring(index2 + 1).trim();
                    s2 = s2.substring(0, index2).trim();
                }
                final ServerItem serverItem2 = new ServerItem();
                serverItem2.host = trim;
                serverItem2.port = new Integer(s2);
                serverItem2.pass = trim2;
                vector.insertElementAt(serverItem2, vector.size());
            }
        }
        final ServerItem[] array2 = new ServerItem[vector.size()];
        for (int j = 0; j < array2.length; ++j) {
            array2[j] = vector.elementAt(j);
        }
        return array2;
    }
    
    private StartupConfiguration getStartupConfiguration() throws Exception {
        final String parameter = this.getParameter("nick");
        if (parameter == null) {
            throw new Exception("Mandatory 'nick' parameter not provided");
        }
        String s = this.getParameter("name");
        if (s == null) {
            s = this.getParameter("fullname");
        }
        if (s == null) {
            throw new Exception("Mandatory 'fullname' parameter not provided");
        }
        final String parameter2 = this.getParameter("host");
        if (parameter2 == null) {
            throw new Exception("Mandatory 'host' parameter not provided");
        }
        String parameter3 = this.getParameter("password");
        if (parameter3 == null) {
            parameter3 = "";
        }
        String parameter4 = this.getParameter("port");
        if (parameter4 == null) {
            parameter4 = "6667";
        }
        final int intValue = new Integer(parameter4);
        String s2 = this.getParameter("alternatenick");
        if (s2 == null) {
            s2 = parameter + "?";
        }
        String parameter5 = this.getParameter("serveralias");
        if (parameter5 == null) {
            parameter5 = "";
        }
        final ServerItem[] servers = this.readServers(parameter2, intValue, parameter3);
        final String[] array = new String[servers.length];
        final int[] array2 = new int[servers.length];
        final String[] array3 = new String[servers.length];
        for (int i = 0; i < servers.length; ++i) {
            array[i] = servers[i].host;
            array2[i] = servers[i].port;
            array3[i] = servers[i].pass;
        }
        return new StartupConfiguration(parameter, s2, s, array3, array, array2, parameter5, this.getArray("command"), this.getArray("plugin"));
    }
}
