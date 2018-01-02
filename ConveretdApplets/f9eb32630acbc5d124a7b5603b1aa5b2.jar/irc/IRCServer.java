// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

public class IRCServer extends IRCObject implements Server, ServerProtocolListener
{
    private ServerProtocol _protocol;
    private Hashtable _channels;
    private Hashtable _queries;
    private Hashtable _chanlist;
    private Status _status;
    private Hashtable _ignoreList;
    private ListenerGroup _listeners;
    private ListenerGroup _replylisteners;
    private ListenerGroup _messagelisteners;
    private String[] _askedNick;
    private String _nick;
    private String _userName;
    private int _tryNickIndex;
    private ModeHandler _mode;
    private String[] _host;
    private int[] _port;
    private String[] _passWord;
    private int _tryServerIndex;
    private boolean _connected;
    private String _name;
    private Source _defaultSource;
    private boolean _serverLeaving;
    private boolean _registered;
    private FirstLineFilter _filter;
    private char[] _nickModes;
    private char[] _nickPrefixes;
    private char[] _channelPrefixes;
    private char[][] _globalModes;
    
    public IRCServer(final IRCConfiguration ircConfiguration, final ServerManager serverManager, final String nick, final String s, final String userName, final String name) {
        super(ircConfiguration);
        this._nickModes = new char[] { 'o', 'h', 'v' };
        this._nickPrefixes = new char[] { '@', '%', '+' };
        this._channelPrefixes = new char[] { '#', '&', '!', '+' };
        this._globalModes = new char[][] { { 'b' }, { 'k' }, { 'l' }, { 'i', 'm', 'n', 'p', 's', 't', 'a', 'q', 'r' } };
        this._filter = new FirstLineFilter(this, serverManager, ircConfiguration);
        this._serverLeaving = false;
        this._name = name;
        this._userName = userName;
        (this._askedNick = new String[2])[0] = nick;
        this._askedNick[1] = s;
        this._nick = nick;
        this._connected = false;
        this._ignoreList = new Hashtable();
        this._channels = new Hashtable();
        this._queries = new Hashtable();
        this._chanlist = new Hashtable();
        this._listeners = new ListenerGroup();
        this._replylisteners = new ListenerGroup();
        this._messagelisteners = new ListenerGroup();
        this._status = new Status(super._ircConfiguration, this);
        this._defaultSource = this._status;
        (this._protocol = new ServerProtocol(super._ircConfiguration)).addServerProtocolListener(this);
        this._host = null;
        this._mode = new ModeHandler(this._globalModes, this._nickModes);
    }
    
    public Object[] specialRequest(final String s, final Object[] array) {
        return this._listeners.sendEvent("specialServerRequest", s, this, array);
    }
    
    public void release() {
        this._protocol.removeServerProtocolListener(this);
        this._filter.release();
        super.release();
    }
    
    public Enumeration getSources() {
        final Vector vector = new Vector<Status>();
        final Enumeration<Object> elements = this._channels.elements();
        while (elements.hasMoreElements()) {
            vector.insertElementAt(elements.nextElement(), vector.size());
        }
        final Enumeration<Status> elements2 = this._queries.elements();
        while (elements2.hasMoreElements()) {
            vector.insertElementAt(elements2.nextElement(), vector.size());
        }
        final Enumeration<Status> elements3 = this._chanlist.elements();
        while (elements3.hasMoreElements()) {
            vector.insertElementAt(elements3.nextElement(), vector.size());
        }
        if (this._status != null) {
            vector.insertElementAt(this._status, vector.size());
        }
        return vector.elements();
    }
    
    public void enumerateSourcesAsCreated(final ServerListener serverListener) {
        final Enumeration<Source> elements = this._channels.elements();
        while (elements.hasMoreElements()) {
            serverListener.sourceCreated(elements.nextElement(), this, new Boolean(false));
        }
        final Enumeration<Source> elements2 = this._queries.elements();
        while (elements2.hasMoreElements()) {
            serverListener.sourceCreated(elements2.nextElement(), this, new Boolean(false));
        }
        final Enumeration<Source> elements3 = this._chanlist.elements();
        while (elements3.hasMoreElements()) {
            serverListener.sourceCreated(elements3.nextElement(), this, new Boolean(false));
        }
        if (this._status != null) {
            serverListener.sourceCreated(this._status, this, new Boolean(true));
        }
    }
    
