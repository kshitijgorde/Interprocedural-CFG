// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public final class GridLayout extends Layout
{
    public int numColumns;
    public boolean makeColumnsEqualWidth;
    public int marginWidth;
    public int marginHeight;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    public int horizontalSpacing;
    public int verticalSpacing;
    
    public GridLayout() {
        this.numColumns = 1;
        this.makeColumnsEqualWidth = false;
        this.marginWidth = 5;
        this.marginHeight = 5;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.horizontalSpacing = 5;
        this.verticalSpacing = 5;
    }
    
    public GridLayout(final int numColumns, final boolean makeColumnsEqualWidth) {
        this.numColumns = 1;
        this.makeColumnsEqualWidth = false;
        this.marginWidth = 5;
        this.marginHeight = 5;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.horizontalSpacing = 5;
        this.verticalSpacing = 5;
        this.numColumns = numColumns;
        this.makeColumnsEqualWidth = makeColumnsEqualWidth;
    }
    
    protected Point computeSize(final Composite composite, final int x, final int y, final boolean b) {
        final Point layout = this.layout(composite, false, 0, 0, x, y, b);
        if (x != -1) {
            layout.x = x;
        }
        if (y != -1) {
            layout.y = y;
        }
        return layout;
    }
    
    protected boolean flushCache(final Control control) {
        final Object layoutData = control.getLayoutData();
        if (layoutData != null) {
            ((GridData)layoutData).flushCache();
        }
        return true;
    }
    
    GridData getData(final Control[][] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final Control control = array[n][n2];
        if (control != null) {
            final GridData gridData = (GridData)control.getLayoutData();
            final int max = Math.max(1, Math.min(gridData.horizontalSpan, n4));
            final int max2 = Math.max(1, gridData.verticalSpan);
            final int n5 = b ? (n + max2 - 1) : (n - max2 + 1);
            final int n6 = b ? (n2 + max - 1) : (n2 - max + 1);
            if (n5 >= 0 && n5 < n3 && n6 >= 0 && n6 < n4 && control == array[n5][n6]) {
                return gridData;
            }
        }
        return null;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final Rectangle clientArea = composite.getClientArea();
        this.layout(composite, true, clientArea.x, clientArea.y, clientArea.width, clientArea.height, b);
    }
    
    Point layout(final Composite composite, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        if (this.numColumns < 1) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        final Control[] children = composite.getChildren();
        int n5 = 0;
        for (int i = 0; i < children.length; ++i) {
            final GridData gridData = (GridData)children[i].getLayoutData();
            if (gridData == null || !gridData.exclude) {
                children[n5++] = children[i];
            }
        }
        if (n5 == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        for (final Control control : children) {
            GridData gridData2 = (GridData)control.getLayoutData();
            if (gridData2 == null) {
                control.setLayoutData(gridData2 = new GridData());
            }
            if (b2) {
                gridData2.flushCache();
            }
            gridData2.computeSize(control, gridData2.widthHint, gridData2.heightHint, b2);
            if (gridData2.grabExcessHorizontalSpace && gridData2.minimumWidth > 0 && gridData2.cacheWidth < gridData2.minimumWidth) {
                int width;
                if (control instanceof Scrollable) {
                    width = ((Scrollable)control).computeTrim(0, 0, 0, 0).width;
                }
                else {
                    width = control.getBorderWidth() * 2;
                }
                final GridData gridData3 = gridData2;
                final GridData gridData4 = gridData2;
                final int n6 = -1;
                gridData4.cacheHeight = n6;
                gridData3.cacheWidth = n6;
                gridData2.computeSize(control, Math.max(0, gridData2.minimumWidth - width), gridData2.heightHint, false);
            }
            if (gridData2.grabExcessVerticalSpace && gridData2.minimumHeight > 0) {
                gridData2.cacheHeight = Math.max(gridData2.cacheHeight, gridData2.minimumHeight);
            }
        }
        int n7 = 0;
        int n8 = 0;
        int max = 0;
        final int numColumns = this.numColumns;
        Control[][] array = new Control[4][numColumns];
        for (final Control control2 : children) {
            final GridData gridData5 = (GridData)control2.getLayoutData();
            final int max2 = Math.max(1, Math.min(gridData5.horizontalSpan, numColumns));
            final int max3 = Math.max(1, gridData5.verticalSpan);
            while (true) {
                final int n9 = n7 + max3;
                if (n9 >= array.length) {
                    final Control[][] array2 = new Control[n9 + 4][numColumns];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array = array2;
                }
                if (array[n7] == null) {
                    array[n7] = new Control[numColumns];
                }
                while (n8 < numColumns && array[n7][n8] != null) {
                    ++n8;
                }
                final int n10 = n8 + max2;
                if (n10 <= numColumns) {
                    int n11;
                    for (n11 = n8; n11 < n10 && array[n7][n11] == null; ++n11) {}
                    if (n11 == n10) {
                        break;
                    }
                    n8 = n11;
                }
                if (n8 + max2 >= numColumns) {
                    n8 = 0;
                    ++n7;
                }
            }
            for (int l = 0; l < max3; ++l) {
                if (array[n7 + l] == null) {
                    array[n7 + l] = new Control[numColumns];
                }
                for (int n12 = 0; n12 < max2; ++n12) {
                    array[n7 + l][n8 + n12] = control2;
                }
            }
            max = Math.max(max, n7 + max3);
            n8 += max2;
        }
        final int n13 = n3 - this.horizontalSpacing * (numColumns - 1) - (this.marginLeft + this.marginWidth * 2 + this.marginRight);
        int n14 = 0;
        final int[] array3 = new int[numColumns];
        final int[] array4 = new int[numColumns];
        final boolean[] array5 = new boolean[numColumns];
        for (int n15 = 0; n15 < numColumns; ++n15) {
            for (int n16 = 0; n16 < max; ++n16) {
                final GridData data = this.getData(array, n16, n15, max, numColumns, true);
                if (data != null && Math.max(1, Math.min(data.horizontalSpan, numColumns)) == 1) {
                    array3[n15] = Math.max(array3[n15], data.cacheWidth + data.horizontalIndent);
                    if (data.grabExcessHorizontalSpace) {
                        if (!array5[n15]) {
                            ++n14;
                        }
                        array5[n15] = true;
                    }
                    if (!data.grabExcessHorizontalSpace || data.minimumWidth != 0) {
                        array4[n15] = Math.max(array4[n15], ((!data.grabExcessHorizontalSpace || data.minimumWidth == -1) ? data.cacheWidth : data.minimumWidth) + data.horizontalIndent);
                    }
                }
            }
            for (int n17 = 0; n17 < max; ++n17) {
                final GridData data2 = this.getData(array, n17, n15, max, numColumns, false);
                if (data2 != null) {
                    final int max4 = Math.max(1, Math.min(data2.horizontalSpan, numColumns));
                    if (max4 > 1) {
                        int n18 = 0;
                        int n19 = 0;
                        int n20 = 0;
                        for (int n21 = 0; n21 < max4; ++n21) {
                            n18 += array3[n15 - n21];
                            n19 += array4[n15 - n21];
                            if (array5[n15 - n21]) {
                                ++n20;
                            }
                        }
                        if (data2.grabExcessHorizontalSpace && n20 == 0) {
                            ++n14;
                            array5[n15] = true;
                        }
                        final int n22 = data2.cacheWidth + data2.horizontalIndent - n18 - (max4 - 1) * this.horizontalSpacing;
                        if (n22 > 0) {
                            if (this.makeColumnsEqualWidth) {
                                final int n23 = (n22 + n18) / max4;
                                final int n24 = (n22 + n18) % max4;
                                int n25 = -1;
                                for (int n26 = 0; n26 < max4; ++n26) {
                                    array3[n25 = n15 - n26] = Math.max(n23, array3[n15 - n26]);
                                }
                                if (n25 > -1) {
                                    final int[] array6 = array3;
                                    final int n27 = n25;
                                    array6[n27] += n24;
                                }
                            }
                            else if (n20 == 0) {
                                final int[] array7 = array3;
                                final int n28 = n15;
                                array7[n28] += n22;
                            }
                            else {
                                final int n29 = n22 / n20;
                                final int n30 = n22 % n20;
                                int n31 = -1;
                                for (int n32 = 0; n32 < max4; ++n32) {
                                    if (array5[n15 - n32]) {
                                        final int[] array8 = array3;
                                        final int n33 = n31 = n15 - n32;
                                        array8[n33] += n29;
                                    }
                                }
                                if (n31 > -1) {
                                    final int[] array9 = array3;
                                    final int n34 = n31;
                                    array9[n34] += n30;
                                }
                            }
                        }
                        if (!data2.grabExcessHorizontalSpace || data2.minimumWidth != 0) {
                            final int n35 = ((!data2.grabExcessHorizontalSpace || data2.minimumWidth == -1) ? data2.cacheWidth : data2.minimumWidth) + (data2.horizontalIndent - n19 - (max4 - 1) * this.horizontalSpacing);
                            if (n35 > 0) {
                                if (n20 == 0) {
                                    final int[] array10 = array4;
                                    final int n36 = n15;
                                    array10[n36] += n35;
                                }
                                else {
                                    final int n37 = n35 / n20;
                                    final int n38 = n35 % n20;
                                    int n39 = -1;
                                    for (int n40 = 0; n40 < max4; ++n40) {
                                        if (array5[n15 - n40]) {
                                            final int[] array11 = array4;
                                            final int n41 = n39 = n15 - n40;
                                            array11[n41] += n37;
                                        }
                                    }
                                    if (n39 > -1) {
                                        final int[] array12 = array4;
                                        final int n42 = n39;
                                        array12[n42] += n38;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.makeColumnsEqualWidth) {
            int max5 = 0;
            int max6 = 0;
            for (int n43 = 0; n43 < numColumns; ++n43) {
                max5 = Math.max(max5, array4[n43]);
                max6 = Math.max(max6, array3[n43]);
            }
            final int n44 = (n3 == -1 || n14 == 0) ? max6 : Math.max(max5, n13 / numColumns);
            for (int n45 = 0; n45 < numColumns; ++n45) {
                array5[n45] = (n14 > 0);
                array3[n45] = n44;
            }
        }
        else if (n3 != -1 && n14 > 0) {
            int n46 = 0;
            for (int n47 = 0; n47 < numColumns; ++n47) {
                n46 += array3[n47];
            }
            int n48 = n14;
            int n49 = (n13 - n46) / n48;
            int n50 = (n13 - n46) % n48;
            int n51 = -1;
            while (n46 != n13) {
                for (int n52 = 0; n52 < numColumns; ++n52) {
                    if (array5[n52]) {
                        if (array3[n52] + n49 > array4[n52]) {
                            array3[n51 = n52] = array3[n52] + n49;
                        }
                        else {
                            array3[n52] = array4[n52];
                            array5[n52] = false;
                            --n48;
                        }
                    }
                }
                if (n51 > -1) {
                    final int[] array13 = array3;
                    final int n53 = n51;
                    array13[n53] += n50;
                }
                for (int n54 = 0; n54 < numColumns; ++n54) {
                    for (int n55 = 0; n55 < max; ++n55) {
                        final GridData data3 = this.getData(array, n55, n54, max, numColumns, false);
                        if (data3 != null) {
                            final int max7 = Math.max(1, Math.min(data3.horizontalSpan, numColumns));
                            if (max7 > 1 && (!data3.grabExcessHorizontalSpace || data3.minimumWidth != 0)) {
                                int n56 = 0;
                                int n57 = 0;
                                for (int n58 = 0; n58 < max7; ++n58) {
                                    n56 += array3[n54 - n58];
                                    if (array5[n54 - n58]) {
                                        ++n57;
                                    }
                                }
                                final int n59 = ((!data3.grabExcessHorizontalSpace || data3.minimumWidth == -1) ? data3.cacheWidth : data3.minimumWidth) + (data3.horizontalIndent - n56 - (max7 - 1) * this.horizontalSpacing);
                                if (n59 > 0) {
                                    if (n57 == 0) {
                                        final int[] array14 = array3;
                                        final int n60 = n54;
                                        array14[n60] += n59;
                                    }
                                    else {
                                        final int n61 = n59 / n57;
                                        final int n62 = n59 % n57;
                                        int n63 = -1;
                                        for (int n64 = 0; n64 < max7; ++n64) {
                                            if (array5[n54 - n64]) {
                                                final int[] array15 = array3;
                                                final int n65 = n63 = n54 - n64;
                                                array15[n65] += n61;
                                            }
                                        }
                                        if (n63 > -1) {
                                            final int[] array16 = array3;
                                            final int n66 = n63;
                                            array16[n66] += n62;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (n48 == 0) {
                    break;
                }
                n46 = 0;
                for (int n67 = 0; n67 < numColumns; ++n67) {
                    n46 += array3[n67];
                }
                n49 = (n13 - n46) / n48;
                n50 = (n13 - n46) % n48;
                n51 = -1;
            }
        }
        GridData[] array17 = null;
        int n68 = 0;
        if (n3 != -1) {
            for (int n69 = 0; n69 < numColumns; ++n69) {
                for (int n70 = 0; n70 < max; ++n70) {
                    final GridData data4 = this.getData(array, n70, n69, max, numColumns, false);
                    if (data4 != null && data4.heightHint == -1) {
                        final Control control3 = array[n70][n69];
                        final int max8 = Math.max(1, Math.min(data4.horizontalSpan, numColumns));
                        int n71 = 0;
                        for (int n72 = 0; n72 < max8; ++n72) {
                            n71 += array3[n69 - n72];
                        }
                        final int n73 = n71 + ((max8 - 1) * this.horizontalSpacing - data4.horizontalIndent);
                        if ((n73 != data4.cacheWidth && data4.horizontalAlignment == 4) || data4.cacheWidth > n73) {
                            int width2;
                            if (control3 instanceof Scrollable) {
                                width2 = ((Scrollable)control3).computeTrim(0, 0, 0, 0).width;
                            }
                            else {
                                width2 = control3.getBorderWidth() * 2;
                            }
                            final GridData gridData6 = data4;
                            final GridData gridData7 = data4;
                            final int n74 = -1;
                            gridData7.cacheHeight = n74;
                            gridData6.cacheWidth = n74;
                            data4.computeSize(control3, Math.max(0, n73 - width2), data4.heightHint, false);
                            if (data4.grabExcessVerticalSpace && data4.minimumHeight > 0) {
                                data4.cacheHeight = Math.max(data4.cacheHeight, data4.minimumHeight);
                            }
                            if (array17 == null) {
                                array17 = new GridData[n5];
                            }
                            array17[n68++] = data4;
                        }
                    }
                }
            }
        }
        final int n75 = n4 - this.verticalSpacing * (max - 1) - (this.marginTop + this.marginHeight * 2 + this.marginBottom);
        int n76 = 0;
        final int[] array18 = new int[max];
        final int[] array19 = new int[max];
        final boolean[] array20 = new boolean[max];
        for (int n77 = 0; n77 < max; ++n77) {
            for (int n78 = 0; n78 < numColumns; ++n78) {
                final GridData data5 = this.getData(array, n77, n78, max, numColumns, true);
                if (data5 != null && Math.max(1, Math.min(data5.verticalSpan, max)) == 1) {
                    array18[n77] = Math.max(array18[n77], data5.cacheHeight + data5.verticalIndent);
                    if (data5.grabExcessVerticalSpace) {
                        if (!array20[n77]) {
                            ++n76;
                        }
                        array20[n77] = true;
                    }
                    if (!data5.grabExcessVerticalSpace || data5.minimumHeight != 0) {
                        array19[n77] = Math.max(array19[n77], ((!data5.grabExcessVerticalSpace || data5.minimumHeight == -1) ? data5.cacheHeight : data5.minimumHeight) + data5.verticalIndent);
                    }
                }
            }
            for (int n79 = 0; n79 < numColumns; ++n79) {
                final GridData data6 = this.getData(array, n77, n79, max, numColumns, false);
                if (data6 != null) {
                    final int max9 = Math.max(1, Math.min(data6.verticalSpan, max));
                    if (max9 > 1) {
                        int n80 = 0;
                        int n81 = 0;
                        int n82 = 0;
                        for (int n83 = 0; n83 < max9; ++n83) {
                            n80 += array18[n77 - n83];
                            n81 += array19[n77 - n83];
                            if (array20[n77 - n83]) {
                                ++n82;
                            }
                        }
                        if (data6.grabExcessVerticalSpace && n82 == 0) {
                            ++n76;
                            array20[n77] = true;
                        }
                        final int n84 = data6.cacheHeight + data6.verticalIndent - n80 - (max9 - 1) * this.verticalSpacing;
                        if (n84 > 0) {
                            if (n82 == 0) {
                                final int[] array21 = array18;
                                final int n85 = n77;
                                array21[n85] += n84;
                            }
                            else {
                                final int n86 = n84 / n82;
                                final int n87 = n84 % n82;
                                int n88 = -1;
                                for (int n89 = 0; n89 < max9; ++n89) {
                                    if (array20[n77 - n89]) {
                                        final int[] array22 = array18;
                                        final int n90 = n88 = n77 - n89;
                                        array22[n90] += n86;
                                    }
                                }
                                if (n88 > -1) {
                                    final int[] array23 = array18;
                                    final int n91 = n88;
                                    array23[n91] += n87;
                                }
                            }
                        }
                        if (!data6.grabExcessVerticalSpace || data6.minimumHeight != 0) {
                            final int n92 = ((!data6.grabExcessVerticalSpace || data6.minimumHeight == -1) ? data6.cacheHeight : data6.minimumHeight) + (data6.verticalIndent - n81 - (max9 - 1) * this.verticalSpacing);
                            if (n92 > 0) {
                                if (n82 == 0) {
                                    final int[] array24 = array19;
                                    final int n93 = n77;
                                    array24[n93] += n92;
                                }
                                else {
                                    final int n94 = n92 / n82;
                                    final int n95 = n92 % n82;
                                    int n96 = -1;
                                    for (int n97 = 0; n97 < max9; ++n97) {
                                        if (array20[n77 - n97]) {
                                            final int[] array25 = array19;
                                            final int n98 = n96 = n77 - n97;
                                            array25[n98] += n94;
                                        }
                                    }
                                    if (n96 > -1) {
                                        final int[] array26 = array19;
                                        final int n99 = n96;
                                        array26[n99] += n95;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n4 != -1 && n76 > 0) {
            int n100 = 0;
            for (int n101 = 0; n101 < max; ++n101) {
                n100 += array18[n101];
            }
            int n102 = n76;
            int n103 = (n75 - n100) / n102;
            int n104 = (n75 - n100) % n102;
            int n105 = -1;
            while (n100 != n75) {
                for (int n106 = 0; n106 < max; ++n106) {
                    if (array20[n106]) {
                        if (array18[n106] + n103 > array19[n106]) {
                            array18[n105 = n106] = array18[n106] + n103;
                        }
                        else {
                            array18[n106] = array19[n106];
                            array20[n106] = false;
                            --n102;
                        }
                    }
                }
                if (n105 > -1) {
                    final int[] array27 = array18;
                    final int n107 = n105;
                    array27[n107] += n104;
                }
                for (int n108 = 0; n108 < max; ++n108) {
                    for (int n109 = 0; n109 < numColumns; ++n109) {
                        final GridData data7 = this.getData(array, n108, n109, max, numColumns, false);
                        if (data7 != null) {
                            final int max10 = Math.max(1, Math.min(data7.verticalSpan, max));
                            if (max10 > 1 && (!data7.grabExcessVerticalSpace || data7.minimumHeight != 0)) {
                                int n110 = 0;
                                int n111 = 0;
                                for (int n112 = 0; n112 < max10; ++n112) {
                                    n110 += array18[n108 - n112];
                                    if (array20[n108 - n112]) {
                                        ++n111;
                                    }
                                }
                                final int n113 = ((!data7.grabExcessVerticalSpace || data7.minimumHeight == -1) ? data7.cacheHeight : data7.minimumHeight) + (data7.verticalIndent - n110 - (max10 - 1) * this.verticalSpacing);
                                if (n113 > 0) {
                                    if (n111 == 0) {
                                        final int[] array28 = array18;
                                        final int n114 = n108;
                                        array28[n114] += n113;
                                    }
                                    else {
                                        final int n115 = n113 / n111;
                                        final int n116 = n113 % n111;
                                        int n117 = -1;
                                        for (int n118 = 0; n118 < max10; ++n118) {
                                            if (array20[n108 - n118]) {
                                                final int[] array29 = array18;
                                                final int n119 = n117 = n108 - n118;
                                                array29[n119] += n115;
                                            }
                                        }
                                        if (n117 > -1) {
                                            final int[] array30 = array18;
                                            final int n120 = n117;
                                            array30[n120] += n116;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (n102 == 0) {
                    break;
                }
                n100 = 0;
                for (int n121 = 0; n121 < max; ++n121) {
                    n100 += array18[n121];
                }
                n103 = (n75 - n100) / n102;
                n104 = (n75 - n100) % n102;
                n105 = -1;
            }
        }
        if (b) {
            int n122 = n2 + this.marginTop + this.marginHeight;
            for (int n123 = 0; n123 < max; ++n123) {
                int n124 = n + this.marginLeft + this.marginWidth;
                for (int n125 = 0; n125 < numColumns; ++n125) {
                    final GridData data8 = this.getData(array, n123, n125, max, numColumns, true);
                    if (data8 != null) {
                        final int max11 = Math.max(1, Math.min(data8.horizontalSpan, numColumns));
                        final int max12 = Math.max(1, data8.verticalSpan);
                        int n126 = 0;
                        int n127 = 0;
                        for (int n128 = 0; n128 < max11; ++n128) {
                            n126 += array3[n125 + n128];
                        }
                        for (int n129 = 0; n129 < max12; ++n129) {
                            n127 += array18[n123 + n129];
                        }
                        final int n130 = n126 + this.horizontalSpacing * (max11 - 1);
                        int n131 = n124 + data8.horizontalIndent;
                        int min = Math.min(data8.cacheWidth, n130);
                        switch (data8.horizontalAlignment) {
                            case 2:
                            case 16777216: {
                                n131 += Math.max(0, (n130 - data8.horizontalIndent - min) / 2);
                                break;
                            }
                            case 3:
                            case 131072:
                            case 16777224: {
                                n131 += Math.max(0, n130 - data8.horizontalIndent - min);
                                break;
                            }
                            case 4: {
                                min = n130 - data8.horizontalIndent;
                                break;
                            }
                        }
                        final int n132 = n127 + this.verticalSpacing * (max12 - 1);
                        int n133 = n122 + data8.verticalIndent;
                        int min2 = Math.min(data8.cacheHeight, n132);
                        switch (data8.verticalAlignment) {
                            case 2:
                            case 16777216: {
                                n133 += Math.max(0, (n132 - data8.verticalIndent - min2) / 2);
                                break;
                            }
                            case 3:
                            case 1024:
                            case 16777224: {
                                n133 += Math.max(0, n132 - data8.verticalIndent - min2);
                                break;
                            }
                            case 4: {
                                min2 = n132 - data8.verticalIndent;
                                break;
                            }
                        }
                        final Control control4 = array[n123][n125];
                        if (control4 != null) {
                            control4.setBounds(n131, n133, min, min2);
                        }
                    }
                    n124 += array3[n125] + this.horizontalSpacing;
                }
                n122 += array18[n123] + this.verticalSpacing;
            }
        }
        for (int n134 = 0; n134 < n68; ++n134) {
            final GridData gridData8 = array17[n134];
            final GridData gridData9 = array17[n134];
            final int n135 = -1;
            gridData9.cacheHeight = n135;
            gridData8.cacheWidth = n135;
        }
        int n136 = 0;
        int n137 = 0;
        for (int n138 = 0; n138 < numColumns; ++n138) {
            n136 += array3[n138];
        }
        for (int n139 = 0; n139 < max; ++n139) {
            n137 += array18[n139];
        }
        return new Point(n136 + (this.horizontalSpacing * (numColumns - 1) + this.marginLeft + this.marginWidth * 2 + this.marginRight), n137 + (this.verticalSpacing * (max - 1) + this.marginTop + this.marginHeight * 2 + this.marginBottom));
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        String s = String.valueOf(this.getName()) + " {";
        if (this.numColumns != 1) {
            s = String.valueOf(s) + "numColumns=" + this.numColumns + " ";
        }
        if (this.makeColumnsEqualWidth) {
            s = String.valueOf(s) + "makeColumnsEqualWidth=" + this.makeColumnsEqualWidth + " ";
        }
        if (this.marginWidth != 0) {
            s = String.valueOf(s) + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            s = String.valueOf(s) + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            s = String.valueOf(s) + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginRight != 0) {
            s = String.valueOf(s) + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginTop != 0) {
            s = String.valueOf(s) + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginBottom != 0) {
            s = String.valueOf(s) + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.horizontalSpacing != 0) {
            s = String.valueOf(s) + "horizontalSpacing=" + this.horizontalSpacing + " ";
        }
        if (this.verticalSpacing != 0) {
            s = String.valueOf(s) + "verticalSpacing=" + this.verticalSpacing + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
