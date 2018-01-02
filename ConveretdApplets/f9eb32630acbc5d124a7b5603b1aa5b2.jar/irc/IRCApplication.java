// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import irc.gui.GUISource;
import java.util.Enumeration;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import irc.plugin.Plugin;
import java.lang.reflect.InvocationTargetException;
import java.awt.Container;
import java.awt.Frame;
import java.util.Hashtable;
import java.util.Vector;
import irc.gui.IRCInterface;
import irc.ident.IdentWrapper;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import irc.gui.IRCInterfaceListener;
import irc.ident.IdentListener;

public class IRCApplication extends IRCObject implements ServerListener, ServerManager, IdentListener, IRCInterfaceListener, WindowListener, ActionListener, PluginManager
{
    private DefaultSource _defaultSource;
    private Interpretor _inter;
    private IdentWrapper _ident;
    private StartupConfiguration _start;
    private IRCInterface _interface;
    private Vector _plugins;
    private Hashtable _pluginsTable;
    private Frame _frame;
    private Container _container;
    private Hashtable _servers;
    private Object _nickLock;
    
    public IRCApplication(final IRCConfiguration ircConfiguration, final StartupConfiguration start, final Container container) {
        super(ircConfiguration);
        this._nickLock = new Object();
        this._container = container;
        this._start = start;
        this._plugins = new Vector();
        this._pluginsTable = new Hashtable();
        final String s = ircConfiguration.getS("gui");
        try {
            this._interface = (IRCInterface)Class.forName("irc.gui." + s + ".Interface").getDeclaredConstructor(ircConfiguration.getClass()).newInstance(ircConfiguration);
        }
        catch (InvocationTargetException ex) {
            ex.getTargetException().printStackTrace();
            throw new Error("Unable to load interface " + s + " : " + ex.getTargetException());
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new Error("Unable to load interface " + s + " : " + t);
        }
        this._servers = new Hashtable();
        (this._defaultSource = new DefaultSource(super._ircConfiguration)).setInterpretor(new DefaultInterpretor(super._ircConfiguration, this._start, this, this));
    }
    
    public synchronized void init() {
        this.loadPlugin(this._interface);
        final String[] plugins = this._start.getPlugins();
        for (int i = 0; i < plugins.length; ++i) {
            this.loadPlugin(plugins[i]);
        }
        this._interface.addIRCInterfaceListener(this);
        if (this._container == null) {
            (this._frame = new Frame()).addWindowListener(this);
            if (this._interface.getComponent() != null) {
                this._frame.add(this._interface.getComponent());
            }
            this._frame.setFont(new Font("", 0, 12));
            this._frame.setSize(640, 400);
            this._frame.show();
        }
        else {
            this._frame = null;
            this._container.removeAll();
            this._container.setLayout(new GridLayout(1, 1));
            if (this._interface.getComponent() != null) {
                this._container.add(this._interface.getComponent());
            }
        }
        (this._inter = new CTCPInterpretor(super._ircConfiguration, this._defaultSource.getInterpretor(), this)).addLast(this._interface.getInterpretor());
        if (super._ircConfiguration.getB("useidentserver")) {
            try {
                this._ident = new IdentWrapper(super._ircConfiguration);
                final Exception start = this._ident.start(this._start.getName(), this);
                if (start != null) {
                    this._defaultSource.report("\u00036*** " + this.getText(513, start.getMessage()));
                }
            }
            catch (Throwable t) {
                super._ircConfiguration.internalError("ident error", t, "plouf@pjirc.com");
            }
        }
        else {
            this._ident = null;
        }
        final String[] initialization = super._ircConfiguration.getInitialization();
        for (int j = 0; j < initialization.length; ++j) {
            this._defaultSource.sendString(initialization[j]);
        }
        final IRCServer ircServer = new IRCServer(super._ircConfiguration, this, this._start.getNick(), this._start.getAltNick(), this._start.getName(), this._start.getAlias());
        ircServer.setServers(this._start.getHost(), this._start.getPort(), this._start.getPass());
        this.newServer(ircServer, super._ircConfiguration.getB("autoconnection"));
        this.requestSourceFocus();
    }
    
    public IRCInterface getIRCInterface() {
        return this._interface;
    }
    
