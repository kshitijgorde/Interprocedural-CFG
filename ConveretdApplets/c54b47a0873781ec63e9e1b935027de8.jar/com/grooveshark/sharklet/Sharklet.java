// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.MissingResourceException;
import java.util.Locale;
import java.net.MalformedURLException;
import com.grooveshark.ui.imageloader.LocalImageLoader;
import java.awt.Frame;
import com.grooveshark.songscanner.ScanFilter;
import com.grooveshark.songscanner.SongScanner;
import com.grooveshark.sharklet.ui.EditorPanel;
import java.awt.Container;
import com.grooveshark.net.uploader.UploaderListener;
import com.grooveshark.sharklet.controller.UploadProgress;
import com.grooveshark.sharklet.controller.UploaderController;
import com.grooveshark.sharklet.controller.ScanController;
import com.grooveshark.ui.wizard.WizardPanelListener;
import com.grooveshark.songscanner.SongScannerListener;
import com.grooveshark.sharklet.controller.ScanProgress;
import com.grooveshark.ui.wizard.WizardContentPanel;
import com.grooveshark.ui.wizard.WizardPanel;
import com.grooveshark.sharklet.ui.UploadStepPanel;
import com.grooveshark.sharklet.ui.mac.MacEditorStepPanel;
import com.grooveshark.sharklet.ui.EditorStepPanel;
import com.grooveshark.sharklet.ui.FolderStepPanel;
import com.grooveshark.ui.fileexplorer.FileExplorerModel;
import com.grooveshark.ui.table.SingleColumnTableModel;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.grooveshark.net.uploader.SongUploader;
import java.net.URL;
import com.grooveshark.ui.imageloader.ImageLoader;
import java.util.ResourceBundle;
import javax.swing.JApplet;

public class Sharklet extends JApplet
{
    private static final long serialVersionUID = -1864209012649492040L;
    private static boolean IS_JRE6;
    private static final String ARTIST_NAME = "artist_name";
    private static final String SESSION_ID = "session_id";
    private static final String SERVER_URL = "server_url";
    private static final String PAGE_URL = "page_url";
    private static final String LOCALE = "loc";
    private static final String RESOURCE_NAME = "Sharklet";
    private static final String IMAGES_FOLDER = "/images/";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_COUNTRY = "US";
    private static ResourceBundle messages;
    private static ImageLoader imageLoader;
    private static URL redirectUrl;
    private static JApplet applet;
    
    public void init() {
        System.out.println("Sharklet 11/18/11");
        Sharklet.IS_JRE6 = this.isApplicationSupported();
        if (!Sharklet.IS_JRE6 && this.canUpgradeToJre6()) {
            this.displayUnsupportedScreen();
        }
        this.disableJTaggerLogging();
        final String artistName = this.loadArtistName();
        final String sessionId = this.loadSessionId();
        final URL serverURL = this.loadServerURL();
        Sharklet.applet = this;
        Sharklet.redirectUrl = this.loadRedirectURL();
        Sharklet.messages = this.loadLocaleResource();
        this.setupImageLoader(serverURL);
        final Grooveshark library = new Grooveshark(serverURL.toString(), sessionId);
        final SongUploader uploader = new SongUploader(library, serverURL.toString(), sessionId);
        this.setupUI(library, uploader, artistName);
    }
    
    private void displayUnsupportedScreen() {
        JOptionPane.showMessageDialog(null, "This application requires Java 1.6", "Running Limited Version of Uploader", 0);
    }
    
    private boolean canUpgradeToJre6() {
        return true;
    }
    
    private void setupUI(final Grooveshark gsApi, final SongUploader uploader, final String artistName) {
        this.enableNativeLookAndFeel();
        final SingleColumnTableModel<Song> tableModel = new SingleColumnTableModel<Song>();
        final FileExplorerModel folderModel = new FileExplorerModel();
        final FolderStepPanel folderPanel = new FolderStepPanel(folderModel);
        EditorPanel editorStep = null;
        if (Sharklet.IS_JRE6) {
            editorStep = new EditorStepPanel(tableModel);
            System.out.println("Running Full Version of Uploader");
        }
        else {
            editorStep = new MacEditorStepPanel(tableModel);
        }
        final UploadStepPanel uploadStep = new UploadStepPanel(tableModel);
        final WizardPanel mainPanel = new WizardPanel();
        mainPanel.addStep(folderPanel);
        mainPanel.addStep(editorStep);
        mainPanel.addStep(uploadStep);
        final SongScanner scanner = this.createSongScanner();
        final ScanProgress scanProgress = new ScanProgress(mainPanel);
        scanner.addListener(scanProgress);
        mainPanel.addWizardListener(scanProgress);
        final ScanController scanController = new ScanController(folderModel, tableModel, scanner, artistName);
        scanner.addListener(scanController);
        scanner.addListener(editorStep);
        final UploaderController uploadController = new UploaderController(gsApi, uploader, tableModel);
        final UploadProgress uploadProgress = new UploadProgress(mainPanel);
        uploadController.addUploadListener(uploadProgress);
        mainPanel.addWizardListener(scanController);
        mainPanel.addWizardListener(uploadController);
        this.setContentPane(mainPanel);
    }
    
    private SongScanner createSongScanner() {
        final ScanFilter filter = new ScanFilter(new String[] { ".mp3" });
        final SongScanner songScanner = new SongScanner(filter);
        return songScanner;
    }
    
    public static Frame findParentFrame() {
        for (Container c = Sharklet.applet; c != null; c = c.getParent()) {
            if (c instanceof Frame) {
                return (Frame)c;
            }
        }
        return null;
    }
    
    private void setupImageLoader(final URL serverUrl) {
        Sharklet.imageLoader = new LocalImageLoader("/images/");
    }
    
    private String loadSessionId() {
        return this.getParameter("session_id");
    }
    
    private String loadArtistName() {
        String artistName = this.getParameter("artist_name");
        if (artistName == null) {
            artistName = "";
        }
        return artistName;
    }
    
    private boolean isApplicationSupported() {
        final String javaVersion = System.getProperty("java.version");
        return javaVersion.startsWith("1.6") || javaVersion.charAt(2) > '5';
    }
    
    private URL loadRedirectURL() {
        final String finishPageUrl = this.getParameter("page_url");
        try {
            return new URL(finishPageUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private URL loadServerURL() {
        final String serverUrl = this.getParameter("server_url");
        try {
            return new URL(serverUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getText(final String key) {
        return Sharklet.messages.getString(key);
    }
    
    private ResourceBundle loadLocaleResource() {
        String country = "US";
        String language = "en";
        final String locale = this.getParameter("loc");
        ResourceBundle resource = null;
        if (locale != null) {
            final String[] countryLanguage = locale.split("_");
            if (countryLanguage.length > 1) {
                language = countryLanguage[0];
                country = countryLanguage[1];
            }
        }
        try {
            final Locale currentLocale = new Locale(language, country);
            resource = ResourceBundle.getBundle("Sharklet", currentLocale);
        }
        catch (MissingResourceException e) {
            final Locale currentLocale2 = new Locale("en", "US");
            resource = ResourceBundle.getBundle("Sharklet", currentLocale2);
        }
        return resource;
    }
    
    private void disableJTaggerLogging() {
        LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
    }
    
    private void enableNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
    }
    
    public static void redirectToFinishPage() {
        Sharklet.applet.getAppletContext().showDocument(Sharklet.redirectUrl);
    }
    
    public static ImageIcon getImage(final String url) {
        return Sharklet.imageLoader.loadImage(url);
    }
}
