// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import org.xidget.ifeature.tree.ITreeWidgetFeature;
import java.util.Collections;
import org.xmodel.BreadthFirstIterator;
import java.util.Collection;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.List;
import org.xidget.IXidget;
import org.xidget.ifeature.tree.IColumnWidthFeature;

public abstract class ColumnWidthFeature implements IColumnWidthFeature
{
    protected IXidget xidget;
    private ColumnStyle[] styles;
    private List<Integer[]> rows;
    private List<Integer>[] sorted;
    private int[] widest;
    private int[] sizes;
    private int width;
    
    protected ColumnWidthFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    public void configure(final IXidget xidget) {
        final int numberOfColumns = findNumberOfColumns(xidget);
        this.styles = new ColumnStyle[numberOfColumns];
        this.widest = new int[numberOfColumns];
        this.sizes = new int[numberOfColumns];
        this.sorted = (List<Integer>[])new List[numberOfColumns];
        this.rows = new ArrayList<Integer[]>();
        for (int i = 0; i < numberOfColumns; ++i) {
            this.sorted[i] = new ArrayList<Integer>();
        }
        final int maxWidth = this.getMaxWidth();
        final List<IModelObject> columnDeclarations = getColumnDeclarations(xidget);
        for (int j = 0; j < columnDeclarations.size(); ++j) {
            final IModelObject modelObject = columnDeclarations.get(j);
            IModelObject modelObject2 = modelObject.getFirstChild("width");
            if (modelObject2 == null) {
                modelObject2 = modelObject.getAttributeNode("width");
            }
            if (modelObject2 == null) {
                this.setFreeWidth(j, -1, -1, 0);
            }
            else {
                final String value = Xlate.get(modelObject2, "pad", (String)null);
                final int n = (value != null) ? parseConstraint(value, maxWidth) : 0;
                final String value2 = Xlate.get(modelObject2, "min", (String)null);
                final int n2 = (value2 != null) ? parseConstraint(value2, maxWidth) : -1;
                final String value3 = Xlate.get(modelObject2, "max", (String)null);
                final int n3 = (value3 != null) ? parseConstraint(value3, maxWidth) : -1;
                final String value4 = Xlate.get(modelObject2, "free");
                if (value4.equals("free")) {
                    this.setFreeWidth(j, n2, n3, n);
                }
                else if (value4.equals("auto")) {
                    this.setAutoWidth(j, n2, n3, n, false);
                }
                else if (value4.endsWith("%")) {
                    this.setRelativeWidth(j, n2, n3, Double.parseDouble(value4.substring(0, value4.length() - 1)) / 100.0, n, false);
                }
                else {
                    this.setAbsoluteWidth(j, parseConstraint(value4, maxWidth), n, false);
                }
            }
        }
        for (int k = columnDeclarations.size(); k < numberOfColumns; ++k) {
            this.setFreeWidth(k, -1, -1, 0);
        }
        this.insertRow(0);
    }
    
    private static List<IModelObject> getColumnDeclarations(final IXidget xidget) {
        final IModelObject config = xidget.getConfig();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        list.addAll((Collection<?>)config.getChildren("column"));
        final List<IModelObject> children = config.getChildren("cell");
        for (int i = 0; i < children.size(); ++i) {
            final IModelObject modelObject = children.get(i);
            if (i == list.size()) {
                list.add(modelObject);
            }
        }
        return list;
    }
    
