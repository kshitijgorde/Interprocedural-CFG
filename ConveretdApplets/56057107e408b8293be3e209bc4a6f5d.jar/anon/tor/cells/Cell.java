// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.cells;

public abstract class Cell
{
    public static final int CELL_SIZE = 512;
    public static final int CELL_PAYLOAD_SIZE = 509;
    private int m_circID;
    private int m_command;
    protected byte[] m_payload;
    
    protected Cell(final int command) {
        this.m_circID = 0;
        this.m_command = command;
        this.m_payload = new byte[509];
    }
    
    protected Cell(final int n, final int circID) {
        this(n);
        this.m_circID = circID;
    }
    
    protected Cell(final int n, final int n2, final byte[] array) {
        this(n, n2);
        this.setPayload(array, 0);
    }
    
    protected Cell(final int n, final int n2, final byte[] array, final int n3) {
        this(n, n2);
        this.setPayload(array, n3);
    }
    
    public byte[] getCellData() {
        final byte[] array = new byte[512];
        array[0] = (byte)(this.m_circID >> 8 & 0xFF);
        array[1] = (byte)(this.m_circID & 0xFF);
        array[2] = (byte)(this.m_command & 0xFF);
        System.arraycopy(this.m_payload, 0, array, 3, 509);
        return array;
    }
    
    public int getCommand() {
        return this.m_command;
    }
    
    public int getCircuitID() {
        return this.m_circID;
    }
    
    public byte[] getPayload() {
        return this.m_payload;
    }
    
    public void setPayload(final byte[] array, final int n) {
        System.arraycopy(array, n, this.m_payload, 0, Math.min(509, array.length));
    }
    
    public static Cell createCell(final byte[] array) {
        if (array.length != 512) {
            return null;
        }
        final int n = (array[0] & 0xFF) << 8 | (array[1] & 0xFF);
        Cell cell = null;
        switch (array[2] & 0xFF) {
            case 2: {
                cell = new CreatedCell(n, array, 3);
                break;
            }
            case 3: {
                cell = new RelayCell(n, array, 3);
                break;
            }
            case 4: {
                cell = new DestroyCell(n, array, 3);
                break;
            }
            case 0: {
                cell = new PaddingCell(n, array, 3);
                break;
            }
            default: {
                cell = null;
                break;
            }
        }
        return cell;
    }
}
