// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class DefaultInterpretor extends BasicInterpretor
{
    private StartupConfiguration _start;
    private ServerManager _mgr;
    private PluginManager _plugin;
    
    public DefaultInterpretor(final IRCConfiguration ircConfiguration, final StartupConfiguration start, final ServerManager mgr, final PluginManager plugin) {
        super(ircConfiguration);
        this._start = start;
        this._mgr = mgr;
        this._plugin = plugin;
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        try {
            if (s.equals("newserver")) {
                if (super._ircConfiguration.getB("multiserver")) {
                    this.test(s, array, 2);
                    int intValue = 6667;
                    String s2 = "";
                    final String s3 = array[1];
                    if (array.length > 3) {
                        intValue = new Integer(array[3]);
                    }
                    if (array.length > 4) {
                        s2 = array[4];
                    }
                    final String s4 = array[2];
                    final IRCServer ircServer = new IRCServer(super._ircConfiguration, this._mgr, this._start.getNick(), this._start.getAltNick(), this._start.getName(), s3);
                    ircServer.setServers(new String[] { s4 }, new int[] { intValue }, new String[] { s2 });
                    this._mgr.newServer(ircServer, true);
                }
                else {
                    source.report(this.getText(13));
                }
            }
            else if (s.equals("load")) {
                this.test(s, array, 1);
                this._plugin.loadPlugin(array[1]);
            }
            else if (s.equals("unload")) {
                this.test(s, array, 1);
                this._plugin.unloadPlugin(array[1]);
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
