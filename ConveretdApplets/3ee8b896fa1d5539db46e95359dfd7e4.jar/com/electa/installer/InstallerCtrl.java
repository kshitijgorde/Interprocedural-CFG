// 
// Decompiled by Procyon v0.5.30
// 

package com.electa.installer;

import java.util.Random;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.logging.Level;
import java.io.RandomAccessFile;
import java.io.File;
import java.awt.Component;
import java.util.regex.Pattern;
import java.util.logging.Logger;

public class InstallerCtrl
{
    private static Logger log;
    private InstallerView view;
    private Long totalReadBytes;
    private MainApplet applet;
    static final Pattern VERSION_PATTERN;
    
    static {
        InstallerCtrl.log = Logger.getLogger(InstallerCtrl.class.getName());
        VERSION_PATTERN = Pattern.compile("^Version=(.+)$");
    }
    
    public InstallerCtrl(final MainApplet applet) {
        this.totalReadBytes = 0L;
        this.view = new InstallerView();
        (this.applet = applet).add(this.view);
    }
    
    public void startProcess() {
        new Thread(new threadStartProcess((threadStartProcess)null)).start();
    }
    
    private boolean FileExist(final String AFile) {
        return new File(AFile).exists();
    }
    
    private String getCurrentVersion() {
        RandomAccessFile in = null;
        try {
            in = new RandomAccessFile(this.applet.versionFile, "r");
            boolean isVersionSection = false;
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("[")) {
                    isVersionSection = line.startsWith("[ProductVersion]");
                }
                else {
                    final Matcher m;
                    if (isVersionSection && (m = InstallerCtrl.VERSION_PATTERN.matcher(line)).find()) {
                        return m.group(1);
                    }
                    continue;
                }
            }
        }
        catch (Exception ex) {
            return "";
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (Exception e) {
                InstallerCtrl.log.log(Level.SEVERE, "Couldn't close versionLocalFile", e);
            }
        }
        try {
            if (in != null) {
                in.close();
            }
        }
        catch (Exception e) {
            InstallerCtrl.log.log(Level.SEVERE, "Couldn't close versionLocalFile", e);
        }
        return "";
    }
    
    private void updateStatusLabel(final String s) {
        this.view.progressBar.setString(s);
    }
    
    private boolean downloadFile(final String AFileURL, final String tempPath, final String tempFileName) {
        boolean res = false;
        try {
            final HttpURLConnection con = (HttpURLConnection)new URL(AFileURL).openConnection();
            con.setRequestProperty("Pragma", "no-cache");
            con.setIfModifiedSince(0L);
            con.setUseCaches(false);
            final int lcLen = con.getContentLength();
            this.view.progressBar.setMaximum(lcLen);
            con.connect();
            final File file = new File(String.valueOf(tempPath) + "\\" + tempFileName);
            synchronized (this) {
                final File parentDir = file.getParentFile();
                if (!parentDir.exists()) {
                    InstallerCtrl.log.info("Dir:" + parentDir.getPath() + " does not exist. Try force directories");
                    try {
                        parentDir.mkdirs();
                    }
                    catch (Exception e) {
                        InstallerCtrl.log.warning("Couldn't create dir:" + parentDir.getAbsolutePath());
                    }
                }
                if (file.exists()) {
                    InstallerCtrl.log.info("Previous file:" + file.getAbsolutePath() + " already exists. Try delete");
                    try {
                        if (!file.delete()) {
                            InstallerCtrl.log.severe("Couldn't delete previous file:" + file.getAbsolutePath());
                        }
                    }
                    catch (Exception e) {
                        InstallerCtrl.log.log(Level.SEVERE, "Couldn't delete previous file:" + file.getAbsolutePath(), e);
                    }
                }
            }
            final InputStream inp = con.getInputStream();
            final BufferedInputStream bufInp = new BufferedInputStream(inp);
            this.copyStream2File(bufInp, file);
            if (file.exists()) {
                InstallerCtrl.log.info("File was succesfully copied. Params:" + file.getName());
                res = true;
            }
            else {
                InstallerCtrl.log.severe("File download fialed. Params:" + file.getName());
                res = false;
            }
        }
        catch (IOException e2) {
            InstallerCtrl.log.log(Level.SEVERE, "Error save file", e2);
            res = false;
        }
        return res;
    }
    
    private void copyStream2File(final InputStream in, final File file) throws IOException {
        final int bufferSize = 1024;
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            final byte[] buf = new byte[1024];
            int readBytes = 0;
            while ((readBytes = in.read(buf)) != -1) {
                if (readBytes < 1024) {
                    out.write(buf, 0, readBytes);
                }
                else {
                    out.write(buf);
                }
                this.updateProgressBar(readBytes);
            }
        }
        catch (IOException e) {
            try {
                this.tryCloseOutputStream(out);
                file.deleteOnExit();
                file.delete();
            }
            catch (Exception ex) {}
            throw new IOException("Error saving file:" + file.getName());
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                this.tryCloseOutputStream(out);
            }
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            this.tryCloseOutputStream(out);
        }
    }
    
    private void tryCloseOutputStream(final BufferedOutputStream out) {
        try {
            out.close();
        }
        catch (Exception ex) {}
    }
    
    private boolean executeFile(final String AFile, final String AParams, final Boolean AWait) {
        return this.FileExist(AFile) && this.executeCmd(AFile, AParams, AWait);
    }
    
    private boolean executeCmd(final String cmd, final String parameters, final Boolean AWait) {
        try {
            int x = 0;
            InstallerCtrl.log.info("Try execute:" + cmd + " " + parameters);
            final Process process = new ProcessBuilder(new String[] { cmd, parameters }).start();
            if (AWait) {
                x = process.waitFor();
            }
            return x == 0;
        }
        catch (Exception e) {
            InstallerCtrl.log.log(Level.SEVERE, "Error execute:" + cmd + " " + parameters, e);
            return false;
        }
    }
    
    private synchronized void updateProgressBar(final long readBytes) {
        this.totalReadBytes += readBytes;
        this.view.progressBar.setValue((int)(Object)this.totalReadBytes);
    }
    
    private synchronized void updateProgressToZero() {
        this.view.progressBar.setValue(0);
    }
    
    private class threadStartProcess implements Runnable
    {
        public void run() {
            boolean lcRes = false;
            InstallerCtrl.this.applet.executeJS(InstallerCtrl.this.applet.onProcessStartedScript);
            InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInit);
            final Random r = new Random();
            final int i = r.nextInt(65535);
            final String lcTempFileName = "tmp_" + i + "_eLectaSetup.exe";
            String lcTempPath;
            try {
                lcTempPath = Utils.parsePath("%temp%");
            }
            catch (Throwable E) {
                lcTempPath = "C:\\eLectaTemp";
            }
            try {
                if (InstallerCtrl.this.applet.geteLectaVersion().equals(InstallerCtrl.this.getCurrentVersion()) && InstallerCtrl.this.FileExist(String.valueOf(InstallerCtrl.this.applet.getInstallPath()) + "\\" + InstallerCtrl.this.applet.runFile)) {
                    InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgLaunching);
                    lcRes = InstallerCtrl.this.executeFile(String.valueOf(InstallerCtrl.this.applet.getInstallPath()) + "\\" + InstallerCtrl.this.applet.runFile, InstallerCtrl.this.applet.getAccessString(), false);
                    InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgComplete);
                    InstallerCtrl.this.applet.executeFinalJS(lcRes);
                    Runtime.getRuntime().exit(0);
                }
                else {
                    InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgStartDownload);
                    boolean DownloadOK = false;
                    DownloadOK = InstallerCtrl.this.downloadFile(InstallerCtrl.this.applet.getSetupFileURL(), lcTempPath, lcTempFileName);
                    if (!DownloadOK) {
                        DownloadOK = InstallerCtrl.this.downloadFile(InstallerCtrl.this.applet.getSetupFileURL1(), lcTempPath, lcTempFileName);
                    }
                    if (DownloadOK) {
                        InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInit);
                        if (InstallerCtrl.this.executeFile(String.valueOf(lcTempPath) + "\\" + lcTempFileName, "/VERYSILENT", true)) {
                            InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInstallFinished);
                            if (InstallerCtrl.this.FileExist(String.valueOf(InstallerCtrl.this.applet.getInstallPath()) + "\\" + InstallerCtrl.this.applet.runFile)) {
                                InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgLaunching);
                                lcRes = InstallerCtrl.this.executeFile(String.valueOf(InstallerCtrl.this.applet.getInstallPath()) + "\\" + InstallerCtrl.this.applet.runFile, InstallerCtrl.this.applet.getAccessString(), false);
                                InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgComplete);
                                InstallerCtrl.this.applet.executeFinalJS(lcRes);
                                Runtime.getRuntime().exit(0);
                            }
                            else {
                                InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInstallFailed);
                                InstallerCtrl.this.updateProgressToZero();
                            }
                        }
                        else {
                            InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInstallFailed);
                            InstallerCtrl.this.updateProgressToZero();
                        }
                    }
                    else {
                        InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgDownloadFailed);
                        InstallerCtrl.this.updateProgressToZero();
                    }
                }
            }
            catch (Exception e) {
                InstallerCtrl.this.updateStatusLabel(InstallerCtrl.this.applet.msgInstallFailed);
                InstallerCtrl.this.updateProgressToZero();
            }
            InstallerCtrl.this.applet.executeFinalJS(lcRes);
        }
    }
}
