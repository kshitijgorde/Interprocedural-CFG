// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Panel;

public class ColorPanel extends Panel
{
    private ColorListener cListener;
    private Dimension panelSize;
    private static int[][] colorGrid;
    
    public ColorPanel(final ColorListener cListener) {
        this.cListener = null;
        this.cListener = cListener;
        this.buildGUI();
    }
    
    public Dimension getPreferredSize() {
        return this.panelSize;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    private void buildGUI() {
        this.setLayout(new FlowLayout(1));
        this.add(this.buildGrids());
    }
    
    private Panel buildGrids() {
        final int n = 6;
        final int n2 = 8;
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(n, n2, 3, 3));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                final int n3 = i * n2 + j;
                final ColorCanvas colorCanvas = new ColorCanvas(ColorPanel.colorGrid[n3][0], ColorPanel.colorGrid[n3][1], ColorPanel.colorGrid[n3][2]);
                panel.add(colorCanvas);
                if (this.cListener != null) {
                    colorCanvas.addListener(this.cListener);
                }
            }
        }
        this.panelSize = new Dimension(160, 122);
        return panel;
    }
    
    static {
        ColorPanel.colorGrid = new int[][] { { 255, 124, 128 }, { 255, 252, 128 }, { 128, 252, 128 }, { 0, 252, 128 }, { 128, 252, 255 }, { 0, 124, 255 }, { 255, 124, 192 }, { 255, 124, 255 }, { 255, 0, 0 }, { 255, 252, 0 }, { 128, 252, 0 }, { 0, 252, 64 }, { 0, 252, 255 }, { 0, 124, 192 }, { 128, 124, 192 }, { 255, 0, 255 }, { 128, 60, 64 }, { 255, 124, 64 }, { 0, 252, 0 }, { 0, 124, 128 }, { 0, 60, 128 }, { 128, 124, 255 }, { 128, 0, 64 }, { 255, 0, 128 }, { 128, 0, 0 }, { 255, 124, 0 }, { 0, 124, 0 }, { 0, 124, 64 }, { 0, 0, 255 }, { 0, 0, 160 }, { 128, 0, 128 }, { 128, 0, 255 }, { 64, 0, 0 }, { 128, 60, 0 }, { 0, 60, 0 }, { 0, 60, 64 }, { 0, 0, 128 }, { 0, 0, 64 }, { 64, 0, 64 }, { 64, 0, 128 }, { 0, 0, 0 }, { 128, 124, 0 }, { 128, 124, 64 }, { 128, 124, 128 }, { 64, 124, 128 }, { 192, 188, 192 }, { 64, 0, 64 }, { 255, 252, 255 } };
    }
}
