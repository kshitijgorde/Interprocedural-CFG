// 
// Decompiled by Procyon v0.5.30
// 

public abstract class c4
{
    public final int a;
    
    public c4(final int a) {
        this.a = a;
    }
    
    public final int d() {
        return this.a;
    }
    
    public final String e() {
        switch (this.a) {
            case 2: {
                return "LOAD";
            }
            case 4: {
                return "CHANGE";
            }
            case 8: {
                return "EXCHANGE";
            }
            case 16: {
                return "ADD";
            }
            case 32: {
                return "DELETE";
            }
            case 256: {
                return "OPERATION";
            }
            case 64: {
                return "INNER_LOAD";
            }
            case 128: {
                return "INNER_CHANGE";
            }
            default: {
                return "UNDEFINED";
            }
        }
    }
    
    public final String f() {
        final StringBuffer sb = new StringBuffer();
        if ((this.a & 0x1) != 0x0) {
            sb.append("IDENTITY | ");
        }
        if ((this.a & 0x2) != 0x0) {
            sb.append("LOAD | ");
        }
        if ((this.a & 0x4) != 0x0) {
            sb.append("CHANGE | ");
        }
        if ((this.a & 0x8) != 0x0) {
            sb.append("EXCHANGE | ");
        }
        if ((this.a & 0x10) != 0x0) {
            sb.append("ADD | ");
        }
        if ((this.a & 0x20) != 0x0) {
            sb.append("DELETE | ");
        }
        if ((this.a & 0x100) != 0x0) {
            sb.append("OPERATION | ");
        }
        if ((this.a & 0x40) != 0x0) {
            sb.append("INNER_LOAD | ");
        }
        if ((this.a & 0x80) != 0x0) {
            sb.append("INNER_CHANGE | ");
        }
        String s = sb.toString();
        if (s.length() > 0) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }
    
    public abstract String toString();
}
