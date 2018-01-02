// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.util.Hashtable;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.Iterator;
import java.util.Map;
import java.util.Enumeration;
import de.mud.jta.event.FocusStatusListener;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.PrintJob;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.mud.jta.event.ReturnFocusRequest;
import de.mud.jta.event.SocketRequest;
import java.lang.reflect.InvocationTargetException;
import java.awt.Button;
import de.mud.jta.event.AppletRequest;
import de.mud.jta.event.SoundListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.net.URL;
import java.awt.datatransfer.Clipboard;
import java.util.Properties;
import javax.swing.RootPaneContainer;
import javax.swing.JApplet;

public class Applet extends JApplet
{
    private static final int debug = 0;
    private String frameTitle;
    private RootPaneContainer appletFrame;
    private Properties options;
    private Common pluginLoader;
    private String host;
    private String port;
    private boolean disconnect;
    private boolean connect;
    private boolean disconnectCloseWindow;
    private Plugin focussedPlugin;
    private Clipboard clipboard;
    private boolean online;
    static /* synthetic */ Class class$de$mud$jta$Applet;
    static /* synthetic */ Class class$java$lang$String;
    
    public Applet() {
        this.frameTitle = null;
        this.options = new Properties();
        this.disconnect = true;
        this.connect = false;
        this.disconnectCloseWindow = true;
        this.online = false;
    }
    
