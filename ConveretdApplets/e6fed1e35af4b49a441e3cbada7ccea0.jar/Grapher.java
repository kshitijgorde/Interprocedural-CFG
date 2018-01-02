import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grapher extends Applet implements Runnable
{
    int R;
    int G;
    int B;
    Color background;
    Color graphcolor;
    Color c;
    Color cursorcolor;
    Color keycolor;
    Color tempcolor;
    Color pointcolor;
    public Image buffer;
    private Graphics g2;
    private Thread animate;
    int N;
    int step;
    int pause;
    int Resolution;
    public boolean moving;
    public boolean ontarget;
    public boolean refine;
    public boolean started;
    public boolean changed;
    public boolean showinfo;
    public boolean reshow;
    public boolean removal;
    public boolean edit;
    public boolean pauserequest;
    public boolean drawing;
    boolean apoint;
    boolean inbox;
    boolean on;
    boolean online;
    KeyPoynt point;
    Function wire;
    Line line;
    double xScale;
    double yScale;
    double xscale;
    double yscale;
    double r;
    double x1;
    double y1;
    double x1target;
    double y1target;
    double xdown;
    double ydown;
    double x;
    double y;
    int xpos;
    int ypos;
    int w;
    int h;
    int style;
    public Button upshift;
    public Button downshift;
    public Button leftshift;
    public Button rightshift;
    public Button leftbutton;
    public Button rightbutton;
    public Button inbutton;
    public Button outbutton;
    public Button stopbutton;
    Label xyprompt;
    Label xprompt;
    Label yprompt;
    TextField setx;
    TextField sety;
    TextField xrange;
    TextField yrange;
    Label wprompt;
    Label hprompt;
    Label rangeprompt;
    int functionNumber;
    Function f;
    Graph graph;
    String formula;
    KeyPoynt p;
    KeyPoynt target;
    Label functionlistlabel;
    Button newfunction;
    Vector functionbuttons;
    Button editbutton;
    Button deletebutton;
    Button Copy;
    Button Replace;
    TextField functset;
    Label functprompt;
    Label errormessage;
    Label Cprompt;
    Label Rprompt;
    Label Gprompt;
    Label Bprompt;
    Scrollbar Rset;
    Scrollbar Gset;
    Scrollbar Bset;
    Button Done;
    Vector Parameters;
    Parameter param;
    Label paramlistlabel;
    Label popup;
    
    public Grapher() {
        this.R = 255;
        this.G = 255;
        this.B = 220;
        this.background = new Color(this.R, this.G, this.B);
        this.graphcolor = Color.black;
        this.c = Color.black;
        this.cursorcolor = Color.orange;
        this.keycolor = Color.red;
        this.tempcolor = new Color(175, 100, 0);
        this.pointcolor = this.tempcolor;
        this.N = 0;
        this.step = 0;
        this.pause = 10;
        this.Resolution = 400;
        this.moving = true;
        this.ontarget = true;
        this.refine = true;
        this.started = false;
        this.changed = false;
        this.showinfo = false;
        this.reshow = false;
        this.removal = false;
        this.edit = false;
        this.pauserequest = false;
        this.drawing = false;
        this.wire = null;
        this.xScale = 4.0;
        this.yScale = 4.0;
        this.xscale = 4.0;
        this.yscale = 4.0;
        this.r = 1.0;
        this.x1 = 0.0;
        this.y1 = 0.0;
        this.x1target = 0.0;
        this.y1target = 0.0;
        this.xdown = 0.0;
        this.ydown = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.xpos = 0;
        this.ypos = 200;
        this.w = 200;
        this.h = 200;
        this.style = 0;
        this.upshift = new Button("^");
        this.downshift = new Button("v");
        this.leftshift = new Button("<");
        this.rightshift = new Button(">");
        this.leftbutton = new Button("<<<");
        this.rightbutton = new Button(">>>");
        this.inbutton = new Button("Zoom In");
        this.outbutton = new Button("ZoomOut");
        this.stopbutton = new Button("Stop");
        this.xyprompt = new Label("Mid-View Coordinates");
        this.xprompt = new Label("x:");
        this.yprompt = new Label("y:");
        this.setx = new TextField("0.0");
        this.sety = new TextField("0.0");
        this.xrange = new TextField("0.0");
        this.yrange = new TextField("0.0");
        this.wprompt = new Label("w:");
        this.hprompt = new Label("h:");
        this.rangeprompt = new Label("View Dimensions");
        this.functionNumber = 0;
        this.functionlistlabel = new Label("Functions List");
        this.newfunction = new Button("New Function");
        this.functionbuttons = new Vector(2);
        this.editbutton = new Button("edit");
        this.deletebutton = new Button("delete");
        this.Copy = new Button("copy");
        this.Replace = new Button("replace");
        this.functset = new TextField(20);
        this.functprompt = new Label("Enter New Function: ");
        this.errormessage = new Label("Syntax Error!");
        this.Cprompt = new Label("Colour");
        this.Rprompt = new Label("R");
        this.Gprompt = new Label("G");
        this.Bprompt = new Label("B");
        this.Rset = new Scrollbar(0, 0, 0, 0, 255);
        this.Gset = new Scrollbar(0, 0, 0, 0, 255);
        this.Bset = new Scrollbar(0, 0, 0, 0, 255);
        this.Done = new Button("done");
        this.Parameters = new Vector();
        this.paramlistlabel = new Label("Parameters");
        this.popup = new Label("");
    }
    
    public void init() {
        this.buffer = this.createImage(200, 200);
        this.g2 = this.buffer.getGraphics();
        this.setBackground(this.background);
        (this.graph = new Graph()).setbg(this.background);
        this.setLayout(null);
        this.add(this.stopbutton);
        this.stopbutton.reshape(285, 345, 30, 20);
        this.add(this.leftbutton);
        this.leftbutton.reshape(245, 345, 35, 20);
        this.add(this.rightbutton);
        this.rightbutton.reshape(320, 345, 35, 20);
        this.add(this.inbutton);
        this.inbutton.reshape(270, 320, 60, 20);
        this.add(this.outbutton);
        this.outbutton.reshape(270, 370, 60, 20);
        this.add(this.xyprompt);
        this.xyprompt.reshape(100, 320, 130, 20);
        this.add(this.xprompt);
        this.xprompt.reshape(103, 340, 12, 20);
        this.add(this.yprompt);
        this.yprompt.reshape(103, 360, 12, 20);
        this.add(this.setx);
        this.setx.reshape(120, 340, 100, 20);
        this.add(this.sety);
        this.sety.reshape(120, 360, 100, 20);
        this.add(this.xrange);
        this.xrange.reshape(390, 340, 100, 20);
        this.add(this.yrange);
        this.yrange.reshape(390, 360, 100, 20);
        this.add(this.wprompt);
        this.wprompt.reshape(370, 340, 20, 20);
        this.add(this.hprompt);
        this.hprompt.reshape(370, 360, 20, 20);
        this.add(this.rangeprompt);
        this.rangeprompt.reshape(380, 320, 130, 20);
        this.add(this.leftshift);
        this.leftshift.reshape(180, 160, 20, 20);
        this.add(this.rightshift);
        this.rightshift.reshape(400, 160, 20, 20);
        this.add(this.upshift);
        this.upshift.reshape(290, 50, 20, 20);
        this.add(this.downshift);
        this.downshift.reshape(290, 270, 20, 20);
        this.add(this.functionlistlabel);
        this.functionlistlabel.reshape(0, 10, 90, 20);
        this.add(this.newfunction);
        this.newfunction.reshape(0, 30, 100, 20);
        this.add(this.editbutton);
        this.add(this.deletebutton);
        this.add(this.Copy);
        this.add(this.Replace);
        this.functset.setText("0");
        this.add(this.functset);
        this.functset.reshape(0, 0, 0, 0);
        this.add(this.functprompt);
        this.functprompt.reshape(0, 0, 0, 0);
        this.add(this.errormessage);
        this.errormessage.reshape(0, 0, 0, 0);
        this.add(this.Cprompt);
        this.Cprompt.reshape(0, 0, 0, 0);
        this.add(this.Rprompt);
        this.Rprompt.reshape(0, 0, 0, 0);
        this.add(this.Rset);
        this.Rset.reshape(0, 0, 0, 0);
        this.add(this.Gprompt);
        this.Gprompt.reshape(0, 0, 0, 0);
        this.add(this.Gset);
        this.Gset.reshape(0, 0, 0, 0);
        this.add(this.Bprompt);
        this.Bprompt.reshape(0, 0, 0, 0);
        this.add(this.Bset);
        this.Bset.reshape(0, 0, 0, 0);
        this.add(this.Done);
        this.Done.reshape(0, 0, 0, 0);
        this.add(this.popup);
        this.popup.reshape(0, 0, 0, 0);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar) {
            this.c = new Color(this.Rset.getValue(), this.Gset.getValue(), this.Bset.getValue());
            this.graph.graphcolors.setElementAt(this.c, this.functionNumber);
            this.moving = true;
        }
        return super.handleEvent(event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        String text = "";
        this.popup.reshape(n + 5, n2 - 10, 0, 0);
        for (int i = 0; i < this.graph.keypoints.size(); ++i) {
            if (((KeyPoynt)this.graph.keypoints.elementAt(i)).style == 0) {
                this.graph.delKeyPoint(i);
            }
        }
        this.inbox = false;
        if (n > 200 && n < 400 && n2 > 70 && n2 < 270) {
            this.inbox = true;
            this.x = this.x1 - this.xscale / 2.0 + (n - 200) * this.xscale / this.w;
            this.xdown = this.x;
            this.y = this.y1 - this.yscale / 2.0 + (270 - n2) * this.yscale / this.h;
            this.ydown = this.y;
            this.apoint = false;
            this.on = false;
            this.online = false;
            this.style = 0;
            this.pointcolor = this.tempcolor;
            double sqrt = this.xscale / 20.0;
            for (int j = 0; j < this.graph.keypoints.size(); ++j) {
                final KeyPoynt point = this.graph.keypoints.elementAt(j);
                if (Math.sqrt(Math.pow(this.x - point.xcoord, 2.0) + Math.pow(this.y - point.ycoord, 2.0)) < sqrt) {
                    this.point = point;
                    this.apoint = true;
                    sqrt = Math.sqrt(Math.pow(this.x - point.xcoord, 2.0) + Math.pow(this.y - point.ycoord, 2.0));
                }
            }
            double n3 = this.yscale / 20.0;
            for (int k = 0; k < this.graph.lines.size(); ++k) {
                final Line line = this.graph.lines.elementAt(k);
                final double xcoord = line.p1.xcoord;
                final double ycoord = line.p1.ycoord;
                final double xcoord2 = line.p2.xcoord;
                final double ycoord2 = line.p2.ycoord;
                final double n4 = Math.abs((this.x - xcoord) * (this.y - ycoord2) - (this.x - xcoord2) * (this.y - ycoord)) / Math.sqrt(Math.pow(xcoord - xcoord2, 2.0) + Math.pow(ycoord - ycoord2, 2.0));
                if (n4 < n3) {
                    n3 = n4;
                    this.line = line;
                    this.online = true;
                }
            }
            if (event.shiftDown() && this.online) {
                this.line.extend = !this.line.extend;
            }
            double abs = this.yscale / 20.0;
            for (int l = 0; l < this.functionbuttons.size(); ++l) {
                final Function wire = this.graph.functions.elementAt(l);
                if (Math.abs(this.y - wire.giveyfor(this.x)) < abs) {
                    abs = Math.abs(this.y - wire.giveyfor(this.x));
                    this.wire = wire;
                    this.on = true;
                    text = "y=" + ((Button)this.functionbuttons.elementAt(l)).getLabel();
                }
            }
            if (event.controlDown() && !this.started) {
                if (event.metaDown() && !this.apoint && this.online) {
                    this.graph.delLine(this.line);
                    this.changed = true;
                    return super.mouseDown(event, n, n2);
                }
                if (this.apoint && event.metaDown()) {
                    this.point.style = 0;
                    this.point.color = this.tempcolor;
                }
                else if (!this.apoint) {
                    this.point = new KeyPoynt(this.x, this.y, this.keycolor, 1);
                    this.graph.addKeyPoint(this.point);
                    this.apoint = true;
                }
                if (event.shiftDown() && this.on) {
                    this.point.style = 2;
                    this.point.wire = this.wire;
                }
                this.changed = true;
            }
            if (!this.on || this.apoint) {
                final int n5 = (int)(Math.log(this.xscale / 10.0) / 2.0);
                final double pow = Math.pow(10.0, n5 - 1);
                final int n6 = (int)(Math.log(this.yscale / 10.0) / 2.0);
                final double pow2 = Math.pow(10.0, n6 - 1);
                text = "(" + this.showbrief(Double.toString(Math.floor(this.x / pow + 0.5) * pow), 4 + Math.abs(n5)) + "," + this.showbrief(Double.toString(Math.floor(this.y / pow2 + 0.5) * pow2), 4 + Math.abs(n6)) + ")";
            }
            this.popup.setText(text);
            this.popup.reshape(n + 5, n2 - 10, 7 * text.length(), 20);
        }
        this.changed = true;
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.inbox && !this.apoint && !this.on) {
            this.p = new KeyPoynt(this.x, this.y, this.pointcolor);
            this.graph.addKeyPoint(this.p);
            this.changed = true;
        }
        if (event.controlDown() && this.started) {
            final int index = this.graph.keypoints.indexOf(this.line.p2);
            boolean b = false;
            double sqrt = this.xscale / 20.0;
            for (int i = 0; i < this.graph.keypoints.size(); ++i) {
                if (i != index) {
                    final KeyPoynt keyPoynt = this.graph.keypoints.elementAt(i);
                    if (Math.sqrt(Math.pow(this.x - keyPoynt.xcoord, 2.0) + Math.pow(this.y - keyPoynt.ycoord, 2.0)) < sqrt) {
                        this.point = keyPoynt;
                        this.line.setEnd(keyPoynt);
                        b = true;
                        sqrt = Math.sqrt(Math.pow(this.x - keyPoynt.xcoord, 2.0) + Math.pow(this.y - keyPoynt.ycoord, 2.0));
                    }
                }
            }
            if (b) {
                this.graph.delKeyPoint(index);
            }
        }
        this.started = false;
        return super.mouseUp(event, n, n2);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.popup.reshape(n, n2, 0, 0);
        this.popup.setText("");
        for (int i = 0; i < this.graph.keypoints.size(); ++i) {
            if (((KeyPoynt)this.graph.keypoints.elementAt(i)).style == 0) {
                this.graph.delKeyPoint(i);
            }
        }
        this.inbox = false;
        if (n > 200 && n < 400 && n2 > 50 && n2 < 270) {
            this.inbox = true;
            this.x = this.x1 - this.xscale / 2.0 + (n - 200) * this.xscale / this.w;
            this.y = this.y1 - this.yscale / 2.0 + (270 - n2) * this.yscale / this.h;
            if (event.shiftDown()) {
                this.x1 = this.x1 - this.x + this.xdown;
                this.y1 = this.y1 - this.y + this.ydown;
                this.changed = true;
            }
            else if (!this.apoint || (this.apoint && this.point.style != 2)) {
                final int n3 = (int)(Math.log(this.xscale / 10.0) / 2.0);
                final double pow = Math.pow(10.0, n3 - 1);
                final int n4 = (int)(Math.log(this.yscale / 10.0) / 2.0);
                final double pow2 = Math.pow(10.0, n4 - 1);
                final String string = "(" + this.showbrief(Double.toString(Math.floor(this.x / pow + 0.5) * pow), 4 + Math.abs(n3)) + "," + this.showbrief(Double.toString(Math.floor(this.y / pow2 + 0.5) * pow2), 4 + Math.abs(n4)) + ")";
                this.popup.setText(string);
                this.popup.reshape(n + 5, n2 - 10, 7 * string.length(), 20);
            }
            if (this.apoint && !event.controlDown()) {
                this.point.moveTo(this.x, this.y);
                if (this.point.style == 2) {
                    this.popup.reshape(n + 5, n2 - 10, 0, 0);
                }
            }
        }
        else {
            this.popup.reshape(n + 5, n2 - 10, 0, 0);
        }
        if (event.controlDown()) {
            if (!this.started) {
                final KeyPoynt keyPoynt = new KeyPoynt(this.x, this.y, Color.red, 1);
                this.graph.addKeyPoint(keyPoynt);
                this.line = new Line(this.point, keyPoynt);
                this.graph.addLine(this.line);
                this.started = true;
            }
            else {
                this.line.moveEndTo(this.x, this.y);
            }
        }
        this.changed = true;
        return super.mouseDrag(event, n, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.newfunction)) {
            this.edit = true;
            this.pauserequest = true;
            this.functionNumber = this.functionbuttons.size();
            this.editbutton.reshape(0, 0, 0, 0);
            this.deletebutton.reshape(0, 0, 0, 0);
            this.Copy.reshape(0, 0, 0, 0);
            this.Replace.reshape(0, 0, 0, 0);
            this.newfunction.reshape(0, 0, 0, 0);
            this.functset.setText("0");
            this.functset.reshape(210, 5, 290, 20);
            this.functprompt.reshape(90, 5, 110, 20);
            this.errormessage.reshape(0, 0, 0, 0);
            this.f = new Function("0");
            while (this.drawing) {}
            this.drawing = true;
            this.pauserequest = false;
            this.graph.addFunction(this.f);
            this.drawframe(this.g2);
            this.repaint();
            this.drawing = false;
            this.changed = true;
            final Button button = new Button("0");
            this.add(button);
            this.functionbuttons.addElement(button);
            this.functionNumber = this.functionbuttons.size() - 1;
            this.c = Color.black;
        }
        if (event.target == this.functset) {
            this.pauserequest = true;
            this.errormessage.reshape(0, 0, 0, 0);
            final int size = this.Parameters.size();
            this.f = new Function(this.functset.getText(), this.Parameters);
            if (this.f.errorTest()) {
                this.errormessage.reshape(500, 15, 100, 20);
            }
            else {
                while (this.drawing) {}
                this.drawing = true;
                this.pauserequest = false;
                for (int i = 0; i < this.graph.keypoints.size(); ++i) {
                    this.point = (KeyPoynt)this.graph.keypoints.elementAt(i);
                    if (this.point.wire == this.graph.functions.elementAt(this.functionNumber)) {
                        this.point.fixTo(this.f);
                    }
                }
                this.graph.functions.setElementAt(this.f, this.functionNumber);
                this.graph.graphs.setElementAt(this.graph.graphFunction(this.f), this.functionNumber);
                this.graph.graphcolors.setElementAt(this.c, this.functionNumber);
                this.functionbuttons.elementAt(this.functionNumber).setLabel(this.functset.getText());
                if (this.Parameters.size() > size) {
                    if (size == 0) {
                        this.add(this.paramlistlabel);
                        this.paramlistlabel.reshape(450, 120, 80, 20);
                    }
                    for (int j = size; j < this.Parameters.size(); ++j) {
                        final Parameter parameter = this.Parameters.elementAt(j);
                        this.add(parameter.prompt);
                        parameter.prompt.reshape(430, 140 + 40 * j, 20, 20);
                        this.add(parameter.input);
                        parameter.input.reshape(450, 140 + 40 * j, 80, 20);
                        this.add(parameter.toggle);
                        parameter.toggle.reshape(530, 140 + 40 * j, 10, 20);
                        this.add(parameter.plus);
                        this.add(parameter.minus);
                        this.add(parameter.inc);
                        this.add(parameter.dec);
                        this.add(parameter.stop);
                        this.add(parameter.powlabel);
                        this.add(parameter.setpow);
                        this.add(parameter.powup);
                        this.add(parameter.powdn);
                    }
                }
                this.drawframe(this.g2);
                this.repaint();
                this.drawing = false;
                this.colorSet();
            }
        }
        if (!this.edit) {
            for (int k = 0; k < this.functionbuttons.size(); ++k) {
                if (event.target.equals(this.functionbuttons.elementAt(k))) {
                    this.functionNumber = k;
                    this.formula = ((Button)event.target).getLabel();
                    this.editbutton.reshape(100, 30 + 20 * k - 10, 50, 20);
                    this.deletebutton.reshape(100, 30 + 20 * k + 10, 50, 20);
                }
            }
        }
        if (event.target.equals(this.deletebutton)) {
            this.pauserequest = true;
            this.editbutton.reshape(0, 0, 0, 0);
            this.deletebutton.reshape(0, 0, 0, 0);
            for (int l = 0; l < this.functionbuttons.size(); ++l) {
                ((Button)this.functionbuttons.elementAt(l)).reshape(0, 10 + 20 * (l + 1), 100, 0);
            }
            this.functionbuttons.removeElementAt(this.functionNumber);
            for (int n = 0; n < this.functionbuttons.size(); ++n) {
                ((Button)this.functionbuttons.elementAt(n)).reshape(0, 10 + 20 * (n + 1), 100, 20);
            }
            this.newfunction.reshape(0, 10 + 20 * (this.functionbuttons.size() + 1), 100, 20);
            while (this.drawing) {}
            this.drawing = true;
            this.pauserequest = false;
            this.graph.delFunction(this.functionNumber);
            this.graph.paramchange = true;
            for (int n2 = 0; n2 < this.Parameters.size(); ++n2) {
                ((Parameter)this.Parameters.elementAt(n2)).active = false;
            }
            this.drawframe(this.g2);
            this.graph.paramchange = true;
            for (int n3 = 0; n3 < this.Parameters.size(); ++n3) {
                final Parameter parameter2 = this.Parameters.elementAt(n3);
                parameter2.prompt.reshape(430, 140 + 40 * n3, 0, 0);
                parameter2.input.reshape(450, 140 + 40 * n3, 0, 0);
                parameter2.toggle.reshape(530, 140 + 40 * n3, 0, 0);
                parameter2.plus.reshape(0, 0, 0, 0);
                parameter2.minus.reshape(0, 0, 0, 0);
                parameter2.inc.reshape(0, 0, 0, 0);
                parameter2.dec.reshape(0, 0, 0, 0);
                parameter2.stop.reshape(0, 0, 0, 0);
                parameter2.powlabel.reshape(0, 0, 0, 0);
                parameter2.setpow.reshape(0, 0, 0, 0);
                parameter2.powup.reshape(0, 0, 0, 0);
                parameter2.powdn.reshape(0, 0, 0, 0);
            }
            int n4 = 0;
            while (n4 < this.Parameters.size()) {
                if (!((Parameter)this.Parameters.elementAt(n4)).active) {
                    this.Parameters.removeElementAt(n4);
                }
                else {
                    ++n4;
                }
            }
            for (int n5 = 0; n5 < this.Parameters.size(); ++n5) {
                final Parameter parameter3 = this.Parameters.elementAt(n5);
                parameter3.prompt.reshape(430, 140 + 40 * n5, 20, 20);
                parameter3.input.reshape(450, 140 + 40 * n5, 80, 20);
                parameter3.toggle.reshape(530, 140 + 40 * n5, 10, 20);
            }
            this.repaint();
            this.drawing = false;
        }
        if (event.target.equals(this.editbutton)) {
            this.Copy.reshape(150, 30 + 20 * this.functionNumber - 10 - 10, 50, 20);
            this.Replace.reshape(150, 30 + 20 * this.functionNumber - 10 + 10, 50, 20);
        }
        if (event.target.equals(this.Replace)) {}
        if (event.target.equals(this.Copy) || event.target.equals(this.Replace)) {
            this.edit = true;
            this.Copy.reshape(0, 0, 0, 0);
            this.Replace.reshape(0, 0, 0, 0);
            this.editbutton.reshape(0, 0, 0, 0);
            this.deletebutton.reshape(0, 0, 0, 0);
            this.newfunction.reshape(0, 0, 0, 0);
            this.functset.reshape(210, 5, 290, 20);
            this.functprompt.reshape(90, 5, 110, 20);
            this.functset.setText(this.formula);
            if (event.target.equals(this.Copy)) {
                this.f = new Function(this.formula);
                this.graph.addFunction(this.f);
                final Button button2 = new Button(this.formula);
                this.add(button2);
                this.functionbuttons.addElement(button2);
                this.functionNumber = this.functionbuttons.size() - 1;
            }
        }
        if (event.target == this.Done) {
            this.pauserequest = true;
            this.Cprompt.reshape(0, 0, 0, 0);
            this.Rprompt.reshape(0, 0, 0, 0);
            this.Rset.reshape(0, 0, 0, 0);
            this.Gprompt.reshape(0, 0, 0, 0);
            this.Gset.reshape(0, 0, 0, 0);
            this.Bprompt.reshape(0, 0, 0, 0);
            this.Bset.reshape(0, 0, 0, 0);
            this.Done.reshape(0, 0, 0, 0);
            this.functset.reshape(0, 0, 0, 0);
            this.functprompt.reshape(0, 0, 0, 0);
            for (int n6 = 0; n6 < this.functionbuttons.size(); ++n6) {
                ((Button)this.functionbuttons.elementAt(n6)).reshape(0, 10 + 20 * (n6 + 1), 100, 20);
            }
            this.newfunction.reshape(0, 10 + 20 * (this.functionbuttons.size() + 1), 100, 20);
            this.edit = false;
            while (this.drawing) {}
            this.drawing = true;
            this.pauserequest = false;
            this.graph.paramchange = true;
            for (int n7 = 0; n7 < this.Parameters.size(); ++n7) {
                ((Parameter)this.Parameters.elementAt(n7)).active = false;
            }
            this.drawframe(this.g2);
            this.graph.paramchange = true;
            for (int n8 = 0; n8 < this.Parameters.size(); ++n8) {
                final Parameter parameter4 = this.Parameters.elementAt(n8);
                parameter4.prompt.reshape(430, 140 + 40 * n8, 0, 0);
                parameter4.input.reshape(450, 140 + 40 * n8, 0, 0);
                parameter4.toggle.reshape(530, 140 + 40 * n8, 0, 0);
                parameter4.plus.reshape(0, 0, 0, 0);
                parameter4.minus.reshape(0, 0, 0, 0);
                parameter4.inc.reshape(0, 0, 0, 0);
                parameter4.dec.reshape(0, 0, 0, 0);
                parameter4.stop.reshape(0, 0, 0, 0);
                parameter4.powlabel.reshape(0, 0, 0, 0);
                parameter4.setpow.reshape(0, 0, 0, 0);
                parameter4.powup.reshape(0, 0, 0, 0);
                parameter4.powdn.reshape(0, 0, 0, 0);
            }
            int n9 = 0;
            while (n9 < this.Parameters.size()) {
                if (!((Parameter)this.Parameters.elementAt(n9)).active) {
                    this.Parameters.removeElementAt(n9);
                }
                else {
                    ++n9;
                }
            }
            for (int n10 = 0; n10 < this.Parameters.size(); ++n10) {
                final Parameter parameter5 = this.Parameters.elementAt(n10);
                parameter5.prompt.reshape(430, 140 + 40 * n10, 20, 20);
                parameter5.input.reshape(450, 140 + 40 * n10, 80, 20);
                parameter5.toggle.reshape(530, 140 + 40 * n10, 10, 20);
            }
            this.repaint();
            this.drawing = false;
        }
        if (event.target == this.setx || event.target == this.sety) {
            this.x1 = Double.valueOf(this.setx.getText());
            this.y1 = Double.valueOf(this.sety.getText());
            this.moving = true;
        }
        if (event.target == this.leftshift) {
            this.x1 -= this.xscale / 10.0;
            this.moving = true;
        }
        if (event.target == this.rightshift) {
            this.x1 += this.xscale / 10.0;
            this.moving = true;
        }
        if (event.target == this.upshift) {
            this.y1 += this.yscale / 10.0;
            this.moving = true;
        }
        if (event.target == this.downshift) {
            this.y1 -= this.yscale / 10.0;
            this.moving = true;
        }
        if (event.target == this.rightbutton) {
            ++this.step;
        }
        if (event.target == this.leftbutton) {
            --this.step;
        }
        if (event.target == this.stopbutton) {
            this.step = 0;
            this.r = 1.0;
        }
        if (event.target == this.inbutton) {
            this.xScale /= 2.0;
            this.yScale /= 2.0;
            this.r = 0.9523809523809523;
        }
        if (event.target == this.outbutton) {
            this.xScale *= 2.0;
            this.yScale *= 2.0;
            this.r = 1.05;
        }
        if (event.target == this.xrange || event.target == this.yrange) {
            this.xscale = Double.valueOf(this.xrange.getText());
            this.yscale = Double.valueOf(this.yrange.getText());
            this.xScale = this.xscale;
            this.yScale = this.yscale;
            this.moving = true;
        }
        for (int n11 = 0; n11 < this.Parameters.size(); ++n11) {
            this.doParam(n11, event);
        }
        return true;
    }
    
    synchronized void doParam(final int n, final Event event) {
        final Parameter parameter = this.Parameters.elementAt(n);
        if (event.target == parameter.input) {
            parameter.step = 0.0;
            parameter.setValue(Double.valueOf(parameter.input.getText()));
            this.graph.paramchange = true;
        }
        if (event.target == parameter.toggle) {
            if (!parameter.controls) {
                parameter.stop.reshape(570, 140 + 40 * n, 0, 0);
                parameter.plus.reshape(540, 130 + 40 * n, 15, 15);
                parameter.minus.reshape(540, 150 + 40 * n, 15, 15);
                parameter.inc.reshape(540, 120 + 40 * n, 30, 15);
                parameter.dec.reshape(540, 160 + 40 * n, 30, 15);
                parameter.powlabel.reshape(555, 140 + 40 * n, 15, 15);
                parameter.setpow.reshape(570, 140 + 40 * n, 30, 20);
                parameter.powup.reshape(585, 120 + 40 * n, 15, 15);
                parameter.powdn.reshape(585, 160 + 40 * n, 15, 15);
                parameter.controls = true;
            }
            else {
                parameter.plus.reshape(550, 130 + 40 * n, 0, 0);
                parameter.minus.reshape(550, 150 + 40 * n, 0, 0);
                parameter.inc.reshape(540, 135 + 40 * n, 30, 15);
                parameter.dec.reshape(540, 145 + 40 * n, 30, 15);
                parameter.stop.reshape(570, 140 + 40 * n, 30, 15);
                parameter.powlabel.reshape(565, 140 + 40 * n, 0, 0);
                parameter.setpow.reshape(575, 140 + 40 * n, 0, 0);
                parameter.powup.reshape(585, 125 + 40 * n, 0, 0);
                parameter.powdn.reshape(585, 155 + 40 * n, 0, 0);
                parameter.controls = false;
            }
        }
        if (event.target == parameter.stop) {
            parameter.step = 0.0;
        }
        if (event.target == parameter.inc) {
            final Parameter parameter2 = parameter;
            ++parameter2.step;
        }
        if (event.target == parameter.dec) {
            final Parameter parameter3 = parameter;
            --parameter3.step;
        }
        if (event.target == parameter.plus) {
            parameter.value += Math.pow(10.0, parameter.pow);
            parameter.input.setText(this.showbrief(Double.toString(parameter.value)));
            this.graph.paramchange = true;
        }
        if (event.target == parameter.minus) {
            parameter.value -= Math.pow(10.0, parameter.pow);
            parameter.input.setText(this.showbrief(Double.toString(parameter.value)));
            this.graph.paramchange = true;
        }
        if (event.target == parameter.powup) {
            final Parameter parameter4 = parameter;
            ++parameter4.pow;
            parameter.setpow.setText(Integer.toString(parameter.pow));
        }
        if (event.target == parameter.powdn) {
            final Parameter parameter5 = parameter;
            --parameter5.pow;
            parameter.setpow.setText(Integer.toString(parameter.pow));
        }
    }
    
    void colorSet() {
        this.Cprompt.reshape(500, 0, 80, 20);
        this.Rprompt.reshape(500, 20, 20, 20);
        this.Rset.setValue(this.c.getRed());
        this.Rset.reshape(520, 20, 60, 20);
        this.Gprompt.reshape(500, 40, 20, 20);
        this.Gset.setValue(this.c.getGreen());
        this.Gset.reshape(520, 40, 60, 20);
        this.Bprompt.reshape(500, 60, 20, 20);
        this.Bset.setValue(this.c.getBlue());
        this.Bset.reshape(520, 60, 60, 20);
        this.Done.reshape(500, 80, 60, 20);
    }
    
    public void start() {
        if (this.animate == null) {
            (this.animate = new Thread(this)).start();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.buffer, 200, 70, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
            if (!this.drawing && !this.pauserequest) {
                this.drawing = true;
                if (this.Parameters.size() > 0) {
                    for (int i = 0; i < this.Parameters.size(); ++i) {
                        this.param = (Parameter)this.Parameters.elementAt(i);
                        this.graph.paramchange = (this.graph.paramchange || this.param.step != 0.0);
                    }
                }
                this.changed = (this.changed || this.moving || !this.ontarget || this.graph.paramchange);
                if (this.changed || this.step != 0 || (this.xscale < this.xScale && this.r > 1.0) || (this.xscale > this.xScale && this.r < 1.0)) {
                    if ((this.xscale < this.xScale && this.r > 1.0) || (this.xscale > this.xScale && this.r < 1.0)) {
                        this.xscale *= this.r;
                        this.yscale *= this.r;
                        this.popup.reshape(0, 0, 0, 0);
                    }
                    this.x1 += this.step * this.xscale / 100.0;
                    if (this.step != 0) {
                        this.popup.reshape(0, 0, 0, 0);
                    }
                    if (this.ontarget) {}
                    if (!this.ontarget) {
                        final double min = Math.min(1.0, Math.sqrt(Math.pow(this.xscale * (this.x1target - this.x1), 2.0) + Math.pow(this.yscale * (this.y1target - this.y1), 2.0)) / (Math.pow(this.x1target - this.x1, 2.0) + Math.pow(this.y1target - this.y1, 2.0)) / 20.0);
                        this.x1 += (this.x1target - this.x1) * min;
                        this.y1 += (this.y1target - this.y1) * min;
                        if (this.x1 == this.x1target && this.y1 == this.y1target) {
                            this.graph.delKeyPoint(this.target);
                            this.ontarget = true;
                        }
                    }
                    if (this.step != 0 || (this.xscale < this.xScale && this.r > 1.0) || (this.xscale > this.xScale && this.r < 1.0)) {
                        this.graph.refine = false;
                    }
                    else {
                        this.graph.refine = true;
                    }
                    if (this.Parameters.size() > 0) {
                        for (int j = 0; j < this.Parameters.size(); ++j) {
                            this.param = (Parameter)this.Parameters.elementAt(j);
                            this.param.value += this.param.step * Math.pow(10.0, this.param.pow - 1);
                        }
                    }
                    this.drawframe(this.g2);
                    this.repaint();
                    for (int k = 0; k < this.Parameters.size(); ++k) {
                        this.param = (Parameter)this.Parameters.elementAt(k);
                        if (this.param.step != 0.0) {
                            this.param.input.setText(this.showbrief(Double.toString(this.param.value)));
                        }
                    }
                    this.drawing = false;
                    this.moving = false;
                    this.changed = false;
                }
                else {
                    this.drawing = false;
                }
            }
        }
    }
    
    public void drawframe(final Graphics graphics) {
        this.drawframe(graphics, 2);
        this.repaint();
    }
    
    public synchronized void drawframe(final Graphics graphics, final int n) {
        this.graph.zoomto(this.x1 - 0.5 * this.xscale, this.y1 - 0.5 * this.yscale, this.x1 + 0.5 * this.xscale, this.y1 + 0.5 * this.yscale);
        this.graph.display(graphics, this.xpos, this.ypos, this.w, this.h, n);
        this.setx.setText(this.showbrief(Double.toString(this.x1)));
        this.sety.setText(this.showbrief(Double.toString(this.y1)));
        this.xrange.setText(this.showbrief(Double.toString(this.xscale)));
        this.yrange.setText(this.showbrief(Double.toString(this.yscale)));
    }
    
    public String showbrief(final String s, final int n) {
        final String s2 = new String();
        String s3;
        if (s.length() <= n) {
            s3 = s;
        }
        else if (s.indexOf(69) != -1) {
            s3 = s.substring(0, n) + s.substring(s.indexOf(69));
        }
        else {
            s3 = s.substring(0, n);
        }
        return s3;
    }
    
    public String showbrief(final String s) {
        return this.showbrief(s, 7);
    }
    
    public void stop() {
        if (this.animate != null) {
            this.animate.stop();
            this.animate = null;
        }
    }
}
