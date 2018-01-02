import java.io.InputStream;
import java.text.MessageFormat;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class FileLoadThread extends Thread
{
    boolean loaded;
    long loadtime;
    SearchApplet4ech sa;
    String encoding;
    String resource_file;
    String[] filedata;
    String[] fileurls;
    
    FileLoadThread(final SearchApplet4ech sa, final String resource_file, final String encoding) {
        this.loaded = false;
        this.loadtime = -1L;
        this.sa = sa;
        this.resource_file = resource_file;
        this.encoding = encoding;
    }
    
    public void run() {
        final Vector vector = new Vector<String>(101);
        final Vector vector2 = new Vector<String>(101);
        final long currentTimeMillis = System.currentTimeMillis();
        int n = 0;
        try {
            final InputStream inputStream = new URL(this.sa.getCodeBase(), this.resource_file).openConnection().getInputStream();
            final BufferedReader bufferedReader = new BufferedReader((this.encoding == null) ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, this.encoding));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<url>")) {
                    vector2.addElement(line.substring(line.indexOf("<url>") + 5, line.indexOf("</url>")));
                    if (++n % 10 != 0) {
                        continue;
                    }
                    this.sa.showStatus(MessageFormat.format(this.sa.getMessage("search.search_initializing_documents"), String.valueOf(n)));
                }
                else {
                    if (!line.startsWith("<title>")) {
                        continue;
                    }
                    vector.addElement(line);
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.filedata = new String[vector.size()];
        this.fileurls = new String[vector2.size()];
        vector.copyInto(this.filedata);
        vector2.copyInto(this.fileurls);
        this.sa.showStatus("");
        this.loaded = true;
        this.loadtime = System.currentTimeMillis() - currentTimeMillis;
        this.sa.prompt();
    }
    
    public String[] getFileData() {
        return this.filedata;
    }
    
    public URL[] getFileURLs() {
        final URL[] array = new URL[this.fileurls.length];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = new URL(this.sa.getCodeBase(), this.fileurls[i]);
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public boolean isLoaded() {
        return this.loaded;
    }
    
    public long getLoadTime() {
        return this.loadtime;
    }
}
