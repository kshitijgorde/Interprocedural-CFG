import java.awt.Event;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ResourceBundle;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class check_box_panel extends Panel
{
    Sitemapper parent_applet;
    CheckboxGroup cbg;
    Checkbox level_two;
    Checkbox level_three;
    Checkbox level_four;
    Checkbox level_all;
    private String STRING_Show_up_to_Level;
    private String STRING_All_Levels;
    
    check_box_panel(final Sitemapper parent_applet) {
        this.STRING_Show_up_to_Level = new String(ResourceBundle.getBundle("SiteMapper").getString("Show_up_to_Level"));
        this.STRING_All_Levels = new String(ResourceBundle.getBundle("SiteMapper").getString("All_Levels"));
        this.setBackground(Color.lightGray);
        this.parent_applet = parent_applet;
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        this.add("West", panel);
        panel.setLayout(new FlowLayout());
        this.cbg = new CheckboxGroup();
        if (parent_applet.wsb.max_level + 1 > 2) {
            panel.add(new Label(this.STRING_Show_up_to_Level));
            panel.add(this.level_two = new Checkbox("2", this.cbg, false));
            if (parent_applet.wsb.max_level + 1 <= 3) {
                panel.add(this.level_all = new Checkbox(this.STRING_All_Levels, this.cbg, true));
                return;
            }
            panel.add(this.level_three = new Checkbox("3", this.cbg, false));
            if (parent_applet.wsb.max_level + 1 <= 4) {
                panel.add(this.level_all = new Checkbox(this.STRING_All_Levels, this.cbg, true));
                return;
            }
            panel.add(this.level_four = new Checkbox("4", this.cbg, false));
            panel.add(this.level_all = new Checkbox(this.STRING_All_Levels, this.cbg, true));
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Checkbox) {
            this.parent_applet.wsb.web_list.removeAllElements();
            int max_level = -1;
            if (event.target.equals(this.level_two)) {
                max_level = 1;
            }
            else if (event.target.equals(this.level_three)) {
                max_level = 2;
            }
            else if (event.target.equals(this.level_four)) {
                max_level = 3;
            }
            else if (event.target.equals(this.level_all)) {
                max_level = this.parent_applet.wsb.max_level;
            }
            this.parent_applet.wsb.restrict_tree(this.parent_applet.wsb.web_tree, max_level);
            this.parent_applet.main_canvas.max_level = max_level;
            this.parent_applet.main_canvas.my_max_width = this.parent_applet.tgb.find_grid_width(max_level);
            this.parent_applet.tgb.remap_grid(max_level);
            this.parent_applet.main_canvas.my_max_width = this.parent_applet.tgb.find_grid_width(max_level);
            this.parent_applet.main_canvas.repaint();
            this.parent_applet.main_list_box.delItems(0, this.parent_applet.main_list_box.countItems() - 1);
            this.parent_applet.main_list_box.draw_web_list();
            return true;
        }
        return false;
    }
}
