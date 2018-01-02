// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import java.io.File;
import com.grooveshark.net.uploader.FileUploader;
import java.util.Iterator;
import com.grooveshark.net.uploader.UploadState;
import java.util.ArrayList;
import com.grooveshark.net.uploader.UploaderListener;
import java.util.List;
import com.grooveshark.sharklet.Grooveshark;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import com.grooveshark.net.uploader.SongUploader;
import com.grooveshark.net.uploader.FileUploadListener;
import com.grooveshark.ui.wizard.WizardPanelListener;

public class UploaderController implements WizardPanelListener, FileUploadListener
{
    private SongUploader uploader;
    private SingleColumnTableModel<Song> model;
    private Grooveshark gsApi;
    private boolean isCancelled;
    private int currentIndex;
    private List<UploaderListener> listeners;
    
    public UploaderController(final Grooveshark gsApi, final SongUploader uploader, final SingleColumnTableModel<Song> model) {
        this.listeners = new ArrayList<UploaderListener>();
        this.uploader = uploader;
        this.gsApi = gsApi;
        this.model = model;
        uploader.addUploaderListener(this);
        uploader.start();
    }
    
    private void uploadFiles() {
        System.out.println("UPLOADING");
        this.isCancelled = false;
        this.notifyUploadStarted(this.model.getRowCount());
        for (int i = 0; i < this.model.getRowCount(); ++i) {
            if (this.isCancelled) {
                System.out.println("UPLOAD CANCELLED");
                break;
            }
            this.currentIndex = i;
            final Song song = this.model.getValueAt(i, 0);
            if (song.isInvalid()) {
                System.out.println("SKIPPING " + song.getFile());
                this.updateModel(this.currentIndex, UploadState.FAILED);
            }
            else if (!song.getState().equals(UploadState.COMPLETE)) {
                try {
                    System.out.println("ADDING " + song.getFile());
                    final boolean needsTobeCached = !this.gsApi.addToLibrary(song);
                    if (needsTobeCached) {
                        System.out.println("UPLOADING " + song.getFile());
                        this.uploader.uploadSong(song, song.getFileHash());
                        this.waitForUploadToFinish();
                    }
                    else {
                        System.out.println("ALREADY IN THE SYSTEM " + song.getFile());
                        this.updateModel(this.currentIndex, UploadState.COMPLETE);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.updateModel(this.currentIndex, UploadState.FAILED);
                }
            }
            else {
                this.updateModel(this.currentIndex, UploadState.COMPLETE);
            }
        }
        this.notifyUploadComplete();
        System.out.println("UPLOAD COMPLETE");
    }
    
    private void updateModel(final int index, final UploadState state) {
        final Song song = this.model.getValueAt(index);
        song.setState(state);
        if (song.getState().equals(UploadState.COMPLETE)) {
            this.notifyFileUploaded(song);
        }
        else if (song.getState().equals(UploadState.FAILED)) {
            this.notifyFileFailed(song);
        }
        this.model.setValueAt(song, index, 0);
    }
    
    private void notifyFileUploaded(final Song song) {
        for (final UploaderListener u : this.listeners) {
            u.fileUploaded(song.getFile());
        }
    }
    
    private void notifyFileFailed(final Song song) {
        for (final UploaderListener u : this.listeners) {
            u.fileSkipped(song.getFile());
        }
    }
    
    private void notifyUploadComplete() {
        for (final UploaderListener u : this.listeners) {
            u.uploadCompleted();
        }
    }
    
    private void notifyUploadStarted(final int totalFiles) {
        for (final UploaderListener u : this.listeners) {
            u.uploadStarted(totalFiles);
        }
    }
    
    public void addUploadListener(final UploaderListener listener) {
        this.listeners.add(listener);
    }
    
    private void waitForUploadToFinish() {
        synchronized (this) {
            try {
                System.out.println("Wating for upload to finish");
                this.wait();
                System.out.println("Upload finished waking up");
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void cancelUpload() {
        this.isCancelled = true;
        this.uploader.cancel();
    }
    
    public void updateStatus(final FileUploader uploader, final UploadState state) {
        final File f = uploader.getFile();
        final Song s = this.model.getValueAt(this.currentIndex, 0);
        final int progress = (int)(100L * uploader.getBytesSent() / f.length());
        s.setProgress(progress);
        this.updateModel(this.currentIndex, state);
        if (state.equals(UploadState.COMPLETE) || state.equals(UploadState.FAILED)) {
            synchronized (this) {
                System.out.println(state + " : " + uploader.getBytesSent() + "/" + f.length());
                this.notify();
            }
        }
    }
    
    public void stepChanged(final int step, final int oldStep) {
        this.model.setEditable(step != 3);
        if (step == 3) {
            new Thread() {
                public void run() {
                    UploaderController.this.uploadFiles();
                }
            }.start();
        }
        else {
            this.cancelUpload();
        }
    }
}
