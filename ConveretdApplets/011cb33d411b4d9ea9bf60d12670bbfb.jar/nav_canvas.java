import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class nav_canvas extends Canvas
{
    tree_entry[][] tree_grid;
    int max_level;
    int my_max_width;
    int x_factor;
    int y_factor;
    Sitemapper parent_applet;
    String current_selection;
    boolean repeat_current;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Dimension offScreenSize;
    
    nav_canvas(final tree_entry[][] tree_grid, final int max_level, final int my_max_width, final Sitemapper parent_applet) {
        this.current_selection = null;
        this.repeat_current = false;
        this.tree_grid = tree_grid;
        this.max_level = max_level;
        this.my_max_width = my_max_width;
        this.parent_applet = parent_applet;
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offScreenImage == null || size.width != this.offScreenSize.width || size.height != this.offScreenSize.height) {
            this.offScreenImage = this.createImage(size.width, size.height);
            this.offScreenSize = size;
            (this.offScreenGraphics = this.offScreenImage.getGraphics()).setFont(this.getFont());
        }
        this.offScreenGraphics.fillRect(0, 0, size.width, size.height);
        this.paint(this.offScreenGraphics);
        graphics.drawImage(this.offScreenImage, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.black);
        this.x_factor = size.width / (this.my_max_width + 1);
        this.y_factor = size.height / (this.max_level + 1);
        for (int i = 0; i < this.my_max_width + 1; ++i) {
            for (int j = 0; j < this.max_level + 1; ++j) {
                if (this.tree_grid[i][j] != null && this.tree_grid[i][j].in_use) {
                    this.draw_node(graphics, i, j, size, this.tree_grid[i][j].publish);
                    this.draw_branches(this.tree_grid[i][j], size, graphics);
                }
            }
        }
    }
    
    void draw_node(final Graphics graphics, final int n, final int n2, final Dimension dimension, final boolean b) {
        if (b) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.fillRect(n * this.x_factor, n2 * this.y_factor, this.x_factor / 2, this.y_factor / 2);
        graphics.setColor(Color.black);
        graphics.drawRect(n * this.x_factor, n2 * this.y_factor, this.x_factor / 2, this.y_factor / 2);
    }
    
    void draw_branches(final tree_entry tree_entry, final Dimension dimension, final Graphics graphics) {
        if (tree_entry.children != null) {
            final tree_entry tree_entry2 = tree_entry.children.firstElement();
            if (!tree_entry2.in_use) {
                return;
            }
            final tree_entry tree_entry3 = tree_entry.children.lastElement();
            graphics.drawLine(tree_entry2.grid_x * this.x_factor + this.x_factor / 4, tree_entry2.grid_y * this.y_factor - this.y_factor / 2 + 5, tree_entry3.grid_x * this.x_factor + this.x_factor / 4, tree_entry3.grid_y * this.y_factor - this.y_factor / 2 + 5);
            graphics.drawLine(tree_entry.grid_x * this.x_factor + this.x_factor / 4, tree_entry.grid_y * this.y_factor + this.y_factor / 2, tree_entry.grid_x * this.x_factor + this.x_factor / 4, tree_entry.grid_y * this.y_factor + (this.y_factor / 2 + 5));
            for (int i = 0; i < tree_entry.children.size(); ++i) {
                final tree_entry tree_entry4 = tree_entry.children.elementAt(i);
                graphics.drawLine(tree_entry4.grid_x * this.x_factor + this.x_factor / 4, tree_entry4.grid_y * this.y_factor - this.y_factor / 2 + 5, tree_entry4.grid_x * this.x_factor + this.x_factor / 4, tree_entry4.grid_y * this.y_factor);
            }
        }
    }
    
    tree_entry find_location(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final Dimension size = this.size();
        this.x_factor = size.width / (this.my_max_width + 1);
        this.y_factor = size.height / (this.max_level + 1);
        for (int i = 0; i < this.my_max_width + 2; ++i) {
            if (n < i * this.x_factor) {
                n3 = i - 1;
                break;
            }
        }
        for (int j = 0; j < this.max_level + 2; ++j) {
            if (n2 < j * this.y_factor) {
                n4 = j - 1;
                break;
            }
        }
        if (this.tree_grid[n3][n4] != null && this.tree_grid[n3][n4].publish) {
            final String get_url = this.tree_grid[n3][n4].get_url();
            if (get_url.equals(this.current_selection)) {
                this.repeat_current = true;
            }
            else {
                this.repeat_current = false;
            }
            this.current_selection = get_url;
            return this.tree_grid[n3][n4];
        }
        this.current_selection = null;
        return null;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final tree_entry find_location = this.find_location(n, n2);
        final Graphics graphics = this.getGraphics();
        if (find_location == null || !find_location.publish) {
            this.update(graphics);
            return false;
        }
        if (!this.repeat_current) {
            this.update(graphics);
            graphics.setColor(Color.red);
            graphics.fillRect(find_location.grid_x * this.x_factor - 1, find_location.grid_y * this.y_factor - 1, this.x_factor / 2 + 2, this.y_factor / 2 + 2);
            graphics.setColor(Color.black);
            final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
            final int stringWidth = fontMetrics.stringWidth(find_location.get_name());
            final Dimension size = this.size();
            int n3 = 0;
            int n4 = 0;
            if (stringWidth + find_location.grid_x * this.x_factor + this.x_factor / 2 + 2 > size.width) {
                n3 = stringWidth + find_location.grid_x * this.x_factor + this.x_factor / 2 + 2 - size.width;
            }
            if (find_location.grid_y * this.y_factor - fontMetrics.getHeight() - 2 < 0) {
                n4 = 0 - (find_location.grid_y * this.y_factor - fontMetrics.getHeight() - 2);
            }
            graphics.setColor(Color.white);
            graphics.fillRect(find_location.grid_x * this.x_factor + this.x_factor / 2 + 2 - n3, find_location.grid_y * this.y_factor - fontMetrics.getHeight() - 2 + n4, stringWidth, fontMetrics.getHeight());
            graphics.setColor(Color.black);
            graphics.drawString(find_location.get_name(), find_location.grid_x * this.x_factor + this.x_factor / 2 + 2 - n3, find_location.grid_y * this.y_factor - 2 + n4);
            return true;
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        System.out.println("current_selection: " + this.current_selection);
        if (this.current_selection != null) {
            this.parent_applet.show_url(this.current_selection);
        }
        return false;
    }
}
