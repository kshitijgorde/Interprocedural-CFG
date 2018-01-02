// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

public class AccessibleRole extends AccessibleBundle
{
    public static final AccessibleRole ALERT;
    public static final AccessibleRole COLUMN_HEADER;
    public static final AccessibleRole COMBO_BOX;
    public static final AccessibleRole DESKTOP_ICON;
    public static final AccessibleRole INTERNAL_FRAME;
    public static final AccessibleRole DESKTOP_PANE;
    public static final AccessibleRole OPTION_PANE;
    public static final AccessibleRole WINDOW;
    public static final AccessibleRole FRAME;
    public static final AccessibleRole DIALOG;
    public static final AccessibleRole COLOR_CHOOSER;
    public static final AccessibleRole DIRECTORY_PANE;
    public static final AccessibleRole FILE_CHOOSER;
    public static final AccessibleRole FILLER;
    public static final AccessibleRole LABEL;
    public static final AccessibleRole ROOT_PANE;
    public static final AccessibleRole GLASS_PANE;
    public static final AccessibleRole LAYERED_PANE;
    public static final AccessibleRole LIST;
    public static final AccessibleRole MENU_BAR;
    public static final AccessibleRole POPUP_MENU;
    public static final AccessibleRole MENU;
    public static final AccessibleRole MENU_ITEM;
    public static final AccessibleRole SEPARATOR;
    public static final AccessibleRole PAGE_TAB_LIST;
    public static final AccessibleRole PAGE_TAB;
    public static final AccessibleRole PANEL;
    public static final AccessibleRole PROGRESS_BAR;
    public static final AccessibleRole PASSWORD_TEXT;
    public static final AccessibleRole PUSH_BUTTON;
    public static final AccessibleRole TOGGLE_BUTTON;
    public static final AccessibleRole CHECK_BOX;
    public static final AccessibleRole RADIO_BUTTON;
    public static final AccessibleRole ROW_HEADER;
    public static final AccessibleRole SCROLL_PANE;
    public static final AccessibleRole SCROLL_BAR;
    public static final AccessibleRole VIEWPORT;
    public static final AccessibleRole SLIDER;
    public static final AccessibleRole SPLIT_PANE;
    public static final AccessibleRole TABLE;
    public static final AccessibleRole TEXT;
    public static final AccessibleRole TREE;
    public static final AccessibleRole TOOL_BAR;
    public static final AccessibleRole TOOL_TIP;
    public static final AccessibleRole AWT_COMPONENT;
    public static final AccessibleRole SWING_COMPONENT;
    public static final AccessibleRole UNKNOWN;
    
    static {
        ALERT = new AccessibleRole("alert");
        COLUMN_HEADER = new AccessibleRole("columnheader");
        COMBO_BOX = new AccessibleRole("combobox");
        DESKTOP_ICON = new AccessibleRole("desktopicon");
        INTERNAL_FRAME = new AccessibleRole("internalframe");
        DESKTOP_PANE = new AccessibleRole("desktoppane");
        OPTION_PANE = new AccessibleRole("optionpane");
        WINDOW = new AccessibleRole("window");
        FRAME = new AccessibleRole("frame");
        DIALOG = new AccessibleRole("dialog");
        COLOR_CHOOSER = new AccessibleRole("colorchooser");
        DIRECTORY_PANE = new AccessibleRole("directorypane");
        FILE_CHOOSER = new AccessibleRole("filechooser");
        FILLER = new AccessibleRole("filler");
        LABEL = new AccessibleRole("label");
        ROOT_PANE = new AccessibleRole("rootpane");
        GLASS_PANE = new AccessibleRole("glasspane");
        LAYERED_PANE = new AccessibleRole("layeredpane");
        LIST = new AccessibleRole("list");
        MENU_BAR = new AccessibleRole("menubar");
        POPUP_MENU = new AccessibleRole("popupmenu");
        MENU = new AccessibleRole("menu");
        MENU_ITEM = new AccessibleRole("menuitem");
        SEPARATOR = new AccessibleRole("separator");
        PAGE_TAB_LIST = new AccessibleRole("pagetablist");
        PAGE_TAB = new AccessibleRole("pagetab");
        PANEL = new AccessibleRole("panel");
        PROGRESS_BAR = new AccessibleRole("progressbar");
        PASSWORD_TEXT = new AccessibleRole("passwordtext");
        PUSH_BUTTON = new AccessibleRole("pushbutton");
        TOGGLE_BUTTON = new AccessibleRole("togglebutton");
        CHECK_BOX = new AccessibleRole("checkbox");
        RADIO_BUTTON = new AccessibleRole("radiobutton");
        ROW_HEADER = new AccessibleRole("rowheader");
        SCROLL_PANE = new AccessibleRole("scrollpane");
        SCROLL_BAR = new AccessibleRole("scrollbar");
        VIEWPORT = new AccessibleRole("viewport");
        SLIDER = new AccessibleRole("slider");
        SPLIT_PANE = new AccessibleRole("splitpane");
        TABLE = new AccessibleRole("table");
        TEXT = new AccessibleRole("text");
        TREE = new AccessibleRole("tree");
        TOOL_BAR = new AccessibleRole("toolbar");
        TOOL_TIP = new AccessibleRole("tooltip");
        AWT_COMPONENT = new AccessibleRole("awtcomponent");
        SWING_COMPONENT = new AccessibleRole("swingcomponent");
        UNKNOWN = new AccessibleRole("unknown");
    }
    
    protected AccessibleRole(final String key) {
        super.key = key;
    }
}
