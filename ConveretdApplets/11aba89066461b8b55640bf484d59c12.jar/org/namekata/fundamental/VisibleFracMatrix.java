// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.table.AbstractTableModel;
import java.awt.Point;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JFrame;

public class VisibleFracMatrix extends JFrame
{
    public static final int rowmax = 20;
    public static final int columnmax = 20;
    private FracMatrix fMatrix;
    private String[] columnNames;
    private String[] rowNames;
    private int rows;
    private int columns;
    private VisibleFracMatrixTableModel myTableModel;
    private JToolBar jToolBar1;
    private JScrollPane jScrollPane1;
    private MyTable jTable1;
    private JLabel jLabel1;
    private MyComboBox myComboBoxRows;
    private JButton jButtonModify;
    private JLabel jLabel2;
    private MyComboBox myComboBoxColumns;
    private TitledBorder titledBorder1;
    private JMenuBar jMenuBar1;
    private JMenu jMenuFile;
    private JMenuItem jMenuItemSaveAs;
    private JMenuItem jMenuItemExit;
    private JMenu jMenuEdit;
    private JMenuItem jMenuItemCopy;
    private TitledBorder titledBorder2;
    private JLabel statusBar;
    private JMenu jMenuShow;
    private JCheckBoxMenuItem jCheckBoxMenuItemFrac;
    private JCheckBoxMenuItem jCheckBoxMenuItemDecimal;
    private FracCellRenderer fCellRenderer;
    private JCheckBoxMenuItem jCheckBoxMenuItemIntFrac;
    private int pos;
    private JMenuItem jMenuItemSolution;
    private LinearEquSolution linearEquSolution;
    private boolean isInApplet;
    private JCheckBoxMenuItem jCheckBoxMenuItemCellWidth;
    private int selectedRow;
    private int selectedColumn;
    
    public VisibleFracMatrix(final int row, final int col, final boolean isInApplet) {
        this.fMatrix = null;
        this.columnNames = new String[21];
        this.rowNames = new String[21];
        this.jToolBar1 = new JToolBar();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new MyTable();
        this.jLabel1 = new JLabel();
        this.myComboBoxRows = new MyComboBox(1, 20);
        this.jButtonModify = new JButton();
        this.jLabel2 = new JLabel();
        this.myComboBoxColumns = new MyComboBox(1, 20);
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuItemSaveAs = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jMenuEdit = new JMenu();
        this.jMenuItemCopy = new JMenuItem();
        this.statusBar = new JLabel();
        this.jMenuShow = new JMenu();
        this.jCheckBoxMenuItemFrac = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemDecimal = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemIntFrac = new JCheckBoxMenuItem();
        this.pos = 0;
        this.jMenuItemSolution = new JMenuItem();
        this.linearEquSolution = null;
        this.isInApplet = false;
        this.jCheckBoxMenuItemCellWidth = new JCheckBoxMenuItem();
        this.selectedRow = -1;
        this.selectedColumn = -1;
        this.init(row, col, isInApplet);
    }
    
    public VisibleFracMatrix(final int row, final int col) {
        this(row, col, false);
    }
    
    public VisibleFracMatrix(final FracMatrix x, final boolean isInApplet) {
        this.fMatrix = null;
        this.columnNames = new String[21];
        this.rowNames = new String[21];
        this.jToolBar1 = new JToolBar();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new MyTable();
        this.jLabel1 = new JLabel();
        this.myComboBoxRows = new MyComboBox(1, 20);
        this.jButtonModify = new JButton();
        this.jLabel2 = new JLabel();
        this.myComboBoxColumns = new MyComboBox(1, 20);
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuItemSaveAs = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jMenuEdit = new JMenu();
        this.jMenuItemCopy = new JMenuItem();
        this.statusBar = new JLabel();
        this.jMenuShow = new JMenu();
        this.jCheckBoxMenuItemFrac = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemDecimal = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemIntFrac = new JCheckBoxMenuItem();
        this.pos = 0;
        this.jMenuItemSolution = new JMenuItem();
        this.linearEquSolution = null;
        this.isInApplet = false;
        this.jCheckBoxMenuItemCellWidth = new JCheckBoxMenuItem();
        this.selectedRow = -1;
        this.selectedColumn = -1;
        this.fMatrix = new FracMatrix(x);
        this.init(x.getRowCount(), x.getColumnCount(), isInApplet);
    }
    
