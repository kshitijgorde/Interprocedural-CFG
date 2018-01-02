// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmNews extends JPanel implements ActionListener
{
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    private static final String TXT_NEWS = "<html>Welcome to new ParaChat v9.12. The following new features have been added in this release:<BR><li>iPhone / iPad App - enable access from your web-based service administration area</li><li>White Label iPhone App - add your own brand to our iPhone App</li><BR>For a complete list, please visit: <a href=\"http://www.parachat.com/9\">http://www.parachat.com/9</a></html>";
    
    public AmNews(final AdmHandler adminHandler) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Main", null, this.createBasic(), "Main");
        this.add(panel, "Center");
    }
    
    private JComponent createBasic() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getNews());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 30)));
        return panel;
    }
    
    private JComponent getNews() {
        return new JLabel("<html>Welcome to new ParaChat v9.12. The following new features have been added in this release:<BR><li>iPhone / iPad App - enable access from your web-based service administration area</li><li>White Label iPhone App - add your own brand to our iPhone App</li><BR>For a complete list, please visit: <a href=\"http://www.parachat.com/9\">http://www.parachat.com/9</a></html>");
    }
}
