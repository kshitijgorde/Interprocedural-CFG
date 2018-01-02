// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Check
{
    String[] host;
    boolean ok;
    
    public boolean checkLicence(final String thathost) {
        this.ok = false;
        for (int z = 0; z < this.host.length; ++z) {
            if (thathost.endsWith(this.host[z])) {
                this.ok = true;
            }
        }
        return this.ok;
    }
    
    public SG1_Check() {
        (this.host = new String[4])[0] = "stsi.com";
        this.host[1] = "stsionline.com";
        this.host[2] = "stevetech.com";
        this.host[3] = "gfos.hr";
        this.ok = false;
    }
}
