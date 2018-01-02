// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;
import netcharts.util.NFDate;
import java.awt.Graphics;
import netcharts.util.NFColor;
import netcharts.util.NFParam;
import java.awt.Font;
import java.util.Enumeration;
import java.awt.Component;
import netcharts.util.NFUtil;
import java.awt.Polygon;
import java.awt.Color;
import netcharts.util.NFParamDef;
import netcharts.util.NFDebug;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Vector;
import java.util.Hashtable;

public class NFDiagram extends NFGraph
{
    public static final int NONE = 0;
    public static final int FROMTO = 1;
    public static final int TOFROM = 2;
    public static final int BOTH = 3;
    private static final boolean a = false;
    private Hashtable b;
    private Vector c;
    private Vector d;
    private boolean e;
    private Dimension f;
    private NFRegionBorder g;
    private StringBuffer h;
    private Vector i;
    private Hashtable j;
    private Hashtable k;
    private Vector l;
    private Rectangle m;
    private int n;
    private NFNode o;
    private int p;
    private int q;
    private boolean r;
    private int s;
    
    public NFDiagram(final Applet applet) {
        this.e = true;
        this.f = new Dimension();
        this.g = new NFRegionBorder();
        this.h = new StringBuffer();
        this.m = new Rectangle();
        this.n = "PolySet-".length();
        this.o = null;
        this.p = 0;
        this.q = 0;
        this.r = false;
        this.s = -1;
        this.initGraph(applet);
        this.b = new Hashtable();
        this.c = new Vector();
        this.d = new Vector();
    }
    
