// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import anon.infoservice.AbstractDatabaseEntry;
import anon.tor.util.DNSCacheEntry;
import anon.AnonServiceEventListener;
import anon.terms.TermsAndConditionConfirmation;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import java.net.ConnectException;
import anon.AnonChannel;
import anon.tor.ordescription.InfoServiceORListFetcher;
import java.util.Date;
import java.io.IOException;
import logging.LogHolder;
import logging.LogType;
import anon.tor.ordescription.ORDescriptor;
import java.util.Enumeration;
import anon.infoservice.ListenerInterface;
import java.util.Random;
import java.security.SecureRandom;
import anon.tor.ordescription.ORListFetcher;
import anon.tor.ordescription.PlainORListFetcher;
import anon.infoservice.IMutableProxyInterface;
import anon.crypto.MyRandom;
import java.util.Hashtable;
import anon.infoservice.Database;
import java.util.Vector;
import anon.tor.ordescription.ORList;
import anon.AnonService;

public class Tor implements Runnable, AnonService
{
    public static final int MAX_ROUTE_LEN = 5;
    public static final int MIN_ROUTE_LEN = 2;
    public static final int DNS_TIME_OUT = 600000;
    private static Tor ms_theTorInstance;
    private ORList m_orList;
    private Vector m_allowedORNames;
    private Vector m_allowedFirstORNames;
    private Vector m_allowedExitNodeNames;
    private Circuit[] m_activeCircuits;
    private int m_MaxNrOfActiveCircuits;
    private Object m_oActiveCircuitSync;
    private Object m_oStartStopSync;
    private FirstOnionRouterConnectionFactory m_firstORFactory;
    private Database m_DNSCache;
    private Hashtable m_CircuitForDestination;
    private Vector[] m_KeysForCircuit;
    private volatile boolean m_bIsStarted;
    private boolean m_bIsCreatingCircuit;
    private boolean m_useDNSCache;
    private int m_circuitLengthMin;
    private int m_circuitLengthMax;
    private int m_ConnectionsPerCircuit;
    private MyRandom m_rand;
    public static final String DEFAULT_DIR_SERVER_ADDR = "moria.seul.org";
    public static final int DEFAULT_DIR_SERVER_PORT = 9031;
    private Thread m_circuitCreator;
    private volatile boolean m_bCloseCreator;
    private IMutableProxyInterface m_proxyInterface;
    static /* synthetic */ Class class$anon$tor$util$DNSCacheEntry;
    
    private Tor() {
        this.m_orList = new ORList(new PlainORListFetcher("moria.seul.org", 9031));
        this.m_oActiveCircuitSync = new Object();
        this.m_oStartStopSync = new Object();
        this.m_firstORFactory = new FirstOnionRouterConnectionFactory(this);
        this.m_circuitLengthMin = 2;
        this.m_circuitLengthMax = 5;
        this.m_ConnectionsPerCircuit = 1000;
        this.m_rand = new MyRandom(new SecureRandom());
        this.m_bIsStarted = false;
        this.m_bIsCreatingCircuit = false;
        this.m_MaxNrOfActiveCircuits = 5;
        this.m_activeCircuits = new Circuit[this.m_MaxNrOfActiveCircuits];
        this.m_useDNSCache = true;
        this.m_DNSCache = Database.getInstance((Tor.class$anon$tor$util$DNSCacheEntry == null) ? (Tor.class$anon$tor$util$DNSCacheEntry = class$("anon.tor.util.DNSCacheEntry")) : Tor.class$anon$tor$util$DNSCacheEntry);
        this.m_CircuitForDestination = new Hashtable();
        this.m_KeysForCircuit = new Vector[this.m_MaxNrOfActiveCircuits];
        this.m_bCloseCreator = false;
        this.m_proxyInterface = null;
    }
    
    private void updateORList() {
        synchronized (this.m_orList) {
            this.m_orList.updateList();
        }
    }
    
