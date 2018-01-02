import java.awt.FontMetrics;
import java.awt.Graphics;
import graphTools.GraphCanvas;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import mathTools.Copyright;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import parser.Function;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IntegralProps extends Applet implements ActionListener, ItemListener
{
    private Function f;
    private Function fPrime;
    private Title title;
    private Instructions instructions;
    private CheckMarks checks;
    private PlotCanvas graph;
    private Checkbox areaButton;
    private Checkbox integralButton;
    private CheckboxGroup topic;
    private Checkbox cb1;
    private Checkbox cb2;
    private Checkbox cb3;
    private Checkbox cb4;
    private Checkbox cb5;
    private Checkbox cb6;
    private Checkbox cb7;
    private Checkbox cb8;
    private Button checkButton;
    private int[] answers;
    private double a;
    private double b;
    
    public void init() {
        this.setBackground(new Color(0, 0, 100));
        this.setLayout(null);
        (this.title = new Title(338, 30)).setBounds(2, 2, 338, 30);
        this.add(this.title);
        (this.graph = new PlotCanvas(338, 169)).setBounds(2, 34, 338, 169);
        this.add(this.graph);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(2, 205, 338, 360);
        panel.setBackground(new Color(255, 255, 200));
        final Font font = new Font("Serif", 1, 14);
        final int n = 120;
        final int n2 = 50;
        final int n3 = 338 - n2;
        final int n4 = 25;
        final String s = "   ";
        this.topic = new CheckboxGroup();
        (this.areaButton = new Checkbox("Total Area", this.topic, true)).setBounds(178, 0, 160, 20);
        this.areaButton.setFont(new Font("Serif", 1, 11));
        this.areaButton.setBackground(new Color(255, 220, 220));
        this.areaButton.addItemListener(this);
        panel.add(this.areaButton);
        (this.integralButton = new Checkbox("Definite Integral", this.topic, false)).setBounds(178, 20, 160, 20);
        this.integralButton.setFont(new Font("Serif", 1, 11));
        this.integralButton.setBackground(new Color(255, 220, 220));
        this.integralButton.addItemListener(this);
        panel.add(this.integralButton);
        (this.instructions = new Instructions(338, 60)).setBounds(0, 50, 338, 60);
        panel.add(this.instructions);
        (this.checks = new CheckMarks(20, 8 * n4)).setBounds(20, n, 20, 8 * n4);
        panel.add(this.checks);
        (this.cb1 = new Checkbox(String.valueOf(s) + "-I + II - III + IV + V")).setBounds(n2, n + 0 * n4, n3, 20);
        this.cb1.setFont(font);
        this.cb1.setBackground(new Color(255, 255, 200));
        this.cb1.addItemListener(this);
        panel.add(this.cb1);
        (this.cb2 = new Checkbox(String.valueOf(s) + "-I + II - III - IV + V")).setBounds(n2, n + n4, n3, 20);
        this.cb2.setFont(font);
        this.cb2.setBackground(new Color(255, 255, 200));
        this.cb2.addItemListener(this);
        panel.add(this.cb2);
        (this.cb3 = new Checkbox(String.valueOf(s) + "I + II + III + IV + V")).setBounds(n2, n + 2 * n4, n3, 20);
        this.cb3.setFont(font);
        this.cb3.setBackground(new Color(255, 255, 200));
        this.cb3.addItemListener(this);
        panel.add(this.cb3);
        (this.cb4 = new Checkbox(String.valueOf(s) + "|I| + |II| + |III| + |IV| + |V|")).setBounds(n2, n + 3 * n4, n3, 20);
        this.cb4.setFont(font);
        this.cb4.setBackground(new Color(255, 255, 200));
        this.cb4.addItemListener(this);
        panel.add(this.cb4);
        (this.cb5 = new Checkbox(String.valueOf(s) + "|-I - II - III - IV - V|")).setBounds(n2, n + 4 * n4, n3, 20);
        this.cb5.setFont(font);
        this.cb5.setBackground(new Color(255, 255, 200));
        this.cb5.addItemListener(this);
        panel.add(this.cb5);
        (this.cb6 = new Checkbox(String.valueOf(s) + "-I + II - III + IV - V")).setBounds(n2, n + 5 * n4, n3, 20);
        this.cb6.setFont(font);
        this.cb6.setBackground(new Color(255, 255, 200));
        this.cb6.addItemListener(this);
        panel.add(this.cb6);
        (this.cb7 = new Checkbox(String.valueOf(s) + "|-I| + II + |-III| + |-IV| + V")).setBounds(n2, n + 6 * n4, n3, 20);
        this.cb7.setFont(font);
        this.cb7.setBackground(new Color(255, 255, 200));
        this.cb7.addItemListener(this);
        panel.add(this.cb7);
        (this.cb8 = new Checkbox(String.valueOf(s) + "II + V - |I + III + IV|")).setBounds(n2, n + 7 * n4, n3, 20);
        this.cb8.setFont(font);
        this.cb8.setBackground(new Color(255, 255, 200));
        this.cb8.addItemListener(this);
        panel.add(this.cb8);
        this.add(panel);
        final Panel panel2 = new Panel();
        panel2.setBounds(0, n + 8 * n4, 338, 30);
        panel2.setBackground(new Color(255, 255, 200));
        panel.add(panel2);
        (this.checkButton = new Button("Check Answers")).setBackground(new Color(100, 255, 100));
        this.checkButton.setFont(new Font("SansSerif", 1, 12));
        this.checkButton.addActionListener(this);
        panel2.add(this.checkButton);
        final Copyright copyright = new Copyright();
        copyright.setBounds(0, 565, 342, 25);
        this.add(copyright);
        this.graph.setGraphics(this.graph.getGraphics());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.checkButton) {
            this.checkAnswers();
            this.repaint();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.answers = null;
        this.instructions.repaint();
        this.checks.repaint();
    }
    
    private void checkAnswers() {
        if (this.areaButton.getState()) {
            this.answers = new int[8];
            if (this.cb1.getState()) {
                this.answers[0] = 1;
            }
            else {
                this.answers[0] = 2;
            }
            if (this.cb2.getState()) {
                this.answers[1] = 1;
            }
            else {
                this.answers[1] = 2;
            }
            if (this.cb3.getState()) {
                this.answers[2] = 0;
            }
            else {
                this.answers[2] = 1;
            }
            if (this.cb4.getState()) {
                this.answers[3] = 0;
            }
            else {
                this.answers[3] = 1;
            }
            if (this.cb5.getState()) {
                this.answers[4] = 0;
            }
            else {
                this.answers[4] = 1;
            }
            if (this.cb6.getState()) {
                this.answers[5] = 1;
            }
            else {
                this.answers[5] = 2;
            }
            if (this.cb7.getState()) {
                this.answers[6] = 0;
            }
            else {
                this.answers[6] = 1;
            }
            if (this.cb8.getState()) {
                this.answers[7] = 1;
            }
            else {
                this.answers[7] = 2;
            }
            this.checks.repaint();
        }
        else {
            this.answers = new int[8];
            if (this.cb1.getState()) {
                this.answers[0] = 1;
            }
            else {
                this.answers[0] = 2;
            }
            if (this.cb2.getState()) {
                this.answers[1] = 0;
            }
            else {
                this.answers[1] = 1;
            }
            if (this.cb3.getState()) {
                this.answers[2] = 1;
            }
            else {
                this.answers[2] = 2;
            }
            if (this.cb4.getState()) {
                this.answers[3] = 1;
            }
            else {
                this.answers[3] = 2;
            }
            if (this.cb5.getState()) {
                this.answers[4] = 1;
            }
            else {
                this.answers[4] = 2;
            }
            if (this.cb6.getState()) {
                this.answers[5] = 1;
            }
            else {
                this.answers[5] = 2;
            }
            if (this.cb7.getState()) {
                this.answers[6] = 1;
            }
            else {
                this.answers[6] = 2;
            }
            if (this.cb8.getState()) {
                this.answers[7] = 0;
            }
            else {
                this.answers[7] = 1;
            }
            this.checks.repaint();
        }
    }
    
    public IntegralProps() {
        this.f = new Function("-(x+4)*(x+2)*(x-1)*(x-3.5)/25");
        this.fPrime = this.f.derivative();
        this.a = 0.0;
        this.b = 4.0;
    }
    
    static /* synthetic */ void access$2(final IntegralProps integralProps, final Function fPrime) {
        integralProps.fPrime = fPrime;
    }
    
    public class PlotCanvas extends GraphCanvas
    {
        public PlotCanvas(final int n, final int n2) {
            super(n, n2);
            this.setWindow(-5.0, 5.0, -3.0, 2.0);
            this.setBackground(new Color(255, 255, 230));
        }
        
        public void paint(final Graphics graphics) {
            this.setGraphics(graphics);
            super.ghostRectangles = false;
            graphics.setColor(new Color(100, 100, 100));
            this.drawAxes();
            this.skipDiscontinuities(true);
            graphics.setColor(new Color(0, 0, 200));
            IntegralProps.access$2(IntegralProps.this, IntegralProps.this.f.derivative());
            for (double xMin = this.xMin(); xMin <= this.xMax(); xMin += this.st(xMin, IntegralProps.this.f, IntegralProps.this.fPrime)) {
                this.plot(xMin, IntegralProps.this.f.value(xMin));
            }
            this.penUp();
            graphics.setColor(new Color(200, 255, 200));
            for (int i = this.xTOh(-4.0); i <= this.xTOh(-2.0); ++i) {
                graphics.drawLine(i, this.yTOv(0.0), i, this.yTOv(IntegralProps.this.f.value(this.hTOx(i))));
            }
            graphics.setColor(new Color(230, 255, 230));
            for (int j = this.xTOh(-2.0); j <= this.xTOh(0.0); ++j) {
                graphics.drawLine(j, this.yTOv(0.0), j, this.yTOv(IntegralProps.this.f.value(this.hTOx(j))));
            }
            graphics.setColor(new Color(255, 210, 210));
            for (int k = this.xTOh(0.0); k <= this.xTOh(1.0); ++k) {
                graphics.drawLine(k, this.yTOv(0.0), k, this.yTOv(IntegralProps.this.f.value(this.hTOx(k))));
            }
            graphics.setColor(new Color(255, 150, 150));
            for (int l = this.xTOh(1.0); l <= this.xTOh(3.5); ++l) {
                graphics.drawLine(l, this.yTOv(0.0), l, this.yTOv(IntegralProps.this.f.value(this.hTOx(l))));
            }
            graphics.setColor(new Color(200, 200, 255));
            for (int xtOh = this.xTOh(3.5); xtOh <= this.xTOh(4.0); ++xtOh) {
                graphics.drawLine(xtOh, this.yTOv(0.0), xtOh, this.yTOv(IntegralProps.this.f.value(this.hTOx(xtOh))));
            }
            graphics.setColor(new Color(100, 100, 100));
            this.drawAxes();
            graphics.setColor(new Color(255, 255, 230));
            graphics.fillRect(this.xTOh(-4.0) - 6, this.yTOv(0.0) + 2, 12, 12);
            this.skipDiscontinuities(true);
            graphics.setColor(new Color(0, 0, 200));
            IntegralProps.access$2(IntegralProps.this, IntegralProps.this.f.derivative());
            for (double xMin2 = this.xMin(); xMin2 <= this.xMax(); xMin2 += this.st(xMin2, IntegralProps.this.f, IntegralProps.this.fPrime)) {
                this.plot(xMin2, IntegralProps.this.f.value(xMin2));
            }
            this.penUp();
            graphics.setFont(new Font("Serif", 1, 12));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int charWidth = fontMetrics.charWidth('I');
            final int stringWidth = fontMetrics.stringWidth("II");
            final int stringWidth2 = fontMetrics.stringWidth("III");
            final int stringWidth3 = fontMetrics.stringWidth("IV");
            final int charWidth2 = fontMetrics.charWidth('V');
            graphics.setColor(new Color(0, 0, 100));
            graphics.drawString("I", this.xTOh(3.9) - charWidth / 2, this.yTOv(-1.0));
            graphics.drawString("II", this.xTOh(2.4) - stringWidth / 2, this.yTOv(0.8));
            graphics.drawString("III", this.xTOh(0.4) - stringWidth2 / 2, this.yTOv(-0.5));
            graphics.drawString("IV", this.xTOh(-0.8) - stringWidth3 / 2, this.yTOv(-0.7));
            graphics.drawString("V", this.xTOh(-3.0) - charWidth2 / 2, this.yTOv(0.4));
            graphics.setColor(new Color(0, 0, 150));
            graphics.drawString("y = f(x)", this.xTOh(-2.9), this.yTOv(1.2));
            graphics.setFont(new Font("Serif", 1, 14));
            graphics.drawString("b", this.xTOh(4.0) - fontMetrics.charWidth('b') / 2, this.yTOv(0.0) - 4);
            graphics.drawString("a", this.xTOh(-4.0) + 2, this.yTOv(0.0) + 2 * fontMetrics.getHeight() / 3);
        }
    }
    
    public class Title extends GraphCanvas
    {
        public Title(final int n, final int n2) {
            super(n, n2);
            this.setBackground(new Color(255, 200, 200));
        }
        
        public void paint(final Graphics graphics) {
            this.setGraphics(graphics);
            graphics.setColor(new Color(0, 0, 100));
            graphics.setFont(new Font("SansSerif", 1, 14));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            final String s = "Properties of Definite Integrals";
            graphics.drawString(s, 169 - fontMetrics.stringWidth(s) / 2, height);
        }
    }
    
    public class Instructions extends GraphCanvas
    {
        public Instructions(final int n, final int n2) {
            super(n, n2);
            this.setBackground(new Color(255, 255, 200));
        }
        
        public void paint(final Graphics graphics) {
            this.setGraphics(graphics);
            graphics.setColor(new Color(0, 0, 100));
            graphics.setFont(new Font("SansSerif", 0, 11));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            int n = 0;
            if (IntegralProps.this.areaButton.getState()) {
                final String s = "Check those boxes below that represent the";
                final String s2 = "TOTAL AREA bounded by the graph of y = f(x)";
                final String s3 = "and the x-axis on the interval a <= x <= b.";
                if (fontMetrics.stringWidth(s) > n) {
                    n = fontMetrics.stringWidth(s);
                }
                if (fontMetrics.stringWidth(s2) > n) {
                    n = fontMetrics.stringWidth(s2);
                }
                if (fontMetrics.stringWidth(s3) > n) {
                    n = fontMetrics.stringWidth(s3);
                }
                graphics.drawString(s, 169 - n / 2, height);
                graphics.drawString(s2, 169 - n / 2, 2 * height);
                graphics.drawString(s3, 169 - n / 2, 3 * height);
            }
            else {
                final String s4 = "Check those boxes below that represent the";
                final String s5 = "value of the DEFINITE INTEGRAL of f(x) on";
                final String s6 = "the interval a <= x <= b.";
                if (fontMetrics.stringWidth(s4) > n) {
                    n = fontMetrics.stringWidth(s4);
                }
                if (fontMetrics.stringWidth(s5) > n) {
                    n = fontMetrics.stringWidth(s5);
                }
                if (fontMetrics.stringWidth(s6) > n) {
                    n = fontMetrics.stringWidth(s6);
                }
                graphics.drawString(s4, 169 - n / 2, height);
                graphics.drawString(s5, 169 - n / 2, 2 * height);
                graphics.drawString(s6, 169 - n / 2, 3 * height);
            }
            graphics.drawRect(169 - n / 2 - 6, 0, n + 12, 7 * height / 2);
        }
    }
    
    public class CheckMarks extends GraphCanvas
    {
        public CheckMarks(final int n, final int n2) {
            super(n, n2);
            this.setBackground(new Color(255, 255, 200));
        }
        
        public void paint(final Graphics graphics) {
            this.setGraphics(graphics);
            for (int i = 0; i < 8; ++i) {
                this.drawMark(i);
            }
        }
        
        private void drawMark(final int n) {
            if (IntegralProps.this.answers == null) {
                return;
            }
            if (IntegralProps.this.answers[n] == 0) {
                super.g.setColor(Color.green);
                final int n2 = 4;
                final int n3 = 25 * n + 9;
                super.g.drawLine(n2, n3, n2 + 4, n3 + 4);
                super.g.drawLine(n2 + 1, n3, n2 + 5, n3 + 4);
                super.g.drawLine(n2 + 4, n3 + 4, n2 + 11, n3 - 7);
                super.g.drawLine(n2 + 5, n3 + 4, n2 + 12, n3 - 7);
            }
            else if (IntegralProps.this.answers[n] == 1) {
                super.g.setColor(Color.red);
                final int n4 = 4;
                final int n5 = 25 * n + 4;
                super.g.drawLine(n4, n5, n4 + 11, n5 + 11);
                super.g.drawLine(n4 + 1, n5, n4 + 12, n5 + 11);
                super.g.drawLine(n4 + 11, n5, n4, n5 + 11);
                super.g.drawLine(n4 + 12, n5, n4 + 1, n5 + 11);
            }
        }
    }
}
