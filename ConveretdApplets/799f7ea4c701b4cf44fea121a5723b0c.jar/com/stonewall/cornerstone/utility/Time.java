// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.File;
import org.xmodel.log.Log;

public class Time
{
    static final String[] binaries;
    private final long ms;
    static final Log log;
    
    static {
        binaries = new String[] { "settime", "settime.exe" };
        log = Log.getLog(Time.class);
    }
    
    public Time() {
        this.ms = System.currentTimeMillis();
    }
    
    public long milliseconds() {
        return this.ms;
    }
    
    public long seconds() {
        return this.ms / 1000L;
    }
    
    public long getDriftInSeconds(final long tm) {
        return tm - this.seconds();
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.ms);
    }
    
    public void set(final long tm) throws Exception {
        final File binary = this.getBinary();
        if (binary == null) {
            Time.log.warn("Required binary not found.  Time not updated.");
            return;
        }
        final String[] args = { binary.getAbsolutePath(), String.valueOf(tm) };
        Runtime.getRuntime().exec(args);
    }
    
    private File getBinary() {
        File result = null;
        final String home = System.getProperty("cornerstone.home");
        final String path = String.valueOf(home) + "/bin/";
        String[] binaries;
        for (int length = (binaries = Time.binaries).length, i = 0; i < length; ++i) {
            final String b = binaries[i];
            final File f = new File(String.valueOf(path) + b);
            if (f.exists()) {
                result = f;
                break;
            }
        }
        return result;
    }
}
