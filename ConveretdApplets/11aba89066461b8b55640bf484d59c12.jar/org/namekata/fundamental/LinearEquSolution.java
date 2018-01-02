// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.table.AbstractTableModel;
import javax.swing.JTextArea;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.awt.Frame;
import java.awt.FileDialog;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JFrame;

public class LinearEquSolution extends JFrame
{
    private FracMatrix fMatrix;
    private JMenuBar jMenuBar1;
    private JMenu jMenuFile;
    private JMenuItem jMenuItemSaveAs;
    private JMenuItem jMenuItemExit;
    private STableModel sTableModel;
    private FracCellRenderer fCellRenderer;
    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JLabel statusBar;
    private JMenu jMenuEdit;
    private JMenuItem jMenuItemEditCopy;
    private JCheckBoxMenuItem jCheckBoxMenuItemDecimal;
    private JCheckBoxMenuItem jCheckBoxMenuItemIntFrac;
    private JCheckBoxMenuItem jCheckBoxMenuItemFrac;
    private JMenu jMenuShow;
    private boolean isInApplet;
    
    public LinearEquSolution(final FracMatrix m) {
        this(m, false);
    }
    
    public LinearEquSolution(final FracMatrix m, final boolean isInApplet) {
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuItemSaveAs = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jTable1 = new JTable();
        this.jScrollPane1 = new JScrollPane();
        this.statusBar = new JLabel();
        this.jMenuEdit = new JMenu();
        this.jMenuItemEditCopy = new JMenuItem();
        this.jCheckBoxMenuItemDecimal = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemIntFrac = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemFrac = new JCheckBoxMenuItem();
        this.jMenuShow = new JMenu();
        this.isInApplet = false;
        this.isInApplet = isInApplet;
        this.fMatrix = m;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.jMenuFile.setMnemonic('F');
        this.jMenuFile.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("File"));
        this.jMenuItemExit.setMnemonic('X');
        this.jMenuItemExit.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Close"));
        this.jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearEquSolution.this.jMenuItemExit_actionPerformed(e);
            }
        });
        this.jTable1.setAutoResizeMode(0);
        this.jTable1.setGridColor(Color.lightGray);
        this.jTable1.setRowSelectionAllowed(false);
        this.statusBar.setText("");
        this.jMenuEdit.setMnemonic('E');
        this.jMenuEdit.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Edit"));
        this.jMenuItemEditCopy.setMnemonic('C');
        this.jMenuItemEditCopy.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Copy"));
        this.jMenuItemEditCopy.setAccelerator(KeyStroke.getKeyStroke(67, 2, false));
        this.jMenuItemEditCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearEquSolution.this.jMenuItemEditCopy_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemDecimal.setMnemonic('D');
        this.jCheckBoxMenuItemDecimal.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Decimal"));
        this.jCheckBoxMenuItemDecimal.setState(false);
        this.jCheckBoxMenuItemDecimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearEquSolution.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemIntFrac.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("MixedFraction"));
        this.jCheckBoxMenuItemIntFrac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearEquSolution.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemFrac.setMnemonic('F');
        this.jCheckBoxMenuItemFrac.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Fraction"));
        this.jCheckBoxMenuItemFrac.setState(true);
        this.jCheckBoxMenuItemFrac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearEquSolution.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jMenuShow.setMnemonic('S');
        this.jMenuShow.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Show"));
        this.jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setDefaultCloseOperation(2);
        this.jMenuBar1.add(this.jMenuFile);
        this.jMenuBar1.add(this.jMenuEdit);
        this.jMenuBar1.add(this.jMenuShow);
        if (!this.isInApplet) {
            this.jMenuItemSaveAs.setMnemonic('A');
            this.jMenuItemSaveAs.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("SaveAs"));
            this.jMenuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(65, 2, false));
            this.jMenuItemSaveAs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    LinearEquSolution.this.jMenuItemSaveAs_actionPerformed(e);
                }
            });
            this.jMenuFile.add(this.jMenuItemSaveAs);
            this.jMenuFile.addSeparator();
        }
        this.jMenuFile.add(this.jMenuItemExit);
        this.setJMenuBar(this.jMenuBar1);
        this.getContentPane().add(this.jScrollPane1, "Center");
        this.getContentPane().add(this.statusBar, "South");
        this.jScrollPane1.getViewport().add(this.jTable1, null);
        this.jMenuEdit.add(this.jMenuItemEditCopy);
        this.jMenuShow.add(this.jCheckBoxMenuItemFrac);
        this.jMenuShow.add(this.jCheckBoxMenuItemDecimal);
        this.jMenuShow.add(this.jCheckBoxMenuItemIntFrac);
        this.setData(this.fMatrix);
    }
    
    private void setData(final FracMatrix m) {
        this.sTableModel = new STableModel(m);
        this.jTable1.setModel(this.sTableModel);
        final CenteredRenderer cCellRenderer = new CenteredRenderer((CenteredRenderer)null);
        this.jTable1.setDefaultRenderer(String.class, cCellRenderer);
        this.fCellRenderer = new FracCellRenderer();
        this.jTable1.setDefaultRenderer(Frac.class, this.fCellRenderer);
        TableColumn column = this.jTable1.getColumnModel().getColumn(0);
        Component comp = this.jTable1.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
        int headerWidth = comp.getPreferredSize().width;
        column.setPreferredWidth(headerWidth);
        for (int j = 1; j < this.jTable1.getColumnCount(); j += 2) {
            column = this.jTable1.getColumnModel().getColumn(j);
            comp = this.jTable1.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(headerWidth);
        }
        this.jTable1.updateUI();
    }
    
    void jMenuItemSaveAs_actionPerformed(final ActionEvent e) {
        final FileDialog fileDialog = new FileDialog(this, ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Save File"), 1);
        fileDialog.setVisible(true);
        final String dirpart = fileDialog.getDirectory();
        final String filepart = fileDialog.getFile();
        if (dirpart == null || filepart == null) {
            return;
        }
        final String fileName = String.valueOf(dirpart) + filepart;
        final File file = new File(fileName);
        try {
            final FileWriter out = new FileWriter(file);
            final String text = this.tableToString();
            out.write(text);
            out.close();
            this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("ToFile")) + fileName + ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("saved"));
        }
        catch (IOException ex) {
            this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("ToFile")) + fileName + ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("CannotSaved"));
        }
    }
    
    void jMenuItemExit_actionPerformed(final ActionEvent e) {
        this.dispose();
    }
    
    private String tableToString() {
        String text = String.valueOf(this.getTitle()) + "\n";
        final int jj = this.sTableModel.getColumnCount();
        for (int j = 0; j < jj; ++j) {
            if (j == jj - 1) {
                text = String.valueOf(text) + this.sTableModel.getColumnName(j) + "\n";
            }
            else {
                text = String.valueOf(text) + this.sTableModel.getColumnName(j) + "\t";
            }
        }
        for (int i = 0; i < this.sTableModel.getRowCount(); ++i) {
            for (int k = 0; k < jj; ++k) {
                if (k == jj - 1) {
                    text = String.valueOf(text) + this.sTableModel.getValueAt(i, k).toString() + "\n";
                }
                else {
                    text = String.valueOf(text) + this.sTableModel.getValueAt(i, k).toString() + "\t";
                }
            }
        }
        text = String.valueOf(text) + "\n";
        return text;
    }
    
    void jMenuItemEditCopy_actionPerformed(final ActionEvent e) {
        final JTextArea jTextArea1 = new JTextArea();
        final String str = this.tableToString();
        jTextArea1.setText(str);
        jTextArea1.selectAll();
        jTextArea1.copy();
    }
    
    void jCheckBoxMenuItem_actionPerformed(final ActionEvent e) {
        final Object obj = e.getSource();
        ((JCheckBoxMenuItem)obj).setState(true);
        if (obj == this.jCheckBoxMenuItemDecimal) {
            this.jCheckBoxMenuItemFrac.setState(false);
            this.jCheckBoxMenuItemIntFrac.setState(false);
            this.fCellRenderer.setFormat(1);
        }
        else if (obj == this.jCheckBoxMenuItemFrac) {
            this.jCheckBoxMenuItemDecimal.setState(false);
            this.jCheckBoxMenuItemIntFrac.setState(false);
            this.fCellRenderer.setFormat(0);
        }
        else if (obj == this.jCheckBoxMenuItemIntFrac) {
            this.jCheckBoxMenuItemDecimal.setState(false);
            this.jCheckBoxMenuItemFrac.setState(false);
            this.fCellRenderer.setFormat(2);
        }
        this.jTable1.updateUI();
    }
    
    private class CenteredRenderer extends JLabel implements TableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            this.setText(value.toString());
            this.setHorizontalAlignment(0);
            this.setOpaque(true);
            if (column == 0) {
                this.setBackground(Color.lightGray);
            }
            else {
                this.setBackground(table.getBackground());
            }
            return this;
        }
    }
    
    private class STableModel extends AbstractTableModel
    {
        private final int maxrows = 20;
        private final int maxcols = 21;
        private String[] columnNames;
        private Object[][] data;
        private int rows;
        private int columns;
        
        public STableModel(final FracMatrix m) {
            this.columnNames = new String[21];
            this.data = new Object[20][21];
            if (m == null) {
                this.rows = 20;
                this.columns = 21;
                for (int i = 0; i < 20; ++i) {
                    this.data[i][0] = "x" + (i + 1);
                }
                this.columnNames[0] = ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Var");
                for (int i = 0; i < 20; ++i) {
                    for (int j = 1; j < 21; ++j) {
                        if (i == 0) {
                            this.columnNames[j] = "";
                        }
                        this.data[i][j] = "";
                    }
                }
                this.columnNames[1] = " = ";
                this.columnNames[2] = "1";
                return;
            }
            final int frows = m.getRowCount();
            final Frac[][] fData = m.getData();
            final int total = m.getColumnCount() - 1;
            final int[] BasisPos = new int[total];
            final Frac[][] NBasisCoef = new Frac[frows][total];
            int Bcount = 0;
            for (int k = 0; k < total; ++k) {
                int ret = this.basispos(m, k);
                for (int l = 0; l < k; ++l) {
                    if (BasisPos[l] == ret) {
                        ret = -1;
                        break;
                    }
                }
                if ((BasisPos[k] = ret) >= 0) {
                    ++Bcount;
                }
                else {
                    for (int i2 = 0; i2 < frows; ++i2) {
                        NBasisCoef[i2][k] = Frac.Zero.sub(fData[i2][k]);
                    }
                }
            }
            final int NBcount = total - Bcount;
            this.columns = NBcount * 2 + 3;
            this.rows = total;
            this.columnNames = new String[this.columns];
            this.data = new Object[this.rows][this.columns];
            this.columnNames[0] = ResourceBundle.getBundle("org.namekata.fundamental/LinearEquSolution").getString("Var");
            this.columnNames[1] = " = ";
            this.columnNames[2] = "1";
            int k2 = 3;
            for (int j2 = 0; j2 < NBcount; ++j2) {
                this.columnNames[k2++] = " + ";
                this.columnNames[k2++] = "a" + (j2 + 1);
            }
            for (int i2 = 0; i2 < this.rows; ++i2) {
                this.data[i2][0] = "x" + (i2 + 1);
            }
            for (int i2 = 0; i2 < this.rows; ++i2) {
                for (int j3 = 1; j3 < this.columns; ++j3) {
                    this.data[i2][j3] = "";
                }
            }
            for (int i2 = 0; i2 < this.rows; ++i2) {
                this.data[i2][2] = ((BasisPos[i2] == -1) ? Frac.Zero : fData[BasisPos[i2]][total]);
            }
            k2 = 4;
            for (int i2 = 0; i2 < total; ++i2) {
                if (BasisPos[i2] == -1) {
                    for (int ii = 0; ii < this.rows; ++ii) {
                        final int iii = BasisPos[ii];
                        if (ii == i2) {
                            this.data[ii][k2] = "1";
                        }
                        else if (iii != -1) {
                            this.data[ii][k2] = NBasisCoef[iii][i2];
                        }
                        else {
                            this.data[ii][k2] = Frac.Zero;
                        }
                    }
                    k2 += 2;
                }
            }
        }
        
        private int basispos(final FracMatrix m, final int j) {
            int pos = 0;
            int num = 0;
            final Frac[][] dd = m.getData();
            for (int k = 0; k < m.getRowCount(); ++k) {
                if (dd[k][j].equals(1L)) {
                    ++num;
                    pos = k;
                }
            }
            if (num == 1) {
                return pos;
            }
            return -1;
        }
        
        @Override
        public String getColumnName(final int col) {
            return this.columnNames[col].toString();
        }
        
        @Override
        public int getColumnCount() {
            return this.columns;
        }
        
        @Override
        public Object getValueAt(final int parm1, final int parm2) {
            return this.data[parm1][parm2];
        }
        
        @Override
        public boolean isCellEditable(final int row, final int col) {
            return false;
        }
        
        @Override
        public int getRowCount() {
            return this.rows;
        }
        
        @Override
        public Class getColumnClass(final int c) {
            return this.getValueAt(0, c).getClass();
        }
    }
}
