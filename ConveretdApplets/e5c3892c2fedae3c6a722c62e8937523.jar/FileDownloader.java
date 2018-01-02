import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Timer;
import javax.swing.ProgressMonitor;
import java.io.File;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileDownloader implements Runnable, ActionListener
{
    private File destinationDir;
    private Metadata[] scenes;
    private imgViewer applet;
    private Sensor currSensor;
    ProgressMonitor pm;
    String currentSceneID;
    int currentSceneIndex;
    Timer refreshTimer;
    
    public FileDownloader(final imgViewer applet, final File destinationDir, final Metadata[] scenes) {
        this.applet = applet;
        this.destinationDir = destinationDir;
        this.scenes = scenes;
        this.currSensor = applet.sensorMenu.getCurrentSensor();
        (this.pm = new ProgressMonitor(applet, "Downloading data to " + destinationDir.toString(), "Downloading " + scenes[0].getEntityIDForDisplay() + " (" + scenes.length + " remaining)", 0, scenes.length)).setMillisToPopup(500);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.pm.setNote("Downloading " + this.currentSceneID + " (" + (this.scenes.length - this.currentSceneIndex) + " remaining)");
        this.pm.setProgress(this.currentSceneIndex);
        if (this.pm.isCanceled() || this.currentSceneIndex >= this.scenes.length) {
            this.pm.setProgress(this.scenes.length);
            this.pm.close();
            this.refreshTimer.stop();
            this.refreshTimer = null;
        }
    }
    
    public void startDownload() {
        new Thread(this, "Download thread").start();
        (this.refreshTimer = new Timer(500, this)).start();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < this.scenes.length; ++i) {
            this.currentSceneID = this.scenes[i].getEntityIDForDisplay();
            this.currentSceneIndex = i;
            final String[] filesForScene = this.currSensor.getFilesForScene(this.scenes[i]);
            String downloadTextFile = null;
            for (int j = 0; j < filesForScene.length; j += 2) {
                String string = filesForScene[j];
                if (string == null) {
                    string = filesForScene[0].substring(0, filesForScene[0].lastIndexOf(47) + 1) + downloadTextFile;
                }
                if (string != null) {
                    final String substring = string.substring(string.lastIndexOf(46));
                    final File file = new File(this.destinationDir, filesForScene[j + 1]);
                    if (substring.equals(".meta")) {
                        downloadTextFile = this.downloadTextFile(string, file);
                    }
                    else {
                        this.downloadBinaryFile(string, file);
                    }
                }
            }
            if (this.pm.isCanceled()) {
                break;
            }
        }
        if (!this.pm.isCanceled()) {
            this.currentSceneIndex = this.scenes.length;
        }
        this.destinationDir = null;
        this.currSensor = null;
    }
    
    private String downloadTextFile(final String s, final File file) {
        String trim = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.applet.getCodeBase(), s).openStream()));
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line, 0, line.length());
                bufferedWriter.newLine();
                if (line.startsWith("LocalBrowseName")) {
                    final int index = line.indexOf(61);
                    if (index == -1) {
                        continue;
                    }
                    trim = line.substring(index + 1).trim();
                }
            }
            bufferedReader.close();
            bufferedReader = null;
            bufferedWriter.close();
            bufferedWriter = null;
        }
        catch (Exception ex) {
            System.out.println("exception: " + ex.toString());
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
            catch (Exception ex2) {}
        }
        return trim;
    }
    
    private void downloadBinaryFile(final String s, final File file) {
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new URL(this.applet.getCodeBase(), s).openStream()));
            fileOutputStream = new FileOutputStream(file);
            final byte[] array = new byte[60000];
            int read;
            while ((read = dataInputStream.read(array)) != -1) {
                fileOutputStream.write(array, 0, read);
            }
            dataInputStream.close();
            dataInputStream = null;
            fileOutputStream.close();
            fileOutputStream = null;
        }
        catch (Exception ex) {
            System.out.println("exception: " + ex.toString());
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
}
