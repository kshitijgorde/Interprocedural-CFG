// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JColorChooser;
import java.awt.Graphics;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import edu.hws.eck.umb.util.I18n;
import javax.swing.Action;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

class PaletteEditPanel extends JPanel
{
    public static final String SELECTED_INDEX_PROPERTY = "PaletteEditPanel_Selected_Index";
    private final Palette palette;
    private ChangeListener changeListener;
    private final int BORDER = 12;
    private ArrayList<Rectangle> colorRects;
    private int selectedIndex;
    public final Action actionDeleteSelected;
    public final Action actionEditSelected;
    public final Action actionAddColor;
    
    public PaletteEditPanel() {
        this((Palette)null);
    }
    
    public PaletteEditPanel(Palette palette) {
        this.colorRects = new ArrayList<Rectangle>();
        this.selectedIndex = -1;
        this.actionDeleteSelected = new AbstractAction(I18n.tr("paletteEditDialog.buttonName.DeleteSelected", new Object[0])) {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (PaletteEditPanel.this.selectedIndex > 0 && PaletteEditPanel.this.selectedIndex < PaletteEditPanel.this.palette.getDivisionPointCount() - 1) {
                    PaletteEditPanel.this.palette.join(PaletteEditPanel.this.selectedIndex);
                }
            }
        };
        this.actionEditSelected = new AbstractAction(I18n.tr("paletteEditDialog.buttonName.EditSelected", new Object[0])) {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (PaletteEditPanel.this.selectedIndex >= 0) {
                    ColorEdit.showDialog(PaletteEditPanel.this, PaletteEditPanel.this.palette, PaletteEditPanel.this.selectedIndex);
                }
            }
        };
        this.actionAddColor = new AbstractAction(I18n.tr("paletteEditDialog.buttonName.AddColorToPalette", new Object[0])) {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorEdit.showDialog(PaletteEditPanel.this, PaletteEditPanel.this.palette, -1);
            }
        };
        this.setPreferredSize(new Dimension(536, 114));
        if (palette == null) {
            palette = new Palette(1);
        }
        this.palette = palette;
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        final Mouser mouser = new Mouser();
        this.addMouseListener(mouser);
        palette.addChangeListener(this.changeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                if (mouser.dragging == -1) {
                    PaletteEditPanel.this.setSelection(-1);
                }
                else {
                    PaletteEditPanel.this.repaint();
                }
            }
        });
    }
    
    public void finalize() {
        this.palette.removeChangeListener(this.changeListener);
    }
    
    void closing() {
        this.palette.removeChangeListener(this.changeListener);
        this.changeListener = null;
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final int n = this.getWidth() - 24;
        final int height = (this.getHeight() - 24) / 3;
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(10, 10, n + 5, height + 4);
        for (int i = 0; i < n; ++i) {
            graphics.setColor(this.palette.getColor(i / (n - 1)));
            graphics.drawLine(12 + i, 12, 12 + i, 12 + height - 1);
        }
        final int divisionPointCount = this.palette.getDivisionPointCount();
        int width = 15;
        if ((width + 1) * divisionPointCount > this.getWidth() - 4) {
            width = (this.getWidth() - 4) / divisionPointCount;
        }
        if (width < 3) {
            width = 3;
        }
        this.colorRects.clear();
        int n2 = Integer.MAX_VALUE;
        for (int j = 0; j < divisionPointCount; ++j) {
            final Rectangle rectangle = new Rectangle();
            rectangle.x = (int)(12.0 + this.palette.getDivisionPoint(j) * n) - width / 2;
            rectangle.y = 12 + 2 * height;
            rectangle.width = width;
            rectangle.height = height;
            this.colorRects.add(rectangle);
            if (j > 0 && rectangle.x - this.colorRects.get(this.colorRects.size() - 2).x < n2) {
                n2 = rectangle.x - this.colorRects.get(this.colorRects.size() - 2).x;
            }
        }
        if (n2 < width) {
            if (divisionPointCount * (width + 1) >= n + width) {
                final double n3 = n / (divisionPointCount - 1);
                for (int k = 0; k < this.colorRects.size(); ++k) {
                    this.colorRects.get(k).x = (int)(12 - width / 2 + n3 * k);
                }
            }
            else {
                final int[] array = new int[this.colorRects.size()];
                for (int l = 0; l < array.length; ++l) {
                    array[l] = this.colorRects.get(l).x;
                }
                while (true) {
                    int n4 = Integer.MAX_VALUE;
                    int n5 = 1;
                    for (int n6 = 1; n6 < array.length; ++n6) {
                        if (array[n6] - array[n6 - 1] < n4) {
                            n4 = array[n6] - array[n6 - 1];
                            n5 = n6;
                        }
                    }
                    if (n4 > width) {
                        break;
                    }
                    final int n7 = array[n5 - 1] + width + 1 - array[n5];
                    int n8 = n7 / 2;
                    int n9 = (n7 + 1) / 2;
                    final int n10 = array[n5 - 1] - 12 + width / 2 - (n5 - 1) * (width + 1);
                    final int n11 = 12 + n + width - width / 2 - array[n5] - (divisionPointCount - n5) * (width + 1) + 1;
                    if (n8 > n10) {
                        n9 += n8 - n10;
                        n8 = n10;
                    }
                    else if (n9 > n11) {
                        n8 += n9 - n11;
                        n9 = n11;
                    }
                    int n12 = n5 - 1;
                    while (n8 > 0) {
                        final int n13 = array[n12] - (array[n12 - 1] + width + 1);
                        array[n12] -= n8;
                        if (n13 > 0) {
                            n8 -= n13;
                        }
                        --n12;
                    }
                    int n14 = n5;
                    while (n9 > 0) {
                        final int n15 = array[n14 + 1] - (array[n14] + width + 1);
                        final int[] array2 = array;
                        final int n16 = n14;
                        array2[n16] += n9;
                        if (n15 > 0) {
                            n9 -= n15;
                        }
                        ++n14;
                    }
                }
                for (int n17 = 0; n17 < array.length; ++n17) {
                    this.colorRects.get(n17).x = array[n17];
                }
            }
        }
        for (int n18 = 0; n18 < divisionPointCount; ++n18) {
            final Rectangle rectangle2 = this.colorRects.get(n18);
            final int n19 = (int)(12.0 + this.palette.getDivisionPoint(n18) * n);
            final int n20 = rectangle2.x + rectangle2.width / 2;
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawLine(n19, 12 + height, n20, 12 + height * 2);
            graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            graphics.setColor(this.palette.getDivisionPointColor(n18));
            graphics.fillRect(rectangle2.x + 1, rectangle2.y + 1, rectangle2.width - 2, rectangle2.height - 2);
        }
        if (this.selectedIndex >= 0) {
            final double divisionPoint = this.palette.getDivisionPoint(this.selectedIndex);
            final Rectangle rectangle3 = this.colorRects.get(this.selectedIndex);
            final int n21 = 12 + (int)(divisionPoint * n);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(n21 - 2, 11, 2, height + 1);
            graphics.drawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
            graphics.setColor(Color.WHITE);
            graphics.drawRect(n21 - 3, 10, 4, height + 3);
            graphics.drawRect(rectangle3.x - 1, rectangle3.y - 1, rectangle3.width + 2, rectangle3.height + 2);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(n21 - 4, 9, 6, height + 5);
            graphics.drawRect(rectangle3.x - 2, rectangle3.y - 2, rectangle3.width + 4, rectangle3.height + 4);
        }
    }
    
    private void setSelection(final int selectedIndex) {
        if (selectedIndex != this.selectedIndex) {
            this.firePropertyChange("PaletteEditPanel_Selected_Index", this.selectedIndex, this.selectedIndex = selectedIndex);
        }
        this.repaint();
    }
    
    private void directColorEdit(final int n) {
        final Color showDialog = JColorChooser.showDialog(this, I18n.tr("colorEditDialog.colorChooserTitl", new Object[0]), this.palette.getDivisionPointColor(n));
        if (showDialog != null) {
            float[] array;
            if (this.palette.getColorType() == 1) {
                array = Color.RGBtoHSB(showDialog.getRed(), showDialog.getGreen(), showDialog.getBlue(), null);
            }
            else {
                array = showDialog.getRGBColorComponents(null);
            }
            this.palette.setDivisionPointColorComponents(n, array[0], array[1], array[2]);
        }
    }
    
    private class Mouser extends MouseAdapter implements MouseMotionListener
    {
        int dragging;
        int dragPosition;
        int dragOffset;
        
        private Mouser() {
            this.dragging = -1;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.dragging = -1;
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.dragOffset = 0;
            final int n = PaletteEditPanel.this.getWidth() - 24;
            final int n2 = (PaletteEditPanel.this.getHeight() - 24) / 3;
            if (y <= 12 || y >= 12 + n2) {
                for (int i = 0; i < PaletteEditPanel.this.colorRects.size(); ++i) {
                    if (((Rectangle)PaletteEditPanel.this.colorRects.get(i)).contains(x, y)) {
                        if (mouseEvent.getClickCount() == 2) {
                            if (!mouseEvent.isShiftDown() && !mouseEvent.isMetaDown()) {
                                PaletteEditPanel.this.directColorEdit(i);
                            }
                            else {
                                ColorEdit.showDialog(PaletteEditPanel.this, PaletteEditPanel.this.palette, i);
                            }
                        }
                        else {
                            PaletteEditPanel.this.setSelection(i);
                            if (i > 0 && i < PaletteEditPanel.this.palette.getDivisionPointCount() - 1) {
                                this.dragging = i;
                                this.dragPosition = 12 + (int)(n * PaletteEditPanel.this.palette.getDivisionPoint(i));
                                this.dragOffset = x - this.dragPosition;
                                PaletteEditPanel.this.addMouseMotionListener(this);
                            }
                        }
                        return;
                    }
                }
                PaletteEditPanel.this.setSelection(-1);
                return;
            }
            final int divisionPointCount = PaletteEditPanel.this.palette.getDivisionPointCount();
            int access$200 = -1;
            int n3 = Integer.MAX_VALUE;
            if (PaletteEditPanel.this.selectedIndex >= 0 && Math.abs(x - (12 + (int)(n * PaletteEditPanel.this.palette.getDivisionPoint(PaletteEditPanel.this.selectedIndex)))) < 4) {
                final int dragPosition = 12 + (int)(n * PaletteEditPanel.this.palette.getDivisionPoint(PaletteEditPanel.this.selectedIndex));
                access$200 = PaletteEditPanel.this.selectedIndex;
                this.dragPosition = dragPosition;
                n3 = Math.abs(dragPosition - x);
            }
            else {
                for (int j = 0; j < divisionPointCount; ++j) {
                    final int dragPosition2 = 12 + (int)(n * PaletteEditPanel.this.palette.getDivisionPoint(j));
                    if (Math.abs(dragPosition2 - x) < n3) {
                        access$200 = j;
                        this.dragPosition = dragPosition2;
                        n3 = Math.abs(dragPosition2 - x);
                    }
                }
            }
            if (n3 < 4) {
                if (mouseEvent.getClickCount() == 1) {
                    PaletteEditPanel.this.setSelection(access$200);
                    if (access$200 > 0 && access$200 < PaletteEditPanel.this.palette.getDivisionPointCount() - 1) {
                        PaletteEditPanel.this.addMouseMotionListener(this);
                        this.dragging = access$200;
                    }
                    return;
                }
                if (mouseEvent.getClickCount() == 2) {
                    if (!mouseEvent.isShiftDown() && !mouseEvent.isMetaDown()) {
                        PaletteEditPanel.this.directColorEdit(access$200);
                    }
                    else {
                        ColorEdit.showDialog(PaletteEditPanel.this, PaletteEditPanel.this.palette, access$200);
                    }
                    return;
                }
            }
            final double n4 = (mouseEvent.getX() - 12) / (PaletteEditPanel.this.getWidth() - 24);
            if (mouseEvent.getClickCount() == 2 && n4 > 0.0 && n4 < 1.0) {
                PaletteEditPanel.this.setSelection(PaletteEditPanel.this.palette.split(n4));
                return;
            }
            PaletteEditPanel.this.setSelection(-1);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (this.dragging >= 0) {
                PaletteEditPanel.this.removeMouseMotionListener(this);
                this.dragging = -1;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (this.dragging == -1) {
                return;
            }
            final int n = PaletteEditPanel.this.getWidth() - 24;
            int n2;
            if (this.dragging == 0) {
                n2 = 13;
            }
            else {
                n2 = 12 + (int)(PaletteEditPanel.this.palette.getDivisionPoint(this.dragging - 1) * n + 0.499) + 1;
            }
            int n3;
            if (this.dragging == PaletteEditPanel.this.palette.getDivisionPointCount() - 1) {
                n3 = 12 + n - 1;
            }
            else {
                n3 = 12 + (int)(PaletteEditPanel.this.palette.getDivisionPoint(this.dragging + 1) * n) - 1;
            }
            int n4 = mouseEvent.getX() - this.dragOffset;
            if (n4 < n2) {
                n4 = n2;
            }
            else if (n4 > n3) {
                n4 = n3;
            }
            if (n4 != this.dragPosition) {
                PaletteEditPanel.this.palette.setDivisionPoint(this.dragging, (n4 - 12) / (PaletteEditPanel.this.getWidth() - 24));
            }
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
