// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.text.DecimalFormat;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;

public class Results
{
    PrintWriter out;
    long start;
    SimpleDateFormat dateFormat;
    
    public Results() throws IOException {
        this.out = null;
        (this.dateFormat = new SimpleDateFormat()).applyPattern("dd-MM-yyyy HHmmss");
        this.start = System.currentTimeMillis();
        final Date now = new Date();
        now.setTime(this.start);
        final String fileName = MattProperties.instance().getProperty("resultsFolder") + System.getProperty("file.separator") + "results " + this.dateFormat.format(now) + ".txt";
        final FileWriter outFile = new FileWriter(fileName);
        (this.out = new PrintWriter(outFile)).println("Started at: " + this.dateFormat.format(now));
    }
    
    public void log(final String msg) {
        this.out.println(msg);
        this.out.flush();
    }
    
    public void close() {
        final long end = System.currentTimeMillis();
        final Date now = new Date();
        now.setTime(end);
        final DecimalFormat nf = new DecimalFormat("0.###");
        this.out.println("Finished at: " + this.dateFormat.format(now) + " took: " + nf.format((end - this.start) / 1000.0f));
        this.out.close();
    }
}
