import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.HyperlinkListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class NDVIGraphDialog extends JDialog implements WindowListener, Observer, ActionListener, ComponentListener, HyperlinkListener
{
    private MosaicData md;
    private imgViewer applet;
    private JCheckBox[] checkBoxes;
    private JButton defaultButton;
    private JEditorPane landcoverHelp;
    private JScrollPane landcoverHelpScrollPane;
    private String[] landcoverName;
    private NDVILineGraph ndviLineGraph;
    private boolean[] savedSelectedLandcover;
    static final int WIDTH = 500;
    static final int HEIGHT = 400;
    static final int MIN_WIDTH = 500;
    static final int MIN_HEIGHT = 400;
    
    public NDVIGraphDialog(final JFrame frame, final imgViewer applet, final MosaicData md) {
        super(frame, "NDVI Graph", false);
        this.landcoverName = new String[] { "DECIDUOUS FOREST", "EVERGREEN FOREST", "HERB. GRASSLANDS", "HERB. WETLANDS", "MIXED FOREST", "PASTURE HAY", "SHRUBLAND", "ROW CROPS", "SMALL GRAINS", "TRANSITIONAL", "WOODY WETLANDS", "Cultivated Crops", "Deciduous Forest", "Evergreen Forest", "Herb. Grasslands", "Herb. Wetlands", "Mixed Forest", "Pasture/Hay", "Shrub/Scrub", "Woody Wetlands" };
        this.applet = applet;
        this.md = md;
        new ParserDelegator();
        this.ndviLineGraph = new NDVILineGraph(applet, md, this, this.landcoverName);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.ndviLineGraph, "Center");
        final JPanel panel2 = new JPanel();
        final JButton button = new JButton("Print");
        button.setMnemonic(80);
        button.setToolTipText("Print NDVI Line Graph");
        button.addActionListener(this);
        panel2.add(button);
        final JButton button2 = new JButton("Close");
        button2.setMnemonic(67);
        button2.setToolTipText("Close NDVI Line Graph");
        button2.addActionListener(this);
        panel2.add(button2);
        panel.add(panel2, "South");
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(12, 2));
        panel3.setToolTipText("Landcover Selecting");
        this.checkBoxes = new JCheckBox[this.landcoverName.length];
        for (int i = 0; i < this.landcoverName.length; ++i) {
            if (i == 0) {
                panel3.add(new JLabel("2008 and prior"));
            }
            if (i == 11) {
                panel3.add(new JLabel(""));
                panel3.add(new JLabel(""));
                panel3.add(new JLabel("2009 forward"));
            }
            (this.checkBoxes[i] = new JCheckBox(this.landcoverName[i] + ": (0)")).setEnabled(false);
            this.checkBoxes[i].setToolTipText("LandCover " + this.landcoverName[i]);
            panel3.add(this.checkBoxes[i]);
        }
        final JPanel panel4 = new JPanel();
        (this.defaultButton = new JButton("Set Default")).addActionListener(this);
        this.defaultButton.setMnemonic(83);
        this.defaultButton.setToolTipText("Set default landcovers");
        panel4.add(this.defaultButton);
        final JButton button3 = new JButton("Close");
        button3.addActionListener(this);
        button3.setMnemonic(67);
        button3.setToolTipText("Close NDVI Line Graph");
        panel4.add(button3);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        panel5.add(panel3, "North");
        panel5.add(panel4, "South");
        (this.landcoverHelp = new JEditorPane()).setEditable(false);
        try {
            this.landcoverHelp.setPage(new URL(applet.getCodeBase(), "LandcoverHelp.html").toString());
        }
        catch (MalformedURLException ex) {
            System.err.println("Malformed URL for NDVI Help");
        }
        catch (IOException ex2) {
            System.err.println("Cannot read NDVI Help URL");
        }
        catch (Exception ex3) {
            System.err.println("Error reading NDVI Help URL");
        }
        this.landcoverHelp.addHyperlinkListener(this);
        (this.landcoverHelpScrollPane = new JScrollPane(this.landcoverHelp)).setVerticalScrollBarPolicy(22);
        this.landcoverHelpScrollPane.setMinimumSize(new Dimension(10, 10));
        this.landcoverHelpScrollPane.setPreferredSize(new Dimension(500, 300));
        final JPanel panel6 = new JPanel();
        final JButton button4 = new JButton("Close");
        button4.addActionListener(this);
        button4.setMnemonic(67);
        button4.setToolTipText("Close NDVI Line Graph");
        panel6.add(button4);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Line Graph", panel);
        tabbedPane.setMnemonicAt(0, 76);
        tabbedPane.setToolTipTextAt(0, "Line graph");
        tabbedPane.addTab("Select Landcover", panel5);
        tabbedPane.setMnemonicAt(1, 83);
        tabbedPane.setToolTipTextAt(1, "Select Landcover");
        tabbedPane.addTab("FAQ", this.landcoverHelpScrollPane);
        tabbedPane.setMnemonicAt(2, 70);
        tabbedPane.setToolTipTextAt(1, "Frequently Asked Questions about NDVI");
        this.getContentPane().add(tabbedPane);
        this.setSize(500, 400);
        this.addWindowListener(this);
        this.addComponentListener(this);
    }
    
    @Override
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            this.applet.getAppletContext().showDocument(hyperlinkEvent.getURL(), "_blank");
        }
    }
    
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
        int width = this.getWidth();
        int height = this.getHeight();
        boolean b = false;
        if (width < 500) {
            b = true;
            width = 500;
        }
        if (height < 400) {
            b = true;
            height = 400;
        }
        if (b) {
            this.setSize(width, height);
        }
    }
    
    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void setDefault(final String text) {
        final String[] split = text.split(":");
        for (int length = this.checkBoxes.length, i = 0; i < length; ++i) {
            if (split[0].equals(this.checkBoxes[i].getText().split(":")[0])) {
                this.checkBoxes[i].setEnabled(true);
                this.checkBoxes[i].setSelected(true);
                this.checkBoxes[i].setText(text);
            }
        }
    }
    
    private void saveLandcoverSelected() {
        final int length = this.checkBoxes.length;
        this.savedSelectedLandcover = new boolean[this.checkBoxes.length];
        for (int i = 0; i < length; ++i) {
            this.savedSelectedLandcover[i] = this.checkBoxes[i].isSelected();
        }
    }
    
    public void clearLandcoverSelected() {
        for (int length = this.checkBoxes.length, i = 0; i < length; ++i) {
            final String[] split = this.checkBoxes[i].getText().split(":");
            this.checkBoxes[i].setEnabled(false);
            this.checkBoxes[i].setSelected(false);
            this.checkBoxes[i].setText(split[0] + ": (0)");
        }
        this.ndviLineGraph.setDefaultLandcover();
    }
    
    public boolean isLandcoverSelected() {
        for (int length = this.checkBoxes.length, i = 0; i < length; ++i) {
            if (this.checkBoxes[i].isSelected()) {
                return true;
            }
        }
        return false;
    }
    
    public Integer getColors(final String s) {
        final String[] split = s.split(":");
        final int length = this.checkBoxes.length;
        final int[] array = new int[this.checkBoxes.length];
        for (int i = 0; i < length; ++i) {
            if (split[0].equals(this.checkBoxes[i].getText().split(":")[0]) && this.checkBoxes[i].isSelected()) {
                return new Integer(i);
            }
        }
        return new Integer(0);
    }
    
    public boolean setLandcover(final String s) {
        final String[] split = s.split(":");
        for (int length = this.checkBoxes.length, i = 0; i < length; ++i) {
            if (split[0].equals(this.checkBoxes[i].getText().split(":")[0]) && this.checkBoxes[i].isSelected()) {
                return true;
            }
        }
        return false;
    }
    
    public void setAvailLandcover(final String text) {
        final int length = this.checkBoxes.length;
        final String[] split = text.split(":");
        for (int i = 0; i < length; ++i) {
            if (split[0].equals(this.checkBoxes[i].getText().split(":")[0])) {
                this.checkBoxes[i].setEnabled(true);
                this.checkBoxes[i].setText(text);
            }
        }
    }
    
    public void restoreLandcoverSelected() {
        for (int length = this.checkBoxes.length, i = 0; i < length; ++i) {
            this.checkBoxes[i].setSelected(this.savedSelectedLandcover[i]);
        }
    }
    
    private void updateData() {
        if (this.isVisible()) {
            final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
            final Metadata currentScene = this.md.getCurrentScene();
            final boolean dataAvailable = this.ndviLineGraph.isDataAvailable();
            if (currentSensor.hasNdviLineGraph) {
                final Metadata scene = this.ndviLineGraph.getScene();
                final Sensor sensor = this.ndviLineGraph.getSensor();
                if (scene == null || currentScene == null || currentScene.gridCol != scene.gridCol || currentScene.gridRow != scene.gridRow || currentSensor != sensor || !dataAvailable) {
                    if (this.isLandcoverSelected()) {
                        this.saveLandcoverSelected();
                    }
                    if (scene == null || currentScene == null || currentScene.gridCol != scene.gridCol || currentScene.gridRow != scene.gridRow || currentSensor != sensor) {
                        this.savedSelectedLandcover = null;
                    }
                    this.clearLandcoverSelected();
                    if (!dataAvailable && this.isLandcoverSelected() && this.savedSelectedLandcover != null) {
                        this.restoreLandcoverSelected();
                    }
                }
                this.ndviLineGraph.performAction();
                this.repaint();
            }
            else {
                this.clearLandcoverSelected();
                this.setVisible(false);
            }
        }
    }
    
    @Override
    public void setVisible(final boolean b) {
        if (b) {
            final boolean visible = this.isVisible();
            super.setVisible(true);
            if (!visible) {
                this.updateData();
            }
        }
        else {
            super.setVisible(false);
        }
    }
    
    public void disableButtons() {
        this.defaultButton.setEnabled(false);
    }
    
    public void enableButtons() {
        this.defaultButton.setEnabled(true);
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable == this.applet.md) {
            this.updateData();
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Close")) {
            this.setVisible(false);
        }
        else if (actionCommand.equals("Set Default")) {
            this.clearLandcoverSelected();
        }
        else if (actionCommand.equals("Print")) {
            final PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(this.ndviLineGraph);
            final PageFormat defaultPage = printerJob.defaultPage();
            if (printerJob.pageDialog(defaultPage) != defaultPage && printerJob.printDialog()) {
                try {
                    printerJob.print();
                }
                catch (Exception ex) {}
            }
        }
    }
}
