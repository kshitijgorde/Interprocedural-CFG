// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.net.ServerSocket;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;

public class IdGenerator
{
    private static final Logger LOG;
    private static final String UNIQUE_STUB;
    private static int instanceCount;
    private static String hostName;
    private String seed;
    private AtomicLong sequence;
    private int length;
    
    public IdGenerator(final String prefix) {
        this.sequence = new AtomicLong(1L);
        synchronized (IdGenerator.UNIQUE_STUB) {
            this.seed = prefix + IdGenerator.UNIQUE_STUB + IdGenerator.instanceCount++ + ":";
            this.length = this.seed.length() + "9223372036854775807".length();
        }
    }
    
    public IdGenerator() {
        this("ID:" + IdGenerator.hostName);
    }
    
    public static String getHostName() {
        return IdGenerator.hostName;
    }
    
    public synchronized String generateId() {
        final StringBuilder sb = new StringBuilder(this.length);
        sb.append(this.seed);
        sb.append(this.sequence.getAndIncrement());
        return sb.toString();
    }
    
    public String generateSanitizedId() {
        String result = this.generateId();
        result = result.replace(':', '-');
        result = result.replace('_', '-');
        result = result.replace('.', '-');
        return result;
    }
    
    public static String getSeedFromId(final String id) {
        String result = id;
        if (id != null) {
            final int index = id.lastIndexOf(58);
            if (index > 0 && index + 1 < id.length()) {
                result = id.substring(0, index + 1);
            }
        }
        return result;
    }
    
    public static long getSequenceFromId(final String id) {
        long result = -1L;
        if (id != null) {
            final int index = id.lastIndexOf(58);
            if (index > 0 && index + 1 < id.length()) {
                final String numStr = id.substring(index + 1, id.length());
                result = Long.parseLong(numStr);
            }
        }
        return result;
    }
    
    public static int compare(final String id1, final String id2) {
        int result = -1;
        final String seed1 = getSeedFromId(id1);
        final String seed2 = getSeedFromId(id2);
        if (seed1 != null && seed2 != null) {
            result = seed1.compareTo(seed2);
            if (result == 0) {
                final long count1 = getSequenceFromId(id1);
                final long count2 = getSequenceFromId(id2);
                result = (int)(count1 - count2);
            }
        }
        return result;
    }
    
    static {
        LOG = LoggerFactory.getLogger(IdGenerator.class);
        String stub = "";
        boolean canAccessSystemProps = true;
        try {
            final SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPropertiesAccess();
            }
        }
        catch (SecurityException se) {
            canAccessSystemProps = false;
        }
        if (canAccessSystemProps) {
            try {
                IdGenerator.hostName = InetAddressUtil.getLocalHostName();
                final ServerSocket ss = new ServerSocket(0);
                stub = "-" + ss.getLocalPort() + "-" + System.currentTimeMillis() + "-";
                Thread.sleep(100L);
                ss.close();
            }
            catch (Exception ioe) {
                IdGenerator.LOG.warn("could not generate unique stub", ioe);
            }
        }
        else {
            IdGenerator.hostName = "localhost";
            stub = "-1-" + System.currentTimeMillis() + "-";
        }
        UNIQUE_STUB = stub;
    }
}
