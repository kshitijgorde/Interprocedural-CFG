// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import javax.swing.JMenu;
import javax.swing.JComponent;
import de.mud.jta.event.SocketRequest;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.TelnetCommandRequest;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import de.mud.jta.PluginListener;
import java.io.InputStream;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class ButtonBar extends Plugin implements FilterPlugin, VisualPlugin, ActionListener, ListSelectionListener
{
    protected JPanel panel;
    private Map buttons;
    private Map choices;
    private Map fields;
    private boolean clearFields;
    FilterPlugin source;
    
    public ButtonBar(final PluginBus bus, final String id) {
        super(bus, id);
        this.panel = new JPanel();
        this.buttons = null;
        this.choices = null;
        this.fields = null;
        this.clearFields = true;
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig cfg) {
                final String file = cfg.getProperty("ButtonBar", id, "setup");
                ButtonBar.this.clearFields = new Boolean(cfg.getProperty("ButtonBar", id, "clearFields"));
                if (file == null) {
                    ButtonBar.this.error("no setup file");
                    return;
                }
                StreamTokenizer setup = null;
                InputStream is = null;
                try {
                    is = this.getClass().getResourceAsStream(file);
                }
                catch (Exception ex) {}
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
                catch (Exception e) {
                    ButtonBar.this.error("cannot load " + file + ": " + e);
                    return;
                }
                setup.commentChar(35);
                setup.quoteChar(34);
                ButtonBar.this.fields = new HashMap();
                ButtonBar.this.buttons = new HashMap();
                ButtonBar.this.choices = new HashMap();
                final GridBagLayout l = new GridBagLayout();
                final GridBagConstraints c = new GridBagConstraints();
                ButtonBar.this.panel.setLayout(l);
                c.fill = 1;
                int ChoiceCount = 0;
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
                                            final JButton b = new JButton(descr);
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
                                else if (setup.sval.equals("label")) {
                                    if ((token = setup.nextToken()) != -1) {
                                        final String descr = setup.sval;
                                        final JLabel b2 = new JLabel(descr);
                                        l.setConstraints(b2, ButtonBar.this.constraints(c, setup));
                                        ButtonBar.this.panel.add(b2);
                                        continue;
                                    }
                                    ButtonBar.this.error("unexpected end of file");
                                    continue;
                                }
                                else {
                                    if (setup.sval.equals("choice")) {
                                        ++ChoiceCount;
                                        final String ident = "C" + ChoiceCount + ".";
                                        final JList list = new JList();
                                        ButtonBar.this.choices.put(list, ident);
                                        list.addListSelectionListener(ButtonBar.this);
                                        l.setConstraints(list, ButtonBar.this.constraints(c, setup));
                                        ButtonBar.this.panel.add(list);
                                        while ((token = setup.nextToken()) != -1) {
                                            if (isKeyword(setup.sval)) {
                                                setup.pushBack();
                                                break;
                                            }
                                            final String descr2 = setup.sval;
                                            token = setup.nextToken();
                                            if (token == -1) {
                                                ButtonBar.this.error("unexpected end of file");
                                            }
                                            else {
                                                final String value = setup.sval;
                                                if (isKeyword(value)) {
                                                    System.err.println(descr2 + ": missing choice command");
                                                    setup.pushBack();
                                                    break;
                                                }
                                                System.out.println("choice: name='" + descr2 + "', value='" + value);
                                                list.add(descr2, new JLabel(descr2));
                                                ButtonBar.this.choices.put(ident + descr2, value);
                                            }
                                        }
                                        ButtonBar.this.error("choices hash: " + ButtonBar.this.choices);
                                        continue;
                                    }
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
                                            if (isKeyword(setup.sval)) {
                                                setup.pushBack();
                                            }
                                            else {
                                                command = setup.sval;
                                            }
                                            token = setup.nextToken();
                                            if (isKeyword(setup.sval)) {
                                                setup.pushBack();
                                                init = command;
                                            }
                                            else {
                                                init = setup.sval;
                                            }
                                            final JTextField t = new JTextField(init, size);
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
                catch (IOException e2) {
                    ButtonBar.this.error("unexpected error while reading setup: " + e2);
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
    
    public void valueChanged(final ListSelectionEvent evt) {
        final String ident;
        if ((ident = this.choices.get(evt.getSource())) != null) {
            final JList list = (JList)evt.getSource();
            final String tmp = this.choices.get(ident + list.getSelectedValue());
            if (tmp != null) {
                this.processEvent(tmp);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent evt) {
        final String tmp;
        if ((tmp = this.buttons.get(evt.getSource())) != null) {
            this.processEvent(tmp);
        }
    }
    
    private void processEvent(String tmp) {
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
                    final JTextField t;
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
        if (function == null) {
            if (cmd.length() > 0) {
                try {
                    this.write(cmd.getBytes());
                }
                catch (IOException e) {
                    this.error("send: " + e);
                }
            }
            return;
        }
        if (function.equals("break")) {
            this.bus.broadcast(new TelnetCommandRequest((byte)(-13)));
            return;
        }
        if (function.equals("exit")) {
            try {
                System.exit(0);
            }
            catch (Exception e2) {
                this.error("cannot exit: " + e2);
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
                    catch (Exception e3) {
                        port = -1;
                    }
                    cmd = cmd.substring(0, idx);
                }
                if (cmd.length() > 0) {
                    address = cmd;
                }
                if (address != null) {
                    if (port != -1) {
                        this.bus.broadcast(new SocketRequest(address, port));
                    }
                    else {
                        this.bus.broadcast(new SocketRequest(address, 23));
                    }
                }
                else {
                    this.error("connect: no address");
                }
            }
            catch (Exception e3) {
                this.error("connect(): failed");
                e3.printStackTrace();
            }
        }
        else if (function.equals("disconnect")) {
            this.bus.broadcast(new SocketRequest());
        }
        else if (function.equals("detach")) {
            this.error("detach not implemented yet");
        }
        else {
            this.error("ERROR: function not implemented: \"" + function + "\"");
        }
    }
    
    public JComponent getPluginVisual() {
        return this.panel;
    }
    
    public JMenu getPluginMenu() {
        return null;
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    private static boolean isKeyword(final String txt) {
        return txt.equals("button") || txt.equals("label") || txt.equals("input") || txt.equals("stretch") || txt.equals("choice") || txt.equals("break");
    }
}
