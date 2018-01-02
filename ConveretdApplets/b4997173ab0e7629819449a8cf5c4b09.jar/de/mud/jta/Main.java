// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.util.Enumeration;
import java.util.Hashtable;
import de.mud.jta.event.ReturnFocusRequest;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.WindowListener;
import de.mud.jta.event.SocketRequest;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import de.mud.jta.event.FocusStatusListener;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.Frame;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.io.IOException;
import java.util.Properties;
import java.awt.datatransfer.Clipboard;

public class Main
{
    private static final int debug = 0;
    private static final boolean personalJava = false;
    private static Plugin focussedPlugin;
    private static Clipboard clipboard;
    static /* synthetic */ Class class$de$mud$jta$Main;
    
    public static void main(final String[] args) {
        final Properties options = new Properties();
        try {
            options.load(((Main.class$de$mud$jta$Main == null) ? (Main.class$de$mud$jta$Main = class$("de.mud.jta.Main")) : Main.class$de$mud$jta$Main).getResourceAsStream("/de/mud/jta/default.conf"));
        }
        catch (IOException e) {
            System.err.println("jta: cannot load default.conf");
        }
        final String error = parseOptions(options, args);
        if (error != null) {
            System.err.println(error);
            System.err.println("usage: de.mud.jta.Main [-plugins pluginlist] [-addplugin plugin] [-config url_or_file] [-term id] [host [port]]");
            System.exit(0);
        }
        final String cfg = options.getProperty("Main.config");
        if (cfg != null) {
            try {
                options.load(new URL(cfg).openStream());
            }
            catch (IOException e2) {
                try {
                    options.load(new FileInputStream(cfg));
                }
                catch (Exception fe) {
                    System.err.println("jta: cannot load " + cfg);
                }
            }
        }
        final String host = options.getProperty("Socket.host");
        final String port = options.getProperty("Socket.port");
        final Frame frame = new Frame("jta: " + host + (port.equals("23") ? "" : (" " + port)));
        try {
            Main.clipboard = frame.getToolkit().getSystemClipboard();
        }
        catch (Exception e3) {
            System.err.println("jta: system clipboard access denied");
            System.err.println("jta: copy & paste only within the JTA");
            Main.clipboard = new Clipboard("de.mud.jta.Main");
        }
        final Common setup = new Common(options);
        setup.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                frame.setTitle("jta: " + host + (port.equals("23") ? "" : (" " + port)));
            }
            
            public void offline() {
                frame.setTitle("jta: offline");
            }
        });
        setup.registerPluginListener(new FocusStatusListener() {
            public void pluginGainedFocus(final Plugin plugin) {
                Main.focussedPlugin = plugin;
            }
            
            public void pluginLostFocus(final Plugin plugin) {
            }
        });
        final Hashtable componentList = setup.getComponents();
        Enumeration names = componentList.keys();
        while (names.hasMoreElements()) {
            final String name = names.nextElement();
            final Component c = componentList.get(name);
            if (options.getProperty("layout." + name) == null) {
                System.err.println("jta: no layout property set for '" + name + "'");
                frame.add("South", c);
            }
            else {
                frame.add(options.getProperty("layout." + name), c);
            }
        }
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent evt) {
                setup.broadcast(new SocketRequest());
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
        final MenuBar mb = new MenuBar();
        final Menu file = new Menu("File");
        file.setShortcut(new MenuShortcut(72, true));
        MenuItem tmp;
        file.add(tmp = new MenuItem("Connect"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                setup.broadcast(new SocketRequest(host, Integer.parseInt(port)));
            }
        });
        file.add(tmp = new MenuItem("Disconnect"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                setup.broadcast(new SocketRequest());
            }
        });
        file.add(new MenuItem("-"));
        file.add(tmp = new MenuItem("Print"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (setup.getComponents().get("Terminal") != null) {
                    final PrintJob printJob = frame.getToolkit().getPrintJob(frame, "JTA Terminal", null);
                    if (printJob == null) {
                        return;
                    }
                    setup.getComponents().get("Terminal").print(printJob.getGraphics());
                    printJob.end();
                }
            }
        });
        file.add(new MenuItem("-"));
        file.add(tmp = new MenuItem("Exit"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                frame.dispose();
                System.exit(0);
            }
        });
        mb.add(file);
        final Menu edit = new Menu("Edit");
        edit.setShortcut(new MenuShortcut(72, true));
        edit.add(tmp = new MenuItem("Copy"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (Main.focussedPlugin instanceof VisualTransferPlugin) {
                    ((VisualTransferPlugin)Main.focussedPlugin).copy(Main.clipboard);
                }
            }
        });
        edit.add(tmp = new MenuItem("Paste"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (Main.focussedPlugin instanceof VisualTransferPlugin) {
                    ((VisualTransferPlugin)Main.focussedPlugin).paste(Main.clipboard);
                }
            }
        });
        mb.add(edit);
        final Hashtable menuList = setup.getMenus();
        names = menuList.keys();
        while (names.hasMoreElements()) {
            final String name2 = names.nextElement();
            mb.add(menuList.get(name2));
        }
        final Menu help = new Menu("Help");
        help.add(tmp = new MenuItem("About"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final Dialog d = new Dialog(frame, "About JTA", true);
                d.add("Center", new Label("Copyright (c) 1996-2000 Matthias L Jugel, Marcus Mei\u00dfner"));
                final Button close = new Button("Close");
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        d.dispose();
                    }
                });
                d.add("South", close);
                d.pack();
                d.setResizable(false);
                d.show();
            }
        });
        mb.setHelpMenu(help);
        frame.setMenuBar(mb);
        frame.pack();
        frame.show();
        setup.broadcast(new SocketRequest(host, Integer.parseInt(port)));
        setup.broadcast(new ReturnFocusRequest());
    }
    
    private static String parseOptions(final Properties options, final String[] args) {
        boolean host = false;
        boolean port = false;
        for (int n = 0; n < args.length; ++n) {
            if (args[n].equals("-config")) {
                if (args[n + 1].startsWith("-")) {
                    return "missing parameter for -config";
                }
                ((Hashtable<String, String>)options).put("Main.config", args[++n]);
            }
            else if (args[n].equals("-plugins")) {
                if (args[n + 1].startsWith("-")) {
                    return "missing parameter for -plugins";
                }
                ((Hashtable<String, String>)options).put("plugins", args[++n]);
            }
            else if (args[n].equals("-addplugin")) {
                if (args[n + 1].startsWith("-")) {
                    return "missing parameter for -addplugin";
                }
                ((Hashtable<String, String>)options).put("plugins", args[++n] + "," + ((Hashtable<K, Object>)options).get("plugins"));
            }
            else if (args[n].equals("-term")) {
                if (args[n + 1].startsWith("-")) {
                    return "missing parameter for -term";
                }
                ((Hashtable<String, String>)options).put("Terminal.id", args[++n]);
            }
            else if (!host) {
                ((Hashtable<String, String>)options).put("Socket.host", args[n]);
                host = true;
            }
            else {
                if (!host || port) {
                    return "unknown parameter '" + args[n] + "'";
                }
                ((Hashtable<String, String>)options).put("Socket.port", args[n]);
                port = true;
            }
        }
        return null;
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
