import java.awt.Event;
import java.awt.Color;
import java.util.Vector;
import java.awt.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class outline_list extends List
{
    Vector web_list;
    Sitemapper parent_applet;
    
    outline_list(final Vector web_list, final Sitemapper parent_applet) {
        this.setBackground(Color.white);
        this.web_list = web_list;
        this.parent_applet = parent_applet;
    }
    
    void draw_web_list() {
        if (this.countItems() == 0) {
            for (int i = 0; i < this.web_list.size(); ++i) {
                final tree_entry tree_entry = this.web_list.elementAt(i);
                if (tree_entry.publish) {
                    String s = "";
                    if (tree_entry.get_level() == 1) {
                        s += "-- ";
                    }
                    else {
                        for (int j = 0; j < tree_entry.get_level(); ++j) {
                            s += "    ";
                        }
                    }
                    this.addItem(s + tree_entry.get_name());
                }
            }
        }
        this.validate();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 701) {
            this.parent_applet.show_url(this.web_list.elementAt(this.getSelectedIndex()).get_url());
            return true;
        }
        return false;
    }
}
