// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.util.Enumeration;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.net.URLConnection;
import java.util.zip.ZipInputStream;
import java.util.zip.Checksum;
import java.util.zip.CheckedInputStream;
import java.util.zip.CRC32;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import com.objectbox.runner.util.JBLogger;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import com.objectbox.runner.beans.DownloadView;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.util.Hashtable;
import java.io.Serializable;

public class JBURLClassloader extends ClassLoader implements Serializable
{
    private String host;
    private String protocol;
    private int port;
    private Class ourClass;
    private Hashtable classList;
    private Hashtable bytecodeList;
    private Hashtable resourceList;
    private Hashtable resourceLoaded;
    private int vector_counter;
    private String directory;
    private String server;
    private String className;
    private String UnresolvedURL;
    private String temporaryURL;
    private boolean flagClass;
    static int counter1;
    private String codebase;
    private String jarfilename;
    private boolean firstrun;
    private boolean jarloaded;
    private String rootclass;
    private String namespace;
    private boolean treeresolved;
    private boolean cacheOn;
    private static final String bytecodecachefilename = "state.ser";
    private boolean checklastmodified;
    private String filesep;
    private String cachedirectory;
    protected transient StateChangeListener aStateChangeListener;
    private Object owner;
    private boolean caching;
    private String securityID;
    private boolean is_saved;
    
    public JBURLClassloader(final String cachedirectory) {
        this.ourClass = null;
        this.classList = new Hashtable();
        this.bytecodeList = new Hashtable();
        this.resourceList = new Hashtable();
        this.resourceLoaded = new Hashtable();
        this.directory = null;
        this.server = null;
        this.className = null;
        this.UnresolvedURL = null;
        this.temporaryURL = null;
        this.firstrun = true;
        this.jarloaded = false;
        this.namespace = "";
        this.treeresolved = false;
        this.cacheOn = true;
        this.checklastmodified = true;
        this.filesep = System.getProperty("file.separator");
        this.cachedirectory = ".";
        this.aStateChangeListener = null;
        this.caching = false;
        this.securityID = "";
        this.is_saved = false;
        this.setCachedirectory(cachedirectory);
        if (this.classList == null) {
            this.classList = new Hashtable();
        }
    }
    
    public void addMetaData(final String s, final Object o) {
        this.resourceList.put(s, o);
    }
    
    public boolean addResource(final String s, final byte[] array) {
        this.resourceList.put(s, array);
        this.log(new String(array));
        return true;
    }
    
    public void addStateChangeListener(final StateChangeListener stateChangeListener) {
        this.aStateChangeListener = StateChangeEventMulticaster.add(this.aStateChangeListener, stateChangeListener);
    }
    
    private synchronized Class createClassFromByteCodes(final String s, final byte[] array, final boolean b) {
        Class<?> defineClass = null;
        try {
            this.log("defineClass " + s);
            defineClass = this.defineClass(s, array, 0, array.length);
            this.classList.put(String.valueOf(this.namespace) + s, defineClass);
            if (b) {
                this.resolveClass(defineClass);
                this.log("resolveClass " + s);
            }
            else {
                this.log("defineClass not resolved" + s);
            }
        }
        catch (ClassFormatError classFormatError) {
            this.log("Could not create class " + s + " " + classFormatError);
            return defineClass;
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return defineClass;
        }
        return defineClass;
    }
    
    public synchronized void dispose() {
        this.nullHashtable(this.classList);
        this.nullHashtable(this.bytecodeList);
        this.nullHashtable(this.resourceList);
        this.nullHashtable(this.resourceLoaded);
    }
    
