// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot1d;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Color;

public class DataSetRenderingPreferences
{
    public static Color[] defaultColor;
    public Stroke stroke;
    public Color color;
    public PLOT_TYPE plotType;
    
    public DataSetRenderingPreferences() {
        this.stroke = new BasicStroke(1.0f);
        this.color = Color.BLUE;
        this.plotType = PLOT_TYPE.SCATTER;
    }
    
    static {
        DataSetRenderingPreferences.defaultColor = new Color[] { Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.PINK };
    }
    
    public enum PLOT_TYPE
    {
        LINE, 
        SCATTER, 
        LINE_SCATTER;
    }
}
