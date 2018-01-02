// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.tz;

import java.io.DataInputStream;
import java.util.Comparator;
import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.TreeSet;
import java.util.Set;
import java.lang.ref.SoftReference;
import org.joda.time.DateTimeZone;
import java.io.IOException;
import java.util.Map;
import java.io.File;

public class ZoneInfoProvider implements Provider
{
    private final File iFileDir;
    private final String iResourcePath;
    private final ClassLoader iLoader;
    private final Map iZoneInfoMap;
    
    public ZoneInfoProvider(final File iFileDir) throws IOException {
        if (iFileDir == null) {
            throw new IllegalArgumentException("No file directory provided");
        }
        if (!iFileDir.exists()) {
            throw new IOException("File directory doesn't exist: " + iFileDir);
        }
        if (!iFileDir.isDirectory()) {
            throw new IOException("File doesn't refer to a directory: " + iFileDir);
        }
        this.iFileDir = iFileDir;
        this.iResourcePath = null;
        this.iLoader = null;
        this.iZoneInfoMap = loadZoneInfoMap(this.openResource("ZoneInfoMap"));
    }
    
    public ZoneInfoProvider(final String s) throws IOException {
        this(s, null, false);
    }
    
    public ZoneInfoProvider(final String s, final ClassLoader classLoader) throws IOException {
        this(s, classLoader, true);
    }
    
    private ZoneInfoProvider(String string, ClassLoader classLoader, final boolean b) throws IOException {
        if (string == null) {
            throw new IllegalArgumentException("No resource path provided");
        }
        if (!string.endsWith("/")) {
            string += '/';
        }
        this.iFileDir = null;
        this.iResourcePath = string;
        if (classLoader == null && !b) {
            classLoader = this.getClass().getClassLoader();
        }
        this.iLoader = classLoader;
        this.iZoneInfoMap = loadZoneInfoMap(this.openResource("ZoneInfoMap"));
    }
    
    public synchronized DateTimeZone getZone(final String s) {
        if (s == null) {
            return null;
        }
        final SoftReference<DateTimeZone> value = this.iZoneInfoMap.get(s);
        if (value == null) {
            return null;
        }
        if (s.equals(value)) {
            return this.loadZoneData(s);
        }
        if (!(value instanceof SoftReference)) {
            return this.getZone((String)value);
        }
        final DateTimeZone dateTimeZone = value.get();
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        return this.loadZoneData(s);
    }
    
    public synchronized Set getAvailableIDs() {
        return new TreeSet(this.iZoneInfoMap.keySet());
    }
    
    protected void uncaughtException(final Exception ex) {
        final Thread currentThread = Thread.currentThread();
        currentThread.getThreadGroup().uncaughtException(currentThread, ex);
    }
    
    private InputStream openResource(final String s) throws IOException {
        InputStream inputStream;
        if (this.iFileDir != null) {
            inputStream = new FileInputStream(new File(this.iFileDir, s));
        }
        else {
            final String concat = this.iResourcePath.concat(s);
            if (this.iLoader != null) {
                inputStream = this.iLoader.getResourceAsStream(concat);
            }
            else {
                inputStream = ClassLoader.getSystemResourceAsStream(concat);
            }
            if (inputStream == null) {
                throw new IOException(new StringBuffer(40).append("Resource not found: \"").append(concat).append("\" ClassLoader: ").append((this.iLoader != null) ? this.iLoader.toString() : "system").toString());
            }
        }
        return inputStream;
    }
    
    private DateTimeZone loadZoneData(final String s) {
        InputStream openResource = null;
        try {
            openResource = this.openResource(s);
            final DateTimeZone from = DateTimeZoneBuilder.readFrom(openResource, s);
            this.iZoneInfoMap.put(s, new SoftReference<DateTimeZone>(from));
            return from;
        }
        catch (IOException ex) {
            this.uncaughtException(ex);
            this.iZoneInfoMap.remove(s);
            return null;
        }
        finally {
            try {
                if (openResource != null) {
                    openResource.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    private static Map loadZoneInfoMap(final InputStream inputStream) throws IOException {
        final TreeMap<String, SoftReference<DateTimeZone>> treeMap = new TreeMap<String, SoftReference<DateTimeZone>>(String.CASE_INSENSITIVE_ORDER);
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            readZoneInfoMap(dataInputStream, treeMap);
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (IOException ex) {}
        }
        treeMap.put("UTC", new SoftReference<DateTimeZone>(DateTimeZone.UTC));
        return treeMap;
    }
    
    private static void readZoneInfoMap(final DataInputStream dataInputStream, final Map map) throws IOException {
        final int unsignedShort = dataInputStream.readUnsignedShort();
        final String[] array = new String[unsignedShort];
        for (int i = 0; i < unsignedShort; ++i) {
            array[i] = dataInputStream.readUTF().intern();
        }
        for (int unsignedShort2 = dataInputStream.readUnsignedShort(), j = 0; j < unsignedShort2; ++j) {
            try {
                map.put(array[dataInputStream.readUnsignedShort()], array[dataInputStream.readUnsignedShort()]);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }
}
