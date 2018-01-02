// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.text.View;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.Font;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.Insets;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TinyTabbedPaneUI extends BasicTabbedPaneUI
{
    int rollover;
    
    public TinyTabbedPaneUI() {
        this.rollover = -1;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTabbedPaneUI();
    }
    
    protected void installListeners() {
        super.installListeners();
        this.tabPane.addMouseMotionListener((MouseMotionListener)this.mouseListener);
    }
    
    protected MouseListener createMouseListener() {
        return new TinyMouseHandler();
    }
    
    protected void installDefaults() {
        super.installDefaults();
    }
    
    private boolean scrollableTabLayoutEnabled() {
        return this.tabPane.getTabLayoutPolicy() == 1;
    }
    
    private void checkRollOver(final int rollover) {
        if (this.rollover >= this.tabPane.getTabCount()) {
            this.rollover = -1;
        }
        if (rollover == this.rollover) {
            return;
        }
        if (this.rollover != -1) {
            this.tabPane.repaint(this.getTabBounds(this.tabPane, this.rollover));
            if (rollover == -1) {
                this.rollover = -1;
            }
        }
        if (rollover >= 0 && this.tabPane.isEnabledAt(rollover)) {
            this.rollover = rollover;
            this.tabPane.repaint(this.getTabBounds(this.tabPane, rollover));
        }
    }
    
    protected LayoutManager createLayoutManager() {
        if (this.tabPane.getTabLayoutPolicy() == 1) {
            return super.createLayoutManager();
        }
        return new TinyTabbedPaneLayout();
    }
    
    private int getTabAtLocation(final int n, final int n2) {
        if (TinyLookAndFeel.is1dot4()) {
            this.ensureCurrentLayout();
            for (int tabCount = this.tabPane.getTabCount(), i = 0; i < tabCount; ++i) {
                if (this.rects[i].contains(n, n2)) {
                    return i;
                }
            }
            return -1;
        }
        return this.tabForCoordinate(this.tabPane, n, n2);
    }
    
    private void ensureCurrentLayout() {
        if (!this.tabPane.isValid()) {
            this.tabPane.validate();
        }
        if (!this.tabPane.isValid()) {
            ((TabbedPaneLayout)this.tabPane.getLayout()).calculateLayoutInfo();
        }
    }
    
    protected void paintTabBackground(final Graphics graphics, final int n, final int n2, int n3, int n4, final int n5, final int n6, final boolean b) {
        boolean enabled = this.tabPane.isEnabledAt(n2);
        if (!this.tabPane.isEnabled()) {
            enabled = false;
        }
        if (b && !Theme.ignoreSelectedBg[Theme.style]) {
            if (enabled) {
                graphics.setColor(Theme.tabSelectedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.tabDisabledSelectedColor[Theme.style].getColor());
            }
        }
        else if (enabled) {
            graphics.setColor(this.tabPane.getBackgroundAt(n2));
        }
        else {
            graphics.setColor(Theme.tabDisabledColor[Theme.style].getColor());
        }
        switch (n) {
            case 2: {
                graphics.fillRect(n3 + 1, n4 + 1, n5 - 1, n6 - 3);
                break;
            }
            case 4: {
                n3 -= 2;
                graphics.fillRect(n3, n4 + 1, n5 - 1, n6 - 3);
                break;
            }
            case 3: {
                n4 -= 2;
                graphics.fillRect(n3 + 1, n4, n5 - 3, n6 - 1);
                break;
            }
            default: {
                graphics.fillRect(n3 + 1, n4 + 1, n5 - 3, n6 - 1);
                break;
            }
        }
    }
    
    protected void paintContentBorder(final Graphics graphics, final int n, final int n2) {
    }
    
    protected void paintFocusIndicator(final Graphics graphics, final int n, final Rectangle[] array, final int n2, final Rectangle rectangle, final Rectangle rectangle2, final boolean b) {
        if (!Theme.tabFocus[Theme.style]) {
            return;
        }
        final Rectangle rectangle3 = array[n2];
        if (this.tabPane.hasFocus() && b) {
            graphics.setColor(Theme.tabFontColor[Theme.style].getColor());
            int x = 0;
            int y = 0;
            int n3 = 0;
            int n4 = 0;
            switch (n) {
                case 2: {
                    x = rectangle3.x + 3;
                    y = rectangle3.y + 3;
                    n3 = rectangle3.width - 5;
                    n4 = rectangle3.height - 7;
                    break;
                }
                case 4: {
                    x = rectangle3.x;
                    y = rectangle3.y + 3;
                    n3 = rectangle3.width - 5;
                    n4 = rectangle3.height - 7;
                    break;
                }
                case 3: {
                    x = rectangle3.x + 3;
                    y = rectangle3.y;
                    n3 = rectangle3.width - 7;
                    n4 = rectangle3.height - 5;
                    break;
                }
                default: {
                    x = rectangle3.x + 3;
                    y = rectangle3.y + 3;
                    n3 = rectangle3.width - 7;
                    n4 = rectangle3.height - 5;
                    break;
                }
            }
            BasicGraphicsUtils.drawDashedRect(graphics, x, y, n3, n4);
        }
    }
    
    protected void paintTabBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        boolean enabled = this.tabPane.isEnabledAt(n2);
        if (!this.tabPane.isEnabled()) {
            enabled = false;
        }
        final boolean b2 = this.rollover == n2;
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyTabBorder(graphics, n, n3, n4, n5, n6, b, enabled, b2);
                break;
            }
            case 1: {
                this.drawWinTabBorder(graphics, n, n3, n4, n5, n6, b, enabled, b2);
                break;
            }
            case 2: {
                this.drawXpTabBorder(graphics, n, n3, n4, n5, n6, b, enabled, b2);
                break;
            }
        }
    }
    
    private void drawTinyTabBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2, final boolean b3) {
    }
    
    private void drawWinTabBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2, final boolean b3) {
        graphics.setColor(Theme.tabLightColor[Theme.style].getColor());
        switch (n) {
            case 2: {
                graphics.drawLine(n2 + 2, n3, n2 + n4 - 1, n3);
                graphics.drawLine(n2 + 1, n3 + 1, n2 + 1, n3 + 1);
                graphics.drawLine(n2, n3 + 2, n2, n3 + n5 - 3);
                graphics.setColor(Theme.tabDarkColor[Theme.style].getColor());
                graphics.drawLine(n2 + 2, n3 + n5 - 2, n2 + n4 - 1, n3 + n5 - 2);
                graphics.setColor(Theme.tabBorderColor[Theme.style].getColor());
                graphics.drawLine(n2 + 2, n3 + n5 - 1, n2 + n4 - 1, n3 + n5 - 1);
                graphics.drawLine(n2 + 1, n3 + n5 - 2, n2 + 1, n3 + n5 - 2);
                break;
            }
            case 4: {
                graphics.drawLine(n2 + n4 - 3, n3, n2, n3);
                graphics.drawLine(n2 + n4 - 2, n3 + 1, n2 + n4 - 2, n3 + 1);
                graphics.drawLine(n2 + n4 - 1, n3 + 2, n2 + n4 - 1, n3 + n5 - 3);
                graphics.setColor(Theme.tabDarkColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 3, n3 + n5 - 2, n2, n3 + n5 - 2);
                graphics.setColor(Theme.tabBorderColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 3, n3 + n5 - 1, n2, n3 + n5 - 1);
                graphics.drawLine(n2 + n4 - 2, n3 + n5 - 2, n2 + n4 - 2, n3 + n5 - 2);
                break;
            }
            case 3: {
                graphics.drawLine(n2 + 2, n3 + n5 - 1, n2 + n4 - 3, n3 + n5 - 1);
                graphics.drawLine(n2, n3 + n5 - 3, n2, n3);
                graphics.drawLine(n2 + 1, n3 + n5 - 2, n2 + 1, n3 + n5 - 2);
                graphics.setColor(Theme.tabDarkColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 2, n3 + n5 - 3, n2 + n4 - 2, n3);
                graphics.setColor(Theme.tabBorderColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 1, n3 + n5 - 3, n2 + n4 - 1, n3);
                graphics.drawLine(n2 + n4 - 2, n3 + n5 - 2, n2 + n4 - 2, n3 + n5 - 2);
                break;
            }
            default: {
                graphics.drawLine(n2 + 2, n3, n2 + n4 - 3, n3);
                graphics.drawLine(n2, n3 + 2, n2, n3 + n5 - 1);
                graphics.drawLine(n2 + 1, n3 + 1, n2 + 1, n3 + 1);
                graphics.setColor(Theme.tabDarkColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 2, n3 + 2, n2 + n4 - 2, n3 + n5 - 1);
                graphics.setColor(Theme.tabBorderColor[Theme.style].getColor());
                graphics.drawLine(n2 + n4 - 1, n3 + 2, n2 + n4 - 1, n3 + n5 - 1);
                graphics.drawLine(n2 + n4 - 2, n3 + 1, n2 + n4 - 2, n3 + 1);
                break;
            }
        }
    }
    
    private void drawXpTabBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2, final boolean b3) {
        if (!b2) {
            DrawRoutines.drawXpTabBorder(graphics, Theme.tabBorderColor[Theme.style].getColor(), n2, n3, n4, n5, n);
        }
        else if (b) {
            DrawRoutines.drawSelectedXpTabBorder(graphics, Theme.tabBorderColor[Theme.style].getColor(), n2, n3, n4, n5, n);
        }
        else if (b3 && Theme.tabRollover[Theme.style]) {
            DrawRoutines.drawSelectedXpTabBorder(graphics, Theme.tabBorderColor[Theme.style].getColor(), n2, n3, n4, n5, n);
        }
        else {
            DrawRoutines.drawXpTabBorder(graphics, Theme.tabBorderColor[Theme.style].getColor(), n2, n3, n4, n5, n);
        }
    }
    
    public void update(final Graphics graphics, final JComponent component) {
        final Insets insets = this.tabPane.getInsets();
        int left = insets.left;
        int top = insets.top;
        int n = this.tabPane.getWidth() - insets.right - insets.left;
        int n2 = this.tabPane.getHeight() - insets.top - insets.bottom;
        if (component.isOpaque()) {
            graphics.setColor(Theme.backColor[Theme.style].getColor());
            graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
        }
        final int tabPlacement = this.tabPane.getTabPlacement();
        switch (tabPlacement) {
            case 2: {
                left += this.calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
                n -= left - insets.left;
                break;
            }
            case 4: {
                n -= this.calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
                break;
            }
            case 3: {
                n2 -= this.calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
                break;
            }
            default: {
                top += this.calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
                n2 -= top - insets.top;
                break;
            }
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyContentBorder(graphics, left, top, n, n2);
                break;
            }
            case 1: {
                this.drawWinContentBorder(graphics, left, top, n, n2);
                break;
            }
            case 2: {
                this.drawXpContentBorder(graphics, left, top, n, n2);
                break;
            }
        }
        super.paint(graphics, component);
    }
    
    private void drawTinyContentBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinContentBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Theme.tabPaneLightColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2, n, n2 + n4 - 2);
        graphics.setColor(Theme.tabPaneDarkColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
        graphics.setColor(Theme.tabPaneBorderColor[Theme.style].getColor());
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
    }
    
    private void drawXpContentBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Theme.tabPaneBorderColor[Theme.style].getColor());
        graphics.drawRect(n, n2, n3 - 3, n4 - 3);
        graphics.setColor(ColorRoutines.darken(Theme.backColor[Theme.style].getColor(), 15));
        graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 3, n2 + n4 - 2);
    }
    
    protected int getTabLabelShiftX(final int n, final int n2, final boolean b) {
        final Rectangle rectangle = this.rects[n2];
        int n3 = 0;
        switch (n) {
            case 2: {
                n3 = (b ? -1 : 1);
                break;
            }
            case 4: {
                n3 = (b ? 1 : -1);
                break;
            }
            default: {
                n3 = 0;
                break;
            }
        }
        return n3;
    }
    
    protected int getTabLabelShiftY(final int n, final int n2, final boolean b) {
        final Rectangle rectangle = this.rects[n2];
        int n3 = 0;
        switch (n) {
            case 3: {
                n3 = (b ? 1 : -1);
                break;
            }
            case 2:
            case 4: {
                n3 = rectangle.height % 2;
                break;
            }
            default: {
                n3 = (b ? -1 : 1);
                break;
            }
        }
        return n3;
    }
    
    protected void paintText(final Graphics graphics, final int n, final Font font, final FontMetrics fontMetrics, final int n2, final String s, final Rectangle rectangle, final boolean b) {
        if (Theme.derivedStyle[Theme.style] == 1) {
            super.paintText(graphics, n, font, fontMetrics, n2, s, rectangle, b);
            return;
        }
        graphics.setFont(font);
        final View textViewForTab = this.getTextViewForTab(n2);
        if (textViewForTab != null) {
            textViewForTab.paint(graphics, rectangle);
        }
        else {
            final int displayedMnemonicIndex = this.tabPane.getDisplayedMnemonicIndexAt(n2);
            if (this.tabPane.isEnabled() && this.tabPane.isEnabledAt(n2)) {
                graphics.setColor(this.tabPane.getForegroundAt(n2));
            }
            else {
                graphics.setColor(Theme.tabDisabledTextColor[Theme.style].getColor());
            }
            BasicGraphicsUtils.drawStringUnderlineCharAt(graphics, s, displayedMnemonicIndex, rectangle.x, rectangle.y + fontMetrics.getAscent());
        }
    }
    
    protected class TinyTabbedPaneLayout extends TabbedPaneLayout
    {
        protected void rotateTabRuns(final int n, final int n2) {
            if (!Theme.fixedTabs[Theme.style]) {
                super.rotateTabRuns(n, n2);
            }
        }
    }
    
    public class TinyMouseHandler implements MouseListener, MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (!TinyTabbedPaneUI.this.tabPane.isEnabled()) {
                return;
            }
            final int access$100 = TinyTabbedPaneUI.this.getTabAtLocation(mouseEvent.getX(), mouseEvent.getY());
            if (access$100 >= 0 && TinyTabbedPaneUI.this.tabPane.isEnabledAt(access$100)) {
                if (access$100 != TinyTabbedPaneUI.this.tabPane.getSelectedIndex()) {
                    TinyTabbedPaneUI.this.tabPane.setSelectedIndex(access$100);
                }
                else if (TinyTabbedPaneUI.this.tabPane.isRequestFocusEnabled()) {
                    TinyTabbedPaneUI.this.tabPane.requestFocus();
                }
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (TinyTabbedPaneUI.this.rollover >= TinyTabbedPaneUI.this.tabPane.getTabCount()) {
                TinyTabbedPaneUI.this.rollover = -1;
            }
            if (TinyTabbedPaneUI.this.rollover != -1) {
                TinyTabbedPaneUI.this.tabPane.repaint(TinyTabbedPaneUI.this.getTabBounds(TinyTabbedPaneUI.this.tabPane, TinyTabbedPaneUI.this.rollover));
                TinyTabbedPaneUI.this.rollover = -1;
            }
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (TinyTabbedPaneUI.this.tabPane == null) {
                return;
            }
            if (!TinyTabbedPaneUI.this.tabPane.isEnabled()) {
                return;
            }
            if (TinyLookAndFeel.is1dot4() && TinyTabbedPaneUI.this.scrollableTabLayoutEnabled()) {
                return;
            }
            TinyTabbedPaneUI.this.checkRollOver(TinyTabbedPaneUI.this.getTabAtLocation(mouseEvent.getX(), mouseEvent.getY()));
        }
    }
}
