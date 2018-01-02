// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.gui.Console;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import jfig.utils.SetupManager;
import java.awt.Point;
import java.awt.Color;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.awt.event.ActionListener;
import java.io.InputStream;
import jfig.canvas.FigTrafo2D;
import java.util.Vector;
import jfig.canvas.ObjectPainter;
import jfig.gui.ConsoleMessage;
import jfig.gui.StatusMessage;
import jfig.gui.ColorCache;

public class FigParser implements Runnable
{
    boolean debug;
    boolean fastMessages;
    int n_errors;
    int line_number;
    ColorCache colorCache;
    int input_resolution;
    int fig_version;
    StatusMessage helper;
    ConsoleMessage printer;
    ObjectPainter painter;
    Vector actionListeners;
    String gl_filename;
    String gl_filetype;
    StringBuffer comment;
    boolean enableNonASCII;
    boolean enableMessages;
    String[] paperSizes;
    private int[] gl_xp;
    private int[] gl_yp;
    public boolean solaris_is_broken;
    int t_errors;
    boolean t_top_level;
    boolean t_inside_compound;
    boolean t_merge;
    FigAttribs t_attribs;
    FigTrafo2D t_trafo;
    FigObjectList t_OL;
    Thread reader;
    InputStream t_f;
    private int n_messages;
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListeners.addElement(actionListener);
    }
    
    public void setFilenameAndType(final String gl_filename, final String gl_filetype) {
        this.gl_filename = gl_filename;
        this.gl_filetype = gl_filetype;
    }
    
    public void setObjectPainter(final ObjectPainter painter) {
        this.painter = painter;
    }
    
    public void setStatusMessage(final StatusMessage helper) {
        this.helper = helper;
    }
    
    public void setConsole(final ConsoleMessage printer) {
        this.printer = printer;
    }
    
    public void set_debug() {
        this.debug = true;
    }
    
    public void reset_debug() {
        this.debug = false;
    }
    
    public void parse_jfig_trigger(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        if (this.debug) {
            this.message("FigParser: FigTrigger: " + s);
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            Integer.parseInt(stringTokenizer.nextToken());
            final int fig_scale = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale2 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale3 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale4 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            String nextToken = stringTokenizer.nextToken();
            String nextToken2 = stringTokenizer.nextToken();
            if (nextToken.equals("\"\"")) {
                nextToken = null;
            }
            if (nextToken2.equals("\"\"")) {
                nextToken2 = null;
            }
            final FigTrigger figTrigger = new FigTrigger(fig_scale, fig_scale2, fig_scale3, fig_scale4, figAttribs, figTrafo2D, nextToken, nextToken2);
            list.insert((FigObject)figTrigger);
            this.check_add_object_comment((FigObject)figTrigger);
        }
        catch (NumberFormatException ex) {
            this.message("-E- Not a valid jfig trigger.");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
        }
    }
    
    final int fig_scale(final int n) {
        if (this.fig_version >= 30) {
            return n * this.input_resolution;
        }
        return (n + 1) * this.input_resolution;
    }
    
    final double fig_scale(final double n) {
        return n * this.input_resolution;
    }
    
    final double fig_arrow_scale(final double n) {
        return n * this.input_resolution;
    }
    
    final int fig_thickness(final int n) {
        if (n <= 0) {
            return 0;
        }
        return n * 30;
    }
    
    final Color old_fig_color(final int n) {
        Color color = null;
        switch (n) {
            case -1: {
                color = Color.black;
                break;
            }
            case 0: {
                color = Color.black;
                break;
            }
            case 1: {
                color = Color.blue;
                break;
            }
            case 2: {
                color = Color.green;
                break;
            }
            case 3: {
                color = Color.cyan;
                break;
            }
            case 4: {
                color = Color.red;
                break;
            }
            case 5: {
                color = Color.magenta;
                break;
            }
            case 6: {
                color = Color.yellow;
                break;
            }
            case 7: {
                color = Color.white;
                break;
            }
            default: {
                color = Color.orange;
                break;
            }
        }
        if (color == null) {
            this.message("FigParser: fig_color broken...");
        }
        return color;
    }
    
    final Color fig_color(final int n) {
        final Color value = this.colorCache.get(n);
        if (value != null) {
            return value;
        }
        this.message("-E- Internal error (unknown color, index=" + n + "), substituting Orange...");
        return Color.orange;
    }
    
    final void handleLineStyle(final FigAttribs figAttribs, final int n, final double n2) {
        switch (n) {
            case -1:
            case 0: {
                figAttribs.lineStyle = 0;
                figAttribs.dashLength = 0.0;
                break;
            }
            case 1: {
                figAttribs.lineStyle = 1;
                figAttribs.dashLength = 30.0 * n2;
                break;
            }
            case 2: {
                figAttribs.lineStyle = 2;
                figAttribs.dashLength = 30.0 * n2;
                break;
            }
            case 3: {
                figAttribs.lineStyle = 3;
                figAttribs.dashLength = 30.0 * n2;
                break;
            }
            case 4: {
                figAttribs.lineStyle = 4;
                figAttribs.dashLength = 30.0 * n2;
                break;
            }
            case 5: {
                figAttribs.lineStyle = 5;
                figAttribs.dashLength = 30.0 * n2;
                break;
            }
            default: {
                this.message("-E- Illegal line style " + n + " Using SOLID_LINE instead!");
                figAttribs.lineStyle = 0;
                break;
            }
        }
        if (this.debug && n > 0) {
            this.message("Line-style dashed/dotted...");
            this.message("dashLength is " + figAttribs.dashLength);
        }
    }
    
    final void handleFillStyle(final int n, final FigAttribs figAttribs) {
        figAttribs.fillStyle = 1;
        final int fig_area_fill = figAttribs.fig_area_fill;
        if (fig_area_fill >= 0 && fig_area_fill <= 40) {
            if (this.fig_version >= 30) {
                figAttribs.fillStyle = 2;
                figAttribs.fillColor = this.colorCache.get(n, fig_area_fill);
                if (figAttribs.fillColor == null) {
                    this.message("-E- No color found for " + n + " and area_fill " + fig_area_fill);
                }
            }
            else {
                figAttribs.fig_fill_color = 0;
                figAttribs.fillStyle = 2;
                --figAttribs.fig_area_fill;
                figAttribs.fillColor = this.colorCache.getFIG21Gray(fig_area_fill);
                if (figAttribs.fillColor == null) {
                    figAttribs.fillStyle = 1;
                    figAttribs.fillColor = Color.black;
                }
            }
        }
        else if (fig_area_fill >= 41) {
            figAttribs.fillStyle = 3;
            if (!figAttribs.getEnableJava2D()) {
                this.message("Area fill style (pattern fill)" + fig_area_fill + " not supported without Java2D. Using solid fill instead.");
            }
        }
        else {
            figAttribs.fillColor = Color.black;
            figAttribs.fillStyle = 1;
        }
        if (figAttribs.fillColor == null) {
            this.message("-E- Internal error in 'handleFillStyle()': fillColor is null, using orange instead!");
            figAttribs.fillColor = Color.orange;
        }
    }
    
    final void old_handleFillStyle(final FigAttribs figAttribs) {
        figAttribs.fillStyle = 1;
        final int fig_area_fill = figAttribs.fig_area_fill;
        if (fig_area_fill > 0 && fig_area_fill <= 20) {
            if (figAttribs.fillColor == Color.black && fig_area_fill <= 20) {
                if (this.debug) {
                    this.message("handleFillStyle for black[" + fig_area_fill + "] ");
                }
                figAttribs.fillStyle = 2;
                final double n = 0.05 * fig_area_fill;
                figAttribs.fillColor = new Color((float)(1.0 - n), (float)(1.0 - n), (float)(1.0 - n));
            }
            else if (figAttribs.fillColor == Color.white && fig_area_fill <= 20) {
                figAttribs.fillStyle = 2;
                final double n2 = 0.05 * fig_area_fill;
                figAttribs.fillColor = new Color((float)n2, (float)n2, (float)n2);
            }
            else {
                if (this.debug) {
                    this.message("-W- filled colored object:  Color may be wrong (tint=" + fig_area_fill + ") ");
                }
                figAttribs.fillStyle = 2;
            }
        }
        else if (fig_area_fill > 41) {
            figAttribs.fillStyle = 2;
            figAttribs.fillColor = Color.orange;
            this.message("Area fill style (pattern fill)" + fig_area_fill + " not yet supported. Using solid fill instead.");
        }
    }
    
    final void handle_forward_arrow(final FigAttribs figAttribs, final FigArrowParams figArrowParams) {
        ++figAttribs.arrowMode;
        figAttribs.arrow_f_Width = figArrowParams.width;
        figAttribs.arrow_f_Length = figArrowParams.height;
        figAttribs.arrow_f_Thickness = figArrowParams.thickness;
        figAttribs.arrow_f_Style = 2 * figArrowParams.type + 2;
        if (figArrowParams.style == 1) {
            ++figAttribs.arrow_f_Style;
        }
    }
    
    final void handle_backward_arrow(final FigAttribs figAttribs, final FigArrowParams figArrowParams) {
        figAttribs.arrowMode += 2;
        figAttribs.arrow_b_Width = figArrowParams.width;
        figAttribs.arrow_b_Length = figArrowParams.height;
        figAttribs.arrow_b_Thickness = figArrowParams.thickness;
        figAttribs.arrow_b_Style = 2 * figArrowParams.type + 2;
        if (figArrowParams.style == 1) {
            ++figAttribs.arrow_b_Style;
        }
    }
    
    void collect_object_comment(final String s) {
        if (this.comment == null) {
            (this.comment = new StringBuffer()).append(s.substring(1).trim());
        }
        else {
            this.comment.append('\n');
            this.comment.append(s.substring(1).trim());
        }
    }
    
    void check_add_object_comment(final FigObject figObject) {
        if (this.comment != null) {
            figObject.setComment(this.comment.toString());
        }
        this.comment = null;
    }
    
    void parse_fig_pseudocolor(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigObjectList list) {
        if (this.debug) {
            this.message("parse_fig_pseudocolor()...");
        }
        this.statusMessage("parsing pseudocolor (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            ColorCache.getColorCache().putUserColor(int1, Integer.parseInt(nextToken.substring(1, nextToken.length()), 16));
        }
        catch (NumberFormatException ex) {
            this.message("-E- Not a valid FIG3.1 file (pseudocolor)");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_ellipse(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        if (this.debug) {
            this.message("parse_fig_ellipse()...");
        }
        this.statusMessage("parsing ellipse (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            Integer.parseInt(stringTokenizer.nextToken());
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            int int4;
            if (this.fig_version >= 30) {
                int4 = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                int4 = int3;
            }
            final int int5 = Integer.parseInt(stringTokenizer.nextToken());
            final int int6 = Integer.parseInt(stringTokenizer.nextToken());
            final int int7 = Integer.parseInt(stringTokenizer.nextToken());
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            final int int8 = Integer.parseInt(stringTokenizer.nextToken());
            final double doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
            final int fig_scale = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale2 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale3 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale4 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            figAttribs.lineWidth = this.fig_thickness(int2);
            figAttribs.lineColor = this.fig_color(int3);
            figAttribs.fillColor = this.fig_color(int4);
            figAttribs.fig_line_color = int3;
            figAttribs.fig_fill_color = int4;
            figAttribs.currentLayer = int5;
            figAttribs.fig_pen_style = int6;
            figAttribs.fig_area_fill = int7;
            this.handleLineStyle(figAttribs, int1, figAttribs.fig_style_val = doubleValue);
            this.handleFillStyle(int4, figAttribs);
            figAttribs.fig_ellipse_direction = int8;
            figAttribs.fig_angle = doubleValue2;
            figAttribs.fig_ellipse_angle = doubleValue2;
            final Point point = new Point(0, 0);
            point.x = fig_scale;
            point.y = fig_scale2;
            final Point point2 = new Point(0, 0);
            point2.x = fig_scale + fig_scale3;
            point2.y = fig_scale2 + fig_scale4;
            final FigEllipse figEllipse = new FigEllipse(point, point2, figAttribs, figTrafo2D);
            list.insert(figEllipse);
            this.check_add_object_comment(figEllipse);
        }
        catch (NumberFormatException ex) {
            this.message("-E- Not a valid FIG3.1 file (ellipse)");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_spline(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        double[] array = null;
        final boolean boolean1 = SetupManager.getBoolean("jfig.enableXSplines", true);
        if (this.debug) {
            this.message("parse_fig_spline()...");
        }
        this.statusMessage("parsing spline (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken());
            int int5;
            if (this.fig_version >= 30) {
                int5 = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                int5 = int4;
            }
            final int int6 = Integer.parseInt(stringTokenizer.nextToken());
            final int int7 = Integer.parseInt(stringTokenizer.nextToken());
            final int int8 = Integer.parseInt(stringTokenizer.nextToken());
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            final int int9 = Integer.parseInt(stringTokenizer.nextToken());
            final int int10 = Integer.parseInt(stringTokenizer.nextToken());
            final int int11 = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            final FigArrowParams figArrowParams = new FigArrowParams();
            final FigArrowParams figArrowParams2 = new FigArrowParams();
            if (int10 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams);
            }
            if (int11 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams2);
            }
            int[] gl_xp;
            int[] gl_yp;
            if (n > 0) {
                gl_xp = new int[n];
                gl_yp = new int[n];
                this.parse_fig_pointline(bufferedReader, n, gl_xp, gl_yp);
            }
            else {
                n = this.parse_fig21_pointline(bufferedReader);
                gl_xp = this.gl_xp;
                gl_yp = this.gl_yp;
            }
            if (figAttribs.figVersion == 31) {
                if (int1 == 2 || int1 == 3) {
                    this.parse_fig31_cpointline(bufferedReader, 2 * n, new double[2 * n], new double[2 * n]);
                }
            }
            else if (figAttribs.figVersion == 32) {
                array = new double[n];
                this.parse_fig32_cpointline(bufferedReader, n, array);
            }
            figAttribs.lineWidth = this.fig_thickness(int3);
            figAttribs.lineColor = this.fig_color(int4);
            figAttribs.fillColor = this.fig_color(int5);
            figAttribs.fig_line_color = int4;
            figAttribs.fig_fill_color = int5;
            figAttribs.currentLayer = int6;
            figAttribs.fig_pen_style = int7;
            figAttribs.fig_area_fill = int8;
            figAttribs.fig_style_val = doubleValue;
            figAttribs.fig_cap_style = int9;
            this.handleLineStyle(figAttribs, int2, doubleValue);
            this.handleFillStyle(int5, figAttribs);
            figAttribs.arrowMode = 0;
            if (int10 == 1) {
                this.handle_forward_arrow(figAttribs, figArrowParams);
            }
            if (int11 == 1) {
                this.handle_backward_arrow(figAttribs, figArrowParams2);
            }
            switch (int1) {
                case 4: {
                    final Point[] array2 = new Point[n];
                    for (int i = 0; i < n; ++i) {
                        array2[i] = new Point(gl_xp[i], gl_yp[i]);
                    }
                    FigPolyline figPolyline;
                    if (boolean1) {
                        figPolyline = new FigGeneralXSpline(gl_xp[0], gl_yp[0], figAttribs, figTrafo2D);
                        figPolyline.setPoints(array2);
                        ((FigXSpline)figPolyline).setSFactors(array);
                    }
                    else {
                        this.message("-W- FIG 3.2 X-splines disabled, using approximated spline instead!");
                        figPolyline = new FigSpline(gl_xp[0], gl_yp[0], false, figAttribs, figTrafo2D);
                        figPolyline.setPoints(array2);
                    }
                    list.insert(figPolyline);
                    this.check_add_object_comment(figPolyline);
                    break;
                }
                case 0: {
                    FigPolyline figPolyline2;
                    if (boolean1) {
                        figPolyline2 = new FigNormalXSpline(gl_xp[0], gl_yp[0], figAttribs, figTrafo2D);
                    }
                    else {
                        figPolyline2 = new FigSpline(gl_xp[0], gl_yp[0], false, figAttribs, figTrafo2D);
                    }
                    final Point[] points = new Point[n];
                    for (int j = 0; j < n; ++j) {
                        points[j] = new Point(gl_xp[j], gl_yp[j]);
                    }
                    figPolyline2.setPoints(points);
                    list.insert(figPolyline2);
                    this.check_add_object_comment(figPolyline2);
                    break;
                }
                case 5: {
                    Point[] array3;
                    if (figAttribs.figVersion == 32) {
                        array3 = new Point[n];
                        for (int k = 0; k < n; ++k) {
                            array3[k] = new Point(gl_xp[k], gl_yp[k]);
                        }
                    }
                    else {
                        array3 = new Point[n - 1];
                        for (int l = 0; l < n - 1; ++l) {
                            array3[l] = new Point(gl_xp[l], gl_yp[l]);
                        }
                    }
                    FigPolyline figPolyline3;
                    if (boolean1) {
                        figPolyline3 = new FigGeneralXSpline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                        figPolyline3.setPoints(array3);
                        ((FigXSpline)figPolyline3).setSFactors(array);
                    }
                    else {
                        this.message("-W- FIG 3.2 X-splines disabled, using approximated spline instead!");
                        figPolyline3 = new FigSpline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                        figPolyline3.setPoints(array3);
                    }
                    list.insert(figPolyline3);
                    this.check_add_object_comment(figPolyline3);
                    break;
                }
                case 1: {
                    FigPolyline figPolyline4;
                    if (boolean1) {
                        figPolyline4 = new FigNormalXSpline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                    }
                    else {
                        figPolyline4 = new FigSpline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                    }
                    Point[] points2;
                    if (figAttribs.figVersion == 32) {
                        points2 = new Point[n];
                        for (int n2 = 0; n2 < n; ++n2) {
                            points2[n2] = new Point(gl_xp[n2], gl_yp[n2]);
                        }
                    }
                    else {
                        points2 = new Point[n - 1];
                        for (int n3 = 0; n3 < n - 1; ++n3) {
                            points2[n3] = new Point(gl_xp[n3], gl_yp[n3]);
                        }
                    }
                    figPolyline4.setPoints(points2);
                    list.insert(figPolyline4);
                    this.check_add_object_comment(figPolyline4);
                    break;
                }
                case 2: {
                    FigPolyline figPolyline5;
                    if (boolean1) {
                        figPolyline5 = new FigBezierXSpline(gl_xp[0], gl_yp[0], false, figAttribs, figTrafo2D);
                    }
                    else {
                        figPolyline5 = new FigBezier(gl_xp[0], gl_yp[0], false, figAttribs, figTrafo2D);
                    }
                    final Point[] points3 = new Point[n];
                    for (int n4 = 0; n4 < n; ++n4) {
                        points3[n4] = new Point(gl_xp[n4], gl_yp[n4]);
                    }
                    figPolyline5.setPoints(points3);
                    list.insert(figPolyline5);
                    this.check_add_object_comment(figPolyline5);
                    break;
                }
                case 3: {
                    FigPolyline figPolyline6;
                    if (boolean1) {
                        figPolyline6 = new FigBezierXSpline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                    }
                    else {
                        figPolyline6 = new FigBezier(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                    }
                    Point[] points4;
                    if (figAttribs.figVersion == 32) {
                        points4 = new Point[n];
                        for (int n5 = 0; n5 < n; ++n5) {
                            points4[n5] = new Point(gl_xp[n5], gl_yp[n5]);
                        }
                    }
                    else {
                        points4 = new Point[n - 1];
                        for (int n6 = 0; n6 < n - 1; ++n6) {
                            points4[n6] = new Point(gl_xp[n6], gl_yp[n6]);
                        }
                    }
                    figPolyline6.setPoints(points4);
                    list.insert(figPolyline6);
                    this.check_add_object_comment(figPolyline6);
                    break;
                }
                default: {
                    this.message("parse_fig_spline: Internal error (unknown sub_type).");
                    break;
                }
            }
        }
        catch (Exception ex) {
            this.message("-E- Not a valid FIG file (spline, number format)");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
            ex.printStackTrace();
        }
    }
    
    String buildString(final String s) {
        String s2 = "";
        try {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 != '\u0013') {
                    if (char1 < ' ' || char1 > '\u007f' || !this.enableNonASCII) {
                        this.message("-W- Parser found non ASCII char '" + char1 + "' in input: " + s);
                    }
                    if (char1 == '\\') {
                        ++i;
                        final char char2 = s.charAt(i);
                        if (char2 == '\\') {
                            s2 += "\\";
                        }
                        else if (char2 == 'n') {
                            s2 += "\n";
                        }
                        else if (char2 <= '7' && char2 >= '0') {
                            final String substring = s.substring(i, i + 3);
                            i += 2;
                            final char c = (char)Integer.parseInt(substring, 8);
                            if (this.debug) {
                                this.message("buildString(): octal escape: '" + substring + "' result char is '" + c + "' (value)" + (int)c);
                            }
                            s2 += c;
                        }
                        else if (char2 == 'u') {
                            final String substring2 = s.substring(i + 1, i + 5);
                            i += 4;
                            s2 += (char)Integer.parseInt(substring2, 16);
                        }
                        else {
                            s2 = s2 + "\\" + char2;
                        }
                    }
                    else {
                        s2 += char1;
                    }
                }
            }
            return s2;
        }
        catch (Exception ex) {
            this.message("FIG 3.1 parser internal error in buildString():  while trying to convert input '" + s + "' dest='" + s2 + "' ");
            ++this.n_errors;
            return s2;
        }
    }
    
    private void printString(final String s) {
        System.out.println("printString: " + s);
        for (int i = 0; i < s.length(); ++i) {
            System.out.print("" + (int)s.charAt(i) + " ");
        }
        System.out.println();
    }
    
    void parse_fig_text(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        int n = 0;
        int fig_line_color = 0;
        int currentLayer = 5;
        int int1 = 0;
        int fig_font = 0;
        double n2 = 0.0;
        double fig_angle = 0.0;
        int fig_font_flags = 0;
        double doubleValue = 0.0;
        double doubleValue2 = 0.0;
        int n3 = 0;
        int n4 = 0;
        if (this.debug) {
            this.message("parse_fig_text()...");
        }
        this.statusMessage("parsing text (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            if (this.fig_version >= 30) {
                Integer.parseInt(stringTokenizer.nextToken());
                n = Integer.parseInt(stringTokenizer.nextToken());
                fig_line_color = Integer.parseInt(stringTokenizer.nextToken());
                currentLayer = Integer.parseInt(stringTokenizer.nextToken());
                int1 = Integer.parseInt(stringTokenizer.nextToken());
                fig_font = Integer.parseInt(stringTokenizer.nextToken());
                n2 = Double.valueOf(stringTokenizer.nextToken());
                fig_angle = Double.valueOf(stringTokenizer.nextToken());
                fig_font_flags = Integer.parseInt(stringTokenizer.nextToken());
                doubleValue = Double.valueOf(stringTokenizer.nextToken());
                doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
                n3 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
                n4 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            }
            else if (this.fig_version == 21) {
                Integer.parseInt(stringTokenizer.nextToken());
                n = Integer.parseInt(stringTokenizer.nextToken());
                fig_font = Integer.parseInt(stringTokenizer.nextToken());
                n2 = Double.valueOf(stringTokenizer.nextToken());
                Integer.parseInt(stringTokenizer.nextToken());
                fig_line_color = Integer.parseInt(stringTokenizer.nextToken());
                currentLayer = Integer.parseInt(stringTokenizer.nextToken());
                fig_angle = Double.valueOf(stringTokenizer.nextToken());
                fig_font_flags = Integer.parseInt(stringTokenizer.nextToken());
                doubleValue = Integer.parseInt(stringTokenizer.nextToken());
                doubleValue2 = Integer.parseInt(stringTokenizer.nextToken());
                n3 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
                n4 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            }
            figAttribs.lineColor = this.fig_color(fig_line_color);
            figAttribs.fig_line_color = fig_line_color;
            figAttribs.currentLayer = currentLayer;
            figAttribs.fig_pen_style = int1;
            figAttribs.fig_font = fig_font;
            figAttribs.fig_font_flags = fig_font_flags;
            figAttribs.fig_angle = fig_angle;
            figAttribs.fig_text_height = doubleValue;
            figAttribs.fig_text_width = doubleValue2;
            final String nextToken = stringTokenizer.nextToken("\n");
            String s2;
            if (this.fig_version >= 30) {
                int index = s.indexOf(nextToken);
                if (index < 0) {
                    index = 0;
                }
                int index2 = s.indexOf("\\001");
                if (index2 < 0) {
                    index2 = s.length() - 4;
                }
                s2 = this.buildString(s.substring(index + 1, index2));
            }
            else {
                s2 = this.buildString(s.substring(s.indexOf(nextToken) + 1, s.length() - 1));
            }
            if (this.debug) {
                this.message("...text: token='" + nextToken + "' string='" + s2 + "' ");
            }
            if (n == 0) {
                figAttribs.textAlign = 1;
            }
            else if (n == 1) {
                figAttribs.textAlign = 2;
            }
            else {
                figAttribs.textAlign = 3;
            }
            figAttribs.fontSize = (int)n2;
            if ((fig_font_flags & 0x4) == 0x0 && !FigAttribs.enableJava2D) {
                this.message("-I- Computer Modern fonts not supported, using Postscript/TrueType font instead.");
            }
            final FigText figText = new FigText(new Point(n3, n4), s2, figAttribs, figTrafo2D);
            list.insert(figText);
            this.check_add_object_comment(figText);
            if (fig_angle != 0.0 && !FigAttribs.enableJava2D) {
                if (this.n_errors < 30) {
                    this.message("-W- cannot display text rotation " + fig_angle + ", " + "for string '" + s2 + "'");
                }
                else if (this.n_errors == 30) {
                    this.message("-W- too many errors, suppressing future messages!");
                }
                else if (this.n_errors > 30) {}
                ++this.n_errors;
            }
        }
        catch (NumberFormatException ex) {
            this.message("Error (text, number format): Not a valid FIG3.1 file");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_arc(final BufferedReader bufferedReader, final String s, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        if (this.debug) {
            this.message("parse_fig_arc()...");
        }
        this.statusMessage("parsing arc (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            Integer.parseInt(stringTokenizer.nextToken());
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            int int4;
            if (this.fig_version >= 30) {
                int4 = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                int4 = int3;
            }
            final int int5 = Integer.parseInt(stringTokenizer.nextToken());
            final int int6 = Integer.parseInt(stringTokenizer.nextToken());
            final int int7 = Integer.parseInt(stringTokenizer.nextToken());
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            if (this.fig_version >= 30) {
                Integer.parseInt(stringTokenizer.nextToken());
            }
            Integer.parseInt(stringTokenizer.nextToken());
            final int int8 = Integer.parseInt(stringTokenizer.nextToken());
            final int int9 = Integer.parseInt(stringTokenizer.nextToken());
            Double.valueOf(stringTokenizer.nextToken());
            Double.valueOf(stringTokenizer.nextToken());
            final int fig_scale = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale2 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale3 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale4 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale5 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final int fig_scale6 = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            final FigArrowParams figArrowParams = new FigArrowParams();
            final FigArrowParams figArrowParams2 = new FigArrowParams();
            if (int8 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams);
            }
            if (int9 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams2);
            }
            figAttribs.lineWidth = this.fig_thickness(int2);
            figAttribs.lineColor = this.fig_color(int3);
            figAttribs.fillColor = this.fig_color(int4);
            figAttribs.fig_line_color = int3;
            figAttribs.fig_fill_color = int4;
            figAttribs.currentLayer = int5;
            figAttribs.fig_pen_style = int6;
            figAttribs.fig_area_fill = int7;
            this.handleLineStyle(figAttribs, int1, figAttribs.fig_style_val = doubleValue);
            this.handleFillStyle(int4, figAttribs);
            figAttribs.arrowMode = 0;
            if (int8 == 1) {
                this.handle_forward_arrow(figAttribs, figArrowParams);
            }
            if (int9 == 1) {
                this.handle_backward_arrow(figAttribs, figArrowParams2);
            }
            final FigArc figArc = new FigArc(new Point(fig_scale, fig_scale2), new Point(fig_scale3, fig_scale4), new Point(fig_scale5, fig_scale6), figAttribs, figTrafo2D);
            list.insert((FigObject)figArc);
            this.check_add_object_comment((FigObject)figArc);
        }
        catch (NumberFormatException ex) {
            this.message("-E- Not a valid FIG3.1 file (arc)");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_pointline(final BufferedReader bufferedReader, final int n, final int[] array, final int[] array2) {
        if (this.debug) {
            this.message("parse_fig_pointline: npoints= " + n);
        }
        String line = null;
        int i = 0;
        try {
            while (i < n) {
                line = bufferedReader.readLine();
                final StringTokenizer stringTokenizer = new StringTokenizer(line, " \n\r\t");
                for (int countTokens = stringTokenizer.countTokens(), j = 0; j < countTokens; j += 2) {
                    array[i] = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
                    array2[i] = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
                    ++i;
                }
                ++this.line_number;
            }
        }
        catch (Exception ex) {
            this.message("-E- Not a valid FIG file (pointline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
            ex.printStackTrace();
        }
    }
    
    int parse_fig21_pointline(final BufferedReader bufferedReader) {
        if (this.debug) {
            this.message("parse_fig21_pointline...");
        }
        String line = null;
        int n = 0;
        try {
            line = bufferedReader.readLine();
            if (this.debug) {
                this.message("parse_fig_pointline: " + line);
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(line, " \n\r\t");
            n = (stringTokenizer.countTokens() - 2) / 2;
            this.gl_xp = new int[n];
            this.gl_yp = new int[n];
            for (int i = 0; i < n; ++i) {
                this.gl_xp[i] = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
                this.gl_yp[i] = this.fig_scale(Integer.parseInt(stringTokenizer.nextToken()));
            }
            ++this.line_number;
        }
        catch (Exception ex) {
            this.message("-E- Not a valid FIG3.1 file (pointline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
        return n;
    }
    
    void parse_fig32_cpointline(final BufferedReader bufferedReader, final int n, final double[] array) {
        if (this.debug) {
            this.message("parse_fig32_cpointline: npoints= " + n);
        }
        String line = null;
        int i = 0;
        try {
            while (i < n) {
                line = bufferedReader.readLine();
                if (this.debug) {
                    this.message("parse_fig32_cpointline: " + line);
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(line, " \n\r\t");
                for (int countTokens = stringTokenizer.countTokens(), j = 0; j < countTokens; ++j) {
                    array[i] = Double.valueOf(stringTokenizer.nextToken());
                    ++i;
                }
                ++this.line_number;
            }
        }
        catch (Exception ex) {
            this.message("-E- Not a valid FIG3.2 file (cpointline) ");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig31_cpointline(final BufferedReader bufferedReader, final int n, final double[] array, final double[] array2) {
        if (this.debug) {
            this.message("parse_fig31_cpointline: npoints= " + n);
        }
        String line = null;
        int i = 0;
        try {
            while (i < n) {
                line = bufferedReader.readLine();
                if (this.debug) {
                    this.message("parse_fig31_cpointline: " + line);
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(line, " \n\r\t");
                for (int countTokens = stringTokenizer.countTokens(), j = 0; j < countTokens; j += 2) {
                    final Double value = Double.valueOf(stringTokenizer.nextToken());
                    final Double value2 = Double.valueOf(stringTokenizer.nextToken());
                    array[i] = this.fig_scale(value);
                    array2[i] = this.fig_scale(value2);
                    ++i;
                    if (this.debug) {
                        this.message("xx " + value + ", yy " + value2);
                    }
                }
                ++this.line_number;
            }
        }
        catch (IOException ex) {
            this.message("-E- Not a valid FIG3.1 file (cpointline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
        catch (NumberFormatException ex2) {
            this.message("-E- Not a valid FIG3.1 file (number format, cpointline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
        catch (Exception ex3) {
            this.message("-E- Not a valid FIG3.1 file (number format, cpointline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_arrow(final BufferedReader bufferedReader, final FigArrowParams figArrowParams) {
        String line = null;
        try {
            line = bufferedReader.readLine();
            final StringTokenizer stringTokenizer = new StringTokenizer(line, " \n\t\r");
            figArrowParams.type = Integer.parseInt(stringTokenizer.nextToken());
            figArrowParams.style = Integer.parseInt(stringTokenizer.nextToken());
            figArrowParams.thickness = this.fig_arrow_scale(Double.valueOf(stringTokenizer.nextToken()));
            figArrowParams.width = this.fig_arrow_scale(Double.valueOf(stringTokenizer.nextToken()));
            figArrowParams.height = this.fig_arrow_scale(Double.valueOf(stringTokenizer.nextToken()));
            ++this.line_number;
        }
        catch (Exception ex) {
            this.message("-E- Not a valid FIG3.1 file (arrow)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
    }
    
    void parse_fig_polyline(final BufferedReader bufferedReader, String line, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        String trim = null;
        if (this.debug) {
            this.message("parse_fig_polyline()...");
        }
        this.statusMessage("parsing polyline (" + this.line_number + ")");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(line, "\n\r\t ");
            Integer.parseInt(stringTokenizer.nextToken());
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken());
            int int5;
            if (this.fig_version >= 30) {
                int5 = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                int5 = int4;
            }
            final int int6 = Integer.parseInt(stringTokenizer.nextToken());
            final int int7 = Integer.parseInt(stringTokenizer.nextToken());
            final int int8 = Integer.parseInt(stringTokenizer.nextToken());
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            int int9;
            int int10;
            if (this.fig_version >= 30) {
                int9 = Integer.parseInt(stringTokenizer.nextToken());
                int10 = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                int9 = 0;
                int10 = 0;
            }
            final int int11 = Integer.parseInt(stringTokenizer.nextToken());
            final int int12 = Integer.parseInt(stringTokenizer.nextToken());
            final int int13 = Integer.parseInt(stringTokenizer.nextToken());
            int n;
            if (this.fig_version >= 30) {
                n = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                n = -1;
            }
            final FigArrowParams figArrowParams = new FigArrowParams();
            final FigArrowParams figArrowParams2 = new FigArrowParams();
            if (int12 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams);
            }
            if (int13 == 1) {
                this.parse_fig_arrow(bufferedReader, figArrowParams2);
            }
            if (int1 == 5) {
                line = bufferedReader.readLine();
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line, " \n\r\t");
                stringTokenizer2.nextToken();
                trim = line.substring(line.indexOf(stringTokenizer2.nextToken())).trim();
                if (this.debug) {
                    this.message("Image source is '" + trim + "' ");
                }
            }
            int[] gl_xp;
            int[] gl_yp;
            if (n > 0) {
                gl_xp = new int[n];
                gl_yp = new int[n];
                this.parse_fig_pointline(bufferedReader, n, gl_xp, gl_yp);
            }
            else {
                n = this.parse_fig21_pointline(bufferedReader);
                gl_xp = this.gl_xp;
                gl_yp = this.gl_yp;
            }
            figAttribs.lineWidth = this.fig_thickness(int3);
            figAttribs.lineColor = this.fig_color(int4);
            figAttribs.fillColor = this.fig_color(int5);
            figAttribs.fig_line_color = int4;
            figAttribs.fig_fill_color = int5;
            figAttribs.currentLayer = int6;
            figAttribs.fig_pen_style = int7;
            figAttribs.fig_area_fill = int8;
            figAttribs.fig_style_val = doubleValue;
            figAttribs.fig_join_style = int9;
            figAttribs.fig_cap_style = int10;
            figAttribs.cornerRadius = this.fig_thickness(int11);
            this.handleLineStyle(figAttribs, int2, doubleValue);
            this.handleFillStyle(int5, figAttribs);
            figAttribs.arrowMode = 0;
            if (int12 == 1) {
                this.handle_forward_arrow(figAttribs, figArrowParams);
            }
            if (int13 == 1) {
                this.handle_backward_arrow(figAttribs, figArrowParams2);
            }
            switch (int1) {
                case 1: {
                    final FigPolyline figPolyline = new FigPolyline(gl_xp[0], gl_yp[0], false, figAttribs, figTrafo2D);
                    final Point[] points = new Point[n];
                    for (int i = 0; i < n; ++i) {
                        points[i] = new Point(gl_xp[i], gl_yp[i]);
                    }
                    figPolyline.setPoints(points);
                    list.insert(figPolyline);
                    this.check_add_object_comment(figPolyline);
                    break;
                }
                case 2: {
                    figAttribs.cornerRadius = 0;
                    int n2 = Integer.MAX_VALUE;
                    int n3 = Integer.MAX_VALUE;
                    int n4 = Integer.MIN_VALUE;
                    int n5 = Integer.MIN_VALUE;
                    for (int j = 0; j < n; ++j) {
                        if (gl_xp[j] < n2) {
                            n2 = gl_xp[j];
                        }
                        if (gl_yp[j] < n3) {
                            n3 = gl_yp[j];
                        }
                        if (gl_xp[j] > n4) {
                            n4 = gl_xp[j];
                        }
                        if (gl_yp[j] > n5) {
                            n5 = gl_yp[j];
                        }
                    }
                    final FigRectangle figRectangle = new FigRectangle(n2, n3, n4, n5, false, figAttribs, figTrafo2D);
                    list.insert(figRectangle);
                    this.check_add_object_comment(figRectangle);
                    break;
                }
                case 3: {
                    final FigPolyline figPolyline2 = new FigPolyline(gl_xp[0], gl_yp[0], true, figAttribs, figTrafo2D);
                    final Point[] points2 = new Point[n - 1];
                    for (int k = 0; k < n - 1; ++k) {
                        points2[k] = new Point(gl_xp[k], gl_yp[k]);
                    }
                    figPolyline2.setPoints(points2);
                    list.insert(figPolyline2);
                    this.check_add_object_comment(figPolyline2);
                    break;
                }
                case 4: {
                    int n6 = Integer.MAX_VALUE;
                    int n7 = Integer.MAX_VALUE;
                    int n8 = Integer.MIN_VALUE;
                    int n9 = Integer.MIN_VALUE;
                    for (int l = 0; l < n; ++l) {
                        if (gl_xp[l] < n6) {
                            n6 = gl_xp[l];
                        }
                        if (gl_yp[l] < n7) {
                            n7 = gl_yp[l];
                        }
                        if (gl_xp[l] > n8) {
                            n8 = gl_xp[l];
                        }
                        if (gl_yp[l] > n9) {
                            n9 = gl_yp[l];
                        }
                    }
                    final FigRectangle figRectangle2 = new FigRectangle(n6, n7, n8, n9, true, figAttribs, figTrafo2D);
                    list.insert(figRectangle2);
                    this.check_add_object_comment(figRectangle2);
                    break;
                }
                case 5: {
                    int n10 = Integer.MAX_VALUE;
                    int n11 = Integer.MAX_VALUE;
                    int n12 = Integer.MIN_VALUE;
                    int n13 = Integer.MIN_VALUE;
                    for (int n14 = 0; n14 < n; ++n14) {
                        if (gl_xp[n14] < n10) {
                            n10 = gl_xp[n14];
                        }
                        if (gl_yp[n14] < n11) {
                            n11 = gl_yp[n14];
                        }
                        if (gl_xp[n14] > n12) {
                            n12 = gl_xp[n14];
                        }
                        if (gl_yp[n14] > n13) {
                            n13 = gl_yp[n14];
                        }
                    }
                    final FigImage figImage = new FigImage(n10, n11, n12, n13, figAttribs, figTrafo2D, (String)null);
                    figImage.setImageAndFilename(trim, this.gl_filename, this.gl_filetype, true);
                    if (this.painter != null) {
                        figImage.setObjectPainter(this.painter);
                    }
                    list.insert((FigObject)figImage);
                    this.check_add_object_comment((FigObject)figImage);
                    break;
                }
                default: {
                    this.message("Illegal polyline sub_type: " + int1);
                    throw new IOException();
                }
            }
        }
        catch (NumberFormatException ex) {
            this.message("-E- Not a valid FIG3.1 file (polyline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
        catch (Exception ex2) {
            this.message("-E- Not a valid FIG3.1 file (polyline)");
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            ++this.n_errors;
        }
    }
    
    void parse_text_plain(final String s, final BufferedReader bufferedReader, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        this.message("reading input as plain/text file...");
        try {
            final FigAttribs figAttribs2 = new FigAttribs();
            figAttribs2.fig_font = 12;
            figAttribs2.fontSize = 14;
            final int n = 480;
            final int n2 = 1200;
            int n3 = n;
            if (s != null) {
                list.append(new FigText(new Point(n2, n3), s, figAttribs2, figTrafo2D));
                n3 += n;
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.append(new FigText(new Point(n2, n3), line, figAttribs2, figTrafo2D));
                n3 += 480;
            }
        }
        catch (Exception ex) {
            this.message("parse_text_plain.: " + ex.toString());
        }
    }
    
    String skip_fig_comments(final BufferedReader bufferedReader) throws IOException {
        Label_0052: {
            break Label_0052;
            String line = null;
            do {
                if (line.length() > 0 && line.charAt(0) != '#') {
                    return line;
                }
                line = bufferedReader.readLine();
                ++this.line_number;
            } while (line != null);
        }
        throw new IOException();
    }
    
    void parse_fig32_header(final BufferedReader bufferedReader, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        String s = null;
        try {
            s = this.skip_fig_comments(bufferedReader);
            if (s.indexOf("Landscape") >= 0) {
                figAttribs.setFigOrientation(0);
                figAttribs.setPageOrientation("Landscape");
            }
            else {
                if (s.indexOf("Portrait") < 0) {
                    throw new IOException("Wrong orientation: " + s);
                }
                figAttribs.setFigOrientation(1);
                figAttribs.setPageOrientation("Portrait");
            }
            s = this.skip_fig_comments(bufferedReader);
            if (s.indexOf("Center") >= 0) {
                figAttribs.setFigJustification(0);
                figAttribs.setPageJustification("Center");
            }
            else {
                if (s.toLowerCase().indexOf("flush left") < 0) {
                    throw new IOException("Wrong alignment: " + s);
                }
                figAttribs.setFigJustification(1);
                figAttribs.setPageJustification("Flush Left");
            }
            s = this.skip_fig_comments(bufferedReader);
            if (s.indexOf("Metric") >= 0) {
                figAttribs.setFigUnits(0);
                figAttribs.setPageUnits("Metric");
            }
            else {
                if (s.indexOf("Inches") < 0) {
                    throw new IOException("Wrong units: " + s);
                }
                figAttribs.setFigUnits(1);
                figAttribs.setPageUnits("Inches");
            }
            s = this.skip_fig_comments(bufferedReader);
            boolean b = false;
            for (int i = 0; i < this.paperSizes.length; ++i) {
                if (s.indexOf(this.paperSizes[i]) >= 0) {
                    b = true;
                    figAttribs.setPaperSize(this.paperSizes[i]);
                }
            }
            if (!b) {
                throw new IOException("Wrong paper size: " + s);
            }
            s = this.skip_fig_comments(bufferedReader);
            figAttribs.setExportMagnification(Double.valueOf(s));
            s = this.skip_fig_comments(bufferedReader);
            if (s.indexOf("Single") >= 0) {
                figAttribs.setSingleOrMultipleSheets("Single");
            }
            else {
                if (s.indexOf("Multiple") < 0) {
                    throw new IOException("Must use either Single or Multiple: " + s);
                }
                figAttribs.setSingleOrMultipleSheets("Multiple");
            }
            s = this.skip_fig_comments(bufferedReader);
            figAttribs.setTransparentColorIndex(Integer.parseInt(s));
            s = this.skip_fig_comments(bufferedReader);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\t\r ");
            figAttribs.setFigResolution(Integer.parseInt(stringTokenizer.nextToken()));
            this.input_resolution = (int)(2400.0 / FigAttribs.fig_resolution);
            figAttribs.setFigOrigin(Integer.parseInt(stringTokenizer.nextToken()));
            if (figAttribs.getFigOrigin() != 2) {
                throw new IOException();
            }
        }
        catch (Exception ex) {
            this.message("-W- Not a valid FIG 3.2 file (header)" + ex);
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
            this.parse_text_plain(s, bufferedReader, figAttribs, figTrafo2D, list);
        }
    }
    
    void parse_fig_header(final BufferedReader bufferedReader, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        String s = null;
        this.statusMessage("parsing header...");
        try {
            s = bufferedReader.readLine();
            ++this.line_number;
            if (s.indexOf("#FIG 3.2") > -1) {
                figAttribs.setFigVersion(this.fig_version = 32);
                this.parse_fig32_header(bufferedReader, figAttribs, figTrafo2D, list);
            }
            else if (s.indexOf("#FIG 3.1") > -1) {
                figAttribs.setFigVersion(this.fig_version = 31);
                s = this.skip_fig_comments(bufferedReader);
                if (s.indexOf("Landscape") >= 0) {
                    figAttribs.setFigOrientation(0);
                    figAttribs.setPageOrientation("Landscape");
                }
                else {
                    if (s.indexOf("Portrait") < 0) {
                        throw new IOException();
                    }
                    figAttribs.setFigOrientation(1);
                    figAttribs.setPageOrientation("Portrait");
                }
                s = this.skip_fig_comments(bufferedReader);
                if (s.indexOf("Center") >= 0) {
                    figAttribs.setFigJustification(0);
                    figAttribs.setPageJustification("Center");
                }
                else {
                    if (s.toLowerCase().indexOf("flush left") < 0) {
                        throw new IOException();
                    }
                    figAttribs.setFigJustification(1);
                    figAttribs.setPageJustification("Flush Left");
                }
                s = this.skip_fig_comments(bufferedReader);
                if (s.indexOf("Metric") >= 0) {
                    figAttribs.setFigUnits(0);
                    figAttribs.setPageUnits("Metric");
                }
                else {
                    if (s.indexOf("Inches") < 0) {
                        throw new IOException();
                    }
                    figAttribs.setFigUnits(1);
                    figAttribs.setPageUnits("Inches");
                }
                s = this.skip_fig_comments(bufferedReader);
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\t\r ");
                figAttribs.setFigResolution(Integer.parseInt(stringTokenizer.nextToken()));
                this.input_resolution = (int)(2400.0 / figAttribs.getFigResolution());
                figAttribs.setFigOrigin(Integer.parseInt(stringTokenizer.nextToken()));
                if (figAttribs.getFigOrigin() != 2) {
                    throw new IOException();
                }
            }
            else {
                if (s.indexOf("FIG 2.1") <= -1) {
                    throw new IOException();
                }
                this.fig_version = 21;
                s = this.skip_fig_comments(bufferedReader);
                figAttribs.setFigJustification(1);
                figAttribs.setFigUnits(1);
                figAttribs.setFigOrigin(2);
                figAttribs.setFigResolution(80);
                this.input_resolution = (int)(2400.0 / figAttribs.getFigResolution());
            }
            if (this.debug) {
                this.message("FIG 3.1 header variables: ");
                this.message(" units " + figAttribs.getFigUnits() + " justification " + figAttribs.getFigJustification() + " resolution " + figAttribs.getFigResolution() + " origin " + figAttribs.getFigOrigin());
            }
        }
        catch (IOException ex) {
            this.message("-E- Not a valid FIG3.1 file (header)");
            if (s != null) {
                this.message("on line " + this.line_number + ": " + s);
            }
            ++this.n_errors;
            this.parse_text_plain(s, bufferedReader, figAttribs, figTrafo2D, list);
        }
    }
    
    public int parse_fig_file(final InputStream inputStream, final boolean b, final boolean b2, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        return this.parse_fig_file_private(new BufferedReader(new InputStreamReader(inputStream)), b, b2, figAttribs, figTrafo2D, list);
    }
    
    private int parse_fig_file_private(final BufferedReader bufferedReader, final boolean b, boolean b2, final FigAttribs figAttribs, final FigTrafo2D trafo, final FigObjectList list) {
        String line = null;
        if (b) {
            this.statusMessage("parsing file...");
        }
        else {
            this.statusMessage("parsing compound... " + this.line_number);
        }
        if (b) {
            this.n_messages = 0;
        }
        System.currentTimeMillis();
        if (bufferedReader == null) {
            this.statusMessage("file not found or empty!");
            return 1;
        }
        try {
            if (b) {
                this.n_errors = 0;
                this.line_number = 0;
                this.parse_fig_header(bufferedReader, figAttribs, trafo, list);
            }
            while ((line = bufferedReader.readLine()) != null) {
                if (this.debug) {
                    this.message("..." + line);
                }
                if (line.length() < 1) {
                    continue;
                }
                final char char1 = line.charAt(0);
                if (char1 == '#') {
                    this.collect_object_comment(line);
                }
                else if (char1 == '0') {
                    this.parse_fig_pseudocolor(bufferedReader, line, figAttribs, list);
                }
                else if (char1 == '1') {
                    this.parse_fig_ellipse(bufferedReader, line, figAttribs, trafo, list);
                }
                else if (char1 == '2') {
                    this.parse_fig_polyline(bufferedReader, line, figAttribs, trafo, list);
                }
                else if (char1 == '3') {
                    this.parse_fig_spline(bufferedReader, line, figAttribs, trafo, list);
                }
                else if (char1 == '4') {
                    this.parse_fig_text(bufferedReader, line, figAttribs, trafo, list);
                }
                else if (char1 == '5') {
                    this.parse_fig_arc(bufferedReader, line, figAttribs, trafo, list);
                }
                else if (char1 == '6') {
                    b2 = true;
                    final FigCompound figCompound = new FigCompound();
                    this.check_add_object_comment(figCompound);
                    figCompound.setTrafo(trafo);
                    this.parse_fig_file_private(bufferedReader, false, b2, figAttribs, trafo, figCompound.getMembers());
                    figCompound.update_bbox();
                    list.insert(figCompound);
                }
                else if (char1 == '-') {
                    if (b2 && line.charAt(1) == '6') {
                        return this.n_errors;
                    }
                    this.message("-E- Not a valid FIG3.1 file, a compound may not be closed here: ");
                    if (line != null) {
                        this.message(line);
                    }
                }
                else {
                    if (char1 != '9') {
                        throw new IOException();
                    }
                    this.parse_jfig_trigger(bufferedReader, line, figAttribs, trafo, list);
                }
                ++this.line_number;
            }
            figAttribs.restoreDefaults();
            if (this.debug) {
                this.message("Parsing FIG3.1 file ok, " + this.n_errors + " possible error(s) on " + this.line_number + " lines.");
            }
            return this.n_errors;
        }
        catch (IOException ex) {
            this.message("-E- Not a valid FIG3.1 file (main parser)" + ex);
            if (line != null) {
                this.message("on line " + this.line_number + ": " + line);
            }
            return ++this.n_errors;
        }
    }
    
    public int parse_fig_file_not_threaded(final InputStream inputStream, final boolean b, final boolean b2, final boolean b3, final FigAttribs figAttribs, final FigTrafo2D figTrafo2D, final FigObjectList list) {
        this.statusMessage("parsing...");
        final int parse_fig_file = this.parse_fig_file(inputStream, b, b2, figAttribs, figTrafo2D, list);
        if (this.actionListeners.size() > 0) {
            this.notifyOurActionListeners(b3);
        }
        return parse_fig_file;
    }
    
    public void notifyOurActionListeners(final boolean b) {
        String s;
        if (b) {
            s = "ParserCallback:merge";
        }
        else {
            s = "ParserCallback:load";
        }
        final ActionEvent actionEvent = new ActionEvent(this, 1001, s);
        final Enumeration<ActionListener> elements = (Enumeration<ActionListener>)this.actionListeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().actionPerformed(actionEvent);
        }
    }
    
    public int parse_fig_file_threaded(final InputStream t_f, final boolean t_top_level, final boolean t_inside_compound, final boolean t_merge, final FigAttribs t_attribs, final FigTrafo2D t_trafo, final FigObjectList t_OL) {
        if (this.solaris_is_broken) {
            return this.parse_fig_file_not_threaded(t_f, t_top_level, t_inside_compound, t_merge, t_attribs, t_trafo, t_OL);
        }
        if (this.reader != null && this.reader.isAlive()) {
            return 12345;
        }
        if (t_top_level) {
            this.n_messages = 0;
        }
        this.t_errors = 0;
        this.t_f = t_f;
        this.t_top_level = t_top_level;
        this.t_inside_compound = t_inside_compound;
        this.t_merge = t_merge;
        this.t_attribs = t_attribs;
        this.t_trafo = t_trafo;
        this.t_OL = t_OL;
        final Thread thread = new Thread(this);
        thread.setPriority(1);
        thread.start();
        return 0;
    }
    
    public void run() {
        if (this.debug) {
            this.message("FigParser.run(): parsing started...");
        }
        this.t_errors = this.parse_fig_file(this.t_f, this.t_top_level, this.t_inside_compound, this.t_attribs, this.t_trafo, this.t_OL);
        if (this.debug) {
            this.message("FigParser.run(): parsing ok, " + this.t_errors + " errors");
        }
        if (this.actionListeners.size() > 0) {
            this.notifyOurActionListeners(this.t_merge);
        }
    }
    
    public void message(final String s) {
        if (this.printer != null) {
            this.printer.consoleMessage(s);
        }
        else {
            try {
                Console.getConsole().message(s);
            }
            catch (Throwable t) {
                System.err.println(s);
            }
        }
    }
    
    public void statusMessage(final String statusMessage) {
        ++this.n_messages;
        if (!this.enableMessages) {
            return;
        }
        if (this.helper != null) {
            if ((this.fastMessages && this.n_messages < 10) || this.n_messages % 50 == 0) {
                this.helper.setStatusMessage(statusMessage);
            }
        }
        else {
            this.message(statusMessage);
        }
    }
    
    public FigParser(final Object o) {
        this.fastMessages = false;
        this.fig_version = 31;
        this.helper = null;
        this.printer = null;
        this.painter = null;
        this.gl_filename = null;
        this.gl_filetype = null;
        this.comment = null;
        this.enableNonASCII = false;
        this.enableMessages = false;
        this.paperSizes = new String[] { "Letter", "Legal", "Ledger", "Tabloid", "A", "B", "C", "D", "E", "A4", "A3", "A2", "A1", "A0", "B5" };
        this.solaris_is_broken = false;
        this.t_errors = 0;
        this.t_top_level = false;
        this.t_inside_compound = false;
        this.t_merge = false;
        this.t_attribs = null;
        this.t_trafo = null;
        this.t_OL = null;
        this.reader = null;
        this.t_f = null;
        this.n_messages = 0;
        this.n_errors = 0;
        this.line_number = 0;
        this.actionListeners = new Vector();
        this.colorCache = ColorCache.getColorCache();
        this.input_resolution = 2;
        this.debug = SetupManager.getBoolean("jfig.objects.FigParser.debug", false);
        this.enableNonASCII = SetupManager.getBoolean("jfig.objects.FigParser.enableNonASCII", true);
        this.enableMessages = SetupManager.getBoolean("jfig.objects.FigParser.enableMessages", false);
        this.fastMessages = SetupManager.getBoolean("jfig.objects.FigParser.UseFastMessages", false);
    }
    
    public FigParser(final Object o, final StatusMessage helper) {
        this(o);
        this.helper = helper;
    }
    
    public FigParser() {
        this(null);
    }
}
