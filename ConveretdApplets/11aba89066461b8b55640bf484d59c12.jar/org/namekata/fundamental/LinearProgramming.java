// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.table.AbstractTableModel;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class LinearProgramming extends JPanel
{
    public static final int LessEqual = 0;
    public static final int Equal = 1;
    public static final int GreaterEqual = 2;
    public static final int Solved = 0;
    public static final int Unbounded = -1;
    public static final int Infeasible = -2;
    private static final Frac fMaxValue;
    private int[] colOfBases;
    private int[] colOfNonbases;
    private int numOfNonbases;
    private int rowOfObjective;
    private int colOfB;
    private int[] dualVar;
    private Object[][] data;
    private String[] columnNames;
    private BorderLayout borderLayout1;
    private MyTableModel myTableModel;
    private JPanel jPanel1;
    private JCheckBox jCheckBoxStep;
    private JButton jButtonSolve;
    private JPanel jPanel2;
    private MyTable jTable1;
    private JScrollPane jScrollPane1;
    private BorderLayout borderLayout2;
    private JLabel statusBar;
    private FracCellRenderer fracCellRenderer;
    private int pivot1;
    private int pivot2;
    private int solution;
    private boolean shadow;
    private int format;
    
    static {
        fMaxValue = new Frac(1000000L);
    }
    
    public LinearProgramming() {
        this.colOfBases = null;
        this.colOfNonbases = null;
        this.numOfNonbases = 0;
        this.rowOfObjective = 0;
        this.colOfB = 0;
        this.dualVar = null;
        this.data = new Object[][] { { Frac.Zero } };
        this.columnNames = new String[] { "1" };
        this.borderLayout1 = new BorderLayout();
        this.myTableModel = new MyTableModel();
        this.jPanel1 = new JPanel();
        this.jCheckBoxStep = new JCheckBox();
        this.jButtonSolve = new JButton();
        this.jPanel2 = new JPanel();
        this.jTable1 = new MyTable();
        this.jScrollPane1 = new JScrollPane();
        this.borderLayout2 = new BorderLayout();
        this.statusBar = new JLabel();
        this.fracCellRenderer = new FracCellRenderer();
        this.shadow = false;
        this.format = 0;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public LinearProgramming(final boolean shadow) {
        this.colOfBases = null;
        this.colOfNonbases = null;
        this.numOfNonbases = 0;
        this.rowOfObjective = 0;
        this.colOfB = 0;
        this.dualVar = null;
        this.data = new Object[][] { { Frac.Zero } };
        this.columnNames = new String[] { "1" };
        this.borderLayout1 = new BorderLayout();
        this.myTableModel = new MyTableModel();
        this.jPanel1 = new JPanel();
        this.jCheckBoxStep = new JCheckBox();
        this.jButtonSolve = new JButton();
        this.jPanel2 = new JPanel();
        this.jTable1 = new MyTable();
        this.jScrollPane1 = new JScrollPane();
        this.borderLayout2 = new BorderLayout();
        this.statusBar = new JLabel();
        this.fracCellRenderer = new FracCellRenderer();
        this.shadow = false;
        this.format = 0;
        this.shadow = shadow;
        this.jTable1 = null;
        this.jCheckBoxStep.setSelected(!shadow);
    }
    
    public void setLP(final Object[][] coefB, final int numOfOriVariables, final boolean[] nonnegative, final int numOfEq, final int[] lessEqGreater) {
        int min = Math.min(coefB.length, numOfEq + 1);
        final int rows;
        min = (rows = Math.min(min, lessEqGreater.length + 1));
        final Object[] tmp = coefB[0];
        min = Math.min(tmp.length, numOfOriVariables + 1);
        final int columns;
        min = (columns = Math.min(min, nonnegative.length + 1));
        int col = 0;
        for (int j = 0; j < columns - 1; ++j) {
            if (nonnegative[j]) {
                ++col;
            }
            else {
                col += 2;
            }
        }
        this.numOfNonbases = col;
        final boolean[] isNonbasis = new boolean[this.numOfNonbases];
        for (int i = 0; i < this.numOfNonbases; ++i) {
            isNonbasis[i] = true;
        }
        this.colOfB = 0;
        for (int i = 0; i < rows - 1; ++i) {
            switch (lessEqGreater[i]) {
                case 0: {
                    ++this.colOfB;
                }
                case 2: {
                    ++this.colOfB;
                    break;
                }
            }
        }
        this.colOfB += this.numOfNonbases;
        this.rowOfObjective = rows - 1;
        this.dualVar = new int[this.rowOfObjective];
        this.data = new Object[this.rowOfObjective + 1][this.colOfB + 1];
        this.colOfBases = new int[this.rowOfObjective];
        this.colOfNonbases = new int[this.numOfNonbases];
        for (int i = 0; i < this.numOfNonbases; ++i) {
            this.colOfNonbases[i] = -1;
        }
        this.columnNames = new String[this.colOfB + 1];
        col = 0;
        int variable = 0;
        for (int k = 0; k < columns - 1; ++k) {
            if (nonnegative[k]) {
                for (int l = 0; l < rows; ++l) {
                    this.data[l][col] = coefB[l][k];
                }
                this.columnNames[col] = "x" + ++variable;
                ++col;
            }
            else {
                for (int l = 0; l < rows; ++l) {
                    this.data[l][col] = coefB[l][k];
                    this.data[l][col + 1] = Frac.Zero.sub((Frac)coefB[l][k]);
                }
                this.columnNames[col] = "(x" + ++variable + "+)";
                this.columnNames[col + 1] = "(x" + variable + "-)";
                col += 2;
            }
        }
        int indBasis = 0;
        final int indenterexit = 0;
        for (int m = 0; m < rows - 1; ++m) {
            switch (lessEqGreater[m]) {
                case 0: {
                    for (int ii = 0; ii < rows; ++ii) {
                        if (ii == m) {
                            this.data[ii][col] = Frac.Unit;
                        }
                        else {
                            this.data[ii][col] = Frac.Zero;
                        }
                    }
                    this.columnNames[col] = "SL" + (indBasis + 1);
                    this.colOfBases[m] = col;
                    this.dualVar[m] = col;
                    ++col;
                    ++indBasis;
                    break;
                }
                case 1: {
                    this.dualVar[m] = -1;
                    break;
                }
                case 2: {
                    for (int ii = 0; ii < rows; ++ii) {
                        if (ii == m) {
                            this.data[ii][col] = Frac.Zero.sub(Frac.Unit);
                        }
                        else {
                            this.data[ii][col] = Frac.Zero;
                        }
                    }
                    this.columnNames[col] = "SU" + (indBasis + 1);
                    this.colOfBases[m] = col;
                    this.dualVar[m] = col;
                    ++col;
                    ++indBasis;
                    break;
                }
            }
        }
        for (int m = 0; m < rows; ++m) {
            this.data[m][this.colOfB] = coefB[m][columns - 1];
        }
        this.columnNames[this.colOfB] = "1";
        for (int m = 0; m < rows - 1; ++m) {
            final int type = lessEqGreater[m];
            if (type == 2) {
                for (int j2 = 0; j2 <= this.colOfB; ++j2) {
                    this.data[m][j2] = Frac.Zero.sub((Frac)this.data[m][j2]);
                }
            }
        }
        for (int m = 0; m < rows - 1; ++m) {
            if (lessEqGreater[m] == 1) {
                boolean nonzero = false;
                for (int j2 = 0; j2 < this.numOfNonbases; ++j2) {
                    if (!((Frac)this.data[m][j2]).equals(0L)) {
                        nonzero = true;
                        this.pivot(j2, m);
                        isNonbasis[j2] = false;
                        break;
                    }
                }
                if (!nonzero && !((Frac)this.data[m][this.colOfB]).equals(0L)) {
                    this.solution = -2;
                    this.myTableModel.fireTableStructureChanged();
                    this.jButtonSolve.setEnabled(false);
                    this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Infeasible"));
                    return;
                }
            }
        }
        int indNonbases = 0;
        for (int j3 = 0; j3 < this.numOfNonbases; ++j3) {
            if (isNonbasis[j3]) {
                this.colOfNonbases[indNonbases++] = j3;
            }
        }
        this.numOfNonbases = indNonbases;
        this.myTableModel.fireTableStructureChanged();
        String msg;
        if (this.isFeasible()) {
            if (this.isOptimal()) {
                msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Optimal")) + Frac.Zero.sub((Frac)this.myTableModel.getValueAt(this.rowOfObjective, this.colOfB)).toString();
                this.solution = 0;
            }
            else {
                final int j2 = this.nonBasisToBasis();
                final int i2 = this.basisToNonbasis(j2);
                if (i2 == -1) {
                    msg = ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Unbounded");
                    this.solution = -1;
                }
                else {
                    this.pivot1 = i2;
                    this.pivot2 = j2;
                    msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("FeasibleSolve")) + (i2 + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("row") + (j2 + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("column");
                    this.solution = 1;
                }
            }
        }
        else {
            final int j2 = this.makeFeasibleEnter()[0];
            if (j2 == -2) {
                msg = ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Infeasible");
                this.solution = -2;
            }
            else {
                final int i2 = this.makeFeasibleExit(j2);
                this.pivot1 = i2;
                this.pivot2 = j2;
                msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("MakeFeasible")) + (i2 + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("row") + (j2 + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("column");
                this.solution = 2;
            }
        }
        if (this.solution >= 1) {
            this.jButtonSolve.setEnabled(true);
        }
        else {
            this.jButtonSolve.setEnabled(false);
        }
        this.statusBar.setText(msg);
    }
    
    private int nonBasisToBasis() {
        int ret = -1;
        Frac max = Frac.Zero.sub(LinearProgramming.fMaxValue);
        for (int j = 0; j < this.numOfNonbases; ++j) {
            max = max.max((Frac)this.data[this.rowOfObjective][this.colOfNonbases[j]]);
        }
        if (!max.isGreater(Frac.Zero)) {
            return ret;
        }
        for (int j = 0; j < this.numOfNonbases; ++j) {
            ret = this.colOfNonbases[j];
            if (((Frac)this.data[this.rowOfObjective][ret]).equals(max)) {
                return ret;
            }
        }
        return ret;
    }
    
    private int basisToNonbasis(final int enter) {
        int ret = -1;
        Frac min = LinearProgramming.fMaxValue;
        final Frac[] temp = new Frac[this.rowOfObjective];
        for (int i = 0; i < this.rowOfObjective; ++i) {
            final Frac a = (Frac)this.data[i][enter];
            if (a.isGreater(Frac.Zero)) {
                final Frac tmp = ((Frac)this.data[i][this.colOfB]).div(a);
                min = min.min(tmp);
                temp[i] = tmp;
            }
            else {
                temp[i] = Frac.Zero.sub(Frac.Unit);
            }
        }
        if (min.equals(LinearProgramming.fMaxValue)) {
            return ret;
        }
        int count = 0;
        for (int j = 0; j < this.rowOfObjective; ++j) {
            if (temp[j].equals(min)) {
                ++count;
            }
        }
        if (count > 1) {
            count *= (int)Math.random();
        }
        else {
            count = 0;
        }
        for (int j = 0; j < this.rowOfObjective; ++j) {
            if (temp[j].equals(min)) {
                if (count == 0) {
                    ret = j;
                    return ret;
                }
                --count;
            }
        }
        return ret;
    }
    
    private void pivot(final int enter, final int exit) {
        Frac f = (Frac)this.data[exit][enter];
        if (f.equals(Frac.Zero)) {
            return;
        }
        if (!f.equals(Frac.Unit)) {
            for (int j = 0; j <= this.colOfB; ++j) {
                this.data[exit][j] = ((Frac)this.data[exit][j]).div(f);
            }
        }
        for (int i = 0; i <= this.rowOfObjective; ++i) {
            if (i != exit) {
                f = (Frac)this.data[i][enter];
                if (!f.equals(Frac.Zero)) {
                    for (int k = 0; k <= this.colOfB; ++k) {
                        this.data[i][k] = ((Frac)this.data[i][k]).sub(f.mul((Frac)this.data[exit][k]));
                    }
                }
            }
        }
        for (int j = 0; j < this.numOfNonbases; ++j) {
            if (this.colOfNonbases[j] == enter) {
                this.colOfNonbases[j] = this.colOfBases[exit];
            }
        }
        this.colOfBases[exit] = enter;
    }
    
    private boolean isOptimal() {
        return this.nonBasisToBasis() == -1;
    }
    
    private int[] makeFeasibleEnter() {
        final int[] enterk = new int[2];
        int enter = -2;
        boolean ret = true;
        int k;
        for (k = this.rowOfObjective - 1; k >= 0; --k) {
            final Frac f = (Frac)this.data[k][this.colOfB];
            if (f.isLess(Frac.Zero)) {
                ret = false;
                break;
            }
        }
        enterk[1] = k;
        if (ret) {
            enterk[0] = -1;
            return enterk;
        }
        for (int i = 0; i < this.colOfB; ++i) {
            final Frac f2 = (Frac)this.data[k][i];
            if (f2.isLess(Frac.Zero)) {
                enter = i;
                break;
            }
        }
        enterk[0] = enter;
        return enterk;
    }
    
    private int makeFeasibleExit(final int enter) {
        int exit = -1;
        Frac min = LinearProgramming.fMaxValue;
        int j;
        int k;
        for (k = (j = this.makeFeasibleEnter()[1]); j < this.rowOfObjective; ++j) {
            final Frac a = (Frac)this.data[j][enter];
            if (!a.equals(Frac.Zero)) {
                final Frac b = ((Frac)this.data[j][this.colOfB]).div(a);
                if (b.isGreater(Frac.Zero)) {
                    min = min.min(b);
                }
            }
        }
        if (!min.isLess(LinearProgramming.fMaxValue)) {
            return exit;
        }
        for (j = k; j < this.rowOfObjective; ++j) {
            final Frac a = (Frac)this.data[j][enter];
            if (!a.equals(Frac.Zero)) {
                final Frac b = ((Frac)this.data[j][this.colOfB]).div(a);
                if (b.isGreater(Frac.Zero) && min.equals(b)) {
                    exit = j;
                    break;
                }
            }
        }
        return exit;
    }
    
    private int makeFeasible() {
        this.pivot(this.pivot2, this.pivot1);
        final int enter = this.makeFeasibleEnter()[0];
        if (enter == -2) {
            this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Infeasible"));
            this.jButtonSolve.setEnabled(false);
            return this.solution = -2;
        }
        if (enter == -1) {
            if (this.isOptimal()) {
                this.jButtonSolve.setEnabled(false);
                final String msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Optimal")) + Frac.Zero.sub((Frac)this.myTableModel.getValueAt(this.rowOfObjective, this.colOfB)).toString();
                this.statusBar.setText(msg);
                return this.solution = 0;
            }
            final int j = this.nonBasisToBasis();
            final int i = this.basisToNonbasis(j);
            if (i == -1) {
                this.statusBar.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Unbounded"));
                return this.solution = -1;
            }
            this.pivot1 = i;
            this.pivot2 = j;
            this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("GotFeasibleSolve")) + (i + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("row") + (j + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("column"));
            return this.solution = 1;
        }
        else {
            final int exit = this.makeFeasibleExit(enter);
            this.pivot1 = exit;
            this.pivot2 = enter;
            if (this.jCheckBoxStep.isSelected()) {
                this.statusBar.setText(String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("MakeFeasible")) + (exit + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("row") + (enter + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("column"));
                return this.solution = 2;
            }
            return this.makeFeasible();
        }
    }
    
    private boolean isFeasible() {
        for (int i = 0; i < this.rowOfObjective; ++i) {
            if (((Frac)this.data[i][this.colOfB]).isLess(Frac.Zero)) {
                return false;
            }
        }
        return true;
    }
    
    public int solve() {
        if (this.solution < 1) {
            return this.solution;
        }
        if (this.jCheckBoxStep.isSelected()) {
            if (this.solution == 1) {
                return this.solve1();
            }
            return this.makeFeasible();
        }
        else {
            if (this.solution == 1) {
                return this.solve1();
            }
            if (this.makeFeasible() == -2) {
                return -2;
            }
            return this.solve1();
        }
    }
    
    private int solve1() {
        this.pivot(this.pivot2, this.pivot1);
        final int j = this.nonBasisToBasis();
        if (j == -1) {
            final String msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Optimal")) + Frac.Zero.sub((Frac)this.myTableModel.getValueAt(this.rowOfObjective, this.colOfB)).toString();
            this.statusBar.setText(msg);
            this.jButtonSolve.setEnabled(false);
            return this.solution = 0;
        }
        final int i = this.basisToNonbasis(j);
        if (i == -1) {
            final String msg = ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Unbounded");
            this.statusBar.setText(msg);
            this.jButtonSolve.setEnabled(false);
            return this.solution = -1;
        }
        this.pivot1 = i;
        this.pivot2 = j;
        this.solution = 1;
        if (this.jCheckBoxStep.isSelected()) {
            final String msg = String.valueOf(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("FeasibleSolve")) + (i + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("row") + (j + 1) + " " + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("column");
            this.statusBar.setText(msg);
            return 1;
        }
        return this.solve1();
    }
    
    public String getSolution() {
        String ret = "";
        final Frac[] variables = this.getVariables();
        for (int len = variables.length, i = 0; i < len; ++i) {
            ret = String.valueOf(ret) + variables[i].toString() + ((i == len - 1) ? "\n" : "\t");
        }
        ret = String.valueOf(ret) + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("maximum") + this.getObjectiveValue().toString() + "\n";
        return ret;
    }
    
    public boolean[] getActiveEq() {
        final boolean[] ret = new boolean[this.rowOfObjective];
        for (int i = 0; i < this.rowOfObjective; ++i) {
            final int j = this.dualVar[i];
            if (j == -1) {
                ret[i] = true;
            }
            else {
                final int ii = this.isBasis(j);
                if (ii != -1) {
                    ret[i] = ((Frac)this.data[ii][this.colOfB]).equals(0L);
                }
                else {
                    ret[i] = true;
                }
            }
        }
        return ret;
    }
    
    public Frac[] getVariables() {
        int numOfVar = 0;
        final int jj = this.colOfB + 1;
        for (int j = 0; j < jj; ++j) {
            final String str = this.columnNames[j];
            final String str2 = str.substring(0, 1);
            if (str2.equals("x")) {
                ++numOfVar;
            }
            else if (str2.equals("(")) {
                ++j;
                ++numOfVar;
            }
        }
        final Frac[] ret = new Frac[numOfVar];
        int ij = 0;
        for (int i = 0; i < jj; ++i) {
            final String str3 = this.columnNames[i];
            final String str4 = str3.substring(0, 1);
            if (str4.equals("x")) {
                final int k = this.isBasis(i);
                if (k != -1) {
                    ret[ij] = (Frac)this.data[k][this.colOfB];
                }
                else {
                    ret[ij] = Frac.Zero;
                }
                ++ij;
            }
            else if (str4.equals("(")) {
                int k = this.isBasis(i);
                if (k != -1) {
                    ret[ij] = (Frac)this.data[k][this.colOfB];
                }
                else {
                    ret[ij] = Frac.Zero;
                }
                ++i;
                k = this.isBasis(i);
                if (k != -1) {
                    ret[ij] = ret[ij].sub((Frac)this.data[k][this.colOfB]);
                }
                ++ij;
            }
        }
        return ret;
    }
    
    private int isBasis(final int col) {
        int ret = -1;
        boolean first = true;
        for (int i = 0; i < this.rowOfObjective; ++i) {
            final Frac f = (Frac)this.data[i][col];
            if (!f.equals(Frac.Zero)) {
                if (!first || !f.equals(Frac.Unit)) {
                    return -1;
                }
                ret = i;
                first = false;
            }
        }
        if (((Frac)this.data[this.rowOfObjective][col]).equals(0L)) {
            return ret;
        }
        return -1;
    }
    
    public Frac getObjectiveValue() {
        return Frac.Zero.sub((Frac)this.myTableModel.getValueAt(this.rowOfObjective, this.colOfB));
    }
    
    @Override
    public String toString() {
        String ret = ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("ResultOrProcess");
        final int ii = this.myTableModel.getRowCount();
        final int jj = this.myTableModel.getColumnCount();
        for (int j = 0; j < jj; ++j) {
            ret = String.valueOf(ret) + this.myTableModel.getColumnName(j) + ((j == jj - 1) ? "\n" : "\t");
        }
        for (int i = 0; i < ii; ++i) {
            for (int k = 0; k < jj; ++k) {
                ret = String.valueOf(ret) + this.myTableModel.getValueAt(i, k) + ((k == jj - 1) ? "\n" : "\t");
            }
        }
        ret = String.valueOf(ret) + "\n";
        if (this.solution == 0) {
            ret = String.valueOf(ret) + ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("ValueOfVarInMaximum") + this.getSolution();
        }
        else {
            ret = String.valueOf(ret) + this.statusBar.getText();
        }
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
    
    private void jbInit() throws Exception {
        this.jCheckBoxStep.setHorizontalAlignment(0);
        this.jCheckBoxStep.setSelected(true);
        this.jCheckBoxStep.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Step"));
        this.setLayout(this.borderLayout1);
        this.jButtonSolve.setText(ResourceBundle.getBundle("org.namekata.fundamental/LinearProgramming").getString("Solve"));
        this.jButtonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LinearProgramming.this.jButtonSolve_actionPerformed(e);
            }
        });
        this.jTable1.setAutoResizeMode(4);
        this.jTable1.setModel(this.myTableModel);
        this.jTable1.setRowSelectionAllowed(false);
        this.jTable1.setDefaultRenderer(Frac.class, this.fracCellRenderer);
        this.jPanel2.setLayout(this.borderLayout2);
        this.add(this.jPanel1, "North");
        this.jPanel1.add(this.jCheckBoxStep, null);
        this.jPanel1.add(this.jButtonSolve, null);
        this.add(this.jPanel2, "Center");
        this.jPanel2.add(this.jScrollPane1, "Center");
        this.jPanel2.add(this.statusBar, "South");
        this.jScrollPane1.getViewport().add(this.jTable1, null);
    }
    
    void jButtonSolve_actionPerformed(final ActionEvent e) {
        this.solve();
        this.jTable1.updateUI();
    }
    
    private class MyTableModel extends AbstractTableModel
    {
        @Override
        public boolean isCellEditable(final int row, final int col) {
            return true;
        }
        
        @Override
        public int getRowCount() {
            return LinearProgramming.this.rowOfObjective + 1;
        }
        
        @Override
        public int getColumnCount() {
            return LinearProgramming.this.colOfB + 1;
        }
        
        @Override
        public String getColumnName(final int col) {
            return LinearProgramming.this.columnNames[col];
        }
        
        @Override
        public Object getValueAt(final int row, final int col) {
            return LinearProgramming.this.data[row][col];
        }
        
        @Override
        public Class getColumnClass(final int c) {
            return this.getValueAt(0, c).getClass();
        }
    }
}
