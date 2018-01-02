// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.console;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import soht.client.java.core.Proxy;
import soht.client.java.configuration.ConfigurationException;
import soht.client.java.configuration.Host;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Vector;
import soht.client.java.configuration.ConfigurationManager;
import java.awt.event.WindowListener;
import java.applet.Applet;

public class AppletProxy extends Applet implements WindowListener, Runnable
{
    ConfigurationManager configurationManager;
    String propertiesFileURL;
    Vector proxyList;
    public String boundPortList;
    int frame;
    int delay;
    Thread animator;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    Image dot;
    Image bigDot;
    
    public String getBoundPortList() {
        return this.boundPortList;
    }
    
    public AppletProxy() {
        this.configurationManager = null;
        this.propertiesFileURL = null;
        this.proxyList = new Vector();
        this.boundPortList = "...not set yet...";
    }
    
    public void readParameters() {
        System.out.println("Inside readparameters");
        final String serverUrl = this.getParameter("server.url");
        if (serverUrl == null) {
            System.out.println("Missing required server.url parameter");
        }
        else {
            this.configurationManager.setServerURL(serverUrl);
        }
        final String loginRequired = this.getParameter("server.loginrequired");
        this.configurationManager.setServerLoginRequired(true);
        if (loginRequired == null) {
            System.out.println("Missing required server.url parameter");
        }
        else if (loginRequired.equals("true")) {
            this.configurationManager.setServerLoginRequired(true);
        }
        else {
            this.configurationManager.setServerLoginRequired(false);
        }
        final String serverUsername = this.getParameter("server.username");
        if (serverUsername != null) {
            this.configurationManager.setServerUsername(serverUsername);
        }
        final String serverPassword = this.getParameter("server.password");
        if (serverPassword != null) {
            this.configurationManager.setServerPassword(serverPassword);
        }
        final String stateless = this.getParameter("server.stateless");
        this.configurationManager.setUseStatelessConnection(false);
        if (stateless != null) {
            if (stateless.equals("true")) {
                this.configurationManager.setUseStatelessConnection(true);
            }
            else {
                this.configurationManager.setUseStatelessConnection(false);
            }
        }
        final String useProxy = this.getParameter("proxy.useproxy");
        this.configurationManager.setUseHTTPProxy(false);
        if (useProxy != null && useProxy.equals("true")) {
            this.configurationManager.setUseHTTPProxy(true);
        }
        final String proxyHost = this.getParameter("proxy.host");
        if (proxyHost != null) {
            this.configurationManager.setProxyHost(proxyHost);
        }
        final String proxyPort = this.getParameter("proxy.port");
        if (proxyPort != null) {
            this.configurationManager.setProxyPort(proxyPort);
        }
        final String portList = this.getParameter("portList");
        if (portList == null) {
            System.out.println("Missing required portList parameter.");
            return;
        }
        System.out.println("PortList = " + portList);
        final StringTokenizer st = new StringTokenizer(portList);
        while (st.hasMoreTokens()) {
            final String portInfo = st.nextToken();
            System.out.println("portInfo = " + portInfo);
            final StringTokenizer st2 = new StringTokenizer(portInfo, ":");
            if (st2.hasMoreTokens()) {
                String localPort = null;
                String remoteHost = null;
                String remotePort = null;
                if (st2.hasMoreTokens()) {
                    localPort = st2.nextToken();
                }
                if (st2.hasMoreTokens()) {
                    remoteHost = st2.nextToken();
                }
                if (st2.hasMoreTokens()) {
                    remotePort = st2.nextToken();
                }
                if (localPort == null || remoteHost == null || remotePort == null) {
                    System.out.println("Unable to parese: " + portInfo);
                }
                else {
                    try {
                        final Host h = new Host(localPort, remoteHost, remotePort);
                        System.out.println("Adding " + localPort + " " + remoteHost + " " + remotePort + " to configurationManager");
                        this.configurationManager.addHost(h);
                    }
                    catch (ConfigurationException e) {
                        System.out.println("Unable to add " + portInfo);
                    }
                }
            }
        }
        System.out.println("leaving readparameters");
    }
    
