// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import java.util.List;
import java.lang.management.ManagementFactory;
import java.io.IOException;
import java.util.Map;
import javax.management.remote.JMXConnectorFactory;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.lang.reflect.Method;
import sun.management.ConnectorAddressLink;
import java.util.Set;
import java.net.URLClassLoader;
import java.net.URL;
import java.io.File;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;

public abstract class AbstractJmxCommand extends AbstractCommand
{
    public static final String DEFAULT_JMX_URL = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
    private JMXServiceURL jmxServiceUrl;
    private String jmxUser;
    private String jmxPassword;
    private boolean jmxUseLocal;
    private JMXConnector jmxConnector;
    private MBeanServerConnection jmxConnection;
    
    protected JMXServiceURL getJmxServiceUrl() {
        return this.jmxServiceUrl;
    }
    
    public static String getJVM() {
        return System.getProperty("java.vm.specification.vendor");
    }
    
    public static boolean isSunJVM() {
        return getJVM().equals("Sun Microsystems Inc.");
    }
    
    protected JMXServiceURL useJmxServiceUrl() throws MalformedURLException {
        if (this.getJmxServiceUrl() == null) {
            String jmxUrl = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
            int connectingPid = -1;
            if (isSunJVM()) {
                try {
                    final String javaHome = System.getProperty("java.home");
                    final String tools = javaHome + File.separator + ".." + File.separator + "lib" + File.separator + "tools.jar";
                    final URLClassLoader loader = new URLClassLoader(new URL[] { new File(tools).toURI().toURL() });
                    final Class monitoredHostClass = Class.forName("sun.jvmstat.monitor.MonitoredHost", true, loader);
                    final Method getMonitoredHostMethod = monitoredHostClass.getMethod("getMonitoredHost", String.class);
                    final Object host = getMonitoredHostMethod.invoke(null, null);
                    final Method activeVmsMethod = host.getClass().getMethod("activeVms", (Class<?>[])null);
                    final Set vms = (Set)activeVmsMethod.invoke(host, (Object[])null);
                    for (final Object vmid : vms) {
                        final int pid = (int)vmid;
                        final Class vmIdentifierClass = Class.forName("sun.jvmstat.monitor.VmIdentifier", true, loader);
                        final Constructor vmIdentifierConstructor = vmIdentifierClass.getConstructor(String.class);
                        final Object vmIdentifier = vmIdentifierConstructor.newInstance(vmid.toString());
                        final Method getMonitoredVmMethod = host.getClass().getMethod("getMonitoredVm", vmIdentifierClass);
                        final Object mvm = getMonitoredVmMethod.invoke(host, vmIdentifier);
                        final Class monitoredVmUtilClass = Class.forName("sun.jvmstat.monitor.MonitoredVmUtil", true, loader);
                        final Method commandLineMethod = monitoredVmUtilClass.getMethod("commandLine", Class.forName("sun.jvmstat.monitor.MonitoredVm", true, loader));
                        final String name = (String)commandLineMethod.invoke(null, mvm);
                        if (name.contains("run.jar start")) {
                            connectingPid = pid;
                            jmxUrl = ConnectorAddressLink.importFrom(pid);
                            break;
                        }
                    }
                }
                catch (Exception ex) {}
            }
            if (connectingPid != -1) {
                this.context.print("Connecting to pid: " + connectingPid);
            }
            else {
                this.context.print("Connecting to JMX URL: " + jmxUrl);
            }
            this.setJmxServiceUrl(jmxUrl);
        }
        return this.getJmxServiceUrl();
    }
    
    protected void setJmxServiceUrl(final JMXServiceURL jmxServiceUrl) {
        this.jmxServiceUrl = jmxServiceUrl;
    }
    
    protected void setJmxServiceUrl(final String jmxServiceUrl) throws MalformedURLException {
        this.setJmxServiceUrl(new JMXServiceURL(jmxServiceUrl));
    }
    
