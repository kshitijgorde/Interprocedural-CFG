// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.StringTokenizer;
import java.awt.Color;

public class GraphDataSeries
{
    public static final Color DEFAULT_COLOR;
    private float[] _fValues;
    private String _sSeriesTitle;
    private String _sSeriesTitleBold;
    private Color _cColor;
    private int _iValueCount;
    private float _fMaxValue;
    private float _fMinValue;
    
    static {
        DEFAULT_COLOR = Color.blue;
    }
    
    public GraphDataSeries(final String s) {
        this._sSeriesTitle = "";
        this._sSeriesTitleBold = "";
        this._iValueCount = 0;
        this._fMaxValue = 0.0f;
        this._fMinValue = 0.0f;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "~");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            switch (n) {
                case 0: {
                    this._cColor = Graph.sgv(nextToken, GraphDataSeries.DEFAULT_COLOR);
                    break;
                }
                case 1: {
                    this._sSeriesTitle = Graph.sgv(nextToken, "");
                    break;
                }
                case 2: {
                    this._sSeriesTitleBold = Graph.sgv(nextToken, "");
                    break;
                }
                case 3: {
                    this._fValues = Graph.sgv(nextToken, this._fValues);
                    break;
                }
            }
            ++n;
        }
    }
    
    public GraphDataSeries(final float[] array, final String s) {
        this(array, s, "", GraphDataSeries.DEFAULT_COLOR);
    }
    
    public GraphDataSeries(final float[] array, final String s, final Color color) {
        this(array, s, "", color);
    }
    
    public GraphDataSeries(final float[] fValues, final String sSeriesTitle, final String sSeriesTitleBold, final Color cColor) {
        this._sSeriesTitle = "";
        this._sSeriesTitleBold = "";
        this._iValueCount = 0;
        this._fMaxValue = 0.0f;
        this._fMinValue = 0.0f;
        this._fValues = fValues;
        this._sSeriesTitle = sSeriesTitle;
        this._cColor = cColor;
        this._sSeriesTitleBold = sSeriesTitleBold;
    }
    
    public Color getColor() {
        return this._cColor;
    }
    
    public float getMaxValue(final int n) {
        if (this._fValues == null) {
            return 0.0f;
        }
        float n2 = this._fValues[0];
        int length = this._fValues.length;
        if (length > n && n != 0) {
            length = n;
        }
        for (int i = 1; i < length; ++i) {
            if (n2 < this._fValues[i]) {
                n2 = this._fValues[i];
            }
        }
        return n2;
    }
    
    public float getMinValue(final int n) {
        if (this._fValues == null) {
            return 0.0f;
        }
        float n2 = this._fValues[0];
        int length = this._fValues.length;
        if (length > n && n != 0) {
            length = n;
        }
        for (int i = 1; i < length; ++i) {
            if (n2 > this._fValues[i]) {
                n2 = this._fValues[i];
            }
        }
        return n2;
    }
    
    public String getProperty() {
        return String.valueOf(Graph.getHEX(this._cColor)) + "~" + Graph.sgv(this._sSeriesTitle) + "~" + Graph.sgv(this._sSeriesTitleBold) + "~" + Graph.getFloatList(this._fValues);
    }
    
    public String getSeriesTitle() {
        return this._sSeriesTitle;
    }
    
    public String getSeriesTitleBold() {
        return this._sSeriesTitleBold;
    }
    
    public int getValueCount() {
        if (this._fValues == null) {
            return 0;
        }
        return this._fValues.length;
    }
    
    public float[] getValues() {
        return this._fValues;
    }
}
