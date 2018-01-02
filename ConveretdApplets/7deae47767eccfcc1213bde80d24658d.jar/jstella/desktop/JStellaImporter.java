// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.awt.EventQueue;
import java.awt.Component;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import jstella.cart.Cartridge;
import jstella.runner.JStellaRunnerUtil;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.FileFilter;
import java.io.IOException;
import jstella.runner.JStellaGame;
import java.util.HashMap;
import javax.swing.ProgressMonitor;
import javax.swing.ProgressMonitorInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.Map;
import java.awt.Frame;

public class JStellaImporter implements Runnable
{
    private Frame myParent;
    private Map<String, File> myMD5FileMap;
    private InputStream myMetadataCollectionStream;
    private ProgressMonitorInputStream myProgressMonitorInputStream;
    private ProgressMonitor myROMProgressMonitor;
    private Runnable myResumeRunner;
    private File[] myROMFiles;
    private ImportMode myImportMode;
    
    public JStellaImporter(final Frame aParent, final Map<String, File> aMD5FileMap, final InputStream aMetadataCollectionStream, final Runnable aResumeRunner, final ImportMode aMode) {
        this.myParent = null;
        this.myMD5FileMap = new HashMap<String, File>();
        this.myMetadataCollectionStream = null;
        this.myProgressMonitorInputStream = null;
        this.myROMProgressMonitor = null;
        this.myResumeRunner = null;
        this.myROMFiles = null;
        this.myImportMode = ImportMode.METADATA;
        this.myParent = aParent;
        this.myMD5FileMap = aMD5FileMap;
        this.myMetadataCollectionStream = aMetadataCollectionStream;
        this.myResumeRunner = aResumeRunner;
        this.myImportMode = aMode;
    }
    
    public void setROMFiles(final File[] aFiles) {
        this.myROMFiles = aFiles;
    }
    
