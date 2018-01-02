// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.jcraft.jogg.StreamState;
import com.fluendo.jheora.Info;
import com.fluendo.jheora.Comment;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Locale;
import java.text.MessageFormat;
import com.fluendo.utils.Base64Converter;
import com.fluendo.utils.Debug;
import java.io.InputStream;
import java.net.URL;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.SyncState;
import java.util.Hashtable;

public class DurationScanner
{
    static final int NOTDETECTED = -1;
    static final int UNKNOWN = 0;
    static final int VORBIS = 1;
    static final int THEORA = 2;
    private long contentLength;
    private long responseOffset;
    private Hashtable streaminfo;
    private SyncState oy;
    private Page og;
    private Packet op;
    
    public DurationScanner() {
        this.contentLength = -1L;
        this.streaminfo = new Hashtable();
        this.oy = new SyncState();
        this.og = new Page();
        this.op = new Packet();
        this.oy.init();
    }
    
    private InputStream openWithConnection(final URL url, final String s, final String s2, final long n) throws IOException {
        final String s3 = "Cortado";
        final URLConnection openConnection = url.openConnection();
        openConnection.setRequestProperty("Connection", "Keep-Alive");
        String s4;
        if (n != 0L && this.contentLength != -1L) {
            s4 = "bytes=" + n + "-" + (this.contentLength - 1L);
        }
        else if (n != 0L) {
            s4 = "bytes=" + n + "-";
        }
        else {
            s4 = null;
        }
        if (s4 != null) {
            Debug.info("doing range: " + s4);
            openConnection.setRequestProperty("Range", s4);
        }
        openConnection.setRequestProperty("User-Agent", s3);
        if (s != null && s2 != null) {
            openConnection.setRequestProperty("Authorization", "Basic " + Base64Converter.encode((s + ":" + s2).getBytes()));
        }
        openConnection.setRequestProperty("Content-Type", "application/octet-stream");
        final InputStream inputStream = openConnection.getInputStream();
        final String headerField = openConnection.getHeaderField("Content-Range");
        if (headerField == null) {
            Debug.info("Response contained no Content-Range field, assuming offset=0");
            this.responseOffset = 0L;
        }
        else {
            try {
                final MessageFormat messageFormat = new MessageFormat("bytes {0,number}-{1,number}");
                messageFormat.setLocale(Locale.US);
                this.responseOffset = ((Number)messageFormat.parse(headerField)[0]).longValue();
                if (this.responseOffset < 0L) {
                    this.responseOffset = 0L;
                }
                Debug.debug("Stream successfully with offset " + this.responseOffset);
            }
            catch (Exception ex) {
                Debug.info("Error parsing Content-Range header");
                this.responseOffset = 0L;
            }
        }
        this.contentLength = openConnection.getHeaderFieldInt("Content-Length", -1) + this.responseOffset;
        return inputStream;
    }
    
    private void determineType(final Packet packet, final StreamInfo streamInfo) {
        final Comment comment = new Comment();
        final Info decoder = new Info();
        comment.clear();
        decoder.clear();
        if (decoder.decodeHeader(comment, packet) == 0) {
            streamInfo.decoder = decoder;
            streamInfo.type = 2;
            ++streamInfo.decodedHeaders;
            return;
        }
        final com.jcraft.jorbis.Comment comment2 = new com.jcraft.jorbis.Comment();
        final com.jcraft.jorbis.Info decoder2 = new com.jcraft.jorbis.Info();
        comment2.init();
        decoder2.init();
        if (decoder2.synthesis_headerin(comment2, packet) == 0) {
            streamInfo.decoder = decoder2;
            streamInfo.type = 1;
            ++streamInfo.decodedHeaders;
            return;
        }
        streamInfo.type = 0;
    }
    
    public float getDurationForBuffer(final byte[] array, final int n) {
        float n2 = -1.0f;
        System.arraycopy(array, 0, this.oy.data, this.oy.buffer(n), n);
        this.oy.wrote(n);
        while (this.oy.pageout(this.og) == 1) {
            final Integer n3 = new Integer(this.og.serialno());
            StreamInfo streamInfo = this.streaminfo.get(n3);
            if (streamInfo == null) {
                streamInfo = new StreamInfo();
                (streamInfo.streamstate = new StreamState()).init(this.og.serialno());
                this.streaminfo.put(n3, streamInfo);
                Debug.info("DurationScanner: created StreamState for stream no. " + n3);
            }
            streamInfo.streamstate.pagein(this.og);
            while (streamInfo.streamstate.packetout(this.op) == 1) {
                final int type = streamInfo.type;
                if (type == -1) {
                    this.determineType(this.op, streamInfo);
                    streamInfo.startgranule = this.og.granulepos();
                }
                switch (type) {
                    case 1: {
                        final float n4 = (this.og.granulepos() - streamInfo.startgranule) / ((com.jcraft.jorbis.Info)streamInfo.decoder).rate;
                        if (n4 <= n2) {
                            continue;
                        }
                        n2 = n4;
                        continue;
                    }
                    case 2: {
                        final Info info = (Info)streamInfo.decoder;
                        continue;
                    }
                }
            }
        }
        return n2;
    }
    
    public float getDurationForURL(final URL url, final String s, final String s2) {
        try {
            final int n = 24576;
            final int n2 = 131072;
            float n3 = 0.0f;
            final byte[] array = new byte[1024];
            final InputStream openWithConnection = this.openWithConnection(url, s, s2, 0L);
            long n4 = 0L;
            float durationForBuffer;
            for (int n5 = openWithConnection.read(array); n4 < n && n5 > 0; n4 += n5, durationForBuffer = this.getDurationForBuffer(array, n5), n3 = ((durationForBuffer > n3) ? durationForBuffer : n3), n5 = openWithConnection.read(array)) {}
            openWithConnection.close();
            final InputStream openWithConnection2 = this.openWithConnection(url, s, s2, this.contentLength - n2);
            if (this.responseOffset == 0L) {
                Debug.warning("DurationScanner: Couldn't complete duration scan due to failing range requests!");
                return -1.0f;
            }
            float durationForBuffer2;
            for (int n6 = openWithConnection2.read(array); n6 > 0 && n4 < (n + n2) * 2; n4 += n6, durationForBuffer2 = this.getDurationForBuffer(array, n6), n3 = ((durationForBuffer2 > n3) ? durationForBuffer2 : n3), n6 = openWithConnection2.read(array)) {}
            return n3;
        }
        catch (IOException ex) {
            Debug.error(ex.toString());
            return -1.0f;
        }
    }
    
    public static void main(final String[] array) throws IOException {
        System.out.println(new DurationScanner().getDurationForURL(new URL(array[0]), null, null));
    }
    
    private class StreamInfo
    {
        public Object decoder;
        public int decodedHeaders;
        public int type;
        public long startgranule;
        public StreamState streamstate;
        public boolean ready;
        
        private StreamInfo() {
            this.decodedHeaders = 0;
            this.type = -1;
            this.ready = false;
        }
    }
}
