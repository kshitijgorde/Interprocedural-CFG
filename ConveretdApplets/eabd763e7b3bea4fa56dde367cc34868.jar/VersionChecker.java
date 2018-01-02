import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VersionChecker extends Applet implements MouseListener, ActionListener
{
    private static final Color BLUE_BM_BRDR;
    private static final Color ORANGE;
    private Label title;
    private CardLayout cardLayout;
    private Panel cards;
    private Font bolded;
    
    public VersionChecker() {
        this.cardLayout = new CardLayout();
        this.cards = new Panel(this.cardLayout);
        this.bolded = new Font("Dialog", 1, 12);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("jre")) {
            this.cardLayout.show(this.cards, "jre");
            this.title.setText("Java Runtime Environment");
        }
        else if (actionCommand.equals("jvm")) {
            this.cardLayout.show(this.cards, "jvm");
            this.title.setText("Java Virtual Machine");
        }
        else if (actionCommand.equals("os")) {
            this.cardLayout.show(this.cards, "os");
            this.title.setText("Operating System Details");
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("javascript:alert(\"" + "Special Thanks to Zach Tomaszewski for the idea\\nand initial implementation of the Java Version Checker.\\n\\nThe original version can be found here:\\nhttp://tinyurl.com/YR2LTQ" + "\")"));
        }
        catch (Exception ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void addToGrid(final GridBagLayout gridBagLayout, final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2, final int fill) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
        gridBagConstraints.fill = fill;
        gridBagLayout.setConstraints(component, gridBagConstraints);
    }
    
    private void displayProperties(final Container container, final String[] array, final String[] array2) {
        final GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        for (int i = 0; i < array.length; ++i) {
            final Label label = new Label(array[i]);
            label.setFont(this.bolded);
            this.addToGrid(layout, label, 0, i, 1, 1, 10, 10, 0);
            container.add(label);
            Label label2;
            if (array2[i].equals("")) {
                label2 = new Label("");
            }
            else {
                try {
                    label2 = new Label(System.getProperty(array2[i]));
                }
                catch (SecurityException ex) {
                    label2 = new Label("[Missing or Access Denied]");
                }
            }
            this.addToGrid(layout, label2, 1, i, 1, 1, 10, 10, 2);
            container.add(label2);
        }
        final Label label3 = new Label("");
        this.addToGrid(layout, label3, 0, -1, 2, 1, 10, 200, 1);
        container.add(label3);
    }
    
    public String getAppletInfo() {
        return "Java Version Checker";
    }
    
    public void init() {
        super.init();
        this.setLayout(new BorderLayout());
        this.addNotify();
        final Color blue_BM_BRDR = VersionChecker.BLUE_BM_BRDR;
        final Color orange = VersionChecker.ORANGE;
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final String[] array = { "Version", "Vendor", "Vendor Website", "", "Specification Name", "Specification Version", "Specification Vendor" };
        final String[] array2 = { "java.version", "java.vendor", "java.vendor.url", "", "java.specification.name", "java.specification.version", "java.specification.vendor" };
        final String[] array3 = { "Name", "Version", "Vendor", "", "Specification Name", "Specification Version", "Specification Vendor" };
        final String[] array4 = { "java.vm.name", "java.vm.version", "java.vm.vendor", "", "java.vm.specification.name", "java.vm.specification.version", "java.vm.specification.vendor" };
        final String[] array5 = { "Name", "Version", "Architecture", "", "Java class path" };
        final String[] array6 = { "os.name", "os.version", "os.arch", "", "java.class.path" };
        this.displayProperties(panel, array, array2);
        this.displayProperties(panel2, array3, array4);
        this.displayProperties(panel3, array5, array6);
        this.cards.add("jre", panel);
        this.cards.add("jvm", panel2);
        this.cards.add("os", panel3);
        this.add(this.cards, "Center");
        final Panel panel4 = new Panel(new FlowLayout(1));
        final Button button = new Button("JRE");
        final Button button2 = new Button("JVM");
        final Button button3 = new Button("OS");
        button.setActionCommand("jre");
        button2.setActionCommand("jvm");
        button3.setActionCommand("os");
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        panel4.add(button);
        panel4.add(button2);
        panel4.add(button3);
        panel4.setBackground(blue_BM_BRDR);
        this.add(panel4, "South");
        (this.title = new Label("Java Runtime Environment", 1)).setFont(this.bolded);
        this.title.setBackground(blue_BM_BRDR);
        this.title.setForeground(orange);
        this.title.addMouseListener(this);
        this.add(this.title, "North");
    }
    
    static {
        BLUE_BM_BRDR = new Color(68, 68, 153);
        ORANGE = new Color(255, 119, 0);
    }
}