    private void a(final long n, final String s) {
        NFDebug.print(n, "NFDiagram: " + s);
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("name"));
        vector.addElement(super.param.defineString("lbl", ""));
        vector.addElement(super.param.defineNumber("x", new Integer(100)));
        vector.addElement(super.param.defineNumber("y", new Integer(100)));
        vector.addElement(super.param.defineColor("fgcolor", Color.black));
        vector.addElement(super.param.defineColor("bgcolor", Color.lightGray));
        vector.addElement(super.param.defineString("font", "TimesRoman"));
        vector.addElement(super.param.defineNumber("fontsize", new Integer(14)));
        super.param.defineVector("Nodes", super.param.defineTuple("Node", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineString("tag"));
        vector2.addElement(super.param.defineNumber("pnt", new Integer(0)));
        super.param.defineVector("PolySet", super.param.defineTuple("Polygons", vector2, true));
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineString("tag"));
        vector3.addElement(super.param.defineString("val"));
        super.param.defineVector("PolyDataSet", super.param.defineTuple("PolyDataVector", vector3, true));
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(super.param.defineString("operator"));
        vector4.addElement(super.param.defineString("compval", ""));
        vector4.addElement(super.param.defineString("compval2", ""));
        vector4.addElement(super.param.defineColor("polycolor", Color.lightGray));
        super.param.defineVector("PolyExpr", super.param.defineTuple("expressions", vector4));
        final Vector<NFParamDef> vector5 = new Vector<NFParamDef>();
        vector5.addElement(super.param.defineString("ctag"));
        vector5.addElement(super.param.defineColor("pcolor"));
        super.param.defineVector("PolyColor", super.param.defineTuple("pcolors", vector5));
        super.param.defineActiveLabel("PolyActiveLabels");
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("FROMTO", new Integer(1));
        hashtable.put("TOFROM", new Integer(2));
        hashtable.put("BOTH", new Integer(3));
        final Vector<NFParamDef> vector6 = new Vector<NFParamDef>();
        vector6.addElement(super.param.defineString("from"));
        vector6.addElement(super.param.defineString("to"));
        vector6.addElement(super.param.defineColor("e-color", Color.blue));
        vector6.addElement(super.param.defineSymbol("e-arrow", hashtable, new Integer(1)));
        vector6.addElement(super.param.defineLineStyle("EdgeLineStyle", 1));
        vector6.addElement(super.param.defineNumber("EdgeLineWidth", new Integer(2)));
        super.param.defineArrow("Edge", vector6);
        super.param.defineVector("Edges", super.param.defineTuple("Edge", vector6));
        final Vector<NFParamDef> vector7 = new Vector<NFParamDef>();
        super.param.defineLine("EdgeArrowLine", vector7);
        vector7.addElement(super.param.defineSymbol("EdgeArrowType", hashtable, new Integer(1)));
        super.param.defineArrow("EdgeArrowArrow", vector7);
        super.param.defineVector("EdgeArrow", super.param.defineTuple("EdgeArrowTuple", vector7));
        super.param.defineActiveLabel("ActiveLabels");
        super.param.defineString("ActiveLabelsEnabled", "ON");
        super.param.defineString("NodeDrag", "ON");
        final Vector vector8 = new Vector();
        super.param.defineLabel("NodeLabelTuple", vector8);
        vector8.removeElementAt(0);
        super.param.defineVector("NodeLabel", super.param.defineTuple("NodeLabelTuple", vector8));
        super.param.defineVector("NodeBox", super.param.defineRegion("NodeBoxTuple"));
    }
    
    protected void loadParams() throws Exception {
        super.loadParams();
        boolean b = false;
        if (super.param.changed("PolyDataSet")) {
            super.graphChanged = true;
            b = true;
            this.k = new Hashtable();
            final Vector vector = (Vector)super.param.get("PolyDataSet");
            if (vector != null) {
                final Enumeration<Vector<String>> elements = vector.elements();
                while (elements != null && elements.hasMoreElements()) {
                    try {
                        final Vector<String> vector2 = elements.nextElement();
                        this.k.put("PolySet-" + vector2.elementAt(0), vector2.elementAt(1));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (super.param.changed("PolySet")) {
            super.graphChanged = true;
            b = true;
            this.deleteAllPolygons();
            this.i = new Vector();
            final Vector loadAllParams = NFActiveLabel.loadAllParams(super.param, "PolyActiveLabels");
            final Enumeration<Vector<String>> elements2 = (Enumeration<Vector<String>>)((Vector)super.param.get("PolySet")).elements();
            int selectedItemIndex = 0;
            while (elements2.hasMoreElements()) {
                try {
                    final Vector<String> vector3 = elements2.nextElement();
                    final String label = vector3.elementAt(0);
                    final Polygon polygon = new Polygon();
                    for (int n = 1; n + 1 < vector3.size(); n += 2) {
                        final Double n2 = (Double)vector3.elementAt(n);
                        final Double n3 = (Double)vector3.elementAt(n + 1);
                        if (!Double.isNaN(n2)) {
                            if (!Double.isNaN(n3)) {
                                polygon.addPoint((int)(Object)n2, (int)(Object)n3);
                            }
                        }
                    }
                    NFActiveLabel addLabel;
                    if (loadAllParams != null && selectedItemIndex < loadAllParams.size()) {
                        addLabel = loadAllParams.elementAt(selectedItemIndex);
                        addLabel.areaPoly = polygon;
                    }
                    else {
                        addLabel = new NFActiveLabel();
                        addLabel.label = label;
                        addLabel.areaPoly = polygon;
                    }
                    if (super.dwell != null) {
                        addLabel = super.dwell.addLabel(addLabel);
                    }
                    addLabel.selectedItemParam = "PolySet-" + label;
                    addLabel.selectedItemIndex = selectedItemIndex;
                    this.i.addElement(addLabel);
                    ++selectedItemIndex;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        if (super.param.changed("PolyColor")) {
            this.j = new Hashtable();
            final Vector vector4 = (Vector)super.param.get("PolyColor");
            if (vector4 != null) {
                final Enumeration<Vector<String>> elements3 = vector4.elements();
                while (elements3 != null && elements3.hasMoreElements()) {
                    try {
                        final Vector<String> vector5 = elements3.nextElement();
                        this.j.put("PolySet-" + vector5.elementAt(0), vector5.elementAt(1));
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
        if (super.param.changed("PolyExpr")) {
            this.l = new Vector();
            final Vector vector6 = (Vector)super.param.get("PolyExpr");
            if (vector6 != null) {
                final Enumeration<Vector<String>> elements4 = vector6.elements();
                while (elements4 != null && elements4.hasMoreElements()) {
                    final NFPolyExpr nfPolyExpr = new NFPolyExpr();
                    try {
                        final Vector<String> vector7 = elements4.nextElement();
                        nfPolyExpr.a = vector7.elementAt(0);
                        nfPolyExpr.b = vector7.elementAt(1);
                        nfPolyExpr.c = vector7.elementAt(2);
                        nfPolyExpr.d = (Color)vector7.elementAt(3);
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                    this.l.addElement(nfPolyExpr);
                }
            }
        }
        if (super.param.changed("Nodes") || super.param.changed("ActiveLabels") || super.param.changed("ActiveLabelsEnabled")) {
            super.graphChanged = true;
            b = true;
            this.deleteAllNodes();
            final Enumeration<Vector<Color>> elements5 = (Enumeration<Vector<Color>>)((Vector)super.param.get("Nodes")).elements();
            final boolean equals = NFUtil.getString(super.param.get("ActiveLabelsEnabled"), "ON").equals("ON");
            final Vector loadAllParams2 = NFActiveLabel.loadAllParams(super.param, "ActiveLabels");
            int n4 = 0;
            while (elements5.hasMoreElements()) {
                final Vector<Color> vector8 = elements5.nextElement();
                NFActiveLabel nfActiveLabel;
                if (equals && loadAllParams2 != null && n4 < loadAllParams2.size()) {
                    nfActiveLabel = loadAllParams2.elementAt(n4);
                }
                else {
                    nfActiveLabel = null;
                }
                Color defaultColor = vector8.elementAt(4);
                if (defaultColor == null) {
                    defaultColor = this.defaultColor(n4);
                }
                Color defaultColor2 = vector8.elementAt(5);
                if (defaultColor2 == null) {
                    defaultColor2 = this.defaultColor(n4);
                }
                this.addNode((String)vector8.elementAt(0), (String)vector8.elementAt(1), ((Number)vector8.elementAt(2)).intValue(), ((Number)vector8.elementAt(3)).intValue(), defaultColor, defaultColor2, NFUtil.getFont((String)vector8.elementAt(6), 1, ((Number)vector8.elementAt(7)).intValue()), nfActiveLabel, equals);
                ++n4;
            }
        }
        if (b || super.param.changed("NodeLabel")) {
            super.graphChanged = true;
            final Vector vector9 = (Vector)super.param.get("NodeLabel");
            if (vector9 != null && vector9.size() > 0) {
                for (int size = this.c.size(), n5 = 0; n5 < vector9.size() && n5 < size; ++n5) {
                    final NFNode nfNode = this.c.elementAt(n5);
                    final Vector<String> vector10 = vector9.elementAt(n5);
                    vector10.insertElementAt(nfNode.e.getLabel(), 0);
                    NFLabel.loadParams(nfNode.e, vector10, 0);
                    nfNode.e.setScale(super.scale);
                    vector10.removeElementAt(0);
                    if (vector10.elementAt(0) == null) {
                        nfNode.e.setColor(this.defaultColor(n5));
                    }
                }
            }
        }
        if (b || super.param.changed("NodeBox")) {
            super.graphChanged = true;
            final Vector vector11 = (Vector)super.param.get("NodeBox");
            if (vector11 != null && vector11.size() > 0) {
                for (int size2 = this.c.size(), n6 = 0; n6 < vector11.size() && n6 < size2; ++n6) {
                    final NFNode nfNode2 = this.c.elementAt(n6);
                    NFRegion.loadParams(nfNode2.f, vector11.elementAt(n6), 0);
                    nfNode2.f.setScale(super.scale);
                }
            }
        }
        if (b || super.param.changed("Edges")) {
            super.graphChanged = true;
            final Enumeration<Vector<Color>> elements6 = (Enumeration<Vector<Color>>)((Vector)super.param.get("Edges")).elements();
            this.deleteAllEdges();
            int n7 = 0;
            while (elements6.hasMoreElements()) {
                final Vector<Color> vector12 = elements6.nextElement();
                Color defaultColor3 = vector12.elementAt(2);
                if (defaultColor3 == null) {
                    defaultColor3 = this.defaultColor(n7);
                }
                final NFArrow loadParams = NFArrow.loadParams(super.param, vector12, 6);
                try {
                    this.addEdge((String)vector12.elementAt(0), (String)vector12.elementAt(1), defaultColor3, ((Number)vector12.elementAt(3)).intValue(), ((Number)vector12.elementAt(4)).intValue(), ((Number)vector12.elementAt(5)).intValue(), loadParams);
                }
                catch (Exception ex5) {
                    this.a(2L, ex5.getMessage());
                }
                ++n7;
            }
        }
        if (b || super.param.changed("EdgeArrow")) {
            super.graphChanged = true;
            final Vector vector13 = (Vector)super.param.get("EdgeArrow");
            for (int i = 0; i < vector13.size(); ++i) {
                final Vector vector14 = vector13.elementAt(i);
                final NFEdge nfEdge = (NFEdge)NFUtil.getObject(this.d, i, null);
                if (nfEdge != null) {
                    nfEdge.e = NFUtil.getNumber(vector14, 0, nfEdge.e);
                    nfEdge.f = NFUtil.getNumber(vector14, 1, nfEdge.f);
                    nfEdge.c = NFUtil.getColor(vector14, 2, nfEdge.c);
                    nfEdge.d = NFUtil.getNumber(vector14, 3, nfEdge.d);
                    nfEdge.g = (NFArrow)NFUtil.getObject(NFArrow.loadParams(super.param, vector14, 4), nfEdge.g);
                }
            }
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.setAllMapComponent(this, super.notesets);
            for (int size3 = super.notesets.size(), j = 0; j < size3; ++j) {
                ((NFNoteSet)super.notesets.elementAt(j)).setAxisMap(null);
            }
        }
        if (super.param.changed("NodeDrag")) {
            final String s = (String)super.param.get("NodeDrag");
            if (s != null) {
                this.e = s.equalsIgnoreCase("ON");
            }
        }
    }
    
    private NFNode a(final String s) throws Exception {
        final NFNode nfNode = this.b.get(s);
        if (nfNode == null) {
            throw new Exception("Unknown Node <" + s + ">");
        }
        return nfNode;
    }
    
    public NFActiveLabel getNodeActiveLabel(final int n) {
        NFNode nfNode;
        try {
            nfNode = this.c.elementAt(n);
        }
        catch (Exception ex) {
            nfNode = null;
        }
        if (nfNode == null) {
            return null;
        }
        return nfNode.d;
    }
    
    public void addNode(final String s, final String s2, final int n, final int n2, final Color color, final Color color2, final Font font, final NFActiveLabel nfActiveLabel) {
        this.addNode(s, s2, n, n2, color, color2, font, nfActiveLabel, true);
    }
    
    public void addNode(final String s, final String label, final int b, final int c, final Color color, final Color color2, final Font font, final NFActiveLabel nfActiveLabel, final boolean b2) {
        NFNode a;
        try {
            a = this.a(s);
        }
        catch (Exception ex) {
            a = new NFNode();
            a.a = s.toString();
            this.b.put(s, a);
            this.c.addElement(a);
        }
        a.b = b;
        a.c = c;
        (a.f = new NFRegion()).setColor(color2);
        (a.e = new NFLabel()).setFont(font);
        a.e.setColor(color);
        a.e.setJustify(4);
        a.e.setComponent(this);
        a.e.setRegion(a.f);
        a.e.setLabel(label);
        if (b2) {
            if (super.dwell != null) {
                a.d = super.dwell.addLabel(nfActiveLabel);
                a.d.selectedItemParam = "Nodes";
                a.d.selectedItemIndex = this.c.indexOf(a);
            }
            else {
                a.d = null;
            }
            a.e.setActiveLabel(a.d);
        }
    }
    
    public void deleteNode(final String s) throws Exception {
        final NFNode a = this.a(s);
        for (int i = this.d.size() - 1; i >= 0; --i) {
            final NFEdge nfEdge = this.d.elementAt(i);
            if (nfEdge.a.equals(a) || nfEdge.b.equals(a)) {
                this.d.removeElementAt(i);
            }
        }
        if (super.dwell != null) {
            super.dwell.removeLabel(a.d);
        }
        this.b.remove(s);
        this.c.removeElement(a);
    }
    
    public void deleteAllNodes() throws Exception {
        this.b.clear();
        if (super.dwell != null) {
            for (int i = 0; i < this.c.size(); ++i) {
                final NFNode nfNode = this.c.elementAt(i);
                if (nfNode.d != null) {
                    super.dwell.removeLabel(nfNode.d);
                }
            }
        }
        this.c.removeAllElements();
        this.d.removeAllElements();
    }
    
    public void deleteAllPolygons() throws Exception {
        if (this.i == null) {
            return;
        }
        if (super.dwell != null) {
            for (int i = 0; i < this.i.size(); ++i) {
                final NFActiveLabel nfActiveLabel = this.i.elementAt(i);
                if (nfActiveLabel != null) {
                    super.dwell.removeLabel(nfActiveLabel);
                }
            }
        }
        this.i.removeAllElements();
    }
    
    public void deleteEdge(final String s, final String s2) throws Exception {
        final NFNode a = this.a(s);
        final NFNode a2 = this.a(s2);
        for (int i = this.d.size() - 1; i >= 0; --i) {
            final NFEdge nfEdge = this.d.elementAt(i);
            if (nfEdge.a.equals(a) && nfEdge.b.equals(a2)) {
                this.d.removeElementAt(i);
            }
        }
    }
    
    public void deleteAllEdges() throws Exception {
        this.d.removeAllElements();
    }
    
    public void setLabel(final String s, final String s2) throws Exception {
        this.a(s).e.setLabel(s2.toString());
    }
    
    public void setPos(final String s, final int b, final int c) throws Exception {
        final NFNode a = this.a(s);
        a.b = b;
        a.c = c;
    }
    
    public void setColor(final String s, final Color color, final Color color2) throws Exception {
        final NFNode a = this.a(s);
        a.e.setColor(color);
        a.f.setColor(color2);
    }
    
    public void setFont(final String s, final Font font) throws Exception {
        this.a(s).e.setFont(font);
    }
    
    public void addEdge(final String s, final String s2, final Color color) throws Exception {
        this.addEdge(s, s2, color, 1);
    }
    
    public void addEdge(final String s, final String s2, final Color color, final int n) throws Exception {
        this.addEdge(s, s2, color, n, 1, 2, new NFArrow());
    }
    
    public void addEdge(final String s, final String s2, final Color c, final int d, final int e, final int f, final NFArrow g) throws Exception {
        final NFEdge nfEdge = new NFEdge();
        nfEdge.a = this.a(s);
        nfEdge.b = this.a(s2);
        nfEdge.c = c;
        nfEdge.d = d;
        nfEdge.e = e;
        nfEdge.f = f;
        nfEdge.g = g;
        this.d.addElement(nfEdge);
    }
    
    public String getArrowStyle(final int n) {
        switch (n) {
            case 0: {
                return "NONE";
            }
            case 1: {
                return "FROMTO";
            }
            case 2: {
                return "TOFROM";
            }
            case 3: {
                return "BOTH";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
    
    public synchronized String getEdges() {
        this.h.setLength(0);
        this.getEdges(this.h);
        return this.h.toString();
    }
    
    public void getEdges(final StringBuffer sb) {
        for (int size = this.d.size(), i = 0; i < size; ++i) {
            if (i == 0) {
                sb.append("\t");
            }
            else {
                sb.append(",\n\t");
            }
            final NFEdge nfEdge = this.d.elementAt(i);
            sb.append("(\"");
            NFParam.doEscapes(nfEdge.a.a, sb);
            sb.append("\",\"");
            NFParam.doEscapes(nfEdge.b.a, sb);
            sb.append("\",");
            sb.append(NFColor.toString(nfEdge.c));
            sb.append(',');
            sb.append(this.getArrowStyle(nfEdge.d));
            sb.append(',');
            sb.append(NFLine.getLineStyleName(nfEdge.e));
            sb.append(',');
            sb.append(nfEdge.f);
            if (nfEdge.g != null) {
                sb.append(',');
                nfEdge.g.getParams(sb);
            }
            sb.append(')');
        }
    }
    
    private void a(final Graphics graphics, final NFEdge nfEdge, final Rectangle rectangle) {
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int x2 = rectangle.x;
        final int y2 = rectangle.y;
        int n;
        int n2;
        int n3;
        int n4;
        if (super.scale <= 0.0) {
            n = x + nfEdge.a.b;
            n2 = y + nfEdge.a.c;
            n3 = x2 + nfEdge.b.b;
            n4 = y2 + nfEdge.b.c;
        }
        else {
            n = x + (int)(super.scale * nfEdge.a.b);
            n2 = y + (int)(super.scale * nfEdge.a.c);
            n3 = x2 + (int)(super.scale * nfEdge.b.b);
            n4 = y2 + (int)(super.scale * nfEdge.b.c);
        }
        final Color color = graphics.getColor();
        graphics.setColor(nfEdge.c);
        NFLine.draw(graphics, n, n2, n3, n4, nfEdge.f, nfEdge.e, null, null, null, super.scale);
        if (nfEdge.g != null) {
            nfEdge.g.setThickness(nfEdge.f);
            nfEdge.g.setScale(super.scale);
            if (nfEdge.d == 1 || nfEdge.d == 3) {
                nfEdge.g.draw(graphics, n, n2, (n + n3) / 2, (n2 + n4) / 2);
            }
            if (nfEdge.d == 2 || nfEdge.d == 3) {
                nfEdge.g.draw(graphics, n3, n4, (n + n3) / 2, (n2 + n4) / 2);
            }
        }
        graphics.setColor(color);
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle rectangle) {
        final int size = this.d.size();
        this.m.x = rectangle.x;
        this.m.y = rectangle.y;
        this.m.width = rectangle.width;
        this.m.height = rectangle.height;
        final Graphics create = graphics.create();
        create.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        for (int i = 0; i < size; ++i) {
            this.a(create, (NFEdge)this.d.elementAt(i), rectangle);
        }
        for (int j = 0; j < this.c.size(); ++j) {
            final NFNode nfNode = this.c.elementAt(j);
            nfNode.e.setComponent(this);
            nfNode.e.setScale(super.scale);
            int b;
            int c;
            if (super.scale <= 0.0) {
                b = nfNode.b;
                c = nfNode.c;
            }
            else {
                b = (int)(nfNode.b * super.scale);
                c = (int)(nfNode.c * super.scale);
            }
            nfNode.e.draw(create, rectangle.x + b, rectangle.y + c);
            final NFActiveLabel d = nfNode.d;
            if (d != null) {
                if (d.label == null || d.label.length() < 1) {
                    d.autoLabel = true;
                }
                if (d.autoLabel) {
                    d.setLabel("(" + nfNode.b + "," + nfNode.c + ")");
                }
            }
        }
        create.dispose();
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
        }
        if (this.i != null) {
            final Graphics create2 = graphics.create();
            create2.setColor(Color.red);
            for (int k = 0; k < this.i.size(); ++k) {
                final NFActiveLabel nfActiveLabel = this.i.elementAt(k);
                final String selectedItemParam = nfActiveLabel.selectedItemParam;
                nfActiveLabel.label = this.getLabel(nfActiveLabel, selectedItemParam);
                final Color polyColor = this.getPolyColor(selectedItemParam);
                if (polyColor != null) {
                    create2.setColor(polyColor);
                    create2.fillPolygon(nfActiveLabel.areaPoly);
                }
            }
            create2.dispose();
        }
    }
    
    public String getLabel(final NFActiveLabel nfActiveLabel, final String s) {
        if (this.k == null) {
            return nfActiveLabel.label;
        }
        final String label = nfActiveLabel.label;
        final String s2 = this.k.get(s);
        if (s2 == null) {
            return label;
        }
        if (nfActiveLabel.selectedItemLabels == null) {
            nfActiveLabel.selectedItemLabels = new Vector();
        }
        else {
            nfActiveLabel.selectedItemLabels.removeAllElements();
        }
        nfActiveLabel.selectedItemLabels.addElement(new String[] { "polygonvalue", s2 });
        return label + "\n" + s2;
    }
    
    public Color getPolyColor(final String s) {
        if (this.k == null) {
            return this.j.get(s);
        }
        final Object value = this.k.get(s);
        if (value == null) {
            return this.j.get(s);
        }
        if (this.l == null || this.l.size() < 1) {
            return this.j.get(s);
        }
        for (int i = 0; i < this.l.size(); ++i) {
            final NFPolyExpr nfPolyExpr = this.l.elementAt(i);
            final Object b = nfPolyExpr.b;
            final Object c = nfPolyExpr.c;
            final String a = nfPolyExpr.a;
            final double a2 = this.a(value);
            final double a3 = this.a(b);
            final double a4 = this.a(c);
            if (a.equals(">") && a2 != Double.NaN && a3 != Double.NaN && a2 > a3) {
                return nfPolyExpr.d;
            }
            if (a.equals("<") && a2 != Double.NaN && a3 != Double.NaN && a2 < a3) {
                return nfPolyExpr.d;
            }
            if (a.equals("==") && a2 != Double.NaN && a3 != Double.NaN && a2 == a3) {
                return nfPolyExpr.d;
            }
            if (a.equals(">=") && a2 != Double.NaN && a3 != Double.NaN && a2 >= a3) {
                return nfPolyExpr.d;
            }
            if (a.equals("<=") && a2 != Double.NaN && a3 != Double.NaN && a2 <= a3) {
                return nfPolyExpr.d;
            }
            if (a.equals("!=") && a2 != Double.NaN && a3 != Double.NaN && a2 != a3) {
                return nfPolyExpr.d;
            }
            if (a.equals("between") && a2 != Double.NaN && a3 != Double.NaN && a4 != Double.NaN && a2 >= a3 && a2 <= a4) {
                return nfPolyExpr.d;
            }
            if (a.equals("=") && value.toString().equals(b.toString())) {
                return nfPolyExpr.d;
            }
            if (a.equals("<>") && !value.toString().equals(b.toString())) {
                return nfPolyExpr.d;
            }
        }
        return this.j.get(s);
    }
    
    private double a(final Object o) {
        try {
            return new NFDate((String)o).getTime();
        }
        catch (Exception ex) {
            try {
                return new Double((String)o);
            }
            catch (Exception ex2) {
                return Double.NaN;
            }
        }
    }
    
    public int getPickIndex() {
        return this.s;
    }
    
    public synchronized boolean mouseDown(final Event event, int n, int n2) {
        double n3 = Double.MAX_VALUE;
        this.o = null;
        this.r = false;
        this.s = -1;
        if (super.mouseDown(event, n, n2)) {
            return true;
        }
        if (!this.e) {
            return false;
        }
        n -= this.m.x;
        n2 -= this.m.y;
        this.o = null;
        for (int i = 0; i < this.c.size(); ++i) {
            final NFNode o = this.c.elementAt(i);
            int b = o.b;
            int c = o.c;
            if (super.scale > 0.0) {
                b *= (int)super.scale;
                c *= (int)super.scale;
            }
            final double n4 = (b - n) * (b - n) + (c - n2) * (c - n2);
            if (n4 < n3) {
                this.o = o;
                this.s = i;
                n3 = n4;
            }
        }
        if (this.o != null) {
            if (super.scale <= 0.0) {
                this.p = n - this.o.b;
                this.q = n2 - this.o.c;
            }
            else {
                this.p = n - (int)(super.scale * this.o.b);
                this.q = n2 - (int)(super.scale * this.o.c);
            }
        }
        return false;
    }
    
    public synchronized boolean mouseDrag(final Event event, int n, int n2) {
        if (!this.e || this.o == null) {
            this.r = false;
            return super.mouseDrag(event, n, n2);
        }
        this.r = true;
        n -= this.m.x;
        n2 -= this.m.y;
        if (super.scale <= 0.0) {
            this.a(n - this.p, n2 - this.q);
        }
        else {
            this.a((int)((n - this.p) / super.scale), (int)((n2 - this.q) / super.scale));
        }
        super.graphChanged = true;
        this.display();
        return true;
    }
    
    private void a(final int b, final int c) {
        if (this.abortPreDrag(this.o.b, this.o.c, b, c)) {
            return;
        }
        this.o.b = b;
        this.o.c = c;
        final Graphics graphics = this.getGraphics();
        if (NFUtil.getJDKVersion() >= 1.2) {
            NFOffscreenImage.setAntiAliasing(graphics, super.antiAliasing);
        }
        this.f = this.o.e.getBounds(graphics, this.f, this.g);
        graphics.dispose();
        if (super.scale <= 0.0) {
            if (this.o.b - this.f.width / 2 < 0) {
                this.o.b = this.f.width / 2;
            }
            if (this.o.c - this.f.height / 2 < 0) {
                this.o.c = this.f.height / 2;
            }
            if (this.o.b + this.f.width / 2 > this.m.width) {
                this.o.b = this.m.width - this.f.width / 2;
            }
            if (this.o.c + this.f.height / 2 > this.m.height) {
                this.o.c = this.m.height - this.f.height / 2;
            }
        }
    }
    
    public synchronized boolean mouseUp(final Event event, int n, int n2) {
        if (!this.e || this.o == null || !this.r) {
            this.o = null;
            this.r = false;
            return super.mouseUp(event, n, n2);
        }
        n -= this.m.x;
        n2 -= this.m.y;
        if (super.scale <= 0.0) {
            this.a(n - this.p, n2 - this.q);
        }
        else {
            this.a((int)((n - this.p) / super.scale), (int)((n2 - this.q) / super.scale));
        }
        this.notifyPostDrag(this.o.b, this.o.c);
        this.o = null;
        super.graphChanged = true;
        this.display();
        return true;
    }
    
    protected void zoom(final int n, final int n2, final int n3, final int n4) {
    }
}
