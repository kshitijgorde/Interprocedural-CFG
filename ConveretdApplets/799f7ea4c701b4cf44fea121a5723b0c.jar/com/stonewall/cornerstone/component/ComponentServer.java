// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import java.util.Hashtable;
import com.stonewall.cornerstone.jms.Timing;
import java.util.Properties;
import java.util.Collections;
import com.stonewall.cornerstone.utility.Encrypted;
import java.io.IOException;
import java.net.InetAddress;
import com.stonewall.cornerstone.feature.FeatureLoader;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.cornerstone.jms.JmsProvider;
import com.stonewall.cornerstone.jms.ProviderFactory;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.feature.IFeature;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ComponentServer implements ComponentServerMBean
{
    private ReentrantLock lock;
    private static ComponentServer inst;
    private final Map<String, Component> components;
    private Map<Class, IFeature> features;
    public static final String CornerstoneServerId = "cornerstone.server.id";
    public static final String CornerstoneServerName = "cornerstone.server.name";
    public static final String CornerstoneServerType = "cornerstone.server.type";
    public static final String CornerstoneServerSn = "cornerstone.server.sn";
    public static final String CornerstoneServerRMIQueue = "cornerstone.server.rmiQueue";
    static final Log log;
    
    static {
        ComponentServer.inst = null;
        log = Log.getLog(ComponentServer.class);
    }
    
    public ComponentServer() {
        this.lock = new ReentrantLock();
        this.components = new LinkedHashMap<String, Component>();
        this.features = new HashMap<Class, IFeature>();
        ComponentServer.inst = this;
    }
    
    public void init() throws Exception {
        final Bootstrap bootstrap = this.getBootstrap();
        bootstrap.init();
        this.setSerialNumber(bootstrap);
        this.setId(bootstrap);
        this.setName(bootstrap);
        bootstrap.store();
        ComponentServer.log.info(ProductDescriptor.getInstance());
        this.logSystemProfile();
        this.logProperties();
        this.jmsInit();
        this.loadFeatures();
        this.createComponents();
        this.initFeatures();
        this.initComponents();
    }
    
    @Override
    public void shutdown() {
        this.shutdownComponents();
        this.shutdownFeatures();
        final JmsProvider jms = ProviderFactory.getJmsProvider();
        jms.close();
    }
    
    @Override
    public void trace(final String component) {
        this.lock.lock();
        try {
            final Component c = this.getComponent(component);
            if (c != null) {
                c.trace();
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void setLog(String name, final String level) {
        if (name.charAt(0) == '~') {
            name = "com.stonewall.cornerstone" + name.substring(1);
        }
        final Log log = Log.getLog(name);
        if (log != null) {
            log.setLevel(Log.getLevelIndex(level));
            log.info("logger: [" + name + "] now logging at: " + level);
        }
    }
    
    @Override
    public void halt() {
        this.halt(0);
    }
    
    public void halt(final int code) {
        this.shutdown();
        System.exit(code);
    }
    
    @Override
    public void restart() {
        try {
            this.shutdown();
            this.sleep(500L);
            this.init();
        }
        catch (Exception e) {
            ComponentServer.log.fatal("Restart failed", e);
            this.halt();
        }
    }
    
    public String getId() {
        return System.getProperty("cornerstone.server.id");
    }
    
    public void setId(final String id) {
        System.setProperty("cornerstone.server.id", id);
    }
    
    public void setName(final String name) {
        System.setProperty("cornerstone.server.name", name);
    }
    
    public String getName() {
        return System.getProperty("cornerstone.server.name");
    }
    
    public Type getType() {
        final String type = System.getProperty("cornerstone.server.type", "invalid");
        return Type.valueOf(type);
    }
    
    public String getSerialNumber() {
        return System.getProperty("cornerstone.server.sn", "");
    }
    
    public void addComponent(final Component c) {
        this.lock.lock();
        try {
            final String name = c.getClass().getName();
            this.components.put(name, c);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public Component getComponent(final Class c) {
        this.lock.lock();
        try {
            return this.components.get(c.getName());
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public Component getComponent(String name) {
        this.lock.lock();
        try {
            if (name.charAt(0) == '~') {
                name = "com.stonewall.cornerstone" + name.substring(1);
            }
            else if (name.charAt(0) == '*') {
                for (final String n : this.components.keySet()) {
                    final String[] tokens = n.split("[.]");
                    final int last = tokens.length - 1;
                    final String simpleName = name.substring(2, name.length());
                    if (simpleName.equals(tokens[last])) {
                        return this.components.get(n);
                    }
                }
            }
            return this.components.get(name);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public static ComponentServer getInstance() {
        return ComponentServer.inst;
    }
    
    protected abstract void createComponents() throws Exception;
    
    protected void initComponents() throws Exception {
        final List<Component> list = new ArrayList<Component>();
        this.lock.lock();
        try {
            list.addAll(this.components.values());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final Component c : list) {
            ComponentServer.log.info("Starting: " + this.abbreviatedName(c));
            c.init(this);
        }
    }
    
    protected void shutdownComponents() {
        final List<Component> list = new ArrayList<Component>();
        this.lock.lock();
        try {
            list.addAll(this.components.values());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final Component c : list) {
            c.shutdown();
        }
    }
    
    protected void loadFeatures() {
        final FeatureLoader featureLoader = new FeatureLoader();
        this.features = featureLoader.load();
    }
    
    public IFeature getFeature(final Class c) {
        this.lock.lock();
        try {
            return this.features.get(c);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public List<IFeature> getFeatures() {
        this.lock.lock();
        try {
            final List<IFeature> l = new ArrayList<IFeature>();
            l.addAll(this.features.values());
            return l;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    protected void initFeatures() throws Exception {
        final List<IFeature> list = new ArrayList<IFeature>();
        this.lock.lock();
        try {
            list.addAll(this.features.values());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final IFeature f : list) {
            ComponentServer.log.info("Starting: " + f.getClass().getName());
            f.init(this);
        }
    }
    
    protected void shutdownFeatures() {
        final List<IFeature> list = new ArrayList<IFeature>();
        this.lock.lock();
        try {
            list.addAll(this.features.values());
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final IFeature f : list) {
            f.shutdown();
        }
    }
    
    protected Bootstrap getBootstrap() {
        return new Bootstrap();
    }
    
    protected void sleep(final long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (Exception e) {
            ComponentServer.log.error(ms, e);
        }
    }
    
    private void setId(final Bootstrap bootstrap) throws Exception {
        final String id = this.hostname();
        this.setId(id);
        bootstrap.addProperty("cornerstone.server.id", id);
    }
    
    private void setName(final Bootstrap bootstrap) throws Exception {
        final String name = this.hostname();
        this.setName(name);
        bootstrap.addProperty("cornerstone.server.name", name);
    }
    
    private String hostname() throws IOException {
        final String hostname = InetAddress.getLocalHost().getHostName();
        return hostname.split("\\.")[0];
    }
    
    private void setSerialNumber(final Bootstrap bootstrap) throws Exception {
        final String p = System.getProperty("cornerstone.server.sn");
        if (p == null || p.length() == 0) {
            final SerialNumber sn = new SerialNumber(this.getType().name());
            sn.read();
            bootstrap.addProperty("cornerstone.server.sn", sn.toString());
        }
    }
    
    private void logProperties() {
        final StringBuilder sb = new StringBuilder("\nProperties:\n");
        for (final String name : this.getPropertyNames()) {
            if (!name.startsWith("cornerstone")) {
                continue;
            }
            sb.append(name);
            final boolean encrypted = Boolean.parseBoolean(System.getProperty(String.valueOf(name) + ".encrypted", "false"));
            String value = System.getProperty(name);
            if (encrypted) {
                final Encrypted en = new Encrypted(value);
                value = en.encrypt();
                sb.append('*');
            }
            sb.append(" = (");
            sb.append(value);
            sb.append(")\n");
        }
        ComponentServer.log.info(sb);
    }
    
    private void logSystemProfile() {
        final Runtime jvm = Runtime.getRuntime();
        ComponentServer.log.info("Available Processors: " + jvm.availableProcessors());
        ComponentServer.log.info("JVM min heap: " + jvm.totalMemory());
        ComponentServer.log.info("JVM max heap: " + jvm.maxMemory());
    }
    
    private List<String> getPropertyNames() {
        final List<String> keys = new ArrayList<String>();
        final Properties p = System.getProperties();
        for (final Object o : ((Hashtable<Object, V>)p).keySet()) {
            keys.add(o.toString());
        }
        Collections.sort(keys);
        return keys;
    }
    
    private String abbreviatedName(final Component c) {
        final String result = c.getClass().getName();
        return result.replaceAll("com\\.stonewall\\.cornerstone\\.", "~");
    }
    
    private void jmsInit() throws Exception {
        final JmsProvider jms = ProviderFactory.getJmsProvider();
        int i = 0;
        while (true) {
            try {
                jms.getConnection();
            }
            catch (Exception e) {
                ComponentServer.log.error("Connection attempt failed: " + i, e);
                this.sleep(5000L);
                ++i;
                continue;
            }
            break;
        }
        Timing.getInstance().init();
    }
    
    public enum Type
    {
        ps("ps", 0), 
        gui("gui", 1), 
        dm("dm", 2), 
        cp("cp", 3), 
        invalid("invalid", 4);
        
        private Type(final String s, final int n) {
        }
    }
}
