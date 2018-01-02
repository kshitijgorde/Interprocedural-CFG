// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.util.Date;
import java.nio.channels.OverlappingFileLockException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.io.File;

public class LockFile
{
    private static final boolean DISABLE_FILE_LOCK;
    private final File file;
    private FileLock lock;
    private RandomAccessFile readFile;
    private int lockCounter;
    private final boolean deleteOnUnlock;
    
    public LockFile(final File file, final boolean deleteOnUnlock) {
        this.file = file;
        this.deleteOnUnlock = deleteOnUnlock;
    }
    
    public synchronized void lock() throws IOException {
        if (LockFile.DISABLE_FILE_LOCK) {
            return;
        }
        if (this.lockCounter > 0) {
            return;
        }
        IOHelper.mkdirs(this.file.getParentFile());
        if (System.getProperty(this.getVmLockKey()) != null) {
            throw new IOException("File '" + this.file + "' could not be locked as lock is already held for this jvm.");
        }
        if (this.lock == null) {
            this.readFile = new RandomAccessFile(this.file, "rw");
            IOException reason = null;
            try {
                this.lock = this.readFile.getChannel().tryLock(0L, this.readFile.getChannel().size(), false);
            }
            catch (OverlappingFileLockException e) {
                reason = IOExceptionSupport.create("File '" + this.file + "' could not be locked.", e);
            }
            catch (IOException ioe) {
                reason = ioe;
            }
            if (this.lock != null) {
                ++this.lockCounter;
                System.setProperty(this.getVmLockKey(), new Date().toString());
            }
            else {
                this.closeReadFile();
                if (reason != null) {
                    throw reason;
                }
                throw new IOException("File '" + this.file + "' could not be locked.");
            }
        }
    }
    
    public void unlock() {
        if (LockFile.DISABLE_FILE_LOCK) {
            return;
        }
        --this.lockCounter;
        if (this.lockCounter != 0) {
            return;
        }
        if (this.lock != null) {
            try {
                this.lock.release();
                System.getProperties().remove(this.getVmLockKey());
            }
            catch (Throwable t) {}
            this.lock = null;
        }
        this.closeReadFile();
        if (this.deleteOnUnlock) {
            this.file.delete();
        }
    }
    
    private String getVmLockKey() throws IOException {
        return this.getClass().getName() + ".lock." + this.file.getCanonicalPath();
    }
    
    private void closeReadFile() {
        if (this.readFile != null) {
            try {
                this.readFile.close();
            }
            catch (Throwable t) {}
            this.readFile = null;
        }
    }
    
    static {
        DISABLE_FILE_LOCK = "true".equals(System.getProperty("java.nio.channels.FileLock.broken", "false"));
    }
}
