// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import java.util.Locale;
import org.eclipse.swt.internal.ole.win32.IEnumVARIANT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.internal.win32.TVITEM;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.IServiceProvider;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Control;
import java.util.Vector;
import org.eclipse.swt.internal.ole.win32.IAccessible;
import org.eclipse.swt.internal.ole.win32.COMObject;

public class Accessible
{
    static final int MAX_RELATION_TYPES = 15;
    static final int TABLE_MODEL_CHANGE_SIZE = 5;
    static final int TEXT_CHANGE_SIZE = 4;
    static final boolean DEBUG = false;
    static final String PROPERTY_USEIA2 = "org.eclipse.swt.accessibility.UseIA2";
    static boolean UseIA2;
    static int UniqueID;
    int refCount;
    int enumIndex;
    COMObject objIAccessible;
    COMObject objIEnumVARIANT;
    COMObject objIServiceProvider;
    COMObject objIAccessible2;
    COMObject objIAccessibleAction;
    COMObject objIAccessibleApplication;
    COMObject objIAccessibleEditableText;
    COMObject objIAccessibleHyperlink;
    COMObject objIAccessibleHypertext;
    COMObject objIAccessibleTable2;
    COMObject objIAccessibleTableCell;
    COMObject objIAccessibleText;
    COMObject objIAccessibleValue;
    IAccessible iaccessible;
    Vector accessibleListeners;
    Vector accessibleControlListeners;
    Vector accessibleTextListeners;
    Vector accessibleActionListeners;
    Vector accessibleEditableTextListeners;
    Vector accessibleHyperlinkListeners;
    Vector accessibleTableListeners;
    Vector accessibleTableCellListeners;
    Vector accessibleTextExtendedListeners;
    Vector accessibleValueListeners;
    Vector accessibleAttributeListeners;
    Relation[] relations;
    Object[] variants;
    Accessible parent;
    Vector children;
    Control control;
    int uniqueID;
    int[] tableChange;
    Object[] textDeleted;
    Object[] textInserted;
    ToolItem item;
    
    static {
        Accessible.UseIA2 = true;
        Accessible.UniqueID = -16;
        final String property = System.getProperty("org.eclipse.swt.accessibility.UseIA2");
        if (property != null && property.equalsIgnoreCase("false")) {
            Accessible.UseIA2 = false;
        }
    }
    
    public Accessible(final Accessible accessible) {
        this.refCount = 0;
        this.enumIndex = 0;
        this.accessibleListeners = new Vector();
        this.accessibleControlListeners = new Vector();
        this.accessibleTextListeners = new Vector();
        this.accessibleActionListeners = new Vector();
        this.accessibleEditableTextListeners = new Vector();
        this.accessibleHyperlinkListeners = new Vector();
        this.accessibleTableListeners = new Vector();
        this.accessibleTableCellListeners = new Vector();
        this.accessibleTextExtendedListeners = new Vector();
        this.accessibleValueListeners = new Vector();
        this.accessibleAttributeListeners = new Vector();
        this.relations = new Relation[15];
        this.children = new Vector();
        this.uniqueID = -1;
        this.parent = checkNull(accessible);
        this.control = accessible.control;
        accessible.children.addElement(this);
        this.AddRef();
    }
    
    protected Accessible() {
        this.refCount = 0;
        this.enumIndex = 0;
        this.accessibleListeners = new Vector();
        this.accessibleControlListeners = new Vector();
        this.accessibleTextListeners = new Vector();
        this.accessibleActionListeners = new Vector();
        this.accessibleEditableTextListeners = new Vector();
        this.accessibleHyperlinkListeners = new Vector();
        this.accessibleTableListeners = new Vector();
        this.accessibleTableCellListeners = new Vector();
        this.accessibleTextExtendedListeners = new Vector();
        this.accessibleValueListeners = new Vector();
        this.accessibleAttributeListeners = new Vector();
        this.relations = new Relation[15];
        this.children = new Vector();
        this.uniqueID = -1;
    }
    
    Accessible(final Control control) {
        this.refCount = 0;
        this.enumIndex = 0;
        this.accessibleListeners = new Vector();
        this.accessibleControlListeners = new Vector();
        this.accessibleTextListeners = new Vector();
        this.accessibleActionListeners = new Vector();
        this.accessibleEditableTextListeners = new Vector();
        this.accessibleHyperlinkListeners = new Vector();
        this.accessibleTableListeners = new Vector();
        this.accessibleTableCellListeners = new Vector();
        this.accessibleTextExtendedListeners = new Vector();
        this.accessibleValueListeners = new Vector();
        this.accessibleAttributeListeners = new Vector();
        this.relations = new Relation[15];
        this.children = new Vector();
        this.uniqueID = -1;
        this.control = control;
        final int[] array = { 0 };
        final int createStdAccessibleObject = COM.CreateStdAccessibleObject(control.handle, -4, COM.IIDIAccessible, array);
        if (array[0] == 0) {
            return;
        }
        if (createStdAccessibleObject != 0) {
            OLE.error(1001, createStdAccessibleObject);
        }
        this.iaccessible = new IAccessible(array[0]);
        this.createIAccessible();
        this.AddRef();
    }
    
    Accessible(final Accessible accessible, final int n) {
        this(accessible);
        this.iaccessible = new IAccessible(n);
    }
    
    static Accessible checkNull(final Accessible accessible) {
        if (accessible == null) {
            SWT.error(4);
        }
        return accessible;
    }
    
