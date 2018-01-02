// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.util.StringTokenizer;
import javax.sound.sampled.AudioSystem;
import java.io.FilenameFilter;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.io.File;

public class TuneLister
{
    static File folder;
    
    public static void main(final String[] args) {
        try {
            final JFileChooser fc = new JFileChooser();
            final WavFilter filter = new WavFilter();
            fc.setFileSelectionMode(2);
            fc.setSelectedFile(new File("" + ((Hashtable<K, Object>)MattProperties.instance()).get("BatchPath")));
            final int returnVal = fc.showOpenDialog(MattGuiNB.instance());
            if (returnVal == 0) {
                TuneLister.folder = fc.getSelectedFile();
                if (TuneLister.folder.isDirectory()) {
                    final File[] files = TuneLister.folder.listFiles(filter);
                    for (int i = 0; i < files.length; ++i) {
                        final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(files[i]);
                        final AudioFormat format = audioInputStream.getFormat();
                        final String name = files[i].getName();
                        final StringTokenizer st = new StringTokenizer(name, "-");
                        System.out.print(st.nextToken().trim() + "\t");
                        System.out.print(st.nextToken().trim() + "\t");
                        System.out.print(st.nextToken().trim() + "\t");
                        if (st.hasMoreTokens()) {
                            String key = st.nextToken();
                            key = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
                            System.out.print(key);
                        }
                        else {
                            System.out.print("D");
                        }
                        System.out.print("\t");
                        System.out.println(audioInputStream.available() / (format.getSampleRate() * 2.0f));
                        audioInputStream.close();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
