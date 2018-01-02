// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import java.util.Properties;
import org.jboss.console.manager.interfaces.SimpleTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.remote.AppletRemoteMBeanInvoker;
import java.net.URL;
import org.jboss.console.remote.SimpleRemoteMBeanInvoker;
import javax.swing.JFrame;
import java.applet.AppletStub;
import javax.swing.JTree;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JApplet;

public class AppletBrowser extends JApplet
{
    AdminTreeBrowser treeBrowser;
    AppletAdminContext ctx;
    public static final String RIGHT_FRAME_NAME = "right";
    protected String sessionId;
    protected String pmJmxName;
    
    public AppletBrowser() {
        this.treeBrowser = null;
        this.ctx = null;
        this.sessionId = null;
        this.pmJmxName = null;
    }
    
    public void start() {
        try {
            this.ctx = new AppletAdminContext();
            this.initAppletParams();
            this.treeBrowser = new AdminTreeBrowser(this.ctx);
            this.initComponents();
            this.initRefreshThread();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshTree(final boolean force) {
        this.treeBrowser.refreshTree(force);
    }
    
    protected void initAppletParams() {
        this.sessionId = this.getParameter("SessionId");
        if (this.sessionId != null) {
            this.sessionId = "jsessionid=" + this.sessionId;
        }
        else {
            this.sessionId = "";
        }
        this.pmJmxName = this.getParameter("PMJMXName");
        if (this.pmJmxName == null) {
            this.pmJmxName = "jboss.admin:service=PluginManager";
        }
    }
    
    protected void initComponents() {
        final JTree tree = this.treeBrowser.getTree();
        final JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.getContentPane().add(scrollPane, "Center");
    }
    
    protected void initRefreshThread() {
        try {
            final String strRefreshSec = this.getParameter("RefreshTime");
            if (strRefreshSec != null && !"".equals(strRefreshSec)) {
                final long refresh = Long.parseLong(strRefreshSec);
                final Thread t = new Thread(new Runnable() {
                    public synchronized void run() {
                        final long timeout = refresh * 1000L;
                    Label_0009_Outer:
                        while (true) {
                            while (true) {
                                try {
                                    while (true) {
                                        this.wait(timeout);
                                        AppletBrowser.this.treeBrowser.refreshTree(false);
                                    }
                                }
                                catch (Exception displayed) {
                                    continue Label_0009_Outer;
                                }
                                continue;
                            }
                        }
                    }
                });
                t.start();
            }
        }
        catch (Exception displayed) {
            displayed.printStackTrace();
        }
    }
    
    public static void main(final String[] args) throws Exception {
        final JApplet applet = new AppletBrowser();
        applet.setStub(new MainAppletStub());
        final JFrame frame = new JFrame("Administration Console");
        frame.getContentPane().add(applet);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(3);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
    
    public class AppletAdminContext implements TreeContext
    {
        String webHost;
        String hostname;
        SimpleRemoteMBeanInvoker invoker;
        
        public AppletAdminContext() {
            this.webHost = null;
            this.hostname = null;
            this.invoker = null;
            final URL root = AppletBrowser.this.getCodeBase();
            this.webHost = root.getProtocol() + ":";
            if (root.getAuthority() != null && root.getAuthority().length() > 0) {
                this.webHost += "//";
                this.webHost += root.getAuthority();
            }
            if (!this.webHost.endsWith("/")) {
                this.webHost += "/";
            }
            this.hostname = AppletBrowser.this.getCodeBase().getHost();
        }
        
        public synchronized SimpleRemoteMBeanInvoker getRemoteMBeanInvoker() {
            if (this.invoker == null) {
                System.out.println(AppletBrowser.this.getCodeBase().toString() + "Invoker");
                try {
                    this.invoker = new AppletRemoteMBeanInvoker(AppletBrowser.this.getCodeBase().toString() + "Invoker");
                }
                catch (Exception displayed) {
                    displayed.printStackTrace();
                }
            }
            return this.invoker;
        }
        
        public void doAdminTreeAction(final TreeAction action) {
            if (action != null && action instanceof HttpLinkTreeAction) {
                final HttpLinkTreeAction act = (HttpLinkTreeAction)action;
                this.openLink(act.getTarget(), act.getFrame());
            }
        }
        
        public void doPopupMenuAction(final SimpleTreeNodeMenuEntry entry) {
            final TreeAction ta = entry.getAction();
            if (ta instanceof HttpLinkTreeAction) {
                final HttpLinkTreeAction act = (HttpLinkTreeAction)ta;
                this.openLink(act.getTarget(), act.getFrame());
            }
            else if (ta instanceof AppletTreeAction) {
                ((AppletTreeAction)ta).doAction(AppletBrowser.this.ctx, AppletBrowser.this);
            }
        }
        
        public Properties getJndiProperties() {
            final Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
            props.setProperty("java.naming.provider.url", this.hostname);
            return props;
        }
        
        public String getServiceJmxName() {
            return AppletBrowser.this.pmJmxName;
        }
        
        public void openLink(final String target, final String frame) {
            try {
                if (target == null) {
                    return;
                }
                System.out.println(target);
                if (frame == null) {
                    AppletBrowser.this.getAppletContext().showDocument(new URL(this.localizeUrl(target)), "right");
                }
                else {
                    AppletBrowser.this.getAppletContext().showDocument(new URL(this.localizeUrl(target)), frame);
                }
            }
            catch (Exception tobad) {
                tobad.printStackTrace();
            }
        }
        
        public String localizeUrl(final String sourceUrl) {
            String target = sourceUrl;
            if (target == null) {
                return null;
            }
            if (!target.toLowerCase().startsWith("http")) {
                if (target.startsWith("/")) {
                    target = target.substring(1);
                }
                target = this.webHost + target;
                if (target.indexOf("?") >= 0) {
                    target = target + "&" + AppletBrowser.this.sessionId;
                }
                else {
                    target = target + ";" + AppletBrowser.this.sessionId;
                }
            }
            return target;
        }
    }
}
