// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10viewer;

import javax.swing.table.DefaultTableCellRenderer;
import com.otnip.datavisualization.plot1d.PlotPane1D;
import com.otnip.irig106.chapter10.packets.AnalogPacket_Format1;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import com.otnip.irig106.chapter10.Packet;
import com.otnip.irig106.chapter10.file.IRIG106Chapter10File;
import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.JPanel;

public class PacketBodyDataViewer extends JPanel
{
    private static final long serialVersionUID = 0L;
    private static final Color dataBodyColor;
    private static final Color noDataColor;
    private static final Color packetHeaderColor;
    private static final Color fillerColor;
    private final JDialog dialog;
    private final IRIG106Chapter10File chapter10File;
    private int packetIndex;
    private Packet packet;
    private int bytesPerColumn;
    public byte[] data;
    private JSpinner columnsPerRow;
    private JLabel dataColorLegendLabel;
    private JComboBox dataFormat;
    private JLabel fillerColorLegendLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane2;
    private JLabel noDataColoLegendLabel;
    private JLabel packetHeaderColorLegendLabel;
    private JPanel packetInfoPanel;
    private JTextPane packetInfoTextPane;
    private JPanel rawDataPanel;
    private JPanel rawDataPanel_colorLegendPanel;
    private JScrollPane rawDataPanel_scrollPane;
    private JPanel rawDataPanel_topPanel;
    private JTabbedPane tabbedPane;
    private JTable table;
    
    public PacketBodyDataViewer(final IRIG106Chapter10File chapter10File, final int packetIndex, final JDialog dialog) {
        this.bytesPerColumn = 25;
        this.chapter10File = chapter10File;
        this.packetIndex = packetIndex;
        this.dialog = dialog;
        this.initComponents();
        this.table.setDefaultRenderer(Object.class, new LocalTableCellRenderer());
        this.table.setDefaultEditor(Object.class, null);
        this.validate();
    }
    
