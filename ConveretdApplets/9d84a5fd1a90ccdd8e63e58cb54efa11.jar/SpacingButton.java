import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpacingButton extends ButtonCtrl
{
    protected static final String kstrPrafixNameParam = "space_";
    private boolean m_bVertical;
    
    SpacingButton(final String strSpacingName, final boolean bVertical) {
        super(null, false, null);
        this.m_bVertical = bVertical;
        this.parseSize(strSpacingName);
    }
    
    void parseSize(final String strSpacingName) {
        final boolean bError = true;
        final String strCoor = new String();
        int nVal = 0;
        final int nIndex = 0;
        if (strSpacingName.startsWith("space_")) {
            try {
                final String strValue = strSpacingName.substring("space_".length());
                nVal = Integer.valueOf(strValue, 10);
                if (this.m_bVertical) {
                    this.setSize(1, nVal);
                    super.m_nXSize = 1;
                    super.m_nYSize = nVal;
                }
                else {
                    this.setSize(nVal, 1);
                    super.m_nXSize = nVal;
                    super.m_nYSize = 1;
                }
            }
            catch (NumberFormatException e) {}
            catch (IndexOutOfBoundsException ndxException) {
                System.err.println("Spacing error");
            }
        }
    }
    
    public void paint(final Graphics g) {
    }
}