    public void init() {
        try {
            this.configurationManager = new ConfigurationManager();
            if (this.configurationManager == null) {
                System.out.println("Unable to configure the configurationmanager");
                return;
            }
            this.readParameters();
            this.initGraphics();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void start() {
        try {
            if (this.configurationManager != null) {
                final Vector proxies = new Vector();
                final Enumeration hosts = this.configurationManager.getHosts().elements();
                while (hosts.hasMoreElements()) {
                    final Host host = hosts.nextElement();
                    final Proxy p = new Proxy(this.configurationManager, host);
                    proxies.add(p);
                    p.startProxy();
                    this.proxyList.addElement(p);
                    System.out.println("Mapping local port (requested): " + host.getLocalPort() + " to remote host: " + host.getRemoteHost() + ":" + host.getRemotePort() + " using SOHT Server at: " + this.configurationManager.getServerURL());
                }
                try {
                    Thread.sleep(200L);
                }
                catch (Exception ex) {}
                boolean allProxiesBound = false;
                final int maxLoop = 20;
                int loopCount = 0;
                while (!allProxiesBound && loopCount < maxLoop) {
                    ++loopCount;
                    boolean checkingAllProxiesBound = true;
                    Proxy p2;
                    for (int i = 0; checkingAllProxiesBound && i < proxies.size(); checkingAllProxiesBound = p2.isRunning(), ++i) {
                        p2 = proxies.elementAt(i);
                    }
                    if (checkingAllProxiesBound) {
                        allProxiesBound = checkingAllProxiesBound;
                    }
                    else {
                        try {
                            Thread.sleep(500L);
                        }
                        catch (Exception ex2) {}
                    }
                }
                if (!allProxiesBound) {
                    throw new Exception("tunnel proxies failed to start in timely manner.");
                }
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < proxies.size(); ++i) {
                    if (i > 0) {
                        sb.append(' ');
                    }
                    final Proxy p2 = proxies.elementAt(i);
                    final Host host2 = p2.getHost();
                    sb.append(host2.getBoundLocalPort()).append(':').append(host2.getRemoteHost()).append(':').append(host2.getRemotePort());
                }
                this.boundPortList = sb.toString();
                System.out.println("boundPortList:  [" + this.boundPortList + "]");
            }
            (this.animator = new Thread(this)).start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        this.animator = null;
        this.offImage = null;
        this.offGraphics = null;
    }
    
    public void stopProxies() {
        System.out.println("*********Inside stop Proxies *******");
        if (this.proxyList == null) {
            return;
        }
        System.out.println("  # of proxies :" + this.proxyList.size());
        for (int i = 0; i < this.proxyList.size(); ++i) {
            final Proxy proxy = this.proxyList.elementAt(i);
            final Host host = proxy.getHost();
            System.out.println("Stopping proxy thread for : " + host.getLocalPort() + " to remote host: " + host.getRemoteHost() + ":" + host.getRemotePort());
            proxy.stopProxy();
        }
    }
    
    public void destroy() {
        System.out.println("Destroying applet");
        this.stopProxies();
    }
    
    public void windowClosing(final WindowEvent evt) {
        System.out.println("Closing window");
        this.stopProxies();
    }
    
    public void windowActivated(final WindowEvent evt) {
    }
    
    public void windowDeactivated(final WindowEvent evt) {
    }
    
    public void windowOpened(final WindowEvent evt) {
    }
    
    public void windowClosed(final WindowEvent evt) {
    }
    
    public void windowIconified(final WindowEvent evt) {
    }
    
    public void windowDeiconified(final WindowEvent evt) {
    }
    
    public void initGraphics() {
        final int fps = 15;
        this.delay = ((fps > 0) ? (1000 / fps) : 100);
        this.dot = this.getImage(this.getCodeBase(), "img/dot.gif");
        this.bigDot = this.getImage(this.getCodeBase(), "img/bigDot.gif");
    }
    
    public void run() {
        long tm = System.currentTimeMillis();
        while (Thread.currentThread() == this.animator) {
            this.repaint();
            try {
                tm += this.delay;
                Thread.sleep(Math.max(0L, tm - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                break;
            }
            ++this.frame;
        }
    }
    
    public void update(final Graphics g) {
        final Dimension d = this.getSize();
        if (this.offGraphics == null || d.width != this.offDimension.width || d.height != this.offDimension.height) {
            this.offDimension = d;
            this.offImage = this.createImage(d.width, d.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(Color.white);
        this.offGraphics.fillRect(0, 0, d.width, d.height);
        this.offGraphics.setColor(Color.black);
        this.paintFrame(this.offGraphics);
        g.drawImage(this.offImage, 0, 0, null);
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void paintFrame(final Graphics g) {
        final Dimension d = this.getSize();
        int w = this.dot.getWidth(this);
        int h = this.dot.getHeight(this);
        if (w > 0 && h > 0) {
            w += d.width;
            g.drawImage(this.dot, d.width - this.frame * 2 % w, (d.height - h) / 2, this);
            g.drawImage(this.dot, d.width - this.frame * 4 % w, (d.height - h) / 3, this);
            g.drawImage(this.dot, d.width - this.frame * 5 % w, (d.height - h) / 4, this);
            g.drawImage(this.dot, d.width - this.frame * 8 % w, (d.height - h) / 5, this);
        }
        w = this.bigDot.getWidth(this);
        h = this.bigDot.getHeight(this);
        if (w > 0 && h > 0) {
            w += d.width;
            g.drawImage(this.bigDot, d.width - this.frame * 2 % w, (d.height - h) / 5, this);
            g.drawImage(this.bigDot, d.width - this.frame * 5 % w, (d.height - h) / 3, this);
            g.drawImage(this.bigDot, d.width - this.frame * 7 % w, (d.height - h) / 4, this);
        }
    }
}