    public String getJmxUser() {
        return this.jmxUser;
    }
    
    public void setJmxUser(final String jmxUser) {
        this.jmxUser = jmxUser;
    }
    
    public String getJmxPassword() {
        return this.jmxPassword;
    }
    
    public void setJmxPassword(final String jmxPassword) {
        this.jmxPassword = jmxPassword;
    }
    
    public boolean isJmxUseLocal() {
        return this.jmxUseLocal;
    }
    
    public void setJmxUseLocal(final boolean jmxUseLocal) {
        this.jmxUseLocal = jmxUseLocal;
    }
    
    private JMXConnector createJmxConnector() throws IOException {
        if (this.jmxConnector != null) {
            this.jmxConnector.connect();
            return this.jmxConnector;
        }
        if (this.jmxUser != null && this.jmxPassword != null) {
            final Map<String, Object> props = new HashMap<String, Object>();
            props.put("jmx.remote.credentials", new String[] { this.jmxUser, this.jmxPassword });
            this.jmxConnector = JMXConnectorFactory.connect(this.useJmxServiceUrl(), props);
        }
        else {
            this.jmxConnector = JMXConnectorFactory.connect(this.useJmxServiceUrl());
        }
        return this.jmxConnector;
    }
    
    protected void closeJmxConnection() {
        try {
            if (this.jmxConnector != null) {
                this.jmxConnector.close();
                this.jmxConnector = null;
            }
        }
        catch (IOException ex) {}
    }
    
    protected MBeanServerConnection createJmxConnection() throws IOException {
        if (this.jmxConnection == null) {
            if (this.isJmxUseLocal()) {
                this.jmxConnection = ManagementFactory.getPlatformMBeanServer();
            }
            else {
                this.jmxConnection = this.createJmxConnector().getMBeanServerConnection();
            }
        }
        return this.jmxConnection;
    }
    
    @Override
    protected void handleOption(final String token, final List<String> tokens) throws Exception {
        if (token.equals("--jmxurl")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("JMX URL not specified."));
            }
            if (this.getJmxServiceUrl() != null) {
                this.context.printException(new IllegalArgumentException("Multiple JMX URL cannot be specified."));
                tokens.clear();
            }
            final String strJmxUrl = tokens.remove(0);
            try {
                this.setJmxServiceUrl(new JMXServiceURL(strJmxUrl));
            }
            catch (MalformedURLException e) {
                this.context.printException(e);
                tokens.clear();
            }
        }
        else if (token.equals("--pid")) {
            if (isSunJVM()) {
                if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                    this.context.printException(new IllegalArgumentException("pid not specified"));
                    return;
                }
                final int pid = Integer.parseInt(tokens.remove(0));
                this.context.print("Connecting to pid: " + pid);
                final String jmxUrl = ConnectorAddressLink.importFrom(pid);
                if (this.getJmxServiceUrl() != null) {
                    this.context.printException(new IllegalArgumentException("JMX URL already specified."));
                    tokens.clear();
                }
                try {
                    this.setJmxServiceUrl(new JMXServiceURL(jmxUrl));
                }
                catch (MalformedURLException e2) {
                    this.context.printException(e2);
                    tokens.clear();
                }
            }
            else {
                this.context.printInfo("--pid option is not available for this VM, using default JMX url");
            }
        }
        else if (token.equals("--jmxuser")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("JMX user not specified."));
            }
            this.setJmxUser(tokens.remove(0));
        }
        else if (token.equals("--jmxpassword")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("JMX password not specified."));
            }
            this.setJmxPassword(tokens.remove(0));
        }
        else if (token.equals("--jmxlocal")) {
            this.setJmxUseLocal(true);
        }
        else {
            super.handleOption(token, tokens);
        }
    }
    
    @Override
    public void execute(final List<String> tokens) throws Exception {
        try {
            super.execute(tokens);
        }
        finally {
            this.closeJmxConnection();
        }
    }
}
