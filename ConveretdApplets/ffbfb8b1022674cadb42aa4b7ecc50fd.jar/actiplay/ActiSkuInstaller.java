// 
// Decompiled by Procyon v0.5.30
// 

package actiplay;

import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Calendar;
import java.security.Permission;
import java.security.AllPermission;
import java.io.File;
import javax.swing.JApplet;

public class ActiSkuInstaller extends JApplet implements Runnable
{
    public static long _timer;
    public static float _size;
    public static float _percent;
    private static boolean _debug;
    private final int BUFFER = 4096;
    private Thread _thread;
    private String _host;
    private String _path;
    private String _exec;
    private String _lang;
    private HttpDownload _http;
    private int _nbThread;
    private boolean _security;
    private int _idsetup;
    private int _os;
    private int _state;
    
    public ActiSkuInstaller() {
        this._host = "v2.actisku.com";
        this._exec = "ActiSkuInstall";
        this._path = "/plugin/0/";
        this._security = true;
        ActiSkuInstaller._percent = 0.0f;
        this._nbThread = 0;
        this._lang = "-en";
        ActiSkuInstaller._debug = true;
        this._os = this.os();
        if (this._os == 0) {
            this._exec += this._lang;
            this._exec += ".exe";
        }
        else {
            this._exec += ".zip";
        }
        this._http = new HttpDownload(this._host, this._path, this._exec, this._nbThread);
        this._state = -1;
    }
    
    private ActiSkuInstaller(final String host, final String exec, final String path) {
        this._host = host;
        this._exec = exec;
        this._path = path;
        this._security = true;
        ActiSkuInstaller._percent = 0.0f;
        this._nbThread = 6;
        this._lang = "-fr";
        ActiSkuInstaller._debug = true;
        this._os = this.os();
        if (this._os == 0) {
            this._exec += this._lang;
            this._exec += ".exe";
        }
        else {
            this._exec += ".zip";
        }
        this._http = new HttpDownload(this._host, this._path, this._exec, this._nbThread);
        this._state = -1;
    }
    
    public void init() {
        ActiSkuInstaller._debug = this.getParameter("Debug").equals("1");
        this._lang = this.getParameter("Lang");
        this._idsetup = Integer.parseInt(this.getParameter("IdSetup"));
        this._host = this.getParameter("HostName");
        this._exec = this.getParameter("ExecName");
        this._path = this.getParameter("PathName");
        this._nbThread = Integer.parseInt(this.getParameter("NbThread"));
        this._os = this.os();
        if (this._os == 0) {
            this._exec += this._lang;
            this._exec += ".exe";
        }
        else {
            this._exec += ".zip";
        }
        this._http = new HttpDownload(this._host, this._path, this._exec, this._nbThread);
        this._state = -1;
        if (this._host.equals("") || this._exec.equals("") || this._path.equals("") || this._lang.equals("")) {
            this._state = 103;
        }
        else {
            this._security = this.security();
            ActiSkuInstaller._percent = 0.0f;
        }
    }
    
    public void start() {
        println("DEBUG : INFO : _debug : " + ActiSkuInstaller._debug);
        println("DEBUG : INFO : _id : " + this._idsetup);
        println("DEBUG : INFO : _host : " + this._host);
        println("DEBUG : INFO : _exec : " + this._exec);
        println("DEBUG : INFO : _path : " + this._path);
        println("DEBUG : INFO : _lang : " + this._lang);
        println("DEBUG : INFO : _security : " + this._security);
        println("DEBUG : INFO : _percent : " + ActiSkuInstaller._percent);
        println("DEBUG : INFO : _state : " + this._state);
        println("DEBUG : -----------------");
        println("DEBUG : ActiSkuInstaller : start()");
        (this._thread = new Thread(this, "ActiSkuInstaller")).start();
    }
    
