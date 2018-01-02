// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.Menu;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.SocketRequest;
import java.awt.event.ActionEvent;
import de.mud.jta.PluginListener;
import java.io.InputStream;
import java.io.IOException;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.util.Hashtable;
import java.awt.Panel;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class ButtonBar extends Plugin implements FilterPlugin, VisualPlugin, ActionListener
{
    protected Panel panel;
    private Hashtable buttons;
    private Hashtable fields;
    private boolean clearFields;
    FilterPlugin source;
    
    public ButtonBar(final PluginBus bus, final String id) {
        super(bus, id);
        this.panel = new Panel();
        this.buttons = null;
        this.fields = null;
        this.clearFields = true;
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig cfg) {
                final String file = cfg.getProperty("ButtonBar", id, "setup");
                ButtonBar.this.clearFields = Boolean.valueOf(cfg.getProperty("ButtonBar", id, "clearFields"));
                if (file == null) {
                    ButtonBar.this.error("no setup file");
                    return;
                }
                StreamTokenizer setup = null;
                InputStream is = null;
                try {
                    is = this.getClass().getResourceAsStream(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                if (is == null) {
                    try {
                        is = new URL(file).openStream();
                    }
                    catch (Exception ue) {
                        ButtonBar.this.error("could not find: " + file);
                        return;
                    }
                }
                try {
                    final InputStreamReader ir = new InputStreamReader(is);
                    setup = new StreamTokenizer(new BufferedReader(ir));
                }
                catch (RuntimeException e2) {
                    ButtonBar.this.error("cannot load " + file + ": " + e2);
                    return;
                }
                setup.commentChar(35);
                setup.quoteChar(34);
                ButtonBar.this.fields = new Hashtable();
                ButtonBar.this.buttons = new Hashtable();
                final GridBagLayout l = new GridBagLayout();
                final GridBagConstraints c = new GridBagConstraints();
                ButtonBar.this.panel.setLayout(l);
                c.fill = 1;
                try {
                    int token;
                    while ((token = setup.nextToken()) != -1) {
                        switch (token) {
                            case -3: {
                                c.gridwidth = 1;
                                c.weightx = 0.0;
                                c.weighty = 0.0;
                                if (setup.sval.equals("button")) {
                                    if ((token = setup.nextToken()) != -1) {
                                        final String descr = setup.sval;
                                        if ((token = setup.nextToken()) != -1) {
                                            final Button b = new Button(descr);
                                            ButtonBar.this.buttons.put(b, setup.sval);
                                            b.addActionListener(ButtonBar.this);
                                            l.setConstraints(b, ButtonBar.this.constraints(c, setup));
                                            ButtonBar.this.panel.add(b);
                                        }
                                        else {
                                            ButtonBar.this.error(descr + ": missing button command");
                                        }
                                        continue;
                                    }
                                    ButtonBar.this.error("unexpected end of file");
                                    continue;
                                }
                                else {
                                    if (!setup.sval.equals("input")) {
                                        continue;
                                    }
                                    if ((token = setup.nextToken()) != -1) {
                                        final String descr = setup.sval;
                                        if ((token = setup.nextToken()) == -2) {
                                            final int size = (int)setup.nval;
                                            String init = "";
                                            String command = "";
                                            token = setup.nextToken();
                                            if (setup.sval.equals("button") || setup.sval.equals("input") || setup.sval.equals("stretch") || setup.sval.equals("break")) {
                                                setup.pushBack();
                                            }
                                            else {
                                                command = setup.sval;
                                            }
                                            token = setup.nextToken();
                                            if (setup.sval.equals("button") || setup.sval.equals("input") || setup.sval.equals("stretch") || setup.sval.equals("break")) {
                                                setup.pushBack();
                                                init = command;
                                            }
                                            else {
                                                init = setup.sval;
                                            }
                                            final TextField t = new TextField(init, size);
                                            if (!init.equals(command)) {
                                                ButtonBar.this.buttons.put(t, command);
                                                t.addActionListener(ButtonBar.this);
                                            }
                                            ButtonBar.this.fields.put(descr, t);
                                            l.setConstraints(t, ButtonBar.this.constraints(c, setup));
                                            ButtonBar.this.panel.add(t);
                                        }
                                        else {
                                            ButtonBar.this.error(descr + ": missing field size");
                                        }
                                        continue;
                                    }
                                    ButtonBar.this.error("unexpected end of file");
                                    continue;
                                }
                                break;
                            }
                            default: {
                                ButtonBar.this.error("syntax error at line " + setup.lineno());
                                continue;
                            }
                        }
                    }
                }
                catch (IOException e3) {
                    ButtonBar.this.error("unexpected error while reading setup: " + e3);
                }
                ButtonBar.this.panel.validate();
            }
        });
    }
    
    private GridBagConstraints constraints(final GridBagConstraints c, final StreamTokenizer setup) throws IOException {
        if (setup.nextToken() == -3) {
            if (setup.sval.equals("break")) {
                c.gridwidth = 0;
            }
            else if (setup.sval.equals("stretch")) {
                c.weightx = 1.0;
            }
            else {
                setup.pushBack();
            }
        }
        else {
            setup.pushBack();
        }
        return c;
    }
    
    public void actionPerformed(final ActionEvent evt) {
        String tmp;
        if ((tmp = this.buttons.get(evt.getSource())) != null) {
            String cmd = "";
            String function = null;
            int idx = 0;
            int oldidx = 0;
            while ((idx = tmp.indexOf(92, oldidx)) >= 0 && ++idx <= tmp.length()) {
                cmd += tmp.substring(oldidx, idx - 1);
                switch (tmp.charAt(idx)) {
                    case 'b': {
                        cmd += "\b";
                        break;
                    }
                    case 'e': {
                        cmd += "\u001b";
                        break;
                    }
                    case 'n': {
                        cmd += "\n";
                        break;
                    }
                    case 'r': {
                        cmd += "\r";
                        break;
                    }
                    case '$': {
                        int ni = tmp.indexOf(40, idx + 1);
                        if (ni < idx) {
                            this.error("ERROR: Function: missing '('");
                            break;
                        }
                        if (ni == ++idx) {
                            this.error("ERROR: Function: missing name");
                            break;
                        }
                        function = tmp.substring(idx, ni);
                        idx = ni + 1;
                        ni = tmp.indexOf(41, idx);
                        if (ni < idx) {
                            this.error("ERROR: Function: missing ')'");
                            break;
                        }
                        tmp = tmp.substring(idx, ni);
                        oldidx = (idx = 0);
                        continue;
                    }
                    case '@': {
                        final int ni = tmp.indexOf(64, idx + 1);
                        if (ni < idx) {
                            this.error("ERROR: Input Field: '@'-End Marker not found");
                            break;
                        }
                        if (ni == ++idx) {
                            this.error("ERROR: Input Field: no name specified");
                            break;
                        }
                        final String name = tmp.substring(idx, ni);
                        idx = ni;
                        final TextField t;
                        if (this.fields == null || (t = this.fields.get(name)) == null) {
                            this.error("ERROR: Input Field: requested input \"" + name + "\" does not exist");
                            break;
                        }
                        cmd += t.getText();
                        if (this.clearFields) {
                            t.setText("");
                            break;
                        }
                        break;
                    }
                    default: {
                        cmd += tmp.substring(idx, ++idx);
                        break;
                    }
                }
                oldidx = ++idx;
            }
            if (oldidx <= tmp.length()) {
                cmd += tmp.substring(oldidx, tmp.length());
            }
            if (function != null) {
                if (function.equals("exit")) {
                    try {
                        System.exit(0);
                    }
                    catch (RuntimeException e) {
                        this.error("cannot exit: " + e);
                    }
                }
                if (function.equals("connect")) {
                    String address = null;
                    int port = -1;
                    try {
                        if ((idx = cmd.indexOf(",")) >= 0) {
                            try {
                                port = Integer.parseInt(cmd.substring(idx + 1, cmd.length()));
                            }
                            catch (Exception e2) {
                                port = -1;
                            }
                            cmd = cmd.substring(0, idx);
                        }
                        if (cmd.length() > 0) {
                            address = cmd;
                        }
                        if (address != null) {
                            if (port != -1) {
                                super.bus.broadcast(new SocketRequest(address, port));
                            }
                            else {
                                super.bus.broadcast(new SocketRequest(address, 23));
                            }
                        }
                        else {
                            this.error("connect: no address");
                        }
                    }
                    catch (Exception e2) {
                        this.error("connect(): failed");
                        e2.printStackTrace();
                    }
                }
                else if (function.equals("disconnect")) {
                    super.bus.broadcast(new SocketRequest());
                }
                else if (function.equals("detach")) {
                    this.error("detach not implemented yet");
                }
                else {
                    this.error("ERROR: function not implemented: \"" + function + "\"");
                }
                return;
            }
            if (cmd.length() > 0) {
                try {
                    this.write(cmd.getBytes());
                }
                catch (IOException e3) {
                    this.error("send: " + e3);
                }
            }
        }
    }
    
    public Component getPluginVisual() {
        return this.panel;
    }
    
    public Menu getPluginMenu() {
        return null;
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
}
