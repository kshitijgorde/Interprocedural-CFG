// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class DatasetUtilities
{
    public static Number[] createNumberArray(final double[] data) {
        final Number[] result = new Number[data.length];
        for (int i = 0; i < data.length; ++i) {
            result[i] = new Double(data[i]);
        }
        return result;
    }
    
    public static Number[][] createNumberArray2D(final double[][] data) {
        final int l1 = data.length;
        final int l2 = data[0].length;
        final Number[][] result = new Number[l1][l2];
        for (int i = 0; i < l1; ++i) {
            result[i] = createNumberArray(data[i]);
        }
        return result;
    }
    
    public static Range getDomainExtent(final Dataset dataset) {
        if (dataset == null) {
            return null;
        }
        if (dataset instanceof CategoryDataset && !(dataset instanceof XYDataset)) {
            throw new IllegalArgumentException("The dataset does not have a numerical domain.");
        }
        if (dataset instanceof DomainInfo) {
            final DomainInfo info = (DomainInfo)dataset;
            return info.getDomainRange();
        }
        if (dataset instanceof XYDataset) {
            return iterateDomainExtent((XYDataset)dataset);
        }
        return null;
    }
    
    public static Range iterateDomainExtent(final XYDataset data) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        for (int seriesCount = data.getSeriesCount(), series = 0; series < seriesCount; ++series) {
            for (int itemCount = data.getItemCount(series), item = 0; item < itemCount; ++item) {
                Number lvalue;
                Number uvalue;
                if (data instanceof IntervalXYDataset) {
                    final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                    lvalue = intervalXYData.getStartXValue(series, item);
                    uvalue = intervalXYData.getEndXValue(series, item);
                }
                else {
                    lvalue = (uvalue = data.getXValue(series, item));
                }
                if (lvalue != null) {
                    minimum = Math.min(minimum, lvalue.doubleValue());
                }
                if (uvalue != null) {
                    maximum = Math.max(maximum, uvalue.doubleValue());
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        }
        return new Range(minimum, maximum);
    }
    
    public static Range getRangeExtent(final Dataset data) {
        if (data == null) {
            return null;
        }
        if (data instanceof RangeInfo) {
            final RangeInfo info = (RangeInfo)data;
            return info.getValueRange();
        }
        if (data instanceof CategoryDataset) {
            return iterateCategoryRangeExtent((CategoryDataset)data);
        }
        if (data instanceof XYDataset) {
            return iterateXYRangeExtent((XYDataset)data);
        }
        return null;
    }
    
    public static Range iterateCategoryRangeExtent(final CategoryDataset data) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        final int rowCount = data.getRowCount();
        final int columnCount = data.getColumnCount();
        for (int row = 0; row < rowCount; ++row) {
            for (int column = 0; column < columnCount; ++column) {
                Number lvalue;
                Number uvalue;
                if (data instanceof IntervalCategoryDataset) {
                    final IntervalCategoryDataset icd = (IntervalCategoryDataset)data;
                    lvalue = icd.getStartValue(row, column);
                    uvalue = icd.getEndValue(row, column);
                }
                else {
                    lvalue = (uvalue = data.getValue(row, column));
                }
                if (lvalue != null) {
                    minimum = Math.min(minimum, lvalue.doubleValue());
                }
                if (uvalue != null) {
                    maximum = Math.max(maximum, uvalue.doubleValue());
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        }
        return new Range(minimum, maximum);
    }
    
    public static Range iterateXYRangeExtent(final XYDataset data) {
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        for (int seriesCount = data.getSeriesCount(), series = 0; series < seriesCount; ++series) {
            for (int itemCount = data.getItemCount(series), item = 0; item < itemCount; ++item) {
                Number lvalue;
                Number uvalue;
                if (data instanceof IntervalXYDataset) {
                    final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                    lvalue = intervalXYData.getStartYValue(series, item);
                    uvalue = intervalXYData.getEndYValue(series, item);
                }
                else if (data instanceof HighLowDataset) {
                    final HighLowDataset highLowData = (HighLowDataset)data;
                    lvalue = highLowData.getLowValue(series, item);
                    uvalue = highLowData.getHighValue(series, item);
                }
                else {
                    lvalue = (uvalue = data.getYValue(series, item));
                }
                if (lvalue != null) {
                    minimum = Math.min(minimum, lvalue.doubleValue());
                }
                if (uvalue != null) {
                    maximum = Math.max(maximum, uvalue.doubleValue());
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        }
        return new Range(minimum, maximum);
    }
    
    public static Number getMinimumDomainValue(final Dataset data) {
        if (data == null) {
            throw new IllegalArgumentException("DatasetUtilities.getMinimumDomainValue: null dataset not allowed.");
        }
        if (data instanceof CategoryDataset && !(data instanceof XYDataset)) {
            throw new IllegalArgumentException("DatasetUtilities.getMinimumDomainValue(...): TableDataset does not have numerical domain.");
        }
        if (data instanceof DomainInfo) {
            final DomainInfo info = (DomainInfo)data;
            return info.getMinimumDomainValue();
        }
        if (!(data instanceof XYDataset)) {
            return null;
        }
        double minimum = Double.POSITIVE_INFINITY;
        final XYDataset xyData = (XYDataset)data;
        for (int seriesCount = xyData.getSeriesCount(), series = 0; series < seriesCount; ++series) {
            for (int itemCount = xyData.getItemCount(series), item = 0; item < itemCount; ++item) {
                Number value;
                if (data instanceof IntervalXYDataset) {
                    final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                    value = intervalXYData.getStartXValue(series, item);
                }
                else {
                    value = xyData.getXValue(series, item);
                }
                if (value != null) {
                    minimum = Math.min(minimum, value.doubleValue());
                }
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        }
        return new Double(minimum);
    }
    
    public static Number getMaximumDomainValue(final Dataset data) {
        if (data == null) {
            throw new IllegalArgumentException("Datasets.getMaximumDomainValue: null dataset not allowed.");
        }
        if (data instanceof CategoryDataset && !(data instanceof XYDataset)) {
            throw new IllegalArgumentException("Datasets.getMaximumDomainValue(...): CategoryDataset does not have numerical domain.");
        }
        if (data instanceof DomainInfo) {
            final DomainInfo info = (DomainInfo)data;
            return info.getMaximumDomainValue();
        }
        if (!(data instanceof XYDataset)) {
            return null;
        }
        final XYDataset xyData = (XYDataset)data;
        double maximum = Double.NEGATIVE_INFINITY;
        for (int seriesCount = xyData.getSeriesCount(), series = 0; series < seriesCount; ++series) {
            for (int itemCount = xyData.getItemCount(series), item = 0; item < itemCount; ++item) {
                Number value;
                if (data instanceof IntervalXYDataset) {
                    final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                    value = intervalXYData.getEndXValue(series, item);
                }
                else {
                    value = xyData.getXValue(series, item);
                }
                if (value != null) {
                    maximum = Math.max(maximum, value.doubleValue());
                }
            }
        }
        if (maximum == Double.NEGATIVE_INFINITY) {
            return null;
        }
        return new Double(maximum);
    }
    
    public static Number getMinimumRangeValue(final Dataset data) {
        if (data == null) {
            throw new IllegalArgumentException("Datasets.getMinimumRangeValue: null dataset not allowed.");
        }
        if (data instanceof RangeInfo) {
            final RangeInfo info = (RangeInfo)data;
            return info.getMinimumRangeValue();
        }
        if (data instanceof CategoryDataset) {
            final CategoryDataset categoryData = (CategoryDataset)data;
            double minimum = Double.POSITIVE_INFINITY;
            final int seriesCount = categoryData.getRowCount();
            final int itemCount = categoryData.getColumnCount();
            for (int series = 0; series < seriesCount; ++series) {
                for (int item = 0; item < itemCount; ++item) {
                    Number value;
                    if (data instanceof IntervalCategoryDataset) {
                        final IntervalCategoryDataset icd = (IntervalCategoryDataset)data;
                        value = icd.getStartValue(series, item);
                    }
                    else {
                        value = categoryData.getValue(series, item);
                    }
                    if (value != null) {
                        minimum = Math.min(minimum, value.doubleValue());
                    }
                }
            }
            if (minimum == Double.POSITIVE_INFINITY) {
                return null;
            }
            return new Double(minimum);
        }
        else {
            if (!(data instanceof XYDataset)) {
                return null;
            }
            final XYDataset xyData = (XYDataset)data;
            double minimum = Double.POSITIVE_INFINITY;
            for (int seriesCount = xyData.getSeriesCount(), series2 = 0; series2 < seriesCount; ++series2) {
                for (int itemCount2 = xyData.getItemCount(series2), item = 0; item < itemCount2; ++item) {
                    Number value;
                    if (data instanceof IntervalXYDataset) {
                        final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                        value = intervalXYData.getStartYValue(series2, item);
                    }
                    else if (data instanceof HighLowDataset) {
                        final HighLowDataset highLowData = (HighLowDataset)data;
                        value = highLowData.getLowValue(series2, item);
                    }
                    else {
                        value = xyData.getYValue(series2, item);
                    }
                    if (value != null) {
                        minimum = Math.min(minimum, value.doubleValue());
                    }
                }
            }
            if (minimum == Double.POSITIVE_INFINITY) {
                return null;
            }
            return new Double(minimum);
        }
    }
    
    public static Number getMaximumRangeValue(final Dataset data) {
        if (data == null) {
            throw new IllegalArgumentException("Datasets.getMinimumRangeValue: null dataset not allowed.");
        }
        if (data instanceof RangeInfo) {
            final RangeInfo info = (RangeInfo)data;
            return info.getMaximumRangeValue();
        }
        if (data instanceof CategoryDataset) {
            final CategoryDataset categoryData = (CategoryDataset)data;
            double maximum = Double.NEGATIVE_INFINITY;
            final int seriesCount = categoryData.getRowCount();
            final int itemCount = categoryData.getColumnCount();
            for (int series = 0; series < seriesCount; ++series) {
                for (int item = 0; item < itemCount; ++item) {
                    Number value;
                    if (data instanceof IntervalCategoryDataset) {
                        final IntervalCategoryDataset icd = (IntervalCategoryDataset)data;
                        value = icd.getEndValue(series, item);
                    }
                    else {
                        value = categoryData.getValue(series, item);
                    }
                    if (value != null) {
                        maximum = Math.max(maximum, value.doubleValue());
                    }
                }
            }
            if (maximum == Double.NEGATIVE_INFINITY) {
                return null;
            }
            return new Double(maximum);
        }
        else {
            if (!(data instanceof XYDataset)) {
                return null;
            }
            final XYDataset xyData = (XYDataset)data;
            double maximum = Double.NEGATIVE_INFINITY;
            for (int seriesCount = xyData.getSeriesCount(), series2 = 0; series2 < seriesCount; ++series2) {
                for (int itemCount2 = xyData.getItemCount(series2), item = 0; item < itemCount2; ++item) {
                    Number value;
                    if (data instanceof IntervalXYDataset) {
                        final IntervalXYDataset intervalXYData = (IntervalXYDataset)data;
                        value = intervalXYData.getEndYValue(series2, item);
                    }
                    else if (data instanceof HighLowDataset) {
                        final HighLowDataset highLowData = (HighLowDataset)data;
                        value = highLowData.getHighValue(series2, item);
                    }
                    else {
                        value = xyData.getYValue(series2, item);
                    }
                    if (value != null) {
                        maximum = Math.max(maximum, value.doubleValue());
                    }
                }
            }
            if (maximum == Double.NEGATIVE_INFINITY) {
                return null;
            }
            return new Double(maximum);
        }
    }
    
    public static PieDataset createPieDatasetForRow(final CategoryDataset data, final Comparable rowKey) {
        final int row = data.getRowIndex(rowKey);
        return createPieDatasetForRow(data, row);
    }
    
    public static PieDataset createPieDatasetForRow(final CategoryDataset data, final int row) {
        final DefaultPieDataset result = new DefaultPieDataset();
        for (int columnCount = data.getColumnCount(), current = 0; current < columnCount; ++current) {
            final Comparable columnKey = data.getColumnKey(current);
            result.setValue(columnKey, data.getValue(row, current));
        }
        return result;
    }
    
    public static PieDataset createPieDatasetForColumn(final CategoryDataset data, final Comparable columnKey) {
        final int column = data.getColumnIndex(columnKey);
        return createPieDatasetForColumn(data, column);
    }
    
    public static PieDataset createPieDatasetForColumn(final CategoryDataset data, final int column) {
        final DefaultPieDataset result = new DefaultPieDataset();
        for (int rowCount = data.getRowCount(), i = 0; i < rowCount; ++i) {
            final Comparable rowKey = data.getRowKey(i);
            result.setValue(rowKey, data.getValue(i, column));
        }
        return result;
    }
    
    public static double calculatePieDatasetTotal(final PieDataset dataset) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        final List keys = dataset.getKeys();
        double totalValue = 0.0;
        for (final Comparable current : keys) {
            if (current != null) {
                final Number value = dataset.getValue(current);
                double v = 0.0;
                if (value != null) {
                    v = value.doubleValue();
                }
                if (v <= 0.0) {
                    continue;
                }
                totalValue += v;
            }
        }
        return totalValue;
    }
    
    public static Range getStackedRangeExtent(final CategoryDataset data) {
        Range result = null;
        if (data != null) {
            double minimum = 0.0;
            double maximum = 0.0;
            for (int categoryCount = data.getColumnCount(), item = 0; item < categoryCount; ++item) {
                double positive = 0.0;
                double negative = 0.0;
                for (int seriesCount = data.getRowCount(), series = 0; series < seriesCount; ++series) {
                    final Number number = data.getValue(series, item);
                    if (number != null) {
                        final double value = number.doubleValue();
                        if (value > 0.0) {
                            positive += value;
                        }
                        if (value < 0.0) {
                            negative += value;
                        }
                    }
                }
                minimum = Math.min(minimum, negative);
                maximum = Math.max(maximum, positive);
            }
            result = new Range(minimum, maximum);
        }
        return result;
    }
    
    public static Range getStackedRangeExtent(final CategoryDataset dataset, final KeyToGroupMap map) {
        Range result = null;
        if (dataset != null) {
            final int[] groupIndex = new int[dataset.getRowCount()];
            for (int i = 0; i < dataset.getRowCount(); ++i) {
                groupIndex[i] = map.getGroupIndex(map.getGroup(dataset.getRowKey(i)));
            }
            final int groupCount = map.getGroupCount();
            final double[] minimum = new double[groupCount];
            final double[] maximum = new double[groupCount];
            for (int categoryCount = dataset.getColumnCount(), item = 0; item < categoryCount; ++item) {
                final double[] positive = new double[groupCount];
                final double[] negative = new double[groupCount];
                for (int seriesCount = dataset.getRowCount(), series = 0; series < seriesCount; ++series) {
                    final Number number = dataset.getValue(series, item);
                    if (number != null) {
                        final double value = number.doubleValue();
                        if (value > 0.0) {
                            positive[groupIndex[series]] += value;
                        }
                        if (value < 0.0) {
                            negative[groupIndex[series]] += value;
                        }
                    }
                }
                for (int g = 0; g < groupCount; ++g) {
                    minimum[g] = Math.min(minimum[g], negative[g]);
                    maximum[g] = Math.max(maximum[g], positive[g]);
                }
            }
            for (int j = 0; j < groupCount; ++j) {
                result = Range.combine(result, new Range(minimum[j], maximum[j]));
            }
        }
        return result;
    }
    
    public static Number getMinimumStackedRangeValue(final CategoryDataset data) {
        Number result = null;
        if (data != null) {
            double minimum = 0.0;
            for (int categoryCount = data.getRowCount(), item = 0; item < categoryCount; ++item) {
                double total = 0.0;
                for (int seriesCount = data.getColumnCount(), series = 0; series < seriesCount; ++series) {
                    final Number number = data.getValue(series, item);
                    if (number != null) {
                        final double value = number.doubleValue();
                        if (value < 0.0) {
                            total += value;
                        }
                    }
                }
                minimum = Math.min(minimum, total);
            }
            result = new Double(minimum);
        }
        return result;
    }
    
    public static Number getMaximumStackedRangeValue(final CategoryDataset data) {
        Number result = null;
        if (data != null) {
            double maximum = 0.0;
            for (int categoryCount = data.getColumnCount(), item = 0; item < categoryCount; ++item) {
                double total = 0.0;
                for (int seriesCount = data.getRowCount(), series = 0; series < seriesCount; ++series) {
                    final Number number = data.getValue(series, item);
                    if (number != null) {
                        final double value = number.doubleValue();
                        if (value > 0.0) {
                            total += value;
                        }
                    }
                }
                maximum = Math.max(maximum, total);
            }
            result = new Double(maximum);
        }
        return result;
    }
    
    public static XYDataset sampleFunction2D(final Function2D f, final double start, final double end, final int samples, final String seriesName) {
        if (f == null) {
            throw new IllegalArgumentException("Null 'f' argument.");
        }
        if (seriesName == null) {
            throw new IllegalArgumentException("Null 'seriesName' argument.");
        }
        if (start >= end) {
            throw new IllegalArgumentException("Requires 'start' < 'end'.");
        }
        if (samples < 2) {
            throw new IllegalArgumentException("Requires 'samples' > 1");
        }
        final XYSeries series = new XYSeries(seriesName);
        final double step = (end - start) / samples;
        for (int i = 0; i <= samples; ++i) {
            final double x = start + step * i;
            series.add(x, f.getValue(x));
        }
        final XYSeriesCollection collection = new XYSeriesCollection(series);
        return collection;
    }
    
    public static CategoryDataset createCategoryDataset(final String rowKeyPrefix, final String columnKeyPrefix, final double[][] data) {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        for (int r = 0; r < data.length; ++r) {
            final String rowKey = rowKeyPrefix + (r + 1);
            for (int c = 0; c < data[r].length; ++c) {
                final String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(new Double(data[r][c]), rowKey, columnKey);
            }
        }
        return result;
    }
    
    public static CategoryDataset createCategoryDataset(final String rowKeyPrefix, final String columnKeyPrefix, final Number[][] data) {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        for (int r = 0; r < data.length; ++r) {
            final String rowKey = rowKeyPrefix + (r + 1);
            for (int c = 0; c < data[r].length; ++c) {
                final String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(data[r][c], rowKey, columnKey);
            }
        }
        return result;
    }
    
    public static CategoryDataset createCategoryDataset(final String[] rowKeys, final String[] columnKeys, final double[][] data) {
        if (rowKeys == null) {
            throw new IllegalArgumentException("Argument 'rowKeys' cannot be null.");
        }
        if (columnKeys == null) {
            throw new IllegalArgumentException("Argument 'columnKeys' cannot be null.");
        }
        if (rowKeys.length != data.length) {
            throw new IllegalArgumentException("The number of row keys does not match the number of rows in the data array.");
        }
        int columnCount = 0;
        for (int r = 0; r < data.length; ++r) {
            columnCount = Math.max(columnCount, data[r].length);
        }
        if (columnKeys.length != columnCount) {
            throw new IllegalArgumentException("The number of column keys does not match the number of columns in the data array.");
        }
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        for (int r2 = 0; r2 < data.length; ++r2) {
            final String rowKey = rowKeys[r2];
            for (int c = 0; c < data[r2].length; ++c) {
                final String columnKey = columnKeys[c];
                result.addValue(new Double(data[r2][c]), rowKey, columnKey);
            }
        }
        return result;
    }
    
    public static CategoryDataset createCategoryDataset(final String rowKey, final KeyedValues rowData) {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        for (int i = 0; i < rowData.getItemCount(); ++i) {
            result.addValue(rowData.getValue(i), rowKey, rowData.getKey(i));
        }
        return result;
    }
    
    public static boolean isEmptyOrNull(final XYDataset data) {
        boolean result = true;
        if (data != null) {
            for (int s = 0; s < data.getSeriesCount(); ++s) {
                if (data.getItemCount(s) > 0) {
                    result = false;
                }
            }
        }
        return result;
    }
    
    public static boolean isEmptyOrNull(final PieDataset dataset) {
        if (dataset == null) {
            return true;
        }
        final int itemCount = dataset.getItemCount();
        if (itemCount == 0) {
            return true;
        }
        for (int item = 0; item < itemCount; ++item) {
            final Number y = dataset.getValue(item);
            if (y != null) {
                final double yy = y.doubleValue();
                if (yy > 0.0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isEmptyOrNull(final CategoryDataset data) {
        if (data == null) {
            return true;
        }
        final int rowCount = data.getRowCount();
        final int columnCount = data.getColumnCount();
        if (rowCount == 0 || columnCount == 0) {
            return true;
        }
        for (int r = 0; r < rowCount; ++r) {
            for (int c = 0; c < columnCount; ++c) {
                if (data.getValue(r, c) != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static PieDataset limitPieDataset(final PieDataset dataset, final double percentThreshold) {
        return limitPieDataset(dataset, percentThreshold, 2, "Other");
    }
    
    public static PieDataset limitPieDataset(final PieDataset dataset, final double percentThreshold, final int minItems) {
        return limitPieDataset(dataset, percentThreshold, minItems, "Other");
    }
    
    public static PieDataset limitPieDataset(final PieDataset dataset, final double percentThreshold, final int minItems, final Comparable key) {
        final DefaultPieDataset result = new DefaultPieDataset();
        final double total = calculatePieDatasetTotal(dataset);
        final List keys = dataset.getKeys();
        final ArrayList otherKeys = new ArrayList();
        for (final Comparable currentKey : keys) {
            final Number dataValue = dataset.getValue(currentKey);
            if (dataValue != null) {
                final double value = dataValue.doubleValue();
                if (value / total >= percentThreshold) {
                    continue;
                }
                otherKeys.add(currentKey);
            }
        }
        final Iterator iterator = keys.iterator();
        double otherValue = 0.0;
        while (iterator.hasNext()) {
            final Comparable currentKey2 = iterator.next();
            final Number dataValue2 = dataset.getValue(currentKey2);
            if (dataValue2 != null) {
                if (otherKeys.contains(currentKey2) && otherKeys.size() >= minItems) {
                    otherValue += dataValue2.doubleValue();
                }
                else {
                    result.setValue(currentKey2, dataValue2);
                }
            }
        }
        if (otherKeys.size() >= minItems) {
            result.setValue(key, otherValue);
        }
        return result;
    }
    
    public static Range getStackedRangeExtent(final TableXYDataset data) {
        if (data == null) {
            return null;
        }
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        for (int itemNo = 0; itemNo < data.getItemCount(); ++itemNo) {
            double value = 0.0;
            for (int seriesNo = 0; seriesNo < data.getSeriesCount(); ++seriesNo) {
                if (data.getYValue(seriesNo, itemNo) != null) {
                    value += data.getYValue(seriesNo, itemNo).doubleValue();
                }
            }
            if (value > maximum) {
                maximum = value;
            }
            if (value < minimum) {
                minimum = value;
            }
        }
        if (minimum == Double.POSITIVE_INFINITY) {
            return null;
        }
        return new Range(minimum, maximum);
    }
    
    public static Range getCumulativeRangeExtent(final CategoryDataset dataset) {
        if (dataset == null) {
            return null;
        }
        boolean allItemsNull = true;
        double minimum = 0.0;
        double maximum = 0.0;
        for (int row = 0; row < dataset.getRowCount(); ++row) {
            double runningTotal = 0.0;
            for (int column = 0; column < dataset.getColumnCount() - 1; ++column) {
                final Number n = dataset.getValue(row, column);
                if (n != null) {
                    allItemsNull = false;
                    final double value = n.doubleValue();
                    runningTotal += value;
                    minimum = Math.min(minimum, runningTotal);
                    maximum = Math.max(maximum, runningTotal);
                }
            }
        }
        if (!allItemsNull) {
            return new Range(minimum, maximum);
        }
        return null;
    }
}
