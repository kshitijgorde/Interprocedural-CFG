// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class LPInput extends JPanel
{
    private static final int MaxVar = 30;
    private static final int MaxEq = 50;
    private static final Integer MaxNumOfVar;
    private static final Integer MaxNumOfEq;
    private static final int DefaultNumOfVar = 4;
    private static final int DefaultNumOfEq = 3;
    private int rows;
    private int columns;
    private String[] columnNames;
    private Object[][] data;
    private Object[] datarow;
    private Object[] datacol;
    private String[] comboStr;
    private MyTableModel myTableModel;
    private FracCellRenderer fracCellRenderer;
    private CheckBoxCellRenderer checkBoxCellRenderer;
    private ComboBoxCellRenderer comboBoxCellRenderer;
    private FracCellEditor fracCellEditor;
    private JScrollPane jScrollPane1;
    private BorderLayout borderLayout1;
    private JLabel statusBar;
    private MyTable jTable1;
    private JPanel jPanel1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton jButtonRev;
    private MyComboBox myComboBoxVar;
    private MyComboBox myComboBoxEq;
    private int format;
    
    static {
        MaxNumOfVar = new Integer(30);
        MaxNumOfEq = new Integer(50);
    }
    
    public LPInput() {
        this.rows = 5;
        this.columns = 6;
        this.columnNames = new String[] { "x1", "x2", "x3", "x4", "", "1" };
        this.data = new Object[LPInput.MaxNumOfEq + 2][LPInput.MaxNumOfVar + 2];
        this.comboStr = new String[] { "<=", "=", ">=" };
        this.myTableModel = new MyTableModel();
        this.fracCellRenderer = new FracCellRenderer();
        this.fracCellEditor = new FracCellEditor();
        this.jScrollPane1 = new JScrollPane();
        this.borderLayout1 = new BorderLayout();
        this.statusBar = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jButtonRev = new JButton();
        this.myComboBoxVar = new MyComboBox(1, 30);
        this.myComboBoxEq = new MyComboBox(1, 50);
        this.format = 0;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.jLabel1.setText(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("NumOfVar"));
        this.jLabel2.setText(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("NumOfEq"));
        this.jLabel3.setText(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("to"));
        this.jButtonRev.setText(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("change"));
        this.jButtonRev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LPInput.this.jButtonRev_actionPerformed(e);
            }
        });
        this.myComboBoxVar.setSelectedIndex(3);
        this.myComboBoxEq.setSelectedIndex(2);
        this.add(this.jScrollPane1, "Center");
        this.checkBoxCellRenderer = new CheckBoxCellRenderer(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("nonnegative"), true);
        this.comboBoxCellRenderer = new ComboBoxCellRenderer(this.comboStr);
        (this.jTable1 = new MyTable() {
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int col) {
                int type = 0;
                if (row == 0) {
                    if (col >= LPInput.this.columns - 2) {
                        type = 3;
                    }
                    else {
                        type = 1;
                    }
                }
                else if (row == LPInput.this.rows - 1) {
                    if (col >= LPInput.this.columns - 2) {
                        type = 3;
                    }
                    else {
                        type = 0;
                    }
                }
                else if (col != LPInput.this.columns - 2) {
                    type = 0;
                }
                else {
                    type = 2;
                }
                switch (type) {
                    case 0: {
                        return LPInput.this.fracCellRenderer;
                    }
                    case 1: {
                        return LPInput.this.checkBoxCellRenderer;
                    }
                    case 2: {
                        return LPInput.this.comboBoxCellRenderer;
                    }
                    default: {
                        return super.getCellRenderer(row, col);
                    }
                }
            }
            
            @Override
            public TableCellEditor getCellEditor(final int row, final int col) {
                int type = 0;
                if (row == 0) {
                    if (col >= LPInput.this.columns - 2) {
                        type = 3;
                    }
                    else {
                        type = 1;
                    }
                }
                else if (col != LPInput.this.columns - 2) {
                    type = 0;
                }
                else {
                    type = 2;
                }
                switch (type) {
                    case 0: {
                        return LPInput.this.fracCellEditor;
                    }
                    case 1: {
                        return new DefaultCellEditor(new JCheckBox(ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("nonnegative")));
                    }
                    case 2: {
                        return new DefaultCellEditor(new JComboBox((E[])LPInput.this.comboStr));
                    }
                    default: {
                        return super.getCellEditor(row, col);
                    }
                }
            }
        }).setAutoResizeMode(0);
        this.jTable1.setColumnSelectionAllowed(false);
        this.jTable1.setRowSelectionAllowed(false);
        this.jTable1.setCellSelectionEnabled(true);
        this.jTable1.setModel(this.myTableModel);
        this.jScrollPane1.getViewport().add(this.jTable1, null);
        this.add(this.statusBar, "South");
        this.add(this.jPanel1, "North");
        this.jPanel1.add(this.jLabel1, null);
        this.jPanel1.add(this.myComboBoxVar, null);
        this.jPanel1.add(this.jLabel2, null);
        this.jPanel1.add(this.myComboBoxEq, null);
        this.jPanel1.add(this.jLabel3, null);
        this.jPanel1.add(this.jButtonRev, null);
    }
    
    public Object[][] getData() {
        return this.data;
    }
    
    public int getNumOfVariable() {
        return this.columns - 2;
    }
    
    public boolean[] getVariableType() {
        final int jj = this.columns - 2;
        final boolean[] ret = new boolean[jj];
        for (int j = 0; j < jj; ++j) {
            if (this.datarow[j].equals(new Boolean(true))) {
                ret[j] = true;
            }
            else {
                ret[j] = false;
            }
        }
        return ret;
    }
    
    public int getNumOfEq() {
        return this.rows - 2;
    }
    
    public int[] getLessEqGreater() {
        final int ii = this.rows - 2;
        final int[] ret = new int[ii];
        for (int i = 0; i < ii; ++i) {
            final String str = (String)this.datacol[i];
            if (str == "<=") {
                ret[i] = 0;
            }
            else if (str == "=") {
                ret[i] = 1;
            }
            else {
                ret[i] = 2;
            }
        }
        return ret;
    }
    
    public boolean isDirty() {
        return this.jTable1.isDirty();
    }
    
    public void setDirty(final boolean b) {
        this.jTable1.setDirty(b);
    }
    
    @Override
    public String toString() {
        String ret = "";
        final int ii = this.myTableModel.getRowCount();
        final int jj = this.myTableModel.getColumnCount();
        for (int j = 0; j < jj; ++j) {
            ret = String.valueOf(ret) + this.myTableModel.getColumnName(j) + ((j == jj - 1) ? "\n" : "\t");
        }
        for (int i = 0; i < ii; ++i) {
            for (int k = 0; k < jj; ++k) {
                ret = String.valueOf(ret) + this.myTableModel.getValueAt(i, k).toString() + ((k == jj - 1) ? "\n" : "\t");
            }
        }
        ret = String.valueOf(ret) + "\n";
        return ret;
    }
    
    public void updateUI(final int format) {
        if (this.format == format) {
            return;
        }
        this.format = format;
        this.fracCellRenderer.setFormat(format);
        this.jTable1.updateUI();
    }
    
    public void refreshData() {
        this.myTableModel.refreshData();
        this.myTableModel.fireTableDataChanged();
    }
    
    void jButtonRev_actionPerformed(final ActionEvent e) {
        final int ii = this.myComboBoxEq.getSelectedIndex() + 1;
        final int jj = this.myComboBoxVar.getSelectedIndex() + 1;
        if (ii != this.rows - 2) {
            this.myTableModel.setRowCount(ii + 2);
        }
        if (jj != this.columns - 2) {
            this.myTableModel.setColumnCount(jj + 2);
        }
        this.data[ii][jj] = Frac.Zero;
    }
    
    static /* synthetic */ void access$2(final LPInput lpInput, final Object[] datarow) {
        lpInput.datarow = datarow;
    }
    
    static /* synthetic */ void access$3(final LPInput lpInput, final Object[] datacol) {
        lpInput.datacol = datacol;
    }
    
    static /* synthetic */ void access$7(final LPInput lpInput, final int rows) {
        lpInput.rows = rows;
    }
    
    static /* synthetic */ void access$8(final LPInput lpInput, final String[] columnNames) {
        lpInput.columnNames = columnNames;
    }
    
    static /* synthetic */ void access$10(final LPInput lpInput, final int columns) {
        lpInput.columns = columns;
    }
    
    private class MyTableModel extends AbstractTableModel
    {
        public MyTableModel() {
            this.init(LPInput.this.rows, LPInput.this.columns);
        }
        
        private void init(final int rows, final int columns) {
            LPInput.access$2(LPInput.this, new Object[columns - 1]);
            LPInput.access$3(LPInput.this, new Object[rows - 1]);
            for (int j = 0; j < columns - 1; ++j) {
                LPInput.this.datarow[j] = new Boolean(true);
            }
            for (int i = 0; i < rows - 1; ++i) {
                LPInput.this.datacol[i] = "<=";
            }
            this.refreshData();
        }
        
        void refreshData() {
            for (int i = 0; i < 51; ++i) {
                for (int j = 0; j < 31; ++j) {
                    LPInput.this.data[i][j] = Frac.Zero;
                }
            }
        }
        
        @Override
        public int getRowCount() {
            return LPInput.this.rows;
        }
        
        public void setRowCount(final int i) {
            final int ii = Math.min(i, 52);
            if (LPInput.this.rows < ii) {
                LPInput.access$3(LPInput.this, new Object[ii - 1]);
                for (int ij = 0; ij < ii - 1; ++ij) {
                    LPInput.this.datacol[ij] = "<=";
                }
            }
            LPInput.access$7(LPInput.this, ii);
            this.fireTableStructureChanged();
        }
        
        @Override
        public int getColumnCount() {
            return LPInput.this.columns;
        }
        
        public void setColumnCount(final int j) {
            final int jj = Math.min(j, 32);
            LPInput.access$8(LPInput.this, new String[jj]);
            for (int k = 0; k < jj - 2; ++k) {
                LPInput.this.columnNames[k] = "x" + (k + 1);
            }
            LPInput.this.columnNames[jj - 2] = "";
            LPInput.this.columnNames[jj - 1] = "1";
            if (LPInput.this.columns < jj) {
                LPInput.access$2(LPInput.this, new Object[jj - 1]);
                for (int k = 0; k < jj - 1; ++k) {
                    LPInput.this.datarow[k] = new Boolean(true);
                }
            }
            LPInput.access$10(LPInput.this, jj);
            this.fireTableStructureChanged();
        }
        
        @Override
        public String getColumnName(final int col) {
            return LPInput.this.columnNames[col];
        }
        
        @Override
        public boolean isCellEditable(final int row, final int col) {
            return (row != 0 || col < LPInput.this.columns - 2) && (row != LPInput.this.rows - 1 || col < LPInput.this.columns - 2);
        }
        
        @Override
        public Object getValueAt(final int row, final int col) {
            if (row == 0) {
                if (col >= LPInput.this.columns - 2) {
                    return "";
                }
                return LPInput.this.datarow[col];
            }
            else if (col == LPInput.this.columns - 2) {
                if (row == LPInput.this.rows - 1) {
                    return " <-- ";
                }
                return LPInput.this.datacol[row - 1];
            }
            else {
                if (col <= LPInput.this.columns - 2) {
                    return LPInput.this.data[row - 1][col];
                }
                if (row == LPInput.this.rows - 1) {
                    return ResourceBundle.getBundle("org.namekata.fundamental/LPInput").getString("ObjectiveFunction");
                }
                return LPInput.this.data[row - 1][col - 1];
            }
        }
        
        @Override
        public void setValueAt(final Object value, final int row, final int col) {
            if (row == 0) {
                LPInput.this.datarow[col] = value;
            }
            else if (col == LPInput.this.columns - 2) {
                LPInput.this.datacol[row - 1] = value;
            }
            else if (col < LPInput.this.columns - 2) {
                LPInput.this.data[row - 1][col] = value;
            }
            else if (col > LPInput.this.columns - 2) {
                LPInput.this.data[row - 1][col - 1] = value;
            }
            this.fireTableCellUpdated(row, col);
        }
    }
}
