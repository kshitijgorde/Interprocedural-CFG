// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import edu.hws.jcm.data.StackOfDouble;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Cases;

public class TableFunction extends FunctionParserExtension
{
    public static final int SMOOTH = 0;
    public static final int PIECEWISE_LINEAR = 1;
    public static final int STEP = 2;
    public static final int STEP_LEFT = 3;
    public static final int STEP_RIGHT = 4;
    private int style;
    private double[] xCoords;
    private double[] yCoords;
    private CubicSegment[] segments;
    private int pointCt;
    
    public TableFunction() {
        this(0);
    }
    
    public TableFunction(final int n) {
        this.xCoords = new double[10];
        this.yCoords = new double[10];
        this.style = this.style;
        if (this.style == 0) {
            this.segments = new CubicSegment[9];
        }
    }
    
    public void copyDataFrom(final TableFunction tableFunction) {
        this.xCoords = tableFunction.xCoords.clone();
        this.yCoords = tableFunction.yCoords.clone();
        this.style = -1;
        this.setStyle(tableFunction.style);
    }
    
    public void setStyle(final int style) {
        if (style == this.style || style < 0 || style > 4) {
            return;
        }
        if ((this.style = style) == 0) {
            this.segments = new CubicSegment[this.xCoords.length - 1];
            for (int i = 0; i < this.pointCt - 1; ++i) {
                this.segments[i] = new CubicSegment(this.xCoords[i], this.xCoords[i + 1], this.yCoords[i], this.yCoords[i + 1], 0.0, 0.0);
            }
            for (int j = 0; j < this.pointCt - 1; ++j) {
                this.interpolateDerivatives(j);
            }
        }
        else {
            this.segments = null;
        }
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public void addPoints(final double[] array, final double[] array2) {
        if (array == null) {
            return;
        }
        int n = array.length;
        if (array2 == null) {
            n = 0;
        }
        else if (array2.length < n) {
            n = array2.length;
        }
        for (int i = 0; i < n; ++i) {
            this.addPoint(array[i], array2[i]);
        }
        for (int j = n; j < array.length; ++j) {
            this.addPoint(array[j], 0.0);
        }
    }
    
    public void addIntervals(final int n, final double n2, final double n3) {
        if (n < 1) {
            return;
        }
        final double n4 = (n3 - n2) / n;
        for (int i = 0; i < n; ++i) {
            this.addPoint(n2 + i * n4, 0.0);
        }
        this.addPoint(n3, 0.0);
    }
    
    public int addPoint(final double n, final double n2) {
        if (Double.isNaN(n)) {
            return -1;
        }
        int n3;
        for (n3 = 0; n3 < this.pointCt && this.xCoords[n3] < n; ++n3) {}
        if (n3 < this.pointCt && this.xCoords[n3] == n) {
            this.yCoords[n3] = n2;
        }
        else {
            if (this.pointCt == this.xCoords.length) {
                final double[] xCoords = new double[2 * this.xCoords.length];
                System.arraycopy(this.xCoords, 0, xCoords, 0, this.xCoords.length);
                this.xCoords = xCoords;
                final double[] yCoords = new double[2 * this.yCoords.length];
                System.arraycopy(this.yCoords, 0, yCoords, 0, this.yCoords.length);
                this.yCoords = yCoords;
                if (this.style == 0) {
                    final CubicSegment[] segments = new CubicSegment[this.xCoords.length - 1];
                    System.arraycopy(this.segments, 0, segments, 0, this.pointCt - 1);
                    this.segments = segments;
                }
            }
            for (int i = this.pointCt; i > n3; --i) {
                this.xCoords[i] = this.xCoords[i - 1];
                this.yCoords[i] = this.yCoords[i - 1];
            }
            this.xCoords[n3] = n;
            this.yCoords[n3] = n2;
            if (this.style == 0 && this.pointCt > 0) {
                if (n3 == this.pointCt) {
                    this.segments[this.pointCt - 1] = new CubicSegment();
                }
                else {
                    for (int j = this.pointCt - 1; j > n3; --j) {
                        this.segments[j] = this.segments[j - 1];
                    }
                    this.segments[n3] = new CubicSegment();
                }
            }
            ++this.pointCt;
        }
        if (this.style == 0 && this.pointCt > 0) {
            if (n3 > 0) {
                this.segments[n3 - 1].setData(this.xCoords[n3 - 1], this.xCoords[n3], this.yCoords[n3 - 1], this.yCoords[n3], 0.0, 0.0);
            }
            if (n3 < this.pointCt - 1) {
                this.segments[n3].setData(this.xCoords[n3], this.xCoords[n3 + 1], this.yCoords[n3], this.yCoords[n3 + 1], 0.0, 0.0);
            }
            for (int k = n3 - 2; k <= n3 + 1; ++k) {
                this.interpolateDerivatives(k);
            }
        }
        return n3;
    }
    
    private void interpolateDerivatives(final int n) {
        if (n < 0 || n > this.pointCt - 2) {
            return;
        }
        if (this.pointCt == 2) {
            this.segments[0].setDerivativesFromNeighbors(Double.NaN, 0.0, Double.NaN, 0.0);
        }
        else if (n == 0) {
            this.segments[0].setDerivativesFromNeighbors(Double.NaN, 0.0, this.xCoords[2], this.yCoords[2]);
        }
        else if (n == this.pointCt - 2) {
            this.segments[this.pointCt - 2].setDerivativesFromNeighbors(this.xCoords[this.pointCt - 3], this.yCoords[this.pointCt - 3], Double.NaN, 0.0);
        }
        else {
            this.segments[n].setDerivativesFromNeighbors(this.xCoords[n - 1], this.yCoords[n - 1], this.xCoords[n + 2], this.yCoords[n + 2]);
        }
    }
    
    public int getPointCount() {
        return this.pointCt;
    }
    
    public double getX(final int n) {
        if (n >= 0 && n < this.pointCt) {
            return this.xCoords[n];
        }
        throw new IllegalArgumentException("Point index out of range: " + n);
    }
    
    public double getY(final int n) {
        if (n >= 0 && n < this.pointCt) {
            return this.yCoords[n];
        }
        throw new IllegalArgumentException("Point index out of range: " + n);
    }
    
    public void setY(final int n, final double n2) {
        if (n >= 0 && n < this.pointCt) {
            this.yCoords[n] = n2;
            if (this.style == 0) {
                if (n > 0) {
                    this.segments[n - 1].setData(this.xCoords[n - 1], this.xCoords[n], this.yCoords[n - 1], this.yCoords[n], 0.0, 0.0);
                }
                if (n < this.pointCt - 1) {
                    this.segments[n].setData(this.xCoords[n], this.xCoords[n + 1], this.yCoords[n], this.yCoords[n + 1], 0.0, 0.0);
                }
                for (int i = n - 2; i <= n + 1; ++i) {
                    this.interpolateDerivatives(i);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Point index out of range: " + n);
    }
    
    public int findPoint(final double n) {
        for (int i = 0; i < this.pointCt; ++i) {
            if (n == this.xCoords[i]) {
                return i;
            }
            if (n <= this.xCoords[i]) {
                break;
            }
        }
        return -1;
    }
    
    public void removePointAt(final int n) {
        if (n < 0 || n >= this.pointCt) {
            throw new IllegalArgumentException("Point index out of range: " + n);
        }
        --this.pointCt;
        for (int i = n; i < this.pointCt; ++i) {
            this.xCoords[i] = this.xCoords[i + 1];
            this.yCoords[i] = this.yCoords[i + 1];
        }
        if (this.style == 0) {
            this.style = -1;
            this.setStyle(0);
        }
    }
    
    public void removeAllPoints() {
        this.pointCt = 0;
        this.xCoords = new double[10];
        this.yCoords = new double[10];
    }
    
    public double getVal(final double n) {
        return this.computeValue(n, null, 0);
    }
    
    private double computeValue(final double n, final Cases cases, final int n2) {
        if (Double.isNaN(n)) {
            return Double.NaN;
        }
        if (this.pointCt == 0 || n < this.xCoords[0] || n > this.xCoords[this.pointCt - 1]) {
            return Double.NaN;
        }
        if (this.pointCt != 1) {
            int n3 = 0;
            int n4 = 0;
            double n5 = 0.0;
            switch (this.style) {
                case 2: {
                    while (n3 < this.pointCt - 1 && n > (this.xCoords[n3] + this.xCoords[n3 + 1]) / 2.0) {
                        ++n3;
                    }
                    n4 = n3;
                    if (n2 == 0) {
                        n5 = this.yCoords[n3];
                        break;
                    }
                    if (n < (this.xCoords[n3] + this.xCoords[n3 + 1]) / 2.0 || n3 == this.pointCt - 1 || this.yCoords[n3] == this.yCoords[n3 + 1]) {
                        n5 = 0.0;
                        break;
                    }
                    n5 = Double.NaN;
                    break;
                }
                case 4: {
                    while (n3 < this.pointCt - 1 && n > this.xCoords[n3]) {
                        ++n3;
                    }
                    n4 = n3;
                    if (n2 == 0) {
                        n5 = this.yCoords[n3];
                        break;
                    }
                    if (n < this.xCoords[n3] || n3 >= this.pointCt - 1 || this.yCoords[n3] == this.yCoords[n3 + 1]) {
                        n5 = 0.0;
                        break;
                    }
                    n5 = Double.NaN;
                    break;
                }
                case 3: {
                    while (n3 < this.pointCt - 1 && n >= this.xCoords[n3 + 1]) {
                        ++n3;
                    }
                    n4 = n3;
                    if (n2 == 0) {
                        n5 = this.yCoords[n3];
                        break;
                    }
                    if (n > this.xCoords[n3] || n3 == 0 || this.yCoords[n3] == this.yCoords[n3 - 1]) {
                        n5 = 0.0;
                        break;
                    }
                    n5 = Double.NaN;
                    break;
                }
                case 1: {
                    while (n3 < this.pointCt - 1 && n > this.xCoords[n3]) {
                        ++n3;
                    }
                    n4 = n3;
                    if (n == this.xCoords[n3]) {
                        if (n2 == 0) {
                            n5 = this.yCoords[n3];
                            break;
                        }
                        if (n3 == 0) {
                            if (n2 == 1) {
                                n5 = (this.yCoords[1] - this.yCoords[0]) / (this.xCoords[1] - this.xCoords[0]);
                                break;
                            }
                            n5 = 0.0;
                            break;
                        }
                        else if (n3 == this.pointCt - 1) {
                            if (n2 == 1) {
                                n5 = (this.yCoords[this.pointCt - 1] - this.yCoords[this.pointCt - 2]) / (this.xCoords[this.pointCt - 1] - this.xCoords[this.pointCt - 2]);
                                break;
                            }
                            n5 = 0.0;
                            break;
                        }
                        else {
                            final double n6 = (this.yCoords[n3] - this.yCoords[n3 - 1]) / (this.xCoords[n3] - this.xCoords[n3 - 1]);
                            if (Math.abs(n6 - (this.yCoords[n3] - this.yCoords[n3 + 1]) / (this.xCoords[n3] - this.xCoords[n3 + 1])) >= 1.0E-12) {
                                n5 = Double.NaN;
                                break;
                            }
                            if (n2 == 1) {
                                n5 = n6;
                                break;
                            }
                            n5 = 0.0;
                            break;
                        }
                    }
                    else {
                        if (n2 == 0) {
                            n5 = this.yCoords[n3 - 1] + (this.yCoords[n3] - this.yCoords[n3 - 1]) / (this.xCoords[n3] - this.xCoords[n3 - 1]) * (n - this.xCoords[n3 - 1]);
                            break;
                        }
                        if (n2 == 1) {
                            n5 = (this.yCoords[n3] - this.yCoords[n3 - 1]) / (this.xCoords[n3] - this.xCoords[n3 - 1]);
                            break;
                        }
                        n5 = 0.0;
                        break;
                    }
                    break;
                }
                default: {
                    while (n3 < this.pointCt - 2 && n > this.xCoords[n3 + 1]) {
                        ++n3;
                    }
                    n4 = n3;
                    if (n != this.xCoords[n3 + 1] || n3 >= this.pointCt - 2 || n3 <= 0) {
                        n5 = this.segments[n3].derivativeValue(n, n2);
                        break;
                    }
                    if (n2 == 0) {
                        n5 = this.yCoords[n3 + 1];
                        break;
                    }
                    if (n2 == 1) {
                        n5 = this.segments[n3].derivativeValue(n, 1);
                        break;
                    }
                    if (Math.abs(this.segments[n3 - 1].derivativeValue(n, 2) - this.segments[n3].derivativeValue(n, 2)) < 1.0E-12) {
                        n5 = this.segments[n3].derivativeValue(n, n2);
                        break;
                    }
                    n5 = Double.NaN;
                    break;
                }
            }
            if (cases != null) {
                cases.addCase(n4);
            }
            return n5;
        }
        if (n2 > 0) {
            return Double.NaN;
        }
        if (cases == null) {
            cases.addCase(0);
        }
        return this.yCoords[0];
    }
    
    public double getValueWithCases(final double[] array, final Cases cases) {
        return this.computeValue(array[0], cases, 0);
    }
    
    public double getVal(final double[] array) {
        return this.computeValue(array[0], null, 0);
    }
    
    public Function derivative(final int n) {
        if (n != 1) {
            throw new IllegalArgumentException("Attempt to take the derivative of a function of one argument with respect to argument number " + n);
        }
        return new Deriv(this);
    }
    
    public Function derivative(final Variable variable) {
        return null;
    }
    
    public boolean dependsOn(final Variable variable) {
        return false;
    }
    
    public int getArity() {
        return 1;
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        stackOfDouble.push(this.computeValue(stackOfDouble.pop(), cases, 0));
    }
    
    private static class Deriv extends FunctionParserExtension
    {
        TableFunction derivativeOf;
        int derivativeLevel;
        
        Deriv(final Deriv deriv) {
            this.derivativeLevel = deriv.derivativeLevel + 1;
            this.derivativeOf = deriv.derivativeOf;
        }
        
        Deriv(final TableFunction derivativeOf) {
            this.derivativeLevel = 1;
            this.derivativeOf = derivativeOf;
        }
        
        public String getName() {
            String s = this.derivativeOf.getName();
            for (int i = 0; i < this.derivativeLevel; ++i) {
                s = String.valueOf(s) + "'";
            }
            return s;
        }
        
        public void setName(final String s) {
        }
        
        public double getValueWithCases(final double[] array, final Cases cases) {
            return this.derivativeOf.computeValue(array[0], cases, this.derivativeLevel);
        }
        
        public double getVal(final double[] array) {
            return this.derivativeOf.computeValue(array[0], null, this.derivativeLevel);
        }
        
        public Function derivative(final int n) {
            if (n != 1) {
                throw new IllegalArgumentException("Attempt to take the derivative of a function of one argument with respect to argument number " + n);
            }
            return new Deriv(this);
        }
        
        public Function derivative(final Variable variable) {
            return null;
        }
        
        public boolean dependsOn(final Variable variable) {
            return false;
        }
        
        public int getArity() {
            return 1;
        }
        
        public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
            stackOfDouble.push(this.derivativeOf.computeValue(stackOfDouble.pop(), cases, this.derivativeLevel));
        }
    }
}
