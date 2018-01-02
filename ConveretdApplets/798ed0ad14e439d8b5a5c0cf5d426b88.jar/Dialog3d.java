import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Dialog3d extends PlotterPanel
{
    private Canvas3d canvas1;
    
    public Dialog3d(final InputStream inputStream, final AbstractBox abstractBox, final FormulaEditor formula, final AppInterface appInterface) {
        this.canvas1 = new Canvas3d(inputStream, abstractBox, appInterface);
        this.canvas1.plotStream.formula = formula;
        this.I(this.canvas1, appInterface);
        this.canvas1.requestFocus();
    }
    
    public final void initToolBar(final WToolBar wToolBar) {
        this.I("axis", this.canvas1.plotStream.showCoorAxis);
        this.I("grid", this.canvas1.plotStream.showCube);
        this.I("actionmove", true);
        switch (this.canvas1.mouseLabelMode) {
            case 1: {
                this.I("actionshowname", true);
                break;
            }
            case 2: {
                this.I("actionshowvalue", true);
                break;
            }
            case 3: {
                this.I("actionshowdef", true);
                break;
            }
        }
    }
    
    public final DataInputStream getDataFromPlotter() {
        return this.canvas1.getData();
    }
    
    protected final String I() {
        return "toolbar_3d.ini";
    }
    
    public final void removeNotify() {
        if (this.canvas1 != null && this.canvas1.box instanceof CapsaComandes && !this.isVisible()) {
            ((FormulaEditorCalc)this.canvas1.plotStream.formula).getPlotterManager().deleteFrame(this.canvas1.box, this, false);
        }
        super.removeNotify();
    }
    
    public final void dispose() {
        this.canvas1.freeResources();
        super.dispose();
    }
    
    public final Canvas3d getCanvas3d() {
        return this.canvas1;
    }
}
