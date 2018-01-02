// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.songscanner;

import java.io.FileFilter;
import java.util.Collection;
import com.grooveshark.sharklet.Song;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.util.List;

public class SongScanner extends Thread
{
    private List<SongScannerListener> listeners;
    private ScanFilter filter;
    private List<File> folders;
    private FileScanner scanner;
    private File currentFolder;
    private boolean isCancelled;
    
    public SongScanner(final ScanFilter filter) {
        this.folders = new LinkedList<File>();
        this.listeners = new ArrayList<SongScannerListener>();
        this.filter = filter;
        this.scanner = new FileScanner();
    }
    
    private boolean isCancelled() {
        return this.isCancelled;
    }
    
    public void setFilter(final ScanFilter filter) {
        this.filter = filter;
    }
    
    public void cancelScan() {
        this.isCancelled = true;
        this.folders.clear();
        this.scanner.cancel();
    }
    
    public void scanFolder(final File aFolder) {
        synchronized (this.folders) {
            this.folders.add(aFolder);
            this.folders.notify();
        }
    }
    
    public void addListener(final SongScannerListener listener) {
        this.listeners.add(listener);
    }
    
    private void notifyScanStarted() {
        for (final SongScannerListener l : this.listeners) {
            l.scanStarted();
        }
    }
    
    private void notifyFilesFound(final int totalFiles) {
        for (final SongScannerListener l : this.listeners) {
            l.foundFiles(totalFiles);
        }
    }
    
    private void notifyScanFinished() {
        for (final SongScannerListener l : this.listeners) {
            l.scanFinished();
        }
    }
    
    private void notifyError(final File file, final Exception exception) {
        for (final SongScannerListener l : this.listeners) {
            l.badFile(file, exception);
        }
    }
    
    private void notifyNewSong(final Song song) {
        for (final SongScannerListener l : this.listeners) {
            l.newSong(song);
        }
    }
    
    public void run() {
        while (Thread.currentThread().isAlive()) {
            this.isCancelled = false;
            this.notifyScanStarted();
            final List<File> filesFound = new ArrayList<File>();
            while (!this.folders.isEmpty()) {
                this.currentFolder = this.folders.remove(0);
                if (this.currentFolder.isDirectory()) {
                    filesFound.addAll(this.findFiles(this.currentFolder));
                }
                else {
                    filesFound.add(this.currentFolder);
                }
                if (this.isCancelled()) {
                    break;
                }
            }
            this.notifyFilesFound(filesFound.size());
            for (final File file : filesFound) {
                if (this.isCancelled()) {
                    break;
                }
                try {
                    this.notifyNewSong(Song.createSongFromFile(file));
                }
                catch (Exception e) {
                    this.notifyError(file, e);
                }
            }
            if (!this.isCancelled()) {
                this.notifyScanFinished();
            }
            this.waitForNewFolder();
        }
    }
    
    private List<File> findFiles(final File folder) {
        return this.scanner.scanFolder(folder, this.filter);
    }
    
    private void waitForNewFolder() {
        synchronized (this.folders) {
            try {
                this.folders.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
}
