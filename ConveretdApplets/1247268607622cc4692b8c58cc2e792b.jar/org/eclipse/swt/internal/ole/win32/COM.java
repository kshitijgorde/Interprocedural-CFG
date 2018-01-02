// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.SHDRAGIMAGE;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;

public class COM extends OS
{
    public static final GUID CLSID_DragDropHelper;
    public static final GUID IID_IDropTargetHelper;
    public static final GUID IID_IDragSourceHelper;
    public static final GUID IID_IDragSourceHelper2;
    public static final GUID IIDJavaBeansBridge;
    public static final GUID IIDShockwaveActiveXControl;
    public static final GUID IIDIEditorSiteTime;
    public static final GUID IIDIEditorSiteProperty;
    public static final GUID IIDIEditorBaseProperty;
    public static final GUID IIDIEditorSite;
    public static final GUID IIDIEditorService;
    public static final GUID IIDIEditorManager;
    public static final GUID IIDIAccessible;
    public static final GUID IIDIAdviseSink;
    public static final GUID IIDIClassFactory;
    public static final GUID IIDIClassFactory2;
    public static final GUID IIDIConnectionPoint;
    public static final GUID IIDIConnectionPointContainer;
    public static final GUID IIDIDataObject;
    public static final GUID IIDIDispatch;
    public static final GUID IIDIDispatchEx;
    public static final GUID IIDIDocHostUIHandler;
    public static final GUID IIDIDocHostShowUI;
    public static final GUID IIDIDropSource;
    public static final GUID IIDIDropTarget;
    public static final GUID IIDIEnumFORMATETC;
    public static final GUID IIDIEnumVARIANT;
    public static final GUID IIDIFont;
    public static final String IIDIHTMLDocumentEvents2 = "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}";
    public static final GUID IIDIInternetSecurityManager;
    public static final GUID IIDIAuthenticate;
    public static final GUID IIDIJScriptTypeInfo;
    public static final GUID IIDIOleClientSite;
    public static final GUID IIDIOleCommandTarget;
    public static final GUID IIDIOleContainer;
    public static final GUID IIDIOleControl;
    public static final GUID IIDIOleControlSite;
    public static final GUID IIDIOleDocument;
    public static final GUID IIDIOleDocumentSite;
    public static final GUID IIDIOleInPlaceActiveObject;
    public static final GUID IIDIOleInPlaceFrame;
    public static final GUID IIDIOleInPlaceObject;
    public static final GUID IIDIOleInPlaceSite;
    public static final GUID IIDIOleInPlaceUIWindow;
    public static final GUID IIDIOleLink;
    public static final GUID IIDIOleObject;
    public static final GUID IIDIOleWindow;
    public static final GUID IIDIPersist;
    public static final GUID IIDIPersistFile;
    public static final GUID IIDIPersistStorage;
    public static final GUID IIDIPersistStream;
    public static final GUID IIDIPersistStreamInit;
    public static final GUID IIDIPropertyNotifySink;
    public static final GUID IIDIProvideClassInfo;
    public static final GUID IIDIProvideClassInfo2;
    public static final GUID IIDIServiceProvider;
    public static final GUID IIDISpecifyPropertyPages;
    public static final GUID IIDIStorage;
    public static final GUID IIDIStream;
    public static final GUID IIDIUnknown;
    public static final GUID IIDIViewObject2;
    public static final GUID CGID_DocHostCommandHandler;
    public static final GUID CGID_Explorer;
    public static final GUID IIDIAccessible2;
    public static final GUID IIDIAccessibleRelation;
    public static final GUID IIDIAccessibleAction;
    public static final GUID IIDIAccessibleComponent;
    public static final GUID IIDIAccessibleValue;
    public static final GUID IIDIAccessibleText;
    public static final GUID IIDIAccessibleEditableText;
    public static final GUID IIDIAccessibleHyperlink;
    public static final GUID IIDIAccessibleHypertext;
    public static final GUID IIDIAccessibleTable;
    public static final GUID IIDIAccessibleTable2;
    public static final GUID IIDIAccessibleTableCell;
    public static final GUID IIDIAccessibleImage;
    public static final GUID IIDIAccessibleApplication;
    public static final GUID IIDIAccessibleContext;
    public static final int CF_TEXT = 1;
    public static final int CF_BITMAP = 2;
    public static final int CF_METAFILEPICT = 3;
    public static final int CF_SYLK = 4;
    public static final int CF_DIF = 5;
    public static final int CF_TIFF = 6;
    public static final int CF_OEMTEXT = 7;
    public static final int CF_DIB = 8;
    public static final int CF_PALETTE = 9;
    public static final int CF_PENDATA = 10;
    public static final int CF_RIFF = 11;
    public static final int CF_WAVE = 12;
    public static final int CF_UNICODETEXT = 13;
    public static final int CF_ENHMETAFILE = 14;
    public static final int CF_HDROP = 15;
    public static final int CF_LOCALE = 16;
    public static final int CF_MAX = 17;
    public static final int CLSCTX_INPROC_HANDLER = 2;
    public static final int CLSCTX_INPROC_SERVER = 1;
    public static final int CLSCTX_LOCAL_SERVER = 4;
    public static final int CLSCTX_REMOTE_SERVER = 16;
    public static final int CO_E_CLASSSTRING = -2147221005;
    public static final int DATADIR_GET = 1;
    public static final int DATADIR_SET = 2;
    public static final int DISPATCH_CONSTRUCT = 16384;
    public static final int DISP_E_EXCEPTION = -2147352567;
    public static final int DISP_E_MEMBERNOTFOUND = -2147352573;
    public static final int DISP_E_UNKNOWNINTERFACE = -2147352575;
    public static final int DISP_E_UNKNOWNNAME = -2147352570;
    public static final int DISPID_AMBIENT_BACKCOLOR = -701;
    public static final int DISPID_AMBIENT_FONT = -703;
    public static final int DISPID_AMBIENT_FORECOLOR = -704;
    public static final int DISPID_AMBIENT_LOCALEID = -705;
    public static final int DISPID_AMBIENT_MESSAGEREFLECT = -706;
    public static final int DISPID_AMBIENT_OFFLINEIFNOTCONNECTED = -5501;
    public static final int DISPID_AMBIENT_SHOWGRABHANDLES = -711;
    public static final int DISPID_AMBIENT_SHOWHATCHING = -712;
    public static final int DISPID_AMBIENT_SILENT = -5502;
    public static final int DISPID_AMBIENT_SUPPORTSMNEMONICS = -714;
    public static final int DISPID_AMBIENT_UIDEAD = -710;
    public static final int DISPID_AMBIENT_USERMODE = -709;
    public static final int DISPID_BACKCOLOR = -501;
    public static final int DISPID_FONT = -512;
    public static final int DISPID_FONT_BOLD = 3;
    public static final int DISPID_FONT_CHARSET = 8;
    public static final int DISPID_FONT_ITALIC = 4;
    public static final int DISPID_FONT_NAME = 0;
    public static final int DISPID_FONT_SIZE = 2;
    public static final int DISPID_FONT_STRIKE = 6;
    public static final int DISPID_FONT_UNDER = 5;
    public static final int DISPID_FONT_WEIGHT = 7;
    public static final int DISPID_FORECOLOR = -513;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDBLCLICK = -601;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDRAGEND = -2147418091;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONDRAGSTART = -2147418101;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYDOWN = -602;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYPRESS = -603;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONKEYUP = -604;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOUT = -2147418103;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOVER = -2147418104;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEMOVE = -606;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEDOWN = -605;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEUP = -607;
    public static final int DISPID_HTMLDOCUMENTEVENTS_ONMOUSEWHEEL = 1033;
    public static final int DRAGDROP_S_DROP = 262400;
    public static final int DRAGDROP_S_CANCEL = 262401;
    public static final int DRAGDROP_S_USEDEFAULTCURSORS = 262402;
    public static final int DROPEFFECT_NONE = 0;
    public static final int DROPEFFECT_COPY = 1;
    public static final int DROPEFFECT_MOVE = 2;
    public static final int DROPEFFECT_LINK = 4;
    public static final int DROPEFFECT_SCROLL = Integer.MIN_VALUE;
    public static final int DSH_ALLOWDROPDESCRIPTIONTEXT = 1;
    public static final int DV_E_FORMATETC = -2147221404;
    public static final int DV_E_STGMEDIUM = -2147221402;
    public static final int DV_E_TYMED = -2147221399;
    public static final int DVASPECT_CONTENT = 1;
    public static final int E_ACCESSDENIED = -2147024891;
    public static final int E_FAIL = -2147467259;
    public static final int E_INVALIDARG = -2147024809;
    public static final int E_NOINTERFACE = -2147467262;
    public static final int E_NOTIMPL = -2147467263;
    public static final int E_NOTSUPPORTED = -2147221248;
    public static final int E_OUTOFMEMORY = -2147024882;
    public static final int GMEM_FIXED = 0;
    public static final int GMEM_ZEROINIT = 64;
    public static final int GUIDKIND_DEFAULT_SOURCE_DISP_IID = 1;
    public static final int IMPLTYPEFLAG_FDEFAULT = 1;
    public static final int IMPLTYPEFLAG_FRESTRICTED = 4;
    public static final int IMPLTYPEFLAG_FSOURCE = 2;
    public static final int LOCALE_SYSTEM_DEFAULT = 1024;
    public static final int LOCALE_USER_DEFAULT = 2048;
    public static final int OLECLOSE_NOSAVE = 1;
    public static final int OLECLOSE_SAVEIFDIRTY = 0;
    public static final int OLEEMBEDDED = 1;
    public static final int OLEIVERB_DISCARDUNDOSTATE = -6;
    public static final int OLEIVERB_INPLACEACTIVATE = -5;
    public static final int OLEIVERB_PRIMARY = 0;
    public static final int OLELINKED = 0;
    public static final int OLERENDER_DRAW = 1;
    public static final int S_FALSE = 1;
    public static final int S_OK = 0;
    public static final int STG_E_FILENOTFOUND = -2147287038;
    public static final int STG_S_CONVERTED = 197120;
    public static final int STGC_DEFAULT = 0;
    public static final int STGM_CONVERT = 131072;
    public static final int STGM_CREATE = 4096;
    public static final int STGM_DELETEONRELEASE = 67108864;
    public static final int STGM_DIRECT = 0;
    public static final int STGM_DIRECT_SWMR = 4194304;
    public static final int STGM_FAILIFTHERE = 0;
    public static final int STGM_NOSCRATCH = 1048576;
    public static final int STGM_NOSNAPSHOT = 2097152;
    public static final int STGM_PRIORITY = 262144;
    public static final int STGM_READ = 0;
    public static final int STGM_READWRITE = 2;
    public static final int STGM_SHARE_DENY_NONE = 64;
    public static final int STGM_SHARE_DENY_READ = 48;
    public static final int STGM_SHARE_DENY_WRITE = 32;
    public static final int STGM_SHARE_EXCLUSIVE = 16;
    public static final int STGM_SIMPLE = 134217728;
    public static final int STGM_TRANSACTED = 65536;
    public static final int STGM_WRITE = 1;
    public static final int STGTY_STORAGE = 1;
    public static final int STGTY_STREAM = 2;
    public static final int STGTY_LOCKBYTES = 3;
    public static final int STGTY_PROPERTY = 4;
    public static final int TYMED_HGLOBAL = 1;
    public static final short DISPATCH_METHOD = 1;
    public static final short DISPATCH_PROPERTYGET = 2;
    public static final short DISPATCH_PROPERTYPUT = 4;
    public static final short DISPATCH_PROPERTYPUTREF = 8;
    public static final short DISPID_PROPERTYPUT = -3;
    public static final short DISPID_UNKNOWN = -1;
    public static final short DISPID_VALUE = 0;
    public static final short VT_BOOL = 11;
    public static final short VT_BSTR = 8;
    public static final short VT_BYREF = 16384;
    public static final short VT_CY = 6;
    public static final short VT_DATE = 7;
    public static final short VT_DISPATCH = 9;
    public static final short VT_EMPTY = 0;
    public static final short VT_ERROR = 10;
    public static final short VT_I1 = 16;
    public static final short VT_I2 = 2;
    public static final short VT_I4 = 3;
    public static final short VT_I8 = 20;
    public static final short VT_NULL = 1;
    public static final short VT_R4 = 4;
    public static final short VT_R8 = 5;
    public static final short VT_UI1 = 17;
    public static final short VT_UI2 = 18;
    public static final short VT_UI4 = 19;
    public static final short VT_UNKNOWN = 13;
    public static final short VT_VARIANT = 12;
    public static boolean FreeUnusedLibraries;
    public static final int CHILDID_SELF = 0;
    public static final int CO_E_OBJNOTCONNECTED = -2147220995;
    public static final int ROLE_SYSTEM_MENUBAR = 2;
    public static final int ROLE_SYSTEM_SCROLLBAR = 3;
    public static final int ROLE_SYSTEM_ALERT = 8;
    public static final int ROLE_SYSTEM_WINDOW = 9;
    public static final int ROLE_SYSTEM_CLIENT = 10;
    public static final int ROLE_SYSTEM_MENUPOPUP = 11;
    public static final int ROLE_SYSTEM_MENUITEM = 12;
    public static final int ROLE_SYSTEM_TOOLTIP = 13;
    public static final int ROLE_SYSTEM_DOCUMENT = 15;
    public static final int ROLE_SYSTEM_DIALOG = 18;
    public static final int ROLE_SYSTEM_GROUPING = 20;
    public static final int ROLE_SYSTEM_SEPARATOR = 21;
    public static final int ROLE_SYSTEM_TOOLBAR = 22;
    public static final int ROLE_SYSTEM_STATUSBAR = 23;
    public static final int ROLE_SYSTEM_TABLE = 24;
    public static final int ROLE_SYSTEM_COLUMNHEADER = 25;
    public static final int ROLE_SYSTEM_ROWHEADER = 26;
    public static final int ROLE_SYSTEM_COLUMN = 27;
    public static final int ROLE_SYSTEM_ROW = 28;
    public static final int ROLE_SYSTEM_CELL = 29;
    public static final int ROLE_SYSTEM_LINK = 30;
    public static final int ROLE_SYSTEM_LIST = 33;
    public static final int ROLE_SYSTEM_LISTITEM = 34;
    public static final int ROLE_SYSTEM_OUTLINE = 35;
    public static final int ROLE_SYSTEM_OUTLINEITEM = 36;
    public static final int ROLE_SYSTEM_PAGETAB = 37;
    public static final int ROLE_SYSTEM_GRAPHIC = 40;
    public static final int ROLE_SYSTEM_STATICTEXT = 41;
    public static final int ROLE_SYSTEM_TEXT = 42;
    public static final int ROLE_SYSTEM_PUSHBUTTON = 43;
    public static final int ROLE_SYSTEM_CHECKBUTTON = 44;
    public static final int ROLE_SYSTEM_RADIOBUTTON = 45;
    public static final int ROLE_SYSTEM_COMBOBOX = 46;
    public static final int ROLE_SYSTEM_DROPLIST = 47;
    public static final int ROLE_SYSTEM_PROGRESSBAR = 48;
    public static final int ROLE_SYSTEM_SLIDER = 51;
    public static final int ROLE_SYSTEM_SPINBUTTON = 52;
    public static final int ROLE_SYSTEM_ANIMATION = 54;
    public static final int ROLE_SYSTEM_PAGETABLIST = 60;
    public static final int ROLE_SYSTEM_CLOCK = 61;
    public static final int ROLE_SYSTEM_SPLITBUTTON = 62;
    public static final int STATE_SYSTEM_NORMAL = 0;
    public static final int STATE_SYSTEM_UNAVAILABLE = 1;
    public static final int STATE_SYSTEM_SELECTED = 2;
    public static final int STATE_SYSTEM_FOCUSED = 4;
    public static final int STATE_SYSTEM_PRESSED = 8;
    public static final int STATE_SYSTEM_CHECKED = 16;
    public static final int STATE_SYSTEM_MIXED = 32;
    public static final int STATE_SYSTEM_READONLY = 64;
    public static final int STATE_SYSTEM_HOTTRACKED = 128;
    public static final int STATE_SYSTEM_EXPANDED = 512;
    public static final int STATE_SYSTEM_COLLAPSED = 1024;
    public static final int STATE_SYSTEM_BUSY = 2048;
    public static final int STATE_SYSTEM_INVISIBLE = 32768;
    public static final int STATE_SYSTEM_OFFSCREEN = 65536;
    public static final int STATE_SYSTEM_SIZEABLE = 131072;
    public static final int STATE_SYSTEM_FOCUSABLE = 1048576;
    public static final int STATE_SYSTEM_SELECTABLE = 2097152;
    public static final int STATE_SYSTEM_LINKED = 4194304;
    public static final int STATE_SYSTEM_MULTISELECTABLE = 16777216;
    public static final int EVENT_OBJECT_SELECTIONWITHIN = 32777;
    public static final int EVENT_OBJECT_STATECHANGE = 32778;
    public static final int EVENT_OBJECT_LOCATIONCHANGE = 32779;
    public static final int EVENT_OBJECT_NAMECHANGE = 32780;
    public static final int EVENT_OBJECT_DESCRIPTIONCHANGE = 32781;
    public static final int EVENT_OBJECT_VALUECHANGE = 32782;
    public static final int EVENT_OBJECT_TEXTSELECTIONCHANGED = 32788;
    public static final int IA2_COORDTYPE_SCREEN_RELATIVE = 0;
    public static final int IA2_COORDTYPE_PARENT_RELATIVE = 1;
    public static final int IA2_STATE_ACTIVE = 1;
    public static final int IA2_STATE_SINGLE_LINE = 8192;
    public static final int IA2_STATE_MULTI_LINE = 512;
    public static final int IA2_STATE_REQUIRED = 2048;
    public static final int IA2_STATE_INVALID_ENTRY = 64;
    public static final int IA2_STATE_SUPPORTS_AUTOCOMPLETION = 32768;
    public static final int IA2_STATE_EDITABLE = 8;
    public static final int IA2_EVENT_DOCUMENT_LOAD_COMPLETE = 261;
    public static final int IA2_EVENT_DOCUMENT_LOAD_STOPPED = 262;
    public static final int IA2_EVENT_DOCUMENT_RELOAD = 263;
    public static final int IA2_EVENT_PAGE_CHANGED = 273;
    public static final int IA2_EVENT_SECTION_CHANGED = 274;
    public static final int IA2_EVENT_ACTION_CHANGED = 257;
    public static final int IA2_EVENT_HYPERLINK_START_INDEX_CHANGED = 269;
    public static final int IA2_EVENT_HYPERLINK_END_INDEX_CHANGED = 264;
    public static final int IA2_EVENT_HYPERLINK_ANCHOR_COUNT_CHANGED = 265;
    public static final int IA2_EVENT_HYPERLINK_SELECTED_LINK_CHANGED = 266;
    public static final int IA2_EVENT_HYPERLINK_ACTIVATED = 267;
    public static final int IA2_EVENT_HYPERTEXT_LINK_SELECTED = 268;
    public static final int IA2_EVENT_HYPERTEXT_LINK_COUNT_CHANGED = 271;
    public static final int IA2_EVENT_ATTRIBUTE_CHANGED = 272;
    public static final int IA2_EVENT_TABLE_CAPTION_CHANGED = 275;
    public static final int IA2_EVENT_TABLE_COLUMN_DESCRIPTION_CHANGED = 276;
    public static final int IA2_EVENT_TABLE_COLUMN_HEADER_CHANGED = 277;
    public static final int IA2_EVENT_TABLE_CHANGED = 278;
    public static final int IA2_EVENT_TABLE_ROW_DESCRIPTION_CHANGED = 279;
    public static final int IA2_EVENT_TABLE_ROW_HEADER_CHANGED = 280;
    public static final int IA2_EVENT_TABLE_SUMMARY_CHANGED = 281;
    public static final int IA2_EVENT_TEXT_ATTRIBUTE_CHANGED = 282;
    public static final int IA2_EVENT_TEXT_CARET_MOVED = 283;
    public static final int IA2_EVENT_TEXT_COLUMN_CHANGED = 285;
    public static final int IA2_EVENT_TEXT_INSERTED = 286;
    public static final int IA2_EVENT_TEXT_REMOVED = 287;
    public static final int IA2_TEXT_BOUNDARY_CHAR = 0;
    public static final int IA2_TEXT_BOUNDARY_WORD = 1;
    public static final int IA2_TEXT_BOUNDARY_SENTENCE = 2;
    public static final int IA2_TEXT_BOUNDARY_PARAGRAPH = 3;
    public static final int IA2_TEXT_BOUNDARY_LINE = 4;
    public static final int IA2_TEXT_BOUNDARY_ALL = 5;
    public static final int IA2_TEXT_OFFSET_LENGTH = -1;
    public static final int IA2_TEXT_OFFSET_CARET = -2;
    public static final int IA2_SCROLL_TYPE_TOP_LEFT = 0;
    public static final int IA2_SCROLL_TYPE_BOTTOM_RIGHT = 1;
    public static final int IA2_SCROLL_TYPE_TOP_EDGE = 2;
    public static final int IA2_SCROLL_TYPE_BOTTOM_EDGE = 3;
    public static final int IA2_SCROLL_TYPE_LEFT_EDGE = 4;
    public static final int IA2_SCROLL_TYPE_RIGHT_EDGE = 5;
    public static final int IA2_SCROLL_TYPE_ANYWHERE = 6;
    
