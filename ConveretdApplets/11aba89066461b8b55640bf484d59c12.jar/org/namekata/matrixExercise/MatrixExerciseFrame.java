// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.matrixExercise;

import java.awt.Frame;
import org.namekata.fundamental.MyAboutDialog;
import javax.swing.JOptionPane;
import java.awt.Window;
import org.namekata.fundamental.VisibleFracMatrix;
import javax.swing.border.Border;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.Locale;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.ResourceBundle;
import java.awt.LayoutManager;
import javax.swing.ListModel;
import org.namekata.fundamental.Frac;
import org.namekata.fundamental.MyComboBox;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JLabel;
import org.namekata.fundamental.FracField;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class MatrixExerciseFrame extends JFrame
{
    private JPanel contentPane;
    private DefaultListModel listModel;
    private MyListCellRenderer myListCellRenderer;
    private int myMatrixs;
    private Point p;
    private int pcount;
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder2;
    private BorderLayout borderLayout1;
    private int count;
    private boolean jListASelected;
    private boolean jListBSelected;
    private JMenuBar jMenuBar1;
    private JMenu jMenuFile;
    private JMenu jMenuHelp;
    private JMenuItem jMenuItemHelpAbout;
    private JMenuItem jMenuItemExit;
    private JPanel jPanel15;
    private JPanel jPanel5;
    private BorderLayout borderLayout4;
    private JToolBar jToolBar1;
    private JButton jButtonProduct;
    private JButton jButtonRank;
    private JButton jButtonLEqu;
    private JButton jButtonAdd;
    private FracField jTextFieldMul;
    private JLabel jLabel12;
    private JLabel jLabel11;
    private JLabel jLabel10;
    private JButton jButtonTrans;
    private JList jListB;
    private JList jListA;
    private JCheckBox jCheckBox1;
    private JPanel jPanel9;
    private JLabel jLabel9;
    private JPanel jPanel8;
    private JLabel jLabel8;
    private JPanel jPanel7;
    private JLabel jLabel7;
    private JPanel jPanel6;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JPanel jPanel4;
    private JPanel jPanel3;
    private JLabel jLabel4;
    private JPanel jPanel2;
    private JLabel jLabel3;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JLabel jLabel1;
    private BorderLayout borderLayout6;
    private BorderLayout borderLayout5;
    private BorderLayout borderLayout3;
    private BorderLayout borderLayout2;
    private JPanel jPanel14;
    private JToolBar jToolBar2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane2;
    private JPanel jPanel12;
    private JScrollPane jScrollPane1;
    private JPanel jPanel11;
    private JPanel jPanel10;
    private GridLayout gridLayout1;
    private JButton jButtonMul;
    private JToolBar jToolBar3;
    private JButton jButtonCharFun;
    private JButton jButtonInverse;
    private JButton jButtonDeterminant;
    private JButton jButtonNew;
    private JButton jButtonPivot;
    private MyComboBox myComboBoxRow1;
    private MyComboBox myComboBoxRow2;
    private boolean isStandAlone;
    private JCheckBox jCheckBoxNewWindow;
    private int jListASelectedIndex;
    private boolean isActivated;
    private JButton jButtonOrthogonalize;
    private JToolBar.Separator separator1;
    private JToolBar.Separator separator;
    private JToolBar.Separator separator2;
    
    public MatrixExerciseFrame() {
        this(true);
    }
    
    public MatrixExerciseFrame(final boolean isStandAlone) {
        this.listModel = new DefaultListModel();
        this.myListCellRenderer = new MyListCellRenderer();
        this.myMatrixs = 0;
        this.p = new Point(0, 0);
        this.pcount = 0;
        this.borderLayout1 = new BorderLayout();
        this.count = 0;
        this.jMenuBar1 = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuHelp = new JMenu();
        this.jMenuItemHelpAbout = new JMenuItem();
        this.jMenuItemExit = new JMenuItem();
        this.jPanel15 = new JPanel();
        this.jPanel5 = new JPanel();
        this.borderLayout4 = new BorderLayout();
        this.jToolBar1 = new JToolBar();
        this.jButtonProduct = new JButton();
        this.jButtonRank = new JButton();
        this.jButtonLEqu = new JButton();
        this.jButtonAdd = new JButton();
        this.jTextFieldMul = new FracField(Frac.Unit, 4);
        this.jLabel12 = new JLabel();
        this.jLabel11 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jButtonTrans = new JButton();
        this.jListB = new JList();
        this.jListA = new JList(this.listModel);
        this.jCheckBox1 = new JCheckBox();
        this.jPanel9 = new JPanel();
        this.jLabel9 = new JLabel();
        this.jPanel8 = new JPanel();
        this.jLabel8 = new JLabel();
        this.jPanel7 = new JPanel();
        this.jLabel7 = new JLabel();
        this.jPanel6 = new JPanel();
        this.jLabel6 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jPanel4 = new JPanel();
        this.jPanel3 = new JPanel();
        this.jLabel4 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jLabel3 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.borderLayout6 = new BorderLayout();
        this.borderLayout5 = new BorderLayout();
        this.borderLayout3 = new BorderLayout();
        this.borderLayout2 = new BorderLayout();
        this.jPanel14 = new JPanel();
        this.jToolBar2 = new JToolBar();
        this.jScrollPane3 = new JScrollPane();
        this.jScrollPane2 = new JScrollPane();
        this.jPanel12 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jPanel11 = new JPanel();
        this.jPanel10 = new JPanel();
        this.gridLayout1 = new GridLayout();
        this.jButtonMul = new JButton();
        this.jToolBar3 = new JToolBar();
        this.jButtonCharFun = new JButton();
        this.jButtonInverse = new JButton();
        this.jButtonDeterminant = new JButton();
        this.jButtonNew = new JButton();
        this.jButtonPivot = new JButton();
        this.myComboBoxRow1 = new MyComboBox();
        this.myComboBoxRow2 = new MyComboBox();
        this.isStandAlone = true;
        this.jCheckBoxNewWindow = new JCheckBox();
        this.jListASelectedIndex = -2;
        this.isActivated = false;
        this.jButtonOrthogonalize = null;
        this.separator1 = null;
        this.separator = null;
        this.separator2 = null;
        this.isStandAlone = isStandAlone;
        this.enableEvents(64L);
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.contentPane = (JPanel)this.getContentPane();
        this.titledBorder1 = new TitledBorder("");
        this.titledBorder2 = new TitledBorder("");
        this.contentPane.setLayout(this.borderLayout1);
        this.setDefaultCloseOperation(2);
        this.setTitle(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("FrameTitle"));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(final WindowEvent e) {
                MatrixExerciseFrame.this.this_windowActivated(e);
            }
            
            @Override
            public void windowDeactivated(final WindowEvent e) {
                MatrixExerciseFrame.this.this_windowDeactivated(e);
            }
        });
        this.jButtonProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonProduct_actionPerformed(e);
            }
        });
        this.jButtonProduct.setText("A * B");
        this.jButtonProduct.setEnabled(false);
        this.jButtonProduct.setToolTipText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("ProductOfAandB"));
        this.jButtonRank.setEnabled(false);
        this.jButtonRank.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("RankOfA"));
        this.jButtonRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonRank_actionPerformed(e);
            }
        });
        this.jButtonLEqu.setEnabled(false);
        this.jButtonLEqu.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("SolveLinearEq(A)"));
        this.jButtonLEqu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonLEqu_actionPerformed(e);
            }
        });
        this.jButtonCharFun.setEnabled(false);
        this.jButtonCharFun.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("CharFun"));
        this.jButtonCharFun.setToolTipText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("CharFunOfA"));
        this.jButtonCharFun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonCharFun_actionPerformed(e);
            }
        });
        this.jButtonInverse.setText("1/A");
        this.jButtonInverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonInverse_actionPerformed(e);
            }
        });
        this.jButtonInverse.setEnabled(false);
        this.jButtonInverse.setToolTipText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("InverseOfA"));
        this.jButtonDeterminant.setEnabled(false);
        this.jButtonDeterminant.setText("|A|");
        this.jButtonDeterminant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonDeterminant_actionPerformed(e);
            }
        });
        this.jButtonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonNew_actionPerformed(e);
            }
        });
        this.jButtonNew.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("NewMatrix"));
        this.jButtonPivot.setEnabled(false);
        this.jButtonPivot.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("PivotOperation"));
        this.jButtonPivot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButtonPivot_actionPerformed(e);
            }
        });
        this.jCheckBox1.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("OperateInverseColumn"));
        this.jCheckBoxNewWindow.setSelected(true);
        this.jCheckBoxNewWindow.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("ShowResultInNewWindow"));
        this.jPanel9.add(this.jCheckBox1, null);
        this.jPanel9.add(this.jCheckBoxNewWindow, null);
        this.jLabel3.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("Ano"));
        this.myComboBoxRow1.setSelectedIndex(1);
        this.myComboBoxRow1.setPreferredSize(new Dimension(50, 24));
        this.myComboBoxRow1.setRange(1, 1);
        JPanel tmp = new JPanel();
        this.jLabel4.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row31"));
        this.jLabel7.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row32"));
        this.jLabel8.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row33"));
        tmp.add(this.jLabel4);
        this.jPanel10.setLayout(new BoxLayout(this.jPanel10, 1));
        this.jPanel10.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel7);
        this.jPanel10.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel8);
        this.jPanel10.add(tmp);
        this.jTextFieldMul.setPreferredSize(new Dimension(90, 21));
        this.jTextFieldMul.setText("1");
        this.jTextFieldMul.setColumns(9);
        this.jLabel5.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row51"));
        this.jLabel9.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row52"));
        this.jLabel10.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row53"));
        this.jPanel11.setLayout(new BoxLayout(this.jPanel11, 1));
        tmp = new JPanel();
        tmp.add(this.jLabel5);
        this.jPanel11.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel9);
        this.jPanel11.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel10);
        this.jPanel11.add(tmp);
        this.myComboBoxRow2.setSelectedIndex(1);
        this.myComboBoxRow2.setPreferredSize(new Dimension(50, 24));
        this.myComboBoxRow2.setRange(1, 1);
        this.jLabel6.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row71"));
        this.jLabel11.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row72"));
        this.jLabel12.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("row73"));
        this.jPanel12.setLayout(new BoxLayout(this.jPanel12, 1));
        tmp = new JPanel();
        tmp.add(this.jLabel6);
        this.jPanel12.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel11);
        this.jPanel12.add(tmp);
        tmp = new JPanel();
        tmp.add(this.jLabel12);
        this.jPanel12.add(tmp);
        this.jButtonAdd.setEnabled(false);
        this.jButtonAdd.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("Add"));
        this.jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButton_actionPerformed(e);
            }
        });
        this.jButtonTrans.setEnabled(false);
        this.jButtonTrans.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("Exchange"));
        this.jButtonTrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButton_actionPerformed(e);
            }
        });
        this.jButtonMul.setEnabled(false);
        this.jButtonMul.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("Multiply"));
        this.jButtonMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jButton_actionPerformed(e);
            }
        });
        this.gridLayout1.setRows(3);
        this.gridLayout1.setColumns(1);
        this.jPanel6.setLayout(this.gridLayout1);
        this.jPanel6.add(this.jButtonAdd, null);
        this.jPanel6.add(this.jButtonTrans, null);
        this.jPanel6.add(this.jButtonMul, null);
        if (Locale.getDefault().getLanguage().equals(new Locale("ja", "", "").getLanguage())) {
            this.jPanel8.add(this.jLabel3, null);
            this.jPanel8.add(this.myComboBoxRow1, null);
            this.jPanel8.add(this.jPanel10, null);
            this.jPanel8.add(this.jTextFieldMul, null);
            this.jPanel8.add(this.jPanel11, null);
            this.jPanel8.add(this.myComboBoxRow2, null);
            this.jPanel8.add(this.jPanel12, null);
            this.jPanel8.add(this.jPanel6, null);
        }
        else {
            this.jPanel8.add(this.jPanel6, null);
            this.jPanel8.add(this.myComboBoxRow1, null);
            this.jPanel8.add(this.jLabel3, null);
            this.jPanel8.add(this.jPanel10, null);
            this.jPanel8.add(this.jTextFieldMul, null);
            this.jPanel8.add(this.jPanel11, null);
            this.jPanel8.add(this.myComboBoxRow2, null);
            this.jPanel8.add(this.jPanel12, null);
        }
        this.jPanel7.setLayout(new BoxLayout(this.jPanel7, 1));
        this.jPanel7.add(this.jPanel9, null);
        this.jPanel7.add(this.jPanel8, null);
        this.jListA.setBorder(BorderFactory.createLineBorder(Color.black));
        this.jListA.setSelectionMode(0);
        this.jListA.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent e) {
                MatrixExerciseFrame.this.jListA_valueChanged(e);
            }
        });
        this.jListA.setCellRenderer(this.myListCellRenderer);
        this.jListB.setBorder(BorderFactory.createLineBorder(Color.black));
        this.jListB.setModel(this.listModel);
        this.jListB.setSelectionMode(0);
        this.jListB.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent e) {
                MatrixExerciseFrame.this.jListB_valueChanged(e);
            }
        });
        this.jListB.setCellRenderer(this.myListCellRenderer);
        this.jScrollPane1.getViewport().add(this.jListA, null);
        this.jScrollPane2.getViewport().add(this.jListB, null);
        this.jPanel14.setLayout(this.borderLayout6);
        this.jScrollPane3.setBorder(null);
        this.jPanel4.setLayout(this.borderLayout5);
        this.jPanel4.add(this.jScrollPane3, "Center");
        this.jLabel1.setBorder(this.titledBorder1);
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MatrixA"));
        this.jPanel1.setLayout(this.borderLayout3);
        this.jPanel1.add(this.jLabel1, "North");
        this.jPanel1.add(this.jScrollPane1, "Center");
        this.jPanel3.setLayout(this.borderLayout2);
        this.jLabel2.setBorder(this.titledBorder2);
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MatrixB"));
        this.jPanel3.add(this.jLabel2, "North");
        this.jPanel3.add(this.jScrollPane2, "Center");
        this.jPanel2.setLayout(new BoxLayout(this.jPanel2, 0));
        this.jPanel2.add(this.jPanel1, null);
        this.jPanel2.add(this.jPanel3, null);
        this.jScrollPane3.getViewport().add(this.jPanel7, null);
        this.jToolBar2.add(this.jPanel4, null);
        this.jPanel14.add(this.jToolBar2, "North");
        this.jPanel14.add(this.jPanel2, "Center");
        this.jToolBar1.add(this.jButtonRank, null);
        this.jToolBar1.add(this.jButtonLEqu, null);
        this.jToolBar1.add(this.jButtonPivot, null);
        this.jToolBar1.add(this.getSeparator());
        this.jToolBar1.add(this.jButtonCharFun);
        this.jPanel5.setLayout(this.borderLayout4);
        this.jPanel5.add(this.jToolBar1, "North");
        this.jPanel5.add(this.jPanel14, "Center");
        this.jToolBar3.add(this.jButtonNew, null);
        this.jToolBar3.add(this.getSeparator1());
        this.jToolBar3.add(this.getJButtonOrthogonalize());
        this.jToolBar3.add(this.getSeparator2());
        this.jToolBar3.add(this.jButtonProduct, null);
        this.jToolBar3.add(this.jButtonDeterminant, null);
        this.jToolBar3.add(this.jButtonInverse, null);
        this.contentPane.add(this.jToolBar3, "North");
        this.contentPane.add(this.jPanel5, "Center");
        this.jMenuFile.setMnemonic('F');
        this.jMenuFile.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MenuFile"));
        this.jMenuHelp.setMnemonic('H');
        this.jMenuHelp.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MenuHelp"));
        this.jMenuItemHelpAbout.setMnemonic('A');
        this.jMenuItemHelpAbout.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MenuHelpAbout"));
        this.jMenuItemHelpAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jMenuItemHelpAbout_actionPerformed(e);
            }
        });
        this.jMenuItemExit.setMnemonic('X');
        this.jMenuItemExit.setText(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MenuExit"));
        this.jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MatrixExerciseFrame.this.jMenuItemExit_actionPerformed(e);
            }
        });
        this.jMenuBar1.add(this.jMenuFile);
        this.jMenuBar1.add(this.jMenuHelp);
        this.jMenuHelp.add(this.jMenuItemHelpAbout);
        this.jMenuFile.add(this.jMenuItemExit);
        this.setJMenuBar(this.jMenuBar1);
    }
    
    public void closeChild(final Object frame) {
        this.listModel.removeElement(frame);
        this.jListA_valueChanged(null);
        this.jListB_valueChanged(null);
    }
    
    @Override
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == 201) {
            for (int ii = this.listModel.getSize(), i = 0; i < ii; ++i) {
                this.listModel.getElementAt(i).processWindowEvent(new WindowEvent(this, 201));
            }
            if (this.isStandAlone) {
                System.exit(0);
            }
        }
    }
    
    void jButtonNew_actionPerformed(final ActionEvent e) {
        this.createMatrix(null, 4, 4, "M" + Integer.toString(++this.myMatrixs));
    }
    
    void jButtonProduct_actionPerformed(final ActionEvent e) {
        final VisibleFracMatrix a = this.jListA.getSelectedValue();
        final VisibleFracMatrix b = this.jListB.getSelectedValue();
        if (a == null || b == null) {
            return;
        }
        if (a.getColumnCount() != b.getRowCount()) {
            this.showmessage(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("CannotComputeProductOfMatrices"));
            return;
        }
        final VisibleFracMatrix matrix = this.createMatrix(a, 0, 0, "(" + a.getTitle() + ")*(" + b.getTitle() + ")");
        matrix.mul(b);
    }
    
    private VisibleFracMatrix createMatrix(final VisibleFracMatrix x, final int row, final int column, final String str) {
        if (this.pcount++ > 10) {
            this.pcount = 0;
            this.p = new Point(0, 0);
        }
        this.p.translate(20, 20);
        VisibleFracMatrix matrix;
        if (x == null) {
            matrix = new VisibleFracMatrix(row, column, !this.isStandAlone);
        }
        else {
            matrix = new VisibleFracMatrix(x, !this.isStandAlone);
        }
        matrix.setTitle(str);
        matrix.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                if (e.getSource().getClass() == VisibleFracMatrix.class) {
                    MatrixExerciseFrame.this.closeChild(e.getSource());
                }
            }
            
            @Override
            public void windowActivated(final WindowEvent e) {
                final VisibleFracMatrix mat = (VisibleFracMatrix)e.getSource();
                MatrixExerciseFrame.this.jListA.setSelectedValue(mat, true);
            }
            
            @Override
            public void windowDeactivated(final WindowEvent e) {
                final VisibleFracMatrix mat = (VisibleFracMatrix)e.getSource();
                final int row = mat.getRowCount();
                MatrixExerciseFrame.this.myComboBoxRow1.setRange(1, row);
                MatrixExerciseFrame.this.myComboBoxRow2.setRange(1, row);
            }
        });
        this.listModel.addElement(matrix);
        matrix.setLocation(this.p);
        matrix.setVisible(true);
        return matrix;
    }
    
    void jListA_valueChanged(final ListSelectionEvent e) {
        final int jListASelectedIndexNow = this.jListA.getSelectedIndex();
        if (jListASelectedIndexNow == -1) {
            this.jListASelectedIndex = -1;
            this.jListASelected = false;
            this.jButtonOrthogonalize.setEnabled(false);
            this.jButtonPivot.setEnabled(false);
            this.jButtonCharFun.setEnabled(false);
            this.jButtonInverse.setEnabled(false);
            this.jButtonDeterminant.setEnabled(false);
            this.jButtonRank.setEnabled(false);
            this.jButtonLEqu.setEnabled(false);
            this.jButtonAdd.setEnabled(false);
            this.jButtonTrans.setEnabled(false);
            this.jButtonMul.setEnabled(false);
            this.jButtonAdd.setEnabled(false);
        }
        else {
            if (this.jListASelectedIndex != jListASelectedIndexNow) {
                this.jListASelectedIndex = jListASelectedIndexNow;
                final VisibleFracMatrix mat = this.jListA.getSelectedValue();
                mat.toFront();
            }
            if (this.isActivated) {
                this.toFront();
            }
            this.jListASelected = true;
            this.jButtonOrthogonalize.setEnabled(true);
            this.jButtonPivot.setEnabled(true);
            this.jButtonCharFun.setEnabled(true);
            this.jButtonInverse.setEnabled(true);
            this.jButtonDeterminant.setEnabled(true);
            this.jButtonRank.setEnabled(true);
            this.jButtonLEqu.setEnabled(true);
            this.jButtonAdd.setEnabled(true);
            this.jButtonTrans.setEnabled(true);
            this.jButtonMul.setEnabled(true);
            if (this.jListBSelected) {
                this.jButtonProduct.setEnabled(true);
            }
        }
    }
    
    private void showmessage(final String msg) {
        final JOptionPane p = new JOptionPane();
        JOptionPane.showMessageDialog(this, msg, ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("Error"), 0);
    }
    
    void jButton_actionPerformed(final ActionEvent e) {
        final Object obj = e.getSource();
        int type = 0;
        Frac mul = Frac.Zero;
        if (obj == this.jButtonAdd) {
            type = 1;
        }
        if (obj == this.jButtonTrans) {
            type = 2;
        }
        if (obj == this.jButtonMul) {
            type = 3;
        }
        final int rowfrom = this.myComboBoxRow1.getSelectedIndex();
        final int rowto = this.myComboBoxRow2.getSelectedIndex();
        if (type != 3 && rowfrom == rowto) {
            this.showmessage(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("CannotOperateInTheSameRow"));
            return;
        }
        try {
            mul = this.jTextFieldMul.getValue();
        }
        catch (NumberFormatException ex) {
            mul = Frac.Zero;
        }
        if (mul.equals(0L)) {
            this.showmessage(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("MultipliedBy0HasNoMeaning"));
            return;
        }
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        String str;
        char[] ch;
        int ij;
        for (str = mat.getTitle(), ch = str.toCharArray(), ij = 0; ij < str.length() && ch[ij] != '-'; ++ij) {}
        str = str.substring(0, ij);
        final VisibleFracMatrix matnew = this.jCheckBoxNewWindow.isSelected() ? this.createMatrix(mat, 0, 0, String.valueOf(str) + "-" + Integer.toString(++this.count)) : mat;
        switch (type) {
            case 1: {
                matnew.fromtomul(rowfrom, rowto, mul, true);
                if (this.jCheckBox1.isSelected()) {
                    matnew.fromtomul(rowto, rowfrom, Frac.Zero.sub(mul), false);
                    break;
                }
                break;
            }
            case 2: {
                matnew.exchange(rowfrom, rowto, true);
                if (this.jCheckBox1.isSelected()) {
                    matnew.exchange(rowto, rowfrom, false);
                    break;
                }
                break;
            }
            case 3: {
                matnew.mulrowcolumns(rowfrom, mul, true);
                if (this.jCheckBox1.isSelected()) {
                    matnew.mulrowcolumns(rowfrom, Frac.Unit.div(mul), false);
                    break;
                }
                break;
            }
        }
    }
    
    void jListB_valueChanged(final ListSelectionEvent e) {
        if (this.jListB.getSelectedIndex() == -1) {
            this.jListBSelected = false;
            this.jButtonProduct.setEnabled(false);
        }
        else {
            this.jListBSelected = true;
            if (this.jListASelected) {
                this.jButtonProduct.setEnabled(true);
            }
        }
    }
    
    void jButtonInverse_actionPerformed(final ActionEvent e) {
        this.inverse();
    }
    
    private void inverse() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        if (!mat.isSquare()) {
            this.showmessage(String.valueOf(mat.getTitle()) + " " + ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("isNotSquareMatrix"));
            return;
        }
        final VisibleFracMatrix tmp = new VisibleFracMatrix(mat);
        if (tmp.determinant().equals(0L)) {
            this.showmessage(String.valueOf(mat.getTitle()) + " " + ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("hasNoInverseMatrix"));
            return;
        }
        final String str = String.valueOf(mat.getTitle()) + "-Inverse";
        final VisibleFracMatrix matnew = this.createMatrix(mat, 0, 0, str);
        matnew.inverse();
    }
    
    private void charfun() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        final String str = String.valueOf(mat.getTitle()) + "-CharFun";
        final VisibleFracMatrix matnew = this.createMatrix(mat, 0, 0, str);
        matnew.characteristicFunction();
    }
    
    void jButtonCharFun_actionPerformed(final ActionEvent e) {
        this.charfun();
    }
    
    void jMenuItemExit_actionPerformed(final ActionEvent e) {
        this.processWindowEvent(new WindowEvent(this, 201));
    }
    
    void jMenuItemHelpAbout_actionPerformed(final ActionEvent e) {
        final MyAboutDialog myAboutDialog1 = new MyAboutDialog(this);
        myAboutDialog1.setTitle(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("FrameTitle"));
        myAboutDialog1.setAppName(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("FrameTitle"));
        myAboutDialog1.setVersion("4.2");
        myAboutDialog1.setYear("2010");
        myAboutDialog1.setVisible(true);
    }
    
    private void determinant() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        if (!mat.isSquare()) {
            this.showmessage(String.valueOf(mat.getTitle()) + " " + ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("isNotSquareMatrix"));
            return;
        }
        final String str = "|" + mat.getTitle() + "|";
        final VisibleFracMatrix matnew = this.createMatrix(mat, 0, 0, str);
        matnew.determinant();
    }
    
    private void rank() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        final String str = String.valueOf(mat.getTitle()) + ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("rank");
        final VisibleFracMatrix matnew = this.createMatrix(mat, 0, 1, str);
        matnew.rank();
    }
    
    private void solveLEqu() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        final String str = String.valueOf(ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString("LinearEq(")) + mat.getTitle() + ResourceBundle.getBundle("org.namekata.matrixExercise/MatrixExerciseFrame").getString(")LinearEq");
        final VisibleFracMatrix matnew = this.createMatrix(mat, 0, 0, str);
        matnew.solveLinearEq();
        final int jj = 21;
        final String[] colNames = new String[jj];
        colNames[0] = "  ";
        for (int j = 1; j < jj - 1; ++j) {
            colNames[j] = "x" + Integer.toString(j);
        }
        colNames[matnew.getColumnCount()] = "1";
        matnew.setColumnNames(colNames);
    }
    
    void jButtonDeterminant_actionPerformed(final ActionEvent e) {
        this.determinant();
    }
    
    void jButtonRank_actionPerformed(final ActionEvent e) {
        this.rank();
    }
    
    void jButtonLEqu_actionPerformed(final ActionEvent e) {
        this.solveLEqu();
    }
    
    void jButtonPivot_actionPerformed(final ActionEvent e) {
        this.pivot();
    }
    
    private void columnVectorOrthogonalize() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        String str;
        char[] ch;
        int ij;
        for (str = mat.getTitle(), ch = str.toCharArray(), ij = 0; ij < str.length() && ch[ij] != '-'; ++ij) {}
        str = str.substring(0, ij);
        final VisibleFracMatrix matnew = this.jCheckBoxNewWindow.isSelected() ? this.createMatrix(mat, 0, 0, String.valueOf(str) + "-" + Integer.toString(++this.count)) : mat;
        matnew.columnVectorOrthogonalize();
    }
    
    private void pivot() {
        final VisibleFracMatrix mat = this.jListA.getSelectedValue();
        if (!mat.isPivotSelected()) {
            this.showmessage("Pivot is not selected!");
            return;
        }
        String str;
        char[] ch;
        int ij;
        for (str = mat.getTitle(), ch = str.toCharArray(), ij = 0; ij < str.length() && ch[ij] != '-'; ++ij) {}
        str = str.substring(0, ij);
        final VisibleFracMatrix matnew = this.jCheckBoxNewWindow.isSelected() ? this.createMatrix(mat, 0, 0, String.valueOf(str) + "-" + Integer.toString(++this.count)) : mat;
        matnew.pivot();
    }
    
    void this_windowActivated(final WindowEvent e) {
        this.isActivated = true;
    }
    
    void this_windowDeactivated(final WindowEvent e) {
        this.isActivated = false;
    }
    
    private JButton getJButtonOrthogonalize() {
        if (this.jButtonOrthogonalize == null) {
            (this.jButtonOrthogonalize = new JButton()).setText("\u5217\u30d9\u30af\u30c8\u30eb\u306e\u76f4\u4ea4\u5316");
            this.jButtonOrthogonalize.setEnabled(false);
            this.jButtonOrthogonalize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    MatrixExerciseFrame.this.columnVectorOrthogonalize();
                }
            });
        }
        return this.jButtonOrthogonalize;
    }
    
    private JToolBar.Separator getSeparator1() {
        if (this.separator1 == null) {
            this.separator1 = new JToolBar.Separator();
        }
        return this.separator1;
    }
    
    private JToolBar.Separator getSeparator() {
        if (this.separator == null) {
            this.separator = new JToolBar.Separator();
        }
        return this.separator;
    }
    
    private JToolBar.Separator getSeparator2() {
        if (this.separator2 == null) {
            this.separator2 = new JToolBar.Separator();
        }
        return this.separator2;
    }
}
