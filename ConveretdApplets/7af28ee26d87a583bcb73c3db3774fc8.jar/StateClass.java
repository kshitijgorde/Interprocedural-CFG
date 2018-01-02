// 
// Decompiled by Procyon v0.5.30
// 

public class StateClass
{
    public static final int LEFT = -1;
    public static final int NO_MOVE = 0;
    public static final int RIGHT = 1;
    private String _stateCurrent;
    private String _valueCurrent;
    private String _valueNext;
    private int _intDirection;
    private String _stateNext;
    
    public StateClass(final String stateCurrent, final String valueCurrent, final String valueNext, final int direction, final String stateNext) {
        this._stateCurrent = stateCurrent.trim();
        this._stateNext = stateNext.trim();
        if (valueCurrent.trim().equals("") || valueCurrent.trim().equals("_")) {
            this._valueCurrent = "";
        }
        else {
            this._valueCurrent = valueCurrent.trim();
        }
        if (valueNext.trim().equals("") || valueNext.trim().equals("_")) {
            this._valueNext = "";
        }
        else {
            this._valueNext = valueNext.trim();
        }
        this._intDirection = ((direction > Math.max(-1, 1) || direction < Math.min(-1, 1)) ? 0 : direction);
    }
    
    public String getStateCurrent() {
        return this._stateCurrent;
    }
    
    public String getStateNext() {
        return this._stateNext;
    }
    
    public String getValueCurrent() {
        return this._valueCurrent;
    }
    
    public String getValueNext() {
        return this._valueNext;
    }
    
    public String getDirection() {
        String stringReturn = "";
        if (this._intDirection == -1) {
            stringReturn = "LEFT";
        }
        else if (this._intDirection == 1) {
            stringReturn = "RIGHT";
        }
        return stringReturn;
    }
    
    public int getDirection(final String nothing) {
        return this._intDirection;
    }
}