    protected static Map<String, File> createMD5FileMap(final File[] aGameFiles) {
        final Map<String, File> zReturn = new HashMap<String, File>();
        for (int i = 0; i < aGameFiles.length; ++i) {
            try {
                final JStellaGame zGame = JStellaGame.loadGameFile(aGameFiles[i]);
                if (zGame != null && zGame.getMD5() != null && !zGame.getMD5().equals("")) {
                    zReturn.put(zGame.getMD5(), aGameFiles[i]);
                }
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return zReturn;
    }
    
    protected static Map<String, File> createMD5FileMap(final File aDirectory) {
        final File[] zFileArray = aDirectory.listFiles(JStellaDesktop.JSFileNameExtensionFilter.FILTER_JSTELLAGAME);
        return createMD5FileMap(zFileArray);
    }
    
    public static Thread launchMetadataImporter(final Frame aParent, final Map<String, File> aMD5FileMap, final InputStream aMetadataCollectionStream, final Runnable aResumeRunner) {
        final JStellaImporter zImporter = new JStellaImporter(aParent, aMD5FileMap, aMetadataCollectionStream, aResumeRunner, ImportMode.METADATA);
        final Thread zReturn = new Thread(zImporter);
        zReturn.setDaemon(true);
        zReturn.start();
        return zReturn;
    }
    
    public static Thread launchROMAndMetadataImporter(final Frame aParent, final File[] aROMFiles, final InputStream aMetadataCollectionStream, final Runnable aResumeRunner) {
        final Map<String, File> zMD5FileMap = new HashMap<String, File>();
        final JStellaImporter zImporter = new JStellaImporter(aParent, zMD5FileMap, aMetadataCollectionStream, aResumeRunner, ImportMode.ROMS_AND_METADATA);
        zImporter.setROMFiles(aROMFiles);
        final Thread zReturn = new Thread(zImporter);
        zReturn.setDaemon(true);
        zReturn.start();
        return zReturn;
    }
    
    private static JStellaGame createGameFromROM(final byte[] aROMData, final String aPlainFilename) {
        JStellaGame zReturn = null;
        zReturn = new JStellaGame();
        zReturn.setGameFilename("" + aPlainFilename + ".j26");
        zReturn.setROMData(aROMData, true);
        return zReturn;
    }
    
    private List<String> createRepositoryMD5List() {
        return new ArrayList<String>(createMD5FileMap(JStellaDesktop.getRepositoryDirectory()).keySet());
    }
    
    private JStellaGame createGameFromROM(final File aROMFile) {
        final byte[] zROMData = JStellaRunnerUtil.readByteArrayFromFile(aROMFile);
        final String zFilenameWithoutExtension = JStellaRunnerUtil.getFilenameWithoutExtension(aROMFile);
        return createGameFromROM(zROMData, zFilenameWithoutExtension);
    }
    
    private File addROMDataToRepository(final byte[] aROMData, final String aSimpleName, final List<String> aMD5List) throws IOException {
        if (this.myROMProgressMonitor != null) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
        File zReturn = null;
        final String zMD5 = Cartridge.calculateMD5(aROMData);
        if (aMD5List == null || !aMD5List.contains(zMD5)) {
            if (aMD5List != null) {
                aMD5List.add(zMD5);
            }
            final JStellaGame zGame = createGameFromROM(aROMData, aSimpleName);
            final File zRepositoryDir = JStellaDesktop.getRepositoryDirectory();
            zReturn = zGame.saveGameFile(zRepositoryDir);
            if (zReturn != null) {
                this.myMD5FileMap.put(zMD5, zReturn);
            }
        }
        else {
            System.out.println("ROM " + aSimpleName + " is already in repository.");
        }
        return zReturn;
    }
    
    public File addROMToRepository(final File aROMFile, final List<String> aMD5List) throws IOException {
        final File zReturn = null;
        final byte[] zROMData = JStellaRunnerUtil.readByteArrayFromFile(aROMFile);
        this.addROMDataToRepository(zROMData, JStellaRunnerUtil.getFilenameWithoutExtension(aROMFile), aMD5List);
        return zReturn;
    }
    
    public File[] addROMsToRepository(final File[] aROMs, final List<String> aMD5List) throws IOException {
        System.out.println("Adding multiple ROMs to repository");
        final List<File> zGameFileList = new ArrayList<File>();
        if (this.myROMProgressMonitor != null) {
            this.myROMProgressMonitor.setMaximum(aROMs.length);
        }
        for (int i = 0; i < aROMs.length; ++i) {
            final File zFile = this.addROMToRepository(aROMs[i], aMD5List);
            if (zFile != null) {
                zGameFileList.add(zFile);
            }
            if (this.myROMProgressMonitor != null) {
                this.myROMProgressMonitor.setProgress(i);
                if (zFile != null) {
                    this.myROMProgressMonitor.setNote("" + zFile.getName());
                }
            }
            if (this.myROMProgressMonitor != null && this.myROMProgressMonitor.isCanceled()) {
                System.out.println("Cancelling ROM import");
                break;
            }
        }
        File[] zGameFiles = new File[zGameFileList.size()];
        zGameFiles = zGameFileList.toArray(zGameFiles);
        return zGameFiles;
    }
    
    public File[] addROMDirectoryToRepository(final File aROMDirectory, final List<String> aMD5List) throws IOException {
        System.out.println("Adding ROM directory to repository");
        assert aROMDirectory.isDirectory();
        File[] zReturn = null;
        if (aROMDirectory.isDirectory()) {
            final File[] zROMFiles = aROMDirectory.listFiles(JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS);
            if (zROMFiles.length > 0) {
                zReturn = this.addROMsToRepository(zROMFiles, aMD5List);
            }
        }
        return zReturn;
    }
    
    private static String getFilename(final ZipEntry aZipEntry) {
        final File zFile = new File(aZipEntry.getName());
        if (zFile != null) {
            return zFile.getName();
        }
        return null;
    }
    
    public File[] addROMZipToRepository(final File aROMZip, final List<String> aMD5List) throws IOException {
        final File[] zReturn = null;
        final ZipFile zZipFile = new ZipFile(aROMZip);
        final int zCount = zZipFile.size();
        if (this.myROMProgressMonitor != null) {
            this.myROMProgressMonitor.setMaximum(zCount);
        }
        int zIndex = 0;
        final Enumeration<? extends ZipEntry> e = zZipFile.entries();
        while (e.hasMoreElements()) {
            ++zIndex;
            final ZipEntry zZipEntry = (ZipEntry)e.nextElement();
            if (this.myROMProgressMonitor != null) {
                this.myROMProgressMonitor.setProgress(zIndex);
            }
            final String zFilename = getFilename(zZipEntry);
            if (JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS.validExtension(zFilename)) {
                System.out.println("Importing file from ZIP: " + zFilename);
                if (this.myROMProgressMonitor != null) {
                    this.myROMProgressMonitor.setNote(zFilename);
                }
                final InputStream zInputStream = zZipFile.getInputStream(zZipEntry);
                if (zInputStream != null) {
                    final byte[] zBytes = JStellaRunnerUtil.readByteArrayFromStream(zInputStream);
                    zInputStream.close();
                    this.addROMDataToRepository(zBytes, JStellaRunnerUtil.getFilenameWithoutExtension(zFilename), aMD5List);
                }
                else {
                    System.out.println("Warning: couldn't read ZIP entry - " + zFilename);
                }
            }
            if (this.myROMProgressMonitor != null && this.myROMProgressMonitor.isCanceled()) {
                System.out.println("Cancelling ROM import");
                break;
            }
        }
        return zReturn;
    }
    
    private void addUnknownToRepository(final File[] aUnknown, final List<String> aMD5List) throws IOException {
        System.out.println("Adding " + aUnknown.length + " files to repository");
        for (int i = 0; i < aUnknown.length; ++i) {
            if (aUnknown[i].isDirectory()) {
                this.addROMDirectoryToRepository(aUnknown[i], aMD5List);
            }
            else if (aUnknown[i].getName().toLowerCase().endsWith(".zip")) {
                this.addROMZipToRepository(aUnknown[i], aMD5List);
            }
            else if (JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS.accept(aUnknown[i])) {
                this.addROMToRepository(aUnknown[i], aMD5List);
            }
            else {
                System.out.println("ERROR: Invalid file type - " + aUnknown[i]);
            }
            if (this.myROMProgressMonitor != null && this.myROMProgressMonitor.isCanceled()) {
                break;
            }
        }
    }
    
    private void mergeMetadata(final Map<String, File> aMD5Map, final JStellaGame aMetadataSource) throws IOException, ClassNotFoundException {
        final File zTarget = aMD5Map.get(aMetadataSource.getMD5());
        if (zTarget != null) {
            System.out.println("Merging metadata: " + aMetadataSource);
            if (this.myProgressMonitorInputStream != null) {
                this.myProgressMonitorInputStream.getProgressMonitor().setNote("" + aMetadataSource.getGameTitle());
            }
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
            final File zRepositoryDir = zTarget.getParentFile();
            final JStellaGame zTargetGame = JStellaGame.loadGameFile(zTarget);
            zTargetGame.importMetadata(aMetadataSource);
            zTargetGame.saveGameFile(zRepositoryDir);
        }
    }
    
    protected void importMetadataItem(final ZipInputStream aZIS, final Map<String, File> aMD5Map) throws IOException, ClassNotFoundException {
        final byte[] zData = JStellaDesktop.readStream(aZIS);
        final Object zObj = JStellaDesktop.toObject(zData);
        if (zObj instanceof JStellaGame) {
            final JStellaGame zSource = (JStellaGame)zObj;
            this.mergeMetadata(aMD5Map, zSource);
        }
        else {
            System.out.println("JStella error: attempted to import a non-game");
        }
    }
    
    protected void importMetadata(final Map<String, File> aMD5FileMap, final InputStream aMetadataCollectionStream) throws IOException, ClassNotFoundException {
        final ZipInputStream zZIS = new ZipInputStream(aMetadataCollectionStream);
        ZipEntry zZipEntry = null;
        do {
            zZipEntry = zZIS.getNextEntry();
            if (zZipEntry != null) {
                this.importMetadataItem(zZIS, aMD5FileMap);
                zZIS.closeEntry();
            }
            if (this.myProgressMonitorInputStream != null && this.myProgressMonitorInputStream.getProgressMonitor().isCanceled()) {
                System.out.println("Cancelling metadata import");
                break;
            }
        } while (zZipEntry != null);
        System.out.println("Finished importing metadata");
        zZIS.close();
    }
    
    private void runROMMode() {
        System.out.println("Run ROM mode");
        try {
            (this.myROMProgressMonitor = new ProgressMonitor(this.myParent, "Adding ROM(s) to repository", "", 0, 10)).setMillisToPopup(1);
            final List<String> zMD5List = this.createRepositoryMD5List();
            this.addUnknownToRepository(this.myROMFiles, zMD5List);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (this.myROMProgressMonitor != null) {
                this.myROMProgressMonitor.close();
            }
        }
    }
    
    private void runMetadataMode() {
        System.out.println("Run metadata mode");
        if (this.myMetadataCollectionStream != null) {
            try {
                this.myProgressMonitorInputStream = new ProgressMonitorInputStream(this.myParent, "Assigning metadata", this.myMetadataCollectionStream);
                this.myProgressMonitorInputStream.getProgressMonitor().setMillisToPopup(10);
                this.importMetadata(this.myMD5FileMap, this.myProgressMonitorInputStream);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
            finally {
                if (this.myProgressMonitorInputStream != null) {
                    this.myProgressMonitorInputStream.getProgressMonitor().close();
                }
            }
        }
    }
    
    public void run() {
        System.out.println("Running importer");
        if (this.myImportMode.equals(ImportMode.ROMS) || this.myImportMode.equals(ImportMode.ROMS_AND_METADATA)) {
            this.runROMMode();
        }
        if (this.myImportMode.equals(ImportMode.METADATA) || this.myImportMode.equals(ImportMode.ROMS_AND_METADATA)) {
            this.runMetadataMode();
        }
        EventQueue.invokeLater(this.myResumeRunner);
    }
    
    public enum ImportMode
    {
        ROMS, 
        METADATA, 
        ROMS_AND_METADATA;
    }
}
