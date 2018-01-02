// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import com.grooveshark.sharklet.Sharklet;
import com.grooveshark.sharklet.Song;
import java.io.File;
import com.grooveshark.ui.wizard.WizardPanel;
import com.grooveshark.ui.wizard.WizardPanelListener;
import com.grooveshark.songscanner.SongScannerListener;

public class ScanProgress implements SongScannerListener, WizardPanelListener
{
    private static final String SCANNING;
    private int processedFiles;
    private WizardPanel panel;
    
    public ScanProgress(final WizardPanel panel) {
        this.panel = panel;
    }
    
    public void badFile(final File file, final Exception exception) {
        this.updateFilesProcessed();
    }
    
    public void newSong(final Song song) {
        this.updateFilesProcessed();
    }
    
    public void foundFiles(final int totalFiles) {
        this.processedFiles = 0;
        this.panel.setProgressText(ScanProgress.SCANNING);
        this.panel.setProcessTotal(totalFiles);
    }
    
    public void scanStarted() {
        this.panel.processStarted();
    }
    
    public void scanFinished() {
        this.panel.processCompleted();
    }
    
    private void updateFilesProcessed() {
        ++this.processedFiles;
        this.panel.setProcessValue(this.processedFiles);
    }
    
    public void stepChanged(final int newStep, final int oldStep) {
        if (newStep != 2) {
            this.panel.processCompleted();
        }
    }
    
    static {
        SCANNING = Sharklet.getText("SCANNING");
    }
}
