// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Vector;

public class ChanList extends IRCSource
{
    private ListenerGroup _listeners;
    private Vector _channels;
    private String _name;
    private boolean _running;
    private int _ignored;
    
    public ChanList(final IRCConfiguration ircConfiguration, final IRCServer server, final String name) {
        super(ircConfiguration, server);
        this._name = name;
        super._server = server;
        this._listeners = new ListenerGroup();
        this._channels = new Vector();
        this._running = false;
    }
    
    public String getType() {
        return "ChanList";
    }
    
    public String getName() {
        return this._name;
    }
    
    public ChannelInfo[] getChannels() {
        final ChannelInfo[] array = new ChannelInfo[this._channels.size()];
        for (int i = 0; i < this._channels.size(); ++i) {
            array[i] = (ChannelInfo)this._channels.elementAt(i);
        }
        return array;
    }
    
    public int getChannelCount() {
        return this._channels.size();
    }
    
    public int getIgnoredChannelCount() {
        return this._ignored;
    }
    
    public void addChannel(final ChannelInfo channelInfo) {
        if (this._channels.size() > 1024 && channelInfo.userCount < 5) {
            ++this._ignored;
            return;
        }
        this._channels.insertElementAt(channelInfo, this._channels.size());
        this._listeners.sendEvent("channelAdded", channelInfo, this);
    }
    
    public void begin() {
        this._ignored = 0;
        this._running = true;
        this._channels = new Vector();
        this._listeners.sendEvent("channelBegin", this);
    }
    
    public void end() {
        this._running = false;
        this._listeners.sendEvent("channelEnd", this);
    }
    
    public void addChanListListener(final ChanListListener chanListListener) {
        this._listeners.addListener(chanListListener);
    }
    
    public void removeChanListListeners(final ChanListListener chanListListener) {
        this._listeners.removeListener(chanListListener);
    }
    
    public void leave() {
        if (this._running) {
            return;
        }
        this.getIRCServer().leaveChanList(this._name);
    }
    
    public boolean talkable() {
        return false;
    }
    
    public boolean mayDefault() {
        return false;
    }
}
