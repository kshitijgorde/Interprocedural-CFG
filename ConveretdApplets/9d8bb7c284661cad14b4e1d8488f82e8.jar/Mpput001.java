// 
// Decompiled by Procyon v0.5.30
// 

public class Mpput001
{
    InfoWindow \u010d;
    boolean \u0116;
    String \u013c;
    int \u0151;
    int \u0152;
    int \u0153;
    int \u0154;
    String[] \u015b;
    
    Mpput001(final String \u013c) {
        this.\u0116 = false;
        this.\u0151 = 20;
        this.\u010d = new InfoWindow("Mpput001");
        this.\u013c = \u013c;
        this.\u015b = new String[this.\u0151];
        for (int i = 0; i < this.\u0151; ++i) {
            this.\u015b[i] = new String("");
        }
    }
    
    private void \u014e(final String s) {
        final int \u0155 = this.\u0154;
        ++this.\u0154;
        if (this.\u0154 == this.\u0151) {
            this.\u0154 = 0;
        }
        if (this.\u0154 != this.\u0153) {
            this.\u015b[\u0155] = s;
            ++this.\u0152;
            return;
        }
        if (this.\u0154 == 0) {
            this.\u0154 = this.\u0151 - 1;
            return;
        }
        --this.\u0154;
    }
    
    public void addPacket(final String s) {
        this.\u014e(s);
    }
    
    public String getBuffer() {
        int n = 0;
        final StringBuffer sb = new StringBuffer();
        while (this.\u0152 > 0 && n < 10) {
            final int \u0153 = this.\u0153;
            ++this.\u0153;
            if (this.\u0153 == this.\u0151) {
                this.\u0153 = 0;
            }
            --this.\u0152;
            final String s = this.\u015b[\u0153];
            this.\u015b[\u0153] = "OLD <" + this.\u015b[\u0153] + ">";
            ++n;
            sb.append("<PACKET>");
            sb.append(s);
            sb.append("</PACKET>");
        }
        if (n == 0) {
            return "[NULL]";
        }
        return sb.toString();
    }
    
    public boolean emptyBuffer() {
        return this.\u0152 == 0;
    }
    
    public void clearBuffer() {
        for (int i = 0; i < this.\u0151; ++i) {
            this.\u015b[i] = "";
        }
    }
    
    public void reset() {
        this.emptyBuffer();
        this.clearBuffer();
    }
    
    public void showBuffer() {
        if (this.\u0116) {
            for (int i = 0; i < this.\u0151; ++i) {
                this.infoWindowMessage("BUFFER[" + i + "]=[" + this.\u015b[i] + "]");
                if (i == this.\u0154) {
                    this.infoWindowMessage("-> last packet");
                }
                if (i == this.\u0153) {
                    this.infoWindowMessage("-> next packet");
                }
                this.infoWindowMessage("\n");
            }
        }
    }
    
    public void consoleMessage(final String s) {
        System.out.println(s);
    }
    
    public void showInfoWindow() {
        this.\u0116 = true;
        this.\u010d.show();
        this.infoWindowMessage("version 2.02");
    }
    
    public void hideInfoWindow() {
        this.\u0116 = false;
        this.\u010d.hide();
    }
    
    public void infoWindowMessage(final String s) {
        if (this.\u0116) {
            this.\u010d.addText(s);
        }
    }
}