    public void enumerateSourcesAsRemoved(final ServerListener serverListener) {
        final Enumeration<Source> elements = this._channels.elements();
        while (elements.hasMoreElements()) {
            serverListener.sourceRemoved(elements.nextElement(), this);
        }
        final Enumeration<Source> elements2 = this._queries.elements();
        while (elements2.hasMoreElements()) {
            serverListener.sourceRemoved(elements2.nextElement(), this);
        }
        final Enumeration<Source> elements3 = this._chanlist.elements();
        while (elements3.hasMoreElements()) {
            serverListener.sourceRemoved(elements3.nextElement(), this);
        }
        if (this._status != null) {
            serverListener.sourceRemoved(this._status, this);
        }
    }
    
    public void setDefaultSource(final Source defaultSource) {
        this._defaultSource = defaultSource;
    }
    
    public Source getDefaultSource() {
        return this._defaultSource;
    }
    
    public void setServers(final String[] array, final int[] array2, final String[] array3) {
        this._tryServerIndex = 0;
        this._host = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this._host[i] = array[i];
        }
        this._port = new int[array2.length];
        for (int j = 0; j < array2.length; ++j) {
            this._port[j] = array2[j];
        }
        this._passWord = new String[array3.length];
        for (int k = 0; k < array3.length; ++k) {
            this._passWord[k] = array3[k];
        }
    }
    
    public void connect() {
        this._tryServerIndex = 0;
        if (this._host != null) {
            this.connect(this._host, this._port, this._passWord);
        }
    }
    
    private void connect(final String[] array, final int[] array2, final String[] passWord) {
        this._registered = false;
        if (this._tryServerIndex == this._host.length) {
            return;
        }
        this._tryNickIndex = 0;
        this._passWord = passWord;
        if (this._protocol.connecting()) {
            this.sendStatusMessage(this.getText(1282, array[this._tryServerIndex], this._host[this._tryServerIndex]));
            return;
        }
        if (this._protocol.connected()) {
            this._protocol.disconnect();
        }
        this._connected = false;
        this.sendStatusMessage(this.getText(1284));
        this._protocol.connect(array[this._tryServerIndex], array2[this._tryServerIndex]);
    }
    
    public void disconnect() {
        if (this._protocol.connected()) {
            if (super._ircConfiguration.getS("quitmessage").length() == 0) {
                this.execute("QUIT");
            }
            else {
                this.execute("QUIT :" + super._ircConfiguration.get("quitmessage"));
            }
        }
        else {
            this.sendStatusMessage(this.getText(1285));
        }
    }
    
    public boolean isConnected() {
        return this._connected;
    }
    
    public void connectionFailed(final String s, final String s2) {
        this.sendStatusMessage(this.getText(1281, s));
        ++this._tryServerIndex;
        if (this._tryServerIndex < this._host.length) {
            this.connect(this._host, this._port, this._passWord);
        }
    }
    
    private void nickUsed() {
        if (this._tryNickIndex >= this._askedNick.length) {
            final Object[] sendEvent = this._listeners.sendEvent("cannotUseRequestedNicknames", new Object[] { this });
            if (sendEvent.length > 0) {
                this._askedNick = (String[])sendEvent[0];
            }
            this._tryNickIndex = 0;
        }
        else if (this._askedNick[this._tryNickIndex].indexOf("?") == -1) {
            ++this._tryNickIndex;
        }
    }
    
    private void register() {
        String s = this._askedNick[this._tryNickIndex];
        if (s.length() == 0) {
            s = "Anon????";
        }
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            char char1 = s.charAt(i);
            if (char1 == '?') {
                char1 = (char)(48.0 + Math.random() * 10.0);
            }
            string += char1;
        }
        if (this._passWord[this._tryServerIndex].length() > 0) {
            this.execute("pass " + this._passWord[this._tryServerIndex]);
        }
        this.execute("nick " + string);
        String s2 = super._ircConfiguration.getS("userid");
        if (s2.length() == 0) {
            s2 = string;
        }
        if (!this._registered) {
            this._registered = true;
            this.execute("user " + s2 + " 0 0 :" + this._userName);
        }
    }
    
    public int getLocalPort() {
        return this._protocol.getLocalPort();
    }
    
    public void connected(final String s) {
        this.sendStatusMessage(this.getText(1286));
        this.register();
    }
    
    private void clear(final Hashtable hashtable) {
        final Enumeration<Source> elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            this._listeners.sendEvent("sourceRemoved", elements.nextElement(), this);
        }
        final Enumeration<Source> elements2 = hashtable.elements();
        while (elements2.hasMoreElements()) {
            elements2.nextElement().release();
        }
        hashtable.clear();
    }
    
    public void disconnected(final String s) {
        this.sendStatusMessage(this.getText(1287, s));
        this.clear(this._channels);
        this.clear(this._queries);
        this.clear(this._chanlist);
        this._mode.reset();
        if (this._status != null) {
            this._status.modeChanged(this.getMode());
        }
        this._connected = false;
        this._listeners.sendEvent("serverDisconnected", this);
        if (this._serverLeaving) {
            this._listeners.sendEvent("sourceRemoved", this._status, this);
            this.deleteStatus("");
            this._listeners.sendEvent("serverLeft", this);
        }
    }
    
    public void sendStatusMessage(final String s) {
        if (this._status != null) {
            this._status.report(s);
        }
    }
    
    public Enumeration getChannels() {
        return this._channels.elements();
    }
    
    public Enumeration getQueries() {
        return this._queries.elements();
    }
    
    public Enumeration getChanLists() {
        return this._chanlist.elements();
    }
    
    public Channel getChannel(final String s, final boolean b) {
        Channel channel = this._channels.get(s.toLowerCase(Locale.ENGLISH));
        if (channel == null && b) {
            channel = new Channel(super._ircConfiguration, s, this);
            this._channels.put(s.toLowerCase(Locale.ENGLISH), channel);
            this._listeners.sendEvent("sourceCreated", channel, this, new Boolean(true));
        }
        return channel;
    }
    
    public Query getQuery(final String s, final boolean b) {
        if (!this._connected) {
            return null;
        }
        if (super._ircConfiguration.getB("disablequeries")) {
            return null;
        }
        Query query = this._queries.get(s.toLowerCase(Locale.ENGLISH));
        if (query == null) {
            query = new Query(super._ircConfiguration, s, this);
            this._queries.put(s.toLowerCase(Locale.ENGLISH), query);
            this._listeners.sendEvent("sourceCreated", query, this, new Boolean(b));
        }
        return query;
    }
    
    private ChanList getChanList(final String s) {
        ChanList list = this._chanlist.get(s.toLowerCase(Locale.ENGLISH));
        if (list == null) {
            list = new ChanList(super._ircConfiguration, this, s);
            this._chanlist.put(s.toLowerCase(Locale.ENGLISH), list);
            this._listeners.sendEvent("sourceCreated", list, this, new Boolean(true));
        }
        return list;
    }
    
    public void leaveChannel(final String s) {
        this.execute("part " + s);
    }
    
    public void leaveQuery(final String s) {
        final Query query = this.getQuery(s, false);
        if (query == null) {
            return;
        }
        this._listeners.sendEvent("sourceRemoved", query, this);
        this.deleteQuery(s);
    }
    
    public void leave() {
        this.leaveStatus("");
    }
    
    public void leaveStatus(final String s) {
        if (this._status == null) {
            return;
        }
        if (this.isConnected()) {
            this._serverLeaving = true;
            this.disconnect();
        }
        else {
            this._listeners.sendEvent("sourceRemoved", this._status, this);
            this.deleteStatus("");
            this._listeners.sendEvent("serverLeft", this);
        }
    }
    
    public void leaveChanList(final String s) {
        this._listeners.sendEvent("sourceRemoved", this.getChanList(s), this);
        this.deleteChanList(s);
    }
    
    private void deleteSource(final Source source) {
        if (source == this._defaultSource) {
            this._defaultSource = null;
        }
        source.release();
    }
    
    private void deleteChannel(final String s) {
        this.deleteSource(this._channels.remove(s.toLowerCase(Locale.ENGLISH)));
    }
    
    private void deleteQuery(final String s) {
        this.deleteSource(this._queries.remove(s.toLowerCase(Locale.ENGLISH)));
    }
    
    private void deleteChanList(final String s) {
        this.deleteSource(this._chanlist.remove(s.toLowerCase(Locale.ENGLISH)));
    }
    
    private void deleteStatus(final String s) {
        this.deleteSource(this._status);
        this._status = null;
    }
    
    public String getServerName() {
        if (this._name.length() != 0) {
            return this._name;
        }
        if (this._tryServerIndex < this._host.length) {
            return this._host[this._tryServerIndex];
        }
        return this._host[0];
    }
    
    public Status getStatus() {
        return this._status;
    }
    
    public void addServerListener(final ServerListener serverListener) {
        this._listeners.addListener(serverListener);
    }
    
    public void removeServerListener(final ServerListener serverListener) {
        this._listeners.removeListener(serverListener);
    }
    
    public void addReplyServerListener(final ReplyServerListener replyServerListener) {
        this._replylisteners.addListener(replyServerListener);
    }
    
    public void addMessageServerListener(final MessageServerListener messageServerListener) {
        this._messagelisteners.addListener(messageServerListener);
    }
    
    public void removeReplyServerListener(final ReplyServerListener replyServerListener) {
        this._replylisteners.removeListener(replyServerListener);
    }
    
    public void removeMessageServerListener(final MessageServerListener messageServerListener) {
        this._messagelisteners.removeListener(messageServerListener);
    }
    
    public char[] getChannelPrefixes() {
        return this._channelPrefixes;
    }
    
    public char[] getNickPrefixes() {
        return this._nickPrefixes;
    }
    
    public char[] getNickModes() {
        return this._nickModes;
    }
    
    public char[][] getChannelModes() {
        return this._globalModes;
    }
    
    public String getNickPrefix(final String s) {
        if (s.length() == 0) {
            return "";
        }
        final char char1 = s.charAt(0);
        for (int i = 0; i < this._nickModes.length; ++i) {
            if (this._nickModes[i] == char1) {
                return "" + this._nickPrefixes[i];
            }
        }
        return "";
    }
    
    public String getNickMode(final String s) {
        if (s.length() == 0) {
            return "";
        }
        final char char1 = s.charAt(0);
        for (int i = 0; i < this._nickPrefixes.length; ++i) {
            if (this._nickPrefixes[i] == char1) {
                return "" + this._nickModes[i];
            }
        }
        return "";
    }
    
    private void setNicks(final Channel channel, final Vector vector) {
        final String[] array = new String[vector.size()];
        final String[] array2 = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
            array2[i] = "";
            if (array[i].length() > 0) {
                array2[i] = this.getNickMode("" + array[i].charAt(0));
                if (array2[i].length() != 0) {
                    array[i] = array[i].substring(1);
                }
            }
        }
        channel.setNicks(array, array2);
    }
    
    private void decodeVariable(final String s, String s2) {
        if (s.toLowerCase(Locale.ENGLISH).equals("prefix")) {
            if (!s2.startsWith("(")) {
                return;
            }
            final int index = s2.indexOf(")");
            if (index < 0) {
                return;
            }
            final String substring = s2.substring(1, index);
            final String substring2 = s2.substring(index + 1);
            if (substring2.length() != substring.length()) {
                return;
            }
            this._nickModes = new char[substring.length()];
            for (int i = 0; i < substring.length(); ++i) {
                this._nickModes[i] = substring.charAt(i);
            }
            this._nickPrefixes = new char[substring.length()];
            for (int j = 0; j < substring2.length(); ++j) {
                this._nickPrefixes[j] = substring2.charAt(j);
            }
        }
        else if (s.toLowerCase(Locale.ENGLISH).equals("chantypes")) {
            this._channelPrefixes = new char[s2.length()];
            for (int k = 0; k < this._channelPrefixes.length; ++k) {
                this._channelPrefixes[k] = s2.charAt(k);
            }
        }
        else if (s.toLowerCase(Locale.ENGLISH).equals("chanmodes")) {
            final int index2 = s2.indexOf(44);
            if (index2 < 0) {
                return;
            }
            final String substring3 = s2.substring(0, index2);
            s2 = s2.substring(index2 + 1);
            final int index3 = s2.indexOf(44);
            if (index3 < 0) {
                return;
            }
            final String substring4 = s2.substring(0, index3);
            s2 = s2.substring(index3 + 1);
            final int index4 = s2.indexOf(44);
            if (index4 < 0) {
                return;
            }
            final String substring5 = s2.substring(0, index4);
            final String substring6 = s2.substring(index4 + 1);
            (this._globalModes = new char[4][])[0] = new char[substring3.length()];
            for (int l = 0; l < substring3.length(); ++l) {
                this._globalModes[0][l] = substring3.charAt(l);
            }
            this._globalModes[1] = new char[substring4.length()];
            for (int n = 0; n < substring4.length(); ++n) {
                this._globalModes[1][n] = substring4.charAt(n);
            }
            this._globalModes[2] = new char[substring5.length()];
            for (int n2 = 0; n2 < substring5.length(); ++n2) {
                this._globalModes[2][n2] = substring5.charAt(n2);
            }
            this._globalModes[3] = new char[substring6.length()];
            for (int n3 = 0; n3 < substring6.length(); ++n3) {
                this._globalModes[3][n3] = substring6.charAt(n3);
            }
        }
    }
    
    private void learnServerVariables(final String[] array) {
        for (int i = 1; i < array.length; ++i) {
            final String s = array[i];
            final int index = s.indexOf(61);
            String substring;
            String substring2;
            if (index < 0) {
                substring = s;
                substring2 = "";
            }
            else {
                substring = s.substring(0, index);
                substring2 = s.substring(index + 1);
            }
            this.decodeVariable(substring, substring2);
        }
        this._mode = new ModeHandler(this._globalModes, this._nickModes);
    }
    
    public void replyReceived(final String s, final String s2, final String[] array) {
        final Object[] sendEvent = this._replylisteners.sendEvent("replyReceived", new Object[] { s, s2, array, this });
        for (int i = 0; i < sendEvent.length; ++i) {
            if (sendEvent[i]) {
                return;
            }
        }
        if (s2.equals("324")) {
            final Channel channel = this.getChannel(array[1], false);
            if (channel != null) {
                String string = "";
                for (int j = 2; j < array.length; ++j) {
                    string = string + " " + array[j];
                }
                channel.applyMode(string.substring(1), "");
            }
        }
        else if (s2.equals("332")) {
            final Channel channel2 = this.getChannel(array[1], false);
            if (channel2 != null) {
                channel2.setTopic(array[2], "");
            }
        }
        else if (s2.equals("353")) {
            int n = 1;
            if (array[1].length() == 1) {
                ++n;
            }
            final Channel channel3 = this.getChannel(array[n], false);
            if (channel3 != null) {
                String string2 = "";
                final Vector vector = new Vector<String>();
                for (int k = 0; k < array[n + 1].length(); ++k) {
                    final char char1 = array[n + 1].charAt(k);
                    if (char1 == ' ') {
                        if (string2.length() > 0) {
                            vector.insertElementAt(string2, vector.size());
                        }
                        string2 = "";
                    }
                    else {
                        string2 += char1;
                    }
                }
                if (string2.length() > 0) {
                    vector.insertElementAt(string2, vector.size());
                }
                this.setNicks(channel3, vector);
            }
        }
        else if (s2.equals("001")) {
            final String nick = array[0];
            if (!nick.equals(this._nick)) {
                this._nick = nick;
                if (this._status != null) {
                    this._status.nickChanged(nick);
                }
            }
            this._connected = true;
            this._listeners.sendEvent("serverConnected", this);
        }
        else if (s2.equals("005")) {
            this.learnServerVariables(array);
        }
        else if (s2.equals("321")) {
            this.getChanList(this._host[this._tryServerIndex]).begin();
        }
        else if (s2.equals("322")) {
            final String s3 = array[1];
            final int intValue = new Integer(array[2]);
            if (intValue < 32767 && this.isChannel(s3)) {
                this.getChanList(this._host[this._tryServerIndex]).addChannel(new ChannelInfo(s3, array[3], intValue));
            }
        }
        else if (s2.equals("323")) {
            this.getChanList(this._host[this._tryServerIndex]).end();
        }
        else if (s2.equals("433")) {
            if (!this._connected) {
                this.nickUsed();
                this.register();
            }
        }
        else if (s2.equals("473") || s2.equals("471") || s2.equals("403") || s2.equals("474") || s2.equals("475") || s2.equals("476") || s2.equals("405")) {
            final String s4 = array[1];
            final Channel channel4 = this.getChannel(s4, false);
            if (channel4 != null) {
                this.sendStatusMessage(this.getText(1290, s4));
                this._listeners.sendEvent("sourceRemoved", channel4, this);
                this.deleteChannel(s4);
            }
        }
        else if (s2.equals("442")) {
            final Channel channel5 = this.getChannel(array[1], false);
            if (channel5 != null) {
                this._listeners.sendEvent("sourceRemoved", channel5, this);
                this.deleteChannel(channel5.getName());
            }
        }
    }
    
    private String extractNick(final String s) {
        final int index = s.indexOf(33);
        if (index == -1) {
            return s;
        }
        return s.substring(0, index);
    }
    
    private boolean isChannel(final String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < this._channelPrefixes.length; ++i) {
            if (s.charAt(0) == this._channelPrefixes[i]) {
                return true;
            }
        }
        return false;
    }
    
    private void globalNickRemove(final String s, final String s2) {
        final Enumeration<Channel> elements = this._channels.elements();
        while (elements.hasMoreElements()) {
            final Channel channel = elements.nextElement();
            if (channel.hasNick(s)) {
                channel.quitNick(s, s2);
            }
        }
    }
    
    private void globalNickChange(final String s, final String s2) {
        final Enumeration<Channel> elements = this._channels.elements();
        while (elements.hasMoreElements()) {
            final Channel channel = elements.nextElement();
            if (channel.hasNick(s)) {
                channel.changeNick(s, s2);
            }
        }
        final Query query = this._queries.get(s.toLowerCase(Locale.ENGLISH));
        if (query != null) {
            this._queries.remove(s.toLowerCase(Locale.ENGLISH));
            query.changeNick(s2);
            final Query query2 = this._queries.get(s2.toLowerCase(Locale.ENGLISH));
            if (query2 != null) {
                query2.leave();
            }
            this._queries.put(s2.toLowerCase(Locale.ENGLISH), query);
        }
    }
    
    public synchronized boolean ignore(final String s) {
        return this._ignoreList.get(s) != null;
    }
    
    public synchronized void addIgnore(final String s) {
        this._ignoreList.put(s, s);
    }
    
    public synchronized void removeIgnore(final String s) {
        this._ignoreList.remove(s);
    }
    
    public void messageReceived(final String s, String lowerCase, final String[] array) {
        final Object[] sendEvent = this._messagelisteners.sendEvent("messageReceived", new Object[] { s, lowerCase, array, this });
        for (int i = 0; i < sendEvent.length; ++i) {
            if (sendEvent[i]) {
                return;
            }
        }
        String string = "";
        for (int j = 0; j < array.length; ++j) {
            string = string + " " + array[j];
        }
        lowerCase = lowerCase.toLowerCase(Locale.ENGLISH);
        final String nick = this.extractNick(s);
        if (lowerCase.equals("notice")) {
            if (!this.ignore(nick) && !this._filter.performFromNotice(nick, array[1]) && this._defaultSource != null) {
                this._defaultSource.noticeReceived(nick, array[1]);
            }
        }
        else if (lowerCase.equals("privmsg")) {
            if (!this.ignore(nick)) {
                if (this.isChannel(array[0])) {
                    if (!this._filter.performFromChannelMessage(array[0], nick, array[1])) {
                        final Channel channel = this.getChannel(array[0], false);
                        if (channel != null) {
                            channel.messageReceived(nick, array[1]);
                        }
                    }
                }
                else if (!this._filter.performFromNickMessage(nick, array[1])) {
                    final Query query = this.getQuery(nick, false);
                    if (query != null) {
                        query.messageReceived(nick, array[1]);
                    }
                }
            }
        }
        else if (lowerCase.equals("join")) {
            if (!nick.equals(this.getNick())) {
                final Channel channel2 = this.getChannel(array[0], false);
                if (channel2 != null) {
                    channel2.joinNick(nick, "");
                }
            }
            else {
                final Channel channel3 = this.getChannel(array[0], true);
                if (channel3 != null) {
                    channel3.resetNicks();
                    this.execute("mode " + array[0]);
                }
            }
        }
        else if (lowerCase.equals("part")) {
            final Channel channel4 = this.getChannel(array[0], false);
            if (channel4 != null) {
                if (array.length > 1) {
                    channel4.partNick(nick, array[1]);
                }
                else {
                    channel4.partNick(nick, "");
                }
                if (nick.equals(this.getNick())) {
                    this._listeners.sendEvent("sourceRemoved", channel4, this);
                    this.deleteChannel(channel4.getName());
                }
            }
        }
        else if (lowerCase.equals("kick")) {
            final Channel channel5 = this.getChannel(array[0], false);
            if (channel5 != null) {
                final String s2 = array[1];
                String s3 = "";
                if (array.length > 2) {
                    s3 = array[2];
                }
                channel5.kickNick(s2, nick, s3);
                if (s2.equals(this.getNick())) {
                    if (super._ircConfiguration.getB("autorejoin")) {
                        channel5.report(this.getText(1289, channel5.getName()));
                        this.execute("join " + array[0]);
                    }
                    else {
                        this._listeners.sendEvent("sourceRemoved", channel5, this);
                        this.deleteChannel(channel5.getName());
                    }
                }
            }
        }
        else if (lowerCase.equals("topic")) {
            final Channel channel6 = this.getChannel(array[0], false);
            if (channel6 != null) {
                channel6.setTopic(array[1], nick);
            }
        }
        else if (lowerCase.equals("mode")) {
            String string2 = "";
            for (int k = 1; k < array.length; ++k) {
                string2 = string2 + array[k] + " ";
            }
            if (this.isChannel(array[0])) {
                final Channel channel7 = this.getChannel(array[0], false);
                if (channel7 != null) {
                    final MultiModeHandler multiModeHandler = new MultiModeHandler(string2, this._globalModes, this._nickModes);
                    while (!multiModeHandler.terminated()) {
                        multiModeHandler.next();
                        if (multiModeHandler.isPrefix() || multiModeHandler.isModeA()) {
                            channel7.applyUserMode(multiModeHandler.getParameter(), multiModeHandler.getMode(), nick);
                        }
                        else if (multiModeHandler.hasParameter()) {
                            channel7.applyMode(multiModeHandler.getMode() + " " + multiModeHandler.getParameter(), nick);
                        }
                        else {
                            channel7.applyMode(multiModeHandler.getMode(), nick);
                        }
                    }
                }
            }
            else if (nick.equals(this.getNick())) {
                this._mode.apply(string2);
                if (this._status != null) {
                    this._status.modeChanged(this.getMode());
                }
            }
        }
        else if (lowerCase.equals("nick")) {
            if (nick.equals(this.getNick())) {
                this._nick = array[0];
                if (this._status != null) {
                    this._status.nickChanged(this.getNick());
                }
            }
            this.globalNickChange(nick, array[0]);
        }
        else if (lowerCase.equals("quit")) {
            if (array.length > 0) {
                this.globalNickRemove(nick, array[0]);
            }
            else {
                this.globalNickRemove(nick, "");
            }
        }
        else if (lowerCase.equals("ping")) {
            this.execute("pong :" + array[0]);
        }
        else if (lowerCase.equals("invite")) {
            final String s4 = array[0];
            final String s5 = array[1];
            if (s4.equals(this.getNick()) && this._status != null) {
                this._status.invited(s5, nick);
            }
        }
        else if (lowerCase.equals("error")) {
            this.sendStatusMessage(this.getText(1288, array[0]));
        }
    }
    
    public String getNick() {
        return this._nick;
    }
    
    public String getUserName() {
        return this._userName;
    }
    
    public String getMode() {
        return this._mode.getMode();
    }
    
    public void say(final String s, final String s2) {
        this.execute("PRIVMSG " + s + " :" + s2);
    }
    
    public void execute(String s) {
        final int index = s.indexOf(32);
        if (index >= 0) {
            final String lowerCase = s.substring(0, index).toLowerCase(Locale.ENGLISH);
            if (lowerCase.equals("join")) {
                String s2 = s.substring(index + 1);
                final int index2 = s2.indexOf(32);
                if (index2 >= 0) {
                    s2 = s2.substring(0, index2);
                }
                if (!super._ircConfiguration.mayJoin(s2)) {
                    return;
                }
            }
            else if (lowerCase.equals("part")) {
                String s3 = s.substring(index + 1);
                final int index3 = s3.indexOf(32);
                if (index3 >= 0) {
                    s3 = s3.substring(0, index3);
                }
                if (!super._ircConfiguration.mayLeave(s3)) {
                    return;
                }
            }
        }
        final int index4 = s.indexOf(32);
        if (index4 > 0) {
            s = s.substring(0, index4).toUpperCase(Locale.ENGLISH) + " " + s.substring(index4 + 1);
        }
        else {
            s = s.toUpperCase(Locale.ENGLISH);
        }
        this.sendString(s);
    }
    
    private void sendString(final String s) {
        try {
            this._protocol.sendString(s);
        }
        catch (Exception ex) {
            this.sendStatusMessage(this.getText(1288, ex.getMessage()));
        }
    }
}
