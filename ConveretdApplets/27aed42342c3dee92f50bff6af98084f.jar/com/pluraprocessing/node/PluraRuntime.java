// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import java.io.ByteArrayOutputStream;
import com.pluraprocessing.common.utility.BitConversionUtility;
import com.pluraprocessing.node.exception.PluraIntervalException;
import com.pluraprocessing.node.domain.JarURL;
import com.pluraprocessing.node.exception.PluraIOException;
import java.util.Collection;
import com.pluraprocessing.common.utility.FilePathUtility;
import com.pluraprocessing.common.domain.Executable;
import java.util.UUID;
import com.pluraprocessing.common.domain.WorkVersion;
import com.pluraprocessing.common.xml.XmlWriter;
import com.pluraprocessing.common.domain.HeapSpace;
import com.pluraprocessing.common.utility.PluraHttpConnectionUtility;
import com.pluraprocessing.common.utility.HttpMethod;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import com.pluraprocessing.node.domain.PluraClassLoaderVersion;
import java.util.Stack;
import com.pluraprocessing.node.runtime.PluraRuntimeClassLoader;
import com.pluraprocessing.common.domain.NodeType;
import java.applet.Applet;
import java.util.Calendar;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.List;

public class PluraRuntime
{
    public static final Environment ENVIRONMENT;
    private static PluraRuntime ref;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String PLURA_ARCHIVE_SVR = "http://archive.pluraserver.com";
    public static final String PLURA_BW_SVR = "http://bandwidth.pluraserver.com";
    public static final String PLURA_DIST_SVR_LOCATION = "/PluraDistribution/PluraClientServer";
    public static final String PLURA_WORK_UNITS_LOCATION = "/PluraArchive/PluraArchivedWorkUnits";
    public static final String PLURA_BW_URI = "/PluraBandwidthCap/BandwidthCap";
    public static final String PLURASTART_CLASSNAME = "com.pluraprocessing.node.StartPlura";
    private static final long NUMBER_MS_BETWEEN_CLIENTID_REQUESTS = 0L;
    private static final double HARD_LIMIT_CPU_PERCENTAGE = 0.8;
    private static final double HARD_LIMIT_BW_PERCENTAGE = 1.0;
    public static final int EXPECTED_MAX_HEAP_SIZE_MB = 2048;
    private static final String PLURA_VERSION_SVR_LOCATION_HEAP_PARAM = "&heapMB=";
    private static final String PLURA_VERSION_SVR_LOCATION_PARENT_VERSION_PARAM = "&parentId=";
    public static int NUMBER_TIMES_SKIP_TS_WUS;
    public static final boolean SWITCH_NODE_SERVERS = false;
    private List<String> badVersions;
    private Map<String, Boolean> nodeServers;
    private int minimumNoWorkThreshhold;
    private int noWorkAppletThreshhold;
    public long numberMsSleepNoWork;
    public long numberMsSleepException;
    public int NUMBER_DISKS_DATA_SERVER;
    public String NODE_SERVER_DISK_NAME;
    public String DATA_BASE_DIRECTORY;
    public String PLURA_DATA_SVR;
    public String PLURA_SVR;
    public String PROTECTION_DOMAIN;
    private String PLURA_JAR_LOCATION;
    public String PLURA_CLIENT_APPLET_LOCATION;
    public String PLURA_VERSION_SVR_LOCATION;
    public String PLURA_TIME_SENSITIVE_WUS_LOCATION;
    private String stdOutBaseLocation;
    private FileOutputStream stdOutStream;
    public long maximumNumberJarBytes;
    private HashMap<String, Object> allData;
    private int numberPiecesData;
    private String affiliateId;
    private String clientId;
    private String pluraServerName;
    private double percentageCPU;
    private double bandwidthPercent;
    private double percentUsed;
    private boolean isStopped;
    private Calendar timeClientWorkLastRequested;
    private double sleepPercent;
    private Applet applet;
    private boolean usesLimitedAPI;
    private NodeType currentNodeType;
    private int numberPluraThreads;
    private int numberThreadsToStart;
    private int numberTimesNoWorkApplet;
    private IStartPlura plura;
    private StartPluraThread startPluraThread;
    private ClassLoader parentClassLoader;
    private PluraRuntimeClassLoader currentClassLoader;
    private Stack<PluraClassLoaderVersion> currentPluraVersions;
    private Map<String, PluraClassLoaderVersion> unusedPluraVersions;
    private Map<URL, byte[]> jarBytes;
    private Long unaccountedForJarBytes;
    private long totalNumberJarBytes;
    private Long maxBandwidthKb;
    private String oldVersionId;
    private int availableHeapSpaceMB;
    private int numberTimesSkippedTimeSensitiveWUs;
    
    static {
        ENVIRONMENT = Environment.LIVE;
    }
    
