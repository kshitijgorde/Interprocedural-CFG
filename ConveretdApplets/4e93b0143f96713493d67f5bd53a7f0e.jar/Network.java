import javax.swing.table.AbstractTableModel;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.util.List;
import java.util.Collections;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Network extends JPanel
{
    private static final String[] TABLE_HEADER;
    private static final int COLUMN_DEST = 0;
    private static final int COLUMN_NEXT = 1;
    private static final int COLUMN_ACTIVE = 2;
    private static final int COLUMN_COUNT = 3;
    private JTable _tableRoutes;
    private char _charThisNetwork;
    
    public Network(final char network, Vector neighbors, final int config) {
        this._charThisNetwork = network;
        this.setLayout(null);
        this.setBackground(Main.WINDOW_COLOR);
        this.setSize(340, 465);
        this.setBorder(new LineBorder(Color.orange, 1));
        this.setLocation(10, 10);
        final JLabel label = new JLabel("Routing Table");
        label.setBounds(20, 10, 300, 50);
        label.setHorizontalAlignment(0);
        label.setHorizontalTextPosition(0);
        label.setVerticalAlignment(0);
        label.setVerticalTextPosition(0);
        label.setForeground(Color.white);
        label.setFont(new Font("Dialog", 1, 18));
        this.add(label);
        (this._tableRoutes = new JTable(new MyNetworkModel())).setSelectionMode(0);
        this._tableRoutes.getTableHeader().setReorderingAllowed(false);
        this._tableRoutes.setBackground(new Color(192, 192, 192));
        this._tableRoutes.clearSelection();
        this._tableRoutes.setRowHeight(24);
        final Vector vectorAllItems = new Vector();
        vectorAllItems.add("");
        for (int i = 0; i < NetManager.NETWORKS.length; ++i) {
            if (NetManager.NETWORKS[i] != this._charThisNetwork) {
                vectorAllItems.add("" + NetManager.NETWORKS[i]);
            }
        }
        final JComboBox comboBoxAll = new JComboBox(vectorAllItems);
        this._tableRoutes.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBoxAll));
        this._tableRoutes.getColumnModel().getColumn(0).setCellRenderer(new ComboBoxRenderer());
        if (neighbors == null) {
            neighbors = new Vector<String>();
        }
        neighbors.add(0, "");
        Collections.sort((List<Comparable>)neighbors);
        final JComboBox comboNextHop = new JComboBox((Vector<E>)neighbors);
        this._tableRoutes.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboNextHop));
        this._tableRoutes.getColumnModel().getColumn(1).setCellRenderer(new ComboBoxRenderer());
        final JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(0);
        this._tableRoutes.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(checkBox));
        this._tableRoutes.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        final JScrollPane scrollTable = new JScrollPane(this._tableRoutes);
        scrollTable.setBounds(20, 50, 300, 170);
        this.add(scrollTable);
        final JLabel labelNetwork = new JLabel("<html>Network&nbsp;&nbsp;Diagram</html>");
        labelNetwork.setBounds(220, 150, 460, 50);
        labelNetwork.setHorizontalAlignment(0);
        labelNetwork.setHorizontalTextPosition(0);
        labelNetwork.setVerticalAlignment(0);
        labelNetwork.setVerticalTextPosition(0);
        labelNetwork.setForeground(Color.white);
        labelNetwork.setFont(new Font("Dialog", 1, 18));
        this.add(labelNetwork);
        this.add(this.smallNetwork(config));
    }
    
    public char getNetworkChar() {
        return this._charThisNetwork;
    }
    
    private JPanel smallNetwork(final int config) {
        final JPanel panel = new JPanel(null);
        panel.setBounds(220, 196, 460, 274);
        panel.setBackground(Main.WINDOW_COLOR);
        panel.setOpaque(false);
        panel.setBorder(new LineBorder(Color.ORANGE, 1));
        final int xLeft = 10;
        final int xRight = 380;
        final int xMiddle = 195;
        final int yTop = 10;
        final int yBottom = 210;
        final int yMiddle = 110;
        final NetComponentSmall cloudA = new NetComponentSmall(xMiddle, yBottom, 'A', 1);
        final NetComponentSmall cloudB = new NetComponentSmall(xLeft, yMiddle, 'B', 2);
        final NetComponentSmall cloudC = new NetComponentSmall(xMiddle, yMiddle, 'C', 3);
        final NetComponentSmall cloudD = new NetComponentSmall(xRight, yMiddle, 'D', 4);
        final NetComponentSmall cloudE = new NetComponentSmall(xMiddle, yTop, 'E', 5);
        panel.add(cloudA);
        panel.add(cloudB);
        panel.add(cloudC);
        panel.add(cloudD);
        panel.add(cloudE);
        this.privSetupConnections(config, panel, cloudA, cloudB, cloudC, cloudD, cloudE);
        return panel;
    }
    
    private void privSetupConnections(final int config, final JPanel panel, final NetComponentSmall cloudA, final NetComponentSmall cloudB, final NetComponentSmall cloudC, final NetComponentSmall cloudD, final NetComponentSmall cloudE) {
        final int length = NetManager.CONFIGURATIONS[config].length;
        LineComponent line = null;
        for (int i = 0; i < length; ++i) {
            final char cLeft = NetManager.CONFIGURATIONS[config][i].charAt(0);
            final char cRight = NetManager.CONFIGURATIONS[config][i].charAt(1);
            if (Main.DEBUG) {
                System.out.println("Left = " + cLeft + ", Right = " + cRight);
            }
            Point ptLeft = null;
            Point ptRight = null;
            Label_0564: {
                switch (cLeft) {
                    case 'A': {
                        switch (cRight) {
                            case 'B': {
                                ptLeft = cloudB.getSouthEast();
                                ptRight = cloudA.getNorthWest();
                                break;
                            }
                            case 'C': {
                                ptLeft = cloudA.getNorth();
                                ptRight = cloudC.getSouth();
                                break;
                            }
                            case 'D': {
                                ptLeft = cloudA.getNorthEast();
                                ptRight = cloudD.getSouthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'B': {
                        switch (cRight) {
                            case 'A': {
                                ptLeft = cloudB.getSouthEast();
                                ptRight = cloudA.getNorthWest();
                                break;
                            }
                            case 'C': {
                                ptLeft = cloudB.getEast();
                                ptRight = cloudC.getWest();
                                break;
                            }
                            case 'E': {
                                ptLeft = cloudB.getNorthEast();
                                ptRight = cloudE.getSouthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'C': {
                        switch (cRight) {
                            case 'A': {
                                ptLeft = cloudC.getSouth();
                                ptRight = cloudA.getNorth();
                                break;
                            }
                            case 'B': {
                                ptLeft = cloudB.getEast();
                                ptRight = cloudC.getWest();
                                break;
                            }
                            case 'D': {
                                ptLeft = cloudC.getEast();
                                ptRight = cloudD.getWest();
                                break;
                            }
                            case 'E': {
                                ptLeft = cloudC.getNorth();
                                ptRight = cloudE.getSouth();
                                break;
                            }
                        }
                        break;
                    }
                    case 'D': {
                        switch (cRight) {
                            case 'A': {
                                ptLeft = cloudD.getSouthWest();
                                ptRight = cloudA.getNorthEast();
                                break;
                            }
                            case 'C': {
                                ptLeft = cloudD.getWest();
                                ptRight = cloudC.getEast();
                                break;
                            }
                            case 'E': {
                                ptLeft = cloudE.getSouthEast();
                                ptRight = cloudD.getNorthWest();
                                break;
                            }
                        }
                        break;
                    }
                    case 'E': {
                        switch (cRight) {
                            case 'B': {
                                ptLeft = cloudB.getNorthEast();
                                ptRight = cloudE.getSouthWest();
                                break Label_0564;
                            }
                            case 'C': {
                                ptLeft = cloudE.getSouth();
                                ptRight = cloudC.getNorth();
                                break Label_0564;
                            }
                            case 'D': {
                                ptLeft = cloudE.getSouthEast();
                                ptRight = cloudD.getNorthWest();
                                break Label_0564;
                            }
                        }
                        break;
                    }
                }
            }
            line = new LineComponent(ptLeft, ptRight, new JLabel());
            panel.add(line);
        }
    }
    
    public char forwardPacket(final String packet) {
        if (packet.length() < 3 || (!packet.matches("[A-Z][.][0-9]") && !packet.matches("[0-9][.][A-Z]"))) {
            return NetManager.LOST_PACKET;
        }
        final char left = packet.charAt(0);
        final char right = packet.charAt(2);
        if (left == this._charThisNetwork) {
            return right;
        }
        if (right == this._charThisNetwork) {
            return left;
        }
        for (int rows = this._tableRoutes.getRowCount(), i = 1; i < rows; ++i) {
            final String strValue = this._tableRoutes.getValueAt(i, 0).toString();
            if (strValue.length() != 0) {
                final char charValue = strValue.charAt(0);
                if (charValue == left || strValue.equalsIgnoreCase("default")) {
                    final String strActive = this._tableRoutes.getValueAt(i, 2).toString();
                    if (!strActive.equalsIgnoreCase("false")) {
                        final String strNextHop = this._tableRoutes.getValueAt(i, 1).toString();
                        if (strNextHop.length() != 0) {
                            final char charNextHop = strNextHop.charAt(0);
                            if (charNextHop != ' ') {
                                return charNextHop;
                            }
                        }
                    }
                }
            }
        }
        return NetManager.LOST_PACKET;
    }
    
    public String toString() {
        return "" + this._charThisNetwork;
    }
    
    static {
        TABLE_HEADER = new String[] { "Destination", "Next Hop", "Active" };
    }
    
    public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer
    {
        public CheckBoxRenderer() {
            this.setHorizontalAlignment(0);
            this.setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object object, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            this.setSelected(object.toString().equalsIgnoreCase("true"));
            return this;
        }
    }
    
    public class ComboBoxRenderer extends JLabel implements TableCellRenderer
    {
        public ComboBoxRenderer() {
            this.setHorizontalAlignment(0);
            this.setHorizontalTextPosition(0);
            this.setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object object, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            this.setText((object == null) ? "" : object.toString());
            return this;
        }
    }
    
    public class MyNetworkModel extends AbstractTableModel
    {
        private Object[][] _objectData;
        private int _numElements;
        private int _lastElement;
        
        public MyNetworkModel() {
            this._numElements = NetManager.NETWORKS.length + 1;
            this._lastElement = this._numElements - 1;
            (this._objectData = new Object[this._numElements][])[0] = new Object[3];
            this._objectData[0][0] = new String("" + Network.this._charThisNetwork);
            this._objectData[0][1] = new String(".");
            this._objectData[0][2] = new Boolean(true);
            for (int i = 1; i < this._numElements; ++i) {
                (this._objectData[i] = new Object[3])[0] = new String("");
                this._objectData[i][1] = new String("");
                this._objectData[i][2] = new Boolean(false);
            }
            this._objectData[this._lastElement][0] = new String("default");
        }
        
        public int getRowCount() {
            return this._objectData.length;
        }
        
        public int getColumnCount() {
            return Network.TABLE_HEADER.length;
        }
        
        public String getColumnName(final int col) {
            return Network.TABLE_HEADER[col];
        }
        
        public Object getValueAt(final int row, final int col) {
            return this._objectData[row][col];
        }
        
        public Class getColumnClass(final int col) {
            return this._objectData[0][col].getClass();
        }
        
        public boolean isCellEditable(final int row, final int col) {
            return (row != this._lastElement || col != 0) && row != 0;
        }
        
        public void setValueAt(final Object value, final int row, final int col) {
            if (value.toString().length() == 0) {
                this._objectData[row][2] = new Boolean(false);
                this.fireTableCellUpdated(row, 2);
            }
            else if (col == 0) {
                this._objectData[row][2] = new Boolean(this._objectData[row][1].toString().length() > 0);
                this.fireTableCellUpdated(row, 2);
            }
            else if (col == 1) {
                this._objectData[row][2] = new Boolean(this._objectData[row][0].toString().length() > 0);
                this.fireTableCellUpdated(row, 2);
            }
            this._objectData[row][col] = value;
            this.fireTableCellUpdated(row, col);
            if (Main.DEBUG) {
                System.out.println(this.toString());
            }
        }
        
        public String toString() {
            final StringBuffer results = new StringBuffer();
            final int numRows = this.getRowCount();
            final int numCols = this.getColumnCount();
            results.append("Routing Table: \n");
            for (int i = 0; i < numRows; ++i) {
                results.append("    row " + i + ":");
                for (int j = 0; j < numCols; ++j) {
                    results.append("  " + this._objectData[i][j]);
                }
                results.append('\n');
            }
            results.append("--------------------------\n");
            return results.toString();
        }
    }
}