    private byte[] downloadByteCodesFromURL(final String s, final DataInputStream dataInputStream) {
        try {
            this.log("Loading classbytes for " + s + " from URL");
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    byteArrayOutputStream.write(dataInputStream.readByte());
                    DownloadView.addBytes(1L);
                }
                catch (IOException ex) {
                    break;
                }
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            this.bytecodeList.put(String.valueOf(this.namespace) + s, byteArray);
            return byteArray;
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return null;
        }
    }
    
    protected void finalize() throws Throwable {
        this.log("classloader in finalize");
        if (!this.is_saved) {
            this.saveme();
            this.dispose();
        }
    }
    
    protected void fireOnChange(final StateChangeEvent stateChangeEvent) {
        if (this.aStateChangeListener == null) {
            return;
        }
        this.aStateChangeListener.onChange(stateChangeEvent);
    }
    
    public String getCachedirectory() {
        return this.cachedirectory;
    }
    
    public boolean getCacheOn() {
        return this.cacheOn;
    }
    
    public boolean getCaching() {
        return this.caching;
    }
    
    public boolean getChecklastmodified() {
        return this.checklastmodified;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getCodebase() {
        return this.codebase;
    }
    
    public Object getMetaData(final String s) {
        return this.resourceList.get(s);
    }
    
    public Object getOwner() {
        return this.owner;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getProtocol() {
        return this.protocol;
    }
    
    public URL getResource(final String s) {
        try {
            final URL resource = this.getClass().getResource(s);
            if (resource != null) {
                this.log("Loading URL resource from system " + s);
                return resource;
            }
            this.log("Loading URL resource " + s);
            final URL url = new URL(this.makeValidUrl(s));
            this.log(url.toString());
            return url;
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return null;
        }
    }
    
    public InputStream getResourceAsStream(final String s) {
        try {
            if (this.resourceList.get(s) != null) {
                return new ByteArrayInputStream(this.resourceList.get(s));
            }
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream != null) {
                this.log("Loading resources from stream from system :" + s);
                return resourceAsStream;
            }
            this.log("Loading resources from stream:" + s);
            final URL url = new URL(this.makeValidUrl(s));
            this.log(url.toString());
            return url.openStream();
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return null;
        }
    }
    
    public String getRootclass() {
        return this.rootclass;
    }
    
    public String getSecurityID() {
        return this.securityID;
    }
    
    public String getServer() {
        try {
            if (!this.codebase.startsWith("http") && !this.codebase.startsWith("file")) {
                return new URL(this.server).getHost();
            }
            return new URL(this.codebase).getHost();
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
            return null;
        }
    }
    
    public Class getTheClass() {
        return this.ourClass;
    }
    
    public final Class loadClass(final String s, final boolean b) throws ClassNotFoundException {
        Class<?> systemClass = null;
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex >= 0) {
                securityManager.checkPackageAccess(s.substring(0, lastIndex));
                securityManager.checkPackageDefinition(s.substring(0, lastIndex));
            }
        }
        this.log("loadClass => " + s);
        try {
            systemClass = this.findSystemClass(s);
            this.log("Resolved " + s + " locally");
            this.setState(3);
        }
        catch (ClassNotFoundException ex) {
            this.log("Resolving Class: " + s);
            systemClass = this.classList.get(s);
            if (systemClass == null) {
                try {
                    this.log("Resolving " + s + " remotely");
                    this.setState(2);
                    final Class loadClassName = this.loadClassName(s);
                    if (loadClassName == null) {
                        this.log("Could not find class " + s);
                        return null;
                    }
                    return loadClassName;
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    this.log(noClassDefFoundError.getMessage());
                    return null;
                }
                catch (Throwable t) {
                    this.log(t.getMessage());
                    return null;
                }
            }
        }
        catch (NoClassDefFoundError noClassDefFoundError2) {
            this.log(noClassDefFoundError2.getMessage());
            return systemClass;
        }
        catch (Throwable t2) {
            this.log(t2.getMessage());
            return systemClass;
        }
        return systemClass;
    }
    
    private Class loadClassFromCache(final String s) throws Exception {
        try {
            this.setState(3);
            if (this.classList.get(String.valueOf(this.namespace) + s) != null) {
                this.log("Loading class from class cache." + s);
                return this.classList.get(String.valueOf(this.namespace) + s);
            }
            this.log(String.valueOf(s) + " is not in class cache");
            if (this.bytecodeList.get(String.valueOf(this.namespace) + s) != null) {
                final byte[] array = this.bytecodeList.get(String.valueOf(this.namespace) + s);
                this.log("Loaded bytecodes from bytecode cache." + s);
                return this.createClassFromByteCodes(s, array, true);
            }
            this.log(String.valueOf(s) + " is not in bytecode cache");
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return null;
        }
        return null;
    }
    
    private Class loadClassFromHTTP(final String s) throws Exception {
        this.setState(2);
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        try {
            final String validUrl = this.makeValidUrl(String.valueOf(s.replace('.', '/')) + ".class");
            final URL url = new URL(validUrl);
            this.log("Retrieving class from:" + validUrl);
            this.port = ((url.getPort() == -1) ? 80 : url.getPort());
            this.protocol = url.getProtocol();
            inputStream = ((HttpURLConnection)url.openConnection()).getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            final byte[] downloadByteCodesFromURL = this.downloadByteCodesFromURL(s, dataInputStream);
            dataInputStream.close();
            inputStream.close();
            inputStream = null;
            System.gc();
            final Class classFromByteCodes = this.createClassFromByteCodes(s, downloadByteCodesFromURL, true);
            this.log("Loaded bytecodes from HTTP server " + this.server);
            return classFromByteCodes;
        }
        catch (Throwable t2) {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                }
            }
            catch (Throwable t) {
                this.log(t.getMessage());
                return null;
            }
            this.ourClass = null;
            this.log("Problems Defining Network Class " + t2);
            return null;
        }
    }
    
    private Class loadClassName(final String s) {
        this.log("loadClassName " + s);
        try {
            return this.resolveClass(s);
        }
        catch (Throwable t) {
            this.log(t.getMessage());
            this.rethrowException(t);
            return null;
        }
    }
    
    private boolean loadJARArchive(final String s) {
        if (this.resourceLoaded.get(s) != null) {
            return true;
        }
        JBLogger.log("\nLoading jar archive " + s);
        String substring = "";
        try {
            final String validUrl = this.makeValidUrl(s);
            final String string = validUrl.hashCode() + "_" + this.rootclass + ".jar";
            final File file = new File(String.valueOf(this.cachedirectory) + this.filesep + string);
            final long lastModified = file.lastModified();
            final long length = file.length();
            JBLogger.log("Local file lastmodified:" + lastModified);
            if (!file.exists() || this.getChecklastmodified()) {
                final URL url = new URL(validUrl);
                this.log(url.toString());
                final URLConnection openConnection = url.openConnection();
                final long lastModified2 = openConnection.getLastModified();
                if (lastModified2 == 0L) {
                    JBLogger.log("Last modified not found.");
                }
                else {
                    JBLogger.log("URL file lastmodified:" + lastModified2);
                }
                JBLogger.log("content-type:" + openConnection.getContentType() + " content-length:" + openConnection.getContentLength());
                final long n = openConnection.getContentLength();
                if (!file.exists() || length != n) {
                    this.setState(2);
                    JBLogger.log("Copying file from server. " + length + "!=" + n);
                    JBLogger.log("URL file lastmodified:" + lastModified2);
                    final InputStream inputStream = openConnection.getInputStream();
                    final FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(this.cachedirectory) + this.filesep + string);
                    int read;
                    while ((read = inputStream.read()) != -1) {
                        fileOutputStream.write(read);
                    }
                    fileOutputStream.close();
                }
            }
            else {
                JBLogger.log("Using local copy of jar file archive.");
                this.setState(3);
                if (this.restoreme()) {
                    return true;
                }
            }
            final FileInputStream fileInputStream = new FileInputStream(String.valueOf(this.cachedirectory) + this.filesep + string);
            final CRC32 crc32 = new CRC32();
            final ZipInputStream zipInputStream = new ZipInputStream(new CheckedInputStream(fileInputStream, crc32));
            JBLogger.log("Checksum1=" + crc32.getValue());
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final int n2 = (int)nextEntry.getCompressedSize();
                if (this.bytecodeList.get(nextEntry.getName()) != null) {
                    break;
                }
                int read2;
                while ((read2 = zipInputStream.read()) != -1) {
                    byteArrayOutputStream.write(read2);
                    DownloadView.addBytes(1L);
                }
                final boolean endsWith = nextEntry.getName().endsWith(".class");
                if (endsWith) {
                    final String replace = nextEntry.getName().replace('/', '.');
                    substring = replace.substring(0, replace.lastIndexOf(46));
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                if (endsWith) {
                    JBLogger.log("Class: " + nextEntry.getName() + " " + n2 + " bytes");
                    this.bytecodeList.put(String.valueOf(this.namespace) + substring, byteArray);
                }
                else {
                    JBLogger.log("Resource: " + nextEntry.getName() + " " + n2 + " bytes");
                    this.resourceList.put(nextEntry.getName(), byteArray);
                }
            }
            zipInputStream.close();
            JBLogger.log("Checksum2=" + crc32.getValue());
            if (!this.validateChecksum(crc32.getValue(), s)) {
                return false;
            }
            this.resourceLoaded.put(s, s);
            JBLogger.log(this.resourceLoaded.toString());
            JBLogger.log(this.resourceList.toString());
        }
        catch (Throwable t) {
            JBLogger.log("\nError:" + t.toString());
            this.jarloaded = false;
        }
        return false;
    }
    
    public void loadJarfile(final boolean jarloaded) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.jarfilename, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.loadJARArchive(stringTokenizer.nextToken());
        }
        this.jarloaded = jarloaded;
    }
    
    private void log(final String s) {
        JBLogger.log(s);
    }
    
    public void makeFile(final String s, final byte[] array) {
        this.log("makeFile " + s);
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(this.cachedirectory) + this.filesep + "resources", s));
            fileOutputStream.write(array);
            fileOutputStream.close();
        }
        catch (Exception ex) {
            this.log(ex.getMessage());
        }
    }
    
    public boolean makeJARArchive(final String s) throws Throwable {
        final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(String.valueOf(this.cachedirectory) + this.filesep + "testjar.jar"));
        final Enumeration<String> keys = (Enumeration<String>)this.bytecodeList.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            final String string = String.valueOf(s2.replace('.', this.filesep.charAt(0))) + ".class";
            final byte[] array = this.bytecodeList.get(s2);
            this.makeFile(string, array);
            final ZipEntry zipEntry = new ZipEntry(string);
            zipEntry.setSize(array.length);
            zipEntry.setTime(System.currentTimeMillis());
            zipEntry.setMethod(8);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(array);
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        final Enumeration<String> keys2 = (Enumeration<String>)this.resourceList.keys();
        while (keys2.hasMoreElements()) {
            final String s3 = keys2.nextElement();
            final byte[] array2 = this.resourceList.get(s3);
            this.makeFile(s3, array2);
            final StringBuffer append = new StringBuffer(String.valueOf(s3)).append(" ");
            new String();
            this.log(append.append(String.valueOf(array2.length)).toString());
            final ZipEntry zipEntry2 = new ZipEntry(s3);
            zipEntry2.setSize(array2.length);
            zipEntry2.setTime(System.currentTimeMillis());
            zipEntry2.setComment("Test kommentar");
            zipEntry2.setMethod(8);
            zipOutputStream.putNextEntry(zipEntry2);
            zipOutputStream.write(array2);
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.setMethod(8);
        zipOutputStream.close();
        return false;
    }
    
    public String makeValidUrl(final String s) {
        if (s.startsWith("http:") || s.startsWith("file:")) {
            return s;
        }
        if (s.startsWith("/")) {
            return String.valueOf(this.server) + s;
        }
        String s2;
        if (!this.codebase.startsWith("http") && !this.codebase.startsWith("file")) {
            s2 = String.valueOf(this.server) + "/" + (this.codebase.startsWith(".") ? "" : this.codebase) + ((this.codebase.length() > 0) ? "/" : "") + s;
        }
        else {
            s2 = String.valueOf(this.codebase) + "/" + s;
        }
        return s2;
    }
    
    private void nullHashtable(final Hashtable hashtable) {
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            keys.nextElement();
        }
        hashtable.clear();
    }
    
    public void readCacheFromFile() {
        if (!this.restoreme()) {
            this.log("Creating new cache");
        }
        else {
            this.log("Using existing cache");
        }
    }
    
    public String removeSlashes(final String s) {
        if (s.length() == 0) {
            return s;
        }
        String s2 = s;
        if (s.startsWith("/")) {
            s2 = s.substring(1, s.length());
        }
        if (s2.endsWith("/")) {
            s2 = s2.substring(0, s2.lastIndexOf("/"));
        }
        return s2;
    }
    
    public void removeStateChangeListener(final StateChangeListener stateChangeListener) {
        this.aStateChangeListener = StateChangeEventMulticaster.remove(this.aStateChangeListener, stateChangeListener);
    }
    
    public Class resolveClass(String substring) throws IOException {
        try {
            this.setState(1);
            if (substring.endsWith(".class")) {
                substring = substring.substring(0, substring.lastIndexOf("."));
            }
            final Class loadClassFromCache = this.loadClassFromCache(substring);
            if (loadClassFromCache != null) {
                return loadClassFromCache;
            }
            return this.loadClassFromHTTP(substring);
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
            this.rethrowException(t);
            return null;
        }
    }
    
    public boolean restoreme() {
        try {
            this.caching = true;
            if (this.bytecodeList.size() > 0) {
                return true;
            }
            final File file = new File(String.valueOf(this.cachedirectory) + this.filesep + this.namespace.hashCode() + "_" + this.rootclass + "_" + "state.ser");
            final ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file), (int)file.length()));
            this.rootclass = (String)objectInputStream.readObject();
            this.bytecodeList = (Hashtable)objectInputStream.readObject();
            this.resourceList = (Hashtable)objectInputStream.readObject();
            this.resourceLoaded = (Hashtable)objectInputStream.readObject();
            this.log("Restored bytecodes\n" + this.bytecodeList);
            this.log("Restored resources\n" + this.resourceList);
            this.log("Restored resourcesloaded\n" + this.resourceLoaded);
            objectInputStream.close();
            return true;
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
            return false;
        }
        finally {
            this.caching = false;
        }
    }
    
    private void rethrowException(final Exception ex) {
    }
    
    private void rethrowException(final Throwable t) {
    }
    
    public void saveme() {
        try {
            this.caching = true;
            this.log("Saving bytecodes\n" + this.bytecodeList);
            final File file = new File(String.valueOf(this.cachedirectory) + this.filesep + this.namespace.hashCode() + "_" + this.rootclass + "_" + "state.ser");
            if (!file.exists()) {
                JBLogger.log("File does not exists ...");
            }
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file), 10240));
            objectOutputStream.writeObject(this.rootclass);
            objectOutputStream.writeObject(this.bytecodeList);
            objectOutputStream.writeObject(this.resourceList);
            objectOutputStream.writeObject(this.resourceLoaded);
            objectOutputStream.close();
            this.is_saved = true;
        }
        catch (IOException ex) {
            this.log(ex.getMessage());
        }
        catch (Throwable t) {
            this.log(t.getMessage());
        }
        finally {
            this.caching = false;
        }
        this.caching = false;
    }
    
    public void setCachedirectory(final String cachedirectory) {
        this.cachedirectory = cachedirectory;
        final File file = new File(this.cachedirectory);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
    }
    
    public void setCacheOn(final boolean cacheOn) {
        this.cacheOn = cacheOn;
    }
    
    private void setCaching(final boolean caching) {
        this.caching = caching;
    }
    
    public void setChecklastmodified(final boolean checklastmodified) {
        this.checklastmodified = checklastmodified;
    }
    
    public void setCodebase(final String s) {
        this.codebase = this.removeSlashes(s);
        this.namespace = String.valueOf(this.makeValidUrl("")) + "_";
    }
    
    public void setJarfile(final String jarfilename) {
        this.jarfilename = jarfilename;
        this.loadJarfile(true);
    }
    
    public void setOwner(final Object owner) {
        this.owner = owner;
    }
    
    public void setRootclass(final String rootclass) {
        this.rootclass = rootclass;
    }
    
    public void setSecurityID(final String securityID) {
        this.securityID = securityID;
    }
    
    public void setServer(final String s) {
        this.server = this.removeSlashes(s);
        this.namespace = String.valueOf(this.makeValidUrl("")) + "_";
    }
    
    public void setState(final int n) {
        this.fireOnChange(new StateChangeEvent(this, n));
    }
    
    public String toString() {
        return "objectBOX (c) 1999 JavaBee Classloader loading:" + this.codebase + "/" + this.rootclass;
    }
    
    public void uploadFile(final String s) {
        try {
            final Object o = null;
            final URLConnection openConnection = new URL(String.valueOf(this.server) + "/" + this.codebase).openConnection();
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-type", "application/octet-stream");
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(openConnection.getOutputStream());
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            final ObjectInputStream objectInputStream = new ObjectInputStream(openConnection.getInputStream());
            objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception ex) {
            this.log(ex.getMessage());
        }
    }
    
    public boolean validateChecksum(final long n, final String s) {
        return true;
    }
}
