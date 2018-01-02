// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import de.mud.jta.event.ReturnFocusRequest;
import java.awt.PrintJob;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.WindowListener;
import de.mud.jta.event.SocketRequest;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import javax.swing.JComponent;
import de.mud.jta.event.FocusStatusListener;
import de.mud.jta.event.OnlineStatusListener;
import javax.swing.JFrame;
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
    private static String host;
    private static String port;
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
        Main.host = options.getProperty("Socket.host");
        Main.port = options.getProperty("Socket.port");
        final JFrame frame = new JFrame("jta: " + Main.host + (Main.port.equals("23") ? "" : (" " + Main.port)));
        try {
            Main.clipboard = frame.getToolkit().getSystemClipboard();
        }
        catch (Exception e3) {
            System.err.println("jta: system clipboard access denied");
            System.err.println("jta: copy & paste only within the JTA");
            Main.clipboard = new Clipboard("de.mud.jta.Main");
        }
        final Common setup = new Common(options);
        if (Main.port == null || Main.port.length() == 0) {
            if (setup.getPlugins().containsKey("SSH")) {
                Main.port = "22";
            }
            else {
                Main.port = "23";
            }
        }
        setup.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                frame.setTitle("jta: " + Main.host + (Main.port.equals("23") ? "" : (" " + Main.port)));
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
        final Map componentList = setup.getComponents();
        for (final String name : componentList.keySet()) {
            final JComponent c = componentList.get(name);
            if (options.getProperty("layout." + name) == null) {
                System.err.println("jta: no layout property set for '" + name + "'");
                frame.add("South", c);
            }
            else {
                frame.getContentPane().add(options.getProperty("layout." + name), c);
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
        final JMenuBar mb = new JMenuBar();
        final JMenu file = new JMenu("File");
        file.setMnemonic(70);
        JMenuItem tmp;
        file.add(tmp = new JMenuItem("Connect"));
        tmp.setAccelerator(KeyStroke.getKeyStroke(67, 3));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String destination = JOptionPane.showInputDialog(frame, new JLabel("Enter your destination host (host[:port])"), "Connect", 3);
                if (destination != null) {
                    int sep = 0;
                    if ((sep = destination.indexOf(32)) > 0 || (sep = destination.indexOf(58)) > 0) {
                        Main.host = destination.substring(0, sep);
                        Main.port = destination.substring(sep + 1);
                    }
                    else {
                        Main.host = destination;
                    }
                    setup.broadcast(new SocketRequest());
                    setup.broadcast(new SocketRequest(Main.host, Integer.parseInt(Main.port)));
                }
            }
        });
        file.add(tmp = new JMenuItem("Disconnect"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                setup.broadcast(new SocketRequest());
            }
        });
        file.addSeparator();
        if (setup.getComponents().get("Terminal") != null) {
            file.add(tmp = new JMenuItem("Print"));
            tmp.setAccelerator(KeyStroke.getKeyStroke(80, 2));
            tmp.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    final PrintJob printJob = frame.getToolkit().getPrintJob(frame, "JTA Terminal", null);
                    if (printJob == null) {
                        return;
                    }
                    setup.getComponents().get("Terminal").print(printJob.getGraphics());
                    printJob.end();
                }
            });
            file.addSeparator();
        }
        file.add(tmp = new JMenuItem("Exit"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                frame.dispose();
                System.exit(0);
            }
        });
        mb.add(file);
        final JMenu edit = new JMenu("Edit");
        edit.add(tmp = new JMenuItem("Copy"));
        tmp.setAccelerator(KeyStroke.getKeyStroke(67, 2));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (Main.focussedPlugin instanceof VisualTransferPlugin) {
                    ((VisualTransferPlugin)Main.focussedPlugin).copy(Main.clipboard);
                }
            }
        });
        edit.add(tmp = new JMenuItem("Paste"));
        tmp.setAccelerator(KeyStroke.getKeyStroke(86, 2));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (Main.focussedPlugin instanceof VisualTransferPlugin) {
                    ((VisualTransferPlugin)Main.focussedPlugin).paste(Main.clipboard);
                }
            }
        });
        mb.add(edit);
        final Map menuList = setup.getMenus();
        for (final String name2 : menuList.keySet()) {
            mb.add(menuList.get(name2));
        }
        final JMenu help = new JMenu("Help");
        help.setMnemonic(156);
        help.add(tmp = new JMenuItem("General"));
        tmp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Help.show(frame, options.getProperty("Help.url"));
            }
        });
        mb.add(help);
        frame.setJMenuBar(mb);
        frame.pack();
        if (new Boolean(options.getProperty("Applet.detach.fullscreen"))) {
            frame.setSize(frame.getToolkit().getScreenSize());
        }
        else {
            frame.pack();
        }
        frame.setVisible(true);
        if (Main.host != null && Main.host.length() > 0) {
            setup.broadcast(new SocketRequest(Main.host, Integer.parseInt(Main.port)));
        }
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