    private PluraRuntime() {
        this.badVersions = new ArrayList<String>();
        this.nodeServers = new HashMap<String, Boolean>();
        this.allData = new HashMap<String, Object>();
        this.isStopped = true;
        this.sleepPercent = 0.0;
        this.numberPluraThreads = 0;
        this.numberThreadsToStart = 0;
        this.numberTimesNoWorkApplet = 0;
        this.startPluraThread = new StartPluraThread();
        this.parentClassLoader = null;
        this.currentPluraVersions = new Stack<PluraClassLoaderVersion>();
        this.unusedPluraVersions = new HashMap<String, PluraClassLoaderVersion>();
        this.jarBytes = new HashMap<URL, byte[]>();
        this.unaccountedForJarBytes = 0L;
        this.totalNumberJarBytes = 0L;
        this.numberTimesSkippedTimeSensitiveWUs = PluraRuntime.NUMBER_TIMES_SKIP_TS_WUS;
        this.init();
        this.determineAvailableHeapSpace();
    }
    
    public static synchronized PluraRuntime getInstance() {
        if (PluraRuntime.ref == null) {
            PluraRuntime.ref = new PluraRuntime();
        }
        return PluraRuntime.ref;
    }
    
    private void init() {
        switch (PluraRuntime.ENVIRONMENT) {
            case LIVE: {
                this.initializeLiveParameters();
                break;
            }
            case SANDBOX: {
                this.initializeSandboxParameters();
                break;
            }
            case TEST: {
                this.initializeTestParameters();
                break;
            }
        }
    }
    
    public synchronized void initialize(final Applet applet, final String versionId, final String affiliateId, final String clientId, final String pluraServerName, final double percentageCPU, final double bandwidthPercent, final boolean isStopped, final int numberThreads, final Boolean isRestricted) {
        this.affiliateId = affiliateId;
        this.clientId = clientId;
        this.pluraServerName = pluraServerName;
        this.percentageCPU = percentageCPU;
        this.bandwidthPercent = bandwidthPercent;
        this.isStopped = isStopped;
        this.numberPluraThreads = numberThreads;
        this.applet = applet;
        this.usesLimitedAPI = false;
        if (versionId != null && applet != null && (this.currentPluraVersions == null || this.currentPluraVersions.isEmpty())) {
            this.currentPluraVersions.add(new PluraClassLoaderVersion(null, versionId, this.noWorkAppletThreshhold));
        }
        else if (applet == null && this.parentClassLoader == null) {
            this.parentClassLoader = this.getClass().getClassLoader();
        }
        print("Initialized PluraRuntime", false);
    }
    
    public synchronized void initialize(final Applet applet, final String versionId, final String affiliateId, final String clientId, final String pluraServerName, final double percentageCPU, final double bandwidthPercent, final boolean isStopped, final int numberThreads, final Boolean isRestricted, final ClassLoader parentClassLoader) {
        this.parentClassLoader = parentClassLoader;
        this.initialize(applet, versionId, affiliateId, clientId, pluraServerName, percentageCPU, bandwidthPercent, isStopped, numberThreads, isRestricted);
    }
    
    private void determineAvailableHeapSpace() {
        Map<Integer, byte[]> testHeapCapacity = new HashMap<Integer, byte[]>();
        int availableHeap = 2048;
        Label_0134: {
            try {
                final int chunkSizeMB = 25;
                for (int numberChunks = 2048 / chunkSizeMB, i = 0; i < numberChunks; ++i) {
                    if (i == numberChunks) {
                        testHeapCapacity.put(i, new byte[(chunkSizeMB + 2048 % chunkSizeMB) * 1024 * 1024]);
                    }
                    else {
                        testHeapCapacity.put(i, new byte[chunkSizeMB * 1024 * 1024]);
                    }
                }
            }
            catch (Throwable e) {
                availableHeap = (int)(Runtime.getRuntime().maxMemory() / 1024L / 1024L);
                break Label_0134;
            }
            finally {
                testHeapCapacity = null;
            }
            testHeapCapacity = null;
        }
        this.setAvailableHeapSpaceMB(availableHeap);
    }
    
    public void createPluraCustomerObject(final int numberPiecesData, final boolean isBandwidthApplication) {
        this.numberPiecesData = numberPiecesData;
        if (this.percentageCPU > 1.0 && this.percentageCPU <= 100.0) {
            this.percentageCPU /= 100.0;
        }
        if (this.percentageCPU > 0.8) {
            this.percentageCPU = 0.8;
        }
        if (this.bandwidthPercent > 1.0 && this.bandwidthPercent <= 100.0) {
            this.bandwidthPercent /= 100.0;
        }
        if (this.bandwidthPercent > 1.0) {
            this.bandwidthPercent = 1.0;
        }
        if (isBandwidthApplication) {
            this.sleepPercent = 0.0;
            this.percentUsed = 1.0;
        }
        else {
            this.sleepPercent = 1.0 / this.percentageCPU - 1.0;
            this.percentUsed = this.percentageCPU;
        }
        if (this.sleepPercent < 0.0) {
            this.sleepPercent = 1.0;
            this.percentUsed = 0.5;
        }
        if (isBandwidthApplication) {
            this.numberThreadsToStart = 1;
        }
        else {
            this.numberThreadsToStart = 1;
        }
    }
    
