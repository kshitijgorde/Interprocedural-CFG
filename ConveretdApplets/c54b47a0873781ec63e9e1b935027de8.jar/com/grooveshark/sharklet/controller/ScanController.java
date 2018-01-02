// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import java.util.Iterator;
import java.util.List;
import com.grooveshark.songscanner.ScanFilter;
import java.io.File;
import com.grooveshark.songscanner.SongScanner;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import com.grooveshark.ui.fileexplorer.FileExplorerModel;
import com.grooveshark.ui.wizard.WizardPanelListener;
import com.grooveshark.songscanner.SongScannerListener;

public class ScanController implements SongScannerListener, WizardPanelListener
{
    public static final String FILETYPE = ".mp3";
    private FileExplorerModel folderModel;
    private SingleColumnTableModel<Song> tableModel;
    private SongScanner scanner;
    private String artistName;
    
    public ScanController(final FileExplorerModel folderModel, final SingleColumnTableModel<Song> tableModel, final SongScanner scanner, final String artistName) {
        this.artistName = artistName;
        this.folderModel = folderModel;
        this.tableModel = tableModel;
        this.scanner = scanner;
    }
    
    public void badFile(final File file, final Exception exception) {
        System.err.println("Bad File: " + file);
    }
    
    public void newSong(final Song song) {
        System.out.println(song);
        if (!this.artistName.equals("")) {
            song.setArtist(this.artistName);
        }
        this.tableModel.add(song);
    }
    
    public void foundFiles(final int totalFiles) {
        System.out.println("Found " + totalFiles + " files");
    }
    
    public void scanStarted() {
        if (!this.artistName.equals("")) {
            System.out.println("Overriding artist with " + this.artistName);
        }
    }
    
    public void scanFinished() {
        System.out.println("Scan finished!");
    }
    
    private void scanFolders() {
        final List<File> folders = this.folderModel.getSelectedFiles();
        final List<File> excludedFiles = this.folderModel.getExclusions();
        final ScanFilter filter = new ScanFilter(excludedFiles, new String[] { ".mp3" });
        this.scanner.setFilter(filter);
        this.tableModel.clear();
        if (!this.scanner.isAlive()) {
            this.scanner.start();
        }
        for (final File f : folders) {
            System.out.println("Scanning " + f);
            this.scanner.scanFolder(f);
        }
    }
    
    private void cancelScan() {
        this.scanner.cancelScan();
    }
    
    public void stepChanged(final int newStep, final int oldStep) {
        if (newStep == 2 && newStep > oldStep) {
            this.scanFolders();
        }
        else {
            this.cancelScan();
        }
    }
}
