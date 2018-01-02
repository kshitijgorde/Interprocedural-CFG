import java.util.Hashtable;
import java.net.URISyntaxException;
import java.net.URI;
import java.awt.Desktop;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.io.IOException;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Properties;
import java.awt.Robot;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RunClient extends Applet implements ActionListener
{
    private Robot robot;
    private static final long serialVersionUID = 1L;
    public static Properties params;
    public JFrame mainFrame;
    public JPanel mainPane;
    public static String mainurl;
    public int lang;
    public String frameName;
    public JPanel totalPanel;
    private JMenuBar menuBar;
    private int capNum;
    private FilenameFilter filter;
    public static final Object[][] JMENU_BUTTONS;
    public static boolean rs;
    
    public static void main(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equalsIgnoreCase("servaddr")) {
                RunClient.mainurl = array[++i];
            }
        }
        new RunClient().doFrame();
    }
    
    public RunClient() {
        this.mainPane = new JPanel();
        this.lang = 0;
        this.frameName = "Epic Scape Client";
        this.menuBar = new JMenuBar();
        this.filter = new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return file.getName().equals("images") && s.endsWith("png");
            }
        };
        try {
            this.loadCaptureAmts();
            this.robot = new Robot();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadCaptureAmts() {
        this.capNum = new File("./images/").listFiles(this.filter).length;
    }
    
    @Override
    public void init() {
        this.doApplet();
    }
    
    void doApplet() {
        try {
            this.readVars();
            this.startClient();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void doFrame() {
        try {
            this.readVars();
            this.openFrame();
            this.startClient();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void readVars() throws IOException {
        ((Hashtable<String, String>)RunClient.params).put("cabbase", "g.cab");
        ((Hashtable<String, String>)RunClient.params).put("java_arguments", "-Xmx1024m -Dsun.java2d.noddraw=true");
        ((Hashtable<String, String>)RunClient.params).put("colourid", "0");
        ((Hashtable<String, String>)RunClient.params).put("worldid", "16");
        ((Hashtable<String, String>)RunClient.params).put("lobbyid", "15");
        ((Hashtable<String, String>)RunClient.params).put("demoid", "0");
        ((Hashtable<String, String>)RunClient.params).put("demoaddress", "");
        ((Hashtable<String, String>)RunClient.params).put("modewhere", "0");
        ((Hashtable<String, String>)RunClient.params).put("modewhat", "0");
        ((Hashtable<String, String>)RunClient.params).put("lang", "0");
        ((Hashtable<String, String>)RunClient.params).put("objecttag", "0");
        ((Hashtable<String, String>)RunClient.params).put("js", "1");
        ((Hashtable<String, String>)RunClient.params).put("game", "0");
        ((Hashtable<String, String>)RunClient.params).put("affid", "0");
        ((Hashtable<String, String>)RunClient.params).put("advert", "1");
        ((Hashtable<String, String>)RunClient.params).put("settings", "wwGlrZHF5gJcZl7tf7KSRh0MZLhiU0gI0xDX6DwZ-Qk");
        ((Hashtable<String, String>)RunClient.params).put("country", "0");
        ((Hashtable<String, String>)RunClient.params).put("haveie6", "0");
        ((Hashtable<String, String>)RunClient.params).put("havefirefox", "1");
        ((Hashtable<String, String>)RunClient.params).put("cookieprefix", "");
        ((Hashtable<String, String>)RunClient.params).put("cookiehost", "127.0.0.1");
        ((Hashtable<String, String>)RunClient.params).put("cachesubdirid", "0");
        ((Hashtable<String, String>)RunClient.params).put("crashurl", "");
        ((Hashtable<String, String>)RunClient.params).put("unsignedurl", "");
        ((Hashtable<String, String>)RunClient.params).put("sitesettings_member", "1");
        ((Hashtable<String, String>)RunClient.params).put("frombilling", "false");
        ((Hashtable<String, String>)RunClient.params).put("sskey", "");
        ((Hashtable<String, String>)RunClient.params).put("force64mb", "false");
        ((Hashtable<String, String>)RunClient.params).put("worldflags", "8");
        ((Hashtable<String, String>)RunClient.params).put("lobbyaddress", RunClient.mainurl);
    }
    
    public void openFrame() {
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        this.mainPane.setLayout(new BorderLayout());
        this.mainPane.add(this);
        this.mainPane.setPreferredSize(new Dimension(765, 503));
        (this.totalPanel = new JPanel()).setPreferredSize(new Dimension(765, 503));
        this.totalPanel.setLayout(new BorderLayout());
        this.totalPanel.add(this.mainPane, "Center");
        (this.mainFrame = new JFrame(this.frameName)).setLayout(new BorderLayout());
        this.mainFrame.getContentPane().add(this.totalPanel, "Center");
        this.mainFrame.setDefaultCloseOperation(3);
        this.setMenuBar();
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }
    
    private void setMenuBar() {
        int n = 0;
        for (int i = 0; i < RunClient.JMENU_BUTTONS.length; ++i) {
            final JMenu menu = new JMenu();
            for (int j = 0; j < RunClient.JMENU_BUTTONS[i].length; ++j) {
                if (j == 0) {
                    menu.setText((String)RunClient.JMENU_BUTTONS[i][j]);
                }
                else if (RunClient.JMENU_BUTTONS[i][j] instanceof JMenuItem) {
                    final JMenuItem menuItem = (JMenuItem)RunClient.JMENU_BUTTONS[i][j];
                    menu.add(menuItem);
                    menuItem.setActionCommand("" + n++);
                    menuItem.addActionListener(this);
                }
                else {
                    menu.add((Component)RunClient.JMENU_BUTTONS[i][j]);
                }
            }
            this.menuBar.add(menu);
        }
        this.mainFrame.setJMenuBar(this.menuBar);
    }
    
    public void startClient() {
        try {
            Applet_Sub1.provideLoaderApplet(this);
            final client client = new client();
            client.init();
            client.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String getParameter(final String s) {
        return ((Hashtable<K, String>)RunClient.params).get(s);
    }
    
    @Override
    public URL getDocumentBase() {
        return this.getCodeBase();
    }
    
    @Override
    public URL getCodeBase() {
        URL url;
        try {
            url = new URL("http://" + RunClient.mainurl);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return url;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        switch (Integer.parseInt(actionEvent.getActionCommand())) {
            case 0: {
                try {
                    Runtime.getRuntime().exec("java -jar dementhium-loader.jar");
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case 1: {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?") == 0) {
                    System.exit(0);
                    break;
                }
                break;
            }
            case 2: {
                this.browse("http://google.com");
                break;
            }
            case 3: {
                this.browse("http://google.com");
                break;
            }
            case 4: {
                this.browse("http://google.comi");
                break;
            }
            case 5: {
                this.browse("http://google.com");
                break;
            }
            case 6: {
                this.browse("http://google.comm/");
                break;
            }
            case 7: {
                this.browse("http://www.youtube.com/");
                break;
            }
            case 8: {
                this.browse("http://google.com");
                break;
            }
            case 9: {
                this.writeImage(this.robot.createScreenCapture(this.getGameScreenRectangle()));
                break;
            }
            default: {
                System.out.println(Integer.parseInt(actionEvent.getActionCommand()));
                break;
            }
        }
    }
    
    private Rectangle getGameScreenRectangle() {
        final Rectangle bounds = this.getBounds();
        bounds.setLocation(this.getLocationOnScreen());
        return bounds;
    }
    
    private void writeImage(final BufferedImage bufferedImage) {
        try {
            ImageIO.write(bufferedImage, "PNG", new File("./images/capture-" + this.capNum++ + ".png"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void browse(final String s) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(s));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            catch (URISyntaxException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    static {
        RunClient.params = new Properties();
        RunClient.mainurl = "76.91.247.237";
        JMENU_BUTTONS = new Object[][] { { "File", new JMenuItem("New Client"), new JSeparator(), new JMenuItem("Exit") }, { "Guides", new JMenuItem("Getting Started"), new JMenuItem("Fighting Monsters"), new JMenuItem("Money Making") }, { "Links", new JMenuItem("Wiki"), new JMenuItem("Forums"), new JMenuItem("Youtube"), new JMenuItem("Donate") }, { "Tools", new JMenuItem("Take a screenshot") } };
        RunClient.rs = false;
    }
}