    private static int findNumberOfColumns(final IXidget xidget) {
        int n = 0;
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(xidget.getConfig());
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (next.isType("table")) {
                final int numberOfChildren = next.getNumberOfChildren("cell");
                if (numberOfChildren <= n) {
                    continue;
                }
                n = numberOfChildren;
            }
        }
        return n;
    }
    
    private static int parseConstraint(String s, final int n) {
        final char char1 = s.charAt(s.length() - 1);
        if (char1 == 'c' || char1 == 'C') {
            s = s.substring(0, s.length() - 1);
            return Integer.parseInt(s) * n;
        }
        if (char1 == 'p' || char1 == 'P') {
            s = s.substring(0, s.length() - 1);
            return Integer.parseInt(s);
        }
        return Integer.parseInt(s);
    }
    
    @Override
    public final void insertColumn(final int n) {
        this.update();
    }
    
    @Override
    public final void removeColumn(final int n) {
        this.update();
    }
    
    @Override
    public final void setAbsoluteWidth(final int n, final int minimum, final int padding, final boolean b) {
        final ColumnStyle columnStyle = new ColumnStyle(null);
        columnStyle.mode = Mode.absolute;
        columnStyle.padding = padding;
        columnStyle.minimum = minimum;
        if (b) {
            final int maxWidth = this.getMaxWidth();
            final ColumnStyle columnStyle2 = columnStyle;
            columnStyle2.minimum *= maxWidth;
        }
        columnStyle.maximum = columnStyle.minimum;
        this.styles[n] = columnStyle;
    }
    
    @Override
    public final void setFreeWidth(final int n, final int minimum, final int maximum, final int padding) {
        final ColumnStyle columnStyle = new ColumnStyle(null);
        columnStyle.mode = Mode.free;
        columnStyle.minimum = minimum;
        columnStyle.maximum = maximum;
        columnStyle.padding = padding;
        this.styles[n] = columnStyle;
    }
    
    @Override
    public final void setRelativeWidth(final int n, final int minimum, final int maximum, final double relative, final int padding, final boolean b) {
        final ColumnStyle columnStyle = new ColumnStyle(null);
        columnStyle.mode = Mode.relative;
        columnStyle.padding = padding;
        columnStyle.minimum = minimum;
        columnStyle.maximum = maximum;
        if (b) {
            final int maxWidth = this.getMaxWidth();
            final ColumnStyle columnStyle2 = columnStyle;
            columnStyle2.minimum *= maxWidth;
            final ColumnStyle columnStyle3 = columnStyle;
            columnStyle3.maximum *= maxWidth;
        }
        columnStyle.relative = relative;
        this.styles[n] = columnStyle;
    }
    
    @Override
    public final void setAutoWidth(final int n, final int minimum, final int maximum, final int padding, final boolean b) {
        final ColumnStyle columnStyle = new ColumnStyle(null);
        columnStyle.mode = Mode.auto;
        columnStyle.padding = padding;
        columnStyle.minimum = minimum;
        columnStyle.maximum = maximum;
        if (b) {
            final int maxWidth = this.getMaxWidth();
            final ColumnStyle columnStyle2 = columnStyle;
            columnStyle2.minimum *= maxWidth;
            final ColumnStyle columnStyle3 = columnStyle;
            columnStyle3.maximum *= maxWidth;
        }
        this.styles[n] = columnStyle;
    }
    
    @Override
    public int getWidth(final int n) {
        return this.sizes[n];
    }
    
    @Override
    public boolean isResizeable(final int n) {
        final Mode mode = this.styles[n].mode;
        return mode == Mode.free || mode == Mode.absolute;
    }
    
    @Override
    public final void setTotalWidth(final int width) {
        this.width = width;
        this.update();
    }
    
    @Override
    public void setColumnTitle(final int n, final String s) {
        this.setColumnText(-1, n, s);
    }
    
    @Override
    public final void setColumnText(int n, final int n2, final String s) {
        ++n;
        final Integer[] array = this.rows.get(n);
        final int binarySearch = Collections.binarySearch(this.sorted[n2], array[n2]);
        if (binarySearch >= 0) {
            this.sorted[n2].remove(binarySearch);
        }
        final int textWidth = this.getTextWidth(s, n == 0);
        array[n2] = textWidth;
        int binarySearch2 = Collections.binarySearch(this.sorted[n2], textWidth);
        if (binarySearch2 < 0) {
            binarySearch2 = -binarySearch2 - 1;
        }
        this.sorted[n2].add(binarySearch2, textWidth);
        if (this.sorted[n2].size() > 0) {
            this.widest[n2] = this.sorted[n2].get(this.sorted[n2].size() - 1);
        }
        this.update();
    }
    
    @Override
    public final void insertRow(int n) {
        ++n;
        for (int i = this.rows.size(); i <= n; ++i) {
            final Integer[] array = new Integer[this.styles.length];
            for (int j = 0; j < array.length; ++j) {
                array[j] = 0;
            }
            this.rows.add(i, array);
        }
        for (int k = 0; k < this.sorted.length; ++k) {
            this.sorted[k].add(0, 0);
        }
    }
    
    @Override
    public final void removeRow(int n) {
        ++n;
        final Integer[] array = this.rows.get(n);
        for (int i = 0; i < this.styles.length; ++i) {
            final int intValue = array[i];
            final int binarySearch = Collections.binarySearch(this.sorted[i], intValue);
            if (intValue == this.widest[i]) {
                if (binarySearch > 0) {
                    this.widest[i] = this.sorted[i].get(binarySearch - 1);
                }
                else {
                    this.widest[i] = 0;
                }
            }
            this.sorted[i].remove(binarySearch);
        }
        this.rows.remove(n);
        this.update();
    }
    
    protected abstract int getMaxWidth();
    
    protected abstract int getTextWidth(final String p0, final boolean p1);
    
    private final void compute(final int[] array) {
        if (this.width == 0) {
            return;
        }
        double n = this.width;
        int length = this.styles.length;
        for (int i = 0; i < array.length; ++i) {
            if (this.styles[i].mode == Mode.absolute) {
                array[i] = this.styles[i].minimum + this.styles[i].padding;
                n -= array[i];
                --length;
            }
            else if (this.styles[i].mode == Mode.auto) {
                array[i] = constrain(this.styles[i], this.widest[i] + this.styles[i].padding);
                n -= array[i];
                --length;
            }
        }
        final double n2 = n;
        for (int j = 0; j < array.length; ++j) {
            if (this.styles[j].mode == Mode.relative) {
                array[j] = constrain(this.styles[j], n2 * this.styles[j].relative + this.styles[j].padding);
                if (array[j] > n) {
                    array[j] = (int)n;
                }
                n -= array[j];
                --length;
            }
        }
        for (int k = 0; k < array.length; ++k) {
            if (this.styles[k].mode == Mode.free) {
                final int n3 = (int)Math.floor(n / length--);
                array[k] = constrain(this.styles[k], n3 + this.styles[k].padding);
                n -= n3;
            }
        }
    }
    
    private static int constrain(final ColumnStyle columnStyle, final double n) {
        if (columnStyle.minimum >= 0 && n < columnStyle.minimum) {
            return columnStyle.minimum;
        }
        if (columnStyle.maximum >= 0 && n > columnStyle.maximum) {
            return columnStyle.maximum;
        }
        return (int)Math.round(n);
    }
    
    public void update() {
        final int[] sizes = new int[this.sizes.length];
        this.compute(sizes);
        final ITreeWidgetFeature treeWidgetFeature = this.xidget.getFeature(ITreeWidgetFeature.class);
        for (int i = 0; i < sizes.length; ++i) {
            treeWidgetFeature.setColumnWidth(i, sizes[i]);
        }
        this.sizes = sizes;
    }
    
    private static final class ColumnStyle
    {
        public Mode mode;
        public int minimum;
        public int maximum;
        public double relative;
        public int padding;
    }
    
    private enum Mode
    {
        absolute("absolute", 0), 
        relative("relative", 1), 
        free("free", 2), 
        auto("auto", 3);
        
        private Mode(final String s, final int n) {
        }
    }
}
