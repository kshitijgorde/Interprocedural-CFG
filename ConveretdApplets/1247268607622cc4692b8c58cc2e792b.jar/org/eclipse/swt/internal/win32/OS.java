// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

import org.eclipse.swt.internal.Library;
import org.eclipse.swt.internal.C;

public class OS extends C
{
    public static final boolean IsWin32s;
    public static final boolean IsWin95;
    public static final boolean IsWinNT;
    public static final boolean IsWinCE;
    public static final boolean IsPPC;
    public static final boolean IsHPC;
    public static final boolean IsSP;
    public static final boolean IsDBLocale;
    public static final boolean IsUnicode;
    public static final int WIN32_MAJOR;
    public static final int WIN32_MINOR;
    public static final int WIN32_VERSION;
    public static final int COMCTL32_MAJOR;
    public static final int COMCTL32_MINOR;
    public static final int COMCTL32_VERSION;
    public static final int SHELL32_MAJOR;
    public static final int SHELL32_MINOR;
    public static final int SHELL32_VERSION;
    public static final String NO_MANIFEST = "org.eclipse.swt.internal.win32.OS.NO_MANIFEST";
    public static final int VER_PLATFORM_WIN32s = 0;
    public static final int VER_PLATFORM_WIN32_WINDOWS = 1;
    public static final int VER_PLATFORM_WIN32_NT = 2;
    public static final int VER_PLATFORM_WIN32_CE = 3;
    public static final int HEAP_ZERO_MEMORY = 8;
    public static final int ACTCTX_FLAG_RESOURCE_NAME_VALID = 8;
    public static final int ACTCTX_FLAG_SET_PROCESS_DEFAULT = 16;
    public static final int MANIFEST_RESOURCE_ID = 2;
    public static final int SM_DBCSENABLED = 42;
    public static final int SM_IMMENABLED = 82;
    public static final int LANG_KOREAN = 18;
    public static final int MAX_PATH = 260;
    static final int SYS_COLOR_INDEX_FLAG;
    public static final int ABS_DOWNDISABLED = 8;
    public static final int ABS_DOWNHOT = 6;
    public static final int ABS_DOWNNORMAL = 5;
    public static final int ABS_DOWNPRESSED = 7;
    public static final int ABS_LEFTDISABLED = 12;
    public static final int ABS_LEFTHOT = 10;
    public static final int ABS_LEFTNORMAL = 9;
    public static final int ABS_LEFTPRESSED = 11;
    public static final int ABS_RIGHTDISABLED = 16;
    public static final int ABS_RIGHTHOT = 14;
    public static final int ABS_RIGHTNORMAL = 13;
    public static final int ABS_RIGHTPRESSED = 15;
    public static final int ABS_UPDISABLED = 4;
    public static final int ABS_UPHOT = 2;
    public static final int ABS_UPNORMAL = 1;
    public static final int ABS_UPPRESSED = 3;
    public static final int AC_SRC_OVER = 0;
    public static final int AC_SRC_ALPHA = 1;
    public static final int ALTERNATE = 1;
    public static final int ASSOCF_NOTRUNCATE = 32;
    public static final int ASSOCF_INIT_IGNOREUNKNOWN = 1024;
    public static final int ASSOCSTR_COMMAND = 1;
    public static final int ASSOCSTR_DEFAULTICON = 15;
    public static final int ASSOCSTR_FRIENDLYAPPNAME = 4;
    public static final int ASSOCSTR_FRIENDLYDOCNAME = 3;
    public static final int AW_SLIDE = 262144;
    public static final int AW_ACTIVATE = 131072;
    public static final int AW_BLEND = 524288;
    public static final int AW_HIDE = 65536;
    public static final int AW_CENTER = 16;
    public static final int AW_HOR_POSITIVE = 1;
    public static final int AW_HOR_NEGATIVE = 2;
    public static final int AW_VER_POSITIVE = 4;
    public static final int AW_VER_NEGATIVE = 8;
    public static final int ATTR_INPUT = 0;
    public static final int ATTR_TARGET_CONVERTED = 1;
    public static final int ATTR_CONVERTED = 2;
    public static final int ATTR_TARGET_NOTCONVERTED = 3;
    public static final int ATTR_INPUT_ERROR = 4;
    public static final int ATTR_FIXEDCONVERTED = 5;
    public static final int BCM_FIRST = 5632;
    public static final int BCM_GETIDEALSIZE = 5633;
    public static final int BCM_GETIMAGELIST = 5635;
    public static final int BCM_GETNOTE = 5642;
    public static final int BCM_GETNOTELENGTH = 5643;
    public static final int BCM_SETIMAGELIST = 5634;
    public static final int BCM_SETNOTE = 5641;
    public static final int BDR_RAISEDOUTER = 1;
    public static final int BDR_SUNKENOUTER = 2;
    public static final int BDR_RAISEDINNER = 4;
    public static final int BDR_SUNKENINNER = 8;
    public static final int BDR_OUTER = 3;
    public static final int BDR_INNER = 12;
    public static final int BDR_RAISED = 5;
    public static final int BDR_SUNKEN = 10;
    public static final int BFFM_INITIALIZED = 1;
    public static final int BFFM_SETSELECTION;
    public static final int BFFM_VALIDATEFAILED;
    public static final int BFFM_VALIDATEFAILEDW = 4;
    public static final int BFFM_VALIDATEFAILEDA = 3;
    public static final int BF_ADJUST = 8192;
    public static final int BF_LEFT = 1;
    public static final int BF_TOP = 2;
    public static final int BF_RIGHT = 4;
    public static final int BF_BOTTOM = 8;
    public static final int BF_RECT = 15;
    public static final int BIF_EDITBOX = 16;
    public static final int BIF_NEWDIALOGSTYLE = 64;
    public static final int BIF_RETURNONLYFSDIRS = 1;
    public static final int BIF_VALIDATE = 32;
    public static final int BITSPIXEL = 12;
    public static final int BI_BITFIELDS = 3;
    public static final int BI_RGB = 0;
    public static final int BLACKNESS = 66;
    public static final int BLACK_BRUSH = 4;
    public static final int BUTTON_IMAGELIST_ALIGN_LEFT = 0;
    public static final int BUTTON_IMAGELIST_ALIGN_RIGHT = 1;
    public static final int BUTTON_IMAGELIST_ALIGN_CENTER = 4;
    public static final int BM_CLICK = 245;
    public static final int BM_GETCHECK = 240;
    public static final int BM_SETCHECK = 241;
    public static final int BM_SETIMAGE = 247;
    public static final int BM_SETSTYLE = 244;
    public static final int BN_CLICKED = 0;
    public static final int BN_DOUBLECLICKED = 5;
    public static final int BPBF_COMPATIBLEBITMAP = 0;
    public static final int BPBF_DIB = 1;
    public static final int BPBF_TOPDOWNDIB = 2;
    public static final int BPBF_TOPDOWNMONODIB = 3;
    public static final int BPPF_ERASE = 1;
    public static final int BPPF_NOCLIP = 2;
    public static final int BPPF_NONCLIENT = 4;
    public static final int BP_PUSHBUTTON = 1;
    public static final int BP_RADIOBUTTON = 2;
    public static final int BP_CHECKBOX = 3;
    public static final int BP_GROUPBOX = 4;
    public static final int BST_CHECKED = 1;
    public static final int BST_INDETERMINATE = 2;
    public static final int BST_UNCHECKED = 0;
    public static final int BS_3STATE = 5;
    public static final int BS_BITMAP = 128;
    public static final int BS_CENTER = 768;
    public static final int BS_CHECKBOX = 2;
    public static final int BS_COMMANDLINK = 14;
    public static final int BS_DEFPUSHBUTTON = 1;
    public static final int BS_FLAT = 32768;
    public static final int BS_GROUPBOX = 7;
    public static final int BS_ICON = 64;
    public static final int BS_LEFT = 256;
    public static final int BS_MULTILINE = 8192;
    public static final int BS_NOTIFY = 16384;
    public static final int BS_OWNERDRAW = 11;
    public static final int BS_PATTERN = 3;
    public static final int BS_PUSHBUTTON = 0;
    public static final int BS_PUSHLIKE = 4096;
    public static final int BS_RADIOBUTTON = 4;
    public static final int BS_RIGHT = 512;
    public static final int BS_SOLID = 0;
    public static final int BTNS_AUTOSIZE = 16;
    public static final int BTNS_BUTTON = 0;
    public static final int BTNS_CHECK = 2;
    public static final int BTNS_CHECKGROUP = 6;
    public static final int BTNS_DROPDOWN = 8;
    public static final int BTNS_GROUP = 4;
    public static final int BTNS_SEP = 1;
    public static final int BTNS_SHOWTEXT = 64;
    public static final int CBN_DROPDOWN = 7;
    public static final int CBN_EDITCHANGE = 5;
    public static final int CBN_KILLFOCUS = 4;
    public static final int CBN_SELCHANGE = 1;
    public static final int CBN_SETFOCUS = 3;
    public static final int CBS_AUTOHSCROLL = 64;
    public static final int CBS_DROPDOWN = 2;
    public static final int CBS_DROPDOWNLIST = 3;
    public static final int CBS_CHECKEDNORMAL = 5;
    public static final int CBS_MIXEDNORMAL = 9;
    public static final int CBS_NOINTEGRALHEIGHT = 1024;
    public static final int CBS_SIMPLE = 1;
    public static final int CBS_UNCHECKEDNORMAL = 1;
    public static final int CBS_CHECKEDDISABLED = 8;
    public static final int CBS_CHECKEDHOT = 6;
    public static final int CBS_CHECKEDPRESSED = 7;
    public static final int CBS_MIXEDDISABLED = 12;
    public static final int CBS_MIXEDHOT = 10;
    public static final int CBS_MIXEDPRESSED = 11;
    public static final int CBS_UNCHECKEDDISABLED = 4;
    public static final int CBS_UNCHECKEDHOT = 2;
    public static final int CBS_UNCHECKEDPRESSED = 3;
    public static final int CB_ADDSTRING = 323;
    public static final int CB_DELETESTRING = 324;
    public static final int CB_ERR = -1;
    public static final int CB_ERRSPACE = -2;
    public static final int CB_FINDSTRINGEXACT = 344;
    public static final int CB_GETCOUNT = 326;
    public static final int CB_GETCURSEL = 327;
    public static final int CB_GETDROPPEDCONTROLRECT = 338;
    public static final int CB_GETDROPPEDSTATE = 343;
    public static final int CB_GETDROPPEDWIDTH = 351;
    public static final int CB_GETEDITSEL = 320;
    public static final int CB_GETHORIZONTALEXTENT = 349;
    public static final int CB_GETITEMHEIGHT = 340;
    public static final int CB_GETLBTEXT = 328;
    public static final int CB_GETLBTEXTLEN = 329;
    public static final int CB_INSERTSTRING = 330;
    public static final int CB_LIMITTEXT = 321;
    public static final int CB_RESETCONTENT = 331;
    public static final int CB_SELECTSTRING = 333;
    public static final int CB_SETCURSEL = 334;
    public static final int CB_SETDROPPEDWIDTH = 352;
    public static final int CB_SETEDITSEL = 322;
    public static final int CB_SETHORIZONTALEXTENT = 350;
    public static final int CB_SETITEMHEIGHT = 339;
    public static final int CB_SHOWDROPDOWN = 335;
    public static final int CBXS_NORMAL = 1;
    public static final int CBXS_HOT = 2;
    public static final int CBXS_PRESSED = 3;
    public static final int CBXS_DISABLED = 4;
    public static final int CCHDEVICENAME = 32;
    public static final int CCHFORMNAME = 32;
    public static final int CCHILDREN_SCROLLBAR = 5;
    public static final int CCM_FIRST = 8192;
    public static final int CCM_SETBKCOLOR = 8193;
    public static final int CCM_SETVERSION = 8199;
    public static final int CCS_NODIVIDER = 64;
    public static final int CCS_NORESIZE = 4;
    public static final int CCS_VERT = 128;
    public static final int CC_ANYCOLOR = 256;
    public static final int CC_ENABLEHOOK = 16;
    public static final int CC_FULLOPEN = 2;
    public static final int CC_RGBINIT = 1;
    public static final int CDDS_POSTERASE = 4;
    public static final int CDDS_POSTPAINT = 2;
    public static final int CDDS_PREERASE = 3;
    public static final int CDDS_PREPAINT = 1;
    public static final int CDDS_ITEM = 65536;
    public static final int CDDS_ITEMPOSTPAINT = 65538;
    public static final int CDDS_ITEMPREPAINT = 65537;
    public static final int CDDS_SUBITEM = 131072;
    public static final int CDDS_SUBITEMPOSTPAINT = 196610;
    public static final int CDDS_SUBITEMPREPAINT = 196609;
    public static final int CDIS_SELECTED = 1;
    public static final int CDIS_GRAYED = 2;
    public static final int CDIS_DISABLED = 4;
    public static final int CDIS_CHECKED = 8;
    public static final int CDIS_FOCUS = 16;
    public static final int CDIS_DEFAULT = 32;
    public static final int CDIS_DROPHILITED = 4096;
    public static final int CDIS_HOT = 64;
    public static final int CDIS_MARKED = 128;
    public static final int CDIS_INDETERMINATE = 256;
    public static final int CDM_FIRST = 1124;
    public static final int CDM_GETSPEC = 1124;
    public static final int CDN_FIRST = -601;
    public static final int CDN_SELCHANGE = -602;
    public static final int CDRF_DODEFAULT = 0;
    public static final int CDRF_DOERASE = 8;
    public static final int CDRF_NEWFONT = 2;
    public static final int CDRF_NOTIFYITEMDRAW = 32;
    public static final int CDRF_NOTIFYPOSTERASE = 64;
    public static final int CDRF_NOTIFYPOSTPAINT = 16;
    public static final int CDRF_NOTIFYSUBITEMDRAW = 32;
    public static final int CDRF_SKIPDEFAULT = 4;
    public static final int CDRF_SKIPPOSTPAINT = 256;
    public static final int CERT_SIMPLE_NAME_STR = 1;
    public static final int CFE_AUTOCOLOR = 1073741824;
    public static final int CFE_ITALIC = 2;
    public static final int CFE_STRIKEOUT = 8;
    public static final int CFE_UNDERLINE = 4;
    public static final int CFM_BOLD = 1;
    public static final int CFM_CHARSET = 134217728;
    public static final int CFM_COLOR = 1073741824;
    public static final int CFM_FACE = 536870912;
    public static final int CFM_ITALIC = 2;
    public static final int CFM_SIZE = Integer.MIN_VALUE;
    public static final int CFM_STRIKEOUT = 8;
    public static final int CFM_UNDERLINE = 4;
    public static final int CFM_WEIGHT = 4194304;
    public static final int CFS_POINT = 2;
    public static final int CFS_RECT = 1;
    public static final int CFS_CANDIDATEPOS = 64;
    public static final int CFS_EXCLUDE = 128;
    public static final int CF_EFFECTS = 256;
    public static final int CF_INITTOLOGFONTSTRUCT = 64;
    public static final int CF_SCREENFONTS = 1;
    public static final int CF_TEXT = 1;
    public static final int CF_UNICODETEXT = 13;
    public static final int CF_USESTYLE = 128;
    public static final int CLR_DEFAULT = -16777216;
    public static final int CLR_INVALID = -1;
    public static final int CLR_NONE = -1;
    public static final int CLSCTX_INPROC_SERVER = 1;
    public static final int CSIDL_APPDATA = 26;
    public static final int CSIDL_LOCAL_APPDATA = 28;
    public static final int COLORONCOLOR = 3;
    public static final int COLOR_3DDKSHADOW;
    public static final int COLOR_3DFACE;
    public static final int COLOR_3DHIGHLIGHT;
    public static final int COLOR_3DHILIGHT;
    public static final int COLOR_3DLIGHT;
    public static final int COLOR_3DSHADOW;
    public static final int COLOR_ACTIVECAPTION;
    public static final int COLOR_BTNFACE;
    public static final int COLOR_BTNHIGHLIGHT;
    public static final int COLOR_BTNSHADOW;
    public static final int COLOR_BTNTEXT;
    public static final int COLOR_CAPTIONTEXT;
    public static final int COLOR_GRADIENTACTIVECAPTION;
    public static final int COLOR_GRADIENTINACTIVECAPTION;
    public static final int COLOR_GRAYTEXT;
    public static final int COLOR_HIGHLIGHT;
    public static final int COLOR_HIGHLIGHTTEXT;
    public static final int COLOR_HOTLIGHT;
    public static final int COLOR_INACTIVECAPTION;
    public static final int COLOR_INACTIVECAPTIONTEXT;
    public static final int COLOR_INFOBK;
    public static final int COLOR_INFOTEXT;
    public static final int COLOR_MENU;
    public static final int COLOR_MENUTEXT;
    public static final int COLOR_SCROLLBAR;
    public static final int COLOR_WINDOW;
    public static final int COLOR_WINDOWFRAME;
    public static final int COLOR_WINDOWTEXT;
    public static final int COMPLEXREGION = 3;
    public static final int CP_ACP = 0;
    public static final int CP_UTF8 = 65001;
    public static final int CP_DROPDOWNBUTTON = 1;
    public static final int CP_INSTALLED = 1;
    public static final int CPS_COMPLETE = 1;
    public static final int CS_BYTEALIGNWINDOW = 8192;
    public static final int CS_DBLCLKS = 8;
    public static final int CS_DROPSHADOW = 131072;
    public static final int CS_GLOBALCLASS = 16384;
    public static final int CS_HREDRAW = 2;
    public static final int CS_VREDRAW = 1;
    public static final int CS_OWNDC = 32;
    public static final int CW_USEDEFAULT = Integer.MIN_VALUE;
    public static final String DATETIMEPICK_CLASS = "SysDateTimePick32";
    public static final int DATE_LONGDATE = 2;
    public static final int DATE_SHORTDATE = 1;
    public static final int DATE_YEARMONTH = 8;
    public static final int DCX_CACHE = 2;
    public static final int DCX_CLIPCHILDREN = 8;
    public static final int DCX_CLIPSIBLINGS = 16;
    public static final int DCX_INTERSECTRGN = 128;
    public static final int DCX_WINDOW = 1;
    public static final int DEFAULT_CHARSET = 1;
    public static final int DEFAULT_GUI_FONT = 17;
    public static final int DFCS_BUTTONCHECK = 0;
    public static final int DFCS_CHECKED = 1024;
    public static final int DFCS_FLAT = 16384;
    public static final int DFCS_INACTIVE = 256;
    public static final int DFCS_PUSHED = 512;
    public static final int DFCS_SCROLLDOWN = 1;
    public static final int DFCS_SCROLLLEFT = 2;
    public static final int DFCS_SCROLLRIGHT = 3;
    public static final int DFCS_SCROLLUP = 0;
    public static final int DFC_BUTTON = 4;
    public static final int DFC_SCROLL = 3;
    public static final int DIB_RGB_COLORS = 0;
    public static final int DISP_E_EXCEPTION = -2147352567;
    public static final int DI_NORMAL = 3;
    public static final int DI_NOMIRROR = 16;
    public static final int DLGC_BUTTON = 8192;
    public static final int DLGC_HASSETSEL = 8;
    public static final int DLGC_STATIC = 256;
    public static final int DLGC_WANTALLKEYS = 4;
    public static final int DLGC_WANTARROWS = 1;
    public static final int DLGC_WANTCHARS = 128;
    public static final int DLGC_WANTTAB = 2;
    public static final short DMCOLLATE_FALSE = 0;
    public static final short DMCOLLATE_TRUE = 1;
    public static final int DM_SETDEFID = 1025;
    public static final int DM_COLLATE = 32768;
    public static final int DM_COPIES = 256;
    public static final int DM_DUPLEX = 4096;
    public static final int DM_ORIENTATION = 1;
    public static final short DMORIENT_PORTRAIT = 1;
    public static final short DMORIENT_LANDSCAPE = 2;
    public static final short DMDUP_SIMPLEX = 1;
    public static final short DMDUP_VERTICAL = 2;
    public static final short DMDUP_HORIZONTAL = 3;
    public static final int DSS_DISABLED = 32;
    public static final int DSTINVERT = 5570569;
    public static final int DST_BITMAP = 4;
    public static final int DST_ICON = 3;
    public static final int DT_BOTTOM = 8;
    public static final int DT_CALCRECT = 1024;
    public static final int DT_CENTER = 1;
    public static final int DT_EDITCONTROL = 8192;
    public static final int DT_EXPANDTABS = 64;
    public static final int DT_ENDELLIPSIS = 32768;
    public static final int DT_HIDEPREFIX = 1048576;
    public static final int DT_LEFT = 0;
    public static final int DT_NOPREFIX = 2048;
    public static final int DT_RASPRINTER = 2;
    public static final int DT_RIGHT = 2;
    public static final int DT_RTLREADING = 131072;
    public static final int DT_SINGLELINE = 32;
    public static final int DT_TOP = 0;
    public static final int DT_VCENTER = 4;
    public static final int DT_WORDBREAK = 16;
    public static final int DTM_FIRST = 4096;
    public static final int DTM_GETSYSTEMTIME = 4097;
    public static final int DTM_GETIDEALSIZE = 4111;
    public static final int DTM_SETFORMAT;
    public static final int DTM_SETSYSTEMTIME = 4098;
    public static final int DTN_FIRST = -760;
    public static final int DTN_DATETIMECHANGE = -759;
    public static final int DTN_CLOSEUP = -753;
    public static final int DTN_DROPDOWN = -754;
    public static final int DTS_LONGDATEFORMAT = 4;
    public static final int DTS_SHORTDATECENTURYFORMAT = 12;
    public static final int DTS_SHORTDATEFORMAT = 0;
    public static final int DTS_TIMEFORMAT = 9;
    public static final int DTS_UPDOWN = 1;
    public static final int DWM_BB_ENABLE = 1;
    public static final int DWM_BB_BLURREGION = 2;
    public static final int DWM_BB_TRANSITIONONMAXIMIZED = 4;
    public static final int E_POINTER = -2147467261;
    public static final int EBP_NORMALGROUPBACKGROUND = 5;
    public static final int EBP_NORMALGROUPCOLLAPSE = 6;
    public static final int EBP_NORMALGROUPEXPAND = 7;
    public static final int EBP_NORMALGROUPHEAD = 8;
    public static final int EBNGC_NORMAL = 1;
    public static final int EBNGC_HOT = 2;
    public static final int EBNGC_PRESSED = 3;
    public static final int EBP_HEADERBACKGROUND = 1;
    public static final int EC_LEFTMARGIN = 1;
    public static final int EC_RIGHTMARGIN = 2;
    public static final int ECOOP_AND = 3;
    public static final int ECOOP_OR = 2;
    public static final int ECO_AUTOHSCROLL = 128;
    public static final int EDGE_RAISED = 5;
    public static final int EDGE_SUNKEN = 10;
    public static final int EDGE_ETCHED = 6;
    public static final int EDGE_BUMP = 9;
    public static final int ELF_VENDOR_SIZE = 4;
    public static final int EM_CANUNDO = 198;
    public static final int EM_CHARFROMPOS = 215;
    public static final int EM_DISPLAYBAND = 1075;
    public static final int EM_GETFIRSTVISIBLELINE = 206;
    public static final int EM_GETLIMITTEXT = 213;
    public static final int EM_GETLINE = 196;
    public static final int EM_GETLINECOUNT = 186;
    public static final int EM_GETMARGINS = 212;
    public static final int EM_GETPASSWORDCHAR = 210;
    public static final int EM_GETSCROLLPOS = 1245;
    public static final int EM_GETSEL = 176;
    public static final int EM_LIMITTEXT = 197;
    public static final int EM_LINEFROMCHAR = 201;
    public static final int EM_LINEINDEX = 187;
    public static final int EM_LINELENGTH = 193;
    public static final int EM_LINESCROLL = 182;
    public static final int EM_POSFROMCHAR = 214;
    public static final int EM_REPLACESEL = 194;
    public static final int EM_SCROLLCARET = 183;
    public static final int EM_SETBKGNDCOLOR = 1091;
    public static final int EM_SETLIMITTEXT = 197;
    public static final int EM_SETMARGINS = 211;
    public static final int EM_SETOPTIONS = 1101;
    public static final int EM_SETPARAFORMAT = 1095;
    public static final int EM_SETPASSWORDCHAR = 204;
    public static final int EM_SETCUEBANNER = 5377;
    public static final int EM_SETREADONLY = 207;
    public static final int EM_SETRECT = 179;
    public static final int EM_SETSEL = 177;
    public static final int EM_SETTABSTOPS = 203;
    public static final int EM_UNDO = 199;
    public static final int EMR_EXTCREATEFONTINDIRECTW = 82;
    public static final int EMR_EXTTEXTOUTW = 84;
    public static final int EN_ALIGN_LTR_EC = 1792;
    public static final int EN_ALIGN_RTL_EC = 1793;
    public static final int EN_CHANGE = 768;
    public static final int EP_EDITTEXT = 1;
    public static final int ERROR_NO_MORE_ITEMS = 259;
    public static final int ESB_DISABLE_BOTH = 3;
    public static final int ESB_ENABLE_BOTH = 0;
    public static final int ES_AUTOHSCROLL = 128;
    public static final int ES_AUTOVSCROLL = 64;
    public static final int ES_CENTER = 1;
    public static final int ES_MULTILINE = 4;
    public static final int ES_NOHIDESEL = 256;
    public static final int ES_PASSWORD = 32;
    public static final int ES_READONLY = 2048;
    public static final int ES_RIGHT = 2;
    public static final int ETO_CLIPPED = 4;
    public static final int ETS_NORMAL = 1;
    public static final int ETS_HOT = 2;
    public static final int ETS_SELECTED = 3;
    public static final int ETS_DISABLED = 4;
    public static final int ETS_FOCUSED = 5;
    public static final int ETS_READONLY = 6;
    public static final int EVENT_OBJECT_FOCUS = 32773;
    public static final int EVENT_OBJECT_LOCATIONCHANGE = 32779;
    public static final int EVENT_OBJECT_SELECTIONWITHIN = 32777;
    public static final int EVENT_OBJECT_VALUECHANGE = 32782;
    public static final short FADF_FIXEDSIZE = 16;
    public static final short FADF_HAVEVARTYPE = 128;
    public static final int FALT = 16;
    public static final int FCONTROL = 8;
    public static final int FE_FONTSMOOTHINGCLEARTYPE = 2;
    public static final int FEATURE_DISABLE_NAVIGATION_SOUNDS = 21;
    public static final int FILE_ATTRIBUTE_DIRECTORY = 16;
    public static final int FILE_ATTRIBUTE_NORMAL = 128;
    public static final int FILE_MAP_READ = 4;
    public static final int FLICKDIRECTION_RIGHT = 0;
    public static final int FLICKDIRECTION_UPRIGHT = 1;
    public static final int FLICKDIRECTION_UP = 2;
    public static final int FLICKDIRECTION_UPLEFT = 3;
    public static final int FLICKDIRECTION_LEFT = 4;
    public static final int FLICKDIRECTION_DOWNLEFT = 5;
    public static final int FLICKDIRECTION_DOWN = 6;
    public static final int FLICKDIRECTION_DOWNRIGHT = 7;
    public static final int FLICKDIRECTION_INVALID = 8;
    public static final int FNERR_INVALIDFILENAME = 12290;
    public static final int FNERR_BUFFERTOOSMALL = 12291;
    public static final int FOF_SILENT = 4;
    public static final int FOF_NOCONFIRMATION = 16;
    public static final int FOF_NOCONFIRMMKDIR = 512;
    public static final int FOF_NOERRORUI = 1024;
    public static final int FOF_NO_UI = 1556;
    public static final int FORMAT_MESSAGE_ALLOCATE_BUFFER = 256;
    public static final int FORMAT_MESSAGE_FROM_SYSTEM = 4096;
    public static final int FORMAT_MESSAGE_IGNORE_INSERTS = 512;
    public static final int FR_PRIVATE = 16;
    public static final int FSHIFT = 4;
    public static final int FVIRTKEY = 1;
    public static final int GBS_NORMAL = 1;
    public static final int GBS_DISABLED = 2;
    public static final int GCP_REORDER = 2;
    public static final int GCP_GLYPHSHAPE = 16;
    public static final int GCP_CLASSIN = 524288;
    public static final int GCP_LIGATE = 32;
    public static final int GCS_COMPSTR = 8;
    public static final int GCS_RESULTSTR = 2048;
    public static final int GCS_COMPATTR = 16;
    public static final int GCS_COMPCLAUSE = 32;
    public static final int GCS_CURSORPOS = 128;
    public static final int GDT_VALID = 0;
    public static final int GET_FEATURE_FROM_PROCESS = 2;
    public static final int GF_BEGIN = 1;
    public static final int GF_INERTIA = 2;
    public static final int GF_END = 4;
    public static final int GGI_MARK_NONEXISTING_GLYPHS = 1;
    public static final int GID_BEGIN = 1;
    public static final int GID_END = 2;
    public static final int GID_ZOOM = 3;
    public static final int GID_PAN = 4;
    public static final int GID_ROTATE = 5;
    public static final int GID_TWOFINGERTAP = 6;
    public static final int GID_PRESSANDTAP = 7;
    public static final int GLPS_CLOSED = 1;
    public static final int GLPS_OPENED = 2;
    public static final int GM_ADVANCED = 2;
    public static final int GMDI_USEDISABLED = 1;
    public static final int GMEM_FIXED = 0;
    public static final int GMEM_ZEROINIT = 64;
    public static final int GN_CONTEXTMENU = 1000;
    public static final int GPTR = 64;
    public static final int GRADIENT_FILL_RECT_H = 0;
    public static final int GRADIENT_FILL_RECT_V = 1;
    public static final int GTL_NUMBYTES = 16;
    public static final int GTL_NUMCHARS = 8;
    public static final int GTL_PRECISE = 2;
    public static final int GT_DEFAULT = 0;
    public static final int GUI_16BITTASK = 32;
    public static final int GUI_CARETBLINKING = 1;
    public static final int GUI_INMENUMODE = 4;
    public static final int GUI_INMOVESIZE = 2;
    public static final int GUI_POPUPMENUMODE = 16;
    public static final int GUI_SYSTEMMENUMODE = 8;
    public static final int GWL_EXSTYLE = -20;
    public static final int GWL_ID = -12;
    public static final int GWL_HWNDPARENT = -8;
    public static final int GWL_STYLE = -16;
    public static final int GWL_USERDATA = -21;
    public static final int GWL_WNDPROC = -4;
    public static final int GWLP_ID = -12;
    public static final int GWLP_HWNDPARENT = -8;
    public static final int GWLP_USERDATA = -21;
    public static final int GWLP_WNDPROC = -4;
    public static final int GW_CHILD = 5;
    public static final int GW_HWNDFIRST = 0;
    public static final int GW_HWNDLAST = 1;
    public static final int GW_HWNDNEXT = 2;
    public static final int GW_HWNDPREV = 3;
    public static final int GW_OWNER = 4;
    public static final int HBMMENU_CALLBACK = -1;
    public static final int HCBT_CREATEWND = 3;
    public static final int HCF_HIGHCONTRASTON = 1;
    public static final int HDF_BITMAP = 8192;
    public static final int HDF_BITMAP_ON_RIGHT = 4096;
    public static final int HDF_CENTER = 2;
    public static final int HDF_JUSTIFYMASK = 3;
    public static final int HDF_IMAGE = 2048;
    public static final int HDF_LEFT = 0;
    public static final int HDF_RIGHT = 1;
    public static final int HDF_SORTUP = 1024;
    public static final int HDF_SORTDOWN = 512;
    public static final int HDI_BITMAP = 16;
    public static final int HDI_IMAGE = 32;
    public static final int HDI_ORDER = 128;
    public static final int HDI_TEXT = 2;
    public static final int HDI_WIDTH = 1;
    public static final int HDI_FORMAT = 4;
    public static final int HDM_FIRST = 4608;
    public static final int HDM_DELETEITEM = 4610;
    public static final int HDM_GETBITMAPMARGIN = 4629;
    public static final int HDM_GETITEMCOUNT = 4608;
    public static final int HDM_GETITEMA = 4611;
    public static final int HDM_GETITEMW = 4619;
    public static final int HDM_GETITEM;
    public static final int HDM_GETITEMRECT = 4615;
    public static final int HDM_GETORDERARRAY = 4625;
    public static final int HDM_HITTEST = 4614;
    public static final int HDM_INSERTITEMA = 4609;
    public static final int HDM_INSERTITEMW = 4618;
    public static final int HDM_INSERTITEM;
    public static final int HDM_LAYOUT = 4613;
    public static final int HDM_ORDERTOINDEX = 4623;
    public static final int HDM_SETIMAGELIST = 4616;
    public static final int HDM_SETITEMA = 4612;
    public static final int HDM_SETITEMW = 4620;
    public static final int HDM_SETITEM;
    public static final int HDM_SETORDERARRAY = 4626;
    public static final int HDN_FIRST = -300;
    public static final int HDN_BEGINDRAG = -310;
    public static final int HDN_BEGINTRACK;
    public static final int HDN_BEGINTRACKW = -326;
    public static final int HDN_BEGINTRACKA = -306;
    public static final int HDN_DIVIDERDBLCLICKA = -305;
    public static final int HDN_DIVIDERDBLCLICKW = -325;
    public static final int HDN_DIVIDERDBLCLICK;
    public static final int HDN_ENDDRAG = -311;
    public static final int HDN_ITEMCHANGED;
    public static final int HDN_ITEMCHANGEDW = -321;
    public static final int HDN_ITEMCHANGEDA = -301;
    public static final int HDN_ITEMCHANGINGW = -320;
    public static final int HDN_ITEMCHANGINGA = -300;
    public static final int HDN_ITEMCLICKW = -322;
    public static final int HDN_ITEMCLICKA = -302;
    public static final int HDN_ITEMDBLCLICKW = -323;
    public static final int HDN_ITEMDBLCLICKA = -303;
    public static final int HDN_ITEMDBLCLICK;
    public static final int HDS_BUTTONS = 2;
    public static final int HDS_DRAGDROP = 64;
    public static final int HDS_FULLDRAG = 128;
    public static final int HDS_HIDDEN = 8;
    public static final int HELPINFO_MENUITEM = 2;
    public static final int HHT_ONDIVIDER = 4;
    public static final int HHT_ONDIVOPEN = 8;
    public static final int HICF_ARROWKEYS = 2;
    public static final int HICF_LEAVING = 32;
    public static final int HICF_MOUSE = 1;
    public static final int HINST_COMMCTRL = -1;
    public static final int HKEY_CLASSES_ROOT = Integer.MIN_VALUE;
    public static final int HKEY_CURRENT_USER = -2147483647;
    public static final int HKEY_LOCAL_MACHINE = -2147483646;
    public static final int HORZRES = 8;
    public static final int HTBORDER = 18;
    public static final int HTCAPTION = 2;
    public static final int HTCLIENT = 1;
    public static final int HTERROR = -2;
    public static final int HTHSCROLL = 6;
    public static final int HTMENU = 5;
    public static final int HTNOWHERE = 0;
    public static final int HTSYSMENU = 3;
    public static final int HTTRANSPARENT = -1;
    public static final int HTVSCROLL = 7;
    public static final int HWND_BOTTOM = 1;
    public static final int HWND_TOP = 0;
    public static final int HWND_TOPMOST = -1;
    public static final int HWND_NOTOPMOST = -2;
    public static final int ICC_COOL_CLASSES = 1024;
    public static final int ICC_DATE_CLASSES = 256;
    public static final int ICM_NOTOPEN = 0;
    public static final int ICON_BIG = 1;
    public static final int ICON_SMALL = 0;
    public static final int I_IMAGECALLBACK = -1;
    public static final int I_IMAGENONE = -2;
    public static final int IDABORT = 3;
    public static final int IDANI_CAPTION = 3;
    public static final int IDB_STD_SMALL_COLOR = 0;
    public static final int IDC_APPSTARTING = 32650;
    public static final int IDC_ARROW = 32512;
    public static final int IDC_CROSS = 32515;
    public static final int IDC_HAND = 32649;
    public static final int IDC_HELP = 32651;
    public static final int IDC_IBEAM = 32513;
    public static final int IDC_NO = 32648;
    public static final int IDC_SIZE = 32640;
    public static final int IDC_SIZEALL = 32646;
    public static final int IDC_SIZENESW = 32643;
    public static final int IDC_SIZENS = 32645;
    public static final int IDC_SIZENWSE = 32642;
    public static final int IDC_SIZEWE = 32644;
    public static final int IDC_UPARROW = 32516;
    public static final int IDC_WAIT = 32514;
    public static final int IDI_APPLICATION = 32512;
    public static final int IDNO = 7;
    public static final int IDOK = 1;
    public static final int IDRETRY = 4;
    public static final int IDYES = 6;
    public static final int ILC_COLOR = 0;
    public static final int ILC_COLOR16 = 16;
    public static final int ILC_COLOR24 = 24;
    public static final int ILC_COLOR32 = 32;
    public static final int ILC_COLOR4 = 4;
    public static final int ILC_COLOR8 = 8;
    public static final int ILC_MASK = 1;
    public static final int ILC_MIRROR = 8192;
    public static final int ILD_NORMAL = 0;
    public static final int ILD_SELECTED = 4;
    public static final int IMAGE_BITMAP = 0;
    public static final int IMAGE_CURSOR = 2;
    public static final int IMAGE_ICON = 1;
    public static final int IME_CMODE_FULLSHAPE = 8;
    public static final int IME_CMODE_KATAKANA = 2;
    public static final int IME_CMODE_NATIVE = 1;
    public static final int IME_CMODE_ROMAN = 16;
    public static final int IMEMOUSE_LDOWN = 1;
    public static final int INFINITE = -1;
    public static final int INPUT_KEYBOARD = 1;
    public static final int INPUT_MOUSE = 0;
    public static final int INTERNET_OPTION_END_BROWSER_SESSION = 42;
    public static final int KEY_ENUMERATE_SUB_KEYS = 8;
    public static final int KEY_NOTIFY = 16;
    public static final int KEY_QUERY_VALUE = 1;
    public static final int KEY_READ = 131097;
    public static final int KEYEVENTF_EXTENDEDKEY = 1;
    public static final int KEYEVENTF_KEYUP = 2;
    public static final int L_MAX_URL_LENGTH = 2084;
    public static final int LANG_NEUTRAL = 0;
    public static final int LANG_USER_DEFAULT = 1024;
    public static final int LAYOUT_RTL = 1;
    public static final int LAYOUT_BITMAPORIENTATIONPRESERVED = 8;
    public static final int LBN_DBLCLK = 2;
    public static final int LBN_SELCHANGE = 1;
    public static final int LBS_EXTENDEDSEL = 2048;
    public static final int LBS_MULTIPLESEL = 8;
    public static final int LBS_NOINTEGRALHEIGHT = 256;
    public static final int LBS_NOTIFY = 1;
    public static final int LB_ADDSTRING = 384;
    public static final int LB_DELETESTRING = 386;
    public static final int LB_ERR = -1;
    public static final int LB_ERRSPACE = -2;
    public static final int LB_FINDSTRINGEXACT = 418;
    public static final int LB_GETCARETINDEX = 415;
    public static final int LB_GETCOUNT = 395;
    public static final int LB_GETCURSEL = 392;
    public static final int LB_GETHORIZONTALEXTENT = 403;
    public static final int LB_GETITEMHEIGHT = 417;
    public static final int LB_GETITEMRECT = 408;
    public static final int LB_GETSEL = 391;
    public static final int LB_GETSELCOUNT = 400;
    public static final int LB_GETSELITEMS = 401;
    public static final int LB_GETTEXT = 393;
    public static final int LB_GETTEXTLEN = 394;
    public static final int LB_GETTOPINDEX = 398;
    public static final int LB_INITSTORAGE = 424;
    public static final int LB_INSERTSTRING = 385;
    public static final int LB_RESETCONTENT = 388;
    public static final int LB_SELITEMRANGE = 411;
    public static final int LB_SELITEMRANGEEX = 387;
    public static final int LB_SETANCHORINDEX = 61852;
    public static final int LB_SETCARETINDEX = 414;
    public static final int LB_SETCURSEL = 390;
    public static final int LB_SETHORIZONTALEXTENT = 404;
    public static final int LB_SETSEL = 389;
    public static final int LB_SETTOPINDEX = 407;
    public static final int LF_FULLFACESIZE = 64;
    public static final int LF_FACESIZE = 32;
    public static final int LGRPID_ARABIC = 13;
    public static final int LGRPID_HEBREW = 12;
    public static final int LGRPID_INSTALLED = 1;
    public static final int LIF_ITEMINDEX = 1;
    public static final int LIF_STATE = 2;
    public static final int LIS_FOCUSED = 1;
    public static final int LIS_ENABLED = 2;
    public static final int LISS_HOT = 2;
    public static final int LISS_SELECTED = 3;
    public static final int LISS_SELECTEDNOTFOCUS = 5;
    public static final int LM_GETIDEALHEIGHT = 1793;
    public static final int LM_SETITEM = 1794;
    public static final int LM_GETITEM = 1795;
    public static final int LCID_SUPPORTED = 2;
    public static final int LOCALE_IDEFAULTANSICODEPAGE = 4100;
    public static final int LOCALE_IDATE = 33;
    public static final int LOCALE_ITIME = 35;
    public static final int LOCALE_RETURN_NUMBER = 536870912;
    public static final int LOCALE_S1159 = 40;
    public static final int LOCALE_S2359 = 41;
    public static final int LOCALE_SDECIMAL = 14;
    public static final int LOCALE_SISO3166CTRYNAME = 90;
    public static final int LOCALE_SISO639LANGNAME = 89;
    public static final int LOCALE_SLONGDATE = 32;
    public static final int LOCALE_SSHORTDATE = 31;
    public static final int LOCALE_STIMEFORMAT = 4099;
    public static final int LOCALE_SYEARMONTH = 4102;
    public static final int LOCALE_SDAYNAME1 = 42;
    public static final int LOCALE_SDAYNAME2 = 43;
    public static final int LOCALE_SDAYNAME3 = 44;
    public static final int LOCALE_SDAYNAME4 = 45;
    public static final int LOCALE_SDAYNAME5 = 46;
    public static final int LOCALE_SDAYNAME6 = 47;
    public static final int LOCALE_SDAYNAME7 = 48;
    public static final int LOCALE_SMONTHNAME1 = 56;
    public static final int LOCALE_SMONTHNAME2 = 57;
    public static final int LOCALE_SMONTHNAME3 = 58;
    public static final int LOCALE_SMONTHNAME4 = 59;
    public static final int LOCALE_SMONTHNAME5 = 60;
    public static final int LOCALE_SMONTHNAME6 = 61;
    public static final int LOCALE_SMONTHNAME7 = 62;
    public static final int LOCALE_SMONTHNAME8 = 63;
    public static final int LOCALE_SMONTHNAME9 = 64;
    public static final int LOCALE_SMONTHNAME10 = 65;
    public static final int LOCALE_SMONTHNAME11 = 66;
    public static final int LOCALE_SMONTHNAME12 = 67;
    public static final int LOCALE_USER_DEFAULT = 1024;
    public static final int LOGPIXELSX = 88;
    public static final int LOGPIXELSY = 90;
    public static final int LPSTR_TEXTCALLBACK = -1;
    public static final int LR_DEFAULTCOLOR = 0;
    public static final int LR_SHARED = 32768;
    public static final int LVCFMT_BITMAP_ON_RIGHT = 4096;
    public static final int LVCFMT_CENTER = 2;
    public static final int LVCFMT_IMAGE = 2048;
    public static final int LVCFMT_LEFT = 0;
    public static final int LVCFMT_RIGHT = 1;
    public static final int LVCF_FMT = 1;
    public static final int LVCF_IMAGE = 16;
    public static final int LVCFMT_JUSTIFYMASK = 3;
    public static final int LVCF_TEXT = 4;
    public static final int LVCF_WIDTH = 2;
    public static final int LVHT_ONITEM = 14;
    public static final int LVHT_ONITEMICON = 2;
    public static final int LVHT_ONITEMLABEL = 4;
    public static final int LVHT_ONITEMSTATEICON = 8;
    public static final int LVIF_IMAGE = 2;
    public static final int LVIF_INDENT = 16;
    public static final int LVIF_STATE = 8;
    public static final int LVIF_TEXT = 1;
    public static final int LVIM_AFTER = 1;
    public static final int LVIR_BOUNDS = 0;
    public static final int LVIR_ICON = 1;
    public static final int LVIR_LABEL = 2;
    public static final int LVIR_SELECTBOUNDS = 3;
    public static final int LVIS_DROPHILITED = 8;
    public static final int LVIS_FOCUSED = 1;
    public static final int LVIS_SELECTED = 2;
    public static final int LVIS_STATEIMAGEMASK = 61440;
    public static final int LVM_FIRST = 4096;
    public static final int LVM_APPROXIMATEVIEWRECT = 4160;
    public static final int LVM_CREATEDRAGIMAGE = 4129;
    public static final int LVM_DELETEALLITEMS = 4105;
    public static final int LVM_DELETECOLUMN = 4124;
    public static final int LVM_DELETEITEM = 4104;
    public static final int LVM_ENSUREVISIBLE = 4115;
    public static final int LVM_GETBKCOLOR = 4096;
    public static final int LVM_GETCOLUMN;
    public static final int LVM_GETCOLUMNORDERARRAY = 4155;
    public static final int LVM_GETCOLUMNWIDTH = 4125;
    public static final int LVM_GETCOUNTPERPAGE = 4136;
    public static final int LVM_GETEXTENDEDLISTVIEWSTYLE = 4151;
    public static final int LVM_GETHEADER = 4127;
    public static final int LVM_GETIMAGELIST = 4098;
    public static final int LVM_GETITEM;
    public static final int LVM_GETITEMW = 4171;
    public static final int LVM_GETITEMA = 4101;
    public static final int LVM_GETITEMCOUNT = 4100;
    public static final int LVM_GETITEMRECT = 4110;
    public static final int LVM_GETITEMSTATE = 4140;
    public static final int LVM_GETNEXTITEM = 4108;
    public static final int LVM_GETSELECTEDCOLUMN = 4270;
    public static final int LVM_GETSELECTEDCOUNT = 4146;
    public static final int LVM_GETSTRINGWIDTH;
    public static final int LVM_GETSUBITEMRECT = 4152;
    public static final int LVM_GETTEXTCOLOR = 4131;
    public static final int LVM_GETTOOLTIPS = 4174;
    public static final int LVM_GETTOPINDEX = 4135;
    public static final int LVM_HITTEST = 4114;
    public static final int LVM_INSERTCOLUMN;
    public static final int LVM_INSERTITEM;
    public static final int LVM_REDRAWITEMS = 4117;
    public static final int LVM_SCROLL = 4116;
    public static final int LVM_SETBKCOLOR = 4097;
    public static final int LVM_SETCALLBACKMASK = 4107;
    public static final int LVM_SETCOLUMN;
    public static final int LVM_SETCOLUMNORDERARRAY = 4154;
    public static final int LVM_SETCOLUMNWIDTH = 4126;
    public static final int LVM_SETEXTENDEDLISTVIEWSTYLE = 4150;
    public static final int LVM_SETIMAGELIST = 4099;
    public static final int LVM_SETINSERTMARK = 4262;
    public static final int LVM_SETITEM;
    public static final int LVM_SETITEMCOUNT = 4143;
    public static final int LVM_SETITEMSTATE = 4139;
    public static final int LVM_SETSELECTIONMARK = 4163;
    public static final int LVM_SETSELECTEDCOLUMN = 4236;
    public static final int LVM_SETTEXTBKCOLOR = 4134;
    public static final int LVM_SETTEXTCOLOR = 4132;
    public static final int LVM_SETTOOLTIPS = 4170;
    public static final int LVM_SUBITEMHITTEST = 4153;
    public static final int LVNI_FOCUSED = 1;
    public static final int LVNI_SELECTED = 2;
    public static final int LVN_BEGINDRAG = -109;
    public static final int LVN_BEGINRDRAG = -111;
    public static final int LVN_COLUMNCLICK = -108;
    public static final int LVN_FIRST = -100;
    public static final int LVN_GETDISPINFOA = -150;
    public static final int LVN_GETDISPINFOW = -177;
    public static final int LVN_ITEMACTIVATE = -114;
    public static final int LVN_ITEMCHANGED = -101;
    public static final int LVN_MARQUEEBEGIN = -156;
    public static final int LVN_ODFINDITEMA = -152;
    public static final int LVN_ODFINDITEMW = -179;
    public static final int LVN_ODSTATECHANGED = -115;
    public static final int LVP_LISTITEM = 1;
    public static final int LVSCW_AUTOSIZE = -1;
    public static final int LVSCW_AUTOSIZE_USEHEADER = -2;
    public static final int LVSICF_NOINVALIDATEALL = 1;
    public static final int LVSICF_NOSCROLL = 2;
    public static final int LVSIL_SMALL = 1;
    public static final int LVSIL_STATE = 2;
    public static final int LVS_EX_DOUBLEBUFFER = 65536;
    public static final int LVS_EX_FULLROWSELECT = 32;
    public static final int LVS_EX_GRIDLINES = 1;
    public static final int LVS_EX_HEADERDRAGDROP = 16;
    public static final int LVS_EX_LABELTIP = 16384;
    public static final int LVS_EX_ONECLICKACTIVATE = 64;
    public static final int LVS_EX_SUBITEMIMAGES = 2;
    public static final int LVS_EX_TRACKSELECT = 8;
    public static final int LVS_EX_TRANSPARENTBKGND = 8388608;
    public static final int LVS_EX_TWOCLICKACTIVATE = 128;
    public static final int LVS_LIST = 3;
    public static final int LVS_NOCOLUMNHEADER = 16384;
    public static final int LVS_NOSCROLL = 8192;
    public static final int LVS_OWNERDATA = 4096;
    public static final int LVS_OWNERDRAWFIXED = 1024;
    public static final int LVS_REPORT = 1;
    public static final int LVS_SHAREIMAGELISTS = 64;
    public static final int LVS_SHOWSELALWAYS = 8;
    public static final int LVS_SINGLESEL = 4;
    public static final int LWA_COLORKEY = 1;
    public static final int LWA_ALPHA = 2;
    public static final int MAX_LINKID_TEXT = 48;
    public static final int MA_NOACTIVATE = 3;
    public static final int MB_ABORTRETRYIGNORE = 2;
    public static final int MB_APPLMODAL = 0;
    public static final int MB_ICONERROR = 16;
    public static final int MB_ICONINFORMATION = 64;
    public static final int MB_ICONQUESTION = 32;
    public static final int MB_ICONWARNING = 48;
    public static final int MB_OK = 0;
    public static final int MB_OKCANCEL = 1;
    public static final int MB_PRECOMPOSED = 1;
    public static final int MB_RETRYCANCEL = 5;
    public static final int MB_RIGHT = 524288;
    public static final int MB_RTLREADING = 1048576;
    public static final int MB_SYSTEMMODAL = 4096;
    public static final int MB_TASKMODAL = 8192;
    public static final int MB_TOPMOST = 262144;
    public static final int MB_YESNO = 4;
    public static final int MB_YESNOCANCEL = 3;
    public static final int MCHT_CALENDAR = 131072;
    public static final int MCHT_CALENDARDATE = 131073;
    public static final int MCM_FIRST = 4096;
    public static final int MCM_GETCURSEL = 4097;
    public static final int MCM_GETMINREQRECT = 4105;
    public static final int MCM_HITTEST = 4110;
    public static final int MCM_SETCURSEL = 4098;
    public static final int MCN_FIRST = -750;
    public static final int MCN_SELCHANGE = -749;
    public static final int MCN_SELECT = -746;
    public static final int MCS_NOTODAY = 16;
    public static final int MDIS_ALLCHILDSTYLES = 1;
    public static final int MFS_CHECKED = 8;
    public static final int MFS_DISABLED = 3;
    public static final int MFS_GRAYED = 3;
    public static final int MFT_RADIOCHECK = 512;
    public static final int MFT_RIGHTJUSTIFY = 16384;
    public static final int MFT_RIGHTORDER = 8192;
    public static final int MFT_SEPARATOR = 2048;
    public static final int MFT_STRING = 0;
    public static final int MF_BYCOMMAND = 0;
    public static final int MF_BYPOSITION = 1024;
    public static final int MF_CHECKED = 8;
    public static final int MF_DISABLED = 2;
    public static final int MF_ENABLED = 0;
    public static final int MF_GRAYED = 1;
    public static final int MF_HILITE = 128;
    public static final int MF_POPUP = 16;
    public static final int MF_SEPARATOR = 2048;
    public static final int MF_SYSMENU = 8192;
    public static final int MF_UNCHECKED = 0;
    public static final int MIIM_BITMAP = 128;
    public static final int MIIM_DATA = 32;
    public static final int MIIM_FTYPE = 256;
    public static final int MIIM_ID = 2;
    public static final int MIIM_STATE = 1;
    public static final int MIIM_STRING = 64;
    public static final int MIIM_SUBMENU = 4;
    public static final int MIIM_TYPE = 16;
    public static final int MIM_BACKGROUND = 2;
    public static final int MIM_STYLE = 16;
    public static final int MK_ALT = 32;
    public static final int MK_CONTROL = 8;
    public static final int MK_LBUTTON = 1;
    public static final int MK_MBUTTON = 16;
    public static final int MK_RBUTTON = 2;
    public static final int MK_SHIFT = 4;
    public static final int MK_XBUTTON1 = 32;
    public static final int MK_XBUTTON2 = 64;
    public static final int MM_TEXT = 1;
    public static final int MNC_CLOSE = 1;
    public static final int MNS_CHECKORBMP = 67108864;
    public static final int MONITOR_DEFAULTTONEAREST = 2;
    public static final int MONITORINFOF_PRIMARY = 1;
    public static final String MONTHCAL_CLASS = "SysMonthCal32";
    public static final int MOUSEEVENTF_ABSOLUTE = 32768;
    public static final int MOUSEEVENTF_LEFTDOWN = 2;
    public static final int MOUSEEVENTF_LEFTUP = 4;
    public static final int MOUSEEVENTF_MIDDLEDOWN = 32;
    public static final int MOUSEEVENTF_MIDDLEUP = 64;
    public static final int MOUSEEVENTF_MOVE = 1;
    public static final int MOUSEEVENTF_RIGHTDOWN = 8;
    public static final int MOUSEEVENTF_RIGHTUP = 16;
    public static final int MOUSEEVENTF_VIRTUALDESK = 16384;
    public static final int MOUSEEVENTF_WHEEL = 2048;
    public static final int MOUSEEVENTF_XDOWN = 128;
    public static final int MOUSEEVENTF_XUP = 256;
    public static final int MSGF_DIALOGBOX = 0;
    public static final int MSGF_COMMCTRL_BEGINDRAG = 16896;
    public static final int MSGF_COMMCTRL_SIZEHEADER = 16897;
    public static final int MSGF_COMMCTRL_DRAGSELECT = 16898;
    public static final int MSGF_COMMCTRL_TOOLBARCUST = 16899;
    public static final int MSGF_MAINLOOP = 8;
    public static final int MSGF_MENU = 2;
    public static final int MSGF_MOVE = 3;
    public static final int MSGF_MESSAGEBOX = 1;
    public static final int MSGF_NEXTWINDOW = 6;
    public static final int MSGF_SCROLLBAR = 5;
    public static final int MSGF_SIZE = 4;
    public static final int MSGF_USER = 4096;
    public static final int MWMO_INPUTAVAILABLE = 4;
    public static final int MWT_LEFTMULTIPLY = 2;
    public static final int NI_COMPOSITIONSTR = 21;
    public static final int NID_READY = 128;
    public static final int NID_MULTI_INPUT = 64;
    public static final int NIF_ICON = 2;
    public static final int NIF_INFO = 16;
    public static final int NIF_MESSAGE = 1;
    public static final int NIF_STATE = 8;
    public static final int NIF_TIP = 4;
    public static final int NIIF_ERROR = 3;
    public static final int NIIF_INFO = 1;
    public static final int NIIF_NONE = 0;
    public static final int NIIF_WARNING = 2;
    public static final int NIM_ADD = 0;
    public static final int NIM_DELETE = 2;
    public static final int NIM_MODIFY = 1;
    public static final int NIN_SELECT = 1024;
    public static final int NINF_KEY = 1;
    public static final int NIN_KEYSELECT = 1025;
    public static final int NIN_BALLOONSHOW = 1026;
    public static final int NIN_BALLOONHIDE = 1027;
    public static final int NIN_BALLOONTIMEOUT = 1028;
    public static final int NIN_BALLOONUSERCLICK = 1029;
    public static final int NIS_HIDDEN = 1;
    public static final int NM_FIRST = 0;
    public static final int NM_CLICK = -2;
    public static final int NM_CUSTOMDRAW = -12;
    public static final int NM_DBLCLK = -3;
    public static final int NM_RECOGNIZEGESTURE = -16;
    public static final int NM_RELEASEDCAPTURE = -16;
    public static final int NM_RETURN = -4;
    public static final int NOTIFYICONDATAA_V2_SIZE;
    public static final int NOTIFYICONDATAW_V2_SIZE;
    public static final int NOTIFYICONDATA_V2_SIZE;
    public static final int NOTSRCCOPY = 3342344;
    public static final int NULLREGION = 1;
    public static final int NULL_BRUSH = 5;
    public static final int NULL_PEN = 8;
    public static final int NUMRESERVED = 106;
    public static final int OBJID_WINDOW = 0;
    public static final int OBJID_SYSMENU = -1;
    public static final int OBJID_TITLEBAR = -2;
    public static final int OBJID_MENU = -3;
    public static final int OBJID_CLIENT = -4;
    public static final int OBJID_VSCROLL = -5;
    public static final int OBJID_HSCROLL = -6;
    public static final int OBJID_SIZEGRIP = -7;
    public static final int OBJID_CARET = -8;
    public static final int OBJID_CURSOR = -9;
    public static final int OBJID_ALERT = -10;
    public static final int OBJID_SOUND = -11;
    public static final int OBJID_QUERYCLASSNAMEIDX = -12;
    public static final int OBJID_NATIVEOM = -16;
    public static final int OBJ_BITMAP = 7;
    public static final int OBJ_FONT = 6;
    public static final int OBJ_PEN = 1;
    public static final int OBM_CHECKBOXES = 32759;
    public static final int ODS_SELECTED = 1;
    public static final int ODT_MENU = 1;
    public static final int OFN_ALLOWMULTISELECT = 512;
    public static final int OFN_EXPLORER = 524288;
    public static final int OFN_ENABLEHOOK = 32;
    public static final int OFN_ENABLESIZING = 8388608;
    public static final int OFN_HIDEREADONLY = 4;
    public static final int OFN_NOCHANGEDIR = 8;
    public static final int OFN_OVERWRITEPROMPT = 2;
    public static final int OIC_BANG = 32515;
    public static final int OIC_HAND = 32513;
    public static final int OIC_INFORMATION = 32516;
    public static final int OIC_QUES = 32514;
    public static final int OIC_WINLOGO = 32517;
    public static final int OPAQUE = 2;
    public static final int PATCOPY = 15728673;
    public static final int PATINVERT = 5898313;
    public static final int PBM_GETPOS = 1032;
    public static final int PBM_GETRANGE = 1031;
    public static final int PBM_GETSTATE = 1041;
    public static final int PBM_SETBARCOLOR = 1033;
    public static final int PBM_SETBKCOLOR = 8193;
    public static final int PBM_SETMARQUEE = 1034;
    public static final int PBM_SETPOS = 1026;
    public static final int PBM_SETRANGE32 = 1030;
    public static final int PBM_SETSTATE = 1040;
    public static final int PBM_STEPIT = 1029;
    public static final int PBS_MARQUEE = 8;
    public static final int PBS_SMOOTH = 1;
    public static final int PBS_VERTICAL = 4;
    public static final int PBS_NORMAL = 1;
    public static final int PBS_HOT = 2;
    public static final int PBS_PRESSED = 3;
    public static final int PBS_DISABLED = 4;
    public static final int PBS_DEFAULTED = 5;
    public static final int PBST_NORMAL = 1;
    public static final int PBST_ERROR = 2;
    public static final int PBST_PAUSED = 3;
    public static final int PD_ALLPAGES = 0;
    public static final int PD_COLLATE = 16;
    public static final int PD_PAGENUMS = 2;
    public static final int PD_PRINTTOFILE = 32;
    public static final int PD_RETURNDC = 256;
    public static final int PD_RETURNDEFAULT = 1024;
    public static final int PD_SELECTION = 1;
    public static final int PD_USEDEVMODECOPIESANDCOLLATE = 262144;
    public static final int PT_CLOSEFIGURE = 1;
    public static final int PT_LINETO = 2;
    public static final int PT_BEZIERTO = 4;
    public static final int PT_MOVETO = 6;
    public static final int PFM_TABSTOPS = 16;
    public static final int PHYSICALHEIGHT = 111;
    public static final int PHYSICALOFFSETX = 112;
    public static final int PHYSICALOFFSETY = 113;
    public static final int PHYSICALWIDTH = 110;
    public static final int PLANES = 14;
    public static final int PM_NOREMOVE = 0;
    public static final int PM_NOYIELD = 2;
    public static final int QS_HOTKEY = 128;
    public static final int QS_KEY = 1;
    public static final int QS_MOUSEMOVE = 2;
    public static final int QS_MOUSEBUTTON = 4;
    public static final int QS_MOUSE = 6;
    public static final int QS_INPUT = 7;
    public static final int QS_POSTMESSAGE = 8;
    public static final int QS_TIMER = 16;
    public static final int QS_PAINT = 32;
    public static final int QS_SENDMESSAGE = 64;
    public static final int QS_ALLINPUT = 127;
    public static final int PM_QS_INPUT = 458752;
    public static final int PM_QS_POSTMESSAGE = 9961472;
    public static final int PM_QS_PAINT = 2097152;
    public static final int PM_QS_SENDMESSAGE = 4194304;
    public static final int PM_REMOVE = 1;
    public static final String PROGRESS_CLASS = "msctls_progress32";
    public static final int PP_BAR = 1;
    public static final int PP_BARVERT = 2;
    public static final int PP_CHUNK = 3;
    public static final int PP_CHUNKVERT = 4;
    public static final int PRF_CHILDREN = 16;
    public static final int PRF_CLIENT = 4;
    public static final int PRF_ERASEBKGND = 8;
    public static final int PRF_NONCLIENT = 2;
    public static final int PROGRESSCHUNKSIZE = 2411;
    public static final int PROGRESSSPACESIZE = 2412;
    public static final int PS_DASH = 1;
    public static final int PS_DASHDOT = 3;
    public static final int PS_DASHDOTDOT = 4;
    public static final int PS_DOT = 2;
    public static final int PS_ENDCAP_FLAT = 512;
    public static final int PS_ENDCAP_SQUARE = 256;
    public static final int PS_ENDCAP_ROUND = 0;
    public static final int PS_ENDCAP_MASK = 3840;
    public static final int PS_GEOMETRIC = 65536;
    public static final int PS_JOIN_BEVEL = 4096;
    public static final int PS_JOIN_MASK = 61440;
    public static final int PS_JOIN_MITER = 8192;
    public static final int PS_JOIN_ROUND = 0;
    public static final int PS_SOLID = 0;
    public static final int PS_STYLE_MASK = 15;
    public static final int PS_TYPE_MASK = 983040;
    public static final int PS_USERSTYLE = 7;
    public static final int R2_COPYPEN = 13;
    public static final int R2_XORPEN = 7;
    public static final int RASTERCAPS = 38;
    public static final int RASTER_FONTTYPE = 1;
    public static final int RBBIM_CHILD = 16;
    public static final int RBBIM_CHILDSIZE = 32;
    public static final int RBBIM_COLORS = 2;
    public static final int RBBIM_HEADERSIZE = 2048;
    public static final int RBBIM_ID = 256;
    public static final int RBBIM_IDEALSIZE = 512;
    public static final int RBBIM_SIZE = 64;
    public static final int RBBIM_STYLE = 1;
    public static final int RBBIM_TEXT = 4;
    public static final int RBBS_BREAK = 1;
    public static final int RBBS_GRIPPERALWAYS = 128;
    public static final int RBBS_NOGRIPPER = 256;
    public static final int RBBS_USECHEVRON = 512;
    public static final int RBBS_VARIABLEHEIGHT = 64;
    public static final int RBN_FIRST = -831;
    public static final int RBN_BEGINDRAG = -835;
    public static final int RBN_CHILDSIZE = -839;
    public static final int RBN_CHEVRONPUSHED = -841;
    public static final int RBN_HEIGHTCHANGE = -831;
    public static final int RBS_DBLCLKTOGGLE = 32768;
    public static final int RBS_BANDBORDERS = 1024;
    public static final int RBS_VARHEIGHT = 512;
    public static final int RB_DELETEBAND = 1026;
    public static final int RB_GETBANDBORDERS = 1058;
    public static final int RB_GETBANDCOUNT = 1036;
    public static final int RB_GETBANDINFO;
    public static final int RB_GETBANDMARGINS = 1064;
    public static final int RB_GETBARHEIGHT = 1051;
    public static final int RB_GETBKCOLOR = 1044;
    public static final int RB_GETRECT = 1033;
    public static final int RB_GETTEXTCOLOR = 1046;
    public static final int RB_IDTOINDEX = 1040;
    public static final int RB_INSERTBAND;
    public static final int RB_MOVEBAND = 1063;
    public static final int RB_SETBANDINFO;
    public static final int RB_SETBKCOLOR = 1043;
    public static final int RB_SETTEXTCOLOR = 1045;
    public static final int RC_BITBLT = 1;
    public static final int RC_PALETTE = 256;
    public static final int RDW_ALLCHILDREN = 128;
    public static final int RDW_ERASE = 4;
    public static final int RDW_FRAME = 1024;
    public static final int RDW_INVALIDATE = 1;
    public static final int RDW_UPDATENOW = 256;
    public static final int READ_CONTROL = 131072;
    public static final String REBARCLASSNAME = "ReBarWindow32";
    public static final int RGN_AND = 1;
    public static final int RGN_COPY = 5;
    public static final int RGN_DIFF = 4;
    public static final int RGN_ERROR = 0;
    public static final int RGN_OR = 2;
    public static final int RP_BAND = 3;
    public static final int SBP_ARROWBTN = 1;
    public static final int SBP_THUMBBTNHORZ = 2;
    public static final int SBP_THUMBBTNVERT = 3;
    public static final int SBP_LOWERTRACKHORZ = 4;
    public static final int SBP_UPPERTRACKHORZ = 5;
    public static final int SBP_LOWERTRACKVERT = 6;
    public static final int SBP_UPPERTRACKVERT = 7;
    public static final int SBP_GRIPPERHORZ = 8;
    public static final int SBP_GRIPPERVERT = 9;
    public static final int SBP_SIZEBOX = 10;
    public static final int SBS_HORZ = 0;
    public static final int SBS_VERT = 1;
    public static final int SB_BOTH = 3;
    public static final int SB_BOTTOM = 7;
    public static final int SB_NONE = 0;
    public static final int SB_CONST_ALPHA = 1;
    public static final int SB_PIXEL_ALPHA = 2;
    public static final int SB_PREMULT_ALPHA = 4;
    public static final int SB_CTL = 2;
    public static final int SB_ENDSCROLL = 8;
    public static final int SB_HORZ = 0;
    public static final int SB_LINEDOWN = 1;
    public static final int SB_LINEUP = 0;
    public static final int SB_PAGEDOWN = 3;
    public static final int SB_PAGEUP = 2;
    public static final int SB_THUMBPOSITION = 4;
    public static final int SB_THUMBTRACK = 5;
    public static final int SB_TOP = 6;
    public static final int SB_VERT = 1;
    public static final int SCF_ALL = 4;
    public static final int SCF_DEFAULT = 0;
    public static final int SCF_SELECTION = 1;
    public static final int SC_CLOSE = 61536;
    public static final int SC_HSCROLL = 61568;
    public static final int SC_KEYMENU = 61696;
    public static final int SC_MAXIMIZE = 61488;
    public static final int SC_MINIMIZE = 61472;
    public static final int SC_NEXTWINDOW = 61504;
    public static final int SC_RESTORE = 61728;
    public static final int SC_SIZE = 61440;
    public static final int SC_TASKLIST = 61744;
    public static final int SC_VSCROLL = 61552;
    public static final int SCRBS_NORMAL = 1;
    public static final int SCRBS_HOT = 2;
    public static final int SCRBS_PRESSED = 3;
    public static final int SCRBS_DISABLED = 4;
    public static final int SEM_FAILCRITICALERRORS = 1;
    public static final int SET_FEATURE_ON_PROCESS = 2;
    public static final int SF_RTF = 2;
    public static final int SHADEBLENDCAPS = 120;
    public static final int SHCMBF_HIDDEN = 2;
    public static final int SHCMBM_OVERRIDEKEY = 1427;
    public static final int SHCMBM_SETSUBMENU = 1424;
    public static final int SHGFP_TYPE_CURRENT = 0;
    public static final int SHCMBM_GETSUBMENU = 1425;
    public static final int SHGFI_ICON = 256;
    public static final int SHGFI_SMALLICON = 1;
    public static final int SHGFI_USEFILEATTRIBUTES = 16;
    public static final int SHMBOF_NODEFAULT = 1;
    public static final int SHMBOF_NOTIFY = 2;
    public static final int SHRG_RETURNCMD = 1;
    public static final int SIGDN_FILESYSPATH = -2147123200;
    public static final int SIF_ALL = 23;
    public static final int SIF_DISABLENOSCROLL = 8;
    public static final int SIF_PAGE = 2;
    public static final int SIF_POS = 4;
    public static final int SIF_RANGE = 1;
    public static final int SIF_TRACKPOS = 16;
    public static final int SIP_DOWN = 1;
    public static final int SIP_UP = 0;
    public static final int SIPF_ON = 1;
    public static final int SIZE_RESTORED = 0;
    public static final int SIZE_MINIMIZED = 1;
    public static final int SIZE_MAXIMIZED = 2;
    public static final int SIZEPALETTE = 104;
    public static final int SM_CMONITORS = 80;
    public static final int SM_CXBORDER = 5;
    public static final int SM_CXCURSOR = 13;
    public static final int SM_CXDOUBLECLK = 36;
    public static final int SM_CYDOUBLECLK = 37;
    public static final int SM_CXEDGE = 45;
    public static final int SM_CXFOCUSBORDER = 83;
    public static final int SM_CXHSCROLL = 21;
    public static final int SM_CXICON = 11;
    public static final int SM_CYICON = 12;
    public static final int SM_CXVIRTUALSCREEN = 78;
    public static final int SM_CYVIRTUALSCREEN = 79;
    public static final int SM_CXSMICON = 49;
    public static final int SM_CYSMICON = 50;
    public static final int SM_CXSCREEN = 0;
    public static final int SM_XVIRTUALSCREEN = 76;
    public static final int SM_YVIRTUALSCREEN = 77;
    public static final int SM_CXVSCROLL = 2;
    public static final int SM_CYBORDER = 6;
    public static final int SM_CYCURSOR = 14;
    public static final int SM_CYFOCUSBORDER = 84;
    public static final int SM_CYHSCROLL = 3;
    public static final int SM_CYMENU = 15;
    public static final int SM_CXMINTRACK = 34;
    public static final int SM_CYMINTRACK = 35;
    public static final int SM_CMOUSEBUTTONS = 43;
    public static final int SM_CYSCREEN = 1;
    public static final int SM_CYVSCROLL = 20;
    public static final int SM_DIGITIZER = 94;
    public static final int SM_MAXIMUMTOUCHES = 95;
    public static final int SPI_GETFONTSMOOTHINGTYPE = 8202;
    public static final int SPI_GETHIGHCONTRAST = 66;
    public static final int SPI_GETWORKAREA = 48;
    public static final int SPI_GETMOUSEVANISH = 4128;
    public static final int SPI_GETNONCLIENTMETRICS = 41;
    public static final int SPI_GETWHEELSCROLLLINES = 104;
    public static final int SPI_GETCARETWIDTH = 8198;
    public static final int SPI_SETSIPINFO = 224;
    public static final int SPI_SETHIGHCONTRAST = 67;
    public static final int SRCAND = 8913094;
    public static final int SRCCOPY = 13369376;
    public static final int SRCINVERT = 6684742;
    public static final int SRCPAINT = 15597702;
    public static final int SS_BITMAP = 14;
    public static final int SS_CENTER = 1;
    public static final int SS_CENTERIMAGE = 512;
    public static final int SS_EDITCONTROL = 8192;
    public static final int SS_ICON = 3;
    public static final int SS_LEFT = 0;
    public static final int SS_LEFTNOWORDWRAP = 12;
    public static final int SS_NOTIFY = 256;
    public static final int SS_OWNERDRAW = 13;
    public static final int SS_REALSIZEIMAGE = 2048;
    public static final int SS_RIGHT = 2;
    public static final int SSA_FALLBACK = 32;
    public static final int SSA_GLYPHS = 128;
    public static final int SSA_METAFILE = 2048;
    public static final int SSA_LINK = 4096;
    public static final int STANDARD_RIGHTS_READ = 131072;
    public static final int STARTF_USESHOWWINDOW = 1;
    public static final int STATE_SYSTEM_INVISIBLE = 32768;
    public static final int STATE_SYSTEM_OFFSCREEN = 65536;
    public static final int STATE_SYSTEM_UNAVAILABLE = 1;
    public static final int STD_COPY = 1;
    public static final int STD_CUT = 0;
    public static final int STD_FILENEW = 6;
    public static final int STD_FILEOPEN = 7;
    public static final int STD_FILESAVE = 8;
    public static final int STD_PASTE = 2;
    public static final int STM_GETIMAGE = 371;
    public static final int STM_SETIMAGE = 370;
    public static final int SWP_ASYNCWINDOWPOS = 16384;
    public static final int SWP_DRAWFRAME = 32;
    public static final int SWP_NOACTIVATE = 16;
    public static final int SWP_NOCOPYBITS = 256;
    public static final int SWP_NOMOVE = 2;
    public static final int SWP_NOREDRAW = 8;
    public static final int SWP_NOSIZE = 1;
    public static final int SWP_NOZORDER = 4;
    public static final int SW_ERASE = 4;
    public static final int SW_HIDE = 0;
    public static final int SW_INVALIDATE = 2;
    public static final int SW_MINIMIZE = 6;
    public static final int SW_PARENTOPENING = 3;
    public static final int SW_RESTORE;
    public static final int SW_SCROLLCHILDREN = 1;
    public static final int SW_SHOW = 5;
    public static final int SW_SHOWMAXIMIZED;
    public static final int SW_SHOWMINIMIZED = 2;
    public static final int SW_SHOWMINNOACTIVE = 7;
    public static final int SW_SHOWNA = 8;
    public static final int SW_SHOWNOACTIVATE = 4;
    public static final int SYNCHRONIZE = 1048576;
    public static final int SYSRGN = 4;
    public static final int SYSTEM_FONT = 13;
    public static final int S_OK = 0;
    public static final int TABP_TABITEM = 1;
    public static final int TABP_TABITEMLEFTEDGE = 2;
    public static final int TABP_TABITEMRIGHTEDGE = 3;
    public static final int TABP_TABITEMBOTHEDGE = 4;
    public static final int TABP_TOPTABITEM = 5;
    public static final int TABP_TOPTABITEMLEFTEDGE = 6;
    public static final int TABP_TOPTABITEMRIGHTEDGE = 7;
    public static final int TABP_TOPTABITEMBOTHEDGE = 8;
    public static final int TABP_PANE = 9;
    public static final int TABP_BODY = 10;
    public static final int TBIF_COMMAND = 32;
    public static final int TBIF_STATE = 4;
    public static final int TBIF_IMAGE = 1;
    public static final int TBIF_LPARAM = 16;
    public static final int TBIF_SIZE = 64;
    public static final int TBIF_STYLE = 8;
    public static final int TBIF_TEXT = 2;
    public static final int TB_GETEXTENDEDSTYLE = 1109;
    public static final int TBM_GETLINESIZE = 1048;
    public static final int TBM_GETPAGESIZE = 1046;
    public static final int TBM_GETPOS = 1024;
    public static final int TBM_GETRANGEMAX = 1026;
    public static final int TBM_GETRANGEMIN = 1025;
    public static final int TBM_GETTHUMBRECT = 1049;
    public static final int TBM_SETLINESIZE = 1047;
    public static final int TBM_SETPAGESIZE = 1045;
    public static final int TBM_SETPOS = 1029;
    public static final int TBM_SETRANGEMAX = 1032;
    public static final int TBM_SETRANGEMIN = 1031;
    public static final int TBM_SETTICFREQ = 1044;
    public static final int TBN_DROPDOWN = -710;
    public static final int TBN_FIRST = -700;
    public static final int TBN_HOTITEMCHANGE = -713;
    public static final int TBSTATE_CHECKED = 1;
    public static final int TBSTATE_PRESSED = 2;
    public static final int TBSTYLE_CUSTOMERASE = 8192;
    public static final int TBSTYLE_DROPDOWN = 8;
    public static final int TBSTATE_ENABLED = 4;
    public static final int TBSTYLE_AUTOSIZE = 16;
    public static final int TBSTYLE_EX_DOUBLEBUFFER = 128;
    public static final int TBSTYLE_EX_DRAWDDARROWS = 1;
    public static final int TBSTYLE_EX_HIDECLIPPEDBUTTONS = 16;
    public static final int TBSTYLE_EX_MIXEDBUTTONS = 8;
    public static final int TBSTYLE_FLAT = 2048;
    public static final int TBSTYLE_LIST = 4096;
    public static final int TBSTYLE_TOOLTIPS = 256;
    public static final int TBSTYLE_TRANSPARENT = 32768;
    public static final int TBSTYLE_WRAPABLE = 512;
    public static final int TBS_AUTOTICKS = 1;
    public static final int TBS_BOTH = 8;
    public static final int TBS_DOWNISLEFT = 1024;
    public static final int TBS_HORZ = 0;
    public static final int TBS_VERT = 2;
    public static final int TB_ADDSTRING;
    public static final int TB_AUTOSIZE = 1057;
    public static final int TB_BUTTONCOUNT = 1048;
    public static final int TB_BUTTONSTRUCTSIZE = 1054;
    public static final int TB_COMMANDTOINDEX = 1049;
    public static final int TB_DELETEBUTTON = 1046;
    public static final int TB_ENDTRACK = 8;
    public static final int TB_GETBUTTON = 1047;
    public static final int TB_GETBUTTONINFO;
    public static final int TB_GETBUTTONSIZE = 1082;
    public static final int TB_GETBUTTONTEXT;
    public static final int TB_GETDISABLEDIMAGELIST = 1079;
    public static final int TB_GETHOTIMAGELIST = 1077;
    public static final int TB_GETHOTITEM = 1095;
    public static final int TB_GETIMAGELIST = 1073;
    public static final int TB_GETITEMRECT = 1053;
    public static final int TB_GETPADDING = 1110;
    public static final int TB_GETROWS = 1064;
    public static final int TB_GETSTATE = 1042;
    public static final int TB_GETTOOLTIPS = 1059;
    public static final int TB_INSERTBUTTON;
    public static final int TB_LOADIMAGES = 1074;
    public static final int TB_MAPACCELERATOR;
    public static final int TB_SETBITMAPSIZE = 1056;
    public static final int TB_SETBUTTONINFO;
    public static final int TB_SETBUTTONSIZE = 1055;
    public static final int TB_SETDISABLEDIMAGELIST = 1078;
    public static final int TB_SETEXTENDEDSTYLE = 1108;
    public static final int TB_SETHOTIMAGELIST = 1076;
    public static final int TB_SETHOTITEM = 1096;
    public static final int TB_SETIMAGELIST = 1072;
    public static final int TB_SETPARENT = 1061;
    public static final int TB_SETROWS = 1063;
    public static final int TB_SETSTATE = 1041;
    public static final int TB_THUMBPOSITION = 4;
    public static final int TBPF_NOPROGRESS = 0;
    public static final int TBPF_INDETERMINATE = 1;
    public static final int TBPF_NORMAL = 2;
    public static final int TBPF_ERROR = 4;
    public static final int TBPF_PAUSED = 8;
    public static final int TCIF_IMAGE = 2;
    public static final int TCIF_TEXT = 1;
    public static final int TCI_SRCCHARSET = 1;
    public static final int TCI_SRCCODEPAGE = 2;
    public static final int TCM_ADJUSTRECT = 4904;
    public static final int TCM_DELETEITEM = 4872;
    public static final int TCM_GETCURSEL = 4875;
    public static final int TCM_GETITEMCOUNT = 4868;
    public static final int TCM_GETITEMRECT = 4874;
    public static final int TCM_GETTOOLTIPS = 4909;
    public static final int TCM_HITTEST = 4877;
    public static final int TCM_INSERTITEM;
    public static final int TCM_SETCURSEL = 4876;
    public static final int TCM_SETIMAGELIST = 4867;
    public static final int TCM_SETITEM;
    public static final int TCN_SELCHANGE = -551;
    public static final int TCN_SELCHANGING = -552;
    public static final int TCS_BOTTOM = 2;
    public static final int TCS_FOCUSNEVER = 32768;
    public static final int TCS_MULTILINE = 512;
    public static final int TCS_TABS = 0;
    public static final int TCS_TOOLTIPS = 16384;
    public static final int TECHNOLOGY = 2;
    public static final int TF_ATTR_INPUT = 0;
    public static final int TF_ATTR_TARGET_CONVERTED = 1;
    public static final int TF_ATTR_CONVERTED = 2;
    public static final int TF_ATTR_TARGET_NOTCONVERTED = 3;
    public static final int TF_ATTR_INPUT_ERROR = 4;
    public static final int TF_ATTR_FIXEDCONVERTED = 5;
    public static final int TF_ATTR_OTHER = -1;
    public static final int TF_CT_NONE = 0;
    public static final int TF_CT_SYSCOLOR = 1;
    public static final int TF_CT_COLORREF = 2;
    public static final int TF_LS_NONE = 0;
    public static final int TF_LS_SOLID = 1;
    public static final int TF_LS_DOT = 2;
    public static final int TF_LS_DASH = 3;
    public static final int TF_LS_SQUIGGLE = 4;
    public static final int TIME_NOSECONDS = 2;
    public static final int TIS_NORMAL = 1;
    public static final int TIS_HOT = 2;
    public static final int TIS_SELECTED = 3;
    public static final int TIS_DISABLED = 4;
    public static final int TIS_FOCUSED = 5;
    public static final int TKP_TRACK = 1;
    public static final int TKP_TRACKVERT = 2;
    public static final int TKP_THUMB = 3;
    public static final int TKP_THUMBBOTTOM = 4;
    public static final int TKP_THUMBTOP = 5;
    public static final int TKP_THUMBVERT = 6;
    public static final int TKP_THUMBLEFT = 7;
    public static final int TKP_THUMBRIGHT = 8;
    public static final int TKP_TICS = 9;
    public static final int TKP_TICSVERT = 10;
    public static final int TME_HOVER = 1;
    public static final int TME_LEAVE = 2;
    public static final int TME_QUERY = 1073741824;
    public static final int TMPF_VECTOR = 2;
    public static final int TMT_CONTENTMARGINS = 3602;
    public static final int TOUCHEVENTF_MOVE = 1;
    public static final int TOUCHEVENTF_DOWN = 2;
    public static final int TOUCHEVENTF_UP = 4;
    public static final int TOUCHEVENTF_INRANGE = 8;
    public static final int TOUCHEVENTF_PRIMARY = 16;
    public static final int TOUCHEVENTF_NOCOALESCE = 32;
    public static final int TOUCHEVENTF_PALM = 128;
    public static final String TOOLBARCLASSNAME = "ToolbarWindow32";
    public static final String TOOLTIPS_CLASS = "tooltips_class32";
    public static final int TP_BUTTON = 1;
    public static final int TP_DROPDOWNBUTTON = 2;
    public static final int TP_SPLITBUTTON = 3;
    public static final int TP_SPLITBUTTONDROPDOWN = 4;
    public static final int TP_SEPARATOR = 5;
    public static final int TP_SEPARATORVERT = 6;
    public static final int TPM_LEFTALIGN = 0;
    public static final int TPM_LEFTBUTTON = 0;
    public static final int TPM_RIGHTBUTTON = 2;
    public static final int TPM_RIGHTALIGN = 8;
    public static final String TRACKBAR_CLASS = "msctls_trackbar32";
    public static final int TRANSPARENT = 1;
    public static final int TREIS_DISABLED = 4;
    public static final int TREIS_HOT = 2;
    public static final int TREIS_NORMAL = 1;
    public static final int TREIS_SELECTED = 3;
    public static final int TREIS_SELECTEDNOTFOCUS = 5;
    public static final int TS_MIN = 0;
    public static final int TS_TRUE = 1;
    public static final int TS_DRAW = 2;
    public static final int TS_NORMAL = 1;
    public static final int TS_HOT = 2;
    public static final int TS_PRESSED = 3;
    public static final int TS_DISABLED = 4;
    public static final int TS_CHECKED = 5;
    public static final int TS_HOTCHECKED = 6;
    public static final int TTDT_AUTOMATIC = 0;
    public static final int TTDT_RESHOW = 1;
    public static final int TTDT_AUTOPOP = 2;
    public static final int TTDT_INITIAL = 3;
    public static final int TTF_ABSOLUTE = 128;
    public static final int TTF_IDISHWND = 1;
    public static final int TTF_SUBCLASS = 16;
    public static final int TTF_RTLREADING = 4;
    public static final int TTF_TRACK = 32;
    public static final int TTF_TRANSPARENT = 256;
    public static final int TTI_NONE = 0;
    public static final int TTI_INFO = 1;
    public static final int TTI_WARNING = 2;
    public static final int TTI_ERROR = 3;
    public static final int TTM_ACTIVATE = 1025;
    public static final int TTM_ADDTOOL;
    public static final int TTM_ADJUSTRECT = 1055;
    public static final int TTM_GETCURRENTTOOLA = 1039;
    public static final int TTM_GETCURRENTTOOLW = 1083;
    public static final int TTM_GETCURRENTTOOL;
    public static final int TTM_GETDELAYTIME = 1045;
    public static final int TTM_DELTOOL;
    public static final int TTM_GETTOOLINFO;
    public static final int TTM_NEWTOOLRECT;
    public static final int TTM_POP = 1052;
    public static final int TTM_SETDELAYTIME = 1027;
    public static final int TTM_SETMAXTIPWIDTH = 1048;
    public static final int TTM_SETTITLEA = 1056;
    public static final int TTM_SETTITLEW = 1057;
    public static final int TTM_SETTITLE;
    public static final int TTM_TRACKPOSITION = 1042;
    public static final int TTM_TRACKACTIVATE = 1041;
    public static final int TTM_UPDATE = 1053;
    public static final int TTN_FIRST = -520;
    public static final int TTN_GETDISPINFO;
    public static final int TTN_GETDISPINFOW = -530;
    public static final int TTN_GETDISPINFOA = -520;
    public static final int TTN_POP = -522;
    public static final int TTN_SHOW = -521;
    public static final int TTS_ALWAYSTIP = 1;
    public static final int TTS_BALLOON = 64;
    public static final int TTS_NOANIMATE = 16;
    public static final int TTS_NOFADE = 32;
    public static final int TTS_NOPREFIX = 2;
    public static final int TV_FIRST = 4352;
    public static final int TVE_COLLAPSE = 1;
    public static final int TVE_COLLAPSERESET = 32768;
    public static final int TVE_EXPAND = 2;
    public static final int TVGN_CARET = 9;
    public static final int TVGN_CHILD = 4;
    public static final int TVGN_DROPHILITED = 8;
    public static final int TVGN_FIRSTVISIBLE = 5;
    public static final int TVGN_LASTVISIBLE = 10;
    public static final int TVGN_NEXT = 1;
    public static final int TVGN_NEXTVISIBLE = 6;
    public static final int TVGN_PARENT = 3;
    public static final int TVGN_PREVIOUS = 2;
    public static final int TVGN_PREVIOUSVISIBLE = 7;
    public static final int TVGN_ROOT = 0;
    public static final int TVHT_ONITEM = 70;
    public static final int TVHT_ONITEMBUTTON = 16;
    public static final int TVHT_ONITEMICON = 2;
    public static final int TVHT_ONITEMINDENT = 8;
    public static final int TVHT_ONITEMRIGHT = 32;
    public static final int TVHT_ONITEMLABEL = 4;
    public static final int TVHT_ONITEMSTATEICON = 64;
    public static final int TVIF_HANDLE = 16;
    public static final int TVIF_IMAGE = 2;
    public static final int TVIF_INTEGRAL = 128;
    public static final int TVIF_PARAM = 4;
    public static final int TVIF_SELECTEDIMAGE = 32;
    public static final int TVIF_STATE = 8;
    public static final int TVIF_TEXT = 1;
    public static final int TVIS_DROPHILITED = 8;
    public static final int TVIS_EXPANDED = 32;
    public static final int TVIS_SELECTED = 2;
    public static final int TVIS_STATEIMAGEMASK = 61440;
    public static final int TVI_FIRST = -65535;
    public static final int TVI_LAST = -65534;
    public static final int TVI_ROOT = -65536;
    public static final int TVI_SORT = -65533;
    public static final int TVM_CREATEDRAGIMAGE = 4370;
    public static final int TVM_DELETEITEM = 4353;
    public static final int TVM_ENSUREVISIBLE = 4372;
    public static final int TVM_EXPAND = 4354;
    public static final int TVM_GETBKCOLOR = 4383;
    public static final int TVM_GETCOUNT = 4357;
    public static final int TVM_GETEXTENDEDSTYLE = 4397;
    public static final int TVM_GETIMAGELIST = 4360;
    public static final int TVM_GETITEM;
    public static final int TVM_GETITEMHEIGHT = 4380;
    public static final int TVM_GETITEMRECT = 4356;
    public static final int TVM_GETITEMSTATE = 4391;
    public static final int TVM_GETNEXTITEM = 4362;
    public static final int TVM_GETTEXTCOLOR = 4384;
    public static final int TVM_GETTOOLTIPS = 4377;
    public static final int TVM_GETVISIBLECOUNT = 4368;
    public static final int TVM_HITTEST = 4369;
    public static final int TVM_INSERTITEM;
    public static final int TVM_MAPACCIDTOHTREEITEM = 4394;
    public static final int TVM_MAPHTREEITEMTOACCID = 4395;
    public static final int TVM_SELECTITEM = 4363;
    public static final int TVM_SETBKCOLOR = 4381;
    public static final int TVM_SETEXTENDEDSTYLE = 4396;
    public static final int TVM_SETIMAGELIST = 4361;
    public static final int TVM_SETINSERTMARK = 4378;
    public static final int TVM_SETITEM;
    public static final int TVM_SETITEMHEIGHT = 4379;
    public static final int TVM_SETSCROLLTIME = 4385;
    public static final int TVM_SETTEXTCOLOR = 4382;
    public static final int TVM_SORTCHILDREN = 4371;
    public static final int TVM_SORTCHILDRENCB = 4373;
    public static final int TVN_BEGINDRAGW = -456;
    public static final int TVN_BEGINDRAGA = -407;
    public static final int TVN_BEGINRDRAGW = -457;
    public static final int TVN_BEGINRDRAGA = -408;
    public static final int TVN_FIRST = -400;
    public static final int TVN_GETDISPINFOA = -403;
    public static final int TVN_GETDISPINFOW = -452;
    public static final int TVN_ITEMCHANGINGW = -417;
    public static final int TVN_ITEMCHANGINGA = -416;
    public static final int TVN_ITEMEXPANDEDA = -406;
    public static final int TVN_ITEMEXPANDEDW = -455;
    public static final int TVN_ITEMEXPANDINGW = -454;
    public static final int TVN_ITEMEXPANDINGA = -405;
    public static final int TVN_SELCHANGEDW = -451;
    public static final int TVN_SELCHANGEDA = -402;
    public static final int TVN_SELCHANGINGW = -450;
    public static final int TVN_SELCHANGINGA = -401;
    public static final int TVP_GLYPH = 2;
    public static final int TVP_TREEITEM = 1;
    public static final int TVSIL_NORMAL = 0;
    public static final int TVSIL_STATE = 2;
    public static final int TVS_DISABLEDRAGDROP = 16;
    public static final int TVS_EX_AUTOHSCROLL = 32;
    public static final int TVS_EX_DOUBLEBUFFER = 4;
    public static final int TVS_EX_DIMMEDCHECKBOXES = 512;
    public static final int TVS_EX_DRAWIMAGEASYNC = 1024;
    public static final int TVS_EX_EXCLUSIONCHECKBOXES = 256;
    public static final int TVS_EX_FADEINOUTEXPANDOS = 64;
    public static final int TVS_EX_MULTISELECT = 2;
    public static final int TVS_EX_NOINDENTSTATE = 8;
    public static final int TVS_EX_PARTIALCHECKBOXES = 128;
    public static final int TVS_EX_RICHTOOLTIP = 16;
    public static final int TVS_FULLROWSELECT = 4096;
    public static final int TVS_HASBUTTONS = 1;
    public static final int TVS_HASLINES = 2;
    public static final int TVS_LINESATROOT = 4;
    public static final int TVS_NOHSCROLL = 32768;
    public static final int TVS_NONEVENHEIGHT = 16384;
    public static final int TVS_NOSCROLL = 8192;
    public static final int TVS_NOTOOLTIPS = 128;
    public static final int TVS_SHOWSELALWAYS = 32;
    public static final int TVS_TRACKSELECT = 512;
    public static final int UDM_GETACCEL = 1132;
    public static final int UDM_GETRANGE32 = 1136;
    public static final int UDM_GETPOS = 1128;
    public static final int UDM_GETPOS32 = 1138;
    public static final int UDM_SETACCEL = 1131;
    public static final int UDM_SETRANGE32 = 1135;
    public static final int UDM_SETPOS = 1127;
    public static final int UDM_SETPOS32 = 1137;
    public static final int UDN_DELTAPOS = -722;
    public static final int UDS_ALIGNLEFT = 8;
    public static final int UDS_ALIGNRIGHT = 4;
    public static final int UDS_AUTOBUDDY = 16;
    public static final int UDS_WRAP = 1;
    public static final int UIS_CLEAR = 2;
    public static final int UIS_INITIALIZE = 3;
    public static final int UIS_SET = 1;
    public static final int UISF_HIDEACCEL = 2;
    public static final int UISF_HIDEFOCUS = 1;
    public static final String UPDOWN_CLASS = "msctls_updown32";
    public static final int USP_E_SCRIPT_NOT_IN_FONT = -2147220992;
    public static final int VERTRES = 10;
    public static final int VK_BACK = 8;
    public static final int VK_CANCEL = 3;
    public static final int VK_CAPITAL = 20;
    public static final int VK_CONTROL = 17;
    public static final int VK_DECIMAL = 110;
    public static final int VK_DELETE = 46;
    public static final int VK_DIVIDE = 111;
    public static final int VK_DOWN = 40;
    public static final int VK_END = 35;
    public static final int VK_ESCAPE = 27;
    public static final int VK_F1 = 112;
    public static final int VK_F10 = 121;
    public static final int VK_F11 = 122;
    public static final int VK_F12 = 123;
    public static final int VK_F13 = 124;
    public static final int VK_F14 = 125;
    public static final int VK_F15 = 126;
    public static final int VK_F16 = 127;
    public static final int VK_F17 = 128;
    public static final int VK_F18 = 129;
    public static final int VK_F19 = 130;
    public static final int VK_F20 = 131;
    public static final int VK_F2 = 113;
    public static final int VK_F3 = 114;
    public static final int VK_F4 = 115;
    public static final int VK_F5 = 116;
    public static final int VK_F6 = 117;
    public static final int VK_F7 = 118;
    public static final int VK_F8 = 119;
    public static final int VK_F9 = 120;
    public static final int VK_HOME = 36;
    public static final int VK_INSERT = 45;
    public static final int VK_L = 76;
    public static final int VK_LBUTTON = 1;
    public static final int VK_LEFT = 37;
    public static final int VK_LCONTROL = 162;
    public static final int VK_LMENU = 164;
    public static final int VK_LSHIFT = 160;
    public static final int VK_MBUTTON = 4;
    public static final int VK_MENU = 18;
    public static final int VK_MULTIPLY = 106;
    public static final int VK_N = 78;
    public static final int VK_NEXT = 34;
    public static final int VK_NUMLOCK = 144;
    public static final int VK_NUMPAD0 = 96;
    public static final int VK_NUMPAD1 = 97;
    public static final int VK_NUMPAD2 = 98;
    public static final int VK_NUMPAD3 = 99;
    public static final int VK_NUMPAD4 = 100;
    public static final int VK_NUMPAD5 = 101;
    public static final int VK_NUMPAD6 = 102;
    public static final int VK_NUMPAD7 = 103;
    public static final int VK_NUMPAD8 = 104;
    public static final int VK_NUMPAD9 = 105;
    public static final int VK_PAUSE = 19;
    public static final int VK_PRIOR = 33;
    public static final int VK_RBUTTON = 2;
    public static final int VK_RETURN = 13;
    public static final int VK_RIGHT = 39;
    public static final int VK_RCONTROL = 163;
    public static final int VK_RMENU = 165;
    public static final int VK_RSHIFT = 161;
    public static final int VK_SCROLL = 145;
    public static final int VK_SEPARATOR = 108;
    public static final int VK_SHIFT = 16;
    public static final int VK_SNAPSHOT = 44;
    public static final int VK_SPACE = 32;
    public static final int VK_SUBTRACT = 109;
    public static final int VK_TAB = 9;
    public static final int VK_UP = 38;
    public static final int VK_XBUTTON1 = 5;
    public static final int VK_XBUTTON2 = 6;
    public static final int VK_ADD = 107;
    public static final int VK_APP1 = 193;
    public static final int VK_APP2 = 194;
    public static final int VK_APP3 = 195;
    public static final int VK_APP4 = 196;
    public static final int VK_APP5 = 197;
    public static final int VK_APP6 = 198;
    public static final int VT_BOOL = 11;
    public static final int VT_LPWSTR = 31;
    public static final short VARIANT_TRUE = -1;
    public static final short VARIANT_FALSE = 0;
    public static final String WC_HEADER = "SysHeader32";
    public static final String WC_LINK = "SysLink";
    public static final String WC_LISTVIEW = "SysListView32";
    public static final String WC_TABCONTROL = "SysTabControl32";
    public static final String WC_TREEVIEW = "SysTreeView32";
    public static final int WINDING = 2;
    public static final int WH_CBT = 5;
    public static final int WH_GETMESSAGE = 3;
    public static final int WH_MSGFILTER = -1;
    public static final int WH_FOREGROUNDIDLE = 11;
    public static final int WHEEL_DELTA = 120;
    public static final int WHEEL_PAGESCROLL = -1;
    public static final int WHITE_BRUSH = 0;
    public static final int WHITENESS = 16711778;
    public static final int WM_ACTIVATE = 6;
    public static final int WM_ACTIVATEAPP = 28;
    public static final int WM_APP = 32768;
    public static final int WM_DWMCOLORIZATIONCOLORCHANGED = 800;
    public static final int WM_CANCELMODE = 31;
    public static final int WM_CAPTURECHANGED = 533;
    public static final int WM_CHANGEUISTATE = 295;
    public static final int WM_CHAR = 258;
    public static final int WM_CLEAR = 771;
    public static final int WM_CLOSE = 16;
    public static final int WM_COMMAND = 273;
    public static final int WM_CONTEXTMENU = 123;
    public static final int WM_COPY = 769;
    public static final int WM_CREATE = 1;
    public static final int WM_CTLCOLORBTN = 309;
    public static final int WM_CTLCOLORDLG = 310;
    public static final int WM_CTLCOLOREDIT = 307;
    public static final int WM_CTLCOLORLISTBOX = 308;
    public static final int WM_CTLCOLORMSGBOX = 306;
    public static final int WM_CTLCOLORSCROLLBAR = 311;
    public static final int WM_CTLCOLORSTATIC = 312;
    public static final int WM_CUT = 768;
    public static final int WM_DEADCHAR = 259;
    public static final int WM_DESTROY = 2;
    public static final int WM_DRAWITEM = 43;
    public static final int WM_ENDSESSION = 22;
    public static final int WM_ENTERIDLE = 289;
    public static final int WM_ERASEBKGND = 20;
    public static final int WM_GESTURE = 281;
    public static final int WM_GETDLGCODE = 135;
    public static final int WM_GETFONT = 49;
    public static final int WM_GETOBJECT = 61;
    public static final int WM_GETMINMAXINFO = 36;
    public static final int WM_HELP = 83;
    public static final int WM_HOTKEY = 786;
    public static final int WM_HSCROLL = 276;
    public static final int WM_IME_CHAR = 646;
    public static final int WM_IME_COMPOSITION = 271;
    public static final int WM_IME_COMPOSITION_START = 269;
    public static final int WM_IME_ENDCOMPOSITION = 270;
    public static final int WM_INITDIALOG = 272;
    public static final int WM_INITMENUPOPUP = 279;
    public static final int WM_INPUTLANGCHANGE = 81;
    public static final int WM_KEYDOWN = 256;
    public static final int WM_KEYFIRST = 256;
    public static final int WM_KEYLAST = 264;
    public static final int WM_KEYUP = 257;
    public static final int WM_KILLFOCUS = 8;
    public static final int WM_LBUTTONDBLCLK = 515;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_MBUTTONDBLCLK = 521;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    public static final int WM_MEASUREITEM = 44;
    public static final int WM_MENUCHAR = 288;
    public static final int WM_MENUSELECT = 287;
    public static final int WM_MOUSEACTIVATE = 33;
    public static final int WM_MOUSEFIRST = 512;
    public static final int WM_MOUSEHOVER = 673;
    public static final int WM_MOUSELEAVE = 675;
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_MOUSEWHEEL = 522;
    public static final int WM_MOUSEHWHEEL = 526;
    public static final int WM_MOUSELAST = 525;
    public static final int WM_MOVE = 3;
    public static final int WM_NCACTIVATE = 134;
    public static final int WM_NCCALCSIZE = 131;
    public static final int WM_NCHITTEST = 132;
    public static final int WM_NCLBUTTONDOWN = 161;
    public static final int WM_NCPAINT = 133;
    public static final int WM_NOTIFY = 78;
    public static final int WM_NULL = 0;
    public static final int WM_PAINT = 15;
    public static final int WM_PALETTECHANGED = 785;
    public static final int WM_PARENTNOTIFY = 528;
    public static final int WM_PASTE = 770;
    public static final int WM_PRINT = 791;
    public static final int WM_PRINTCLIENT = 792;
    public static final int WM_QUERYENDSESSION = 17;
    public static final int WM_QUERYNEWPALETTE = 783;
    public static final int WM_QUERYOPEN = 19;
    public static final int WM_QUERYUISTATE = 297;
    public static final int WM_RBUTTONDBLCLK = 518;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_SETCURSOR = 32;
    public static final int WM_SETFOCUS = 7;
    public static final int WM_SETFONT = 48;
    public static final int WM_SETICON = 128;
    public static final int WM_SETREDRAW = 11;
    public static final int WM_SETTEXT = 12;
    public static final int WM_SETTINGCHANGE = 26;
    public static final int WM_SHOWWINDOW = 24;
    public static final int WM_SIZE = 5;
    public static final int WM_SYSCHAR = 262;
    public static final int WM_SYSCOLORCHANGE = 21;
    public static final int WM_SYSCOMMAND = 274;
    public static final int WM_SYSKEYDOWN = 260;
    public static final int WM_SYSKEYUP = 261;
    public static final int WM_TABLET_FLICK = 715;
    public static final int WM_TIMER = 275;
    public static final int WM_THEMECHANGED = 794;
    public static final int WM_TOUCH = 576;
    public static final int WM_UNDO = 772;
    public static final int WM_UPDATEUISTATE = 296;
    public static final int WM_USER = 1024;
    public static final int WM_VSCROLL = 277;
    public static final int WM_WINDOWPOSCHANGED = 71;
    public static final int WM_WINDOWPOSCHANGING = 70;
    public static final int WS_BORDER = 8388608;
    public static final int WS_CAPTION = 12582912;
    public static final int WS_CHILD = 1073741824;
    public static final int WS_CLIPCHILDREN = 33554432;
    public static final int WS_CLIPSIBLINGS = 67108864;
    public static final int WS_DISABLED = 67108864;
    public static final int WS_EX_APPWINDOW = 262144;
    public static final int WS_EX_CAPTIONOKBTN = Integer.MIN_VALUE;
    public static final int WS_EX_CLIENTEDGE = 512;
    public static final int WS_EX_COMPOSITED = 33554432;
    public static final int WS_EX_DLGMODALFRAME = 1;
    public static final int WS_EX_LAYERED = 524288;
    public static final int WS_EX_LAYOUTRTL = 4194304;
    public static final int WS_EX_LEFTSCROLLBAR = 16384;
    public static final int WS_EX_MDICHILD = 64;
    public static final int WS_EX_NOINHERITLAYOUT = 1048576;
    public static final int WS_EX_NOACTIVATE = 134217728;
    public static final int WS_EX_RIGHT = 4096;
    public static final int WS_EX_RTLREADING = 8192;
    public static final int WS_EX_STATICEDGE = 131072;
    public static final int WS_EX_TOOLWINDOW = 128;
    public static final int WS_EX_TOPMOST = 8;
    public static final int WS_EX_TRANSPARENT = 32;
    public static final int WS_HSCROLL = 1048576;
    public static final int WS_MAXIMIZEBOX;
    public static final int WS_MINIMIZEBOX;
    public static final int WS_OVERLAPPED;
    public static final int WS_OVERLAPPEDWINDOW = 13565952;
    public static final int WS_POPUP = Integer.MIN_VALUE;
    public static final int WS_SYSMENU = 524288;
    public static final int WS_TABSTOP = 65536;
    public static final int WS_THICKFRAME = 262144;
    public static final int WS_VISIBLE = 268435456;
    public static final int WS_VSCROLL = 2097152;
    public static final int WM_XBUTTONDOWN = 523;
    public static final int WM_XBUTTONUP = 524;
    public static final int WM_XBUTTONDBLCLK = 525;
    public static final int XBUTTON1 = 1;
    public static final int XBUTTON2 = 2;
    public static final int X509_ASN_ENCODING = 1;
    public static final int PROCESS_DUP_HANDLE = 64;
    public static final int PROCESS_VM_READ = 16;
    public static final int DUPLICATE_SAME_ACCESS = 2;
    
