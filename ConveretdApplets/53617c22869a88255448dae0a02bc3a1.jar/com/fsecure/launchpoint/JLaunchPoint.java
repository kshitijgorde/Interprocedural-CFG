// 
// Decompiled by Procyon v0.5.30
// 

package com.fsecure.launchpoint;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.File;
import netscape.javascript.JSException;
import java.security.AccessControlException;
import netscape.javascript.JSObject;
import java.applet.Applet;

public final class JLaunchPoint extends Applet
{
    private final String VALUE_EMPTY = "";
    private final String XML_CONFIG_FILE = "/config.xml";
    private final String URL_REQUEST_METHOD_GET = "GET";
    private final int URL_READ_TIME_OUT = 15000;
    private JSObject window;
    private String language;
    private String statusCheck;
    
    public JLaunchPoint() {
        this.statusCheck = "fail";
    }
    
    @Override
    public void init() {
        try {
            this.statusCheck = "fail";
            this.language = this.getParameter("language");
            this.statusCheck = "success";
        }
        catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + " init()");
        }
        catch (AccessControlException ex2) {
            System.out.println(ex2.getMessage() + " init()");
        }
        catch (Exception ex3) {
            this.statusCheck = "fail";
            this.setMessage("ERROR_OCCURED");
        }
    }
    
    @Override
    public void start() {
        try {
            this.startProcess();
        }
        catch (AccessControlException ex) {
            System.out.println(ex.getMessage() + " start()");
        }
        catch (NullPointerException ex2) {
            System.out.println(ex2.getMessage() + " start()");
        }
    }
    
    @Override
    public void stop() {
        System.out.println("Stop......");
    }
    
    @Override
    public void destroy() {
        System.out.println("Destroying......");
    }
    
    private final void setPercentage(final String s) {
        if (s.length() != 0) {
            try {
                (this.window = JSObject.getWindow((Applet)this)).eval("showStatus('" + s + "')");
            }
            catch (JSException ex) {
                ex.printStackTrace();
            }
            catch (NullPointerException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    private final void showLoading() {
        try {
            (this.window = JSObject.getWindow((Applet)this)).eval("showLoadingString()");
        }
        catch (JSException ex) {
            throw ex;
        }
    }
    
    private final void showSuccess() {
        try {
            (this.window = JSObject.getWindow((Applet)this)).eval("showSuccess()");
        }
        catch (JSException ex) {
            throw ex;
        }
    }
    
    private final void setMessage(final String s) {
        if (!s.equals("")) {
            try {
                (this.window = JSObject.getWindow((Applet)this)).eval("showMessage('" + s + "')");
            }
            catch (JSException ex) {
                ex.printStackTrace();
            }
            catch (NullPointerException ex2) {
                ex2.printStackTrace();
            }
            finally {
                this.window = null;
            }
        }
    }
    
    private final void startProcess() {
        try {
            String download = "";
            String name = "";
            final Class<JLaunchPoint> clazz = JLaunchPoint.class;
            this.getClass();
            final InputStream resourceAsStream = clazz.getResourceAsStream("/config.xml");
            if (resourceAsStream != null) {
                final JLaunchPointXMLReader launchPointXMLReader = new JLaunchPointXMLReader();
                if (launchPointXMLReader != null) {
                    launchPointXMLReader.setConfigStream(resourceAsStream);
                    launchPointXMLReader.digest();
                    download = launchPointXMLReader.getDownloadFrom();
                    name = new File(download).getName();
                }
                if (download != null && !"".equals(download) && name != null && !"".equals(name)) {
                    final String constructTempFilePath = JLaunchPointUtil.constructTempFilePath(name);
                    if (!"".equals(constructTempFilePath) && this.downloadFile(download, constructTempFilePath)) {
                        final JLaunchPointCheckSum launchPointCheckSum = new JLaunchPointCheckSum();
                        if (launchPointCheckSum.isCheckSumMatch(launchPointXMLReader.getCheckSum(), launchPointCheckSum.getCheckSum(constructTempFilePath))) {
                            try {
                                if (JLaunchPointUtil.executeProgram(constructTempFilePath, this.language)) {
                                    this.showSuccess();
                                }
                                else {
                                    this.setMessage("ERROR_OCCURED");
                                }
                            }
                            catch (Exception ex) {
                                this.setMessage("ERROR_OCCURED");
                            }
                        }
                        else {
                            this.setMessage("INVALID_BINARY");
                        }
                    }
                }
            }
        }
        catch (AccessControlException ex2) {
            this.setMessage("RESTART_BROWSER");
            System.exit(0);
        }
        catch (Exception ex3) {
            this.setMessage("ERROR_OCCURED");
            System.exit(0);
        }
    }
    
    private final boolean downloadFile(final String s, final String s2) {
        HttpURLConnection httpURLConnection = null;
        final byte[] array = new byte[1024];
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            JLaunchPointUtil.deleteDownloadedFile(s2);
            httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setUseCaches(false);
            try {
                httpURLConnection.connect();
            }
            catch (IOException ex5) {
                this.setMessage("DOWNLOAD_FAILED");
                httpURLConnection.disconnect();
                System.gc();
                return false;
            }
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2));
            }
            catch (IOException ex6) {
                this.setMessage("INSTANCE_RUNNING");
                httpURLConnection.disconnect();
                System.gc();
                return false;
            }
            int n = 0;
            final int contentLength = httpURLConnection.getContentLength();
            this.showLoading();
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
                final String showProgressioninPercentage = JLaunchPointUtil.showProgressioninPercentage(n, contentLength);
                try {
                    this.setPercentage(showProgressioninPercentage);
                }
                catch (JSException ex) {
                    ex.printStackTrace();
                }
                catch (NullPointerException ex2) {
                    ex2.printStackTrace();
                }
            }
            return true;
        }
        catch (Exception ex7) {
            this.setMessage("DOWNLOAD_FAILED");
            return false;
        }
        finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (IOException ex3) {
                    ex3.printStackTrace();
                    this.setMessage("DOWNLOAD_FAILED");
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                }
                catch (IOException ex4) {
                    ex4.printStackTrace();
                    this.setMessage("DOWNLOAD_FAILED");
                }
            }
            httpURLConnection.disconnect();
        }
    }
    
    public final String get_status() {
        return this.statusCheck;
    }
}