    public void newServer(final Server server, final boolean b) {
        server.addServerListener(this);
        this._servers.put(server, server);
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().serverCreated(server);
        }
        server.enumerateSourcesAsCreated(this);
        if (b) {
            server.connect();
        }
    }
    
    public synchronized void uninit() {
        final Enumeration<Server> keys = this._servers.keys();
        while (keys.hasMoreElements()) {
            keys.nextElement().leave();
        }
        if (this._ident != null) {
            this._ident.stop();
        }
        this._interface.removeIRCInterfaceListener(this);
        if (this._frame != null) {
            this._frame.removeWindowListener(this);
        }
        this._frame = null;
        while (this._plugins.size() > 0) {
            this.unloadPlugin(this._plugins.elementAt(this._plugins.size() - 1));
        }
        this._pluginsTable = new Hashtable();
        if (this._container != null) {
            this._container.removeAll();
        }
        EventDispatcher.clearCache();
    }
    
    public boolean loadPlugin(final String s) {
        if (this._pluginsTable.get(s) != null) {
            return false;
        }
        Plugin plugin;
        try {
            plugin = (Plugin)Class.forName("irc.plugin." + s).getDeclaredConstructor(super._ircConfiguration.getClass()).newInstance(super._ircConfiguration);
            this.loadPlugin(plugin);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
        this._pluginsTable.put(s, plugin);
        return true;
    }
    
    public boolean unloadPlugin(final String s) {
        final Plugin plugin = this._pluginsTable.get(s);
        if (plugin == null) {
            return false;
        }
        this._pluginsTable.remove(s);
        this.unloadPlugin(plugin);
        return true;
    }
    
    private void loadPlugin(final Plugin plugin) {
        plugin.setIRCApplication(this);
        plugin.load();
        this._plugins.insertElementAt(plugin, this._plugins.size());
        plugin.sourceCreated(this._defaultSource, Boolean.TRUE);
        final Enumeration<Server> keys = this._servers.keys();
        while (keys.hasMoreElements()) {
            final Server server = keys.nextElement();
            plugin.serverCreated(server);
            server.enumerateSourcesAsCreated(new addHandler(plugin));
        }
    }
    
    private void unloadPlugin(final Plugin plugin) {
        for (int i = 0; i < this._plugins.size(); ++i) {
            if (this._plugins.elementAt(i) == plugin) {
                this._plugins.removeElementAt(i);
                final Enumeration<Server> keys = this._servers.keys();
                while (keys.hasMoreElements()) {
                    final Server server = keys.nextElement();
                    server.enumerateSourcesAsRemoved(new removeHandler(plugin));
                    plugin.serverRemoved(server);
                }
                plugin.sourceRemoved(this._defaultSource);
                plugin.unload();
                return;
            }
        }
    }
    
    public void sourceCreated(final Source source, final Server server, final Boolean b) {
        source.getInterpretor().addLast(this._inter);
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().sourceCreated(source, b);
        }
    }
    
    public void sourceRemoved(final Source source, final Server server) {
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().sourceRemoved(source);
        }
    }
    
    public void serverLeft(final Server server) {
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().serverRemoved(server);
        }
        this._servers.remove(server);
        server.removeServerListener(this);
    }
    
    private Frame getParentFrame() {
        if (this._frame != null) {
            return this._frame;
        }
        for (Container container = this._container; container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                return (Frame)container;
            }
        }
        return null;
    }
    
    public Container getContainer() {
        if (this._frame != null) {
            return this._frame;
        }
        return this._container;
    }
    
    public Object specialServerRequest(final String s, final Server server, final Object[] array) {
        if (s.equals("DCCFileRequest")) {
            return super._ircConfiguration.getSecurityProvider().getSaveFile(array[1].toString(), this.getText(769) + " [" + array[1] + "]");
        }
        if (s.equals("DCCChatRequest")) {
            return new Boolean(super._ircConfiguration.getSecurityProvider().confirm(this.getParentFrame(), this.getText(1820), this.getText(1821, (String)array[0])));
        }
        super._ircConfiguration.internalError("Unknown request : " + s, null, "plouf@pjirc.com");
        return null;
    }
    
    public void serverConnected(final Server server) {
        for (int i = 0; i < this._start.getCommands().length; ++i) {
            if (this._start.getCommands()[i].startsWith("/") && server instanceof IRCServer) {
                ((IRCServer)server).getStatus().sendString(this._start.getCommands()[i]);
            }
            else {
                server.execute(this._start.getCommands()[i]);
            }
        }
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().serverConnected(server);
        }
    }
    
    public void serverDisconnected(final Server server) {
        final Enumeration<Plugin> elements = this._plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().serverDisconnected(server);
        }
    }
    
    public void identRequested(final String s, final Integer n, final String s2) {
        this._defaultSource.report("\u00036*** " + this.getText(514, s));
        String s3 = null;
        switch (n) {
            case -1: {
                s3 = this.getText(515);
                break;
            }
            case 0: {
                s3 = this.getText(516, s2);
                break;
            }
            case 1: {
                s3 = this.getText(516, this.getText(517) + " : " + s2);
                break;
            }
            case 2: {
                s3 = this.getText(518);
                break;
            }
            default: {
                s3 = this.getText(523);
                break;
            }
        }
        this._defaultSource.report("\u00036*** " + s3);
    }
    
    public void identRunning(final Integer n) {
        this._defaultSource.report("\u00036*** " + this.getText(519, n + ""));
    }
    
    public void identLeaving(final String s) {
        this._defaultSource.report("\u00036*** " + this.getText(520, s));
    }
    
    public void activeChanged(final GUISource guiSource, final IRCInterface ircInterface) {
        if (guiSource != null) {
            if (guiSource.getSource().mayDefault()) {
                guiSource.getSource().getServer().setDefaultSource(guiSource.getSource());
            }
            if (this._frame != null) {
                this._frame.setTitle(guiSource.getTitle());
            }
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this._frame) {
            EventDispatcher.dispatchEventAsync(this, "uninit", new Object[0]);
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        ((Frame)windowEvent.getSource()).hide();
        ((Frame)windowEvent.getSource()).dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private GUISource getActiveSource() {
        return this._interface.getActive();
    }
    
    private Source getSource(final String s, final String s2, final String s3) {
        final Enumeration<Server> keys = this._servers.keys();
        while (keys.hasMoreElements()) {
            final Server server = keys.nextElement();
            if (server.getServerName().equals(s) || s.length() == 0) {
                final Enumeration sources = server.getSources();
                while (sources.hasMoreElements()) {
                    final Source source = sources.nextElement();
                    if (source.getType().equals(s2) && source.getName().equals(s3)) {
                        return source;
                    }
                }
            }
        }
        return null;
    }
    
    public void requestSourceFocus() {
        final GUISource activeSource = this.getActiveSource();
        if (activeSource == null) {
            return;
        }
        activeSource.requestFocus();
    }
    
    public void requestSourceFocus(final String s, final String s2, final String s3) {
        final Source source = this.getSource(s, s2, s3);
        if (source != null) {
            final GUISource guiSource = this._interface.getGUISource(source);
            if (guiSource != null) {
                this._interface.setActive(guiSource);
            }
        }
    }
    
    public void sendString(final String s, final String s2, final String s3, final String s4) {
        final Source source = this.getSource(s, s2, s3);
        if (source != null) {
            source.sendString(s4);
        }
    }
    
    public void sendString(final String s) {
        final GUISource activeSource = this.getActiveSource();
        if (activeSource == null) {
            return;
        }
        activeSource.getSource().sendString(s);
    }
    
    public void setFieldText(final String fieldText) {
        final GUISource activeSource = this.getActiveSource();
        if (activeSource == null) {
            return;
        }
        activeSource.setFieldText(fieldText);
    }
    
    public String getFieldText() {
        final GUISource activeSource = this.getActiveSource();
        if (activeSource == null) {
            return "";
        }
        return activeSource.getFieldText();
    }
    
    public void validateText() {
        final GUISource activeSource = this.getActiveSource();
        if (activeSource == null) {
            return;
        }
        activeSource.validateText();
    }
    
    public void sendPluginEvent(final String s, final Object o) {
        final Plugin plugin = this._pluginsTable.get(s);
        if (plugin == null) {
            return;
        }
        plugin.externalEvent(o);
    }
    
    public Object getPluginValue(final String s, final Object o) {
        final Plugin plugin = this._pluginsTable.get(s);
        if (plugin == null) {
            return null;
        }
        return plugin.getValue(o);
    }
    
    public String[] cannotUseRequestedNicknames(final Server server) {
        synchronized (this._nickLock) {
            if (this._interface.getComponent() != null) {
                this._interface.getComponent().setEnabled(false);
            }
            if (this._frame != null) {
                this._frame.setEnabled(false);
            }
            final Frame frame = new Frame();
            frame.setLayout(new FlowLayout());
            frame.setSize(200, 65);
            frame.setTitle(this.getText(1818));
            final TextField textField = new TextField(this._start.getNick());
            final Button button = new Button("Ok");
            button.addActionListener(this);
            frame.add(textField);
            frame.add(button);
            frame.show();
            try {
                this._nickLock.wait();
            }
            catch (InterruptedException ex) {}
            frame.hide();
            frame.remove(button);
            frame.remove(textField);
            frame.dispose();
            final String[] array = { textField.getText() };
            if (this._frame != null) {
                this._frame.setEnabled(true);
            }
            if (this._interface.getComponent() != null) {
                this._interface.getComponent().setEnabled(true);
            }
            return array;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this._nickLock) {
            this._nickLock.notifyAll();
        }
    }
    
    private static void usage() {
        System.out.println("Usage :");
        System.out.println("   java irc.IRCApplication -f configfile");
        System.out.println("or java irc.IRCApplication -p nick fullname host gui");
        System.out.println("");
        System.out.println("Without any parameter, '-f pjirc.cfg' parameters are assumed.");
    }
    
    public static void go(final String[] array) {
        final LocalFileHandler localFileHandler = new LocalFileHandler();
        try {
            IRCConfiguration ircConfiguration;
            StartupConfiguration loadStartupConfiguration;
            if (array.length == 0 || (array.length >= 2 && array[0].equals("-f"))) {
                String s = "pjirc.cfg";
                if (array.length >= 2) {
                    s = array[1];
                }
                final ConfigurationLoader configurationLoader = new ConfigurationLoader(new StreamParameterProvider(localFileHandler.getInputStream(s)), new NullURLHandler(), new AWTImageLoader(), new NullSoundHandler(), localFileHandler);
                ircConfiguration = configurationLoader.loadIRCConfiguration();
                loadStartupConfiguration = configurationLoader.loadStartupConfiguration();
            }
            else {
                if (array.length < 5 || !array[0].equals("-p")) {
                    usage();
                    return;
                }
                ircConfiguration = new ConfigurationLoader(new StreamParameterProvider(null), new NullURLHandler(), new AWTImageLoader(), new NullSoundHandler(), localFileHandler).loadIRCConfiguration();
                ircConfiguration.set("gui", array[4]);
                loadStartupConfiguration = new StartupConfiguration(array[1], "", array[2], new String[] { "" }, new String[] { array[3] }, new int[] { 6667 }, "", new String[0], new String[0]);
            }
            EventDispatcher.dispatchEventAsyncAndWaitEx(new IRCApplication(ircConfiguration, loadStartupConfiguration, null), "init", new Object[0]);
        }
        catch (Throwable t) {
            System.out.println("Error : " + t.getMessage());
            t.printStackTrace();
        }
    }
    
    public static void main(final String[] array) {
        go(array);
    }
    
    class addHandler implements ServerListener
    {
        private Plugin _plugin;
        
        public addHandler(final Plugin plugin) {
            this._plugin = plugin;
        }
        
        public void serverConnected(final Server server) {
        }
        
        public void serverDisconnected(final Server server) {
        }
        
        public void serverLeft(final Server server) {
        }
        
        public String[] cannotUseRequestedNicknames(final Server server) {
            return null;
        }
        
        public void sourceCreated(final Source source, final Server server, final Boolean b) {
            this._plugin.sourceCreated(source, b);
        }
        
        public void sourceRemoved(final Source source, final Server server) {
        }
        
        public Object specialServerRequest(final String s, final Server server, final Object[] array) {
            return null;
        }
    }
    
    class removeHandler implements ServerListener
    {
        private Plugin _plugin;
        
        public removeHandler(final Plugin plugin) {
            this._plugin = plugin;
        }
        
        public void serverConnected(final Server server) {
        }
        
        public void serverDisconnected(final Server server) {
        }
        
        public void serverLeft(final Server server) {
        }
        
        public String[] cannotUseRequestedNicknames(final Server server) {
            return null;
        }
        
        public void sourceCreated(final Source source, final Server server, final Boolean b) {
        }
        
        public void sourceRemoved(final Source source, final Server server) {
            this._plugin.sourceRemoved(source);
        }
        
        public Object specialServerRequest(final String s, final Server server, final Object[] array) {
            return null;
        }
    }
}