    protected synchronized Circuit getCircuitForDestination(String resolveDNS, final int n, final Hashtable hashtable) {
        if (!this.m_bIsStarted) {
            return null;
        }
        if (!ListenerInterface.isValidIP(resolveDNS)) {
            resolveDNS = this.resolveDNS(resolveDNS);
            if (!ListenerInterface.isValidIP(resolveDNS)) {
                return null;
            }
        }
        final String string = resolveDNS + ":" + n;
        if (this.m_CircuitForDestination.containsKey(string)) {
            final Circuit circuit = this.m_activeCircuits[this.m_CircuitForDestination.get(string)];
            if (circuit != null && !circuit.isShutdown() && circuit.isAllowed(resolveDNS, n) && (hashtable == null || !hashtable.containsKey(circuit))) {
                return circuit;
            }
        }
        for (int i = 0; i < this.m_MaxNrOfActiveCircuits; ++i) {
            final Circuit circuit2 = this.m_activeCircuits[i];
            if (circuit2 != null && !circuit2.isShutdown() && circuit2.isAllowed(resolveDNS, n) && (hashtable == null || !hashtable.containsKey(circuit2))) {
                this.m_CircuitForDestination.put(string, new Integer(i));
                if (this.m_KeysForCircuit[i] == null) {
                    this.m_KeysForCircuit[i] = new Vector();
                }
                this.m_KeysForCircuit[i].addElement(string);
                return circuit2;
            }
        }
        synchronized (this.m_oActiveCircuitSync) {
            for (int j = 0; j < 5; ++j) {
                int nextInt = this.m_rand.nextInt(this.m_MaxNrOfActiveCircuits);
                int k = 0;
                int n2 = 0;
                while (k < this.m_MaxNrOfActiveCircuits) {
                    n2 = nextInt % this.m_MaxNrOfActiveCircuits;
                    if (this.m_activeCircuits[n2] == null || this.m_activeCircuits[n2].isShutdown()) {
                        if (this.m_KeysForCircuit[n2] != null) {
                            final Enumeration elements = this.m_KeysForCircuit[n2].elements();
                            while (elements.hasMoreElements()) {
                                this.m_CircuitForDestination.remove(elements.nextElement());
                            }
                            this.m_KeysForCircuit[n2] = null;
                        }
                        this.m_activeCircuits[n2] = this.createNewCircuit(resolveDNS, n);
                        if (this.m_activeCircuits[n2] != null && !this.m_activeCircuits[n2].isShutdown()) {
                            this.m_CircuitForDestination.put(string, new Integer(n2));
                            (this.m_KeysForCircuit[n2] = new Vector()).addElement(string);
                            return this.m_activeCircuits[n2];
                        }
                        break;
                    }
                    else {
                        if (this.m_activeCircuits[n2].isAllowed(resolveDNS, n)) {
                            if (!this.m_KeysForCircuit[n2].contains(string)) {
                                this.m_CircuitForDestination.put(string, new Integer(n2));
                                this.m_KeysForCircuit[n2].addElement(string);
                            }
                            return this.m_activeCircuits[n2];
                        }
                        ++nextInt;
                        ++k;
                    }
                }
                if (this.m_activeCircuits[n2] != null && !this.m_activeCircuits[n2].isShutdown()) {
                    final int n3 = nextInt % this.m_MaxNrOfActiveCircuits;
                    this.m_activeCircuits[n3].shutdown();
                    final Enumeration elements2 = this.m_KeysForCircuit[n3].elements();
                    while (elements2.hasMoreElements()) {
                        this.m_CircuitForDestination.remove(elements2.nextElement());
                    }
                    this.m_KeysForCircuit[n3] = null;
                    this.m_activeCircuits[n3] = this.createNewCircuit(resolveDNS, n);
                    if (this.m_activeCircuits[n3] != null && !this.m_activeCircuits[n3].isShutdown()) {
                        this.m_CircuitForDestination.put(string, new Integer(n3));
                        (this.m_KeysForCircuit[n3] = new Vector()).addElement(string);
                        return this.m_activeCircuits[n3];
                    }
                }
            }
            return null;
        }
    }
    
