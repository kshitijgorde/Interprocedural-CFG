// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.Color;
import java.awt.Component;
import edu.wise.correl.Cor_app;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Container;

public class MeanLesson extends Container implements CorrelOutput
{
    private static MeanLessonPanel mlp;
    static ScrollPane sp;
    Image img;
    Graphics graphics;
    
    public MeanLesson(final int n, final int n2, final Image img, final Cor_app cor_app) {
        this.img = img;
        this.graphics = img.getGraphics();
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        (MeanLesson.sp = new ScrollPane(0)).setBackground(StyleSheet.BACKGROUND);
        this.add(MeanLesson.sp);
        MeanLesson.sp.setSize(n, n2);
        (MeanLesson.mlp = new MeanLessonPanel(-19, n2, img, cor_app)).setBackground(StyleSheet.BACKGROUND);
        MeanLesson.sp.add(MeanLesson.mlp);
        this.update();
    }
    
    public void update() {
        MeanLesson.mlp.repaint();
        if (MeanLesson.mlp.getSize().height > this.getSize().height) {
            this.invalidate();
            this.setSize(this.getSize().width, MeanLesson.mlp.getSize().height + 40);
            this.doLayout();
            this.validate();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(-1, 0, this.getSize().width, this.getSize().height);
    }
}
