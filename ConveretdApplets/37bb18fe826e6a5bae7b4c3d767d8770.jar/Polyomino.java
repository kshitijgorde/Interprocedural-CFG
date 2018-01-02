import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

class Polyomino
{
    public static final int NONE = 0;
    public static final int ROTATE = 1;
    public static final int FLIP = 2;
    private int id;
    private int type;
    private ArrayList userOrients;
    private ArrayList orients;
    private int number;
    private Coord[] placements;
    private int used;
    private boolean changed;
    
    Polyomino() {
        this.type = 1;
        this.userOrients = new ArrayList();
        this.number = 0;
        this.used = 0;
        this.changed = true;
    }
    
    public boolean isChanged() {
        return this.changed;
    }
    
    public boolean validate(final GridType gridType) {
        this.initialiseOrients(gridType);
        return this.changed = false;
    }
    
    public void reset() {
        this.used = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.changed = (this.id != id);
        this.id = id;
    }
    
    public void setNumber(final int number) {
        if (this.number != number) {
            this.number = number;
            this.placements = new Coord[number];
            for (int i = 0; i < number; ++i) {
                this.placements[i] = new Coord();
            }
            this.changed = true;
        }
    }
    
    public Coord[] getPlacements() {
        synchronized (this.placements) {
            final Coord[] array = new Coord[this.used];
            for (int i = 0; i < this.used; ++i) {
                array[i] = new Coord(this.placements[i]);
            }
            return array;
        }
    }
    
    public Coord getPlacement(final int n) {
        return new Coord(this.placements[n]);
    }
    
    public void setType(final int type) {
        if (this.type != type) {
            this.type = type;
            this.orients = null;
            this.changed = true;
        }
    }
    
    public void addUserOrient(final Coord[] array, final GridType gridType) {
        if (array.length == 0) {
            return;
        }
        gridType.normalise(array);
        for (int i = 0; i < array.length; ++i) {
            array[i].ori = i;
        }
        this.userOrients.add(array);
        this.orients = null;
        this.changed = true;
    }
    
    public void setUserOrient(final int n, final Coord[] array, final GridType gridType) {
        if (array.length == 0) {
            return;
        }
        gridType.normalise(array);
        this.userOrients.set(n, array);
        this.orients = null;
        this.changed = true;
    }
    
