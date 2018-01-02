import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efloy
{
    EfloyParam[] params;
    EfloyParam[] fixpars;
    EfloyParam[] envpars;
    String chrom;
    String fixed;
    String environ;
    int width;
    int height;
    int margin;
    int v0;
    int sleep;
    int id;
    int father;
    int mother;
    int type;
    Color color;
    int NumberOfNeighbors;
    float MutationFactor;
    float CrossoverFactor;
    float MaxSpeed;
    float BounceSpeed;
    float ApproachAcceleration;
    float RetreatAcceleration;
    float CenterAcceleration;
    float DistBrotherFactor;
    float DistStrangerFactor;
    float DistLocalFactor;
    int CollisionDistance;
    float CollisionBrotherFactor;
    float CollisionStrangerFactor;
    float CollisionLocalFactor;
    float MaxEnergyDose;
    float MaxSafetyDose;
    float MaxCooperationDose;
    int fitness;
    int energy;
    int safety;
    int cooperation;
    float EnergyFactor;
    float SafetyFactor;
    float CooperationFactor;
    float SurviversFactor;
    int PopulationSize;
    float FreeWillFactor;
    int LifeSpan;
    Efloy[] neighbors;
    float x;
    float y;
    float xtail;
    float ytail;
    float vx;
    float vy;
    float xc;
    float yc;
    
    public void Draw(final Graphics g) {
        g.setColor(this.color);
        if (Efloys.DrawNumbers) {
            g.drawString("" + this.id, (int)this.x, (int)this.y);
        }
        else {
            g.drawLine((int)this.xtail, (int)this.ytail, (int)this.x, (int)this.y);
            g.fillOval((int)this.x, (int)this.y, 3, 3);
        }
    }
    
    public int dist(final Efloy ng) {
        final int d1 = (int)(this.x - ng.x);
        final int d2 = (int)(this.y - ng.y);
        final int d3 = d1 * d1 + d2 * d2;
        return d3;
    }
    
    public String GetTypeName() {
        String s;
        if (this.type == 0) {
            s = "Local";
        }
        else if (this.type == 1) {
            s = "Stranger";
        }
        else {
            s = "Local";
        }
        return s;
    }
    
    private void DecodeFixed() {
        this.id = (int)this.GetParam(this.fixpars, this.fixed, 0);
        this.father = (int)this.GetParam(this.fixpars, this.fixed, 1);
        this.mother = (int)this.GetParam(this.fixpars, this.fixed, 2);
        this.type = (int)this.GetParam(this.fixpars, this.fixed, 3);
        final int col = (int)this.GetParam(this.fixpars, this.fixed, 4);
        this.AssignColor(col);
        this.NumberOfNeighbors = (int)this.GetParam(this.fixpars, this.fixed, 5);
        this.MutationFactor = this.GetParam(this.fixpars, this.fixed, 6);
        this.CrossoverFactor = this.GetParam(this.fixpars, this.fixed, 7);
        this.energy = (int)this.GetParam(this.fixpars, this.fixed, 8);
        this.safety = (int)this.GetParam(this.fixpars, this.fixed, 9);
        this.cooperation = (int)this.GetParam(this.fixpars, this.fixed, 10);
        this.fitness = (int)this.GetParam(this.fixpars, this.fixed, 11);
    }
    
    private void DecodeEnviron() {
        this.width = (int)this.GetParam(this.envpars, this.environ, 0);
        this.height = (int)this.GetParam(this.envpars, this.environ, 1);
        this.margin = (int)this.GetParam(this.envpars, this.environ, 2);
        this.v0 = (int)this.GetParam(this.envpars, this.environ, 3);
        this.sleep = (int)this.GetParam(this.envpars, this.environ, 4);
        this.EnergyFactor = this.GetParam(this.envpars, this.environ, 5);
        this.SafetyFactor = this.GetParam(this.envpars, this.environ, 6);
        this.CooperationFactor = this.GetParam(this.envpars, this.environ, 7);
        this.SurviversFactor = this.GetParam(this.envpars, this.environ, 8);
        this.MaxEnergyDose = this.GetParam(this.envpars, this.environ, 9);
        this.MaxSafetyDose = this.GetParam(this.envpars, this.environ, 10);
        this.MaxCooperationDose = this.GetParam(this.envpars, this.environ, 11);
        this.PopulationSize = (int)this.GetParam(this.envpars, this.environ, 12);
        this.FreeWillFactor = this.GetParam(this.envpars, this.environ, 13);
        this.LifeSpan = (int)this.GetParam(this.envpars, this.environ, 14);
    }
    
    public String GetColorName() {
        String s;
        if (this.color == Color.black) {
            s = "BLACK";
        }
        else if (this.color == Color.blue) {
            s = "BLUE";
        }
        else if (this.color == Color.cyan) {
            s = "CYAN";
        }
        else if (this.color == Color.darkGray) {
            s = "DARKGRAY";
        }
        else if (this.color == Color.gray) {
            s = "GRAY";
        }
        else if (this.color == Color.green) {
            s = "GREEN";
        }
        else if (this.color == Color.lightGray) {
            s = "LIGHTGRAY";
        }
        else if (this.color == Color.magenta) {
            s = "MAGENTA";
        }
        else if (this.color == Color.orange) {
            s = "ORANGE";
        }
        else if (this.color == Color.pink) {
            s = "PINK";
        }
        else if (this.color == Color.red) {
            s = "RED";
        }
        else if (this.color == Color.white) {
            s = "WHITE";
        }
        else if (this.color == Color.yellow) {
            s = "YELLOW";
        }
        else {
            s = "GREEN";
        }
        return s;
    }
    
    public void GetNeighbors() {
        for (int i = 0; i < this.NumberOfNeighbors; ++i) {
            final Efloy ng = this.neighbors[i];
            for (int j = 0; j < this.NumberOfNeighbors; ++j) {
                if (this.dist(ng.neighbors[j]) < this.dist(ng)) {
                    this.neighbors[i] = ng.neighbors[j];
                }
                if (ng.neighbors[j].type == 1) {
                    this.neighbors[i] = ng.neighbors[j];
                }
            }
        }
        this.xc = 0.0f;
        this.yc = 0.0f;
        for (int k = 0; k < this.NumberOfNeighbors; ++k) {
            if (this.neighbors[k] == this) {
                this.neighbors[k] = this.neighbors[k].neighbors[0];
            }
            this.xc += this.neighbors[k].x;
            this.yc += this.neighbors[k].y;
        }
        this.xc /= this.NumberOfNeighbors;
        this.yc /= this.NumberOfNeighbors;
    }
    
    private float GetParam(final EfloyParam[] pars, final String st, final int pos) {
        final float par = pars[pos].DecodeValue(st.charAt(pos));
        return par;
    }
    
    private void DecodeChrom() {
        this.MaxSpeed = this.GetParam(this.params, this.chrom, 0);
        this.BounceSpeed = this.GetParam(this.params, this.chrom, 1);
        this.ApproachAcceleration = this.GetParam(this.params, this.chrom, 2);
        this.RetreatAcceleration = this.GetParam(this.params, this.chrom, 3);
        this.CenterAcceleration = this.GetParam(this.params, this.chrom, 4);
        this.DistBrotherFactor = this.GetParam(this.params, this.chrom, 5);
        this.DistStrangerFactor = this.GetParam(this.params, this.chrom, 6);
        this.DistLocalFactor = this.GetParam(this.params, this.chrom, 7);
        this.CollisionDistance = (int)this.GetParam(this.params, this.chrom, 8);
        this.CollisionBrotherFactor = this.GetParam(this.params, this.chrom, 9);
        this.CollisionStrangerFactor = this.GetParam(this.params, this.chrom, 10);
        this.CollisionLocalFactor = this.GetParam(this.params, this.chrom, 11);
    }
    
    public Efloy(final EfloyParam[] pr, final String cr, final EfloyParam[] fp, final String fx, final EfloyParam[] ep, final String ev) {
        this.params = pr;
        this.fixpars = fp;
        this.envpars = ep;
        this.chrom = cr;
        this.fixed = fx;
        this.environ = ev;
        this.DecodeFixed();
        this.DecodeEnviron();
        this.DecodeChrom();
        this.GetFitness();
        this.x = (float)Math.random() * (this.width - this.margin * 2) + this.margin;
        this.y = (float)Math.random() * (this.height - this.margin * 2) + this.margin;
        this.xtail = this.x;
        this.ytail = this.y;
        this.vx = (float)Math.random() * this.v0 - this.v0 / 2;
        this.vy = (float)Math.random() * this.v0 - this.v0 / 2;
        this.neighbors = new Efloy[this.NumberOfNeighbors];
    }
    
    public String CrossOver(final String MateChrom) {
        final int pos = (int)(Math.random() * this.chrom.length());
        final String s1 = this.chrom.substring(0, pos);
        final String s2 = MateChrom.substring(pos);
        final String s3 = s1.concat(s2);
        return s3;
    }
    
    public int GetColorNumber() {
        int s;
        if (this.color == Color.black) {
            s = 0;
        }
        else if (this.color == Color.blue) {
            s = 1;
        }
        else if (this.color == Color.cyan) {
            s = 2;
        }
        else if (this.color == Color.darkGray) {
            s = 3;
        }
        else if (this.color == Color.gray) {
            s = 4;
        }
        else if (this.color == Color.green) {
            s = 5;
        }
        else if (this.color == Color.lightGray) {
            s = 6;
        }
        else if (this.color == Color.magenta) {
            s = 7;
        }
        else if (this.color == Color.orange) {
            s = 8;
        }
        else if (this.color == Color.pink) {
            s = 9;
        }
        else if (this.color == Color.red) {
            s = 10;
        }
        else if (this.color == Color.white) {
            s = 11;
        }
        else if (this.color == Color.yellow) {
            s = 12;
        }
        else {
            s = 5;
        }
        return s;
    }
    
    public void shuffle(final boolean Graycode, final float mf) {
        this.chrom = this.mutate(Graycode, mf);
        this.DecodeChrom();
        final int c = (int)(Math.random() * 14.0 + 1.0);
        this.AssignColor(c);
        if (this.color == Color.red) {
            this.type = 1;
        }
        else {
            this.type = 0;
        }
        this.x = (float)Math.random() * (this.width - this.margin * 2) + this.margin;
        this.y = (float)Math.random() * (this.height - this.margin * 2) + this.margin;
        this.xtail = this.x;
        this.ytail = this.y;
        this.vx = (float)Math.random() * this.v0 - this.v0 / 2;
        this.vy = (float)Math.random() * this.v0 - this.v0 / 2;
    }
    
    public void GetFitness() {
        this.fitness = (int)(this.energy * this.EnergyFactor + this.safety * this.SafetyFactor + this.cooperation * this.CooperationFactor);
    }
    
    public void AssignColor(final String c) {
        if (c.equals("BLACK")) {
            this.color = Color.black;
        }
        else if (c.equals("BLUE")) {
            this.color = Color.blue;
        }
        else if (c.equals("CYAN")) {
            this.color = Color.cyan;
        }
        else if (c.equals("GRAY")) {
            this.color = Color.gray;
        }
        else if (c.equals("GREEN")) {
            this.color = Color.green;
        }
        else if (c.equals("LIGHTGRAY")) {
            this.color = Color.lightGray;
        }
        else if (c.equals("MAGENTA")) {
            this.color = Color.magenta;
        }
        else if (c.equals("ORANGE")) {
            this.color = Color.orange;
        }
        else if (c.equals("PINK")) {
            this.color = Color.pink;
        }
        else if (c.equals("RED")) {
            this.color = Color.red;
        }
        else if (c.equals("WHITE")) {
            this.color = Color.white;
        }
        else if (c.equals("YELLOW")) {
            this.color = Color.yellow;
        }
        else {
            this.color = Color.green;
        }
    }
    
    public void AssignColor(final int n) {
        if (n == 0) {
            this.color = Color.black;
        }
        else if (n == 1) {
            this.color = Color.blue;
        }
        else if (n == 2) {
            this.color = Color.cyan;
        }
        else if (n == 4) {
            this.color = Color.gray;
        }
        else if (n == 5) {
            this.color = Color.green;
        }
        else if (n == 6) {
            this.color = Color.lightGray;
        }
        else if (n == 7) {
            this.color = Color.magenta;
        }
        else if (n == 8) {
            this.color = Color.orange;
        }
        else if (n == 9) {
            this.color = Color.pink;
        }
        else if (n == 10) {
            this.color = Color.red;
        }
        else if (n == 11) {
            this.color = Color.white;
        }
        else if (n == 12) {
            this.color = Color.yellow;
        }
        else {
            this.color = Color.green;
        }
    }
    
    public void Process() {
        this.xtail = this.x;
        this.ytail = this.y;
        int rev = -1;
        for (int i = 0; i < this.NumberOfNeighbors; ++i) {
            final int d = this.dist(this.neighbors[i]);
            if (d == 0) {
                rev = 0;
            }
            else if (d < this.CollisionDistance) {
                if (this.neighbors[i].type != this.type) {
                    if (this.type == 1) {
                        --this.energy;
                        rev = (int)this.CollisionLocalFactor;
                        if (this.energy < 0) {
                            this.energy = 0;
                        }
                        if (Efloys.CurrentGeneration > 0L) {
                            Efloys.appcontext.showStatus("Generation #" + Efloys.CurrentGeneration + "  Stranger's remainig energy: " + this.energy);
                        }
                        else {
                            Efloys.appcontext.showStatus("Stranger's remainig energy: " + this.energy);
                        }
                    }
                    else {
                        this.energy += (int)this.MaxEnergyDose;
                        rev = (int)this.CollisionStrangerFactor;
                    }
                }
                else {
                    this.safety += (int)this.MaxSafetyDose;
                    rev = (int)this.CollisionBrotherFactor;
                }
            }
            else if (this.type == 1) {
                rev = (int)this.DistLocalFactor;
            }
            else if (this.neighbors[i].type == 1) {
                rev = (int)this.DistStrangerFactor;
            }
            else {
                final int SafetyAddition = (int)(this.MaxSafetyDose * this.CollisionDistance / (d + this.CollisionDistance));
                this.safety += SafetyAddition;
                rev = (int)this.DistBrotherFactor;
            }
            if (this.x < this.neighbors[i].x) {
                this.vx += this.ApproachAcceleration * rev;
            }
            else {
                this.vx -= this.ApproachAcceleration * rev;
            }
            if (this.y < this.neighbors[i].y) {
                this.vy += this.ApproachAcceleration * rev;
            }
            else {
                this.vy -= this.ApproachAcceleration * rev;
            }
        }
        if (this.vx > this.MaxSpeed) {
            this.vx = this.MaxSpeed;
        }
        if (this.vx < -this.MaxSpeed) {
            this.vx = -this.MaxSpeed;
        }
        if (this.vy > this.MaxSpeed) {
            this.vy = this.MaxSpeed;
        }
        if (this.vy < -this.MaxSpeed) {
            this.vy = -this.MaxSpeed;
        }
        if (this.x < 1.0f) {
            this.vx = this.BounceSpeed;
        }
        if (this.x > this.width - 3) {
            this.vx = -this.BounceSpeed;
        }
        if (this.y < 1.0f) {
            this.vy = this.BounceSpeed;
        }
        if (this.y > this.height - 1) {
            this.vy = -this.BounceSpeed;
        }
        if (this.type == 0) {
            final float v = (float)Math.sqrt(this.vx * this.vx + this.vy * this.vy);
            this.energy += (int)(v / this.MaxSpeed * this.MaxEnergyDose);
            if (this.x < this.width / 2) {
                this.vx += this.CenterAcceleration;
            }
            if (this.x > this.width / 2) {
                this.vx -= this.CenterAcceleration;
            }
            if (this.y < this.height / 2) {
                this.vy += this.CenterAcceleration;
            }
            if (this.y > this.height / 2) {
                this.vy -= this.CenterAcceleration;
            }
        }
        this.x += this.vx;
        this.y += this.vy;
        if (this.energy < 1) {
            if (Efloys.WithSound) {
                Efloys.joy.play();
            }
            this.color = Color.green;
            this.type = 0;
            this.energy = 10;
            this.safety = 10;
            this.cooperation = 10;
            Efloys.NewGeneration = true;
        }
        this.GetFitness();
    }
    
    public String mutate() {
        String st = this.chrom;
        final StringBuffer sb = new StringBuffer(this.chrom.length());
        for (int i = 0; i < this.chrom.length(); ++i) {
            final char old;
            char kar = old = this.chrom.charAt(i);
            if (Efloys.Flip(this.MutationFactor)) {
                kar = (char)(Math.random() * this.params[i].nsteps + 65.0);
            }
            sb.append(kar);
        }
        st = sb.toString();
        return st;
    }
    
    private String mutate(final boolean GrayCode, final float mf) {
        String st = this.chrom;
        final StringBuffer sb = new StringBuffer(this.chrom.length());
        for (int i = 0; i < this.chrom.length(); ++i) {
            final char old;
            char kar = old = this.chrom.charAt(i);
            if (Efloys.Flip(mf)) {
                if (GrayCode) {
                    if (Efloys.Flip(0.5f) && kar < '\u00fa') {
                        ++kar;
                    }
                    else if (kar > 'A') {
                        kar -= 1;
                    }
                }
                else {
                    kar = (char)(Math.random() * this.params[i].nsteps + 65.0);
                }
            }
            sb.append(kar);
        }
        st = sb.toString();
        return st;
    }
}