    private void initComponents() {
        final JPanel packetNavigatorPanel = new JPanel();
        final JButton nextPacket = new JButton();
        final JButton nextPacketType = new JButton();
        final JButton previousPacket = new JButton();
        final JButton previousPacketType = new JButton();
        final JButton previousChannelID = new JButton();
        final JButton nextChannelID = new JButton();
        final JLabel nextLabel = new JLabel();
        final JLabel previousLabel = new JLabel();
        this.tabbedPane = new JTabbedPane();
        this.rawDataPanel = new JPanel();
        this.rawDataPanel_topPanel = new JPanel();
        this.jLabel1 = new JLabel();
        this.columnsPerRow = new JSpinner();
        this.jLabel2 = new JLabel();
        this.dataFormat = new JComboBox();
        this.rawDataPanel_colorLegendPanel = new JPanel();
        this.packetHeaderColorLegendLabel = new JLabel();
        this.dataColorLegendLabel = new JLabel();
        this.fillerColorLegendLabel = new JLabel();
        this.noDataColoLegendLabel = new JLabel();
        this.rawDataPanel_scrollPane = new JScrollPane();
        this.table = new JTable();
        this.packetInfoPanel = new JPanel();
        this.jScrollPane2 = new JScrollPane();
        this.packetInfoTextPane = new JTextPane();
        this.setLayout(new BorderLayout());
        packetNavigatorPanel.setLayout(new GridBagLayout());
        nextPacket.setText("Packet");
        nextPacket.setToolTipText("Next Packet");
        nextPacket.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.nextPacketActionPerformed(evt);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(nextPacket, gridBagConstraints);
        nextPacketType.setText("Packet Type");
        nextPacketType.setToolTipText("Next Packet Of Same Type");
        nextPacketType.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.nextPacketTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(nextPacketType, gridBagConstraints);
        previousPacket.setText("Packet");
        previousPacket.setToolTipText("Previous Packet");
        previousPacket.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.previousPacketActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(previousPacket, gridBagConstraints);
        previousPacketType.setText("Packet Type");
        previousPacketType.setToolTipText("Previous Packet Of Same Type");
        previousPacketType.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.previousPacketTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(previousPacketType, gridBagConstraints);
        previousChannelID.setText("Channel ID");
        previousChannelID.setToolTipText("Previous Packet With Same Channel ID");
        previousChannelID.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.previousChannelIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(previousChannelID, gridBagConstraints);
        nextChannelID.setText("Channel ID");
        nextChannelID.setToolTipText("Next Packet  With Same Channel ID");
        nextChannelID.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PacketBodyDataViewer.this.nextChannelIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(nextChannelID, gridBagConstraints);
        nextLabel.setText("Next:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(nextLabel, gridBagConstraints);
        previousLabel.setText("Previous:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        packetNavigatorPanel.add(previousLabel, gridBagConstraints);
        this.add(packetNavigatorPanel, "First");
        this.rawDataPanel.setLayout(new BorderLayout());
        this.rawDataPanel_topPanel.setLayout(new GridBagLayout());
        this.jLabel1.setText("Columns Per Row");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.rawDataPanel_topPanel.add(this.jLabel1, gridBagConstraints);
        this.columnsPerRow.setValue(25);
        this.columnsPerRow.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                PacketBodyDataViewer.this.columnsPerRowStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 10);
        this.rawDataPanel_topPanel.add(this.columnsPerRow, gridBagConstraints);
        this.jLabel2.setText("Data Format");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        this.rawDataPanel_topPanel.add(this.jLabel2, gridBagConstraints);
        this.dataFormat.setModel(new DefaultComboBoxModel<String>(new String[] { "Hex", "Decimal", "Binary", "ASCII" }));
        this.dataFormat.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent evt) {
                PacketBodyDataViewer.this.dataFormatMouseReleased(evt);
            }
        });
        this.dataFormat.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                PacketBodyDataViewer.this.dataFormatItemStateChanged(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.rawDataPanel_topPanel.add(this.dataFormat, gridBagConstraints);
        this.rawDataPanel_colorLegendPanel.setBorder(BorderFactory.createTitledBorder(null, "Color Legend", 2, 0));
        this.rawDataPanel_colorLegendPanel.setLayout(new GridLayout(2, 2));
        this.packetHeaderColorLegendLabel.setBackground(PacketBodyDataViewer.packetHeaderColor);
        this.packetHeaderColorLegendLabel.setHorizontalAlignment(0);
        this.packetHeaderColorLegendLabel.setText("Header");
        this.packetHeaderColorLegendLabel.setOpaque(true);
        this.rawDataPanel_colorLegendPanel.add(this.packetHeaderColorLegendLabel);
        this.dataColorLegendLabel.setBackground(PacketBodyDataViewer.dataBodyColor);
        this.dataColorLegendLabel.setHorizontalAlignment(0);
        this.dataColorLegendLabel.setText("Data");
        this.dataColorLegendLabel.setOpaque(true);
        this.rawDataPanel_colorLegendPanel.add(this.dataColorLegendLabel);
        this.fillerColorLegendLabel.setBackground(PacketBodyDataViewer.fillerColor);
        this.fillerColorLegendLabel.setHorizontalAlignment(0);
        this.fillerColorLegendLabel.setText("Filler & Checksum");
        this.fillerColorLegendLabel.setOpaque(true);
        this.rawDataPanel_colorLegendPanel.add(this.fillerColorLegendLabel);
        this.noDataColoLegendLabel.setBackground(PacketBodyDataViewer.noDataColor);
        this.noDataColoLegendLabel.setHorizontalAlignment(0);
        this.noDataColoLegendLabel.setText("No Data");
        this.noDataColoLegendLabel.setOpaque(true);
        this.rawDataPanel_colorLegendPanel.add(this.noDataColoLegendLabel);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.rawDataPanel_topPanel.add(this.rawDataPanel_colorLegendPanel, gridBagConstraints);
        this.rawDataPanel.add(this.rawDataPanel_topPanel, "North");
        this.table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.rawDataPanel_scrollPane.setViewportView(this.table);
        this.rawDataPanel.add(this.rawDataPanel_scrollPane, "Center");
        this.tabbedPane.addTab("Raw Data", this.rawDataPanel);
        this.packetInfoPanel.setLayout(new BorderLayout());
        this.jScrollPane2.setViewportView(this.packetInfoTextPane);
        this.packetInfoPanel.add(this.jScrollPane2, "Center");
        this.tabbedPane.addTab("Packet Info", this.packetInfoPanel);
        this.add(this.tabbedPane, "Center");
    }
    
    private void dataFormatItemStateChanged(final ItemEvent evt) {
        this.setData(this.data);
    }
    
    private void dataFormatMouseReleased(final MouseEvent evt) {
        this.setData(this.data);
    }
    
    private void columnsPerRowStateChanged(final ChangeEvent evt) {
        this.bytesPerColumn = (int)this.columnsPerRow.getValue();
        this.setData(this.data);
    }
    
    private void nextPacketActionPerformed(final ActionEvent evt) {
        try {
            this.setPacket(this.chapter10File.getPacket(++this.packetIndex));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void nextPacketTypeActionPerformed(final ActionEvent evt) {
        try {
            this.setPacket(this.chapter10File.getNextPacket(this.packetIndex, this.packet.getHeader().getDataType()));
            this.packetIndex = this.chapter10File.getCurrentPacketIndex();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void previousPacketActionPerformed(final ActionEvent evt) {
        try {
            final IRIG106Chapter10File chapter10File = this.chapter10File;
            final int n = this.packetIndex - 1;
            this.packetIndex = n;
            this.setPacket(chapter10File.getPacket(n));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void nextChannelIDActionPerformed(final ActionEvent evt) {
        try {
            this.setPacket(this.chapter10File.getNextPacket(this.packetIndex, this.packet.getHeader().getChannelID()));
            this.packetIndex = this.chapter10File.getCurrentPacketIndex();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void previousChannelIDActionPerformed(final ActionEvent evt) {
        try {
            this.setPacket(this.chapter10File.getPreviousPacket(this.packetIndex, this.packet.getHeader().getChannelID()));
            this.packetIndex = this.chapter10File.getCurrentPacketIndex();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void previousPacketTypeActionPerformed(final ActionEvent evt) {
        try {
            this.setPacket(this.chapter10File.getPreviousPacket(this.packetIndex, this.packet.getHeader().getDataType()));
            this.packetIndex = this.chapter10File.getCurrentPacketIndex();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setPacket(final Packet packet) throws Exception {
        this.packet = packet;
        if (this.dialog != null) {
            this.dialog.setTitle("Packet #" + this.packetIndex + ": " + packet.getHeader().getDataType() + " (Channel " + packet.getHeader().getChannelID() + ")");
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(packet.getHeader().getPacketLength());
        packet.write(baos);
        this.setData(baos.toByteArray());
        this.setPacketInfo(packet.getHeader().toString() + "\n\n" + packet.getPacketBody().toString());
        switch (packet.getHeader().getDataType()) {
            case Analog_Format1: {
                final AnalogPacket_Format1 analogPacket = (AnalogPacket_Format1)packet.getPacketBody();
                final double[] dataBuffer = new double[this.data.length];
                final boolean signed = true;
                final int subchannelId = 0;
                final int N = analogPacket.getData(dataBuffer, subchannelId, signed);
                final PlotPane1D plotPane = new PlotPane1D();
                final double[] plotData = new double[N];
                for (int i = 0; i < N; ++i) {
                    plotData[i] = dataBuffer[i];
                }
                plotPane.plot(plotData);
                final int tabIndex = this.tabbedPane.getSelectedIndex();
                if (this.tabbedPane.getTabCount() != 3) {
                    this.tabbedPane.addTab("Data View", plotPane);
                    break;
                }
                this.tabbedPane.remove(2);
                this.tabbedPane.addTab("Data View", plotPane);
                if (tabIndex == 2) {
                    this.tabbedPane.setSelectedIndex(2);
                    break;
                }
                break;
            }
            default: {
                if (this.tabbedPane.getTabCount() == 3) {
                    this.tabbedPane.remove(2);
                    break;
                }
                break;
            }
        }
    }
    
    public void setPacketInfo(final String info) {
        if (info != null) {
            this.packetInfoTextPane.setText(info);
        }
        else {
            this.packetInfoTextPane.setText("No Info");
        }
    }
    
    private void setData(final byte[] data) {
        this.data = data;
        final DefaultTableModel model = new DefaultTableModel();
        final Object[] cols = new Object[this.bytesPerColumn];
        for (int i = 0; i < cols.length; ++i) {
            cols[i] = i;
        }
        model.setColumnIdentifiers(cols);
        model.setRowCount(1 + data.length / this.bytesPerColumn);
        this.table.setModel(model);
    }
    
    static {
        dataBodyColor = new Color(192, 255, 192);
        noDataColor = new Color(127, 127, 127);
        packetHeaderColor = new Color(192, 192, 255);
        fillerColor = new Color(127, 192, 255);
    }
    
    private class LocalTableCellRenderer extends DefaultTableCellRenderer
    {
        private static final long serialVersionUID = 0L;
        
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(0);
            final int dataByte = PacketBodyDataViewer.this.bytesPerColumn * row + column;
            if (dataByte < PacketBodyDataViewer.this.data.length) {
                switch (PacketBodyDataViewer.this.dataFormat.getSelectedIndex()) {
                    case 0: {
                        String hexString;
                        for (hexString = Integer.toHexString(PacketBodyDataViewer.this.data[dataByte] & 0xFF).toUpperCase(); hexString.length() < 2; hexString = "0" + hexString) {}
                        label.setText(hexString);
                        break;
                    }
                    case 1: {
                        label.setText(Integer.toString(PacketBodyDataViewer.this.data[dataByte] & 0xFF));
                        break;
                    }
                    case 2: {
                        String binaryString;
                        for (binaryString = Integer.toBinaryString(PacketBodyDataViewer.this.data[dataByte] & 0xFF); binaryString.length() < 8; binaryString = "0" + binaryString) {}
                        label.setText(binaryString);
                        break;
                    }
                    case 3: {
                        label.setText(Character.toString((char)PacketBodyDataViewer.this.data[dataByte]));
                        break;
                    }
                }
            }
            else {
                label.setText("");
            }
            if (dataByte < 24) {
                label.setBackground(PacketBodyDataViewer.packetHeaderColor);
            }
            else if (dataByte < 24 + PacketBodyDataViewer.this.packet.getHeader().getDataLength()) {
                label.setBackground(PacketBodyDataViewer.dataBodyColor);
            }
            else if (dataByte < PacketBodyDataViewer.this.packet.getHeader().getPacketLength()) {
                label.setBackground(PacketBodyDataViewer.fillerColor);
            }
            else {
                label.setBackground(PacketBodyDataViewer.noDataColor);
            }
            return label;
        }
    }
}
