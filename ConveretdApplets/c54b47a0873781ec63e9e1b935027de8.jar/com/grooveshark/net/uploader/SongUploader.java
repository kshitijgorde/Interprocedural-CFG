// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.net.uploader;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.Checksum;
import java.io.InputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Adler32;
import java.io.FileInputStream;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import com.grooveshark.sharklet.Song;
import java.util.List;
import com.grooveshark.sharklet.Grooveshark;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;

public class SongUploader extends Thread implements FileUploadListener
{
    private HttpClient client;
    private PostMethod post;
    private StringPart sessionPost;
    private FilePartMonitor filePost;
    private Grooveshark grooveshark;
    private List<FileUploadListener> listeners;
    private List<String> hashes;
    private List<Song> songs;
    
    public SongUploader(final Grooveshark grooveshark, final String serverUrl, final String sessionId) {
        this.client = new HttpClient();
        this.sessionPost = new StringPart("sessionId", sessionId);
        this.grooveshark = grooveshark;
        this.listeners = new LinkedList<FileUploadListener>();
        this.hashes = new LinkedList<String>();
        this.songs = new LinkedList<Song>();
    }
    
    public void addUploaderListener(final FileUploadListener listener) {
        this.listeners.add(listener);
    }
    
    public void uploadSong(final Song song, final String fileHash) {
        synchronized (this.songs) {
            this.songs.add(song);
            this.hashes.add(fileHash);
            this.songs.notify();
        }
    }
    
    private void notifyListeners(final UploadState state) {
        for (final FileUploadListener l : this.listeners) {
            l.updateStatus(this.filePost, state);
        }
    }
    
    public void run() {
        while (Thread.currentThread().isAlive()) {
            while (!this.songs.isEmpty()) {
                final Song song = this.songs.remove(0);
                final String filename = this.hashes.remove(0) + ".mp3";
                final String serverUrl = this.grooveshark.getUploadLocation(song);
                this.transfer(serverUrl, song, filename);
            }
            this.waitForNewFile();
        }
    }
    
    private void waitForNewFile() {
        synchronized (this.songs) {
            try {
                this.songs.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void cancel() {
        if (this.post == null) {
            return;
        }
        this.post.abort();
    }
    
    private long getFileChecksum(final File file) throws FileNotFoundException, IOException {
        final CheckedInputStream cis = new CheckedInputStream(new FileInputStream(file), new Adler32());
        try {
            final byte[] buffer = new byte[4096];
            while (cis.read(buffer) >= 0) {}
            return cis.getChecksum().getValue();
        }
        finally {
            try {
                cis.close();
            }
            catch (IOException ex) {}
        }
    }
    
    private void transfer(final String serverUrl, final Song song, final String filename) {
        System.out.println(serverUrl);
        this.post = new PostMethod(serverUrl);
        int status = 0;
        try {
            final File file = song.getFile();
            final long checksum = this.getFileChecksum(file);
            (this.filePost = new FilePartMonitor(file, filename)).addUploaderListener(this);
            final String originalFilename = file.getName();
            final StringPart filenamePost = new StringPart("filename", originalFilename, "utf-8");
            final StringPart artistPost = new StringPart("artistName", song.getArtist(), "utf-8");
            final StringPart titlePost = new StringPart("songName", song.getTitle(), "utf-8");
            final StringPart albumPost = new StringPart("albumName", song.getAlbum(), "utf-8");
            final StringPart genrePost = new StringPart("genre", song.getGenre(), "utf-8");
            final StringPart yearPost = new StringPart("year", song.getYear() + "", "utf-8");
            final StringPart trackPost = new StringPart("track", song.getTrackNumber() + "", "utf-8");
            final StringPart checksumPost = new StringPart("checksum", checksum + "", "utf-8");
            final StringPart sizePost = new StringPart("size", file.length() + "", "utf-8");
            final Part[] postArguments = { filenamePost, this.sessionPost, this.filePost, artistPost, titlePost, albumPost, genrePost, yearPost, trackPost, checksumPost, sizePost };
            final HttpMethodParams params = this.post.getParams();
            final MultipartRequestEntity request = new MultipartRequestEntity(postArguments, params);
            this.post.setRequestEntity((RequestEntity)request);
            status = this.client.executeMethod((HttpMethod)this.post);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final boolean wasUploadedSuccessful = status == 200;
        final UploadState finalState = wasUploadedSuccessful ? UploadState.COMPLETE : UploadState.FAILED;
        this.notifyListeners(finalState);
    }
    
    public void updateStatus(final FileUploader uploader, final UploadState state) {
        if (!state.equals(UploadState.COMPLETE) && !state.equals(UploadState.FAILED)) {
            this.notifyListeners(state);
        }
    }
}
