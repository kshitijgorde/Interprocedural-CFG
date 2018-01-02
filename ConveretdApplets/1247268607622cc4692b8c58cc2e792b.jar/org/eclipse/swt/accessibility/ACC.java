// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

public class ACC
{
    public static final int STATE_NORMAL = 0;
    public static final int STATE_SELECTED = 2;
    public static final int STATE_SELECTABLE = 2097152;
    public static final int STATE_MULTISELECTABLE = 16777216;
    public static final int STATE_FOCUSED = 4;
    public static final int STATE_FOCUSABLE = 1048576;
    public static final int STATE_PRESSED = 8;
    public static final int STATE_CHECKED = 16;
    public static final int STATE_EXPANDED = 512;
    public static final int STATE_COLLAPSED = 1024;
    public static final int STATE_HOTTRACKED = 128;
    public static final int STATE_BUSY = 2048;
    public static final int STATE_READONLY = 64;
    public static final int STATE_INVISIBLE = 32768;
    public static final int STATE_OFFSCREEN = 65536;
    public static final int STATE_SIZEABLE = 131072;
    public static final int STATE_LINKED = 4194304;
    public static final int STATE_DISABLED = 1;
    public static final int STATE_ACTIVE = 67108864;
    public static final int STATE_SINGLELINE = 134217728;
    public static final int STATE_MULTILINE = 268435456;
    public static final int STATE_REQUIRED = 33554432;
    public static final int STATE_INVALID_ENTRY = 536870912;
    public static final int STATE_SUPPORTS_AUTOCOMPLETION = 1073741824;
    public static final int ROLE_CLIENT_AREA = 10;
    public static final int ROLE_WINDOW = 9;
    public static final int ROLE_MENUBAR = 2;
    public static final int ROLE_MENU = 11;
    public static final int ROLE_MENUITEM = 12;
    public static final int ROLE_SEPARATOR = 21;
    public static final int ROLE_TOOLTIP = 13;
    public static final int ROLE_SCROLLBAR = 3;
    public static final int ROLE_DIALOG = 18;
    public static final int ROLE_LABEL = 41;
    public static final int ROLE_PUSHBUTTON = 43;
    public static final int ROLE_CHECKBUTTON = 44;
    public static final int ROLE_RADIOBUTTON = 45;
    public static final int ROLE_SPLITBUTTON = 62;
    public static final int ROLE_COMBOBOX = 46;
    public static final int ROLE_TEXT = 42;
    public static final int ROLE_TOOLBAR = 22;
    public static final int ROLE_LIST = 33;
    public static final int ROLE_LISTITEM = 34;
    public static final int ROLE_TABLE = 24;
    public static final int ROLE_TABLECELL = 29;
    public static final int ROLE_TABLECOLUMNHEADER = 25;
    public static final int ROLE_TABLECOLUMN = 25;
    public static final int ROLE_TABLEROWHEADER = 26;
    public static final int ROLE_TREE = 35;
    public static final int ROLE_TREEITEM = 36;
    public static final int ROLE_TABFOLDER = 60;
    public static final int ROLE_TABITEM = 37;
    public static final int ROLE_PROGRESSBAR = 48;
    public static final int ROLE_SLIDER = 51;
    public static final int ROLE_LINK = 30;
    public static final int ROLE_ALERT = 8;
    public static final int ROLE_ANIMATION = 54;
    public static final int ROLE_CANVAS = 1025;
    public static final int ROLE_COLUMN = 27;
    public static final int ROLE_DOCUMENT = 15;
    public static final int ROLE_GRAPHIC = 40;
    public static final int ROLE_GROUP = 20;
    public static final int ROLE_ROW = 28;
    public static final int ROLE_SPINBUTTON = 52;
    public static final int ROLE_STATUSBAR = 23;
    public static final int ROLE_CHECKMENUITEM = 1027;
    public static final int ROLE_RADIOMENUITEM = 1073;
    public static final int ROLE_CLOCK = 61;
    public static final int ROLE_CALENDAR = 47;
    public static final int ROLE_DATETIME = 1029;
    public static final int ROLE_FOOTER = 1038;
    public static final int ROLE_FORM = 1040;
    public static final int ROLE_HEADER = 1043;
    public static final int ROLE_HEADING = 1044;
    public static final int ROLE_PAGE = 1053;
    public static final int ROLE_PARAGRAPH = 1054;
    public static final int ROLE_SECTION = 1060;
    public static final int CHILDID_SELF = -1;
    public static final int CHILDID_NONE = -2;
    public static final int CHILDID_MULTIPLE = -3;
    public static final int CHILDID_CHILD_AT_INDEX = -4;
    public static final int CHILDID_CHILD_INDEX = -5;
    public static final int VISIBLE = 1;
    public static final int INSERT = 0;
    public static final int DELETE = 1;
    public static final int TEXT_INSERT = 0;
    public static final int TEXT_DELETE = 1;
    public static final String OK = "OK";
    public static final int TEXT_BOUNDARY_CHAR = 0;
    public static final int TEXT_BOUNDARY_WORD = 1;
    public static final int TEXT_BOUNDARY_SENTENCE = 2;
    public static final int TEXT_BOUNDARY_PARAGRAPH = 3;
    public static final int TEXT_BOUNDARY_LINE = 4;
    public static final int TEXT_BOUNDARY_ALL = 5;
    public static final int SCROLL_TYPE_TOP_LEFT = 0;
    public static final int SCROLL_TYPE_BOTTOM_RIGHT = 1;
    public static final int SCROLL_TYPE_TOP_EDGE = 2;
    public static final int SCROLL_TYPE_BOTTOM_EDGE = 3;
    public static final int SCROLL_TYPE_LEFT_EDGE = 4;
    public static final int SCROLL_TYPE_RIGHT_EDGE = 5;
    public static final int SCROLL_TYPE_ANYWHERE = 6;
    public static final int SCROLL_TYPE_POINT = 7;
    public static final int EVENT_SELECTION_CHANGED = 32777;
    public static final int EVENT_TEXT_SELECTION_CHANGED = 32788;
    public static final int EVENT_STATE_CHANGED = 32778;
    public static final int EVENT_LOCATION_CHANGED = 32779;
    public static final int EVENT_NAME_CHANGED = 32780;
    public static final int EVENT_DESCRIPTION_CHANGED = 32781;
    public static final int EVENT_VALUE_CHANGED = 32782;
    public static final int EVENT_DOCUMENT_LOAD_COMPLETE = 261;
    public static final int EVENT_DOCUMENT_LOAD_STOPPED = 262;
    public static final int EVENT_DOCUMENT_RELOAD = 263;
    public static final int EVENT_PAGE_CHANGED = 273;
    public static final int EVENT_SECTION_CHANGED = 274;
    public static final int EVENT_ACTION_CHANGED = 256;
    public static final int EVENT_HYPERLINK_START_INDEX_CHANGED = 269;
    public static final int EVENT_HYPERLINK_END_INDEX_CHANGED = 264;
    public static final int EVENT_HYPERLINK_ANCHOR_COUNT_CHANGED = 265;
    public static final int EVENT_HYPERLINK_SELECTED_LINK_CHANGED = 266;
    public static final int EVENT_HYPERLINK_ACTIVATED = 267;
    public static final int EVENT_HYPERTEXT_LINK_SELECTED = 268;
    public static final int EVENT_HYPERTEXT_LINK_COUNT_CHANGED = 271;
    public static final int EVENT_ATTRIBUTE_CHANGED = 512;
    public static final int EVENT_TABLE_CAPTION_CHANGED = 515;
    public static final int EVENT_TABLE_COLUMN_DESCRIPTION_CHANGED = 516;
    public static final int EVENT_TABLE_COLUMN_HEADER_CHANGED = 517;
    public static final int EVENT_TABLE_CHANGED = 518;
    public static final int EVENT_TABLE_ROW_DESCRIPTION_CHANGED = 519;
    public static final int EVENT_TABLE_ROW_HEADER_CHANGED = 520;
    public static final int EVENT_TABLE_SUMMARY_CHANGED = 521;
    public static final int EVENT_TEXT_ATTRIBUTE_CHANGED = 522;
    public static final int EVENT_TEXT_CARET_MOVED = 283;
    public static final int EVENT_TEXT_COLUMN_CHANGED = 285;
    public static final int EVENT_TEXT_CHANGED = 524;
    public static final int RELATION_CONTROLLED_BY = 0;
    public static final int RELATION_CONTROLLER_FOR = 1;
    public static final int RELATION_DESCRIBED_BY = 2;
    public static final int RELATION_DESCRIPTION_FOR = 3;
    public static final int RELATION_EMBEDDED_BY = 4;
    public static final int RELATION_EMBEDS = 5;
    public static final int RELATION_FLOWS_FROM = 6;
    public static final int RELATION_FLOWS_TO = 7;
    public static final int RELATION_LABEL_FOR = 8;
    public static final int RELATION_LABELLED_BY = 9;
    public static final int RELATION_MEMBER_OF = 10;
    public static final int RELATION_NODE_CHILD_OF = 11;
    public static final int RELATION_PARENT_WINDOW_OF = 12;
    public static final int RELATION_POPUP_FOR = 13;
    public static final int RELATION_SUBWINDOW_OF = 14;
}