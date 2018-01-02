// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.ole.win32.COMObject;

class Relation
{
    Accessible accessible;
    COMObject objIAccessibleRelation;
    int refCount;
    int type;
    Accessible[] targets;
    static final String[] relationTypeString;
    static final String[] localizedRelationTypeString;
    
    static {
        relationTypeString = new String[] { "controlledBy", "controllerFor", "describedBy", "descriptionFor", "embeddedBy", "embeds", "flowsFrom", "flowsTo", "labelFor", "labelledBy", "memberOf", "nodeChildOf", "parentWindowOf", "popupFor", "subwindowOf" };
        localizedRelationTypeString = new String[] { SWT.getMessage("SWT_Controlled_By"), SWT.getMessage("SWT_Controller_For"), SWT.getMessage("SWT_Described_By"), SWT.getMessage("SWT_Description_For"), SWT.getMessage("SWT_Embedded_By"), SWT.getMessage("SWT_Embeds"), SWT.getMessage("SWT_Flows_From"), SWT.getMessage("SWT_Flows_To"), SWT.getMessage("SWT_Label_For"), SWT.getMessage("SWT_Labelled_By"), SWT.getMessage("SWT_Member_Of"), SWT.getMessage("SWT_Node_Child_Of"), SWT.getMessage("SWT_Parent_Window_Of"), SWT.getMessage("SWT_Popup_For"), SWT.getMessage("SWT_Subwindow_Of") };
    }
    
    Relation(final Accessible accessible, final int type) {
        this.accessible = accessible;
        this.type = type;
        this.targets = new Accessible[0];
        this.AddRef();
    }
    
    int getAddress() {
        if (this.objIAccessibleRelation == null) {
            this.createIAccessibleRelation();
        }
        return this.objIAccessibleRelation.getAddress();
    }
    
    void createIAccessibleRelation() {
        this.objIAccessibleRelation = new COMObject(new int[] { 2, 0, 0, 1, 1, 1, 2, 3 }) {
            public int method0(final int[] array) {
                return Relation.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return Relation.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return Relation.this.Release();
            }
            
            public int method3(final int[] array) {
                return Relation.this.get_relationType(array[0]);
            }
            
            public int method4(final int[] array) {
                return Relation.this.get_localizedRelationType(array[0]);
            }
            
            public int method5(final int[] array) {
                return Relation.this.get_nTargets(array[0]);
            }
            
            public int method6(final int[] array) {
                return Relation.this.get_target(array[0], array[1]);
            }
            
            public int method7(final int[] array) {
                return Relation.this.get_targets(array[0], array[1], array[2]);
            }
        };
    }
    
    int QueryInterface(final int n, final int n2) {
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIAccessibleRelation)) {
            OS.MoveMemory(n2, new int[] { this.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        return -2147467262;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            if (this.objIAccessibleRelation != null) {
                this.objIAccessibleRelation.dispose();
            }
            this.objIAccessibleRelation = null;
        }
        return this.refCount;
    }
    
    int get_relationType(final int n) {
        this.setString(n, Relation.relationTypeString[this.type]);
        return 0;
    }
    
    int get_localizedRelationType(final int n) {
        this.setString(n, Relation.localizedRelationTypeString[this.type]);
        return 0;
    }
    
    int get_nTargets(final int n) {
        OS.MoveMemory(n, new int[] { this.targets.length }, 4);
        return 0;
    }
    
    int get_target(final int n, final int n2) {
        if (n < 0 || n >= this.targets.length) {
            return -2147024809;
        }
        final Accessible accessible = this.targets[n];
        accessible.AddRef();
        OS.MoveMemory(n2, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        return 0;
    }
    
    int get_targets(final int n, final int n2, final int n3) {
        final int min = Math.min(this.targets.length, n);
        for (int i = 0; i < min; ++i) {
            final Accessible accessible = this.targets[i];
            accessible.AddRef();
            OS.MoveMemory(n2 + i * OS.PTR_SIZEOF, new int[] { accessible.getAddress() }, OS.PTR_SIZEOF);
        }
        OS.MoveMemory(n3, new int[] { min }, 4);
        return 0;
    }
    
    void addTarget(final Accessible accessible) {
        if (this.containsTarget(accessible)) {
            return;
        }
        final Accessible[] targets = new Accessible[this.targets.length + 1];
        System.arraycopy(this.targets, 0, targets, 0, this.targets.length);
        targets[this.targets.length] = accessible;
        this.targets = targets;
    }
    
    boolean containsTarget(final Accessible accessible) {
        for (int i = 0; i < this.targets.length; ++i) {
            if (this.targets[i] == accessible) {
                return true;
            }
        }
        return false;
    }
    
    void removeTarget(final Accessible accessible) {
        if (!this.containsTarget(accessible)) {
            return;
        }
        final Accessible[] targets = new Accessible[this.targets.length - 1];
        int n = 0;
        for (int i = 0; i < this.targets.length; ++i) {
            if (this.targets[i] != accessible) {
                targets[n++] = this.targets[i];
            }
        }
        this.targets = targets;
    }
    
    boolean hasTargets() {
        return this.targets.length > 0;
    }
    
    void setString(final int n, final String s) {
        OS.MoveMemory(n, new int[] { COM.SysAllocString((String.valueOf(s) + "\u0000").toCharArray()) }, OS.PTR_SIZEOF);
    }
}
