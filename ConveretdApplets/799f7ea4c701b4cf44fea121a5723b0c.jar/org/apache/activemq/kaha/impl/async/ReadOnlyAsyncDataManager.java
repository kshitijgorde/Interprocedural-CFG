// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import org.apache.activemq.util.LinkedNode;
import java.util.Collections;
import java.util.Collection;
import java.io.FilenameFilter;
import java.io.File;
import java.util.ArrayList;
import org.slf4j.Logger;

public class ReadOnlyAsyncDataManager extends AsyncDataManager
{
    private static final Logger LOG;
    private final ArrayList<File> dirs;
    
    public ReadOnlyAsyncDataManager(final ArrayList<File> dirs) {
        this.dirs = dirs;
    }
    
    @Override
    public synchronized void start() throws IOException {
        if (this.started) {
            return;
        }
        this.started = true;
        this.accessorPool = new DataFileAccessorPool(this);
        final ArrayList<File> files = new ArrayList<File>();
        for (final File d : this.dirs) {
            final File directory = d;
            final File[] f = d.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(final File dir, final String n) {
                    return dir.equals(d) && n.startsWith(ReadOnlyAsyncDataManager.this.filePrefix);
                }
            });
            for (int i = 0; i < f.length; ++i) {
                files.add(f[i]);
            }
        }
        for (final File file : files) {
            try {
                final String n = file.getName();
                final String numStr = n.substring(this.filePrefix.length(), n.length());
                final int num = Integer.parseInt(numStr);
                final DataFile dataFile = new ReadOnlyDataFile(file, num, this.preferedFileLength);
                this.fileMap.put(dataFile.getDataFileId(), dataFile);
                this.storeSize.addAndGet(dataFile.getLength());
            }
            catch (NumberFormatException ex) {}
        }
        final List<DataFile> dataFiles = new ArrayList<DataFile>(this.fileMap.values());
        Collections.sort(dataFiles);
        this.currentWriteFile = null;
        for (final DataFile df : dataFiles) {
            if (this.currentWriteFile != null) {
                this.currentWriteFile.linkAfter(df);
            }
            this.currentWriteFile = df;
            this.fileByFileMap.put(df.getFile(), df);
        }
        if (this.currentWriteFile != null) {
            Location l = this.lastAppendLocation.get();
            if (l != null && l.getDataFileId() != this.currentWriteFile.getDataFileId()) {
                l = null;
            }
        }
    }
    
    @Override
    public synchronized void close() throws IOException {
        if (!this.started) {
            return;
        }
        this.accessorPool.close();
        this.fileMap.clear();
        this.fileByFileMap.clear();
        this.started = false;
    }
    
    public Location getFirstLocation() throws IllegalStateException, IOException {
        if (this.currentWriteFile == null) {
            return null;
        }
        final DataFile first = (DataFile)this.currentWriteFile.getHeadNode();
        final Location cur = new Location();
        cur.setDataFileId(first.getDataFileId());
        cur.setOffset(0);
        cur.setSize(0);
        return this.getNextLocation(cur);
    }
    
    @Override
    public synchronized boolean delete() throws IOException {
        throw new RuntimeException("Cannot delete a ReadOnlyAsyncDataManager");
    }
    
    static {
        LOG = LoggerFactory.getLogger(ReadOnlyAsyncDataManager.class);
    }
}
