import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Comparator;
import javax.swing.Icon;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class GridType implements Icon, Comparator
{
    static final Class[] types;
    public static final String[] names;
    protected int numRotate;
    protected int numReflect;
    protected int numDim;
    protected int numTypes;
    int height;
    int width;
    protected static final int screenBorder = 5;
    static /* synthetic */ Class class$GridTypeSquare;
    static /* synthetic */ Class class$GridTypeRect;
    static /* synthetic */ Class class$GridTypeDiamond;
    static /* synthetic */ Class class$GridTypeTan;
    static /* synthetic */ Class class$GridTypeOctagon;
    static /* synthetic */ Class class$GridTypePentagon;
    static /* synthetic */ Class class$GridTypePentagonHalf;
    static /* synthetic */ Class class$GridTypeTriangle;
    static /* synthetic */ Class class$GridTypeHexagon;
    static /* synthetic */ Class class$GridTypeCubeTile;
    static /* synthetic */ Class class$GridTypeDrafter;
    static /* synthetic */ Class class$GridTypeCube;
    
    public int getNumRotate() {
        return this.numRotate;
    }
    
    public int getNumReflect() {
        return this.numReflect;
    }
    
    public int numDim() {
        return this.numDim;
    }
    
    public static int getNumTypes() {
        return GridType.types.length;
    }
    
    public String toString() {
        for (int i = 0; i < GridType.types.length; ++i) {
            if (this.getClass() == GridType.types[i]) {
                return GridType.names[i];
            }
        }
        return "";
    }
    
    public static GridType factory(final Class clazz) {
        for (int i = 0; i < GridType.types.length; ++i) {
            if (GridType.types[i] == clazz) {
                return factory(i);
            }
        }
        return null;
    }
    
    public static GridType factory(final String s) {
        for (int i = 0; i < GridType.types.length; ++i) {
            if (GridType.names[i].equalsIgnoreCase(s)) {
                return factory(i);
            }
        }
        return null;
    }
    
    public static GridType factory(final int n) {
        GridType gridType = null;
        try {
            gridType = GridType.types[n].newInstance();
        }
        catch (Exception ex) {}
        return gridType;
    }
    
    public void setSize(final int width, final int height) {
        this.height = height;
        this.width = width;
    }
    
    public int getIconHeight() {
        return this.height;
    }
    
    public int getIconWidth() {
        return this.width;
    }
    
    public abstract void paintIcon(final Component p0, final Graphics p1, final int p2, final int p3);
    
    public abstract void reset();
    
    public abstract void paintComponent(final Component p0, final Graphics p1, final Board p2, final Color[] p3, final boolean p4);
    
    public abstract void paintOutline(final Graphics p0, final Coord p1, final Coord p2);
    
    protected abstract void paintTileOutline(final Graphics p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public abstract void paintCentredOutline(final Graphics p0, final Coord[] p1, final int p2, final int p3, final int p4);
    
    protected void paintOutline(final Graphics graphics, final Coord[] array, final int n, final int n2, final int n3) {
        if (this.numDim < 3) {
            for (int i = 0; i < array.length; ++i) {
                this.paintTileOutline(graphics, array[i].x - array[n3].x, array[i].y - array[n3].y, array[i].z, n, n2);
            }
        }
        else {
            for (int j = 0; j < array.length; ++j) {
                this.paintTileOutline(graphics, array[j].x - array[n3].x, array[j].y - array[n3].y, array[j].z - array[n3].z, n, n2);
            }
        }
    }
    
    public abstract boolean screen2Grid(final Coord p0, final int p1, final int p2);
    
    public Color getBlockColour(final int n, final Component component, final Color[] array, final boolean b) {
        if (n == 0 && !b) {
            return component.getBackground();
        }
        if (array == null) {
            return component.getForeground();
        }
        return array[n - 1];
    }
    
    public abstract Coord getAdjacent(final Coord p0, final int p1);
    
    public void getFirstBlock(final Coord coord, final Coord coord2, final Coord coord3) {
        coord.x = ((coord2.x < coord3.x) ? coord2.x : coord3.x);
        coord.y = ((coord2.y < coord3.y) ? coord2.y : coord3.y);
        if (this.numDim < 3) {
            coord.z = (coord2.equals(coord3) ? coord2.z : 0);
        }
        else {
            coord.z = ((coord2.z < coord3.z) ? coord2.z : coord3.z);
        }
    }
    
    public boolean getNextBlock(final Coord coord, final Coord coord2, final Coord coord3) {
        if (coord2.equals(coord3)) {
            return false;
        }
        if (this.numDim < 3) {
            ++coord.z;
            if (coord.z < this.numTypes) {
                return true;
            }
            coord.z = 0;
        }
        if (coord.x < coord3.x || coord.x < coord2.x) {
            ++coord.x;
            return true;
        }
        coord.x = ((coord2.x < coord3.x) ? coord2.x : coord3.x);
        if (coord.y < coord3.y || coord.y < coord2.y) {
            ++coord.y;
            return true;
        }
        coord.y = ((coord2.y < coord3.y) ? coord2.y : coord3.y);
        if (this.numDim == 3) {
            if (coord.z < coord3.z || coord.z < coord2.z) {
                ++coord.z;
                return true;
            }
            coord.z = ((coord2.z < coord3.z) ? coord2.z : coord3.z);
        }
        return false;
    }
    
    public void getTranslationRange(final Board board, final Coord coord, final Coord coord2) {
        board.getRange(coord, coord2);
        if (this.numDim < 3) {
            final boolean b = false;
            coord2.z = (b ? 1 : 0);
            coord.z = (b ? 1 : 0);
        }
    }
    
    public boolean getNextTranslation(final Coord coord, final Coord coord2, final Coord coord3) {
        ++coord.x;
        if (coord.x > coord3.x) {
            coord.x = coord2.x;
            ++coord.y;
            if (coord.y > coord3.y) {
                coord.y = coord2.y;
                if (this.numDim < 3) {
                    return false;
                }
                ++coord.z;
                if (coord.z > coord3.z) {
                    coord.z = coord2.z;
                    return false;
                }
            }
        }
        return true;
    }
    
    public int compare(final Object o, final Object o2) {
        final Coord coord = (Coord)o;
        final Coord coord2 = (Coord)o2;
        if (this.numDim < 3) {
            if (coord.z < coord2.z) {
                return -1;
            }
            if (coord.z > coord2.z) {
                return 1;
            }
        }
        if (coord.x < coord2.x) {
            return -1;
        }
        if (coord.x > coord2.x) {
            return 1;
        }
        if (coord.y < coord2.y) {
            return -1;
        }
        if (coord.y > coord2.y) {
            return 1;
        }
        if (this.numDim == 3) {
            if (coord.z < coord2.z) {
                return -1;
            }
            if (coord.z > coord2.z) {
                return 1;
            }
        }
        return 0;
    }
    
    public void normalise(final Coord[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        final Coord coord = new Coord(array[0]);
        for (int i = 0; i < array.length; ++i) {
            coord.min(array[i]);
        }
        if (this.numDim < 3) {
            coord.z = 0;
        }
        for (int j = 0; j < array.length; ++j) {
            array[j].sub(coord);
        }
        Arrays.sort(array, this);
    }
    
    public abstract void getRotate(final Coord p0, final int p1);
    
    public void getRotate(final Coord[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            this.getRotate(array[i], n);
        }
        this.normalise(array);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        types = new Class[] { (GridType.class$GridTypeSquare == null) ? (GridType.class$GridTypeSquare = class$("GridTypeSquare")) : GridType.class$GridTypeSquare, (GridType.class$GridTypeRect == null) ? (GridType.class$GridTypeRect = class$("GridTypeRect")) : GridType.class$GridTypeRect, (GridType.class$GridTypeDiamond == null) ? (GridType.class$GridTypeDiamond = class$("GridTypeDiamond")) : GridType.class$GridTypeDiamond, (GridType.class$GridTypeTan == null) ? (GridType.class$GridTypeTan = class$("GridTypeTan")) : GridType.class$GridTypeTan, (GridType.class$GridTypeOctagon == null) ? (GridType.class$GridTypeOctagon = class$("GridTypeOctagon")) : GridType.class$GridTypeOctagon, (GridType.class$GridTypePentagon == null) ? (GridType.class$GridTypePentagon = class$("GridTypePentagon")) : GridType.class$GridTypePentagon, (GridType.class$GridTypePentagonHalf == null) ? (GridType.class$GridTypePentagonHalf = class$("GridTypePentagonHalf")) : GridType.class$GridTypePentagonHalf, (GridType.class$GridTypeTriangle == null) ? (GridType.class$GridTypeTriangle = class$("GridTypeTriangle")) : GridType.class$GridTypeTriangle, (GridType.class$GridTypeHexagon == null) ? (GridType.class$GridTypeHexagon = class$("GridTypeHexagon")) : GridType.class$GridTypeHexagon, (GridType.class$GridTypeCubeTile == null) ? (GridType.class$GridTypeCubeTile = class$("GridTypeCubeTile")) : GridType.class$GridTypeCubeTile, (GridType.class$GridTypeDrafter == null) ? (GridType.class$GridTypeDrafter = class$("GridTypeDrafter")) : GridType.class$GridTypeDrafter, (GridType.class$GridTypeCube == null) ? (GridType.class$GridTypeCube = class$("GridTypeCube")) : GridType.class$GridTypeCube };
        names = new String[] { "Square", "Rectangle", "Diamond", "Tan", "Octagon", "Pentagon", "Pentagonhalf", "Triangle", "Hexagon", "Cubetiling", "Drafter", "Cube" };
    }
}
