// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class ChannelInterpretor extends IRCInterpretor
{
    public ChannelInterpretor(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
    }
    
    private boolean isChannel(final String s, final Source source) {
        if (s.length() == 0) {
            return false;
        }
        final Server server = source.getServer();
        if (server instanceof IRCServer) {
            final char[] channelPrefixes = ((IRCServer)server).getChannelPrefixes();
            for (int i = 0; i < channelPrefixes.length; ++i) {
                if (s.charAt(0) == channelPrefixes[i]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        try {
            if (s.equals("part")) {
                if (array.length == 1) {
                    this.sendString(source, "/part " + source.getName());
                }
                else if (this.isChannel(array[1], source)) {
                    super.handleCommand(source, s, array, array2);
                }
                else {
                    this.sendString(source, "/part " + source.getName() + " " + array2[1]);
                }
            }
            else if (s.equals("hop")) {
                this.sendString(source, "/part");
                this.sendString(source, "/join " + source.getName());
            }
            else if (s.equals("onotice")) {
                this.test(s, array, 1);
                if (this.isChannel(array[1], source)) {
                    super.handleCommand(source, s, array, array2);
                }
                else {
                    this.sendString(source, "/onotice " + source.getName() + " " + array2[1]);
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