    static {
        CLSID_DragDropHelper = IIDFromString("{4657278A-411B-11d2-839A-00C04FD918D0}");
        IID_IDropTargetHelper = IIDFromString("{4657278B-411B-11d2-839A-00C04FD918D0}");
        IID_IDragSourceHelper = IIDFromString("{DE5BF786-477A-11d2-839D-00C04FD918D0}");
        IID_IDragSourceHelper2 = IIDFromString("{83E07D0D-0C5F-4163-BF1A-60B274051E40}");
        IIDJavaBeansBridge = IIDFromString("{8AD9C840-044E-11D1-B3E9-00805F499D93}");
        IIDShockwaveActiveXControl = IIDFromString("{166B1BCA-3F9C-11CF-8075-444553540000}");
        IIDIEditorSiteTime = IIDFromString("{6BD2AEFE-7876-45e6-A6E7-3BFCDF6540AA}");
        IIDIEditorSiteProperty = IIDFromString("{D381A1F4-2326-4f3c-AFB9-B7537DB9E238}");
        IIDIEditorBaseProperty = IIDFromString("{61E55B0B-2647-47c4-8C89-E736EF15D636}");
        IIDIEditorSite = IIDFromString("{CDD88AB9-B01D-426E-B0F0-30973E9A074B}");
        IIDIEditorService = IIDFromString("{BEE283FE-7B42-4FF3-8232-0F07D43ABCF1}");
        IIDIEditorManager = IIDFromString("{EFDE08C4-BE87-4B1A-BF84-15FC30207180}");
        IIDIAccessible = IIDFromString("{618736E0-3C3D-11CF-810C-00AA00389B71}");
        IIDIAdviseSink = IIDFromString("{0000010F-0000-0000-C000-000000000046}");
        IIDIClassFactory = IIDFromString("{00000001-0000-0000-C000-000000000046}");
        IIDIClassFactory2 = IIDFromString("{B196B28F-BAB4-101A-B69C-00AA00341D07}");
        IIDIConnectionPoint = IIDFromString("{B196B286-BAB4-101A-B69C-00AA00341D07}");
        IIDIConnectionPointContainer = IIDFromString("{B196B284-BAB4-101A-B69C-00AA00341D07}");
        IIDIDataObject = IIDFromString("{0000010E-0000-0000-C000-000000000046}");
        IIDIDispatch = IIDFromString("{00020400-0000-0000-C000-000000000046}");
        IIDIDispatchEx = IIDFromString("{A6EF9860-C720-11D0-9337-00A0C90DCAA9}");
        IIDIDocHostUIHandler = IIDFromString("{BD3F23C0-D43E-11CF-893B-00AA00BDCE1A}");
        IIDIDocHostShowUI = IIDFromString("{C4D244B0-D43E-11CF-893B-00AA00BDCE1A}");
        IIDIDropSource = IIDFromString("{00000121-0000-0000-C000-000000000046}");
        IIDIDropTarget = IIDFromString("{00000122-0000-0000-C000-000000000046}");
        IIDIEnumFORMATETC = IIDFromString("{00000103-0000-0000-C000-000000000046}");
        IIDIEnumVARIANT = IIDFromString("{00020404-0000-0000-C000-000000000046}");
        IIDIFont = IIDFromString("{BEF6E002-A874-101A-8BBA-00AA00300CAB}");
        IIDIInternetSecurityManager = IIDFromString("{79eac9ee-baf9-11ce-8c82-00aa004ba90b}");
        IIDIAuthenticate = IIDFromString("{79eac9d0-baf9-11ce-8c82-00aa004ba90b}");
        IIDIJScriptTypeInfo = IIDFromString("{C59C6B12-F6C1-11CF-8835-00A0C911E8B2}");
        IIDIOleClientSite = IIDFromString("{00000118-0000-0000-C000-000000000046}");
        IIDIOleCommandTarget = IIDFromString("{B722BCCB-4E68-101B-A2BC-00AA00404770}");
        IIDIOleContainer = IIDFromString("{0000011B-0000-0000-C000-000000000046}");
        IIDIOleControl = IIDFromString("{B196B288-BAB4-101A-B69C-00AA00341D07}");
        IIDIOleControlSite = IIDFromString("{B196B289-BAB4-101A-B69C-00AA00341D07}");
        IIDIOleDocument = IIDFromString("{B722BCC5-4E68-101B-A2BC-00AA00404770}");
        IIDIOleDocumentSite = IIDFromString("{B722BCC7-4E68-101B-A2BC-00AA00404770}");
        IIDIOleInPlaceActiveObject = IIDFromString("{00000117-0000-0000-C000-000000000046}");
        IIDIOleInPlaceFrame = IIDFromString("{00000116-0000-0000-C000-000000000046}");
        IIDIOleInPlaceObject = IIDFromString("{00000113-0000-0000-C000-000000000046}");
        IIDIOleInPlaceSite = IIDFromString("{00000119-0000-0000-C000-000000000046}");
        IIDIOleInPlaceUIWindow = IIDFromString("{00000115-0000-0000-C000-000000000046}");
        IIDIOleLink = IIDFromString("{0000011D-0000-0000-C000-000000000046}");
        IIDIOleObject = IIDFromString("{00000112-0000-0000-C000-000000000046}");
        IIDIOleWindow = IIDFromString("{00000114-0000-0000-C000-000000000046}");
        IIDIPersist = IIDFromString("{0000010C-0000-0000-C000-000000000046}");
        IIDIPersistFile = IIDFromString("{0000010B-0000-0000-C000-000000000046}");
        IIDIPersistStorage = IIDFromString("{0000010A-0000-0000-C000-000000000046}");
        IIDIPersistStream = IIDFromString("{00000109-0000-0000-C000-000000000046}");
        IIDIPersistStreamInit = IIDFromString("{7FD52380-4E07-101B-AE2D-08002B2EC713}");
        IIDIPropertyNotifySink = IIDFromString("{9BFBBC02-EFF1-101A-84ED-00AA00341D07}");
        IIDIProvideClassInfo = IIDFromString("{B196B283-BAB4-101A-B69C-00AA00341D07}");
        IIDIProvideClassInfo2 = IIDFromString("{A6BC3AC0-DBAA-11CE-9DE3-00AA004BB851}");
        IIDIServiceProvider = IIDFromString("{6d5140c1-7436-11ce-8034-00aa006009fa}");
        IIDISpecifyPropertyPages = IIDFromString("{B196B28B-BAB4-101A-B69C-00AA00341D07}");
        IIDIStorage = IIDFromString("{0000000B-0000-0000-C000-000000000046}");
        IIDIStream = IIDFromString("{0000000C-0000-0000-C000-000000000046}");
        IIDIUnknown = IIDFromString("{00000000-0000-0000-C000-000000000046}");
        IIDIViewObject2 = IIDFromString("{00000127-0000-0000-C000-000000000046}");
        CGID_DocHostCommandHandler = IIDFromString("{f38bc242-b950-11d1-8918-00c04fc2c836}");
        CGID_Explorer = IIDFromString("{000214D0-0000-0000-C000-000000000046}");
        IIDIAccessible2 = IIDFromString("{E89F726E-C4F4-4c19-BB19-B647D7FA8478}");
        IIDIAccessibleRelation = IIDFromString("{7CDF86EE-C3DA-496a-BDA4-281B336E1FDC}");
        IIDIAccessibleAction = IIDFromString("{B70D9F59-3B5A-4dba-AB9E-22012F607DF5}");
        IIDIAccessibleComponent = IIDFromString("{1546D4B0-4C98-4bda-89AE-9A64748BDDE4}");
        IIDIAccessibleValue = IIDFromString("{35855B5B-C566-4fd0-A7B1-E65465600394}");
        IIDIAccessibleText = IIDFromString("{24FD2FFB-3AAD-4a08-8335-A3AD89C0FB4B}");
        IIDIAccessibleEditableText = IIDFromString("{A59AA09A-7011-4b65-939D-32B1FB5547E3}");
        IIDIAccessibleHyperlink = IIDFromString("{01C20F2B-3DD2-400f-949F-AD00BDAB1D41}");
        IIDIAccessibleHypertext = IIDFromString("{6B4F8BBF-F1F2-418a-B35E-A195BC4103B9}");
        IIDIAccessibleTable = IIDFromString("{35AD8070-C20C-4fb4-B094-F4F7275DD469}");
        IIDIAccessibleTable2 = IIDFromString("{6167f295-06f0-4cdd-a1fa-02e25153d869}");
        IIDIAccessibleTableCell = IIDFromString("{594116B1-C99F-4847-AD06-0A7A86ECE645}");
        IIDIAccessibleImage = IIDFromString("{FE5ABB3D-615E-4f7b-909F-5F0EDA9E8DDE}");
        IIDIAccessibleApplication = IIDFromString("{D49DED83-5B25-43F4-9B95-93B44595979E}");
        IIDIAccessibleContext = IIDFromString("{77A123E4-5794-44e0-B8BF-DE600C9D29BD}");
        COM.FreeUnusedLibraries = true;
    }
    
