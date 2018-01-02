import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class TGATE
{
    public Color Background;
    public int x;
    public int y;
    public final int xsize = 60;
    public final int ysize = 60;
    String name;
    Voltage v_source;
    Voltage v_gate_n;
    Voltage v_gate_p;
    Voltage v_drain;
    Voltage v_channel;
    Voltage v_undef;
    Wire w_drain;
    Wire w_source;
    Wire w_gate_n;
    Wire w_gate_p;
    
    TGATE(final int x, final int y, final String name, final Voltage v_source, final Voltage v_gate_n, final Voltage v_drain) {
        this.Background = new Color(183, 183, 183);
        this.x = x;
        this.y = y;
        this.name = name;
        this.v_source = v_source;
        this.v_gate_n = v_gate_n;
        this.v_drain = v_drain;
        this.v_channel = new Voltage(2);
        this.v_gate_p = new Voltage(v_gate_n.getinv());
        this.v_undef = new Voltage(2);
        (this.w_gate_p = new Wire(x + 30, y, x + 30, y + 10, this.v_gate_p, "nC")).append(x + 20, y + 10);
        this.w_gate_p.append(x + 40, y + 10);
        (this.w_gate_n = new Wire(x + 30, y + 60, x + 30, y + 50, v_gate_n, "")).append(x + 20, y + 50);
        this.w_gate_n.append(x + 40, y + 50);
        (this.w_source = new Wire(x, y + 30, x + 20, y + 30, v_source, "C")).append(x + 20, y + 15);
        this.w_source.append(x + 20, y + 45);
        this.w_source.addSolderDot(x + 20, y + 30);
        (this.w_drain = new Wire(x + 60, y + 30, x + 40, y + 30, v_drain, "")).append(x + 40, y + 15);
        this.w_drain.append(x + 40, y + 45);
        this.w_drain.addSolderDot(x + 40, y + 30);
    }
    
    public int getXsize() {
        return 60;
    }
    
    public int getYsize() {
        return 60;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setBackground(final Color background) {
        this.Background = background;
    }
    
    public void setVoltages(final Voltage voltage, final Voltage voltage2, final Voltage voltage3) {
        this.v_source.set(voltage.get());
        this.v_gate_n.set(voltage2.get());
        this.v_drain.set(voltage3.get());
        this.v_gate_p.set(this.v_gate_n.getinv());
    }
    
    private void setVoltageColor(final Voltage voltage, final Graphics graphics) {
        switch (voltage.get()) {
            case 0: {
                graphics.setColor(Color.blue);
            }
            case 1: {
                graphics.setColor(Color.red);
            }
            case 2: {
                graphics.setColor(Color.green);
            }
            case 3: {
                graphics.setColor(Color.orange);
            }
            case 4: {
                graphics.setColor(Color.green);
            }
            default: {
                graphics.setColor(Color.green);
                System.out.println("setVoltageColor: Unknown voltage" + voltage);
            }
        }
    }
    
    public void drawTGATE(final Graphics graphics) {
        graphics.setColor(this.Background);
        graphics.fillRect(this.x + 2, this.y + 2, 56, 56);
        this.w_gate_n.drawWire(graphics);
        this.v_gate_p.setinv(this.v_gate_n.get());
        this.w_gate_p.drawWire(graphics);
        this.setVoltageColor(this.v_gate_p, graphics);
        graphics.fillRect(this.x + 28, this.y + 2, 4, 7);
        graphics.fillRect(this.x + 27, this.y + 3, 6, 5);
        switch (this.v_gate_n.get()) {
            case 0: {
                graphics.setColor(Color.black);
                graphics.drawRect(this.x + 20, this.y + 15, 20, 3);
                graphics.drawRect(this.x + 20, this.y + 42, 20, 3);
                break;
            }
            case 1: {
                if (this.v_source.get() == this.v_drain.get()) {
                    this.setVoltageColor(this.v_source, graphics);
                }
                else if (this.v_source.get() == 3) {
                    this.v_source.set(this.v_drain.get());
                    this.setVoltageColor(this.v_drain, graphics);
                }
                else if (this.v_drain.get() == 3) {
                    this.v_drain.set(this.v_source.get());
                    this.setVoltageColor(this.v_source, graphics);
                }
                else {
                    this.setVoltageColor(this.v_undef, graphics);
                }
                graphics.fillRect(this.x + 20, this.y + 15, 20, 4);
                graphics.fillRect(this.x + 20, this.y + 42, 20, 4);
                break;
            }
            case 2:
            case 3: {
                graphics.setColor(Color.green);
                graphics.fillRect(this.x + 20, this.y + 15, 20, 4);
                graphics.fillRect(this.x + 20, this.y + 42, 20, 4);
                break;
            }
            default: {
                graphics.setColor(Color.green);
                graphics.fillRect(this.x + 20, this.y + 15, 20, 4);
                graphics.fillRect(this.x + 20, this.y + 42, 20, 4);
                System.out.println("drawMOS: unknown gate voltage: " + this.v_gate_n.get());
                break;
            }
        }
        this.w_source.drawWire(graphics);
        this.w_drain.drawWire(graphics);
    }
    
    public void drawTGATElabel(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawString(this.name, this.x + 2, this.y + 60 - 2);
    }
    
    public void drawTGATEvoltages(final Graphics graphics) {
        graphics.setColor(Color.black);
        this.w_source.drawVoltageLabel(graphics, 0);
        this.w_drain.drawVoltageLabel(graphics, 1);
        this.w_gate_n.drawVoltageLabel(graphics, 1);
    }
}