    public synchronized void createRestrictedPluraCustomerObject() {
        this.numberPiecesData = 0;
        if (this.percentageCPU > 1.0 && this.percentageCPU <= 100.0) {
            this.percentageCPU /= 100.0;
        }
        if (this.bandwidthPercent > 1.0 && this.bandwidthPercent <= 100.0) {
            this.bandwidthPercent /= 100.0;
        }
        this.sleepPercent = 1.0 / this.percentageCPU - 1.0;
        this.percentUsed = this.percentageCPU;
        if (this.sleepPercent < 0.0) {
            this.sleepPercent = 1.0;
            this.percentUsed = 0.5;
        }
        this.numberThreadsToStart = 1;
    }
    
    private void initializeSandboxParameters() {
        PluraRuntime.NUMBER_TIMES_SKIP_TS_WUS = 0;
        this.nodeServers.put("http://plura002.pluraserver.com", false);
        this.maximumNumberJarBytes = 31457280L;
        this.noWorkAppletThreshhold = Integer.MAX_VALUE;
        this.minimumNoWorkThreshhold = 10;
        this.numberMsSleepNoWork = 2000L;
        this.numberMsSleepException = 2000L;
        this.NUMBER_DISKS_DATA_SERVER = 1;
        this.NODE_SERVER_DISK_NAME = "data";
        this.DATA_BASE_DIRECTORY = "/etc/plura/data/";
        this.PLURA_DATA_SVR = "http://plura002.pluraserver.com";
        this.PLURA_SVR = "http://plura002.pluraserver.com";
        this.PROTECTION_DOMAIN = this.PLURA_SVR;
        this.PLURA_JAR_LOCATION = String.valueOf(this.PLURA_SVR) + "/plura/java/plura.jar";
        this.PLURA_CLIENT_APPLET_LOCATION = "/Plura/PluraClient";
        this.PLURA_VERSION_SVR_LOCATION = "/Plura/PluraClientVersion?nodeType=";
        this.PLURA_TIME_SENSITIVE_WUS_LOCATION = "/Plura/PluraClientTimeSensitiveWork";
        this.stdOutBaseLocation = "/var/www/stdout/";
    }
    
    private void initializeLiveParameters() {
        PluraRuntime.NUMBER_TIMES_SKIP_TS_WUS = 1;
        this.nodeServers.put("http://64.125.222.17", false);
        this.nodeServers.put("http://64.125.222.18", false);
        this.nodeServers.put("http://64.125.222.22", false);
        this.maximumNumberJarBytes = 31457280L;
        this.noWorkAppletThreshhold = 10;
        this.minimumNoWorkThreshhold = 2;
        this.numberMsSleepNoWork = 2000L;
        this.numberMsSleepException = 10000L;
        this.NUMBER_DISKS_DATA_SERVER = 2;
        this.NODE_SERVER_DISK_NAME = "data";
        this.DATA_BASE_DIRECTORY = "/etc/plura/data/";
        this.PLURA_DATA_SVR = "http://data.pluraserver.com";
        this.PLURA_SVR = "http://64.125.222.21";
        this.PROTECTION_DOMAIN = "http://www.pluraserver.com";
        this.PLURA_JAR_LOCATION = String.valueOf(this.PLURA_SVR) + "/plura/java/plura.jar";
        final String appletLocation = "/Plura20100617";
        this.PLURA_CLIENT_APPLET_LOCATION = String.valueOf(appletLocation) + "/PluraClient";
        this.PLURA_VERSION_SVR_LOCATION = String.valueOf(appletLocation) + "/PluraClientVersion?nodeType=";
        this.PLURA_TIME_SENSITIVE_WUS_LOCATION = String.valueOf(appletLocation) + "/PluraClientTimeSensitiveWork";
    }
    
    private void initializeTestParameters() {
        PluraRuntime.NUMBER_TIMES_SKIP_TS_WUS = 0;
        this.nodeServers.put("http://test.pluraserver.com", false);
        this.maximumNumberJarBytes = 31457280L;
        this.noWorkAppletThreshhold = 10;
        this.minimumNoWorkThreshhold = 10;
        this.numberMsSleepNoWork = 2000L;
        this.numberMsSleepException = 2000L;
        this.NUMBER_DISKS_DATA_SERVER = 1;
        this.NODE_SERVER_DISK_NAME = "data";
        this.DATA_BASE_DIRECTORY = "/etc/plura/data/";
        this.PLURA_DATA_SVR = "http://test.pluraserver.com";
        this.PLURA_SVR = "http://test.pluraserver.com";
        this.PROTECTION_DOMAIN = this.PLURA_SVR;
        this.PLURA_JAR_LOCATION = String.valueOf(this.PLURA_SVR) + "/plura/java/plura.jar";
        this.PLURA_CLIENT_APPLET_LOCATION = "/Plura/PluraClient";
        this.PLURA_VERSION_SVR_LOCATION = "/Plura/PluraClientVersion?nodeType=";
        this.PLURA_TIME_SENSITIVE_WUS_LOCATION = "/Plura/PluraClientTimeSensitiveWork";
    }
    
