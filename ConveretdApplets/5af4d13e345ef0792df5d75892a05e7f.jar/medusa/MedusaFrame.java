// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.util.Iterator;
import medusa.graph.Node;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentEvent;
import javax.swing.JColorChooser;
import java.io.FilenameFilter;
import java.awt.FileDialog;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.BoxLayout;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.plaf.ColorUIResource;
import java.awt.Window;
import javax.swing.JOptionPane;
import java.io.IOException;
import medusa.graph.Graph;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import medusa.dataio.DataLoader;
import javax.swing.Timer;
import javax.swing.ProgressMonitor;
import medusa.graphedit.EditGraphDialog;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSlider;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.Color;
import medusa.display.EditableGraphPanel;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

public final class MedusaFrame extends JFrame implements ItemListener, ComponentListener, ChangeListener
{
    EditableGraphPanel stringletPanel;
    MedusaSettings stringSettings;
    static final Color darkBackground;
    static final Color foreground;
    public static final Color STRINGCOLOR;
    Color background;
    Border graphBorder;
    JScrollPane jScrollPane;
    Frame f;
    public static final String version = "1.03";
    JButton frButton;
    JButton stringButton;
    JPanel controlPanel;
    JToggleButton relax;
    JToggleButton temperatureButton;
    JLabel fileLabel;
    JLabel infoLabel;
    JLabel emptyLabel;
    JButton recalcButton;
    private Action toggleRelax;
    JPanel scalerPanel;
    JLabel scalerLabel;
    JSlider scalerSlider;
    JMenu optionMenu;
    JMenu selectMenu;
    JMenu colorMenu;
    JMenu imageMenu;
    JMenu fontMenu;
    JMenuItem selectAllMenuItem;
    JMenuItem selectNoneMenuItem;
    JMenuItem searchMenuItem;
    JMenuItem selectNodeFromFileMenuItem;
    JMenuItem invertFixMenuItem;
    JMenuItem changeColorMenuItem;
    JMenuItem switchColorMenuItem;
    JMenuItem copyColorMenuItem;
    JMenuItem xGradientColorMenuItem;
    JMenuItem changeNodeSizeMenuItem;
    JMenuItem selectNodeByRegExpMenuItem;
    JMenuItem addNodeMenuItem;
    JMenuItem setLabelColorMenuItem;
    JMenuItem setBasicEdgeColorMenuItem;
    JMenuItem loadImageMenuItem;
    JMenuItem setBackgroundColorMenuItem;
    JMenuItem changeFontSizeMenuItem;
    JMenuItem clearImageMenuItem;
    JMenuItem setEdgeLenMenuItem;
    JMenu manipulateMenu;
    JMenu rotateMenu;
    JMenu flipMenu;
    JMenuItem rotateRightMenuItem;
    JMenuItem rotateLeftMenuItem;
    JMenuItem flipXItem;
    JMenuItem flipYItem;
    JMenuItem deleteNodeMenuItem;
    JMenuItem changeNodeShapeMenuItem;
    JMenuItem cropMenuItem;
    JMenuItem randomGraphMenuItem;
    JMenuItem clearGraphMenuItem;
    JMenuItem editGraphMenuItem;
    JMenu fileMenu;
    JMenuBar menuBar;
    JMenuItem openMenuItem;
    JMenuItem addMenuItem;
    JMenu importMenu;
    JMenuItem importTabbedMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem saveHTMLParametersMenuItem;
    JMenu exportMenu;
    JMenu exportImageMenu;
    JMenu exportPostscriptMenu;
    JMenuItem exportJPGMenuItem;
    JMenuItem exportPNGMenuItem;
    JMenuItem quitMenuItem;
    JMenuItem exportPSMenuItem;
    JMenuItem exportEPSMenuItem;
    JMenuItem exportPajekMenuItem;
    JMenu interactionMenu;
    JMenuItem loadInteractionMenuItem;
    JMenu stringDefaultMenu;
    JMenuItem stringProteinInteractionMenuItem;
    JMenuItem stringCOGInteractionMenuItem;
    JMenuItem hideShowMenuItem;
    JMenu displayMenu;
    JCheckBoxMenuItem confidenceCBMItem;
    JCheckBoxMenuItem prettyCBMItem;
    JCheckBoxMenuItem namesCBMItem;
    JCheckBoxMenuItem showNamesCBMItem;
    JCheckBoxMenuItem directedCBMItem;
    JCheckBoxMenuItem hideWhenMoveCBMItem;
    JCheckBoxMenuItem alphaCBMItem;
    JMenu helpMenu;
    JMenuItem aboutMenuItem;
    JPanel legendPanel;
    JToggleButton[] toggleButton;
    JLabel legendTitleLabel;
    JPanel legendTitlePanel;
    JLabel logoLabel;
    JScrollPane legendScroller;
    EditGraphDialog egd;
    double oldScale;
    double scale;
    String lastDir;
    String lastFile;
    String lastInter;
    ProgressMonitor loader;
    Timer timer;
    DataLoader dl;
    private ActionListener runLoad;
    private boolean hideInteractions;
    