    private static GUID IIDFromString(final String s) {
        final int length = s.length();
        final char[] array = new char[length + 1];
        s.getChars(0, length, array, 0);
        final GUID guid = new GUID();
        if (IIDFromString(array, guid) == 0) {
            return guid;
        }
        return null;
    }
    
    public static final native int CLSIDFromProgID(final char[] p0, final GUID p1);
    
    public static final native int CLSIDFromString(final char[] p0, final GUID p1);
    
    public static final native int CoCreateInstance(final GUID p0, final int p1, final int p2, final GUID p3, final int[] p4);
    
    public static final native void CoFreeUnusedLibraries();
    
    public static final native int CoGetClassObject(final GUID p0, final int p1, final int p2, final GUID p3, final int[] p4);
    
    public static final native int CoLockObjectExternal(final int p0, final boolean p1, final boolean p2);
    
    public static final native int DoDragDrop(final int p0, final int p1, final int p2, final int[] p3);
    
    public static final native int GetClassFile(final char[] p0, final GUID p1);
    
    public static final native int IIDFromString(final char[] p0, final GUID p1);
    
    public static final native boolean IsEqualGUID(final GUID p0, final GUID p1);
    
    public static final native void MoveMemory(final int p0, final FORMATETC p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final GUID p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final OLEINPLACEFRAMEINFO p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final STATSTG p1, final int p2);
    
