import java.awt.Frame;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Enumeration;
import modules.Module;
import java.io.IOException;
import java.util.Hashtable;
import java.awt.Container;
import java.util.Vector;
import display.Terminal;
import socket.TelnetIO;
import socket.StatusPeer;
import display.TerminalHost;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class telnet extends Applet implements Runnable, TerminalHost, StatusPeer
{
    protected TelnetIO tio;
    protected Terminal term;
    protected String address;
    protected int port;
    protected String proxy;
    protected int proxyport;
    protected String emulation;
    protected Vector modules;
    private boolean localecho;
    private boolean connected;
    private Thread t;
    private Container parent;
    public Hashtable params;
    
    public telnet() {
        this.port = 23;
        this.proxy = null;
        this.emulation = "vt320";
        this.modules = null;
        this.localecho = true;
        this.connected = false;
    }
    
    public boolean connect() {
        return this.connect(this.address, this.port);
    }
    
    public boolean connect(final String host) {
        return this.connect(host, this.port);
    }
    
    public boolean connect(final String host, final int prt) {
        this.address = host;
        this.port = prt;
        if (this.address == null || this.address.length() == 0) {
            return false;
        }
        if (this.t != null && this.connected) {
            System.err.println("telnet: connect: existing connection preserved");
            return false;
        }
        this.t = null;
        try {
            if (this.tio != null) {
                try {
                    this.tio.disconnect();
                }
                catch (IOException ex) {}
            }
            else {
                (this.tio = new TelnetIO()).setPeer((StatusPeer)this);
            }
            this.term.putString("Trying " + this.address + ((this.port == 23) ? "" : (" " + this.port)) + " ...\n\r");
            try {
                if (this.proxy != null) {
                    this.tio.connect(this.proxy, this.proxyport);
                    final String str = "relay " + this.address + " " + this.port + "\n";
                    final byte[] bytes = new byte[str.length()];
                    str.getBytes(0, str.length(), bytes, 0);
                    this.tio.send(bytes);
                }
                else {
                    this.tio.connect(this.address, this.port);
                }
                this.term.putString("Connected to " + this.address + ".\n\r");
                this.connected = true;
                this.localecho = true;
                if (this.getParameter("localecho") != null && this.getParameter("localecho").equals("no")) {
                    this.localecho = false;
                }
                if (this.modules != null) {
                    final Enumeration modlist = this.modules.elements();
                    while (modlist.hasMoreElements()) {
                        modlist.nextElement().connect(this.address, this.port);
                    }
                }
            }
            catch (IOException e) {
                this.term.putString("Failed to connect.\n\r");
                this.tio = null;
                System.err.println("telnet: failed to connect to " + this.address + " " + this.port);
                e.printStackTrace();
                return false;
            }
            (this.t = new Thread(this)).setPriority(1);
            this.t.start();
        }
        catch (Exception e2) {
            System.err.println("telnet: an error occured:");
            e2.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean disconnect() {
        if (this.tio == null) {
            System.err.println("telnet: no connection");
            return false;
        }
        try {
            this.connected = false;
            this.t = null;
            if (this.modules != null) {
                final Enumeration modlist = this.modules.elements();
                while (modlist.hasMoreElements()) {
                    modlist.nextElement().disconnect();
                }
            }
            this.term.putString("\n\rConnection closed.\n\r");
            this.tio.disconnect();
        }
        catch (Exception e) {
            System.err.println("telnet: disconnection problem");
            e.printStackTrace();
            this.tio = null;
            this.t = null;
            return false;
        }
        return true;
    }
    
    public String getAppletInfo() {
        String info = "The Java(tm) Telnet Applet\n$Id: telnet.java,v 1.21 1999/03/04 07:45:50 leo Exp $\n";
        info = String.valueOf(info) + "Terminal emulation: " + this.term.getTerminalType() + " [" + ((Component)this.term).toString() + "]\n";
        info = String.valueOf(info) + "Terminal IO version: " + this.tio.toString() + "\n";
        if (this.modules != null && this.modules.size() > 0) {
            info = String.valueOf(info) + "Resident modules loaded: (" + this.modules.size() + ")";
            for (int i = 0; i < this.modules.size(); ++i) {
                info = String.valueOf(info) + "   + " + this.modules.elementAt(i).toString() + "\n";
            }
        }
        return info;
    }
    
    public String getParameter(final String name) {
        if (this.params == null) {
            return super.getParameter(name);
        }
        return this.params.get(name);
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "address", "String", "IP address" }, { "port", "Integer", "Port number" }, { "proxy", "String", "IP address of relay" }, { "proxyport", "Integer", "Port number of relay" }, { "emulation", "String", "Emulation to be used (standard is vt320)" }, { "localecho", "String", "Localecho Mode (on/off/auto)" } };
        final String[][] tinfo = (String[][])((this.term != null) ? this.term.getParameterInfo() : null);
        String[][] pinfo;
        if (tinfo != null) {
            pinfo = new String[tinfo.length + 3][3];
        }
        else {
            pinfo = new String[3][3];
        }
        System.arraycopy(info, 0, pinfo, 0, 3);
        System.arraycopy(tinfo, 0, pinfo, 3, tinfo.length);
        return pinfo;
    }
    
    public void init() {
        this.parent = this.getParent();
        final String parameter = this.getParameter("address");
        this.address = parameter;
        if (parameter == null) {
            this.address = this.getDocumentBase().getHost();
        }
        String tmp;
        if ((tmp = this.getParameter("port")) == null) {
            this.port = 23;
        }
        else {
            this.port = Integer.parseInt(tmp);
        }
        final String parameter2 = this.getParameter("proxy");
        this.proxy = parameter2;
        if (parameter2 != null) {
            if ((tmp = this.getParameter("proxyport")) == null) {
                this.proxyport = 31415;
            }
            else {
                this.proxyport = Integer.parseInt(tmp);
            }
        }
        if ((this.emulation = this.getParameter("emulation")) == null) {
            this.emulation = "vt320";
        }
        try {
            this.term = (Terminal)Class.forName("display." + this.emulation).newInstance();
            System.out.println("telnet: load terminal emulation: " + this.emulation);
        }
        catch (Exception e) {
            System.err.println("telnet: cannot load terminal emulation " + this.emulation);
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.modules = new Vector();
        int nr = 1;
        while ((tmp = this.getParameter("module#" + nr++)) != null) {
            try {
                Panel north = null;
                Panel south = null;
                Panel west = null;
                Panel east = null;
                String position = "North";
                String initFile = null;
                if (tmp.indexOf(44) != -1) {
                    initFile = tmp.substring(tmp.indexOf(45));
                    tmp = tmp.substring(0, tmp.indexOf(44));
                    initFile = tmp.substring(tmp.indexOf(45));
                }
                if (tmp.indexOf(64) != -1) {
                    position = tmp.substring(tmp.indexOf(64) + 1);
                    tmp = tmp.substring(0, tmp.indexOf(64));
                }
                final Object obj = Class.forName("modules." + tmp).newInstance();
                try {
                    ((Module)obj).setLoader((Object)this);
                    this.modules.addElement(obj);
                    System.out.println("telnet: module " + tmp + " detected");
                }
                catch (ClassCastException ex) {
                    System.out.println("telnet: warning: " + tmp + " may not be a " + "valid module");
                }
                try {
                    final Component component = (Component)obj;
                    if (position.equals("North")) {
                        if (north == null) {
                            north = new Panel();
                            this.add("North", north);
                        }
                        north.add(component);
                    }
                    else if (position.equals("South")) {
                        if (south == null) {
                            south = new Panel();
                            this.add("South", south);
                        }
                        south.add(component);
                    }
                    else if (position.equals("East")) {
                        if (east == null) {
                            east = new Panel();
                            this.add("East", east);
                        }
                        east.add(component);
                    }
                    else if (position.equals("West")) {
                        if (west == null) {
                            west = new Panel();
                            this.add("West", west);
                        }
                        west.add(component);
                    }
                    System.err.println("telnet: module " + tmp + " is a visible component");
                }
                catch (ClassCastException ex2) {}
            }
            catch (Exception e2) {
                System.err.println("telnet: cannot load module " + tmp);
                e2.printStackTrace();
            }
        }
        if (this.modules.isEmpty()) {
            this.modules = null;
        }
        this.add("Center", (Component)this.term);
    }
    
    public static void main(final String[] args) {
        final telnet applet = new telnet();
        applet.params = new Hashtable();
        switch (args.length) {
            case 2: {
                applet.params.put("port", args[1]);
            }
            case 1: {
                applet.params.put("address", args[0]);
                break;
            }
            default: {
                System.out.println("Usage: java telnet host [port]");
                System.exit(0);
                break;
            }
        }
        applet.params.put("VTscrollbar", "true");
        applet.params.put("module#1", "ButtonBar");
        applet.params.put("1#Button", "Exit|\\$exit()");
        applet.params.put("2#Button", "Connect|\\$connect(\\@address@,\\@port@)");
        applet.params.put("3#Input", "address#30|" + ((args.length > 0) ? args[0] : "localhost"));
        applet.params.put("4#Input", "port#4|23");
        applet.params.put("5#Button", "Disconnect|\\$disconnect()");
        final Frame frame = new Frame("The Java Telnet Application [" + args[0] + "]");
        frame.setLayout(new BorderLayout());
        frame.add("Center", (Component)applet);
        frame.resize(380, 590);
        applet.init();
        frame.pack();
        frame.show();
        applet.start();
    }
    
    public Object notifyStatus(final Vector status) {
        final String what = status.elementAt(0);
        if (what.equals("NAWS")) {
            return this.term.getSize();
        }
        if (!what.equals("TTYPE")) {
            if (what.equals("LOCALECHO") || what.equals("NOLOCALECHO")) {
                boolean nlocalecho = this.localecho;
                if (what.equals("LOCALECHO")) {
                    nlocalecho = true;
                }
                if (what.equals("NOLOCALECHO")) {
                    nlocalecho = false;
                }
                if (this.getParameter("localecho") == null || this.getParameter("localecho").equals("auto")) {
                    this.localecho = nlocalecho;
                }
            }
            return null;
        }
        if (this.term.getTerminalType() == null) {
            return this.emulation;
        }
        return this.term.getTerminalType();
    }
    
    public void run() {
        while (this.t != null) {
            try {
                String tmp = new String(this.tio.receive(), 0);
                if (this.modules != null) {
                    final Enumeration modlist = this.modules.elements();
                    while (modlist.hasMoreElements()) {
                        final Module m = modlist.nextElement();
                        final String modified = m.receive(tmp);
                        if (modified == null) {
                            this.modules.removeElement(m);
                        }
                        else {
                            tmp = modified;
                        }
                    }
                }
                this.term.putString(tmp);
            }
            catch (IOException ex) {
                this.disconnect();
            }
        }
    }
    
    public boolean send(final String str) {
        if (this.connected) {
            try {
                final byte[] bytes = new byte[str.length()];
                str.getBytes(0, str.length(), bytes, 0);
                this.tio.send(bytes);
                if (!this.localecho) {
                    return true;
                }
                if (str.length() == 2 && str.charAt(0) == '\r' && str.charAt(1) == '\0') {
                    this.term.putString("\r\n");
                    return true;
                }
                this.term.putString(str);
                return true;
            }
            catch (Exception ex) {
                System.err.println("telnet.send(): disconnected");
                this.disconnect();
                return false;
            }
            return false;
        }
        return false;
    }
    
    public void start() {
        if (!this.connect(this.address, this.port) && this.params == null) {
            this.showStatus("telnet: connection to " + this.address + " " + this.port + " failed");
        }
    }
    
    public final void stop() {
        this.disconnect();
    }
    
    public boolean writeToSocket(final String str) {
        if (this.connected) {
            try {
                final byte[] bytes = new byte[str.length()];
                str.getBytes(0, str.length(), bytes, 0);
                this.tio.send(bytes);
                return true;
            }
            catch (Exception ex) {
                System.err.println("telnet.send(): disconnected");
                this.disconnect();
                return false;
            }
            return false;
        }
        return false;
    }
    
    public void writeToUser(final String str) {
        if (this.term != null) {
            this.term.putString(str);
        }
    }
}
