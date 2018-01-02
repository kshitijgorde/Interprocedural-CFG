import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class AntennaElm extends RailElm
{
    double fmphase;
    
    public AntennaElm(final int n, final int n2) {
        super(n, n2, 0);
    }
    
    public AntennaElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.waveform = 0;
    }
    
    void stamp() {
        AntennaElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource);
    }
    
    void doStep() {
        AntennaElm.sim.updateVoltageSource(0, this.nodes[0], this.voltSource, this.getVoltage());
    }
    
    double getVoltage() {
        this.fmphase += 6.283185307179586 * (2200.0 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 13.0) * 100.0) * AntennaElm.sim.timeStep;
        return Math.sin(6.283185307179586 * AntennaElm.sim.t * 3000.0) * (1.3 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 12.0)) * 3.0 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 2710.0) * (1.3 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 13.0)) * 3.0 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 2433.0) * (1.3 + Math.sin(6.283185307179586 * AntennaElm.sim.t * 14.0)) * 3.0 + 3.0 * Math.sin(this.fmphase);
    }
    
    int getDumpType() {
        return 65;
    }
}
