import java.awt.Event;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Button;
import java.awt.List;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class search_panel extends Panel
{
    List search_list_box;
    Button search_button;
    TextField search_field;
    Vector web_list;
    Vector current_list;
    Sitemapper parent_applet;
    Vector temp_list;
    private String STRING_Search_Now;
    
    search_panel(final Vector vector, final Sitemapper parent_applet) {
        final String s = new String(ResourceBundle.getBundle("SiteMapper").getString("Search_Now"));
        this.parent_applet = parent_applet;
        this.web_list = (Vector)vector.clone();
        this.current_list = new Vector();
        this.setLayout(new BorderLayout());
        this.add("Center", this.search_list_box = new List());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        final Panel panel2 = new Panel();
        panel.add("West", panel2);
        panel2.setLayout(new FlowLayout());
        panel.setBackground(Color.lightGray);
        panel2.add(this.search_field = new TextField(35));
        panel2.add(this.search_button = new Button(s));
        this.add("South", panel);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                this.parent_applet.show_url(this.current_list.elementAt(this.search_list_box.getSelectedIndex()).get_url());
                return true;
            }
            case 1001: {
                if (event.target instanceof Button) {
                    this.search_for_text();
                    return true;
                }
            }
            case 401: {
                if (event.key == 10) {
                    this.search_for_text();
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }
    
    void search_for_text() {
        final String lowerCase = this.search_field.getText().toLowerCase();
        this.search_list_box.delItems(0, this.search_list_box.countItems() - 1);
        this.current_list.removeAllElements();
        for (int i = 0; i < this.web_list.size(); ++i) {
            final tree_entry tree_entry = this.web_list.elementAt(i);
            if (tree_entry.publish && tree_entry.get_name().toLowerCase().indexOf(lowerCase) != -1) {
                this.search_list_box.addItem(tree_entry.get_name());
                this.current_list.addElement(tree_entry);
            }
        }
    }
}
