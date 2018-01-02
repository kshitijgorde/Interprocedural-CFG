// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Graphics;
import netcharts.util.NFDebug;
import netcharts.util.NFUtil;
import netcharts.util.NFParam;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Vector;

public class NFNoteSet extends NFDraggable
{
    public String xaxis;
    public String yaxis;
    public static String XAXIS_DEFAULT;
    public static String YAXIS_DEFAULT;
    protected NFAxisMap axisMap;
    public static int MaxNoteSets;
    public boolean clip;
    public String name;
    protected Vector notes;
    protected NFNote sharedNote;
    private Vector a;
    private Vector b;
    private Vector c;
    private NFActiveRegion d;
    private Component e;
    private Dimension f;
    private double g;
    private int h;
    protected static final boolean DRAGGABLE_DEFAULT = true;
    private boolean i;
    private Vector j;
    private int k;
    private int l;
    
    public NFNoteSet() {
        this.xaxis = NFNoteSet.XAXIS_DEFAULT;
        this.yaxis = NFNoteSet.YAXIS_DEFAULT;
        this.axisMap = null;
        this.clip = false;
        this.name = null;
        this.notes = new Vector();
        this.sharedNote = new NFNote();
        this.a = null;
        this.b = null;
        this.c = new Vector();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0.0;
        this.h = -1;
        this.i = true;
        this.j = null;
        this.k = 0;
        this.l = -1;
        super.debugType = 8192L;
        super.debugName = "NFNoteSet";
    }
    
    public NFNoteSet(final NFActiveRegion d) {
        this.xaxis = NFNoteSet.XAXIS_DEFAULT;
        this.yaxis = NFNoteSet.YAXIS_DEFAULT;
        this.axisMap = null;
        this.clip = false;
        this.name = null;
        this.notes = new Vector();
        this.sharedNote = new NFNote();
        this.a = null;
        this.b = null;
        this.c = new Vector();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0.0;
        this.h = -1;
        this.i = true;
        this.j = null;
        this.k = 0;
        this.l = -1;
        super.debugType = 8192L;
        super.debugName = "NFNoteSet";
        this.d = d;
    }
    
