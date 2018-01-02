// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.www;

import java.io.File;
import org.apache.catalina.Realm;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.catalina.deploy.SecurityCollection;
import java.net.UnknownHostException;
import java.net.InetAddress;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Engine;
import org.apache.commons.modeler.util.IntrospectionUtils;
import org.apache.catalina.Container;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.component.Component;
import org.apache.catalina.startup.Embedded;

public class Tomcat extends Embedded implements Component
{
    final Application[] webapps;
    static final Log log;
    
    static {
        log = Log.getLog(Tomcat.class);
    }
    
    public Tomcat(final Application... webapps) {
        this.webapps = webapps;
    }
    
    public void init(final ComponentServer container) throws Exception {
        if (this.notAvailable(this.root())) {
            Tomcat.log.info("Web root (" + this.root() + ") not found - web server not started");
            return;
        }
        final Engine e = this.createEngine();
        final Host h = this.createHost("localhost", this.root());
        Context c = null;
        Application[] webapps;
        for (int length = (webapps = this.webapps).length, i = 0; i < length; ++i) {
            final Application a = webapps[i];
            switch (a) {
                case bootstrap: {
                    c = this.setupBootstrapApplication();
                    if (c != null) {
                        h.addChild((Container)c);
                        break;
                    }
                    break;
                }
                case report: {
                    c = this.setupReportApplication();
                    if (c != null) {
                        h.addChild((Container)c);
                        break;
                    }
                    break;
                }
            }
        }
        e.setDefaultHost("localhost");
        e.addChild((Container)h);
        this.addEngine(e);
        if (this.engines.length > 0) {
            final Connector https = this.createConnector(this.address(), this.port(), true);
            IntrospectionUtils.setProperty((Object)https, "sslProtocol", "TLS");
            IntrospectionUtils.setProperty((Object)https, "keystoreFile", String.valueOf(this.home()) + "/etc/cert/tomcat.keystore");
            IntrospectionUtils.setProperty((Object)https, "keystorePass", "cornerstone");
            IntrospectionUtils.setProperty((Object)https, "keystoreType", "JKS");
            IntrospectionUtils.setProperty((Object)https, "clientAuth", "false");
            https.setProtocol("TLS");
            this.addConnector(https);
            this.start();
        }
    }
    
    public void shutdown() {
        try {
            this.stop();
        }
        catch (Exception e) {
            Tomcat.log.error(this, e);
        }
    }
    
    public void trace() {
        Tomcat.log.warn("Not-Implemented");
    }
    
    public InetAddress address() throws UnknownHostException {
        InetAddress result = null;
        final String host = System.getProperty("cornerstone.www.address");
        if (host != null && host.length() > 0) {
            result = InetAddress.getByName(host);
        }
        return result;
    }
    
    public int port() {
        return Integer.parseInt(System.getProperty("cornerstone.www.port", "8080"));
    }
    
    Context setupBootstrapApplication() throws Exception {
        if (this.notDeployed("bootstrap")) {
            Tomcat.log.info("/bootstrap not deployed, context not created");
            return null;
        }
        final SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        final SecurityConstraint constraint = new SecurityConstraint();
        constraint.addAuthRole("BootstrapAdmin");
        constraint.addCollection(collection);
        final LoginConfig loginConfig = new LoginConfig();
        loginConfig.setAuthMethod("DIGEST");
        loginConfig.setRealmName("BootstrapRealm");
        final Context context = this.createContext("/bootstrap", String.valueOf(this.root()) + "/bootstrap");
        context.addConstraint(constraint);
        context.addSecurityRole("BootstrapAdmin");
        context.setLoginConfig(loginConfig);
        context.setRealm((Realm)new BootstrapRealm());
        return context;
    }
    
    Context setupReportApplication() throws Exception {
        if (this.notDeployed("report")) {
            Tomcat.log.info("/report not deployed, context not created");
            return null;
        }
        final SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        final SecurityConstraint constraint = new SecurityConstraint();
        constraint.addAuthRole("User");
        constraint.addCollection(collection);
        final LoginConfig loginConfig = new LoginConfig();
        loginConfig.setAuthMethod("DIGEST");
        loginConfig.setRealmName("UserRealm");
        final Context context = this.createContext("/report", String.valueOf(this.root()) + "/report");
        context.addConstraint(constraint);
        context.addSecurityRole("User");
        context.setLoginConfig(loginConfig);
        context.setRealm((Realm)new UserRealm());
        return context;
    }
    
    protected String home() {
        return System.getProperty("cornerstone.home");
    }
    
    protected String root() {
        return String.valueOf(this.home()) + "/webapps";
    }
    
    protected boolean notAvailable(final String path) {
        return !new File(path).canRead();
    }
    
    protected boolean notDeployed(final String context) {
        final String base = String.valueOf(this.root()) + "/" + context;
        final String war = String.valueOf(base) + ".war";
        return this.notAvailable(base) && this.notAvailable(war);
    }
    
    public enum Application
    {
        bootstrap("bootstrap", 0), 
        report("report", 1);
        
        private Application(final String s, final int n) {
        }
    }
}
