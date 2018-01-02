// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.HashSet;
import java.net.DatagramPacket;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Random;
import java.util.Timer;
import java.util.Map;
import java.util.List;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class JmDNS
{
    private static Logger logger;
    public static String VERSION;
    private InetAddress group;
    private MulticastSocket socket;
    protected boolean closed;
    private List listeners;
    private Map serviceListeners;
    private List typeListeners;
    private DNSCache cache;
    Map services;
    Map serviceTypes;
    private Thread shutdown;
    HostInfo localHost;
    private Thread incomingListener;
    private int throttle;
    private long lastThrottleIncrement;
    private Timer timer;
    private static final Random random;
    private Object ioLock;
    private DNSIncoming plannedAnswer;
    private DNSState state;
    TimerTask task;
    private HashMap serviceCollectors;
    
    public JmDNS() throws IOException {
        this.closed = false;
        this.incomingListener = null;
        this.ioLock = new Object();
        this.state = DNSState.PROBING_1;
        this.serviceCollectors = new HashMap();
        JmDNS.logger.finer("JmDNS instance created");
        try {
            final InetAddress addr = InetAddress.getLocalHost();
            this.init(addr.isLoopbackAddress() ? null : addr, addr.getHostName());
        }
        catch (IOException e) {
            this.init(null, "computer");
        }
    }
    
    public JmDNS(final InetAddress addr) throws IOException {
        this.closed = false;
        this.incomingListener = null;
        this.ioLock = new Object();
        this.state = DNSState.PROBING_1;
        this.serviceCollectors = new HashMap();
        try {
            this.init(addr, addr.getHostName());
        }
        catch (IOException e) {
            this.init(null, "computer");
        }
    }
    
    private void init(final InetAddress address, String name) throws IOException {
        final int idx = name.indexOf(".");
        if (idx > 0) {
            name = name.substring(0, idx);
        }
        name += ".local.";
        this.localHost = new HostInfo(address, name);
        this.cache = new DNSCache(100);
        this.listeners = Collections.synchronizedList(new ArrayList<Object>());
        this.serviceListeners = new HashMap();
        this.typeListeners = new ArrayList();
        this.services = new Hashtable(20);
        this.serviceTypes = new Hashtable(20);
        this.timer = new Timer("JmDNS.Timer");
        new RecordReaper().start();
        this.shutdown = new Thread(new Shutdown(), "JmDNS.Shutdown");
        Runtime.getRuntime().addShutdownHook(this.shutdown);
        this.incomingListener = new Thread(new SocketListener(), "JmDNS.SocketListener");
        this.openMulticastSocket(this.localHost);
        this.start(this.services.values());
    }
    
    private void start(final Collection serviceInfos) {
        this.state = DNSState.PROBING_1;
        this.incomingListener.start();
        new Prober().start();
        final Iterator iterator = serviceInfos.iterator();
        while (iterator.hasNext()) {
            try {
                this.registerService(new ServiceInfo(iterator.next()));
            }
            catch (Exception exception) {
                JmDNS.logger.log(Level.WARNING, "start() Registration exception ", exception);
            }
        }
    }
    
    private void openMulticastSocket(final HostInfo hostInfo) throws IOException {
        if (this.group == null) {
            this.group = InetAddress.getByName("224.0.0.251");
        }
        if (this.socket != null) {
            this.closeMulticastSocket();
        }
        this.socket = new MulticastSocket(5353);
        if (hostInfo != null && this.localHost.getInterface() != null) {
            this.socket.setNetworkInterface(hostInfo.getInterface());
        }
        this.socket.setTimeToLive(255);
        this.socket.joinGroup(this.group);
    }
    
    private void closeMulticastSocket() {
        JmDNS.logger.finer("closeMulticastSocket()");
        if (this.socket != null) {
            try {
                this.socket.leaveGroup(this.group);
                this.socket.close();
                if (this.incomingListener != null) {
                    this.incomingListener.join();
                }
            }
            catch (Exception exception) {
                JmDNS.logger.log(Level.WARNING, "closeMulticastSocket() Close socket exception ", exception);
            }
            this.socket = null;
        }
    }
    
    synchronized void advanceState() {
        this.state = this.state.advance();
        this.notifyAll();
    }
    
    synchronized void revertState() {
        this.state = this.state.revert();
        this.notifyAll();
    }
    
    synchronized void cancel() {
        this.state = DNSState.CANCELED;
        this.notifyAll();
    }
    
    DNSState getState() {
        return this.state;
    }
    
    DNSCache getCache() {
        return this.cache;
    }
    
    public String getHostName() {
        return this.localHost.getName();
    }
    
    public HostInfo getLocalHost() {
        return this.localHost;
    }
    
    public InetAddress getInterface() throws IOException {
        return this.socket.getInterface();
    }
    
    public ServiceInfo getServiceInfo(final String type, final String name) {
        return this.getServiceInfo(type, name, 3000);
    }
    
    public ServiceInfo getServiceInfo(final String type, final String name, final int timeout) {
        final ServiceInfo info = new ServiceInfo(type, name);
        new ServiceInfoResolver(info).start();
        try {
            final long end = System.currentTimeMillis() + timeout;
            synchronized (info) {
                long delay;
                while (!info.hasData() && (delay = end - System.currentTimeMillis()) > 0L) {
                    info.wait(delay);
                }
            }
        }
        catch (InterruptedException ex) {}
        return info.hasData() ? info : null;
    }
    
    public void requestServiceInfo(final String type, final String name) {
        this.requestServiceInfo(type, name, 3000);
    }
    
    public void requestServiceInfo(final String type, final String name, final int timeout) {
        this.registerServiceType(type);
        final ServiceInfo info = new ServiceInfo(type, name);
        new ServiceInfoResolver(info).start();
        try {
            final long end = System.currentTimeMillis() + timeout;
            synchronized (info) {
                long delay;
                while (!info.hasData() && (delay = end - System.currentTimeMillis()) > 0L) {
                    info.wait(delay);
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    void handleServiceResolved(final ServiceInfo info) {
        final List list = this.serviceListeners.get(info.type.toLowerCase());
        if (list != null) {
            final ServiceEvent event = new ServiceEvent(this, info.type, info.getName(), info);
            final ArrayList listCopy = new ArrayList(list);
            final Iterator iterator = listCopy.iterator();
            while (iterator.hasNext()) {
                iterator.next().serviceResolved(event);
            }
        }
    }
    
    public void addServiceTypeListener(final ServiceTypeListener listener) throws IOException {
        synchronized (this) {
            this.typeListeners.remove(listener);
            this.typeListeners.add(listener);
        }
        final Iterator iterator = this.serviceTypes.values().iterator();
        while (iterator.hasNext()) {
            listener.serviceTypeAdded(new ServiceEvent(this, iterator.next(), null, null));
        }
        new TypeResolver().start();
    }
    
    public void removeServiceTypeListener(final ServiceTypeListener listener) {
        synchronized (this) {
            this.typeListeners.remove(listener);
        }
    }
    
    public void addServiceListener(final String type, final ServiceListener listener) {
        final String lotype = type.toLowerCase();
        this.removeServiceListener(lotype, listener);
        List list = null;
        synchronized (this) {
            list = this.serviceListeners.get(lotype);
            if (list == null) {
                list = Collections.synchronizedList(new LinkedList<Object>());
                this.serviceListeners.put(lotype, list);
            }
            list.add(listener);
        }
        for (DNSCache.CacheNode n : this.cache) {
            while (n != null) {
                final DNSRecord rec = (DNSRecord)n.getValue();
                if (rec.type == 33 && rec.name.endsWith(type)) {
                    listener.serviceAdded(new ServiceEvent(this, type, toUnqualifiedName(type, rec.name), null));
                }
                n = n.next();
            }
        }
        new ServiceResolver(type).start();
    }
    
    public void removeServiceListener(String type, final ServiceListener listener) {
        type = type.toLowerCase();
        final List list = this.serviceListeners.get(type);
        if (list != null) {
            synchronized (this) {
                list.remove(listener);
                if (list.size() == 0) {
                    this.serviceListeners.remove(type);
                }
            }
        }
    }
    
    public void registerService(final ServiceInfo info) throws IOException {
        this.registerServiceType(info.type);
        info.server = this.localHost.getName();
        info.addr = this.localHost.getAddress();
        synchronized (this) {
            this.makeServiceNameUnique(info);
            this.services.put(info.getQualifiedName().toLowerCase(), info);
        }
        new Prober().start();
        try {
            synchronized (info) {
                while (info.getState().compareTo(DNSState.ANNOUNCED) < 0) {
                    info.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
        JmDNS.logger.fine("registerService() JmDNS registered service as " + info);
    }
    
    public void unregisterService(final ServiceInfo info) {
        synchronized (this) {
            this.services.remove(info.getQualifiedName().toLowerCase());
        }
        info.cancel();
        final Object lock = new Object();
        new Canceler(info, lock).start();
        try {
            synchronized (lock) {
                lock.wait();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void unregisterAllServices() {
        JmDNS.logger.finer("unregisterAllServices()");
        if (this.services.size() == 0) {
            return;
        }
        final Collection list;
        synchronized (this) {
            list = new LinkedList(this.services.values());
            this.services.clear();
        }
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel();
        }
        final Object lock = new Object();
        new Canceler(list, lock).start();
        try {
            synchronized (lock) {
                if (!this.closed) {
                    lock.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void registerServiceType(final String type) {
        final String name = type.toLowerCase();
        if (this.serviceTypes.get(name) == null && type.indexOf("._mdns._udp.") < 0 && !type.endsWith(".in-addr.arpa.")) {
            final Collection list;
            synchronized (this) {
                this.serviceTypes.put(name, type);
                list = new LinkedList(this.typeListeners);
            }
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().serviceTypeAdded(new ServiceEvent(this, type, null, null));
            }
        }
    }
    
    private boolean makeHostNameUnique(final DNSRecord.Address host) {
        final String originalName = host.getName();
        final long now = System.currentTimeMillis();
        boolean collision;
        do {
            collision = false;
            for (DNSCache.CacheNode j = this.cache.find(host.getName().toLowerCase()); j != null; j = j.next()) {
                final DNSRecord a = (DNSRecord)j.getValue();
            }
        } while (collision);
        return !originalName.equals(host.getName());
    }
    
    private boolean makeServiceNameUnique(final ServiceInfo info) {
        final String originalQualifiedName = info.getQualifiedName();
        final long now = System.currentTimeMillis();
        boolean collision;
        do {
            collision = false;
            for (DNSCache.CacheNode j = this.cache.find(info.getQualifiedName().toLowerCase()); j != null; j = j.next()) {
                final DNSRecord a = (DNSRecord)j.getValue();
                if (a.type == 33 && !a.isExpired(now)) {
                    final DNSRecord.Service s = (DNSRecord.Service)a;
                    if (s.port != info.port || !s.server.equals(this.localHost.getName())) {
                        JmDNS.logger.finer("makeServiceNameUnique() JmDNS.makeServiceNameUnique srv collision:" + a + " s.server=" + s.server + " " + this.localHost.getName() + " equals:" + s.server.equals(this.localHost.getName()));
                        info.setName(this.incrementName(info.getName()));
                        collision = true;
                        break;
                    }
                }
            }
            final Object selfService = this.services.get(info.getQualifiedName().toLowerCase());
            if (selfService != null && selfService != info) {
                info.setName(this.incrementName(info.getName()));
                collision = true;
            }
        } while (collision);
        return !originalQualifiedName.equals(info.getQualifiedName());
    }
    
    String incrementName(String name) {
        try {
            final int l = name.lastIndexOf(40);
            final int r = name.lastIndexOf(41);
            if (l >= 0 && l < r) {
                name = name.substring(0, l) + "(" + (Integer.parseInt(name.substring(l + 1, r)) + 1) + ")";
            }
            else {
                name += " (2)";
            }
        }
        catch (NumberFormatException e) {
            name += " (2)";
        }
        return name;
    }
    
    void addListener(final DNSListener listener, final DNSQuestion question) {
        final long now = System.currentTimeMillis();
        synchronized (this) {
            this.listeners.add(listener);
        }
        if (question != null) {
            for (DNSCache.CacheNode i = this.cache.find(question.name); i != null; i = i.next()) {
                final DNSRecord c = (DNSRecord)i.getValue();
                if (question.answeredBy(c) && !c.isExpired(now)) {
                    listener.updateRecord(this, now, c);
                }
            }
        }
    }
    
    void removeListener(final DNSListener listener) {
        synchronized (this) {
            this.listeners.remove(listener);
        }
    }
    
    void updateRecord(final long now, final DNSRecord rec) {
        List listenerList = null;
        synchronized (this) {
            listenerList = new ArrayList(this.listeners);
        }
        for (final DNSListener listener : listenerList) {
            listener.updateRecord(this, now, rec);
        }
        if (rec.type == 12 || rec.type == 33) {
            List serviceListenerList = null;
            synchronized (this) {
                serviceListenerList = this.serviceListeners.get(rec.name.toLowerCase());
                if (serviceListenerList != null) {
                    serviceListenerList = new ArrayList(serviceListenerList);
                }
            }
            if (serviceListenerList != null) {
                final boolean expired = rec.isExpired(now);
                final String type = rec.getName();
                final String name = ((DNSRecord.Pointer)rec).getAlias();
                if (!expired) {
                    final ServiceEvent event = new ServiceEvent(this, type, toUnqualifiedName(type, name), null);
                    final Iterator iterator2 = serviceListenerList.iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().serviceAdded(event);
                    }
                }
                else {
                    final ServiceEvent event = new ServiceEvent(this, type, toUnqualifiedName(type, name), null);
                    final Iterator iterator2 = serviceListenerList.iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().serviceRemoved(event);
                    }
                }
            }
        }
    }
    
    private void handleResponse(final DNSIncoming msg) throws IOException {
        final long now = System.currentTimeMillis();
        boolean hostConflictDetected = false;
        boolean serviceConflictDetected = false;
        final Iterator i = msg.answers.iterator();
        while (i.hasNext()) {
            boolean isInformative = false;
            DNSRecord rec = i.next();
            final boolean expired = rec.isExpired(now);
            final DNSRecord c = (DNSRecord)this.cache.get(rec);
            if (c != null) {
                if (expired) {
                    isInformative = true;
                    this.cache.remove(c);
                }
                else {
                    c.resetTTL(rec);
                    rec = c;
                }
            }
            else if (!expired) {
                isInformative = true;
                this.cache.add(rec);
            }
            switch (rec.type) {
                case 12: {
                    if (rec.getName().indexOf("._mdns._udp.") < 0) {
                        this.registerServiceType(rec.name);
                        break;
                    }
                    if (!expired && rec.name.startsWith("_services._mdns._udp.")) {
                        isInformative = true;
                        this.registerServiceType(((DNSRecord.Pointer)rec).alias);
                        continue;
                    }
                    continue;
                }
            }
            if (rec.getType() == 1 || rec.getType() == 28) {
                hostConflictDetected |= rec.handleResponse(this);
            }
            else {
                serviceConflictDetected |= rec.handleResponse(this);
            }
            if (isInformative) {
                this.updateRecord(now, rec);
            }
        }
        if (hostConflictDetected || serviceConflictDetected) {
            new Prober().start();
        }
    }
    
    private void handleQuery(final DNSIncoming in, final InetAddress addr, final int port) throws IOException {
        boolean hostConflictDetected = false;
        boolean serviceConflictDetected = false;
        final long expirationTime = System.currentTimeMillis() + 120L;
        for (final DNSRecord answer : in.answers) {
            if (answer.getType() == 1 || answer.getType() == 28) {
                hostConflictDetected |= answer.handleQuery(this, expirationTime);
            }
            else {
                serviceConflictDetected |= answer.handleQuery(this, expirationTime);
            }
        }
        if (this.plannedAnswer != null) {
            this.plannedAnswer.append(in);
        }
        else {
            if (in.isTruncated()) {
                this.plannedAnswer = in;
            }
            new Responder(in, addr, port).start();
        }
        if (hostConflictDetected || serviceConflictDetected) {
            new Prober().start();
        }
    }
    
    DNSOutgoing addAnswer(final DNSIncoming in, final InetAddress addr, final int port, DNSOutgoing out, final DNSRecord rec) throws IOException {
        if (out == null) {
            out = new DNSOutgoing(33792);
        }
        try {
            out.addAnswer(in, rec);
        }
        catch (IOException e) {
            final DNSOutgoing dnsOutgoing = out;
            dnsOutgoing.flags |= 0x200;
            out.id = in.id;
            out.finish();
            this.send(out);
            out = new DNSOutgoing(33792);
            out.addAnswer(in, rec);
        }
        return out;
    }
    
    private void send(final DNSOutgoing out) throws IOException {
        out.finish();
        if (!out.isEmpty()) {
            final DatagramPacket packet = new DatagramPacket(out.data, out.off, this.group, 5353);
            try {
                final DNSIncoming msg = new DNSIncoming(packet);
                JmDNS.logger.finest("send() JmDNS out:" + msg.print(true));
            }
            catch (IOException e) {
                JmDNS.logger.throwing(this.getClass().toString(), "send(DNSOutgoing) - JmDNS can not parse what it sends!!!", e);
            }
            this.socket.send(packet);
        }
    }
    
    protected void recover() {
        JmDNS.logger.finer("recover()");
        if (DNSState.CANCELED != this.state) {
            synchronized (this) {
                JmDNS.logger.finer("recover() Cleanning up");
                this.state = DNSState.CANCELED;
                final Collection oldServiceInfos = new ArrayList(this.services.values());
                this.unregisterAllServices();
                this.disposeServiceCollectors();
                this.closeMulticastSocket();
                this.cache.clear();
                JmDNS.logger.finer("recover() All is clean");
                try {
                    this.openMulticastSocket(this.localHost);
                    this.start(oldServiceInfos);
                }
                catch (Exception exception) {
                    JmDNS.logger.log(Level.WARNING, "recover() Start services exception ", exception);
                }
                JmDNS.logger.log(Level.WARNING, "recover() We are back!");
            }
        }
    }
    
    public void close() {
        if (this.state != DNSState.CANCELED) {
            synchronized (this) {
                this.state = DNSState.CANCELED;
                this.unregisterAllServices();
                this.disposeServiceCollectors();
                this.closeMulticastSocket();
                this.timer.cancel();
                if (this.shutdown != null) {
                    Runtime.getRuntime().removeShutdownHook(this.shutdown);
                }
            }
        }
    }
    
    void print() {
        System.out.println("---- cache ----");
        this.cache.print();
        System.out.println();
    }
    
    public void printServices() {
        System.err.println(this.toString());
    }
    
    @Override
    public String toString() {
        final StringBuffer aLog = new StringBuffer();
        aLog.append("\t---- Services -----");
        if (this.services != null) {
            for (final Object key : this.services.keySet()) {
                aLog.append("\n\t\tService: " + key + ": " + this.services.get(key));
            }
        }
        aLog.append("\n");
        aLog.append("\t---- Types ----");
        if (this.serviceTypes != null) {
            for (final Object key : this.serviceTypes.keySet()) {
                aLog.append("\n\t\tType: " + key + ": " + this.serviceTypes.get(key));
            }
        }
        aLog.append("\n");
        aLog.append(this.cache.toString());
        aLog.append("\n");
        aLog.append("\t---- Service Collectors ----");
        if (this.serviceCollectors != null) {
            synchronized (this.serviceCollectors) {
                for (final Object key2 : this.serviceCollectors.keySet()) {
                    aLog.append("\n\t\tService Collector: " + key2 + ": " + this.serviceCollectors.get(key2));
                }
                this.serviceCollectors.clear();
            }
        }
        return aLog.toString();
    }
    
    public ServiceInfo[] list(final String type) {
        ServiceCollector collector;
        boolean newCollectorCreated;
        synchronized (this.serviceCollectors) {
            collector = this.serviceCollectors.get(type);
            if (collector == null) {
                collector = new ServiceCollector(type);
                this.serviceCollectors.put(type, collector);
                this.addServiceListener(type, collector);
                newCollectorCreated = true;
            }
            else {
                newCollectorCreated = false;
            }
        }
        if (newCollectorCreated) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
        }
        return collector.list();
    }
    
    private void disposeServiceCollectors() {
        JmDNS.logger.finer("disposeServiceCollectors()");
        synchronized (this.serviceCollectors) {
            for (final ServiceCollector collector : this.serviceCollectors.values()) {
                this.removeServiceListener(collector.type, collector);
            }
            this.serviceCollectors.clear();
        }
    }
    
    private static String toUnqualifiedName(final String type, final String qualifiedName) {
        if (qualifiedName.endsWith(type)) {
            return qualifiedName.substring(0, qualifiedName.length() - type.length() - 1);
        }
        return qualifiedName;
    }
    
    static {
        JmDNS.logger = Logger.getLogger(JmDNS.class.toString());
        JmDNS.VERSION = "2.0";
        random = new Random();
    }
    
    class SocketListener implements Runnable
    {
        @Override
        public void run() {
            try {
                final byte[] buf = new byte[8972];
                final DatagramPacket packet = new DatagramPacket(buf, buf.length);
                while (JmDNS.this.state != DNSState.CANCELED) {
                    packet.setLength(buf.length);
                    JmDNS.this.socket.receive(packet);
                    if (JmDNS.this.state == DNSState.CANCELED) {
                        break;
                    }
                    try {
                        if (JmDNS.this.localHost.shouldIgnorePacket(packet)) {
                            continue;
                        }
                        final DNSIncoming msg = new DNSIncoming(packet);
                        JmDNS.logger.finest("SocketListener.run() JmDNS in:" + msg.print(true));
                        synchronized (JmDNS.this.ioLock) {
                            if (msg.isQuery()) {
                                if (packet.getPort() != 5353) {
                                    JmDNS.this.handleQuery(msg, packet.getAddress(), packet.getPort());
                                }
                                JmDNS.this.handleQuery(msg, JmDNS.this.group, 5353);
                            }
                            else {
                                JmDNS.this.handleResponse(msg);
                            }
                        }
                    }
                    catch (IOException e) {
                        JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                    }
                }
            }
            catch (IOException e2) {
                if (JmDNS.this.state != DNSState.CANCELED) {
                    JmDNS.logger.log(Level.WARNING, "run() exception ", e2);
                    JmDNS.this.recover();
                }
            }
        }
    }
    
    private class RecordReaper extends TimerTask
    {
        public void start() {
            JmDNS.this.timer.schedule(this, 10000L, 10000L);
        }
        
        @Override
        public void run() {
            synchronized (JmDNS.this) {
                if (JmDNS.this.state == DNSState.CANCELED) {
                    return;
                }
                JmDNS.logger.finest("run() JmDNS reaping cache");
                final List list = new ArrayList();
                synchronized (JmDNS.this.cache) {
                    for (DNSCache.CacheNode n : JmDNS.this.cache) {
                        while (n != null) {
                            list.add(n.getValue());
                            n = n.next();
                        }
                    }
                }
                final long now = System.currentTimeMillis();
                for (final DNSRecord c : list) {
                    if (c.isExpired(now)) {
                        JmDNS.this.updateRecord(now, c);
                        JmDNS.this.cache.remove(c);
                    }
                }
            }
        }
    }
    
    private class Prober extends TimerTask
    {
        DNSState taskState;
        
        public Prober() {
            this.taskState = DNSState.PROBING_1;
            if (JmDNS.this.state == DNSState.PROBING_1) {
                JmDNS.this.task = this;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.getState() == DNSState.PROBING_1) {
                        info.task = this;
                    }
                }
            }
        }
        
        public void start() {
            final long now = System.currentTimeMillis();
            if (now - JmDNS.this.lastThrottleIncrement < 5000L) {
                JmDNS.this.throttle++;
            }
            else {
                JmDNS.this.throttle = 1;
            }
            JmDNS.this.lastThrottleIncrement = now;
            if (JmDNS.this.state == DNSState.ANNOUNCED && JmDNS.this.throttle < 10) {
                JmDNS.this.timer.schedule(this, JmDNS.random.nextInt(251), 250L);
            }
            else {
                JmDNS.this.timer.schedule(this, 1000L, 1000L);
            }
        }
        
        @Override
        public boolean cancel() {
            if (JmDNS.this.task == this) {
                JmDNS.this.task = null;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.task == this) {
                        info.task = null;
                    }
                }
            }
            return super.cancel();
        }
        
        @Override
        public void run() {
            synchronized (JmDNS.this.ioLock) {
                DNSOutgoing out = null;
                try {
                    if (JmDNS.this.state == this.taskState && JmDNS.this.task == this) {
                        if (out == null) {
                            out = new DNSOutgoing(0);
                        }
                        out.addQuestion(new DNSQuestion(JmDNS.this.localHost.getName(), 255, 1));
                        DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                        if (answer != null) {
                            out.addAuthorativeAnswer(answer);
                        }
                        answer = JmDNS.this.localHost.getDNS6AddressRecord();
                        if (answer != null) {
                            out.addAuthorativeAnswer(answer);
                        }
                        JmDNS.this.advanceState();
                    }
                    final List list;
                    synchronized (JmDNS.this) {
                        list = new LinkedList(JmDNS.this.services.values());
                    }
                    for (final ServiceInfo info : list) {
                        synchronized (info) {
                            if (info.getState() != this.taskState || info.task != this) {
                                continue;
                            }
                            info.advanceState();
                            JmDNS.logger.fine("run() JmDNS probing " + info.getQualifiedName() + " state " + info.getState());
                            if (out == null) {
                                out = new DNSOutgoing(0);
                                out.addQuestion(new DNSQuestion(info.getQualifiedName(), 255, 1));
                            }
                            out.addAuthorativeAnswer(new DNSRecord.Service(info.getQualifiedName(), 33, 1, 3600, info.priority, info.weight, info.port, JmDNS.this.localHost.getName()));
                        }
                    }
                    if (out == null) {
                        this.cancel();
                        return;
                    }
                    JmDNS.logger.finer("run() JmDNS probing #" + this.taskState);
                    JmDNS.this.send(out);
                }
                catch (Throwable e) {
                    JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                    JmDNS.this.recover();
                }
                this.taskState = this.taskState.advance();
                if (!this.taskState.isProbing()) {
                    this.cancel();
                    new Announcer().start();
                }
            }
        }
    }
    
    private class Announcer extends TimerTask
    {
        DNSState taskState;
        
        public Announcer() {
            this.taskState = DNSState.ANNOUNCING_1;
            if (JmDNS.this.state == DNSState.ANNOUNCING_1) {
                JmDNS.this.task = this;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.getState() == DNSState.ANNOUNCING_1) {
                        info.task = this;
                    }
                }
            }
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 1000L, 1000L);
        }
        
        @Override
        public boolean cancel() {
            if (JmDNS.this.task == this) {
                JmDNS.this.task = null;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.task == this) {
                        info.task = null;
                    }
                }
            }
            return super.cancel();
        }
        
        @Override
        public void run() {
            DNSOutgoing out = null;
            try {
                if (JmDNS.this.state == this.taskState) {
                    if (out == null) {
                        out = new DNSOutgoing(33792);
                    }
                    DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                    if (answer != null) {
                        out.addAnswer(answer, 0L);
                    }
                    answer = JmDNS.this.localHost.getDNS6AddressRecord();
                    if (answer != null) {
                        out.addAnswer(answer, 0L);
                    }
                    JmDNS.this.advanceState();
                }
                final List list;
                synchronized (JmDNS.this) {
                    list = new ArrayList(JmDNS.this.services.values());
                }
                for (final ServiceInfo info : list) {
                    synchronized (info) {
                        if (info.getState() != this.taskState || info.task != this) {
                            continue;
                        }
                        info.advanceState();
                        JmDNS.logger.finer("run() JmDNS announcing " + info.getQualifiedName() + " state " + info.getState());
                        if (out == null) {
                            out = new DNSOutgoing(33792);
                        }
                        out.addAnswer(new DNSRecord.Pointer(info.type, 12, 1, 3600, info.getQualifiedName()), 0L);
                        out.addAnswer(new DNSRecord.Service(info.getQualifiedName(), 33, 1, 3600, info.priority, info.weight, info.port, JmDNS.this.localHost.getName()), 0L);
                        out.addAnswer(new DNSRecord.Text(info.getQualifiedName(), 16, 1, 3600, info.text), 0L);
                    }
                }
                if (out != null) {
                    JmDNS.logger.finer("run() JmDNS announcing #" + this.taskState);
                    JmDNS.this.send(out);
                }
                else {
                    this.cancel();
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
            this.taskState = this.taskState.advance();
            if (!this.taskState.isAnnouncing()) {
                this.cancel();
                new Renewer().start();
            }
        }
    }
    
    private class Renewer extends TimerTask
    {
        DNSState taskState;
        
        public Renewer() {
            this.taskState = DNSState.ANNOUNCED;
            if (JmDNS.this.state == DNSState.ANNOUNCED) {
                JmDNS.this.task = this;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.getState() == DNSState.ANNOUNCED) {
                        info.task = this;
                    }
                }
            }
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 1800000L, 1800000L);
        }
        
        @Override
        public boolean cancel() {
            if (JmDNS.this.task == this) {
                JmDNS.this.task = null;
            }
            synchronized (JmDNS.this) {
                for (final ServiceInfo info : JmDNS.this.services.values()) {
                    if (info.task == this) {
                        info.task = null;
                    }
                }
            }
            return super.cancel();
        }
        
        @Override
        public void run() {
            DNSOutgoing out = null;
            try {
                if (JmDNS.this.state == this.taskState) {
                    if (out == null) {
                        out = new DNSOutgoing(33792);
                    }
                    DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                    if (answer != null) {
                        out.addAnswer(answer, 0L);
                    }
                    answer = JmDNS.this.localHost.getDNS6AddressRecord();
                    if (answer != null) {
                        out.addAnswer(answer, 0L);
                    }
                    JmDNS.this.advanceState();
                }
                final List list;
                synchronized (JmDNS.this) {
                    list = new ArrayList(JmDNS.this.services.values());
                }
                for (final ServiceInfo info : list) {
                    synchronized (info) {
                        if (info.getState() != this.taskState || info.task != this) {
                            continue;
                        }
                        info.advanceState();
                        JmDNS.logger.finer("run() JmDNS announced " + info.getQualifiedName() + " state " + info.getState());
                        if (out == null) {
                            out = new DNSOutgoing(33792);
                        }
                        out.addAnswer(new DNSRecord.Pointer(info.type, 12, 1, 3600, info.getQualifiedName()), 0L);
                        out.addAnswer(new DNSRecord.Service(info.getQualifiedName(), 33, 1, 3600, info.priority, info.weight, info.port, JmDNS.this.localHost.getName()), 0L);
                        out.addAnswer(new DNSRecord.Text(info.getQualifiedName(), 16, 1, 3600, info.text), 0L);
                    }
                }
                if (out != null) {
                    JmDNS.logger.finer("run() JmDNS announced");
                    JmDNS.this.send(out);
                }
                else {
                    this.cancel();
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
            this.taskState = this.taskState.advance();
            if (!this.taskState.isAnnounced()) {
                this.cancel();
            }
        }
    }
    
    private class Responder extends TimerTask
    {
        private DNSIncoming in;
        private InetAddress addr;
        private int port;
        
        public Responder(final DNSIncoming in, final InetAddress addr, final int port) {
            this.in = in;
            this.addr = addr;
            this.port = port;
        }
        
        public void start() {
            boolean iAmTheOnlyOne = true;
            for (final DNSEntry entry : this.in.questions) {
                if (entry instanceof DNSQuestion) {
                    final DNSQuestion q = (DNSQuestion)entry;
                    JmDNS.logger.finest("start() question=" + q);
                    iAmTheOnlyOne &= (q.type == 33 || q.type == 16 || q.type == 1 || q.type == 28 || JmDNS.this.localHost.getName().equalsIgnoreCase(q.name) || JmDNS.this.services.containsKey(q.name.toLowerCase()));
                    if (!iAmTheOnlyOne) {
                        break;
                    }
                    continue;
                }
            }
            int delay = (iAmTheOnlyOne && !this.in.isTruncated()) ? 0 : (20 + JmDNS.random.nextInt(96) - this.in.elapseSinceArrival());
            if (delay < 0) {
                delay = 0;
            }
            JmDNS.logger.finest("start() Responder chosen delay=" + delay);
            JmDNS.this.timer.schedule(this, delay);
        }
        
        @Override
        public void run() {
            synchronized (JmDNS.this.ioLock) {
                if (JmDNS.this.plannedAnswer == this.in) {
                    JmDNS.this.plannedAnswer = null;
                }
                final HashSet questions = new HashSet();
                final HashSet answers = new HashSet();
                if (JmDNS.this.state == DNSState.ANNOUNCED) {
                    try {
                        final long now = System.currentTimeMillis();
                        final long expirationTime = now + 1L;
                        final boolean isUnicast = this.port != 5353;
                        for (final DNSEntry entry : this.in.questions) {
                            if (entry instanceof DNSQuestion) {
                                final DNSQuestion q = (DNSQuestion)entry;
                                if (isUnicast) {
                                    questions.add(q);
                                }
                                int type = q.type;
                                if (type == 255 || type == 33) {
                                    if (JmDNS.this.localHost.getName().equalsIgnoreCase(q.getName())) {
                                        DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                                        if (answer != null) {
                                            answers.add(answer);
                                        }
                                        answer = JmDNS.this.localHost.getDNS6AddressRecord();
                                        if (answer != null) {
                                            answers.add(answer);
                                        }
                                        type = 0;
                                    }
                                    else if (JmDNS.this.serviceTypes.containsKey(q.getName().toLowerCase())) {
                                        type = 12;
                                    }
                                }
                                switch (type) {
                                    case 1: {
                                        final DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                                        if (answer != null) {
                                            answers.add(answer);
                                            continue;
                                        }
                                        continue;
                                    }
                                    case 28: {
                                        final DNSRecord answer = JmDNS.this.localHost.getDNS6AddressRecord();
                                        if (answer != null) {
                                            answers.add(answer);
                                            continue;
                                        }
                                        continue;
                                    }
                                    case 12: {
                                        for (final ServiceInfo info : JmDNS.this.services.values()) {
                                            if (info.getState() == DNSState.ANNOUNCED && q.name.equalsIgnoreCase(info.type)) {
                                                DNSRecord answer2 = JmDNS.this.localHost.getDNS4AddressRecord();
                                                if (answer2 != null) {
                                                    answers.add(answer2);
                                                }
                                                answer2 = JmDNS.this.localHost.getDNS6AddressRecord();
                                                if (answer2 != null) {
                                                    answers.add(answer2);
                                                }
                                                answers.add(new DNSRecord.Pointer(info.type, 12, 1, 3600, info.getQualifiedName()));
                                                answers.add(new DNSRecord.Service(info.getQualifiedName(), 33, 32769, 3600, info.priority, info.weight, info.port, JmDNS.this.localHost.getName()));
                                                answers.add(new DNSRecord.Text(info.getQualifiedName(), 16, 32769, 3600, info.text));
                                            }
                                        }
                                        if (q.name.equalsIgnoreCase("_services._mdns._udp.local.")) {
                                            final Iterator serviceTypeIterator = JmDNS.this.serviceTypes.values().iterator();
                                            while (serviceTypeIterator.hasNext()) {
                                                answers.add(new DNSRecord.Pointer("_services._mdns._udp.local.", 12, 1, 3600, serviceTypeIterator.next()));
                                            }
                                            continue;
                                        }
                                        continue;
                                    }
                                    case 16:
                                    case 33:
                                    case 255: {
                                        final ServiceInfo info2 = JmDNS.this.services.get(q.name.toLowerCase());
                                        if (info2 != null && info2.getState() == DNSState.ANNOUNCED) {
                                            DNSRecord answer3 = JmDNS.this.localHost.getDNS4AddressRecord();
                                            if (answer3 != null) {
                                                answers.add(answer3);
                                            }
                                            answer3 = JmDNS.this.localHost.getDNS6AddressRecord();
                                            if (answer3 != null) {
                                                answers.add(answer3);
                                            }
                                            answers.add(new DNSRecord.Pointer(info2.type, 12, 1, 3600, info2.getQualifiedName()));
                                            answers.add(new DNSRecord.Service(info2.getQualifiedName(), 33, 32769, 3600, info2.priority, info2.weight, info2.port, JmDNS.this.localHost.getName()));
                                            answers.add(new DNSRecord.Text(info2.getQualifiedName(), 16, 32769, 3600, info2.text));
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            }
                        }
                        for (final DNSRecord knownAnswer : this.in.answers) {
                            if (knownAnswer.ttl > 1800 && answers.remove(knownAnswer)) {
                                JmDNS.logger.log(Level.FINER, "JmDNS Responder Known Answer Removed");
                            }
                        }
                        if (answers.size() != 0) {
                            JmDNS.logger.finer("run() JmDNS responding");
                            DNSOutgoing out = null;
                            if (isUnicast) {
                                out = new DNSOutgoing(33792, false);
                            }
                            Iterator j = questions.iterator();
                            while (j.hasNext()) {
                                out.addQuestion(j.next());
                            }
                            j = answers.iterator();
                            while (j.hasNext()) {
                                out = JmDNS.this.addAnswer(this.in, this.addr, this.port, out, j.next());
                            }
                            JmDNS.this.send(out);
                        }
                        this.cancel();
                    }
                    catch (Throwable e) {
                        JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                        JmDNS.this.close();
                    }
                }
            }
        }
    }
    
    private class TypeResolver extends TimerTask
    {
        int count;
        
        private TypeResolver() {
            this.count = 0;
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 225L, 225L);
        }
        
        @Override
        public void run() {
            try {
                if (JmDNS.this.state == DNSState.ANNOUNCED) {
                    if (++this.count < 3) {
                        JmDNS.logger.finer("run() JmDNS querying type");
                        final DNSOutgoing out = new DNSOutgoing(0);
                        out.addQuestion(new DNSQuestion("_services._mdns._udp.local.", 12, 1));
                        final Iterator iterator = JmDNS.this.serviceTypes.values().iterator();
                        while (iterator.hasNext()) {
                            out.addAnswer(new DNSRecord.Pointer("_services._mdns._udp.local.", 12, 1, 3600, iterator.next()), 0L);
                        }
                        JmDNS.this.send(out);
                    }
                    else {
                        this.cancel();
                    }
                }
                else if (JmDNS.this.state == DNSState.CANCELED) {
                    this.cancel();
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
        }
    }
    
    private class ServiceResolver extends TimerTask
    {
        int count;
        private String type;
        
        public ServiceResolver(final String type) {
            this.count = 0;
            this.type = type;
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 225L, 225L);
        }
        
        @Override
        public void run() {
            try {
                if (JmDNS.this.state == DNSState.ANNOUNCED) {
                    if (this.count++ < 3) {
                        JmDNS.logger.finer("run() JmDNS querying service");
                        final long now = System.currentTimeMillis();
                        final DNSOutgoing out = new DNSOutgoing(0);
                        out.addQuestion(new DNSQuestion(this.type, 12, 1));
                        for (final ServiceInfo info : JmDNS.this.services.values()) {
                            try {
                                out.addAnswer(new DNSRecord.Pointer(info.type, 12, 1, 3600, info.getQualifiedName()), now);
                            }
                            catch (IOException ee) {
                                break;
                            }
                        }
                        JmDNS.this.send(out);
                    }
                    else {
                        this.cancel();
                    }
                }
                else if (JmDNS.this.state == DNSState.CANCELED) {
                    this.cancel();
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
        }
    }
    
    private class ServiceInfoResolver extends TimerTask
    {
        int count;
        private ServiceInfo info;
        
        public ServiceInfoResolver(final ServiceInfo info) {
            this.count = 0;
            this.info = info;
            (info.dns = JmDNS.this).addListener(info, new DNSQuestion(info.getQualifiedName(), 255, 1));
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 225L, 225L);
        }
        
        @Override
        public void run() {
            try {
                if (JmDNS.this.state == DNSState.ANNOUNCED) {
                    if (this.count++ < 3 && !this.info.hasData()) {
                        final long now = System.currentTimeMillis();
                        final DNSOutgoing out = new DNSOutgoing(0);
                        out.addQuestion(new DNSQuestion(this.info.getQualifiedName(), 33, 1));
                        out.addQuestion(new DNSQuestion(this.info.getQualifiedName(), 16, 1));
                        if (this.info.server != null) {
                            out.addQuestion(new DNSQuestion(this.info.server, 1, 1));
                        }
                        out.addAnswer((DNSRecord)JmDNS.this.cache.get(this.info.getQualifiedName(), 33, 1), now);
                        out.addAnswer((DNSRecord)JmDNS.this.cache.get(this.info.getQualifiedName(), 16, 1), now);
                        if (this.info.server != null) {
                            out.addAnswer((DNSRecord)JmDNS.this.cache.get(this.info.server, 1, 1), now);
                        }
                        JmDNS.this.send(out);
                    }
                    else {
                        this.cancel();
                        JmDNS.this.removeListener(this.info);
                    }
                }
                else if (JmDNS.this.state == DNSState.CANCELED) {
                    this.cancel();
                    JmDNS.this.removeListener(this.info);
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
        }
    }
    
    private class Canceler extends TimerTask
    {
        int count;
        private ServiceInfo[] infos;
        private Object lock;
        int ttl;
        
        public Canceler(final ServiceInfo info, final Object lock) {
            this.count = 0;
            this.ttl = 0;
            this.infos = new ServiceInfo[] { info };
            this.lock = lock;
            JmDNS.this.addListener(info, new DNSQuestion(info.getQualifiedName(), 255, 1));
        }
        
        public Canceler(final ServiceInfo[] infos, final Object lock) {
            this.count = 0;
            this.ttl = 0;
            this.infos = infos;
            this.lock = lock;
        }
        
        public Canceler(final Collection infos, final Object lock) {
            this.count = 0;
            this.ttl = 0;
            this.infos = infos.toArray(new ServiceInfo[infos.size()]);
            this.lock = lock;
        }
        
        public void start() {
            JmDNS.this.timer.schedule(this, 0L, 1000L);
        }
        
        @Override
        public void run() {
            try {
                if (++this.count < 3) {
                    JmDNS.logger.finer("run() JmDNS canceling service");
                    final DNSOutgoing out = new DNSOutgoing(33792);
                    for (int i = 0; i < this.infos.length; ++i) {
                        final ServiceInfo info = this.infos[i];
                        out.addAnswer(new DNSRecord.Pointer(info.type, 12, 1, this.ttl, info.getQualifiedName()), 0L);
                        out.addAnswer(new DNSRecord.Service(info.getQualifiedName(), 33, 1, this.ttl, info.priority, info.weight, info.port, JmDNS.this.localHost.getName()), 0L);
                        out.addAnswer(new DNSRecord.Text(info.getQualifiedName(), 16, 1, this.ttl, info.text), 0L);
                        DNSRecord answer = JmDNS.this.localHost.getDNS4AddressRecord();
                        if (answer != null) {
                            out.addAnswer(answer, 0L);
                        }
                        answer = JmDNS.this.localHost.getDNS6AddressRecord();
                        if (answer != null) {
                            out.addAnswer(answer, 0L);
                        }
                    }
                    JmDNS.this.send(out);
                }
                else {
                    synchronized (this.lock) {
                        JmDNS.this.closed = true;
                        this.lock.notifyAll();
                    }
                    this.cancel();
                }
            }
            catch (Throwable e) {
                JmDNS.logger.log(Level.WARNING, "run() exception ", e);
                JmDNS.this.recover();
            }
        }
    }
    
    private class Shutdown implements Runnable
    {
        @Override
        public void run() {
            JmDNS.this.shutdown = null;
            JmDNS.this.close();
        }
    }
    
    private static class ServiceCollector implements ServiceListener
    {
        private static Logger logger;
        private Map infos;
        public String type;
        
        public ServiceCollector(final String type) {
            this.infos = Collections.synchronizedMap(new HashMap<Object, Object>());
            this.type = type;
        }
        
        @Override
        public void serviceAdded(final ServiceEvent event) {
            synchronized (this.infos) {
                event.getDNS().requestServiceInfo(event.getType(), event.getName(), 0);
            }
        }
        
        @Override
        public void serviceRemoved(final ServiceEvent event) {
            synchronized (this.infos) {
                this.infos.remove(event.getName());
            }
        }
        
        @Override
        public void serviceResolved(final ServiceEvent event) {
            synchronized (this.infos) {
                this.infos.put(event.getName(), event.getInfo());
            }
        }
        
        public ServiceInfo[] list() {
            synchronized (this.infos) {
                return (ServiceInfo[])this.infos.values().toArray(new ServiceInfo[this.infos.size()]);
            }
        }
        
        @Override
        public String toString() {
            final StringBuffer aLog = new StringBuffer();
            synchronized (this.infos) {
                for (final Object key : this.infos.keySet()) {
                    aLog.append("\n\t\tService: " + key + ": " + this.infos.get(key));
                }
            }
            return aLog.toString();
        }
        
        static {
            ServiceCollector.logger = Logger.getLogger(ServiceCollector.class.toString());
        }
    }
}
