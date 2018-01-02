import java.awt.Image;
import java.awt.Event;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class NewCanvas extends Canvas
{
    static Font font1;
    static Font font2;
    static Color c1;
    static Color c2;
    String op;
    static String tot;
    EuroCalc applet;
    LogicUnit logicUnit;
    Container owner;
    int x;
    int y;
    int s_x;
    int s_y;
    
    public NewCanvas(final String op, final Container owner, final Applet applet) {
        this.x = 5;
        this.y = 5;
        this.s_x = 19;
        this.s_y = 31;
        this.owner = owner;
        this.applet = (EuroCalc)applet;
        this.logicUnit = this.applet.getLogicUnit();
        this.resize(50, 40);
        this.owner.validate();
        this.setBackground(Color.blue);
        this.op = op;
    }
    
    public String getOp() {
        return this.op;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(NewCanvas.c1);
        graphics.fillRoundRect(5, 5, 43, 33, 10, 20);
        graphics.setColor(NewCanvas.c2);
        graphics.fillRoundRect(this.x, this.y, 40, 30, 10, 20);
        graphics.setColor(Color.black);
        graphics.setFont(NewCanvas.font1);
        final int stringWidth = graphics.getFontMetrics().stringWidth(this.op);
        graphics.drawString(this.op, 25 - stringWidth / 2, this.s_y);
        graphics.setFont(NewCanvas.font2);
        graphics.setColor(Color.yellow);
        graphics.drawString(this.op, 24 - stringWidth / 2, this.s_y - 1);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        NewCanvas.tot = String.valueOf(NewCanvas.tot) + this.op;
        this.x = 6;
        this.y = 6;
        this.s_x = 20;
        this.s_y = 32;
        this.repaint();
        final Object target = event.target;
        final char char1 = this.op.charAt(0);
        if (Character.isDigit(char1)) {
            this.logicUnit.OnDigit(char1);
        }
        if (target == EuroCalc.b_sig) {
            this.logicUnit.OnSign();
        }
        if (target == EuroCalc.b_poi) {
            final LogicUnit logicUnit = this.logicUnit;
            if (logicUnit.factor == 1.0) {
                final LogicUnit logicUnit2 = logicUnit;
                logicUnit2.factor /= 10.0;
            }
        }
        if (target == EuroCalc.b_add) {
            this.logicUnit.OnAdd();
        }
        if (target == EuroCalc.b_res) {
            this.logicUnit.OnEnter();
        }
        if (target == EuroCalc.b_mul) {
            this.logicUnit.OnMultiply();
        }
        if (target == EuroCalc.b_sub) {
            this.logicUnit.OnSubstract();
        }
        if (target == EuroCalc.b_div) {
            this.logicUnit.OnDivide();
        }
        if (target == EuroCalc.b_pct) {
            final LogicUnit logicUnit3 = this.logicUnit;
            if (!logicUnit3.first_operation) {
                logicUnit3.ma *= logicUnit3.mb / 100.0;
                logicUnit3.digitList.setText(String.valueOf(logicUnit3.ma));
                logicUnit3.setEuro();
            }
        }
        if (target == EuroCalc.b_mem1) {
            this.logicUnit.OnMemory();
        }
        if (target == EuroCalc.b_mem2) {
            final LogicUnit logicUnit4 = this.logicUnit;
            if (logicUnit4.digitList.memoryLabel.getText().equals("mem") && true) {
                logicUnit4.digit = 1;
                logicUnit4.ma = logicUnit4.myMemory.value;
                logicUnit4.current_currency = logicUnit4.myMemory.currency;
                logicUnit4.digitList.setText(String.valueOf(logicUnit4.ma));
                logicUnit4.setEuro();
            }
            this.applet.setFlagCurr2(FlagCanvas.HS.get(this.logicUnit.current_currency));
        }
        if (target == EuroCalc.b_mem3) {
            final LogicUnit logicUnit5 = this.logicUnit;
            logicUnit5.myMemory.value = 0.0;
            logicUnit5.digitList.memoryLabel.setText("   ");
        }
        if (target == EuroCalc.b_mem4) {
            this.logicUnit.OnCancel();
        }
        if (target == EuroCalc.b_mem5) {
            this.logicUnit.Reset();
            this.applet.setFlagCurr2(FlagCanvas.HS.get(this.logicUnit.current_currency));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.x = 5;
        this.y = 5;
        this.s_x = 19;
        this.s_y = 31;
        this.repaint();
        return true;
    }
    
    static {
        NewCanvas.font1 = new Font("Arial", 1, 26);
        NewCanvas.font2 = new Font("Arial", 1, 24);
        NewCanvas.c1 = new Color(51, 0, 153);
        NewCanvas.c2 = new Color(51, 0, 255);
        NewCanvas.tot = "";
    }
}
