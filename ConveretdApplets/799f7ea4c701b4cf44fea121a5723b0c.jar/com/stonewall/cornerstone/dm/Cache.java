// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import java.io.FileFilter;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import java.io.OutputStream;
import java.io.File;

public class Cache
{
    File file;
    File deviceDir;
    OutputStream ostr;
    int errors;
    final ReentrantLock lock;
    static final String suffix = ".txt";
    static final String subdir = "/etc/deviceCache/";
    static final Log log;
    
    static {
        log = Log.getLog(Cache.class);
    }
    
    Cache(final String dirname, final String filename) {
        this.file = null;
        this.deviceDir = null;
        this.ostr = null;
        this.errors = 0;
        this.lock = new ReentrantLock();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        final String dt = dateFormat.format(new Date());
        this.deviceDir = new File(String.valueOf(getDir()) + dirname);
        this.file = new File(String.valueOf(getDir()) + dirname + File.separator + filename + dt + ".txt");
        this.open();
    }
    
    Cache(final File file) {
        this.file = null;
        this.deviceDir = null;
        this.ostr = null;
        this.errors = 0;
        this.lock = new ReentrantLock();
        this.file = file;
    }
    
    public void finalize() {
        this.close();
    }
    
    String getName() {
        final String fn = this.file.getName();
        return fn.substring(0, fn.indexOf(46));
    }
    
    Cache open() {
        Cache.log.debug("Open cache: " + this.file);
        try {
            if (!this.deviceDir.exists()) {
                this.deviceDir.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            this.ostr = new FileOutputStream(this.file);
        }
        catch (Exception e) {
            Cache.log.error(this.file, e);
        }
        return this;
    }
    
    void close() {
        this.lock.lock();
        try {
            if (this.ostr == null) {
                return;
            }
            this.ostr.close();
            Cache.log.debug("Closed cache: " + this.file);
        }
        catch (Exception e) {
            Cache.log.error(this.file, e);
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void delete() {
        if (this.errors == 0) {
            Cache.log.debug("Delete cache: " + this.file);
            this.close();
            this.file.delete();
        }
        else {
            Cache.log.debug("cache: " + this.file + " has recovery errors:" + this.errors);
            this.markAsDeleted();
        }
    }
    
    void markAsDeleted() {
        Cache.log.debug("Mark cache: " + this.file + " as deleted.");
        this.close();
        final File deleted = new File(String.valueOf(this.file.getAbsolutePath()) + ".deleted");
        this.file.renameTo(deleted);
    }
    
    void write(final String entry) {
        this.lock.lock();
        try {
            this.ostr.write(entry.getBytes());
            this.ostr.flush();
        }
        catch (Exception e) {
            Cache.log.error(this.file, e);
            return;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    String read() throws Exception {
        final InputStream istr = new FileInputStream(this.file);
        final String entry = istr.toString();
        istr.close();
        return entry;
    }
    
    static FileFilter getFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(final File f) {
                return f.getName().endsWith(".txt");
            }
        };
    }
    
    static String getDir() {
        return String.valueOf(System.getProperty("cornerstone.home")) + File.separator + "/etc/deviceCache/";
    }
}