    public static void print(final String output, final boolean printInSandbox) {
    }
    
    public void setStdOut(final String location) throws FileNotFoundException, UnsupportedEncodingException {
        if (this.stdOutStream != null) {
            try {
                this.stdOutStream.close();
            }
            catch (IOException ex) {}
        }
        this.stdOutStream = new FileOutputStream(location, true);
        final PrintStream out = new PrintStream(this.stdOutStream, true, "UTF-8");
        System.setOut(out);
        System.setErr(out);
    }
    
    public void blockStdOut() {
    }
    
    public static String now() {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf.format(cal.getTime());
    }
    
    public synchronized void saveData(final HashMap<String, Object> currentData) throws IOException {
        final ArrayList<String> keysToRemove = new ArrayList<String>();
        this.allData.putAll(currentData);
        if (this.allData.size() > getInstance().getNumberPiecesData()) {
            int numberOver = this.allData.size() - this.numberPiecesData;
            for (final String key : this.allData.keySet()) {
                if (numberOver <= 0) {
                    break;
                }
                keysToRemove.add(key);
                --numberOver;
            }
        }
        for (final String key2 : keysToRemove) {
            this.allData.remove(key2);
        }
    }
    
    public synchronized void changeVersion() {
        this.pluraServerName = null;
        if (this.getCurrentVersionId() != null) {
            this.oldVersionId = this.getCurrentVersionId();
            this.setNumberTimesNoWork(0);
            final PluraClassLoaderVersion temp = this.currentPluraVersions.pop();
            this.unusedPluraVersions.put(temp.getVersionId(), temp);
            if (!PluraRuntime.ENVIRONMENT.equals(Environment.SANDBOX)) {
                for (final String server : this.nodeServers.keySet()) {
                    this.nodeServers.put(server, false);
                }
            }
            print("CHANGING VERSIONS.", false);
            String unused = "";
            for (final String s : this.unusedPluraVersions.keySet()) {
                final PluraClassLoaderVersion v = this.unusedPluraVersions.get(s);
                unused = String.valueOf(unused) + "; " + v.getVersionId();
            }
            print("UNUSED VERSIONS" + unused, false);
            String current = "";
            for (final PluraClassLoaderVersion v2 : this.currentPluraVersions) {
                current = String.valueOf(current) + "; " + v2.getVersionId();
            }
            print("CURRENT VERSIONS" + current, false);
        }
        if (this.plura != null) {
            this.plura.stopPluraThreads(true, true);
        }
        this.initialize(null, null, this.affiliateId, this.clientId, this.pluraServerName, this.percentageCPU, this.bandwidthPercent, this.isStopped, this.numberPluraThreads, null);
        this.startPluraThreads();
    }
    
    public synchronized void logNoWork() {
        if (this.currentPluraVersions != null && this.currentPluraVersions.size() > 1) {
            this.setNumberTimesNoWork(this.getNumberTimesNoWork() + 1);
            if (this.getNumberTimesNoWork() >= this.getNoWorkThreshhold()) {
                final boolean found = false;
                if (!found) {
                    print("CALLING CHANGE VERSION", false);
                    this.changeVersion();
                }
            }
        }
    }
    
    public synchronized void startPluraThreads() {
        if (this.startPluraThread.isAlive()) {
            this.startPluraThread.stopThread();
            this.startPluraThread = null;
        }
        (this.startPluraThread = new StartPluraThread()).start();
    }
    
    public synchronized void stopPluraThreads(final boolean keepThreadsAlive) {
        if (this.stdOutStream != null) {
            try {
                this.stdOutStream.close();
            }
            catch (IOException ex) {}
        }
        if (this.plura != null) {
            this.plura.stopPluraThreads(keepThreadsAlive, true);
        }
    }
    
    public synchronized boolean pluraIsStopped() {
        return this.plura == null || this.plura.pluraIsStopped();
    }
    
    public PluraRuntimeClassLoader getNewClassloader(final URL[] urls, final Map<URL, byte[]> bytes, final ClassLoader parentClassLoader) throws IOException {
        PluraRuntimeClassLoader currentClassLoader;
        if (parentClassLoader != null) {
            currentClassLoader = new PluraRuntimeClassLoader(urls, bytes, this.PLURA_SVR, this.PROTECTION_DOMAIN, parentClassLoader);
        }
        else {
            currentClassLoader = new PluraRuntimeClassLoader(urls, bytes, this.PLURA_SVR, this.PROTECTION_DOMAIN);
        }
        return currentClassLoader;
    }
    
