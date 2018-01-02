// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.awt.Window;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Button;
import java.awt.Container;
import java.util.Hashtable;
import java.awt.Panel;

public class ButtonBar extends Panel implements Module
{
    private telnet parent;
    private Hashtable buttons;
    private Hashtable fields;
    private Container toplevel;
    
    public ButtonBar() {
        this.buttons = null;
        this.fields = null;
    }
    
    public void addNotify() {
        if (this.buttons == null && this.fields == null) {
            int nr = 1;
            String button = null;
            String input = null;
            while ((button = this.parent.getParameter(String.valueOf(nr) + "#Button")) != null || (input = this.parent.getParameter(String.valueOf(nr) + "#Input")) != null) {
                ++nr;
                if (button != null) {
                    if (this.buttons == null) {
                        this.buttons = new Hashtable();
                    }
                    final int idx = button.indexOf(124);
                    if (button.length() == 0) {
                        System.out.println("ButtonBar: Button: no definition");
                    }
                    if (idx < 0 || idx == 0) {
                        System.out.println("ButtonBar: Button: empty name \"" + button + "\"");
                        continue;
                    }
                    if (idx == button.length() - 1) {
                        System.out.println("ButtonBar: Button: empty command \"" + button + "\"");
                        continue;
                    }
                    final Button b = new Button(button.substring(0, idx));
                    this.buttons.put(b, button.substring(idx + 1, button.length()));
                    this.add(b);
                }
                else if (input != null) {
                    if (this.fields == null) {
                        this.fields = new Hashtable();
                    }
                    if (this.buttons == null) {
                        this.buttons = new Hashtable();
                    }
                    int idx = input.indexOf(124);
                    if (input.length() == 0) {
                        System.out.println("ButtonBar: Input field: no definition");
                    }
                    if (idx < 0 || idx == 0) {
                        System.out.println("ButtonBar: Input field: empty name \"" + input + "\"");
                        continue;
                    }
                    final int si;
                    if ((si = input.indexOf(35, 0)) == 0) {
                        System.out.println("ButtonBar: Input field: empty name");
                        continue;
                    }
                    int size;
                    if (si < 0 || si == idx - 1) {
                        size = 10;
                    }
                    else {
                        size = Integer.parseInt(input.substring(si + 1, idx));
                    }
                    final TextField t = new TextField(input.substring(idx + 1, (input.lastIndexOf(124) == idx) ? input.length() : (idx = input.lastIndexOf(124))), size);
                    this.buttons.put(t, input.substring(idx + 1, input.length()));
                    this.fields.put(input.substring(0, (si < 0) ? idx : si), t);
                    this.add(t);
                }
                input = (button = null);
            }
        }
        super.addNotify();
    }
    
    public void connect(final String host, final int port) {
    }
    
    public void disconnect() {
    }
    
