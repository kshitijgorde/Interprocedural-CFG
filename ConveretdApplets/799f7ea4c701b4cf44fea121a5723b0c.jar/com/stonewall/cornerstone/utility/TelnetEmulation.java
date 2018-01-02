// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.Element;

class TelnetEmulation extends MMLEmulation
{
    int iacCount;
    boolean echo;
    
    TelnetEmulation(final Element root) {
        super(root);
        this.iacCount = 0;
        this.echo = true;
        final String ec = root.getChildText("echo", TelnetEmulation.cnns);
        if (ec != null) {
            this.echo = Boolean.valueOf(ec);
        }
    }
    
    @Override
    void preprocess() throws Exception {
        this.iacProcess();
        if (this.echo) {
            this.wrBfr.clear();
            this.rdBfr.mark();
            this.wrBfr.put(this.rdBfr);
            this.rdBfr.reset();
            this.wrBfr.flip();
            this.simulator.getChannel().write(this.wrBfr);
        }
    }
    
    void iacProcess() {
        for (int i = 0; i < this.rdBfr.limit(); ++i) {
            IAC iac = IAC.valueOf(this.rdBfr.get(i));
            if (iac == IAC.IAC) {
                iac = IAC.valueOf(this.rdBfr.get(++i));
                ++this.iacCount;
                switch (iac) {
                    case SB: {
                        i = this.iacSb(i);
                        break;
                    }
                    case WILL: {
                        i = this.iacWill(i);
                        break;
                    }
                    case WONT: {
                        i = this.iacWont(i);
                        break;
                    }
                    case DO: {
                        i = this.iacDo(i);
                        break;
                    }
                    case DONT: {
                        i = this.iacDont(i);
                        break;
                    }
                    default: {
                        i = this.rdBfr.limit();
                        break;
                    }
                }
                while (this.rdBfr.position() <= i) {
                    this.rdBfr.get();
                }
            }
        }
    }
    
    int iacSb(int i) {
        TelnetEmulation.log.debug("SB (begin)");
        while (i < this.rdBfr.limit() && IAC.valueOf(this.rdBfr.get(i)) != IAC.SE) {
            ++i;
        }
        TelnetEmulation.log.debug("SB (end)");
        return i;
    }
    
    int iacDo(int i) {
        final IAC iac = IAC.valueOf(this.rdBfr.get(++i));
        TelnetEmulation.log.debug("DO " + iac);
        switch (iac) {
            case ECHO: {
                this.setEcho(true);
                this.iacWrite(IAC.IAC, IAC.WILL, iac);
                break;
            }
            case SGA: {
                this.setEcho(false);
                this.iacWrite(IAC.IAC, IAC.WONT, iac);
                break;
            }
            case STATUS:
            case TM:
            case TTY_TYPE:
            case NAWS:
            case TTY_SP:
            case RFC:
            case LM:
            case ENV: {
                this.iacWrite(IAC.IAC, IAC.WONT, iac);
                break;
            }
        }
        return i;
    }
    
    int iacDont(int i) {
        final IAC iac = IAC.valueOf(this.rdBfr.get(++i));
        TelnetEmulation.log.debug("DONT " + iac);
        switch (iac) {
            case ECHO: {
                this.setEcho(false);
                break;
            }
        }
        this.iacWrite(IAC.IAC, IAC.WONT, iac);
        return i;
    }
    
    int iacWill(int i) {
        final IAC iac = IAC.valueOf(this.rdBfr.get(++i));
        TelnetEmulation.log.debug("WILL " + iac);
        switch (iac) {
            case ECHO: {
                this.iacWrite(IAC.IAC, IAC.DO, iac);
                this.setEcho(false);
                break;
            }
            case SGA:
            case STATUS:
            case TM:
            case TTY_TYPE:
            case NAWS:
            case TTY_SP:
            case RFC:
            case LM:
            case ENV: {
                this.iacWrite(IAC.IAC, IAC.DONT, iac);
                break;
            }
        }
        return i;
    }
    
    int iacWont(int i) {
        final IAC iac = IAC.valueOf(this.rdBfr.get(++i));
        TelnetEmulation.log.debug("WONT " + iac);
        switch (iac) {
            case ECHO: {
                this.setEcho(true);
                break;
            }
        }
        this.iacWrite(IAC.IAC, IAC.DONT, iac);
        return i;
    }
    
    void iacWrite(final IAC... iacs) {
        final StringBuilder sb = new StringBuilder();
        final byte[] bytes = new byte[iacs.length];
        for (int i = 0; i < iacs.length; ++i) {
            bytes[i] = iacs[i].toByte();
            sb.append(iacs[i]);
            sb.append(' ');
        }
        TelnetEmulation.log.debug(sb);
        try {
            this.wrBfr.clear();
            this.wrBfr.put(bytes);
            this.wrBfr.flip();
            this.simulator.channel.write(this.wrBfr);
        }
        catch (Exception e) {
            TelnetEmulation.log.error(iacs, e);
        }
    }
    
    void setEcho(final boolean flag) {
        TelnetEmulation.log.debug("ECHO=" + flag);
        this.echo = flag;
    }
    
    enum IAC
    {
        ECHO("ECHO", 0, 1), 
        SGA("SGA", 1, 3), 
        STATUS("STATUS", 2, 5), 
        TM("TM", 3, 7), 
        TTY_TYPE("TTY_TYPE", 4, 24), 
        NAWS("NAWS", 5, 31), 
        TTY_SP("TTY_SP", 6, 32), 
        RFC("RFC", 7, 33), 
        LM("LM", 8, 34), 
        ENV("ENV", 9, 36), 
        SE("SE", 10, 240), 
        NOP("NOP", 11, 241), 
        DM("DM", 12, 242), 
        BRK("BRK", 13, 243), 
        IP("IP", 14, 244), 
        AO("AO", 15, 245), 
        AYT("AYT", 16, 246), 
        EC("EC", 17, 247), 
        EL("EL", 18, 248), 
        GA("GA", 19, 249), 
        SB("SB", 20, 250), 
        WILL("WILL", 21, 251), 
        WONT("WONT", 22, 252), 
        DO("DO", 23, 253), 
        DONT("DONT", 24, 254), 
        IAC("IAC", 25, 255), 
        BAD("BAD", 26, 0);
        
        final int v;
        
        private IAC(final String s, final int n, final int b) {
            this.v = b;
        }
        
        static IAC valueOf(final byte b) {
            IAC[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final IAC iac = values[i];
                if (iac.toByte() == b) {
                    return iac;
                }
            }
            return IAC.BAD;
        }
        
        byte toByte() {
            return (byte)(Object)Integer.valueOf(this.v);
        }
    }
}
