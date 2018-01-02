import java.util.ResourceBundle;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class tree_grid_builder
{
    public Vector web_tree;
    public int max_level;
    public int my_max_width;
    public tree_entry[][] tree_grid;
    public static final int MAP_WEB_TREE = 0;
    public static final int TRANSFER_TO_GRID = 1;
    private int[] row_position;
    private String STRING_Recursing_error;
    
    tree_grid_builder(final Vector web_tree, final int max_level) {
        this.my_max_width = 0;
        this.STRING_Recursing_error = new String(ResourceBundle.getBundle("SiteMapper").getString("Recursing_error"));
        this.web_tree = web_tree;
        this.max_level = max_level;
        this.init_row_position();
        this.recurse_tree(web_tree, 0);
        this.tree_grid = new tree_entry[this.find_max_width() + 2][max_level + 2];
        this.recurse_tree(web_tree, 1);
    }
    
    public int find_max_width() {
        int my_max_width = 0;
        for (int i = 0; i < this.row_position.length; ++i) {
            if (this.row_position[i] > my_max_width) {
                my_max_width = this.row_position[i];
            }
        }
        return this.my_max_width = my_max_width;
    }
    
    private void recurse_tree(final Vector vector, final int n) {
        for (int i = 0; i < vector.size(); ++i) {
            final tree_entry tree_entry = vector.elementAt(i);
            if (tree_entry.in_use) {
                switch (n) {
                    case 0: {
                        this.map_element(tree_entry);
                        if (tree_entry.children != null) {
                            this.recurse_tree(tree_entry.children, n);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (tree_entry.children != null) {
                            this.recurse_tree(tree_entry.children, n);
                        }
                        this.tree_grid[this.find_child_median(tree_entry)][tree_entry.grid_y] = tree_entry;
                        break;
                    }
                    default: {
                        System.out.println(this.STRING_Recursing_error);
                        return;
                    }
                }
            }
        }
    }
    
    private void init_row_position() {
        this.row_position = new int[this.max_level + 1];
        for (int i = 0; i < this.max_level + 1; ++i) {
            this.row_position[i] = -1;
        }
    }
    
    private void print_row_position() {
        for (int i = 0; i < this.row_position.length; ++i) {
            System.out.println("level " + i + ": " + this.row_position[i]);
        }
    }
    
    private int find_above(final int n) {
        if (n > 0) {
            return this.row_position[n - 1];
        }
        return -1;
    }
    
    private int find_below(final int n) {
        int n2 = -1;
        for (int i = n + 1; i < this.row_position.length; ++i) {
            if (this.row_position[i] > n2) {
                n2 = this.row_position[i];
            }
        }
        return n2 + 1;
    }
    
    private void map_element(final tree_entry tree_entry) {
        final int get_level = tree_entry.get_level();
        final int[] row_position = this.row_position;
        final int n = get_level;
        ++row_position[n];
        this.row_position[get_level] = Math.max(this.find_above(get_level), Math.max(this.row_position[get_level], this.find_below(get_level)));
        tree_entry.grid_x = this.row_position[get_level];
        tree_entry.grid_y = get_level;
    }
    
    private int find_child_median(final tree_entry tree_entry) {
        if (tree_entry.children != null && tree_entry.children.firstElement().in_use) {
            final tree_entry tree_entry2 = tree_entry.children.firstElement();
            tree_entry.grid_x = tree_entry2.grid_x + (tree_entry.children.lastElement().grid_x - tree_entry2.grid_x) / 2;
        }
        return tree_entry.grid_x;
    }
    
    public int find_grid_width(final int n) {
        int n2 = 0;
        for (int i = 0; i < n + 1; ++i) {
            for (int j = 0; j < this.my_max_width + 1; ++j) {
                if (this.tree_grid[j][i] != null && j > n2) {
                    n2 = j;
                }
            }
        }
        return n2;
    }
    
    private void clear_tree_grid() {
        for (int i = 0; i < this.max_level + 1; ++i) {
            for (int j = 0; j < this.my_max_width + 1; ++j) {
                this.tree_grid[j][i] = null;
            }
        }
    }
    
    public void remap_grid(final int max_level) {
        this.max_level = max_level;
        this.clear_tree_grid();
        this.init_row_position();
        this.recurse_tree(this.web_tree, 0);
        this.recurse_tree(this.web_tree, 1);
    }
}
