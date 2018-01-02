// 
// Decompiled by Procyon v0.5.30
// 

class Diode
{
    int[] nodes;
    CirSim sim;
    public double leakage;
    double vt;
    double vdcoef;
    double fwdrop;
    double zvoltage;
    double zoffset;
    double lastvoltdiff;
    double vcrit;
    
    Diode(final CirSim sim) {
        this.leakage = 1.0E-14;
        this.sim = sim;
        this.nodes = new int[2];
    }
    
    void setup(final double fwdrop, final double zvoltage) {
        this.fwdrop = fwdrop;
        this.zvoltage = zvoltage;
        this.vdcoef = Math.log(1.0 / this.leakage + 1.0) / this.fwdrop;
        this.vt = 1.0 / this.vdcoef;
        this.vcrit = this.vt * Math.log(this.vt / (Math.sqrt(2.0) * this.leakage));
        if (this.zvoltage == 0.0) {
            this.zoffset = 0.0;
        }
        else {
            this.zoffset = this.zvoltage - Math.log(-(1.0 + -0.005 / this.leakage)) / this.vdcoef;
        }
    }
    
    void reset() {
        this.lastvoltdiff = 0.0;
    }
    
    double limitStep(double n, double n2) {
        if (n > this.vcrit && Math.abs(n - n2) > this.vt + this.vt) {
            if (n2 > 0.0) {
                final double n3 = 1.0 + (n - n2) / this.vt;
                if (n3 > 0.0) {
                    n = n2 + this.vt * Math.log(n3);
                    n = Math.max(Math.log(1.0E-6 / this.leakage) * this.vt, n);
                }
                else {
                    n = this.vcrit;
                }
            }
            else {
                n = this.vt * Math.log(n / this.vt);
            }
            this.sim.converged = false;
        }
        else if (n < 0.0 && this.zoffset != 0.0) {
            n = -n - this.zoffset;
            n2 = -n2 - this.zoffset;
            if (n > this.vcrit && Math.abs(n - n2) > this.vt + this.vt) {
                if (n2 > 0.0) {
                    final double n4 = 1.0 + (n - n2) / this.vt;
                    if (n4 > 0.0) {
                        n = n2 + this.vt * Math.log(n4);
                        n = Math.max(Math.log(1.0E-6 / this.leakage) * this.vt, n);
                    }
                    else {
                        n = this.vcrit;
                    }
                }
                else {
                    n = this.vt * Math.log(n / this.vt);
                }
                this.sim.converged = false;
            }
            n = -(n + this.zoffset);
        }
        return n;
    }
    
    void stamp(final int n, final int n2) {
        this.nodes[0] = n;
        this.nodes[1] = n2;
        this.sim.stampNonLinear(this.nodes[0]);
        this.sim.stampNonLinear(this.nodes[1]);
    }
    
    void doStep(double limitStep) {
        if (Math.abs(limitStep - this.lastvoltdiff) > 0.01) {
            this.sim.converged = false;
        }
        limitStep = this.limitStep(limitStep, this.lastvoltdiff);
        this.lastvoltdiff = limitStep;
        if (limitStep >= 0.0 || this.zvoltage == 0.0) {
            double exp = Math.exp(limitStep * this.vdcoef);
            if (limitStep < 0.0) {
                exp = 1.0;
            }
            final double n = this.vdcoef * this.leakage * exp;
            final double n2 = (exp - 1.0) * this.leakage - n * limitStep;
            this.sim.stampConductance(this.nodes[0], this.nodes[1], n);
            this.sim.stampCurrentSource(this.nodes[0], this.nodes[1], n2);
        }
        else {
            final double n3 = this.leakage * this.vdcoef * (Math.exp(limitStep * this.vdcoef) + Math.exp((-limitStep - this.zoffset) * this.vdcoef));
            final double n4 = this.leakage * (Math.exp(limitStep * this.vdcoef) - Math.exp((-limitStep - this.zoffset) * this.vdcoef) - 1.0) + n3 * -limitStep;
            this.sim.stampConductance(this.nodes[0], this.nodes[1], n3);
            this.sim.stampCurrentSource(this.nodes[0], this.nodes[1], n4);
        }
    }
    
    double calculateCurrent(final double n) {
        if (n >= 0.0 || this.zvoltage == 0.0) {
            return this.leakage * (Math.exp(n * this.vdcoef) - 1.0);
        }
        return this.leakage * (Math.exp(n * this.vdcoef) - Math.exp((-n - this.zoffset) * this.vdcoef) - 1.0);
    }
}
