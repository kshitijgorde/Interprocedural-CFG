// 
// Decompiled by Procyon v0.5.30
// 

package actiplay;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpDownload
{
    private int _size;
    private ArrayList _listSock;
    private final String _host;
    private final String _path;
    private final String _exec;
    private final String _httpFile;
    private final int _nbthread;
    public static float _percent;
    private int _state;
    
    public HttpDownload(final String host, final String path, final String exec, final int thread) {
        ActiSkuInstaller.println("DEBUG : HttpDownload : HttpDownload( " + host + " , " + path + " , " + exec + " )");
        this._host = host;
        this._path = path;
        this._exec = exec;
        this._nbthread = thread;
        this._listSock = new ArrayList();
        this._httpFile = "http://" + this._host + this._path + this._exec;
        this._state = -1;
        this.init();
    }
    
    private boolean init() {
        ActiSkuInstaller.print("DEBUG : HttpDownload : init() ");
        try {
            final URL u = new URL(this._httpFile);
            final HttpURLConnection con = (HttpURLConnection)u.openConnection();
            this._size = con.getContentLength();
            ActiSkuInstaller._size = this._size;
            ActiSkuInstaller.println(": " + this._size);
            con.disconnect();
            this._state = 200;
            return true;
        }
        catch (MalformedURLException ex) {
            this._state = 202;
            return false;
        }
        catch (IOException ex2) {
            this._state = 203;
            return false;
        }
    }
    
    public boolean downloadPart(final int begin, final int size) {
        ActiSkuInstaller.println("DEBUG : HttpDownload : downloadPart( " + begin + " , " + size + " )");
        final String getCommand = "GET " + this._path + this._exec + " HTTP/1.1\r\n" + "Host: " + this._host + "\r\n" + "User-Agent: ActiGineHTTPModule\r\nRange: bytes=" + begin + "-\r\nConnection: close\r\n\r\n";
        try {
            final SocketThread sock = new SocketThread(new Socket(InetAddress.getByName(this._host), 80), getCommand, size);
            this._listSock.add(sock);
            sock.start();
            return true;
        }
        catch (IOException ex) {
            this._state = 204;
            return false;
        }
    }
    
    public boolean downloadFinished() {
        for (int i = 0; i < this._listSock.size(); ++i) {
            final int value = this._listSock.get(i).state();
            if (value == 300) {
                return false;
            }
            if (value > 301) {
                this._state = value;
                return true;
            }
        }
        return true;
    }
    
    public boolean save() {
        ActiSkuInstaller.println("DEBUG : HttpDownload : save( " + this._exec + " )");
        FileOutputStream wr = null;
        try {
            final String folder = System.getProperty("java.io.tmpdir") + "ActiSku";
            new File(folder).mkdir();
            final File filetmp = new File(folder + File.separator + this._exec);
            filetmp.createNewFile();
            wr = new FileOutputStream(filetmp);
            for (int i = 0; i < this._listSock.size(); ++i) {
                wr.write(this._listSock.get(i).getPartFile());
            }
            wr.close();
            this._state = 201;
            return true;
        }
        catch (FileNotFoundException ex) {
            this._state = 205;
            return false;
        }
        catch (IOException ex2) {
            this._state = 206;
            return false;
        }
    }
    
    public int getSize() {
        return this._size;
    }
    
    public int state() {
        return this._state;
    }
    
    public boolean download() {
        if (this._nbthread > 0) {
            return this.downloadWithThread();
        }
        return this.downloadWithoutThread();
    }
    
    private boolean downloadWithThread() {
        ActiSkuInstaller.println("DEBUG : HttpDownload : download( " + this._exec + " , " + this._nbthread + " ) : ");
        final long begin = Calendar.getInstance().getTime().getTime();
        final int partToDownload = this._size / this._nbthread;
        for (int i = 0; i < this._nbthread; ++i) {
            this.downloadPart(i * partToDownload, partToDownload);
        }
        if (this._state != 200) {
            return false;
        }
        int percenttmp = -1;
        while (!this.downloadFinished()) {
            HttpDownload._percent = 0.0f;
            for (int j = 0; j < this._nbthread; ++j) {
                HttpDownload._percent += this._listSock.get(j).getPercent();
            }
            HttpDownload._percent /= this._nbthread;
            ActiSkuInstaller._percent = HttpDownload._percent;
            if ((int)HttpDownload._percent % 10 == 0 && (int)HttpDownload._percent > percenttmp) {
                percenttmp = (int)HttpDownload._percent;
                ActiSkuInstaller.print((int)HttpDownload._percent + " ");
            }
        }
        if (this._state != 200) {
            return false;
        }
        final long end = Calendar.getInstance().getTime().getTime();
        final long totalTime = (end - begin) / 1000L;
        ActiSkuInstaller.println("");
        ActiSkuInstaller.println("DEBUG : HttpDownload : download() : " + this._size * 8 / totalTime / 1000.0f + "Kb/s (" + this._size / totalTime / 1000.0f + "Ko/s) (total time: " + (float)totalTime + " seconds) ");
        this.save();
        return this._state == 201;
    }
    
    private boolean downloadWithoutThread() {
        ActiSkuInstaller.print("DEBUG : ActiSkuInstaller : download( " + this._exec + " ) : ");
        try {
            final long begin = Calendar.getInstance().getTime().getTime();
            HttpURLConnection.setFollowRedirects(false);
            final URL u = new URL("http://" + this._host + this._path + this._exec);
            final String folder = System.getProperty("java.io.tmpdir") + "ActiSku";
            new File(folder).mkdir();
            final File filetmp = new File(folder + File.separator + this._exec);
            filetmp.createNewFile();
            final HttpURLConnection con = (HttpURLConnection)u.openConnection();
            con.setUseCaches(false);
            final int size = con.getContentLength();
            final InputStream str = con.getInputStream();
            final OutputStream wr = new FileOutputStream(filetmp);
            final byte[] buf = new byte[1024];
            int percenttmp = -1;
            HttpDownload._percent = 0.0f;
            int len;
            while ((len = str.read(buf)) > 0) {
                wr.write(buf, 0, len);
                HttpDownload._percent += len / size * 100.0f;
                ActiSkuInstaller._percent = HttpDownload._percent;
                if ((int)HttpDownload._percent % 10 == 0 && (int)HttpDownload._percent > percenttmp) {
                    percenttmp = (int)HttpDownload._percent;
                    ActiSkuInstaller.print((int)HttpDownload._percent + " ");
                }
            }
            wr.close();
            str.close();
            con.disconnect();
            final long end = Calendar.getInstance().getTime().getTime();
            final long totalTime = (end - begin) / 1000L;
            ActiSkuInstaller.println("");
            ActiSkuInstaller.println("DEBUG : ActiSkuInstaller : download() : " + size * 8 / totalTime / 1000.0f + "Kb/s (" + size / totalTime / 1000.0f + "Ko/s) (total time: " + (float)totalTime + " seconds) ");
            return true;
        }
        catch (MalformedURLException ex) {
            this._state = 207;
            return false;
        }
        catch (IOException ex2) {
            this._state = 208;
            return false;
        }
    }
    
    public class HTTP_STATE
    {
        public static final int UNKNOW = -1;
        public static final int WAITING = 200;
        public static final int SUCCESS = 201;
        public static final int FAILED_INIT_URL = 202;
        public static final int FAILED_INIT_IO = 203;
        public static final int FAILED_DOWN_SOCK_IO = 204;
        public static final int FAILED_SAVE_NOTFOUND = 205;
        public static final int FAILED_SAVE_IO = 206;
        public static final int FAILED_DOWN_NO_THREAD_URL = 207;
        public static final int FAILED_DOWN_NO_THREAD_IO = 208;
    }
}