    private PluraClassLoaderVersion initializeBasePluraClassLoader() throws IOException {
        final List<URL> urls = new ArrayList<URL>();
        urls.add(new URL(this.PLURA_JAR_LOCATION));
        return new PluraClassLoaderVersion(urls, null, Integer.MAX_VALUE);
    }
    
    public long accountForBytesDownloaded() {
        long bytes = 1L;
        synchronized (this.unaccountedForJarBytes) {
            bytes = this.unaccountedForJarBytes;
        }
        // monitorexit(this.unaccountedForJarBytes = Long.valueOf(0L))
        return bytes;
    }
    
    private Map<URL, byte[]> getPreviouslyLoadedJarBytesForURLs(final List<URL> urls) throws IOException {
        final Map<URL, byte[]> bytes = new HashMap<URL, byte[]>();
        if (this.currentClassLoader != null) {
            for (final URL url : urls) {
                if (this.jarBytes.get(url) != null) {
                    bytes.put(url, this.jarBytes.get(url));
                }
            }
        }
        return bytes;
    }
    
    public void removeExcessJARs(final List<URL> urls) {
        while (this.totalNumberJarBytes > this.maximumNumberJarBytes) {
            URL smallestJARUrl = null;
            long smallestJARBytes = Long.MAX_VALUE;
            for (final URL key : this.jarBytes.keySet()) {
                if (!urls.contains(key) && this.jarBytes.get(key).length < smallestJARBytes) {
                    smallestJARBytes = this.jarBytes.get(key).length;
                    smallestJARUrl = key;
                }
            }
            if (smallestJARUrl == null) {
                break;
            }
            print("REMOVING JAR W/ URL " + smallestJARUrl + " AND # BYTES " + smallestJARBytes, false);
            this.jarBytes.remove(smallestJARUrl);
            this.totalNumberJarBytes -= smallestJARBytes;
        }
    }
    
    public String getPluraServer() throws IOException {
        if (!PluraRuntime.ENVIRONMENT.equals(Environment.LIVE)) {
            return this.PLURA_SVR;
        }
        final String server = PluraHttpConnectionUtility.httpRequestByDomain(this.PLURA_SVR, 80, "/PluraDistribution/PluraClientServer", "", null, HttpMethod.GET).getResponseBodyAsTrimmedUTF8String();
        if (server.equals("")) {
            throw new IOException("Invalid server name.");
        }
        try {
            new URL(server);
        }
        catch (Throwable e) {
            throw new IOException("Invalid server name.");
        }
        return server;
    }
    
