// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class FracMatrix implements MyOrder
{
    public static final int rowmax = 20;
    public static final int columnmax = 20;
    private Frac[][] data;
    private int rows;
    private int columns;
    private Frac fValue;
    private int iValue;
    
    public FracMatrix() {
        this.data = new Frac[20][20];
        this.fValue = Frac.Zero;
        this.iValue = 0;
        this.init(4, 4);
    }
    
    public FracMatrix(final int row, final int col) {
        this.data = new Frac[20][20];
        this.fValue = Frac.Zero;
        this.iValue = 0;
        this.init(row, col);
    }
    
    public FracMatrix(final FracMatrix x) {
        this.data = new Frac[20][20];
        this.fValue = Frac.Zero;
        this.iValue = 0;
        this.init(x.rows, x.columns);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.data[i][j] = x.data[i][j];
            }
        }
    }
    
    public FracMatrix(final Frac[][] f) {
        this.data = new Frac[20][20];
        this.fValue = Frac.Zero;
        this.iValue = 0;
        final int r = f.length;
        int c = 0;
        for (int i = 0; i < r; ++i) {
            c = Math.max(c, f[i].length);
        }
        this.init(r, c);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < f[i].length; ++j) {
                this.data[i][j] = f[i][j];
            }
        }
    }
    
    public Frac getFValue() {
        return this.fValue;
    }
    
    public int getIValue() {
        return this.iValue;
    }
    
    public boolean isSquare() {
        return this.rows == this.columns;
    }
    
    public boolean isStochastic() {
        if (!this.isSquare()) {
            return false;
        }
        for (int j = 0; j < this.columns; ++j) {
            Frac sum = Frac.Zero;
            for (int i = 0; i < this.rows; ++i) {
                sum = sum.add(this.data[i][j]);
            }
            if (!sum.equals(Frac.Unit)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object x) {
        return x != null && x.getClass() == FracMatrix.class && this.equals((FracMatrix)x);
    }
    
    public boolean equals(final FracMatrix x) {
        if (x == null) {
            return false;
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            return false;
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (!this.data[i][j].equals(x.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean isGreater(final Object x) {
        return x != null && x.getClass() == FracMatrix.class && this.isGreater((FracMatrix)x);
    }
    
    public boolean isGreater(final FracMatrix x) {
        if (x == null) {
            return false;
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            return false;
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (!this.data[i][j].isGreater(x.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isEqGreater(final FracMatrix x) {
        if (x == null) {
            return false;
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            return false;
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (this.data[i][j].isLess(x.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isEqLess(final FracMatrix x) {
        if (x == null) {
            return false;
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            return false;
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (this.data[i][j].isGreater(x.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean isLess(final Object x) {
        return x != null && x.getClass() == FracMatrix.class && this.isLess((FracMatrix)x);
    }
    
    public boolean isLess(final FracMatrix x) {
        if (x == null) {
            return false;
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            return false;
        }
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (!this.data[i][j].isLess(x.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void init(int row, int col) {
        if (row > 20) {
            row = 20;
        }
        else if (row <= 0) {
            row = 1;
        }
        this.rows = row;
        if (col > 20) {
            col = 20;
        }
        else if (col <= 0) {
            col = 1;
        }
        this.columns = col;
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.data[i][j] = Frac.Zero;
            }
        }
    }
    
    public boolean setRowCount(final int row) {
        if (row <= 0 || row > 20) {
            return false;
        }
        if (row == this.rows) {
            return false;
        }
        this.rows = row;
        return true;
    }
    
    public boolean setColumnCount(final int col) {
        if (col <= 0 || col > 20) {
            return false;
        }
        if (col == this.columns) {
            return false;
        }
        this.columns = col;
        return true;
    }
    
    public void setValue(final Object value, final int row, final int col) {
        this.data[row][col] = (Frac)value;
    }
    
    public int getRowCount() {
        return this.rows;
    }
    
    public int getColumnCount() {
        return this.columns;
    }
    
    public Frac[][] getData() {
        return this.data;
    }
    
    public FracMatrix add(final FracMatrix x) throws NullFracMatrixException, SumCannotComputedException {
        if (x == null) {
            throw new NullFracMatrixException();
        }
        if (this.rows != x.rows || this.columns != x.columns) {
            throw new SumCannotComputedException();
        }
        final FracMatrix retVal = new FracMatrix(this);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                retVal.data[i][j] = this.data[i][j].add(x.data[i][j]);
            }
        }
        return retVal;
    }
    
    public FracMatrix mul(final FracMatrix x) throws NullFracMatrixException, ProductCannotComputedException {
        if (x == null) {
            throw new NullFracMatrixException();
        }
        if (this.columns != x.rows) {
            throw new ProductCannotComputedException();
        }
        final FracMatrix retVal = new FracMatrix(this.rows, x.columns);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < x.columns; ++j) {
                Frac sum = Frac.Zero;
                for (int k = 0; k < this.columns; ++k) {
                    Frac f = this.data[i][k];
                    f = f.mul(x.data[k][j]);
                    sum = sum.add(f);
                }
                retVal.data[i][j] = sum;
            }
        }
        return retVal;
    }
    
    public FracMatrix determinant() throws NotSquareMatrixException {
        if (!this.isSquare()) {
            throw new NotSquareMatrixException();
        }
        final FracMatrix retVal = new FracMatrix(this);
        retVal.fValue = retVal.computeDeterminant();
        return retVal;
    }
    
    public void exchange(final int from, final int to, final boolean row) {
        if (from == to || from < 0 || to < 0) {
            return;
        }
        int rangeMax;
        if (row) {
            if (from >= this.rows || to >= this.rows) {
                return;
            }
            rangeMax = this.columns;
        }
        else {
            if (from >= this.columns || to >= this.columns) {
                return;
            }
            rangeMax = this.rows;
        }
        for (int i = 0; i < rangeMax; ++i) {
            int ii;
            int iii;
            int jj;
            int jjj;
            if (row) {
                ii = from;
                iii = to;
                jj = i;
                jjj = i;
            }
            else {
                jj = from;
                jjj = to;
                ii = i;
                iii = i;
            }
            final Frac tmp = this.data[ii][jj];
            this.data[ii][jj] = this.data[iii][jjj];
            this.data[iii][jjj] = tmp;
        }
    }
    
    public void mulrowcolumns(final int to, final Frac f, final boolean row) {
        if (to < 0 || f.equals(1L)) {
            return;
        }
        int rangeMax;
        if (row) {
            if (to >= this.rows) {
                return;
            }
            rangeMax = this.columns;
        }
        else {
            if (to >= this.columns) {
                return;
            }
            rangeMax = this.rows;
        }
        for (int i = 0; i < rangeMax; ++i) {
            int ii;
            int jj;
            if (row) {
                ii = to;
                jj = i;
            }
            else {
                ii = i;
                jj = to;
            }
            this.data[ii][jj] = f.mul(this.data[ii][jj]);
        }
    }
    
    public void fromtomul(final int from, final int to, final Frac f, final boolean row) {
        if (from == to || from < 0 || to < 0 || f.equals(0L)) {
            return;
        }
        int rangeMax;
        if (row) {
            if (from >= this.rows) {
                return;
            }
            rangeMax = this.columns;
        }
        else {
            if (from >= this.columns) {
                return;
            }
            rangeMax = this.rows;
        }
        for (int i = 0; i < rangeMax; ++i) {
            int ii;
            int iii;
            int jj;
            int jjj;
            if (row) {
                ii = from;
                iii = to;
                jj = i;
                jjj = i;
            }
            else {
                ii = i;
                iii = i;
                jj = from;
                jjj = to;
            }
            this.data[iii][jjj] = this.data[iii][jjj].add(f.mul(this.data[ii][jj]));
        }
    }
    
    private Frac computeDeterminant() {
        for (int j = 0; j < this.columns; ++j) {
            if (this.data[j][j].equals(0L)) {
                int ii;
                for (ii = j + 1; ii < this.rows && this.data[ii][j].equals(0L); ++ii) {}
                if (ii == this.rows) {
                    continue;
                }
                this.exchange(j, ii, true);
                this.mulrowcolumns(j, new Frac(-1L), true);
            }
            for (int k = j + 1; k < this.rows; ++k) {
                final Frac f = this.data[k][j].div(this.data[j][j]);
                this.fromtomul(j, k, f.mul(-1L), true);
            }
        }
        Frac ret = Frac.Unit;
        for (int i = 0; i < this.rows; ++i) {
            ret = ret.mul(this.data[i][i]);
        }
        return ret;
    }
    
    public FracMatrix rank() {
        final FracMatrix tmp = new FracMatrix(this);
        tmp.iValue = tmp.maketriangular(false);
        return tmp;
    }
    
    private int maketriangular(final boolean unit) {
        int retVal = 0;
        final int ii = this.rows;
        final int jj = this.columns;
        for (int i = 0, j = 0; i < this.rows && j < this.columns; ++j) {
            int k;
            for (k = i; k < this.rows && this.data[k][j].equals(0L); ++k) {}
            if (k != this.rows) {
                if (k != i) {
                    this.exchange(k, i, true);
                }
                final Frac tmp = this.data[i][j];
                ++retVal;
                final int type = unit ? 1 : 2;
                switch (type) {
                    case 1: {
                        this.mulrowcolumns(i, Frac.Unit.div(tmp), true);
                        for (int ij = 0; ij < this.rows; ++ij) {
                            if (ij != i) {
                                final Frac f = this.data[ij][j];
                                if (!f.equals(0L)) {
                                    this.fromtomul(i, ij, Frac.Zero.sub(f), true);
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        for (int ij = i + 1; ij < this.rows; ++ij) {
                            final Frac f = this.data[ij][j].div(tmp);
                            if (!f.equals(0L)) {
                                this.fromtomul(i, ij, Frac.Zero.sub(f), true);
                            }
                        }
                        break;
                    }
                }
                ++i;
            }
        }
        return retVal;
    }
    
    public FracMatrix solveLinearEq() {
        final FracMatrix ret = new FracMatrix(this);
        ret.maketriangular(true);
        for (int i = 0; i < this.rows; ++i) {
            Frac f = Frac.Zero;
            for (int j = 0; j < this.columns - 1; ++j) {
                f = f.add(ret.data[i][j]);
            }
            if (f.equals(0L) && !ret.data[i][this.columns - 1].equals(0L)) {
                ret.iValue = 0;
                return ret;
            }
        }
        ret.iValue = 1;
        return ret;
    }
    
    public FracMatrix characteristicFunction() {
        final FracMatrix ret = new FracMatrix(this);
        ret.charfun();
        return ret;
    }
    
    private void charfun() {
        for (int j = 0; j < this.columns - 1; ++j) {
            this.charfun1(j);
        }
    }
    
    private void charfun1(final int col) {
        if (col < 0 || col >= this.columns - 1) {
            return;
        }
        int i;
        for (i = col + 1; i < this.rows && this.data[i][col].equals(0L); ++i) {}
        if (i == this.rows) {
            return;
        }
        this.exchange(i, col + 1, true);
        this.exchange(i, col + 1, false);
        final Frac tmp = this.data[col + 1][col];
        if (!tmp.equals(1L)) {
            final Frac f = Frac.Unit.div(tmp);
            this.mulrowcolumns(col + 1, f, true);
            this.mulrowcolumns(col + 1, tmp, false);
        }
        for (int ij = 0; ij < this.rows; ++ij) {
            if (ij != col + 1) {
                final Frac f2 = this.data[ij][col];
                if (!f2.equals(0L)) {
                    final Frac f3 = Frac.Zero.sub(f2);
                    this.fromtomul(col + 1, ij, f3, true);
                    this.fromtomul(ij, col + 1, f2, false);
                }
            }
        }
    }
    
    public void pivot(final int row, final int col) {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.columns) {
            return;
        }
        final Frac f = this.data[row][col];
        if (f.equals(0L)) {
            return;
        }
        if (!f.equals(1L)) {
            this.mulrowcolumns(row, Frac.Unit.div(f), true);
        }
        for (int i = 0; i < this.rows; ++i) {
            if (i != row) {
                Frac mul = this.data[i][col];
                if (!mul.equals(0L)) {
                    mul = Frac.Zero.sub(mul);
                    this.fromtomul(row, i, mul, true);
                }
            }
        }
    }
    
    public void columnVectorOrthogonalize() {
        final FracMatrix ret = new FracMatrix(this);
        for (int j = 1; j < this.columns; ++j) {
            Frac innerproduct = Frac.Zero;
            for (int k = 0; k < this.rows; ++k) {
                innerproduct = innerproduct.add(this.data[k][j - 1].mul(this.data[k][j - 1]));
            }
            if (!innerproduct.equals(Frac.Zero)) {
                for (int i = j; i < this.columns; ++i) {
                    Frac innerproduct2 = Frac.Zero;
                    for (int l = 0; l < this.rows; ++l) {
                        innerproduct2 = innerproduct2.add(ret.data[l][i].mul(this.data[l][j - 1]));
                    }
                    for (int l = 0; l < this.rows; ++l) {
                        Frac temp = this.data[l][j - 1];
                        temp = temp.mul(innerproduct2);
                        temp = temp.div(innerproduct);
                        this.data[l][i] = this.data[l][i].sub(temp);
                    }
                }
            }
        }
    }
    
    public FracMatrix inverse() throws NotSquareMatrixException, InverseNotExistException {
        if (!this.isSquare()) {
            throw new NotSquareMatrixException();
        }
        final FracMatrix ret = new FracMatrix(this.rows, this.rows);
        for (int i = 0; i < this.rows; ++i) {
            ret.data[i][i] = Frac.Unit;
        }
        if (!this.inverse1(ret, new FracMatrix(this))) {
            throw new InverseNotExistException();
        }
        return ret;
    }
    
    private boolean inverse1(final FracMatrix ret, final FracMatrix input) {
        for (int i = 0; i < this.rows; ++i) {
            if (input.data[i][i].equals(0L)) {
                int ii;
                for (ii = i + 1; ii < this.rows && input.data[ii][i].equals(0L); ++ii) {}
                if (ii == this.rows) {
                    return false;
                }
                input.exchange(i, ii, true);
                ret.exchange(i, ii, true);
            }
            Frac f = Frac.Unit.div(input.data[i][i]);
            input.mulrowcolumns(i, f, true);
            ret.mulrowcolumns(i, f, true);
            for (int k = 0; k < this.rows; ++k) {
                if (k != i) {
                    f = Frac.Zero.sub(input.data[k][i]);
                    ret.fromtomul(i, k, f, true);
                    input.fromtomul(i, k, f, true);
                }
            }
        }
        return true;
    }
    
    public FracMatrix minus() {
        final FracMatrix retVal = new FracMatrix(this);
        final Frac f = new Frac(-1L);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                retVal.data[i][j] = this.data[i][j].mul(f);
            }
        }
        return retVal;
    }
    
    public FracMatrix sub(final FracMatrix x) throws NullFracMatrixException {
        if (x == null) {
            throw new NullFracMatrixException();
        }
        return this.sub(x.minus());
    }
    
    @Override
    public String toString() {
        String retVal = "(";
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                if (j != 0) {
                    retVal = String.valueOf(retVal) + ", ";
                }
                retVal = String.valueOf(retVal) + this.data[i][j].toString();
                if (j == this.columns - 1) {
                    if (i == this.rows - 1) {
                        retVal = String.valueOf(retVal) + ")";
                    }
                    retVal = String.valueOf(retVal) + "\n";
                }
            }
        }
        return retVal;
    }
    
    public class ProductCannotComputedException extends Exception
    {
        ProductCannotComputedException() {
            super("FracMatrix: Cannot compute produce ot matrices!");
        }
        
        ProductCannotComputedException(final String s) {
            super(s);
        }
    }
    
    public class NullFracMatrixException extends Exception
    {
        NullFracMatrixException() {
            super("FracMatrix: NullMatrix assigned!");
        }
        
        NullFracMatrixException(final String s) {
            super(s);
        }
    }
    
    public class SumCannotComputedException extends Exception
    {
        SumCannotComputedException() {
            super("FracMatrix: Cannot compute sum of matrices!");
        }
        
        SumCannotComputedException(final String s) {
            super(s);
        }
    }
    
    public class NotSquareMatrixException extends Exception
    {
        NotSquareMatrixException() {
            super("FracMatrix: Not a square matrix");
        }
        
        NotSquareMatrixException(final String s) {
            super(s);
        }
    }
    
    public class InverseNotExistException extends Exception
    {
        InverseNotExistException() {
            super("FracMatrix: Inverse does not exist!");
        }
        
        InverseNotExistException(final String s) {
            super(s);
        }
    }
}
