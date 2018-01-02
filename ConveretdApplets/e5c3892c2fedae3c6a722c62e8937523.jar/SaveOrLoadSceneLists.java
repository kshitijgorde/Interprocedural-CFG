import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.ProgressMonitor;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class SaveOrLoadSceneLists
{
    static File savedDirectory;
    
    static void save(final imgViewer imgViewer, final boolean b) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        fileChooser.setDialogTitle("Select scene list file name");
        if (SaveOrLoadSceneLists.savedDirectory != null && SaveOrLoadSceneLists.savedDirectory.exists()) {
            fileChooser.setCurrentDirectory(SaveOrLoadSceneLists.savedDirectory);
        }
        if (fileChooser.showSaveDialog(imgViewer.getDialogContainer()) == 0) {
            final File selectedFile = fileChooser.getSelectedFile();
            SaveOrLoadSceneLists.savedDirectory = fileChooser.getCurrentDirectory();
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new FileWriter(selectedFile)));
                printWriter.println("GloVis Scene List");
                final Sensor[] sensors = imgViewer.getSensors();
                for (int i = 0; i < sensors.length; ++i) {
                    final Sensor sensor = sensors[i];
                    if (!sensor.hasMultipleDatasets) {
                        SceneList list;
                        if (b) {
                            list = sensor.hiddenSceneList;
                        }
                        else {
                            list = sensor.sceneList;
                        }
                        final int sceneCount = list.getSceneCount();
                        if (sceneCount > 0) {
                            printWriter.println("sensor=" + sensor.sensorName);
                            for (int j = 0; j < sceneCount; ++j) {
                                printWriter.println(sensor.buildEntityID(list.getSceneAt(j)));
                            }
                        }
                    }
                }
                printWriter.close();
                printWriter = null;
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "Error writing " + selectedFile, "Error Writing Scene List File", 0);
                System.out.println("exception: " + ex.toString());
                try {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    static void load(final imgViewer imgViewer, final boolean b) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        fileChooser.setDialogTitle("Select scene list file name");
        if (SaveOrLoadSceneLists.savedDirectory != null && SaveOrLoadSceneLists.savedDirectory.exists()) {
            fileChooser.setCurrentDirectory(SaveOrLoadSceneLists.savedDirectory);
        }
        if (fileChooser.showOpenDialog(imgViewer.getDialogContainer()) == 0) {
            final File selectedFile = fileChooser.getSelectedFile();
            SaveOrLoadSceneLists.savedDirectory = fileChooser.getCurrentDirectory();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(selectedFile));
                final String line = bufferedReader.readLine();
                if (line == null || !line.equals("GloVis Scene List")) {
                    JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "The file does not appear to be a GloVis Scene List File", "Error Loading Scene List File", 0);
                    bufferedReader.close();
                    bufferedReader = null;
                    return;
                }
                final Vector<LoadedScene> vector = new Vector<LoadedScene>();
                final Sensor[] sensors = imgViewer.getSensors();
                boolean b2 = false;
                boolean b3 = false;
                Sensor sensor = null;
                String line2;
                while ((line2 = bufferedReader.readLine()) != null) {
                    if (line2.startsWith("sensor=")) {
                        b3 = false;
                        final String substring = line2.substring(line2.indexOf(61) + 1);
                        for (int i = 0; i < sensors.length; ++i) {
                            final Sensor sensor2 = sensors[i];
                            if (substring.equals(sensor2.sensorName)) {
                                sensor = sensor2;
                                b3 = true;
                                break;
                            }
                        }
                    }
                    else {
                        if (!b3) {
                            JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "The file does not appear to be a GloVis Scene List File", "Error Loading Scene List File", 0);
                            b2 = true;
                            break;
                        }
                        final LoadedScene loadedScene = new LoadedScene();
                        loadedScene.sensor = sensor;
                        loadedScene.entityID = line2;
                        vector.add(loadedScene);
                    }
                }
                bufferedReader.close();
                bufferedReader = null;
                if (!b2) {
                    new SceneValidator(imgViewer, vector, b).startValidation();
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "Error loading " + selectedFile, "Error Loading Scene List File", 0);
                System.out.println("exception: " + ex.toString());
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    static {
        SaveOrLoadSceneLists.savedDirectory = null;
    }
    
    private static class LoadedScene
    {
        Sensor sensor;
        String entityID;
    }
    
    private static class ValidatedScene
    {
        Sensor sensor;
        Metadata scene;
    }
    
    private static class SceneValidator implements Runnable, ActionListener
    {
        private imgViewer applet;
        private Vector loadedScenes;
        private boolean useHiddenList;
        ProgressMonitor pm;
        String currentSceneID;
        int currentSceneIndex;
        Timer refreshTimer;
        
        SceneValidator(final imgViewer applet, final Vector loadedScenes, final boolean useHiddenList) {
            this.applet = applet;
            this.loadedScenes = loadedScenes;
            this.useHiddenList = useHiddenList;
            (this.pm = new ProgressMonitor(applet.getDialogContainer(), "Validating Scene List", null, 0, loadedScenes.size())).setMillisToPopup(500);
            (this.refreshTimer = new Timer(500, this)).start();
        }
        
        public void startValidation() {
            new Thread(this, "Validate Thread").start();
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            this.pm.setNote("Validating " + this.currentSceneID);
            this.pm.setProgress(this.currentSceneIndex);
            if (this.pm.isCanceled() || this.currentSceneIndex >= this.loadedScenes.size()) {
                this.pm.setProgress(this.loadedScenes.size());
                this.pm.close();
                this.refreshTimer.stop();
                this.refreshTimer = null;
            }
        }
        
        @Override
        public void run() {
            final int size = this.loadedScenes.size();
            final Vector<ValidatedScene> vector = new Vector<ValidatedScene>();
            final String[] array = new String[10];
            array[0] = "The following scenes are not in the inventory:";
            int n = 0;
            for (int i = 0; i < size; ++i) {
                final LoadedScene loadedScene = this.loadedScenes.elementAt(i);
                final Sensor sensor = loadedScene.sensor;
                this.currentSceneID = loadedScene.entityID;
                this.currentSceneIndex = i;
                final Metadata searchForScene = this.applet.searchForSceneDialog.searchForScene(sensor, loadedScene.entityID);
                if (searchForScene != null) {
                    final ValidatedScene validatedScene = new ValidatedScene();
                    validatedScene.sensor = sensor;
                    validatedScene.scene = searchForScene;
                    vector.add(validatedScene);
                }
                else if (n < 8) {
                    ++n;
                    array[n] = sensor.sensorName + ": " + loadedScene.entityID;
                }
                else if (n == 8) {
                    ++n;
                    array[n] = "  and more...";
                }
                if (this.pm.isCanceled()) {
                    break;
                }
            }
            if (!this.pm.isCanceled()) {
                this.currentSceneIndex = size;
                String[] array2 = null;
                if (n > 0) {
                    array2 = new String[n + 1];
                    for (int j = 0; j < n + 1; ++j) {
                        array2[j] = array[j];
                    }
                }
                SwingUtilities.invokeLater(new UpdateSceneLists(this.applet, vector, this.useHiddenList, array2));
            }
        }
    }
    
    private static class UpdateSceneLists implements Runnable
    {
        private imgViewer applet;
        private Vector validatedScenes;
        private String[] errorList;
        private boolean useHiddenList;
        
        UpdateSceneLists(final imgViewer applet, final Vector validatedScenes, final boolean useHiddenList, final String[] errorList) {
            this.applet = applet;
            this.validatedScenes = validatedScenes;
            this.useHiddenList = useHiddenList;
            this.errorList = errorList;
        }
        
        @Override
        public void run() {
            if (this.errorList != null) {
                JOptionPane.showMessageDialog(this.applet.getDialogContainer(), this.errorList, "Error Loading Scene List File", 0);
            }
            for (int size = this.validatedScenes.size(), i = 0; i < size; ++i) {
                final ValidatedScene validatedScene = this.validatedScenes.elementAt(i);
                if (!this.useHiddenList) {
                    validatedScene.sensor.sceneList.add(validatedScene.scene);
                }
                else {
                    validatedScene.sensor.hiddenSceneList.add(validatedScene.scene);
                }
            }
        }
    }
}