    static {
        Library.loadLibrary("swt");
        OSVERSIONINFO osversioninfo = new OSVERSIONINFOW();
        osversioninfo.dwOSVersionInfoSize = OSVERSIONINFOW.sizeof;
        if (!GetVersionExW((OSVERSIONINFOW)osversioninfo)) {
            osversioninfo = new OSVERSIONINFOA();
            osversioninfo.dwOSVersionInfoSize = OSVERSIONINFOA.sizeof;
            GetVersionExA((OSVERSIONINFOA)osversioninfo);
        }
        OSVERSIONINFO.sizeof = osversioninfo.dwOSVersionInfoSize;
        IsWin32s = (osversioninfo.dwPlatformId == 0);
        IsWin95 = (osversioninfo.dwPlatformId == 1);
        IsWinNT = (osversioninfo.dwPlatformId == 2);
        IsWinCE = (osversioninfo.dwPlatformId == 3);
        IsSP = IsSP();
        IsPPC = IsPPC();
        IsHPC = (OS.IsWinCE && !OS.IsPPC && !OS.IsSP);
        WIN32_MAJOR = osversioninfo.dwMajorVersion;
        WIN32_MINOR = osversioninfo.dwMinorVersion;
        WIN32_VERSION = VERSION(OS.WIN32_MAJOR, OS.WIN32_MINOR);
        IsUnicode = (!OS.IsWin32s && !OS.IsWin95);
        if (System.getProperty("org.eclipse.swt.internal.win32.OS.NO_MANIFEST") == null && !OS.IsWinCE && OS.WIN32_VERSION >= VERSION(5, 1)) {
            TCHAR tchar;
            for (tchar = new TCHAR(0, 260); GetModuleFileName(GetLibraryHandle(), tchar, tchar.length()) == tchar.length(); tchar = new TCHAR(0, tchar.length() + 260)) {}
            final int getProcessHeap = GetProcessHeap();
            final int n = tchar.length() * (OS.IsUnicode ? 2 : 1);
            final int heapAlloc = HeapAlloc(getProcessHeap, 8, n);
            MoveMemory(heapAlloc, tchar, n);
            final ACTCTX actctx = new ACTCTX();
            actctx.cbSize = ACTCTX.sizeof;
            actctx.dwFlags = 24;
            actctx.lpSource = heapAlloc;
            actctx.lpResourceName = 2;
            final int createActCtx = CreateActCtx(actctx);
            if (heapAlloc != 0) {
                HeapFree(getProcessHeap, 0, heapAlloc);
            }
            ActivateActCtx(createActCtx, new int[1]);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= VERSION(6, 0)) {
            SetProcessDPIAware();
        }
        final boolean b = GetSystemMetrics(42) != 0;
        final boolean b2 = GetSystemMetrics(82) != 0;
        IsDBLocale = (b || b2);
        if (!OS.IsWinCE && OS.WIN32_VERSION == VERSION(5, 1) && PRIMARYLANGID(GetSystemDefaultUILanguage()) == 18) {
            final OSVERSIONINFOEX osversioninfoex = OS.IsUnicode ? new OSVERSIONINFOEXW() : new OSVERSIONINFOEXA();
            osversioninfoex.dwOSVersionInfoSize = OSVERSIONINFOEX.sizeof;
            GetVersionEx(osversioninfoex);
            if (osversioninfoex.wServicePackMajor < 2) {
                ImmDisableTextFrameService(0);
            }
        }
        final DLLVERSIONINFO dllversioninfo = new DLLVERSIONINFO();
        dllversioninfo.cbSize = DLLVERSIONINFO.sizeof;
        dllversioninfo.dwMajorVersion = 4;
        dllversioninfo.dwMinorVersion = 0;
        final int loadLibrary = LoadLibrary(new TCHAR(0, "comctl32.dll", true));
        if (loadLibrary != 0) {
            final String s = "DllGetVersion\u0000";
            final byte[] array = new byte[s.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = (byte)s.charAt(i);
            }
            final int getProcAddress = GetProcAddress(loadLibrary, array);
            if (getProcAddress != 0) {
                Call(getProcAddress, dllversioninfo);
            }
            FreeLibrary(loadLibrary);
        }
        COMCTL32_MAJOR = dllversioninfo.dwMajorVersion;
        COMCTL32_MINOR = dllversioninfo.dwMinorVersion;
        COMCTL32_VERSION = VERSION(OS.COMCTL32_MAJOR, OS.COMCTL32_MINOR);
        final DLLVERSIONINFO dllversioninfo2 = new DLLVERSIONINFO();
        dllversioninfo2.cbSize = DLLVERSIONINFO.sizeof;
        dllversioninfo2.dwMajorVersion = 4;
        final int loadLibrary2 = LoadLibrary(new TCHAR(0, "Shell32.dll", true));
        if (loadLibrary2 != 0) {
            final String s2 = "DllGetVersion\u0000";
            final byte[] array2 = new byte[s2.length()];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = (byte)s2.charAt(j);
            }
            final int getProcAddress2 = GetProcAddress(loadLibrary2, array2);
            if (getProcAddress2 != 0) {
                Call(getProcAddress2, dllversioninfo2);
            }
            FreeLibrary(loadLibrary2);
        }
        SHELL32_MAJOR = dllversioninfo2.dwMajorVersion;
        SHELL32_MINOR = dllversioninfo2.dwMinorVersion;
        SHELL32_VERSION = VERSION(OS.SHELL32_MAJOR, OS.SHELL32_MINOR);
        SYS_COLOR_INDEX_FLAG = (OS.IsWinCE ? 1073741824 : 0);
        BFFM_SETSELECTION = (OS.IsUnicode ? 1127 : 1126);
        BFFM_VALIDATEFAILED = (OS.IsUnicode ? 4 : 3);
        COLOR_3DDKSHADOW = (0x15 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_3DFACE = (0xF | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_3DHIGHLIGHT = (0x14 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_3DHILIGHT = (0x14 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_3DLIGHT = (0x16 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_3DSHADOW = (0x10 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_ACTIVECAPTION = (0x2 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_BTNFACE = (0xF | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_BTNHIGHLIGHT = (0x14 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_BTNSHADOW = (0x10 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_BTNTEXT = (0x12 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_CAPTIONTEXT = (0x9 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_GRADIENTACTIVECAPTION = (0x1B | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_GRADIENTINACTIVECAPTION = (0x1C | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_GRAYTEXT = (0x11 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_HIGHLIGHT = (0xD | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_HIGHLIGHTTEXT = (0xE | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_HOTLIGHT = (0x1A | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_INACTIVECAPTION = (0x3 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_INACTIVECAPTIONTEXT = (0x13 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_INFOBK = (0x18 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_INFOTEXT = (0x17 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_MENU = (0x4 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_MENUTEXT = (0x7 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_SCROLLBAR = OS.SYS_COLOR_INDEX_FLAG;
        COLOR_WINDOW = (0x5 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_WINDOWFRAME = (0x6 | OS.SYS_COLOR_INDEX_FLAG);
        COLOR_WINDOWTEXT = (0x8 | OS.SYS_COLOR_INDEX_FLAG);
        DTM_SETFORMAT = (OS.IsUnicode ? 4146 : 4101);
        HDM_GETITEM = (OS.IsUnicode ? 4619 : 4611);
        HDM_INSERTITEM = (OS.IsUnicode ? 4618 : 4609);
        HDM_SETITEM = (OS.IsUnicode ? 4620 : 4612);
        HDN_BEGINTRACK = (OS.IsUnicode ? -326 : -306);
        HDN_DIVIDERDBLCLICK = (OS.IsUnicode ? -325 : -305);
        HDN_ITEMCHANGED = (OS.IsUnicode ? -321 : -301);
        HDN_ITEMDBLCLICK = (OS.IsUnicode ? -323 : -303);
        LVM_GETCOLUMN = (OS.IsUnicode ? 4191 : 4121);
        LVM_GETITEM = (OS.IsUnicode ? 4171 : 4101);
        LVM_GETSTRINGWIDTH = (OS.IsUnicode ? 4183 : 4113);
        LVM_INSERTCOLUMN = (OS.IsUnicode ? 4193 : 4123);
        LVM_INSERTITEM = (OS.IsUnicode ? 4173 : 4103);
        LVM_SETCOLUMN = (OS.IsUnicode ? 4192 : 4122);
        LVM_SETITEM = (OS.IsUnicode ? 4172 : 4102);
        NOTIFYICONDATAA_V2_SIZE = NOTIFYICONDATAA_V2_SIZE();
        NOTIFYICONDATAW_V2_SIZE = NOTIFYICONDATAW_V2_SIZE();
        NOTIFYICONDATA_V2_SIZE = (OS.IsUnicode ? OS.NOTIFYICONDATAW_V2_SIZE : OS.NOTIFYICONDATAA_V2_SIZE);
        RB_GETBANDINFO = (OS.IsUnicode ? 1052 : 1053);
        RB_INSERTBAND = (OS.IsUnicode ? 1034 : 1025);
        RB_SETBANDINFO = (OS.IsUnicode ? 1035 : 1030);
        SW_RESTORE = (OS.IsWinCE ? 13 : 9);
        SW_SHOWMAXIMIZED = (OS.IsWinCE ? 11 : 3);
        TB_ADDSTRING = (OS.IsUnicode ? 1101 : 1052);
        TB_GETBUTTONINFO = (OS.IsUnicode ? 1087 : 1089);
        TB_GETBUTTONTEXT = (OS.IsUnicode ? 1099 : 1069);
        TB_INSERTBUTTON = (OS.IsUnicode ? 1091 : 1045);
        TB_MAPACCELERATOR = 1024 + (OS.IsUnicode ? 90 : 78);
        TB_SETBUTTONINFO = (OS.IsUnicode ? 1088 : 1090);
        TCM_INSERTITEM = (OS.IsUnicode ? 4926 : 4871);
        TCM_SETITEM = (OS.IsUnicode ? 4925 : 4870);
        TTM_ADDTOOL = (OS.IsUnicode ? 1074 : 1028);
        TTM_GETCURRENTTOOL = 1024 + (OS.IsUnicode ? 59 : 15);
        TTM_DELTOOL = (OS.IsUnicode ? 1075 : 1029);
        TTM_GETTOOLINFO = 1024 + (OS.IsUnicode ? 53 : 8);
        TTM_NEWTOOLRECT = 1024 + (OS.IsUnicode ? 52 : 6);
        TTM_SETTITLE = 1024 + (OS.IsUnicode ? 33 : 32);
        TTN_GETDISPINFO = (OS.IsUnicode ? -530 : -520);
        TVM_GETITEM = (OS.IsUnicode ? 4414 : 4364);
        TVM_INSERTITEM = (OS.IsUnicode ? 4402 : 4352);
        TVM_SETITEM = (OS.IsUnicode ? 4415 : 4365);
        WS_MAXIMIZEBOX = (OS.IsWinCE ? 131072 : 65536);
        WS_MINIMIZEBOX = (OS.IsWinCE ? 65536 : 131072);
        WS_OVERLAPPED = (OS.IsWinCE ? 12582912 : 0);
    }
    
    public static int VERSION(final int n, final int n2) {
        return n << 16 | n2;
    }
    
    public static final native int ACCEL_sizeof();
    
    public static final native int ACTCTX_sizeof();
    
    public static final native int BITMAP_sizeof();
    
    public static final native int BITMAPINFOHEADER_sizeof();
    
    public static final native int BLENDFUNCTION_sizeof();
    
    public static final native int BP_PAINTPARAMS_sizeof();
    
    public static final native int BROWSEINFO_sizeof();
    
    public static final native int BUTTON_IMAGELIST_sizeof();
    
    public static final native int CANDIDATEFORM_sizeof();
    
    public static final native int CERT_CONTEXT_sizeof();
    
    public static final native int CERT_INFO_sizeof();
    
    public static final native int CERT_NAME_BLOB_sizeof();
    
    public static final native int CERT_PUBLIC_KEY_INFO_sizeof();
    
    public static final native int CHOOSECOLOR_sizeof();
    
    public static final native int CHOOSEFONT_sizeof();
    
    public static final native int COMBOBOXINFO_sizeof();
    
    public static final native int COMPOSITIONFORM_sizeof();
    
    public static final native int CREATESTRUCT_sizeof();
    
    public static final native int CRYPT_ALGORITHM_IDENTIFIER_sizeof();
    
    public static final native int CRYPT_BIT_BLOB_sizeof();
    
    public static final native int CRYPT_INTEGER_BLOB_sizeof();
    
    public static final native int CRYPT_OBJID_BLOB_sizeof();
    
    public static final native int DEVMODEA_sizeof();
    
    public static final native int DEVMODEW_sizeof();
    
    public static final native int DIBSECTION_sizeof();
    
    public static final native int DLLVERSIONINFO_sizeof();
    
    public static final native int DOCHOSTUIINFO_sizeof();
    
    public static final native int DOCINFO_sizeof();
    
    public static final native int DRAWITEMSTRUCT_sizeof();
    
    public static final native int DROPFILES_sizeof();
    
    public static final native int DWM_BLURBEHIND_sizeof();
    
    public static final native int EMR_sizeof();
    
    public static final native int EMREXTCREATEFONTINDIRECTW_sizeof();
    
    public static final native int EXTLOGFONTW_sizeof();
    
    public static final native int EXTLOGPEN_sizeof();
    
    public static final native int FILETIME_sizeof();
    
    public static final native int FLICK_DATA_sizeof();
    
    public static final native int FLICK_POINT_sizeof();
    
    public static final native int GCP_RESULTS_sizeof();
    
    public static final native int GESTURECONFIG_sizeof();
    
    public static final native int GESTUREINFO_sizeof();
    
    public static final native int GRADIENT_RECT_sizeof();
    
    public static final native int GUITHREADINFO_sizeof();
    
    public static final native int HDITEM_sizeof();
    
    public static final native int HDLAYOUT_sizeof();
    
    public static final native int HDHITTESTINFO_sizeof();
    
    public static final native int HELPINFO_sizeof();
    
    public static final native int HIGHCONTRAST_sizeof();
    
    public static final native int ICONINFO_sizeof();
    
    public static final native int INITCOMMONCONTROLSEX_sizeof();
    
    public static final native int INPUT_sizeof();
    
    public static final native int KEYBDINPUT_sizeof();
    
    public static final native int LITEM_sizeof();
    
    public static final native int LOGBRUSH_sizeof();
    
    public static final native int LOGFONTA_sizeof();
    
    public static final native int LOGFONTW_sizeof();
    
    public static final native int LOGPEN_sizeof();
    
    public static final native int LVCOLUMN_sizeof();
    
    public static final native int LVHITTESTINFO_sizeof();
    
    public static final native int LVITEM_sizeof();
    
    public static final native int LVINSERTMARK_sizeof();
    
    public static final native int MARGINS_sizeof();
    
    public static final native int MCHITTESTINFO_sizeof();
    
    public static final native int MEASUREITEMSTRUCT_sizeof();
    
    public static final native int MENUBARINFO_sizeof();
    
    public static final native int MENUINFO_sizeof();
    
    public static final native int MENUITEMINFO_sizeof();
    
    public static final native int MINMAXINFO_sizeof();
    
    public static final native int MOUSEINPUT_sizeof();
    
    public static final native int MONITORINFO_sizeof();
    
    public static final native int MSG_sizeof();
    
    public static final native int NMCUSTOMDRAW_sizeof();
    
    public static final native int NMHDR_sizeof();
    
    public static final native int NMHEADER_sizeof();
    
    public static final native int NMLINK_sizeof();
    
    public static final native int NMLISTVIEW_sizeof();
    
    public static final native int NMLVCUSTOMDRAW_sizeof();
    
    public static final native int NMLVDISPINFO_sizeof();
    
    public static final native int NMLVFINDITEM_sizeof();
    
    public static final native int NMLVODSTATECHANGE_sizeof();
    
    public static final native int NMREBARCHEVRON_sizeof();
    
    public static final native int NMREBARCHILDSIZE_sizeof();
    
    public static final native int NMRGINFO_sizeof();
    
    public static final native int NMTBHOTITEM_sizeof();
    
    public static final native int NMTREEVIEW_sizeof();
    
    public static final native int NMTOOLBAR_sizeof();
    
    public static final native int NMTTDISPINFOA_sizeof();
    
    public static final native int NMTTDISPINFOW_sizeof();
    
    public static final native int NMTTCUSTOMDRAW_sizeof();
    
    public static final native int NMTVCUSTOMDRAW_sizeof();
    
    public static final native int NMTVDISPINFO_sizeof();
    
    public static final native int NMTVITEMCHANGE_sizeof();
    
    public static final native int NMUPDOWN_sizeof();
    
    public static final native int NONCLIENTMETRICSA_sizeof();
    
    public static final native int NONCLIENTMETRICSW_sizeof();
    
    public static final native int NOTIFYICONDATAA_V2_SIZE();
    
    public static final native int NOTIFYICONDATAW_V2_SIZE();
    
    public static final native int OFNOTIFY_sizeof();
    
    public static final native int OPENFILENAME_sizeof();
    
    public static final native int OSVERSIONINFOA_sizeof();
    
    public static final native int OSVERSIONINFOW_sizeof();
    
    public static final native int OSVERSIONINFOEXA_sizeof();
    
    public static final native int OSVERSIONINFOEXW_sizeof();
    
    public static final native int OUTLINETEXTMETRICA_sizeof();
    
    public static final native int OUTLINETEXTMETRICW_sizeof();
    
    public static final native int PAINTSTRUCT_sizeof();
    
    public static final native int PANOSE_sizeof();
    
    public static final native int POINT_sizeof();
    
    public static final native int PRINTDLG_sizeof();
    
    public static final native int PROCESS_INFORMATION_sizeof();
    
    public static final native int PROPVARIANT_sizeof();
    
    public static final native int PROPERTYKEY_sizeof();
    
    public static final native int REBARBANDINFO_sizeof();
    
    public static final native int RECT_sizeof();
    
    public static final native int SAFEARRAY_sizeof();
    
    public static final native int SAFEARRAYBOUND_sizeof();
    
    public static final native int SCRIPT_ANALYSIS_sizeof();
    
    public static final native int SCRIPT_CONTROL_sizeof();
    
    public static final native int SCRIPT_DIGITSUBSTITUTE_sizeof();
    
    public static final native int SCRIPT_FONTPROPERTIES_sizeof();
    
    public static final native int SCRIPT_ITEM_sizeof();
    
    public static final native int SCRIPT_LOGATTR_sizeof();
    
    public static final native int SCRIPT_PROPERTIES_sizeof();
    
    public static final native int SCRIPT_STATE_sizeof();
    
    public static final native int SCRIPT_STRING_ANALYSIS_sizeof();
    
    public static final native int SCROLLBARINFO_sizeof();
    
    public static final native int SCROLLINFO_sizeof();
    
    public static final native int SHACTIVATEINFO_sizeof();
    
    public static final native int SHDRAGIMAGE_sizeof();
    
    public static final native int SHELLEXECUTEINFO_sizeof();
    
    public static final native int SHFILEINFOA_sizeof();
    
    public static final native int SHFILEINFOW_sizeof();
    
    public static final native int SHMENUBARINFO_sizeof();
    
    public static final native int SHRGINFO_sizeof();
    
    public static final native int SIPINFO_sizeof();
    
    public static final native int SIZE_sizeof();
    
    public static final native int STARTUPINFO_sizeof();
    
    public static final native int SYSTEMTIME_sizeof();
    
    public static final native int TBBUTTON_sizeof();
    
    public static final native int TBBUTTONINFO_sizeof();
    
    public static final native int TCITEM_sizeof();
    
    public static final native int TCHITTESTINFO_sizeof();
    
    public static final native int TEXTMETRICA_sizeof();
    
    public static final native int TEXTMETRICW_sizeof();
    
    public static final native int TF_DA_COLOR_sizeof();
    
    public static final native int TF_DISPLAYATTRIBUTE_sizeof();
    
    public static final native int TOOLINFO_sizeof();
    
    public static final native int TOUCHINPUT_sizeof();
    
    public static final native int TRACKMOUSEEVENT_sizeof();
    
    public static final native int TRIVERTEX_sizeof();
    
    public static final native int TVHITTESTINFO_sizeof();
    
    public static final native int TVINSERTSTRUCT_sizeof();
    
    public static final native int TVITEM_sizeof();
    
    public static final native int TVITEMEX_sizeof();
    
    public static final native int TVSORTCB_sizeof();
    
    public static final native int UDACCEL_sizeof();
    
    public static final native int WINDOWPLACEMENT_sizeof();
    
    public static final native int WINDOWPOS_sizeof();
    
    public static final native int WNDCLASS_sizeof();
    
    public static final int AddFontResourceEx(final TCHAR tchar, final int n, final int n2) {
        if (OS.IsUnicode) {
            return AddFontResourceExW((char[])((tchar == null) ? null : tchar.chars), n, n2);
        }
        return AddFontResourceExA((byte[])((tchar == null) ? null : tchar.bytes), n, n2);
    }
    
    public static final int AssocQueryString(final int n, final int n2, final TCHAR tchar, final TCHAR tchar2, final TCHAR tchar3, final int[] array) {
        if (OS.IsUnicode) {
            return AssocQueryStringW(n, n2, (char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), (char[])((tchar3 == null) ? null : tchar3.chars), array);
        }
        return AssocQueryStringA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), (byte[])((tchar3 == null) ? null : tchar3.bytes), array);
    }
    
    public static final int CallWindowProc(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (OS.IsUnicode) {
            return CallWindowProcW(n, n2, n3, n4, n5);
        }
        return CallWindowProcA(n, n2, n3, n4, n5);
    }
    
    public static final int CertNameToStr(final int n, final CERT_NAME_BLOB cert_NAME_BLOB, final int n2, final TCHAR tchar, final int n3) {
        if (OS.IsUnicode) {
            return CertNameToStrW(n, cert_NAME_BLOB, n2, (char[])((tchar == null) ? null : tchar.chars), n3);
        }
        return CertNameToStrA(n, cert_NAME_BLOB, n2, (byte[])((tchar == null) ? null : tchar.bytes), n3);
    }
    
    public static final int CharUpper(final int n) {
        if (OS.IsUnicode) {
            return CharUpperW(n);
        }
        return CharUpperA(n);
    }
    
    public static final int CharLower(final int n) {
        if (OS.IsUnicode) {
            return CharLowerW(n);
        }
        return CharLowerA(n);
    }
    
    public static final boolean ChooseColor(final CHOOSECOLOR choosecolor) {
        if (OS.IsUnicode) {
            return ChooseColorW(choosecolor);
        }
        return ChooseColorA(choosecolor);
    }
    
    public static final boolean ChooseFont(final CHOOSEFONT choosefont) {
        if (OS.IsUnicode) {
            return ChooseFontW(choosefont);
        }
        return ChooseFontA(choosefont);
    }
    
    public static final int CreateActCtx(final ACTCTX actctx) {
        if (OS.IsUnicode) {
            return CreateActCtxW(actctx);
        }
        return CreateActCtxA(actctx);
    }
    
    public static final int CreateAcceleratorTable(final byte[] array, final int n) {
        if (OS.IsUnicode) {
            return CreateAcceleratorTableW(array, n);
        }
        return CreateAcceleratorTableA(array, n);
    }
    
    public static final int CreateDC(final TCHAR tchar, final TCHAR tchar2, final int n, final int n2) {
        if (OS.IsUnicode) {
            return CreateDCW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n, n2);
        }
        return CreateDCA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n, n2);
    }
    
    public static final int CreateEnhMetaFile(final int n, final TCHAR tchar, final RECT rect, final TCHAR tchar2) {
        if (OS.IsUnicode) {
            return CreateEnhMetaFileW(n, (char[])((tchar == null) ? null : tchar.chars), rect, (char[])((tchar2 == null) ? null : tchar2.chars));
        }
        return CreateEnhMetaFileA(n, (byte[])((tchar == null) ? null : tchar.bytes), rect, (byte[])((tchar2 == null) ? null : tchar2.bytes));
    }
    
    public static final int CreateFontIndirect(final int n) {
        if (OS.IsUnicode) {
            return CreateFontIndirectW(n);
        }
        return CreateFontIndirectA(n);
    }
    
    public static final int CreateFontIndirect(final LOGFONT logfont) {
        if (OS.IsUnicode) {
            return CreateFontIndirectW((LOGFONTW)logfont);
        }
        return CreateFontIndirectA((LOGFONTA)logfont);
    }
    
    public static final boolean CreateProcess(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6, final int n7, final STARTUPINFO startupinfo, final PROCESS_INFORMATION process_INFORMATION) {
        if (OS.IsUnicode) {
            return CreateProcessW(n, n2, n3, n4, b, n5, n6, n7, startupinfo, process_INFORMATION);
        }
        return CreateProcessA(n, n2, n3, n4, b, n5, n6, n7, startupinfo, process_INFORMATION);
    }
    
    public static final int CreateWindowEx(final int n, final TCHAR tchar, final TCHAR tchar2, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final CREATESTRUCT createstruct) {
        if (OS.IsUnicode) {
            return CreateWindowExW(n, (char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n2, n3, n4, n5, n6, n7, n8, n9, createstruct);
        }
        return CreateWindowExA(n, (byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n2, n3, n4, n5, n6, n7, n8, n9, createstruct);
    }
    
    public static final int DefMDIChildProc(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return DefMDIChildProcW(n, n2, n3, n4);
        }
        return DefMDIChildProcA(n, n2, n3, n4);
    }
    
    public static final int DefFrameProc(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (OS.IsUnicode) {
            return DefFrameProcW(n, n2, n3, n4, n5);
        }
        return DefFrameProcA(n, n2, n3, n4, n5);
    }
    
    public static final int DefWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return DefWindowProcW(n, n2, n3, n4);
        }
        return DefWindowProcA(n, n2, n3, n4);
    }
    
    public static final int DispatchMessage(final MSG msg) {
        if (OS.IsUnicode) {
            return DispatchMessageW(msg);
        }
        return DispatchMessageA(msg);
    }
    
    public static final int DragQueryFile(final int n, final int n2, final TCHAR tchar, final int n3) {
        if (OS.IsUnicode) {
            return DragQueryFileW(n, n2, (char[])((tchar == null) ? null : tchar.chars), n3);
        }
        return DragQueryFileA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), n3);
    }
    
    public static final boolean DrawState(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        if (OS.IsUnicode) {
            return DrawStateW(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        }
        return DrawStateA(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public static final int DrawText(final int n, final TCHAR tchar, final int n2, final RECT rect, final int n3) {
        if (OS.IsUnicode) {
            return DrawTextW(n, (char[])((tchar == null) ? null : tchar.chars), n2, rect, n3);
        }
        return DrawTextA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, rect, n3);
    }
    
    public static final int EnumFontFamilies(final int n, final TCHAR tchar, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return EnumFontFamiliesW(n, (char[])((tchar == null) ? null : tchar.chars), n2, n3);
        }
        return EnumFontFamiliesA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, n3);
    }
    
    public static final int EnumFontFamiliesEx(final int n, final LOGFONT logfont, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return EnumFontFamiliesExW(n, (LOGFONTW)logfont, n2, n3, n4);
        }
        return EnumFontFamiliesExA(n, (LOGFONTA)logfont, n2, n3, n4);
    }
    
    public static final boolean EnumSystemLocales(final int n, final int n2) {
        if (OS.IsUnicode) {
            return EnumSystemLocalesW(n, n2);
        }
        return EnumSystemLocalesA(n, n2);
    }
    
    public static final boolean EnumSystemLanguageGroups(final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return EnumSystemLanguageGroupsW(n, n2, n3);
        }
        return EnumSystemLanguageGroupsA(n, n2, n3);
    }
    
    public static final int ExpandEnvironmentStrings(final TCHAR tchar, final TCHAR tchar2, final int n) {
        if (OS.IsUnicode) {
            return ExpandEnvironmentStringsW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n);
        }
        return ExpandEnvironmentStringsA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n);
    }
    
    public static final int ExtractIconEx(final TCHAR tchar, final int n, final int[] array, final int[] array2, final int n2) {
        if (OS.IsUnicode) {
            return ExtractIconExW((char[])((tchar == null) ? null : tchar.chars), n, array, array2, n2);
        }
        return ExtractIconExA((byte[])((tchar == null) ? null : tchar.bytes), n, array, array2, n2);
    }
    
    public static final boolean ExtTextOut(final int n, final int n2, final int n3, final int n4, final RECT rect, final TCHAR tchar, final int n5, final int[] array) {
        if (OS.IsUnicode) {
            return ExtTextOutW(n, n2, n3, n4, rect, (char[])((tchar == null) ? null : tchar.chars), n5, array);
        }
        return ExtTextOutA(n, n2, n3, n4, rect, (byte[])((tchar == null) ? null : tchar.bytes), n5, array);
    }
    
    public static final int FindWindow(final TCHAR tchar, final TCHAR tchar2) {
        if (OS.IsUnicode) {
            return FindWindowW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars));
        }
        return FindWindowA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes));
    }
    
    public static final int FormatMessage(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        if (OS.IsUnicode) {
            return FormatMessageW(n, n2, n3, n4, array, n5, n6);
        }
        return FormatMessageA(n, n2, n3, n4, array, n5, n6);
    }
    
    public static final boolean GetCharABCWidths(final int n, final int n2, final int n3, final int[] array) {
        if (OS.IsUnicode) {
            return GetCharABCWidthsW(n, n2, n3, array);
        }
        return GetCharABCWidthsA(n, n2, n3, array);
    }
    
    public static final int GetCharacterPlacement(final int n, final TCHAR tchar, final int n2, final int n3, final GCP_RESULTS gcp_RESULTS, final int n4) {
        if (OS.IsUnicode) {
            return GetCharacterPlacementW(n, (char[])((tchar == null) ? null : tchar.chars), n2, n3, gcp_RESULTS, n4);
        }
        return GetCharacterPlacementA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, n3, gcp_RESULTS, n4);
    }
    
    public static final boolean GetCharWidth(final int n, final int n2, final int n3, final int[] array) {
        if (OS.IsUnicode) {
            return GetCharWidthW(n, n2, n3, array);
        }
        return GetCharWidthA(n, n2, n3, array);
    }
    
    public static final boolean GetClassInfo(final int n, final TCHAR tchar, final WNDCLASS wndclass) {
        if (OS.IsUnicode) {
            return GetClassInfoW(n, (char[])((tchar == null) ? null : tchar.chars), wndclass);
        }
        return GetClassInfoA(n, (byte[])((tchar == null) ? null : tchar.bytes), wndclass);
    }
    
    public static final int GetClassName(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            return GetClassNameW(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        return GetClassNameA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
    }
    
    public static final int GetClipboardFormatName(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            return GetClipboardFormatNameW(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        return GetClipboardFormatNameA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
    }
    
    public static final int GetDateFormat(final int n, final int n2, final SYSTEMTIME systemtime, final TCHAR tchar, final TCHAR tchar2, final int n3) {
        if (OS.IsUnicode) {
            return GetDateFormatW(n, n2, systemtime, (char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n3);
        }
        return GetDateFormatA(n, n2, systemtime, (byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n3);
    }
    
    public static final int GetKeyNameText(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            return GetKeyNameTextW(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        return GetKeyNameTextA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
    }
    
    public static final int GetLocaleInfo(final int n, final int n2, final TCHAR tchar, final int n3) {
        if (OS.IsUnicode) {
            return GetLocaleInfoW(n, n2, (char[])((tchar == null) ? null : tchar.chars), n3);
        }
        return GetLocaleInfoA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), n3);
    }
    
    public static final boolean GetMenuItemInfo(final int n, final int n2, final boolean b, final MENUITEMINFO menuiteminfo) {
        if (OS.IsUnicode) {
            return GetMenuItemInfoW(n, n2, b, menuiteminfo);
        }
        return GetMenuItemInfoA(n, n2, b, menuiteminfo);
    }
    
    public static final boolean GetMessage(final MSG msg, final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return GetMessageW(msg, n, n2, n3);
        }
        return GetMessageA(msg, n, n2, n3);
    }
    
    public static final int GetModuleFileName(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            return GetModuleFileNameW(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        return GetModuleFileNameA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
    }
    
    public static final int GetModuleHandle(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return GetModuleHandleW((char[])((tchar == null) ? null : tchar.chars));
        }
        return GetModuleHandleA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final boolean GetMonitorInfo(final int n, final MONITORINFO monitorinfo) {
        if (OS.IsUnicode) {
            return GetMonitorInfoW(n, monitorinfo);
        }
        return GetMonitorInfoA(n, monitorinfo);
    }
    
    public static final int GetObject(final int n, final int n2, final BITMAP bitmap) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, bitmap);
        }
        return GetObjectA(n, n2, bitmap);
    }
    
    public static final int GetObject(final int n, final int n2, final DIBSECTION dibsection) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, dibsection);
        }
        return GetObjectA(n, n2, dibsection);
    }
    
    public static final int GetObject(final int n, final int n2, final EXTLOGPEN extlogpen) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, extlogpen);
        }
        return GetObjectA(n, n2, extlogpen);
    }
    
    public static final int GetObject(final int n, final int n2, final LOGBRUSH logbrush) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, logbrush);
        }
        return GetObjectA(n, n2, logbrush);
    }
    
    public static final int GetObject(final int n, final int n2, final LOGFONT logfont) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, (LOGFONTW)logfont);
        }
        return GetObjectA(n, n2, (LOGFONTA)logfont);
    }
    
    public static final int GetObject(final int n, final int n2, final LOGPEN logpen) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, logpen);
        }
        return GetObjectA(n, n2, logpen);
    }
    
    public static final int GetObject(final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return GetObjectW(n, n2, n3);
        }
        return GetObjectA(n, n2, n3);
    }
    
    public static final boolean GetOpenFileName(final OPENFILENAME openfilename) {
        if (OS.IsUnicode) {
            return GetOpenFileNameW(openfilename);
        }
        return GetOpenFileNameA(openfilename);
    }
    
    public static final int GetOutlineTextMetrics(final int n, final int n2, final OUTLINETEXTMETRIC outlinetextmetric) {
        if (OS.IsUnicode) {
            return GetOutlineTextMetricsW(n, n2, (OUTLINETEXTMETRICW)outlinetextmetric);
        }
        return GetOutlineTextMetricsA(n, n2, (OUTLINETEXTMETRICA)outlinetextmetric);
    }
    
    public static final int GetProfileString(final TCHAR tchar, final TCHAR tchar2, final TCHAR tchar3, final TCHAR tchar4, final int n) {
        if (OS.IsUnicode) {
            return GetProfileStringW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), (char[])((tchar3 == null) ? null : tchar3.chars), (char[])((tchar4 == null) ? null : tchar4.chars), n);
        }
        return GetProfileStringA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), (byte[])((tchar3 == null) ? null : tchar3.bytes), (byte[])((tchar4 == null) ? null : tchar4.bytes), n);
    }
    
    public static int GetProp(final int n, final int n2) {
        if (OS.IsUnicode) {
            return GetPropW(n, n2);
        }
        return GetPropA(n, n2);
    }
    
    public static final boolean GetSaveFileName(final OPENFILENAME openfilename) {
        if (OS.IsUnicode) {
            return GetSaveFileNameW(openfilename);
        }
        return GetSaveFileNameA(openfilename);
    }
    
    public static final void GetStartupInfo(final STARTUPINFO startupinfo) {
        if (OS.IsUnicode) {
            GetStartupInfoW(startupinfo);
        }
        else {
            GetStartupInfoA(startupinfo);
        }
    }
    
    public static final boolean GetTextExtentPoint32(final int n, final TCHAR tchar, final int n2, final SIZE size) {
        if (OS.IsUnicode) {
            return GetTextExtentPoint32W(n, (char[])((tchar == null) ? null : tchar.chars), n2, size);
        }
        return GetTextExtentPoint32A(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, size);
    }
    
    public static final boolean GetTextMetrics(final int n, final TEXTMETRIC textmetric) {
        if (OS.IsUnicode) {
            return GetTextMetricsW(n, (TEXTMETRICW)textmetric);
        }
        return GetTextMetricsA(n, (TEXTMETRICA)textmetric);
    }
    
    public static final int GetTimeFormat(final int n, final int n2, final SYSTEMTIME systemtime, final TCHAR tchar, final TCHAR tchar2, final int n3) {
        if (OS.IsUnicode) {
            return GetTimeFormatW(n, n2, systemtime, (char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n3);
        }
        return GetTimeFormatA(n, n2, systemtime, (byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n3);
    }
    
    public static final boolean GetVersionEx(final OSVERSIONINFO osversioninfo) {
        if (OS.IsUnicode) {
            return GetVersionExW((OSVERSIONINFOW)osversioninfo);
        }
        return GetVersionExA((OSVERSIONINFOA)osversioninfo);
    }
    
    public static final boolean GetVersionEx(final OSVERSIONINFOEX osversioninfoex) {
        if (OS.IsUnicode) {
            return GetVersionExW((OSVERSIONINFOEXW)osversioninfoex);
        }
        return GetVersionExA((OSVERSIONINFOEXA)osversioninfoex);
    }
    
    public static final int GetWindowLong(final int n, final int n2) {
        if (OS.IsUnicode) {
            return GetWindowLongW(n, n2);
        }
        return GetWindowLongA(n, n2);
    }
    
    public static final int GetWindowLongPtr(final int n, final int n2) {
        if (OS.IsUnicode) {
            return GetWindowLongPtrW(n, n2);
        }
        return GetWindowLongPtrA(n, n2);
    }
    
    public static final int GetWindowText(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            return GetWindowTextW(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        return GetWindowTextA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
    }
    
    public static final int GetWindowTextLength(final int n) {
        if (OS.IsUnicode) {
            return GetWindowTextLengthW(n);
        }
        return GetWindowTextLengthA(n);
    }
    
    public static final int GlobalAddAtom(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return GlobalAddAtomW((char[])((tchar == null) ? null : tchar.chars));
        }
        return GlobalAddAtomA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final boolean ImmGetCompositionFont(final int n, final LOGFONT logfont) {
        if (OS.IsUnicode) {
            return ImmGetCompositionFontW(n, (LOGFONTW)logfont);
        }
        return ImmGetCompositionFontA(n, (LOGFONTA)logfont);
    }
    
    public static final boolean ImmSetCompositionFont(final int n, final LOGFONT logfont) {
        if (OS.IsUnicode) {
            return ImmSetCompositionFontW(n, (LOGFONTW)logfont);
        }
        return ImmSetCompositionFontA(n, (LOGFONTA)logfont);
    }
    
    public static final int ImmGetCompositionString(final int n, final int n2, final byte[] array, final int n3) {
        if (OS.IsUnicode) {
            return ImmGetCompositionStringW(n, n2, array, n3);
        }
        return ImmGetCompositionStringA(n, n2, array, n3);
    }
    
    public static final int ImmGetCompositionString(final int n, final int n2, final int[] array, final int n3) {
        if (OS.IsUnicode) {
            return ImmGetCompositionStringW(n, n2, array, n3);
        }
        return ImmGetCompositionStringA(n, n2, array, n3);
    }
    
    public static final int ImmGetCompositionString(final int n, final int n2, final TCHAR tchar, final int n3) {
        if (OS.IsUnicode) {
            return ImmGetCompositionStringW(n, n2, (char[])((tchar == null) ? null : tchar.chars), n3);
        }
        return ImmGetCompositionStringA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), n3);
    }
    
    public static final boolean InternetGetCookie(final TCHAR tchar, final TCHAR tchar2, final TCHAR tchar3, final int[] array) {
        if (OS.IsUnicode) {
            return InternetGetCookieW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), (char[])((tchar3 == null) ? null : tchar3.chars), array);
        }
        return InternetGetCookieA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), (byte[])((tchar3 == null) ? null : tchar3.bytes), array);
    }
    
    public static final boolean InternetSetCookie(final TCHAR tchar, final TCHAR tchar2, final TCHAR tchar3) {
        if (OS.IsUnicode) {
            return InternetSetCookieW((char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), (char[])((tchar3 == null) ? null : tchar3.chars));
        }
        return InternetSetCookieA((byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), (byte[])((tchar3 == null) ? null : tchar3.bytes));
    }
    
    public static final boolean InsertMenu(final int n, final int n2, final int n3, final int n4, final TCHAR tchar) {
        if (OS.IsUnicode) {
            return InsertMenuW(n, n2, n3, n4, (char[])((tchar == null) ? null : tchar.chars));
        }
        return InsertMenuA(n, n2, n3, n4, (byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final boolean InsertMenuItem(final int n, final int n2, final boolean b, final MENUITEMINFO menuiteminfo) {
        if (OS.IsUnicode) {
            return InsertMenuItemW(n, n2, b, menuiteminfo);
        }
        return InsertMenuItemA(n, n2, b, menuiteminfo);
    }
    
    public static final int LoadBitmap(final int n, final int n2) {
        if (OS.IsUnicode) {
            return LoadBitmapW(n, n2);
        }
        return LoadBitmapA(n, n2);
    }
    
    public static final int LoadCursor(final int n, final int n2) {
        if (OS.IsUnicode) {
            return LoadCursorW(n, n2);
        }
        return LoadCursorA(n, n2);
    }
    
    public static final int LoadIcon(final int n, final int n2) {
        if (OS.IsUnicode) {
            return LoadIconW(n, n2);
        }
        return LoadIconA(n, n2);
    }
    
    public static final int LoadImage(final int n, final TCHAR tchar, final int n2, final int n3, final int n4, final int n5) {
        if (OS.IsUnicode) {
            return LoadImageW(n, (char[])((tchar == null) ? null : tchar.chars), n2, n3, n4, n5);
        }
        return LoadImageA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, n3, n4, n5);
    }
    
    public static final int LoadImage(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (OS.IsUnicode) {
            return LoadImageW(n, n2, n3, n4, n5, n6);
        }
        return LoadImageA(n, n2, n3, n4, n5, n6);
    }
    
    public static final int LoadLibrary(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return LoadLibraryW((char[])((tchar == null) ? null : tchar.chars));
        }
        return LoadLibraryA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int LoadString(final int n, final int n2, final TCHAR tchar, final int n3) {
        if (OS.IsUnicode) {
            return LoadStringW(n, n2, (char[])((tchar == null) ? null : tchar.chars), n3);
        }
        return LoadStringA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), n3);
    }
    
    public static final int MapVirtualKey(final int n, final int n2) {
        if (OS.IsUnicode) {
            return MapVirtualKeyW(n, n2);
        }
        return MapVirtualKeyA(n, n2);
    }
    
    public static final int MessageBox(final int n, final TCHAR tchar, final TCHAR tchar2, final int n2) {
        if (OS.IsUnicode) {
            return MessageBoxW(n, (char[])((tchar == null) ? null : tchar.chars), (char[])((tchar2 == null) ? null : tchar2.chars), n2);
        }
        return MessageBoxA(n, (byte[])((tchar == null) ? null : tchar.bytes), (byte[])((tchar2 == null) ? null : tchar2.bytes), n2);
    }
    
    public static final void MoveMemory(final int n, final TCHAR tchar, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory(n, (char[])((tchar == null) ? null : tchar.chars), n2);
        }
        else {
            MoveMemory(n, (byte[])((tchar == null) ? null : tchar.bytes), n2);
        }
    }
    
    public static final void MoveMemory(final TCHAR tchar, final int n, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory((char[])((tchar == null) ? null : tchar.chars), n, n2);
        }
        else {
            MoveMemory((byte[])((tchar == null) ? null : tchar.bytes), n, n2);
        }
    }
    
    public static final void MoveMemory(final int n, final DEVMODE devmode, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory(n, (DEVMODEW)devmode, n2);
        }
        else {
            MoveMemory(n, (DEVMODEA)devmode, n2);
        }
    }
    
    public static final void MoveMemory(final DEVMODE devmode, final int n, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory((DEVMODEW)devmode, n, n2);
        }
        else {
            MoveMemory((DEVMODEA)devmode, n, n2);
        }
    }
    
    public static final void MoveMemory(final int n, final LOGFONT logfont, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory(n, (LOGFONTW)logfont, n2);
        }
        else {
            MoveMemory(n, (LOGFONTA)logfont, n2);
        }
    }
    
    public static final void MoveMemory(final LOGFONT logfont, final int n, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory((LOGFONTW)logfont, n, n2);
        }
        else {
            MoveMemory((LOGFONTA)logfont, n, n2);
        }
    }
    
    public static final void MoveMemory(final int n, final NMTTDISPINFO nmttdispinfo, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory(n, (NMTTDISPINFOW)nmttdispinfo, n2);
        }
        else {
            MoveMemory(n, (NMTTDISPINFOA)nmttdispinfo, n2);
        }
    }
    
    public static final void MoveMemory(final NMTTDISPINFO nmttdispinfo, final int n, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory((NMTTDISPINFOW)nmttdispinfo, n, n2);
        }
        else {
            MoveMemory((NMTTDISPINFOA)nmttdispinfo, n, n2);
        }
    }
    
    public static final void MoveMemory(final TEXTMETRIC textmetric, final int n, final int n2) {
        if (OS.IsUnicode) {
            MoveMemory((TEXTMETRICW)textmetric, n, n2);
        }
        else {
            MoveMemory((TEXTMETRICA)textmetric, n, n2);
        }
    }
    
    public static final boolean PeekMessage(final MSG msg, final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return PeekMessageW(msg, n, n2, n3, n4);
        }
        return PeekMessageA(msg, n, n2, n3, n4);
    }
    
    public static final boolean PostMessage(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return PostMessageW(n, n2, n3, n4);
        }
        return PostMessageA(n, n2, n3, n4);
    }
    
    public static final boolean PostThreadMessage(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return PostThreadMessageW(n, n2, n3, n4);
        }
        return PostThreadMessageA(n, n2, n3, n4);
    }
    
    public static final boolean PrintDlg(final PRINTDLG printdlg) {
        if (OS.IsUnicode) {
            return PrintDlgW(printdlg);
        }
        return PrintDlgA(printdlg);
    }
    
    public static final int RegEnumKeyEx(final int n, final int n2, final TCHAR tchar, final int[] array, final int[] array2, final TCHAR tchar2, final int[] array3, final FILETIME filetime) {
        if (OS.IsUnicode) {
            return RegEnumKeyExW(n, n2, (char[])((tchar == null) ? null : tchar.chars), array, array2, (char[])((tchar2 == null) ? null : tchar2.chars), array3, filetime);
        }
        return RegEnumKeyExA(n, n2, (byte[])((tchar == null) ? null : tchar.bytes), array, array2, (byte[])((tchar2 == null) ? null : tchar2.bytes), array3, filetime);
    }
    
    public static final int RegisterClass(final WNDCLASS wndclass) {
        if (OS.IsUnicode) {
            return RegisterClassW(wndclass);
        }
        return RegisterClassA(wndclass);
    }
    
    public static final int RegisterClipboardFormat(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return RegisterClipboardFormatW((char[])((tchar == null) ? null : tchar.chars));
        }
        return RegisterClipboardFormatA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int RegisterWindowMessage(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return RegisterWindowMessageW((char[])((tchar == null) ? null : tchar.chars));
        }
        return RegisterWindowMessageA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int RegOpenKeyEx(final int n, final TCHAR tchar, final int n2, final int n3, final int[] array) {
        if (OS.IsUnicode) {
            return RegOpenKeyExW(n, (char[])((tchar == null) ? null : tchar.chars), n2, n3, array);
        }
        return RegOpenKeyExA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, n3, array);
    }
    
    public static final int RegQueryInfoKey(final int n, final int n2, final int[] array, final int n3, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int n4) {
        if (OS.IsUnicode) {
            return RegQueryInfoKeyW(n, n2, array, n3, array2, array3, array4, array5, array6, array7, array8, n4);
        }
        return RegQueryInfoKeyA(n, n2, array, n3, array2, array3, array4, array5, array6, array7, array8, n4);
    }
    
    public static final int RegQueryValueEx(final int n, final TCHAR tchar, final int n2, final int[] array, final TCHAR tchar2, final int[] array2) {
        if (OS.IsUnicode) {
            return RegQueryValueExW(n, (char[])((tchar == null) ? null : tchar.chars), n2, array, (char[])((tchar2 == null) ? null : tchar2.chars), array2);
        }
        return RegQueryValueExA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, array, (byte[])((tchar2 == null) ? null : tchar2.bytes), array2);
    }
    
    public static final int RegQueryValueEx(final int n, final TCHAR tchar, final int n2, final int[] array, final int[] array2, final int[] array3) {
        if (OS.IsUnicode) {
            return RegQueryValueExW(n, (char[])((tchar == null) ? null : tchar.chars), n2, array, array2, array3);
        }
        return RegQueryValueExA(n, (byte[])((tchar == null) ? null : tchar.bytes), n2, array, array2, array3);
    }
    
    public static final int RemoveProp(final int n, final int n2) {
        if (OS.IsUnicode) {
            return RemovePropW(n, n2);
        }
        return RemovePropA(n, n2);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TCHAR tchar) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, (char[])((tchar == null) ? null : tchar.chars));
        }
        return SendMessageA(n, n2, n3, (byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int SendMessage(final int n, final int n2, final int[] array, final int[] array2) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, array, array2);
        }
        return SendMessageA(n, n2, array, array2);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final SIZE size) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, size);
        }
        return SendMessageA(n, n2, n3, size);
    }
    
    public static final int SendMessage(final int n, final int n2, final int[] array, final int n3) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, array, n3);
        }
        return SendMessageA(n, n2, array, n3);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final int[] array) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, array);
        }
        return SendMessageA(n, n2, n3, array);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final char[] array) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, array);
        }
        return SendMessageA(n, n2, n3, array);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final short[] array) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, array);
        }
        return SendMessageA(n, n2, n3, array);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, n4);
        }
        return SendMessageA(n, n2, n3, n4);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final LITEM litem) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, litem);
        }
        return SendMessageA(n, n2, n3, litem);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final LVCOLUMN lvcolumn) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, lvcolumn);
        }
        return SendMessageA(n, n2, n3, lvcolumn);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final LVHITTESTINFO lvhittestinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, lvhittestinfo);
        }
        return SendMessageA(n, n2, n3, lvhittestinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final LVITEM lvitem) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, lvitem);
        }
        return SendMessageA(n, n2, n3, lvitem);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final LVINSERTMARK lvinsertmark) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, lvinsertmark);
        }
        return SendMessageA(n, n2, n3, lvinsertmark);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final MARGINS margins) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, margins);
        }
        return SendMessageA(n, n2, n3, margins);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final POINT point) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, point);
        }
        return SendMessageA(n, n2, n3, point);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final MCHITTESTINFO mchittestinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, mchittestinfo);
        }
        return SendMessageA(n, n2, n3, mchittestinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final REBARBANDINFO rebarbandinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, rebarbandinfo);
        }
        return SendMessageA(n, n2, n3, rebarbandinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final RECT rect) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, rect);
        }
        return SendMessageA(n, n2, n3, rect);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final SYSTEMTIME systemtime) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, systemtime);
        }
        return SendMessageA(n, n2, n3, systemtime);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final SHDRAGIMAGE shdragimage) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, shdragimage);
        }
        return SendMessageA(n, n2, n3, shdragimage);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TBBUTTON tbbutton) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tbbutton);
        }
        return SendMessageA(n, n2, n3, tbbutton);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TBBUTTONINFO tbbuttoninfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tbbuttoninfo);
        }
        return SendMessageA(n, n2, n3, tbbuttoninfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TCITEM tcitem) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tcitem);
        }
        return SendMessageA(n, n2, n3, tcitem);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TCHITTESTINFO tchittestinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tchittestinfo);
        }
        return SendMessageA(n, n2, n3, tchittestinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TOOLINFO toolinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, toolinfo);
        }
        return SendMessageA(n, n2, n3, toolinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TVHITTESTINFO tvhittestinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tvhittestinfo);
        }
        return SendMessageA(n, n2, n3, tvhittestinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TVINSERTSTRUCT tvinsertstruct) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tvinsertstruct);
        }
        return SendMessageA(n, n2, n3, tvinsertstruct);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TVITEM tvitem) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tvitem);
        }
        return SendMessageA(n, n2, n3, tvitem);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final TVSORTCB tvsortcb) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, tvsortcb);
        }
        return SendMessageA(n, n2, n3, tvsortcb);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final UDACCEL udaccel) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, udaccel);
        }
        return SendMessageA(n, n2, n3, udaccel);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final HDHITTESTINFO hdhittestinfo) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, hdhittestinfo);
        }
        return SendMessageA(n, n2, n3, hdhittestinfo);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final HDITEM hditem) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, hditem);
        }
        return SendMessageA(n, n2, n3, hditem);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final HDLAYOUT hdlayout) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, hdlayout);
        }
        return SendMessageA(n, n2, n3, hdlayout);
    }
    
    public static final int SendMessage(final int n, final int n2, final int n3, final BUTTON_IMAGELIST button_IMAGELIST) {
        if (OS.IsUnicode) {
            return SendMessageW(n, n2, n3, button_IMAGELIST);
        }
        return SendMessageA(n, n2, n3, button_IMAGELIST);
    }
    
    public static final boolean SetMenuItemInfo(final int n, final int n2, final boolean b, final MENUITEMINFO menuiteminfo) {
        if (OS.IsUnicode) {
            return SetMenuItemInfoW(n, n2, b, menuiteminfo);
        }
        return SetMenuItemInfoA(n, n2, b, menuiteminfo);
    }
    
    public static final boolean SetDllDirectory(final TCHAR tchar) {
        if (OS.IsUnicode) {
            return SetDllDirectoryW((char[])((tchar == null) ? null : tchar.chars));
        }
        return SetDllDirectoryA((byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static boolean SetProp(final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return SetPropW(n, n2, n3);
        }
        return SetPropA(n, n2, n3);
    }
    
    public static final int SetWindowLong(final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return SetWindowLongW(n, n2, n3);
        }
        return SetWindowLongA(n, n2, n3);
    }
    
    public static final int SetWindowLongPtr(final int n, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return SetWindowLongPtrW(n, n2, n3);
        }
        return SetWindowLongPtrA(n, n2, n3);
    }
    
    public static final int SetWindowsHookEx(final int n, final int n2, final int n3, final int n4) {
        if (OS.IsUnicode) {
            return SetWindowsHookExW(n, n2, n3, n4);
        }
        return SetWindowsHookExA(n, n2, n3, n4);
    }
    
    public static final boolean SetWindowText(final int n, final TCHAR tchar) {
        if (OS.IsUnicode) {
            return SetWindowTextW(n, (char[])((tchar == null) ? null : tchar.chars));
        }
        return SetWindowTextA(n, (byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int SHBrowseForFolder(final BROWSEINFO browseinfo) {
        if (OS.IsUnicode) {
            return SHBrowseForFolderW(browseinfo);
        }
        return SHBrowseForFolderA(browseinfo);
    }
    
    public static final boolean ShellExecuteEx(final SHELLEXECUTEINFO shellexecuteinfo) {
        if (OS.IsUnicode) {
            return ShellExecuteExW(shellexecuteinfo);
        }
        return ShellExecuteExA(shellexecuteinfo);
    }
    
    public static int SHGetFileInfo(final TCHAR tchar, final int n, final SHFILEINFO shfileinfo, final int n2, final int n3) {
        if (OS.IsUnicode) {
            return SHGetFileInfoW((char[])((tchar == null) ? null : tchar.chars), n, (SHFILEINFOW)shfileinfo, n2, n3);
        }
        return SHGetFileInfoA((byte[])((tchar == null) ? null : tchar.bytes), n, (SHFILEINFOA)shfileinfo, n2, n3);
    }
    
    public static final boolean Shell_NotifyIcon(final int n, final NOTIFYICONDATA notifyicondata) {
        if (OS.IsUnicode) {
            return Shell_NotifyIconW(n, (NOTIFYICONDATAW)notifyicondata);
        }
        return Shell_NotifyIconA(n, (NOTIFYICONDATAA)notifyicondata);
    }
    
    public static final int SHGetFolderPath(final int n, final int n2, final int n3, final int n4, final TCHAR tchar) {
        if (OS.IsUnicode) {
            return SHGetFolderPathW(n, n2, n3, n4, (char[])((tchar == null) ? null : tchar.chars));
        }
        return SHGetFolderPathA(n, n2, n3, n4, (byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final boolean SHGetPathFromIDList(final int n, final TCHAR tchar) {
        if (OS.IsUnicode) {
            return SHGetPathFromIDListW(n, (char[])((tchar == null) ? null : tchar.chars));
        }
        return SHGetPathFromIDListA(n, (byte[])((tchar == null) ? null : tchar.bytes));
    }
    
    public static final int StartDoc(final int n, final DOCINFO docinfo) {
        if (OS.IsUnicode) {
            return StartDocW(n, docinfo);
        }
        return StartDocA(n, docinfo);
    }
    
    public static final boolean SystemParametersInfo(final int n, final int n2, final RECT rect, final int n3) {
        if (OS.IsUnicode) {
            return SystemParametersInfoW(n, n2, rect, n3);
        }
        return SystemParametersInfoA(n, n2, rect, n3);
    }
    
    public static final boolean SystemParametersInfo(final int n, final int n2, final HIGHCONTRAST highcontrast, final int n3) {
        if (OS.IsUnicode) {
            return SystemParametersInfoW(n, n2, highcontrast, n3);
        }
        return SystemParametersInfoA(n, n2, highcontrast, n3);
    }
    
    public static final boolean SystemParametersInfo(final int n, final int n2, final NONCLIENTMETRICS nonclientmetrics, final int n3) {
        if (OS.IsUnicode) {
            return SystemParametersInfoW(n, n2, (NONCLIENTMETRICSW)nonclientmetrics, n3);
        }
        return SystemParametersInfoA(n, n2, (NONCLIENTMETRICSA)nonclientmetrics, n3);
    }
    
    public static final boolean SystemParametersInfo(final int n, final int n2, final int[] array, final int n3) {
        if (OS.IsUnicode) {
            return SystemParametersInfoW(n, n2, array, n3);
        }
        return SystemParametersInfoA(n, n2, array, n3);
    }
    
    public static final int TranslateAccelerator(final int n, final int n2, final MSG msg) {
        if (OS.IsUnicode) {
            return TranslateAcceleratorW(n, n2, msg);
        }
        return TranslateAcceleratorA(n, n2, msg);
    }
    
    public static final boolean UnregisterClass(final TCHAR tchar, final int n) {
        if (OS.IsUnicode) {
            return UnregisterClassW((char[])((tchar == null) ? null : tchar.chars), n);
        }
        return UnregisterClassA((byte[])((tchar == null) ? null : tchar.bytes), n);
    }
    
    public static final short VkKeyScan(final short n) {
        if (OS.IsUnicode) {
            return VkKeyScanW(n);
        }
        return VkKeyScanA(n);
    }
    
    public static final native int AbortDoc(final int p0);
    
    public static final native boolean ActivateActCtx(final int p0, final int[] p1);
    
    public static final native int ActivateKeyboardLayout(final int p0, final int p1);
    
    public static final native int AddFontResourceExW(final char[] p0, final int p1, final int p2);
    
    public static final native int AddFontResourceExA(final byte[] p0, final int p1, final int p2);
    
    public static final native boolean AdjustWindowRectEx(final RECT p0, final int p1, final boolean p2, final int p3);
    
    public static final native boolean AllowSetForegroundWindow(final int p0);
    
    public static final native boolean AlphaBlend(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final BLENDFUNCTION p10);
    
    public static final native boolean AnimateWindow(final int p0, final int p1, final int p2);
    
    public static final native boolean Arc(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native int AssocQueryStringA(final int p0, final int p1, final byte[] p2, final byte[] p3, final byte[] p4, final int[] p5);
    
    public static final native int AssocQueryStringW(final int p0, final int p1, final char[] p2, final char[] p3, final char[] p4, final int[] p5);
    
    public static final native boolean AttachThreadInput(final int p0, final int p1, final boolean p2);
    
    public static final native int BeginBufferedPaint(final int p0, final RECT p1, final int p2, final BP_PAINTPARAMS p3, final int[] p4);
    
    public static final native int BeginDeferWindowPos(final int p0);
    
    public static final native int BeginPaint(final int p0, final PAINTSTRUCT p1);
    
    public static final native boolean BeginPath(final int p0);
    
    public static final native boolean BitBlt(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native boolean BringWindowToTop(final int p0);
    
    public static final native int BufferedPaintInit();
    
    public static final native int BufferedPaintSetAlpha(final int p0, final RECT p1, final byte p2);
    
    public static final native int BufferedPaintUnInit();
    
    public static final native int Call(final int p0);
    
    public static final native int Call(final int p0, final DLLVERSIONINFO p1);
    
    public static final native int CallNextHookEx(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int CallWindowProcW(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int CallWindowProcA(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int CertNameToStrW(final int p0, final CERT_NAME_BLOB p1, final int p2, final char[] p3, final int p4);
    
    public static final native int CertNameToStrA(final int p0, final CERT_NAME_BLOB p1, final int p2, final byte[] p3, final int p4);
    
    public static final native int CharLowerW(final int p0);
    
    public static final native int CharLowerA(final int p0);
    
    public static final native int CharUpperW(final int p0);
    
    public static final native int CharUpperA(final int p0);
    
    public static final native boolean CheckMenuItem(final int p0, final int p1, final int p2);
    
    public static final native boolean ChooseColorW(final CHOOSECOLOR p0);
    
    public static final native boolean ChooseColorA(final CHOOSECOLOR p0);
    
    public static final native boolean ChooseFontW(final CHOOSEFONT p0);
    
    public static final native boolean ChooseFontA(final CHOOSEFONT p0);
    
    public static final native boolean ClientToScreen(final int p0, final POINT p1);
    
    public static final native boolean CloseClipboard();
    
    public static final native int CloseEnhMetaFile(final int p0);
    
    public static final native int CloseGestureInfoHandle(final int p0);
    
    public static final native boolean CloseHandle(final int p0);
    
    public static final native int CloseThemeData(final int p0);
    
    public static final native boolean CloseTouchInputHandle(final int p0);
    
    public static final native int CoCreateInstance(final byte[] p0, final int p1, final int p2, final byte[] p3, final int[] p4);
    
    public static final native int CoInternetIsFeatureEnabled(final int p0, final int p1);
    
    public static final native int CoInternetSetFeatureEnabled(final int p0, final int p1, final boolean p2);
    
    public static final native int CombineRgn(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean CommandBar_AddAdornments(final int p0, final int p1, final int p2);
    
    public static final native int CommandBar_Create(final int p0, final int p1, final int p2);
    
    public static final native void CommandBar_Destroy(final int p0);
    
    public static final native boolean CommandBar_DrawMenuBar(final int p0, final int p1);
    
    public static final native int CommandBar_Height(final int p0);
    
    public static final native boolean CommandBar_InsertMenubarEx(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean CommandBar_Show(final int p0, final boolean p1);
    
    public static final native int CommDlgExtendedError();
    
    public static final native int CopyImage(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int CoTaskMemAlloc(final int p0);
    
    public static final native void CoTaskMemFree(final int p0);
    
    public static final native int CreateAcceleratorTableW(final byte[] p0, final int p1);
    
    public static final native int CreateAcceleratorTableA(final byte[] p0, final int p1);
    
    public static final native int CreateActCtxW(final ACTCTX p0);
    
    public static final native int CreateActCtxA(final ACTCTX p0);
    
    public static final native int CreateBitmap(final int p0, final int p1, final int p2, final int p3, final byte[] p4);
    
    public static final native boolean CreateCaret(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int CreateCompatibleBitmap(final int p0, final int p1, final int p2);
    
    public static final native int CreateCompatibleDC(final int p0);
    
    public static final native int CreateCursor(final int p0, final int p1, final int p2, final int p3, final int p4, final byte[] p5, final byte[] p6);
    
    public static final native int CreateDCW(final char[] p0, final char[] p1, final int p2, final int p3);
    
    public static final native int CreateDCA(final byte[] p0, final byte[] p1, final int p2, final int p3);
    
    public static final native int CreateDIBSection(final int p0, final byte[] p1, final int p2, final int[] p3, final int p4, final int p5);
    
    public static final native int CreateDIBSection(final int p0, final int p1, final int p2, final int[] p3, final int p4, final int p5);
    
    public static final native int CreateEnhMetaFileW(final int p0, final char[] p1, final RECT p2, final char[] p3);
    
    public static final native int CreateEnhMetaFileA(final int p0, final byte[] p1, final RECT p2, final byte[] p3);
    
    public static final native int CreateFontIndirectW(final int p0);
    
    public static final native int CreateFontIndirectA(final int p0);
    
    public static final native int CreateFontIndirectW(final LOGFONTW p0);
    
    public static final native int CreateFontIndirectA(final LOGFONTA p0);
    
    public static final native int CreateIconIndirect(final ICONINFO p0);
    
    public static final native int CreateMenu();
    
    public static final native int CreatePalette(final byte[] p0);
    
    public static final native int CreatePatternBrush(final int p0);
    
    public static final native int CreatePen(final int p0, final int p1, final int p2);
    
    public static final native int CreatePolygonRgn(final int[] p0, final int p1, final int p2);
    
    public static final native int CreatePopupMenu();
    
    public static final native boolean CreateProcessW(final int p0, final int p1, final int p2, final int p3, final boolean p4, final int p5, final int p6, final int p7, final STARTUPINFO p8, final PROCESS_INFORMATION p9);
    
    public static final native boolean CreateProcessA(final int p0, final int p1, final int p2, final int p3, final boolean p4, final int p5, final int p6, final int p7, final STARTUPINFO p8, final PROCESS_INFORMATION p9);
    
    public static final native int CreateRectRgn(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int CreateSolidBrush(final int p0);
    
    public static final native int CreateStreamOnHGlobal(final int p0, final boolean p1, final int[] p2);
    
    public static final native int CreateWindowExW(final int p0, final char[] p1, final char[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final CREATESTRUCT p11);
    
    public static final native int CreateWindowExA(final int p0, final byte[] p1, final byte[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final CREATESTRUCT p11);
    
    public static final native int DeferWindowPos(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native int DefMDIChildProcW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int DefMDIChildProcA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int DefFrameProcW(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int DefFrameProcA(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int DefWindowProcW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int DefWindowProcA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean DeleteDC(final int p0);
    
    public static final native boolean DeleteEnhMetaFile(final int p0);
    
    public static final native boolean DeleteMenu(final int p0, final int p1, final int p2);
    
    public static final native boolean DeleteObject(final int p0);
    
    public static final native boolean DestroyAcceleratorTable(final int p0);
    
    public static final native boolean DestroyCaret();
    
    public static final native boolean DestroyCursor(final int p0);
    
    public static final native boolean DestroyIcon(final int p0);
    
    public static final native boolean DestroyMenu(final int p0);
    
    public static final native boolean DestroyWindow(final int p0);
    
    public static final native int DispatchMessageW(final MSG p0);
    
    public static final native int DispatchMessageA(final MSG p0);
    
    public static final native boolean DPtoLP(final int p0, final POINT p1, final int p2);
    
    public static final native boolean DragDetect(final int p0, final POINT p1);
    
    public static final native void DragFinish(final int p0);
    
    public static final native int DragQueryFileA(final int p0, final int p1, final byte[] p2, final int p3);
    
    public static final native int DragQueryFileW(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native boolean DrawAnimatedRects(final int p0, final int p1, final RECT p2, final RECT p3);
    
    public static final native boolean DrawEdge(final int p0, final RECT p1, final int p2, final int p3);
    
    public static final native boolean DrawFocusRect(final int p0, final RECT p1);
    
    public static final native boolean DrawFrameControl(final int p0, final RECT p1, final int p2, final int p3);
    
    public static final native boolean DrawIconEx(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native boolean DrawMenuBar(final int p0);
    
    public static final native boolean DrawStateW(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9);
    
    public static final native boolean DrawStateA(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9);
    
    public static final native int DrawTextW(final int p0, final char[] p1, final int p2, final RECT p3, final int p4);
    
    public static final native int DrawTextA(final int p0, final byte[] p1, final int p2, final RECT p3, final int p4);
    
    public static final native int DrawThemeBackground(final int p0, final int p1, final int p2, final int p3, final RECT p4, final RECT p5);
    
    public static final native int DrawThemeEdge(final int p0, final int p1, final int p2, final int p3, final RECT p4, final int p5, final int p6, final RECT p7);
    
    public static final native int DrawThemeIcon(final int p0, final int p1, final int p2, final int p3, final RECT p4, final int p5, final int p6);
    
    public static final native int DrawThemeParentBackground(final int p0, final int p1, final RECT p2);
    
    public static final native int DrawThemeText(final int p0, final int p1, final int p2, final int p3, final char[] p4, final int p5, final int p6, final int p7, final RECT p8);
    
    public static final native int DwmEnableBlurBehindWindow(final int p0, final DWM_BLURBEHIND p1);
    
    public static final native int DwmExtendFrameIntoClientArea(final int p0, final MARGINS p1);
    
    public static final native boolean Ellipse(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean EnableMenuItem(final int p0, final int p1, final int p2);
    
    public static final native boolean EnableScrollBar(final int p0, final int p1, final int p2);
    
    public static final native boolean EnableWindow(final int p0, final boolean p1);
    
    public static final native boolean EnumSystemLanguageGroupsW(final int p0, final int p1, final int p2);
    
    public static final native boolean EnumSystemLanguageGroupsA(final int p0, final int p1, final int p2);
    
    public static final native boolean EnumSystemLocalesW(final int p0, final int p1);
    
    public static final native boolean EnumSystemLocalesA(final int p0, final int p1);
    
    public static final native boolean EndDeferWindowPos(final int p0);
    
    public static final native int EndBufferedPaint(final int p0, final boolean p1);
    
    public static final native int EndDoc(final int p0);
    
    public static final native int EndPage(final int p0);
    
    public static final native int EndPaint(final int p0, final PAINTSTRUCT p1);
    
    public static final native boolean EndPath(final int p0);
    
    public static final native boolean EnumDisplayMonitors(final int p0, final RECT p1, final int p2, final int p3);
    
    public static final native boolean EnumEnhMetaFile(final int p0, final int p1, final int p2, final int p3, final RECT p4);
    
    public static final native int EnumFontFamiliesW(final int p0, final char[] p1, final int p2, final int p3);
    
    public static final native int EnumFontFamiliesA(final int p0, final byte[] p1, final int p2, final int p3);
    
    public static final native int EnumFontFamiliesExW(final int p0, final LOGFONTW p1, final int p2, final int p3, final int p4);
    
    public static final native int EnumFontFamiliesExA(final int p0, final LOGFONTA p1, final int p2, final int p3, final int p4);
    
    public static final native boolean EqualRect(final RECT p0, final RECT p1);
    
    public static final native boolean EqualRgn(final int p0, final int p1);
    
    public static final native int ExcludeClipRect(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int ExpandEnvironmentStringsW(final char[] p0, final char[] p1, final int p2);
    
    public static final native int ExpandEnvironmentStringsA(final byte[] p0, final byte[] p1, final int p2);
    
    public static final native int ExtCreatePen(final int p0, final int p1, final LOGBRUSH p2, final int p3, final int[] p4);
    
    public static final native int ExtCreateRegion(final float[] p0, final int p1, final int[] p2);
    
    public static final native boolean ExtTextOutW(final int p0, final int p1, final int p2, final int p3, final RECT p4, final char[] p5, final int p6, final int[] p7);
    
    public static final native boolean ExtTextOutA(final int p0, final int p1, final int p2, final int p3, final RECT p4, final byte[] p5, final int p6, final int[] p7);
    
    public static final native int ExtractIconExW(final char[] p0, final int p1, final int[] p2, final int[] p3, final int p4);
    
    public static final native int ExtractIconExA(final byte[] p0, final int p1, final int[] p2, final int[] p3, final int p4);
    
    public static final native boolean FileTimeToSystemTime(final FILETIME p0, final SYSTEMTIME p1);
    
    public static final native int FillRect(final int p0, final RECT p1, final int p2);
    
    public static final native boolean FillPath(final int p0);
    
    public static final native int FindWindowA(final byte[] p0, final byte[] p1);
    
    public static final native int FindWindowW(final char[] p0, final char[] p1);
    
    public static final native int FormatMessageA(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6);
    
    public static final native int FormatMessageW(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6);
    
    public static final native boolean FreeLibrary(final int p0);
    
    public static final native int GdiSetBatchLimit(final int p0);
    
    public static final native int GET_WHEEL_DELTA_WPARAM(final int p0);
    
    public static final native int GET_X_LPARAM(final int p0);
    
    public static final native int GET_Y_LPARAM(final int p0);
    
    public static final native int GetACP();
    
    public static final native short GetAsyncKeyState(final int p0);
    
    public static final native int GetActiveWindow();
    
    public static final native int GetBkColor(final int p0);
    
    public static final native int GetCapture();
    
    public static final native boolean GetCaretPos(final POINT p0);
    
    public static final native boolean GetCharABCWidthsA(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native boolean GetCharABCWidthsW(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int GetCharacterPlacementW(final int p0, final char[] p1, final int p2, final int p3, final GCP_RESULTS p4, final int p5);
    
    public static final native int GetCharacterPlacementA(final int p0, final byte[] p1, final int p2, final int p3, final GCP_RESULTS p4, final int p5);
    
    public static final native boolean GetCharWidthA(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native boolean GetCharWidthW(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native boolean GetClassInfoW(final int p0, final char[] p1, final WNDCLASS p2);
    
    public static final native boolean GetClassInfoA(final int p0, final byte[] p1, final WNDCLASS p2);
    
    public static final native int GetClassNameW(final int p0, final char[] p1, final int p2);
    
    public static final native int GetClassNameA(final int p0, final byte[] p1, final int p2);
    
    public static final native boolean GetClientRect(final int p0, final RECT p1);
    
    public static final native int GetClipboardData(final int p0);
    
    public static final native int GetClipboardFormatNameA(final int p0, final byte[] p1, final int p2);
    
    public static final native int GetClipboardFormatNameW(final int p0, final char[] p1, final int p2);
    
    public static final native int GetClipBox(final int p0, final RECT p1);
    
    public static final native int GetClipRgn(final int p0, final int p1);
    
    public static final native boolean GetComboBoxInfo(final int p0, final COMBOBOXINFO p1);
    
    public static final native int GetCurrentObject(final int p0, final int p1);
    
    public static final native int GetCurrentProcessId();
    
    public static final native int GetCurrentThreadId();
    
    public static final native int GetCursor();
    
    public static final native boolean GetCursorPos(final POINT p0);
    
    public static final native int GetDateFormatW(final int p0, final int p1, final SYSTEMTIME p2, final char[] p3, final char[] p4, final int p5);
    
    public static final native int GetDateFormatA(final int p0, final int p1, final SYSTEMTIME p2, final byte[] p3, final byte[] p4, final int p5);
    
    public static final native int GetDC(final int p0);
    
    public static final native int GetDCEx(final int p0, final int p1, final int p2);
    
    public static final native int GetDesktopWindow();
    
    public static final native int GetDeviceCaps(final int p0, final int p1);
    
    public static final native int GetDialogBaseUnits();
    
    public static final native int GetDIBColorTable(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int GetDIBits(final int p0, final int p1, final int p2, final int p3, final int p4, final byte[] p5, final int p6);
    
    public static final native int GetDlgItem(final int p0, final int p1);
    
    public static final native int GetDoubleClickTime();
    
    public static final native int GetFocus();
    
    public static final native int GetFontLanguageInfo(final int p0);
    
    public static final native int GetForegroundWindow();
    
    public static final native boolean GetGestureInfo(final int p0, final GESTUREINFO p1);
    
    public static final native int GetGraphicsMode(final int p0);
    
    public static final native int GetGlyphIndicesW(final int p0, final char[] p1, final int p2, final short[] p3, final int p4);
    
    public static final native boolean GetGUIThreadInfo(final int p0, final GUITHREADINFO p1);
    
    public static final native boolean GetIconInfo(final int p0, final ICONINFO p1);
    
    public static final native int GetKeyboardLayoutList(final int p0, final int[] p1);
    
    public static final native int GetKeyboardLayout(final int p0);
    
    public static final native short GetKeyState(final int p0);
    
    public static final native boolean GetKeyboardState(final byte[] p0);
    
    public static final native int GetKeyNameTextW(final int p0, final char[] p1, final int p2);
    
    public static final native int GetKeyNameTextA(final int p0, final byte[] p1, final int p2);
    
    public static final native int GetLastActivePopup(final int p0);
    
    public static final native int GetLastError();
    
    public static final native boolean GetLayeredWindowAttributes(final int p0, final int[] p1, final byte[] p2, final int[] p3);
    
    public static final native int GetLayout(final int p0);
    
    public static final native int GetLibraryHandle();
    
    public static final native int GetLocaleInfoW(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native int GetLocaleInfoA(final int p0, final int p1, final byte[] p2, final int p3);
    
    public static final native int GetMenu(final int p0);
    
    public static final native boolean GetMenuBarInfo(final int p0, final int p1, final int p2, final MENUBARINFO p3);
    
    public static final native int GetMenuDefaultItem(final int p0, final int p1, final int p2);
    
    public static final native boolean GetMenuInfo(final int p0, final MENUINFO p1);
    
    public static final native int GetMenuItemCount(final int p0);
    
    public static final native boolean GetMenuItemInfoW(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean GetMenuItemInfoA(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean GetMenuItemRect(final int p0, final int p1, final int p2, final RECT p3);
    
    public static final native boolean GetMessageW(final MSG p0, final int p1, final int p2, final int p3);
    
    public static final native boolean GetMessageA(final MSG p0, final int p1, final int p2, final int p3);
    
    public static final native int GetMessagePos();
    
    public static final native int GetMessageTime();
    
    public static final native int GetMetaRgn(final int p0, final int p1);
    
    public static final native int GetThemeColor(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int GetThemeTextExtent(final int p0, final int p1, final int p2, final int p3, final char[] p4, final int p5, final int p6, final RECT p7, final RECT p8);
    
    public static final native int GetTextCharset(final int p0);
    
    public static final native int GetTickCount();
    
    public static final native int GetMapMode(final int p0);
    
    public static final native int GetModuleFileNameW(final int p0, final char[] p1, final int p2);
    
    public static final native int GetModuleFileNameA(final int p0, final byte[] p1, final int p2);
    
    public static final native int GetModuleHandleW(final char[] p0);
    
    public static final native int GetModuleHandleA(final byte[] p0);
    
    public static final native boolean GetMonitorInfoW(final int p0, final MONITORINFO p1);
    
    public static final native boolean GetMonitorInfoA(final int p0, final MONITORINFO p1);
    
    public static final native int GetNearestPaletteIndex(final int p0, final int p1);
    
    public static final native int GetObjectA(final int p0, final int p1, final BITMAP p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final BITMAP p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final DIBSECTION p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final DIBSECTION p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final EXTLOGPEN p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final EXTLOGPEN p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final LOGBRUSH p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final LOGBRUSH p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final LOGFONTA p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final LOGFONTW p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final LOGPEN p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final LOGPEN p2);
    
    public static final native int GetObjectA(final int p0, final int p1, final int p2);
    
    public static final native int GetObjectW(final int p0, final int p1, final int p2);
    
    public static final native boolean GetOpenFileNameW(final OPENFILENAME p0);
    
    public static final native boolean GetOpenFileNameA(final OPENFILENAME p0);
    
    public static final native int GetOutlineTextMetricsW(final int p0, final int p1, final OUTLINETEXTMETRICW p2);
    
    public static final native int GetOutlineTextMetricsA(final int p0, final int p1, final OUTLINETEXTMETRICA p2);
    
    public static final native int GetPath(final int p0, final int[] p1, final byte[] p2, final int p3);
    
    public static final native int GetPaletteEntries(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int GetParent(final int p0);
    
    public static final native int GetPixel(final int p0, final int p1, final int p2);
    
    public static final native int GetPolyFillMode(final int p0);
    
    public static final native int GetProcAddress(final int p0, final byte[] p1);
    
    public static final native int GetProcessHeap();
    
    public static final native int GetProcessHeaps(final int p0, final int[] p1);
    
    public static final native int GetProfileStringW(final char[] p0, final char[] p1, final char[] p2, final char[] p3, final int p4);
    
    public static final native int GetProfileStringA(final byte[] p0, final byte[] p1, final byte[] p2, final byte[] p3, final int p4);
    
    public static final native int GetPropW(final int p0, final int p1);
    
    public static final native int GetPropA(final int p0, final int p1);
    
    public static final native int GetRandomRgn(final int p0, final int p1, final int p2);
    
    public static final native int GetRegionData(final int p0, final int p1, final int[] p2);
    
    public static final native int GetRgnBox(final int p0, final RECT p1);
    
    public static final native int GetROP2(final int p0);
    
    public static final native boolean GetSaveFileNameW(final OPENFILENAME p0);
    
    public static final native boolean GetSaveFileNameA(final OPENFILENAME p0);
    
    public static final native boolean GetScrollBarInfo(final int p0, final int p1, final SCROLLBARINFO p2);
    
    public static final native boolean GetScrollInfo(final int p0, final int p1, final SCROLLINFO p2);
    
    public static final native void GetStartupInfoW(final STARTUPINFO p0);
    
    public static final native void GetStartupInfoA(final STARTUPINFO p0);
    
    public static final native int GetStockObject(final int p0);
    
    public static final native int GetSysColor(final int p0);
    
    public static final native int GetSysColorBrush(final int p0);
    
    public static final native short GetSystemDefaultUILanguage();
    
    public static final native int GetSystemMenu(final int p0, final boolean p1);
    
    public static final native int GetSystemMetrics(final int p0);
    
    public static final native int GetSystemPaletteEntries(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int GetTextColor(final int p0);
    
    public static final native boolean GetTextExtentPoint32W(final int p0, final char[] p1, final int p2, final SIZE p3);
    
    public static final native boolean GetTextExtentPoint32A(final int p0, final byte[] p1, final int p2, final SIZE p3);
    
    public static final native boolean GetTextMetricsW(final int p0, final TEXTMETRICW p1);
    
    public static final native boolean GetTextMetricsA(final int p0, final TEXTMETRICA p1);
    
    public static final native int GetThemeInt(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int GetThemeMargins(final int p0, final int p1, final int p2, final int p3, final int p4, final RECT p5, final MARGINS p6);
    
    public static final native int GetThemeBackgroundContentRect(final int p0, final int p1, final int p2, final int p3, final RECT p4, final RECT p5);
    
    public static final native int GetThemeBackgroundExtent(final int p0, final int p1, final int p2, final int p3, final RECT p4, final RECT p5);
    
    public static final native int GetThemePartSize(final int p0, final int p1, final int p2, final int p3, final RECT p4, final int p5, final SIZE p6);
    
    public static final native int GetThemeMetric(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    public static final native int GetThemeRect(final int p0, final int p1, final int p2, final int p3, final RECT p4);
    
    public static final native int GetThemeSysSize(final int p0, final int p1);
    
    public static final native int GetTimeFormatW(final int p0, final int p1, final SYSTEMTIME p2, final char[] p3, final char[] p4, final int p5);
    
    public static final native int GetTimeFormatA(final int p0, final int p1, final SYSTEMTIME p2, final byte[] p3, final byte[] p4, final int p5);
    
    public static final native boolean GetTouchInputInfo(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean GetUpdateRect(final int p0, final RECT p1, final boolean p2);
    
    public static final native int GetUpdateRgn(final int p0, final int p1, final boolean p2);
    
    public static final native boolean GetVersionExW(final OSVERSIONINFOEXW p0);
    
    public static final native boolean GetVersionExA(final OSVERSIONINFOEXA p0);
    
    public static final native boolean GetVersionExW(final OSVERSIONINFOW p0);
    
    public static final native boolean GetVersionExA(final OSVERSIONINFOA p0);
    
    public static final native int GetWindow(final int p0, final int p1);
    
    public static final native int GetWindowLongW(final int p0, final int p1);
    
    public static final native int GetWindowLongA(final int p0, final int p1);
    
    public static final native int GetWindowLongPtrW(final int p0, final int p1);
    
    public static final native int GetWindowLongPtrA(final int p0, final int p1);
    
    public static final native int GetWindowDC(final int p0);
    
    public static final native boolean GetWindowOrgEx(final int p0, final POINT p1);
    
    public static final native boolean GetWindowPlacement(final int p0, final WINDOWPLACEMENT p1);
    
    public static final native boolean GetWindowRect(final int p0, final RECT p1);
    
    public static final native int GetWindowRgn(final int p0, final int p1);
    
    public static final native int GetWindowTextW(final int p0, final char[] p1, final int p2);
    
    public static final native int GetWindowTextA(final int p0, final byte[] p1, final int p2);
    
    public static final native int GetWindowTextLengthW(final int p0);
    
    public static final native int GetWindowTextLengthA(final int p0);
    
    public static final native int GetWindowTheme(final int p0);
    
    public static final native int GetWindowThreadProcessId(final int p0, final int[] p1);
    
    public static final native boolean GetWorldTransform(final int p0, final float[] p1);
    
    public static final native double GID_ROTATE_ANGLE_FROM_ARGUMENT(final long p0);
    
    public static final native int GlobalAddAtomW(final char[] p0);
    
    public static final native int GlobalAddAtomA(final byte[] p0);
    
    public static final native int GlobalAlloc(final int p0, final int p1);
    
    public static final native int GlobalFree(final int p0);
    
    public static final native int GlobalLock(final int p0);
    
    public static final native int GlobalSize(final int p0);
    
    public static final native boolean GlobalUnlock(final int p0);
    
    public static final native boolean GradientFill(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int HIWORD(final int p0);
    
    public static final native int HeapAlloc(final int p0, final int p1, final int p2);
    
    public static final native boolean HeapFree(final int p0, final int p1, final int p2);
    
    public static final native boolean HeapValidate(final int p0, final int p1, final int p2);
    
    public static final native boolean HideCaret(final int p0);
    
    public static final native int HitTestThemeBackground(final int p0, final int p1, final int p2, final int p3, final int p4, final RECT p5, final int p6, final POINT p7, final short[] p8);
    
    public static final native int IIDFromString(final char[] p0, final byte[] p1);
    
    public static final native int ImageList_Add(final int p0, final int p1, final int p2);
    
    public static final native int ImageList_AddMasked(final int p0, final int p1, final int p2);
    
    public static final native boolean ImageList_BeginDrag(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int ImageList_Create(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean ImageList_Destroy(final int p0);
    
    public static final native boolean ImageList_DragEnter(final int p0, final int p1, final int p2);
    
    public static final native boolean ImageList_DragLeave(final int p0);
    
    public static final native boolean ImageList_DragMove(final int p0, final int p1);
    
    public static final native boolean ImageList_DragShowNolock(final boolean p0);
    
    public static final native boolean ImageList_Draw(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native void ImageList_EndDrag();
    
    public static final native int ImageList_GetDragImage(final POINT p0, final POINT p1);
    
    public static final native int ImageList_GetIcon(final int p0, final int p1, final int p2);
    
    public static final native boolean ImageList_GetIconSize(final int p0, final int[] p1, final int[] p2);
    
    public static final native int ImageList_GetImageCount(final int p0);
    
    public static final native boolean ImageList_Remove(final int p0, final int p1);
    
    public static final native boolean ImageList_Replace(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int ImageList_ReplaceIcon(final int p0, final int p1, final int p2);
    
    public static final native boolean ImageList_SetIconSize(final int p0, final int p1, final int p2);
    
    public static final native int ImmAssociateContext(final int p0, final int p1);
    
    public static final native int ImmCreateContext();
    
    public static final native boolean ImmDestroyContext(final int p0);
    
    public static final native boolean ImmDisableTextFrameService(final int p0);
    
    public static final native boolean ImmGetCompositionFontW(final int p0, final LOGFONTW p1);
    
    public static final native boolean ImmGetCompositionFontA(final int p0, final LOGFONTA p1);
    
    public static final native int ImmGetCompositionStringW(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native int ImmGetCompositionStringA(final int p0, final int p1, final byte[] p2, final int p3);
    
    public static final native int ImmGetCompositionStringW(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int ImmGetCompositionStringA(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int ImmGetCompositionStringW(final int p0, final int p1, final byte[] p2, final int p3);
    
    public static final native int ImmGetContext(final int p0);
    
    public static final native boolean ImmGetConversionStatus(final int p0, final int[] p1, final int[] p2);
    
    public static final native int ImmGetDefaultIMEWnd(final int p0);
    
    public static final native boolean ImmGetOpenStatus(final int p0);
    
    public static final native boolean ImmNotifyIME(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean ImmReleaseContext(final int p0, final int p1);
    
    public static final native boolean ImmSetCompositionFontW(final int p0, final LOGFONTW p1);
    
    public static final native boolean ImmSetCompositionFontA(final int p0, final LOGFONTA p1);
    
    public static final native boolean ImmSetCompositionWindow(final int p0, final COMPOSITIONFORM p1);
    
    public static final native boolean ImmSetCandidateWindow(final int p0, final CANDIDATEFORM p1);
    
    public static final native boolean ImmSetConversionStatus(final int p0, final int p1, final int p2);
    
    public static final native boolean ImmSetOpenStatus(final int p0, final boolean p1);
    
    public static final native void InitCommonControls();
    
    public static final native boolean InitCommonControlsEx(final INITCOMMONCONTROLSEX p0);
    
    public static final native boolean InSendMessage();
    
    public static final native boolean InsertMenuW(final int p0, final int p1, final int p2, final int p3, final char[] p4);
    
    public static final native boolean InsertMenuA(final int p0, final int p1, final int p2, final int p3, final byte[] p4);
    
    public static final native boolean InsertMenuItemW(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean InsertMenuItemA(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean InternetGetCookieA(final byte[] p0, final byte[] p1, final byte[] p2, final int[] p3);
    
    public static final native boolean InternetGetCookieW(final char[] p0, final char[] p1, final char[] p2, final int[] p3);
    
    public static final native boolean InternetSetCookieA(final byte[] p0, final byte[] p1, final byte[] p2);
    
    public static final native boolean InternetSetCookieW(final char[] p0, final char[] p1, final char[] p2);
    
    public static final native boolean InternetSetOption(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int IntersectClipRect(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean IntersectRect(final RECT p0, final RECT p1, final RECT p2);
    
    public static final native boolean InvalidateRect(final int p0, final RECT p1, final boolean p2);
    
    public static final native boolean InvalidateRgn(final int p0, final int p1, final boolean p2);
    
    public static final native boolean IsAppThemed();
    
    public static final native boolean IsBadReadPtr(final int p0, final int p1);
    
    public static final native boolean IsBadWritePtr(final int p0, final int p1);
    
    public static final native boolean IsDBCSLeadByte(final byte p0);
    
    public static final native boolean IsHungAppWindow(final int p0);
    
    public static final native boolean IsIconic(final int p0);
    
    public static final native boolean IsPPC();
    
    public static final native boolean IsSP();
    
    public static final native boolean IsTouchWindow(final int p0, final long[] p1);
    
    public static final native boolean IsWindowEnabled(final int p0);
    
    public static final native boolean IsWindowVisible(final int p0);
    
    public static final native boolean IsZoomed(final int p0);
    
    public static final native boolean KillTimer(final int p0, final int p1);
    
    public static final native boolean LineTo(final int p0, final int p1, final int p2);
    
    public static final native int LoadBitmapW(final int p0, final int p1);
    
    public static final native int LoadBitmapA(final int p0, final int p1);
    
    public static final native int LoadCursorW(final int p0, final int p1);
    
    public static final native int LoadCursorA(final int p0, final int p1);
    
    public static final native int LoadIconW(final int p0, final int p1);
    
    public static final native int LoadIconA(final int p0, final int p1);
    
    public static final native int LoadImageW(final int p0, final char[] p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int LoadImageA(final int p0, final byte[] p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int LoadImageW(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int LoadImageA(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int LoadStringW(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native int LoadStringA(final int p0, final int p1, final byte[] p2, final int p3);
    
    public static final native int LoadLibraryW(final char[] p0);
    
    public static final native int LoadLibraryA(final byte[] p0);
    
    public static final native int LocalFree(final int p0);
    
    public static final native boolean LockWindowUpdate(final int p0);
    
    public static final native int LODWORD(final long p0);
    
    public static final native int LOWORD(final int p0);
    
    public static final native boolean LPtoDP(final int p0, final POINT p1, final int p2);
    
    public static final native int MAKEWORD(final int p0, final int p1);
    
    public static final native int MAKEWPARAM(final int p0, final int p1);
    
    public static final native int MAKELPARAM(final int p0, final int p1);
    
    public static final native int MAKELRESULT(final int p0, final int p1);
    
    public static final native int MapVirtualKeyW(final int p0, final int p1);
    
    public static final native int MapVirtualKeyA(final int p0, final int p1);
    
    public static final native int MapWindowPoints(final int p0, final int p1, final POINT p2, final int p3);
    
    public static final native int MapWindowPoints(final int p0, final int p1, final RECT p2, final int p3);
    
    public static final native boolean MCIWndRegisterClass();
    
    public static final native boolean MessageBeep(final int p0);
    
    public static final native int MessageBoxW(final int p0, final char[] p1, final char[] p2, final int p3);
    
    public static final native int MessageBoxA(final int p0, final byte[] p1, final byte[] p2, final int p3);
    
    public static final native boolean ModifyWorldTransform(final int p0, final float[] p1, final int p2);
    
    public static final native int MonitorFromWindow(final int p0, final int p1);
    
    public static final native void MoveMemory(final char[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final ACCEL p1, final int p2);
    
    public static final native void MoveMemory(final byte[] p0, final BITMAPINFOHEADER p1, final int p2);
    
    public static final native void MoveMemory(final int[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final long[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final double[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final float[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final short[] p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final byte[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final char[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final int[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final DEVMODEW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final DEVMODEA p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final DOCHOSTUIINFO p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final GRADIENT_RECT p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final LOGFONTW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final LOGFONTA p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final MEASUREITEMSTRUCT p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final MINMAXINFO p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final MSG p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final UDACCEL p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMTTDISPINFOW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMTTDISPINFOA p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final OPENFILENAME p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final RECT p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final SAFEARRAY p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final TRIVERTEX p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final WINDOWPOS p1, final int p2);
    
    public static final native void MoveMemory(final BITMAPINFOHEADER p0, final byte[] p1, final int p2);
    
    public static final native void MoveMemory(final BITMAPINFOHEADER p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final CERT_CONTEXT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final CERT_INFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final DEVMODEW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final DEVMODEA p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final DOCHOSTUIINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final DRAWITEMSTRUCT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final EXTLOGPEN p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final FLICK_DATA p0, final int[] p1, final int p2);
    
    public static final native void MoveMemory(final FLICK_POINT p0, final int[] p1, final int p2);
    
    public static final native void MoveMemory(final HDITEM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final HELPINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final LOGFONTW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final LOGFONTA p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final MEASUREITEMSTRUCT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final MINMAXINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final OFNOTIFY p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final OPENFILENAME p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final POINT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final POINT p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final NMHDR p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMRGINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMCUSTOMDRAW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMLVCUSTOMDRAW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTBHOTITEM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTREEVIEW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTVCUSTOMDRAW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTVITEMCHANGE p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMUPDOWN p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMLVCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMTVCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMTTCUSTOMDRAW p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMLVDISPINFO p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final NMTVDISPINFO p1, final int p2);
    
    public static final native void MoveMemory(final NMLVDISPINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTVDISPINFO p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMLVFINDITEM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMLVODSTATECHANGE p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMHEADER p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMLINK p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMLISTVIEW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMREBARCHILDSIZE p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMREBARCHEVRON p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTOOLBAR p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTTCUSTOMDRAW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTTDISPINFOW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final NMTTDISPINFOA p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final RECT p0, final int[] p1, final int p2);
    
    public static final native void MoveMemory(final SHDRAGIMAGE p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final EMR p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final EMREXTCREATEFONTINDIRECTW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final SHDRAGIMAGE p1, final int p2);
    
    public static final native void MoveMemory(final TEXTMETRICW p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final TEXTMETRICA p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final TOUCHINPUT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final TVITEM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final WINDOWPOS p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final MSG p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final UDACCEL p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final DROPFILES p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final double[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final float[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final long[] p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final short[] p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_ITEM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_LOGATTR p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final SCRIPT_PROPERTIES p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final KEYBDINPUT p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final MOUSEINPUT p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final GESTURECONFIG p1, final int p2);
    
    public static final native boolean MoveToEx(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int MsgWaitForMultipleObjectsEx(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int MultiByteToWideChar(final int p0, final int p1, final byte[] p2, final int p3, final char[] p4, final int p5);
    
    public static final native int MultiByteToWideChar(final int p0, final int p1, final int p2, final int p3, final char[] p4, final int p5);
    
    public static final native void NotifyWinEvent(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean OffsetRect(final RECT p0, final int p1, final int p2);
    
    public static final native int OffsetRgn(final int p0, final int p1, final int p2);
    
    public static final native int OleInitialize(final int p0);
    
    public static final native void OleUninitialize();
    
    public static final native boolean OpenClipboard(final int p0);
    
    public static final native int OpenThemeData(final int p0, final char[] p1);
    
    public static final native boolean PatBlt(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native boolean PathIsExe(final int p0);
    
    public static final native boolean PeekMessageW(final MSG p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean PeekMessageA(final MSG p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean Pie(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native void POINTSTOPOINT(final POINT p0, final int p1);
    
    public static final native boolean Polygon(final int p0, final int[] p1, final int p2);
    
    public static final native boolean Polyline(final int p0, final int[] p1, final int p2);
    
    public static final native boolean PostMessageW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean PostMessageA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean PostThreadMessageW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean PostThreadMessageA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native short PRIMARYLANGID(final int p0);
    
    public static final native boolean PrintDlgW(final PRINTDLG p0);
    
    public static final native boolean PrintDlgA(final PRINTDLG p0);
    
    public static final native boolean PrintWindow(final int p0, final int p1, final int p2);
    
    public static final native int PSPropertyKeyFromString(final char[] p0, final PROPERTYKEY p1);
    
    public static final native boolean PtInRect(final RECT p0, final POINT p1);
    
    public static final native boolean PtInRegion(final int p0, final int p1, final int p2);
    
    public static final native int RealizePalette(final int p0);
    
    public static final native boolean Rectangle(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean RectInRegion(final int p0, final RECT p1);
    
    public static final native boolean RedrawWindow(final int p0, final RECT p1, final int p2, final int p3);
    
    public static final native int RegCloseKey(final int p0);
    
    public static final native int RegEnumKeyExW(final int p0, final int p1, final char[] p2, final int[] p3, final int[] p4, final char[] p5, final int[] p6, final FILETIME p7);
    
    public static final native int RegEnumKeyExA(final int p0, final int p1, final byte[] p2, final int[] p3, final int[] p4, final byte[] p5, final int[] p6, final FILETIME p7);
    
    public static final native int RegisterClassW(final WNDCLASS p0);
    
    public static final native int RegisterClassA(final WNDCLASS p0);
    
    public static final native boolean RegisterTouchWindow(final int p0, final int p1);
    
    public static final native int RegisterWindowMessageW(final char[] p0);
    
    public static final native int RegisterWindowMessageA(final byte[] p0);
    
    public static final native int RegisterClipboardFormatA(final byte[] p0);
    
    public static final native int RegisterClipboardFormatW(final char[] p0);
    
    public static final native int RegOpenKeyExW(final int p0, final char[] p1, final int p2, final int p3, final int[] p4);
    
    public static final native int RegOpenKeyExA(final int p0, final byte[] p1, final int p2, final int p3, final int[] p4);
    
    public static final native int RegQueryInfoKeyW(final int p0, final int p1, final int[] p2, final int p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final int[] p10, final int p11);
    
    public static final native int RegQueryInfoKeyA(final int p0, final int p1, final int[] p2, final int p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final int[] p10, final int p11);
    
    public static final native int RegQueryValueExW(final int p0, final char[] p1, final int p2, final int[] p3, final char[] p4, final int[] p5);
    
    public static final native int RegQueryValueExW(final int p0, final char[] p1, final int p2, final int[] p3, final int[] p4, final int[] p5);
    
    public static final native int RegQueryValueExA(final int p0, final byte[] p1, final int p2, final int[] p3, final byte[] p4, final int[] p5);
    
    public static final native int RegQueryValueExA(final int p0, final byte[] p1, final int p2, final int[] p3, final int[] p4, final int[] p5);
    
    public static final native boolean ReleaseCapture();
    
    public static final native int ReleaseDC(final int p0, final int p1);
    
    public static final native boolean RemoveMenu(final int p0, final int p1, final int p2);
    
    public static final native int RemovePropA(final int p0, final int p1);
    
    public static final native int RemovePropW(final int p0, final int p1);
    
    public static final native boolean ReplyMessage(final int p0);
    
    public static final native boolean RestoreDC(final int p0, final int p1);
    
    public static final native boolean RoundRect(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int SaveDC(final int p0);
    
    public static final native boolean ScreenToClient(final int p0, final POINT p1);
    
    public static final native int ScriptApplyDigitSubstitution(final SCRIPT_DIGITSUBSTITUTE p0, final SCRIPT_CONTROL p1, final SCRIPT_STATE p2);
    
    public static final native int ScriptBreak(final char[] p0, final int p1, final SCRIPT_ANALYSIS p2, final int p3);
    
    public static final native int ScriptGetProperties(final int[] p0, final int[] p1);
    
    public static final native int ScriptCacheGetHeight(final int p0, final int p1, final int[] p2);
    
    public static final native int ScriptCPtoX(final int p0, final boolean p1, final int p2, final int p3, final int p4, final int p5, final int p6, final SCRIPT_ANALYSIS p7, final int[] p8);
    
    public static final native int ScriptFreeCache(final int p0);
    
    public static final native int ScriptGetFontProperties(final int p0, final int p1, final SCRIPT_FONTPROPERTIES p2);
    
    public static final native int ScriptGetLogicalWidths(final SCRIPT_ANALYSIS p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int[] p6);
    
    public static final native int ScriptItemize(final char[] p0, final int p1, final int p2, final SCRIPT_CONTROL p3, final SCRIPT_STATE p4, final int p5, final int[] p6);
    
    public static final native int ScriptJustify(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int ScriptLayout(final int p0, final byte[] p1, final int[] p2, final int[] p3);
    
    public static final native int ScriptPlace(final int p0, final int p1, final int p2, final int p3, final int p4, final SCRIPT_ANALYSIS p5, final int p6, final int p7, final int[] p8);
    
    public static final native int ScriptRecordDigitSubstitution(final int p0, final SCRIPT_DIGITSUBSTITUTE p1);
    
    public static final native int ScriptGetCMap(final int p0, final int p1, final char[] p2, final int p3, final int p4, final short[] p5);
    
    public static final native int ScriptShape(final int p0, final int p1, final char[] p2, final int p3, final int p4, final SCRIPT_ANALYSIS p5, final int p6, final int p7, final int p8, final int[] p9);
    
    public static final native int ScriptStringAnalyse(final int p0, final char[] p1, final int p2, final int p3, final int p4, final int p5, final int p6, final SCRIPT_CONTROL p7, final SCRIPT_STATE p8, final int p9, final int p10, final int p11, final int p12);
    
    public static final native int ScriptStringOut(final int p0, final int p1, final int p2, final int p3, final RECT p4, final int p5, final int p6, final boolean p7);
    
    public static final native int ScriptStringFree(final int p0);
    
    public static final native int ScriptTextOut(final int p0, final int p1, final int p2, final int p3, final int p4, final RECT p5, final SCRIPT_ANALYSIS p6, final int p7, final int p8, final int p9, final int p10, final int p11, final int p12, final int p13);
    
    public static final native int ScriptXtoCP(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final SCRIPT_ANALYSIS p6, final int[] p7, final int[] p8);
    
    public static final native int ScrollWindowEx(final int p0, final int p1, final int p2, final RECT p3, final RECT p4, final int p5, final RECT p6, final int p7);
    
    public static final native int SelectClipRgn(final int p0, final int p1);
    
    public static final native int SelectObject(final int p0, final int p1);
    
    public static final native int SelectPalette(final int p0, final int p1, final boolean p2);
    
    public static final native int SendInput(final int p0, final int p1, final int p2);
    
    public static final native int SendMessageW(final int p0, final int p1, final int[] p2, final int[] p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final char[] p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final short[] p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final LVCOLUMN p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final LVHITTESTINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final LITEM p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final LVITEM p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final LVINSERTMARK p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final MARGINS p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final MCHITTESTINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final POINT p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final REBARBANDINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final RECT p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final SYSTEMTIME p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final SHDRAGIMAGE p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TBBUTTON p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TBBUTTONINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TCITEM p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TCHITTESTINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TOOLINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TVHITTESTINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TVINSERTSTRUCT p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TVITEM p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final TVSORTCB p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final UDACCEL p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final HDHITTESTINFO p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final HDITEM p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final HDLAYOUT p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final BUTTON_IMAGELIST p3);
    
    public static final native int SendMessageW(final int p0, final int p1, final int p2, final SIZE p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int[] p2, final int[] p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final short[] p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final char[] p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final LVCOLUMN p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final LVHITTESTINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final LITEM p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final LVINSERTMARK p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final LVITEM p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final MARGINS p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final MCHITTESTINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final POINT p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final REBARBANDINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final RECT p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final SYSTEMTIME p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final SHDRAGIMAGE p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TBBUTTON p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TBBUTTONINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TCITEM p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TCHITTESTINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TOOLINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TVHITTESTINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TVINSERTSTRUCT p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TVITEM p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final TVSORTCB p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final UDACCEL p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final HDHITTESTINFO p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final HDITEM p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final HDLAYOUT p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final BUTTON_IMAGELIST p3);
    
    public static final native int SendMessageA(final int p0, final int p1, final int p2, final SIZE p3);
    
    public static final native int SetActiveWindow(final int p0);
    
    public static final native int SetBkColor(final int p0, final int p1);
    
    public static final native int SetBkMode(final int p0, final int p1);
    
    public static final native boolean SetBrushOrgEx(final int p0, final int p1, final int p2, final POINT p3);
    
    public static final native int SetCapture(final int p0);
    
    public static final native boolean SetCaretPos(final int p0, final int p1);
    
    public static final native int SetClipboardData(final int p0, final int p1);
    
    public static final native int SetCurrentProcessExplicitAppUserModelID(final char[] p0);
    
    public static final native int SetCursor(final int p0);
    
    public static final native boolean SetCursorPos(final int p0, final int p1);
    
    public static final native int SetDIBColorTable(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native boolean SetDllDirectoryA(final byte[] p0);
    
    public static final native boolean SetDllDirectoryW(final char[] p0);
    
    public static final native int SetErrorMode(final int p0);
    
    public static final native int SetFocus(final int p0);
    
    public static final native boolean SetForegroundWindow(final int p0);
    
    public static final native boolean SetGestureConfig(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int SetGraphicsMode(final int p0, final int p1);
    
    public static final native boolean SetLayeredWindowAttributes(final int p0, final int p1, final byte p2, final int p3);
    
    public static final native int SetLayout(final int p0, final int p1);
    
    public static final native int SetMapMode(final int p0, final int p1);
    
    public static final native int SetMapperFlags(final int p0, final int p1);
    
    public static final native boolean SetMenu(final int p0, final int p1);
    
    public static final native boolean SetMenuDefaultItem(final int p0, final int p1, final int p2);
    
    public static final native boolean SetMenuInfo(final int p0, final MENUINFO p1);
    
    public static final native boolean SetMenuItemInfoW(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native boolean SetMenuItemInfoA(final int p0, final int p1, final boolean p2, final MENUITEMINFO p3);
    
    public static final native int SetMetaRgn(final int p0);
    
    public static final native int SetPaletteEntries(final int p0, final int p1, final int p2, final byte[] p3);
    
    public static final native int SetParent(final int p0, final int p1);
    
    public static final native int SetPixel(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int SetPolyFillMode(final int p0, final int p1);
    
    public static final native boolean SetProcessDPIAware();
    
    public static final native boolean SetRect(final RECT p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean SetRectRgn(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int SetROP2(final int p0, final int p1);
    
    public static final native boolean SetScrollInfo(final int p0, final int p1, final SCROLLINFO p2, final boolean p3);
    
    public static final native int SetStretchBltMode(final int p0, final int p1);
    
    public static final native boolean SetPropW(final int p0, final int p1, final int p2);
    
    public static final native boolean SetPropA(final int p0, final int p1, final int p2);
    
    public static final native int SetTextAlign(final int p0, final int p1);
    
    public static final native int SetTextColor(final int p0, final int p1);
    
    public static final native int SetTimer(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean SetViewportExtEx(final int p0, final int p1, final int p2, final SIZE p3);
    
    public static final native boolean SetViewportOrgEx(final int p0, final int p1, final int p2, final POINT p3);
    
    public static final native int SetWindowLongW(final int p0, final int p1, final int p2);
    
    public static final native int SetWindowLongA(final int p0, final int p1, final int p2);
    
    public static final native int SetWindowLongPtrW(final int p0, final int p1, final int p2);
    
    public static final native int SetWindowLongPtrA(final int p0, final int p1, final int p2);
    
    public static final native boolean SetWindowExtEx(final int p0, final int p1, final int p2, final SIZE p3);
    
    public static final native boolean SetWindowOrgEx(final int p0, final int p1, final int p2, final POINT p3);
    
    public static final native boolean SetWindowPlacement(final int p0, final WINDOWPLACEMENT p1);
    
    public static final native boolean SetWindowPos(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int SetWindowRgn(final int p0, final int p1, final boolean p2);
    
    public static final native boolean SetWindowTextW(final int p0, final char[] p1);
    
    public static final native boolean SetWindowTextA(final int p0, final byte[] p1);
    
    public static final native int SetWindowTheme(final int p0, final char[] p1, final char[] p2);
    
    public static final native int SetWindowsHookExW(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int SetWindowsHookExA(final int p0, final int p1, final int p2, final int p3);
    
    public static final native boolean SetWorldTransform(final int p0, final float[] p1);
    
    public static final native int SHBrowseForFolderW(final BROWSEINFO p0);
    
    public static final native int SHBrowseForFolderA(final BROWSEINFO p0);
    
    public static final native boolean SHCreateMenuBar(final SHMENUBARINFO p0);
    
    public static final native int SHGetFileInfoW(final char[] p0, final int p1, final SHFILEINFOW p2, final int p3, final int p4);
    
    public static final native int SHGetFileInfoA(final byte[] p0, final int p1, final SHFILEINFOA p2, final int p3, final int p4);
    
    public static final native int SHGetFolderPathW(final int p0, final int p1, final int p2, final int p3, final char[] p4);
    
    public static final native int SHGetFolderPathA(final int p0, final int p1, final int p2, final int p3, final byte[] p4);
    
    public static final native boolean SHHandleWMSettingChange(final int p0, final int p1, final int p2, final SHACTIVATEINFO p3);
    
    public static final native int SHRecognizeGesture(final SHRGINFO p0);
    
    public static final native void SHSendBackToFocusWindow(final int p0, final int p1, final int p2);
    
    public static final native boolean SHSipPreference(final int p0, final int p1);
    
    public static final native boolean ShellExecuteExW(final SHELLEXECUTEINFO p0);
    
    public static final native boolean ShellExecuteExA(final SHELLEXECUTEINFO p0);
    
    public static final native boolean Shell_NotifyIconA(final int p0, final NOTIFYICONDATAA p1);
    
    public static final native boolean Shell_NotifyIconW(final int p0, final NOTIFYICONDATAW p1);
    
    public static final native int SHGetMalloc(final int[] p0);
    
    public static final native boolean SHGetPathFromIDListW(final int p0, final char[] p1);
    
    public static final native boolean SHGetPathFromIDListA(final int p0, final byte[] p1);
    
    public static final native int SHCreateItemInKnownFolder(final byte[] p0, final int p1, final char[] p2, final byte[] p3, final int[] p4);
    
    public static final native int SHCreateItemFromRelativeName(final int p0, final char[] p1, final int p2, final byte[] p3, final int[] p4);
    
    public static final native boolean SHSetAppKeyWndAssoc(final byte p0, final int p1);
    
    public static final native boolean ShowCaret(final int p0);
    
    public static final native int ShowCursor(final boolean p0);
    
    public static final native boolean ShowOwnedPopups(final int p0, final boolean p1);
    
    public static final native boolean ShowScrollBar(final int p0, final int p1, final boolean p2);
    
    public static final native boolean ShowWindow(final int p0, final int p1);
    
    public static final native boolean SipGetInfo(final SIPINFO p0);
    
    public static final native int StartDocW(final int p0, final DOCINFO p1);
    
    public static final native int StartDocA(final int p0, final DOCINFO p1);
    
    public static final native int StartPage(final int p0);
    
    public static final native boolean StretchBlt(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native boolean StrokePath(final int p0);
    
    public static final native boolean SystemParametersInfoW(final int p0, final int p1, final HIGHCONTRAST p2, final int p3);
    
    public static final native boolean SystemParametersInfoA(final int p0, final int p1, final HIGHCONTRAST p2, final int p3);
    
    public static final native boolean SystemParametersInfoW(final int p0, final int p1, final RECT p2, final int p3);
    
    public static final native boolean SystemParametersInfoA(final int p0, final int p1, final RECT p2, final int p3);
    
    public static final native boolean SystemParametersInfoW(final int p0, final int p1, final NONCLIENTMETRICSW p2, final int p3);
    
    public static final native boolean SystemParametersInfoA(final int p0, final int p1, final NONCLIENTMETRICSA p2, final int p3);
    
    public static final native boolean SystemParametersInfoW(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native boolean SystemParametersInfoA(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int ToAscii(final int p0, final int p1, final byte[] p2, final short[] p3, final int p4);
    
    public static final native int ToUnicode(final int p0, final int p1, final byte[] p2, final char[] p3, final int p4, final int p5);
    
    public static final native long TOUCH_COORD_TO_PIXEL(final long p0);
    
    public static final native boolean TreeView_GetItemRect(final int p0, final int p1, final RECT p2, final boolean p3);
    
    public static final native boolean TrackMouseEvent(final TRACKMOUSEEVENT p0);
    
    public static final native boolean TrackPopupMenu(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final RECT p6);
    
    public static final native int TranslateAcceleratorW(final int p0, final int p1, final MSG p2);
    
    public static final native int TranslateAcceleratorA(final int p0, final int p1, final MSG p2);
    
    public static final native boolean TranslateCharsetInfo(final int p0, final int[] p1, final int p2);
    
    public static final native boolean TranslateMDISysAccel(final int p0, final MSG p1);
    
    public static final native boolean TranslateMessage(final MSG p0);
    
    public static final native boolean TransparentBlt(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native boolean TransparentImage(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native boolean UnhookWindowsHookEx(final int p0);
    
    public static final native boolean UnregisterClassW(final char[] p0, final int p1);
    
    public static final native boolean UnregisterClassA(final byte[] p0, final int p1);
    
    public static final native boolean UpdateLayeredWindow(final int p0, final int p1, final POINT p2, final SIZE p3, final int p4, final POINT p5, final int p6, final BLENDFUNCTION p7, final int p8);
    
    public static final native boolean UnregisterTouchWindow(final int p0);
    
    public static final native boolean UpdateWindow(final int p0);
    
    public static final native boolean ValidateRect(final int p0, final RECT p1);
    
    public static final native short VkKeyScanW(final short p0);
    
    public static final native short VkKeyScanA(final short p0);
    
    public static final native int VtblCall(final int p0, final int p1);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final int[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3, final int p4, final long[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int[] p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final short p2, final byte[] p3, final byte[] p4, final byte[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int[] p2);
    
    public static final native int VtblCall(final int p0, final int p1, final long[] p2);
    
    public static final native int VtblCall(final int p0, final int p1, final byte[] p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final byte[] p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int[] p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long[] p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final TF_DISPLAYATTRIBUTE p2);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long p3, final long p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final long p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int[] p2, final byte[] p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int[] p2, final byte[] p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final PROPERTYKEY p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final PROPERTYKEY p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final char[] p4, final char[] p5, final int p6);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3, final char[] p4, final char[] p5, final long p6);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long[] p3);
    
    public static final native boolean WaitMessage();
    
    public static final native int WideCharToMultiByte(final int p0, final int p1, final char[] p2, final int p3, final byte[] p4, final int p5, final byte[] p6, final boolean[] p7);
    
    public static final native int WideCharToMultiByte(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int p5, final byte[] p6, final boolean[] p7);
    
    public static final native int WindowFromDC(final int p0);
    
    public static final native int WindowFromPoint(final POINT p0);
    
    public static final native int wcslen(final int p0);
    
    public static final native int MapViewOfFile(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native boolean UnmapViewOfFile(final int p0);
    
    public static final native int OpenProcess(final int p0, final boolean p1, final int p2);
    
    public static final native int GetCurrentProcess();
    
    public static final native boolean DuplicateHandle(final int p0, final int p1, final int p2, final int[] p3, final int p4, final boolean p5, final int p6);
}
