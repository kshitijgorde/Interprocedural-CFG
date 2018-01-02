// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Component;
import netcharts.util.NFUtil;
import java.util.Hashtable;
import netcharts.util.NFParamDef;
import netcharts.util.NFParam;
import java.awt.Color;
import java.util.Vector;

public class NFRadialAxis
{
    protected static String NAME_DEFAULT;
    protected String name;
    protected static boolean LABEL_ENABLED_DEFAULT;
    protected boolean labelEnabled;
    protected NFSpacing spacing;
    protected double scale;
    protected NFLabel label;
    protected NFLabel ticLabel;
    protected Vector ticActiveLabels;
    protected NFActiveRegion dwell;
    protected NFActiveLabel titleAL;
    protected Color axisColor;
    protected static final int MaxRadialAxes = 50;
    protected static double MINIMUM_DEFAULT;
    protected double minimum;
    protected static double MAXIMUM_DEFAULT;
    protected double maximum;
    protected static double STEP_DEFAULT;
    protected double step;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DECIMAL = 5;
    private static int a;
    private int b;
    private static String c;
    private String d;
    protected boolean showTics;
    protected boolean showLabel;
    protected boolean showTicLabels;
    protected boolean showLine;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected NFSpacing ticSpacing;
    private boolean e;
    private static boolean f;
    protected int groupSize;
    protected char groupSymbol;
    protected char decSymbol;
    
    public NFRadialAxis() {
        this.name = NFRadialAxis.NAME_DEFAULT;
        this.labelEnabled = NFRadialAxis.LABEL_ENABLED_DEFAULT;
        this.spacing = null;
        this.scale = 0.0;
        this.label = new NFLabel();
        this.ticLabel = new NFLabel();
        this.ticActiveLabels = new Vector();
        this.dwell = null;
        this.titleAL = null;
        this.axisColor = Color.black;
        this.minimum = NFRadialAxis.MINIMUM_DEFAULT;
        this.maximum = NFRadialAxis.MAXIMUM_DEFAULT;
        this.step = NFRadialAxis.STEP_DEFAULT;
        this.b = NFRadialAxis.a;
        this.d = NFRadialAxis.c;
        this.showTics = true;
        this.showLabel = true;
        this.showTicLabels = true;
        this.showLine = true;
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.ticSpacing = null;
        this.e = false;
        this.groupSize = 3;
        this.groupSymbol = ',';
        this.decSymbol = '.';
    }
    
    public void setTicActiveLabels(final Vector ticActiveLabels) {
        if (this.dwell != null && this.ticActiveLabels != null) {
            this.dwell.removeLabel(this.ticActiveLabels);
        }
        this.ticActiveLabels = ticActiveLabels;
        if (this.dwell != null && this.ticActiveLabels != null) {
            this.dwell.addLabels(this.ticActiveLabels, this.ticActiveLabels.size());
        }
    }
    
    public Vector getTicActiveLabels() {
        return this.ticActiveLabels;
    }
    
    public void setTitleActiveLabel(final NFActiveLabel titleAL) {
        if (this.dwell != null && this.titleAL != null) {
            this.dwell.removeLabel(this.titleAL);
        }
        this.titleAL = titleAL;
        if (this.dwell != null && this.titleAL != null) {
            this.dwell.addLabel(this.titleAL);
        }
    }
    
    public NFActiveLabel getTitleActiveLabel() {
        return this.titleAL;
    }
    
    public void setLabel(final NFLabel label) {
        this.label = label;
    }
    
    public NFLabel getLabel() {
        return this.label;
    }
    
    public void showTicsAndLabels(final boolean showTics, final boolean showTicLabels) {
        this.showTics = showTics;
        this.showTicLabels = showTicLabels;
    }
    
    public void showAxisLine(final boolean showLine) {
        this.showLine = showLine;
    }
    
    public void showLabel(final boolean showLabel) {
        this.showLabel = showLabel;
    }
    
    public boolean getShowLabel() {
        return this.showLabel;
    }
    
    public void setTicLabel(final NFLabel ticLabel) {
        this.ticLabel = ticLabel;
    }
    
    public NFLabel getTicLabel() {
        return this.ticLabel;
    }
    
