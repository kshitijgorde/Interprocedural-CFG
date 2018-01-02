// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class StartupConfiguration
{
    private String _nick;
    private String _altNick;
    private String _name;
    private String[] _pass;
    private String[] _host;
    private String _alias;
    private int[] _port;
    private String[] _commands;
    private String[] _plugins;
    
    public StartupConfiguration(final String nick, final String altNick, final String name, final String[] pass, final String[] host, final int[] port, final String alias, final String[] commands, final String[] plugins) {
        this._nick = nick;
        this._altNick = altNick;
        this._name = name;
        this._pass = pass;
        this._host = host;
        this._port = port;
        this._alias = alias;
        this._commands = commands;
        this._plugins = plugins;
    }
    
    public String getNick() {
        return this._nick;
    }
    
    public String getAltNick() {
        return this._altNick;
    }
    
    public String getName() {
        return this._name;
    }
    
    public String[] getPass() {
        return this._pass;
    }
    
    public String[] getHost() {
        return this._host;
    }
    
    public int[] getPort() {
        return this._port;
    }
    
    public String getAlias() {
        return this._alias;
    }
    
    public String[] getCommands() {
        return this._commands;
    }
    
    public String[] getPlugins() {
        return this._plugins;
    }
}
