// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import medusa.graph.Graph;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JDialog;

public class DataConnection extends JDialog implements ItemListener, ActionListener
{
    String intialSelection;
    private static DataConnection dataConnection;
    final int preferredHeight = 20;
    final int preferredWidth = 50;
    final Color stringColor;
    private ArrayList queryList;
    private boolean cogMode;
    private static Graph graph;
    AnimPanel ap;
    private String organism;
    private float cutoff;
    private ButtonGroup buttonGroup1;
    private JButton cancelFinalButton;
    private JButton cancelFirstButton;
    private JCheckBox currentNodesCheckBox;
    private JTabbedPane dataConnectionTabbedPane;
    private JComboBox depthComboBox;
    private JLabel geneLabel;
    private JComboBox organismBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel queryPanel;
    private JPanel dataPanel;
    private JPanel selectPanel;
    private JPanel subDataPanel;
    private JRadioButton exampleNodeRadioButton1;
    private JRadioButton exampleNodeRadioButton2;
    private JRadioButton jRadioButton3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea nodeTextArea;
    private JButton submitFinalButton;
    private JButton submitFirstButton;
    private JPanel acceptPanel;
    private JLabel acceptLabel;
    private JButton colorButton;
    private JButton shapeButton;
    private JButton acceptAndCloseButton;
    private JButton cancelAndCloseButton;
    private JPanel subAcceptPanel;
    private JButton clearTextButton;
    private JCheckBox cogBox;
    private final int port = 4444;
    private final String url = "string.embl.de";
    private ProgressMonitor progressBar;
    private JProgressBar progressBar2;
    private ActionListener updateEnergyBar;
    private Timer timer;
    private Timer timer2;
    private CallString cs;
    private GetGraph gg;
    GetCogGraph gcg;
    private ActionListener callStringActionListener;
    private ActionListener getGraphActionListener;
    private ActionListener getCogGraphActionListener;
    private Annotator ag;
    private ActionListener annotateGraphActionListener;
    
