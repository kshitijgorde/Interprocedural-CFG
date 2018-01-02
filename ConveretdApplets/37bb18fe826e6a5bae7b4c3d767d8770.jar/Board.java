import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

class Board
{
    HashMap blockList;
    boolean changed;
    
    public Board() {
        this.blockList = new HashMap();
        this.changed = true;
    }
    
    public boolean isChanged() {
        return this.changed;
    }
    
    public boolean validate() {
        return this.getNumBlocks() == 0 || (this.changed = false);
    }
    
    public Iterator getIterator() {
        return this.blockList.entrySet().iterator();
    }
    
    public int getNumBlocks() {
        return this.blockList.size();
    }
    
    public int getNumEmptyBlocks() {
        int n = 0;
        final Iterator<Map.Entry<K, Integer>> iterator = this.blockList.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() == 0) {
                ++n;
            }
        }
        return n;
    }
    
    public int getContents(final int n, final int n2, final int n3) {
        return this.getContents(new Coord(n, n2, n3));
    }
    
    public int getContents(final Coord coord) {
        final Integer value = this.blockList.get(coord);
        if (value == null) {
            return -1;
        }
        return value;
    }
    
    public void setContents(final int n, final int n2, final int n3, final int n4) {
        this.setContents(new Coord(n, n2, n3), n4);
    }
    
    public void setContents(final Coord coord) {
        this.setContents(coord, coord.ori);
    }
    
    public void setContents(final Coord coord, final int n) {
        final Coord coord2 = new Coord(coord);
        coord2.ori = 0;
        if (n == -1) {
            this.blockList.remove(coord2);
        }
        else {
            this.blockList.put(coord2, new Integer(n));
        }
    }
    
    public void remove(final int n) {
        for (final Map.Entry<K, Integer> entry : this.blockList.entrySet()) {
            if (entry.getValue() == n) {
                entry.setValue(new Integer(0));
            }
        }
    }
    
    public void clear() {
        final Iterator<Map.Entry<K, Integer>> iterator = this.blockList.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().setValue(new Integer(0));
        }
    }
    
    public void wipe() {
        this.blockList.clear();
        this.changed = true;
    }
    
    public ArrayList getBlockList() {
        final ArrayList<Coord> list = new ArrayList<Coord>();
        for (final Map.Entry<K, Integer> entry : this.blockList.entrySet()) {
            final Coord coord = new Coord(entry.getKey());
            coord.ori = entry.getValue();
            list.add(coord);
        }
        return list;
    }
    
    public Coord[] getBlockArray() {
        final Coord[] array = new Coord[this.getNumBlocks()];
        int n = 0;
        for (final Map.Entry<K, Integer> entry : this.blockList.entrySet()) {
            final Coord coord = new Coord(entry.getKey());
            coord.ori = entry.getValue();
            array[n++] = coord;
        }
        return array;
    }
    
    public void setBoard(final Board board) {
        this.blockList.clear();
        final Iterator iterator = board.getIterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, V> entry = iterator.next();
            this.blockList.put(entry.getKey(), entry.getValue());
        }
        this.changed = true;
    }
    
    public void setBoard(final Solution solution) {
        this.clear();
        for (int i = 0; i < solution.getNumPoly(); ++i) {
            final Polyomino poly = solution.getPoly(i);
            final Coord[] placement = solution.getPlacement(i);
            for (int j = 0; j < placement.length; ++j) {
                if (placement[j] != null) {
                    poly.place(this, placement[j], j);
                }
            }
        }
    }
    
    public void setBoard(final Coord[] array, final int n) {
        this.blockList.clear();
        this.changed = true;
        for (int i = 0; i < array.length; ++i) {
            this.setContents(array[i], n);
        }
    }
    
    public void setBoard(final ArrayList list) {
        this.blockList.clear();
        this.changed = true;
        for (int i = 0; i < list.size(); ++i) {
            this.setContents(list.get(i));
        }
    }
    
    public void getRange(final Coord coord, final Coord coord2) {
        final Iterator iterator = this.getIterator();
        if (!iterator.hasNext()) {
            return;
        }
        final Coord coord3 = iterator.next().getKey();
        coord.set(coord3);
        coord2.set(coord3);
        while (iterator.hasNext()) {
            final Coord coord4 = iterator.next().getKey();
            coord.min(coord4);
            coord2.max(coord4);
        }
    }
    
    public static Board parse(final Parser parser) throws IOException {
        parser.skipOpenBracket();
        final int number = parser.readNumber();
        final int number2 = parser.readNumber();
        int number3 = 1;
        final String string = parser.readString();
        parser.pushback();
        if (!string.equals(")") && !string.equals(";")) {
            number3 = parser.readNumber();
        }
        final Board board = new Board();
        for (int i = 0; i < number; ++i) {
            for (int j = 0; j < number2; ++j) {
                for (int k = 0; k < number3; ++k) {
                    board.setContents(i, j, k, 0);
                }
            }
        }
        while (true) {
            final String string2 = parser.readString();
            if (string2.equals(")")) {
                break;
            }
            if (!string2.equals(";")) {
                parser.pushback();
            }
            final int number4 = parser.readNumber();
            final int number5 = parser.readNumber();
            int number6 = 0;
            final String string3 = parser.readString();
            parser.pushback();
            if (!string3.equals(")") && !string3.equals(";")) {
                number6 = parser.readNumber();
            }
            board.setContents(number4, number5, number6, -1);
        }
        return board;
    }
    
    public String textRepr() {
        if (this.getNumBlocks() == 0) {
            return "";
        }
        final Coord coord = new Coord();
        final Coord coord2 = new Coord();
        this.getRange(coord, coord2);
        final StringBuffer sb = new StringBuffer();
        sb.append("Board (" + (coord2.x - coord.x + 1) + " " + (coord2.y - coord.y + 1) + ((coord2.z > coord.z) ? (" " + (coord2.z - coord.z + 1)) : ""));
        for (int i = coord.z; i <= coord2.z; ++i) {
            for (int j = coord.y; j <= coord2.y; ++j) {
                for (int k = coord.x; k <= coord2.x; ++k) {
                    if (this.getContents(k, j, i) == -1) {
                        sb.append(";\n       " + (k - coord.x) + " " + (j - coord.y) + ((coord2.z > coord.z) ? (" " + (i - coord.z)) : ""));
                    }
                }
            }
        }
        sb.append(")\n");
        return sb.toString();
    }
}
