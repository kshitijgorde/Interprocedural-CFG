// 
// Decompiled by Procyon v0.5.30
// 

package newstick.content;

import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Content extends Thread
{
    public int retryIfError;
    private IContentParser parser;
    
    public Content(final IContentParser parser) {
        this.retryIfError = 60;
        this.parser = parser;
        if (parser == null) {
            throw new NullPointerException("parser");
        }
    }
    
    public void run() {
        final URL url = this.parser.getUrl();
        if (url == null) {
            throw new NullPointerException("url");
        }
        boolean b = false;
        while (!b) {
            try {
                boolean onRead = true;
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                this.parser.beforeReading();
                String line;
                do {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        onRead = this.parser.onRead(line);
                    }
                } while (line != null && onRead);
                this.parser.afterReading();
                b = true;
            }
            catch (IOException ex) {
                this.parser.onIOException(ex);
                b = false;
                try {
                    Thread.sleep(1000 * this.retryIfError);
                }
                catch (InterruptedException ex2) {}
                this.parser.reset();
            }
        }
    }
}