    public void getVersion(final NodeType nodeType, String response) throws IOException, PluraIOException {
        this.currentNodeType = nodeType;
        if (this.plura != null) {
            this.plura.stopComputeInAllPluraThreads();
            this.plura.stopPluraThreads(false, false);
            while (!this.plura.pluraIsStopped()) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            this.plura = null;
        }
        this.currentClassLoader = null;
        if (this.currentPluraVersions == null || this.currentPluraVersions.isEmpty()) {
            (this.currentPluraVersions = new Stack<PluraClassLoaderVersion>()).push(this.initializeBasePluraClassLoader());
        }
        if (response == null) {
            String uri = String.valueOf(this.PLURA_VERSION_SVR_LOCATION) + nodeType.toString() + "&heapMB=" + HeapSpace.getHeapSpace(this.getAvailableHeapSpaceMB());
            if (this.getCurrentVersionId() != null) {
                uri = String.valueOf(uri) + "&parentId=" + this.getCurrentVersionId();
            }
            print("VERSION REQUEST URI. \n" + uri, false);
            response = PluraHttpConnectionUtility.httpRequestByIP(this.pluraServerName, 80, uri, "", null, HttpMethod.GET).getResponseBodyAsTrimmedUTF8String();
        }
        if (response.contains(new XmlWriter().error().GetXml()) || response.equals("")) {
            throw new IOException();
        }
        print("VERSION RESPONSE. \n" + response, false);
        final String[] versions = response.split("\n");
        final List<PluraClassLoaderVersion> versionObjs = new ArrayList<PluraClassLoaderVersion>();
        String[] array;
        for (int length = (array = versions).length, k = 0; k < length; ++k) {
            final String version = array[k];
            final String[] components = version.split(":");
            if (components != null && components.length > 0) {
                final String versionId = components[0];
                if (this.badVersions.contains(versionId)) {
                    throw new IOException("This version is bad!!  Get another one, please.");
                }
                if (!versionId.equals(this.getCurrentVersionId())) {
                    final WorkVersion vers = new WorkVersion();
                    vers.setId(UUID.fromString(versionId));
                    for (int i = 1; i < components.length; ++i) {
                        final String[] executableComponents = components[i].split(",");
                        final Executable exec = new Executable();
                        exec.setId(UUID.fromString(executableComponents[0]));
                        exec.setRestricted(Boolean.parseBoolean(executableComponents[1]));
                        exec.setLazyLoad(Boolean.parseBoolean(executableComponents[2]));
                        vers.getExecutables().add(exec);
                    }
                    PluraClassLoaderVersion pclVers = this.unusedPluraVersions.get(vers.getId().toString());
                    final List<URL> pclUrls = new ArrayList<URL>();
                    if (pclVers != null) {
                        this.unusedPluraVersions.remove(vers.getId().toString());
                    }
                    else {
                        for (final Executable exec : vers.getExecutables()) {
                            pclUrls.add(new URL(String.valueOf(this.PLURA_SVR) + FilePathUtility.getJarFileLocationString(exec.getId().toString(), exec.isRestricted())));
                        }
                        pclVers = new PluraClassLoaderVersion(pclUrls, vers.getId().toString(), this.minimumNoWorkThreshhold);
                    }
                    versionObjs.add(pclVers);
                }
            }
        }
        for (int j = versionObjs.size() - 1; j >= 0; --j) {
            this.currentPluraVersions.push(versionObjs.get(j));
        }
        final List<URL> urls = new ArrayList<URL>();
        for (final PluraClassLoaderVersion vers2 : this.currentPluraVersions) {
            urls.addAll(vers2.getUrls());
        }
        try {
            this.currentClassLoader = this.getNewClassloader(urls.toArray(new URL[0]), this.getPreviouslyLoadedJarBytesForURLs(urls), this.parentClassLoader);
        }
        catch (IOException e) {
            throw new PluraIOException();
        }
        for (final PluraClassLoaderVersion v : this.currentPluraVersions) {
            for (final URL url : v.getUrls()) {
                if (this.jarBytes.get(url) == null) {
                    final byte[] jar = this.currentClassLoader.getBytesInJars().get(url).getJarBytes();
                    this.jarBytes.put(url, jar);
                    this.totalNumberJarBytes += jar.length;
                    synchronized (this.unaccountedForJarBytes) {
                    }
                    // monitorexit(this.unaccountedForJarBytes = Long.valueOf(this.unaccountedForJarBytes.longValue() + (long)jar.length))
                    v.incrementNoWorkThreshold(this.currentClassLoader.getBytesInJars().get(url).getTimeToLoad());
                }
            }
        }
        this.currentClassLoader.setBytesInJars(new HashMap<URL, JarURL>());
        this.removeExcessJARs(urls);
        if (PluraRuntime.ENVIRONMENT.equals(Environment.SANDBOX)) {
            this.setStdOut(String.valueOf(this.stdOutBaseLocation) + this.getCurrentVersionId());
        }
        else {
            this.blockStdOut();
        }
        print("AFTER NEW VERSION GET.", false);
        String unused = "";
        for (final String s : this.unusedPluraVersions.keySet()) {
            final PluraClassLoaderVersion v2 = this.unusedPluraVersions.get(s);
            unused = String.valueOf(unused) + "; " + v2.getVersionId();
        }
        print("UNUSED VERSIONS" + unused, false);
        String current = "";
        for (final PluraClassLoaderVersion v3 : this.currentPluraVersions) {
            current = String.valueOf(current) + "; " + v3.getVersionId();
        }
        print("CURRENT VERSIONS" + current, false);
    }
    
    public synchronized int getWorkUnitsCompletedByClient(final String clientId, final Calendar date) throws PluraIntervalException, PluraIOException, IOException {
        if (getInstance().getTimeClientWorkLastRequested() == null) {
            getInstance().setTimeClientWorkLastRequested(Calendar.getInstance());
        }
        else if (0L > System.currentTimeMillis() - getInstance().getTimeClientWorkLastRequested().getTimeInMillis()) {
            throw new PluraIntervalException();
        }
        try {
            return Integer.parseInt(PluraHttpConnectionUtility.httpRequestByIP("http://archive.pluraserver.com", 8080, "/PluraArchive/PluraArchivedWorkUnits?affiliate=" + getInstance().getAffiliateId() + "&" + "client" + "=" + clientId + "&" + "date" + "=" + new SimpleDateFormat("MM-dd-yyyy").format(date.getTime()), "", HttpMethod.GET).getResponseBodyAsTrimmedUTF8String());
        }
        catch (Exception e) {
            throw new PluraIOException();
        }
    }
    
