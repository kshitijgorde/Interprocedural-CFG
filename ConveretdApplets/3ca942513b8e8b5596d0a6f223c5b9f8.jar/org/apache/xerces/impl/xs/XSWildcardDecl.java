// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSWildcard;

public class XSWildcardDecl implements XSWildcard
{
    public static final String ABSENT;
    public short fType;
    public short fProcessContents;
    public String[] fNamespaceList;
    public XSAnnotationImpl fAnnotation;
    private String fDescription;
    
    public XSWildcardDecl() {
        this.fType = 1;
        this.fProcessContents = 1;
        this.fAnnotation = null;
        this.fDescription = null;
    }
    
    public boolean allowNamespace(final String s) {
        if (this.fType == 1) {
            return true;
        }
        if (this.fType == 2) {
            boolean b = false;
            for (int length = this.fNamespaceList.length, n = 0; n < length && !b; ++n) {
                if (s == this.fNamespaceList[n]) {
                    b = true;
                }
            }
            if (!b) {
                return true;
            }
        }
        if (this.fType == 3) {
            for (int length2 = this.fNamespaceList.length, i = 0; i < length2; ++i) {
                if (s == this.fNamespaceList[i]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isSubsetOf(final XSWildcardDecl xsWildcardDecl) {
        if (xsWildcardDecl == null) {
            return false;
        }
        if (xsWildcardDecl.fType == 1) {
            return true;
        }
        if (this.fType == 2 && xsWildcardDecl.fType == 2 && this.fNamespaceList[0] == xsWildcardDecl.fNamespaceList[0]) {
            return true;
        }
        if (this.fType == 3) {
            if (xsWildcardDecl.fType == 3 && this.subset2sets(this.fNamespaceList, xsWildcardDecl.fNamespaceList)) {
                return true;
            }
            if (xsWildcardDecl.fType == 2 && !this.elementInSet(xsWildcardDecl.fNamespaceList[0], this.fNamespaceList) && !this.elementInSet(XSWildcardDecl.ABSENT, this.fNamespaceList)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean weakerProcessContents(final XSWildcardDecl xsWildcardDecl) {
        return (this.fProcessContents == 3 && xsWildcardDecl.fProcessContents == 1) || (this.fProcessContents == 2 && xsWildcardDecl.fProcessContents != 2);
    }
    
    public XSWildcardDecl performUnionWith(final XSWildcardDecl xsWildcardDecl, final short fProcessContents) {
        if (xsWildcardDecl == null) {
            return null;
        }
        final XSWildcardDecl xsWildcardDecl2 = new XSWildcardDecl();
        xsWildcardDecl2.fProcessContents = fProcessContents;
        if (this.areSame(xsWildcardDecl)) {
            xsWildcardDecl2.fType = this.fType;
            xsWildcardDecl2.fNamespaceList = this.fNamespaceList;
        }
        else if (this.fType == 1 || xsWildcardDecl.fType == 1) {
            xsWildcardDecl2.fType = 1;
        }
        else if (this.fType == 3 && xsWildcardDecl.fType == 3) {
            xsWildcardDecl2.fType = 3;
            xsWildcardDecl2.fNamespaceList = this.union2sets(this.fNamespaceList, xsWildcardDecl.fNamespaceList);
        }
        else if (this.fType == 2 && xsWildcardDecl.fType == 2) {
            xsWildcardDecl2.fType = 2;
            (xsWildcardDecl2.fNamespaceList = new String[2])[0] = XSWildcardDecl.ABSENT;
            xsWildcardDecl2.fNamespaceList[1] = XSWildcardDecl.ABSENT;
        }
        else if ((this.fType == 2 && xsWildcardDecl.fType == 3) || (this.fType == 3 && xsWildcardDecl.fType == 2)) {
            String[] array;
            String[] array2;
            if (this.fType == 2) {
                array = this.fNamespaceList;
                array2 = xsWildcardDecl.fNamespaceList;
            }
            else {
                array = xsWildcardDecl.fNamespaceList;
                array2 = this.fNamespaceList;
            }
            final boolean elementInSet = this.elementInSet(XSWildcardDecl.ABSENT, array2);
            if (array[0] != XSWildcardDecl.ABSENT) {
                final boolean elementInSet2 = this.elementInSet(array[0], array2);
                if (elementInSet2 && elementInSet) {
                    xsWildcardDecl2.fType = 1;
                }
                else if (elementInSet2 && !elementInSet) {
                    xsWildcardDecl2.fType = 2;
                    (xsWildcardDecl2.fNamespaceList = new String[2])[0] = XSWildcardDecl.ABSENT;
                    xsWildcardDecl2.fNamespaceList[1] = XSWildcardDecl.ABSENT;
                }
                else {
                    if (!elementInSet2 && elementInSet) {
                        return null;
                    }
                    xsWildcardDecl2.fType = 2;
                    xsWildcardDecl2.fNamespaceList = array;
                }
            }
            else if (elementInSet) {
                xsWildcardDecl2.fType = 1;
            }
            else {
                xsWildcardDecl2.fType = 2;
                xsWildcardDecl2.fNamespaceList = array;
            }
        }
        return xsWildcardDecl2;
    }
    
    public XSWildcardDecl performIntersectionWith(final XSWildcardDecl xsWildcardDecl, final short fProcessContents) {
        if (xsWildcardDecl == null) {
            return null;
        }
        final XSWildcardDecl xsWildcardDecl2 = new XSWildcardDecl();
        xsWildcardDecl2.fProcessContents = fProcessContents;
        if (this.areSame(xsWildcardDecl)) {
            xsWildcardDecl2.fType = this.fType;
            xsWildcardDecl2.fNamespaceList = this.fNamespaceList;
        }
        else if (this.fType == 1 || xsWildcardDecl.fType == 1) {
            XSWildcardDecl xsWildcardDecl3 = this;
            if (this.fType == 1) {
                xsWildcardDecl3 = xsWildcardDecl;
            }
            xsWildcardDecl2.fType = xsWildcardDecl3.fType;
            xsWildcardDecl2.fNamespaceList = xsWildcardDecl3.fNamespaceList;
        }
        else if ((this.fType == 2 && xsWildcardDecl.fType == 3) || (this.fType == 3 && xsWildcardDecl.fType == 2)) {
            String[] array;
            String[] array2;
            if (this.fType == 2) {
                array = this.fNamespaceList;
                array2 = xsWildcardDecl.fNamespaceList;
            }
            else {
                array = xsWildcardDecl.fNamespaceList;
                array2 = this.fNamespaceList;
            }
            final int length = array2.length;
            final String[] array3 = new String[length];
            int n = 0;
            for (int i = 0; i < length; ++i) {
                if (array2[i] != array[0] && array2[i] != XSWildcardDecl.ABSENT) {
                    array3[n++] = array2[i];
                }
            }
            xsWildcardDecl2.fType = 3;
            System.arraycopy(array3, 0, xsWildcardDecl2.fNamespaceList = new String[n], 0, n);
        }
        else if (this.fType == 3 && xsWildcardDecl.fType == 3) {
            xsWildcardDecl2.fType = 3;
            xsWildcardDecl2.fNamespaceList = this.intersect2sets(this.fNamespaceList, xsWildcardDecl.fNamespaceList);
        }
        else if (this.fType == 2 && xsWildcardDecl.fType == 2) {
            if (this.fNamespaceList[0] != XSWildcardDecl.ABSENT && xsWildcardDecl.fNamespaceList[0] != XSWildcardDecl.ABSENT) {
                return null;
            }
            XSWildcardDecl xsWildcardDecl4 = this;
            if (this.fNamespaceList[0] == XSWildcardDecl.ABSENT) {
                xsWildcardDecl4 = xsWildcardDecl;
            }
            xsWildcardDecl2.fType = xsWildcardDecl4.fType;
            xsWildcardDecl2.fNamespaceList = xsWildcardDecl4.fNamespaceList;
        }
        return xsWildcardDecl2;
    }
    
    private boolean areSame(final XSWildcardDecl xsWildcardDecl) {
        if (this.fType == xsWildcardDecl.fType) {
            if (this.fType == 1) {
                return true;
            }
            if (this.fType == 2) {
                return this.fNamespaceList[0] == xsWildcardDecl.fNamespaceList[0];
            }
            if (this.fNamespaceList.length == xsWildcardDecl.fNamespaceList.length) {
                for (int i = 0; i < this.fNamespaceList.length; ++i) {
                    if (!this.elementInSet(this.fNamespaceList[i], xsWildcardDecl.fNamespaceList)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    String[] intersect2sets(final String[] array, final String[] array2) {
        final String[] array3 = new String[Math.min(array.length, array2.length)];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (this.elementInSet(array[i], array2)) {
                array3[n++] = array[i];
            }
        }
        final String[] array4 = new String[n];
        System.arraycopy(array3, 0, array4, 0, n);
        return array4;
    }
    
    String[] union2sets(final String[] array, final String[] array2) {
        final String[] array3 = new String[array.length];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (!this.elementInSet(array[i], array2)) {
                array3[n++] = array[i];
            }
        }
        final String[] array4 = new String[n + array2.length];
        System.arraycopy(array3, 0, array4, 0, n);
        System.arraycopy(array2, 0, array4, n, array2.length);
        return array4;
    }
    
    boolean subset2sets(final String[] array, final String[] array2) {
        for (int i = 0; i < array.length; ++i) {
            if (!this.elementInSet(array[i], array2)) {
                return false;
            }
        }
        return true;
    }
    
    boolean elementInSet(final String s, final String[] array) {
        boolean b = false;
        for (int n = 0; n < array.length && !b; ++n) {
            if (s == array[n]) {
                b = true;
            }
        }
        return b;
    }
    
    public String toString() {
        if (this.fDescription == null) {
            final StringBuffer sb = new StringBuffer();
            sb.append("WC[");
            switch (this.fType) {
                case 1: {
                    sb.append("##any");
                    break;
                }
                case 2: {
                    sb.append("##other");
                    sb.append(":\"");
                    if (this.fNamespaceList[0] != null) {
                        sb.append(this.fNamespaceList[0]);
                    }
                    sb.append("\"");
                    break;
                }
                case 3: {
                    if (this.fNamespaceList.length == 0) {
                        break;
                    }
                    sb.append("\"");
                    if (this.fNamespaceList[0] != null) {
                        sb.append(this.fNamespaceList[0]);
                    }
                    sb.append("\"");
                    for (int i = 1; i < this.fNamespaceList.length; ++i) {
                        sb.append(",\"");
                        if (this.fNamespaceList[i] != null) {
                            sb.append(this.fNamespaceList[i]);
                        }
                        sb.append("\"");
                    }
                    break;
                }
            }
            sb.append("]");
            this.fDescription = sb.toString();
        }
        return this.fDescription;
    }
    
    public short getType() {
        return 9;
    }
    
    public String getName() {
        return null;
    }
    
    public String getNamespace() {
        return null;
    }
    
    public short getConstraintType() {
        return this.fType;
    }
    
    public StringList getNsConstraintList() {
        return new StringListImpl(this.fNamespaceList, (this.fNamespaceList == null) ? 0 : this.fNamespaceList.length);
    }
    
    public short getProcessContents() {
        return this.fProcessContents;
    }
    
    public String getProcessContentsAsString() {
        switch (this.fProcessContents) {
            case 2: {
                return "skip";
            }
            case 3: {
                return "lax";
            }
            case 1: {
                return "strict";
            }
            default: {
                return "invalid value";
            }
        }
    }
    
    public XSAnnotation getAnnotation() {
        return this.fAnnotation;
    }
    
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
    
    static {
        ABSENT = null;
    }
}
