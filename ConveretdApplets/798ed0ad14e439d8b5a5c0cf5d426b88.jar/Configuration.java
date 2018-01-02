import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Configuration
{
    private static boolean autoServerURL;
    public static String jar;
    public static final String with_package;
    public static String applet_class;
    public static final String manualURL;
    private static String url_base;
    public static final String url_save_download;
    public static final Color colorBarraPlotter;
    public static final Color colorPlotterToolBar;
    public static final Color colorMainToolBar;
    public static final Color colorEastToolBar;
    public static final Color colorTooltip;
    static Class Z;
    
    public static final String choose_save_request_exec_file() {
        return OmegaSystem.dual ? "dual.wiris.servlets.Save" : "wiris.servlets.Save";
    }
    
    public static final String getPackage() {
        final String name = ((Configuration.Z == null) ? (Configuration.Z = Z("OmegaSystem")) : Configuration.Z).getName();
        final int lastIndex = name.lastIndexOf(".");
        if (lastIndex < 0) {
            return "";
        }
        final int lastIndex2 = name.lastIndexOf(".", lastIndex - 1);
        if (lastIndex2 >= 0) {
            return "/" + Utils.substitute(name.substring(0, lastIndex2), '.', "/") + "/";
        }
        return "";
    }
    
    public static final String url_exec() {
        return Utils.urlCat(Configuration.url_base, "servlet");
    }
    
    public static final String url_calculate_exec() {
        return Utils.urlCat(url_exec(), OmegaSystem.dual ? "dual.wiris.servlets.Calculate" : "wiris.servlets.Calculate");
    }
    
    public static final String url_code_base() {
        return Configuration.autoServerURL ? OmegaSystem.code_base : "";
    }
    
    private static final Class Z(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Configuration.autoServerURL = true;
        Configuration.jar = "wrs_net_en.jar";
        with_package = getPackage();
        Configuration.applet_class = "WirisApplet_net_en.class";
        colorBarraPlotter = new Color(255, 150, 0);
        colorPlotterToolBar = new Color(255, 204, 204);
        colorMainToolBar = new Color(16772812);
        colorEastToolBar = new Color(16772812);
        colorTooltip = new Color(14344191);
        Configuration.url_base = (Configuration.autoServerURL ? OmegaSystem.get_url_base() : "http://");
        if (Configuration.autoServerURL && (Configuration.url_base == null || !Configuration.url_base.trim().startsWith("http"))) {
            Configuration.url_base = "http://localhost:8080/wiris";
        }
        manualURL = Configuration.url_base + "/manual" + (OmegaSystem.dual ? ".dual" : "") + "/" + "en" + "/";
        url_save_download = Utils.urlCat(Configuration.url_base, "servlet/");
    }
}
