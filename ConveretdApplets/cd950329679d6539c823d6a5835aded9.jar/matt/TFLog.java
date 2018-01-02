// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.io.FileWriter;
import java.io.File;

public class TFLog
{
    public static void deleteLog(final String fileName) {
        final File f = new File(fileName);
        f.delete();
    }
    
    public static void TFLog(final String fileName, final String txt) {
        try {
            final FileWriter fw = new FileWriter(new File(MattProperties.getString("resultsFolder") + System.getProperty("file.separator") + fileName), true);
            fw.write(txt);
            fw.write(System.getProperty("line.separator"));
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
