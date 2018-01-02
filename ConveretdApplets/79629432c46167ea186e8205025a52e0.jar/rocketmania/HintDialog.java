// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import sexy.gui.ButtonWidget;
import sexy.gui.ButtonListener;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.Widget;
import sexy.gui.WidgetManager;
import sexy.gui.OutlineButtonWidget;
import sexy.gui.OutlineDialogWidget;

public class HintDialog extends OutlineDialogWidget
{
    static final int[][] mDialogColors;
    static final int[][] mContinueButtonColors;
    static final int[][] skNoHintsButtonColors;
    OutlineButtonWidget mNoMoreHintsButton;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mNoMoreHintsButton);
    }
    
    public int GetPreferredHeight(final int n) {
        return super.GetPreferredHeight(n) + 12;
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        super.Draw(sexyGraphics);
        sexyGraphics.SetColor(new Color(20, 53, 0));
        sexyGraphics.FillRect(2, super.mHeight - super.mDropShadowSize - 22, super.mWidth - super.mDropShadowSize - 4, 1);
    }
    
    public HintDialog(final WidgetManager widgetManager, final ButtonListener buttonListener, final String s, final String s2) {
        super(widgetManager, buttonListener, s, s2, "Click here to continue", 2);
        this.mNoMoreHintsButton = new OutlineButtonWidget(super.mWidgetManager, 100, buttonListener);
        this.mNoMoreHintsButton.mLabel = "Click here to disable hints";
        this.mNoMoreHintsButton.SetColors(HintDialog.skNoHintsButtonColors);
        this.mNoMoreHintsButton.mDoFinger = true;
        this.SetColors(HintDialog.mDialogColors);
        this.SetButtonColors(HintDialog.mContinueButtonColors);
        super.mDropShadowSize = 8;
    }
    
    static {
        mDialogColors = new int[][] { { 200, 200, 200 }, { 255, 255, 255 }, { 255, 235, 30 }, { 180, 60, 150 }, { 120, 40, 90 }, { 0, 0, 0, 128 } };
        mContinueButtonColors = new int[][] { { 200, 200, 200 }, { 150, 50, 120 }, { 180, 60, 150 }, { 210, 70, 180 }, { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 } };
        skNoHintsButtonColors = new int[][] { { 183, 63, 150 }, { 183, 63, 150 }, { 183, 63, 150 }, { 183, 63, 150 }, { 90, 30, 45 }, { 255, 235, 30 }, { 255, 255, 255 } };
    }
    
    public void Resize(final int n, final int n2, final int n3, final int n4) {
        super.Resize(n, n2, n3, n4);
        final ButtonWidget mYesButton = super.mYesButton;
        mYesButton.mY -= 8;
        this.mNoMoreHintsButton.Resize(super.mX + 2, super.mY + super.mHeight - super.mDropShadowSize - 22, super.mWidth - super.mDropShadowSize - 4, 20);
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        widgetManager.AddWidget(this.mNoMoreHintsButton);
    }
}
