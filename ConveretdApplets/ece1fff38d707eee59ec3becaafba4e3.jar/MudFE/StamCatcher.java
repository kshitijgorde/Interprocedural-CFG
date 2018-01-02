// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

class StamCatcher implements DisplayInterface
{
    protected DisplayInterface d1;
    StringBuffer sb;
    HealthPanel hp;
    MudFrame theframe;
    
    public void reset() {
        this.sb.setLength(0);
    }
    
    public StamCatcher(final DisplayInterface d1, final MudFrame tf) {
        this.sb = new StringBuffer(10);
        this.d1 = d1;
        this.theframe = tf;
    }
    
    public void update() {
        if (this.hp == null) {
            this.hp = this.theframe.hp;
        }
        if (this.hp != null) {
            try {
                this.hp.setstam(Integer.parseInt(this.sb.toString()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.reset();
    }
    
    public void addChar(final char b, final Attribute a) {
        this.d1.addChar(b, a);
        this.sb.append(b);
    }
    
    public void cleol() {
        this.d1.cleol();
    }
    
    public void addString(final String b, final Attribute a) {
        this.d1.addString(b, a);
        this.sb.append(b);
    }
    
    public void addString(final String b) {
        this.d1.addString(b);
        this.sb.append(b);
    }
    
    public void cls() {
        this.d1.cls();
    }
}
