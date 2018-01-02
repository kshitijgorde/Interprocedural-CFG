import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitTerminal implements BitSource
{
    private boolean _boolSelected;
    private boolean _boolMultiple;
    private Vector _downStream;
    private Point _pointTerminal;
    
    public BitTerminal() {
        this._downStream = new Vector();
        this._pointTerminal = new Point(0, 0);
        this._boolSelected = false;
        this._boolMultiple = false;
    }
    
    public BitTerminal(final boolean multiple, final int x, final int y) {
        this._downStream = new Vector();
        this._pointTerminal = new Point(x, y);
        this._boolSelected = false;
        this._boolMultiple = multiple;
    }
    
    public BitTerminal(final UpdateEvent downStream, final boolean multiple, final int x, final int y) {
        (this._downStream = new Vector()).addElement(downStream);
        this._pointTerminal = new Point(x, y);
        this._boolSelected = false;
        this._boolMultiple = multiple;
    }
    
    public boolean isSelected() {
        return this._boolSelected;
    }
    
    public void setSelected(final boolean selected) {
        this._boolSelected = selected;
        if (this._boolMultiple) {
            for (int i = 0; i < this._downStream.size(); ++i) {
                this._downStream.elementAt(i).update();
            }
        }
        else if (this._downStream.size() > 0) {
            this._downStream.elementAt(0).update();
        }
    }
    
    public int getValue() {
        return this.isSelected() ? 1 : 0;
    }
    
    public void setValue(final int value) {
        this.setSelected(value != 0);
    }
    
    public Point getTerminal() {
        return this._pointTerminal;
    }
    
    public void setTerminal(final Point point) {
        if (this._pointTerminal.x == point.x && this._pointTerminal.y == point.y) {
            return;
        }
        this._pointTerminal = point;
    }
    
    public void setDownStream(final UpdateEvent downStream) {
        this._downStream.add(0, downStream);
    }
}
