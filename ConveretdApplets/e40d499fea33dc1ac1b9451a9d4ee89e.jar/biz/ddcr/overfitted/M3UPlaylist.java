// 
// Decompiled by Procyon v0.5.30
// 

package biz.ddcr.overfitted;

import org.apache.commons.logging.LogFactory;
import java.io.InvalidClassException;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.apache.commons.logging.Log;

public class M3UPlaylist implements Playlist
{
    private static Log log;
    private ArrayList<String> items;
    private int index;
    
    public M3UPlaylist(final String fileName) {
        this(fileName, "UTF-8");
    }
    
    public M3UPlaylist(final String fileName, final String charset) {
        this.items = null;
        this.items = this.readPlaylist(fileName, charset);
    }
    
    public void rewind() {
        this.index = 0;
    }
    
    public String getNextItem() {
        if (this.items != null && this.index < this.items.size()) {
            return this.items.get(this.index++);
        }
        return null;
    }
    
    private boolean isMIMETypeOk(final String type) {
        return type.equalsIgnoreCase("audio/x-mpegurl") || type.equalsIgnoreCase("audio/mpeg-url") || type.equalsIgnoreCase("audio/scpls") || type.equalsIgnoreCase("audio/x-scpls");
    }
    
    private ArrayList<String> readBuffer(final BufferedReader in) throws Exception {
        final ArrayList<String> fileContent = new ArrayList<String>();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            fileContent.add(inputLine.trim());
        }
        in.close();
        return fileContent;
    }
    
    private ArrayList<String> getPLSorM3UFileHandle(final String fileName, final String charset) throws Exception {
        ArrayList<String> fileContent = null;
        try {
            final URL url = new URL(fileName);
            URLConnection uc = null;
            uc = url.openConnection();
            final String type = uc.getContentType();
            if (!this.isMIMETypeOk(type)) {
                return null;
            }
            final InputStreamReader in = (charset != null && !charset.isEmpty()) ? new InputStreamReader(uc.getInputStream(), charset) : new InputStreamReader(uc.getInputStream());
            fileContent = this.readBuffer(new BufferedReader(in));
        }
        catch (MalformedURLException e) {
            if (!fileName.toLowerCase().endsWith(".pls") && !fileName.toLowerCase().endsWith(".m3u")) {
                return null;
            }
            final FileInputStream fIn = new FileInputStream(new File(fileName));
            final InputStreamReader in2 = (charset != null && !charset.isEmpty()) ? new InputStreamReader(fIn, charset) : new InputStreamReader(fIn);
            fileContent = this.readBuffer(new BufferedReader(in2));
        }
        return fileContent;
    }
    
    private ArrayList<String> readPlaylist(final String name, final String charset) {
        final ArrayList<String> localItems = new ArrayList<String>();
        try {
            final ArrayList<String> fullContent = this.getPLSorM3UFileHandle(name, charset);
            if (fullContent == null) {
                throw new InvalidClassException("Not an M3U or PLS");
            }
            for (int i = 0; i < fullContent.size(); ++i) {
                if (fullContent.get(i).length() > 7 && fullContent.get(i).substring(0, 7).equalsIgnoreCase("FILE://")) {
                    localItems.add(fullContent.get(i).substring(7).trim());
                }
                else if (fullContent.get(i).length() > 4 && fullContent.get(i).substring(0, 4).equalsIgnoreCase("FILE")) {
                    final int localIndex = fullContent.get(i).indexOf(61);
                    if (localIndex > 0) {
                        localItems.add(fullContent.get(i).substring(localIndex + 1).trim());
                    }
                }
            }
        }
        catch (Exception e) {
            M3UPlaylist.log.error((Object)"Error reading the playlist", (Throwable)e);
        }
        this.rewind();
        return localItems;
    }
    
    static {
        M3UPlaylist.log = LogFactory.getLog((Class)ShoutcastPlayer.class);
    }
}
