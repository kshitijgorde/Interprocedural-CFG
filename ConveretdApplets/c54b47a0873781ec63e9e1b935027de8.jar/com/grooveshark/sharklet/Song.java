// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet;

import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import com.grooveshark.net.uploader.UploadState;
import java.io.File;

public class Song
{
    private static final int DEFAULT_YEAR = 1900;
    private static final int DEFAULT_TRACK = 0;
    private transient File songFile;
    private transient int progress;
    private transient UploadState state;
    private String filename;
    private String artist;
    private String genre;
    private String title;
    private String album;
    private String hash;
    private int sampleRate;
    private int track;
    private int year;
    private long duration;
    private long bitrate;
    
    private Song() {
    }
    
    private Song(final File songFile) throws Exception {
        this.songFile = songFile;
        this.state = UploadState.QUEUED;
        this.initializeSong(songFile);
    }
    
    public boolean isInvalid() {
        return this.getTitle().equals("") || this.getAlbum().equals("") || this.getArtist().equals("") || this.getBitrate() < 128L || this.getSampleRate() != 44100;
    }
    
    public String getFileHash() {
        if (this.hash == null) {
            this.hash = FileHash.computeHashForFile(this.songFile);
        }
        return this.hash;
    }
    
    public static Song createBlankSong() {
        return new Song();
    }
    
    public static Song createSongFromFile(final File file) throws Exception {
        return new Song(file);
    }
    
    private void initializeSong(final File songFile) throws Exception {
        final AudioFile aFile = AudioFileIO.read(songFile);
        Tag tag = aFile.getTag();
        if (tag == null) {
            tag = aFile.getTagOrCreateAndSetDefault();
            aFile.commit();
        }
        final AudioHeader header = aFile.getAudioHeader();
        if (header == null) {
            throw new Exception();
        }
        this.filename = songFile.getName();
        this.duration = header.getTrackLength();
        this.bitrate = header.getBitRateAsNumber();
        this.sampleRate = header.getSampleRateAsNumber();
        this.title = this.nullToEmptyString(tag.getFirstTitle());
        this.artist = this.nullToEmptyString(tag.getFirstArtist());
        this.album = this.nullToEmptyString(tag.getFirstAlbum());
        this.genre = this.nullToEmptyString(tag.getFirstGenre());
        this.track = this.loadTrackNumber(tag);
        this.year = this.loadYear(tag);
    }
    
    private int loadYear(final Tag tag) {
        final String year = tag.getFirstYear();
        if (year != null && !year.equals("")) {
            try {
                return Integer.parseInt(year);
            }
            catch (Exception ex) {}
        }
        return 1900;
    }
    
    private int loadTrackNumber(final Tag tag) {
        int trackNumber = 0;
        final boolean supportsTrackNumber = !(tag instanceof ID3v1Tag);
        if (supportsTrackNumber) {
            String track = tag.getFirstTrack();
            if (track.contains("/")) {
                final String[] temp = track.split("/");
                if (temp.length > 0) {
                    track = temp[0];
                }
            }
            try {
                trackNumber = Integer.parseInt(track);
            }
            catch (Exception ex) {}
        }
        return trackNumber;
    }
    
    private String nullToEmptyString(final String str) {
        return (str == null) ? "" : str;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getArtist() {
        return this.artist;
    }
    
    public void setArtist(final String artist) {
        this.artist = artist;
    }
    
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(final String genre) {
        this.genre = genre;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(final String filename) {
        this.filename = filename;
    }
    
    public String getAlbum() {
        return this.album;
    }
    
    public void setAlbum(final String album) {
        this.album = album;
    }
    
    public int getTrackNumber() {
        return this.track;
    }
    
    public void setTrackNumber(final int trackNumber) {
        this.track = trackNumber;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setYear(final int year) {
        this.year = year;
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public long getBitrate() {
        return this.bitrate;
    }
    
    public File getFile() {
        return this.songFile;
    }
    
    public String toString() {
        return String.format("---------------------------\nFile: %s\nTitle: %s\nArtist: %s\nAlbum: %s\n[%s, %s]", this.songFile.getName(), this.title, this.artist, this.album, this.sampleRate, this.bitrate);
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public void setProgress(final int progress) {
        this.progress = progress;
    }
    
    public UploadState getState() {
        return this.state;
    }
    
    public void setState(final UploadState state) {
        this.state = state;
    }
    
    public boolean equals(final Object obj) {
        final Song other = (Song)obj;
        return super.equals(obj) || this.songFile.equals(other.songFile);
    }
}