    public void setDwell(final NFActiveRegion dwell) {
        final NFActiveRegion dwell2 = this.dwell;
        this.dwell = dwell;
        if (dwell2 != null) {
            if (this.titleAL != null) {
                dwell2.removeLabel(this.titleAL);
            }
            if (this.ticActiveLabels != null && this.ticActiveLabels.size() > 0) {
                dwell2.removeLabel(this.ticActiveLabels);
            }
        }
        if (dwell != null) {
            if (this.titleAL != null) {
                dwell.addLabel(this.titleAL);
            }
            if (this.ticActiveLabels != null && this.ticActiveLabels.size() > 0) {
                dwell.addLabels(this.ticActiveLabels, this.ticActiveLabels.size());
            }
        }
    }
    
    public NFActiveRegion getDwell() {
        return this.dwell;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setLabelEnabled(final boolean labelEnabled) {
        this.labelEnabled = labelEnabled;
    }
    
    public boolean isLabelEnabled() {
        return this.labelEnabled;
    }
    
    public void setScale(final double minimum, final double maximum, final double step) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.step = step;
        this.spacing = new NFSpacing(minimum, maximum, step);
    }
    
    public static void defineRadialAxes(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineString("RadialAxisName", null));
        vector.addElement(nfParam.defineDate("RadialAxisMin", null));
        vector.addElement(nfParam.defineDate("RadialAxisMax", null));
        vector.addElement(nfParam.defineDate("RadialAxisStep", null));
        vector.addElement(nfParam.defineColor("RadialAxisColor", Color.black));
        nfParam.defineVector("RadialAxes", nfParam.defineTuple("RadialAxisTuple", vector));
        final Vector vector2 = new Vector();
        nfParam.defineLabel("RATics", vector2);
        nfParam.defineVector("RadialAxesTics", nfParam.defineTuple("RadialAxesTicsTuple", vector2));
        final Vector vector3 = new Vector();
        nfParam.defineLabel("RALabel", vector3);
        nfParam.defineVector("RadialAxesLabel", nfParam.defineTuple("RadialAxesLabelTuple", vector3));
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("FLOAT", new Integer(2));
        hashtable.put("INTEGER", new Integer(1));
        hashtable.put("DECIMAL", new Integer(5));
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(nfParam.defineSymbol("RadialAxesFormatType", hashtable, new Integer(2)));
        vector4.addElement(nfParam.defineString("RadialAxesFormatStr"));
        nfParam.defineVector("RadialAxesFormat", nfParam.defineTuple("RadialAxesFormatTuple", vector4));
        nfParam.defineActiveLabel("RadialAxesTitleActiveLabels");
    }
    
    public static NFRadialAxis loadRadialAxis(final NFParam nfParam, final Vector vector, final NFActiveRegion dwell) {
        if (vector == null) {
            return null;
        }
        final NFRadialAxis nfRadialAxis = new NFRadialAxis();
        nfRadialAxis.setName(NFUtil.getString(vector, 0, NFRadialAxis.NAME_DEFAULT));
        nfRadialAxis.setScale(NFUtil.getNumber(vector, 1, NFRadialAxis.MINIMUM_DEFAULT), NFUtil.getNumber(vector, 2, NFRadialAxis.MAXIMUM_DEFAULT), NFUtil.getNumber(vector, 3, NFRadialAxis.STEP_DEFAULT));
        nfRadialAxis.setColor(NFUtil.getColor(vector, 4, Color.black));
        if (dwell != null) {
            nfRadialAxis.setDwell(dwell);
        }
        return nfRadialAxis;
    }
    
    public static Vector loadRadialAxes(final NFParam nfParam, final NFActiveRegion nfActiveRegion) throws Exception {
        final Vector<NFRadialAxis> vector = new Vector<NFRadialAxis>();
        final Vector vector2 = (Vector)nfParam.get("RadialAxes");
        for (int n = 0; vector2 != null && n < vector2.size(); ++n) {
            vector.addElement(loadRadialAxis(nfParam, vector2.elementAt(n), nfActiveRegion));
        }
        loadAxisTics(nfParam, vector);
        loadAxisLabel(nfParam, vector);
        loadActiveLabels(nfParam, vector);
        loadAxisFormat(nfParam, vector);
        return vector;
    }
    
    protected static void loadAxisTics(final NFParam nfParam, final Vector vector) throws Exception {
        final Vector vector2 = (Vector)nfParam.get("RadialAxesTics");
        if (vector2 == null || vector2.size() == 0) {
            return;
        }
        for (int n = 0; n < vector2.size() && n < vector.size(); ++n) {
            final NFRadialAxis nfRadialAxis = vector.elementAt(n);
            nfRadialAxis.loadAxisTics(vector2.elementAt(n), nfRadialAxis.getScale());
        }
    }
    
    protected void loadAxisTics(final Vector vector, final double scale) {
        this.showTicsAndLabels(true, !NFUtil.getString(vector, 0, "OFF").equals("OFF"));
        final NFLabel ticLabel = this.getTicLabel();
        ticLabel.setColor(NFUtil.getColor(vector, 1, NFLabel.COLOR_DEFAULT));
        ticLabel.setFont(NFUtil.getFont(NFUtil.getString(vector, 2, NFLabel.FONT_DEFAULT.getName()), 1, NFUtil.getNumber(vector, 3, NFLabel.FONT_DEFAULT.getSize())));
        this.setScale(scale);
        ticLabel.setAngle(NFUtil.getNumber(vector, 4, NFLabel.ANGLE_DEFAULT));
    }
    
    protected static void loadAxisLabel(final NFParam nfParam, final Vector vector) throws Exception {
        final Vector vector2 = (Vector)nfParam.get("RadialAxesLabel");
        if (vector2 == null || vector2.size() == 0) {
            return;
        }
        for (int n = 0; n < vector2.size() && n < vector.size(); ++n) {
            final NFRadialAxis nfRadialAxis = vector.elementAt(n);
            nfRadialAxis.loadAxisLabel(nfParam.getComponent(), vector2.elementAt(n), nfRadialAxis.getScale());
        }
    }
    
    protected void loadAxisLabel(final Component component, final Vector vector, final double scale) {
        this.showLabel(!NFUtil.getString(vector, 0, "OFF").equals("OFF"));
        final NFLabel label = this.getLabel();
        label.setComponent(component);
        label.setColor(NFUtil.getColor(vector, 1, NFLabel.COLOR_DEFAULT));
        label.setFont(NFUtil.getFont(NFUtil.getString(vector, 2, NFLabel.FONT_DEFAULT.getName()), 1, NFUtil.getNumber(vector, 3, NFLabel.FONT_DEFAULT.getSize())));
        this.setScale(scale);
        label.setAngle(NFUtil.getNumber(vector, 4, NFLabel.ANGLE_DEFAULT));
    }
    
    protected static void loadActiveLabels(final NFParam nfParam, final Vector vector) {
        final Vector loadAllParams = NFActiveLabel.loadAllParams(nfParam, "RadialAxesTitleActiveLabels");
        for (int n = 0; n < vector.size() && n < loadAllParams.size(); ++n) {
            vector.elementAt(n).setTitleActiveLabel(loadAllParams.elementAt(n));
        }
    }
    
    protected static void loadAxisFormat(final NFParam nfParam, final Vector vector) throws Exception {
        final Vector vector2 = (Vector)nfParam.get("RadialAxesFormat");
        if (vector2 == null || vector2.size() == 0) {
            return;
        }
        for (int n = 0; n < vector2.size() && n < vector.size(); ++n) {
            vector.elementAt(n).loadAxisFormat(vector2.elementAt(n));
        }
    }
    
    protected void loadAxisFormat(final Vector vector) {
        this.b = NFUtil.getNumber(vector, 0, NFRadialAxis.a);
        this.d = NFUtil.getString(vector, 1, NFRadialAxis.c);
    }
    
    public void setScale(final double scale) {
        this.scale = scale;
        this.label.setScale(scale);
        this.ticLabel.setScale(scale);
    }
    
    public void setColor(final Color axisColor) {
        this.axisColor = axisColor;
    }
    
    public double getScale() {
        return this.scale;
    }
    
    public void setLocation(final int x1, final int y1, final int x2, final int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.ticSpacing = new NFSpacing(this.x1, this.y1, this.x2 - this.x1, this.y1 - this.y2, this.spacing.size() - 1);
        if (!this.showLabel) {
            return;
        }
        this.label.setLabel(this.getName());
        this.adjustLabelPos();
        final Rectangle labelPos = getLabelPos(this.label);
        if (this.titleAL != null) {
            this.titleAL.setBounds(labelPos.x, labelPos.y, labelPos.width, labelPos.height);
        }
    }
    
    protected static Point getIntersection(final Rectangle rectangle, final int n, final int n2, final int n3, final int n4) {
        double n5;
        if (n3 == n) {
            n5 = (n4 - n2) / (n3 - n + 0.01);
        }
        else if (n4 == n2) {
            n5 = (n4 - n2 + 0.01) / (n3 - n);
        }
        else {
            n5 = (n4 - n2) / (n3 - n);
        }
        a("x1=" + n + " y1=" + n2 + " x2=" + n3 + " y2=" + n4 + " slope= " + n5 + " y2<y1=" + (n4 < n2));
        final Point point = new Point(n, n2);
        final Point point2 = new Point(n3, n4);
        final Point point3 = new Point(rectangle.x, rectangle.y);
        final Point point4 = new Point(rectangle.x + rectangle.width, rectangle.y);
        final Point point5 = new Point(rectangle.x, rectangle.y + rectangle.height);
        final Point point6 = new Point(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        Point point7;
        if (n5 < 0.0) {
            if (n4 < n2) {
                point7 = intersectionPoint(point, point2, point3, point5);
                if (point7 == null) {
                    point7 = intersectionPoint(point, point2, point5, point6);
                }
            }
            else {
                point7 = intersectionPoint(point, point2, point4, point6);
                if (point7 == null) {
                    point7 = intersectionPoint(point, point2, point3, point4);
                }
            }
        }
        else if (n4 < n2) {
            point7 = intersectionPoint(point, point2, point4, point6);
            if (point7 == null) {
                point7 = intersectionPoint(point, point2, point5, point6);
            }
        }
        else {
            point7 = intersectionPoint(point, point2, point3, point5);
            if (point7 == null) {
                point7 = intersectionPoint(point, point2, point3, point4);
            }
        }
        return point7;
    }
    
    public Point getPoint(double n) {
        if (n <= Math.min(this.minimum, this.maximum)) {
            return new Point(this.x1, this.y1);
        }
        if (n >= Math.max(this.maximum, this.minimum)) {
            return new Point(this.x2, this.y2);
        }
        final Point point = new Point(this.x1, this.y1);
        n = (n - this.minimum) / (this.maximum - this.minimum);
        point.x = (int)NFUtil.rint(n * (this.x2 - this.x1) + this.x1);
        point.y = (int)NFUtil.rint(n * (this.y2 - this.y1) + this.y1);
        return point;
    }
    
    public void draw(final Graphics graphics, final Rectangle rectangle, final double n) {
        final double n2 = 3.141592653589793 * ((n + 90.0) / 180.0);
        if (this.showLabel) {
            this.label.draw(graphics);
        }
        if (this.showLine) {
            NFLine.draw(graphics, this.x1, this.y1, this.x2, this.y2, 1, 1, this.axisColor, null, null, this.scale);
        }
        for (int i = 0; i < this.ticSpacing.size(); ++i) {
            if (!this.e || i != 0) {
                final Point nthPoint = this.ticSpacing.getNthPoint(i);
                int n3 = nthPoint.x - (int)NFUtil.rint(Math.cos(n2) * rectangle.width * 0.02);
                int n4 = nthPoint.y + (int)NFUtil.rint(Math.sin(n2) * rectangle.height * 0.02);
                if (this.showTics) {
                    NFLine.draw(graphics, nthPoint.x, nthPoint.y, n3, n4, 1, 1, this.axisColor, null, null, this.scale);
                }
                if (this.showTicLabels) {
                    final String label = this.getLabel(this.spacing.getNthValue(i));
                    this.ticLabel.setLabel(label);
                    final Dimension bounds = this.ticLabel.getBounds(graphics);
                    if (n3 > nthPoint.x) {
                        n3 += bounds.width / 2;
                    }
                    else if (n3 < nthPoint.x) {
                        n3 -= bounds.width / 2;
                    }
                    if (n4 > nthPoint.y) {
                        n4 += bounds.height / 2;
                    }
                    else if (n4 < nthPoint.y) {
                        n4 -= bounds.height / 2;
                    }
                    this.ticLabel.setPos(n3, n4);
                    if (this.ticActiveLabels != null && i < this.ticActiveLabels.size()) {
                        final NFActiveLabel nfActiveLabel = this.ticActiveLabels.elementAt(i);
                        if (nfActiveLabel.autoLabel) {
                            nfActiveLabel.setLabel(label);
                        }
                        nfActiveLabel.setBounds(n3 - bounds.width / 2, n4 - bounds.height / 2, bounds.width, bounds.height);
                    }
                    this.ticLabel.draw(graphics);
                }
            }
        }
    }
    
    protected static Rectangle getLabelPos(final NFLabel nfLabel) {
        final Dimension bounds = nfLabel.getBounds(null);
        return new Rectangle(nfLabel.x - bounds.width / 2, nfLabel.y - bounds.height / 2, bounds.width, bounds.height);
    }
    
    protected String getLabel(final double n) {
        return NFUtil.formatNumericValue(n, this.b, this.d, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
    }
    
    protected static Point intersectionPoint(final Point point, final Point point2, final Point point3, final Point point4) {
        if (!intersects(point, point2, point3, point4)) {
            return null;
        }
        final Point point5 = new Point(point.x - point3.x, point.y - point3.y);
        final Point point6 = new Point(point2.x - point.x, point2.y - point.y);
        final Point point7 = new Point(point4.x - point3.x, point4.y - point3.y);
        final double abs = Math.abs((point5.x * point7.y - point5.y * point7.x) / (point6.x * point7.y - point6.y * point7.x));
        return new Point(point.x + (int)(abs * point6.x), point.y + (int)(abs * point6.y));
    }
    
    protected static boolean intersects(final Point point, final Point point2, final Point point3, final Point point4) {
        final double triangularArea = triangularArea(point, point2, point3);
        final double triangularArea2 = triangularArea(point, point2, point4);
        final boolean b = (triangularArea <= 0.0 && triangularArea2 >= 0.0) || (triangularArea >= 0.0 && triangularArea2 <= 0.0);
        final double triangularArea3 = triangularArea(point3, point4, point);
        final double triangularArea4 = triangularArea(point3, point4, point2);
        final boolean b2 = (triangularArea3 <= 0.0 && triangularArea4 >= 0.0) || (triangularArea3 >= 0.0 && triangularArea4 <= 0.0);
        return b && b2;
    }
    
    protected static double triangularArea(final Point point, final Point point2, final Point point3) {
        return ((point2.x - point.x) * (point3.y - point.y) - (point2.y - point.y) * (point3.x - point.x)) / 2.0;
    }
    
    protected void adjustLabelPos() {
        if (!this.showLabel) {
            return;
        }
        final Dimension bounds = this.label.getBounds(null);
        if (this.x2 == this.x1) {
            if (this.y2 > this.y1) {
                this.label.setPos(this.x2, this.y2 + bounds.height / 2);
            }
            else {
                this.label.setPos(this.x2, this.y2 - bounds.height / 2);
            }
            return;
        }
        if (this.y2 == this.y1) {
            if (this.x2 > this.x1) {
                this.label.setPos(this.x2 + (bounds.width / 2 + 3), this.y2);
            }
            else {
                this.label.setPos(this.x2 - (bounds.width / 2 + 3), this.y2);
            }
            return;
        }
        final int n = bounds.width / 2 + 3;
        final int n2 = bounds.height / 2;
        this.label.setPos((int)((this.x2 > this.x1) ? (this.x2 + n) : ((double)(this.x2 - n))), (int)((this.y2 > this.y1) ? (this.y2 + n2) : ((double)(this.y2 - n2))));
    }
    
    private static void a(final String s) {
        if (NFRadialAxis.f) {
            System.out.println(s);
        }
    }
    
    public void setNumberFormat(final int groupSize, final char groupSymbol, final char decSymbol) {
        this.groupSize = groupSize;
        this.groupSymbol = groupSymbol;
        this.decSymbol = decSymbol;
    }
    
    public char getGroupSymbol() {
        return this.groupSymbol;
    }
    
    public int getGroupSize() {
        return this.groupSize;
    }
    
    public char getDecimalSymbol() {
        return this.decSymbol;
    }
    
    static {
        NFRadialAxis.NAME_DEFAULT = "NoNameRadialAxis";
        NFRadialAxis.LABEL_ENABLED_DEFAULT = false;
        NFRadialAxis.MINIMUM_DEFAULT = 0.0;
        NFRadialAxis.MAXIMUM_DEFAULT = 0.0;
        NFRadialAxis.STEP_DEFAULT = 0.0;
        NFRadialAxis.a = 2;
        NFRadialAxis.c = null;
        NFRadialAxis.f = false;
    }
}