    public synchronized double getAvailableBandwidth() {
        if (PluraRuntime.ENVIRONMENT.equals(Environment.SANDBOX)) {
            return 9.223372036854776E18;
        }
        String response = "";
        do {
            try {
                String params = "";
                boolean max = false;
                if (this.maxBandwidthKb == null) {
                    max = true;
                    params = "?max=1";
                }
                else if (this.maxBandwidthKb == -1L) {
                    return -1.0;
                }
                response = PluraHttpConnectionUtility.httpRequestByDomain("http://bandwidth.pluraserver.com", 80, "/PluraBandwidthCap/BandwidthCap" + params, "", null, HttpMethod.GET).getResponseBodyAsTrimmedUTF8String();
                if (response.equals("-5")) {
                    print("Waiting 15 seconds to get bandwidth max.", false);
                    try {
                        Thread.sleep(15000L);
                    }
                    catch (InterruptedException ex) {}
                }
                else if (response.equals("")) {
                    print("Error getting bandwidth max. Sleep 1 minute.", false);
                    try {
                        Thread.sleep(60000L);
                    }
                    catch (InterruptedException ex2) {}
                }
                else if (max) {
                    final String[] responses = response.split(",");
                    this.maxBandwidthKb = Long.parseLong(responses[1]);
                    if (this.maxBandwidthKb == -1L) {
                        return -1.0;
                    }
                    return this.maxBandwidthKb - Double.parseDouble(responses[0]);
                }
            }
            catch (Throwable e) {
                print("Error getting bandwidth max. Sleep 1 minute.", false);
                try {
                    Thread.sleep(60000L);
                }
                catch (InterruptedException ex3) {}
            }
        } while (response.equals("") || response.equals("-5"));
        print("Got available bandwidth: " + (this.maxBandwidthKb - Double.parseDouble(response)), false);
        return this.maxBandwidthKb - Double.parseDouble(response);
    }
    
    public static void sendErrorMessage(final String text, final String stacktrace) {
        try {
            PluraHttpConnectionUtility.httpRequestByDomain("http://error.pluraserver.com", 80, "/PluraErrorLog/PluraErrorLog", String.valueOf(BitConversionUtility.convert8bitTo6bit(text.getBytes())) + "," + BitConversionUtility.convert8bitTo6bit(stacktrace.getBytes()) + ",1", null, HttpMethod.POST);
        }
        catch (IOException ex) {}
    }
    
    public HashMap<String, Object> getAllData() {
        return this.allData;
    }
    
    public Map<String, Boolean> getNodeServers() {
        return this.nodeServers;
    }
    
    public void resetAllCurrentPluraVersions(final boolean error) {
        if (error && this.getCurrentVersionId() != null) {
            getInstance().getBadVersions().add(this.getCurrentVersionId());
        }
        while (!this.currentPluraVersions.isEmpty()) {
            this.setNumberTimesNoWork(0);
            final PluraClassLoaderVersion temp = this.currentPluraVersions.pop();
            this.unusedPluraVersions.put(temp.getVersionId(), temp);
        }
    }
    
    public void setAllData(final HashMap<String, Object> allData) {
        this.allData = allData;
    }
    
    public int getNumberPiecesData() {
        return this.numberPiecesData;
    }
    
    public void setNumberPiecesData(final int numberPiecesData) {
        this.numberPiecesData = numberPiecesData;
    }
    
    public PluraClassLoaderVersion getCurrentVersion() {
        if (this.currentPluraVersions == null || this.currentPluraVersions.size() == 0) {
            return null;
        }
        return this.currentPluraVersions.peek();
    }
    
    public String getCurrentVersionId() {
        if (this.currentPluraVersions == null || this.currentPluraVersions.size() == 0) {
            return null;
        }
        return this.currentPluraVersions.peek().getVersionId();
    }
    
    public String getAffiliateId() {
        return this.affiliateId;
    }
    
    public void setAffiliateId(final String affiliateId) {
        this.affiliateId = affiliateId;
    }
    
