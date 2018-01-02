// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class AboutDialog extends JDialog implements ActionListener
{
    private static AboutDialog aboutFrame;
    JPanel aboutPanel;
    JLabel aboutLabel;
    JLabel titleLabel;
    JPanel southPanel;
    private String version;
    JLabel westLabel;
    JLabel versionLabel;
    String titleText;
    String aboutText;
    private JButton submitButton;
    
    private AboutDialog(final Frame frame, final Component locationRelativeTo) {
        super(frame, "", true);
        this.version = null;
        this.titleText = "<html><center><h2>Medusa</h2></center></html>";
        this.aboutText = "<html><center>Author: Sean Hooper<p>Bork Group<p>EMBL Heidelberg 2005</center><p><center><b>hooper@embl.de</b><br>Full documentation at<p>www.bork.embl.de/medusa</center></html>";
        this.submitButton = new JButton("OK");
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.init();
        this.setLocationRelativeTo(locationRelativeTo);
    }
    
    private void init() {
        this.aboutPanel = new JPanel();
        this.southPanel = new JPanel();
        this.aboutLabel = new JLabel(this.aboutText);
        this.versionLabel = new JLabel();
        this.aboutLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        (this.titleLabel = new JLabel(this.titleText)).setBackground(MedusaFrame.STRINGCOLOR);
        this.titleLabel.setIcon(new ImageIcon(this.getClass().getResource("/medusa/images/medusa_logo_small.png")));
        this.titleLabel.setOpaque(true);
        final Container contentPane = this.getContentPane();
        this.aboutPanel.setLayout(new BorderLayout());
        if (this.version != null) {
            this.southPanel.add(this.versionLabel);
        }
        this.southPanel.add(this.submitButton);
        contentPane.add(this.aboutPanel);
        this.aboutPanel.add("North", this.titleLabel);
        this.aboutPanel.add("Center", this.aboutLabel);
        this.submitButton.addActionListener(this);
        this.aboutPanel.add("South", this.southPanel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.submitButton)) {
            this.submitButtonActionPerformed();
        }
    }
    
    private void submitButtonActionPerformed() {
        this.setVisible(false);
    }
    
    public static void showDialog(final Component component, final Component component2) {
        (AboutDialog.aboutFrame = new AboutDialog(JOptionPane.getFrameForComponent(component), component2)).pack();
        AboutDialog.aboutFrame.setVisible(true);
    }
    
    public static void showDialog(final Component component, final Component component2, final String version) {
        (AboutDialog.aboutFrame = new AboutDialog(JOptionPane.getFrameForComponent(component), component2)).setVersion(version);
        AboutDialog.aboutFrame.pack();
        AboutDialog.aboutFrame.setVisible(true);
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
}
