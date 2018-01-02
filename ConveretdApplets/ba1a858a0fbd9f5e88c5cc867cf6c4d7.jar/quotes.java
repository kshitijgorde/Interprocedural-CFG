import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;
import java.util.Random;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class quotes
{
    private InputStream quoteFile;
    private final int numberOfQuotes = 45;
    private String[] theQuotes;
    private String line;
    private int counter;
    private int activeQuote;
    private Random rand;
    private Applet parentApplet;
    
    public quotes(final Applet parentApplet) {
        this.parentApplet = parentApplet;
        this.rand = new Random();
        this.theQuotes = new String[45];
        try {
            this.quoteFile = new URL(this.parentApplet.getCodeBase(), "quotes.txt").openStream();
        }
        catch (Exception ex) {}
        final DataInputStream dataInputStream = new DataInputStream(this.quoteFile);
        try {
            Label_0100: {
                break Label_0100;
                String line;
                do {
                    this.theQuotes[this.counter] = this.line;
                    ++this.counter;
                    line = dataInputStream.readLine();
                    this.line = line;
                } while (line != null);
            }
        }
        catch (IOException ex2) {}
        finally {
            try {
                dataInputStream.close();
            }
            catch (IOException ex3) {}
        }
    }
    
    public String getQuote() {
        this.activeQuote = Math.abs(this.rand.nextInt()) % 45;
        return this.theQuotes[this.activeQuote];
    }
}
