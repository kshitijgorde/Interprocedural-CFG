import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class MOS
{
    public static final int NMOS = 1;
    public static final int PMOS = 2;
    public Color Background;
    public int x;
    public int y;
    public final int xsize = 60;
    public final int ysize = 60;
    public int type;
    String name;
    Voltage v_source;
    Voltage v_gate;
    Voltage v_drain;
    Voltage v_channel;
    Wire w_drain;
    Wire w_source;
    Wire w_gate;
    
    MOS(final int x, final int y, final int type, final String name, final Voltage voltage, final Voltage voltage2, final Voltage voltage3) {
        this.Background = Color.lightGray;
        this.x = x;
        this.y = y;
        this.type = type;
        this.name = name;
        this.v_source = new Voltage(voltage);
        this.v_gate = new Voltage(voltage2);
        this.v_drain = new Voltage(voltage3);
        this.v_channel = new Voltage(2);
        if (type == 1) {
            (this.w_gate = new Wire(x, y + 30, x + 30, y + 30, voltage2)).append(x + 30, y + 20);
            this.w_gate.append(x + 30, y + 39);
            (this.w_source = new Wire(x + 35, y + 40, x + 50, y + 40, voltage)).append(x + 50, y + 59);
            (this.w_drain = new Wire(x + 35, y + 19, x + 50, y + 19, voltage3)).append(x + 50, y);
            return;
        }
        (this.w_gate = new Wire(x, y + 30, x + 30, y + 30, voltage2)).append(x + 30, y + 20);
        this.w_gate.append(x + 30, y + 39);
        (this.w_source = new Wire(x + 35, y + 19, x + 50, y + 19, voltage)).append(x + 50, y);
        (this.w_drain = new Wire(x + 35, y + 40, x + 50, y + 40, voltage3)).append(x + 50, y + 59);
    }
    
    public int getXsize() {
        return 60;
    }
    
    public int getYsize() {
        return 60;
    }
    
    public int getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setBackground(final Color background) {
        this.Background = background;
    }
    
    public void setVoltages(final Voltage voltage, final Voltage voltage2, final Voltage voltage3) {
        this.v_source.set(voltage.get());
        this.v_gate.set(voltage2.get());
        this.v_drain.set(voltage3.get());
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
                graphics.setColor(Color.black);
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
    
    public void drawMOS(final Graphics graphics) {
        graphics.setColor(this.Background);
        graphics.fillRect(this.x + 15, this.y + 10, 40, 40);
        this.w_source.drawWire(graphics);
        this.w_drain.drawWire(graphics);
        this.w_gate.drawWire(graphics);
        if (this.type == 2) {
            this.setVoltageColor(this.v_gate, graphics);
            graphics.fillRect(this.x + 24, this.y + 27, 4, 7);
            graphics.fillRect(this.x + 23, this.y + 28, 6, 5);
            switch (this.v_gate.get()) {
                case 0: {
                    this.setVoltageColor(this.v_source, graphics);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                }
                case 1: {
                    graphics.setColor(Color.black);
                    graphics.drawRect(this.x + 35, this.y + 20, 3, 19);
                }
                case 2:
                case 3: {
                    graphics.setColor(Color.green);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                }
                default: {
                    graphics.setColor(Color.green);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                    System.out.println("drawMOS: unknown gate voltage: " + this.v_gate.get());
                }
            }
        }
        else {
            if (this.type != 1) {
                System.out.println("drawMOS: unknown type" + this.type);
                return;
            }
            switch (this.v_gate.get()) {
                case 0: {
                    graphics.setColor(Color.black);
                    graphics.drawRect(this.x + 35, this.y + 20, 3, 19);
                }
                case 1: {
                    this.setVoltageColor(this.v_source, graphics);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                }
                case 2:
                case 3: {
                    graphics.setColor(Color.green);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                }
                default: {
                    graphics.setColor(Color.green);
                    graphics.fillRect(this.x + 35, this.y + 20, 3, 20);
                    System.out.println("drawMOS: unknown gate voltage: " + this.v_gate.get());
                }
            }
        }
    }
    
    public void drawMOSlabel(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawString(this.name, this.x + 2, this.y + 60 - 2);
    }
    
    public void drawMOSvoltages(final Graphics graphics) {
        graphics.setColor(Color.black);
        this.w_source.drawVoltageLabel(graphics, 1);
        this.w_drain.drawVoltageLabel(graphics, 1);
        this.w_gate.drawVoltageLabel(graphics, 0);
    }
}
