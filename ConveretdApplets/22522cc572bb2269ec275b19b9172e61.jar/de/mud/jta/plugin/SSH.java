// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Frame;
import de.mud.jta.event.OnlineStatusListener;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.event.LocalEchoRequest;
import de.mud.jta.event.WindowSizeRequest;
import java.awt.Dimension;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.TerminalTypeRequest;
import java.io.IOException;
import de.mud.jta.PluginBus;
import de.mud.ssh.SshIO;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class SSH extends Plugin implements FilterPlugin
{
    protected FilterPlugin source;
    protected SshIO handler;
    protected String user;
    protected String pass;
    private static final int debug = 0;
    private boolean auth;
    private byte[] buffer;
    private int pos;
    
    public SSH(final PluginBus bus, final String id) {
        super(bus, id);
        this.auth = false;
        this.handler = new SshIO() {
            public String getTerminalType() {
                return (String)bus.broadcast(new TerminalTypeRequest());
            }
            
            public Dimension getWindowSize() {
                return (Dimension)bus.broadcast(new WindowSizeRequest());
            }
            
            public void setLocalEcho(final boolean echo) {
                bus.broadcast(new LocalEchoRequest(echo));
            }
            
            public void write(final byte[] b) throws IOException {
                SSH.this.source.write(b);
            }
        };
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                SSH.this.user = config.getProperty("SSH", id, "user");
                SSH.this.pass = config.getProperty("SSH", id, "password");
            }
        });
        bus.registerPluginListener(new OnlineStatusListener() {
            private final /* synthetic */ SSH this$0;
            
            public void online() {
                if (SSH.this.pass == null) {
                    final Frame frame = new Frame("SSH User Authentication");
                    Panel panel = new Panel(new GridLayout(3, 1));
                    panel.add(new Label("SSH Authorization required"));
                    panel.add(new Label("SSH implementation 1998 by Cedric Gourio"));
                    panel.add(new Label("Adapted 1999 to the JTA by Matthias L. Jugel"));
                    frame.add("North", panel);
                    panel = new Panel(new GridLayout(2, 2));
                    final TextField login = new TextField(SSH.this.user, 10);
                    final TextField passw = new TextField(10);
                    login.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            passw.requestFocus();
                        }
                    });
                    passw.setEchoChar('*');
                    panel.add(new Label("User name"));
                    panel.add(login);
                    panel.add(new Label("Password"));
                    panel.add(passw);
                    frame.add("Center", panel);
                    panel = new Panel();
                    final Button cancel = new Button("Cancel");
                    final Button ok = new Button("Login");
                    final ActionListener enter = new ActionListener() {
                        private final /* synthetic */ SSH$3 this$1 = this$1;
                        
                        public void actionPerformed(final ActionEvent evt) {
                            this.this$1.this$0.handler.setLogin(login.getText());
                            this.this$1.this$0.handler.setPassword(passw.getText());
                            frame.dispose();
                            this.this$1.this$0.auth = true;
                        }
                    };
                    ok.addActionListener(enter);
                    passw.addActionListener(enter);
                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            frame.dispose();
                        }
                    });
                    panel.add(cancel);
                    panel.add(ok);
                    frame.add("South", panel);
                    frame.pack();
                    frame.show();
                    frame.setLocation(frame.getToolkit().getScreenSize().width / 2 - frame.getSize().width / 2, frame.getToolkit().getScreenSize().height / 2 - frame.getSize().height / 2);
                }
                else {
                    SSH.this.error(SSH.this.user + ":" + SSH.this.pass);
                    SSH.this.handler.setLogin(SSH.this.user);
                    SSH.this.handler.setPassword(SSH.this.pass);
                    SSH.this.auth = true;
                }
            }
            
            public void offline() {
                SSH.this.handler.disconnect();
                SSH.this.auth = false;
            }
        });
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        while (!this.auth) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.buffer != null) {
            final int amount = (this.buffer.length - this.pos <= b.length) ? (this.buffer.length - this.pos) : b.length;
            System.arraycopy(this.buffer, this.pos, b, 0, amount);
            if (this.pos + amount < this.buffer.length) {
                this.pos += amount;
            }
            else {
                this.buffer = null;
            }
            return amount;
        }
        int n = this.source.read(b);
        if (n > 0) {
            final byte[] tmp = new byte[n];
            System.arraycopy(b, 0, tmp, 0, n);
            this.buffer = this.handler.handleSSH(tmp);
            if (this.buffer == null || this.buffer.length <= 0) {
                return 0;
            }
            final int amount2 = (this.buffer.length <= b.length) ? this.buffer.length : b.length;
            System.arraycopy(this.buffer, 0, b, 0, amount2);
            n = (this.pos = amount2);
            if (amount2 == this.buffer.length) {
                this.buffer = null;
                this.pos = 0;
            }
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        if (!this.auth) {
            return;
        }
        for (int i = 0; i < b.length; ++i) {
            switch (b[i]) {
                case 10: {
                    b[i] = 13;
                    break;
                }
            }
        }
        this.handler.sendData(new String(b));
    }
}
