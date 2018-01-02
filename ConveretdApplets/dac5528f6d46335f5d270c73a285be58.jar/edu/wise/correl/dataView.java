// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import edu.wise.correl.gui.StyleSheet;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import edu.wise.graph.CorrelGraph;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class dataView extends Panel
{
    public Image image;
    public Graphics graphics;
    int width;
    int height;
    private InnerDataView idv;
    private static CorrelGraph cg;
    private int idv_ystart;
    private int idv_xstart;
    static FontMetrics f_reg_fm;
    private double interval_y;
    private double interval_x;
    private Cor_app ca;
    public static final boolean DEBUG = false;
    
    public dataView(final Cor_app ca, final int width, final int height, final Image image, final Image image2) {
        this.idv_ystart = 2;
        this.idv_xstart = 45;
        this.interval_y = 0.0;
        this.interval_x = 0.0;
        this.setLayout(null);
        this.ca = ca;
        this.setFont(StyleSheet.f_reg);
        this.width = width;
        this.height = height;
        this.setSize(this.width, this.height);
        this.add(this.idv = new InnerDataView(ca, this, 270, 270, image2));
        this.idv.setBounds(this.idv_xstart, this.idv_ystart, this.idv.width(), this.idv.height());
        this.image = image;
        (this.graphics = image.getGraphics()).setColor(StyleSheet.BACKGROUND);
        this.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.idv.setZoom(0.8);
    }
    
    public void update() {
        this.idv.update();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.graphics.setColor(StyleSheet.BACKGROUND);
        this.graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.graphics.setColor(Color.black);
        this.graphics.drawRect(0, 0, this.width - 1, this.height - 1);
        final String[] labels = CorrelGraph.getLabels(6, true);
        final String[] labels2 = CorrelGraph.getLabels(6, false);
        this.interval_y = this.idv.height() / labels2.length;
        this.interval_x = this.idv.width() / labels.length;
        this.graphics.setFont(StyleSheet.f_reg);
        final int n = this.graphics.getFontMetrics().getDescent() + 1;
        final int height = this.graphics.getFontMetrics().getHeight();
        int n2 = 0;
        for (int i = 1; i < labels2.length; ++i) {
            int charWidth = 0;
            if (labels2[i].charAt(0) == '-') {
                charWidth = graphics.getFontMetrics().charWidth('-');
            }
            n2 = 33 - charWidth;
            this.graphics.drawString(labels2[i], this.idv_xstart - n2, this.idv.height() + this.idv_ystart - (int)Math.round(this.interval_y * i) + n);
            this.graphics.drawString(labels[i], this.idv_xstart + (int)Math.round(this.interval_x * i) - this.graphics.getFontMetrics().stringWidth(labels[i]) / 2, this.idv.height() + this.idv_ystart + height - 2);
        }
        this.graphics.drawString(Cor_app.cd.getXName(), this.idv.width() / 2 + n2 + this.idv_ystart, this.idv.height() + this.idv_ystart + 20);
        final String yName = Cor_app.cd.getYName();
        int j = 0;
        final int n3 = (this.idv.getSize().height - (yName.length() - 2) * height) / 2;
        while (j < yName.length()) {
            final char char1 = yName.charAt(j);
            this.graphics.drawString(String.valueOf(char1), 1 + (11 - graphics.getFontMetrics().charWidth(char1)) / 2, n3 + height * j);
            ++j;
        }
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void setDrawRegLine(final boolean drawRegLine) {
        this.idv.setDrawRegLine(drawRegLine);
    }
    
    public void setDrawSSpred(final boolean drawSSpred) {
        this.idv.setDrawSSpred(drawSSpred);
    }
    
    public void setDrawSStot(final boolean drawSStot) {
        this.idv.setDrawSStot(drawSStot);
    }
    
    public void setDrawSSErr(final boolean drawSSErr) {
        this.idv.setDrawSSErr(drawSSErr);
    }
    
    public void setDrawYbar(final boolean drawYbar) {
        this.idv.setDrawYbar(drawYbar);
    }
    
    public void setZoom(final double zoom) {
        this.idv.setZoom(zoom);
    }
    
    public void setDrawGrid(final boolean drawGrid) {
        this.idv.setDrawGrid(drawGrid);
    }
    
    public boolean getDrawRegLine() {
        return this.idv.getDrawRegLine();
    }
    
    public boolean getDrawSSpred() {
        return this.idv.getDrawSSpred();
    }
    
    public boolean getDrawSStot() {
        return this.idv.getDrawSStot();
    }
    
    public boolean getDrawSSErr() {
        return this.idv.getDrawSSErr();
    }
    
    public boolean getDrawYbar() {
        return this.idv.getDrawYbar();
    }
    
    public InnerDataView getIDV() {
        return this.idv;
    }
}