    public static final native void MoveMemory(final int p0, final STGMEDIUM p1, final int p2);
    
    public static final native void MoveMemory(final STGMEDIUM p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final DISPPARAMS p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final FORMATETC p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final GUID p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final STATSTG p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final TYPEATTR p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final RECT p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final FUNCDESC p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final VARDESC p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final VARIANT p0, final int p1, final int p2);
    
    public static final native int OleCreate(final GUID p0, final GUID p1, final int p2, final FORMATETC p3, final int p4, final int p5, final int[] p6);
    
    public static final native int OleCreateFromFile(final GUID p0, final char[] p1, final GUID p2, final int p3, final FORMATETC p4, final int p5, final int p6, final int[] p7);
    
    public static final native int OleCreatePropertyFrame(final int p0, final int p1, final int p2, final char[] p3, final int p4, final int[] p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native int OleDraw(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int OleFlushClipboard();
    
    public static final native int OleGetClipboard(final int[] p0);
    
    public static final native int OleIsCurrentClipboard(final int p0);
    
    public static final native boolean OleIsRunning(final int p0);
    
    public static final native int OleLoad(final int p0, final GUID p1, final int p2, final int[] p3);
    
    public static final native int OleRun(final int p0);
    
    public static final native int OleSave(final int p0, final int p1, final boolean p2);
    
    public static final native int OleSetClipboard(final int p0);
    
    public static final native int OleSetContainedObject(final int p0, final boolean p1);
    
    public static final native int OleSetMenuDescriptor(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int OleTranslateColor(final int p0, final int p1, final int[] p2);
    
    public static final native int ProgIDFromCLSID(final GUID p0, final int[] p1);
    
    public static final native int RegisterDragDrop(final int p0, final int p1);
    
    public static final native void ReleaseStgMedium(final int p0);
    
    public static final native int RevokeDragDrop(final int p0);
    
    public static final native int SHDoDragDrop(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int StgCreateDocfile(final char[] p0, final int p1, final int p2, final int[] p3);
    
    public static final native int StgIsStorageFile(final char[] p0);
    
    public static final native int StgOpenStorage(final char[] p0, final int p1, final int p2, final int p3, final int p4, final int[] p5);
    
    public static final native int StringFromCLSID(final GUID p0, final int[] p1);
    
    public static final native int SysAllocString(final char[] p0);
    
    public static final native void SysFreeString(final int p0);
    
    public static final native int SysStringByteLen(final int p0);
    
    public static final native int SysStringLen(final int p0);
    
    public static final native int VariantChangeType(final int p0, final int p1, final short p2, final short p3);
    
    public static final native int VariantClear(final int p0);
    
    public static final native void VariantInit(final int p0);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final char[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final POINT p4, final int p5);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final POINT p4, final int p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final POINT p3, final int p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final POINT p3, final long p4);
    
    public static final native int VtblCall(final int p0, final int p1, final POINT p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final SHDRAGIMAGE p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final SHDRAGIMAGE p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final long p3, final int p4, final int p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int p4, final int p5, final int p6, final int[] p7);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final long p3, final int p4, final int p5, final int p6, final long[] p7);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final int p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final long p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final DVTARGETDEVICE p4, final SIZE p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final GUID p4, final int p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final GUID p4, final long p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final FORMATETC p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final FORMATETC p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final GUID p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final GUID p3, final int p4, final int p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final GUID p3, final long p4, final long p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final GUID p3, final int p4, final int p5, final DISPPARAMS p6, final int p7, final EXCEPINFO p8, final int[] p9);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final GUID p3, final int p4, final int p5, final DISPPARAMS p6, final long p7, final EXCEPINFO p8, final int[] p9);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final STATSTG p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final STATSTG p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final MSG p2);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final MSG p3, final int p4, final int p5, final int p6, final RECT p7);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final MSG p3, final long p4, final int p5, final long p6, final RECT p7);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final SIZE p3);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final boolean p3);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final boolean p3);
    
    public static final native int VtblCall(final int p0, final int p1, final boolean p2);
    
    public static final native int VtblCall(final int p0, final int p1, final boolean p2, final int p3);
    
    public static final native int VtblCall(final int p0, final int p1, final boolean p2, final long p3);
    
    public static final native int VtblCall(final int p0, final int p1, final CAUUID p2);
    
    public static final native int VtblCall(final int p0, final int p1, final CONTROLINFO p2);
    
    public static final native int VtblCall(final int p0, final int p1, final FORMATETC p2);
    
    public static final native int VtblCall(final int p0, final int p1, final FORMATETC p2, final STGMEDIUM p3);
    
    public static final native int VtblCall(final int p0, final int p1, final FORMATETC p2, final STGMEDIUM p3, final boolean p4);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final int[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final long[] p3);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final GUID p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final GUID p3, final long[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final int p3, final int p4, final int p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final long p3, final int p4, final int p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final int p3, final int p4, final long p5, final long p6);
    
    public static final native int VtblCall(final int p0, final int p1, final GUID p2, final int p3, final OLECMD p4, final OLECMDTEXT p5);
    
    public static final native int VtblCall(final int p0, final int p1, final LICINFO p2);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2, final int p3, final boolean p4);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2, final long p3, final boolean p4);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2, final int p3, final int p4);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2, final long p3, final long p4);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2, final RECT p3);
    
    public static final native int VtblCall(final int p0, final int p1, final RECT p2);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long[] p3, final long[] p4, final int[] p5, final long[] p6);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int[] p3, final int p4, final int[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final long[] p3, final int p4, final int[] p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final DISPPARAMS p5, final int p6, final EXCEPINFO p7, final int p8);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final DISPPARAMS p5, final long p6, final EXCEPINFO p7, final long p8);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int[] p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final char[] p2, final int p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    public static final native int VtblCall(final int p0, final int p1, final long p2, final long p3, final boolean p4);
    
    public static final native int WriteClassStg(final int p0, final GUID p1);
    
    public static final native int AccessibleObjectFromWindow(final int p0, final int p1, final GUID p2, final int[] p3);
    
    public static final native int CreateStdAccessibleObject(final int p0, final int p1, final GUID p2, final int[] p3);
    
    public static final native int LresultFromObject(final GUID p0, final int p1, final int p2);
    
    public static final native int AccessibleChildren(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int VtblCall(final int p0, final long p1, final int p2, final int p3, final int p4, final int p5, final long p6);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native int VtblCall(final int p0, final long p1, final long p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native int VtblCall(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9);
    
    public static final native int VtblCall_VARIANT(final int p0, final int p1, final int p2);
    
    public static final native int VtblCall_VARIANTP(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int VtblCall_IVARIANT(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int VtblCall_IVARIANTP(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int VtblCall_PVARIANTP(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int VtblCall_PPPPVARIANT(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native int get_accChild_CALLBACK(final int p0);
    
    public static final native int get_accName_CALLBACK(final int p0);
    
    public static final native int get_accValue_CALLBACK(final int p0);
    
    public static final native int get_accDescription_CALLBACK(final int p0);
    
    public static final native int get_accRole_CALLBACK(final int p0);
    
    public static final native int get_accState_CALLBACK(final int p0);
    
    public static final native int get_accHelp_CALLBACK(final int p0);
    
    public static final native int get_accHelpTopic_CALLBACK(final int p0);
    
    public static final native int get_accKeyboardShortcut_CALLBACK(final int p0);
    
    public static final native int get_accDefaultAction_CALLBACK(final int p0);
    
    public static final native int accSelect_CALLBACK(final int p0);
    
    public static final native int accLocation_CALLBACK(final int p0);
    
    public static final native int accNavigate_CALLBACK(final int p0);
    
    public static final native int accDoDefaultAction_CALLBACK(final int p0);
    
    public static final native int put_accName_CALLBACK(final int p0);
    
    public static final native int put_accValue_CALLBACK(final int p0);
    
    public static final native int CALLBACK_setCurrentValue(final int p0);
    
    public static final native int CAUUID_sizeof();
    
    public static final native int CONTROLINFO_sizeof();
    
    public static final native int COSERVERINFO_sizeof();
    
    public static final native int DISPPARAMS_sizeof();
    
    public static final native int DVTARGETDEVICE_sizeof();
    
    public static final native int ELEMDESC_sizeof();
    
    public static final native int EXCEPINFO_sizeof();
    
    public static final native int FORMATETC_sizeof();
    
    public static final native int FUNCDESC_sizeof();
    
    public static final native int GUID_sizeof();
    
    public static final native int LICINFO_sizeof();
    
    public static final native int OLECMD_sizeof();
    
    public static final native int OLEINPLACEFRAMEINFO_sizeof();
    
    public static final native int STATSTG_sizeof();
    
    public static final native int STGMEDIUM_sizeof();
    
    public static final native int TYPEATTR_sizeof();
    
    public static final native int TYPEDESC_sizeof();
    
    public static final native int VARDESC_sizeof();
    
    public static final native int VARIANT_sizeof();
}
