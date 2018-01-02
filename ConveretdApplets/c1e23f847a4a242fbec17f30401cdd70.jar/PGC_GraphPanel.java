import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_GraphPanel extends Panel implements Runnable
{
    private PGC owner;
    private Image offscreen_img;
    private Graphics offscreen_graphics;
    private Dimension offscreen_size;
    private Image background_img;
    private boolean animation_enabled;
    private Thread animation_thread;
    private boolean painting;
    private Point graphsize;
    private Point base;
    private Complex center;
    private int op;
    private int zoom;
    private int tracking_var;
    private Complex tracking_scroll;
    private int current_var;
    private boolean origin_connector;
    private boolean plotting;
    private int font_char_width;
    private int font_char_height;
    private double font_mid_distance;
    private final int PIXMAX = 32752;
    private final int INTMAX = 2147483646;
    
    public PGC_GraphPanel(final PGC owner) {
        this.animation_enabled = false;
        this.painting = false;
        this.graphsize = new Point(0, 0);
        this.base = new Point(0, 0);
        this.center = Complex.Number(0.0);
        this.op = 0;
        this.zoom = 1;
        this.tracking_var = -1;
        this.origin_connector = false;
        this.plotting = false;
        this.owner = owner;
        for (int i = 0; i < this.owner.variable.length; ++i) {
            if (this.owner.variable[i].is_function) {
                this.owner.variable[i].plotting_vec = new Vector();
            }
        }
        this.background_img = this.owner.LoadBackgroundImage();
    }
    
    public boolean GetLine() {
        return this.origin_connector;
    }
    
    public void SetLine(final boolean origin_connector) {
        this.origin_connector = origin_connector;
        this.repaint();
    }
    
    public boolean GetSpin() {
        return this.animation_thread != null;
    }
    
    public void SetSpin(final boolean b) {
        if (b) {
            if (this.animation_thread == null) {
                (this.animation_thread = new Thread(this)).start();
            }
        }
        else if (this.animation_thread != null) {
            this.animation_thread.stop();
            this.animation_thread = null;
            this.repaint();
        }
    }
    
    public void EnableSpin(final boolean animation_enabled) {
        this.animation_enabled = animation_enabled;
    }
    
    public boolean GetEnableSpin() {
        return this.animation_enabled;
    }
    
    public boolean GetPlot() {
        return this.plotting;
    }
    
    public void SetPlot(final boolean plotting) {
        if (!plotting) {
            this.PlotBreak();
        }
        this.plotting = plotting;
        this.repaint();
    }
    
    public void PlotBreak() {
        if (this.plotting) {
            this.PlotAdd(4, null);
            this.PlotAdd(5, null);
            this.PlotAdd(6, null);
            this.PlotAdd(7, null);
            this.PlotAdd(8, null);
            this.PlotAdd(9, null);
        }
    }
    
    public void PlotAdd(final int n, final Complex complex) {
        this.owner.variable[n].plotting_vec.addElement(complex);
    }
    
    public void PlotClear() {
        for (int i = 0; i < this.owner.variable.length; ++i) {
            if (this.owner.variable[i].is_function) {
                this.owner.variable[i].plotting_vec = null;
                this.owner.variable[i].plotting_vec = new Vector();
            }
        }
        this.repaint();
    }
    
    private int FindDisplayableInputVariable(int n) {
        boolean b = false;
        for (int i = 0; i < this.owner.variable.length; ++i) {
            if (n >= this.owner.variable.length) {
                n = 0;
            }
            if (this.owner.variable[n].is_input && this.owner.variable[n].is_displayed) {
                b = true;
                break;
            }
            ++n;
        }
        if (!b) {
            n = 0;
        }
        return n;
    }
    
    public void UpdateDisplay() {
        this.current_var = this.FindDisplayableInputVariable(this.current_var);
        this.SetZoomDelta(0);
    }
    
    public void PaintNow() {
        this.paint(this.getGraphics());
    }
    
    public int GetOp() {
        return this.op;
    }
    
    public void SetOp(final int op) {
        this.op = op;
        this.current_var = this.FindDisplayableInputVariable(this.current_var);
        this.PlotBreak();
        this.repaint();
    }
    
    public int GetZoom() {
        return this.zoom;
    }
    
    public void SetZoomDelta(final int n) {
        final double n2 = this.zoom;
        double n3;
        if (n2 <= 10.0) {
            n3 = n2 + n;
        }
        else if (n2 <= 40.0) {
            n3 = n2 + 2 * n;
        }
        else if (n2 <= 100.0) {
            n3 = n2 + 4 * n;
        }
        else if (n2 <= 400.0) {
            n3 = n2 + 10 * n;
        }
        else if (n2 <= 1000.0) {
            n3 = n2 + 40 * n;
        }
        else if (n2 <= 4000.0) {
            n3 = n2 + 100 * n;
        }
        else if (n2 <= 10000.0) {
            n3 = n2 + 400 * n;
        }
        else if (n2 <= 40000.0) {
            n3 = n2 + 1000 * n;
        }
        else if (n2 <= 100000.0) {
            n3 = n2 + 4000 * n;
        }
        else if (n2 <= 400000.0) {
            n3 = n2 + 10000 * n;
        }
        else if (n2 <= 1000000.0) {
            n3 = n2 + 40000 * n;
        }
        else if (n2 <= 4000000.0) {
            n3 = n2 + 100000 * n;
        }
        else if (n2 <= 1.0E7) {
            n3 = n2 + 400000 * n;
        }
        else if (n2 <= 4.0E7) {
            n3 = n2 + 1000000 * n;
        }
        else if (n2 <= 1.0E8) {
            n3 = n2 + 4000000 * n;
        }
        else if (n2 <= 4.0E8) {
            n3 = n2 + 10000000 * n;
        }
        else if (n2 <= 1.0E9) {
            n3 = n2 + 40000000 * n;
        }
        else {
            n3 = n2 + 100000000 * n;
        }
        int zoom;
        if (n3 < 1.0) {
            zoom = 1;
        }
        else if (n3 > 2.0E9) {
            zoom = 2000000000;
        }
        else {
            zoom = (int)(n3 + 0.5);
        }
        if (zoom < 1) {
            zoom = 1;
        }
        else if (zoom >= 10) {
            if (zoom < 40) {
                zoom = (zoom + 1) / 2 * 2;
            }
            else if (zoom < 100) {
                zoom = (zoom + 3) / 4 * 4;
            }
            else if (zoom < 400) {
                zoom = (zoom + 9) / 10 * 10;
            }
            else if (zoom < 1000) {
                zoom = (zoom + 39) / 40 * 40;
            }
            else if (zoom < 4000) {
                zoom = (zoom + 99) / 100 * 100;
            }
            else if (zoom < 10000) {
                zoom = (zoom + 399) / 400 * 400;
            }
            else if (zoom < 40000) {
                zoom = (zoom + 999) / 1000 * 1000;
            }
            else if (zoom < 100000) {
                zoom = (zoom + 3999) / 4000 * 4000;
            }
            else if (zoom < 400000) {
                zoom = (zoom + 9999) / 10000 * 10000;
            }
            else if (zoom < 1000000) {
                zoom = (zoom + 39999) / 40000 * 40000;
            }
            else if (zoom < 4000000) {
                zoom = (zoom + 99999) / 100000 * 100000;
            }
            else if (zoom < 10000000) {
                zoom = (zoom + 399999) / 400000 * 400000;
            }
            else if (zoom < 40000000) {
                zoom = (zoom + 999999) / 1000000 * 1000000;
            }
            else if (zoom < 100000000) {
                zoom = (zoom + 3999999) / 4000000 * 4000000;
            }
            else if (zoom < 400000000) {
                zoom = (zoom + 9999999) / 10000000 * 10000000;
            }
            else if (zoom < 1000000000) {
                zoom = (zoom + 39999999) / 40000000 * 40000000;
            }
            else {
                zoom = (zoom + 99999999) / 100000000 * 100000000;
            }
        }
        if (zoom < 1) {
            zoom = 1;
        }
        this.zoom = zoom;
        this.repaint();
    }
    
    public void SetZoom(final int zoom) {
        this.zoom = zoom;
        this.SetZoomDelta(0);
    }
    
    public void SetCenterDelta(final int n, final int n2) {
        this.center = this.GetComplex(new Point(this.base.x + n, this.base.y + n2));
        this.repaint();
    }
    
    public void SetCenter(Complex number) {
        if (number == null) {
            number = Complex.Number(0.0, 0.0);
        }
        this.center = number;
        this.repaint();
    }
    
    public void SetActivePoint(final int n) {
        this.current_var = this.FindDisplayableInputVariable(n);
    }
    
    public void NextActivePoint() {
        this.current_var = this.FindDisplayableInputVariable(this.current_var + 1);
    }
    
    public boolean Painting() {
        return this.painting;
    }
    
    private int GetXOrigin() {
        double n = this.base.x - Math.floor(this.center.re * this.zoom + 0.5);
        if (n < -2.147483646E9) {
            n = -2.147483646E9;
        }
        if (n > 2.147483646E9) {
            n = 2.147483646E9;
        }
        return (int)n;
    }
    
    private int GetYOrigin() {
        double n = this.base.y + Math.floor(this.center.im * this.zoom + 0.5);
        if (n < -2.147483646E9) {
            n = -2.147483646E9;
        }
        if (n > 2.147483646E9) {
            n = 2.147483646E9;
        }
        return (int)n;
    }
    
    private void ComplexToPoint(final Complex complex, final Point point) {
        final double n = this.base.x - Math.floor(this.center.re * this.zoom + 0.5);
        final double n2 = this.base.y + Math.floor(this.center.im * this.zoom + 0.5);
        final double floor = Math.floor(complex.re * this.zoom + 0.5);
        final double floor2 = Math.floor(complex.im * this.zoom + 0.5);
        double n3 = n + floor;
        double n4 = n2 - floor2;
        if (n3 < -2.147483646E9) {
            n3 = -2.147483646E9;
            if (floor != 0.0) {
                n4 = n2 - floor2 * (-2.147483646E9 - n) / floor;
            }
        }
        if (n3 > 2.147483646E9) {
            n3 = 2.147483646E9;
            if (floor != 0.0) {
                n4 = n2 - floor2 * (2.147483646E9 - n) / floor;
            }
        }
        if (n4 < -2.147483646E9) {
            n4 = -2.147483646E9;
            if (floor2 != 0.0) {
                n3 = n + floor * (n2 + 2.147483646E9) / floor2;
            }
        }
        if (n4 > 2.147483646E9) {
            n4 = 2.147483646E9;
            if (floor2 != 0.0) {
                n3 = n + floor * (n2 - 2.147483646E9) / floor2;
            }
        }
        point.x = (int)n3;
        point.y = (int)n4;
    }
    
    private void PointToComplex(final Point point, final Complex complex) {
        final double n = this.base.x - Math.floor(this.center.re * this.zoom + 0.5);
        final double n2 = this.base.y + Math.floor(this.center.im * this.zoom + 0.5);
        complex.re = (point.x - n) / this.zoom;
        complex.im = (n2 - point.y) / this.zoom;
    }
    
    private Point GetPoint(final Complex complex) {
        final Point point = new Point(0, 0);
        this.ComplexToPoint(complex, point);
        return point;
    }
    
    private Complex GetComplex(final Point point) {
        final Complex complex = new Complex();
        this.PointToComplex(point, complex);
        return complex;
    }
    
    private boolean PointVisible(final Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < this.graphsize.x && point.y < this.graphsize.y;
    }
    
    private int PixelLimit(final int n, final int n2, final int n3, final int n4, final int n5) {
        double n6 = n2;
        final double n7 = n3 - n;
        final double n8 = n4 - n2;
        if (n7 != 0.0) {
            n6 = n4 - n8 * (n3 - n5) / n7;
        }
        return (int)Math.floor(n6 + 0.5);
    }
    
    private void DrawLine(int n, int n2, int n3, int n4) {
        if (n < -32752) {
            n2 = this.PixelLimit(n, n2, n3, n4, -32752);
            n = -32752;
        }
        if (n > 32752) {
            n2 = this.PixelLimit(n, n2, n3, n4, 32752);
            n = 32752;
        }
        if (n2 < -32752) {
            n = this.PixelLimit(n2, n, n4, n3, -32752);
            n2 = -32752;
        }
        if (n2 > 32752) {
            n = this.PixelLimit(n2, n, n4, n3, 32752);
            n2 = 32752;
        }
        if (n3 < -32752) {
            n4 = this.PixelLimit(n3, n4, n, n2, -32752);
            n3 = -32752;
        }
        if (n3 > 32752) {
            n4 = this.PixelLimit(n3, n4, n, n2, 32752);
            n3 = 32752;
        }
        if (n4 < -32752) {
            n3 = this.PixelLimit(n4, n3, n2, n, -32752);
            n4 = -32752;
        }
        if (n4 > 32752) {
            n3 = this.PixelLimit(n4, n3, n2, n, 32752);
            n4 = 32752;
        }
        this.offscreen_graphics.drawLine(n, n2, n3, n4);
    }
    
    private void DrawRect(final int n, final int n2, final int n3, final int n4) {
        if (n >= -32752 && n <= 32752 && n2 >= -32752 && n2 <= 32752) {
            this.offscreen_graphics.drawRect(n, n2, n3, n4);
        }
    }
    
    private void FillRect(final int n, final int n2, final int n3, final int n4) {
        if (n >= -32752 && n <= 32752 && n2 >= -32752 && n2 <= 32752) {
            this.offscreen_graphics.fillRect(n, n2, n3, n4);
        }
    }
    
    private void DrawString(final String s, final int n, final int n2) {
        if (n >= -32752 && n <= 32752 && n2 >= -32752 && n2 <= 32752) {
            this.offscreen_graphics.drawString(s, n, n2);
        }
    }
    
    private void DrawImage(final Image image, final int n, final int n2, final ImageObserver imageObserver) {
        if (n >= -32752 && n <= 32752 && n2 >= -32752 && n2 <= 32752) {
            this.offscreen_graphics.drawImage(image, n, n2, imageObserver);
        }
    }
    
    private int GetVariableCoordinatesIndex(final Complex complex) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < this.owner.variable.length; ++i) {
            if (this.owner.variable[i].is_input && this.owner.variable[i].is_displayed) {
                final Complex sub = Complex.Sub(this.owner.variable[i].coordinates, complex);
                final double n3 = sub.re * sub.re + sub.im * sub.im;
                if (n3 * (this.zoom * this.zoom) <= 400.0 && (n == -1 || n3 < n2)) {
                    n = i;
                    n2 = n3;
                }
            }
        }
        return n;
    }
    
    private void GetFontInfo() {
        if (this.font_char_height == 0) {
            final char c = 'e';
            final FontMetrics fontMetrics = this.offscreen_graphics.getFontMetrics();
            this.font_char_height = fontMetrics.getAscent();
            this.font_char_width = fontMetrics.charWidth(c);
            this.font_char_height -= this.font_char_height >> 2;
            this.font_mid_distance = Math.sqrt(this.font_char_height * this.font_char_height + this.font_char_width * this.font_char_width) / 2.0;
        }
    }
    
    private void DrawPointLabel(final PGC_Variable pgc_Variable, final Color color) {
        final Complex coordinates = pgc_Variable.coordinates;
        if (coordinates != null) {
            final String name = pgc_Variable.name;
            if (name.length() >= 1) {
                name.charAt(0);
            }
            this.GetFontInfo();
            final int n = 6;
            final double abs = Complex.Abs(coordinates);
            final Complex number = Complex.Number();
            if (abs == 0.0) {
                number.re = 1.0 / this.zoom * (this.font_mid_distance + n);
                number.im = 0.0;
            }
            else {
                number.re = coordinates.re / abs * (1.0 / this.zoom) * (this.font_mid_distance + n);
                number.im = coordinates.im / abs * (1.0 / this.zoom) * (this.font_mid_distance + n);
            }
            Complex complex = Complex.Number(1.0);
            int n2 = 0;
            for (int i = 0; i < this.owner.variable.length; ++i) {
                if (i != pgc_Variable.index && this.owner.variable[i].is_displayed && this.owner.variable[i].coordinates != null) {
                    final Complex sub = Complex.Sub(this.owner.variable[i].coordinates, coordinates);
                    if ((sub.re * sub.re + sub.im * sub.im) * (this.zoom * this.zoom) < 1.0) {
                        final Point getPoint = this.GetPoint(coordinates);
                        final Point getPoint2 = this.GetPoint(this.owner.variable[i].coordinates);
                        if (getPoint.x == getPoint2.x && getPoint.y == getPoint2.y && i < pgc_Variable.index) {
                            ++n2;
                        }
                    }
                }
            }
            if (n2 == 1) {
                complex = Complex.Number(0.0, -1.0);
            }
            else if (n2 == 2) {
                complex = Complex.Number(-1.0, 0.0);
            }
            else if (n2 == 3) {
                complex = Complex.Number(0.0, 1.0);
            }
            final Point getPoint3;
            final Point point = getPoint3 = this.GetPoint(Complex.Add(coordinates, Complex.Mul(number, complex)));
            getPoint3.x -= this.font_char_width / 2;
            final Point point2 = point;
            point2.y += this.font_char_height / 2;
            this.offscreen_graphics.setColor(color);
            this.DrawString(name, point.x, point.y);
        }
    }
    
    private void AnimationStep() {
        final double n = 0.031415926535897934;
        this.owner.variable[this.current_var].coordinates = Complex.Mul(this.owner.variable[this.current_var].coordinates, Complex.Number(Math.cos(n), Math.sin(n)));
    }
    
    private void UserDefined() {
        final PGC_Eval getEval = this.owner.GetEval();
        for (int i = 0; i < this.owner.variable.length; ++i) {
            if (i != 4 || this.op >= 12) {
                if (this.owner.variable[i].is_function && this.owner.variable[i].is_displayed) {
                    getEval.Evaluate(this.owner.variable[i].expression);
                    this.owner.variable[i].coordinates = getEval.value;
                }
            }
        }
    }
    
    public void init() {
    }
    
    public void run() {
        this.setBackground(PGC.color_background);
        while (true) {
            if (!this.painting) {
                this.AnimationStep();
                this.repaint();
            }
            try {
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.owner.DisplayCoordinates(this.GetComplex(new Point(n, n2)));
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.requestFocus();
        this.owner.SetSpin(false);
        if (this.tracking_var == -1) {
            if (event.controlDown() || event.metaDown()) {
                this.tracking_scroll = this.GetComplex(new Point(n, n2));
            }
            else {
                this.tracking_var = this.GetVariableCoordinatesIndex(this.GetComplex(new Point(n, n2)));
                if (this.tracking_var >= 0) {
                    this.current_var = this.tracking_var;
                }
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.owner.SetSpin(false);
        final Complex getComplex = this.GetComplex(new Point(n, n2));
        this.owner.DisplayCoordinates(getComplex);
        if (this.tracking_var >= 0) {
            if (event.shiftDown()) {
                final Complex floor = Complex.Floor(Complex.Number(getComplex.re + 0.5, getComplex.im + 0.5));
                final double abs = Math.abs(getComplex.re - floor.re);
                final double abs2 = Math.abs(getComplex.im - floor.im);
                if (abs <= 9.0 / this.zoom && abs2 <= 9.0 / this.zoom) {
                    getComplex.re = floor.re;
                    getComplex.im = floor.im;
                }
                else if (abs > abs2) {
                    getComplex.im = floor.im;
                }
                else {
                    getComplex.re = floor.re;
                }
            }
            this.owner.variable[this.tracking_var].coordinates = getComplex;
            this.repaint();
        }
        else if (this.tracking_scroll != null) {
            final Point getPoint = this.GetPoint(this.tracking_scroll);
            this.owner.SetCenter(this.GetComplex(new Point(getPoint.x - (n - this.base.x), getPoint.y - (n2 - this.base.y))));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.owner.SetSpin(false);
        if (this.tracking_var >= 0) {
            this.tracking_var = -1;
            this.repaint();
        }
        if (this.tracking_scroll != null) {
            this.tracking_scroll = null;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.owner.DisplayCoordinates(null);
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        if (event.shiftDown()) {
            n4 = 5;
        }
        final boolean controlDown = event.controlDown();
        if (n == 1004) {
            b = true;
            b2 = true;
            n2 = 0;
            n3 = -n4;
        }
        else if (n == 1005) {
            b = true;
            b2 = true;
            n2 = 0;
            n3 = n4;
        }
        else if (n == 1006) {
            b = true;
            b2 = true;
            n2 = -n4;
            n3 = 0;
        }
        else if (n == 1007) {
            b = true;
            b2 = true;
            n2 = n4;
            n3 = 0;
        }
        else if (n == 43) {
            b = true;
            this.owner.SetZoomDelta(1);
        }
        else if (n == 61) {
            b = true;
            this.owner.SetZoomDelta(1);
        }
        else if (n == 45) {
            b = true;
            this.owner.SetZoomDelta(-1);
        }
        else if (n == 108) {
            b = true;
            this.owner.CheckLine(null);
        }
        else if (n == 76) {
            b = true;
            this.owner.CheckLine(null);
        }
        else if (n == 115) {
            b = true;
            this.owner.CheckSpin(null);
        }
        else if (n == 83) {
            b = true;
            this.owner.CheckSpin(null);
        }
        else if (n == 112) {
            b = true;
            this.owner.CheckPlot(null);
        }
        else if (n == 80) {
            b = true;
            this.owner.CheckPlot(null);
        }
        else if (n == 99) {
            b = true;
            this.owner.Clear();
        }
        else if (n == 67) {
            b = true;
            this.owner.Clear();
        }
        else if (n == 32) {
            b = true;
            this.owner.Step(1);
        }
        else if (n == 62) {
            b = true;
            this.owner.Step(100);
        }
        else if (n == 122) {
            b = true;
            this.owner.SetCenter(null);
            this.owner.SetZoom(this.owner.option_zoom);
        }
        else if (n == 90) {
            b = true;
            this.owner.SetCenter(null);
            this.owner.SetZoom(this.owner.option_zoom);
        }
        else if (n == 110) {
            b = true;
            b3 = true;
        }
        else if (n == 78) {
            b = true;
            b3 = true;
        }
        if (b2) {
            if (controlDown) {
                this.owner.SetCenterDelta(n2 * 20, n3 * 20);
            }
            else {
                final Point getPoint;
                final Point point = getPoint = this.GetPoint(this.owner.variable[this.current_var].coordinates);
                getPoint.x += n2;
                final Point point2 = point;
                point2.y += n3;
                this.owner.variable[this.current_var].coordinates = this.GetComplex(point);
                this.repaint();
            }
        }
        if (b3) {
            this.NextActivePoint();
        }
        return b;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        this.graphsize.x = size.width;
        this.graphsize.y = size.height;
        if (this.graphsize.x < 1 || this.graphsize.y < 1) {
            return;
        }
        this.painting = true;
        this.base.x = this.graphsize.x / 2;
        this.base.y = this.graphsize.y / 2;
        if (this.offscreen_img == null || this.graphsize.x != this.offscreen_size.width || this.graphsize.y != this.offscreen_size.height) {
            this.offscreen_img = this.createImage(this.graphsize.x, this.graphsize.y);
            this.offscreen_size = new Dimension(size);
            (this.offscreen_graphics = this.offscreen_img.getGraphics()).setFont(this.getFont());
        }
        this.owner.variable[4].coordinates = null;
        this.owner.variable[5].coordinates = null;
        this.owner.variable[6].coordinates = null;
        this.owner.variable[7].coordinates = null;
        this.owner.variable[8].coordinates = null;
        this.owner.variable[9].coordinates = null;
        if (this.op == 1) {
            this.owner.variable[4].coordinates = Complex.Add(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
        }
        else if (this.op == 2) {
            this.owner.variable[4].coordinates = Complex.Sub(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
        }
        else if (this.op == 3) {
            this.owner.variable[4].coordinates = Complex.Mul(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
        }
        else if (this.op == 4) {
            this.owner.variable[4].coordinates = Complex.Div(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
        }
        else if (this.op == 5) {
            this.owner.variable[4].coordinates = Complex.Pow(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
        }
        else if (this.op == 6) {
            this.owner.variable[4].coordinates = Complex.Number(Complex.Dot(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates));
        }
        else if (this.op == 7) {
            this.owner.variable[4].coordinates = Complex.Number(Complex.Cross(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates));
        }
        else if (this.op == 8) {
            this.owner.variable[4].coordinates = Complex.Number(Complex.Dot(this.owner.variable[0].coordinates, this.owner.variable[2].coordinates), Complex.Dot(this.owner.variable[1].coordinates, this.owner.variable[2].coordinates));
        }
        else if (this.op == 9) {
            this.owner.variable[4].coordinates = Complex.Sqrt(this.owner.variable[0].coordinates);
        }
        else if (this.op == 10) {
            this.owner.variable[4].coordinates = Complex.Pow(this.owner.variable[0].coordinates, Complex.Number(0.3333333333333333));
        }
        else if (this.op == 11) {
            this.owner.variable[4].coordinates = Complex.Exp(this.owner.variable[0].coordinates);
        }
        this.UserDefined();
        Complex coordinates = null;
        Complex coordinates2 = null;
        Complex coordinates3 = null;
        Complex coordinates4 = null;
        Complex coordinates5 = null;
        Complex coordinates6 = null;
        Complex coordinates7 = null;
        Complex coordinates8 = null;
        Complex coordinates9 = null;
        Complex coordinates10 = null;
        if (this.owner.variable[0].is_displayed) {
            coordinates = this.owner.variable[0].coordinates;
        }
        if (this.owner.variable[1].is_displayed) {
            coordinates2 = this.owner.variable[1].coordinates;
        }
        if (this.owner.variable[2].is_displayed) {
            coordinates3 = this.owner.variable[2].coordinates;
        }
        if (this.owner.variable[3].is_displayed) {
            coordinates4 = this.owner.variable[3].coordinates;
        }
        if (this.owner.variable[4].is_displayed) {
            coordinates5 = this.owner.variable[4].coordinates;
        }
        if (this.owner.variable[5].is_displayed) {
            coordinates6 = this.owner.variable[5].coordinates;
        }
        if (this.owner.variable[6].is_displayed) {
            coordinates7 = this.owner.variable[6].coordinates;
        }
        if (this.owner.variable[7].is_displayed) {
            coordinates8 = this.owner.variable[7].coordinates;
        }
        if (this.owner.variable[8].is_displayed) {
            coordinates9 = this.owner.variable[8].coordinates;
        }
        if (this.owner.variable[9].is_displayed) {
            coordinates10 = this.owner.variable[9].coordinates;
        }
        this.owner.TextFieldsOutput(coordinates, coordinates2, coordinates3, coordinates4, coordinates5, coordinates6, coordinates7, coordinates8, coordinates9, coordinates10);
        if (this.plotting) {
            if (coordinates5 != null) {
                this.PlotAdd(4, coordinates5);
            }
            if (coordinates6 != null) {
                this.PlotAdd(5, coordinates6);
            }
            if (coordinates7 != null) {
                this.PlotAdd(6, coordinates7);
            }
            if (coordinates8 != null) {
                this.PlotAdd(7, coordinates8);
            }
            if (coordinates9 != null) {
                this.PlotAdd(8, coordinates9);
            }
            if (coordinates10 != null) {
                this.PlotAdd(9, coordinates10);
            }
        }
        this.offscreen_graphics.setColor(PGC.color_background);
        this.offscreen_graphics.fillRect(0, 0, this.graphsize.x, this.graphsize.y);
        if (this.background_img != null) {
            this.DrawImage(this.background_img, this.GetXOrigin() - this.background_img.getWidth(this) / 2, this.GetYOrigin() - this.background_img.getHeight(this) / 2, this);
        }
        final Complex getComplex = this.GetComplex(new Point(0, 0));
        final Complex getComplex2 = this.GetComplex(this.graphsize);
        int zoom;
        if (this.zoom >= 8) {
            zoom = this.zoom;
            getComplex.re = Math.floor(getComplex.re);
            getComplex.im = Math.floor(getComplex.im);
            getComplex2.re = Math.ceil(getComplex2.re);
            getComplex2.im = Math.ceil(getComplex2.im);
        }
        else {
            zoom = this.zoom * 10;
            getComplex.re = Math.floor(getComplex.re / 10.0) * 10.0;
            getComplex.im = Math.floor(getComplex.im / 10.0) * 10.0;
            getComplex2.re = Math.ceil(getComplex2.re / 10.0) * 10.0;
            getComplex2.im = Math.ceil(getComplex2.im / 10.0) * 10.0;
        }
        final Point getPoint = this.GetPoint(getComplex);
        final Point getPoint2 = this.GetPoint(getComplex2);
        this.offscreen_graphics.setColor(PGC.color_grid);
        for (int i = getPoint.x; i <= getPoint2.x; i += zoom) {
            this.DrawLine(i, 0, i, this.graphsize.y);
        }
        for (int j = getPoint.y; j <= getPoint2.y; j += zoom) {
            this.DrawLine(0, j, this.graphsize.x, j);
        }
        this.offscreen_graphics.setColor(PGC.color_axis);
        this.DrawLine(0, this.GetYOrigin(), this.graphsize.x, this.GetYOrigin());
        this.DrawLine(this.GetXOrigin(), 0, this.GetXOrigin(), this.graphsize.y);
        final PGC_Eval getEval = this.owner.GetEval();
        for (int k = 0; k < this.owner.variable.length; ++k) {
            if (this.owner.variable[k].is_function && !this.owner.variable[k].is_displayed) {
                final String expression = this.owner.variable[k].expression;
                if (k == 4) {
                    this.offscreen_graphics.setColor(PGC.color_func_f_plot);
                }
                if (k == 5) {
                    this.offscreen_graphics.setColor(PGC.color_func_g_plot);
                }
                if (k == 6) {
                    this.offscreen_graphics.setColor(PGC.color_func_h_plot);
                }
                if (k == 7) {
                    this.offscreen_graphics.setColor(PGC.color_func_k_plot);
                }
                if (k == 8) {
                    this.offscreen_graphics.setColor(PGC.color_func_m_plot);
                }
                if (k == 9) {
                    this.offscreen_graphics.setColor(PGC.color_func_n_plot);
                }
                getEval.Evaluate(expression);
                if (getEval.uses_autoplot_t) {
                    Point point = null;
                    final Complex number = Complex.Number(0.0);
                    double n = this.owner.t_minimum;
                    double n2 = this.owner.t_maximum;
                    int t_count = this.owner.t_count;
                    if (n > n2) {
                        n = this.owner.t_maximum;
                        n2 = this.owner.t_minimum;
                    }
                    if (t_count < 1) {
                        t_count = 1;
                    }
                    for (double n3 = (n2 - n) / t_count, n4 = n2 + n3, re = n; re < n4; re += n3) {
                        number.re = re;
                        number.im = 0.0;
                        this.owner.SetAutoplotVariable(number);
                        getEval.Evaluate(expression);
                        final Complex value = getEval.value;
                        if (value == null) {
                            point = null;
                        }
                        else if (!Complex.IsDefined(value)) {
                            point = null;
                        }
                        else {
                            final Point getPoint3 = this.GetPoint(value);
                            this.FillRect(getPoint3.x, getPoint3.y, 1, 1);
                            if (point != null && (this.PointVisible(point) || this.PointVisible(getPoint3))) {
                                this.DrawLine(point.x, point.y, getPoint3.x, getPoint3.y);
                            }
                            point = getPoint3;
                        }
                    }
                }
                else if (getEval.uses_autoplot_x) {
                    Point point2 = null;
                    final Point point3 = new Point(0, 0);
                    final Complex number2 = Complex.Number(0.0);
                    for (int l = 0; l < this.graphsize.x; ++l) {
                        point3.x = l;
                        point3.y = 0;
                        this.PointToComplex(point3, number2);
                        number2.im = 0.0;
                        this.owner.SetAutoplotVariable(number2);
                        getEval.Evaluate(expression);
                        final Complex value2 = getEval.value;
                        if (value2 == null) {
                            point2 = null;
                        }
                        else if (!this.owner.LooksReal(value2)) {
                            point2 = null;
                        }
                        else if (!Complex.IsDefined(value2)) {
                            point2 = null;
                        }
                        else {
                            value2.im = value2.re;
                            value2.re = number2.re;
                            final Point getPoint4 = this.GetPoint(value2);
                            this.FillRect(getPoint4.x, getPoint4.y, 1, 1);
                            if (point2 != null && (this.PointVisible(point2) || this.PointVisible(getPoint4))) {
                                this.DrawLine(point2.x, point2.y, getPoint4.x, getPoint4.y);
                            }
                            point2 = getPoint4;
                        }
                    }
                }
                this.owner.SetAutoplotVariable(null);
            }
        }
        for (int n5 = 0; n5 < this.owner.variable.length; ++n5) {
            if (this.owner.variable[n5].plotting_vec != null) {
                Point point4 = null;
                if (n5 == 4) {
                    this.offscreen_graphics.setColor(PGC.color_func_f_plot);
                }
                if (n5 == 5) {
                    this.offscreen_graphics.setColor(PGC.color_func_g_plot);
                }
                if (n5 == 6) {
                    this.offscreen_graphics.setColor(PGC.color_func_h_plot);
                }
                if (n5 == 7) {
                    this.offscreen_graphics.setColor(PGC.color_func_k_plot);
                }
                if (n5 == 8) {
                    this.offscreen_graphics.setColor(PGC.color_func_m_plot);
                }
                if (n5 == 9) {
                    this.offscreen_graphics.setColor(PGC.color_func_n_plot);
                }
                for (int n6 = 0; n6 < this.owner.variable[n5].plotting_vec.size(); ++n6) {
                    final Complex complex = this.owner.variable[n5].plotting_vec.elementAt(n6);
                    if (complex == null) {
                        point4 = null;
                    }
                    else if (!Complex.IsDefined(complex)) {
                        point4 = null;
                    }
                    else {
                        final Point getPoint5 = this.GetPoint(complex);
                        this.FillRect(getPoint5.x, getPoint5.y, 1, 1);
                        if (point4 != null && (this.PointVisible(point4) || this.PointVisible(getPoint5))) {
                            this.DrawLine(point4.x, point4.y, getPoint5.x, getPoint5.y);
                        }
                        point4 = getPoint5;
                    }
                }
            }
        }
        for (int n7 = 0; n7 < this.owner.variable.length; ++n7) {
            if (this.owner.variable[n7].is_input && this.owner.variable[n7].is_displayed && Complex.IsDefined(this.owner.variable[n7].coordinates)) {
                final Point getPoint6 = this.GetPoint(this.owner.variable[n7].coordinates);
                if (this.origin_connector) {
                    this.offscreen_graphics.setColor(PGC.color_origin_connector_line);
                    this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint6.x, getPoint6.y);
                }
                this.DrawPointLabel(this.owner.variable[n7], PGC.color_input_label);
            }
        }
        this.DrawPointLabel(this.owner.variable[4], PGC.color_func_f_label);
        this.DrawPointLabel(this.owner.variable[5], PGC.color_func_g_label);
        this.DrawPointLabel(this.owner.variable[6], PGC.color_func_h_label);
        this.DrawPointLabel(this.owner.variable[7], PGC.color_func_k_label);
        this.DrawPointLabel(this.owner.variable[8], PGC.color_func_m_label);
        this.DrawPointLabel(this.owner.variable[9], PGC.color_func_n_label);
        for (int n8 = 0; n8 < this.owner.variable.length; ++n8) {
            if (this.owner.variable[n8].is_input && this.owner.variable[n8].is_displayed) {
                final Rectangle rectangle = new Rectangle(this.GetPoint(this.owner.variable[n8].coordinates));
                rectangle.grow(2, 2);
                this.offscreen_graphics.setColor(PGC.color_input_point);
                this.DrawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        for (int n9 = 0; n9 < this.owner.variable.length; ++n9) {
            if (this.owner.variable[n9].is_function && this.owner.variable[n9].is_displayed) {
                Complex complex2 = this.owner.variable[n9].coordinates;
                if (complex2 != null && Complex.IsDefined(complex2)) {
                    int n10 = 0;
                    while (true) {
                        final Point getPoint7 = this.GetPoint(complex2);
                        if (this.origin_connector) {
                            this.offscreen_graphics.setColor(PGC.color_origin_connector_line);
                            this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint7.x, getPoint7.y);
                        }
                        final Rectangle rectangle2 = new Rectangle(getPoint7);
                        rectangle2.grow(2, 2);
                        if (n9 == 4) {
                            this.offscreen_graphics.setColor(PGC.color_func_f_point);
                        }
                        if (n9 == 5) {
                            this.offscreen_graphics.setColor(PGC.color_func_g_point);
                        }
                        if (n9 == 6) {
                            this.offscreen_graphics.setColor(PGC.color_func_h_point);
                        }
                        if (n9 == 7) {
                            this.offscreen_graphics.setColor(PGC.color_func_k_point);
                        }
                        if (n9 == 8) {
                            this.offscreen_graphics.setColor(PGC.color_func_m_point);
                        }
                        if (n9 == 9) {
                            this.offscreen_graphics.setColor(PGC.color_func_n_point);
                        }
                        this.DrawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                        if (n9 == 4 && this.op == 9 && n10 < 1) {
                            complex2 = Complex.Neg(complex2);
                        }
                        else {
                            if (n9 != 4 || this.op != 10 || n10 >= 2) {
                                break;
                            }
                            complex2 = Complex.Mul(complex2, Complex.Number(-0.5, Math.sqrt(3.0) / 2.0));
                        }
                        ++n10;
                    }
                }
            }
        }
        if ((this.op == 6 || this.op == 7) && (this.tracking_var != -1 || this.animation_thread != null)) {
            Complex complex3;
            Point point5;
            Point point6;
            if (this.current_var == 0) {
                complex3 = Complex.Shadow(this.owner.variable[0].coordinates, this.owner.variable[1].coordinates);
                point5 = this.GetPoint(this.owner.variable[0].coordinates);
                point6 = this.GetPoint(this.owner.variable[1].coordinates);
            }
            else {
                complex3 = Complex.Shadow(this.owner.variable[1].coordinates, this.owner.variable[0].coordinates);
                point5 = this.GetPoint(this.owner.variable[1].coordinates);
                point6 = this.GetPoint(this.owner.variable[0].coordinates);
            }
            final Point getPoint8 = this.GetPoint(complex3);
            this.offscreen_graphics.setColor(PGC.color_origin_connector_line);
            this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), point5.x, point5.y);
            this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), point6.x, point6.y);
            this.offscreen_graphics.setColor(PGC.color_lightray);
            this.DrawLine(point5.x, point5.y, getPoint8.x, getPoint8.y);
            this.offscreen_graphics.setColor(PGC.color_shadow);
            final Rectangle rectangle3 = new Rectangle(getPoint8);
            rectangle3.grow(2, 2);
            this.DrawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
            this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint8.x, getPoint8.y);
        }
        if (this.op == 8 && (this.tracking_var == 2 || (this.animation_thread != null && this.current_var == 2))) {
            for (int n11 = 0; n11 <= 1; ++n11) {
                final Complex shadow = Complex.Shadow(this.owner.variable[2].coordinates, this.owner.variable[n11].coordinates);
                final Point getPoint9 = this.GetPoint(this.owner.variable[2].coordinates);
                final Point getPoint10 = this.GetPoint(this.owner.variable[n11].coordinates);
                final Point getPoint11 = this.GetPoint(shadow);
                this.offscreen_graphics.setColor(PGC.color_origin_connector_line);
                this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint9.x, getPoint9.y);
                this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint10.x, getPoint10.y);
                this.offscreen_graphics.setColor(PGC.color_lightray);
                this.DrawLine(getPoint9.x, getPoint9.y, getPoint11.x, getPoint11.y);
                this.offscreen_graphics.setColor(PGC.color_shadow);
                final Rectangle rectangle4 = new Rectangle(getPoint11);
                rectangle4.grow(2, 2);
                this.DrawRect(rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height);
                this.DrawLine(this.GetXOrigin(), this.GetYOrigin(), getPoint11.x, getPoint11.y);
                int n12 = -1;
                if (n11 == 0) {
                    n12 = 10;
                }
                else if (n11 == 1) {
                    n12 = 11;
                }
                if (n12 != -1) {
                    this.owner.variable[n12].coordinates = shadow;
                    this.owner.variable[n12].is_displayed = true;
                    this.DrawPointLabel(this.owner.variable[n12], PGC.color_shadow_label);
                    this.owner.variable[n12].is_displayed = false;
                }
            }
        }
        if (this.owner.option_graph_border) {
            this.offscreen_graphics.setColor(PGC.color_3d_dark);
            this.offscreen_graphics.drawLine(0, 0, this.graphsize.x - 2, 0);
            this.offscreen_graphics.drawLine(0, 0, 0, this.graphsize.y - 2);
            this.offscreen_graphics.setColor(Color.black);
            this.offscreen_graphics.drawLine(1, 1, this.graphsize.x - 1, 1);
            this.offscreen_graphics.drawLine(1, 1, 1, this.graphsize.y - 1);
            this.offscreen_graphics.setColor(Color.lightGray);
            this.offscreen_graphics.drawLine(this.graphsize.x - 2, 1, this.graphsize.x - 2, this.graphsize.y - 2);
            this.offscreen_graphics.drawLine(1, this.graphsize.y - 2, this.graphsize.x - 2, this.graphsize.y - 2);
            this.offscreen_graphics.setColor(PGC.color_3d_light);
            this.offscreen_graphics.drawLine(this.graphsize.x - 1, 0, this.graphsize.x - 1, this.graphsize.y - 1);
            this.offscreen_graphics.drawLine(0, this.graphsize.y - 1, this.graphsize.x - 1, this.graphsize.y - 1);
        }
        graphics.drawImage(this.offscreen_img, 0, 0, this);
        this.painting = false;
    }
}
