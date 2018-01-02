// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.otnip.tools.files.FileTools;
import java.util.prefs.Preferences;
import javax.swing.JDialog;
import com.otnip.tools.files.FileChooser;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.otnip.irig106.chapter10.PacketHeader;
import com.otnip.irig106.chapter10.tools.RelativeTimeCounter;
import com.otnip.irig106.chapter10.packets.TimePacket_Format1;
import com.otnip.irig106.chapter10.Packet;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import com.otnip.irig106.chapter10viewer.tools.IRIGChapter10ToolsUI;
import java.io.File;
import javax.swing.JOptionPane;
import com.otnip.irig106.chapter9.ui.tmats.TMATSUI;
import com.otnip.irig106.chapter9.TMATS;
import com.otnip.irig106.chapter10.tools.IRIGChapter10Tools;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.otnip.tools.IconFactory;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import com.otnip.irig106.chapter10.file.IRIG106Chapter10File;
import java.util.HashMap;
import javax.swing.JFrame;

public class IRIG106Chapter10FileViewer extends JFrame
{
    private static final long serialVersionUID = 0L;
    private static final HashMap<Integer, String> labels;
    private IRIG106Chapter10FileStatistics stats;
    private IRIG106Chapter10File chapter10File;
    private PacketViewerWrapper[] packets;
    private int packetsToView;
    private int startPacketIndex;
    private JPanel controlPanel;
    private JTextField currentPacketsField;
    private JLabel currentPacketsLabel;
    private JPanel globalStatisticsPanel;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JTabbedPane mainTabbedPane;
    private JButton nextPacketBlockButton;
    private JButton packetsToViewButton;
    private JTextField packetsToViewField;
    private JButton previousPacketBlockButton;
    private JPanel rawPacketsPanel;
    private JScrollPane rawPacketsScrollPane;
    private JSlider seekBar;
    private JButton sourceFileButton;
    private JTextField sourceFileLabel;
    private JPanel statsChartPanel;
    private JTabbedPane statsPanel;
    private JScrollPane statsScrollPane;
    private JTextPane statsTextPane;
    private JTable table;
    private JPanel tmatsView;
    private JPanel toolsPanel;
    
    public IRIG106Chapter10FileViewer() {
        this.packetsToView = 1000;
        this.startPacketIndex = 0;
        this.initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        for (int i = this.seekBar.getMinimum(); i < this.seekBar.getMaximum(); ++i) {
            IRIG106Chapter10FileViewer.labels.put(i, Integer.toString(i));
        }
        this.seekBar.createStandardLabels(10);
        this.table.setDefaultRenderer(Object.class, new LocalTableCellRenderer());
        this.table.setDefaultEditor(Object.class, null);
        this.setIconImage(IconFactory.getIcon("groundstation").getImage());
    }
    
