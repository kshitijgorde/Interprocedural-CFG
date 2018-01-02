// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jlgui.basicplayer;

import java.net.URLConnection;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.spi.mpeg.sampled.file.tag.TagParseListener;
import javazoom.spi.mpeg.sampled.file.IcyListener;
import java.io.InputStream;
import javazoom.spi.mpeg.sampled.file.tag.IcyInputStream;
import java.io.BufferedInputStream;
import org.tritonus.share.TDebug;
import javax.sound.sampled.AudioInputStream;
import java.net.URL;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

public class MpegAudioFileReaderWorkaround extends MpegAudioFileReader
{
    public AudioInputStream getAudioInputStream(final URL url, final String userAgent) throws UnsupportedAudioFileException, IOException {
        if (TDebug.TraceAudioFileReader) {
            TDebug.out("MpegAudioFileReaderWorkaround.getAudioInputStream(URL,String): begin");
        }
        final long lFileLengthInBytes = -1L;
        final URLConnection conn = url.openConnection();
        boolean isShout = false;
        final int toRead = 4;
        final byte[] head = new byte[toRead];
        if (userAgent != null) {
            conn.setRequestProperty("User-Agent", userAgent);
        }
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Icy-Metadata", "1");
        conn.setRequestProperty("Connection", "close");
        final BufferedInputStream bInputStream = new BufferedInputStream(conn.getInputStream());
        bInputStream.mark(toRead);
        final int read = bInputStream.read(head, 0, toRead);
        if (read > 2 && (head[0] == 73 | head[0] == 105) && (head[1] == 67 | head[1] == 99) && (head[2] == 89 | head[2] == 121)) {
            isShout = true;
        }
        bInputStream.reset();
        InputStream inputStream = null;
        if (isShout) {
            final IcyInputStream icyStream = new IcyInputStream((InputStream)bInputStream);
            icyStream.addTagParseListener((TagParseListener)IcyListener.getInstance());
            inputStream = (InputStream)icyStream;
        }
        else {
            final String metaint = conn.getHeaderField("icy-metaint");
            if (metaint != null) {
                final IcyInputStream icyStream2 = new IcyInputStream((InputStream)bInputStream, metaint);
                icyStream2.addTagParseListener((TagParseListener)IcyListener.getInstance());
                inputStream = (InputStream)icyStream2;
            }
            else {
                inputStream = bInputStream;
            }
        }
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = this.getAudioInputStream(inputStream, lFileLengthInBytes);
        }
        catch (UnsupportedAudioFileException e) {
            inputStream.close();
            throw e;
        }
        catch (IOException e2) {
            inputStream.close();
            throw e2;
        }
        if (TDebug.TraceAudioFileReader) {
            TDebug.out("MpegAudioFileReaderWorkaround.getAudioInputStream(URL,String): end");
        }
        return audioInputStream;
    }
}