    public void delUserOrient(final int n) {
        this.userOrients.remove(n);
        this.orients = null;
        this.changed = true;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getAvailable() {
        return this.number - this.used;
    }
    
    public boolean isUsed() {
        return this.used == this.number;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getNumUserOrient() {
        return this.userOrients.size();
    }
    
    public Coord[] getUserOrient(final int n) {
        return this.userOrients.get(n);
    }
    
    public int getNumOrient() {
        return this.orients.size();
    }
    
    public Coord[] getOrient(final int n) {
        return this.orients.get(n);
    }
    
    public int getNumBlocks(final int n) {
        return this.getOrient(n).length;
    }
    
    public int getMinNumBlocks() {
        int length = 0;
        for (int i = 0; i < this.getNumUserOrient(); ++i) {
            final Coord[] userOrient = this.getUserOrient(i);
            if (i == 0 || length > userOrient.length) {
                length = userOrient.length;
            }
        }
        return length * (this.number - this.used);
    }
    
    public int getMaxNumBlocks() {
        int length = 0;
        for (int i = 0; i < this.getNumUserOrient(); ++i) {
            final Coord[] userOrient = this.getUserOrient(i);
            if (i == 0 || length < userOrient.length) {
                length = userOrient.length;
            }
        }
        return length * (this.number - this.used);
    }
    
    public Coord getCoord(final int n, final Coord coord) {
        final Coord coord2 = new Coord(this.getOrient(coord.ori)[n]);
        coord2.add(coord);
        return coord2;
    }
    
    public int findOrient(final Coord[] array) {
        for (int i = 0; i < this.getNumOrient(); ++i) {
            if (Arrays.equals(array, this.getOrient(i))) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean sameShape(final Polyomino polyomino) {
        if (polyomino == null) {
            return false;
        }
        for (int i = 0; i < this.getNumOrient(); ++i) {
            if (polyomino.findOrient(this.getOrient(i)) < 0) {
                return false;
            }
        }
        return true;
    }
    
    private void initialiseOrients(final GridType gridType) {
        this.orients = new ArrayList();
        for (int i = 0; i < this.userOrients.size(); ++i) {
            final Coord[] array = this.userOrients.get(i);
            if (this.type == 0) {
                gridType.normalise(array);
                this.addOrient(array);
            }
            else {
                final Coord[] array2 = new Coord[array.length];
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = new Coord();
                }
                for (int n = (this.type == 1) ? gridType.getNumRotate() : gridType.getNumReflect(), k = 0; k < n; ++k) {
                    for (int l = 0; l < array2.length; ++l) {
                        array2[l].set(array[l]);
                    }
                    gridType.getRotate(array2, k);
                    this.addOrient(array2);
                }
            }
        }
    }
    
    private void addOrient(final Coord[] array) {
        for (int i = 0; i < this.orients.size(); ++i) {
            if (Arrays.equals(this.getOrient(i), array)) {
                return;
            }
        }
        final Coord[] array2 = new Coord[array.length];
        for (int j = 0; j < array.length; ++j) {
            array2[j] = new Coord(array[j]);
        }
        this.orients.add(array2);
    }
    
    public static Polyomino parse(final Parser parser, final GridType gridType) throws IOException {
        final Polyomino polyomino = new Polyomino();
        int type = 1;
        final String string = parser.readString();
        if (string.equalsIgnoreCase("flip")) {
            type = 2;
        }
        else if (string.equalsIgnoreCase("fixed")) {
            type = 0;
        }
        else {
            parser.pushback();
        }
        polyomino.setType(type);
        int number = 1;
        if (!parser.readString().equals("(")) {
            parser.pushback();
            number = parser.readNumber();
            parser.skipOpenBracket();
        }
        polyomino.setNumber(number);
        final ArrayList list = new ArrayList<Coord>();
        while (true) {
            final String string2 = parser.readString();
            if (!string2.equals(")") && !string2.equals(";")) {
                parser.pushback();
                list.add(Coord.parse(parser));
            }
            else {
                if (list.size() > 0) {
                    final Coord[] array = new Coord[list.size()];
                    for (int i = 0; i < list.size(); ++i) {
                        array[i] = list.get(i);
                    }
                    polyomino.addUserOrient(array, gridType);
                    list.clear();
                }
                if (!string2.equals(";")) {
                    break;
                }
                continue;
            }
        }
        return polyomino;
    }
    
    public String textRepr() {
        final StringBuffer sb = new StringBuffer();
        sb.append("tile ");
        if (this.type == 1) {
            sb.append("");
        }
        else if (this.type == 2) {
            sb.append("flip");
        }
        else if (this.type == 0) {
            sb.append("fixed");
        }
        if (this.getNumber() > 1) {
            sb.append(" " + this.getNumber());
        }
        sb.append("(");
        for (int i = 0; i < this.getNumUserOrient(); ++i) {
            if (i != 0) {
                sb.append(";");
            }
            final Coord[] userOrient = this.getUserOrient(i);
            for (int j = 0; j < userOrient.length; ++j) {
                if (j != 0) {
                    sb.append(",");
                }
                sb.append(userOrient[j]);
            }
        }
        sb.append(")\n");
        return sb.toString();
    }
    
    public boolean canPlace(final Board board, final Coord coord) {
        final Coord[] orient = this.getOrient(coord.ori);
        final Coord coord2 = new Coord();
        for (int i = 0; i < orient.length; ++i) {
            coord2.set(orient[i]);
            coord2.add(coord);
            coord2.ori = 0;
            if (board.getContents(coord2) != 0) {
                return false;
            }
        }
        return true;
    }
    
    public void placeS(final Coord coord) {
        synchronized (this.placements) {
            this.placements[this.used].set(coord);
        }
        ++this.used;
    }
    
    public void place(final Board board, final Coord coord) {
        this.place(board, coord, this.used);
    }
    
    public void place(final Board board, final Coord coord, final int n) {
        final Coord[] orient = this.getOrient(coord.ori);
        final Coord coord2 = new Coord();
        for (int i = 0; i < orient.length; ++i) {
            coord2.set(orient[i]);
            coord2.add(coord);
            board.setContents(coord2, this.id + n);
        }
    }
    
    public void removeS() {
        --this.used;
    }
    
    public void remove(final Board board, final Coord coord) {
        final Coord[] orient = this.getOrient(coord.ori);
        final Coord coord2 = new Coord();
        for (int i = 0; i < orient.length; ++i) {
            coord2.set(orient[i]);
            coord2.add(coord);
            board.setContents(coord2, 0);
        }
    }
    
    public void remove(final Board board, final int n) {
        final int used = this.used;
        for (int i = used - 1; i >= n; --i) {
            this.remove(board, this.placements[i]);
            this.removeS();
        }
        for (int j = n; j < used - 1; ++j) {
            this.place(board, this.placements[j + 1]);
            this.placeS(this.placements[j + 1]);
        }
    }
}
