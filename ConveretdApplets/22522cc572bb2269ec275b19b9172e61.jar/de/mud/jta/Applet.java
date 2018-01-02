// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Enumeration;
import de.mud.jta.event.FocusStatusListener;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.PrintJob;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuBar;
import de.mud.jta.event.SocketRequest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.mud.jta.event.ReturnFocusRequest;
import java.lang.reflect.InvocationTargetException;
import java.awt.Button;
import de.mud.jta.event.AppletRequest;
import de.mud.jta.event.SoundListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.net.URL;
import java.awt.datatransfer.Clipboard;
import java.util.Properties;
import java.awt.Container;

public class Applet extends java.applet.Applet
{
    private static final int debug = 0;
    private String frameTitle;
    private Container appletFrame;
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
        this.runSecurely(new Runnable() {
            private final /* synthetic */ Applet this$0;
            
            public void run() {
                if (Applet.this.pluginLoader == null) {
                    try {
                        Applet.this.options.load(((Applet.class$de$mud$jta$Applet == null) ? (Applet.class$de$mud$jta$Applet = Applet.class$("de.mud.jta.Applet")) : Applet.class$de$mud$jta$Applet).getResourceAsStream("/de/mud/jta/default.conf"));
                    }
                    catch (Exception e3) {
                        try {
                            final URL url = new URL(Applet.this.getCodeBase() + "default.conf");
                            Applet.this.options.load(url.openStream());
                        }
                        catch (Exception e4) {
                            System.err.println("jta: cannot load default.conf");
                            System.err.println("jta: try extracting it from the jar file");
                            System.err.println("jta: expected file here: " + Applet.this.getCodeBase() + "default.conf");
                        }
                    }
                    String value;
                    if ((value = Applet.this.getParameter("config")) != null) {
                        final Properties appletParams = new Properties();
                        URL url2 = null;
                        try {
                            url2 = new URL(value);
                        }
                        catch (Exception e) {
                            try {
                                url2 = new URL(Applet.this.getCodeBase() + value);
                            }
                            catch (Exception ce) {
                                System.err.println("jta: could not find config file: " + ce);
                            }
                        }
                        if (url2 != null) {
                            try {
                                appletParams.load(url2.openStream());
                                final Enumeration ape = appletParams.keys();
                                while (ape.hasMoreElements()) {
                                    final String key = ape.nextElement();
                                    ((Hashtable<String, String>)Applet.this.options).put(key, appletParams.getProperty(key));
                                }
                            }
                            catch (Exception e) {
                                System.err.println("jta: could not load config file: " + e);
                            }
                        }
                    }
                    Applet.this.parameterOverride(Applet.this.options);
                    Applet.this.pluginLoader = new Common(Applet.this.options);
                    Applet.this.host = Applet.this.getParameter("Socket.host");
                    if (Applet.this.host == null) {
                        Applet.this.host = Applet.this.options.getProperty("Socket.host");
                    }
                    if (Applet.this.host == null) {
                        Applet.this.host = Applet.this.getCodeBase().getHost();
                    }
                    Applet.this.port = Applet.this.options.getProperty("Socket.port");
                    if (Applet.this.port == null) {
                        Applet.this.port = "23";
                    }
                    if (Boolean.valueOf(Applet.this.options.getProperty("Applet.connect"))) {
                        Applet.this.connect = true;
                    }
                    if (!Boolean.valueOf(Applet.this.options.getProperty("Applet.disconnect"))) {
                        Applet.this.disconnect = false;
                    }
                    if (!Boolean.valueOf(Applet.this.options.getProperty("Applet.disconnect.closeWindow"))) {
                        Applet.this.disconnectCloseWindow = false;
                    }
                    Applet.this.frameTitle = Applet.this.options.getProperty("Applet.detach.title");
                    if (Boolean.valueOf(Applet.this.options.getProperty("Applet.detach"))) {
                        if (Applet.this.frameTitle == null) {
                            Applet.this.appletFrame = new Frame("jta: " + Applet.this.host + (Applet.this.port.equals("23") ? "" : (" " + Applet.this.port)));
                        }
                        else {
                            Applet.this.appletFrame = new Frame(Applet.this.frameTitle);
                        }
                    }
                    else {
                        Applet.this.appletFrame = Applet.this;
                    }
                    Applet.this.appletFrame.setLayout(new BorderLayout());
                    final Hashtable componentList = Applet.this.pluginLoader.getComponents();
                    Enumeration names = componentList.keys();
                    while (names.hasMoreElements()) {
                        final String name = names.nextElement();
                        final Component c = componentList.get(name);
                        if ((value = Applet.this.options.getProperty("layout." + name)) != null) {
                            Applet.this.appletFrame.add(value, c);
                        }
                        else {
                            System.err.println("jta: no layout property set for '" + name + "'");
                            System.err.println("jta: ignoring '" + name + "'");
                        }
                    }
                    Applet.this.pluginLoader.registerPluginListener(new SoundListener() {
                        private final /* synthetic */ Applet$1 this$1 = this$1;
                        
                        public void playSound(final URL audioClip) {
                            this.this$1.this$0.getAudioClip(audioClip).play();
                        }
                    });
                    Applet.this.pluginLoader.broadcast(new AppletRequest(Applet.this));
                    if (Applet.this.appletFrame != Applet.this) {
                        final String startText = Applet.this.options.getProperty("Applet.detach.startText");
                        final String stopText = Applet.this.options.getProperty("Applet.detach.stopText");
                        final Button close = new Button();
                        try {
                            final Class privilegeManager = Class.forName("netscape.security.PrivilegeManager");
                            final Method enable = privilegeManager.getMethod("enablePrivilege", (Applet.class$java$lang$String == null) ? (Applet.class$java$lang$String = Applet.class$("java.lang.String")) : Applet.class$java$lang$String);
                            enable.invoke(privilegeManager, Applet.this.options.getProperty("Applet.Netscape.privilege"));
                            Applet.this.clipboard = Applet.this.appletFrame.getToolkit().getSystemClipboard();
                        }
                        catch (NoClassDefFoundError nc) {
                            System.err.println("Applet: This is not Netscape ...");
                        }
                        catch (Exception e2) {
                            System.err.println("Applet: system clipboard access denied: " + ((e2 instanceof InvocationTargetException) ? ((InvocationTargetException)e2).getTargetException() : e2));
                            e2.printStackTrace();
                        }
                        finally {
                            if (Applet.this.clipboard != null) {
                                System.err.println("Applet: copy & paste only within the JTA");
                                Applet.this.clipboard = new Clipboard("de.mud.jta.Main");
                            }
                        }
                        if (Boolean.valueOf(Applet.this.options.getProperty("Applet.detach.immediately"))) {
                            if (Boolean.valueOf(Applet.this.options.getProperty("Applet.detach.fullscreen"))) {
                                ((Frame)Applet.this.appletFrame).setSize(Applet.this.appletFrame.getToolkit().getScreenSize());
                            }
                            else {
                                ((Frame)Applet.this.appletFrame).pack();
                            }
                            ((Frame)Applet.this.appletFrame).show();
                            Applet.this.pluginLoader.broadcast(new ReturnFocusRequest());
                            close.setLabel((startText != null) ? stopText : "Disconnect");
                        }
                        else {
                            close.setLabel((startText != null) ? startText : "Connect");
                        }
                        close.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                if (((Frame)this.this$1.this$0.appletFrame).isVisible()) {
                                    this.this$1.this$0.pluginLoader.broadcast(new SocketRequest());
                                    ((Frame)this.this$1.this$0.appletFrame).setVisible(false);
                                    close.setLabel((startText != null) ? startText : "Connect");
                                }
                                else {
                                    if (this.this$1.this$0.frameTitle == null) {
                                        ((Frame)this.this$1.this$0.appletFrame).setTitle("jta: " + this.this$1.this$0.host + (this.this$1.this$0.port.equals("23") ? "" : (" " + this.this$1.this$0.port)));
                                    }
                                    if (Boolean.valueOf(this.this$1.this$0.options.getProperty("Applet.detach.fullscreen"))) {
                                        ((Frame)this.this$1.this$0.appletFrame).setSize(this.this$1.this$0.appletFrame.getToolkit().getScreenSize());
                                    }
                                    else {
                                        ((Frame)this.this$1.this$0.appletFrame).pack();
                                    }
                                    ((Frame)this.this$1.this$0.appletFrame).show();
                                    this.this$1.this$0.getAppletContext().showStatus("Trying " + this.this$1.this$0.host + " " + this.this$1.this$0.port + " ...");
                                    this.this$1.this$0.pluginLoader.broadcast(new SocketRequest(this.this$1.this$0.host, Integer.parseInt(this.this$1.this$0.port)));
                                    this.this$1.this$0.pluginLoader.broadcast(new ReturnFocusRequest());
                                    close.setLabel((stopText != null) ? stopText : "Disconnect");
                                }
                            }
                        });
                        Applet.this.setLayout(new BorderLayout());
                        Applet.this.add("Center", close);
                        final MenuBar mb = new MenuBar();
                        final Menu file = new Menu("File");
                        file.setShortcut(new MenuShortcut(72, true));
                        MenuItem tmp;
                        file.add(tmp = new MenuItem("Connect"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                this.this$1.this$0.pluginLoader.broadcast(new SocketRequest(this.this$1.this$0.host, Integer.parseInt(this.this$1.this$0.port)));
                            }
                        });
                        file.add(tmp = new MenuItem("Disconnect"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                this.this$1.this$0.pluginLoader.broadcast(new SocketRequest());
                            }
                        });
                        file.add(new MenuItem("-"));
                        file.add(tmp = new MenuItem("Print"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                if (this.this$1.this$0.pluginLoader.getComponents().get("Terminal") != null) {
                                    final PrintJob printJob = this.this$1.this$0.appletFrame.getToolkit().getPrintJob((Frame)this.this$1.this$0.appletFrame, "JTA Terminal", null);
                                    this.this$1.this$0.pluginLoader.getComponents().get("Terminal").print(printJob.getGraphics());
                                    printJob.end();
                                }
                            }
                        });
                        file.add(new MenuItem("-"));
                        file.add(tmp = new MenuItem("Exit"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                ((Frame)this.this$1.this$0.appletFrame).setVisible(false);
                                this.this$1.this$0.pluginLoader.broadcast(new SocketRequest());
                                close.setLabel((startText != null) ? startText : "Connect");
                            }
                        });
                        mb.add(file);
                        final Menu edit = new Menu("Edit");
                        edit.setShortcut(new MenuShortcut(72, true));
                        edit.add(tmp = new MenuItem("Copy"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                if (this.this$1.this$0.focussedPlugin instanceof VisualTransferPlugin) {
                                    ((VisualTransferPlugin)this.this$1.this$0.focussedPlugin).copy(this.this$1.this$0.clipboard);
                                }
                            }
                        });
                        edit.add(tmp = new MenuItem("Paste"));
                        tmp.addActionListener(new ActionListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent evt) {
                                if (this.this$1.this$0.focussedPlugin instanceof VisualTransferPlugin) {
                                    ((VisualTransferPlugin)this.this$1.this$0.focussedPlugin).paste(this.this$1.this$0.clipboard);
                                }
                            }
                        });
                        mb.add(edit);
                        final Hashtable menuList = Applet.this.pluginLoader.getMenus();
                        names = menuList.keys();
                        while (names.hasMoreElements()) {
                            final String name2 = names.nextElement();
                            mb.add(menuList.get(name2));
                        }
                        if (Boolean.valueOf(Applet.this.options.getProperty("Applet.detach.menuBar"))) {
                            ((Frame)Applet.this.appletFrame).setMenuBar(mb);
                        }
                        try {
                            ((Frame)Applet.this.appletFrame).addWindowListener(new WindowAdapter() {
                                private final /* synthetic */ Applet$1 this$1 = this$1;
                                
                                public void windowClosing(final WindowEvent evt) {
                                    this.this$1.this$0.pluginLoader.broadcast(new SocketRequest());
                                    ((Frame)this.this$1.this$0.appletFrame).setVisible(false);
                                    close.setLabel((startText != null) ? startText : "Connect");
                                }
                            });
                        }
                        catch (Exception e5) {
                            System.err.println("Applet: could not set up Window event listener");
                            System.err.println("Applet: you will not be able to close it");
                        }
                        Applet.this.pluginLoader.registerPluginListener(new OnlineStatusListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void online() {
                                this.this$1.this$0.online = true;
                                if (!((Frame)this.this$1.this$0.appletFrame).isVisible()) {
                                    ((Frame)this.this$1.this$0.appletFrame).setVisible(true);
                                }
                            }
                            
                            public void offline() {
                                this.this$1.this$0.online = false;
                                if (this.this$1.this$0.disconnectCloseWindow) {
                                    ((Frame)this.this$1.this$0.appletFrame).setVisible(false);
                                    close.setLabel((startText != null) ? startText : "Connect");
                                }
                            }
                        });
                        Applet.this.pluginLoader.registerPluginListener(new FocusStatusListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void pluginGainedFocus(final Plugin plugin) {
                                this.this$1.this$0.focussedPlugin = plugin;
                            }
                            
                            public void pluginLostFocus(final Plugin plugin) {
                            }
                        });
                    }
                    else {
                        Applet.this.pluginLoader.registerPluginListener(new OnlineStatusListener() {
                            private final /* synthetic */ Applet$1 this$1 = this$1;
                            
                            public void online() {
                                this.this$1.this$0.online = true;
                            }
                            
                            public void offline() {
                                this.this$1.this$0.online = false;
                            }
                        });
                    }
                }
            }
        });
    }
    
    public void start() {
        this.runSecurely(new Runnable() {
            public void run() {
                if (!Applet.this.online && (Applet.this.appletFrame == Applet.this || Applet.this.connect)) {
                    Applet.this.getAppletContext().showStatus("Trying " + Applet.this.host + " " + Applet.this.port + " ...");
                    Applet.this.pluginLoader.broadcast(new SocketRequest(Applet.this.host, Integer.parseInt(Applet.this.port)));
                    Applet.this.pluginLoader.broadcast(new ReturnFocusRequest());
                }
            }
        });
    }
    
    public void stop() {
        this.runSecurely(new Runnable() {
            public void run() {
                if (Applet.this.online && Applet.this.disconnect) {
                    Applet.this.pluginLoader.broadcast(new SocketRequest());
                }
            }
        });
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
    
    private void runSecurely(final Runnable runnable) {
        try {
            if (System.getProperty("java.vendor").startsWith("Microsoft")) {
                final Class pe = Class.forName("com.ms.security.PolicyEngine");
                if (pe != null) {
                    PolicyEngine.assertPermission(PermissionID.SYSTEM);
                    PolicyEngine.assertPermission(PermissionID.NETIO);
                    PolicyEngine.assertPermission(PermissionID.PROPERTY);
                    PolicyEngine.assertPermission(PermissionID.THREAD);
                    PolicyEngine.assertPermission(PermissionID.FILEIO);
                    PolicyEngine.assertPermission(PermissionID.USERFILEIO);
                    System.err.println("SUCCESSfully asserted MS JVM permissions");
                    System.err.flush();
                }
            }
        }
        catch (Throwable e) {
            System.err.println("Microsoft JVM permissions not asserted.");
            e.printStackTrace(System.err);
            System.err.flush();
        }
        runnable.run();
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
