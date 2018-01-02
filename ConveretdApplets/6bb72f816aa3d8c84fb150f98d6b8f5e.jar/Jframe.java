import java.awt.event.ActionEvent;
import java.net.URI;
import java.awt.Desktop;
import java.net.URL;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import java.net.InetAddress;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jframe extends client implements ActionListener
{
    private static JMenuItem menuItem;
    public static JFrame frame;
    public static JPanel gamePanel;
    
    public Jframe(final String[] array) {
        try {
            SignLink.startpriv(InetAddress.getByName(Jframe.server));
            this.initUI();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            (Jframe.frame = new JFrame("Devilishpkz")).setLayout(new BorderLayout());
            Jframe.frame.setDefaultCloseOperation(3);
            Jframe.gamePanel = new JPanel();
            this.webclient = false;
            Jframe.gamePanel.setLayout(new BorderLayout());
            Jframe.gamePanel.add(this);
            final JMenu menu = new JMenu("File");
            final JMenu menu2 = new JMenu("Forums");
            for (final String s : new String[] { "-", "Exit", "-", "Forums" }) {
                final JMenuItem menuItem = new JMenuItem(s);
                if (s.equalsIgnoreCase("-")) {
                    menu.addSeparator();
                }
                else {
                    menuItem.addActionListener(this);
                    menu.add(menuItem);
                }
            }
            final JMenuBar menuBar = new JMenuBar();
            final JMenuBar menuBar2 = new JMenuBar();
            Jframe.frame.setMinimumSize(new Dimension(781, 562));
            Jframe.frame.add(menuBar2);
            menuBar.add(menu);
            Jframe.frame.getContentPane().add(menuBar, "North");
            Jframe.frame.getContentPane().add(Jframe.gamePanel, "Center");
            Jframe.frame.pack();
            Jframe.frame.setVisible(true);
            Jframe.frame.setResizable(true);
            this.init();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public URL getCodeBase() {
        try {
            return new URL("http://" + Jframe.server + "/cache");
        }
        catch (Exception ex) {
            return super.getCodeBase();
        }
    }
    
    @Override
    public URL getDocumentBase() {
        return this.getCodeBase();
    }
    
    public void loadError(final String s) {
        System.out.println("loadError: " + s);
    }
    
    @Override
    public String getParameter(final String s) {
        return "";
    }
    
    private static void openUpWebSite(final String s) {
        final Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(s));
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        try {
            if (actionCommand != null) {
                if (actionCommand.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                if (actionCommand.equalsIgnoreCase("Forums")) {
                    openUpWebSite("http://www.devilishpkz.org/smf");
                }
            }
        }
        catch (Exception ex) {}
    }
}