    public static void setDwell(final Vector vector, final NFActiveRegion dwell) {
        if (vector == null) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).setDwell(dwell);
        }
    }
    
    public void setIndex(final int h) {
        this.h = h;
        for (int selectedItemIndex = 0; this.a != null && selectedItemIndex < this.a.size(); ++selectedItemIndex) {
            final NFActiveLabel nfActiveLabel = this.a.elementAt(selectedItemIndex);
            nfActiveLabel.selectedItemParam = "NoteSet" + h;
            nfActiveLabel.selectedItemIndex = selectedItemIndex;
        }
    }
    
    public int getIndex() {
        return this.h;
    }
    
    public void setDwell(final NFActiveRegion d) {
        if (this.d != null) {
            this.d.removeLabel(this.a);
        }
        if ((this.d = d) == null) {
            return;
        }
        if (d.getSelectMode()) {
            this.a = d.createSelectLabels(null, this.notes.size());
        }
        else {
            this.a = this.b;
            if (this.a != null) {
                d.addLabels(this.a, this.notes.size());
            }
        }
        if (this.a == null) {
            this.a = new Vector();
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFActiveLabel nfActiveLabel = this.a.elementAt(i);
            nfActiveLabel.selectedItemParam = "NoteSet" + (this.h + 1);
            nfActiveLabel.selectedItemIndex = i;
        }
        if (this.a.size() != this.notes.size()) {
            for (int j = this.a.size(); j < this.notes.size(); ++j) {
                final NFActiveLabel nfActiveLabel2 = new NFActiveLabel();
                nfActiveLabel2.selectedItemParam = "NoteSet" + (this.h + 1);
                nfActiveLabel2.selectedItemIndex = j;
                this.a.addElement(nfActiveLabel2);
                d.addLabel(nfActiveLabel2);
            }
        }
    }
    
    public static void setScale(final Vector vector, final double n) {
        if (vector == null) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFNoteSet set = vector.elementAt(i);
            set.setScale(n);
            set.sharedNote.setScale(n);
        }
    }
    
    public void setScale(final double g) {
        this.g = g;
    }
    
    private void a(final String s) throws Exception {
        throw new Exception("NFNoteSet: NoteAxis uses " + s.toUpperCase() + ", but that Axis is undefined");
    }
    
    public void setAxisMap(final NFAxisMap axisMap) throws Exception {
        this.axisMap = axisMap;
        if (NFCoord.getMapType(this.xaxis) == -1 && (axisMap == null || axisMap.getAxis(this.xaxis) == null)) {
            this.a(this.xaxis);
        }
        if (NFCoord.getMapType(this.yaxis) == -1 && (axisMap == null || axisMap.getAxis(this.yaxis) == null)) {
            this.a(this.yaxis);
        }
    }
    
    public void setMapComponent(final Component component) {
        this.e = component;
        this.sharedNote.setComponent(component);
    }
    
    public void loadParams(final NFParam nfParam, final String s) {
        try {
            if (nfParam.changed(s + "NoteSet")) {
                this.notes = (Vector)nfParam.get(s + "NoteSet");
            }
            this.sharedNote.loadSharedNote(nfParam, s);
            if (nfParam.changed(s + "NoteAxis")) {
                this.loadNoteAxis((Vector)nfParam.get(s + "NoteAxis"));
            }
        }
        catch (Exception ex) {
            this.debug("loadParams(): " + ex);
        }
    }
    
    public void loadNoteAxis(final Vector vector) {
        this.xaxis = NFUtil.getString(vector, 0, NFNoteSet.XAXIS_DEFAULT);
        this.yaxis = NFUtil.getString(vector, 1, NFNoteSet.YAXIS_DEFAULT);
        if (NFCoord.getMapType(this.xaxis) == -1 || NFCoord.getMapType(this.yaxis) == -1) {
            this.clip = true;
        }
        else {
            this.clip = false;
        }
    }
    
    public static int getNoteSetIndex(final Vector vector, final NFActiveLabel nfActiveLabel) {
        if (vector == null) {
            return -1;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFNoteSet set = vector.elementAt(i);
            if (set.a != null) {
                for (int n = 0; set.a != null && n < set.a.size(); ++n) {
                    if (set.a.elementAt(n) == nfActiveLabel) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static NFActiveLabel getNoteSetActiveLabel(final Vector vector, final int n) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return null;
        }
        final NFNoteSet set = vector.elementAt(n);
        if (set.a == null || set.a.size() < 1) {
            return null;
        }
        return (NFActiveLabel)set.a.elementAt(0);
    }
    
    public static boolean loadAllParams(final NFParam nfParam, final Vector vector) {
        return loadAllParams(nfParam, vector, null);
    }
    
    public static boolean loadAllParams(final NFParam nfParam, final Vector vector, final NFActiveRegion nfActiveRegion) {
        boolean b = false;
        try {
            if (nfParam.changed("NoteSets")) {
                final Vector vector2 = (Vector)nfParam.get("NoteSets");
                if (vector2 == null || vector2.size() < 1) {
                    if (vector.size() > 0) {
                        b = true;
                        a(vector, 0);
                    }
                    return b;
                }
                b = true;
                for (int i = 0; i < vector2.size(); ++i) {
                    NFNoteSet set;
                    if (i < vector.size()) {
                        set = vector.elementAt(i);
                    }
                    else {
                        set = new NFNoteSet(nfActiveRegion);
                        vector.addElement(set);
                    }
                    final Vector<String> vector3 = vector2.elementAt(i);
                    set.name = vector3.elementAt(0);
                    set.sharedNote.a = (int)vector3.elementAt(1);
                }
                a(vector, vector2.size());
            }
            final int size = vector.size();
            if (size == 0) {
                return false;
            }
            final Vector vector4 = (Vector)nfParam.get("NoteDrag");
            for (int j = 1; j <= size; ++j) {
                final NFNoteSet set2 = vector.elementAt(j - 1);
                final String string = NFUtil.getString(vector4, j - 1, null);
                set2.i = (string == null || string.equalsIgnoreCase("ON"));
                if (nfParam.changed("NoteSet" + j)) {
                    final Vector notes = (Vector)nfParam.get("NoteSet" + j);
                    b = true;
                    if (notes != null) {
                        set2.notes = notes;
                        set2.setDwell(nfActiveRegion);
                    }
                }
            }
            for (int k = 1; k <= size; ++k) {
                final NFNoteSet set3 = vector.elementAt(k - 1);
                if (nfParam.changed("NoteActiveLabels" + k)) {
                    b = true;
                    set3.b = NFActiveLabel.loadAllParams(nfParam, "NoteActiveLabels" + k);
                    set3.setDwell(nfActiveRegion);
                }
            }
            if (nfParam.changed("NoteLabel")) {
                final Vector vector5 = (Vector)nfParam.get("NoteLabel");
                if (vector5 != null) {
                    b = true;
                    final double chartScale = nfParam.getChartScale();
                    for (int n = 0; n < vector5.size() && n < size; ++n) {
                        final NFNoteSet set4 = vector.elementAt(n);
                        set4.sharedNote.loadParams(vector5.elementAt(n));
                        set4.sharedNote.setScale(chartScale);
                    }
                }
            }
            if (nfParam.changed("NoteBox")) {
                final Vector vector6 = (Vector)nfParam.get("NoteBox");
                if (vector6 != null) {
                    b = true;
                    for (int n2 = 0; n2 < vector6.size() && n2 < size; ++n2) {
                        vector.elementAt(n2).sharedNote.setRegion(NFRegion.loadParams(nfParam, vector6.elementAt(n2)));
                    }
                }
            }
            if (nfParam.changed("NoteAxis")) {
                final Vector vector7 = (Vector)nfParam.get("NoteAxis");
                if (vector7 != null) {
                    b = true;
                    for (int n3 = 0; n3 < vector7.size() && n3 < size; ++n3) {
                        vector.elementAt(n3).loadNoteAxis(vector7.elementAt(n3));
                    }
                }
            }
            if (nfParam.changed("NoteArrow")) {
                final Vector vector8 = (Vector)nfParam.get("NoteArrow");
                if (vector8 != null) {
                    b = true;
                    for (int n4 = 0; n4 < vector8.size() && n4 < size; ++n4) {
                        vector.elementAt(n4).sharedNote.loadArrow(nfParam, "", vector8.elementAt(n4));
                    }
                }
            }
        }
        catch (Exception ex) {
            NFDebug.print("NFNoteSet: loadAllParams failed");
            ex.printStackTrace();
        }
        if (vector != null) {
            for (int l = 0; l < vector.size(); ++l) {
                vector.elementAt(l).setIndex(l + 1);
            }
        }
        return b;
    }
    
    public void removeActiveLabels() {
        if (this.a == null) {
            return;
        }
        try {
            if (this.d != null) {
                this.d.removeLabel(this.a);
            }
            this.a = null;
        }
        catch (Exception ex) {
            this.debug("Could not remove active labels");
            this.debug(ex.toString());
        }
    }
    
    private static void a(final Vector vector, final int n) {
        while (vector.size() > n) {
            final NFNoteSet set = vector.elementAt(n);
            if (set.a != null) {
                set.removeActiveLabels();
            }
            vector.removeElementAt(n);
        }
    }
    
    public static void drawAllNoteSets(final Vector vector, final Graphics graphics) {
        drawAllNoteSets(vector, graphics, null);
    }
    
    public static void drawAllNoteSets(final Vector vector, final Graphics graphics, final Graphics graphics2) {
        final int size = vector.size();
        try {
            for (int i = 0; i < size; ++i) {
                final NFNoteSet set = vector.elementAt(i);
                if (set != null) {
                    set.draw(graphics, graphics2);
                }
            }
        }
        catch (Exception ex) {
            NFDebug.print("NFNoteSet: drawAllNoteSets: " + ex);
        }
    }
    
    public static void setAllMapComponent(final Component mapComponent, final Vector vector) {
        final int size = vector.size();
        try {
            for (int i = 0; i < size; ++i) {
                vector.elementAt(i).setMapComponent(mapComponent);
            }
        }
        catch (Exception ex) {
            NFDebug.print("NFNoteSet: setAllMapComponent: " + ex);
        }
    }
    
    public void draw(final Graphics graphics, final Graphics graphics2) {
        final int size = this.notes.size();
        if (this.e != null) {
            this.f = this.e.size();
        }
        if (this.clip && graphics2 != null) {
            this.sharedNote.setClipRectangle(graphics2.getClipRect());
        }
        else {
            this.sharedNote.setClipRectangle(null);
        }
        try {
            for (int i = 0; i < size; ++i) {
                this.a((Vector)this.notes.elementAt(i));
                NFActiveLabel activeLabel;
                if (this.a != null && i < this.a.size()) {
                    activeLabel = this.a.elementAt(i);
                }
                else {
                    activeLabel = null;
                }
                this.sharedNote.setActiveLabel(activeLabel);
                final Rectangle a = this.a(i);
                if (this.clip && graphics2 != null) {
                    this.sharedNote.draw(graphics2, a);
                }
                else {
                    this.sharedNote.draw(graphics, a);
                }
            }
        }
        catch (Exception ex) {
            NFDebug.print("NFNoteSet: draw failed: " + ex);
        }
    }
    
    private Rectangle a(final int i) {
        while (i >= this.c.size()) {
            this.c.addElement(new Rectangle());
        }
        return this.c.elementAt(i);
    }
    
    private void a(final Vector vector) throws Exception {
        this.sharedNote.setLabel(vector.elementAt(0));
        final String element = vector.elementAt(1);
        final String element2 = vector.elementAt(2);
        final String element3 = vector.elementAt(3);
        final String element4 = vector.elementAt(4);
        final String element5 = vector.elementAt(5);
        final String element6 = vector.elementAt(6);
        final String element7 = vector.elementAt(7);
        final String element8 = vector.elementAt(8);
        if (element == null || element2 == null) {
            NFDebug.print("NFNoteSet: Note point X/Y cannot be NULL!");
            return;
        }
        this.sharedNote.setCoordinates((int)NFCoord.getPixel(element, this.xaxis, this.axisMap, this.f.width, -1.0), (int)NFCoord.getPixel(element2, this.yaxis, this.axisMap, this.f.height, -1.0), (int)NFCoord.getPixel(element3, this.xaxis, this.axisMap, this.f.width, -1.0), (int)NFCoord.getPixel(element4, this.yaxis, this.axisMap, this.f.height, -1.0), (int)NFCoord.getPixel(element5, this.xaxis, this.axisMap, this.f.width, -1.0), (int)NFCoord.getPixel(element6, this.yaxis, this.axisMap, this.f.height, -1.0), (int)NFCoord.getPixel(element7, this.xaxis, this.axisMap, this.f.width, -1.0), (int)NFCoord.getPixel(element8, this.yaxis, this.axisMap, this.f.height, -1.0));
    }
    
    public static void addObserver(final Object o, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).addObserver(o);
        }
    }
    
    public static void removeObserver(final Object o, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).removeObserver(o);
        }
    }
    
    public static boolean mouseDown(final Event event, final int n, final int n2, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i).mouseDown(event, n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean mouseDrag(final Event event, final int n, final int n2, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i).mouseDrag(event, n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean mouseUp(final Event event, final int n, final int n2, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i).mouseUp(event, n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public int getDragPoint() {
        return this.k;
    }
    
    public int getDragNoteIndex() {
        return this.l;
    }
    
    public boolean isDraggable(final Event event, final int n, final int n2) {
        if (!this.i) {
            return false;
        }
        final int size = this.notes.size();
        for (int i = 0; i < size; ++i) {
            final Vector j = this.notes.elementAt(i);
            final Rectangle a = this.a(i);
            if (n >= a.x && n <= a.x + a.width && n2 >= a.y && n2 <= a.y + a.height) {
                this.j = j;
                this.l = i;
                this.k = 0;
                return true;
            }
        }
        int k;
        for (k = 0; k < size; ++k) {
            final Vector l = this.notes.elementAt(k);
            try {
                this.a(l);
                final int hitPoint = this.sharedNote.hitPoint(n, n2);
                if (hitPoint != -1) {
                    this.j = l;
                    this.l = k;
                    this.k = hitPoint;
                    return true;
                }
            }
            catch (Exception ex) {}
        }
        this.j = null;
        this.l = k;
        this.k = 0;
        return false;
    }
    
    public double[] getCoords() {
        if (this.j == null) {
            return null;
        }
        final int n = 2 * this.k + 1;
        super.curCoord[0] = NFCoord.getValue(this.j.elementAt(n), this.xaxis, this.axisMap);
        super.curCoord[1] = NFCoord.getValue(this.j.elementAt(n + 1), this.yaxis, this.axisMap);
        return super.curCoord;
    }
    
    public double[] moveRelative(final Event event, final int n, final int n2) {
        if (this.j == null) {
            return null;
        }
        if (n == 0 && n2 == 0) {
            return null;
        }
        final int n3 = 2 * this.k + 1;
        super.newCoord[0] = NFCoord.getValue(NFCoord.getPixel(this.j.elementAt(n3), this.xaxis, this.axisMap, this.f.width, -1.0) + n, this.xaxis, this.axisMap, this.f.width);
        super.newCoord[1] = NFCoord.getValue(NFCoord.getPixel(this.j.elementAt(n3 + 1), this.yaxis, this.axisMap, this.f.height, -1.0) + n2, this.yaxis, this.axisMap, this.f.height);
        return super.newCoord;
    }
    
    public boolean dragTo(final double[] array) {
        if (this.j == null) {
            return false;
        }
        final int n = 2 * this.k + 1;
        this.j.setElementAt(new Double(array[0]), n);
        this.j.setElementAt(new Double(array[1]), n + 1);
        return true;
    }
    
    static {
        NFNoteSet.XAXIS_DEFAULT = "PIXEL";
        NFNoteSet.YAXIS_DEFAULT = "PIXEL";
        NFNoteSet.MaxNoteSets = 20;
    }
}
