import java.io.IOException;
import java.io.FileOutputStream;
import java.security.Permission;
import java.security.AccessController;
import java.io.FilePermission;
import java.io.FilenameFilter;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.awt.Desktop;
import java.io.File;
import java.applet.Applet;
import netscape.javascript.JSObject;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnipMP3Converter extends JApplet
{
    public void init() {
        try {
            if (!this.getDocumentBase().getHost().toLowerCase().contains("snipmp3.com")) {
                throw new SecurityException("java.io.* import failed.");
            }
            final JSObject mainWindow = JSObject.getWindow((Applet)this);
            mainWindow.eval("load();");
            if (this.getParameter("title") != null && this.getParameter("url") != null) {
                this.downloadVideo(this.getParameter("title"), this.getParameter("url"));
            }
            else if (this.getParameter("path") != null) {
                final File file = new File(this.getParameter("path"));
                Desktop.getDesktop().open(file.getParentFile());
            }
            else if (this.getParameter("u").contains("youtube.com")) {
                final String site = "youtube.com";
                final String ourl = this.getParameter("u");
                final String video_id = this.inbtwn(this.getParameter("u"), "v=", "&");
                final String pageSource = this.getUrlContent("http://www.youtube.com/watch?v=" + video_id + "&fmt=18", "GET");
                final String title = this.setHTMLEntity(this.inbtwn(pageSource, "title\" content=\"", "\""));
                final String flvlink = URLDecoder.decode(this.inbtwn(pageSource, "%2C5%7C", "&"), "UTF-8");
                final String imgurl = "http://i.ytimg.com/vi/" + video_id + "/default.jpg";
                if (flvlink != null) {
                    mainWindow.eval("show('" + title + "','" + flvlink + "','" + imgurl + "','" + site + "','" + ourl + "', 'Standard Quality');");
                }
                System.out.println("Video ID: " + video_id + "\r\n");
                System.out.println("Title: " + title + "\r\n");
                if (flvlink != null) {
                    System.out.println("FLV: " + flvlink + "\r\n");
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private String inbtwn(final String a, final String b, final String c) {
        try {
            final String[] arr1 = a.split(b);
            final String[] arr2 = arr1[1].split(c);
            if (arr2[0] == null) {
                return arr1[1];
            }
            return arr2[0];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private String getUrlContent(final String url, final String httpMethod) {
        try {
            final URL u = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("User-Agent", this.getParameter("ua"));
            final InputStream is = conn.getInputStream();
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toString();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    private String setHTMLEntity(final String input) {
        String output = "";
        try {
            output = input.replace("&amp;", "&").toString();
            output = output.replace("&lt;", "").toString();
            output = output.replace("&gt;", "").toString();
            output = output.replace("&#39;", "").toString();
            output = output.replace("&quot;", "").toString();
            output = output.replace("&", "").toString();
            output = output.replace("amp;", "").toString();
            output = output.replace("\\\"", "").toString();
            output = output.replace("\\'", "").toString();
            output = output.replace("'", "").toString();
            output = output.replace("'", "").toString();
            output = output.replace("<", "").toString();
            output = output.replace(">", "").toString();
            output = output.replace("?", "").toString();
            output = output.replace("/", "").toString();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return input;
        }
        return output;
    }
    
    public static String getSaveFilePath(String title) {
        final FileDialog fd = new FileDialog(new Frame(), "Save As...", 1);
        title = title.replaceAll("[/\\:*?\"<>'|]", "");
        title = title.replaceAll("(&[^;]+)", "");
        title = title.replace(":", "");
        fd.setFilenameFilter(new FilenameFilter() {
            public boolean accept(final File dir, final String name) {
                return name.endsWith(".mp3");
            }
        });
        fd.setFile(title + ".mp3");
        fd.setVisible(true);
        String str3 = fd.getFile();
        final String str4 = fd.getDirectory();
        if (str4 == null) {
            return null;
        }
        str3 = str3.replace(".mp3", "");
        final String str5;
        str3 = (str5 = str3 + ".mp3");
        final File localFile = new File(str4, str5);
        System.out.println("Output Path: " + str4 + str5 + "\r\n");
        final String compath = str4 + str5;
        final FilePermission perm = new FilePermission("file://" + compath.replace("\\", "/"), "write");
        AccessController.checkPermission(perm);
        return localFile.getPath();
    }
    
    public void downloadVideo(String title, final String url) {
        title = this.setHTMLEntity(title);
        final JSObject mainWindow = JSObject.getWindow((Applet)this);
        final HttpURLConnection conn = null;
        FlvAudioStream localFlvAudioStream = null;
        FileOutputStream localFileOutputStream = null;
        String outputpath = null;
        int i = 0;
        int j = 0;
        int k = 0;
        final int m = 0;
        final byte[] arrayOfByte = new byte[512];
        try {
            outputpath = getSaveFilePath(title);
            if (outputpath != null) {
                final String out2 = outputpath.replace("\\", "\\\\");
                mainWindow.eval("dir=\"" + out2 + "\";");
                localFileOutputStream = new FileOutputStream(outputpath);
                final URL u = new URL(url);
                final HttpURLConnection conn2 = (HttpURLConnection)u.openConnection();
                conn2.connect();
                k = conn2.getContentLength();
                System.out.println("Total Bytes: " + k + "\r\n");
                localFlvAudioStream = new FlvAudioStream(conn2.getInputStream());
                while ((i = localFlvAudioStream.read(arrayOfByte, 0, 512)) > 0) {
                    localFileOutputStream.write(arrayOfByte, 0, i);
                    j = (int)localFlvAudioStream.getInputOffset();
                    if (j != k) {
                        mainWindow.eval("isdl=true;");
                    }
                    else {
                        mainWindow.eval("isdl=false;");
                    }
                    mainWindow.eval("progress('" + j + "','" + k + "');");
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            try {
                if (localFlvAudioStream != null) {
                    localFlvAudioStream.close();
                }
                if (localFileOutputStream != null) {
                    localFileOutputStream.close();
                }
            }
            catch (IOException ex2) {
                System.out.println(ex2);
            }
        }
        finally {
            try {
                if (localFlvAudioStream != null) {
                    localFlvAudioStream.close();
                }
                if (localFileOutputStream != null) {
                    localFileOutputStream.close();
                }
            }
            catch (IOException ex3) {
                System.out.println(ex3);
            }
        }
    }
}
