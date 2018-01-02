import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class mines
{
    static final int MINE = 16;
    static final int SCOPERTA = 32;
    static final int MASK = 31;
    private int[] play_field;
    public Vector toShow;
    private int rows;
    private int cols;
    private int num_mines;
    
    public mines(final int rows, final int cols, final int num_mines) {
        this.toShow = new Vector();
        this.rows = rows;
        this.cols = cols;
        this.num_mines = num_mines;
        this.play_field = new int[rows * cols];
        this.FillPlayfield();
    }
    
    public int Rows() {
        return this.rows;
    }
    
    public int Cols() {
        return this.cols;
    }
    
    public int Mines() {
        return this.num_mines;
    }
    
    public int get_play_field(final int n, final int n2) {
        if (n < 0 || n > this.rows - 1 || n2 < 0 || n2 > this.cols - 1) {
            return 0;
        }
        return this.play_field[n * this.rows + n2];
    }
    
    public int get_play_field(final int n) {
        return this.play_field[n];
    }
    
    public void set_play_field(final int n, final int n2, final int n3) {
        this.play_field[n * this.rows + n2] = n3;
    }
    
    public void set_play_field(final int n, final int n2) {
        this.play_field[n] = n2;
    }
    
    private void FillPlayfield() {
        for (int i = 0; i < this.num_mines; ++i) {
            int n;
            do {
                n = (int)(Math.random() * this.rows * this.cols);
            } while (this.play_field[n] == 16);
            this.play_field[n] = 16;
        }
        int n2 = 0;
        for (int j = 0; j < this.rows; ++j) {
            for (int k = 0; k < this.cols; ++k) {
                if (this.get_play_field(j, k) != 16) {
                    try {
                        if (this.get_play_field(j - 1, k - 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {}
                    try {
                        if (this.get_play_field(j - 1, k) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {}
                    try {
                        if (this.get_play_field(j - 1, k + 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {}
                    try {
                        if (this.get_play_field(j, k - 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex4) {}
                    try {
                        if (this.get_play_field(j, k + 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex5) {}
                    try {
                        if (this.get_play_field(j + 1, k - 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex6) {}
                    try {
                        if (this.get_play_field(j + 1, k) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex7) {}
                    try {
                        if (this.get_play_field(j + 1, k + 1) == 16) {
                            ++n2;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex8) {}
                    this.set_play_field(j, k, n2);
                    n2 = 0;
                }
            }
        }
    }
    
    public void Show(final int n, final int n2) {
        if (n >= 0 && n < this.rows && n2 >= 0 && n2 < this.cols && (this.get_play_field(n, n2) & 0x20) != 0x20) {
            if (this.get_play_field(n, n2) == 0) {
                this.toShow.addElement(new Integer(n * this.rows + n2));
                this.set_play_field(n, n2, this.get_play_field(n, n2) | 0x20);
                this.Show(n - 1, n2 - 1);
                this.Show(n - 1, n2);
                this.Show(n - 1, n2 + 1);
                this.Show(n, n2 - 1);
                this.Show(n, n2 + 1);
                this.Show(n + 1, n2 - 1);
                this.Show(n + 1, n2);
                this.Show(n + 1, n2 + 1);
                return;
            }
            this.toShow.addElement(new Integer(n * this.rows + n2));
            this.set_play_field(n, n2, this.get_play_field(n, n2) | 0x20);
        }
    }
}
