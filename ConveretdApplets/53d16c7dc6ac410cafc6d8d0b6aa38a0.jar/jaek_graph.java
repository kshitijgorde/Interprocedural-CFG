import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jaek_graph extends Applet
{
    private String linesep;
    private Graph graph;
    private ScrollPane scrollPane;
    private ScrollableCanvas canvas;
    private boolean bTooltip;
    private Tooltip tooltip;
    private Util u;
    private boolean bJava2;
    
    public jaek_graph() {
        this.linesep = null;
        this.graph = null;
        this.scrollPane = null;
        this.canvas = null;
        this.bTooltip = false;
        this.tooltip = null;
        this.u = null;
        this.bJava2 = false;
    }
    
    public boolean parmBoolean(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        return "YES".equals(parameter) || (!"NO".equals(parameter) && b);
    }
    
    public void init() {
        this.linesep = System.getProperty("line.separator");
        this.setBackground(Color.white);
        try {
            Class.forName("java.awt.Graphics2D");
            this.u = new Util2();
            this.bJava2 = true;
        }
        catch (Throwable t) {
            this.u = new Util();
            this.bJava2 = false;
        }
        this.graph = new Graph();
        this.canvas = new ScrollableCanvas();
        this.setLayout(new BorderLayout());
        (this.scrollPane = new ScrollPane()).add(this.canvas);
        this.add("Center", this.scrollPane);
        this.bTooltip = this.parmBoolean("TOOLTIP", true);
        if (this.bTooltip) {
            this.canvas.addMouseMotionListener(new mouseMot(this.graph.lines));
        }
    }
    
    private void draw_frame(final Graphics graphics, final Rectangle rectangle) {
        graphics.setColor(Color.darkGray);
        graphics.drawLine(rectangle.x + this.graph.margext, rectangle.y + this.graph.margext, rectangle.x + this.graph.margext, rectangle.y + rectangle.height + this.graph.margext);
        graphics.drawLine(rectangle.x + this.graph.margext, rectangle.y + rectangle.height + this.graph.margext, rectangle.x + rectangle.width + this.graph.margext, rectangle.y + rectangle.height + this.graph.margext);
    }
    
    public String getAppletInfo() {
        return "JAEK GRAPH 1.0";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Applet", "JAEK GRAPH 1.0", "" }, { "Version", "1.0", "" }, { "Copyright", "2002 - Emmanuel Keller", "" }, { "Email", "ekeller@mac.com", "" } };
    }
    
    private class ScrollableCanvas extends Canvas
    {
        public Dimension getMinimumSize() {
            return new Dimension(0, 0);
        }
        
        public Dimension getPreferredSize() {
            if (jaek_graph.this.graph == null) {
                return this.getMinimumSize();
            }
            if (jaek_graph.this.graph.totalDim == null) {
                return this.getMinimumSize();
            }
            return jaek_graph.this.graph.totalDim;
        }
        
        public void paint(final Graphics graphics) {
            jaek_graph.this.graph.draw(graphics);
            if (jaek_graph.this.tooltip != null) {
                jaek_graph.this.tooltip.draw(graphics);
            }
        }
    }
    
    private class Graph
    {
        private Title title;
        private Lines lines;
        private YLabels ylabels;
        private XLabels xlabels;
        private Legend legend;
        protected int margext;
        private boolean bCalculated;
        protected Rectangle rGraph;
        protected Dimension totalDim;
        
        public Graph() {
            this.title = null;
            this.lines = null;
            this.ylabels = null;
            this.xlabels = null;
            this.legend = null;
            this.margext = 6;
            this.bCalculated = false;
            this.title = new Title();
            this.lines = new Lines();
            this.ylabels = new YLabels();
            if (jaek_graph.this.bJava2) {
                this.xlabels = new XLabels2();
            }
            else {
                this.xlabels = new XLabels();
            }
            this.legend = new Legend();
        }
        
        public void calc(final Graphics graphics) {
            final Font font = graphics.getFont();
            this.title.calc(graphics);
            this.xlabels.calc(graphics);
            int n = jaek_graph.this.getSize().height - this.title.getHeight() - this.xlabels.getHeight() - this.margext * 2 - 20;
            if (n < 40) {
                n = 40;
            }
            final int n2 = n + this.title.getHeight() + this.xlabels.getHeight() + this.margext * 2;
            this.ylabels.calc(graphics, this.lines.min_value, this.lines.max_value, n);
            this.legend.calc(graphics, this.lines);
            final int n3 = this.ylabels.getWidth() + this.lines.getWidth() + this.legend.getWidth() + this.margext * 2;
            this.rGraph = new Rectangle(this.ylabels.getWidth(), this.title.getHeight(), this.lines.getWidth(), n);
            this.totalDim = new Dimension(n3, n2);
            graphics.setFont(font);
            this.bCalculated = true;
            jaek_graph.this.scrollPane.doLayout();
        }
        
        public void draw(final Graphics graphics) {
            if (!this.bCalculated) {
                this.calc(graphics);
            }
            final Font font = graphics.getFont();
            this.title.draw(graphics, this.rGraph);
            this.xlabels.draw(graphics, this.rGraph, this.lines.x_width);
            this.ylabels.draw(graphics, this.lines, this.rGraph);
            this.legend.draw(graphics, this.lines, this.rGraph);
            this.lines.draw(graphics, this.rGraph);
            jaek_graph.this.draw_frame(graphics, this.rGraph);
            graphics.setFont(font);
        }
    }
    
    private class Title
    {
        private String name;
        private Rectangle rect;
        private boolean bAffTitle;
        
        public Title() {
            this.name = null;
            this.rect = null;
            this.name = jaek_graph.this.getParameter("TITLE");
            this.bAffTitle = jaek_graph.this.parmBoolean("DRAW_TITLE", true);
        }
        
        public int getWidth() {
            if (!this.bAffTitle || this.rect == null) {
                return 0;
            }
            return this.rect.width;
        }
        
        public int getHeight() {
            if (!this.bAffTitle || this.rect == null) {
                return 0;
            }
            return this.rect.height + 5;
        }
        
        private void setGraph(final Graphics graphics) {
            graphics.setColor(Color.darkGray);
            jaek_graph.this.u.deriveFont(graphics, 1, 11);
        }
        
        private void calc(final Graphics graph) {
            if (this.name == null || !this.bAffTitle) {
                return;
            }
            this.setGraph(graph);
            this.rect = jaek_graph.this.u.calcTextBounds(graph, this.name);
        }
        
        private void draw(final Graphics graph, final Rectangle rectangle) {
            if (this.name == null || !this.bAffTitle) {
                return;
            }
            this.setGraph(graph);
            int x = (rectangle.width - this.rect.width) / 2 + rectangle.x;
            if (x < rectangle.x) {
                x = rectangle.x;
            }
            graph.drawString(this.name, x + jaek_graph.this.graph.margext, this.rect.height + jaek_graph.this.graph.margext);
        }
    }
    
    private class Util
    {
        public Rectangle calcTextBounds(final Graphics graphics, final String s) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            return new Rectangle(0, -fontMetrics.getMaxAscent(), fontMetrics.stringWidth(s), fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent());
        }
        
        public void deriveFont(final Graphics graphics, final int n, final int n2) {
            graphics.setFont(new Font(graphics.getFont().getName(), n, n2));
        }
        
        public Object[] arrayAddObject(final Object[] array, final Object o) {
            Object[] array2;
            if (array == null) {
                array2 = new Object[] { null };
            }
            else {
                array2 = new Object[array.length + 1];
                for (int i = 0; i < array.length; ++i) {
                    array2[i] = array[i];
                }
            }
            array2[array2.length - 1] = o;
            return array2;
        }
    }
    
    private class Lines
    {
        private double max_value;
        private double min_value;
        private double mid_value;
        private int x_max_count;
        private int x_width;
        private int n_values;
        private Line[] line;
        private int n_color;
        private Color[] colors;
        
        public Lines() {
            this.n_color = 0;
            this.colors = new Color[] { Color.blue, Color.green, Color.red, Color.cyan, Color.yellow, Color.magenta, Color.orange, Color.pink };
            this.max_value = 0.0;
            this.min_value = 0.0;
            this.mid_value = 0.0;
            this.x_max_count = 0;
            this.x_width = 12;
            this.n_values = 0;
            final String parameter = jaek_graph.this.getParameter("X_WIDTH");
            if (parameter != null) {
                this.x_width = Integer.parseInt(parameter);
            }
            if (this.x_width < 1) {
                this.x_width = 12;
            }
            int n;
            for (n = 0; jaek_graph.this.getParameter("L" + (n + 1) + "_NAME") != null; ++n) {}
            this.line = new Line[n];
            for (int i = 0; i < this.line.length; ++i) {
                this.line[i] = new Line("L" + (i + 1) + "_", this, i);
                if (this.line[i].value.length > this.x_max_count) {
                    this.x_max_count = this.line[i].value.length;
                }
                if (this.line[i].max_value > this.max_value) {
                    this.max_value = this.line[i].max_value;
                }
                if (this.line[i].min_value < this.min_value) {
                    this.min_value = this.line[i].min_value;
                }
                if (this.line[i].value.length > this.n_values) {
                    this.n_values = this.line[i].value.length;
                }
            }
        }
        
        public Color nextColor() {
            return this.colors[this.n_color++ % this.colors.length];
        }
        
        public int getWidth() {
            return (this.x_max_count - 1) * this.x_width;
        }
        
        public void draw(final Graphics graphics, final Rectangle rectangle) {
            for (int i = 0; i < this.line.length; ++i) {
                this.line[i].draw_grid(graphics, rectangle, this.x_width, this);
            }
            for (int j = 0; j < this.line.length; ++j) {
                this.line[j].draw_line(graphics, rectangle, this.x_width, this);
            }
        }
        
        public double getYvalue(final double n, final Rectangle rectangle) {
            return rectangle.y + rectangle.height - rectangle.height / (this.max_value - this.min_value) * (n - this.min_value);
        }
    }
    
    private class Line
    {
        private double max_value;
        private double min_value;
        private double[] value;
        private Color color;
        private String name;
        private boolean bAffXGrid;
        private boolean bAffBullet;
        private Rectangle[] rect;
        
        Line(final String s, final Lines lines, final int n) {
            this.max_value = 0.0;
            this.min_value = 0.0;
            this.bAffBullet = jaek_graph.this.parmBoolean(s + "BULLET", true);
            this.bAffXGrid = jaek_graph.this.parmBoolean("X_GRID", true);
            this.name = jaek_graph.this.getParameter(s + "NAME");
            final String parameter = jaek_graph.this.getParameter(s + "COLOR");
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                if (stringTokenizer.countTokens() == 3) {
                    this.color = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                }
            }
            if (this.color == null) {
                this.color = lines.nextColor();
            }
            final String parameter2 = jaek_graph.this.getParameter(s + "COMPUTE");
            if (parameter2 == null) {
                this.paramsValues(s);
            }
            else {
                this.computeValues(parameter2, lines, n);
            }
            for (int i = 0; i < this.value.length; ++i) {
                if (this.value[i] > this.max_value) {
                    this.max_value = this.value[i];
                }
                if (this.value[i] < this.min_value) {
                    this.min_value = this.value[i];
                }
            }
        }
        
        private void paramsValues(final String s) {
            int n;
            for (n = 0; jaek_graph.this.getParameter(s + "V" + (n + 1)) != null; ++n) {}
            this.rect = new Rectangle[n];
            this.value = new double[n];
            for (int i = 0; i < n; ++i) {
                this.value[i] = new Double(jaek_graph.this.getParameter(s + "V" + (i + 1)));
            }
        }
        
        private void computeValues(final String s, final Lines lines, final int n) {
            this.rect = new Rectangle[lines.n_values];
            this.value = new double[lines.n_values];
            for (int i = 0; i < this.value.length; ++i) {
                this.value[i] = new Compute(s, i, lines, n).value;
            }
        }
        
        private void draw_grid(final Graphics graphics, final Rectangle rectangle, final int n, final Lines lines) {
            if (!this.bAffXGrid) {
                return;
            }
            graphics.setColor(Color.lightGray);
            final double yvalue = lines.getYvalue(lines.mid_value, rectangle);
            for (int i = 0; i < this.value.length; ++i) {
                final int n2 = rectangle.x + i * n;
                graphics.drawLine(n2 + jaek_graph.this.graph.margext, (int)(yvalue + jaek_graph.this.graph.margext), n2 + jaek_graph.this.graph.margext, (int)(lines.getYvalue(this.value[i], rectangle) + jaek_graph.this.graph.margext));
            }
        }
        
        private void draw_line(final Graphics graphics, final Rectangle rectangle, final int n, final Lines lines) {
            Point point = null;
            graphics.setColor(this.color);
            for (int i = 0; i < this.value.length; ++i) {
                final Point point2 = new Point(rectangle.x + i * n, (int)lines.getYvalue(this.value[i], rectangle));
                this.rect[i] = new Rectangle(point2.x - 2 + jaek_graph.this.graph.margext, point2.y - 2 + jaek_graph.this.graph.margext, 4, 4);
                if (this.bAffBullet) {
                    graphics.drawRect(this.rect[i].x, this.rect[i].y, this.rect[i].width, this.rect[i].height);
                }
                if (point != null) {
                    graphics.drawLine(point.x + jaek_graph.this.graph.margext, point.y + jaek_graph.this.graph.margext, point2.x + jaek_graph.this.graph.margext, point2.y + jaek_graph.this.graph.margext);
                }
                point = point2;
            }
        }
        
        private class Compute
        {
            private int pos;
            private int stade;
            private char[] ac;
            private char last_op;
            private double value;
            
            Compute(final String s, final int n, final Lines lines, final int n2) {
                this.pos = 0;
                this.stade = 0;
                this.last_op = '\0';
                this.value = 0.0;
                this.value = 0.0;
                this.ac = s.toCharArray();
                while (this.stade != 99 && this.pos < this.ac.length) {
                    if (this.ac[this.pos] == ' ') {
                        ++this.pos;
                    }
                    else {
                        switch (this.stade) {
                            case 1: {
                                if (!this.findOperator()) {
                                    this.stade = 99;
                                    continue;
                                }
                                this.stade = 0;
                                continue;
                            }
                            default: {
                                continue;
                            }
                            case 0: {
                                if (this.findCommand("L")) {
                                    this.commandLine(n, lines, n2);
                                }
                                else if (this.findCommand("CUMUL")) {
                                    this.commandCumul(n, lines, n2);
                                }
                                else {
                                    final Double value = this.findValue(true);
                                    if (value == null) {
                                        this.stade = 99;
                                        continue;
                                    }
                                    if (!this.doOp(value)) {
                                        this.stade = 99;
                                        continue;
                                    }
                                }
                                this.stade = 1;
                                continue;
                            }
                            case 99: {
                                System.err.println("Syntax error.");
                                continue;
                            }
                        }
                    }
                }
            }
            
            private Double findValue(final boolean b) {
                String string = "";
                boolean b2 = true;
                while (b2 && this.pos < this.ac.length) {
                    switch (this.ac[this.pos]) {
                        case '.': {
                            if (!b) {
                                b2 = false;
                                continue;
                            }
                        }
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            string += this.ac[this.pos];
                            ++this.pos;
                            continue;
                        }
                        default: {
                            b2 = false;
                            continue;
                        }
                    }
                }
                if (string.length() > 0) {
                    return new Double(string);
                }
                return null;
            }
            
            private boolean findCommand(final String s) {
                final char[] charArray = s.toCharArray();
                if (this.ac.length - this.pos < charArray.length) {
                    return false;
                }
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] != this.ac[this.pos + i]) {
                        return false;
                    }
                }
                this.pos += charArray.length;
                return true;
            }
            
            private boolean findOperator() {
                switch (this.ac[this.pos]) {
                    case '*':
                    case '+':
                    case '-':
                    case '/': {
                        this.last_op = this.ac[this.pos];
                        ++this.pos;
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
            
            private int findLineNumber(final int n) {
                final Double value = this.findValue(false);
                if (value == null) {
                    System.err.println("No line number");
                    this.stade = 99;
                    return -1;
                }
                final int n2 = (int)(Object)value - 1;
                if (n2 < 0 || n2 >= n) {
                    System.err.println("Bad line number");
                    this.stade = 99;
                    return -1;
                }
                return n2;
            }
            
            private boolean commandLine(final int n, final Lines lines, final int n2) {
                final int lineNumber = this.findLineNumber(n2);
                if (lineNumber < 0) {
                    System.err.println("Error in function line (L)");
                    this.stade = 99;
                    return false;
                }
                return this.doOp(lines.line[lineNumber].value[n]);
            }
            
            private boolean commandCumul(final int n, final Lines lines, final int n2) {
                final int lineNumber = this.findLineNumber(n2);
                if (lineNumber >= n2 || lineNumber < 0) {
                    System.err.println("Error in function CUMUL");
                    this.stade = 99;
                    return false;
                }
                double n3 = 0.0;
                for (int i = 0; i <= n; ++i) {
                    n3 += lines.line[lineNumber].value[i];
                }
                return this.doOp(n3);
            }
            
            private boolean doOp(final double value) {
                switch (this.last_op) {
                    case '\0': {
                        this.value = value;
                        break;
                    }
                    case '+': {
                        this.value += value;
                        break;
                    }
                    case '-': {
                        this.value -= value;
                        break;
                    }
                    case '*': {
                        this.value *= value;
                    }
                    case '/': {
                        this.value /= value;
                        break;
                    }
                    default: {
                        return false;
                    }
                }
                this.last_op = '\0';
                return true;
            }
        }
    }
    
    private class YLabels
    {
        private boolean bAffLabel;
        private boolean bAffGrid;
        private Object[] label;
        private int[] width;
        private int max_width;
        
        public YLabels() {
            this.bAffLabel = jaek_graph.this.parmBoolean("Y_LABEL", true);
            this.bAffGrid = jaek_graph.this.parmBoolean("Y_GRID", true);
            this.max_width = 0;
        }
        
        public int getWidth() {
            if (!this.bAffLabel) {
                return 0;
            }
            return this.max_width + 5;
        }
        
        private void setGraphs(final Graphics graphics) {
            jaek_graph.this.u.deriveFont(graphics, 0, 11);
            graphics.setColor(Color.lightGray);
        }
        
        private void calc(final Graphics graphs, final double n, final double n2, final int n3) {
            this.setGraphs(graphs);
            if (n < 0.0 && n2 > 0.0) {
                this.label = jaek_graph.this.u.arrayAddObject(this.label, new Double(0.0));
            }
            this.label = jaek_graph.this.u.arrayAddObject(this.label, new Double(n));
            this.label = jaek_graph.this.u.arrayAddObject(this.label, new Double(n2));
            this.max_width = 0;
            this.width = new int[this.label.length];
            for (int i = 0; i < this.label.length; ++i) {
                this.width[i] = jaek_graph.this.u.calcTextBounds(graphs, this.label[i].toString()).width;
                if (this.width[i] > this.max_width) {
                    this.max_width = this.width[i];
                }
            }
        }
        
        private void draw(final Graphics graphs, final Lines lines, final Rectangle rectangle) {
            this.setGraphs(graphs);
            final int n = rectangle.y + rectangle.height;
            for (int i = 0; i < this.label.length; ++i) {
                final double yvalue = lines.getYvalue((double)this.label[i], rectangle);
                final int n2 = rectangle.x - this.width[i] - 2;
                if (this.bAffLabel) {
                    graphs.drawString(this.label[i].toString(), n2 + jaek_graph.this.graph.margext, (int)(yvalue + 5.0 + jaek_graph.this.graph.margext));
                }
                if (this.bAffGrid) {
                    graphs.drawLine(rectangle.x + jaek_graph.this.graph.margext, (int)(yvalue + jaek_graph.this.graph.margext), rectangle.x + rectangle.width + jaek_graph.this.graph.margext, (int)(yvalue + jaek_graph.this.graph.margext));
                }
            }
        }
    }
    
    private class XLabels
    {
        protected boolean bAffLabel;
        protected String[] label;
        protected int max_height;
        
        public XLabels() {
            this.bAffLabel = jaek_graph.this.parmBoolean("X_LABEL", true);
            int n;
            for (n = 0; jaek_graph.this.getParameter("LBL" + (n + 1)) != null; ++n) {}
            this.label = new String[n];
            this.max_height = 0;
            for (int i = 0; i < this.label.length; ++i) {
                this.label[i] = jaek_graph.this.getParameter("LBL" + (i + 1));
            }
        }
        
        public int getHeight() {
            if (!this.bAffLabel) {
                return 0;
            }
            return this.max_height + 5;
        }
        
        public void setGraph(final Graphics graphics, final int n) {
            jaek_graph.this.u.deriveFont(graphics, 0, n);
            graphics.setColor(Color.darkGray);
        }
        
        public void calc(final Graphics graphics) {
            if (!this.bAffLabel) {
                return;
            }
            this.setGraph(graphics, 11);
            for (int i = 0; i < this.label.length; ++i) {
                final int length = this.label[i].length();
                int max_height = 0;
                for (int j = 0; j < length; ++j) {
                    max_height += -jaek_graph.this.u.calcTextBounds(graphics, this.label[i].substring(j, j + 1)).y;
                }
                if (max_height > this.max_height) {
                    this.max_height = max_height;
                }
            }
        }
        
        public void draw(final Graphics graphics, final Rectangle rectangle, final int n) {
            if (!this.bAffLabel) {
                return;
            }
            this.setGraph(graphics, 11);
            for (int i = 0; i < this.label.length; ++i) {
                final int n2 = i * n + rectangle.x;
                int n3 = rectangle.y + rectangle.height + 2;
                for (int length = this.label[i].length(), j = 0; j < length; ++j) {
                    final String substring = this.label[i].substring(j, j + 1);
                    final Rectangle calcTextBounds = jaek_graph.this.u.calcTextBounds(graphics, substring);
                    if (j == 0) {
                        n3 -= calcTextBounds.y;
                    }
                    graphics.drawString(substring, jaek_graph.this.graph.margext + n2 - calcTextBounds.width / 2, n3 + jaek_graph.this.graph.margext);
                    n3 -= calcTextBounds.y;
                }
            }
        }
    }
    
    private class Legend
    {
        private boolean bAff;
        private int max_width;
        private int[] width;
        
        public Legend() {
            this.bAff = jaek_graph.this.parmBoolean("LEGEND", true);
        }
        
        public int getWidth() {
            if (!this.bAff) {
                return 0;
            }
            return this.max_width + 5;
        }
        
        public void setGraph(final Graphics graphics, final Line line) {
            graphics.setColor(line.color);
            jaek_graph.this.u.deriveFont(graphics, 0, 11);
        }
        
        public void calc(final Graphics graphics, final Lines lines) {
            if (!this.bAff) {
                return;
            }
            this.width = new int[lines.line.length];
            for (int i = 0; i < lines.line.length; ++i) {
                if (lines.line[i].name != null) {
                    this.setGraph(graphics, lines.line[i]);
                    this.width[i] = jaek_graph.this.u.calcTextBounds(graphics, lines.line[i].name).width;
                    if (this.width[i] + 2 > this.max_width) {
                        this.max_width = this.width[i] + 2;
                    }
                }
            }
        }
        
        private void draw(final Graphics graphics, final Lines lines, final Rectangle rectangle) {
            if (!this.bAff) {
                return;
            }
            int n = rectangle.y + 11;
            final int n2 = rectangle.x + rectangle.width + 5;
            for (int i = 0; i < lines.line.length; ++i) {
                if (lines.line[i].name != null) {
                    this.setGraph(graphics, lines.line[i]);
                    graphics.drawString(lines.line[i].name, n2 + jaek_graph.this.graph.margext, n + jaek_graph.this.graph.margext);
                    n += 11;
                }
            }
        }
    }
    
    private class XLabels2 extends XLabels
    {
        protected int[] height;
        protected int[] width;
        
        public XLabels2() {
            this.height = new int[super.label.length];
            this.width = new int[super.label.length];
        }
        
        public AffineTransform enterGraph(final Graphics graphics) {
            final AffineTransform affineTransform = new AffineTransform();
            affineTransform.setToIdentity();
            affineTransform.rotate(Math.toRadians(270.0));
            return affineTransform;
        }
        
        public void calc(final Graphics graphics) {
            if (!super.bAffLabel) {
                return;
            }
            this.setGraph(graphics, 12);
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final AffineTransform transform = graphics2D.getTransform();
            graphics2D.transform(this.enterGraph(graphics));
            for (int i = 0; i < super.label.length; ++i) {
                final Rectangle calcTextBounds = jaek_graph.this.u.calcTextBounds(graphics, super.label[i]);
                this.width[i] = -calcTextBounds.y;
                this.height[i] = (int)calcTextBounds.getWidth();
                if (this.height[i] > super.max_height) {
                    super.max_height = this.height[i];
                }
            }
            graphics2D.setTransform(transform);
        }
        
        public void draw(final Graphics graphics, final Rectangle rectangle, final int n) {
            if (!super.bAffLabel) {
                return;
            }
            this.setGraph(graphics, 12);
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final AffineTransform transform = graphics2D.getTransform();
            graphics2D.transform(this.enterGraph(graphics2D));
            for (int i = 0; i < super.label.length; ++i) {
                graphics2D.drawString(super.label[i], -rectangle.height - this.height[i] - 2.0f - rectangle.y - jaek_graph.this.graph.margext, i * n + this.width[i] / 2 + rectangle.x + jaek_graph.this.graph.margext);
            }
            graphics2D.setTransform(transform);
        }
    }
    
    private class Tooltip
    {
        private Object[] liste;
        private Point pt;
        
        public Tooltip(final Point pt) {
            this.liste = null;
            this.pt = pt;
        }
        
        public void add(final String s, final Color color) {
            this.liste = jaek_graph.this.u.arrayAddObject(this.liste, new Object[] { s, color.darker() });
        }
        
        public boolean equals(final Tooltip tooltip) {
            if (this.pt.x != tooltip.pt.x || this.pt.y != tooltip.pt.y) {
                return false;
            }
            if (this.liste == null) {
                return false;
            }
            if (this.liste.length != tooltip.liste.length) {
                return false;
            }
            for (int i = 0; i < this.liste.length; ++i) {
                if (!((String)((Object[])this.liste[i])[0]).equals(((Object[])tooltip.liste[i])[0])) {
                    return false;
                }
            }
            return true;
        }
        
        public void draw(final Graphics graphics) {
            if (this.liste == null) {
                return;
            }
            jaek_graph.this.u.deriveFont(graphics, 0, 10);
            int y = this.pt.y;
            final int n = this.pt.x + 10;
            for (int i = 0; i < this.liste.length; ++i) {
                final Object[] array = (Object[])this.liste[i];
                final String s = (String)array[0];
                final Rectangle calcTextBounds = jaek_graph.this.u.calcTextBounds(graphics, s);
                calcTextBounds.setLocation(n, y + calcTextBounds.y);
                graphics.setColor(new Color(240, 240, 240));
                graphics.fillRect(calcTextBounds.x, calcTextBounds.y, calcTextBounds.width, calcTextBounds.height);
                graphics.setColor((Color)array[1]);
                graphics.drawString(s, n, y);
                y -= calcTextBounds.height;
            }
        }
    }
    
    public class mouseMot extends MouseMotionAdapter
    {
        private Lines lines;
        
        public mouseMot(final Lines lines) {
            this.lines = lines;
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (!jaek_graph.this.bTooltip) {
                if (jaek_graph.this.tooltip != null) {
                    jaek_graph.this.tooltip = null;
                    jaek_graph.this.canvas.repaint();
                }
                return;
            }
            if (this.lines == null) {
                return;
            }
            if (this.lines.line == null) {
                return;
            }
            final Point point = mouseEvent.getPoint();
            Tooltip tooltip = null;
            for (int i = 0; i < this.lines.line.length; ++i) {
                final Line line = this.lines.line[i];
                for (int j = 0; j < line.rect.length; ++j) {
                    if (line.rect[j].contains(point)) {
                        if (tooltip == null) {
                            tooltip = new Tooltip(point);
                        }
                        tooltip.add(Double.toString(line.value[j]), line.color);
                    }
                }
            }
            if (tooltip != null) {
                if (jaek_graph.this.tooltip == null) {
                    jaek_graph.this.tooltip = tooltip;
                    jaek_graph.this.canvas.repaint();
                }
                else if (!jaek_graph.this.tooltip.equals(tooltip)) {
                    jaek_graph.this.tooltip = tooltip;
                }
                jaek_graph.this.canvas.repaint();
            }
            else if (jaek_graph.this.tooltip != null) {
                jaek_graph.this.tooltip = null;
                jaek_graph.this.canvas.repaint();
            }
        }
    }
    
    private class Util2 extends Util
    {
        public Rectangle calcTextBounds(final Graphics graphics, final String s) {
            return graphics.getFont().getStringBounds(s, ((Graphics2D)graphics).getFontRenderContext()).getBounds();
        }
        
        public void deriveFont(final Graphics graphics, final int n, final int n2) {
            graphics.setFont(graphics.getFont().deriveFont(10.0f));
            graphics.setFont(graphics.getFont().deriveFont(0));
        }
    }
}
