// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import edu.wise.utils.FormatUtils;
import edu.wise.graph.StyleSheet;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class ReportPanel extends Panel
{
    String temp1;
    String temp2;
    String temp3;
    private int x;
    private int y;
    private int offset;
    private int smallWidth;
    private int smallHeight;
    private int fx;
    private int fy;
    private UtilApplet utilApplet;
    private Util util;
    protected Image image;
    protected Graphics graphics;
    
    public ReportPanel(final UtilApplet utilApplet, final int n, final int n2) {
        this.x = 0;
        this.y = 0;
        this.offset = 0;
        this.fx = 0;
        this.fy = 20;
        this.image = null;
        this.setLayout(null);
        this.setSize(n, n2);
        this.setBackground(Color.white);
        this.smallWidth = (int)Math.floor(n / 4);
        this.smallHeight = (int)Math.floor(n2 / 6);
        this.utilApplet = utilApplet;
        this.util = utilApplet.getUtil();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.image = this.createImage(this.getSize().width, this.getSize().height);
        (this.graphics = this.image.getGraphics()).setColor(Color.white);
        this.graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.graphics.setColor(Color.black);
        this.paint(this.graphics);
        graphics.drawImage(this.image, 0, 0, this);
        this.image.flush();
    }
    
    private int centerFont(final Graphics graphics, final String s) {
        return this.centerFont(graphics, s, 1);
    }
    
    private int centerFont(final Graphics graphics, final String s, final int n) {
        final int n2 = this.smallWidth * n - Math.round(graphics.getFontMetrics().stringWidth(s));
        if (n2 < 1) {
            return 0;
        }
        return Math.round(n2 / 2);
    }
    
    private void setFont(final Graphics graphics, final Font font) {
        graphics.setFont(font);
        this.fy = graphics.getFontMetrics().getHeight();
        this.fy = this.smallHeight - this.fy;
        if (this.fy < 1) {
            this.fy = Math.round(this.smallHeight / 2);
        }
        else {
            this.fy = Math.round(this.smallHeight / 2 + this.fy / 2);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.setFont(graphics, StyleSheet.LABEL_FONT);
        graphics.setColor(StyleSheet.YELLOW);
        graphics.fillRect(this.x, this.y + this.offset, this.getSize().width, this.getSize().height);
        graphics.setColor(Color.yellow);
        graphics.fillRect(this.x + this.smallWidth * 0 + 1, this.y + this.offset + this.smallHeight * 5 + 1, this.smallWidth * 4 - 2, this.smallHeight - 2);
        graphics.setColor(Color.black);
        graphics.drawRect(0, -1, this.getSize().width - 1, this.getSize().height);
        graphics.drawRect(this.x, this.y + this.offset - 1, this.smallWidth * 4, this.smallHeight + 1);
        graphics.drawRect(this.x, this.y + this.offset + this.smallHeight, this.smallWidth * 2, this.smallHeight * 2);
        graphics.drawRect(this.x, this.y + this.offset + this.smallHeight * 3, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x, this.y + this.offset + this.smallHeight * 4, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x, this.y + this.offset + this.smallHeight * 5, this.smallWidth, this.smallHeight);
        graphics.drawString("Predicted", this.x + this.fx + this.centerFont(graphics, "Predicted", 2), this.fy + this.y + this.offset + this.smallHeight * 0);
        graphics.drawString("Group 1", this.x + this.fx + this.centerFont(graphics, "Group 1"), this.fy + this.y + this.offset + this.smallHeight);
        graphics.drawString("N", this.x + this.fx + this.centerFont(graphics, "N"), this.fy + this.y + this.offset + this.smallHeight * 2);
        this.setFont(graphics, StyleSheet.REGULAR_FONT);
        this.temp1 = FormatUtils.rounder_str(this.util.getn11(), 2);
        this.temp2 = FormatUtils.rounder_str(this.util.getn12(), 2);
        this.temp3 = FormatUtils.rounder_str(this.util.getn11() + this.util.getn12(), 2);
        graphics.drawString(this.temp1, this.x + this.fx + this.centerFont(graphics, this.temp1), this.fy + this.y + this.offset + this.smallHeight * 3);
        graphics.drawString(this.temp2, this.x + this.fx + this.centerFont(graphics, this.temp2), this.fy + this.y + this.offset + this.smallHeight * 4);
        this.setFont(graphics, StyleSheet.LABEL_FONT);
        graphics.drawString(this.temp3, this.x + this.fx + this.centerFont(graphics, this.temp3), this.fy + this.y + this.offset + this.smallHeight * 5);
        graphics.drawRect(this.x + this.smallWidth, this.y + this.offset + this.smallHeight * 3, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth, this.y + this.offset + this.smallHeight * 4, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth, this.y + this.offset + this.smallHeight * 5, this.smallWidth, this.smallHeight);
        graphics.drawString("Group 2", this.x + this.fx + this.smallWidth + this.centerFont(graphics, "Group 2", 1), this.fy + this.y + this.offset + this.smallHeight);
        graphics.drawString("N", this.x + this.fx + this.smallWidth + this.centerFont(graphics, "N", 1), this.fy + this.y + this.offset + this.smallHeight * 2);
        this.setFont(graphics, StyleSheet.REGULAR_FONT);
        this.temp1 = FormatUtils.rounder_str(this.util.getn21(), 2);
        this.temp2 = FormatUtils.rounder_str(this.util.getn22(), 2);
        this.temp3 = FormatUtils.rounder_str(this.util.getn21() + this.util.getn22(), 2);
        graphics.drawString(this.temp1, this.x + this.fx + this.smallWidth + this.centerFont(graphics, this.temp1), this.fy + this.y + this.offset + this.smallHeight * 3);
        graphics.drawString(this.temp2, this.x + this.fx + this.smallWidth + this.centerFont(graphics, this.temp2), this.fy + this.y + this.offset + this.smallHeight * 4);
        this.setFont(graphics, StyleSheet.LABEL_FONT);
        graphics.drawString(this.temp3, this.x + this.fx + this.smallWidth + this.centerFont(graphics, this.temp3), this.fy + this.y + this.offset + this.smallHeight * 5);
        graphics.drawRect(this.x + this.smallWidth * 2, this.y + this.offset + this.smallHeight, this.smallWidth, this.smallHeight * 2);
        graphics.drawRect(this.x + this.smallWidth * 2, this.y + this.offset + this.smallHeight * 3, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth * 2, this.y + this.offset + this.smallHeight * 4, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth * 2, this.y + this.offset + this.smallHeight * 5, this.smallWidth, this.smallHeight);
        graphics.drawString("Prop.", this.x + this.fx + this.smallWidth * 2 + this.centerFont(graphics, "Prop.", 1), this.fy + this.y + this.offset + this.smallHeight);
        graphics.drawString("Cor.", this.x + this.fx + this.smallWidth * 2 + this.centerFont(graphics, "Cor.", 1), this.fy + this.y + this.offset + this.smallHeight * 2);
        this.setFont(graphics, StyleSheet.REGULAR_FONT);
        this.temp1 = FormatUtils.rounder_str(this.util.getpc1(), 2);
        this.temp2 = FormatUtils.rounder_str(this.util.getpc2(), 2);
        this.temp3 = FormatUtils.rounder_str(this.util.getpcT(), 2);
        graphics.drawString(this.temp1, this.x + this.fx + this.smallWidth * 2 + this.centerFont(graphics, this.temp1), this.fy + this.y + this.offset + this.smallHeight * 3);
        graphics.drawString(this.temp2, this.x + this.fx + this.smallWidth * 2 + this.centerFont(graphics, this.temp2), this.fy + this.y + this.offset + this.smallHeight * 4);
        this.setFont(graphics, StyleSheet.LABEL_FONT);
        graphics.drawString(this.temp3, this.x + this.fx + this.smallWidth * 2 + this.centerFont(graphics, this.temp3), this.fy + this.y + this.offset + this.smallHeight * 5);
        graphics.drawRect(this.x + this.smallWidth * 3, this.y + this.offset + this.smallHeight, this.smallWidth, this.smallHeight * 2);
        graphics.drawRect(this.x + this.smallWidth * 3, this.y + this.offset + this.smallHeight * 3, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth * 3, this.y + this.offset + this.smallHeight * 4, this.smallWidth, this.smallHeight);
        graphics.drawRect(this.x + this.smallWidth * 3, this.y + this.offset + this.smallHeight * 5, this.smallWidth, this.smallHeight);
        graphics.drawString("Utility", this.x + this.fx + this.smallWidth * 3 + this.centerFont(graphics, "Utility", 1), this.fy + this.y + this.offset + this.smallHeight * 2);
        this.setFont(graphics, StyleSheet.REGULAR_FONT);
        this.temp1 = FormatUtils.rounder_str(this.util.getu1(), 2);
        this.temp2 = FormatUtils.rounder_str(this.util.getu2(), 2);
        this.temp3 = FormatUtils.rounder_str(this.util.getuT(), 2);
        graphics.drawString(this.temp1, this.x + this.fx + this.smallWidth * 3 + this.centerFont(graphics, this.temp1), this.fy + this.y + this.offset + this.smallHeight * 3);
        graphics.drawString(this.temp2, this.x + this.fx + this.smallWidth * 3 + this.centerFont(graphics, this.temp2), this.fy + this.y + this.offset + this.smallHeight * 4);
        this.setFont(graphics, StyleSheet.LABEL_FONT);
        graphics.drawString(this.temp3, this.x + this.fx + this.smallWidth * 3 + this.centerFont(graphics, this.temp3), this.fy + this.y + this.offset + this.smallHeight * 5);
    }
}