    public MedusaFrame() {
        super("Medusa 1.03");
        this.background = MedusaFrame.STRINGCOLOR;
        this.graphBorder = BorderFactory.createRaisedBevelBorder();
        this.f = new Frame();
        this.frButton = new JButton("Spring");
        this.stringButton = new JButton("Data");
        this.relax = new JToggleButton("Relax", false);
        this.temperatureButton = new JToggleButton("Cooling", true);
        this.fileLabel = new JLabel("<html>File: none<b></b></html>");
        this.infoLabel = new JLabel("<html><b>nodes: 0<br>edges: 0</b></html>");
        this.emptyLabel = new JLabel("  ");
        this.recalcButton = new JButton("Recalculate");
        this.toggleRelax = new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final boolean selected = MedusaFrame.this.relax.isSelected();
                if (!selected) {
                    MedusaFrame.this.stringletPanel.start();
                }
                else {
                    MedusaFrame.this.stringletPanel.stop();
                }
                MedusaFrame.this.relax.setSelected(!selected);
            }
        };
        this.scalerPanel = new JPanel();
        this.scalerLabel = new JLabel("  Scale");
        this.scalerSlider = new JSlider(1, 100, 300, 100);
        this.optionMenu = new JMenu("Options");
        this.selectMenu = new JMenu("Select");
        this.colorMenu = new JMenu("Color");
        this.imageMenu = new JMenu("Image");
        this.fontMenu = new JMenu("Font");
        this.selectAllMenuItem = new JMenuItem("All");
        this.selectNoneMenuItem = new JMenuItem("None");
        this.searchMenuItem = new JMenuItem("Search");
        this.selectNodeFromFileMenuItem = new JMenuItem("From file");
        this.invertFixMenuItem = new JMenuItem("Invert");
        this.changeColorMenuItem = new JMenuItem("Change node color");
        this.switchColorMenuItem = new JMenuItem("Switch channels");
        this.copyColorMenuItem = new JMenuItem("Copy channels");
        this.xGradientColorMenuItem = new JMenuItem("X gradient");
        this.changeNodeSizeMenuItem = new JMenuItem("Change node size");
        this.selectNodeByRegExpMenuItem = new JMenuItem("By name");
        this.addNodeMenuItem = new JMenuItem("Add nodes");
        this.setLabelColorMenuItem = new JMenuItem("Label");
        this.setBasicEdgeColorMenuItem = new JMenuItem("Base color");
        this.loadImageMenuItem = new JMenuItem("Load");
        this.setBackgroundColorMenuItem = new JMenuItem("Background");
        this.changeFontSizeMenuItem = new JMenuItem("Font size");
        this.clearImageMenuItem = new JMenuItem("Clear");
        this.setEdgeLenMenuItem = new JMenuItem("Edge Length");
        this.manipulateMenu = new JMenu("Manipulate");
        this.rotateMenu = new JMenu("Rotate");
        this.flipMenu = new JMenu("Flip");
        this.rotateRightMenuItem = new JMenuItem("90d clockwise");
        this.rotateLeftMenuItem = new JMenuItem("90d anti-clockwise");
        this.flipXItem = new JMenuItem("Vertical");
        this.flipYItem = new JMenuItem("Horizontal");
        this.deleteNodeMenuItem = new JMenuItem("Delete nodes");
        this.changeNodeShapeMenuItem = new JMenuItem("Change node shape");
        this.cropMenuItem = new JMenuItem("Crop graph");
        this.randomGraphMenuItem = new JMenuItem("Random graph");
        this.clearGraphMenuItem = new JMenuItem("Clear graph");
        this.editGraphMenuItem = new JMenuItem("Edit graph");
        this.fileMenu = new JMenu("File");
        this.menuBar = new JMenuBar();
        this.openMenuItem = new JMenuItem("New");
        this.addMenuItem = new JMenuItem("Append");
        this.importMenu = new JMenu("Import");
        this.importTabbedMenuItem = new JMenuItem("Simple tabbed");
        this.saveMenuItem = new JMenuItem("Save");
        this.saveHTMLParametersMenuItem = new JMenuItem("HTML Parameters");
        this.exportMenu = new JMenu("Export");
        this.exportImageMenu = new JMenu("Image");
        this.exportPostscriptMenu = new JMenu("PostScript");
        this.exportJPGMenuItem = new JMenuItem("JPG");
        this.exportPNGMenuItem = new JMenuItem("PNG");
        this.quitMenuItem = new JMenuItem("Quit");
        this.exportPSMenuItem = new JMenuItem("PS");
        this.exportEPSMenuItem = new JMenuItem("EPS");
        this.exportPajekMenuItem = new JMenuItem("Pajek");
        this.interactionMenu = new JMenu("Interaction");
        this.loadInteractionMenuItem = new JMenuItem("Load");
        this.stringDefaultMenu = new JMenu("STRING default");
        this.stringProteinInteractionMenuItem = new JMenuItem("Protein");
        this.stringCOGInteractionMenuItem = new JMenuItem("COGS");
        this.hideShowMenuItem = new JMenuItem("Hide");
        this.displayMenu = new JMenu("Display");
        this.confidenceCBMItem = new JCheckBoxMenuItem("Confidence", true);
        this.prettyCBMItem = new JCheckBoxMenuItem("Interactions", false);
        this.namesCBMItem = new JCheckBoxMenuItem("Labels", true);
        this.showNamesCBMItem = new JCheckBoxMenuItem("Show names", true);
        this.directedCBMItem = new JCheckBoxMenuItem("Directed", false);
        this.hideWhenMoveCBMItem = new JCheckBoxMenuItem("Hide when move", false);
        this.alphaCBMItem = new JCheckBoxMenuItem("Alpha conf", true);
        this.helpMenu = new JMenu("Help");
        this.aboutMenuItem = new JMenuItem("About Medusa");
        this.legendPanel = new JPanel();
        this.oldScale = 1.0;
        this.scale = 1.0;
        this.lastDir = null;
        this.lastFile = null;
        this.lastInter = null;
        this.runLoad = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.infoLabel.setText(MedusaFrame.this.dl.getStatus() + " " + MedusaFrame.this.dl.getProgress());
                if (MedusaFrame.this.dl.isDone()) {
                    MedusaFrame.this.timer.stop();
                    MedusaFrame.this.dl.stop();
                    MedusaFrame.this.infoLabel.setText("Cleaning graph");
                    MedusaFrame.this.setCursor(null);
                    MedusaFrame.this.stringletPanel.setGraph(MedusaFrame.this.dl.getGraph());
                    MedusaFrame.this.updateInfo();
                    MedusaFrame.this.updateFileInfo();
                    MedusaFrame.this.stringletPanel.repaint();
                }
            }
        };
        this.hideInteractions = false;
    }
    
    private final void init() {
        this.stringSettings = new MedusaSettings();
        (this.stringletPanel = new EditableGraphPanel(this.stringSettings)).setBasicEdgeColor(Color.black);
        this.stringletPanel.setTimeFrameXY(600, 600);
        this.stringletPanel.setBorder(this.graphBorder);
        this.stringletPanel.setShowBorder(false);
        this.stringletPanel.setArrows(false);
        this.stringletPanel.setCool(true);
        this.jScrollPane = new JScrollPane(this.stringletPanel);
        this.egd = new EditGraphDialog(this, true);
        this.buildGUI();
    }
    
    protected void buildGUI() {
        this.setDefaultCloseOperation(3);
        this.addComponentListener(this);
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        this.menuBar.setBackground(this.background);
        this.populateFileMenu();
        this.menuBar.add(this.fileMenu);
        this.populateSelectMenu();
        this.menuBar.add(this.selectMenu);
        this.populateOptionMenu();
        this.menuBar.add(this.optionMenu);
        this.populateManipulateMenu();
        this.menuBar.add(this.manipulateMenu);
        this.populateInteractionMenu();
        this.menuBar.add(this.interactionMenu);
        this.populateDisplayMenu();
        this.menuBar.add(this.displayMenu);
        this.populateHelpMenu();
        this.menuBar.add(this.helpMenu);
        this.setJMenuBar(this.menuBar);
        this.controlPanel = new JPanel();
        this.populateControlPanel();
        this.controlPanel.setBorder(this.graphBorder);
        contentPane.add("Center", this.jScrollPane);
        contentPane.add("South", this.controlPanel);
        this.populateLegendPanel();
        this.populateScalerPanel();
        contentPane.add("East", this.scalerPanel);
    }
    
    public void loadData(final String s, final boolean b) throws IOException, DataFormatException {
        System.out.println("Loading " + s + ". Please wait");
        this.dl = new DataLoader();
        Graph graph;
        if (b) {
            graph = this.dl.load(s);
        }
        else {
            graph = this.dl.loadSimplest(s);
        }
        this.stringletPanel.setGraph(graph);
    }
    
    private final void loadInteractionSettings(final String s) throws IOException {
        this.stringSettings.load(s);
        this.updateLegend();
        this.stringletPanel.updateStringSettings(this.stringSettings);
    }
    
    void initData() {
        try {
            final boolean loadSettings = this.loadSettings();
            this.defaultNet();
            if (this.stringletPanel == null) {
                System.out.println("stringletPanel is null");
            }
            if (loadSettings) {
                this.waitForLoad(this.lastDir, 0);
            }
        }
        catch (IOException ex2) {
            this.defaultNet();
        }
        catch (DataFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Data Error", 0);
            this.defaultNet();
        }
    }
    
    public void defaultNet() {
    }
    
    public static void main(final String[] array) {
        setLnF();
        final MedusaFrame medusaFrame = new MedusaFrame();
        medusaFrame.init();
        try {
            medusaFrame.checkArgs(array);
        }
        catch (DataFormatException ex) {
            System.out.println("Data format error! Check your data.");
            ex.printStackTrace();
            System.exit(0);
        }
        catch (IOException ex2) {
            System.out.println("File error! Check the integrity of your file.");
            ex2.printStackTrace();
            System.exit(0);
        }
        medusaFrame.pack();
        centerWindow(medusaFrame);
        medusaFrame.setVisible(true);
    }
    
    public void checkArgs(final String[] array) throws IOException, DataFormatException {
        System.out.println("Checking arguments");
        final int length = array.length;
        if (length == 0) {
            System.out.println("No arguments. Proceeding normally");
            this.initData();
            return;
        }
        if (length == 2) {
            this.checkArgPair(array);
            return;
        }
        if (length == 4) {
            this.checkArgPair(new String[] { array[0], array[1] });
            this.checkArgPair(new String[] { array[2], array[3] });
        }
    }
    
    private void checkArgPair(final String[] array) throws IOException, DataFormatException {
        if (array[0].compareTo("f") == 0) {
            this.loadData(array[1], true);
        }
        if (array[0].compareTo("i") == 0) {
            this.loadInteractionSettings(array[1]);
        }
        if (array[0].compareTo("t") == 0) {
            this.loadData(array[1], false);
        }
    }
    
    public static void setLnF() {
        final ColorUIResource colorUIResource = new ColorUIResource(new Color(255, 255, 255));
    }
    
    public static void centerWindow(final Window window) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = window.getSize();
        window.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
    }
    
    private void populateControlPanel() {
        this.controlPanel.add(this.fileLabel);
        this.controlPanel.add(this.infoLabel);
        this.controlPanel.add(this.emptyLabel);
        this.controlPanel.add(this.relax);
        this.relax.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (MedusaFrame.this.relax.isSelected()) {
                    MedusaFrame.this.stringletPanel.start();
                }
                else {
                    MedusaFrame.this.stringletPanel.stop();
                }
            }
        });
        this.controlPanel.add(this.temperatureButton);
        this.temperatureButton.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setCool(MedusaFrame.this.temperatureButton.isSelected());
            }
        });
        this.controlPanel.add(this.stringButton);
        this.stringButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stringConnection();
            }
        });
        this.controlPanel.add(this.frButton);
        this.frButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stopRelax();
                MedusaFrame.this.stringletPanel.energy();
            }
        });
        this.controlPanel.add(this.recalcButton);
        this.recalcButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.recalcEvent();
            }
        });
    }
    
    private void updateInfo() {
        final String[] graphData = this.stringletPanel.getGraphData();
        this.infoLabel.setText("<html><b>nodes: " + graphData[0] + "<br>edges: " + graphData[1] + "</b></html>");
    }
    
    private void updateFileInfo() {
        this.fileLabel.setText(this.lastFile);
    }
    
    private void populateScalerPanel() {
        this.scalerPanel.setLayout(new BoxLayout(this.scalerPanel, 1));
        this.scalerPanel.setOpaque(true);
        this.scalerPanel.setBackground(this.background);
        this.scalerSlider.setMajorTickSpacing(50);
        this.scalerSlider.setMinorTickSpacing(10);
        this.scalerSlider.setPaintTicks(true);
        this.scalerSlider.setPaintLabels(true);
        this.scalerSlider.setBackground(this.background);
        this.scalerSlider.setForeground(MedusaFrame.foreground);
        this.scalerPanel.setBackground(this.background);
        this.scalerPanel.setForeground(MedusaFrame.foreground);
        this.legendPanel.setAlignmentX(0.0f);
        this.scalerSlider.setAlignmentX(0.0f);
        this.scalerLabel.setAlignmentX(0.0f);
        this.scalerLabel.setForeground(MedusaFrame.foreground);
        this.scalerPanel.add(this.legendPanel);
        this.scalerPanel.add(this.scalerLabel);
        this.scalerPanel.add(this.scalerSlider);
        this.scalerSlider.addChangeListener(this);
    }
    
    private void populateOptionMenu() {
        this.optionMenu.setMnemonic(79);
        this.colorMenu.setMnemonic(67);
        this.fontMenu.setMnemonic(70);
        this.searchMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.searchNodeEvent();
            }
        });
        this.changeColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.changeColorEvent();
            }
        });
        this.switchColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.switchColorEvent();
            }
        });
        this.copyColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.copyColorEvent();
            }
        });
        this.xGradientColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stringletPanel.addGradientX(2);
            }
        });
        this.changeNodeSizeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.changeNodeSizeEvent();
            }
        });
        this.setBasicEdgeColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.setBasicEdgeColorEvent();
            }
        });
        this.setLabelColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.setLabelColorEvent();
            }
        });
        this.setBackgroundColorMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.setBackgroundColorEvent();
            }
        });
        this.loadImageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.loadImageEvent();
            }
        });
        this.clearImageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.clearImageEvent();
            }
        });
        this.changeFontSizeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.changeFontSizeEvent();
            }
        });
        this.setEdgeLenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.setEdgeLenEvent();
            }
        });
        this.colorMenu.add(this.changeColorMenuItem);
        this.colorMenu.add(this.switchColorMenuItem);
        this.colorMenu.add(this.copyColorMenuItem);
        this.optionMenu.setBackground(this.background);
        this.optionMenu.setForeground(MedusaFrame.foreground);
        this.optionMenu.add(this.searchMenuItem);
        this.optionMenu.add(this.changeNodeSizeMenuItem);
        this.optionMenu.add(this.setEdgeLenMenuItem);
        this.colorMenu.addSeparator();
        this.colorMenu.add(this.setBasicEdgeColorMenuItem);
        this.fontMenu.add(this.setLabelColorMenuItem);
        this.colorMenu.add(this.setBackgroundColorMenuItem);
        this.optionMenu.addSeparator();
        this.optionMenu.add(this.colorMenu);
        this.fontMenu.add(this.changeFontSizeMenuItem);
        this.optionMenu.add(this.fontMenu);
        this.optionMenu.addSeparator();
        this.imageMenu.add(this.loadImageMenuItem);
        this.imageMenu.add(this.clearImageMenuItem);
        this.optionMenu.add(this.imageMenu);
    }
    
    private void populateSelectMenu() {
        this.selectMenu.setBackground(this.background);
        this.selectMenu.setForeground(MedusaFrame.foreground);
        this.selectMenu.setMnemonic(83);
        this.selectNodeByRegExpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.searchNodeEvent();
            }
        });
        this.selectNodeFromFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.selectNodeFromFileEvent();
            }
        });
        this.selectAllMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stringletPanel.setFix(true);
            }
        });
        this.selectNoneMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stringletPanel.setFix(false);
            }
        });
        this.invertFixMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stringletPanel.invertFix();
            }
        });
        this.selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(65, 8));
        this.selectNoneMenuItem.setAccelerator(KeyStroke.getKeyStroke(81, 8));
        this.selectMenu.add(this.selectAllMenuItem);
        this.selectMenu.add(this.selectNoneMenuItem);
        this.selectMenu.add(this.selectNodeByRegExpMenuItem);
        this.selectMenu.add(this.selectNodeFromFileMenuItem);
        this.selectMenu.add(this.invertFixMenuItem);
    }
    
    private void populateManipulateMenu() {
        this.manipulateMenu.setMnemonic(77);
        this.changeNodeShapeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.setShapeEvent();
            }
        });
        this.deleteNodeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.stopRelax();
                MedusaFrame.this.stringletPanel.removeFixedNodes();
                MedusaFrame.this.updateInfo();
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.cropMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String showInputDialog = JOptionPane.showInputDialog("Choose minimum confidence level:", "1.0");
                if (showInputDialog != null) {
                    MedusaFrame.this.stringletPanel.crop(Double.parseDouble(showInputDialog));
                    MedusaFrame.this.updateInfo();
                    MedusaFrame.this.stringletPanel.repaint();
                }
            }
        });
        this.randomGraphMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.getRandomGraphEvent();
            }
        });
        this.clearGraphMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.clearGraphEvent();
            }
        });
        this.rotateRightMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.rotateEvent(1.5707963267948966);
            }
        });
        this.rotateLeftMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.rotateEvent(-1.5707963267948966);
            }
        });
        this.flipXItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.flipX();
            }
        });
        this.flipYItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.flipY();
            }
        });
        this.editGraphMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.editGraph();
            }
        });
        this.manipulateMenu.add(this.clearGraphMenuItem);
        this.manipulateMenu.add(this.changeNodeShapeMenuItem);
        this.manipulateMenu.add(this.deleteNodeMenuItem);
        this.manipulateMenu.add(this.cropMenuItem);
        this.manipulateMenu.add(this.randomGraphMenuItem);
        this.manipulateMenu.setForeground(MedusaFrame.foreground);
        this.manipulateMenu.setBackground(this.background);
        this.rotateMenu.add(this.rotateRightMenuItem);
        this.rotateMenu.add(this.rotateLeftMenuItem);
        this.flipMenu.add(this.flipXItem);
        this.flipMenu.add(this.flipYItem);
        this.manipulateMenu.add(this.rotateMenu);
        this.manipulateMenu.add(this.flipMenu);
        this.manipulateMenu.addSeparator();
        this.manipulateMenu.add(this.editGraphMenuItem);
    }
    
    protected void populateFileMenu() {
        this.fileMenu.setMnemonic(70);
        this.quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.quitApplication();
            }
        });
        this.openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.loadEvent(0);
            }
        });
        this.addMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.addGraphEvent();
            }
        });
        this.saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.saveEvent();
            }
        });
        this.saveHTMLParametersMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.saveHTMLParametersEvent();
            }
        });
        this.exportJPGMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.exportImageEvent(false);
            }
        });
        this.exportPNGMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.exportImageEvent(true);
            }
        });
        this.exportPSMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.exportPSEvent();
            }
        });
        this.exportEPSMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.exportEPSEvent();
            }
        });
        this.exportPajekMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.exportPajekEvent();
            }
        });
        this.importTabbedMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.loadEvent(1);
            }
        });
        this.fileMenu.setBackground(this.background);
        this.fileMenu.setForeground(MedusaFrame.foreground);
        this.fileMenu.add(this.openMenuItem);
        this.fileMenu.add(this.addMenuItem);
        this.fileMenu.add(this.importMenu);
        this.fileMenu.add(this.saveMenuItem);
        this.exportImageMenu.add(this.exportJPGMenuItem);
        this.exportImageMenu.add(this.exportPNGMenuItem);
        this.exportPostscriptMenu.add(this.exportPSMenuItem);
        this.exportPostscriptMenu.add(this.exportEPSMenuItem);
        this.exportMenu.add(this.exportImageMenu);
        this.exportMenu.add(this.exportPostscriptMenu);
        this.exportMenu.add(this.exportPajekMenuItem);
        this.exportMenu.add(this.saveHTMLParametersMenuItem);
        this.importMenu.add(this.importTabbedMenuItem);
        this.fileMenu.add(this.exportMenu);
        this.fileMenu.addSeparator();
        this.fileMenu.add(this.quitMenuItem);
    }
    
    protected void populateInteractionMenu() {
        this.interactionMenu.setMnemonic(73);
        this.loadInteractionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.interactionEvent();
            }
        });
        this.stringProteinInteractionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.proteinInteractionEvent();
            }
        });
        this.stringCOGInteractionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.cogInteractionEvent();
            }
        });
        this.hideShowMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.hideShowInteractionsEvent();
            }
        });
        this.interactionMenu.add(this.loadInteractionMenuItem);
        this.stringDefaultMenu.add(this.stringProteinInteractionMenuItem);
        this.stringDefaultMenu.add(this.stringCOGInteractionMenuItem);
        this.interactionMenu.add(this.stringDefaultMenu);
        this.interactionMenu.addSeparator();
        this.interactionMenu.add(this.hideShowMenuItem);
        this.interactionMenu.setBackground(this.background);
        this.interactionMenu.setForeground(MedusaFrame.foreground);
    }
    
    protected void populateDisplayMenu() {
        this.displayMenu.setMnemonic(68);
        this.prettyCBMItem.setAccelerator(KeyStroke.getKeyStroke(73, 8));
        this.prettyCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setPretty(MedusaFrame.this.prettyCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.namesCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setLabel(MedusaFrame.this.namesCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.showNamesCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setShowName(MedusaFrame.this.showNamesCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.confidenceCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setConfidence(MedusaFrame.this.confidenceCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.directedCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setArrows(MedusaFrame.this.directedCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.hideWhenMoveCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setHideWhenMove(MedusaFrame.this.hideWhenMoveCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.alphaCBMItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                MedusaFrame.this.stringletPanel.setAlpha(MedusaFrame.this.alphaCBMItem.isSelected());
                MedusaFrame.this.stringletPanel.repaint();
            }
        });
        this.displayMenu.setBackground(this.background);
        this.displayMenu.setForeground(MedusaFrame.foreground);
        this.displayMenu.add(this.prettyCBMItem);
        this.displayMenu.add(this.namesCBMItem);
        this.displayMenu.add(this.showNamesCBMItem);
        this.displayMenu.add(this.directedCBMItem);
        this.displayMenu.add(this.confidenceCBMItem);
        this.displayMenu.add(this.alphaCBMItem);
        this.displayMenu.addSeparator();
        this.displayMenu.add(this.hideWhenMoveCBMItem);
    }
    
    protected void populateHelpMenu() {
        this.aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MedusaFrame.this.aboutEvent();
            }
        });
        this.helpMenu.add(this.aboutMenuItem);
        this.helpMenu.setBackground(this.background);
        this.helpMenu.setForeground(MedusaFrame.foreground);
    }
    
    protected void populateLegendPanel() {
        this.legendPanel.setLayout(new BoxLayout(this.legendPanel, 1));
        this.legendPanel.setOpaque(true);
        this.legendPanel.setBackground(this.background);
        this.initLegendTitle();
        this.legendPanel.add(this.legendTitlePanel);
        this.initLegendScroller();
        this.legendPanel.add(this.legendScroller);
    }
    
    public void updateLegend() {
        this.legendPanel.removeAll();
        this.initLegendScroller();
        this.legendPanel.add(this.legendTitlePanel);
        this.legendPanel.add(this.legendScroller);
        this.legendPanel.validate();
        this.scalerPanel.validate();
        this.scalerPanel.repaint();
    }
    
    private void initLegendTitle() {
        (this.legendTitlePanel = new JPanel()).setLayout(new BoxLayout(this.legendTitlePanel, 1));
        this.legendTitlePanel.setOpaque(true);
        this.legendTitlePanel.setBackground(this.background);
        this.logoLabel = new JLabel();
        (this.legendTitleLabel = new JLabel("Interactions")).setBackground(this.background);
        this.legendTitleLabel.setForeground(MedusaFrame.foreground);
        this.logoLabel.setIcon(new ImageIcon(this.getClass().getResource("/medusa/images/medusa_logo_small.png")));
        this.legendTitlePanel.add(this.logoLabel);
        this.legendTitlePanel.add(this.legendTitleLabel);
    }
    
    private void initLegendScroller() {
        final Dimension preferredSize = new Dimension(150, 100);
        final ShowEdgeMultiPanel showEdgeMultiPanel = new ShowEdgeMultiPanel(this, this.stringSettings);
        showEdgeMultiPanel.setBackground(this.background);
        (this.legendScroller = new JScrollPane(showEdgeMultiPanel)).setPreferredSize(preferredSize);
        this.legendScroller.revalidate();
    }
    
    private int yes_no(final String s) {
        return JOptionPane.showConfirmDialog(this, s, "Confirm", 0);
    }
    
    private void clearGraphEvent() {
        this.stopAllRelax();
        if (this.yes_no("Your data will be lost. Are you sure?") == 0) {
            this.stringletPanel.clearGraph();
            this.stringletPanel.repaint();
            this.updateInfo();
        }
    }
    
    private void recalcEvent() {
        this.stopAllRelax();
        if (this.yes_no("This will return edge length \nand orientation to default values\n Are you sure you want to do this?") == 0) {
            this.stringletPanel.autoFixOrientation();
            this.stringletPanel.calculateEdgeLength();
            this.stringletPanel.repaint();
        }
    }
    
    private void rotateEvent(final double n) {
        this.stopAllRelax();
        this.stringletPanel.rotate(n);
        this.stringletPanel.repaint();
    }
    
    private void flipEvent(final boolean b, final boolean b2) {
        this.stopAllRelax();
        this.stringletPanel.mirror(b, b2);
        this.stringletPanel.repaint();
    }
    
    private void flipX() {
        this.flipEvent(true, false);
    }
    
    private void flipY() {
        this.flipEvent(false, true);
    }
    
    private void editGraph() {
        this.stopAllRelax();
        (this.egd = new EditGraphDialog(this, true)).setGraph(this.stringletPanel.getGraph());
        this.egd.setVisible(true);
        final Graph graph = this.egd.getGraph();
        if (graph != null) {
            this.stringletPanel.setGraph(graph);
            this.stringletPanel.repaint();
        }
    }
    
    private void stringConnection() {
        JOptionPane.showConfirmDialog(this, "STRING requires a license and is not\navailable to open source", "Disabled", -1, 1);
    }
    
    private void aboutEvent() {
        AboutDialog.showDialog(this, null, "1.03");
    }
    
    private void setShapeEvent() {
        this.stringletPanel.setShape(JOptionPane.showOptionDialog(this, "Choose a node shape", "Shape", 1, 3, null, new Object[] { "circle", "rectangle", "triangle", "diamond" }, "circle"));
    }
    
    public void loadImageEvent() {
        this.stringletPanel.stop();
        this.relax.setSelected(false);
        final FileDialog fileDialog = new FileDialog(this, "Load image", 0);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.stringletPanel.setImage(directory + file);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "The image could not be found or opened", "Load Error", 0);
            }
        }
        this.stringletPanel.repaint();
    }
    
    private void stopAllRelax() {
        this.stringletPanel.stop();
        this.relax.setSelected(false);
        this.controlPanel.repaint();
    }
    
    public void clearImageEvent() {
        this.stopAllRelax();
        this.stringletPanel.clearImage();
        this.stringletPanel.repaint();
    }
    
    public void quitApplication() {
        this.stopAllRelax();
        System.out.println("Closing application");
        try {
            this.saveSettings();
            System.out.println("Current settings saved");
        }
        catch (IOException ex) {
            System.out.println("...settings not saved due to file problems");
        }
        this.remove(this.stringletPanel);
        System.exit(0);
    }
    
    private void loadEvent(final int n) {
        this.stopAllRelax();
        this.scaleOut();
        final FileDialog fileDialog = new FileDialog(this, "Load graph", 0);
        if (this.lastDir != null) {
            fileDialog.setDirectory(this.lastDir);
        }
        fileDialog.setFilenameFilter(new DataFileFilter());
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.waitForLoad(directory + file, n);
                this.lastDir = directory;
                this.lastFile = file;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "The file could not be found or opened", "Load Error", 0);
            }
            catch (DataFormatException ex2) {
                JOptionPane.showMessageDialog(this, ex2.getMessage(), "Data Error", 0);
            }
        }
    }
    
    private void getRandomGraphEvent() {
        if (this.yes_no("This will generate a random graph. Are you sure?") == 0) {
            this.stopAllRelax();
            this.stringletPanel.randomGraph(30, 60);
            this.updateInfo();
            this.stringletPanel.repaint();
        }
    }
    
    private void addGraphEvent() {
        this.stopAllRelax();
        this.scaleOut();
        final FileDialog fileDialog = new FileDialog(this, "Add graph", 0);
        if (this.lastDir != null) {
            fileDialog.setFile(this.lastDir + this.lastFile);
        }
        fileDialog.setFilenameFilter(new DataFileFilter());
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.stringletPanel.appendGraph(directory + file);
                this.lastDir = directory + file;
                this.lastFile = file;
                this.updateInfo();
                this.stringletPanel.repaint();
            }
            catch (IOException ex2) {
                JOptionPane.showMessageDialog(this, "The file could not be found or opened", "Load Error", 0);
            }
            catch (DataFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Data Error", 0);
            }
        }
    }
    
    private void saveEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Save as", 1);
        fileDialog.setFilenameFilter(new DataFileFilter());
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            this.lastDir = directory;
            this.lastFile = file;
            try {
                this.stringletPanel.saveGraph(directory + file);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Save error", 0);
            }
        }
    }
    
    private void saveHTMLParametersEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Save applet parameters to", 1);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        this.dl = new DataLoader();
        if (file != null) {
            try {
                this.dl.saveHTMLParameters(this.stringletPanel.getGraph(), file);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Save error", 0);
            }
        }
    }
    
    private void exportEPSEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Save as EPS", 1);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.stringletPanel.writeEPS(directory + file);
                JOptionPane.showMessageDialog(this, "Panel exported to " + file, "EPS saved", 1);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Save error", 0);
            }
        }
    }
    
    private void exportPSEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Save as PS", 1);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.stringletPanel.writePS(directory + file);
                JOptionPane.showMessageDialog(this, "Panel exported to " + file, "PS saved", 1);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Save error", 0);
            }
        }
    }
    
    private void exportPajekEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Save as pajek", 1);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.stringletPanel.writePajek(directory + file);
                JOptionPane.showMessageDialog(this, "Exported to pajek format " + file, "Pajek", 1);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "Save error", 0);
            }
        }
    }
    
    private void interactionEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Load interaction file", 0);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        if (file != null) {
            try {
                this.lastInter = directory + file;
                this.stringSettings.load(directory + file);
                this.updateLegend();
                this.stringletPanel.updateStringSettings(this.stringSettings);
                this.legendPanel.repaint();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Interaction settings could not be loaded.\nCheck if the file is valid.", "Load error", 0);
            }
        }
    }
    
    private void exportImageEvent(final boolean param) {
        final String string = "network." + (param ? "png" : "jpg");
        final FileDialog fileDialog = new FileDialog(this, "Save as" + (param ? "png" : "jpg"), 1);
        fileDialog.setFile(string);
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        this.stringletPanel.saveImage(fileDialog.getDirectory() + file, param ? 1 : 0);
        JOptionPane.showMessageDialog(this, "Panel exported to " + file, "Image saved", 1);
    }
    
    private void switchColorEvent() {
        final int[] showDialog = ColorJDialog.showDialog(this.f, null, "switch with");
        this.stringletPanel.manipulateColor(showDialog[0], showDialog[1], true);
        this.stringletPanel.repaint();
    }
    
    private void copyColorEvent() {
        final int[] showDialog = ColorJDialog.showDialog(this.f, null, "copy to");
        this.stringletPanel.manipulateColor(showDialog[0], showDialog[1], false);
        this.stringletPanel.repaint();
    }
    
    private void changeColorEvent() {
        final Color showDialog = JColorChooser.showDialog(this.getRootPane(), "Choose node color", Color.red);
        if (showDialog != null) {
            this.stringletPanel.changeNodeColor(showDialog);
            this.stringletPanel.repaint();
        }
    }
    
    private void setLabelColorEvent() {
        final Color showDialog = JColorChooser.showDialog(this.getRootPane(), "Choose label color", Color.black);
        if (showDialog != null) {
            this.stringletPanel.setFontColor(showDialog);
            this.stringletPanel.repaint();
        }
    }
    
    private void setBackgroundColorEvent() {
        final Color showDialog = JColorChooser.showDialog(this.getRootPane(), "Choose label color", Color.white);
        if (showDialog != null) {
            this.stringletPanel.setBackgroundColor(showDialog);
            this.stringletPanel.repaint();
        }
    }
    
    private void setBasicEdgeColorEvent() {
        final Color showDialog = JColorChooser.showDialog(this.getRootPane(), "Choose label color", Color.gray);
        if (showDialog != null) {
            this.stringletPanel.setBasicEdgeColor(showDialog);
            this.stringletPanel.repaint();
        }
    }
    
    private void changeNodeSizeEvent() {
        final String showInputDialog = JOptionPane.showInputDialog("Enter node size in pixels:\nDefault value is 10", "");
        if (showInputDialog != null && showInputDialog.length() > 0) {
            try {
                this.stringletPanel.setNodeSize(Integer.parseInt(showInputDialog));
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect input", "An integer is required for this action", 0);
            }
        }
        this.stringletPanel.repaint();
    }
    
    private void changeFontSizeEvent() {
        final String showInputDialog = JOptionPane.showInputDialog("Enter font size:\nDefault value is 10", "");
        if (showInputDialog != null && showInputDialog.length() > 0) {
            try {
                this.stringletPanel.changeFont(Integer.parseInt(showInputDialog));
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect input", "An integer is required for this action", 0);
            }
        }
        this.stringletPanel.repaint();
    }
    
    private void searchNodeEvent() {
        final String showInputDialog = JOptionPane.showInputDialog("Mark node matching regular expression:\ne.g. \"mito.*\"", "");
        if (showInputDialog != null && !this.stringletPanel.findLabel(showInputDialog)) {
            JOptionPane.showMessageDialog(this, "No match found", "No nodes matching your pattern were found", 0);
        }
        this.stringletPanel.repaint();
    }
    
    private void selectNodeFromFileEvent() {
        final FileDialog fileDialog = new FileDialog(this, "Load node list", 0);
        if (this.lastDir != null) {
            fileDialog.setDirectory(this.lastDir);
        }
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        final String directory = fileDialog.getDirectory();
        try {
            this.stringletPanel.selectNodeFromFile(directory + file);
            this.stringletPanel.repaint();
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Load Error", 0);
        }
    }
    
    public void stopRelax() {
        this.stringletPanel.stop();
        if (this.relax.isSelected()) {
            this.relax.setSelected(false);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public void handleClassStateChange(final ItemEvent itemEvent) {
        this.handleToggle(itemEvent.getSource());
    }
    
    public void handleToggle(final Object o) {
        System.out.println("debug line 1522 event handled");
        System.out.println(((ShowEdgePanel)o).getNumber());
    }
    
    public void handleEdgeEvent(final int n, final boolean b) {
        this.stringletPanel.setShowEdge(n - 1, b);
        this.stringletPanel.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final Dimension size = this.stringletPanel.getSize();
            final double oldScale = slider.getValue() / 100.0;
            this.stringletPanel.setScale(oldScale / this.oldScale);
            this.stringletPanel.setTimeFrameXY(size);
            this.oldScale = oldScale;
            this.stringletPanel.repaint();
            this.stringletPanel.revalidate();
            this.jScrollPane.revalidate();
        }
    }
    
    public void scaleOut() {
        this.scalerSlider.setValue(100);
        final double oldScale = 1.0;
        this.stringletPanel.setScale(oldScale / this.oldScale);
        this.stringletPanel.setTimeFrameXY(this.stringletPanel.getSize());
        this.oldScale = oldScale;
        this.stringletPanel.repaint();
        this.stringletPanel.revalidate();
        this.jScrollPane.revalidate();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void saveSettings() throws IOException {
        if (this.lastDir != null) {
            final FileWriter fileWriter = new FileWriter(new File("NetViewSettings.ini"));
            fileWriter.write("LASTDIR=");
            fileWriter.write(this.lastDir + "\n");
            if (this.lastInter != null) {
                fileWriter.write("INTERACT=");
                fileWriter.write(this.lastInter + "\n");
            }
            fileWriter.close();
        }
    }
    
    private boolean loadSettings() throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("NetViewSettings.ini"))));
        final String line = bufferedReader.readLine();
        if (line == null) {
            System.out.println("Error in NetViewSettings.ini");
            return false;
        }
        final Matcher matcher = Pattern.compile("LASTDIR=(.+)").matcher(line);
        if (matcher.find()) {
            this.lastDir = matcher.group(1);
            final String line2 = bufferedReader.readLine();
            if (line2 != null) {
                final Matcher matcher2 = Pattern.compile("INTERACT=(\\w+)").matcher(line2);
                if (matcher2.find()) {
                    this.lastInter = matcher2.group(1);
                }
            }
            bufferedReader.close();
            return true;
        }
        return false;
    }
    
    private String getFixedNodesAsString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("");
        final Iterator<Node> iterator = this.stringletPanel.getFixed().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getLabel());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private void proteinInteractionEvent() {
        this.stringSettings.clear();
        this.stringSettings.init();
        this.updateLegend();
        this.stringletPanel.updateStringSettings(this.stringSettings);
        this.legendPanel.repaint();
    }
    
    private void cogInteractionEvent() {
        this.stringSettings.clear();
        this.stringSettings.initCOGS();
        this.updateLegend();
        this.stringletPanel.updateStringSettings(this.stringSettings);
        this.legendPanel.repaint();
    }
    
    private void setEdgeLenEvent() {
        final String showInputDialog = JOptionPane.showInputDialog("Enter desired edge length in pixels:\nSet to 0 for default length", String.valueOf((int)this.stringletPanel.getEdgeLen()));
        if (showInputDialog != null && showInputDialog.length() > 0) {
            try {
                this.stringletPanel.setEdgeLen(Integer.parseInt(showInputDialog));
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect input", "An integer is required for this action", 0);
            }
        }
    }
    
    public void waitForLoad(final String s, final int loadType) throws IOException, DataFormatException {
        (this.dl = new DataLoader(600, 600, s)).setLoadType(loadType);
        this.timer = new Timer(500, this.runLoad);
        this.dl.start();
        this.timer.start();
        this.setCursor(Cursor.getPredefinedCursor(3));
    }
    
    private void hideShowInteractionsEvent() {
        final Container contentPane = this.getContentPane();
        if (!this.hideInteractions) {
            contentPane.remove(this.scalerPanel);
            this.hideShowMenuItem.setText("Show");
            this.hideInteractions = true;
        }
        else {
            contentPane.add("East", this.scalerPanel);
            this.hideShowMenuItem.setText("Hide");
            this.hideInteractions = false;
        }
        this.validate();
    }
    
    static {
        darkBackground = new Color(0.33f, 0.0f, 0.0f);
        foreground = new Color(1.0f, 1.0f, 1.0f);
        STRINGCOLOR = new Color(161, 173, 236);
    }
}