    public void init() {
        if (this.pluginLoader == null) {
            try {
                this.options.load(((Applet.class$de$mud$jta$Applet == null) ? (Applet.class$de$mud$jta$Applet = class$("de.mud.jta.Applet")) : Applet.class$de$mud$jta$Applet).getResourceAsStream("/de/mud/jta/default.conf"));
            }
            catch (Exception e4) {
                try {
                    final URL url = new URL(this.getCodeBase() + "default.conf");
                    this.options.load(url.openStream());
                }
                catch (Exception e5) {
                    System.err.println("jta: cannot load default.conf");
                    System.err.println("jta: try extracting it from the jar file");
                    System.err.println("jta: expected file here: " + this.getCodeBase() + "default.conf");
                }
            }
            String value;
            if ((value = this.getParameter("config")) != null) {
                final Properties appletParams = new Properties();
                URL url2 = null;
                try {
                    url2 = new URL(value);
                }
                catch (Exception e6) {
                    try {
                        url2 = new URL(this.getCodeBase() + value);
                    }
                    catch (Exception ce) {
                        System.err.println("jta: could not find config file: " + ce);
                    }
                }
                if (url2 != null) {
                    try {
                        appletParams.load(((Applet.class$de$mud$jta$Applet == null) ? (Applet.class$de$mud$jta$Applet = class$("de.mud.jta.Applet")) : Applet.class$de$mud$jta$Applet).getResourceAsStream("/de/mud/jta/" + value));
                        final Enumeration ape = appletParams.keys();
                        while (ape.hasMoreElements()) {
                            final String key = ape.nextElement();
                            ((Hashtable<String, String>)this.options).put(key, appletParams.getProperty(key));
                        }
                    }
                    catch (Exception e6) {
                        try {
                            appletParams.load(url2.openStream());
                            final Enumeration ape2 = appletParams.keys();
                            while (ape2.hasMoreElements()) {
                                final String key2 = ape2.nextElement();
                                ((Hashtable<String, String>)this.options).put(key2, appletParams.getProperty(key2));
                            }
                        }
                        catch (Exception e2) {
                            System.err.println("jta: could not load config file: " + e2);
                        }
                    }
                }
            }
            this.parameterOverride(this.options);
            this.pluginLoader = new Common(this.options);
            this.host = this.options.getProperty("Socket.host");
            if (this.host == null) {
                this.host = this.getCodeBase().getHost();
            }
            this.port = this.options.getProperty("Socket.port");
            if (this.port == null) {
                this.port = "23";
            }
            if (new Boolean(this.options.getProperty("Applet.connect"))) {
                this.connect = true;
            }
            if (!new Boolean(this.options.getProperty("Applet.disconnect"))) {
                this.disconnect = false;
            }
            if (!new Boolean(this.options.getProperty("Applet.disconnect.closeWindow"))) {
                this.disconnectCloseWindow = false;
            }
            this.frameTitle = this.options.getProperty("Applet.detach.title");
            if (new Boolean(this.options.getProperty("Applet.detach"))) {
                if (this.frameTitle == null) {
                    this.appletFrame = new JFrame("jta: " + this.host + (this.port.equals("23") ? "" : (" " + this.port)));
                }
                else {
                    this.appletFrame = new JFrame(this.frameTitle);
                }
            }
            else {
                this.appletFrame = this;
            }
            this.appletFrame.getContentPane().setLayout(new BorderLayout());
            final Map componentList = this.pluginLoader.getComponents();
            for (final String name : componentList.keySet()) {
                final Component c = componentList.get(name);
                if ((value = this.options.getProperty("layout." + name)) != null) {
                    this.appletFrame.getContentPane().add(value, c);
                }
                else {
                    System.err.println("jta: no layout property set for '" + name + "'");
                    System.err.println("jta: ignoring '" + name + "'");
                }
            }
            this.pluginLoader.registerPluginListener(new SoundListener() {
                public void playSound(final URL audioClip) {
                    Applet.this.getAudioClip(audioClip).play();
                }
            });
            this.pluginLoader.broadcast(new AppletRequest(this));
            if (this.appletFrame != this) {
                final String startText = this.options.getProperty("Applet.detach.startText");
                final String stopText = this.options.getProperty("Applet.detach.stopText");
                final Button close = new Button();
                final Vector privileges = Common.split(this.options.getProperty("Applet.Netscape.privilege"), ',');
                Class privilegeManager = null;
                Method enable = null;
                try {
                    privilegeManager = Class.forName("netscape.security.PrivilegeManager");
                    enable = privilegeManager.getMethod("enablePrivilege", (Applet.class$java$lang$String == null) ? (Applet.class$java$lang$String = class$("java.lang.String")) : Applet.class$java$lang$String);
                }
                catch (Exception e3) {
                    System.err.println("Applet: This is not Netscape ...");
                }
                if (privilegeManager != null && enable != null && privileges != null) {
                    for (int i = 0; i < privileges.size(); ++i) {
                        try {
                            enable.invoke(privilegeManager, privileges.elementAt(i));
                            System.out.println("Applet: access for '" + privileges.elementAt(i) + "' allowed");
                        }
                        catch (Exception e7) {
                            System.err.println("Applet: access for '" + privileges.elementAt(i) + "' denied");
                        }
                    }
                }
                try {
                    this.clipboard = this.appletFrame.getContentPane().getToolkit().getSystemClipboard();
                    System.err.println("Applet: acquired system clipboard: " + this.clipboard);
                }
                catch (Exception e3) {
                    System.err.println("Applet: system clipboard access denied: " + ((e3 instanceof InvocationTargetException) ? ((InvocationTargetException)e3).getTargetException() : e3));
                }
                finally {
                    if (this.clipboard == null) {
                        System.err.println("Applet: copy & paste only within the JTA");
                        this.clipboard = new Clipboard("de.mud.jta.Main");
                    }
                }
                if (new Boolean(this.options.getProperty("Applet.detach.immediately"))) {
                    if (new Boolean(this.options.getProperty("Applet.detach.fullscreen"))) {
                        ((JFrame)this.appletFrame).setSize(this.appletFrame.getContentPane().getToolkit().getScreenSize());
                    }
                    else {
                        ((JFrame)this.appletFrame).pack();
                    }
                    ((JFrame)this.appletFrame).show();
                    this.pluginLoader.broadcast(new SocketRequest(this.host, Integer.parseInt(this.port)));
                    this.pluginLoader.broadcast(new ReturnFocusRequest());
                    close.setLabel((startText != null) ? stopText : "Disconnect");
                }
                else {
                    close.setLabel((startText != null) ? startText : "Connect");
                }
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        if (((JFrame)Applet.this.appletFrame).isVisible()) {
                            Applet.this.pluginLoader.broadcast(new SocketRequest());
                            ((JFrame)Applet.this.appletFrame).setVisible(false);
                            close.setLabel((startText != null) ? startText : "Connect");
                        }
                        else {
                            if (Applet.this.frameTitle == null) {
                                ((JFrame)Applet.this.appletFrame).setTitle("jta: " + Applet.this.host + (Applet.this.port.equals("23") ? "" : (" " + Applet.this.port)));
                            }
                            if (new Boolean(Applet.this.options.getProperty("Applet.detach.fullscreen"))) {
                                ((JFrame)Applet.this.appletFrame).setSize(Applet.this.appletFrame.getContentPane().getToolkit().getScreenSize());
                            }
                            else {
                                ((JFrame)Applet.this.appletFrame).pack();
                            }
                            ((JFrame)Applet.this.appletFrame).show();
                            if (Applet.this.port == null || Applet.this.port.length() <= 0) {
                                Applet.this.port = "23";
                            }
                            Applet.this.getAppletContext().showStatus("Trying " + Applet.this.host + " " + Applet.this.port + " ...");
                            Applet.this.pluginLoader.broadcast(new SocketRequest(Applet.this.host, Integer.parseInt(Applet.this.port)));
                            Applet.this.pluginLoader.broadcast(new ReturnFocusRequest());
                            close.setLabel((stopText != null) ? stopText : "Disconnect");
                        }
                    }
                });
                this.getContentPane().setLayout(new BorderLayout());
                this.getContentPane().add("Center", close);
                final MenuBar mb = new MenuBar();
                final Menu file = new Menu("File");
                file.setShortcut(new MenuShortcut(70, true));
                MenuItem tmp;
                file.add(tmp = new MenuItem("Connect"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        Applet.this.pluginLoader.broadcast(new SocketRequest(Applet.this.host, Integer.parseInt(Applet.this.port)));
                    }
                });
                file.add(tmp = new MenuItem("Disconnect"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        Applet.this.pluginLoader.broadcast(new SocketRequest());
                    }
                });
                file.add(new MenuItem("-"));
                file.add(tmp = new MenuItem("Print"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        if (Applet.this.pluginLoader.getComponents().get("Terminal") != null) {
                            final PrintJob printJob = Applet.this.appletFrame.getContentPane().getToolkit().getPrintJob((Frame)Applet.this.appletFrame, "JTA Terminal", null);
                            Applet.this.pluginLoader.getComponents().get("Terminal").print(printJob.getGraphics());
                            printJob.end();
                        }
                    }
                });
                file.add(new MenuItem("-"));
                file.add(tmp = new MenuItem("Exit"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ((JFrame)Applet.this.appletFrame).setVisible(false);
                        Applet.this.pluginLoader.broadcast(new SocketRequest());
                        close.setLabel((startText != null) ? startText : "Connect");
                    }
                });
                mb.add(file);
                final Menu edit = new Menu("Edit");
                edit.setShortcut(new MenuShortcut(69, true));
                edit.add(tmp = new MenuItem("Copy"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        if (Applet.this.focussedPlugin instanceof VisualTransferPlugin) {
                            ((VisualTransferPlugin)Applet.this.focussedPlugin).copy(Applet.this.clipboard);
                        }
                    }
                });
                edit.add(tmp = new MenuItem("Paste"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        if (Applet.this.focussedPlugin instanceof VisualTransferPlugin) {
                            ((VisualTransferPlugin)Applet.this.focussedPlugin).paste(Applet.this.clipboard);
                        }
                    }
                });
                mb.add(edit);
                final Map menuList = this.pluginLoader.getMenus();
                for (final String name2 : menuList.keySet()) {
                    final Object o = menuList.get(name2);
                    if (o instanceof Menu) {
                        mb.add((Menu)o);
                    }
                }
                final Menu help = new Menu("Help");
                help.setShortcut(new MenuShortcut(156, true));
                help.add(tmp = new MenuItem("General"));
                tmp.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        Help.show(Applet.this.appletFrame.getContentPane(), Applet.this.options.getProperty("Help.url"));
                    }
                });
                mb.setHelpMenu(help);
                if (new Boolean(this.options.getProperty("Applet.detach.menuBar"))) {
                    ((JFrame)this.appletFrame).setMenuBar(mb);
                }
                try {
                    ((JFrame)this.appletFrame).addWindowListener(new WindowAdapter() {
                        public void windowClosing(final WindowEvent evt) {
                            Applet.this.pluginLoader.broadcast(new SocketRequest());
                            ((JFrame)Applet.this.appletFrame).setVisible(false);
                            close.setLabel((startText != null) ? startText : "Connect");
                        }
                    });
                }
                catch (Exception e8) {
                    System.err.println("Applet: could not set up Window event listener");
                    System.err.println("Applet: you will not be able to close it");
                }
                this.pluginLoader.registerPluginListener(new OnlineStatusListener() {
                    public void online() {
                        Applet.this.online = true;
                        if (!((JFrame)Applet.this.appletFrame).isVisible()) {
                            ((JFrame)Applet.this.appletFrame).setVisible(true);
                        }
                    }
                    
                    public void offline() {
                        Applet.this.online = false;
                        if (Applet.this.disconnectCloseWindow) {
                            ((JFrame)Applet.this.appletFrame).setVisible(false);
                            close.setLabel((startText != null) ? startText : "Connect");
                        }
                    }
                });
                this.pluginLoader.registerPluginListener(new FocusStatusListener() {
                    public void pluginGainedFocus(final Plugin plugin) {
                        Applet.this.focussedPlugin = plugin;
                    }
                    
                    public void pluginLostFocus(final Plugin plugin) {
                    }
                });
            }
            else {
                this.pluginLoader.registerPluginListener(new OnlineStatusListener() {
                    public void online() {
                        Applet.this.online = true;
                    }
                    
                    public void offline() {
                        Applet.this.online = false;
                    }
                });
            }
        }
    }
    
    public void start() {
        if (!this.online && (this.appletFrame == this || this.connect)) {
            this.getAppletContext().showStatus("Trying " + this.host + " " + this.port + " ...");
            this.pluginLoader.broadcast(new SocketRequest(this.host, Integer.parseInt(this.port)));
            this.pluginLoader.broadcast(new ReturnFocusRequest());
        }
    }
    
    public void stop() {
        if (this.online && this.disconnect) {
            this.pluginLoader.broadcast(new SocketRequest());
        }
    }
    
    private void parameterOverride(final Properties options) {
        final Enumeration e = options.keys();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            final String value = this.getParameter(key);
            if (value != null) {
                System.out.println("Applet: overriding value of " + key + " with " + value);
                ((Hashtable<String, String>)options).put(key, value);
            }
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
