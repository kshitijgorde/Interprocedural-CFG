// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

class SashFormLayout extends Layout
{
    protected Point computeSize(final Composite composite, final int n, final int n2, final boolean b) {
        final SashForm sashForm = (SashForm)composite;
        final Control[] controls = sashForm.getControls(true);
        int max = 0;
        int max2 = 0;
        if (controls.length == 0) {
            if (n != -1) {
                max = n;
            }
            if (n2 != -1) {
                max2 = n2;
            }
            return new Point(max, max2);
        }
        final boolean b2 = sashForm.getOrientation() == 512;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < controls.length; ++i) {
            if (b2) {
                final Point computeSize = controls[i].computeSize(n, -1, b);
                if (computeSize.y > n4) {
                    n3 = i;
                    n4 = computeSize.y;
                }
                max = Math.max(max, computeSize.x);
            }
            else {
                final Point computeSize2 = controls[i].computeSize(-1, n2, b);
                if (computeSize2.x > n4) {
                    n3 = i;
                    n4 = computeSize2.x;
                }
                max2 = Math.max(max2, computeSize2.y);
            }
        }
        final long[] array = new long[controls.length];
        long n5 = 0L;
        for (int j = 0; j < controls.length; ++j) {
            final Object layoutData = controls[j].getLayoutData();
            if (layoutData != null && layoutData instanceof SashFormData) {
                array[j] = ((SashFormData)layoutData).weight;
            }
            else {
                final SashFormData layoutData2 = new SashFormData();
                controls[j].setLayoutData(layoutData2);
                final SashFormData sashFormData = layoutData2;
                final long[] array2 = array;
                final int n6 = j;
                final long weight = 13108L;
                array2[n6] = weight;
                sashFormData.weight = weight;
            }
            n5 += array[j];
        }
        if (array[n3] > 0L) {
            final int n7 = (sashForm.sashes.length > 0) ? (sashForm.SASH_WIDTH + sashForm.sashes[0].getBorderWidth() * 2) : sashForm.SASH_WIDTH;
            if (b2) {
                max2 += (int)(n5 * n4 / array[n3]) + (controls.length - 1) * n7;
            }
            else {
                max += (int)(n5 * n4 / array[n3]) + (controls.length - 1) * n7;
            }
        }
        int n8 = max + sashForm.getBorderWidth() * 2;
        int n9 = max2 + sashForm.getBorderWidth() * 2;
        if (n != -1) {
            n8 = n;
        }
        if (n2 != -1) {
            n9 = n2;
        }
        return new Point(n8, n9);
    }
    
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final SashForm sashForm = (SashForm)composite;
        final Rectangle clientArea = sashForm.getClientArea();
        if (clientArea.width <= 1 || clientArea.height <= 1) {
            return;
        }
        final Control[] controls = sashForm.getControls(true);
        if (sashForm.controls.length == 0 && controls.length == 0) {
            return;
        }
        sashForm.controls = controls;
        final Control[] controls2 = sashForm.controls;
        if (sashForm.maxControl != null && !sashForm.maxControl.isDisposed()) {
            for (int i = 0; i < controls2.length; ++i) {
                if (controls2[i] != sashForm.maxControl) {
                    controls2[i].setBounds(-200, -200, 0, 0);
                }
                else {
                    controls2[i].setBounds(clientArea);
                }
            }
            return;
        }
        if (sashForm.sashes.length < controls2.length - 1) {
            final Sash[] sashes = new Sash[controls2.length - 1];
            System.arraycopy(sashForm.sashes, 0, sashes, 0, sashForm.sashes.length);
            for (int j = sashForm.sashes.length; j < sashes.length; ++j) {
                sashes[j] = sashForm.createSash();
            }
            sashForm.sashes = sashes;
        }
        if (sashForm.sashes.length > controls2.length - 1) {
            if (controls2.length == 0) {
                for (int k = 0; k < sashForm.sashes.length; ++k) {
                    sashForm.sashes[k].dispose();
                }
                sashForm.sashes = new Sash[0];
            }
            else {
                final Sash[] sashes2 = new Sash[controls2.length - 1];
                System.arraycopy(sashForm.sashes, 0, sashes2, 0, sashes2.length);
                for (int l = controls2.length - 1; l < sashForm.sashes.length; ++l) {
                    sashForm.sashes[l].dispose();
                }
                sashForm.sashes = sashes2;
            }
        }
        if (controls2.length == 0) {
            return;
        }
        final Sash[] sashes3 = sashForm.sashes;
        final long[] array = new long[controls2.length];
        long n = 0L;
        for (int n2 = 0; n2 < controls2.length; ++n2) {
            final Object layoutData = controls2[n2].getLayoutData();
            if (layoutData != null && layoutData instanceof SashFormData) {
                array[n2] = ((SashFormData)layoutData).weight;
            }
            else {
                final SashFormData layoutData2 = new SashFormData();
                controls2[n2].setLayoutData(layoutData2);
                final SashFormData sashFormData = layoutData2;
                final long[] array2 = array;
                final int n3 = n2;
                final long weight = 13108L;
                array2[n3] = weight;
                sashFormData.weight = weight;
            }
            n += array[n2];
        }
        final int n4 = (sashes3.length > 0) ? (sashForm.SASH_WIDTH + sashes3[0].getBorderWidth() * 2) : sashForm.SASH_WIDTH;
        if (sashForm.getOrientation() == 256) {
            final int n5 = (int)(array[0] * (clientArea.width - sashes3.length * n4) / n);
            final int x = clientArea.x;
            controls2[0].setBounds(x, clientArea.y, n5, clientArea.height);
            int n6 = x + n5;
            for (int n7 = 1; n7 < controls2.length - 1; ++n7) {
                sashes3[n7 - 1].setBounds(n6, clientArea.y, n4, clientArea.height);
                final int n8 = n6 + n4;
                final int n9 = (int)(array[n7] * (clientArea.width - sashes3.length * n4) / n);
                controls2[n7].setBounds(n8, clientArea.y, n9, clientArea.height);
                n6 = n8 + n9;
            }
            if (controls2.length > 1) {
                sashes3[sashes3.length - 1].setBounds(n6, clientArea.y, n4, clientArea.height);
                final int n10 = n6 + n4;
                controls2[controls2.length - 1].setBounds(n10, clientArea.y, clientArea.width - n10, clientArea.height);
            }
        }
        else {
            final int n11 = (int)(array[0] * (clientArea.height - sashes3.length * n4) / n);
            final int y = clientArea.y;
            controls2[0].setBounds(clientArea.x, y, clientArea.width, n11);
            int n12 = y + n11;
            for (int n13 = 1; n13 < controls2.length - 1; ++n13) {
                sashes3[n13 - 1].setBounds(clientArea.x, n12, clientArea.width, n4);
                final int n14 = n12 + n4;
                final int n15 = (int)(array[n13] * (clientArea.height - sashes3.length * n4) / n);
                controls2[n13].setBounds(clientArea.x, n14, clientArea.width, n15);
                n12 = n14 + n15;
            }
            if (controls2.length > 1) {
                sashes3[sashes3.length - 1].setBounds(clientArea.x, n12, clientArea.width, n4);
                final int n16 = n12 + n4;
                controls2[controls2.length - 1].setBounds(clientArea.x, n16, clientArea.width, clientArea.height - n16);
            }
        }
    }
}