    public void run() {
        println("DEBUG : ActiSkuInstaller : run()");
        if (this._state == -1) {
            if (this._security) {
                this._state = 100;
                if (this.download()) {
                    final String folder = System.getProperty("java.io.tmpdir") + "ActiSku";
                    final File path = new File(folder);
                    if (this._os == 0) {
                        if (this.exec()) {
                            if (this.delete(path)) {
                                this._state = 101;
                                println("DEBUG : ActiSkuInstaller : run() : STATE.SUCCESS");
                            }
                            else {
                                this._state = 102;
                                println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_DELETE");
                            }
                        }
                        else {
                            this._state = 106;
                            println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_EXEC");
                        }
                    }
                    else if (this.unzip()) {
                        if (this.exec()) {
                            if (this.delete(path)) {
                                this._state = 101;
                                println("DEBUG : ActiSkuInstaller : run() : STATE.SUCCESS");
                            }
                            else {
                                this._state = 102;
                                println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_DELETE");
                            }
                        }
                        else {
                            this._state = 106;
                            println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_EXEC");
                        }
                    }
                    else {
                        this._state = 107;
                        println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_UNZIP");
                    }
                }
                else {
                    this._state = 105;
                    println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_DOWNLOAD");
                }
            }
            else {
                this._state = 104;
                println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_SECURITY");
            }
        }
        else {
            this._state = 103;
            println("DEBUG : ActiSkuInstaller : run() : STATE.FAILED_PARAMETER");
        }
        println("DEBUG : ActiSkuInstaller : run() : " + this.state());
    }
    
    private int os() {
        print("DEBUG : ActiSkuInstaller : os()");
        String nameOS = "os.name";
        nameOS = System.getProperty(nameOS).toLowerCase();
        println(" : " + nameOS);
        if (nameOS.indexOf("windows") >= 0) {
            return 0;
        }
        if (nameOS.indexOf("mac") >= 0) {
            return 2;
        }
        return 1;
    }
    
    private boolean security() {
        print("DEBUG : ActiSkuInstaller : security() : ");
        if (System.getSecurityManager() != null) {
            try {
                System.getSecurityManager().checkPermission(new AllPermission());
                println("");
                return true;
            }
            catch (SecurityException e) {
                return false;
            }
        }
        return false;
    }
    
    private boolean download() {
        println("DEBUG : ActiSkuInstaller : download()");
        ActiSkuInstaller._timer = Calendar.getInstance().getTime().getTime();
        return this._http.download();
    }
    
    private boolean exec() {
        println("DEBUG : ActiSkuInstaller : exec()");
        if (this._os == 0) {
            return this.launchWin();
        }
        return this.launchUnix();
    }
    
