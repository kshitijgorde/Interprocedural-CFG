import com.vncproxy.web.tools.VNC123Session;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.net.Socket;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.Icon;
import java.net.ServerSocket;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.gotoservers.server.VNCServer;
import java.net.URL;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123Main extends JApplet
{
    private static final long serialVersionUID = 1L;
    private VNC123ComThread LocalToHub;
    private VNC123ComThread HubToLocal;
    private Process pVNCLocalExe;
    private String tmpDir;
    private String sessionId;
    private String localVNCServerPassword;
    private String initErrorMessage;
    private VNC123HttpsInit init;
    private VNC123ListenerThread listener;
    private VNC123DataInit dataInit;
    private boolean bye;
    private String userHashCode;
    private String appletType;
    private URL codeBase;
    private String initType;
    private String browser;
    private VNCServer vnc;
    
    public VNC123Main() {
        this.LocalToHub = null;
        this.HubToLocal = null;
        this.pVNCLocalExe = null;
        this.tmpDir = System.getProperty("java.io.tmpdir");
        this.sessionId = "0";
        this.localVNCServerPassword = null;
        this.initErrorMessage = null;
        this.init = null;
        this.listener = null;
        this.dataInit = null;
        this.bye = false;
        this.userHashCode = null;
        this.appletType = "Server";
        this.codeBase = null;
        this.initType = "https";
        this.browser = null;
        this.vnc = null;
    }
    
    public void init() {
        try {
            if (this.getParameter("appletType") != null) {
                this.appletType = this.getParameter("appletType");
            }
            System.out.println("VNC123" + this.appletType + "Starting ...");
            if (this.getParameter("codeBase") != null) {
                this.codeBase = new URL(this.getParameter("codeBase"));
            }
            else {
                this.codeBase = this.getCodeBase();
            }
            System.out.println("- codeBase : " + this.codeBase);
            if (this.getParameter("initType") != null) {
                this.initType = this.getParameter("initType");
            }
            System.out.println("- initType : " + this.initType);
            if (this.getParameter("browser") != null) {
                this.browser = this.getParameter("browser");
            }
            System.out.println("- browser : " + this.browser);
            final float javaVersion = Float.parseFloat(System.getProperty("java.version").substring(0, 3));
            System.out.println("- Java Version : " + javaVersion);
            if (javaVersion < 1.5) {
                throw new Exception("Invalid Java Version. You need Java >= 1.5...");
            }
            if (this.appletType.equals("Viewer")) {
                System.out.println("Extracting files to " + this.tmpDir);
                this.extractFileFromJar("appletKeyStore", String.valueOf(this.tmpDir) + "VNC123AppletKeyStore");
                this.extractFileFromJar("vncviewer.exe", String.valueOf(this.tmpDir) + "VNC123Viewer.exe");
                this.extractFileFromJar("VncViewer.jar", String.valueOf(this.tmpDir) + "vncviewer.jar");
                this.sessionId = JOptionPane.showInputDialog(this, "Enter the SID :\n", "VNC SID", -1).replaceAll(" ", "");
                this.init = new VNC123HttpsInit(this.codeBase, String.valueOf(this.tmpDir) + "VNC123AppletKeyStore", this.initType, this.browser);
                this.dataInit = this.init.comSession(this.sessionId, "Viewer", this.userHashCode, null);
                if (this.dataInit.error != null) {
                    throw new Exception(this.dataInit.error);
                }
                this.sessionId = this.dataInit.sessionId;
                final ServerSocket localServerSocket = new ServerSocket(0);
                final Object[] options = { "Windows executable", "EXPERIMENTAL : Java Client" };
                final int VNCViewerType = JOptionPane.showOptionDialog(this, "Please select the VNC viewer you want to use :", "VNC viewer type", 1, 3, null, options, options[0]);
                if (VNCViewerType == 0) {
                    System.out.println("Starting VNCViewer (win32) on port " + localServerSocket.getLocalPort());
                    this.pVNCLocalExe = Runtime.getRuntime().exec(String.valueOf(this.tmpDir) + "VNC123Viewer.exe" + " /8bit /scale 90 /compresslevel 9 127.0.0.1::" + localServerSocket.getLocalPort() + ":1");
                }
                else {
                    final String javaViewerPassword = JOptionPane.showInputDialog(this, "Enter the password :\n", "VNC PASSWORD", -1).replaceAll(" ", "");
                    final String javaRunString = String.valueOf(System.getProperty("java.home")) + "/bin/java -jar " + this.tmpDir + "VncViewer.jar HOST localhost PORT " + localServerSocket.getLocalPort() + " PASSWORD " + javaViewerPassword;
                    this.pVNCLocalExe = Runtime.getRuntime().exec(javaRunString);
                    System.out.println("Starting VNCViewer (Java) on port " + localServerSocket.getLocalPort());
                }
                System.out.println("Starting Tunnel ...");
                Socket localSocket = null;
                localSocket = localServerSocket.accept();
                (this.LocalToHub = new VNC123ComThread(localSocket, this.codeBase, this.dataInit.sessionId, this.dataInit.key, "LocalToHub", this.appletType)).start();
                (this.HubToLocal = new VNC123ComThread(localSocket, this.codeBase, this.dataInit.sessionId, this.dataInit.key, "HubToLocal", this.appletType)).start();
            }
            else {
                if (!this.appletType.equals("Server")) {
                    throw new Exception("Invalid appletType");
                }
                final String OSName = System.getProperty("os.name");
                System.out.println("- OS Name : " + OSName);
                if (!OSName.startsWith("Windows")) {
                    throw new Exception("Invalid OS. So far, Windows support only...");
                }
                System.out.println("Extracting files to " + this.tmpDir);
                this.extractFileFromJar("appletKeyStore", String.valueOf(this.tmpDir) + "VNC123AppletKeyStore");
                this.extractFileFromJar("vnchooks.dll", String.valueOf(this.tmpDir) + "vncHooks.dll");
                this.extractFileFromJar("winvnc.exe", String.valueOf(this.tmpDir) + "VNC123Server.exe");
                this.init = new VNC123HttpsInit(this.codeBase, String.valueOf(this.tmpDir) + "VNC123AppletKeyStore", this.initType, this.browser);
                this.dataInit = this.init.comSession(this.sessionId, "Server", this.userHashCode, null);
                this.sessionId = this.dataInit.sessionId;
                ServerSocket localServerSocket2 = null;
                int localVNCServerPort = 0;
                final Random rand = new Random();
                this.localVNCServerPassword = new StringBuilder().append(1000 + rand.nextInt(8999)).toString();
                localServerSocket2 = new ServerSocket(0);
                localVNCServerPort = localServerSocket2.getLocalPort();
                localServerSocket2.close();
                final Object[] options2 = { "Windows executable", "EXPERIMENTAL : Java applet" };
                final int VNCServerType = JOptionPane.showOptionDialog(this, "Please select the VNC server you want to use :", "VNC server type", 1, 3, null, options2, options2[0]);
                if (VNCServerType == 0) {
                    final PrintWriter pwConf = new PrintWriter(new FileWriter(String.valueOf(this.tmpDir) + "VNC123Server.exe.ini"));
                    pwConf.println("[Server]");
                    pwConf.println("PortNumber=" + localVNCServerPort);
                    pwConf.println("Password=" + this.localVNCServerPassword);
                    pwConf.close();
                    System.out.println("Running VNC Server on port : " + localVNCServerPort);
                    this.pVNCLocalExe = Runtime.getRuntime().exec(String.valueOf(this.tmpDir) + "VNC123Server.exe");
                }
                else {
                    (this.vnc = new VNCServer()).start(this.localVNCServerPassword, localVNCServerPort, "localhost");
                }
                System.out.println("Connecting to VNC Server ...");
                Socket localSocket2 = null;
                boolean connection = false;
                while (!connection) {
                    try {
                        localSocket2 = new Socket("localhost", localVNCServerPort);
                        connection = true;
                    }
                    catch (Exception e2) {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (Exception ex) {}
                    }
                }
                System.out.println("Starting Tunnel ...");
                (this.LocalToHub = new VNC123ComThread(localSocket2, this.codeBase, this.dataInit.sessionId, this.dataInit.key, "LocalToHub", this.appletType)).start();
                (this.HubToLocal = new VNC123ComThread(localSocket2, this.codeBase, this.dataInit.sessionId, this.dataInit.key, "HubToLocal", this.appletType)).start();
            }
        }
        catch (Exception e) {
            this.initErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void start() {
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
        final JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription("Menu");
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        final JMenuItem menuItem3 = new JMenuItem("Stats");
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                VNC123Main.this.comSessionStats("VNC Statistics");
            }
        });
        menu.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Close");
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                VNC123Main.this.stop();
            }
        });
        menu.add(menuItem4);
        this.setJMenuBar(menuBar);
        if (this.initErrorMessage != null) {
            System.out.println("Error on initilialization : " + this.initErrorMessage);
            textArea.append("Error on initilialization : \n- " + this.initErrorMessage + "\n");
        }
        else if (this.appletType.equals("Viewer")) {
            textArea.append("VNC Viewer started OK\n");
        }
        else {
            textArea.append("VNC Server started OK :\n");
            textArea.append("- SID : " + this.sessionId.substring(0, 3) + " " + this.sessionId.substring(3, 6) + "\n");
            textArea.append("- Password : " + this.localVNCServerPassword + "\n");
        }
        try {
            (this.listener = new VNC123ListenerThread(this, this.LocalToHub, this.HubToLocal, this.pVNCLocalExe)).start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        this.bye();
    }
    
    public void destroy() {
        this.bye();
    }
    
    public void bye() {
        System.out.println("Bye bye...");
        if (this.listener.isAlive()) {
            this.listener.stopMe();
        }
        if (!this.bye) {
            this.comSessionStats("Thank you for using VNC123 !");
        }
        this.bye = true;
        if (this.pVNCLocalExe != null) {
            this.pVNCLocalExe.destroy();
        }
        if (this.vnc != null) {
            this.vnc.close();
        }
        try {
            if (this.init != null) {
                this.init.comSession(this.sessionId, "Close", this.userHashCode, this.dataInit.key);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final List<String> fileList = new ArrayList<String>();
        fileList.add("VNC123Server.exe");
        fileList.add("VNC123Viewer.exe");
        fileList.add("VNC123Server.exe.ini");
        fileList.add("vnchooks.dll");
        fileList.add("VNC123AppletKeyStore");
        for (int index = 0; index < fileList.size(); ++index) {
            final File f = new File(String.valueOf(this.tmpDir) + fileList.get(index));
            if (f.isFile()) {
                System.out.println("Removing file : " + f.getAbsolutePath());
                f.delete();
            }
        }
    }
    
    public void extractFileFromJar(final String jarFile, final String localFile) throws Exception {
        OutputStream out = null;
        InputStream in = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            in = VNC123Main.class.getResourceAsStream(jarFile);
            final byte[] buffer = new byte[1024];
            long numWritten = 0L;
            int numRead;
            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
                numWritten += numRead;
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException ex) {}
        }
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        catch (IOException ex2) {}
    }
    
    public void comSessionStats(final String title) {
        try {
            if (this.init != null) {
                final VNC123DataInit theDataInit = this.init.comSession(this.sessionId, "Stats", this.userHashCode, this.dataInit.key);
                if (theDataInit.error == null) {
                    final VNC123Session theSession = theDataInit.session;
                    if (theSession != null) {
                        final TimeZone tz = TimeZone.getTimeZone("GMT");
                        final SimpleDateFormat sdf = new SimpleDateFormat("zzz yyyy-MM-dd HH:mm:ss");
                        sdf.setTimeZone(tz);
                        JOptionPane.showMessageDialog(this, "Session Id : " + theSession.sid + "\nStart Date : " + sdf.format(new Date(theSession.startDate)).toString() + "\nEnd Date : " + sdf.format(new Date(theSession.lastMove)).toString() + "\nNb Requets : " + theSession.nbRequests + "\nBytes : " + theSession.dataSize + "\n", title, -1);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Error : Session is null", title, -1);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, theDataInit.error, title, -1);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
