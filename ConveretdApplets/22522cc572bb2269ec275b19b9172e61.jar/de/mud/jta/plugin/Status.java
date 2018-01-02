// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.Menu;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.Component;
import de.mud.jta.event.SocketListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginListener;
import java.awt.Color;
import java.awt.Font;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.util.Hashtable;
import java.awt.Panel;
import java.awt.Label;
import de.mud.jta.VisualPlugin;
import de.mud.jta.Plugin;

public class Status extends Plugin implements VisualPlugin, Runnable
{
    private static final int debug = 1;
    private Label status;
    private Label host;
    private Panel sPanel;
    private String address;
    private String port;
    private String infoURL;
    private int interval;
    private Thread infoThread;
    private Hashtable ports;
    
    public Status(final PluginBus bus, final String id) {
        super(bus, id);
        this.ports = new Hashtable();
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                Status.this.infoURL = config.getProperty("Status", id, "info");
                if (Status.this.infoURL != null) {
                    Status.this.host.setAlignment(1);
                }
                String tmp;
                if ((tmp = config.getProperty("Status", id, "font")) != null) {
                    final String font = tmp;
                    int style = 0;
                    int fsize = 12;
                    if ((tmp = config.getProperty("Status", id, "fontSize")) != null) {
                        fsize = Integer.parseInt(tmp);
                    }
                    final String fontStyle = config.getProperty("Status", id, "fontStyle");
                    if (fontStyle == null || fontStyle.equals("plain")) {
                        style = 0;
                    }
                    else if (fontStyle.equals("bold")) {
                        style = 1;
                    }
                    else if (fontStyle.equals("italic")) {
                        style = 2;
                    }
                    else if (fontStyle.equals("bold+italic")) {
                        style = 3;
                    }
                    Status.this.host.setFont(new Font(font, style, fsize));
                }
                if ((tmp = config.getProperty("Status", id, "foreground")) != null) {
                    Status.this.host.setForeground(Color.decode(tmp));
                }
                if ((tmp = config.getProperty("Status", id, "background")) != null) {
                    Status.this.host.setBackground(Color.decode(tmp));
                }
                if (config.getProperty("Status", id, "interval") != null) {
                    try {
                        Status.this.interval = Integer.parseInt(config.getProperty("Status", id, "interval"));
                        Status.this.infoThread = new Thread(Status.this);
                        Status.this.infoThread.start();
                    }
                    catch (NumberFormatException e) {
                        Status.this.error("interval is not a number");
                    }
                }
            }
        });
        this.ports.put("22", "ssh");
        this.ports.put("23", "telnet");
        this.ports.put("25", "smtp");
        this.sPanel = new Panel(new BorderLayout());
        this.host = new Label("Not connected.", 0);
        bus.registerPluginListener(new SocketListener() {
            public void connect(final String addr, final int p) {
                Status.this.address = addr;
                if (Status.this.address == null || Status.this.address.length() == 0) {
                    Status.this.address = "<unknwon host>";
                }
                if (Status.this.ports.get("" + p) != null) {
                    Status.this.port = Status.this.ports.get("" + p);
                }
                else {
                    Status.this.port = "" + p;
                }
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Trying " + Status.this.address + " " + Status.this.port + " ...");
                }
            }
            
            public void disconnect() {
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Not connected.");
                }
            }
        });
        this.sPanel.add("Center", this.host);
        this.status = new Label("offline", 1);
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                Status.this.status.setText("online");
                Status.this.status.setBackground(Color.green);
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Connected to " + Status.this.address + " " + Status.this.port);
                }
            }
            
            public void offline() {
                Status.this.status.setText("offline");
                Status.this.status.setBackground(Color.red);
                if (Status.this.infoURL == null) {
                    Status.this.host.setText("Not connected.");
                }
            }
        });
        this.sPanel.add("East", this.status);
    }
    
    public void run() {
        URL url = null;
        try {
            url = new URL(this.infoURL);
        }
        catch (Exception e) {
            this.error("infoURL is not valid: " + e);
            this.infoURL = null;
            return;
        }
        while (url != null && this.infoThread != null) {
            try {
                final BufferedReader content = new BufferedReader(new InputStreamReader(url.openStream()));
                try {
                    String line;
                    while ((line = content.readLine()) != null) {
                        if (line.startsWith("#")) {
                            final String color = line.substring(1, 7);
                            line = line.substring(8);
                            this.host.setForeground(Color.decode("#" + color));
                        }
                        this.host.setText(line);
                        final Thread infoThread = this.infoThread;
                        Thread.sleep(10 * this.interval);
                    }
                }
                catch (IOException e2) {
                    this.error("error while loading info ...");
                }
                final Thread infoThread2 = this.infoThread;
                Thread.sleep(100 * this.interval);
                continue;
            }
            catch (Exception e) {
                this.error("error retrieving info content: " + e);
                e.printStackTrace();
                this.host.setForeground(Color.red);
                this.host.setText("error retrieving info content");
                this.infoURL = null;
                return;
            }
            break;
        }
    }
    
    public Component getPluginVisual() {
        return this.sPanel;
    }
    
    public Menu getPluginMenu() {
        return null;
    }
}