    private Circuit createNewCircuit(final String s, final int n) {
        if (!this.m_bIsStarted) {
            return null;
        }
        synchronized (this.m_oStartStopSync) {
            this.m_bIsCreatingCircuit = true;
        }
        try {
            synchronized (this.m_orList) {
                final Vector vector = new Vector<ORDescriptor>();
                final int n2 = this.m_rand.nextInt(this.m_circuitLengthMax - this.m_circuitLengthMin + 1) + this.m_circuitLengthMin;
                final Date published = this.m_orList.getPublished();
                if (this.m_orList.size() == 0 || (published != null && published.getTime() < System.currentTimeMillis() - 3600000L)) {
                    this.updateORList();
                    if (this.m_orList.size() == 0) {
                        return null;
                    }
                }
                ORDescriptor orDescriptor;
                if (this.m_allowedFirstORNames != null) {
                    orDescriptor = this.m_orList.getByRandom(this.m_allowedFirstORNames);
                }
                else {
                    orDescriptor = this.m_orList.getByRandom(n2);
                }
                LogHolder.log(7, LogType.TOR, "added as first: " + orDescriptor);
                vector.addElement(orDescriptor);
                final Vector list = this.m_orList.getList();
                final Enumeration elements = ((Vector)list.clone()).elements();
                while (elements.hasMoreElements()) {
                    final ORDescriptor orDescriptor2 = elements.nextElement();
                    if (this.m_allowedExitNodeNames != null && !this.m_allowedExitNodeNames.contains(orDescriptor2.getName())) {
                        list.removeElement(orDescriptor2);
                    }
                    else if (s != null && !orDescriptor2.getAcl().isAllowed(s, n)) {
                        list.removeElement(orDescriptor2);
                    }
                    else {
                        if (!vector.contains(orDescriptor2)) {
                            continue;
                        }
                        list.removeElement(orDescriptor2);
                    }
                }
                if (list.size() <= 0) {
                    return null;
                }
                final ORDescriptor orDescriptor3 = list.elementAt(this.m_rand.nextInt(list.size()));
                vector.addElement(orDescriptor3);
                LogHolder.log(7, LogType.TOR, "added as last: " + orDescriptor3);
                for (int i = 2; i < n2; ++i) {
                    ORDescriptor orDescriptor4;
                    do {
                        if (this.m_allowedORNames != null) {
                            orDescriptor4 = this.m_orList.getByRandom(this.m_allowedORNames);
                        }
                        else {
                            orDescriptor4 = this.m_orList.getByRandom(n2);
                        }
                    } while (vector.contains(orDescriptor4));
                    LogHolder.log(7, LogType.TOR, "added " + orDescriptor4);
                    vector.insertElementAt(orDescriptor4, 1);
                }
                final ORDescriptor orDescriptor5 = vector.elementAt(0);
                final FirstOnionRouterConnection firstOnionRouterConnection = this.m_firstORFactory.createFirstOnionRouterConnection(orDescriptor5);
                if (firstOnionRouterConnection == null) {
                    LogHolder.log(7, LogType.TOR, "removed " + orDescriptor5.getName());
                    this.m_orList.remove(orDescriptor5.getName());
                    throw new IOException("Problem with router " + vector + ". Cannot connect.");
                }
                final Circuit circuit = firstOnionRouterConnection.createCircuit(vector);
                this.m_bIsCreatingCircuit = false;
                if (circuit == null) {
                    return null;
                }
                circuit.setMaxNrOfStreams(this.m_ConnectionsPerCircuit);
                return circuit;
            }
        }
        catch (Exception ex) {
            this.m_bIsCreatingCircuit = false;
            return null;
        }
        finally {
            this.m_bIsCreatingCircuit = false;
        }
    }
    
    public static Tor getInstance() {
        if (Tor.ms_theTorInstance == null) {
            Tor.ms_theTorInstance = new Tor();
        }
        return Tor.ms_theTorInstance;
    }
    
