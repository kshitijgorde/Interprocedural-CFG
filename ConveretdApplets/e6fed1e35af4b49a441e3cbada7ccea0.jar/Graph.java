import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Graph
{
    int Resolution;
    int refinement;
    boolean refine;
    int xpos;
    int ypos;
    int w;
    int h;
    Graphics g;
    private Color bg;
    private Color graphcolor;
    Color cursorcolor;
    double cursx;
    double cursy;
    private double a;
    private double b;
    private double c;
    private double d;
    private Function f;
    private double[][] z;
    private double[][] zz;
    private boolean[] sing;
    public Vector functions;
    public Vector graphs;
    public Vector graphcolors;
    public Vector keypoints;
    public Vector lines;
    private boolean newfunction;
    private boolean newdomain;
    public boolean paramchange;
    
    public Graph() {
        this.Resolution = 100;
        this.refinement = 0;
        this.refine = true;
        this.xpos = 0;
        this.ypos = 0;
        this.w = 0;
        this.h = 0;
        this.g = null;
        this.bg = Color.white;
        this.graphcolor = Color.black;
        this.cursorcolor = Color.orange;
        this.cursx = 0.0;
        this.cursy = 0.0;
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.paramchange = false;
        this.paramchange = false;
        this.functions = new Vector();
        this.graphs = new Vector();
        this.graphcolors = new Vector();
        this.keypoints = new Vector();
        this.lines = new Vector();
        this.sing = new boolean[this.Resolution];
        this.z = new double[2][this.Resolution];
        this.zoomto(0.0, 0.0, 0.0, 0.0);
    }
    
    public Graph(final Function function) {
        this.Resolution = 100;
        this.refinement = 0;
        this.refine = true;
        this.xpos = 0;
        this.ypos = 0;
        this.w = 0;
        this.h = 0;
        this.g = null;
        this.bg = Color.white;
        this.graphcolor = Color.black;
        this.cursorcolor = Color.orange;
        this.cursx = 0.0;
        this.cursy = 0.0;
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.paramchange = false;
        this.functions = new Vector();
        this.addFunction(function);
        this.graphs = new Vector();
        this.z = new double[2][this.Resolution];
        this.zoomto(0.0, 0.0, 0.0, 0.0);
    }
    
    public Graph(final Function function, final double n, final double n2, final double n3, final double n4) {
        this.Resolution = 100;
        this.refinement = 0;
        this.refine = true;
        this.xpos = 0;
        this.ypos = 0;
        this.w = 0;
        this.h = 0;
        this.g = null;
        this.bg = Color.white;
        this.graphcolor = Color.black;
        this.cursorcolor = Color.orange;
        this.cursx = 0.0;
        this.cursy = 0.0;
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.paramchange = false;
        this.functions = new Vector();
        this.addFunction(function);
        this.graphs = new Vector();
        this.z = new double[2][this.Resolution];
        this.zoomto(n, n2, n3, n4);
    }
    
    public void addFunction(final Function function) {
        this.functions.addElement(function);
        this.graphs.addElement(this.graphFunction(function));
        this.graphcolors.addElement(Color.black);
        this.newdomain = false;
    }
    
    public void delFunction(final int n) {
        this.functions.removeElementAt(n);
        this.graphs.removeElementAt(n);
        this.graphcolors.removeElementAt(n);
        this.newdomain = false;
    }
    
    public void delFunction(final Function function) {
        final int index = this.functions.indexOf(function);
        if (index != -1) {
            this.functions.removeElementAt(index);
            this.graphs.removeElementAt(index);
            this.graphcolors.removeElementAt(index);
        }
        this.newdomain = false;
    }
    
    public void insFunction(final Function function, final int n) {
        this.functions.insertElementAt(function, n);
        this.graphs.insertElementAt(this.graphFunction(function), n);
        this.graphcolors.insertElementAt(Color.black, n);
        this.newdomain = false;
    }
    
    public void clearFunctions() {
        this.functions.removeAllElements();
        this.graphs.removeAllElements();
        this.graphcolors.removeAllElements();
        this.newdomain = false;
    }
    
    public void addKeyPoint(final KeyPoynt keyPoynt) {
        this.keypoints.addElement(keyPoynt);
    }
    
    public void delKeyPoint(final int n) {
        this.keypoints.removeElementAt(n);
    }
    
    public void delKeyPoint(final KeyPoynt keyPoynt) {
        final int index = this.keypoints.indexOf(keyPoynt);
        if (index != -1) {
            this.keypoints.removeElementAt(index);
        }
    }
    
    public void insKeyPoint(final KeyPoynt keyPoynt, final int n) {
        this.keypoints.insertElementAt(keyPoynt, n);
    }
    
    public void clearKeyPoints() {
        this.keypoints.removeAllElements();
    }
    
    public void addLine(final Line line) {
        this.lines.addElement(line);
    }
    
    public void delLine(final int n) {
        this.lines.removeElementAt(n);
    }
    
    public void delLine(final Line line) {
        final int index = this.lines.indexOf(line);
        if (index != -1) {
            this.lines.removeElementAt(index);
        }
    }
    
    public void insLine(final Line line, final int n) {
        this.lines.insertElementAt(line, n);
    }
    
    public void clearLines() {
        this.lines.removeAllElements();
    }
    
    public void zoomto(final double a, final double b, final double c, final double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.newdomain = true;
    }
    
    public void zoom(final double n, final double n2, final double n3) {
        this.zoomto(n - n3 * (this.c - this.a) / 2.0, n2 - n3 * (this.d - this.b) / 2.0, n + n3 * (this.c - this.a) / 2.0, n2 + n3 * (this.d - this.b) / 2.0);
    }
    
    public void zoom(final double n, final double n2, final double n3, final double n4) {
        this.zoomto(n - n3 * (this.c - this.a) / 2.0, n2 - n4 * (this.d - this.b) / 2.0, n + n3 * (this.c - this.a) / 2.0, n2 + n4 * (this.d - this.b) / 2.0);
    }
    
    public void setbg(final Color bg) {
        this.bg = bg;
    }
    
    public void graphcolor(final Color graphcolor) {
        this.graphcolor = graphcolor;
    }
    
    public void display(final Graphics g, final int xpos, final int ypos, final int w, final int h, final int n) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.w = w;
        this.h = h;
        g.setColor(this.bg);
        g.fillRect(0, 0, w, h);
        this.g = g;
        this.showGrid();
        if (this.paramchange && this.functions.size() > 0) {
            for (int i = 0; i < this.functions.size(); ++i) {
                this.graphs.setElementAt(this.graphFunction((Function)this.functions.elementAt(i)), i);
            }
            this.paramchange = false;
        }
        else if (this.newdomain) {
            this.regraph();
            this.newdomain = false;
        }
        this.showGraphs(n);
        this.showKeyPoints();
        this.showLines();
        this.showCursor();
    }
    
    public int Xpos(final double n) {
        return this.xpos + (int)((n - this.a) * this.w / (this.c - this.a));
    }
    
    public int Ypos(final double n) {
        int n2 = this.ypos - (int)((n - this.b) * this.h / (this.d - this.b));
        if (n2 > 2000) {
            n2 = 2000;
        }
        if (n2 < -2000) {
            n2 = -2000;
        }
        return n2;
    }
    
    public void showCursor() {
        this.g.setColor(this.cursorcolor);
        this.g.drawLine(this.xpos + this.w / 2 - 5, this.ypos - this.h / 2, this.xpos + this.w / 2 + 5, this.ypos - this.h / 2);
        this.g.drawLine(this.xpos + this.w / 2, this.ypos - this.h / 2 - 5, this.xpos + this.w / 2, this.ypos - this.h / 2 + 5);
    }
    
    public void showKeyPoints() {
        if (!this.keypoints.isEmpty()) {
            for (int i = 0; i < this.keypoints.size(); ++i) {
                this.showKeyPoint(i);
            }
        }
    }
    
    public void showKeyPoint(final int n) {
        final KeyPoynt keyPoynt = this.keypoints.elementAt(n);
        this.g.setColor(keyPoynt.color);
        if (keyPoynt.style == 2) {
            keyPoynt.ycoord = keyPoynt.wire.giveyfor(keyPoynt.xcoord);
        }
        final int xpos = this.Xpos(keyPoynt.xcoord);
        final int ypos = this.Ypos(keyPoynt.ycoord);
        switch (keyPoynt.style) {
            default: {
                this.g.drawLine(xpos - 3, ypos, xpos + 3, ypos);
                this.g.drawLine(xpos, ypos - 3, xpos, ypos + 3);
                break;
            }
            case 1: {
                this.g.drawLine(xpos - 5, ypos, xpos + 5, ypos);
                this.g.drawLine(xpos, ypos - 5, xpos, ypos + 5);
                break;
            }
            case 2: {
                this.g.fillOval(xpos - 5, ypos - 5, 10, 10);
                break;
            }
        }
    }
    
    public void showLines() {
        if (!this.lines.isEmpty()) {
            for (int i = 0; i < this.lines.size(); ++i) {
                this.showLine(i);
            }
        }
    }
    
    public void showLine(final int n) {
        final Line line = this.lines.elementAt(n);
        this.g.setColor(line.color);
        double xcoord = line.p1.xcoord;
        double ycoord = line.p1.ycoord;
        double xcoord2 = line.p2.xcoord;
        double ycoord2 = line.p2.ycoord;
        if (line.extend) {
            double n2 = xcoord;
            double c = ycoord;
            double n3 = xcoord2;
            double d = ycoord2;
            if (xcoord2 == xcoord) {
                if (ycoord2 != ycoord) {
                    c = this.c;
                    d = this.d;
                }
            }
            else if (ycoord2 == ycoord) {
                n2 = this.a;
                n3 = this.b;
            }
            else {
                n2 = Math.max(this.a, xcoord + Math.min((xcoord2 - xcoord) / (ycoord2 - ycoord) * (this.d - ycoord), (xcoord2 - xcoord) / (ycoord2 - ycoord) * (this.c - ycoord)));
                c = ycoord + (ycoord2 - ycoord) / (xcoord2 - xcoord) * (n2 - xcoord);
                n3 = Math.min(this.b, xcoord + Math.max((xcoord2 - xcoord) / (ycoord2 - ycoord) * (this.d - ycoord), (xcoord2 - xcoord) / (ycoord2 - ycoord) * (this.c - ycoord)));
                d = ycoord + (ycoord2 - ycoord) / (xcoord2 - xcoord) * (n3 - xcoord);
            }
            xcoord = n2;
            ycoord = c;
            xcoord2 = n3;
            ycoord2 = d;
        }
        this.g.drawLine(this.Xpos(xcoord), this.Ypos(ycoord), this.Xpos(xcoord2), this.Ypos(ycoord2));
    }
    
    public void showGrid() {
        final int red = this.bg.getRed();
        final int green = this.bg.getGreen();
        final int blue = this.bg.getBlue();
        final double pow = Math.pow(10.0, Math.floor(Math.log(this.c - this.a) / Math.log(10.0)) - 1.0);
        double n = Math.floor(this.a / pow) * pow;
        final int n2 = this.xpos + (int)((n - this.a) * this.w / (this.c - this.a));
        double n3 = Math.floor(this.b / pow) * pow;
        final int n4 = this.ypos + (int)((n3 - this.b) * this.h / (this.d - this.b));
        this.g.setColor(new Color((int)((7 * red + red * Math.floor((this.c - this.a) / pow) / 100.0) / 8.0), (int)((7 * green + green * Math.floor((this.c - this.a) / pow) / 100.0) / 8.0), (int)((7 * blue + blue * Math.floor((this.c - this.a) / pow) / 100.0) / 8.0)));
        while (n < this.c) {
            final int n5 = this.xpos + (int)((n - this.a) * this.w / (this.c - this.a));
            this.g.drawLine(n5, this.ypos, n5, this.ypos - this.h);
            n += pow;
        }
        while (n3 < this.d) {
            final int n6 = this.ypos - (int)((n3 - this.b) * this.h / (this.d - this.b));
            this.g.drawLine(this.xpos, n6, this.xpos + this.w, n6);
            n3 += pow;
        }
        final double n7 = pow * 10.0;
        double n8 = Math.floor(this.a / n7) * n7;
        final int n9 = this.xpos + (int)((n8 - this.a) * this.w / (this.c - this.a));
        double n10 = Math.floor(this.b / n7) * n7;
        final int n11 = this.ypos + (int)((n10 - this.b) * this.h / (this.d - this.b));
        this.g.setColor(new Color((int)((3 * red + red * Math.floor((this.c - this.a) / n7) / 100.0) / 4.0), (int)((3 * green + green * Math.floor((this.c - this.a) / n7) / 100.0) / 4.0), (int)((3 * blue + blue * Math.floor((this.c - this.a) / n7) / 100.0) / 4.0)));
        while (n8 < this.c) {
            final int n12 = this.xpos + (int)((n8 - this.a) * this.w / (this.c - this.a));
            this.g.drawLine(n12, this.ypos, n12, this.ypos - this.h);
            n8 += n7;
        }
        while (n10 < this.d) {
            final int n13 = this.ypos - (int)((n10 - this.b) * this.h / (this.d - this.b));
            this.g.drawLine(this.xpos, n13, this.xpos + this.w, n13);
            n10 += n7;
        }
        final double n14 = n7 * 10.0;
        double n15 = Math.floor(this.a / n14) * n14;
        final int n16 = this.xpos + (int)((n15 - this.a) * this.w / (this.c - this.a));
        double n17 = Math.floor(this.b / n14) * n14;
        final int n18 = this.ypos + (int)((n17 - this.b) * this.h / (this.d - this.b));
        this.g.setColor(new Color((int)((3 * red + red * Math.floor((this.c - this.a) / n14) / 100.0) / 5.0), (int)((3 * green + green * Math.floor((this.c - this.a) / n14) / 100.0) / 5.0), (int)((3 * blue + blue * Math.floor((this.c - this.a) / n14) / 100.0) / 5.0)));
        while (n15 < this.c) {
            final int n19 = this.xpos + (int)((n15 - this.a) * this.w / (this.c - this.a));
            this.g.drawLine(n19, this.ypos, n19, this.ypos - this.h);
            n15 += n14;
        }
        while (n17 < this.d) {
            final int n20 = this.ypos - (int)((n17 - this.b) * this.h / (this.d - this.b));
            this.g.drawLine(this.xpos, n20, this.xpos + this.w, n20);
            n17 += n14;
        }
        if (this.a * this.c < 0.0) {
            this.g.setColor(new Color(red / 4, green / 4, blue / 4));
            final int n21 = this.xpos + (int)((0.0 - this.a) * this.w / (this.c - this.a));
            this.g.drawLine(n21, this.ypos, n21, this.ypos - this.h);
        }
        if (this.b * this.d < 0.0) {
            this.g.setColor(new Color(red / 4, green / 4, blue / 4));
            final int n22 = this.ypos - (int)((0.0 - this.b) * this.h / (this.d - this.b));
            this.g.drawLine(this.xpos, n22, this.xpos + this.w, n22);
        }
    }
    
    public void showGraphs(final int n) {
        if (!this.graphs.isEmpty()) {
            for (int i = 0; i < this.graphs.size(); ++i) {
                this.showGraph(i, n);
            }
        }
    }
    
    public void showGraph(final int n, final int n2) {
        this.refinement = 0;
        this.z = this.graphs.elementAt(n);
        final double[] array = new double[this.Resolution + 1];
        array[0] = 0.0;
        array[this.Resolution] = 0.0;
        this.g.setColor(this.graphcolors.elementAt(n));
        for (int i = 0; i < this.Resolution; ++i) {
            if (i > 0 && this.z[0][i] - this.z[0][i - 1] != 0.0) {
                array[i] = (this.z[1][i] - this.z[1][i - 1]) / (this.z[0][i] - this.z[0][i - 1]);
            }
        }
        for (int j = 1; j < this.Resolution; ++j) {
            if ((!this.refine || n2 == 0 || Math.max(Math.abs(array[j] - array[j - 1]), Math.abs(array[j] - array[j + 1])) < Math.max(1.0, Math.abs(array[j]))) && !Double.isNaN(this.z[1][j] + this.z[1][j - 1])) {
                this.g.drawLine(this.Xpos(this.z[0][j - 1]), this.Ypos(this.z[1][j - 1]), this.Xpos(this.z[0][j]), this.Ypos(this.z[1][j]));
            }
            else if (this.refine && n2 != 0 && (!Double.isNaN(this.z[1][j]) || !Double.isNaN(this.z[1][j - 1]))) {
                this.refineGraphStep(n, this.z[0][j - 1], this.z[0][j], n2);
            }
        }
    }
    
    public void refineGraphStep(final int n, final double n2, final double n3, final int n4) {
        final int n5 = 3;
        this.f = this.functions.elementAt(n);
        ++this.refinement;
        final double[] array = new double[n5 + 2];
        array[n5 + 1] = (array[0] = 0.0);
        final double[][] array2 = new double[2][n5 + 1];
        for (int i = 0; i < n5 + 1; ++i) {
            array2[0][i] = n2 + (n3 - n2) * i / n5;
            array2[1][i] = this.f.giveyfor(array2[0][i]);
            if (i > 0) {
                array[i] = (array2[1][i] - array2[1][i - 1]) / (array2[0][i] - array2[0][i - 1]);
            }
        }
        for (int j = 1; j < n5 + 1; ++j) {
            if (!Double.isNaN(array2[1][j] + array2[1][j - 1])) {
                if (this.refinement >= n4 || Math.max(Math.abs(array[j] - array[j - 1]), Math.abs(array[j] - array[j + 1])) < Math.max(1.0, Math.abs(array[j]))) {
                    this.g.drawLine(this.Xpos(array2[0][j - 1]), this.Ypos(array2[1][j - 1]), this.Xpos(array2[0][j]), this.Ypos(array2[1][j]));
                }
                else {
                    this.refineGraphStep(n, array2[0][j - 1], array2[0][j], n4);
                }
            }
        }
        --this.refinement;
    }
    
    public double[][] graphFunction(final Function function) {
        this.zz = new double[2][this.Resolution];
        for (int i = 0; i < this.Resolution; ++i) {
            this.zz[0][i] = this.a + (this.c - this.a) * i / this.Resolution;
            this.zz[1][i] = function.giveyfor(this.zz[0][i]);
        }
        return this.zz;
    }
    
    public void regraph() {
        if (!this.functions.isEmpty()) {
            for (int i = 0; i < this.functions.size(); ++i) {
                this.f = (Function)this.functions.elementAt(i);
                this.z = (double[][])this.graphs.elementAt(i);
                final double[] array = new double[this.Resolution];
                final double[] array2 = new double[this.Resolution];
                for (int j = 0; j < this.Resolution; ++j) {
                    array[j] = this.z[0][j];
                    array2[j] = this.z[1][j];
                }
                for (int k = 0; k < this.Resolution; ++k) {
                    this.z[0][k] = this.a + (this.c - this.a) * k / this.Resolution;
                }
                int resolution = 0;
                for (int l = 0; l < this.Resolution; ++l) {
                    boolean b = false;
                    for (int n = resolution; n >= resolution && n < this.Resolution && !b; ++n) {
                        if (Math.abs(array[n] - this.z[0][l]) < 1.0 * (this.c - this.a) / this.Resolution) {
                            this.z[0][l] = array[n];
                            this.z[1][l] = array2[n];
                            resolution = n + 1;
                            b = true;
                        }
                        else if (array[n] > this.z[0][l]) {
                            this.z[1][l] = this.f.giveyfor(this.z[0][l]);
                            resolution = n;
                            b = true;
                        }
                    }
                    if (!b) {
                        this.z[1][l] = this.f.giveyfor(this.z[0][l]);
                        resolution = this.Resolution;
                    }
                }
                this.graphs.setElementAt(this.z, i);
            }
        }
    }
}
