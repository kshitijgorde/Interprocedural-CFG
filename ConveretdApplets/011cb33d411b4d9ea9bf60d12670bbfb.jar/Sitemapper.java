import java.util.StringTokenizer;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.util.ResourceBundle;
import java.util.Locale;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sitemapper extends Applet
{
    String window_name;
    String url_path;
    web_structure_builder wsb;
    tree_grid_builder tgb;
    button_panel view_buttons;
    nav_canvas main_canvas;
    outline_list main_list_box;
    check_box_panel user_level_panel;
    search_panel search_area;
    private String STRING_Error_showing_url;
    private String STRING_No_text_file;
    static String seperator_string;
    
    public Sitemapper() {
        this.window_name = null;
        this.url_path = null;
    }
    
    public void init() {
        super.init();
        this.STRING_Error_showing_url = new String(ResourceBundle.getBundle("SiteMapper", Locale.GERMAN).getString("Error_showing_url"));
        this.STRING_No_text_file = new String(ResourceBundle.getBundle("SiteMapper", Locale.GERMAN).getString("No_text_file"));
        this.setBackground(Color.white);
        this.load_site_info(this.getParameter("source_file"));
        this.setup_components();
        this.display_view(this.view_buttons.view);
    }
    
    void display_view(final int n) {
        if (n == 1) {
            this.add("Center", this.main_list_box);
            this.main_list_box.draw_web_list();
        }
        else if (n == 0) {
            this.add("Center", this.main_canvas);
            this.main_canvas.repaint();
        }
        else if (n == 2) {
            this.add("Center", this.search_area);
        }
        this.validate();
    }
    
    void remove_current_components(final int n) {
        if (n == 0) {
            this.remove(this.main_canvas);
            this.remove(this.user_level_panel);
        }
        else if (n == 1) {
            this.remove(this.main_list_box);
            this.remove(this.user_level_panel);
        }
        else if (n == 2) {
            this.remove(this.search_area);
        }
    }
    
    void setup_components() {
        this.setLayout(new BorderLayout(10, 10));
        this.add("North", this.view_buttons = new button_panel(this));
        this.add("South", this.user_level_panel = new check_box_panel(this));
        this.main_list_box = new outline_list(this.wsb.web_list, this);
        this.search_area = new search_panel(this.wsb.web_list, this);
        this.main_canvas = new nav_canvas(this.tgb.tree_grid, this.wsb.max_level, this.tgb.my_max_width, this);
    }
    
    void show_url(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(this.url_path + s), this.window_name);
        }
        catch (Exception ex) {
            System.out.println(this.STRING_Error_showing_url);
        }
    }
    
    public void load_site_info(final String s) {
        this.window_name = "netobjects_main_power";
        try {
            final URL url = new URL(this.getDocumentBase().toString());
            this.url_path = url.getProtocol() + "://" + url.getHost();
            if (url.getPort() != -1) {
                this.url_path = this.url_path + ":" + url.getPort();
            }
            this.url_path += url.getFile();
            this.url_path = this.url_path.substring(0, this.url_path.indexOf("Sitemapper.html"));
        }
        catch (Exception ex) {}
        final StringTokenizer stringTokenizer = new StringTokenizer(s, Sitemapper.seperator_string);
        if (stringTokenizer.countTokens() > 0) {
            this.wsb = new web_structure_builder(stringTokenizer);
            this.tgb = new tree_grid_builder(this.wsb.web_tree, this.wsb.max_level);
            return;
        }
        System.out.println(ResourceBundle.getBundle("SiteMapper").getString("No_text_file"));
    }
    
    static {
        Sitemapper.seperator_string = "*";
    }
}
