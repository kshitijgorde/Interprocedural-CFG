// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Color;

public class GraphDataSeries
{
    public static final Color DEFAULT_COLOR;
    private float[] _fValues;
    private String _sSeriesTitle;
    private String _sSeriesTitleBold;
    private String _sSeriesScale;
    private Color _cColor;
    private int _iValueCount;
    private float _fMaxValue;
    private float _fMinValue;
    
    static {
        DEFAULT_COLOR = Color.blue;
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
        this._sSeriesScale = "";
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
    
    public void setColor(final Color cColor) {
        this._cColor = cColor;
    }
    
    public void setSeriesTitle(final String sSeriesTitle) {
        this._sSeriesTitle = sSeriesTitle;
    }
}