    private boolean unzip() {
        print("DEBUG : ActiSkuInstaller : unzip() : ");
        ZipInputStream in = null;
        try {
            final String folder = System.getProperty("java.io.tmpdir") + "ActiSku";
            final String outdir = System.getProperty("user.home") + File.separator + "Library" + File.separator + "ActiSku";
            println(folder + " --> " + outdir);
            this.delete(new File(outdir));
            final File inFile = new File(folder + File.separator + this._exec);
            new File(outdir).mkdir();
            final File outFolder = new File(outdir);
            BufferedOutputStream out = null;
            in = new ZipInputStream(new BufferedInputStream(new FileInputStream(inFile)));
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                this.mkdirRec(outFolder.getPath() + File.separator + entry.getName());
                if (!entry.isDirectory()) {
                    final byte[] data = new byte[4096];
                    out = new BufferedOutputStream(new FileOutputStream(outFolder.getPath() + File.separator + entry.getName()), 4096);
                    int count;
                    while ((count = in.read(data, 0, 4096)) != -1) {
                        out.write(data, 0, count);
                    }
                    out.flush();
                    out.close();
                }
            }
            in.close();
            final File ftmp = new File(outdir + File.separator + "param");
            final FileWriter fw = new FileWriter(ftmp);
            fw.write(outdir + File.separator + "bin" + File.separator + "releaseminsize" + File.separator + "x86" + File.separator + "ActiSkuMain.app");
            fw.write(" -ShopExplorer -SetupId=");
            fw.write("" + this._idsetup);
            fw.write(" -Silent");
            fw.flush();
            fw.close();
            return true;
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex2) {}
        return false;
    }
    
    public boolean delete(final File path) {
        println("DEBUG : ActiSkuInstaller : delete( " + path.getPath() + " )");
        if (path.exists()) {
            final File[] files = path.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    this.delete(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }
    
    private boolean launchUnix() {
        print("DEBUG : ActiSkuInstaller : launchUnix() : ");
        String app = System.getProperty("user.home") + File.separator + "Library" + File.separator + "ActiSku";
        app = app + File.separator + "bin" + File.separator + "releaseminsize" + File.separator + "x86" + File.separator;
        app = app + "ActiSkuMain.app" + File.separator;
        final String[] args = { "/bin/sh", "-c", "chmod 777 " + app + "Contents" + File.separator + "MacOS" + File.separator + "ActiSkuMain" };
        println(args);
        if (this.launch(args)) {
            final String[] args2 = { "/bin/sh", "-c", "open " + app };
            print("DEBUG : ActiSkuInstaller : launchUnix() : ");
            println(args2);
            return this.launch(args2, false);
        }
        return false;
    }
    
    private boolean launchWin() {
        print("DEBUG : ActiSkuInstaller : launchWin() : ");
        final String folder = System.getProperty("java.io.tmpdir") + "ActiSku";
        final String[] args = { "cmd.exe", "/c", folder + File.separator + this._exec };
        println(args);
        return this.launch(args);
    }
    
    private boolean launch(final String[] args) {
        return this.launch(args, true);
    }
    
    private boolean launch(final String[] args, final boolean waitFor) {
        print("DEBUG : ActiSkuInstaller : launch ( ");
        print(args);
        println(")");
        try {
            final Runtime runtime = Runtime.getRuntime();
            final Process process = runtime.exec(args);
            if (waitFor) {
                process.waitFor();
            }
            return true;
        }
        catch (IOException ex) {}
        catch (InterruptedException ex2) {}
        return false;
    }
    
    private void mkdirRec(final String path) {
        try {
            String tmp = "";
            for (int i = 0; i < path.length(); ++i) {
                tmp += path.charAt(i);
                if (path.charAt(i) == '\\' || path.charAt(i) == '/') {
                    new File(tmp).mkdir();
                }
            }
            final char c = path.charAt(path.length() - 1);
            if (c != '\\' && c != '/') {
                new File(tmp).createNewFile();
                println("DEBUG : ActiSkuInstaller : mkdirRec(" + path + ") : File : " + tmp);
            }
        }
        catch (IOException ex) {}
    }
    
    public int state() {
        if (this._http.state() > 201) {
            return this._http.state();
        }
        return this._state;
    }
    
    public int percent() {
        println("Percent : " + ActiSkuInstaller._percent + " %");
        return (int)ActiSkuInstaller._percent;
    }
    
    public float debit() {
        final float sizeTmp = ActiSkuInstaller._size * ActiSkuInstaller._percent / 100.0f;
        float debit = 0.0f;
        if (this.timeElapsed() > 0 && sizeTmp > 0.0f) {
            debit = sizeTmp / this.timeElapsed() / 1000.0f;
        }
        println("Debit : " + debit + " Ko/s");
        return debit;
    }
    
    public int timeElapsed() {
        final long time = (Calendar.getInstance().getTime().getTime() - ActiSkuInstaller._timer) / 1000L;
        println("Time : " + time + " s");
        return (int)time;
    }
    
    public static void print(final String s) {
        if (ActiSkuInstaller._debug) {
            System.out.print(s);
        }
    }
    
    public static void print(final String[] args) {
        if (ActiSkuInstaller._debug) {
            for (int i = 0; i < args.length; ++i) {
                System.out.print(args[i]);
                System.out.print(" ");
            }
        }
    }
    
    public static void println(final String[] args) {
        if (ActiSkuInstaller._debug) {
            for (int i = 0; i < args.length; ++i) {
                System.out.print(args[i]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void println(final String s) {
        if (ActiSkuInstaller._debug) {
            System.out.println(s);
        }
    }
    
    public static void main(final String[] args) {
        final ActiSkuInstaller asi = new ActiSkuInstaller("v2.actisku.com", "ActiSkuInstall", "/plugin/0/");
        asi.start();
    }
    
    static {
        ActiSkuInstaller._timer = 0L;
        ActiSkuInstaller._size = 0.0f;
        ActiSkuInstaller._percent = 0.0f;
        ActiSkuInstaller._debug = true;
    }
    
    public class OS
    {
        public static final int WINDOWS = 0;
        public static final int LINUX = 1;
        public static final int MAC = 2;
    }
    
    public class STATE
    {
        public static final int UNKNOW = -1;
        public static final int WAITING = 100;
        public static final int SUCCESS = 101;
        public static final int FAILED_DELETE = 102;
        public static final int FAILED_PARAMETER = 103;
        public static final int FAILED_SECURITY = 104;
        public static final int FAILED_DOWNLOAD = 105;
        public static final int FAILED_EXEC = 106;
        public static final int FAILED_UNZIP = 107;
    }
}