    public void run() {
        int n = 0;
        while (!this.m_bCloseCreator && !this.m_bCloseCreator) {
            synchronized (this.m_oActiveCircuitSync) {
                int n2 = -1;
                for (int i = 0; i < this.m_MaxNrOfActiveCircuits; ++i) {
                    if (this.m_activeCircuits[i] == null || this.m_activeCircuits[i].isShutdown()) {
                        n2 = i;
                        break;
                    }
                }
                if (n2 != -1) {
                    n = 1;
                    final Circuit newCircuit = this.createNewCircuit("141.76.46.1", 80);
                    if (newCircuit == null) {
                        continue;
                    }
                    this.m_activeCircuits[n2] = newCircuit;
                }
            }
            if (n != 0) {
                n = 0;
                try {
                    Thread.sleep(10000L);
                }
                catch (InterruptedException ex) {}
            }
            else {
                try {
                    Thread.sleep(30000L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        this.m_circuitCreator = null;
    }
    
    private void start(final boolean b) throws IOException {
        synchronized (this.m_oStartStopSync) {
            this.m_bIsStarted = true;
            this.m_bCloseCreator = false;
            this.m_activeCircuits = new Circuit[this.m_MaxNrOfActiveCircuits];
            if (b) {
                (this.m_circuitCreator = new Thread(this, "TorCircuitCreator")).setDaemon(true);
                this.m_circuitCreator.start();
            }
            else {
                this.m_circuitCreator = null;
            }
        }
    }
    
    private void stop() {
        synchronized (this.m_oStartStopSync) {
            this.m_bIsStarted = false;
            this.m_bCloseCreator = true;
            if (this.m_circuitCreator != null) {
                try {
                    this.m_circuitCreator.interrupt();
                }
                catch (Exception ex) {}
                try {
                    this.m_circuitCreator.join();
                }
                catch (InterruptedException ex2) {}
                this.m_circuitCreator = null;
            }
            if (this.m_bIsCreatingCircuit) {
                this.m_firstORFactory.closeAll();
                while (this.m_bIsCreatingCircuit) {
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
            this.m_firstORFactory.closeAll();
        }
    }
    
    private void setCircuitLength(final int circuitLengthMin, final int circuitLengthMax) {
        if (circuitLengthMax >= circuitLengthMin && circuitLengthMin >= 2 && circuitLengthMax <= 5) {
            this.m_circuitLengthMax = circuitLengthMax;
            this.m_circuitLengthMin = circuitLengthMin;
        }
    }
    
    private void setConnectionsPerRoute(final int connectionsPerCircuit) {
        this.m_ConnectionsPerCircuit = connectionsPerCircuit;
    }
    
    private void setORListServer(final boolean b, final String s, final int n) {
        if (b) {
            this.m_orList.setFetcher(new InfoServiceORListFetcher());
        }
        else {
            this.m_orList.setFetcher(new PlainORListFetcher(s, n));
        }
    }
    
    public void setUseDNSCache(final boolean useDNSCache) {
        this.m_useDNSCache = useDNSCache;
    }
    
    public AnonChannel createChannel(final int n) throws ConnectException {
        if (n != 1) {
            return null;
        }
        try {
            return new TorSocksChannel(this);
        }
        catch (Exception ex) {
            throw new ConnectException("Could not create Tor channel: " + ex.getMessage());
        }
    }
    
    public AnonChannel createChannel(final String s, final int n) throws ConnectException {
        try {
            return this.getCircuitForDestination(s, n, null).createChannel(s, n);
        }
        catch (Exception ex) {
            throw new ConnectException("Error creating Tor channel: " + ex.getMessage());
        }
    }
    
    public synchronized int initialize(final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        if (!(anonServerDescription instanceof TorAnonServerDescription)) {
            return -5;
        }
        final TorAnonServerDescription torAnonServerDescription = (TorAnonServerDescription)anonServerDescription;
        this.setORListServer(torAnonServerDescription.useInfoService(), torAnonServerDescription.getTorDirServerAddr(), torAnonServerDescription.getTorDirServerPort());
        this.setCircuitLength(torAnonServerDescription.getMinRouteLen(), torAnonServerDescription.getMaxRouteLen());
        this.setConnectionsPerRoute(torAnonServerDescription.getMaxConnectionsPerRoute());
        try {
            this.start(torAnonServerDescription.startCircuitsAtStartup());
        }
        catch (Exception ex) {
            return -9;
        }
        return 0;
    }
    
    public int setProxy(final IMutableProxyInterface proxyInterface) {
        this.m_proxyInterface = proxyInterface;
        return 0;
    }
    
    public IMutableProxyInterface getProxy() {
        return this.m_proxyInterface;
    }
    
    public void shutdown(final boolean b) {
        try {
            this.stop();
        }
        catch (Exception ex) {}
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListeners() {
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public synchronized String resolveDNS(final String s) {
        String s2 = null;
        if (this.m_useDNSCache) {
            final DNSCacheEntry dnsCacheEntry = (DNSCacheEntry)this.m_DNSCache.getEntryById(s);
            if (dnsCacheEntry != null) {
                LogHolder.log(7, LogType.TOR, "Resolved from Database : " + dnsCacheEntry.getId() + " - " + dnsCacheEntry.getIp());
                return dnsCacheEntry.getIp();
            }
        }
        synchronized (this.m_oActiveCircuitSync) {
            for (int i = 0; i < 3; ++i) {
                final int nextInt = this.m_rand.nextInt(this.m_MaxNrOfActiveCircuits);
                if (this.m_activeCircuits[nextInt] == null || this.m_activeCircuits[nextInt].isShutdown()) {
                    this.m_activeCircuits[nextInt] = this.createNewCircuit(null, -1);
                }
                if (this.m_activeCircuits[nextInt] != null && !this.m_activeCircuits[nextInt].isShutdown()) {
                    final String resolveDNS = this.m_activeCircuits[nextInt].resolveDNS(s);
                    if (resolveDNS != null) {
                        s2 = resolveDNS;
                        break;
                    }
                }
            }
        }
        if (s2 != null) {
            final DNSCacheEntry dnsCacheEntry2 = new DNSCacheEntry(s, s2, System.currentTimeMillis() + 600000L);
            this.m_DNSCache.update(dnsCacheEntry2);
            LogHolder.log(7, LogType.TOR, "Adding to Database : " + dnsCacheEntry2.getId() + " - " + dnsCacheEntry2.getIp());
        }
        return s2;
    }
    
    public boolean isConnected() {
        return this.m_bIsStarted;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Tor.ms_theTorInstance = null;
    }
}