    public String getClientId() {
        return this.clientId.replace(" ", "");
    }
    
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }
    
    public String getPluraServerName() {
        return this.pluraServerName;
    }
    
    public void setPluraServerName(final String pluraServerName) {
        this.pluraServerName = pluraServerName;
    }
    
    public double getPercentageCPU() {
        return this.percentageCPU;
    }
    
    public void setPercentageCPU(final double percentageCPU) {
        this.percentageCPU = percentageCPU;
    }
    
    public double getBandwidthPercent() {
        return this.bandwidthPercent;
    }
    
    public void setBandwidthPercent(final double bandwidthPercent) {
        this.bandwidthPercent = bandwidthPercent;
    }
    
    public double getPercentUsed() {
        return this.percentUsed;
    }
    
    public void setPercentUsed(final double percentUsed) {
        this.percentUsed = percentUsed;
    }
    
    public boolean isStopped() {
        return this.isStopped;
    }
    
    public void setStopped(final boolean isStopped) {
        this.isStopped = isStopped;
    }
    
    public Calendar getTimeClientWorkLastRequested() {
        return this.timeClientWorkLastRequested;
    }
    
    public void setTimeClientWorkLastRequested(final Calendar timeClientWorkLastRequested) {
        this.timeClientWorkLastRequested = timeClientWorkLastRequested;
    }
    
    public double getSleepPercent() {
        return this.sleepPercent;
    }
    
    public void setSleepPercent(final double sleepPercent) {
        this.sleepPercent = sleepPercent;
    }
    
    public Applet getApplet() {
        return this.applet;
    }
    
    public void setApplet(final Applet applet) {
        this.applet = applet;
    }
    
    public int getNumberPluraThreads() {
        return this.numberPluraThreads;
    }
    
    public void setNumberPluraThreads(final int numberPluraThreads) {
        this.numberPluraThreads = numberPluraThreads;
    }
    
    public int getNumberThreadsToStart() {
        return this.numberThreadsToStart;
    }
    
    public void setNumberThreadsToStart(final int numberThreadsToStart) {
        this.numberThreadsToStart = numberThreadsToStart;
    }
    
    public int getNumberTimesNoWork() {
        if (this.currentPluraVersions == null || this.currentPluraVersions.size() == 0) {
            return 0;
        }
        return this.currentPluraVersions.peek().getNumberTimesNoWork();
    }
    
    public void setNumberTimesNoWork(final int number) {
        if (this.currentPluraVersions != null && this.currentPluraVersions.size() > 0) {
            this.currentPluraVersions.peek().setNumberTimesNoWork(number);
        }
    }
    
    public int getNumberTimesNoWorkApplet() {
        return this.numberTimesNoWorkApplet;
    }
    
    public void setNumberTimesNoWorkApplet(final int numberTimesNoWorkApplet) {
        this.numberTimesNoWorkApplet = numberTimesNoWorkApplet;
    }
    
    public int getNoWorkThreshhold() {
        if (this.currentPluraVersions == null || this.currentPluraVersions.size() == 0) {
            return 0;
        }
        return this.currentPluraVersions.peek().getNoWorkThreshold();
    }
    
    public int getNoWorkAppletThreshhold() {
        return this.noWorkAppletThreshhold;
    }
    
    public Boolean usesLimitedAPI() {
        return this.usesLimitedAPI;
    }
    
    public ClassLoader getCurrentClassLoader() {
        return this.currentClassLoader;
    }
    
    public StartPluraThread getStartPluraThread() {
        return this.startPluraThread;
    }
    
    public void setStartPluraThread(final StartPluraThread startPluraThread) {
        this.startPluraThread = startPluraThread;
    }
    
    public IStartPlura getPlura() {
        return this.plura;
    }
    
    public void setPlura(final IStartPlura plura) {
        this.plura = plura;
    }
    
    public List<String> getBadVersions() {
        return this.badVersions;
    }
    
    public void setBadVersions(final List<String> badVersions) {
        this.badVersions = badVersions;
    }
    
    public int getAvailableHeapSpaceMB() {
        return this.availableHeapSpaceMB;
    }
    
    public void setAvailableHeapSpaceMB(final int availableHeapSpaceMB) {
        this.availableHeapSpaceMB = availableHeapSpaceMB;
    }
    
    public String getOldVersionId() {
        return this.oldVersionId;
    }
    
    public int getNumberTimesSkippedTimeSensitiveWUs() {
        return this.numberTimesSkippedTimeSensitiveWUs;
    }
    
    public void setNumberTimesSkippedTimeSensitiveWUs(final int numberTimesSkippedTimeSensitiveWUs) {
        this.numberTimesSkippedTimeSensitiveWUs = numberTimesSkippedTimeSensitiveWUs;
    }
    
    public NodeType getCurrentNodeType() {
        return this.currentNodeType;
    }
    
    public void setCurrentNodeType(final NodeType currentNodeType) {
        this.currentNodeType = currentNodeType;
    }
    
    class NowhereOutputStream extends PrintStream
    {
        @Override
        public void print(final boolean b) {
        }
        
        @Override
        public void print(final char c) {
        }
        
        @Override
        public void print(final char[] s) {
        }
        
        @Override
        public void print(final double d) {
        }
        
        @Override
        public void print(final float f) {
        }
        
        @Override
        public void print(final int i) {
        }
        
        @Override
        public void print(final long l) {
        }
        
        @Override
        public void print(final Object obj) {
        }
        
        @Override
        public void print(final String s) {
        }
        
        @Override
        public void println() {
        }
        
        @Override
        public void println(final boolean x) {
        }
        
        @Override
        public void println(final char x) {
        }
        
        @Override
        public void println(final char[] x) {
        }
        
        @Override
        public void println(final double x) {
        }
        
        @Override
        public void println(final float x) {
        }
        
        @Override
        public void println(final int x) {
        }
        
        @Override
        public void println(final long x) {
        }
        
        @Override
        public void println(final Object x) {
        }
        
        @Override
        public void println(final String x) {
        }
        
        @Override
        public void write(final byte[] buf, final int off, final int len) {
        }
        
        @Override
        public void write(final int b) {
        }
        
        public NowhereOutputStream(final OutputStream out) {
            super(new ByteArrayOutputStream());
        }
    }
}