    private synchronized void loadGlobalStatistics() {
        if (this.stats != null) {
            return;
        }
        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    IRIG106Chapter10FileViewer.this.stats = new IRIG106Chapter10FileStatistics(IRIG106Chapter10FileViewer.this.chapter10File.getFile());
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.removeAll();
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.setLayout(new GridBagLayout());
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.add(IRIG106Chapter10FileViewer.this.stats.getProgressIndicator());
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.validate();
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.repaint();
                    try {
                        IRIG106Chapter10FileViewer.this.stats.analyze();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    IRIG106Chapter10FileViewer.this.statsTextPane.setText(IRIG106Chapter10FileViewer.this.stats.toString());
                    IRIG106Chapter10FileViewer.this.statsChartPanel.removeAll();
                    IRIG106Chapter10FileViewer.this.statsChartPanel.add(IRIG106Chapter10FileViewer.this.stats.getCharts());
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.removeAll();
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.setLayout(new BorderLayout());
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.add(IRIG106Chapter10FileViewer.this.statsPanel);
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.validate();
                    IRIG106Chapter10FileViewer.this.globalStatisticsPanel.repaint();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
    
    private void setPackets(final PacketViewerWrapper[] packets) {
        this.packets = packets;
        final DefaultTableModel model = new DefaultTableModel();
        final Object[] o = { "#", "Seq #", "Packet Type", "Channel ID", "Packet Length (B)", "Time" };
        model.setColumnIdentifiers(o);
        model.setRowCount(packets.length);
        this.table.setModel(model);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(75);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(75);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(225);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(75);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(125);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(150);
    }
    
    private void updateTMATSDisplays() {
        try {
            final String s = IRIGChapter10Tools.getTMATS(this.chapter10File.getFile());
            final TMATSUI ui = new TMATSUI(new TMATS(s));
            this.tmatsView.removeAll();
            this.tmatsView.add(ui);
            this.tmatsView.validate();
            this.tmatsView.repaint();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error Updating Display", 0);
        }
    }
    
    public void setCurrentFile(final File file) throws Exception {
        this.chapter10File = new IRIG106Chapter10File(file);
        this.toolsPanel.removeAll();
        this.toolsPanel.add(new IRIGChapter10ToolsUI(this.chapter10File.getFile()));
        this.setTitle("IRIG 106 Chapter 10 File Viewer:  " + file.getName());
        this.sourceFileLabel.setText(file.getAbsolutePath());
        this.startPacketIndex = 0;
        this.mainTabbedPane.setEnabled(true);
        this.updateTMATSDisplays();
        this.updateDisplay();
    }
    
    private void updateDisplay() {
        ArrayList<PacketViewerWrapper> packets = null;
        if (this.chapter10File.getFile() != null) {
            try {
                final RelativeTimeCounter timer = this.chapter10File.getRelativeTimerCounter(this.startPacketIndex);
                this.packetsToViewField.setText(Integer.toString(this.packetsToView));
                this.currentPacketsField.setText(this.startPacketIndex + " -> " + (this.startPacketIndex + this.packetsToView - 1));
                final RandomAccessFile ras = new RandomAccessFile(this.chapter10File.getFile(), "r");
                ras.seek(this.chapter10File.getPacketPosition(this.startPacketIndex));
                final double scale = 100.0 / this.chapter10File.getFile().length();
                final int numberOfPackets = Math.min(this.chapter10File.getNumberOfPackets() - this.startPacketIndex, this.packetsToView);
                packets = new ArrayList<PacketViewerWrapper>(numberOfPackets);
                for (int packetIndex = 0; packetIndex < numberOfPackets; ++packetIndex) {
                    final PacketViewerWrapper packet = new PacketViewerWrapper(ras, this.startPacketIndex + packetIndex);
                    final PacketHeader packetHeader = packet.getHeader();
                    if (packetHeader.getDataType() == Packet.PacketType.Time_Format1) {
                        final TimePacket_Format1 timePacket = (TimePacket_Format1)packet.getPacketBody();
                        timer.setReference(timePacket.getIRIGTime(), packetHeader.getRelativeTimeCounter());
                    }
                    packet.irigTime = timer.getTime(packetHeader.getRelativeTimeCounter());
                    packets.add(packet);
                }
                this.seekBar.setValue((int)(ras.getFilePointer() * scale));
                ras.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error Updating Display", 0);
            }
            finally {
                this.setPackets(packets.toArray(new PacketViewerWrapper[packets.size()]));
            }
        }
    }
    
    private String getString(final int row, final int column) {
        String result = null;
        if (this.packets != null) {
            final PacketViewerWrapper packet = this.packets[row];
            final PacketHeader packetHeader = packet.getHeader();
            switch (column) {
                case 0: {
                    result = Long.toString(packet.packetIndex);
                    break;
                }
                case 1: {
                    result = Integer.toString(packetHeader.getSequenceNumber());
                    break;
                }
                case 2: {
                    result = packetHeader.getDataType().toString();
                    break;
                }
                case 3: {
                    result = Integer.toString(packetHeader.getChannelID());
                    break;
                }
                case 4: {
                    result = Integer.toString(packetHeader.getPacketLength());
                    break;
                }
                case 5: {
                    result = IRIGChapter10Tools.getIRIGString(packet.irigTime);
                    break;
                }
            }
        }
        return result;
    }
    
    private void initComponents() {
        this.jPanel3 = new JPanel();
        this.sourceFileLabel = new JTextField();
        this.sourceFileButton = new JButton();
        this.statsPanel = new JTabbedPane();
        this.statsChartPanel = new JPanel();
        this.statsScrollPane = new JScrollPane();
        this.statsTextPane = new JTextPane();
        this.mainTabbedPane = new JTabbedPane();
        this.tmatsView = new JPanel();
        this.rawPacketsPanel = new JPanel();
        this.rawPacketsScrollPane = new JScrollPane();
        this.table = new JTable();
        this.controlPanel = new JPanel();
        this.currentPacketsLabel = new JLabel();
        this.currentPacketsField = new JTextField();
        this.packetsToViewField = new JTextField();
        this.packetsToViewButton = new JButton();
        this.jPanel1 = new JPanel();
        this.previousPacketBlockButton = new JButton();
        this.seekBar = new JSlider();
        this.nextPacketBlockButton = new JButton();
        this.globalStatisticsPanel = new JPanel();
        this.toolsPanel = new JPanel();
        this.jPanel3.setLayout(new BorderLayout());
        this.sourceFileLabel.setEditable(false);
        this.jPanel3.add(this.sourceFileLabel, "Center");
        this.sourceFileButton.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/document_pulse.png")));
        this.sourceFileButton.setText("Source File");
        this.sourceFileButton.setToolTipText("Sets The Source File For Analysis");
        this.sourceFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIG106Chapter10FileViewer.this.sourceFileButtonActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.sourceFileButton, "West");
        this.statsChartPanel.setLayout(new BorderLayout());
        this.statsPanel.addTab("Graphical Summary", IconFactory.getIcon("/IconExperience/icons/24x24/shadow/pie-chart.png"), this.statsChartPanel);
        this.statsScrollPane.setMaximumSize(new Dimension(800, 600));
        this.statsScrollPane.setPreferredSize(new Dimension(640, 480));
        this.statsScrollPane.setViewportView(this.statsTextPane);
        this.statsPanel.addTab("Text Summary", IconFactory.getIcon("/IconExperience/icons/24x24/shadow/text_code.png"), this.statsScrollPane);
        this.setDefaultCloseOperation(3);
        this.setTitle("IRIG 106 Chapter 10 File Viewer");
        this.setForeground(Color.black);
        this.addComponentListener(new ComponentAdapter() {
            public void componentMoved(final ComponentEvent evt) {
                IRIG106Chapter10FileViewer.this.formComponentMoved(evt);
            }
            
            public void componentResized(final ComponentEvent evt) {
                IRIG106Chapter10FileViewer.this.formComponentResized(evt);
            }
        });
        this.mainTabbedPane.setTabPlacement(4);
        this.mainTabbedPane.setOpaque(true);
        this.tmatsView.setLayout(new BorderLayout());
        this.mainTabbedPane.addTab("MetaData/TMATS View", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/atom.png")), this.tmatsView, "TMATS/Meta-Data Interface");
        this.rawPacketsPanel.setLayout(new BorderLayout());
        this.table.setModel(new DefaultTableModel(new Object[][] { new Object[0], new Object[0], new Object[0], new Object[0] }, new String[0]));
        this.table.setToolTipText("Double-Click To Inspect Packet");
        this.table.setAutoResizeMode(0);
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                IRIG106Chapter10FileViewer.this.tableMouseClicked(evt);
            }
        });
        this.rawPacketsScrollPane.setViewportView(this.table);
        this.rawPacketsPanel.add(this.rawPacketsScrollPane, "Center");
        this.controlPanel.setLayout(new GridBagLayout());
        this.currentPacketsLabel.setHorizontalAlignment(0);
        this.currentPacketsLabel.setText("Current Packets");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 6, 5, 5);
        this.controlPanel.add(this.currentPacketsLabel, gridBagConstraints);
        this.currentPacketsField.setColumns(20);
        this.currentPacketsField.setEditable(false);
        this.currentPacketsField.setHorizontalAlignment(0);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 10);
        this.controlPanel.add(this.currentPacketsField, gridBagConstraints);
        this.packetsToViewField.setColumns(6);
        this.packetsToViewField.setEditable(false);
        this.packetsToViewField.setHorizontalAlignment(0);
        this.packetsToViewField.setText("1000");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        this.controlPanel.add(this.packetsToViewField, gridBagConstraints);
        this.packetsToViewButton.setText("Packets To View");
        this.packetsToViewButton.setToolTipText("Sets the number of desired packets to view at once");
        this.packetsToViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIG106Chapter10FileViewer.this.packetsToViewButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 4, 5);
        this.controlPanel.add(this.packetsToViewButton, gridBagConstraints);
        this.jPanel1.setLayout(new GridBagLayout());
        this.previousPacketBlockButton.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/media_step_back.png")));
        this.previousPacketBlockButton.setToolTipText("Previous Set Of Packets");
        this.previousPacketBlockButton.setRolloverIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/plain/media_step_back.png")));
        this.previousPacketBlockButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIG106Chapter10FileViewer.this.previousPacketBlockButtonActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.previousPacketBlockButton, new GridBagConstraints());
        this.seekBar.setMajorTickSpacing(25);
        this.seekBar.setMinorTickSpacing(5);
        this.seekBar.setPaintLabels(true);
        this.seekBar.setPaintTicks(true);
        this.seekBar.setValue(0);
        this.seekBar.setEnabled(false);
        this.jPanel1.add(this.seekBar, new GridBagConstraints());
        this.nextPacketBlockButton.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/plain/media_step_forward.png")));
        this.nextPacketBlockButton.setToolTipText("Next Set Of Packets");
        this.nextPacketBlockButton.setRolloverIcon(IconFactory.getIcon("/IconExperience/icons/24x24/plain/media_step_forward.png"));
        this.nextPacketBlockButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                IRIG106Chapter10FileViewer.this.nextPacketBlockButtonActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.nextPacketBlockButton, new GridBagConstraints());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        this.controlPanel.add(this.jPanel1, gridBagConstraints);
        this.rawPacketsPanel.add(this.controlPanel, "North");
        this.mainTabbedPane.addTab("Raw Packets View", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/text_binary.png")), this.rawPacketsPanel, "Packet-Level Interface");
        this.globalStatisticsPanel.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent evt) {
                IRIG106Chapter10FileViewer.this.globalStatisticsPanelComponentShown(evt);
            }
        });
        this.globalStatisticsPanel.setLayout(new BorderLayout());
        this.mainTabbedPane.addTab("Global Statistics", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/pie-chart.png")), this.globalStatisticsPanel, "View Statistics For Entire Chapter10 File");
        this.toolsPanel.setLayout(new BorderLayout());
        this.mainTabbedPane.addTab("Tools", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/toolbox.png")), this.toolsPanel);
        this.getContentPane().add(this.mainTabbedPane, "Center");
    }
    
    private void nextPacketBlockButtonActionPerformed(final ActionEvent evt) {
        this.startPacketIndex += this.packetsToView;
        if (this.startPacketIndex > this.chapter10File.getNumberOfPackets()) {
            this.startPacketIndex -= this.packetsToView;
        }
        this.updateDisplay();
    }
    
    private void previousPacketBlockButtonActionPerformed(final ActionEvent evt) {
        this.startPacketIndex -= this.packetsToView;
        if (this.startPacketIndex < 0) {
            this.startPacketIndex = 0;
        }
        this.updateDisplay();
    }
    
    private void packetsToViewButtonActionPerformed(final ActionEvent evt) {
        final String newValue = JOptionPane.showInputDialog(this, "Input", "Packets TO View", 3);
        if (newValue != null) {
            try {
                final int value = Integer.parseInt(newValue);
                if (value < 0) {
                    throw new Exception("");
                }
                this.packetsToView = value;
                this.updateDisplay();
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could Not Parse " + newValue, "Error Parsing Input", 0);
            }
        }
    }
    
    private void sourceFileButtonActionPerformed(final ActionEvent evt) {
        try {
            final File file = FileChooser.showOpenDialog(this, "Select Chapter 10 File", "ch10, c10", null);
            if (file != null) {
                this.chapter10File = new IRIG106Chapter10File(file);
                this.setTitle("IRIG 106 Chapter 10 File Viewer:  " + file.getName());
                this.sourceFileLabel.setText(file.getAbsolutePath());
                this.startPacketIndex = 0;
                this.mainTabbedPane.setEnabled(true);
                this.updateTMATSDisplays();
                this.updateDisplay();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void tableMouseClicked(final MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            try {
                System.out.println(this.packets[this.table.getSelectedRow()].getPacketBody());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            final JDialog dialog = new JDialog();
            final PacketBodyDataViewer viewer = new PacketBodyDataViewer(this.chapter10File, this.startPacketIndex + this.table.getSelectedRow(), dialog);
            try {
                viewer.setPacket(this.packets[this.table.getSelectedRow()]);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            dialog.getContentPane().add(viewer);
            dialog.setSize(640, 480);
            dialog.setVisible(true);
        }
    }
    
    private void globalStatisticsPanelComponentShown(final ComponentEvent evt) {
        this.loadGlobalStatistics();
    }
    
    private void formComponentMoved(final ComponentEvent evt) {
        final Preferences preferences = Preferences.userNodeForPackage(this.getClass());
        preferences.putInt("x", this.getX());
        preferences.putInt("y", this.getY());
        try {
            preferences.flush();
        }
        catch (Exception ex) {}
    }
    
    private void formComponentResized(final ComponentEvent evt) {
        final Preferences preferences = Preferences.userNodeForPackage(this.getClass());
        preferences.putInt("width", this.getWidth());
        preferences.putInt("height", this.getHeight());
        try {
            preferences.flush();
        }
        catch (Exception ex) {}
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IconFactory.addClasspath("/com/otnip/media");
                FileTools.setFileExtensionAndDescription("ch10", "Chapter 10");
                FileTools.setFileExtensionAndDescription("c10", "Chapter 10");
                FileTools.setFileExtensionAndDescription("tmats", "Telemetry Attributes Transfer Standard");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                final IRIG106Chapter10FileViewer ui = new IRIG106Chapter10FileViewer();
                final File file = FileChooser.showOpenDialog(ui, "Select Chapter 10 File", "ch10, c10", null);
                if (file != null) {
                    try {
                        ui.setCurrentFile(file);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        System.exit(0);
                    }
                }
                else {
                    System.exit(0);
                }
                final Preferences preferences = Preferences.userNodeForPackage(ui.getClass());
                final int width = preferences.getInt("width", 900);
                final int height = preferences.getInt("height", 600);
                ui.setSize(width, height);
                final int x = preferences.getInt("x", 0);
                final int y = preferences.getInt("y", 0);
                ui.setLocation(x, y);
                ui.setVisible(true);
            }
        });
    }
    
    static {
        labels = new HashMap<Integer, String>();
    }
    
    private class LocalTableCellRenderer extends DefaultTableCellRenderer
    {
        private static final long serialVersionUID = 0L;
        JLabel label;
        private final Color[] backgrounds;
        private final Color selectedBackground;
        
        private LocalTableCellRenderer() {
            this.label = new JLabel();
            this.backgrounds = new Color[] { new Color(192, 192, 255), new Color(127, 192, 255) };
            this.selectedBackground = new Color(192, 255, 192);
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(0);
            if (isSelected) {
                label.setBackground(this.selectedBackground);
            }
            else {
                label.setBackground(this.backgrounds[row % 2]);
            }
            if (IRIG106Chapter10FileViewer.this.packets != null) {
                final Packet packet = IRIG106Chapter10FileViewer.this.packets[row];
                final PacketHeader packetHeader = packet.getHeader();
                label.setText(IRIG106Chapter10FileViewer.this.getString(row, column));
            }
            return label;
        }
    }
}
