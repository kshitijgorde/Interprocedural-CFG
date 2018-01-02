import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class DistPanel extends Panel
{
    int WIDTH;
    int HEIGHT;
    static Choice n_size_choice;
    static Choice effect;
    static Label label2;
    static Label label6;
    static Label effectLabel;
    static Label n_lab;
    static Label mu_nod_lab;
    static Label mu_alt_lab;
    static Label sigma_lab;
    static Label alpha_lab;
    static Label mean_lab;
    static Label mean2_lab;
    static Label m_out;
    static Label s_out;
    static Label z_out;
    static Label d_out;
    static Label delta_out;
    static Label beta_lab;
    static Label power_lab;
    static Label mean_dif_lab;
    static Label one_tail_p;
    static Label two_tail_p;
    static TextField mean_dif_tf;
    static TextField n_tf;
    static TextField mu_nod_tf;
    static TextField mu_alt_tf;
    static TextField sigma_tf;
    static TextField alpha_tf;
    static TextField beta_tf;
    static TextField d_tf;
    static TextField power_tf;
    String m_txt;
    String s_txt;
    String z_txt;
    String d_txt;
    String delta_txt;
    Checkbox controls;
    static Button sampleButton;
    static Button setButton;
    static Button clear;
    static Color darkGreen;
    static Font smallFont;
    static Font smallFont_BOLD;
    static Font regularFont;
    static Font regularFont_BOLD;
    FontMetrics smallFM;
    FontMetrics regularFM;
    int comp_ht;
    int temp;
    static int com_base;
    static boolean show_pop;
    static boolean show_alt;
    static boolean draw_arrow;
    static int x_scale;
    static double pop_scale;
    static double alt_scale;
    static double[] x_pop_lab;
    static double[] y_pop_lab;
    static double[] y_alt_lab;
    static double y_temp_alt;
    static double y_temp;
    static final int x_base = 37;
    static final int y_base = 280;
    
    static {
        DistPanel.darkGreen = new Color(0, 128, 0);
        DistPanel.smallFont = new Font("Monaco", 0, 10);
        DistPanel.smallFont_BOLD = new Font("Chicago", 0, 10);
        DistPanel.regularFont = new Font("Chicago", 0, 10);
        DistPanel.regularFont_BOLD = new Font("Chicago", 1, 12);
        DistPanel.show_pop = true;
        DistPanel.show_alt = true;
        DistPanel.draw_arrow = false;
        DistPanel.x_scale = 4;
        DistPanel.pop_scale = 0.2 * get_y_base();
        DistPanel.alt_scale = 0.8 * get_y_base();
        DistPanel.x_pop_lab = new double[101];
        DistPanel.y_pop_lab = new double[Graph_Labels.spaces + 2];
        DistPanel.y_alt_lab = new double[Graph_Labels.spaces + 2];
        DistPanel.y_temp_alt = (DistPanel.alt_scale - 10.0) / (Graph_Labels.spaces + 2);
        DistPanel.y_temp = (DistPanel.alt_scale - 10.0) / (Graph_Labels.spaces + 2);
    }
    
    public static int get_y_base() {
        return 280;
    }
    
    public static int get_y_pop_base() {
        return 57;
    }
    
    public static int get_x_base() {
        return 37;
    }
    
    public static int get_x_scale() {
        return DistPanel.x_scale;
    }
    
    DistPanel(final int width, final int height) {
        this.m_txt = "mean = ";
        this.s_txt = "s= ";
        this.z_txt = "z= ";
        this.d_txt = "d'= ";
        this.delta_txt = "delta= ";
        this.smallFM = this.getFontMetrics(DistPanel.smallFont);
        this.regularFM = this.getFontMetrics(DistPanel.regularFont);
        this.comp_ht = this.smallFM.getHeight();
        this.temp = this.get_stringWidth("0.000") + 7;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setFont(DistPanel.smallFont);
    }
    
    DistPanel() {
        this.m_txt = "mean = ";
        this.s_txt = "s= ";
        this.z_txt = "z= ";
        this.d_txt = "d'= ";
        this.delta_txt = "delta= ";
        this.smallFM = this.getFontMetrics(DistPanel.smallFont);
        this.regularFM = this.getFontMetrics(DistPanel.regularFont);
        this.comp_ht = this.smallFM.getHeight();
        this.temp = this.get_stringWidth("0.000") + 7;
        this.setBackground(Color.white);
    }
    
    public int get_stringWidth(final String s) {
        return this.smallFM.stringWidth(s);
    }
}
