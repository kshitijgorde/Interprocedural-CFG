// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Hashtable;

public class Channel extends IRCSource implements ReplyServerListener
{
    private String _name;
    private String _topic;
    private ModeHandler _mode;
    private ListenerGroup _listeners;
    private Hashtable _nicks;
    
    public Channel(final IRCConfiguration ircConfiguration, final String name, final IRCServer ircServer) {
        super(ircConfiguration, ircServer);
        this._name = name;
        this._topic = "";
        this._mode = new ModeHandler(ircServer.getChannelModes(), ircServer.getNickModes());
        this._listeners = new ListenerGroup();
        this._nicks = new Hashtable();
        ircServer.addReplyServerListener(this);
        if (super._ircConfiguration.getASLMaster()) {
            this.getIRCServer().execute("WHO " + this._name);
        }
        this.setInterpretor(new ChannelInterpretor(ircConfiguration));
    }
    
    public void release() {
        ((IRCServer)super._server).removeReplyServerListener(this);
        super.release();
    }
    
    public void addChannelListener(final ChannelListener channelListener) {
        this._listeners.addListener(channelListener);
    }
    
    public void removeChannelListener(final ChannelListener channelListener) {
        this._listeners.removeListener(channelListener);
    }
    
    public void addChannelListener2(final ChannelListener2 channelListener2) {
        this._listeners.addListener(channelListener2);
    }
    
    public void removeChannelListener2(final ChannelListener2 channelListener2) {
        this._listeners.removeListener(channelListener2);
    }
    
    public String getType() {
        return "Channel";
    }
    
    public String getName() {
        return this._name;
    }
    
    public boolean talkable() {
        return true;
    }
    
    public void leave() {
        this.getIRCServer().leaveChannel(this.getName());
    }
    
    public boolean hasNick(final String s) {
        return this._nicks.get(s.toLowerCase(Locale.ENGLISH)) != null;
    }
    
    public void joinNick(final String s, final String s2) {
        this._nicks.put(s.toLowerCase(Locale.ENGLISH), new Nick(s, s2, this.getIRCServer().getChannelModes(), this.getIRCServer().getNickModes()));
        if (super._ircConfiguration.getASLMaster()) {
            this.getIRCServer().execute("WHO " + s);
        }
        this._listeners.sendEvent("nickJoin", s, s2, this);
    }
    
    public void resetNicks() {
        this._nicks.clear();
        this._listeners.sendEvent("nickReset", this);
    }
    
    public void setNicks(final String[] array, final String[] array2) {
        for (int i = 0; i < array.length; ++i) {
            this._nicks.put(array[i].toLowerCase(Locale.ENGLISH), new Nick(array[i], array2[i], this.getIRCServer().getChannelModes(), this.getIRCServer().getNickModes()));
        }
        this._listeners.sendEvent("nickSet", array, array2, this);
    }
    
    public void partNick(final String s, final String s2) {
        this._nicks.remove(s.toLowerCase(Locale.ENGLISH));
        this._listeners.sendEvent("nickPart", s, s2, this);
    }
    
    public void kickNick(final String s, final String s2, final String s3) {
        this._nicks.remove(s.toLowerCase(Locale.ENGLISH));
        this._listeners.sendEvent("nickKick", new Object[] { s, s2, s3, this });
    }
    
    public void quitNick(final String s, final String s2) {
        this._nicks.remove(s.toLowerCase(Locale.ENGLISH));
        this._listeners.sendEvent("nickQuit", s, s2, this);
    }
    
    public String[] getNicks() {
        final String[] array = new String[this._nicks.size()];
        final Enumeration<Nick> elements = this._nicks.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            array[n++] = elements.nextElement().Name;
        }
        return array;
    }
    
    public String getNickMode(final String s) {
        final Nick nick = this._nicks.get(s.toLowerCase(Locale.ENGLISH));
        if (nick == null) {
            return null;
        }
        return nick.Mode.getMode();
    }
    
    public void setTopic(final String topic, final String s) {
        this._topic = topic;
        this._listeners.sendEvent("topicChanged", topic, s, this);
    }
    
    public void applyUserMode(final String s, final String s2, final String s3) {
        final Nick nick = this._nicks.get(s.toLowerCase(Locale.ENGLISH));
        if (nick != null) {
            nick.Mode.apply(s2);
        }
        this._listeners.sendEvent("nickModeApply", new Object[] { s, s2, s3, this });
    }
    
    public void applyMode(final String s, final String s2) {
        this._mode.apply(s);
        this._listeners.sendEvent("modeApply", s, s2, this);
    }
    
    public String getMode() {
        return this._mode.getMode();
    }
    
    public String getTopic() {
        return this._topic;
    }
    
    public void changeNick(final String s, final String name) {
        final Nick nick = this._nicks.get(s.toLowerCase(Locale.ENGLISH));
        this._nicks.remove(s.toLowerCase(Locale.ENGLISH));
        nick.Name = name;
        this._nicks.put(name.toLowerCase(Locale.ENGLISH), nick);
        this._listeners.sendEvent("nickChanged", s, name, this);
    }
    
    private void learn(final String s, final String whois) {
        final Nick nick = this._nicks.get(s.toLowerCase(Locale.ENGLISH));
        if (nick == null) {
            return;
        }
        nick.Whois = whois;
        this._listeners.sendEvent("nickWhoisUpdated", s, whois, this);
    }
    
    public String whois(final String s) {
        final Nick nick = this._nicks.get(s.toLowerCase(Locale.ENGLISH));
        if (nick == null) {
            return "";
        }
        return nick.Whois;
    }
    
    public Boolean replyReceived(final String s, final String s2, final String[] array, final IRCServer ircServer) {
        if (s2.equals("352")) {
            String substring = array[array.length - 1];
            final int index = substring.indexOf(" ");
            if (index != -1) {
                substring = substring.substring(index + 1);
            }
            this.learn(array[5], substring);
        }
        return Boolean.FALSE;
    }
}
