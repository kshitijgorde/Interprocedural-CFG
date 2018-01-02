import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Parameters
{
    public static String copyright;
    public static Applet applet;
    public static String chartDataType;
    public static boolean PopupOK;
    public static boolean CursorOK;
    private static long start_time;
    private static boolean Debug;
    public static boolean xCursor;
    public static boolean yCursor;
    public static Color defaultBGColor;
    public static Color defaultFGColor;
    public static Color defaultButtonColor;
    public static Color chartBGColor;
    public static Color chartFGColor;
    public static Color chartBorderColor;
    public static Color chartCursorColor;
    public static Color chartCursorBGColor;
    public static Color chartCursorFGColor;
    public static Color chartTrendColor;
    public static int chartBorder;
    public static Color dialogBGColor;
    public static Color dialogFGColor;
    public static Color chartGridColor;
    public static Color chartLegendColor;
    public static int indicatorWidth;
    public static Color[] indicatorColor;
    public static String urlQuote;
    public static Font labelFont;
    public static Font titleFont;
    public static int dataHeight;
    public static String dataFile;
    public static String paramString;
    public final String[] Message;
    
    static {
        Parameters.copyright = "  ";
        Parameters.indicatorColor = new Color[] { new Color(0, 128, 128), new Color(128, 0, 128), new Color(128, 128, 0), new Color(0, 255, 255), new Color(255, 0, 255), new Color(255, 255, 0), new Color(0, 0, 128), new Color(128, 0, 0), new Color(0, 128, 0) };
        Parameters.urlQuote = "quote.php3?icode=";
    }
    
    public Parameters() {
        this.Message = new String[] { "Welcome to IRISChart.", "The best technical analysis system on the Internet.", "Click on the right mouse button for menu.", "Initialising system. Please Wait....", Parameters.copyright };
    }
    
    public Parameters(final Applet applet) {
        this.Message = new String[] { "Welcome to IRISChart.", "The best technical analysis system on the Internet.", "Click on the right mouse button for menu.", "Initialising system. Please Wait....", Parameters.copyright };
        Parameters.start_time = System.currentTimeMillis();
        Parameters.applet = applet;
        Parameters.Debug = false;
        Parameters.PopupOK = true;
        Parameters.CursorOK = true;
        Parameters.xCursor = true;
        Parameters.yCursor = true;
        Parameters.defaultFGColor = this.getColor(Parameters.applet.getParameter("defaultFgColor"), new Color(0, 0, 128));
        Parameters.defaultBGColor = this.getColor(Parameters.applet.getParameter("defaultBgColor"), new Color(250, 255, 200));
        Parameters.defaultButtonColor = this.getColor(Parameters.applet.getParameter("ButtonColor"), new Color(212, 212, 212));
        Parameters.chartFGColor = this.getColor(Parameters.applet.getParameter("chartFgColor"), Parameters.defaultFGColor);
        Parameters.chartBGColor = this.getColor(Parameters.applet.getParameter("chartBgColor"), Parameters.defaultBGColor);
        Parameters.chartBorderColor = this.getColor(Parameters.applet.getParameter("chartBorderColor"), Parameters.defaultBGColor.darker());
        Parameters.chartCursorColor = this.getColor(Parameters.applet.getParameter("chartCursorColor"), Color.red);
        Parameters.chartCursorBGColor = this.getColor(Parameters.applet.getParameter("chartCursorBgColor"), Parameters.chartBGColor);
        Parameters.chartCursorFGColor = this.getColor(Parameters.applet.getParameter("chartCursorFgColor"), Parameters.chartFGColor);
        Parameters.chartTrendColor = this.getColor(Parameters.applet.getParameter("chartTrendColor"), Color.red);
        Parameters.chartGridColor = this.getColor(Parameters.applet.getParameter("chartGridColor"), Parameters.chartFGColor);
        Parameters.chartLegendColor = this.getColor(Parameters.applet.getParameter("chartLegendColor"), Parameters.chartFGColor);
        Parameters.dialogFGColor = this.getColor(Parameters.applet.getParameter("dialogFgColor"), Parameters.defaultFGColor);
        Parameters.dialogBGColor = this.getColor(Parameters.applet.getParameter("dialogBgColor"), Parameters.defaultBGColor);
        Parameters.chartDataType = Parameters.applet.getParameter("dataType");
        final String parameter = Parameters.applet.getParameter("dataHeight");
        Parameters.dataHeight = ((parameter != null) ? Integer.parseInt(parameter) : 0);
        final String parameter2 = Parameters.applet.getParameter("copyright");
        Parameters.copyright = ((parameter2 != null) ? parameter2 : Parameters.copyright);
        final String parameter3 = Parameters.applet.getParameter("dataFile");
        Parameters.dataFile = ((parameter3 != null) ? parameter3 : "quote.php3");
        final String parameter4 = Parameters.applet.getParameter("paramString");
        Parameters.paramString = ((parameter4 != null) ? parameter4 : "?icode=ASSOCHEM");
        if (Parameters.applet.getParameter("CursorOK") != null) {
            if (Parameters.applet.getParameter("CursorOK").toString().trim().toUpperCase().equals("FALSE")) {
                Parameters.CursorOK = false;
            }
            else {
                Parameters.CursorOK = true;
            }
        }
        if (Parameters.applet.getParameter("PopupOK") != null) {
            if (Parameters.applet.getParameter("PopupOK").toString().trim().toUpperCase().equals("FALSE")) {
                Parameters.PopupOK = false;
            }
            else {
                Parameters.PopupOK = true;
            }
        }
        Parameters.labelFont = new Font("sanserif", 0, 12);
        Parameters.titleFont = new Font("sanserif", 1, 14);
        Parameters.chartBorder = 3;
        Parameters.indicatorWidth = 1;
    }
    
    public Color getColor(final String s, final Color color) {
        Color color2;
        if (s == null) {
            color2 = color;
        }
        else {
            try {
                color2 = new Color(Integer.parseInt(s, 16));
            }
            catch (NumberFormatException ex) {
                color2 = color;
            }
        }
        return color2;
    }
}
