// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle implements $X5B
{
    private static final Object[][] contents;
    
    static {
        contents = new Object[][] { { "NO_SELECTION", "(no selection)" }, { "LOAD_MAP", "loading map..." }, { "NO_MAPFILE", "Can't load map from file:" }, { "ABOUT", "about BlinkMap.." }, { "MANUAL", "manual" }, { "SHOW_IN_BROWSER", "display within browser" }, { "FLIP_FRONT", "show BlinkMap window" }, { "SHOW_IN_WINDOW", "extra window" }, { "SAT_INFO", "The application is shown within it's own window now. You can close the window to display the application within this browser \n \n When you close this browser- window the application- window will be closed, too." }, { "MARK_ON_MAP", "show markers on map" }, { "LOOPX", "This version has been expired.\n \nPlease update this version from {0}." }, { "LBL_SMALLER", "smaller" }, { "LBL_BIGGER", "bigger" } };
    }
    
    public Object[][] getContents() {
        return Resources.contents;
    }
}
