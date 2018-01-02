// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import javax.jnlp.PersistenceService;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.jnlp.UnavailableServiceException;
import java.net.MalformedURLException;
import javax.jnlp.ServiceManager;
import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import jstella.runner.JStellaGame;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Frame;
import jstella.runner.InputMaster;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;
import java.io.File;
import java.io.FileFilter;
import jstella.runner.JStellaRunnerUtil;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class JStellaDesktop
{
    public static final int DESTINATION_EXIT = 0;
    public static final int DESTINATION_REPOSITORY = 1;
    public static final int DESTINATION_CLASSIC = 2;
    public static final String RESOURCE_STANDARD_METADATA = "/jstella/resources/metadata/metadata.j26mc";
    private static final String MUFFIN_ID_CONFIG_DIRECTORY = "configdir";
    private static JStellaPlayer myPlayer;
    private static JStellaRepository myRepository;
    private static JStellaSetupWizard mySetupWizard;
    private static JFileChooser myFileBrowser;
    private static ImageIcon myIcon64;
    private static ImageIcon myIcon32;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Starting JStella...");
                initJStella();
                System.out.println("Creating player...");
                JStellaDesktop.myPlayer = new JStellaPlayer();
                JStellaDesktop.myPlayer.setIconImage(JStellaDesktop.myIcon32.getImage());
                JStellaJoystickInput.ControllerTextTest();
                System.out.println("Searching for joystick...");
                JStellaJoystickInput.detectJoystick();
                System.out.println("Showing main window...");
                showMainWindow();
            }
        });
    }
    
    public static void doClassicMode() {
        if (JStellaDesktop.myPlayer == null) {
            JStellaDesktop.myPlayer = new JStellaPlayer();
        }
        JStellaDesktop.myPlayer.updateRepositoryMode(false);
        JStellaDesktop.myPlayer.setVisible(true);
    }
    
    public static void doRepositoryMode() {
        if (JStellaDesktop.myRepository == null) {
            (JStellaDesktop.myRepository = new JStellaRepository()).setIconImage(JStellaDesktop.myIcon32.getImage());
        }
        JStellaDesktop.myPlayer.updateRepositoryMode(true);
        JStellaDesktop.myRepository.setVisible(true);
    }
    
    public static void doWizard() {
        if (JStellaDesktop.mySetupWizard == null) {
            (JStellaDesktop.mySetupWizard = new JStellaSetupWizard()).setIconImage(JStellaDesktop.myIcon32.getImage());
        }
        JStellaDesktop.mySetupWizard.showWizard();
    }
    
    private static void showMainWindow() {
        if (!hasGamesInRepository()) {
            showDefaultWindow();
        }
        else if (JStellaRunnerUtil.isConfigNegative("jstella.repositorymode")) {
            System.out.println("STARTUP MODE=classic");
            doClassicMode();
        }
        else {
            System.out.println("STARTUP MODE=repository");
            doRepositoryMode();
        }
    }
    
    private static void showDefaultWindow() {
        doWizard();
    }
    
    private static void initJStella() {
        initConfiguration();
        initIcons();
    }
    
    protected static boolean hasGamesInRepository() {
        boolean zReturn = false;
        final File zReposDir = getRepositoryDirectory();
        if (zReposDir != null && zReposDir.isDirectory()) {
            final File[] zGames = zReposDir.listFiles(JSFileNameExtensionFilter.FILTER_JSTELLAGAME);
            if (zGames.length > 0) {
                zReturn = true;
            }
        }
        return zReturn;
    }
    
    private static void initIcons() {
        final URL zIcon32URL = JStellaDesktop.class.getResource("/jstella/resources/jstellaicon32.gif");
        JStellaDesktop.myIcon32 = new ImageIcon(zIcon32URL);
        final URL zIcon64URL = JStellaDesktop.class.getResource("/jstella/resources/jstellaicon64.gif");
        JStellaDesktop.myIcon64 = new ImageIcon(zIcon64URL);
    }
    
    private static void initConfiguration() {
        if (getConfiguration() == null) {
            setConfiguration(new HashMap<String, String>());
        }
        final boolean zConfigLoaded = loadConfig();
        if (!zConfigLoaded) {
            loadDefaultConfiguration();
        }
        else if (zConfigLoaded && !InputMaster.checkConfigMapForControls(getConfiguration())) {
            System.out.println("JStella configuration file lacks control settings...adding default control settings");
            loadDefaultConfiguration();
        }
    }
    
    private static void loadDefaultConfiguration() {
        getConfiguration().clear();
        InputMaster.addDefaultControlItemsToConfigMap(getConfiguration());
    }
    
    protected static void setConfiguration(final Map<String, String> aConfigMap) {
        JStellaRunnerUtil.setConfiguration(aConfigMap);
    }
    
    protected static Map<String, String> getConfiguration() {
        return JStellaRunnerUtil.getConfiguration();
    }
    
    public static boolean showConfigurationDialog(final Frame aParent) {
        boolean zChanged = false;
        final Map<String, String> zNewConfig = JStellaConfigurationDialog.runJStellaConfiguration(aParent, getConfiguration());
        if (zNewConfig != null) {
            zChanged = true;
            getConfiguration().clear();
            getConfiguration().putAll(zNewConfig);
            saveConfig();
        }
        return zChanged;
    }
    
    public static void closeFrame(final Frame aClosingFrame) {
        assert aClosingFrame != null;
        if (aClosingFrame == JStellaDesktop.myPlayer && isRepositoryMode()) {
            JStellaDesktop.myPlayer.setVisible(false);
            JStellaDesktop.myRepository.setVisible(true);
        }
        else {
            final int zResult = JOptionPane.showConfirmDialog(aClosingFrame, "Exit JStella?", "Exit", 0);
            if (zResult == 0) {
                System.exit(0);
            }
        }
    }
    
    private static boolean isRepositoryMode() {
        return JStellaDesktop.myRepository != null;
    }
    
    public static void runROM(final byte[] aROMData, final String aSimpleName, final Map<String, String> aConfigMap) {
        if (JStellaDesktop.myPlayer == null) {
            JStellaDesktop.myPlayer = new JStellaPlayer();
        }
        if (JStellaDesktop.myRepository != null) {
            JStellaDesktop.myPlayer.setLocation(JStellaDesktop.myRepository.getLocation());
            JStellaDesktop.myRepository.setVisible(false);
        }
        JStellaDesktop.myPlayer.loadROM(aROMData, aSimpleName, aConfigMap);
    }
    
    public static void playJStellaGame(final JStellaGame aGame) {
        if (JStellaDesktop.myPlayer == null) {
            JStellaDesktop.myPlayer = new JStellaPlayer();
        }
        if (JStellaDesktop.myRepository != null) {
            JStellaDesktop.myPlayer.setLocation(JStellaDesktop.myRepository.getLocation());
            JStellaDesktop.myRepository.setVisible(false);
        }
        JStellaDesktop.myPlayer.playJStellaGame(aGame);
    }
    
    public static File getRepositoryDirectory() {
        File zReturn = null;
        if (getConfiguration().containsKey("jstella.dir.repository")) {
            final String zReposDirString = getConfiguration().get("jstella.dir.repository");
            final File zReposDir = new File(zReposDirString);
            if (zReposDir.isDirectory()) {
                zReturn = zReposDir;
            }
        }
        else {
            final File zUserDataDir = getUserDataDirectory();
            if (zUserDataDir != null && zUserDataDir.exists() && zUserDataDir.isDirectory()) {
                final File zReposDir = new File(zUserDataDir, "repository");
                final boolean zSuccess = validateDirectory(zReposDir);
                if (zSuccess) {
                    zReturn = zReposDir;
                }
            }
        }
        return zReturn;
    }
    
    private static void showErrorMessage(final String aMessage) {
        JOptionPane.showMessageDialog(null, aMessage, "JStella error", 0);
    }
    
    private static boolean validateDirectory(final File aDirectory) {
        boolean zValidated = false;
        if (aDirectory.isFile()) {
            showErrorMessage("Not a directory: " + aDirectory.getParent());
        }
        else {
            if (!aDirectory.exists()) {
                final boolean zCreated = aDirectory.mkdir();
                if (!zCreated) {
                    showErrorMessage("Unable to create directory: " + aDirectory.getPath());
                }
            }
            zValidated = aDirectory.isDirectory();
        }
        return zValidated;
    }
    
    public static boolean setUserDataDirectory(final File aDirectory) {
        if (aDirectory.isFile()) {
            showErrorMessage("Specified item is not a directory");
            return false;
        }
        if (!aDirectory.exists()) {
            final int zResult = JOptionPane.showConfirmDialog(null, "" + aDirectory.getPath() + " does not exist.  Create?", "Create directory?", 0);
            if (zResult != 0) {
                return false;
            }
        }
        boolean zSuccess = true;
        zSuccess = validateDirectory(aDirectory);
        if (!zSuccess) {
            return false;
        }
        zSuccess = validateDirectory(new File(aDirectory, "savedgames"));
        if (!zSuccess) {
            return false;
        }
        zSuccess = validateDirectory(new File(aDirectory, "repository"));
        if (!zSuccess) {
            return false;
        }
        getConfiguration().put("jstella.dir.userdata", aDirectory.getPath());
        System.out.println("Stored user data directory: " + aDirectory.getPath());
        saveConfig();
        return true;
    }
    
    public static File getUserDataDirectory() {
        final File zReturn = null;
        String zDirName = getConfiguration().get("jstella.dir.userdata");
        if (zDirName == null) {
            zDirName = "";
        }
        return new File(zDirName);
    }
    
    private static boolean loadConfig() {
        boolean zSuccess = false;
        JStellaRunnerUtil.clearConfiguration();
        try {
            String zWorkingDir = getConfigurationDirectory();
            if (zWorkingDir == null) {
                zWorkingDir = "";
            }
            final File zCfgFile = new File(zWorkingDir, "jstella.cfg");
            if (zCfgFile.exists()) {
                final FileInputStream zFIS = new FileInputStream(zCfgFile);
                final Map<String, String> zLoadedConfig = JStellaRunnerUtil.readConfigFromStream(zFIS);
                getConfiguration().putAll(zLoadedConfig);
                zFIS.close();
                zSuccess = true;
            }
            else {
                System.out.println("JStella configuration file does not exist");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return zSuccess;
    }
    
    public static void saveConfig() {
        try {
            final String zUserDataDir = getConfiguration().get("jstella.dir.userdata");
            if (zUserDataDir != null) {
                setConfigurationDirectory(zUserDataDir);
            }
            String zWorkingDir = getConfigurationDirectory();
            if (zWorkingDir == null) {
                zWorkingDir = "";
            }
            final File zCfgFile = new File(zWorkingDir, "jstella.cfg");
            if (!zCfgFile.exists()) {
                System.out.println("Creating new configuratio file");
                zCfgFile.createNewFile();
            }
            if (zCfgFile.exists()) {
                final FileOutputStream zFOS = new FileOutputStream(zCfgFile);
                JStellaRunnerUtil.writeConfigToStream(zFOS, getConfiguration());
                zFOS.close();
            }
            else {
                System.out.println("Warning: was unable to create configuration file");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static JStellaPlayer getPlayer() {
        return JStellaDesktop.myPlayer;
    }
    
    private static void setConfigurationDirectory(final String aConfigurationDirectory) {
        try {
            storeMuffin("configdir", aConfigurationDirectory);
        }
        catch (IOException e) {
            System.out.println("Unable to store config directory as muffin");
        }
    }
    
    private static String getConfigurationDirectory() {
        String zConfig = null;
        try {
            final FileContents zFileContents = getWebStartMuffinInput("configdir");
            if (zFileContents != null) {
                final String zStr = readStringFromFileContents(zFileContents);
                System.out.println("Config file directory=" + zStr + " (Web Start muffin)");
                zConfig = zStr.trim();
            }
            else {
                String zWorkingDir = System.getProperty("user.dir");
                System.out.println("Config file directory=" + zWorkingDir + " (no Web Start muffin detected)");
                if (zWorkingDir == null) {
                    zWorkingDir = "";
                }
                zConfig = zWorkingDir;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return zConfig;
    }
    
    private static URL getJStellaMuffinURL(final String aMuffinID) throws UnavailableServiceException {
        URL zMuffinURL = null;
        try {
            final BasicService zBasicService = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
            final URL zCodeBaseURL = zBasicService.getCodeBase();
            final URL zJStellaURL = new URL(zCodeBaseURL, "jstella");
            zMuffinURL = new URL(zJStellaURL, aMuffinID);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return zMuffinURL;
    }
    
    private static String readStringFromFileContents(final FileContents aFileContents) throws IOException {
        final StringBuffer zSB = new StringBuffer();
        final InputStream zIS = aFileContents.getInputStream();
        final BufferedReader zBR = new BufferedReader(new InputStreamReader(zIS));
        final char[] zCharBuffer = new char[5];
        int zCharsRead = 0;
        while ((zCharsRead = zBR.read(zCharBuffer)) > 0) {
            zSB.append(zCharBuffer, 0, zCharsRead);
        }
        zIS.close();
        return zSB.toString();
    }
    
    public static FileContents getWebStartMuffinInput(final String aMuffinID) throws IOException, FileNotFoundException {
        FileContents zReturn = null;
        try {
            final byte[] zBuffer = null;
            final PersistenceService zPersistenceService = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
            if (zPersistenceService != null) {
                final URL zMuffinURL = getJStellaMuffinURL(aMuffinID);
                zReturn = zPersistenceService.get(zMuffinURL);
            }
        }
        catch (UnavailableServiceException e) {}
        catch (FileNotFoundException ex) {}
        return zReturn;
    }
    
    public static void storeMuffin(final String aMuffinID, final String aMuffinValue) throws IOException {
        storeMuffin(aMuffinID, aMuffinValue.getBytes());
    }
    
    public static void storeMuffin(final String aMuffinID, final byte[] aMuffinData) throws IOException {
        OutputStream zOS = null;
        try {
            final PersistenceService zPersistenceService = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
            if (zPersistenceService != null) {
                try {
                    final URL zMuffinURL = getJStellaMuffinURL(aMuffinID);
                    FileContents zFC = null;
                    try {
                        zFC = zPersistenceService.get(zMuffinURL);
                        zPersistenceService.delete(zMuffinURL);
                        zFC = null;
                    }
                    catch (FileNotFoundException ex) {}
                    final long zLength = zPersistenceService.create(zMuffinURL, (long)aMuffinData.length);
                    if (zLength >= aMuffinData.length) {
                        zFC = zPersistenceService.get(zMuffinURL);
                        zOS = new BufferedOutputStream(zFC.getOutputStream(true));
                        zOS.write(aMuffinData);
                        zOS.close();
                    }
                    else {
                        System.out.println("Error : allocated item isn't large enough to hold muffin");
                    }
                }
                finally {
                    if (zOS != null) {
                        zOS.close();
                    }
                }
            }
        }
        catch (UnavailableServiceException ex2) {}
    }
    
    protected static JFileChooser getFileBrowser() {
        if (JStellaDesktop.myFileBrowser == null) {
            System.out.println("Creating file browser...");
            JStellaDesktop.myFileBrowser = new JFileChooser();
        }
        return JStellaDesktop.myFileBrowser;
    }
    
    protected static void configureFileBrowser(final boolean aAllowMultipleSelection, final boolean aAllowDirectorySelection, final boolean aAllowFileSelection, final File aCurrentDir, final File aDefaultFile, final javax.swing.filechooser.FileFilter aFilter) {
        int zFileSelectionMode = 2;
        if (!aAllowFileSelection) {
            zFileSelectionMode = 1;
        }
        else if (!aAllowDirectorySelection) {
            zFileSelectionMode = 0;
        }
        getFileBrowser().setFileSelectionMode(zFileSelectionMode);
        getFileBrowser().setMultiSelectionEnabled(aAllowMultipleSelection);
        getFileBrowser().setCurrentDirectory(aCurrentDir);
        getFileBrowser().setSelectedFile(aDefaultFile);
        getFileBrowser().setFileFilter(aFilter);
    }
    
    public static byte[] toByteArray(final Object aObj) throws IOException {
        byte[] zReturn = null;
        final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
        final ObjectOutputStream zOOS = new ObjectOutputStream(zBAOS);
        zOOS.writeObject(aObj);
        zOOS.close();
        zReturn = zBAOS.toByteArray();
        return zReturn;
    }
    
    public static Object toObject(final byte[] aByteArray) throws IOException, ClassNotFoundException {
        Object zReturn = null;
        final ByteArrayInputStream zBAIS = new ByteArrayInputStream(aByteArray);
        final ObjectInputStream zOIS = new ObjectInputStream(zBAIS);
        zReturn = zOIS.readObject();
        zOIS.close();
        return zReturn;
    }
    
    public static byte[] readStream(final InputStream aStream) throws IOException {
        final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
        int zInt = 0;
        while ((zInt = aStream.read()) != -1) {
            zBAOS.write(zInt);
        }
        zBAOS.close();
        return zBAOS.toByteArray();
    }
    
    static {
        JStellaDesktop.myPlayer = null;
        JStellaDesktop.myRepository = null;
        JStellaDesktop.mySetupWizard = null;
        JStellaDesktop.myFileBrowser = null;
        JStellaDesktop.myIcon64 = null;
        JStellaDesktop.myIcon32 = null;
    }
    
    public static class JSFileNameExtensionFilter extends javax.swing.filechooser.FileFilter implements FileFilter
    {
        private static final String[] EXTENSIONS_ROM;
        private static final String[] EXTENSIONS_SAVEDSTATE;
        private static final String[] EXTENSIONS_GRAPHIC;
        private static final String[] EXTENSIONS_JSTELLAGAME;
        private static final String[] EXTENSIONS_METADATA_COLLECTION;
        private static final String[] EXTENSIONS_ZIP;
        public static final JSFileNameExtensionFilter FILTER_ROMS;
        public static final JSFileNameExtensionFilter FILTER_JSTELLA_STATE;
        public static final JSFileNameExtensionFilter FILTER_GRAPHICS;
        public static final JSFileNameExtensionFilter FILTER_JSTELLAGAME;
        public static final JSFileNameExtensionFilter FILTER_METADATA_COLLECTION;
        public static final JSFileNameExtensionFilter FILTER_ZIP_FILE;
        private String myDescription;
        private String[] myExtensions;
        
        public JSFileNameExtensionFilter(final String aDescription, final String[] aExtensionSet) {
            this.myDescription = "";
            this.myExtensions = new String[0];
            this.myDescription = aDescription;
            this.myExtensions = aExtensionSet;
        }
        
        public String getDescription() {
            return this.myDescription;
        }
        
        public boolean accept(final File f) {
            return (f != null && this.validExtension(f, this.myExtensions)) || !f.isFile();
        }
        
        private boolean validExtension(final File f, final String[] aExtensionSet) {
            for (final String thisExtension : aExtensionSet) {
                if (f.getPath().toLowerCase().endsWith("." + thisExtension.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean validExtension(final String aFilename) {
            for (final String thisExtension : this.myExtensions) {
                if (aFilename.toLowerCase().endsWith("." + thisExtension.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        
        public String getMainExtension() {
            return this.myExtensions[0];
        }
        
        static {
            EXTENSIONS_ROM = new String[] { "bin", "a26" };
            EXTENSIONS_SAVEDSTATE = new String[] { "jssg" };
            EXTENSIONS_GRAPHIC = new String[] { "gif", "png", "jpg", "jpeg" };
            EXTENSIONS_JSTELLAGAME = new String[] { "j26" };
            EXTENSIONS_METADATA_COLLECTION = new String[] { "j26mc" };
            EXTENSIONS_ZIP = new String[] { "zip" };
            FILTER_ROMS = new JSFileNameExtensionFilter("2600 ROM file (*.bin, *.a26)", JSFileNameExtensionFilter.EXTENSIONS_ROM);
            FILTER_JSTELLA_STATE = new JSFileNameExtensionFilter("JStella saved game (*.jssg)", JSFileNameExtensionFilter.EXTENSIONS_SAVEDSTATE);
            FILTER_GRAPHICS = new JSFileNameExtensionFilter("Graphic files (*.gif, *.png, *.jpg, *.jpeg)", JSFileNameExtensionFilter.EXTENSIONS_GRAPHIC);
            FILTER_JSTELLAGAME = new JSFileNameExtensionFilter("JStella game (*.j26)", JSFileNameExtensionFilter.EXTENSIONS_JSTELLAGAME);
            FILTER_METADATA_COLLECTION = new JSFileNameExtensionFilter("JStella metadata collection (*.j26mc)", JSFileNameExtensionFilter.EXTENSIONS_METADATA_COLLECTION);
            FILTER_ZIP_FILE = new JSFileNameExtensionFilter("ZIP file", JSFileNameExtensionFilter.EXTENSIONS_ZIP);
        }
    }
    
    public static class DirectoryFileFilter extends FileFilter
    {
        public static final DirectoryFileFilter DIRECTORY_FILEFILTER;
        
        public boolean accept(final File f) {
            return f.isDirectory();
        }
        
        public String getDescription() {
            return "Directory";
        }
        
        static {
            DIRECTORY_FILEFILTER = new DirectoryFileFilter();
        }
    }
}
