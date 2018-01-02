import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

class Coord
{
    public int x;
    public int y;
    public int z;
    public int ori;
    
    public Coord() {
        final boolean b = false;
        this.ori = (b ? 1 : 0);
        this.z = (b ? 1 : 0);
        this.y = (b ? 1 : 0);
        this.x = (b ? 1 : 0);
    }
    
    public Coord(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ori = 0;
    }
    
    public Coord(final int x, final int y, final int z, final int ori) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ori = ori;
    }
    
    public Coord(final Coord coord) {
        this.x = coord.x;
        this.y = coord.y;
        this.z = coord.z;
        this.ori = coord.ori;
    }
    
    public void add(final Coord coord) {
        this.x += coord.x;
        this.y += coord.y;
        this.z += coord.z;
    }
    
    public void sub(final Coord coord) {
        this.x -= coord.x;
        this.y -= coord.y;
        this.z -= coord.z;
    }
    
    public boolean min(final Coord coord) {
        boolean b = false;
        if (coord.x < this.x) {
            this.x = coord.x;
            b = true;
        }
        if (coord.y < this.y) {
            this.y = coord.y;
            b = true;
        }
        if (coord.z < this.z) {
            this.z = coord.z;
            b = true;
        }
        return b;
    }
    
    public boolean max(final Coord coord) {
        boolean b = false;
        if (coord.x > this.x) {
            this.x = coord.x;
            b = true;
        }
        if (coord.y > this.y) {
            this.y = coord.y;
            b = true;
        }
        if (coord.z > this.z) {
            this.z = coord.z;
            b = true;
        }
        return b;
    }
    
    public void set() {
        final boolean b = false;
        this.ori = (b ? 1 : 0);
        this.z = (b ? 1 : 0);
        this.y = (b ? 1 : 0);
        this.x = (b ? 1 : 0);
    }
    
    public void set(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ori = 0;
    }
    
    public void set(final Coord coord) {
        this.x = coord.x;
        this.y = coord.y;
        this.z = coord.z;
        this.ori = coord.ori;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("(").append(this.x).append(",").append(this.y);
        if (this.z != 0 || this.ori != 0) {
            sb.append(",").append(this.z);
        }
        if (this.ori != 0) {
            sb.append(";").append(this.ori);
        }
        sb.append(")");
        return sb.toString();
    }
    
    public static Coord parse(final Parser parser) throws IOException {
        final Coord coord = new Coord();
        if (!parser.readString().equals("(")) {
            throw new IOException("Expected '(' at start of coordinate.");
        }
        coord.x = parser.readNumber();
        coord.y = parser.readNumber();
        String s = parser.readString();
        if (!s.equals(")")) {
            parser.pushback();
            coord.z = parser.readNumber();
            s = parser.readString();
            if (s.equals(";")) {
                coord.ori = parser.readNumber();
                s = parser.readString();
            }
        }
        if (!s.equals(")")) {
            throw new IOException("Expected ')' at end of coordinate.");
        }
        return coord;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof Coord) {
            final Coord coord = (Coord)o;
            return this.x == coord.x && this.y == coord.y && this.z == coord.z;
        }
        return false;
    }
    
    public int hashCode() {
        return 10000 * this.z + 100 * this.y + this.x;
    }
}