    public DataConnection(final Frame frame, final Component locationRelativeTo) {
        super(frame, "String", true);
        this.intialSelection = "";
        this.stringColor = new Color(161, 173, 236);
        this.queryList = new ArrayList();
        this.cogMode = false;
        this.organism = "All";
        this.cutoff = 0.3f;
        this.callStringActionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (DataConnection.this.progressBar.isCanceled() || DataConnection.this.cs.isDone()) {
                    DataConnection.this.timer.stop();
                    DataConnection.this.cs.stop();
                    DataConnection.this.setCursor(null);
                    DataConnection.this.selectPanel = DataConnection.this.cs.getPanel();
                    DataConnection.this.jScrollPane2.setViewportView(DataConnection.this.selectPanel);
                    final int indexOfTab = DataConnection.this.dataConnectionTabbedPane.indexOfTab("Data");
                    DataConnection.this.dataConnectionTabbedPane.setEnabledAt(indexOfTab, true);
                    DataConnection.this.dataConnectionTabbedPane.setSelectedIndex(indexOfTab);
                }
            }
        };
        this.getGraphActionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.progressBar2.setString(DataConnection.this.gg.getProgress() + " entries");
                if (DataConnection.this.gg.isDone()) {
                    DataConnection.this.timer2.stop();
                    DataConnection.this.gg.stop();
                    DataConnection.this.progressBar2.setString("creating graph");
                    DataConnection.graph = DataConnection.this.gg.getGraph();
                    DataConnection.graph.autoFixOrientation();
                    DataConnection.this.annotateGraph();
                }
            }
        };
        this.getCogGraphActionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.progressBar2.setString(DataConnection.this.gcg.getProgress() + " entries");
                if (DataConnection.this.gcg.isDone()) {
                    DataConnection.this.timer2.stop();
                    DataConnection.this.gcg.stop();
                    DataConnection.graph = DataConnection.this.gcg.getGraph();
                    DataConnection.graph.autoFixOrientation();
                    DataConnection.this.updateAccept();
                    DataConnection.this.progressBar2.setIndeterminate(false);
                    DataConnection.this.progressBar2.setString(DataConnection.this.gcg.getProgress() + " entries");
                    DataConnection.this.setCursor(null);
                    if (DataConnection.this.gcg.getStatus().compareTo("ok") == 0) {
                        final int indexOfTab = DataConnection.this.dataConnectionTabbedPane.indexOfTab("Accept");
                        DataConnection.this.dataConnectionTabbedPane.setEnabledAt(indexOfTab, true);
                        DataConnection.this.dataConnectionTabbedPane.setSelectedIndex(indexOfTab);
                    }
                    else {
                        DataConnection.this.selectPanel.add(new JLabel("Sorry, no matches could be found."));
                        DataConnection.this.jScrollPane2.setViewportView(DataConnection.this.selectPanel);
                        final int indexOfTab2 = DataConnection.this.dataConnectionTabbedPane.indexOfTab("Data");
                        DataConnection.this.dataConnectionTabbedPane.setEnabledAt(indexOfTab2, true);
                        DataConnection.this.dataConnectionTabbedPane.setSelectedIndex(indexOfTab2);
                    }
                }
            }
        };
        this.annotateGraphActionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.progressBar2.setValue(DataConnection.this.ag.getProgress());
                if (DataConnection.this.ag.isDone()) {
                    DataConnection.this.timer2.stop();
                    DataConnection.this.ag.stop();
                    DataConnection.this.updateAccept();
                    DataConnection.this.progressBar2.setIndeterminate(false);
                    DataConnection.this.progressBar2.setString(DataConnection.this.gg.getProgress() + " entries");
                    DataConnection.this.setCursor(null);
                    final int indexOfTab = DataConnection.this.dataConnectionTabbedPane.indexOfTab("Accept");
                    DataConnection.this.dataConnectionTabbedPane.setEnabledAt(indexOfTab, true);
                    DataConnection.this.dataConnectionTabbedPane.setSelectedIndex(indexOfTab);
                }
            }
        };
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.initComponents();
        this.setLocationRelativeTo(locationRelativeTo);
    }
    
    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.jRadioButton3 = new JRadioButton();
        this.jLabel1 = new JLabel();
        this.dataConnectionTabbedPane = new JTabbedPane();
        this.queryPanel = new JPanel();
        this.geneLabel = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.nodeTextArea = new JTextArea();
        this.cancelFirstButton = new JButton();
        this.jLabel3 = new JLabel();
        this.organismBox = new JComboBox();
        this.submitFirstButton = new JButton();
        this.currentNodesCheckBox = new JCheckBox();
        this.dataPanel = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.selectPanel = new JPanel();
        this.exampleNodeRadioButton2 = new JRadioButton();
        this.exampleNodeRadioButton1 = new JRadioButton();
        this.subDataPanel = new JPanel();
        this.jLabel4 = new JLabel("label 4");
        this.depthComboBox = new JComboBox();
        this.submitFinalButton = new JButton();
        this.cancelFinalButton = new JButton();
        this.jLabel5 = new JLabel();
        this.jLabel2 = new JLabel();
        this.clearTextButton = new JButton("Clear");
        this.cogBox = new JCheckBox("Cog mode");
        this.jRadioButton3.setText("jRadioButton3");
        this.jLabel1.setBackground(new Color(161, 173, 236));
        this.jLabel1.setFont(new Font("Times New Roman", 1, 24));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("STRING Data Connection");
        this.jLabel1.setOpaque(true);
        this.getContentPane().add(this.jLabel1, "North");
        this.dataConnectionTabbedPane.setBackground(new Color(161, 173, 236));
        this.dataConnectionTabbedPane.setOpaque(true);
        this.queryPanel.setLayout(new GridBagLayout());
        this.queryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.queryPanel.setPreferredSize(new Dimension(300, 200));
        this.geneLabel.setText("Gene name");
        this.geneLabel.setToolTipText("Enter gene names here");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        this.queryPanel.add(this.geneLabel, gridBagConstraints);
        this.jScrollPane1.setPreferredSize(new Dimension(200, 80));
        this.jScrollPane1.setViewportView(this.nodeTextArea);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 7;
        gridBagConstraints2.gridheight = 2;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.ipadx = 176;
        gridBagConstraints2.ipady = 56;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(3, 3, 3, 3);
        this.queryPanel.add(this.jScrollPane1, gridBagConstraints2);
        this.cancelFirstButton.setText("Cancel");
        this.cancelFirstButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.cancelFirstButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 7;
        gridBagConstraints3.gridy = 6;
        gridBagConstraints3.insets = new Insets(3, 3, 3, 3);
        this.queryPanel.add(this.cancelFirstButton, gridBagConstraints3);
        this.jLabel3.setText("Minimum score cutoff");
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 3;
        gridBagConstraints4.gridwidth = 5;
        gridBagConstraints4.ipady = 5;
        gridBagConstraints4.insets = new Insets(3, 29, 3, 29);
        this.queryPanel.add(this.jLabel3, gridBagConstraints4);
        this.buildOrganismBox();
        this.organismBox.setPreferredSize(new Dimension(40, 20));
        this.organismBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                DataConnection.this.organismBoxItemStateChanged(itemEvent);
            }
        });
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 5;
        gridBagConstraints5.gridy = 3;
        gridBagConstraints5.gridwidth = 3;
        gridBagConstraints5.gridheight = 3;
        gridBagConstraints5.ipadx = 74;
        gridBagConstraints5.ipady = 4;
        gridBagConstraints5.insets = new Insets(3, 3, 3, 3);
        this.queryPanel.add(this.organismBox, gridBagConstraints5);
        this.submitFirstButton.setText("Submit");
        this.submitFirstButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.submitFirstButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 2;
        gridBagConstraints6.gridy = 6;
        gridBagConstraints6.gridwidth = 3;
        gridBagConstraints6.insets = new Insets(3, 3, 3, 3);
        this.queryPanel.add(this.submitFirstButton, gridBagConstraints6);
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 6;
        gridBagConstraints7.gridy = 0;
        this.queryPanel.add(this.cogBox);
        this.cogBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                DataConnection.this.cogBoxItemStateChanged(itemEvent);
            }
        });
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 4;
        gridBagConstraints8.gridy = 0;
        this.queryPanel.add(this.clearTextButton, gridBagConstraints8);
        this.clearTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.clearTextButtonActionPerformed(actionEvent);
            }
        });
        this.dataConnectionTabbedPane.addTab("Query", this.queryPanel);
        this.dataPanel.setLayout(new BorderLayout());
        this.dataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.jScrollPane2.setPreferredSize(new Dimension(300, 300));
        this.selectPanel.setLayout(new BoxLayout(this.selectPanel, 1));
        this.selectPanel.setBackground(new Color(255, 255, 255));
        this.jScrollPane2.setViewportView(this.selectPanel);
        this.dataPanel.add(this.jScrollPane2, "Center");
        this.jLabel4.setText("Depth");
        (this.progressBar2 = new JProgressBar(0, 500)).setStringPainted(true);
        this.progressBar2.setValue(0);
        this.progressBar2.setString("Getting data");
        this.progressBar2.setIndeterminate(false);
        this.subDataPanel.add(this.progressBar2);
        this.depthComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                DataConnection.this.depthComboBoxItemStateChanged(itemEvent);
            }
        });
        this.submitFinalButton.setText("Submit");
        this.submitFinalButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.submitDataButtonActionPerformed(actionEvent);
            }
        });
        this.subDataPanel.add(this.submitFinalButton);
        this.cancelFinalButton.setText("Cancel");
        this.cancelFinalButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.cancelFinalButtonActionPerformed(actionEvent);
            }
        });
        this.subDataPanel.add(this.cancelFinalButton);
        this.dataPanel.add(this.subDataPanel, "South");
        this.jLabel5.setText("Select genes");
        this.dataPanel.add(this.jLabel5, "North");
        this.dataConnectionTabbedPane.addTab("Data", this.dataPanel);
        this.dataConnectionTabbedPane.setEnabledAt(this.dataConnectionTabbedPane.indexOfTab("Data"), false);
        this.getContentPane().add(this.dataConnectionTabbedPane, "Center");
        this.ap = new AnimPanel();
        this.getContentPane().add(this.ap, "West");
        this.acceptPanel = new JPanel();
        this.subAcceptPanel = new JPanel();
        (this.acceptLabel = new JLabel()).setHorizontalAlignment(0);
        (this.colorButton = new JButton("Color")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.colorButtonActionPerformed(actionEvent);
            }
        });
        (this.shapeButton = new JButton("Shape")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.shapeButtonActionPerformed(actionEvent);
            }
        });
        (this.acceptAndCloseButton = new JButton("Accept")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.submitFinalButtonActionPerformed(actionEvent);
            }
        });
        (this.cancelAndCloseButton = new JButton("Cancel")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataConnection.this.cancelFinalButtonActionPerformed(actionEvent);
            }
        });
        this.acceptPanel.setLayout(new BorderLayout());
        this.subAcceptPanel.setLayout(new FlowLayout());
        this.acceptPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.subAcceptPanel.add(this.acceptAndCloseButton);
        this.subAcceptPanel.add(this.cancelAndCloseButton);
        this.subAcceptPanel.add(this.colorButton);
        this.subAcceptPanel.add(this.shapeButton);
        this.acceptPanel.add("Center", this.acceptLabel);
        this.acceptPanel.add("South", this.subAcceptPanel);
        this.dataConnectionTabbedPane.addTab("Accept", this.acceptPanel);
        this.dataConnectionTabbedPane.setEnabledAt(this.dataConnectionTabbedPane.indexOfTab("Accept"), false);
        this.pack();
    }
    
    public void exampleNodes() {
        this.nodeTextArea.append("TRPB_SYNY3");
    }
    
    private void setInitialSelection(final String s) {
        this.nodeTextArea.append(s);
    }
    
    private void updateAccept() {
        this.acceptLabel.setText("<html>Data to import:<p><center>nodes: <b>" + DataConnection.graph.getNodeSize() + "</b><p>edges: <b>" + DataConnection.graph.getEdgeSize() + "</b><p></center><p>Accept this?");
    }
    
    private void submitFinalButtonActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    private void colorButtonActionPerformed(final ActionEvent actionEvent) {
        final Color showDialog = JColorChooser.showDialog(this.getRootPane(), "Choose node color", Color.red);
        if (showDialog != null) {
            DataConnection.graph.setNodeColor(showDialog);
        }
    }
    
    private void shapeButtonActionPerformed(final ActionEvent actionEvent) {
        DataConnection.graph.setNodeShape(JOptionPane.showOptionDialog(this, "Choose a node shape", "Shape", 1, 3, null, new Object[] { "circle", "rectangle", "triangle", "diamond" }, "circle"));
    }
    
    private void submitDataButtonActionPerformed(final ActionEvent actionEvent) {
        this.getGraph();
    }
    
    private void depthComboBoxItemStateChanged(final ItemEvent itemEvent) {
    }
    
    private void organismBoxItemStateChanged(final ItemEvent itemEvent) {
        this.cutoff = (float)this.organismBox.getSelectedItem();
    }
    
    private void cancelFirstButtonActionPerformed(final ActionEvent actionEvent) {
        this.cancelAction();
    }
    
    private void clearTextButtonActionPerformed(final ActionEvent actionEvent) {
        this.nodeTextArea.setText("");
    }
    
    private void submitFirstButtonActionPerformed(final ActionEvent actionEvent) {
        if (!this.cogMode) {
            this.connect();
        }
        if (this.cogMode) {
            this.getCogGraph();
        }
    }
    
    private void cogBoxItemStateChanged(final ItemEvent itemEvent) {
        this.cogMode = this.cogBox.isSelected();
    }
    
    private void currentNodesCheckBoxItemStateChanged(final ItemEvent itemEvent) {
    }
    
    private void cancelFinalButtonActionPerformed(final ActionEvent actionEvent) {
        this.cancelAction();
    }
    
    private void exitForm(final WindowEvent windowEvent) {
        System.exit(0);
    }
    
    private void cancelAction() {
        this.ap.stop();
        DataConnection.graph = null;
        this.setVisible(false);
    }
    
    private void buildQuery() {
        this.queryList.clear();
        final String[] split = this.nodeTextArea.getText().split("\\n");
        String string = "";
        if (this.organism.compareTo("All") != 0) {
            string = "_" + this.organism;
        }
        for (int i = 0; i < split.length; ++i) {
            this.queryList.add("GN:" + split[i] + string);
        }
    }
    
    public void testingThreads() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("1076.CAE25513");
        list.add("1148.TRPB_SYNY3");
        list.add("117.TRPB_RHOBA");
        list.add("118099.TRPB_BUCAI");
        this.gg = new GetGraph("string.embl.de", 4444, list, 0.0f);
        (this.timer2 = new Timer(200, this.getGraphActionListener)).start();
        this.gg.start();
    }
    
    public void connect() {
        final String[] split = this.nodeTextArea.getText().split("\\n");
        this.progressBar = new ProgressMonitor(this, "Sending query", "", 0, split.length);
        this.cs = new CallString("string.embl.de", 4444, split);
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.timer = new Timer(200, this.callStringActionListener);
        this.cs.start();
        this.timer.start();
    }
    
    public void getGraph() {
        this.gg = new GetGraph("string.embl.de", 4444, this.buildSelectionList(), this.cutoff);
        this.progressBar2.setIndeterminate(true);
        this.progressBar2.repaint();
        this.setCursor(Cursor.getPredefinedCursor(3));
        (this.timer2 = new Timer(200, this.getGraphActionListener)).start();
        this.gg.start();
    }
    
    private void annotateGraph() {
        this.ag = new Annotator("string.embl.de", 4444, DataConnection.graph);
        this.progressBar2.setIndeterminate(false);
        this.progressBar2.setMaximum(this.ag.getMax());
        this.progressBar2.setString("Annotating");
        this.progressBar2.setValue(0);
        this.progressBar2.repaint();
        this.setCursor(Cursor.getPredefinedCursor(3));
        (this.timer2 = new Timer(200, this.annotateGraphActionListener)).start();
        this.ag.start();
    }
    
    private void getCogGraph() {
        final String[] split = this.nodeTextArea.getText().split("\\n");
        final int indexOfTab = this.dataConnectionTabbedPane.indexOfTab("Data");
        this.dataConnectionTabbedPane.setEnabledAt(indexOfTab, true);
        this.dataConnectionTabbedPane.setSelectedIndex(indexOfTab);
        this.gcg = new GetCogGraph("string.embl.de", 4444, split, this.cutoff);
        this.progressBar2.setIndeterminate(true);
        this.progressBar2.repaint();
        this.setCursor(Cursor.getPredefinedCursor(3));
        (this.timer2 = new Timer(200, this.getCogGraphActionListener)).start();
        this.gcg.start();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public ArrayList buildSelectionList() {
        final int componentCount = this.selectPanel.getComponentCount();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < componentCount; ++i) {
            final JRadioButton radioButton = (JRadioButton)this.selectPanel.getComponent(i);
            if (radioButton.isSelected()) {
                list.add(radioButton.getText());
            }
        }
        return list;
    }
    
    private void buildOrganismBox() {
        for (int i = 1; i < 10; ++i) {
            this.organismBox.addItem(new Float(i / 10.0f));
        }
        this.organismBox.setSelectedIndex(3);
        this.cutoff = 0.3f;
    }
    
    public Graph returnGraph() {
        return DataConnection.graph;
    }
    
    public static Graph showDialog(final Component component, final Component component2) {
        (DataConnection.dataConnection = new DataConnection(JOptionPane.getFrameForComponent(component), component2)).setSize(450, 300);
        DataConnection.dataConnection.setVisible(true);
        return DataConnection.graph;
    }
    
    public static Graph showDialog(final String initialSelection, final Component component, final Component component2) {
        (DataConnection.dataConnection = new DataConnection(JOptionPane.getFrameForComponent(component), component2)).setSize(450, 300);
        DataConnection.dataConnection.setInitialSelection(initialSelection);
        DataConnection.dataConnection.setVisible(true);
        return DataConnection.graph;
    }
    
    static {
        DataConnection.graph = new Graph();
    }
}
