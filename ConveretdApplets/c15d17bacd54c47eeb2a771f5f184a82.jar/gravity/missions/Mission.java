// 
// Decompiled by Procyon v0.5.30
// 

package gravity.missions;

public abstract class Mission
{
    protected boolean _needTarget;
    protected boolean _needUFO;
    protected boolean _drawAtmospheres;
    protected boolean _hitTarget;
    protected boolean _scanTarget;
    protected int _minPlanetsScannedNr;
    protected int _minTicksNr;
    protected boolean _escapeProbe;
    protected String _description;
    protected String _successMessage;
    protected String _incomingData;
    protected String _failureAdditional;
    
    public Mission() {
        this._needTarget = false;
        this._needUFO = false;
        this._drawAtmospheres = false;
        this._hitTarget = false;
        this._scanTarget = false;
        this._minPlanetsScannedNr = 0;
        this._minTicksNr = 0;
        this._escapeProbe = true;
        this._description = "";
        this._successMessage = "";
        this._incomingData = "";
        this._failureAdditional = "";
    }
    
    public boolean getNeedTarget() {
        return this._needTarget;
    }
    
    public boolean getNeedUFO() {
        return this._needUFO;
    }
    
    public boolean getDrawAtmospheres() {
        return this._drawAtmospheres;
    }
    
    public boolean getHitTarget() {
        return this._hitTarget;
    }
    
    public boolean getScanTarget() {
        return this._scanTarget;
    }
    
    public int getMinPlanetsScannedNr() {
        return this._minPlanetsScannedNr;
    }
    
    public int getMinTicksNr() {
        return this._minTicksNr;
    }
    
    public boolean getEscapeProbe() {
        return this._escapeProbe;
    }
    
    public String getDescription() {
        return this._description;
    }
    
    public String getSuccessMessage(final int n, final int n2, final int n3) {
        return this._successMessage;
    }
    
    public String getIncomingData(final int n, final int n2, final boolean b) {
        return this._incomingData;
    }
    
    public String getFailureAdditional(final int n, final int n2) {
        return this._failureAdditional;
    }
}