    public VisibleFracMatrix(final FracMatrix x) {
        this(x, false);
    }
    
    public VisibleFracMatrix(final VisibleFracMatrix x, final boolean isInApplet) {
        this.fMatrix = null;
        this.columnNames = new String[21];
        this.rowNames = new String[21];
        this.jToolBar1 = new JToolBar();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new MyTable();
        this.jLabel1 = new JLabel();
        this.myComboBoxRows = new MyComboBox(1, 20);
        this.jButtonModify = new JButton();
        this.jLabel2 = new JLabel();
        this.myComboBoxColumns = new MyComboBox(1, 20);
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuItemSaveAs = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jMenuEdit = new JMenu();
        this.jMenuItemCopy = new JMenuItem();
        this.statusBar = new JLabel();
        this.jMenuShow = new JMenu();
        this.jCheckBoxMenuItemFrac = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemDecimal = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemIntFrac = new JCheckBoxMenuItem();
        this.pos = 0;
        this.jMenuItemSolution = new JMenuItem();
        this.linearEquSolution = null;
        this.isInApplet = false;
        this.jCheckBoxMenuItemCellWidth = new JCheckBoxMenuItem();
        this.selectedRow = -1;
        this.selectedColumn = -1;
        this.selectedRow = x.jTable1.getSelectedRow();
        this.selectedColumn = x.jTable1.getSelectedColumn();
        this.fMatrix = new FracMatrix(x.fMatrix);
        this.init(x.rows, x.columns, isInApplet);
    }
    
    public VisibleFracMatrix(final VisibleFracMatrix x) {
        this(x, false);
    }
    