    void createIAccessible() {
        this.objIAccessible = new COMObject(new int[] { 2, 0, 0, 1, 3, 5, 8, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 2, 1, 1, 2, 2, 5, 3, 3, 1, 2, 2 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_accParent(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_accChildCount(array[0]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_accChild(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_accName(array[0], array[1]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_accValue(array[0], array[1]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_accDescription(array[0], array[1]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_accRole(array[0], array[1]);
            }
            
            public int method14(final int[] array) {
                return Accessible.this.get_accState(array[0], array[1]);
            }
            
            public int method15(final int[] array) {
                return Accessible.this.get_accHelp(array[0], array[1]);
            }
            
            public int method16(final int[] array) {
                return Accessible.this.get_accHelpTopic(array[0], array[1], array[2]);
            }
            
            public int method17(final int[] array) {
                return Accessible.this.get_accKeyboardShortcut(array[0], array[1]);
            }
            
            public int method18(final int[] array) {
                return Accessible.this.get_accFocus(array[0]);
            }
            
            public int method19(final int[] array) {
                return Accessible.this.get_accSelection(array[0]);
            }
            
            public int method20(final int[] array) {
                return Accessible.this.get_accDefaultAction(array[0], array[1]);
            }
            
            public int method21(final int[] array) {
                return Accessible.this.accSelect(array[0], array[1]);
            }
            
            public int method22(final int[] array) {
                return Accessible.this.accLocation(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method23(final int[] array) {
                return Accessible.this.accNavigate(array[0], array[1], array[2]);
            }
            
            public int method24(final int[] array) {
                return Accessible.this.accHitTest(array[0], array[1], array[2]);
            }
            
            public int method25(final int[] array) {
                return Accessible.this.accDoDefaultAction(array[0]);
            }
            
            public int method26(final int[] array) {
                return Accessible.this.put_accName(array[0], array[1]);
            }
            
            public int method27(final int[] array) {
                return Accessible.this.put_accValue(array[0], array[1]);
            }
        };
        final int ppVtable = this.objIAccessible.ppVtable;
        final int[] array = { 0 };
        OS.MoveMemory(array, ppVtable, OS.PTR_SIZEOF);
        final int[] array2 = new int[28];
        OS.MoveMemory(array2, array[0], OS.PTR_SIZEOF * array2.length);
        array2[9] = COM.get_accChild_CALLBACK(array2[9]);
        array2[10] = COM.get_accName_CALLBACK(array2[10]);
        array2[11] = COM.get_accValue_CALLBACK(array2[11]);
        array2[12] = COM.get_accDescription_CALLBACK(array2[12]);
        array2[13] = COM.get_accRole_CALLBACK(array2[13]);
        array2[14] = COM.get_accState_CALLBACK(array2[14]);
        array2[15] = COM.get_accHelp_CALLBACK(array2[15]);
        array2[16] = COM.get_accHelpTopic_CALLBACK(array2[16]);
        array2[17] = COM.get_accKeyboardShortcut_CALLBACK(array2[17]);
        array2[20] = COM.get_accDefaultAction_CALLBACK(array2[20]);
        array2[21] = COM.accSelect_CALLBACK(array2[21]);
        array2[22] = COM.accLocation_CALLBACK(array2[22]);
        array2[23] = COM.accNavigate_CALLBACK(array2[23]);
        array2[25] = COM.accDoDefaultAction_CALLBACK(array2[25]);
        array2[26] = COM.put_accName_CALLBACK(array2[26]);
        array2[27] = COM.put_accValue_CALLBACK(array2[27]);
        OS.MoveMemory(array[0], array2, OS.PTR_SIZEOF * array2.length);
    }
    
    void createIAccessible2() {
        this.objIAccessible2 = new COMObject(new int[] { 2, 0, 0, 1, 3, 5, 8, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 2, 1, 1, 2, 2, 5, 3, 3, 1, 2, 2, 1, 2, 3, 1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_accParent(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_accChildCount(array[0]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_accChild(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_accName(array[0], array[1]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_accValue(array[0], array[1]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_accDescription(array[0], array[1]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_accRole(array[0], array[1]);
            }
            
            public int method14(final int[] array) {
                return Accessible.this.get_accState(array[0], array[1]);
            }
            
            public int method15(final int[] array) {
                return Accessible.this.get_accHelp(array[0], array[1]);
            }
            
            public int method16(final int[] array) {
                return Accessible.this.get_accHelpTopic(array[0], array[1], array[2]);
            }
            
            public int method17(final int[] array) {
                return Accessible.this.get_accKeyboardShortcut(array[0], array[1]);
            }
            
            public int method18(final int[] array) {
                return Accessible.this.get_accFocus(array[0]);
            }
            
            public int method19(final int[] array) {
                return Accessible.this.get_accSelection(array[0]);
            }
            
            public int method20(final int[] array) {
                return Accessible.this.get_accDefaultAction(array[0], array[1]);
            }
            
            public int method21(final int[] array) {
                return Accessible.this.accSelect(array[0], array[1]);
            }
            
            public int method22(final int[] array) {
                return Accessible.this.accLocation(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method23(final int[] array) {
                return Accessible.this.accNavigate(array[0], array[1], array[2]);
            }
            
            public int method24(final int[] array) {
                return Accessible.this.accHitTest(array[0], array[1], array[2]);
            }
            
            public int method25(final int[] array) {
                return Accessible.this.accDoDefaultAction(array[0]);
            }
            
            public int method26(final int[] array) {
                return Accessible.this.put_accName(array[0], array[1]);
            }
            
            public int method27(final int[] array) {
                return Accessible.this.put_accValue(array[0], array[1]);
            }
            
            public int method28(final int[] array) {
                return Accessible.this.get_nRelations(array[0]);
            }
            
            public int method29(final int[] array) {
                return Accessible.this.get_relation(array[0], array[1]);
            }
            
            public int method30(final int[] array) {
                return Accessible.this.get_relations(array[0], array[1], array[2]);
            }
            
            public int method31(final int[] array) {
                return Accessible.this.get_role(array[0]);
            }
            
            public int method32(final int[] array) {
                return Accessible.this.scrollTo(array[0]);
            }
            
            public int method33(final int[] array) {
                return Accessible.this.scrollToPoint(array[0], array[1], array[2]);
            }
            
            public int method34(final int[] array) {
                return Accessible.this.get_groupPosition(array[0], array[1], array[2]);
            }
            
            public int method35(final int[] array) {
                return Accessible.this.get_states(array[0]);
            }
            
            public int method36(final int[] array) {
                return Accessible.this.get_extendedRole(array[0]);
            }
            
            public int method37(final int[] array) {
                return Accessible.this.get_localizedExtendedRole(array[0]);
            }
            
            public int method38(final int[] array) {
                return Accessible.this.get_nExtendedStates(array[0]);
            }
            
            public int method39(final int[] array) {
                return Accessible.this.get_extendedStates(array[0], array[1], array[2]);
            }
            
            public int method40(final int[] array) {
                return Accessible.this.get_localizedExtendedStates(array[0], array[1], array[2]);
            }
            
            public int method41(final int[] array) {
                return Accessible.this.get_uniqueID(array[0]);
            }
            
            public int method42(final int[] array) {
                return Accessible.this.get_windowHandle(array[0]);
            }
            
            public int method43(final int[] array) {
                return Accessible.this.get_indexInParent(array[0]);
            }
            
            public int method44(final int[] array) {
                return Accessible.this.get_locale(array[0]);
            }
            
            public int method45(final int[] array) {
                return Accessible.this.get_attributes(array[0]);
            }
        };
        final int ppVtable = this.objIAccessible2.ppVtable;
        final int[] array = { 0 };
        OS.MoveMemory(array, ppVtable, OS.PTR_SIZEOF);
        final int[] array2 = new int[28];
        OS.MoveMemory(array2, array[0], OS.PTR_SIZEOF * array2.length);
        array2[9] = COM.get_accChild_CALLBACK(array2[9]);
        array2[10] = COM.get_accName_CALLBACK(array2[10]);
        array2[11] = COM.get_accValue_CALLBACK(array2[11]);
        array2[12] = COM.get_accDescription_CALLBACK(array2[12]);
        array2[13] = COM.get_accRole_CALLBACK(array2[13]);
        array2[14] = COM.get_accState_CALLBACK(array2[14]);
        array2[15] = COM.get_accHelp_CALLBACK(array2[15]);
        array2[16] = COM.get_accHelpTopic_CALLBACK(array2[16]);
        array2[17] = COM.get_accKeyboardShortcut_CALLBACK(array2[17]);
        array2[20] = COM.get_accDefaultAction_CALLBACK(array2[20]);
        array2[21] = COM.accSelect_CALLBACK(array2[21]);
        array2[22] = COM.accLocation_CALLBACK(array2[22]);
        array2[23] = COM.accNavigate_CALLBACK(array2[23]);
        array2[25] = COM.accDoDefaultAction_CALLBACK(array2[25]);
        array2[26] = COM.put_accName_CALLBACK(array2[26]);
        array2[27] = COM.put_accValue_CALLBACK(array2[27]);
        OS.MoveMemory(array[0], array2, OS.PTR_SIZEOF * array2.length);
    }
    
    void createIAccessibleAction() {
        this.objIAccessibleAction = new COMObject(new int[] { 2, 0, 0, 1, 1, 2, 4, 2, 2 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_nActions(array[0]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.doAction(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_description(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_keyBinding(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_name(array[0], array[1]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_localizedName(array[0], array[1]);
            }
        };
    }
    
    void createIAccessibleApplication() {
        this.objIAccessibleApplication = new COMObject(new int[] { 2, 0, 0, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_appName(array[0]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.get_appVersion(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_toolkitName(array[0]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_toolkitVersion(array[0]);
            }
        };
    }
    
    void createIAccessibleEditableText() {
        this.objIAccessibleEditableText = new COMObject(new int[] { 2, 0, 0, 2, 2, 2, 2, 1, 3, 3 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.copyText(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.deleteText(array[0], array[1]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.insertText(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.cutText(array[0], array[1]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.pasteText(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.replaceText(array[0], array[1], array[2]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.setAttributes(array[0], array[1], array[2]);
            }
        };
    }
    
    void createIAccessibleHyperlink() {
        this.objIAccessibleHyperlink = new COMObject(new int[] { 2, 0, 0, 1, 1, 2, 4, 2, 2, 2, 2, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_nActions(array[0]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.doAction(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_description(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_keyBinding(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_name(array[0], array[1]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_localizedName(array[0], array[1]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_anchor(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_anchorTarget(array[0], array[1]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_startIndex(array[0]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_endIndex(array[0]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_valid(array[0]);
            }
        };
    }
    
    void createIAccessibleHypertext() {
        this.objIAccessibleHypertext = new COMObject(new int[] { 2, 0, 0, 2, 4, 1, 6, 1, 4, 3, 3, 5, 5, 5, 1, 1, 3, 1, 3, 5, 1, 1, 1, 2, 2 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.addSelection(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.get_attributes(array[0], array[1], array[2], array[3]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_caretOffset(array[0]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_characterExtents(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_nSelections(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_offsetAtPoint(array[0], array[1], array[2], array[3]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_selection(array[0], array[1], array[2]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_text(array[0], array[1], array[2]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_textBeforeOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_textAfterOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_textAtOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method14(final int[] array) {
                return Accessible.this.removeSelection(array[0]);
            }
            
            public int method15(final int[] array) {
                return Accessible.this.setCaretOffset(array[0]);
            }
            
            public int method16(final int[] array) {
                return Accessible.this.setSelection(array[0], array[1], array[2]);
            }
            
            public int method17(final int[] array) {
                return Accessible.this.get_nCharacters(array[0]);
            }
            
            public int method18(final int[] array) {
                return Accessible.this.scrollSubstringTo(array[0], array[1], array[2]);
            }
            
            public int method19(final int[] array) {
                return Accessible.this.scrollSubstringToPoint(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method20(final int[] array) {
                return Accessible.this.get_newText(array[0]);
            }
            
            public int method21(final int[] array) {
                return Accessible.this.get_oldText(array[0]);
            }
            
            public int method22(final int[] array) {
                return Accessible.this.get_nHyperlinks(array[0]);
            }
            
            public int method23(final int[] array) {
                return Accessible.this.get_hyperlink(array[0], array[1]);
            }
            
            public int method24(final int[] array) {
                return Accessible.this.get_hyperlinkIndex(array[0], array[1]);
            }
        };
    }
    
    void createIAccessibleTable2() {
        this.objIAccessibleTable2 = new COMObject(new int[] { 2, 0, 0, 3, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_cellAt(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.get_caption(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_columnDescription(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_nColumns(array[0]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_nRows(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_nSelectedCells(array[0]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_nSelectedColumns(array[0]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_nSelectedRows(array[0]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_rowDescription(array[0], array[1]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_selectedCells(array[0], array[1]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_selectedColumns(array[0], array[1]);
            }
            
            public int method14(final int[] array) {
                return Accessible.this.get_selectedRows(array[0], array[1]);
            }
            
            public int method15(final int[] array) {
                return Accessible.this.get_summary(array[0]);
            }
            
            public int method16(final int[] array) {
                return Accessible.this.get_isColumnSelected(array[0], array[1]);
            }
            
            public int method17(final int[] array) {
                return Accessible.this.get_isRowSelected(array[0], array[1]);
            }
            
            public int method18(final int[] array) {
                return Accessible.this.selectRow(array[0]);
            }
            
            public int method19(final int[] array) {
                return Accessible.this.selectColumn(array[0]);
            }
            
            public int method20(final int[] array) {
                return Accessible.this.unselectRow(array[0]);
            }
            
            public int method21(final int[] array) {
                return Accessible.this.unselectColumn(array[0]);
            }
            
            public int method22(final int[] array) {
                return Accessible.this.get_modelChange(array[0]);
            }
        };
    }
    
    void createIAccessibleTableCell() {
        this.objIAccessibleTableCell = new COMObject(new int[] { 2, 0, 0, 1, 2, 1, 1, 2, 1, 1, 5, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_columnExtent(array[0]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.get_columnHeaderCells(array[0], array[1]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_columnIndex(array[0]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_rowExtent(array[0]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_rowHeaderCells(array[0], array[1]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_rowIndex(array[0]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_isSelected(array[0]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_rowColumnExtents(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_table(array[0]);
            }
        };
    }
    
    void createIAccessibleText() {
        this.objIAccessibleText = new COMObject(new int[] { 2, 0, 0, 2, 4, 1, 6, 1, 4, 3, 3, 5, 5, 5, 1, 1, 3, 1, 3, 5, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.addSelection(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.get_attributes(array[0], array[1], array[2], array[3]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_caretOffset(array[0]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_characterExtents(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method7(final int[] array) {
                return Accessible.this.get_nSelections(array[0]);
            }
            
            public int method8(final int[] array) {
                return Accessible.this.get_offsetAtPoint(array[0], array[1], array[2], array[3]);
            }
            
            public int method9(final int[] array) {
                return Accessible.this.get_selection(array[0], array[1], array[2]);
            }
            
            public int method10(final int[] array) {
                return Accessible.this.get_text(array[0], array[1], array[2]);
            }
            
            public int method11(final int[] array) {
                return Accessible.this.get_textBeforeOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method12(final int[] array) {
                return Accessible.this.get_textAfterOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method13(final int[] array) {
                return Accessible.this.get_textAtOffset(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method14(final int[] array) {
                return Accessible.this.removeSelection(array[0]);
            }
            
            public int method15(final int[] array) {
                return Accessible.this.setCaretOffset(array[0]);
            }
            
            public int method16(final int[] array) {
                return Accessible.this.setSelection(array[0], array[1], array[2]);
            }
            
            public int method17(final int[] array) {
                return Accessible.this.get_nCharacters(array[0]);
            }
            
            public int method18(final int[] array) {
                return Accessible.this.scrollSubstringTo(array[0], array[1], array[2]);
            }
            
            public int method19(final int[] array) {
                return Accessible.this.scrollSubstringToPoint(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method20(final int[] array) {
                return Accessible.this.get_newText(array[0]);
            }
            
            public int method21(final int[] array) {
                return Accessible.this.get_oldText(array[0]);
            }
        };
    }
    
    void createIAccessibleValue() {
        this.objIAccessibleValue = new COMObject(new int[] { 2, 0, 0, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.get_currentValue(array[0]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.setCurrentValue(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.get_maximumValue(array[0]);
            }
            
            public int method6(final int[] array) {
                return Accessible.this.get_minimumValue(array[0]);
            }
        };
        final int ppVtable = this.objIAccessibleValue.ppVtable;
        final int[] array = { 0 };
        OS.MoveMemory(array, ppVtable, OS.PTR_SIZEOF);
        final int[] array2 = new int[7];
        OS.MoveMemory(array2, array[0], OS.PTR_SIZEOF * array2.length);
        array2[4] = COM.CALLBACK_setCurrentValue(array2[4]);
        OS.MoveMemory(array[0], array2, OS.PTR_SIZEOF * array2.length);
    }
    
    void createIEnumVARIANT() {
        this.objIEnumVARIANT = new COMObject(new int[] { 2, 0, 0, 3, 1, 0, 1 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.Next(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return Accessible.this.Skip(array[0]);
            }
            
            public int method5(final int[] array) {
                return Accessible.this.Reset();
            }
            
            public int method6(final int[] array) {
                return Accessible.this.Clone(array[0]);
            }
        };
    }
    
    void createIServiceProvider() {
        this.objIServiceProvider = new COMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return Accessible.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Accessible.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Accessible.this.Release();
            }
            
            public int method3(final int[] array) {
                return Accessible.this.QueryService(array[0], array[1], array[2]);
            }
        };
    }
    
    public static Accessible internal_new_Accessible(final Control control) {
        return new Accessible(control);
    }
    
    public void addAccessibleListener(final AccessibleListener accessibleListener) {
        this.checkWidget();
        if (accessibleListener == null) {
            SWT.error(4);
        }
        this.accessibleListeners.addElement(accessibleListener);
    }
    
    public void addAccessibleControlListener(final AccessibleControlListener accessibleControlListener) {
        this.checkWidget();
        if (accessibleControlListener == null) {
            SWT.error(4);
        }
        this.accessibleControlListeners.addElement(accessibleControlListener);
    }
    
    public void addAccessibleTextListener(final AccessibleTextListener accessibleTextListener) {
        this.checkWidget();
        if (accessibleTextListener == null) {
            SWT.error(4);
        }
        if (accessibleTextListener instanceof AccessibleTextExtendedListener) {
            this.accessibleTextExtendedListeners.addElement(accessibleTextListener);
        }
        else {
            this.accessibleTextListeners.addElement(accessibleTextListener);
        }
    }
    
    public void addAccessibleActionListener(final AccessibleActionListener accessibleActionListener) {
        this.checkWidget();
        if (accessibleActionListener == null) {
            SWT.error(4);
        }
        this.accessibleActionListeners.addElement(accessibleActionListener);
    }
    
    public void addAccessibleEditableTextListener(final AccessibleEditableTextListener accessibleEditableTextListener) {
        this.checkWidget();
        if (accessibleEditableTextListener == null) {
            SWT.error(4);
        }
        this.accessibleEditableTextListeners.addElement(accessibleEditableTextListener);
    }
    
    public void addAccessibleHyperlinkListener(final AccessibleHyperlinkListener accessibleHyperlinkListener) {
        this.checkWidget();
        if (accessibleHyperlinkListener == null) {
            SWT.error(4);
        }
        this.accessibleHyperlinkListeners.addElement(accessibleHyperlinkListener);
    }
    
    public void addAccessibleTableListener(final AccessibleTableListener accessibleTableListener) {
        this.checkWidget();
        if (accessibleTableListener == null) {
            SWT.error(4);
        }
        this.accessibleTableListeners.addElement(accessibleTableListener);
    }
    
    public void addAccessibleTableCellListener(final AccessibleTableCellListener accessibleTableCellListener) {
        this.checkWidget();
        if (accessibleTableCellListener == null) {
            SWT.error(4);
        }
        this.accessibleTableCellListeners.addElement(accessibleTableCellListener);
    }
    
    public void addAccessibleValueListener(final AccessibleValueListener accessibleValueListener) {
        this.checkWidget();
        if (accessibleValueListener == null) {
            SWT.error(4);
        }
        this.accessibleValueListeners.addElement(accessibleValueListener);
    }
    
    public void addAccessibleAttributeListener(final AccessibleAttributeListener accessibleAttributeListener) {
        this.checkWidget();
        if (accessibleAttributeListener == null) {
            SWT.error(4);
        }
        this.accessibleAttributeListeners.addElement(accessibleAttributeListener);
    }
    
    public void addRelation(final int n, final Accessible accessible) {
        this.checkWidget();
        if (this.relations[n] == null) {
            this.relations[n] = new Relation(this, n);
        }
        this.relations[n].addTarget(accessible);
    }
    
    public void dispose() {
        if (this.parent == null) {
            return;
        }
        this.Release();
        this.parent.children.removeElement(this);
        this.parent = null;
    }
    
    int getAddress() {
        if (this.objIAccessible == null) {
            this.createIAccessible();
        }
        return this.objIAccessible.getAddress();
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public void internal_dispose_Accessible() {
        if (this.iaccessible != null) {
            this.iaccessible.Release();
        }
        this.iaccessible = null;
        this.Release();
        for (int i = 0; i < this.children.size(); ++i) {
            ((Accessible)this.children.elementAt(i)).dispose();
        }
    }
    
    public int internal_WM_GETOBJECT(final int n, final int n2) {
        if (this.objIAccessible == null) {
            return 0;
        }
        if (n2 == -4) {
            return COM.LresultFromObject(COM.IIDIAccessible, n, this.objIAccessible.getAddress());
        }
        return 0;
    }
    
    public void removeAccessibleListener(final AccessibleListener accessibleListener) {
        this.checkWidget();
        if (accessibleListener == null) {
            SWT.error(4);
        }
        this.accessibleListeners.removeElement(accessibleListener);
    }
    
    public void removeAccessibleControlListener(final AccessibleControlListener accessibleControlListener) {
        this.checkWidget();
        if (accessibleControlListener == null) {
            SWT.error(4);
        }
        this.accessibleControlListeners.removeElement(accessibleControlListener);
    }
    
    public void removeAccessibleTextListener(final AccessibleTextListener accessibleTextListener) {
        this.checkWidget();
        if (accessibleTextListener == null) {
            SWT.error(4);
        }
        if (accessibleTextListener instanceof AccessibleTextExtendedListener) {
            this.accessibleTextExtendedListeners.removeElement(accessibleTextListener);
        }
        else {
            this.accessibleTextListeners.removeElement(accessibleTextListener);
        }
    }
    
    public void removeAccessibleActionListener(final AccessibleActionListener accessibleActionListener) {
        this.checkWidget();
        if (accessibleActionListener == null) {
            SWT.error(4);
        }
        this.accessibleActionListeners.removeElement(accessibleActionListener);
    }
    
    public void removeAccessibleEditableTextListener(final AccessibleEditableTextListener accessibleEditableTextListener) {
        this.checkWidget();
        if (accessibleEditableTextListener == null) {
            SWT.error(4);
        }
        this.accessibleEditableTextListeners.removeElement(accessibleEditableTextListener);
    }
    
    public void removeAccessibleHyperlinkListener(final AccessibleHyperlinkListener accessibleHyperlinkListener) {
        this.checkWidget();
        if (accessibleHyperlinkListener == null) {
            SWT.error(4);
        }
        this.accessibleHyperlinkListeners.removeElement(accessibleHyperlinkListener);
    }
    
    public void removeAccessibleTableListener(final AccessibleTableListener accessibleTableListener) {
        this.checkWidget();
        if (accessibleTableListener == null) {
            SWT.error(4);
        }
        this.accessibleTableListeners.removeElement(accessibleTableListener);
    }
    
    public void removeAccessibleTableCellListener(final AccessibleTableCellListener accessibleTableCellListener) {
        this.checkWidget();
        if (accessibleTableCellListener == null) {
            SWT.error(4);
        }
        this.accessibleTableCellListeners.removeElement(accessibleTableCellListener);
    }
    
    public void removeAccessibleValueListener(final AccessibleValueListener accessibleValueListener) {
        this.checkWidget();
        if (accessibleValueListener == null) {
            SWT.error(4);
        }
        this.accessibleValueListeners.removeElement(accessibleValueListener);
    }
    
    public void removeAccessibleAttributeListener(final AccessibleAttributeListener accessibleAttributeListener) {
        this.checkWidget();
        if (accessibleAttributeListener == null) {
            SWT.error(4);
        }
        this.accessibleAttributeListeners.removeElement(accessibleAttributeListener);
    }
    
    public void removeRelation(final int n, final Accessible accessible) {
        this.checkWidget();
        final Relation relation = this.relations[n];
        if (relation != null) {
            relation.removeTarget(accessible);
            if (!relation.hasTargets()) {
                this.relations[n].Release();
                this.relations[n] = null;
            }
        }
    }
    
    public void sendEvent(final int n, final Object o) {
        this.checkWidget();
        if (!Accessible.UseIA2) {
            return;
        }
        switch (n) {
            case 518: {
                if (!(o instanceof int[])) {
                    break;
                }
                if (((int[])o).length != 5) {
                    break;
                }
                this.tableChange = (int[])o;
                OS.NotifyWinEvent(278, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 524: {
                if (!(o instanceof Object[])) {
                    break;
                }
                if (((Object[])o).length != 4) {
                    break;
                }
                switch ((int)((Object[])o)[0]) {
                    case 1: {
                        this.textDeleted = (Object[])o;
                        OS.NotifyWinEvent(287, this.control.handle, -4, this.eventChildID());
                        break;
                    }
                    case 0: {
                        this.textInserted = (Object[])o;
                        OS.NotifyWinEvent(286, this.control.handle, -4, this.eventChildID());
                        break;
                    }
                }
                break;
            }
            case 268: {
                if (!(o instanceof Integer)) {
                    break;
                }
                OS.NotifyWinEvent(268, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32782: {
                OS.NotifyWinEvent(32782, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32778: {
                OS.NotifyWinEvent(32778, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32777: {
                OS.NotifyWinEvent(32777, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32788: {
                OS.NotifyWinEvent(32788, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32779: {
                OS.NotifyWinEvent(32779, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32780: {
                OS.NotifyWinEvent(32780, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 32781: {
                OS.NotifyWinEvent(32781, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 261: {
                OS.NotifyWinEvent(261, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 262: {
                OS.NotifyWinEvent(262, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 263: {
                OS.NotifyWinEvent(263, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 273: {
                OS.NotifyWinEvent(273, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 274: {
                OS.NotifyWinEvent(274, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 256: {
                OS.NotifyWinEvent(257, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 269: {
                OS.NotifyWinEvent(269, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 264: {
                OS.NotifyWinEvent(264, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 265: {
                OS.NotifyWinEvent(265, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 266: {
                OS.NotifyWinEvent(266, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 267: {
                OS.NotifyWinEvent(267, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 271: {
                OS.NotifyWinEvent(271, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 512: {
                OS.NotifyWinEvent(272, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 515: {
                OS.NotifyWinEvent(275, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 516: {
                OS.NotifyWinEvent(276, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 517: {
                OS.NotifyWinEvent(277, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 519: {
                OS.NotifyWinEvent(279, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 520: {
                OS.NotifyWinEvent(280, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 521: {
                OS.NotifyWinEvent(281, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 522: {
                OS.NotifyWinEvent(282, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 283: {
                OS.NotifyWinEvent(283, this.control.handle, -4, this.eventChildID());
                break;
            }
            case 285: {
                OS.NotifyWinEvent(285, this.control.handle, -4, this.eventChildID());
                break;
            }
        }
    }
    
    public void selectionChanged() {
        this.checkWidget();
        OS.NotifyWinEvent(32777, this.control.handle, -4, this.eventChildID());
    }
    
    public void setFocus(final int n) {
        this.checkWidget();
        OS.NotifyWinEvent(32773, this.control.handle, -4, (n == -1) ? this.eventChildID() : this.childIDToOs(n));
    }
    
    public void textCaretMoved(final int n) {
        this.checkWidget();
        OS.NotifyWinEvent(32779, this.control.handle, -8, this.eventChildID());
        if (!Accessible.UseIA2) {
            return;
        }
        OS.NotifyWinEvent(283, this.control.handle, -4, this.eventChildID());
    }
    
    public void textChanged(final int n, final int start, final int n2) {
        this.checkWidget();
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = start;
        accessibleTextEvent.end = start + n2;
        accessibleTextEvent.count = 0;
        accessibleTextEvent.type = 5;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getText(accessibleTextEvent);
        }
        if (accessibleTextEvent.result != null) {
            this.sendEvent(524, new Object[] { new Integer(n), new Integer(start), new Integer(start + n2), accessibleTextEvent.result });
            return;
        }
        OS.NotifyWinEvent(32782, this.control.handle, -4, this.eventChildID());
    }
    
    public void textSelectionChanged() {
        this.checkWidget();
        OS.NotifyWinEvent(32782, this.control.handle, -4, this.eventChildID());
    }
    
    int QueryInterface(final int n, final int n2) {
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDispatch) || COM.IsEqualGUID(guid, COM.IIDIAccessible)) {
            if (this.objIAccessible == null) {
                this.createIAccessible();
            }
            OS.MoveMemory(n2, new int[] { this.objIAccessible.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIEnumVARIANT)) {
            if (this.objIEnumVARIANT == null) {
                this.createIEnumVARIANT();
            }
            OS.MoveMemory(n2, new int[] { this.objIEnumVARIANT.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return this.enumIndex = 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIServiceProvider)) {
            if (!Accessible.UseIA2) {
                return -2147467262;
            }
            if (this.accessibleActionListeners.size() > 0 || this.accessibleAttributeListeners.size() > 0 || this.accessibleHyperlinkListeners.size() > 0 || this.accessibleTableListeners.size() > 0 || this.accessibleTableCellListeners.size() > 0 || this.accessibleTextExtendedListeners.size() > 0 || this.accessibleValueListeners.size() > 0 || this.getRelationCount() > 0) {
                if (this.objIServiceProvider == null) {
                    this.createIServiceProvider();
                }
                OS.MoveMemory(n2, new int[] { this.objIServiceProvider.getAddress() }, OS.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else {
            final int queryAccessible2Interfaces = this.queryAccessible2Interfaces(guid, n2);
            if (queryAccessible2Interfaces != 1) {
                return queryAccessible2Interfaces;
            }
            if (this.iaccessible != null) {
                final int[] array = { 0 };
                final int queryInterface = this.iaccessible.QueryInterface(guid, array);
                OS.MoveMemory(n2, array, OS.PTR_SIZEOF);
                return queryInterface;
            }
            return -2147467262;
        }
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            if (this.objIAccessible != null) {
                this.objIAccessible.dispose();
            }
            this.objIAccessible = null;
            if (this.objIEnumVARIANT != null) {
                this.objIEnumVARIANT.dispose();
            }
            this.objIEnumVARIANT = null;
            if (this.objIServiceProvider != null) {
                this.objIServiceProvider.dispose();
            }
            this.objIServiceProvider = null;
            if (this.objIAccessible2 != null) {
                this.objIAccessible2.dispose();
            }
            this.objIAccessible2 = null;
            if (this.objIAccessibleAction != null) {
                this.objIAccessibleAction.dispose();
            }
            this.objIAccessibleAction = null;
            if (this.objIAccessibleApplication != null) {
                this.objIAccessibleApplication.dispose();
            }
            this.objIAccessibleApplication = null;
            if (this.objIAccessibleEditableText != null) {
                this.objIAccessibleEditableText.dispose();
            }
            this.objIAccessibleEditableText = null;
            if (this.objIAccessibleHyperlink != null) {
                this.objIAccessibleHyperlink.dispose();
            }
            this.objIAccessibleHyperlink = null;
            if (this.objIAccessibleHypertext != null) {
                this.objIAccessibleHypertext.dispose();
            }
            this.objIAccessibleHypertext = null;
            if (this.objIAccessibleTable2 != null) {
                this.objIAccessibleTable2.dispose();
            }
            this.objIAccessibleTable2 = null;
            if (this.objIAccessibleTableCell != null) {
                this.objIAccessibleTableCell.dispose();
            }
            this.objIAccessibleTableCell = null;
            if (this.objIAccessibleText != null) {
                this.objIAccessibleText.dispose();
            }
            this.objIAccessibleText = null;
            if (this.objIAccessibleValue != null) {
                this.objIAccessibleValue.dispose();
            }
            this.objIAccessibleValue = null;
            for (int i = 0; i < this.relations.length; ++i) {
                if (this.relations[i] != null) {
                    this.relations[i].Release();
                }
            }
        }
        return this.refCount;
    }
    
    int QueryService(final int n, final int n2, final int n3) {
        OS.MoveMemory(n3, new int[1], OS.PTR_SIZEOF);
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        final GUID guid2 = new GUID();
        COM.MoveMemory(guid2, n2, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIAccessible)) {
            if (COM.IsEqualGUID(guid2, COM.IIDIUnknown) || (COM.IsEqualGUID(guid2, COM.IIDIDispatch) | COM.IsEqualGUID(guid2, COM.IIDIAccessible))) {
                if (this.objIAccessible == null) {
                    this.createIAccessible();
                }
                OS.MoveMemory(n3, new int[] { this.objIAccessible.getAddress() }, OS.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            final int queryAccessible2Interfaces = this.queryAccessible2Interfaces(guid2, n3);
            if (queryAccessible2Interfaces != 1) {
                return queryAccessible2Interfaces;
            }
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAccessible2)) {
            final int queryAccessible2Interfaces2 = this.queryAccessible2Interfaces(guid2, n3);
            if (queryAccessible2Interfaces2 != 1) {
                return queryAccessible2Interfaces2;
            }
        }
        if (this.iaccessible != null) {
            final int[] array = { 0 };
            if (this.iaccessible.QueryInterface(COM.IIDIServiceProvider, array) == 0) {
                final IServiceProvider serviceProvider = new IServiceProvider(array[0]);
                final int[] array2 = { 0 };
                final int queryService = serviceProvider.QueryService(guid, guid2, array2);
                OS.MoveMemory(n3, array2, OS.PTR_SIZEOF);
                return queryService;
            }
        }
        return -2147467262;
    }
    
    int queryAccessible2Interfaces(final GUID guid, final int n) {
        if (COM.IsEqualGUID(guid, COM.IIDIAccessible2)) {
            if (this.accessibleActionListeners.size() > 0 || this.accessibleAttributeListeners.size() > 0 || this.accessibleHyperlinkListeners.size() > 0 || this.accessibleTableListeners.size() > 0 || this.accessibleTableCellListeners.size() > 0 || this.accessibleTextExtendedListeners.size() > 0 || this.accessibleValueListeners.size() > 0 || this.getRelationCount() > 0) {
                if (this.objIAccessible2 == null) {
                    this.createIAccessible2();
                }
                OS.MoveMemory(n, new int[] { this.objIAccessible2.getAddress() }, OS.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleAction)) {
            if (this.accessibleActionListeners.size() > 0) {
                if (this.objIAccessibleAction == null) {
                    this.createIAccessibleAction();
                }
                OS.MoveMemory(n, new int[] { this.objIAccessibleAction.getAddress() }, OS.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            return -2147467262;
        }
        else {
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleApplication)) {
                if (this.objIAccessibleApplication == null) {
                    this.createIAccessibleApplication();
                }
                OS.MoveMemory(n, new int[] { this.objIAccessibleApplication.getAddress() }, OS.PTR_SIZEOF);
                this.AddRef();
                return 0;
            }
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleComponent)) {
                return -2147467262;
            }
            if (COM.IsEqualGUID(guid, COM.IIDIAccessibleEditableText)) {
                if (this.accessibleEditableTextListeners.size() > 0) {
                    if (this.objIAccessibleEditableText == null) {
                        this.createIAccessibleEditableText();
                    }
                    OS.MoveMemory(n, new int[] { this.objIAccessibleEditableText.getAddress() }, OS.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleHyperlink)) {
                if (this.accessibleHyperlinkListeners.size() > 0) {
                    if (this.objIAccessibleHyperlink == null) {
                        this.createIAccessibleHyperlink();
                    }
                    OS.MoveMemory(n, new int[] { this.objIAccessibleHyperlink.getAddress() }, OS.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleHypertext)) {
                if (this.accessibleTextExtendedListeners.size() > 0) {
                    if (this.objIAccessibleHypertext == null) {
                        this.createIAccessibleHypertext();
                    }
                    OS.MoveMemory(n, new int[] { this.objIAccessibleHypertext.getAddress() }, OS.PTR_SIZEOF);
                    this.AddRef();
                    return 0;
                }
                return -2147467262;
            }
            else {
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleImage)) {
                    return -2147467262;
                }
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTable)) {
                    return -2147467262;
                }
                if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTable2)) {
                    if (this.accessibleTableListeners.size() > 0) {
                        if (this.objIAccessibleTable2 == null) {
                            this.createIAccessibleTable2();
                        }
                        OS.MoveMemory(n, new int[] { this.objIAccessibleTable2.getAddress() }, OS.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleTableCell)) {
                    if (this.accessibleTableCellListeners.size() > 0) {
                        if (this.objIAccessibleTableCell == null) {
                            this.createIAccessibleTableCell();
                        }
                        OS.MoveMemory(n, new int[] { this.objIAccessibleTableCell.getAddress() }, OS.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else if (COM.IsEqualGUID(guid, COM.IIDIAccessibleText)) {
                    if (this.accessibleTextExtendedListeners.size() > 0) {
                        if (this.objIAccessibleText == null) {
                            this.createIAccessibleText();
                        }
                        OS.MoveMemory(n, new int[] { this.objIAccessibleText.getAddress() }, OS.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
                else {
                    if (!COM.IsEqualGUID(guid, COM.IIDIAccessibleValue)) {
                        return 1;
                    }
                    if (this.accessibleValueListeners.size() > 0) {
                        if (this.objIAccessibleValue == null) {
                            this.createIAccessibleValue();
                        }
                        OS.MoveMemory(n, new int[] { this.objIAccessibleValue.getAddress() }, OS.PTR_SIZEOF);
                        this.AddRef();
                        return 0;
                    }
                    return -2147467262;
                }
            }
        }
    }
    
    int accDoDefaultAction(final int n) {
        if (this.accessibleActionListeners.size() > 0) {
            final VARIANT variant = this.getVARIANT(n);
            if (variant.vt != 3) {
                return -2147024809;
            }
            if (variant.lVal == 0) {
                return this.doAction(0);
            }
        }
        int accDoDefaultAction = -2147352573;
        if (this.iaccessible != null) {
            accDoDefaultAction = this.iaccessible.accDoDefaultAction(n);
            if (accDoDefaultAction == -2147024809) {
                accDoDefaultAction = -2147352573;
            }
        }
        return accDoDefaultAction;
    }
    
    int accHitTest(final int x, final int y, final int n) {
        int lVal = -2;
        int lVal2 = 0;
        if (this.iaccessible != null) {
            final int accHitTest = this.iaccessible.accHitTest(x, y, n);
            if (accHitTest == 0) {
                final VARIANT variant = this.getVARIANT(n);
                if (variant.vt == 3) {
                    lVal = variant.lVal;
                }
                else if (variant.vt == 9) {
                    lVal2 = variant.lVal;
                }
            }
            if (this.accessibleControlListeners.size() == 0) {
                return accHitTest;
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = ((lVal == -2) ? -2 : this.osToChildID(lVal));
        accessibleControlEvent.x = x;
        accessibleControlEvent.y = y;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getChildAtPoint(accessibleControlEvent);
        }
        final Accessible accessible = accessibleControlEvent.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(n, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = accessibleControlEvent.childID;
        if (childID != -2) {
            this.setIntVARIANT(n, (short)3, this.childIDToOs(childID));
            return 0;
        }
        if (lVal2 != 0) {
            return 0;
        }
        this.setIntVARIANT(n, (short)0, 0);
        return 1;
    }
    
    int accLocation(final int n, final int n2, final int n3, final int n4, final int n5) {
        final VARIANT variant = this.getVARIANT(n5);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        if (this.iaccessible != null) {
            int accLocation = this.iaccessible.accLocation(n, n2, n3, n4, n5);
            if (accLocation == -2147024809) {
                accLocation = -2147352573;
            }
            if (this.accessibleControlListeners.size() == 0) {
                return accLocation;
            }
            if (accLocation == 0) {
                final int[] array = { 0 };
                final int[] array2 = { 0 };
                final int[] array3 = { 0 };
                final int[] array4 = { 0 };
                OS.MoveMemory(array, n, 4);
                OS.MoveMemory(array2, n2, 4);
                OS.MoveMemory(array3, n3, 4);
                OS.MoveMemory(array4, n4, 4);
                x = array[0];
                y = array2[0];
                width = array3[0];
                height = array4[0];
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = this.osToChildID(variant.lVal);
        accessibleControlEvent.x = x;
        accessibleControlEvent.y = y;
        accessibleControlEvent.width = width;
        accessibleControlEvent.height = height;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getLocation(accessibleControlEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleControlEvent.x }, 4);
        OS.MoveMemory(n2, new int[] { accessibleControlEvent.y }, 4);
        OS.MoveMemory(n3, new int[] { accessibleControlEvent.width }, 4);
        OS.MoveMemory(n4, new int[] { accessibleControlEvent.height }, 4);
        return 0;
    }
    
    int accNavigate(final int n, final int n2, final int n3) {
        int accNavigate = -2147352573;
        if (this.iaccessible != null) {
            accNavigate = this.iaccessible.accNavigate(n, n2, n3);
            if (accNavigate == -2147024809) {
                accNavigate = -2147352573;
            }
        }
        return accNavigate;
    }
    
    int accSelect(final int n, final int n2) {
        int accSelect = -2147352573;
        if (this.iaccessible != null) {
            accSelect = this.iaccessible.accSelect(n, n2);
            if (accSelect == -2147024809) {
                accSelect = -2147352573;
            }
        }
        return accSelect;
    }
    
    int get_accChild(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        if (variant.lVal == 0) {
            this.AddRef();
            OS.MoveMemory(n2, new int[] { this.getAddress() }, OS.PTR_SIZEOF);
            return 0;
        }
        final int osToChildID = this.osToChildID(variant.lVal);
        int get_accChild = 1;
        Accessible accessible = null;
        if (this.iaccessible != null) {
            get_accChild = this.iaccessible.get_accChild(n, n2);
            if (get_accChild == -2147024809) {
                get_accChild = 1;
            }
            if (get_accChild == 0 && this.control instanceof ToolBar) {
                final ToolItem item = ((ToolBar)this.control).getItem(osToChildID);
                if (item != null && (item.getStyle() & 0x4) != 0x0) {
                    final int[] array = { 0 };
                    OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                    boolean b = false;
                    for (int i = 0; i < this.children.size(); ++i) {
                        final Accessible accessible2 = this.children.elementAt(i);
                        if (accessible2.item == item) {
                            accessible2.dispose();
                            accessible2.item = null;
                            b = true;
                            break;
                        }
                    }
                    accessible = new Accessible(this, array[0]);
                    accessible.item = item;
                    if (!b) {
                        item.addListener(12, new Listener() {
                            public void handleEvent(final Event event) {
                                for (int i = 0; i < Accessible.this.children.size(); ++i) {
                                    final Accessible accessible = Accessible.this.children.elementAt(i);
                                    if (accessible.item == item) {
                                        accessible.dispose();
                                    }
                                }
                            }
                        });
                    }
                    accessible.addAccessibleListener(new AccessibleAdapter() {
                        public void getName(final AccessibleEvent accessibleEvent) {
                            if (accessibleEvent.childID == -1) {
                                final AccessibleEvent accessibleEvent2 = new AccessibleEvent(Accessible.this);
                                accessibleEvent2.childID = osToChildID;
                                for (int i = 0; i < Accessible.this.accessibleListeners.size(); ++i) {
                                    ((AccessibleListener)Accessible.this.accessibleListeners.elementAt(i)).getName(accessibleEvent2);
                                }
                                accessibleEvent.result = accessibleEvent2.result;
                            }
                        }
                    });
                }
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = osToChildID;
        for (int j = 0; j < this.accessibleControlListeners.size(); ++j) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(j)).getChild(accessibleControlEvent);
        }
        Accessible accessible3 = accessibleControlEvent.accessible;
        if (accessible3 == null) {
            accessible3 = accessible;
        }
        if (accessible3 != null) {
            accessible3.AddRef();
            OS.MoveMemory(n2, new int[] { accessible3.getAddress() }, OS.PTR_SIZEOF);
            return 0;
        }
        return get_accChild;
    }
    
    int get_accChildCount(final int n) {
        int detail = 0;
        if (this.iaccessible != null) {
            final int get_accChildCount = this.iaccessible.get_accChildCount(n);
            if (get_accChildCount == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n, 4);
                detail = array[0];
            }
            if (this.accessibleControlListeners.size() == 0) {
                return get_accChildCount;
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = -1;
        accessibleControlEvent.detail = detail;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getChildCount(accessibleControlEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleControlEvent.detail }, 4);
        return 0;
    }
    
    int get_accDefaultAction(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int n3 = -2147352573;
        String result = null;
        if (this.iaccessible != null) {
            n3 = this.iaccessible.get_accDefaultAction(n, n2);
            if (n3 == -2147024809) {
                n3 = 1;
            }
            if (this.accessibleControlListeners.size() == 0) {
                return n3;
            }
            if (n3 == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = this.osToChildID(variant.lVal);
        accessibleControlEvent.result = result;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getDefaultAction(accessibleControlEvent);
        }
        if ((accessibleControlEvent.result == null || accessibleControlEvent.result.length() == 0) && variant.lVal == 0) {
            n3 = this.get_name(0, n2);
        }
        if (accessibleControlEvent.result == null) {
            return n3;
        }
        if (accessibleControlEvent.result.length() == 0) {
            return 1;
        }
        this.setString(n2, accessibleControlEvent.result);
        return 0;
    }
    
    int get_accDescription(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int get_accDescription = -2147352573;
        String result = null;
        if (this.iaccessible != null) {
            get_accDescription = this.iaccessible.get_accDescription(n, n2);
            if (get_accDescription == -2147024809) {
                get_accDescription = 1;
            }
            if (this.accessibleListeners.size() == 0 && !(this.control instanceof Tree)) {
                return get_accDescription;
            }
            if (get_accDescription == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
        }
        final AccessibleEvent accessibleEvent = new AccessibleEvent(this);
        accessibleEvent.childID = this.osToChildID(variant.lVal);
        accessibleEvent.result = result;
        if (variant.lVal != 0 && this.control instanceof Tree) {
            final Tree tree = (Tree)this.control;
            final int columnCount = tree.getColumnCount();
            if (columnCount > 1) {
                final int handle = this.control.handle;
                int n3;
                if (OS.COMCTL32_MAJOR >= 6) {
                    n3 = OS.SendMessage(handle, 4394, variant.lVal, 0);
                }
                else {
                    n3 = variant.lVal;
                }
                final Widget widget = tree.getDisplay().findWidget(handle, n3);
                accessibleEvent.result = "";
                if (widget != null && widget instanceof TreeItem) {
                    final TreeItem treeItem = (TreeItem)widget;
                    for (int i = 1; i < columnCount; ++i) {
                        final AccessibleEvent accessibleEvent2 = accessibleEvent;
                        accessibleEvent2.result = String.valueOf(accessibleEvent2.result) + tree.getColumn(i).getText() + ": " + treeItem.getText(i);
                        if (i + 1 < columnCount) {
                            final AccessibleEvent accessibleEvent3 = accessibleEvent;
                            accessibleEvent3.result = String.valueOf(accessibleEvent3.result) + ", ";
                        }
                    }
                }
            }
        }
        for (int j = 0; j < this.accessibleListeners.size(); ++j) {
            ((AccessibleListener)this.accessibleListeners.elementAt(j)).getDescription(accessibleEvent);
        }
        if (accessibleEvent.result == null) {
            return get_accDescription;
        }
        if (accessibleEvent.result.length() == 0) {
            return 1;
        }
        this.setString(n2, accessibleEvent.result);
        return 0;
    }
    
    int get_accFocus(final int n) {
        int lVal = -2;
        if (this.iaccessible != null) {
            final int get_accFocus = this.iaccessible.get_accFocus(n);
            if (get_accFocus == 0) {
                final VARIANT variant = this.getVARIANT(n);
                if (variant.vt == 3) {
                    lVal = variant.lVal;
                }
            }
            if (this.accessibleControlListeners.size() == 0) {
                return get_accFocus;
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = ((lVal == -2) ? -2 : this.osToChildID(lVal));
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getFocus(accessibleControlEvent);
        }
        final Accessible accessible = accessibleControlEvent.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(n, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = accessibleControlEvent.childID;
        if (childID == -2) {
            this.setIntVARIANT(n, (short)0, 0);
            return 1;
        }
        if (childID == -1) {
            this.AddRef();
            this.setIntVARIANT(n, (short)3, 0);
            return 0;
        }
        this.setIntVARIANT(n, (short)3, this.childIDToOs(childID));
        return 0;
    }
    
    int get_accHelp(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int get_accHelp = -2147352573;
        String result = null;
        if (this.iaccessible != null) {
            get_accHelp = this.iaccessible.get_accHelp(n, n2);
            if (get_accHelp == -2147024809) {
                get_accHelp = 1;
            }
            if (this.accessibleListeners.size() == 0) {
                return get_accHelp;
            }
            if (get_accHelp == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
        }
        final AccessibleEvent accessibleEvent = new AccessibleEvent(this);
        accessibleEvent.childID = this.osToChildID(variant.lVal);
        accessibleEvent.result = result;
        for (int i = 0; i < this.accessibleListeners.size(); ++i) {
            ((AccessibleListener)this.accessibleListeners.elementAt(i)).getHelp(accessibleEvent);
        }
        if (accessibleEvent.result == null) {
            return get_accHelp;
        }
        if (accessibleEvent.result.length() == 0) {
            return 1;
        }
        this.setString(n2, accessibleEvent.result);
        return 0;
    }
    
    int get_accHelpTopic(final int n, final int n2, final int n3) {
        int get_accHelpTopic = -2147352573;
        if (this.iaccessible != null) {
            get_accHelpTopic = this.iaccessible.get_accHelpTopic(n, n2, n3);
            if (get_accHelpTopic == -2147024809) {
                get_accHelpTopic = -2147352573;
            }
        }
        return get_accHelpTopic;
    }
    
    int get_accKeyboardShortcut(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int get_accKeyboardShortcut = -2147352573;
        String result = null;
        if (this.iaccessible != null) {
            get_accKeyboardShortcut = this.iaccessible.get_accKeyboardShortcut(n, n2);
            if (get_accKeyboardShortcut == -2147024809) {
                get_accKeyboardShortcut = 1;
            }
            if (this.accessibleListeners.size() == 0) {
                return get_accKeyboardShortcut;
            }
            if (get_accKeyboardShortcut == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
        }
        final AccessibleEvent accessibleEvent = new AccessibleEvent(this);
        accessibleEvent.childID = this.osToChildID(variant.lVal);
        accessibleEvent.result = result;
        for (int i = 0; i < this.accessibleListeners.size(); ++i) {
            ((AccessibleListener)this.accessibleListeners.elementAt(i)).getKeyboardShortcut(accessibleEvent);
        }
        if (accessibleEvent.result == null) {
            return get_accKeyboardShortcut;
        }
        if (accessibleEvent.result.length() == 0) {
            return 1;
        }
        this.setString(n2, accessibleEvent.result);
        return 0;
    }
    
    int get_accName(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int get_accName = 1;
        String result = null;
        if (this.iaccessible != null) {
            get_accName = this.iaccessible.get_accName(n, n2);
            if (get_accName == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
            if (get_accName == -2147024809) {
                get_accName = 1;
            }
            if (this.accessibleListeners.size() == 0) {
                return get_accName;
            }
        }
        final AccessibleEvent accessibleEvent = new AccessibleEvent(this);
        accessibleEvent.childID = this.osToChildID(variant.lVal);
        accessibleEvent.result = result;
        for (int i = 0; i < this.accessibleListeners.size(); ++i) {
            ((AccessibleListener)this.accessibleListeners.elementAt(i)).getName(accessibleEvent);
        }
        if (accessibleEvent.result == null) {
            return get_accName;
        }
        if (accessibleEvent.result.length() == 0) {
            return 1;
        }
        this.setString(n2, accessibleEvent.result);
        return 0;
    }
    
    int get_accParent(final int n) {
        int get_accParent = -2147352573;
        if (this.iaccessible != null) {
            get_accParent = this.iaccessible.get_accParent(n);
        }
        if (this.parent != null) {
            this.parent.AddRef();
            OS.MoveMemory(n, new int[] { this.parent.getAddress() }, OS.PTR_SIZEOF);
            get_accParent = 0;
        }
        return get_accParent;
    }
    
    int get_accRole(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int lVal = 10;
        if (this.iaccessible != null && this.iaccessible.get_accRole(n, n2) == 0) {
            final VARIANT variant2 = this.getVARIANT(n2);
            if (variant2.vt == 3) {
                lVal = variant2.lVal;
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = this.osToChildID(variant.lVal);
        accessibleControlEvent.detail = this.osToRole(lVal);
        if ((this.control instanceof Tree || this.control instanceof Table) && variant.lVal != 0 && (this.control.getStyle() & 0x20) != 0x0) {
            accessibleControlEvent.detail = 44;
        }
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getRole(accessibleControlEvent);
        }
        this.setIntVARIANT(n2, (short)3, this.roleToOs(accessibleControlEvent.detail));
        return 0;
    }
    
    int get_accSelection(final int n) {
        int osToChildID = -2;
        int lVal = 0;
        if (this.iaccessible != null) {
            final int get_accSelection = this.iaccessible.get_accSelection(n);
            if (this.accessibleControlListeners.size() == 0) {
                return get_accSelection;
            }
            if (get_accSelection == 0) {
                final VARIANT variant = this.getVARIANT(n);
                if (variant.vt == 3) {
                    osToChildID = this.osToChildID(variant.lVal);
                }
                else if (variant.vt == 9) {
                    lVal = variant.lVal;
                }
                else if (variant.vt == 13) {
                    osToChildID = -3;
                }
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = osToChildID;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getSelection(accessibleControlEvent);
        }
        final Accessible accessible = accessibleControlEvent.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(n, (short)9, accessible.getAddress());
            return 0;
        }
        final int childID = accessibleControlEvent.childID;
        if (childID == -2) {
            if (lVal != 0) {
                return 0;
            }
            this.setIntVARIANT(n, (short)0, 0);
            return 1;
        }
        else {
            if (childID == -3) {
                return 0;
            }
            if (childID == -1) {
                this.AddRef();
                this.setPtrVARIANT(n, (short)9, this.getAddress());
                return 0;
            }
            this.setIntVARIANT(n, (short)3, this.childIDToOs(childID));
            return 0;
        }
    }
    
    int get_accState(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int lVal = 0;
        if (this.iaccessible != null && this.iaccessible.get_accState(n, n2) == 0) {
            final VARIANT variant2 = this.getVARIANT(n2);
            if (variant2.vt == 3) {
                lVal = variant2.lVal;
            }
        }
        boolean b = false;
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = this.osToChildID(variant.lVal);
        accessibleControlEvent.detail = this.osToState(lVal);
        if (variant.lVal != 0) {
            if (this.control instanceof Tree && (this.control.getStyle() & 0x20) != 0x0) {
                final int handle = this.control.handle;
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 24;
                tvitem.stateMask = 61440;
                if (OS.COMCTL32_MAJOR >= 6) {
                    tvitem.hItem = OS.SendMessage(handle, 4394, variant.lVal, 0);
                }
                else {
                    tvitem.hItem = variant.lVal;
                }
                if (OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem) != 0 && (tvitem.state >> 12 & 0x1) == 0x0) {
                    final AccessibleControlEvent accessibleControlEvent2 = accessibleControlEvent;
                    accessibleControlEvent2.detail |= 0x10;
                }
                b = (tvitem.state >> 12 > 2);
            }
            else if (this.control instanceof Table && (this.control.getStyle() & 0x20) != 0x0) {
                final Table table = (Table)this.control;
                final int childID = accessibleControlEvent.childID;
                if (childID >= 0 && childID < table.getItemCount()) {
                    final TableItem item = table.getItem(childID);
                    if (item.getChecked()) {
                        final AccessibleControlEvent accessibleControlEvent3 = accessibleControlEvent;
                        accessibleControlEvent3.detail |= 0x10;
                    }
                    if (item.getGrayed()) {
                        b = true;
                    }
                }
            }
        }
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getState(accessibleControlEvent);
        }
        int stateToOs = this.stateToOs(accessibleControlEvent.detail);
        if ((stateToOs & 0x10) != 0x0 && b) {
            stateToOs = ((stateToOs & 0xFFFFFFEF) | 0x20);
        }
        this.setIntVARIANT(n2, (short)3, stateToOs);
        return 0;
    }
    
    int get_accValue(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int get_accValue = -2147352573;
        String result = null;
        if (this.iaccessible != null) {
            get_accValue = this.iaccessible.get_accValue(n, n2);
            if (get_accValue == 0) {
                final int[] array = { 0 };
                OS.MoveMemory(array, n2, OS.PTR_SIZEOF);
                final int sysStringByteLen = COM.SysStringByteLen(array[0]);
                if (sysStringByteLen > 0) {
                    final char[] array2 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array2, array[0], sysStringByteLen);
                    result = new String(array2);
                }
            }
            if (get_accValue == -2147024809) {
                get_accValue = -2147352573;
            }
            if (this.accessibleControlListeners.size() == 0) {
                return get_accValue;
            }
        }
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = this.osToChildID(variant.lVal);
        accessibleControlEvent.result = result;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getValue(accessibleControlEvent);
        }
        if (accessibleControlEvent.result == null) {
            return get_accValue;
        }
        this.setString(n2, accessibleControlEvent.result);
        return 0;
    }
    
    int put_accName(final int n, final int n2) {
        return -2147467263;
    }
    
    int put_accValue(final int n, final int n2) {
        final VARIANT variant = this.getVARIANT(n);
        if (variant.vt != 3) {
            return -2147024809;
        }
        int put_accValue = -2147352573;
        if (variant.lVal == 0 && this.accessibleEditableTextListeners.size() > 0) {
            final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
            accessibleEditableTextEvent.start = 0;
            accessibleEditableTextEvent.end = this.getCharacterCount();
            if (accessibleEditableTextEvent.end >= 0) {
                final int sysStringByteLen = COM.SysStringByteLen(n2);
                final char[] array = new char[(sysStringByteLen + 1) / 2];
                OS.MoveMemory(array, n2, sysStringByteLen);
                accessibleEditableTextEvent.string = new String(array);
                for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
                    ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).replaceText(accessibleEditableTextEvent);
                }
                if (accessibleEditableTextEvent.result != null && accessibleEditableTextEvent.result.equals("OK")) {
                    put_accValue = 0;
                }
            }
        }
        if (put_accValue != 0 && this.iaccessible != null) {
            put_accValue = this.iaccessible.put_accValue(n, n2);
            if (put_accValue == -2147024809) {
                put_accValue = -2147352573;
            }
        }
        return put_accValue;
    }
    
    int Next(final int n, final int n2, final int n3) {
        if (this.iaccessible != null && this.accessibleControlListeners.size() == 0) {
            final int[] array = { 0 };
            final int queryInterface = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, array);
            if (queryInterface != 0) {
                return queryInterface;
            }
            final IEnumVARIANT enumVARIANT = new IEnumVARIANT(array[0]);
            final int[] array2 = { 0 };
            final int next = enumVARIANT.Next(n, n2, array2);
            enumVARIANT.Release();
            OS.MoveMemory(n3, array2, 4);
            return next;
        }
        else {
            if (n2 == 0) {
                return -2147024809;
            }
            if (n3 == 0 && n != 1) {
                return -2147024809;
            }
            if (this.enumIndex == 0) {
                final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
                accessibleControlEvent.childID = -1;
                for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
                    ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getChildren(accessibleControlEvent);
                }
                this.variants = accessibleControlEvent.children;
            }
            Object[] array3 = null;
            if (this.variants != null && n >= 1) {
                int n4 = this.enumIndex + n - 1;
                if (n4 > this.variants.length - 1) {
                    n4 = this.variants.length - 1;
                }
                if (this.enumIndex <= n4) {
                    array3 = new Object[n4 - this.enumIndex + 1];
                    for (int j = 0; j < array3.length; ++j) {
                        final Object o = this.variants[this.enumIndex];
                        if (o instanceof Integer) {
                            array3[j] = new Integer(this.childIDToOs((int)o));
                        }
                        else {
                            array3[j] = o;
                        }
                        ++this.enumIndex;
                    }
                }
            }
            if (array3 != null) {
                for (int k = 0; k < array3.length; ++k) {
                    final Object o2 = array3[k];
                    if (o2 instanceof Integer) {
                        this.setIntVARIANT(n2 + k * VARIANT.sizeof, (short)3, (int)o2);
                    }
                    else {
                        final Accessible accessible = (Accessible)o2;
                        accessible.AddRef();
                        this.setPtrVARIANT(n2 + k * VARIANT.sizeof, (short)9, accessible.getAddress());
                    }
                }
                if (n3 != 0) {
                    OS.MoveMemory(n3, new int[] { array3.length }, 4);
                }
                if (array3.length == n) {
                    return 0;
                }
            }
            else if (n3 != 0) {
                OS.MoveMemory(n3, new int[1], 4);
            }
            return 1;
        }
    }
    
    int Skip(final int n) {
        if (this.iaccessible != null && this.accessibleControlListeners.size() == 0) {
            final int[] array = { 0 };
            final int queryInterface = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, array);
            if (queryInterface != 0) {
                return queryInterface;
            }
            final IEnumVARIANT enumVARIANT = new IEnumVARIANT(array[0]);
            final int skip = enumVARIANT.Skip(n);
            enumVARIANT.Release();
            return skip;
        }
        else {
            if (n < 1) {
                return -2147024809;
            }
            this.enumIndex += n;
            if (this.enumIndex > this.variants.length - 1) {
                this.enumIndex = this.variants.length - 1;
                return 1;
            }
            return 0;
        }
    }
    
    int Reset() {
        if (this.iaccessible == null || this.accessibleControlListeners.size() != 0) {
            return this.enumIndex = 0;
        }
        final int[] array = { 0 };
        final int queryInterface = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, array);
        if (queryInterface != 0) {
            return queryInterface;
        }
        final IEnumVARIANT enumVARIANT = new IEnumVARIANT(array[0]);
        final int reset = enumVARIANT.Reset();
        enumVARIANT.Release();
        return reset;
    }
    
    int Clone(final int n) {
        if (this.iaccessible != null && this.accessibleControlListeners.size() == 0) {
            final int[] array = { 0 };
            final int queryInterface = this.iaccessible.QueryInterface(COM.IIDIEnumVARIANT, array);
            if (queryInterface != 0) {
                return queryInterface;
            }
            final IEnumVARIANT enumVARIANT = new IEnumVARIANT(array[0]);
            final int[] array2 = { 0 };
            final int clone = enumVARIANT.Clone(array2);
            enumVARIANT.Release();
            OS.MoveMemory(n, array2, OS.PTR_SIZEOF);
            return clone;
        }
        else {
            if (n == 0) {
                return -2147024809;
            }
            OS.MoveMemory(n, new int[] { this.objIEnumVARIANT.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
    }
    
    int get_nRelations(final int n) {
        OS.MoveMemory(n, new int[] { this.getRelationCount() }, 4);
        return 0;
    }
    
    int get_relation(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < 15; ++i) {
            final Relation relation = this.relations[i];
            if (relation != null) {
                ++n3;
            }
            if (n3 == n) {
                relation.AddRef();
                OS.MoveMemory(n2, new int[] { relation.getAddress() }, OS.PTR_SIZEOF);
                return 0;
            }
        }
        return -2147024809;
    }
    
    int get_relations(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int n5 = 0; n5 < 15 && n4 != n; ++n5) {
            final Relation relation = this.relations[n5];
            if (relation != null) {
                relation.AddRef();
                OS.MoveMemory(n2 + n4 * OS.PTR_SIZEOF, new int[] { relation.getAddress() }, OS.PTR_SIZEOF);
                ++n4;
            }
        }
        OS.MoveMemory(n3, new int[] { n4 }, 4);
        return 0;
    }
    
    int get_role(final int n) {
        int n2 = this.getRole();
        if (n2 == 0) {
            n2 = this.getDefaultRole();
        }
        OS.MoveMemory(n, new int[] { n2 }, 4);
        return 0;
    }
    
    int scrollTo(final int n) {
        if (n < 4 || n > 6) {
            return -2147024809;
        }
        return -2147467263;
    }
    
    int scrollToPoint(final int n, final int n2, final int n3) {
        if (n != 0) {
            return -2147024809;
        }
        return -2147467263;
    }
    
    int get_groupPosition(final int n, final int n2, final int n3) {
        final boolean b = false;
        OS.MoveMemory(n, new int[] { b }, 4);
        final boolean b2 = false;
        OS.MoveMemory(n2, new int[] { b2 }, 4);
        final boolean b3 = false;
        OS.MoveMemory(n3, new int[] { b3 }, 4);
        if (!b && !b2 && !b3) {
            return 1;
        }
        return 0;
    }
    
    int get_states(final int n) {
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = -1;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getState(accessibleControlEvent);
        }
        final int detail = accessibleControlEvent.detail;
        int n2 = 0;
        if ((detail & 0x4000000) != 0x0) {
            n2 |= 0x1;
        }
        if ((detail & 0x8000000) != 0x0) {
            n2 |= 0x2000;
        }
        if ((detail & 0x10000000) != 0x0) {
            n2 |= 0x200;
        }
        if ((detail & 0x2000000) != 0x0) {
            n2 |= 0x800;
        }
        if ((detail & 0x20000000) != 0x0) {
            n2 |= 0x40;
        }
        if ((detail & 0x40000000) != 0x0) {
            n2 |= 0x8000;
        }
        if (this.getRole() == 42 && this.accessibleTextExtendedListeners.size() > 0) {
            n2 |= 0x8;
        }
        OS.MoveMemory(n, new int[] { n2 }, 4);
        return 0;
    }
    
    int get_extendedRole(final int n) {
        this.setString(n, null);
        return 1;
    }
    
    int get_localizedExtendedRole(final int n) {
        this.setString(n, null);
        return 1;
    }
    
    int get_nExtendedStates(final int n) {
        OS.MoveMemory(n, new int[1], 4);
        return 0;
    }
    
    int get_extendedStates(final int n, final int n2, final int n3) {
        this.setString(n2, null);
        OS.MoveMemory(n3, new int[1], 4);
        return 1;
    }
    
    int get_localizedExtendedStates(final int n, final int n2, final int n3) {
        this.setString(n2, null);
        OS.MoveMemory(n3, new int[1], 4);
        return 1;
    }
    
    int get_uniqueID(final int n) {
        if (this.uniqueID == -1) {
            this.uniqueID = Accessible.UniqueID--;
        }
        OS.MoveMemory(n, new int[] { this.uniqueID }, 4);
        return 0;
    }
    
    int get_windowHandle(final int n) {
        OS.MoveMemory(n, new int[] { this.control.handle }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_indexInParent(final int n) {
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = -5;
        accessibleControlEvent.detail = -1;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getChild(accessibleControlEvent);
        }
        final int detail = accessibleControlEvent.detail;
        OS.MoveMemory(n, new int[] { detail }, 4);
        return (detail == -1) ? 1 : 0;
    }
    
    int get_locale(final int n) {
        final Locale default1 = Locale.getDefault();
        OS.MoveMemory(n, new int[] { COM.SysAllocString((String.valueOf(default1.getLanguage()) + "\u0000").toCharArray()) }, OS.PTR_SIZEOF);
        OS.MoveMemory(n + OS.PTR_SIZEOF, new int[] { COM.SysAllocString((String.valueOf(default1.getCountry()) + "\u0000").toCharArray()) }, OS.PTR_SIZEOF);
        OS.MoveMemory(n + 2 * OS.PTR_SIZEOF, new int[] { COM.SysAllocString((String.valueOf(default1.getVariant()) + "\u0000").toCharArray()) }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_attributes(final int n) {
        final AccessibleAttributeEvent accessibleAttributeEvent = new AccessibleAttributeEvent(this);
        for (int i = 0; i < this.accessibleAttributeListeners.size(); ++i) {
            ((AccessibleAttributeListener)this.accessibleAttributeListeners.elementAt(i)).getAttributes(accessibleAttributeEvent);
        }
        String s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf("")).append("margin-left:").append(accessibleAttributeEvent.leftMargin).append(";").toString())).append("margin-top:").append(accessibleAttributeEvent.topMargin).append(";").toString())).append("margin-right:").append(accessibleAttributeEvent.rightMargin).append(";").toString()) + "margin-bottom:" + accessibleAttributeEvent.bottomMargin + ";";
        if (accessibleAttributeEvent.tabStops != null) {
            for (int j = 0; j < accessibleAttributeEvent.tabStops.length; ++j) {
                s = String.valueOf(s) + "tab-stop:position=" + accessibleAttributeEvent.tabStops[j] + ";";
            }
        }
        if (accessibleAttributeEvent.justify) {
            s = String.valueOf(s) + "text-align:justify;";
        }
        String s2 = String.valueOf(new StringBuffer(String.valueOf(s)).append("text-align:").append((accessibleAttributeEvent.alignment == 16384) ? "left" : ((accessibleAttributeEvent.alignment == 131072) ? "right" : "center")).append(";").toString()) + "text-indent:" + accessibleAttributeEvent.indent + ";";
        if (accessibleAttributeEvent.attributes != null) {
            for (int n2 = 0; n2 + 1 < accessibleAttributeEvent.attributes.length; n2 += 2) {
                s2 = String.valueOf(s2) + accessibleAttributeEvent.attributes[n2] + ":" + accessibleAttributeEvent.attributes[n2 + 1] + ";";
            }
        }
        if (this.getRole() == 42) {
            s2 = String.valueOf(s2) + "text-model:a1;";
        }
        this.setString(n, s2);
        if (s2.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_nActions(final int n) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).getActionCount(accessibleActionEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleActionEvent.count }, 4);
        return 0;
    }
    
    int doAction(final int index) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        accessibleActionEvent.index = index;
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).doAction(accessibleActionEvent);
        }
        if (accessibleActionEvent.result == null || !accessibleActionEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_description(final int index, final int n) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        accessibleActionEvent.index = index;
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).getDescription(accessibleActionEvent);
        }
        this.setString(n, accessibleActionEvent.result);
        if (accessibleActionEvent.result == null || accessibleActionEvent.result.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_keyBinding(final int index, final int n, final int n2, final int n3) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        accessibleActionEvent.index = index;
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).getKeyBinding(accessibleActionEvent);
        }
        final String result = accessibleActionEvent.result;
        int length = 0;
        if (result != null) {
            length = result.length();
        }
        int n4;
        int n5;
        int index2;
        for (n4 = 0, n5 = 0; n4 < length && n5 != n; n4 = index2 + 1) {
            index2 = result.indexOf(59, n4);
            if (index2 == -1) {
                index2 = length;
            }
            final String substring = result.substring(n4, index2);
            if (substring.length() > 0) {
                this.setString(n2 + n5 * OS.PTR_SIZEOF, substring);
                ++n5;
            }
        }
        OS.MoveMemory(n3, new int[] { n5 }, 4);
        if (n5 == 0) {
            this.setString(n2, null);
            return 1;
        }
        return 0;
    }
    
    int get_name(final int index, final int n) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        accessibleActionEvent.index = index;
        accessibleActionEvent.localized = false;
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).getName(accessibleActionEvent);
        }
        if (accessibleActionEvent.result == null || accessibleActionEvent.result.length() == 0) {
            this.setString(n, null);
            return 1;
        }
        this.setString(n, accessibleActionEvent.result);
        return 0;
    }
    
    int get_localizedName(final int index, final int n) {
        final AccessibleActionEvent accessibleActionEvent = new AccessibleActionEvent(this);
        accessibleActionEvent.index = index;
        accessibleActionEvent.localized = true;
        for (int i = 0; i < this.accessibleActionListeners.size(); ++i) {
            ((AccessibleActionListener)this.accessibleActionListeners.elementAt(i)).getName(accessibleActionEvent);
        }
        if (accessibleActionEvent.result == null || accessibleActionEvent.result.length() == 0) {
            this.setString(n, null);
            return 1;
        }
        this.setString(n, accessibleActionEvent.result);
        return 0;
    }
    
    int get_appName(final int n) {
        final String appName = Display.getAppName();
        if (appName == null || appName.length() == 0) {
            this.setString(n, null);
            return 1;
        }
        this.setString(n, appName);
        return 0;
    }
    
    int get_appVersion(final int n) {
        final String appVersion = Display.getAppVersion();
        if (appVersion == null || appVersion.length() == 0) {
            this.setString(n, null);
            return 1;
        }
        this.setString(n, appVersion);
        return 0;
    }
    
    int get_toolkitName(final int n) {
        this.setString(n, "SWT");
        return 0;
    }
    
    int get_toolkitVersion(final int n) {
        this.setString(n, new StringBuffer().append(SWT.getVersion()).toString());
        return 0;
    }
    
    int copyText(final int n, final int n2) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).copyText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int deleteText(final int n, final int n2) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        accessibleEditableTextEvent.string = "";
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).replaceText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int insertText(final int n, final int n2) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = accessibleEditableTextEvent.start;
        accessibleEditableTextEvent.string = this.getString(n2);
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).replaceText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int cutText(final int n, final int n2) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).cutText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int pasteText(final int n) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = accessibleEditableTextEvent.start;
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).pasteText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int replaceText(final int n, final int n2, final int n3) {
        final AccessibleEditableTextEvent accessibleEditableTextEvent = new AccessibleEditableTextEvent(this);
        accessibleEditableTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleEditableTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        accessibleEditableTextEvent.string = this.getString(n3);
        for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
            ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).replaceText(accessibleEditableTextEvent);
        }
        if (accessibleEditableTextEvent.result == null || !accessibleEditableTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setAttributes(final int n, final int n2, final int n3) {
        final AccessibleTextAttributeEvent textAttributes = new AccessibleTextAttributeEvent(this);
        final String string = this.getString(n3);
        if (string != null && string.length() > 0) {
            textAttributes.start = ((n == -1) ? this.getCharacterCount() : n);
            textAttributes.end = ((n2 == -1) ? this.getCharacterCount() : n2);
            final TextStyle textStyle = new TextStyle();
            FontData fontData = null;
            int int1 = 10;
            String[] attributes = new String[0];
            for (int n4 = 0, n5 = string.indexOf(59); n5 != -1 && n5 < string.length(); n5 = string.indexOf(59, n4)) {
                final String trim = string.substring(n4, n5).trim();
                final int index = trim.indexOf(58);
                if (index != -1 && index + 1 < trim.length()) {
                    final String[] array = new String[attributes.length + 2];
                    System.arraycopy(attributes, 0, array, 0, attributes.length);
                    array[attributes.length] = trim.substring(0, index).trim();
                    array[attributes.length + 1] = trim.substring(index + 1).trim();
                    attributes = array;
                }
                n4 = n5 + 1;
            }
            for (int n6 = 0; n6 + 1 < attributes.length; n6 += 2) {
                final String s = attributes[n6];
                final String name = attributes[n6 + 1];
                if (s.equals("text-position")) {
                    if (name.equals("super")) {
                        textStyle.rise = int1 / 2;
                    }
                    else if (name.equals("sub")) {
                        textStyle.rise = -int1 / 2;
                    }
                }
                else if (s.equals("text-underline-type")) {
                    textStyle.underline = true;
                    if (name.equals("double")) {
                        textStyle.underlineStyle = 1;
                    }
                    else if (name.equals("single") && textStyle.underlineStyle != 3 && textStyle.underlineStyle != 2) {
                        textStyle.underlineStyle = 0;
                    }
                }
                else if (s.equals("text-underline-style") && name.equals("wave")) {
                    textStyle.underline = true;
                    textStyle.underlineStyle = 3;
                }
                else if (s.equals("invalid") && name.equals("true")) {
                    textStyle.underline = true;
                    textStyle.underlineStyle = 2;
                }
                else if (s.equals("text-line-through-type")) {
                    if (name.equals("single")) {
                        textStyle.strikeout = true;
                    }
                }
                else if (s.equals("font-family")) {
                    if (fontData == null) {
                        fontData = new FontData();
                    }
                    fontData.setName(name);
                }
                else if (s.equals("font-size")) {
                    try {
                        int1 = Integer.parseInt(name.endsWith("pt") ? name.substring(0, name.length() - 2) : name);
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setHeight(int1);
                        if (textStyle.rise > 0) {
                            textStyle.rise = int1 / 2;
                        }
                        else if (textStyle.rise < 0) {
                            textStyle.rise = -int1 / 2;
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                else if (s.equals("font-style")) {
                    if (name.equals("italic")) {
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setStyle(fontData.getStyle() | 0x2);
                    }
                }
                else if (s.equals("font-weight")) {
                    if (name.equals("bold")) {
                        if (fontData == null) {
                            fontData = new FontData();
                        }
                        fontData.setStyle(fontData.getStyle() | 0x1);
                    }
                    else {
                        try {
                            final int int2 = Integer.parseInt(name);
                            if (fontData == null) {
                                fontData = new FontData();
                            }
                            if (int2 > 400) {
                                fontData.setStyle(fontData.getStyle() | 0x1);
                            }
                        }
                        catch (NumberFormatException ex2) {}
                    }
                }
                else if (s.equals("color")) {
                    textStyle.foreground = this.colorFromString(name);
                }
                else if (s.equals("background-color")) {
                    textStyle.background = this.colorFromString(name);
                }
            }
            if (attributes.length > 0) {
                textAttributes.attributes = attributes;
                if (fontData != null) {
                    textStyle.font = new Font(this.control.getDisplay(), fontData);
                }
                if (!textStyle.equals(new TextStyle())) {
                    textAttributes.textStyle = textStyle;
                }
            }
            for (int i = 0; i < this.accessibleEditableTextListeners.size(); ++i) {
                ((AccessibleEditableTextListener)this.accessibleEditableTextListeners.elementAt(i)).setTextAttributes(textAttributes);
            }
            if (textStyle.font != null) {
                textStyle.font.dispose();
            }
            if (textStyle.foreground != null) {
                textStyle.foreground.dispose();
            }
            if (textStyle.background != null) {
                textStyle.background.dispose();
            }
        }
        if (textAttributes.result == null || !textAttributes.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_anchor(final int index, final int n) {
        final AccessibleHyperlinkEvent accessibleHyperlinkEvent = new AccessibleHyperlinkEvent(this);
        accessibleHyperlinkEvent.index = index;
        for (int i = 0; i < this.accessibleHyperlinkListeners.size(); ++i) {
            ((AccessibleHyperlinkListener)this.accessibleHyperlinkListeners.elementAt(i)).getAnchor(accessibleHyperlinkEvent);
        }
        final Accessible accessible = accessibleHyperlinkEvent.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(n, (short)9, accessible.getAddress());
            return 0;
        }
        this.setStringVARIANT(n, accessibleHyperlinkEvent.result);
        if (accessibleHyperlinkEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_anchorTarget(final int index, final int n) {
        final AccessibleHyperlinkEvent accessibleHyperlinkEvent = new AccessibleHyperlinkEvent(this);
        accessibleHyperlinkEvent.index = index;
        for (int i = 0; i < this.accessibleHyperlinkListeners.size(); ++i) {
            ((AccessibleHyperlinkListener)this.accessibleHyperlinkListeners.elementAt(i)).getAnchorTarget(accessibleHyperlinkEvent);
        }
        final Accessible accessible = accessibleHyperlinkEvent.accessible;
        if (accessible != null) {
            accessible.AddRef();
            this.setPtrVARIANT(n, (short)9, accessible.getAddress());
            return 0;
        }
        this.setStringVARIANT(n, accessibleHyperlinkEvent.result);
        if (accessibleHyperlinkEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_startIndex(final int n) {
        final AccessibleHyperlinkEvent accessibleHyperlinkEvent = new AccessibleHyperlinkEvent(this);
        for (int i = 0; i < this.accessibleHyperlinkListeners.size(); ++i) {
            ((AccessibleHyperlinkListener)this.accessibleHyperlinkListeners.elementAt(i)).getStartIndex(accessibleHyperlinkEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleHyperlinkEvent.index }, 4);
        return 0;
    }
    
    int get_endIndex(final int n) {
        final AccessibleHyperlinkEvent accessibleHyperlinkEvent = new AccessibleHyperlinkEvent(this);
        for (int i = 0; i < this.accessibleHyperlinkListeners.size(); ++i) {
            ((AccessibleHyperlinkListener)this.accessibleHyperlinkListeners.elementAt(i)).getEndIndex(accessibleHyperlinkEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleHyperlinkEvent.index }, 4);
        return 0;
    }
    
    int get_valid(final int n) {
        return -2147467263;
    }
    
    int get_nHyperlinks(final int n) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getHyperlinkCount(accessibleTextEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTextEvent.count }, 4);
        return 0;
    }
    
    int get_hyperlink(final int index, final int n) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.index = index;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getHyperlink(accessibleTextEvent);
        }
        final Accessible accessible = accessibleTextEvent.accessible;
        if (accessible == null) {
            this.setIntVARIANT(n, (short)0, 0);
            return -2147024809;
        }
        accessible.AddRef();
        OS.MoveMemory(n, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_hyperlinkIndex(final int offset, final int n) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.offset = offset;
        accessibleTextEvent.index = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getHyperlinkIndex(accessibleTextEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTextEvent.index }, 4);
        if (accessibleTextEvent.index == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_cellAt(final int row, final int column, final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.row = row;
        accessibleTableEvent.column = column;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getCell(accessibleTableEvent);
        }
        final Accessible accessible = accessibleTableEvent.accessible;
        if (accessible == null) {
            return -2147024809;
        }
        accessible.AddRef();
        OS.MoveMemory(n, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_caption(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getCaption(accessibleTableEvent);
        }
        final Accessible accessible = accessibleTableEvent.accessible;
        if (accessible == null) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(n, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_columnDescription(final int column, final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.column = column;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getColumnDescription(accessibleTableEvent);
        }
        this.setString(n, accessibleTableEvent.result);
        if (accessibleTableEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_nColumns(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getColumnCount(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.count }, 4);
        return 0;
    }
    
    int get_nRows(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getRowCount(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.count }, 4);
        return 0;
    }
    
    int get_nSelectedCells(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedCellCount(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.count }, 4);
        return 0;
    }
    
    int get_nSelectedColumns(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedColumnCount(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.count }, 4);
        return 0;
    }
    
    int get_nSelectedRows(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedRowCount(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.count }, 4);
        return 0;
    }
    
    int get_rowDescription(final int row, final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.row = row;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getRowDescription(accessibleTableEvent);
        }
        this.setString(n, accessibleTableEvent.result);
        if (accessibleTableEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_selectedCells(final int n, final int n2) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedCells(accessibleTableEvent);
        }
        if (accessibleTableEvent.accessibles == null || accessibleTableEvent.accessibles.length == 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            OS.MoveMemory(n2, new int[1], 4);
            return 1;
        }
        final int length = accessibleTableEvent.accessibles.length;
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(length * OS.PTR_SIZEOF);
        int n3 = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = accessibleTableEvent.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(coTaskMemAlloc + j * OS.PTR_SIZEOF, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
                ++n3;
            }
        }
        OS.MoveMemory(n, new int[] { coTaskMemAlloc }, OS.PTR_SIZEOF);
        OS.MoveMemory(n2, new int[] { n3 }, 4);
        return 0;
    }
    
    int get_selectedColumns(final int n, final int n2) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedColumns(accessibleTableEvent);
        }
        final int n3 = (accessibleTableEvent.selected == null) ? 0 : accessibleTableEvent.selected.length;
        if (n3 == 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            OS.MoveMemory(n2, new int[1], 4);
            return 1;
        }
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(n3 * 4);
        OS.MoveMemory(coTaskMemAlloc, accessibleTableEvent.selected, n3 * 4);
        OS.MoveMemory(n, new int[] { coTaskMemAlloc }, OS.PTR_SIZEOF);
        OS.MoveMemory(n2, new int[] { n3 }, 4);
        return 0;
    }
    
    int get_selectedRows(final int n, final int n2) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSelectedRows(accessibleTableEvent);
        }
        final int n3 = (accessibleTableEvent.selected == null) ? 0 : accessibleTableEvent.selected.length;
        if (n3 == 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            OS.MoveMemory(n2, new int[1], 4);
            return 1;
        }
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(n3 * 4);
        OS.MoveMemory(coTaskMemAlloc, accessibleTableEvent.selected, n3 * 4);
        OS.MoveMemory(n, new int[] { coTaskMemAlloc }, OS.PTR_SIZEOF);
        OS.MoveMemory(n2, new int[] { n3 }, 4);
        return 0;
    }
    
    int get_summary(final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).getSummary(accessibleTableEvent);
        }
        final Accessible accessible = accessibleTableEvent.accessible;
        if (accessible == null) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(n, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_isColumnSelected(final int column, final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.column = column;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).isColumnSelected(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.isSelected }, 4);
        return 0;
    }
    
    int get_isRowSelected(final int row, final int n) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.row = row;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).isRowSelected(accessibleTableEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableEvent.isSelected }, 4);
        return 0;
    }
    
    int selectRow(final int row) {
        final AccessibleTableEvent selectedRow = new AccessibleTableEvent(this);
        selectedRow.row = row;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).setSelectedRow(selectedRow);
        }
        if (selectedRow.result == null || !selectedRow.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int selectColumn(final int column) {
        final AccessibleTableEvent selectedColumn = new AccessibleTableEvent(this);
        selectedColumn.column = column;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).setSelectedColumn(selectedColumn);
        }
        if (selectedColumn.result == null || !selectedColumn.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int unselectRow(final int row) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.row = row;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).deselectRow(accessibleTableEvent);
        }
        if (accessibleTableEvent.result == null || !accessibleTableEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int unselectColumn(final int column) {
        final AccessibleTableEvent accessibleTableEvent = new AccessibleTableEvent(this);
        accessibleTableEvent.column = column;
        for (int i = 0; i < this.accessibleTableListeners.size(); ++i) {
            ((AccessibleTableListener)this.accessibleTableListeners.elementAt(i)).deselectColumn(accessibleTableEvent);
        }
        if (accessibleTableEvent.result == null || !accessibleTableEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_modelChange(final int n) {
        if (this.tableChange == null) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            return 1;
        }
        OS.MoveMemory(n, this.tableChange, this.tableChange.length * 4);
        return 0;
    }
    
    int get_columnExtent(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getColumnSpan(accessibleTableCellEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableCellEvent.count }, 4);
        return 0;
    }
    
    int get_columnHeaderCells(final int n, final int n2) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getColumnHeaders(accessibleTableCellEvent);
        }
        if (accessibleTableCellEvent.accessibles == null || accessibleTableCellEvent.accessibles.length == 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            OS.MoveMemory(n2, new int[1], 4);
            return 1;
        }
        final int length = accessibleTableCellEvent.accessibles.length;
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(length * OS.PTR_SIZEOF);
        int n3 = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = accessibleTableCellEvent.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(coTaskMemAlloc + j * OS.PTR_SIZEOF, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
                ++n3;
            }
        }
        OS.MoveMemory(n, new int[] { coTaskMemAlloc }, OS.PTR_SIZEOF);
        OS.MoveMemory(n2, new int[] { n3 }, 4);
        return 0;
    }
    
    int get_columnIndex(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getColumnIndex(accessibleTableCellEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableCellEvent.index }, 4);
        return 0;
    }
    
    int get_rowExtent(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getRowSpan(accessibleTableCellEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableCellEvent.count }, 4);
        return 0;
    }
    
    int get_rowHeaderCells(final int n, final int n2) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getRowHeaders(accessibleTableCellEvent);
        }
        if (accessibleTableCellEvent.accessibles == null || accessibleTableCellEvent.accessibles.length == 0) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            OS.MoveMemory(n2, new int[1], 4);
            return 1;
        }
        final int length = accessibleTableCellEvent.accessibles.length;
        final int coTaskMemAlloc = OS.CoTaskMemAlloc(length * OS.PTR_SIZEOF);
        int n3 = 0;
        for (int j = 0; j < length; ++j) {
            final Accessible accessible = accessibleTableCellEvent.accessibles[j];
            if (accessible != null) {
                accessible.AddRef();
                OS.MoveMemory(coTaskMemAlloc + j * OS.PTR_SIZEOF, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
                ++n3;
            }
        }
        OS.MoveMemory(n, new int[] { coTaskMemAlloc }, OS.PTR_SIZEOF);
        OS.MoveMemory(n2, new int[] { n3 }, 4);
        return 0;
    }
    
    int get_rowIndex(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getRowIndex(accessibleTableCellEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableCellEvent.index }, 4);
        return 0;
    }
    
    int get_isSelected(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).isSelected(accessibleTableCellEvent);
        }
        OS.MoveMemory(n, new int[] { accessibleTableCellEvent.isSelected }, 4);
        return 0;
    }
    
    int get_rowColumnExtents(final int n, final int n2, final int n3, final int n4, final int n5) {
        return -2147352573;
    }
    
    int get_table(final int n) {
        final AccessibleTableCellEvent accessibleTableCellEvent = new AccessibleTableCellEvent(this);
        for (int i = 0; i < this.accessibleTableCellListeners.size(); ++i) {
            ((AccessibleTableCellListener)this.accessibleTableCellListeners.elementAt(i)).getTable(accessibleTableCellEvent);
        }
        final Accessible accessible = accessibleTableCellEvent.accessible;
        if (accessible == null) {
            OS.MoveMemory(n, new int[1], OS.PTR_SIZEOF);
            return 1;
        }
        accessible.AddRef();
        OS.MoveMemory(n, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int addSelection(final int n, final int n2) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).addSelection(accessibleTextEvent);
        }
        if (accessibleTextEvent.result == null || !accessibleTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_attributes(final int n, final int n2, final int n3, final int n4) {
        final AccessibleTextAttributeEvent accessibleTextAttributeEvent = new AccessibleTextAttributeEvent(this);
        accessibleTextAttributeEvent.offset = ((n == -1) ? this.getCharacterCount() : n);
        for (int i = 0; i < this.accessibleAttributeListeners.size(); ++i) {
            ((AccessibleAttributeListener)this.accessibleAttributeListeners.elementAt(i)).getTextAttributes(accessibleTextAttributeEvent);
        }
        String s = "";
        final TextStyle textStyle = accessibleTextAttributeEvent.textStyle;
        if (textStyle != null) {
            if (textStyle.rise != 0) {
                final String string = String.valueOf(s) + "text-position:";
                if (textStyle.rise > 0) {
                    s = String.valueOf(string) + "super";
                }
                else {
                    s = String.valueOf(string) + "sub";
                }
            }
            if (textStyle.underline) {
                final String string2 = String.valueOf(s) + "text-underline-type:";
                switch (textStyle.underlineStyle) {
                    case 0: {
                        s = String.valueOf(string2) + "single;";
                        break;
                    }
                    case 1: {
                        s = String.valueOf(string2) + "double;";
                        break;
                    }
                    case 3: {
                        s = String.valueOf(string2) + "single;text-underline-style:wave;";
                        break;
                    }
                    case 2: {
                        s = String.valueOf(string2) + "single;text-underline-style:wave;invalid:true;";
                        break;
                    }
                    default: {
                        s = String.valueOf(string2) + "none;";
                        break;
                    }
                }
            }
            if (textStyle.strikeout) {
                s = String.valueOf(s) + "text-line-through-type:single;";
            }
            final Font font = textStyle.font;
            if (font != null && !font.isDisposed()) {
                final FontData fontData = font.getFontData()[0];
                s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s)).append("font-family:").append(fontData.getName()).append(";").toString())).append("font-size:").append(fontData.getHeight()).append("pt;").toString())).append("font-style:").append((fontData.data.lfItalic != 0) ? "italic" : "normal").append(";").toString()) + "font-weight:" + fontData.data.lfWeight + ";";
            }
            final Color foreground = textStyle.foreground;
            if (foreground != null && !foreground.isDisposed()) {
                s = String.valueOf(s) + "color:rgb(" + foreground.getRed() + "," + foreground.getGreen() + "," + foreground.getBlue() + ");";
            }
            final Color background = textStyle.background;
            if (background != null && !background.isDisposed()) {
                s = String.valueOf(s) + "background-color:rgb(" + background.getRed() + "," + background.getGreen() + "," + background.getBlue() + ");";
            }
        }
        if (accessibleTextAttributeEvent.attributes != null) {
            for (int n5 = 0; n5 + 1 < accessibleTextAttributeEvent.attributes.length; n5 += 2) {
                s = String.valueOf(s) + accessibleTextAttributeEvent.attributes[n5] + ":" + accessibleTextAttributeEvent.attributes[n5 + 1] + ";";
            }
        }
        OS.MoveMemory(n2, new int[] { accessibleTextAttributeEvent.start }, 4);
        OS.MoveMemory(n3, new int[] { accessibleTextAttributeEvent.end }, 4);
        this.setString(n4, s);
        if (s.length() == 0) {
            return 1;
        }
        return 0;
    }
    
    int get_caretOffset(final int n) {
        final int caretOffset = this.getCaretOffset();
        OS.MoveMemory(n, new int[] { caretOffset }, 4);
        if (caretOffset == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_characterExtents(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int characterCount = this.getCharacterCount();
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = ((n == -1) ? characterCount : ((n < 0) ? 0 : n));
        accessibleTextEvent.end = ((n == -1 || n >= characterCount) ? characterCount : (n + 1));
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getTextBounds(accessibleTextEvent);
        }
        OS.MoveMemory(n3, new int[] { accessibleTextEvent.x }, 4);
        OS.MoveMemory(n4, new int[] { accessibleTextEvent.y }, 4);
        OS.MoveMemory(n5, new int[] { accessibleTextEvent.width }, 4);
        OS.MoveMemory(n6, new int[] { accessibleTextEvent.height }, 4);
        if (accessibleTextEvent.width == 0 && accessibleTextEvent.height == 0) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_nSelections(final int n) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.count = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getSelectionCount(accessibleTextEvent);
        }
        if (accessibleTextEvent.count == -1) {
            accessibleTextEvent.childID = -1;
            accessibleTextEvent.offset = -1;
            accessibleTextEvent.length = 0;
            for (int j = 0; j < this.accessibleTextListeners.size(); ++j) {
                ((AccessibleTextListener)this.accessibleTextListeners.elementAt(j)).getSelectionRange(accessibleTextEvent);
            }
            accessibleTextEvent.count = ((accessibleTextEvent.offset != -1 && accessibleTextEvent.length > 0) ? 1 : 0);
        }
        OS.MoveMemory(n, new int[] { accessibleTextEvent.count }, 4);
        return 0;
    }
    
    int get_offsetAtPoint(final int x, final int y, final int n, final int n2) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.x = x;
        accessibleTextEvent.y = y;
        accessibleTextEvent.offset = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getOffsetAtPoint(accessibleTextEvent);
        }
        OS.MoveMemory(n2, new int[] { accessibleTextEvent.offset }, 4);
        if (accessibleTextEvent.offset == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_selection(final int index, final int n, final int n2) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.index = index;
        accessibleTextEvent.start = -1;
        accessibleTextEvent.end = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getSelection(accessibleTextEvent);
        }
        if (accessibleTextEvent.start == -1 && index == 0) {
            accessibleTextEvent.childID = -1;
            accessibleTextEvent.offset = -1;
            accessibleTextEvent.length = 0;
            for (int j = 0; j < this.accessibleTextListeners.size(); ++j) {
                ((AccessibleTextListener)this.accessibleTextListeners.elementAt(j)).getSelectionRange(accessibleTextEvent);
            }
            accessibleTextEvent.start = accessibleTextEvent.offset;
            accessibleTextEvent.end = accessibleTextEvent.offset + accessibleTextEvent.length;
        }
        OS.MoveMemory(n, new int[] { accessibleTextEvent.start }, 4);
        OS.MoveMemory(n2, new int[] { accessibleTextEvent.end }, 4);
        if (accessibleTextEvent.start == -1) {
            return 1;
        }
        return 0;
    }
    
    int get_text(final int n, final int n2, final int n3) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = ((n == -1) ? this.getCharacterCount() : n);
        accessibleTextEvent.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        if (accessibleTextEvent.start > accessibleTextEvent.end) {
            final int start = accessibleTextEvent.start;
            accessibleTextEvent.start = accessibleTextEvent.end;
            accessibleTextEvent.end = start;
        }
        accessibleTextEvent.count = 0;
        accessibleTextEvent.type = 5;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getText(accessibleTextEvent);
        }
        if (accessibleTextEvent.result == null) {
            final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
            accessibleControlEvent.childID = -1;
            for (int j = 0; j < this.accessibleControlListeners.size(); ++j) {
                final AccessibleControlListener accessibleControlListener = this.accessibleControlListeners.elementAt(j);
                accessibleControlListener.getRole(accessibleControlEvent);
                accessibleControlListener.getValue(accessibleControlEvent);
            }
            if (accessibleControlEvent.detail == 42) {
                accessibleTextEvent.result = accessibleControlEvent.result;
            }
        }
        this.setString(n3, accessibleTextEvent.result);
        if (accessibleTextEvent.result == null) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_textBeforeOffset(final int n, final int n2, final int n3, final int n4, final int n5) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        final int characterCount = this.getCharacterCount();
        accessibleTextEvent.start = ((n == -1) ? characterCount : ((n == -2) ? this.getCaretOffset() : n));
        accessibleTextEvent.end = accessibleTextEvent.start;
        accessibleTextEvent.count = -1;
        switch (n2) {
            case 0: {
                accessibleTextEvent.type = 0;
                break;
            }
            case 1: {
                accessibleTextEvent.type = 1;
                break;
            }
            case 2: {
                accessibleTextEvent.type = 2;
                break;
            }
            case 3: {
                accessibleTextEvent.type = 3;
                break;
            }
            case 4: {
                accessibleTextEvent.type = 4;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int start = accessibleTextEvent.start;
        final int end = accessibleTextEvent.end;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getText(accessibleTextEvent);
        }
        if (accessibleTextEvent.end < characterCount) {
            switch (n2) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start2 = accessibleTextEvent.start;
                    accessibleTextEvent.start = start;
                    accessibleTextEvent.end = end;
                    accessibleTextEvent.count = 0;
                    for (int j = 0; j < this.accessibleTextExtendedListeners.size(); ++j) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(j)).getText(accessibleTextEvent);
                    }
                    accessibleTextEvent.end = accessibleTextEvent.start;
                    accessibleTextEvent.start = start2;
                    accessibleTextEvent.type = 5;
                    accessibleTextEvent.count = 0;
                    for (int k = 0; k < this.accessibleTextExtendedListeners.size(); ++k) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(k)).getText(accessibleTextEvent);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(n3, new int[] { accessibleTextEvent.start }, 4);
        OS.MoveMemory(n4, new int[] { accessibleTextEvent.end }, 4);
        this.setString(n5, accessibleTextEvent.result);
        if (accessibleTextEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_textAfterOffset(final int n, final int n2, final int n3, final int n4, final int n5) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        final int characterCount = this.getCharacterCount();
        accessibleTextEvent.start = ((n == -1) ? characterCount : ((n == -2) ? this.getCaretOffset() : n));
        accessibleTextEvent.end = accessibleTextEvent.start;
        accessibleTextEvent.count = 1;
        switch (n2) {
            case 0: {
                accessibleTextEvent.type = 0;
                break;
            }
            case 1: {
                accessibleTextEvent.type = 1;
                break;
            }
            case 2: {
                accessibleTextEvent.type = 2;
                break;
            }
            case 3: {
                accessibleTextEvent.type = 3;
                break;
            }
            case 4: {
                accessibleTextEvent.type = 4;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int start = accessibleTextEvent.start;
        final int end = accessibleTextEvent.end;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getText(accessibleTextEvent);
        }
        if (accessibleTextEvent.end < characterCount) {
            switch (n2) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start2 = accessibleTextEvent.start;
                    accessibleTextEvent.start = start;
                    accessibleTextEvent.end = end;
                    accessibleTextEvent.count = 2;
                    for (int j = 0; j < this.accessibleTextExtendedListeners.size(); ++j) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(j)).getText(accessibleTextEvent);
                    }
                    accessibleTextEvent.end = accessibleTextEvent.start;
                    accessibleTextEvent.start = start2;
                    accessibleTextEvent.type = 5;
                    accessibleTextEvent.count = 0;
                    for (int k = 0; k < this.accessibleTextExtendedListeners.size(); ++k) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(k)).getText(accessibleTextEvent);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(n3, new int[] { accessibleTextEvent.start }, 4);
        OS.MoveMemory(n4, new int[] { accessibleTextEvent.end }, 4);
        this.setString(n5, accessibleTextEvent.result);
        if (accessibleTextEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int get_textAtOffset(final int n, final int n2, final int n3, final int n4, final int n5) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        final int characterCount = this.getCharacterCount();
        accessibleTextEvent.start = ((n == -1) ? characterCount : ((n == -2) ? this.getCaretOffset() : n));
        accessibleTextEvent.end = accessibleTextEvent.start;
        accessibleTextEvent.count = 0;
        switch (n2) {
            case 0: {
                accessibleTextEvent.type = 0;
                break;
            }
            case 1: {
                accessibleTextEvent.type = 1;
                break;
            }
            case 2: {
                accessibleTextEvent.type = 2;
                break;
            }
            case 3: {
                accessibleTextEvent.type = 3;
                break;
            }
            case 4: {
                accessibleTextEvent.type = 4;
                break;
            }
            case 5: {
                accessibleTextEvent.type = 5;
                accessibleTextEvent.start = 0;
                accessibleTextEvent.end = characterCount;
                accessibleTextEvent.count = 0;
                break;
            }
            default: {
                return -2147024809;
            }
        }
        final int start = accessibleTextEvent.start;
        final int end = accessibleTextEvent.end;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getText(accessibleTextEvent);
        }
        if (accessibleTextEvent.end < characterCount) {
            switch (n2) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    final int start2 = accessibleTextEvent.start;
                    accessibleTextEvent.start = start;
                    accessibleTextEvent.end = end;
                    accessibleTextEvent.count = 1;
                    for (int j = 0; j < this.accessibleTextExtendedListeners.size(); ++j) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(j)).getText(accessibleTextEvent);
                    }
                    accessibleTextEvent.end = accessibleTextEvent.start;
                    accessibleTextEvent.start = start2;
                    accessibleTextEvent.type = 5;
                    accessibleTextEvent.count = 0;
                    for (int k = 0; k < this.accessibleTextExtendedListeners.size(); ++k) {
                        ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(k)).getText(accessibleTextEvent);
                    }
                    break;
                }
            }
        }
        OS.MoveMemory(n3, new int[] { accessibleTextEvent.start }, 4);
        OS.MoveMemory(n4, new int[] { accessibleTextEvent.end }, 4);
        this.setString(n5, accessibleTextEvent.result);
        if (accessibleTextEvent.result == null) {
            return 1;
        }
        return 0;
    }
    
    int removeSelection(final int index) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.index = index;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).removeSelection(accessibleTextEvent);
        }
        if (accessibleTextEvent.result == null || !accessibleTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setCaretOffset(final int n) {
        final AccessibleTextEvent caretOffset = new AccessibleTextEvent(this);
        caretOffset.offset = ((n == -1) ? this.getCharacterCount() : n);
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).setCaretOffset(caretOffset);
        }
        if (caretOffset.result == null || !caretOffset.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int setSelection(final int index, final int n, final int n2) {
        final AccessibleTextEvent selection = new AccessibleTextEvent(this);
        selection.index = index;
        selection.start = ((n == -1) ? this.getCharacterCount() : n);
        selection.end = ((n2 == -1) ? this.getCharacterCount() : n2);
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).setSelection(selection);
        }
        if (selection.result == null || !selection.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_nCharacters(final int n) {
        OS.MoveMemory(n, new int[] { this.getCharacterCount() }, 4);
        return 0;
    }
    
    int scrollSubstringTo(final int start, final int end, final int n) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = start;
        accessibleTextEvent.end = end;
        switch (n) {
            case 0: {
                accessibleTextEvent.type = 0;
                break;
            }
            case 1: {
                accessibleTextEvent.type = 1;
                break;
            }
            case 2: {
                accessibleTextEvent.type = 2;
                break;
            }
            case 3: {
                accessibleTextEvent.type = 3;
                break;
            }
            case 4: {
                accessibleTextEvent.type = 4;
                break;
            }
            case 5: {
                accessibleTextEvent.type = 5;
                break;
            }
            case 6: {
                accessibleTextEvent.type = 6;
                break;
            }
        }
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).scrollText(accessibleTextEvent);
        }
        if (accessibleTextEvent.result == null || !accessibleTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int scrollSubstringToPoint(final int start, final int end, final int n, final int x, final int y) {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.start = start;
        accessibleTextEvent.end = end;
        accessibleTextEvent.type = 7;
        accessibleTextEvent.x = x;
        accessibleTextEvent.y = y;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).scrollText(accessibleTextEvent);
        }
        if (accessibleTextEvent.result == null || !accessibleTextEvent.result.equals("OK")) {
            return -2147024809;
        }
        return 0;
    }
    
    int get_newText(final int n) {
        String s = null;
        int intValue = 0;
        int intValue2 = 0;
        if (this.textInserted != null) {
            s = (String)this.textInserted[3];
            intValue = (int)this.textInserted[1];
            intValue2 = (int)this.textInserted[2];
        }
        this.setString(n, s);
        OS.MoveMemory(n + OS.PTR_SIZEOF, new int[] { intValue }, 4);
        OS.MoveMemory(n + OS.PTR_SIZEOF + 4, new int[] { intValue2 }, 4);
        if (this.textInserted == null) {
            return 1;
        }
        return 0;
    }
    
    int get_oldText(final int n) {
        String s = null;
        int intValue = 0;
        int intValue2 = 0;
        if (this.textDeleted != null) {
            s = (String)this.textDeleted[3];
            intValue = (int)this.textDeleted[1];
            intValue2 = (int)this.textDeleted[2];
        }
        this.setString(n, s);
        OS.MoveMemory(n + OS.PTR_SIZEOF, new int[] { intValue }, 4);
        OS.MoveMemory(n + OS.PTR_SIZEOF + 4, new int[] { intValue2 }, 4);
        if (this.textDeleted == null) {
            return 1;
        }
        return 0;
    }
    
    int get_currentValue(final int n) {
        final AccessibleValueEvent accessibleValueEvent = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListeners.size(); ++i) {
            ((AccessibleValueListener)this.accessibleValueListeners.elementAt(i)).getCurrentValue(accessibleValueEvent);
        }
        this.setNumberVARIANT(n, accessibleValueEvent.value);
        return 0;
    }
    
    int setCurrentValue(final int n) {
        final AccessibleValueEvent currentValue = new AccessibleValueEvent(this);
        currentValue.value = this.getNumberVARIANT(n);
        for (int i = 0; i < this.accessibleValueListeners.size(); ++i) {
            ((AccessibleValueListener)this.accessibleValueListeners.elementAt(i)).setCurrentValue(currentValue);
        }
        return 0;
    }
    
    int get_maximumValue(final int n) {
        final AccessibleValueEvent accessibleValueEvent = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListeners.size(); ++i) {
            ((AccessibleValueListener)this.accessibleValueListeners.elementAt(i)).getMaximumValue(accessibleValueEvent);
        }
        this.setNumberVARIANT(n, accessibleValueEvent.value);
        return 0;
    }
    
    int get_minimumValue(final int n) {
        final AccessibleValueEvent accessibleValueEvent = new AccessibleValueEvent(this);
        for (int i = 0; i < this.accessibleValueListeners.size(); ++i) {
            ((AccessibleValueListener)this.accessibleValueListeners.elementAt(i)).getMinimumValue(accessibleValueEvent);
        }
        this.setNumberVARIANT(n, accessibleValueEvent.value);
        return 0;
    }
    
    int eventChildID() {
        if (this.parent == null) {
            return 0;
        }
        if (this.uniqueID == -1) {
            this.uniqueID = Accessible.UniqueID--;
        }
        return this.uniqueID;
    }
    
    void checkUniqueID(final int n) {
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = n;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getChild(accessibleControlEvent);
        }
        final Accessible accessible = accessibleControlEvent.accessible;
        if (accessible != null && accessible.uniqueID == -1) {
            accessible.uniqueID = n;
        }
    }
    
    int childIDToOs(final int n) {
        if (n == -1) {
            return 0;
        }
        int sendMessage = n + 1;
        if (this.control instanceof Tree) {
            if (OS.COMCTL32_MAJOR < 6) {
                sendMessage = n;
            }
            else {
                sendMessage = OS.SendMessage(this.control.handle, 4395, n, 0);
            }
        }
        this.checkUniqueID(sendMessage);
        return sendMessage;
    }
    
    int osToChildID(final int n) {
        if (n == 0) {
            return -1;
        }
        if (!(this.control instanceof Tree)) {
            return n - 1;
        }
        if (OS.COMCTL32_MAJOR < 6) {
            return n;
        }
        return OS.SendMessage(this.control.handle, 4394, n, 0);
    }
    
    int stateToOs(final int n) {
        int n2 = 0;
        if ((n & 0x2) != 0x0) {
            n2 |= 0x2;
        }
        if ((n & 0x200000) != 0x0) {
            n2 |= 0x200000;
        }
        if ((n & 0x1000000) != 0x0) {
            n2 |= 0x1000000;
        }
        if ((n & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        if ((n & 0x100000) != 0x0) {
            n2 |= 0x100000;
        }
        if ((n & 0x8) != 0x0) {
            n2 |= 0x8;
        }
        if ((n & 0x10) != 0x0) {
            n2 |= 0x10;
        }
        if ((n & 0x200) != 0x0) {
            n2 |= 0x200;
        }
        if ((n & 0x400) != 0x0) {
            n2 |= 0x400;
        }
        if ((n & 0x80) != 0x0) {
            n2 |= 0x80;
        }
        if ((n & 0x800) != 0x0) {
            n2 |= 0x800;
        }
        if ((n & 0x40) != 0x0) {
            n2 |= 0x40;
        }
        if ((n & 0x8000) != 0x0) {
            n2 |= 0x8000;
        }
        if ((n & 0x10000) != 0x0) {
            n2 |= 0x10000;
        }
        if ((n & 0x20000) != 0x0) {
            n2 |= 0x20000;
        }
        if ((n & 0x400000) != 0x0) {
            n2 |= 0x400000;
        }
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        return n2;
    }
    
    int osToState(final int n) {
        int n2 = 0;
        if ((n & 0x2) != 0x0) {
            n2 |= 0x2;
        }
        if ((n & 0x200000) != 0x0) {
            n2 |= 0x200000;
        }
        if ((n & 0x1000000) != 0x0) {
            n2 |= 0x1000000;
        }
        if ((n & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        if ((n & 0x100000) != 0x0) {
            n2 |= 0x100000;
        }
        if ((n & 0x8) != 0x0) {
            n2 |= 0x8;
        }
        if ((n & 0x10) != 0x0) {
            n2 |= 0x10;
        }
        if ((n & 0x200) != 0x0) {
            n2 |= 0x200;
        }
        if ((n & 0x400) != 0x0) {
            n2 |= 0x400;
        }
        if ((n & 0x80) != 0x0) {
            n2 |= 0x80;
        }
        if ((n & 0x800) != 0x0) {
            n2 |= 0x800;
        }
        if ((n & 0x40) != 0x0) {
            n2 |= 0x40;
        }
        if ((n & 0x8000) != 0x0) {
            n2 |= 0x8000;
        }
        if ((n & 0x10000) != 0x0) {
            n2 |= 0x10000;
        }
        if ((n & 0x20000) != 0x0) {
            n2 |= 0x20000;
        }
        if ((n & 0x400000) != 0x0) {
            n2 |= 0x400000;
        }
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        return n2;
    }
    
    int roleToOs(final int n) {
        switch (n) {
            case 10: {
                return 10;
            }
            case 9: {
                return 9;
            }
            case 2: {
                return 2;
            }
            case 11: {
                return 11;
            }
            case 12: {
                return 12;
            }
            case 21: {
                return 21;
            }
            case 13: {
                return 13;
            }
            case 3: {
                return 3;
            }
            case 18: {
                return 18;
            }
            case 41: {
                return 41;
            }
            case 43: {
                return 43;
            }
            case 44: {
                return 44;
            }
            case 45: {
                return 45;
            }
            case 62: {
                return 62;
            }
            case 46: {
                return 46;
            }
            case 42: {
                return 42;
            }
            case 22: {
                return 22;
            }
            case 33: {
                return 33;
            }
            case 34: {
                return 34;
            }
            case 24: {
                return 24;
            }
            case 29: {
                return 29;
            }
            case 25: {
                return 25;
            }
            case 26: {
                return 26;
            }
            case 35: {
                return 35;
            }
            case 36: {
                return 36;
            }
            case 60: {
                return 60;
            }
            case 37: {
                return 37;
            }
            case 48: {
                return 48;
            }
            case 51: {
                return 51;
            }
            case 30: {
                return 30;
            }
            case 8: {
                return 8;
            }
            case 54: {
                return 54;
            }
            case 27: {
                return 27;
            }
            case 15: {
                return 15;
            }
            case 40: {
                return 40;
            }
            case 20: {
                return 20;
            }
            case 28: {
                return 28;
            }
            case 52: {
                return 52;
            }
            case 23: {
                return 23;
            }
            case 61: {
                return 61;
            }
            case 47: {
                return 47;
            }
            case 1025: {
                return 10;
            }
            case 1027: {
                return 12;
            }
            case 1073: {
                return 12;
            }
            case 1029: {
                return 47;
            }
            case 1038: {
                return 10;
            }
            case 1040: {
                return 10;
            }
            case 1043: {
                return 10;
            }
            case 1044: {
                return 10;
            }
            case 1053: {
                return 10;
            }
            case 1054: {
                return 10;
            }
            case 1060: {
                return 10;
            }
            default: {
                return 10;
            }
        }
    }
    
    int osToRole(final int n) {
        switch (n) {
            case 10: {
                return 10;
            }
            case 9: {
                return 9;
            }
            case 2: {
                return 2;
            }
            case 11: {
                return 11;
            }
            case 12: {
                return 12;
            }
            case 21: {
                return 21;
            }
            case 13: {
                return 13;
            }
            case 3: {
                return 3;
            }
            case 18: {
                return 18;
            }
            case 41: {
                return 41;
            }
            case 43: {
                return 43;
            }
            case 44: {
                return 44;
            }
            case 45: {
                return 45;
            }
            case 62: {
                return 62;
            }
            case 46: {
                return 46;
            }
            case 42: {
                return 42;
            }
            case 22: {
                return 22;
            }
            case 33: {
                return 33;
            }
            case 34: {
                return 34;
            }
            case 24: {
                return 24;
            }
            case 29: {
                return 29;
            }
            case 25: {
                return 25;
            }
            case 26: {
                return 26;
            }
            case 35: {
                return 35;
            }
            case 36: {
                return 36;
            }
            case 60: {
                return 60;
            }
            case 37: {
                return 37;
            }
            case 48: {
                return 48;
            }
            case 51: {
                return 51;
            }
            case 30: {
                return 30;
            }
            case 8: {
                return 8;
            }
            case 54: {
                return 54;
            }
            case 27: {
                return 27;
            }
            case 15: {
                return 15;
            }
            case 40: {
                return 40;
            }
            case 20: {
                return 20;
            }
            case 28: {
                return 28;
            }
            case 52: {
                return 52;
            }
            case 23: {
                return 23;
            }
            case 61: {
                return 61;
            }
            case 47: {
                return 47;
            }
            default: {
                return 10;
            }
        }
    }
    
    Color colorFromString(final String s) {
        try {
            final int index = s.indexOf(40);
            final int index2 = s.indexOf(44);
            final int index3 = s.indexOf(44, index2 + 1);
            return new Color(this.control.getDisplay(), Integer.parseInt(s.substring(index + 1, index2)), Integer.parseInt(s.substring(index2 + 1, index3)), Integer.parseInt(s.substring(index3 + 1, s.indexOf(41))));
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    int getCaretOffset() {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.offset = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextListener)this.accessibleTextExtendedListeners.elementAt(i)).getCaretOffset(accessibleTextEvent);
        }
        if (accessibleTextEvent.offset == -1) {
            for (int j = 0; j < this.accessibleTextListeners.size(); ++j) {
                accessibleTextEvent.childID = -1;
                ((AccessibleTextListener)this.accessibleTextListeners.elementAt(j)).getCaretOffset(accessibleTextEvent);
            }
        }
        return accessibleTextEvent.offset;
    }
    
    int getCharacterCount() {
        final AccessibleTextEvent accessibleTextEvent = new AccessibleTextEvent(this);
        accessibleTextEvent.count = -1;
        for (int i = 0; i < this.accessibleTextExtendedListeners.size(); ++i) {
            ((AccessibleTextExtendedListener)this.accessibleTextExtendedListeners.elementAt(i)).getCharacterCount(accessibleTextEvent);
        }
        if (accessibleTextEvent.count == -1) {
            final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
            accessibleControlEvent.childID = -1;
            for (int j = 0; j < this.accessibleControlListeners.size(); ++j) {
                final AccessibleControlListener accessibleControlListener = this.accessibleControlListeners.elementAt(j);
                accessibleControlListener.getRole(accessibleControlEvent);
                accessibleControlListener.getValue(accessibleControlEvent);
            }
            accessibleTextEvent.count = ((accessibleControlEvent.detail == 42 && accessibleControlEvent.result != null) ? accessibleControlEvent.result.length() : 0);
        }
        return accessibleTextEvent.count;
    }
    
    int getRelationCount() {
        int n = 0;
        for (int i = 0; i < 15; ++i) {
            if (this.relations[i] != null) {
                ++n;
            }
        }
        return n;
    }
    
    int getRole() {
        final AccessibleControlEvent accessibleControlEvent = new AccessibleControlEvent(this);
        accessibleControlEvent.childID = -1;
        for (int i = 0; i < this.accessibleControlListeners.size(); ++i) {
            ((AccessibleControlListener)this.accessibleControlListeners.elementAt(i)).getRole(accessibleControlEvent);
        }
        return accessibleControlEvent.detail;
    }
    
    int getDefaultRole() {
        int lVal = 10;
        if (this.iaccessible != null) {
            final int globalAlloc = OS.GlobalAlloc(64, VARIANT.sizeof);
            this.setIntVARIANT(globalAlloc, (short)3, 0);
            final int globalAlloc2 = OS.GlobalAlloc(64, VARIANT.sizeof);
            if (this.iaccessible.get_accRole(globalAlloc, globalAlloc2) == 0) {
                final VARIANT variant = this.getVARIANT(globalAlloc2);
                if (variant.vt == 3) {
                    lVal = variant.lVal;
                }
            }
            OS.GlobalFree(globalAlloc);
            OS.GlobalFree(globalAlloc2);
        }
        return lVal;
    }
    
    String getString(final int n) {
        final int[] array = { 0 };
        OS.MoveMemory(array, n, OS.PTR_SIZEOF);
        final int sysStringByteLen = COM.SysStringByteLen(array[0]);
        if (sysStringByteLen == 0) {
            return "";
        }
        final char[] array2 = new char[(sysStringByteLen + 1) / 2];
        OS.MoveMemory(array2, array[0], sysStringByteLen);
        return new String(array2);
    }
    
    VARIANT getVARIANT(final int n) {
        final VARIANT variant = new VARIANT();
        COM.MoveMemory(variant, n, VARIANT.sizeof);
        return variant;
    }
    
    Number getNumberVARIANT(final int n) {
        final VARIANT variant = new VARIANT();
        COM.MoveMemory(variant, n, VARIANT.sizeof);
        if (variant.vt == 20) {
            return new Long(variant.lVal);
        }
        return new Integer(variant.lVal);
    }
    
    void setIntVARIANT(final int n, final short n2, final int n3) {
        if (n2 == 3 || n2 == 0) {
            OS.MoveMemory(n, new short[] { n2 }, 2);
            OS.MoveMemory(n + 8, new int[] { n3 }, 4);
        }
    }
    
    void setPtrVARIANT(final int n, final short n2, final int n3) {
        if (n2 == 9 || n2 == 13) {
            OS.MoveMemory(n, new short[] { n2 }, 2);
            OS.MoveMemory(n + 8, new int[] { n3 }, OS.PTR_SIZEOF);
        }
    }
    
    void setNumberVARIANT(final int n, final Number n2) {
        if (n2 == null) {
            OS.MoveMemory(n, new short[1], 2);
            OS.MoveMemory(n + 8, new int[1], 4);
        }
        else if (n2 instanceof Double) {
            OS.MoveMemory(n, new short[] { 5 }, 2);
            OS.MoveMemory(n + 8, new double[] { n2.doubleValue() }, 8);
        }
        else if (n2 instanceof Float) {
            OS.MoveMemory(n, new short[] { 4 }, 2);
            OS.MoveMemory(n + 8, new float[] { n2.floatValue() }, 4);
        }
        else if (n2 instanceof Long) {
            OS.MoveMemory(n, new short[] { 20 }, 2);
            OS.MoveMemory(n + 8, new long[] { n2.longValue() }, 8);
        }
        else {
            OS.MoveMemory(n, new short[] { 3 }, 2);
            OS.MoveMemory(n + 8, new int[] { n2.intValue() }, 4);
        }
    }
    
    void setString(final int n, final String s) {
        int sysAllocString = 0;
        if (s != null) {
            sysAllocString = COM.SysAllocString((String.valueOf(s) + "\u0000").toCharArray());
        }
        OS.MoveMemory(n, new int[] { sysAllocString }, OS.PTR_SIZEOF);
    }
    
    void setStringVARIANT(final int n, final String s) {
        int sysAllocString = 0;
        if (s != null) {
            sysAllocString = COM.SysAllocString((String.valueOf(s) + "\u0000").toCharArray());
        }
        OS.MoveMemory(n, new short[] { (sysAllocString == 0) ? 0 : 8 }, 2);
        OS.MoveMemory(n + 8, new int[] { sysAllocString }, OS.PTR_SIZEOF);
    }
    
    void checkWidget() {
        if (!this.isValidThread()) {
            SWT.error(22);
        }
        if (this.control.isDisposed()) {
            SWT.error(24);
        }
    }
    
    boolean isValidThread() {
        return this.control.getDisplay().getThread() == Thread.currentThread();
    }
    
    static void print(final String s) {
    }
    
    String getRoleString(final int n) {
        return "Unknown role (" + n + ")";
    }
    
    String getStateString(final int n) {
        if (n == 0) {
            return " no state bits set";
        }
        return new StringBuffer().toString();
    }
    
    String getIA2StatesString(final int n) {
        if (n == 0) {
            return " no state bits set";
        }
        return new StringBuffer().toString();
    }
    
    String getEventString(final int n) {
        return "Unknown event (" + n + ")";
    }
    
    private String hresult(final int n) {
        return " HRESULT=" + n;
    }
    
    boolean interesting(final GUID guid) {
        return false;
    }
    
    String guidString(final GUID guid) {
        return guid.toString();
    }
    
    static GUID IIDFromString(final String s) {
        return null;
    }
    
    public String toString() {
        return super.toString();
    }
}
