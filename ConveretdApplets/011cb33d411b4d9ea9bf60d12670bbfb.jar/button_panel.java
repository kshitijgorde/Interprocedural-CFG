import java.awt.Event;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ResourceBundle;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class button_panel extends Panel
{
    Sitemapper parent_applet;
    private String STRING_Structure;
    private String STRING_Outline;
    private String STRING_Search;
    int view;
    static final int STRUCTURE_VIEW = 0;
    static final int OUTLINE_VIEW = 1;
    static final int SEARCH_VIEW = 2;
    
    button_panel(final Sitemapper parent_applet) {
        this.parent_applet = parent_applet;
        this.STRING_Structure = new String(ResourceBundle.getBundle("SiteMapper").getString("Structure"));
        this.STRING_Outline = new String(ResourceBundle.getBundle("SiteMapper").getString("Outline"));
        this.STRING_Search = new String(ResourceBundle.getBundle("SiteMapper").getString("Search"));
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        this.add("East", panel);
        panel.setLayout(new FlowLayout());
        panel.add(new Button(this.STRING_Structure));
        panel.add(new Button(this.STRING_Outline));
        panel.add(new Button(this.STRING_Search));
        this.view = 0;
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals(this.STRING_Structure)) {
            if (this.view != 0) {
                this.parent_applet.remove_current_components(this.view);
                this.parent_applet.add("South", this.parent_applet.user_level_panel);
                this.parent_applet.display_view(this.view = 0);
                return true;
            }
        }
        else if (o.equals(this.STRING_Outline)) {
            if (this.view != 1) {
                this.parent_applet.remove_current_components(this.view);
                this.parent_applet.add("South", this.parent_applet.user_level_panel);
                this.parent_applet.display_view(this.view = 1);
                return true;
            }
        }
        else if (o.equals(this.STRING_Search) && this.view != 2) {
            this.parent_applet.remove_current_components(this.view);
            this.parent_applet.remove(this.parent_applet.user_level_panel);
            this.parent_applet.display_view(this.view = 2);
            return true;
        }
        return false;
    }
}