    private void init(final int row, final int col, final boolean isInApplet) {
        this.isInApplet = isInApplet;
        this.rows = row;
        this.columns = col;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public VisibleFracMatrix(final boolean isInApplet) {
        this.fMatrix = null;
        this.columnNames = new String[21];
        this.rowNames = new String[21];
        this.jToolBar1 = new JToolBar();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new MyTable();
        this.jLabel1 = new JLabel();
        this.myComboBoxRows = new MyComboBox(1, 20);
        this.jButtonModify = new JButton();
        this.jLabel2 = new JLabel();
        this.myComboBoxColumns = new MyComboBox(1, 20);
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuItemSaveAs = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jMenuEdit = new JMenu();
        this.jMenuItemCopy = new JMenuItem();
        this.statusBar = new JLabel();
        this.jMenuShow = new JMenu();
        this.jCheckBoxMenuItemFrac = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemDecimal = new JCheckBoxMenuItem();
        this.jCheckBoxMenuItemIntFrac = new JCheckBoxMenuItem();
        this.pos = 0;
        this.jMenuItemSolution = new JMenuItem();
        this.linearEquSolution = null;
        this.isInApplet = false;
        this.jCheckBoxMenuItemCellWidth = new JCheckBoxMenuItem();
        this.selectedRow = -1;
        this.selectedColumn = -1;
        this.init(4, 4, isInApplet);
    }
    
    public VisibleFracMatrix() {
        this(false);
    }
    
    public void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == 201) {
            this.jMenuItemExit_actionPerformed(null);
        }
    }
    
    private void jbInit() throws Exception {
        this.titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(134, 134, 134)), ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Matrix"));
        this.jLabel1.setText(" " + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("row"));
        this.jButtonModify.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Change"));
        this.jButtonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jButtonModify_actionPerformed(e);
            }
        });
        this.jLabel2.setText(" " + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("column"));
        this.jToolBar1.setBorder(null);
        this.jScrollPane1.setBorder(this.titledBorder2);
        this.myComboBoxColumns.setMaximumSize(new Dimension(80, 21));
        this.myComboBoxRows.setMaximumSize(new Dimension(80, 21));
        this.jMenuItemExit.setMnemonic('X');
        this.jMenuItemExit.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Close"));
        this.jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jMenuItemExit_actionPerformed(e);
            }
        });
        this.jMenuFile.setMnemonic('F');
        this.jMenuFile.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("File"));
        this.jMenuEdit.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Edit"));
        this.jMenuItemCopy.setMnemonic('C');
        this.jMenuItemCopy.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Copy"));
        this.jMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(67, 2, false));
        this.jMenuItemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jMenuItemCopy_actionPerformed(e);
            }
        });
        this.jTable1.setAutoResizeMode(4);
        this.jTable1.setCellSelectionEnabled(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                VisibleFracMatrix.this.this_windowClosing(e);
            }
        });
        this.jMenuShow.setMnemonic('S');
        this.jMenuShow.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Show"));
        this.jCheckBoxMenuItemFrac.setMnemonic('F');
        this.jCheckBoxMenuItemFrac.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Fraction"));
        this.jCheckBoxMenuItemFrac.setState(true);
        this.jCheckBoxMenuItemFrac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemDecimal.setMnemonic('D');
        this.jCheckBoxMenuItemDecimal.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Decimal"));
        this.jCheckBoxMenuItemDecimal.setState(false);
        this.jCheckBoxMenuItemDecimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemIntFrac.setMnemonic('I');
        this.jCheckBoxMenuItemIntFrac.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("MixedFraction"));
        this.jCheckBoxMenuItemIntFrac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jCheckBoxMenuItem_actionPerformed(e);
            }
        });
        this.jMenuItemSolution.setMnemonic('S');
        this.jMenuItemSolution.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("ViewSolution"));
        this.jMenuItemSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jMenuItemSolution_actionPerformed(e);
            }
        });
        this.jCheckBoxMenuItemCellWidth.setSelected(true);
        this.jCheckBoxMenuItemCellWidth.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("AlignCellWidth"));
        this.jCheckBoxMenuItemCellWidth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VisibleFracMatrix.this.jCheckBoxMenuItemCellWidth_actionPerformed(e);
            }
        });
        this.setDefaultCloseOperation(2);
        this.getContentPane().add(this.jToolBar1, "North");
        this.jToolBar1.add(this.jLabel1, null);
        this.jToolBar1.add(this.myComboBoxRows, null);
        this.getContentPane().add(this.jScrollPane1, "Center");
        this.getContentPane().add(this.statusBar, "South");
        this.jScrollPane1.getViewport().add(this.jTable1, null);
        this.jToolBar1.add(this.jButtonModify, null);
        this.jToolBar1.add(this.jLabel2, null);
        this.jToolBar1.add(this.myComboBoxColumns, null);
        this.jMenuBar1.add(this.jMenuFile);
        this.jMenuBar1.add(this.jMenuEdit);
        this.jMenuBar1.add(this.jMenuShow);
        if (!this.isInApplet) {
            this.jMenuItemSaveAs.setMnemonic('A');
            this.jMenuItemSaveAs.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("SaveAs"));
            this.jMenuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(65, 2, false));
            this.jMenuItemSaveAs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    VisibleFracMatrix.this.jMenuItemSaveAs_actionPerformed(e);
                }
            });
            this.jMenuFile.add(this.jMenuItemSaveAs);
            this.jMenuFile.addSeparator();
        }
        this.jMenuFile.add(this.jMenuItemExit);
        this.jMenuEdit.add(this.jMenuItemCopy);
        this.jMenuShow.add(this.jCheckBoxMenuItemFrac);
        ++this.pos;
        this.jMenuShow.add(this.jCheckBoxMenuItemDecimal);
        ++this.pos;
        this.jMenuShow.add(this.jCheckBoxMenuItemIntFrac);
        ++this.pos;
        this.jMenuShow.addSeparator();
        ++this.pos;
        this.jMenuShow.add(this.jCheckBoxMenuItemCellWidth);
        ++this.pos;
        this.setJMenuBar(this.jMenuBar1);
        this.setTitle(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Matrix"));
        this.myTableModel = new VisibleFracMatrixTableModel();
        this.myComboBoxColumns.setSelectedIndex(this.columns - 1);
        this.myComboBoxRows.setSelectedIndex(this.rows - 1);
        if (this.fMatrix == null) {
            this.fMatrix = new FracMatrix(this.rows, this.columns);
        }
        final FracCellEditor fCellEditor = new FracCellEditor();
        this.jTable1.setDefaultEditor(Frac.class, fCellEditor);
        this.fCellRenderer = new FracCellRenderer();
        this.jTable1.setDefaultRenderer(Frac.class, this.fCellRenderer);
        final CenteredRenderer cRenderer = new CenteredRenderer((CenteredRenderer)null);
        this.jTable1.setDefaultRenderer(String.class, cRenderer);
        this.jTable1.setModel(this.myTableModel);
        if (this.selectedRow != -1 && this.selectedColumn != -1) {
            this.jTable1.setRowSelectionInterval(this.selectedRow, this.selectedRow);
            this.jTable1.setColumnSelectionInterval(this.selectedColumn, this.selectedColumn);
        }
        this.setSize(400, 300);
        this.adjustfirstcolumn();
    }
    
    public void characteristicFunction() {
        this.setFMatrix(this.fMatrix.characteristicFunction());
    }
    
    public boolean isSquare() {
        return this.fMatrix.isSquare();
    }
    
    public boolean isPivotSelected() {
        final int row = this.jTable1.getSelectedRow();
        final int col = this.jTable1.getSelectedColumn() - 1;
        return row != -1 && col != -1;
    }
    
    public void mul(final VisibleFracMatrix x) {
        try {
            this.setFMatrix(this.fMatrix.mul(x.fMatrix));
            this.myTableModel.fireTableStructureChanged();
            this.adjustfirstcolumn();
        }
        catch (FracMatrix.NullFracMatrixException ex) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("CannotComputeProduct"));
        }
        catch (FracMatrix.ProductCannotComputedException ex2) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("CannotComputeProduct"));
        }
    }
    
    public void inverse() {
        try {
            this.setFMatrix(this.fMatrix.inverse());
        }
        catch (FracMatrix.NotSquareMatrixException ex) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("NotSquareMatrix"));
        }
        catch (FracMatrix.InverseNotExistException ex2) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("InverseNotExist"));
        }
    }
    
    public void solveLinearEq() {
        this.setFMatrix(this.fMatrix.solveLinearEq());
        if (this.fMatrix.getIValue() == 0) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("NoSolutionForLinearEq"));
            return;
        }
        this.setShowMenu();
    }
    
    private void setShowMenu() {
        this.jMenuShow.addSeparator();
        this.jMenuShow.add(this.jMenuItemSolution);
        this.jMenuShow.updateUI();
    }
    
    public void columnVectorOrthogonalize() {
        this.fMatrix.columnVectorOrthogonalize();
        this.jTable1.updateUI();
    }
    
    public void pivot() {
        final int row = this.jTable1.getSelectedRow();
        final int col = this.jTable1.getSelectedColumn() - 1;
        if (row == -1 || col == -1) {
            return;
        }
        this.fMatrix.pivot(row, col);
        this.jTable1.updateUI();
    }
    
    public void rank() {
        this.setFMatrix(this.fMatrix.rank());
        this.statusBar.setText(String.valueOf(this.getTitle()) + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("is") + this.fMatrix.getIValue() + " " + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("\u3067\u3059"));
    }
    
    public Frac determinant() {
        try {
            this.setFMatrix(this.fMatrix.determinant());
        }
        catch (FracMatrix.NotSquareMatrixException ex) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("NotSquareMatrix"));
            return Frac.Zero;
        }
        final Frac ret = this.fMatrix.getFValue();
        final String str = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Determinant")) + this.getTitle() + " = " + ret + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("\u3067\u3059");
        this.statusBar.setText(str);
        return ret;
    }
    
    public int getRowCount() {
        return this.fMatrix.getRowCount();
    }
    
    public int getColumnCount() {
        return this.fMatrix.getColumnCount();
    }
    
    public void fromtomul(final int from, final int to, final Frac mul, final boolean row) {
        this.fMatrix.fromtomul(from, to, mul, row);
        this.jTable1.updateUI();
    }
    
    public void exchange(final int from, final int to, final boolean row) {
        this.fMatrix.exchange(from, to, row);
        this.jTable1.updateUI();
    }
    
    public void mulrowcolumns(final int to, final Frac mul, final boolean row) {
        this.fMatrix.mulrowcolumns(to, mul, row);
        this.jTable1.updateUI();
    }
    
    public void setColumnNames(final String[] colNames) {
        if (this.columnNames.length != colNames.length) {
            return;
        }
        this.columnNames = colNames;
        this.myTableModel.fireTableStructureChanged();
        this.adjustfirstcolumn();
    }
    
    public void setFMatrix(final FracMatrix x) {
        this.fMatrix = x;
        this.rows = x.getRowCount();
        this.columns = x.getColumnCount();
    }
    
    void jMenuItemExit_actionPerformed(final ActionEvent e) {
        this.dispose();
    }
    
    private String tableToString() {
        String text = String.valueOf(this.getTitle()) + "\n";
        final int jj = this.myTableModel.getColumnCount();
        for (int j = 0; j < jj; ++j) {
            if (j == jj - 1) {
                text = String.valueOf(text) + this.myTableModel.getColumnName(j) + "\n";
            }
            else {
                text = String.valueOf(text) + this.myTableModel.getColumnName(j) + "\t";
            }
        }
        for (int i = 0; i < this.myTableModel.getRowCount(); ++i) {
            for (int k = 0; k < jj; ++k) {
                if (k == jj - 1) {
                    text = String.valueOf(text) + this.myTableModel.getValueAt(i, k).toString() + "\n";
                }
                else {
                    text = String.valueOf(text) + this.myTableModel.getValueAt(i, k).toString() + "\t";
                }
            }
        }
        text = String.valueOf(text) + "\n";
        text = String.valueOf(text) + this.statusBar.getText();
        text = String.valueOf(text) + "\n";
        return text;
    }
    
    void jMenuItemCopy_actionPerformed(final ActionEvent e) {
        final JTextArea jTextArea1 = new JTextArea();
        final String str = this.tableToString();
        jTextArea1.setText(str);
        jTextArea1.selectAll();
        jTextArea1.copy();
    }
    
    void jMenuItemSaveAs_actionPerformed(final ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        final int returnVal = fc.showSaveDialog(this);
        if (returnVal == 0) {
            final File file = fc.getSelectedFile();
            final String fileName = file.getName();
            try {
                final FileWriter out = new FileWriter(file);
                final String text = this.tableToString();
                out.write(text);
                out.close();
                this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("toFile")) + fileName + " " + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Saved"));
            }
            catch (IOException ex) {
                this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("toFile")) + fileName + " " + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("CannotBeSaved"));
            }
        }
    }
    
    private void adjustfirstcolumn() {
        final TableColumn column = this.jTable1.getColumnModel().getColumn(0);
        final Component comp = this.jTable1.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
        final int headerWidth = comp.getPreferredSize().width;
        column.setPreferredWidth(headerWidth);
    }
    
    void jButtonModify_actionPerformed(final ActionEvent e) {
        final int newrows = this.myComboBoxRows.getSelectedIndex() + 1;
        final int newcolumns = this.myComboBoxColumns.getSelectedIndex() + 1;
        if (this.rows == newrows && this.columns == newcolumns) {
            return;
        }
        this.myTableModel.setRows(newrows);
        this.myTableModel.setColumns(newcolumns);
        this.adjustfirstcolumn();
    }
    
    void this_windowClosing(final WindowEvent e) {
        if (this.linearEquSolution != null) {
            this.linearEquSolution.dispose();
        }
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
    
    void jMenuItemSolution_actionPerformed(final ActionEvent e) {
        this.linearEquSolution = new LinearEquSolution(this.fMatrix, this.isInApplet);
        final Point p = this.getLocation();
        p.translate(100, 100);
        this.linearEquSolution.setSize(400, 250);
        this.linearEquSolution.setLocation(p);
        this.linearEquSolution.setTitle(String.valueOf(this.getTitle()) + ResourceBundle.getBundle("org.namekata.fundamental/VisibleFracMatrix").getString("Solution"));
        this.linearEquSolution.setVisible(true);
        this.jMenuShow.remove(this.jMenuItemSolution);
        this.jMenuShow.remove(this.pos);
        this.jMenuShow.updateUI();
    }
    
    void jCheckBoxMenuItemCellWidth_actionPerformed(final ActionEvent e) {
        final boolean state = this.jCheckBoxMenuItemCellWidth.getState();
        this.jCheckBoxMenuItemCellWidth.setState(state);
        if (state) {
            this.jTable1.setAutoResizeMode(4);
        }
        else {
            this.jTable1.setAutoResizeMode(0);
        }
    }
    
    static /* synthetic */ void access$5(final VisibleFracMatrix visibleFracMatrix, final int rows) {
        visibleFracMatrix.rows = rows;
    }
    
    static /* synthetic */ void access$6(final VisibleFracMatrix visibleFracMatrix, final int columns) {
        visibleFracMatrix.columns = columns;
    }
    
    public class VisibleFracMatrixTableModel extends AbstractTableModel
    {
        public VisibleFracMatrixTableModel() {
            VisibleFracMatrix.this.columnNames[0] = " ";
            for (int j = 1; j < 20; ++j) {
                VisibleFracMatrix.this.columnNames[j] = new StringBuilder().append(j).toString();
            }
            for (int i = 0; i < 20; ++i) {
                VisibleFracMatrix.this.rowNames[i] = new StringBuilder().append(i + 1).toString();
            }
        }
        
        @Override
        public Class getColumnClass(final int c) {
            return this.getValueAt(0, c).getClass();
        }
        
        @Override
        public int getColumnCount() {
            return VisibleFracMatrix.this.columns + 1;
        }
        
        @Override
        public int getRowCount() {
            return VisibleFracMatrix.this.rows;
        }
        
        @Override
        public String getColumnName(final int col) {
            return VisibleFracMatrix.this.columnNames[col];
        }
        
        @Override
        public Object getValueAt(final int row, final int col) {
            if (col == 0) {
                return VisibleFracMatrix.this.rowNames[row];
            }
            return VisibleFracMatrix.this.fMatrix.getData()[row][col - 1];
        }
        
        @Override
        public boolean isCellEditable(final int row, final int col) {
            return col >= 1;
        }
        
        @Override
        public void setValueAt(final Object value, final int row, final int col) {
            VisibleFracMatrix.this.fMatrix.setValue(value, row, col - 1);
        }
        
        public void setRows(final int row) {
            if (row == VisibleFracMatrix.this.rows || row < 1 || row > 20) {
                return;
            }
            VisibleFracMatrix.access$5(VisibleFracMatrix.this, row);
            VisibleFracMatrix.this.fMatrix.setRowCount(VisibleFracMatrix.this.rows);
            this.fireTableDataChanged();
        }
        
        public void setColumns(final int col) {
            if (col == VisibleFracMatrix.this.columns || col < 1 || col > 20) {
                return;
            }
            VisibleFracMatrix.access$6(VisibleFracMatrix.this, col);
            VisibleFracMatrix.this.fMatrix.setColumnCount(VisibleFracMatrix.this.columns);
            this.fireTableStructureChanged();
        }
    }
    
    private class CenteredRenderer extends JLabel implements TableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            this.setText(value.toString());
            this.setHorizontalAlignment(0);
            this.setOpaque(true);
            this.setBackground(Color.lightGray);
            return this;
        }
    }
}