    public boolean handleEvent(final Event evt) {
        String tmp;
        if (evt.id != 1001 || (tmp = this.buttons.get(evt.target)) == null) {
            return false;
        }
        System.out.println("ButtonBar: " + tmp);
        String cmd = "";
        String function = null;
        int idx = 0;
        int oldidx = 0;
        while ((idx = tmp.indexOf(92, oldidx)) >= 0 && ++idx <= tmp.length()) {
            cmd = String.valueOf(cmd) + tmp.substring(oldidx, idx - 1);
            switch (tmp.charAt(idx)) {
                case 'b': {
                    cmd = String.valueOf(cmd) + "\b";
                    break;
                }
                case 'e': {
                    cmd = String.valueOf(cmd) + "\u001b";
                    break;
                }
                case 'n': {
                    cmd = String.valueOf(cmd) + "\n";
                    break;
                }
                case 'r': {
                    cmd = String.valueOf(cmd) + "\r";
                    break;
                }
                case '$': {
                    int ni = tmp.indexOf(40, idx + 1);
                    if (ni < idx) {
                        System.out.println("ERROR: Function: missing '('");
                        break;
                    }
                    if (ni == ++idx) {
                        System.out.println("ERROR: Function: missing name");
                        break;
                    }
                    function = tmp.substring(idx, ni);
                    idx = ni + 1;
                    ni = tmp.indexOf(41, idx);
                    if (ni < idx) {
                        System.out.println("ERROR: Function: missing ')'");
                        break;
                    }
                    tmp = tmp.substring(idx, ni);
                    oldidx = (idx = 0);
                    continue;
                }
                case '@': {
                    final int ni = tmp.indexOf(64, idx + 1);
                    if (ni < idx) {
                        System.out.println("ERROR: Input Field: '@'-End Marker not found");
                        break;
                    }
                    if (ni == ++idx) {
                        System.out.println("ERROR: Input Field: no name specified");
                        break;
                    }
                    final String name = tmp.substring(idx, ni);
                    idx = ni;
                    final TextField t;
                    if (this.fields == null || (t = this.fields.get(name)) == null) {
                        System.out.println("ERROR: Input Field: requested input \"" + name + "\" does not exist");
                        break;
                    }
                    cmd = String.valueOf(cmd) + t.getText();
                    t.setText("");
                    break;
                }
                default: {
                    cmd = String.valueOf(cmd) + tmp.substring(idx, ++idx);
                    break;
                }
            }
            oldidx = ++idx;
        }
        if (oldidx <= tmp.length()) {
            cmd = String.valueOf(cmd) + tmp.substring(oldidx, tmp.length());
        }
        if (function != null) {
            if (function.equals("exit")) {
                try {
                    System.exit(0);
                }
                catch (Exception e) {
                    e.printStackTrace();
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
                        catch (Exception ex) {
                            port = -1;
                        }
                        cmd = cmd.substring(0, idx);
                    }
                    if (cmd.length() > 0) {
                        address = cmd;
                    }
                    if (address != null) {
                        if (port != -1) {
                            this.parent.connect(address, port);
                        }
                        else {
                            this.parent.connect(address);
                        }
                    }
                    else {
                        this.parent.connect();
                    }
                }
                catch (Exception e2) {
                    System.err.println("ButtonBar: connect(): failed");
                    e2.printStackTrace();
                }
            }
            else if (function.equals("disconnect") && this.parent.disconnect()) {
                this.parent.send("\r\nClosed connection.\r\n");
            }
            else if (function.equals("detach")) {
                if (((Component)this.parent).getParent() instanceof Frame) {
                    final Frame top = (Frame)((Component)this.parent).getParent();
                    if (this.toplevel != null) {
                        System.out.println("ButtonBar: reattaching applet...");
                        this.toplevel.setLayout(new BorderLayout());
                        this.toplevel.add("Center", (Component)this.parent);
                        this.toplevel.validate();
                        this.toplevel.layout();
                        this.toplevel = null;
                    }
                    else {
                        System.out.println("ButtonBar: destroying window...");
                        this.parent.disconnect();
                    }
                    top.dispose();
                }
                else {
                    System.out.println("ButtonBar: detaching applet...");
                    this.toplevel = ((Component)this.parent).getParent();
                    final frame top2 = new frame("The Java Telnet Applet");
                    final Dimension s = ((Component)this.parent).size();
                    ((Component)top2).reshape(0, 0, s.width, s.height);
                    ((Container)top2).setLayout(new BorderLayout());
                    ((Container)top2).add("Center", (Component)this.parent);
                    ((Window)top2).pack();
                    ((Window)top2).show();
                }
            }
            else {
                System.out.println("ERROR: function not implemented: \"" + function + "\"");
            }
            return true;
        }
        if (cmd.length() > 0) {
            this.parent.send(cmd);
        }
        return true;
    }
    
    public String receive(final String s) {
        return null;
    }
    
    public void setLoader(final Object o) {
        this.parent = (telnet)o;
    }
}
