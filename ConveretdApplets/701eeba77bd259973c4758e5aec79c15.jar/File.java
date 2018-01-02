import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class File
{
    private URL url;
    
    public File(final URL url) {
        this.url = url;
    }
    
    public String getData() {
        String string = "";
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.url.openStream()));
            while (bufferedReader.ready()) {
                string += bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (Exception ex) {}
        return string;
    }
}
