// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import edu.davidson.tools.SUtil;
import java.awt.Font;
import edu.davidson.tools.SApplet;
import edu.davidson.numerics.Parser;

public class TextThing extends Thing
{
    private String text;
    private String calcStr;
    private double[] textVars;
    Parser calcFunc;
    
    public TextThing(final SApplet sApplet, final SScalable sScalable, final String s, final double n, final double n2) {
        this(sApplet, sScalable, s, null, n, n2);
    }
    
    public TextThing(final SApplet applet, final SScalable sScalable, final String text, final String calcStr, final double n, final double n2) {
        super(sScalable, n, n2);
        this.textVars = new double[3];
        super.font = new Font("Helvetica", 1, 14);
        this.text = text;
        super.applet = applet;
        this.calcStr = calcStr;
        if (this.calcStr == null) {
            return;
        }
        (this.calcFunc = new Parser(3)).defineVariable(1, "t");
        this.calcFunc.defineVariable(2, "x");
        this.calcFunc.defineVariable(3, "y");
        this.calcFunc.define(this.calcStr);
        this.calcFunc.parse();
        if (this.calcFunc.getErrorCode() != 0) {
            System.out.println("Failed to parse calc-text: ".concat(String.valueOf(String.valueOf(this.calcStr))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error: ").append(this.calcFunc.getErrorString()).append(" at function 1, position ").append(this.calcFunc.getErrorPosition()))));
        }
    }
    
    public final String getText() {
        double n = 0.0;
        if (this.calcStr == null || this.calcStr.equals("")) {
            return this.text;
        }
        if (super.applet != null) {
            this.textVars[0] = super.applet.clock.getTime();
        }
        this.textVars[1] = super.x;
        this.textVars[2] = super.y;
        try {
            n = this.calcFunc.evaluate(this.textVars);
            n = SUtil.chop(n, 1.0E-12);
        }
        catch (Exception ex) {}
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.text))).append(" ").append(super.format.form(n))));
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.font);
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        graphics.setColor(super.color);
        graphics.drawString(this.getText(), n, n2);
        graphics.setColor(Color.black);
        graphics.setFont(font);
    }
}
