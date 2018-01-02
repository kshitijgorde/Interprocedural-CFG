// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import com.grooveshark.sharklet.Sharklet;
import java.io.File;
import com.grooveshark.ui.wizard.WizardPanel;
import com.grooveshark.net.uploader.UploaderListener;

public class UploadProgress implements UploaderListener
{
    private static final String UPLOADING;
    private WizardPanel panel;
    private int processedFiles;
    
    public UploadProgress(final WizardPanel panel) {
        this.panel = panel;
    }
    
    private void updateProgress() {
        ++this.processedFiles;
        this.panel.setProcessValue(this.processedFiles + 1);
    }
    
    public void fileSkipped(final File file) {
        this.updateProgress();
    }
    
    public void fileUploaded(final File file) {
        this.updateProgress();
    }
    
    public void uploadCompleted() {
        this.panel.processCompleted();
    }
    
    public void uploadStarted(final int totalFiles) {
        this.panel.processStarted();
        this.processedFiles = 0;
        this.panel.setProgressText(UploadProgress.UPLOADING);
        this.panel.setProcessTotal(totalFiles);
    }
    
    static {
        UPLOADING = Sharklet.getText("UPLOADING");
    }
}
