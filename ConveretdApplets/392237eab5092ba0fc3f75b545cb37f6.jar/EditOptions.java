// 
// Decompiled by Procyon v0.5.30
// 

class EditOptions implements Editable
{
    CirSim sim;
    
    public EditOptions(final CirSim sim) {
        this.sim = sim;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Time step size (s)", this.sim.timeStep, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Range for voltage color (V)", CircuitElm.voltageRange, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0 && editInfo.value > 0.0) {
            this.sim.timeStep = editInfo.value;
        }
        if (n == 1 && editInfo.value > 0.0) {
            CircuitElm.voltageRange = editInfo.value;
        }
    }
}
