// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

class ReadFileThread extends Thread
{
    String version;
    URL codebase;
    
    ReadFileThread(final URL url) {
        this.version = "";
        this.codebase = url;
    }
    
    protected String getLatestJREVersion() {
        return this.version;
    }
    
    public void run() {
        try {
            final URL urlStory = new URL(this.codebase, "JreCurrentVersion2.txt");
            final BufferedReader brStory = new BufferedReader(new InputStreamReader(urlStory.openStream()));
            while (brStory.ready()) {
                this.version = brStory.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("Error reading latest JRE version.");
            System.out.println(e);
        }
    }
}
