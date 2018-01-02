import java.awt.Event;
import java.awt.Image;
import java.awt.CheckboxGroup;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Container;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PGC extends Applet
{
    private String[] main_args;
    private PGC_Frame main_frame;
    private Container main_parent;
    private Dimension main_size;
    private Point main_location;
    private Panel pnl_root_launch;
    private Panel pnl_root_calculator;
    private PGC_GraphPanel pnl_graph;
    private Panel pnl_ctl;
    private Panel pnl_ctl_buttons;
    private Panel pnl_ctl_buttons_function;
    private Panel pnl_ctl_buttons_zoom;
    private Panel pnl_ctl_buttons_func;
    private Panel pnl_ctl_coordinates;
    private Panel pnl_ctl_coordinates_description;
    private PGC_ChoiceOp choice_op;
    private PGC_ButtonFixed btn_zoomout;
    private PGC_ButtonFixed btn_zoomin;
    private Checkbox chk_line;
    private Checkbox chk_spin;
    private Checkbox chk_plot;
    private PGC_ButtonFixed btn_details;
    private PGC_ButtonFixed btn_step;
    private PGC_ButtonFixed btn_plot_clear;
    private PGC_ButtonFixed btn_about;
    private PGC_ButtonFixed btn_attach_detach;
    private Button btn_launch;
    private Label lbl_zoom;
    private PGC_LabelFixed lbl_description;
    private PGC_LabelFixed lbl_coordinate_x;
    private PGC_LabelFixed lbl_coordinate_y;
    private PGC_LabelFixed lbl_f_expr;
    private PGC_LabelFixed lbl_g_expr;
    private PGC_TextFieldFixed txt_f_expr;
    private PGC_TextFieldFixed txt_g_expr;
    private PGC_TextFrame about_frame;
    private Point about_location;
    private PGC_DetailsFrame details_frame;
    private Point details_location;
    private static PGC_TextFrame trace_frame;
    private PGC_Eval eval;
    public boolean stand_alone;
    public boolean launch_mode;
    public static boolean option_enable_trace;
    public String option_launch_button;
    public int option_launch_size_x;
    public int option_launch_size_y;
    public String option_background_image;
    public boolean option_control_panel;
    public boolean option_graph_border;
    public boolean option_builtin_functions;
    public boolean option_builtin_examples;
    public Complex option_center;
    public int option_zoom;
    public boolean option_line;
    public boolean option_plot;
    public boolean option_spin;
    public int option_active;
    public int option_op;
    public PGC_Variable[] variable;
    public double t_minimum;
    public double t_maximum;
    public int t_count;
    public static Color color_panel;
    public static Color color_background;
    public static Color color_axis;
    public static Color color_grid;
    public static Color color_origin_connector_line;
    public static Color color_input_point;
    public static Color color_input_label;
    public static Color color_func_f_point;
    public static Color color_func_f_label;
    public static Color color_func_f_plot;
    public static Color color_func_g_point;
    public static Color color_func_g_label;
    public static Color color_func_g_plot;
    public static Color color_func_h_point;
    public static Color color_func_h_label;
    public static Color color_func_h_plot;
    public static Color color_func_k_point;
    public static Color color_func_k_label;
    public static Color color_func_k_plot;
    public static Color color_func_m_point;
    public static Color color_func_m_label;
    public static Color color_func_m_plot;
    public static Color color_func_n_point;
    public static Color color_func_n_label;
    public static Color color_func_n_plot;
    public static Color color_lightray;
    public static Color color_shadow;
    public static Color color_shadow_label;
    public static Color color_3d_dark;
    public static Color color_3d_light;
    public static Dimension component_tiny_button_size;
    public static Dimension component_normal_button_size;
    public static Dimension component_number_text_size;
    public static Dimension component_wide_text_size;
    public static Dimension component_full_text_size;
    public static final int VAR_A = 0;
    public static final int VAR_B = 1;
    public static final int VAR_C = 2;
    public static final int VAR_D = 3;
    public static final int VAR_F = 4;
    public static final int VAR_G = 5;
    public static final int VAR_H = 6;
    public static final int VAR_K = 7;
    public static final int VAR_M = 8;
    public static final int VAR_N = 9;
    public static final int VAR_SHADOW_X = 10;
    public static final int VAR_SHADOW_Y = 11;
    public static final int VAR_AUTOPLOT = 12;
    public static final int VAR_AUTOINCREMENT = 13;
    public static final int VAR_COUNT = 14;
    public static final int OP_UNDEFINED = 0;
    public static final int OP_ADD = 1;
    public static final int OP_SUB = 2;
    public static final int OP_MUL = 3;
    public static final int OP_DIV = 4;
    public static final int OP_POW = 5;
    public static final int OP_DOT = 6;
    public static final int OP_CROSS = 7;
    public static final int OP_MAT = 8;
    public static final int OP_SQRT = 9;
    public static final int OP_CUBERT = 10;
    public static final int OP_EXP = 11;
    public static final int OP_USER = 12;
    public static final int OP_EXAMPLE_1 = 13;
    public static final int OP_EXAMPLE_2 = 14;
    public static final int OP_EXAMPLE_3 = 15;
    public static final int OP_EXAMPLE_4 = 16;
    public static final int OP_EXAMPLE_5 = 17;
    public static final int OP_EXAMPLE_6 = 18;
    public static final int OP_EXAMPLE_7 = 19;
    public static final int OP_EXAMPLE_8 = 20;
    public static final int OP_COUNT = 21;
    public static final int OP_EXAMPLE_9 = 21;
    public static final int OP_EXAMPLE_10 = 22;
    
    public void MainFrameLaunch() {
        if (this.main_frame == null) {
            this.main_frame = new PGC_Frame(this, "Plane Graphic Calculator");
            this.LayoutCalculator();
            this.SetOptions();
            this.main_frame.setLayout(new BorderLayout());
            this.main_frame.add("Center", this.pnl_root_calculator);
            this.main_frame.pack();
            Dimension main_size;
            if (this.main_size == null) {
                final Dimension dimension;
                main_size = (dimension = new Dimension(this.option_launch_size_x, this.option_launch_size_y));
                dimension.height += 50;
                final Dimension dimension2 = main_size;
                dimension2.width += 6;
                this.main_size = main_size;
            }
            else {
                main_size = new Dimension(this.main_size);
            }
            this.main_frame.resize(main_size);
            if (this.main_location == null) {
                this.main_location = new Point(20, 20);
            }
            this.main_frame.move(this.main_location.x, this.main_location.y);
            this.main_frame.validate();
            if (this.btn_attach_detach != null) {
                this.btn_attach_detach.setLabel("Close");
            }
            this.main_frame.show();
            return;
        }
        this.main_frame.toFront();
        this.main_frame.show();
        this.main_frame.requestFocus();
    }
    
    public void MainFrameUnlaunch() {
        if (this.main_frame != null) {
            this.main_size = new Dimension(this.main_frame.size());
            final Point location = this.main_frame.location();
            this.main_location = new Point(location.x, location.y);
            this.main_frame.remove(this);
            this.main_frame.dispose();
            this.main_frame = null;
        }
    }
    
    public void MainFrameDetach() {
        this.main_parent = this.getParent();
        this.main_frame = new PGC_Frame(this, "Plane Graphic Calculator");
        Dimension dimension;
        if (this.main_size == null) {
            if (this.main_parent == null) {
                this.main_size = new Dimension(this.size());
            }
            else {
                this.main_size = new Dimension(this.main_parent.size());
            }
            final Dimension dimension2;
            dimension = (dimension2 = new Dimension(this.main_size));
            dimension2.height += 50;
            final Dimension dimension3 = dimension;
            dimension3.width += 6;
        }
        else {
            dimension = new Dimension(this.main_size);
        }
        this.main_frame.resize(dimension);
        if (this.main_location == null) {
            if (this.main_parent == null) {
                this.main_location = new Point(20, 20);
            }
            else {
                final Point location = this.main_parent.location();
                this.main_location = new Point(location.x + 20, location.y + 20);
            }
        }
        this.main_frame.move(this.main_location.x, this.main_location.y);
        this.main_frame.add("Center", this);
        this.main_frame.validate();
        if (this.btn_attach_detach != null) {
            this.btn_attach_detach.setLabel("Attach");
        }
        this.main_frame.show();
    }
    
    public void MainFrameAttach() {
        if (this.stand_alone) {
            System.exit(0);
        }
        this.main_size = new Dimension(this.getParent().size());
        final Point location = this.getParent().location();
        this.main_location = new Point(location.x, location.y);
        if (this.main_frame != null) {
            this.main_frame.remove(this);
            this.main_frame.dispose();
            this.main_frame = null;
        }
        if (this.main_parent != null) {
            this.main_parent.add("Center", this);
            this.main_parent.validate();
            if (this.btn_attach_detach != null) {
                this.btn_attach_detach.setLabel("Detach");
            }
            this.main_parent.show();
        }
    }
    
    public void MainFrameDestroy() {
        if (this.main_frame != null) {
            this.main_frame.remove(this);
            this.main_frame.dispose();
            this.main_frame = null;
        }
        this.FrameDestroy(this.about_frame);
        this.FrameDestroy(this.details_frame);
    }
    
    public void MainFrameClose() {
        if (this.launch_mode) {
            this.MainFrameUnlaunch();
            this.MainFrameDestroy();
            return;
        }
        this.MainFrameAttach();
    }
    
    public static GridBagConstraints StdConstraintsFillMax() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        return gridBagConstraints;
    }
    
    public static GridBagConstraints StdConstraintsFillMax(final Insets insets) {
        final GridBagConstraints stdConstraintsFillMax = StdConstraintsFillMax();
        stdConstraintsFillMax.insets = insets;
        return stdConstraintsFillMax;
    }
    
    public static GridBagConstraints StdConstraints() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        return gridBagConstraints;
    }
    
    public static GridBagConstraints StdConstraints(final Insets insets) {
        final GridBagConstraints stdConstraints = StdConstraints();
        stdConstraints.insets = insets;
        return stdConstraints;
    }
    
    public static GridBagConstraints StdConstraintsNewLine() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        return gridBagConstraints;
    }
    
    public static GridBagConstraints StdConstraintsNewLine(final Insets insets) {
        final GridBagConstraints stdConstraintsNewLine = StdConstraintsNewLine();
        stdConstraintsNewLine.insets = insets;
        return stdConstraintsNewLine;
    }
    
    public static Panel StdPanel() {
        final Panel panel = new Panel();
        panel.setBackground(PGC.color_panel);
        panel.setLayout(new GridBagLayout());
        return panel;
    }
    
    private void LayoutCalculator() {
        this.pnl_root_calculator = StdPanel();
        final Panel pnl_root_calculator = this.pnl_root_calculator;
        final PGC_GraphPanel pnl_graph = new PGC_GraphPanel(this);
        this.pnl_graph = pnl_graph;
        final PGC_GraphPanel pgc_GraphPanel = pnl_graph;
        pnl_root_calculator.add(pnl_graph);
        ((GridBagLayout)this.pnl_root_calculator.getLayout()).setConstraints(pgc_GraphPanel, StdConstraintsFillMax());
        if (this.option_control_panel) {
            final Panel pnl_root_calculator2 = this.pnl_root_calculator;
            final Panel stdPanel = StdPanel();
            this.pnl_ctl = stdPanel;
            final Panel panel = stdPanel;
            pnl_root_calculator2.add(stdPanel);
            ((GridBagLayout)this.pnl_root_calculator.getLayout()).setConstraints(panel, StdConstraintsNewLine(new Insets(5, 10, 5, 0)));
            final Panel pnl_ctl = this.pnl_ctl;
            final Panel stdPanel2 = StdPanel();
            this.pnl_ctl_buttons = stdPanel2;
            final Panel panel2 = stdPanel2;
            pnl_ctl.add(stdPanel2);
            ((GridBagLayout)this.pnl_ctl.getLayout()).setConstraints(panel2, StdConstraints());
            final Panel pnl_ctl_buttons = this.pnl_ctl_buttons;
            final Panel stdPanel3 = StdPanel();
            this.pnl_ctl_buttons_function = stdPanel3;
            final Panel panel3 = stdPanel3;
            pnl_ctl_buttons.add(stdPanel3);
            ((GridBagLayout)this.pnl_ctl_buttons.getLayout()).setConstraints(panel3, StdConstraintsNewLine(new Insets(3, 0, 0, 0)));
            this.pnl_ctl_buttons_function.add(new Label("Function: "));
            this.pnl_ctl_buttons_function.add(this.choice_op = new PGC_ChoiceOp(this));
            this.choice_op.setBackground(Color.white);
            for (int i = 1; i < 21; ++i) {
                if ((i >= 12 || this.option_builtin_functions) && (i <= 12 || this.option_builtin_examples)) {
                    this.choice_op.addItem(OpChoiceLabel(i));
                }
            }
            this.pnl_ctl_buttons_function.add(new PGC_LabelFixed(new Dimension(10, -1)));
            this.pnl_ctl_buttons_function.add(this.btn_details = new PGC_ButtonFixed("Details", PGC.component_normal_button_size));
            this.pnl_ctl_buttons_function.add(this.btn_step = new PGC_ButtonFixed("Step", PGC.component_normal_button_size));
            this.pnl_ctl_buttons_function.add(this.btn_plot_clear = new PGC_ButtonFixed("Clear", PGC.component_normal_button_size));
            this.pnl_ctl_buttons_function.add(this.btn_about = new PGC_ButtonFixed("About", PGC.component_normal_button_size));
            if (!this.stand_alone) {
                this.pnl_ctl_buttons_function.add(this.btn_attach_detach = new PGC_ButtonFixed("Detach", PGC.component_normal_button_size));
            }
            final Panel pnl_ctl_buttons2 = this.pnl_ctl_buttons;
            final Panel stdPanel4 = StdPanel();
            this.pnl_ctl_buttons_zoom = stdPanel4;
            final Panel panel4 = stdPanel4;
            pnl_ctl_buttons2.add(stdPanel4);
            ((GridBagLayout)this.pnl_ctl_buttons.getLayout()).setConstraints(panel4, StdConstraintsNewLine(new Insets(3, 0, 0, 0)));
            this.pnl_ctl_buttons_zoom.add(this.lbl_zoom = new Label("Zoom: 9999 X "));
            this.pnl_ctl_buttons_zoom.add(this.btn_zoomout = new PGC_ButtonFixed("-", PGC.component_tiny_button_size));
            this.pnl_ctl_buttons_zoom.add(this.btn_zoomin = new PGC_ButtonFixed("+", PGC.component_tiny_button_size));
            this.pnl_ctl_buttons_zoom.add(new PGC_LabelFixed(new Dimension(20, -1)));
            this.pnl_ctl_buttons_zoom.add(this.lbl_coordinate_x = new PGC_LabelFixed("x =", new Dimension(82, -1)));
            this.pnl_ctl_buttons_zoom.add(new PGC_LabelFixed(new Dimension(4, -1)));
            this.pnl_ctl_buttons_zoom.add(this.lbl_coordinate_y = new PGC_LabelFixed("y =", new Dimension(82, -1)));
            this.pnl_ctl_buttons_zoom.add(new PGC_LabelFixed(new Dimension(20, -1)));
            this.pnl_ctl_buttons_zoom.add(this.chk_plot = new Checkbox("Plot  ", null, false));
            this.pnl_ctl_buttons_zoom.add(this.chk_spin = new Checkbox("Spin  ", null, false));
            this.pnl_ctl_buttons_zoom.add(this.chk_line = new Checkbox("Line  ", null, false));
            final Panel pnl_ctl_buttons3 = this.pnl_ctl_buttons;
            final Panel stdPanel5 = StdPanel();
            this.pnl_ctl_buttons_func = stdPanel5;
            final Panel panel5 = stdPanel5;
            pnl_ctl_buttons3.add(stdPanel5);
            ((GridBagLayout)this.pnl_ctl_buttons.getLayout()).setConstraints(panel5, StdConstraintsNewLine(new Insets(3, 0, 0, 0)));
            final Panel pnl_ctl_buttons_func = this.pnl_ctl_buttons_func;
            final PGC_LabelFixed lbl_f_expr = new PGC_LabelFixed("f = ", new Dimension(22, -1));
            this.lbl_f_expr = lbl_f_expr;
            final PGC_LabelFixed pgc_LabelFixed = lbl_f_expr;
            pnl_ctl_buttons_func.add(lbl_f_expr);
            ((GridBagLayout)this.pnl_ctl_buttons_func.getLayout()).setConstraints(pgc_LabelFixed, StdConstraints());
            this.pnl_ctl_buttons_func.add(this.txt_f_expr = new PGC_TextFieldFixed(this, new Dimension(220, -1)));
            this.pnl_ctl_buttons_func.add(new PGC_LabelFixed("", new Dimension(12, -1)));
            final Panel pnl_ctl_buttons_func2 = this.pnl_ctl_buttons_func;
            final PGC_LabelFixed lbl_g_expr = new PGC_LabelFixed("g = ", new Dimension(26, -1));
            this.lbl_g_expr = lbl_g_expr;
            final PGC_LabelFixed pgc_LabelFixed2 = lbl_g_expr;
            pnl_ctl_buttons_func2.add(lbl_g_expr);
            ((GridBagLayout)this.pnl_ctl_buttons_func.getLayout()).setConstraints(pgc_LabelFixed2, StdConstraints());
            this.pnl_ctl_buttons_func.add(this.txt_g_expr = new PGC_TextFieldFixed(this, new Dimension(220, -1)));
        }
        this.pnl_graph.init();
        this.pnl_graph.show();
    }
    
    private void LayoutLaunchButton() {
        this.pnl_root_launch = StdPanel();
        final Panel pnl_root_launch = this.pnl_root_launch;
        final Button btn_launch = new Button(this.option_launch_button);
        this.btn_launch = btn_launch;
        final Button button = btn_launch;
        pnl_root_launch.add(btn_launch);
        ((GridBagLayout)this.pnl_root_launch.getLayout()).setConstraints(button, StdConstraintsFillMax());
    }
    
    private void DefineVariables() {
        final int index = 0;
        this.variable[index] = new PGC_Variable();
        this.variable[index].index = index;
        this.variable[index].name = "a";
        this.variable[index].is_input = true;
        this.variable[index].coordinates = Complex.Number(1.0, 1.0);
        final int index2 = 1;
        this.variable[index2] = new PGC_Variable();
        this.variable[index2].index = index2;
        this.variable[index2].name = "b";
        this.variable[index2].is_input = true;
        this.variable[index2].coordinates = Complex.Number(1.125, -0.25);
        final int index3 = 2;
        this.variable[index3] = new PGC_Variable();
        this.variable[index3].index = index3;
        this.variable[index3].name = "c";
        this.variable[index3].is_input = true;
        this.variable[index3].coordinates = Complex.Number(-0.5, -1.0);
        final int index4 = 3;
        this.variable[index4] = new PGC_Variable();
        this.variable[index4].index = index4;
        this.variable[index4].name = "d";
        this.variable[index4].is_input = true;
        this.variable[index4].coordinates = Complex.Number(-0.25, 0.5);
        final int index5 = 4;
        this.variable[index5] = new PGC_Variable();
        this.variable[index5].index = index5;
        this.variable[index5].name = "f";
        this.variable[index5].is_function = true;
        this.variable[index5].expression = "";
        final int index6 = 5;
        this.variable[index6] = new PGC_Variable();
        this.variable[index6].index = index6;
        this.variable[index6].name = "g";
        this.variable[index6].is_function = true;
        this.variable[index6].expression = "";
        final int index7 = 6;
        this.variable[index7] = new PGC_Variable();
        this.variable[index7].index = index7;
        this.variable[index7].name = "h";
        this.variable[index7].is_function = true;
        this.variable[index7].expression = "";
        final int index8 = 7;
        this.variable[index8] = new PGC_Variable();
        this.variable[index8].index = index8;
        this.variable[index8].name = "k";
        this.variable[index8].is_function = true;
        this.variable[index8].expression = "";
        final int index9 = 8;
        this.variable[index9] = new PGC_Variable();
        this.variable[index9].index = index9;
        this.variable[index9].name = "m";
        this.variable[index9].is_function = true;
        this.variable[index9].expression = "";
        final int index10 = 9;
        this.variable[index10] = new PGC_Variable();
        this.variable[index10].index = index10;
        this.variable[index10].name = "n";
        this.variable[index10].is_function = true;
        this.variable[index10].expression = "";
        final int index11 = 10;
        this.variable[index11] = new PGC_Variable();
        this.variable[index11].index = index11;
        this.variable[index11].name = "x";
        final int index12 = 11;
        this.variable[index12] = new PGC_Variable();
        this.variable[index12].index = index12;
        this.variable[index12].name = "y";
        final int index13 = 12;
        this.variable[index13] = new PGC_Variable();
        this.variable[index13].index = index13;
        this.variable[index13].name = "";
        this.variable[index13].is_autoplot_var = true;
        final int index14 = 13;
        this.variable[index14] = new PGC_Variable();
        this.variable[index14].index = index14;
        this.variable[index14].name = "";
        this.variable[index14].is_autoincr_var = true;
        this.variable[index14].coordinates = Complex.Number(0.0, 0.0);
    }
    
    public static String OpDescription(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "Addition";
                break;
            }
            case 2: {
                s = "Subtraction";
                break;
            }
            case 3: {
                s = "Complex multiplication";
                break;
            }
            case 4: {
                s = "Complex division";
                break;
            }
            case 5: {
                s = "Complex power";
                break;
            }
            case 6: {
                s = "Vector dot product";
                break;
            }
            case 7: {
                s = "Vector cross product";
                break;
            }
            case 8: {
                s = "Matrix multiplication";
                break;
            }
            case 9: {
                s = "Complex square roots";
                break;
            }
            case 10: {
                s = "Complex cube roots";
                break;
            }
            case 11: {
                s = "Complex exponential";
                break;
            }
            case 12: {
                s = "User-defined functions";
                break;
            }
            case 13: {
                s = "Example 1";
                break;
            }
            case 14: {
                s = "Example 2";
                break;
            }
            case 15: {
                s = "Example 3";
                break;
            }
            case 16: {
                s = "Example 4";
                break;
            }
            case 17: {
                s = "Example 5";
                break;
            }
            case 18: {
                s = "Example 6";
                break;
            }
            case 19: {
                s = "Example 7";
                break;
            }
            case 20: {
                s = "Example 8";
                break;
            }
            case 21: {
                s = "Example 9";
                break;
            }
            case 22: {
                s = "Example 10";
                break;
            }
            default: {
                s = "";
                break;
            }
        }
        return s;
    }
    
    public static String OpLabel(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "f = a + b";
                break;
            }
            case 2: {
                s = "f = a - b";
                break;
            }
            case 3: {
                s = "f = a * b";
                break;
            }
            case 4: {
                s = "f = a / b";
                break;
            }
            case 5: {
                s = "f = a ^ b";
                break;
            }
            case 6: {
                s = "f = a · b";
                break;
            }
            case 7: {
                s = "f = a \u00d7 b";
                break;
            }
            case 8: {
                s = "f = mat(a,b) · c";
                break;
            }
            case 9: {
                s = "f = V¯a";
                break;
            }
            case 10: {
                s = "f = 3V¯a";
                break;
            }
            case 11: {
                s = "f = exp a";
                break;
            }
            case 12: {
                s = "user-defined";
                break;
            }
            case 13: {
                s = "example 1";
                break;
            }
            case 14: {
                s = "example 2";
                break;
            }
            case 15: {
                s = "example 3";
                break;
            }
            case 16: {
                s = "example 4";
                break;
            }
            case 17: {
                s = "example 5";
                break;
            }
            case 18: {
                s = "example 6";
                break;
            }
            case 19: {
                s = "example 7";
                break;
            }
            case 20: {
                s = "example 8";
                break;
            }
            case 21: {
                s = "example 9";
                break;
            }
            case 22: {
                s = "example 10";
                break;
            }
            default: {
                s = "";
                break;
            }
        }
        return s;
    }
    
    public static String OpExpression(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "a + b";
                break;
            }
            case 2: {
                s = "a - b";
                break;
            }
            case 3: {
                s = "a * b";
                break;
            }
            case 4: {
                s = "a / b";
                break;
            }
            case 5: {
                s = "a ^ b";
                break;
            }
            case 6: {
                s = "a dot b";
                break;
            }
            case 7: {
                s = "a cross b";
                break;
            }
            case 8: {
                s = "(a dot c, b dot c)";
                break;
            }
            case 9: {
                s = "sqrt a";
                break;
            }
            case 10: {
                s = "a ^ (1/3)";
                break;
            }
            case 11: {
                s = "exp a";
                break;
            }
            default: {
                s = "";
                break;
            }
        }
        return s;
    }
    
    public static String OpChoiceLabel(final int n) {
        return "  " + OpLabel(n) + "  ";
    }
    
    public static int OpEvalChoiceLabel(final String s) {
        int n = 0;
        for (int i = 0; i < 21; ++i) {
            if (s.equals(OpChoiceLabel(i))) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static int OpArgCount(final int n) {
        int n2 = 0;
        switch (n) {
            case 1: {
                n2 = 2;
                break;
            }
            case 2: {
                n2 = 2;
                break;
            }
            case 3: {
                n2 = 2;
                break;
            }
            case 4: {
                n2 = 2;
                break;
            }
            case 5: {
                n2 = 2;
                break;
            }
            case 6: {
                n2 = 2;
                break;
            }
            case 7: {
                n2 = 2;
                break;
            }
            case 8: {
                n2 = 3;
                break;
            }
            case 9: {
                n2 = 1;
                break;
            }
            case 10: {
                n2 = 1;
                break;
            }
            case 11: {
                n2 = 1;
                break;
            }
            case 12: {
                n2 = 0;
                break;
            }
            default: {
                n2 = 0;
                break;
            }
        }
        return n2;
    }
    
    public static void Trace(final Object o) {
        if (PGC.option_enable_trace) {
            if (PGC.trace_frame == null) {
                (PGC.trace_frame = new PGC_TextFrame(null, "Trace", 20, 80)).init();
                PGC.trace_frame.show();
            }
            PGC.trace_frame.Write(o);
        }
    }
    
    public static void AssertionFailure() {
        throw new Error("assertion failure");
    }
    
    public boolean GetLine() {
        return this.pnl_graph.GetLine();
    }
    
    public void SetLine(final boolean state) {
        if (this.pnl_ctl != null && this.chk_line.getState() == !state) {
            this.chk_line.setState(state);
        }
        this.pnl_graph.SetLine(state);
    }
    
    public void CheckLine(final Boolean b) {
        if (b != null) {
            this.SetLine(b);
            return;
        }
        this.SetLine(!this.GetLine());
    }
    
    public boolean GetSpin() {
        return this.pnl_graph.GetSpin();
    }
    
    public void SetSpin(boolean state) {
        if (state && !this.GetEnableSpin()) {
            state = false;
        }
        if (this.pnl_ctl != null && this.chk_spin.getState() == !state) {
            this.chk_spin.setState(state);
        }
        this.pnl_graph.SetSpin(state);
    }
    
    public void CheckSpin(final Boolean b) {
        if (b != null) {
            this.SetSpin(b);
            return;
        }
        this.SetSpin(!this.GetSpin());
    }
    
    public void EnableSpin(final boolean b) {
        if (this.pnl_ctl != null) {
            this.chk_spin.enable(b);
        }
        this.pnl_graph.EnableSpin(b);
        if (!b) {
            this.SetSpin(false);
        }
    }
    
    public boolean GetEnableSpin() {
        return this.pnl_graph.GetEnableSpin();
    }
    
    public boolean GetPlot() {
        return this.pnl_graph.GetPlot();
    }
    
    public void SetPlot(final boolean state) {
        if (this.pnl_ctl != null && this.chk_plot.getState() == !state) {
            this.chk_plot.setState(state);
        }
        this.pnl_graph.SetPlot(state);
    }
    
    public void CheckPlot(final Boolean b) {
        if (b != null) {
            this.SetPlot(b);
            return;
        }
        this.SetPlot(!this.GetPlot());
    }
    
    public void Clear() {
        this.SetPlot(false);
        this.SetAutoincrementVariable(Complex.Number(0.0));
        this.pnl_graph.PlotClear();
        this.UpdateDisplay();
    }
    
    public void AttachDetach() {
        if (this.btn_attach_detach != null) {
            if (this.main_frame == null) {
                this.MainFrameDetach();
                return;
            }
            this.MainFrameClose();
        }
    }
    
    public void GraphFocus() {
        this.pnl_graph.requestFocus();
    }
    
    public void About() {
        if (this.about_frame == null) {
            (this.about_frame = new PGC_TextFrame(this, "Plane Graphic Calculator", 10, 50)).init();
            if (this.about_location != null) {
                this.about_frame.move(this.about_location.x, this.about_location.y);
            }
            else {
                this.about_frame.move(50, 50);
            }
            this.AboutText();
            this.about_frame.show();
        }
        this.about_frame.toFront();
        this.about_frame.show();
        this.about_frame.requestFocus();
    }
    
    private void AboutText() {
        this.about_frame.Write(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append("\n").toString())).append(" Plane Graphic Calculator\n").toString())).append(" Version 1.0\n").toString())).append(" July 2000\n").toString())).append("\n").toString())).append(" This software is donated to the public,\n").toString())).append(" and can be used with no restrictions.\n").toString()) + "\n");
        this.about_frame.ShowTop();
    }
    
    public void Details() {
        if (this.details_frame == null) {
            (this.details_frame = new PGC_DetailsFrame(this, "Details")).init();
            this.UpdateDisplay();
            if (this.details_location != null) {
                this.details_frame.move(this.details_location.x, this.details_location.y);
            }
            else {
                this.details_frame.move(50, 50);
            }
            this.details_frame.show();
        }
        else {
            this.UpdateDisplay();
        }
        this.details_frame.toFront();
        this.details_frame.show();
        this.details_frame.requestFocus();
    }
    
    public void FrameDestroy(final Frame frame) {
        if (frame != null) {
            frame.dispose();
            if (frame == this.about_frame) {
                this.about_frame = null;
                this.about_location = frame.location();
                return;
            }
            if (frame == this.details_frame) {
                this.details_frame = null;
                this.details_location = frame.location();
            }
        }
    }
    
    public PGC_Eval GetEval() {
        return this.eval;
    }
    
    public void UpdateDisplay() {
        int getOp = this.GetOp();
        boolean b = false;
        final PGC_Eval getEval = this.GetEval();
        getEval.Evaluate(this.variable[4].expression);
        if (!getEval.empty) {
            b = true;
        }
        if (b && getOp < 12) {
            getOp = 12;
            this.pnl_graph.SetOp(getOp);
        }
        if (this.pnl_ctl != null && this.choice_op.getSelectedItem() != OpChoiceLabel(getOp)) {
            this.choice_op.select(OpChoiceLabel(getOp));
        }
        if (this.details_frame != null) {
            this.details_frame.lbl_error_message.setText("");
        }
        for (int i = 0; i < this.variable.length; ++i) {
            this.variable[i].is_displayed = false;
        }
        int opArgCount = OpArgCount(getOp);
        if (opArgCount == 1) {
            this.variable[0].is_displayed = true;
            this.variable[4].is_displayed = true;
        }
        else if (opArgCount == 2) {
            this.variable[0].is_displayed = true;
            this.variable[1].is_displayed = true;
            this.variable[4].is_displayed = true;
        }
        else if (opArgCount == 3) {
            this.variable[0].is_displayed = true;
            this.variable[1].is_displayed = true;
            this.variable[2].is_displayed = true;
            this.variable[4].is_displayed = true;
        }
        final PGC_Eval getEval2 = this.GetEval();
        String string = "";
        for (int j = 0; j < this.variable.length; ++j) {
            if (this.variable[j].is_function) {
                if (j != 4 || getOp >= 12) {
                    getEval2.Evaluate(this.variable[j].expression);
                    this.variable[j].is_displayed = (!getEval2.empty && !getEval2.uses_autoplot);
                    for (int k = 0; k < this.variable.length; ++k) {
                        if (this.variable[k].is_input) {
                            this.variable[k].is_displayed = (this.variable[k].is_displayed || getEval2.uses_input[k]);
                        }
                    }
                    if (getEval2.message.length() > 0 && string.length() == 0) {
                        string = "ERROR in " + this.variable[j].name + ": " + getEval2.message;
                    }
                }
            }
        }
        if (this.details_frame != null && string.length() > 0) {
            this.details_frame.lbl_error_message.setText(string);
        }
        for (int l = 0; l < this.variable.length; ++l) {
            if (this.variable[l].is_input && this.variable[l].is_displayed) {
                ++opArgCount;
            }
        }
        this.EnableSpin(opArgCount > 0);
        if (this.details_frame != null) {
            final boolean is_displayed = this.variable[0].is_displayed;
            this.details_frame.lbl_a.setText(is_displayed ? "a" : "");
            this.details_frame.lbl_a_x.setText(is_displayed ? " x= " : "");
            this.details_frame.txt_a_x.setEditable(is_displayed);
            this.details_frame.txt_a_x.enable(is_displayed);
            this.details_frame.txt_a_x.select(0, 0);
            this.details_frame.lbl_a_y.setText(is_displayed ? " y= " : "");
            this.details_frame.txt_a_y.setEditable(is_displayed);
            this.details_frame.txt_a_y.enable(is_displayed);
            this.details_frame.txt_a_y.select(0, 0);
            final boolean is_displayed2 = this.variable[1].is_displayed;
            this.details_frame.lbl_b.setText(is_displayed2 ? "b" : "");
            this.details_frame.lbl_b_x.setText(is_displayed2 ? " x= " : "");
            this.details_frame.txt_b_x.setEditable(is_displayed2);
            this.details_frame.txt_b_x.enable(is_displayed2);
            this.details_frame.txt_b_x.select(0, 0);
            this.details_frame.lbl_b_y.setText(is_displayed2 ? " y= " : "");
            this.details_frame.txt_b_y.setEditable(is_displayed2);
            this.details_frame.txt_b_y.enable(is_displayed2);
            this.details_frame.txt_b_y.select(0, 0);
            final boolean is_displayed3 = this.variable[2].is_displayed;
            this.details_frame.lbl_c.setText(is_displayed3 ? "c" : "");
            this.details_frame.lbl_c_x.setText(is_displayed3 ? " x= " : "");
            this.details_frame.txt_c_x.setEditable(is_displayed3);
            this.details_frame.txt_c_x.enable(is_displayed3);
            this.details_frame.txt_c_x.select(0, 0);
            this.details_frame.lbl_c_y.setText(is_displayed3 ? " y= " : "");
            this.details_frame.txt_c_y.setEditable(is_displayed3);
            this.details_frame.txt_c_y.enable(is_displayed3);
            this.details_frame.txt_c_y.select(0, 0);
            final boolean is_displayed4 = this.variable[3].is_displayed;
            this.details_frame.lbl_d.setText(is_displayed4 ? "d" : "");
            this.details_frame.lbl_d_x.setText(is_displayed4 ? " x= " : "");
            this.details_frame.txt_d_x.setEditable(is_displayed4);
            this.details_frame.txt_d_x.enable(is_displayed4);
            this.details_frame.txt_d_x.select(0, 0);
            this.details_frame.lbl_d_y.setText(is_displayed4 ? " y= " : "");
            this.details_frame.txt_d_y.setEditable(is_displayed4);
            this.details_frame.txt_d_y.enable(is_displayed4);
            this.details_frame.txt_d_y.select(0, 0);
            final boolean is_displayed5 = this.variable[4].is_displayed;
            this.details_frame.lbl_f.setText(is_displayed5 ? "f" : "");
            this.details_frame.lbl_f_x.setText(is_displayed5 ? " x= " : "");
            this.details_frame.txt_f_x.setEditable(false);
            this.details_frame.txt_f_x.enable(is_displayed5);
            this.details_frame.txt_f_x.select(0, 0);
            this.details_frame.lbl_f_y.setText(is_displayed5 ? " y= " : "");
            this.details_frame.txt_f_y.setEditable(false);
            this.details_frame.txt_f_y.enable(is_displayed5);
            this.details_frame.txt_f_y.select(0, 0);
            final boolean is_displayed6 = this.variable[5].is_displayed;
            this.details_frame.lbl_g.setText(is_displayed6 ? "g" : "");
            this.details_frame.lbl_g_x.setText(is_displayed6 ? " x= " : "");
            this.details_frame.txt_g_x.setEditable(false);
            this.details_frame.txt_g_x.enable(is_displayed6);
            this.details_frame.txt_g_x.select(0, 0);
            this.details_frame.lbl_g_y.setText(is_displayed6 ? " y= " : "");
            this.details_frame.txt_g_y.setEditable(false);
            this.details_frame.txt_g_y.enable(is_displayed6);
            this.details_frame.txt_g_y.select(0, 0);
            final boolean is_displayed7 = this.variable[6].is_displayed;
            this.details_frame.lbl_h.setText(is_displayed7 ? "h" : "");
            this.details_frame.lbl_h_x.setText(is_displayed7 ? " x= " : "");
            this.details_frame.txt_h_x.setEditable(false);
            this.details_frame.txt_h_x.enable(is_displayed7);
            this.details_frame.txt_h_x.select(0, 0);
            this.details_frame.lbl_h_y.setText(is_displayed7 ? " y= " : "");
            this.details_frame.txt_h_y.setEditable(false);
            this.details_frame.txt_h_y.enable(is_displayed7);
            this.details_frame.txt_h_y.select(0, 0);
            final boolean is_displayed8 = this.variable[7].is_displayed;
            this.details_frame.lbl_k.setText(is_displayed8 ? "k" : "");
            this.details_frame.lbl_k_x.setText(is_displayed8 ? " x= " : "");
            this.details_frame.txt_k_x.setEditable(false);
            this.details_frame.txt_k_x.enable(is_displayed8);
            this.details_frame.txt_k_x.select(0, 0);
            this.details_frame.lbl_k_y.setText(is_displayed8 ? " y= " : "");
            this.details_frame.txt_k_y.setEditable(false);
            this.details_frame.txt_k_y.enable(is_displayed8);
            this.details_frame.txt_k_y.select(0, 0);
            final boolean is_displayed9 = this.variable[8].is_displayed;
            this.details_frame.lbl_m.setText(is_displayed9 ? "m" : "");
            this.details_frame.lbl_m_x.setText(is_displayed9 ? " x= " : "");
            this.details_frame.txt_m_x.setEditable(false);
            this.details_frame.txt_m_x.enable(is_displayed9);
            this.details_frame.txt_m_x.select(0, 0);
            this.details_frame.lbl_m_y.setText(is_displayed9 ? " y= " : "");
            this.details_frame.txt_m_y.setEditable(false);
            this.details_frame.txt_m_y.enable(is_displayed9);
            this.details_frame.txt_m_y.select(0, 0);
            final boolean is_displayed10 = this.variable[9].is_displayed;
            this.details_frame.lbl_n.setText(is_displayed10 ? "n" : "");
            this.details_frame.lbl_n_x.setText(is_displayed10 ? " x= " : "");
            this.details_frame.txt_n_x.setEditable(false);
            this.details_frame.txt_n_x.enable(is_displayed10);
            this.details_frame.txt_n_x.select(0, 0);
            this.details_frame.lbl_n_y.setText(is_displayed10 ? " y= " : "");
            this.details_frame.txt_n_y.setEditable(false);
            this.details_frame.txt_n_y.enable(is_displayed10);
            this.details_frame.txt_n_y.select(0, 0);
        }
        if (this.pnl_ctl != null) {
            this.txt_f_expr.select(0, 0);
            this.txt_g_expr.select(0, 0);
            this.txt_f_expr.setText(this.variable[4].expression);
            this.txt_g_expr.setText(this.variable[5].expression);
            final Complex coordinates = this.variable[13].coordinates;
            if (coordinates != null) {
                if (coordinates.re < 0.5) {
                    this.btn_step.setLabel("Step");
                }
                else {
                    this.btn_step.setLabel("Step " + (int)(coordinates.re + 0.5));
                }
            }
        }
        if (this.details_frame != null) {
            this.details_frame.txt_h_expr.select(0, 0);
            this.details_frame.txt_k_expr.select(0, 0);
            this.details_frame.txt_m_expr.select(0, 0);
            this.details_frame.txt_n_expr.select(0, 0);
            this.details_frame.txt_h_expr.setText(this.variable[6].expression);
            this.details_frame.txt_k_expr.setText(this.variable[7].expression);
            this.details_frame.txt_m_expr.setText(this.variable[8].expression);
            this.details_frame.txt_n_expr.setText(this.variable[9].expression);
            this.details_frame.txt_t_min.select(0, 0);
            this.details_frame.txt_t_max.select(0, 0);
            this.details_frame.txt_t_rep.select(0, 0);
            this.details_frame.txt_t_min.setText(String.valueOf(this.t_minimum));
            this.details_frame.txt_t_max.setText(String.valueOf(this.t_maximum));
            this.details_frame.txt_t_rep.setText(String.valueOf(this.t_count));
        }
        this.pnl_graph.UpdateDisplay();
    }
    
    public int GetOp() {
        return this.pnl_graph.GetOp();
    }
    
    public void SetOp(final int n) {
        if (n < 12) {
            this.variable[4].expression = "";
        }
        if (n >= 13 && n <= 22) {
            for (int i = 4; i <= 9; ++i) {
                this.SetVariableExpression(i, "");
            }
            this.SetSpin(false);
            this.SetPlot(false);
            this.Clear();
            switch (n) {
                case 13: {
                    this.SetVariableExpression(4, "(a+b)/2");
                    this.SetLine(false);
                    break;
                }
                case 14: {
                    this.SetVariableExpression(4, "(cy, cx)");
                    this.SetVariableExpression(5, "(-fx, fy)");
                    this.SetLine(true);
                    break;
                }
                case 15: {
                    this.SetVariableExpression(4, "2x - 1");
                    this.SetVariableExpression(5, "2x^2 + 4x + 1");
                    break;
                }
                case 16: {
                    this.SetVariableExpression(4, "sin(x/ax * pi/2) * ay");
                    this.SetLine(false);
                    break;
                }
                case 17: {
                    this.SetVariableExpression(4, "(cos t, sin t)");
                    this.SetVariableExpression(5, "c + t d");
                    this.SetLine(true);
                    break;
                }
                case 18: {
                    this.SetVariableExpression(4, "(ax cos t + i ay sin t) * b + c");
                    this.SetLine(true);
                    break;
                }
                case 19: {
                    this.SetVariableExpression(4, "i^(4s/5)   {click the Step button}");
                    this.SetVariableExpression(5, "{click the Step button 5 times}");
                    this.SetLine(false);
                    this.SetPlot(true);
                    break;
                }
                case 20: {
                    this.SetVariableExpression(4, "i^(8t(ax+(s/2000),ay/100))");
                    this.SetLine(false);
                    break;
                }
            }
        }
        this.pnl_graph.SetOp(n);
        this.UpdateDisplay();
    }
    
    public void SetZoomDelta(final int n) {
        this.pnl_graph.SetZoomDelta(n);
        if (this.pnl_ctl != null) {
            final int getZoom = this.GetZoom();
            String s;
            if (getZoom < 10000) {
                s = String.valueOf(getZoom);
            }
            else if (getZoom < 10000000) {
                s = getZoom / 1000 + "K";
            }
            else {
                s = getZoom / 1000000 + "M";
            }
            this.lbl_zoom.setText("Zoom: " + s + " X  ");
        }
    }
    
    public void SetZoom(final int n) {
        this.pnl_graph.SetZoom(n);
        this.SetZoomDelta(0);
    }
    
    public int GetZoom() {
        return this.pnl_graph.GetZoom();
    }
    
    public boolean LooksReal(final Complex complex) {
        return Math.abs(complex.im) < 0.5 / this.GetZoom();
    }
    
    public void SetCenterDelta(final int n, final int n2) {
        this.pnl_graph.SetCenterDelta(n, n2);
    }
    
    public void SetCenter(final Complex complex) {
        this.pnl_graph.SetCenter(complex);
    }
    
    public void SetActivePoint(final int n) {
        this.pnl_graph.SetActivePoint(n);
    }
    
    public void DisplayCoordinates(final Complex complex) {
        if (this.pnl_ctl != null) {
            if (complex == null) {
                this.lbl_coordinate_x.setText("x = ");
                this.lbl_coordinate_y.setText("y = ");
                return;
            }
            final int getZoom = this.GetZoom();
            final double log = Math.log(10.0);
            final double floor = Math.floor(Math.exp((Math.floor(Math.log(getZoom) / log) + 2.0) * log) + 0.5);
            int n;
            if (floor > 1.0E9) {
                n = 1000000000;
            }
            else {
                n = (int)floor;
            }
            final double n2 = (complex.re > 0.0) ? 1.0 : ((complex.re < 0.0) ? -1.0 : 0.0);
            final double n3 = (complex.im > 0.0) ? 1.0 : ((complex.im < 0.0) ? -1.0 : 0.0);
            final double n4 = n2 * (Math.floor(Math.abs(complex.re) * n + 0.5) / n);
            final double n5 = n3 * (Math.floor(Math.abs(complex.im) * n + 0.5) / n);
            this.lbl_coordinate_x.setText("x = " + String.valueOf(n4));
            this.lbl_coordinate_y.setText("y = " + String.valueOf(n5));
        }
    }
    
    public Complex GetVariableCoordinates(final int n) {
        Complex coordinates = null;
        if (this.variable[n].is_displayed) {
            coordinates = this.variable[n].coordinates;
        }
        return coordinates;
    }
    
    public void SetVariableCoordinates(final int n, final Complex coordinates) {
        if (this.variable[n].is_displayed) {
            this.variable[n].coordinates = coordinates;
        }
    }
    
    public String GetVariableExpression(final int n) {
        String s = this.variable[n].expression;
        if (this.GetOp() < 12 && n == 4) {
            s = OpExpression(this.GetOp());
        }
        return s;
    }
    
    public void SetVariableExpression(final int n, final String expression) {
        this.variable[n].expression = expression;
    }
    
    public Complex GetAutoplotVariable() {
        return this.variable[12].coordinates;
    }
    
    public void SetAutoplotVariable(final Complex coordinates) {
        this.variable[12].coordinates = coordinates;
    }
    
    public Complex GetAutoincrementVariable() {
        return this.variable[13].coordinates;
    }
    
    public void SetAutoincrementVariable(final Complex coordinates) {
        this.variable[13].coordinates = coordinates;
    }
    
    public void Step(final int n) {
        if (!this.pnl_graph.Painting()) {
            for (int i = 0; i < n; ++i) {
                if (this.variable[4].is_displayed) {
                    this.SetVariableCoordinates(0, this.variable[4].coordinates);
                }
                this.SetAutoincrementVariable(Complex.Number(this.GetAutoincrementVariable().re + 1.0));
                this.pnl_graph.PaintNow();
            }
            this.UpdateDisplay();
        }
    }
    
    public void TextFieldsInput(final PGC_TextFieldFixed pgc_TextFieldFixed) {
        if (this.details_frame != null) {
            final Complex number = Complex.Number();
            final Complex number2 = Complex.Number();
            final Complex number3 = Complex.Number();
            final Complex number4 = Complex.Number();
            this.eval.Evaluate(this.details_frame.txt_a_x.getText());
            if (this.eval.value != null) {
                number.re = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_a_y.getText());
            if (this.eval.value != null) {
                number.im = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_b_x.getText());
            if (this.eval.value != null) {
                number2.re = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_b_y.getText());
            if (this.eval.value != null) {
                number2.im = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_c_x.getText());
            if (this.eval.value != null) {
                number3.re = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_c_y.getText());
            if (this.eval.value != null) {
                number3.im = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_d_x.getText());
            if (this.eval.value != null) {
                number4.re = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_d_y.getText());
            if (this.eval.value != null) {
                number4.im = this.eval.value.re;
            }
            this.SetVariableCoordinates(0, number);
            this.SetVariableCoordinates(1, number2);
            this.SetVariableCoordinates(2, number3);
            this.SetVariableCoordinates(3, number4);
            this.variable[4].expression = this.txt_f_expr.getText();
            this.variable[5].expression = this.txt_g_expr.getText();
            this.variable[6].expression = this.details_frame.txt_h_expr.getText();
            this.variable[7].expression = this.details_frame.txt_k_expr.getText();
            this.variable[8].expression = this.details_frame.txt_m_expr.getText();
            this.variable[9].expression = this.details_frame.txt_n_expr.getText();
            this.eval.Evaluate(this.details_frame.txt_t_min.getText());
            if (this.eval.value != null) {
                this.t_minimum = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_t_max.getText());
            if (this.eval.value != null) {
                this.t_maximum = this.eval.value.re;
            }
            this.eval.Evaluate(this.details_frame.txt_t_rep.getText());
            if (this.eval.value != null) {
                this.t_count = (int)this.eval.value.re;
            }
        }
        if (this.pnl_ctl != null) {
            this.variable[4].expression = this.txt_f_expr.getText();
            this.variable[5].expression = this.txt_g_expr.getText();
        }
        this.UpdateDisplay();
        this.SetZoomDelta(0);
    }
    
    public void TextFieldsOutput(final Complex complex, final Complex complex2, final Complex complex3, final Complex complex4, final Complex complex5, final Complex complex6, final Complex complex7, final Complex complex8, final Complex complex9, final Complex complex10) {
        if (this.details_frame != null) {
            final PGC_TextFieldFixed txt_a_x = this.details_frame.txt_a_x;
            final PGC_TextFieldFixed txt_a_y = this.details_frame.txt_a_y;
            if (complex == null) {
                txt_a_x.setText("");
                txt_a_y.setText("");
            }
            else {
                txt_a_x.setText(String.valueOf(complex.re));
                txt_a_y.setText(String.valueOf(complex.im));
            }
            final PGC_TextFieldFixed txt_b_x = this.details_frame.txt_b_x;
            final PGC_TextFieldFixed txt_b_y = this.details_frame.txt_b_y;
            if (complex2 == null) {
                txt_b_x.setText("");
                txt_b_y.setText("");
            }
            else {
                txt_b_x.setText(String.valueOf(complex2.re));
                txt_b_y.setText(String.valueOf(complex2.im));
            }
            final PGC_TextFieldFixed txt_c_x = this.details_frame.txt_c_x;
            final PGC_TextFieldFixed txt_c_y = this.details_frame.txt_c_y;
            if (complex3 == null) {
                txt_c_x.setText("");
                txt_c_y.setText("");
            }
            else {
                txt_c_x.setText(String.valueOf(complex3.re));
                txt_c_y.setText(String.valueOf(complex3.im));
            }
            final PGC_TextFieldFixed txt_d_x = this.details_frame.txt_d_x;
            final PGC_TextFieldFixed txt_d_y = this.details_frame.txt_d_y;
            if (complex4 == null) {
                txt_d_x.setText("");
                txt_d_y.setText("");
            }
            else {
                txt_d_x.setText(String.valueOf(complex4.re));
                txt_d_y.setText(String.valueOf(complex4.im));
            }
            final PGC_TextFieldFixed txt_f_x = this.details_frame.txt_f_x;
            final PGC_TextFieldFixed txt_f_y = this.details_frame.txt_f_y;
            if (complex5 == null) {
                txt_f_x.setText("");
                txt_f_y.setText("");
            }
            else {
                txt_f_x.setText(String.valueOf(complex5.re));
                txt_f_y.setText(String.valueOf(complex5.im));
            }
            final PGC_TextFieldFixed txt_g_x = this.details_frame.txt_g_x;
            final PGC_TextFieldFixed txt_g_y = this.details_frame.txt_g_y;
            if (complex6 == null) {
                txt_g_x.setText("");
                txt_g_y.setText("");
            }
            else {
                txt_g_x.setText(String.valueOf(complex6.re));
                txt_g_y.setText(String.valueOf(complex6.im));
            }
            final PGC_TextFieldFixed txt_h_x = this.details_frame.txt_h_x;
            final PGC_TextFieldFixed txt_h_y = this.details_frame.txt_h_y;
            if (complex7 == null) {
                txt_h_x.setText("");
                txt_h_y.setText("");
            }
            else {
                txt_h_x.setText(String.valueOf(complex7.re));
                txt_h_y.setText(String.valueOf(complex7.im));
            }
            final PGC_TextFieldFixed txt_k_x = this.details_frame.txt_k_x;
            final PGC_TextFieldFixed txt_k_y = this.details_frame.txt_k_y;
            if (complex8 == null) {
                txt_k_x.setText("");
                txt_k_y.setText("");
            }
            else {
                txt_k_x.setText(String.valueOf(complex8.re));
                txt_k_y.setText(String.valueOf(complex8.im));
            }
            final PGC_TextFieldFixed txt_m_x = this.details_frame.txt_m_x;
            final PGC_TextFieldFixed txt_m_y = this.details_frame.txt_m_y;
            if (complex9 == null) {
                txt_m_x.setText("");
                txt_m_y.setText("");
            }
            else {
                txt_m_x.setText(String.valueOf(complex9.re));
                txt_m_y.setText(String.valueOf(complex9.im));
            }
            final PGC_TextFieldFixed txt_n_x = this.details_frame.txt_n_x;
            final PGC_TextFieldFixed txt_n_y = this.details_frame.txt_n_y;
            if (complex10 == null) {
                txt_n_x.setText("");
                txt_n_y.setText("");
                return;
            }
            txt_n_x.setText(String.valueOf(complex10.re));
            txt_n_y.setText(String.valueOf(complex10.im));
        }
    }
    
    public Image LoadBackgroundImage() {
        Image image = null;
        if (this.option_background_image != null && this.option_background_image.length() > 0) {
            image = this.getImage(this.getCodeBase(), this.option_background_image);
        }
        return image;
    }
    
    private void SetOptions() {
        this.SetOp(this.option_op);
        this.SetCenter(this.option_center);
        this.SetZoom(this.option_zoom);
        this.SetLine(this.option_line);
        this.SetPlot(this.option_plot);
        this.SetSpin(this.option_spin);
        this.SetActivePoint(this.option_active);
    }
    
    public static void main(final String[] main_args) {
        final PGC pgc = new PGC();
        pgc.main_args = main_args;
        pgc.stand_alone = true;
        pgc.init();
        pgc.resize(new Dimension(pgc.option_launch_size_x, pgc.option_launch_size_y));
        pgc.start();
        pgc.MainFrameDetach();
    }
    
    public void init() {
        this.MainFrameDestroy();
        final PGC_Eval getEval = this.GetEval();
        final String parameter = this.getParameter("enable_trace");
        if (parameter != null) {
            PGC.option_enable_trace = (PGC_Utility.IValue(parameter) != 0);
        }
        final String parameter2 = this.getParameter("launch_button");
        if (parameter2 != null) {
            this.option_launch_button = new String(parameter2);
        }
        this.launch_mode = (this.option_launch_button != null && this.option_launch_button.length() > 0);
        final String parameter3 = this.getParameter("launch_size");
        if (parameter3 != null) {
            getEval.Evaluate(parameter3);
            if (getEval.value != null) {
                this.option_launch_size_x = (int)getEval.value.re;
                this.option_launch_size_y = (int)getEval.value.im;
            }
        }
        final String parameter4 = this.getParameter("background_image");
        if (parameter4 != null) {
            this.option_background_image = new String(parameter4);
        }
        final String parameter5 = this.getParameter("control_panel");
        if (parameter5 != null) {
            this.option_control_panel = (PGC_Utility.IValue(parameter5) != 0);
        }
        final String parameter6 = this.getParameter("graph_border");
        if (parameter6 != null) {
            this.option_graph_border = (PGC_Utility.IValue(parameter6) != 0);
        }
        final String parameter7 = this.getParameter("builtin_examples");
        if (parameter7 != null) {
            this.option_builtin_examples = (PGC_Utility.IValue(parameter7) != 0);
        }
        final String parameter8 = this.getParameter("builtin_functions");
        if (parameter8 != null) {
            this.option_builtin_functions = (PGC_Utility.IValue(parameter8) != 0);
        }
        if (!this.option_builtin_functions) {
            this.option_op = 12;
        }
        final String parameter9 = this.getParameter("center");
        if (parameter9 != null) {
            getEval.Evaluate(parameter9);
            if (getEval.value != null) {
                this.option_center = getEval.value;
            }
        }
        final String parameter10 = this.getParameter("zoom");
        if (parameter10 != null) {
            getEval.Evaluate(parameter10);
            if (getEval.value != null) {
                this.option_zoom = (int)getEval.value.re;
            }
        }
        final String parameter11 = this.getParameter("line");
        if (parameter11 != null) {
            this.option_line = (PGC_Utility.IValue(parameter11) != 0);
        }
        final String parameter12 = this.getParameter("plot");
        if (parameter12 != null) {
            this.option_plot = (PGC_Utility.IValue(parameter12) != 0);
        }
        final String parameter13 = this.getParameter("spin");
        if (parameter13 != null) {
            this.option_spin = (PGC_Utility.IValue(parameter13) != 0);
        }
        final String parameter14 = this.getParameter("active");
        if (parameter14 != null) {
            if (parameter14.equalsIgnoreCase("a")) {
                this.option_active = 0;
            }
            if (parameter14.equalsIgnoreCase("b")) {
                this.option_active = 1;
            }
            if (parameter14.equalsIgnoreCase("c")) {
                this.option_active = 2;
            }
            if (parameter14.equalsIgnoreCase("d")) {
                this.option_active = 3;
            }
        }
        final String parameter15 = this.getParameter("t_minimum");
        if (parameter15 != null) {
            getEval.Evaluate(parameter15);
            if (getEval.value != null) {
                this.t_minimum = getEval.value.re;
            }
        }
        final String parameter16 = this.getParameter("t_maximum");
        if (parameter16 != null) {
            getEval.Evaluate(parameter16);
            if (getEval.value != null) {
                this.t_maximum = getEval.value.re;
            }
        }
        final String parameter17 = this.getParameter("t_count");
        if (parameter17 != null) {
            getEval.Evaluate(parameter17);
            if (getEval.value != null) {
                this.t_count = (int)getEval.value.re;
            }
        }
        this.DefineVariables();
        for (int i = 0; i < this.variable.length; ++i) {
            if (this.variable[i].is_input) {
                final String parameter18 = this.getParameter(this.variable[i].name);
                if (parameter18 != null) {
                    getEval.Evaluate(parameter18);
                    if (getEval.value != null) {
                        this.variable[i].coordinates = getEval.value;
                    }
                }
            }
            else if (this.variable[i].is_function) {
                final String parameter19 = this.getParameter(this.variable[i].name);
                if (parameter19 != null) {
                    this.variable[i].expression = parameter19;
                    if (i == 4 && parameter19.length() > 0) {
                        this.option_op = 12;
                    }
                }
            }
        }
        if (this.launch_mode) {
            this.LayoutLaunchButton();
            this.setLayout(new BorderLayout());
            this.add("Center", this.pnl_root_launch);
            return;
        }
        this.LayoutCalculator();
        this.setLayout(new BorderLayout());
        this.add("Center", this.pnl_root_calculator);
        this.SetOptions();
    }
    
    public void start() {
    }
    
    public void stop() {
        this.MainFrameDestroy();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.id == 1001) {
            if (event.target == this.btn_details) {
                this.Details();
                return true;
            }
            if (event.target == this.btn_step) {
                this.Step(1);
                return true;
            }
            if (event.target == this.btn_plot_clear) {
                this.Clear();
                return true;
            }
            if (event.target == this.btn_about) {
                this.About();
                return true;
            }
            if (event.target == this.btn_attach_detach) {
                this.AttachDetach();
                return true;
            }
            if (event.target == this.btn_launch) {
                this.MainFrameLaunch();
                return true;
            }
            if (event.target == this.btn_zoomout) {
                this.SetZoomDelta(-1);
                return true;
            }
            if (event.target == this.btn_zoomin) {
                this.SetZoomDelta(1);
                return true;
            }
            if (event.target == this.chk_line) {
                this.CheckLine((Boolean)event.arg);
                return true;
            }
            if (event.target == this.chk_spin) {
                this.CheckSpin((Boolean)event.arg);
                return true;
            }
            if (event.target == this.chk_plot) {
                this.CheckPlot((Boolean)event.arg);
                return true;
            }
        }
        return super.action(event, o);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final boolean mouseDown = super.mouseDown(event, n, n2);
        if (event.target.getClass().toString().equals("class java.awt.Panel")) {
            this.GraphFocus();
        }
        return mouseDown;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b;
        if (n == 10 || n == 27) {
            this.GraphFocus();
            b = true;
        }
        else if (event.target.getClass().toString().equals("class PGC_ButtonFixed")) {
            b = this.pnl_graph.keyDown(event, n);
        }
        else if (event.target.getClass().toString().equals("class java.awt.Checkbox")) {
            b = this.pnl_graph.keyDown(event, n);
        }
        else if (event.target.getClass().toString().equals("class java.awt.Panel")) {
            b = this.pnl_graph.keyDown(event, n);
        }
        else {
            b = super.keyDown(event, n);
        }
        return b;
    }
    
    public String getParameter(final String s) {
        String parameter;
        if (this.stand_alone) {
            parameter = null;
        }
        else {
            parameter = super.getParameter(s);
        }
        return parameter;
    }
    
    public PGC() {
        this.eval = new PGC_Eval(this);
        this.stand_alone = false;
        this.launch_mode = false;
        this.option_launch_button = "";
        this.option_launch_size_x = 560;
        this.option_launch_size_y = 440;
        this.option_control_panel = true;
        this.option_graph_border = true;
        this.option_builtin_functions = true;
        this.option_builtin_examples = true;
        this.option_center = Complex.Number(0.0);
        this.option_zoom = 80;
        this.option_line = true;
        this.option_plot = false;
        this.option_spin = false;
        this.option_op = 1;
        this.variable = new PGC_Variable[14];
        this.t_minimum = -10.0;
        this.t_maximum = 10.0;
        this.t_count = 200;
    }
    
    static {
        PGC.trace_frame = null;
        PGC.option_enable_trace = true;
        PGC.color_panel = new Color(204, 204, 204);
        PGC.color_background = new Color(255, 255, 255);
        PGC.color_axis = new Color(0, 0, 0);
        PGC.color_grid = new Color(236, 236, 236);
        PGC.color_origin_connector_line = new Color(204, 204, 204);
        PGC.color_input_point = new Color(0, 0, 0);
        PGC.color_input_label = new Color(102, 102, 102);
        PGC.color_func_f_point = new Color(0, 0, 255);
        PGC.color_func_f_label = new Color(0, 0, 255);
        PGC.color_func_f_plot = new Color(153, 153, 255);
        PGC.color_func_g_point = new Color(204, 0, 0);
        PGC.color_func_g_label = new Color(204, 0, 0);
        PGC.color_func_g_plot = new Color(255, 153, 153);
        PGC.color_func_h_point = new Color(0, 153, 0);
        PGC.color_func_h_label = new Color(0, 153, 0);
        PGC.color_func_h_plot = new Color(0, 204, 0);
        PGC.color_func_k_point = new Color(204, 0, 204);
        PGC.color_func_k_label = new Color(204, 0, 204);
        PGC.color_func_k_plot = new Color(204, 102, 204);
        PGC.color_func_m_point = new Color(0, 153, 153);
        PGC.color_func_m_label = new Color(0, 153, 153);
        PGC.color_func_m_plot = new Color(102, 204, 204);
        PGC.color_func_n_point = new Color(102, 102, 102);
        PGC.color_func_n_label = new Color(102, 102, 102);
        PGC.color_func_n_plot = new Color(153, 153, 153);
        PGC.color_lightray = new Color(255, 204, 0);
        PGC.color_shadow = new Color(255, 102, 153);
        PGC.color_shadow_label = new Color(255, 0, 0);
        PGC.color_3d_dark = new Color(153, 153, 153);
        PGC.color_3d_light = new Color(236, 236, 236);
        PGC.component_tiny_button_size = new Dimension(24, -1);
        PGC.component_normal_button_size = new Dimension(64, -1);
        PGC.component_number_text_size = new Dimension(164, -1);
        PGC.component_wide_text_size = new Dimension(360, -1);
        PGC.component_full_text_size = new Dimension(388, -1);
    }
}
