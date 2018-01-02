import java.util.Enumeration;
import java.applet.Applet;
import java.util.Hashtable;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Properties
{
    public static final c a;
    public static int b;
    public static Hashtable c;
    
    private static final void a(final String s, final String s2, final String s3) {
        final Object put = c.c.put(s, new Object(s2, s3) {
            public String a = a;
            public String b = b;
        });
        if (put != null) {
            final Object object = put;
            System.err.println("WhProperties Declaration Error: " + s + " defined twice" + "\nfirst: " + object.a + " " + object.b + "\nsecond: " + s2 + " " + s3);
        }
    }
    
    public c(final Applet applet) {
        this.clear();
        this.a(applet);
    }
    
    public void a(final Applet applet) {
        final Enumeration<String> keys = c.c.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String parameter;
            if ((parameter = applet.getParameter(s)) != null) {
                ((Hashtable<String, String>)this).put(s, parameter);
                if (!s.endsWith("1")) {
                    continue;
                }
                final String substring = s.substring(0, s.length() - 1);
                String parameter2;
                for (int n = 2; (parameter2 = applet.getParameter(substring + n)) != null; ++n) {
                    ((Hashtable<String, String>)this).put(substring + n, parameter2);
                }
            }
        }
    }
    
    public c() {
    }
    
    static {
        a = new c();
        c.b = 68;
        c.c = new Hashtable(c.b);
        a("ImageTunnel", "root-relative path with ?", "resource tunnel");
        a("BackgroundColor", "color", "background color");
        a("FlyoverBackgroundColor", "color", "flyover background color");
        a("FlyoverHeaderColor", "color", "flyover header color");
        a("GridColor", "color", "grid color");
        a("BrandingOn", "boolean", "show branding");
        a("ShowColorBar", "boolean", "show color bar");
        a("ShowGridLines", "boolean", "show grid lines");
        a("ShowLabels", "boolean", "show labels");
        a("PackOption", "boolean", "pack display");
        a("GridThickness", "nonnegative integer", "grid pixel width");
        a("GroupSeparator", "nonnegative integer", "group separator pixel width");
        a("SubmapSeparator", "nonnegative integer", "submap separator pixel width");
        a("NumCols", "positive integer", "column count");
        a("CellWidth", "comma-separated list", "heatmap cell width overrides");
        a("FlyoverWait", "nonnegative double", "flyover popup delay in seconds");
        a("Heatmap", "string", "heatmap id");
        a("InitToken", "string", "initialization token");
        a("Server", "string", "service server host");
        a("Port", "positive integer", "service server port");
        a("Tunnel", "root-relative path with ?", "protocol tunnel");
        a("KeyList1", "comma-separated list", "heatmap key overrides");
        a("FlyoverDOPList1", "digit list", "flyover drop-out priority list");
        a("ClickThroughLabel1", "string", "click-through label for left-click menus");
        a("Splash", "absolute/relative url", "splash image");
        a("Logo", "absolute/relative url", "logo image");
        a("BackButton", "absolute/relative url", "back button image");
        a("ForwardButton", "absolute/relative url", "forward button image");
        a("AnalyticsButton", "absolute/relative url", "analytics button image");
        a("ColumnsButton", "absolute/relative url", "columns button image");
        a("OptionsButton", "absolute/relative url", "options button image");
        a("HelpButton", "absolute/relative url", "help button image");
        a("NegColor", "color", "negative color");
        a("MidColor", "color", "middle color");
        a("PosColor", "color", "positive color");
        a("LabelFont", "font", "label font");
        a("UndefinedColor", "color", "undefined value color");
        a("UndefinedString", "string", "undefined value string");
        a("DefaultColorField", "string", "default color analytic field");
        a("DefaultGroupField", "string", "default grouping field");
        a("DefaultSortField", "string, boolean", "default sorting field, ascending");
        a("HelpURL", "absolute/relative url", "help documentation");
        a("AboutURL", "absolute/relative url", "About documentation");
        a("Title", "string", "heatmap title");
        a("RowLabelWidth", "nonnegative integer", "row label character width");
        a("WarningBannerHeight", "nonnegative integer", "expected warning banner height");
        a("TimeOut", "nonnegative double", "time out in seconds (0 for none)");
        a("NumRequestAttempts", "positive integer", "number of attempts to request service response");
        a("MaxReRequestDelay", "positive double", "delay to back off failed requests in seconds");
        a("ShowErrorDialogs", "boolean", "asserted to show error dialogs");
        a("TearOffWidth", "nonnegative integer", "launched tear-off width");
        a("TearOffHeight", "nonnegative integer", "launched tear-off height");
        a("TearOffX", "integer", "launched tear-off x-coordinate");
        a("TearOffY", "integer", "launched tear-off y-coordinate");
        a("Caption", "string", "launch button text");
        a("UseLeftClickMenus", "boolean", "asserted to use left-click menus");
        a("MinStringWidth", "nonnegative integer/percentage", "minimum display characters/pixel-width percentage");
        a("NegMosaicColor", "color", "negative mosaic color");
        a("ShowConnectionStatus", "boolean", "asserted to show connection status");
        a("ShowConnectionInformation", "boolean", "asserted to show connection information");
        a("StatusString", "string", "text to the left of indicator light");
        a("StatusFlyoverString", "string", "string to left of status on rollover");
        a("LeftBranding", "absolute/relative url", "left branding image");
        a("CenterBranding", "absolute/relative url", "center branding image");
        a("RightBranding", "absolute/relative url", "right branding image");
        a("BrandingBackground", "absolute/relative url", "branding background color");
        a("HighlightRate", "nonnegative double", "cell highlight rate");
        a("History", "serialized object", "browsing history");
    }
}
