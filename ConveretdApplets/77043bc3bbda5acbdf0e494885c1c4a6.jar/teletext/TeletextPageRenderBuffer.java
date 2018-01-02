// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextPageRenderBuffer
{
    TeletextPage teletextPage;
    TeletextPageRenderCell[][] cellMatrix;
    boolean[] doubleHeightFlags;
    
    public TeletextPageRenderBuffer(final TeletextPage page) {
        this.setPage(page);
    }
    
    public void setPage(final TeletextPage teletextPage) {
        this.teletextPage = teletextPage;
        this.cellMatrix = new TeletextPageRenderCell[this.teletextPage.getHeight()][this.teletextPage.getWidth()];
        this.doubleHeightFlags = new boolean[this.teletextPage.getHeight()];
        for (int i = 0; i < this.teletextPage.getHeight(); ++i) {
            for (int j = 0; j < this.teletextPage.getWidth(); ++j) {
                this.cellMatrix[i][j] = new TeletextPageRenderCell();
            }
            this.doubleHeightFlags[i] = false;
        }
    }
    
    public TeletextPage getPage() {
        return this.teletextPage;
    }
    
    public TeletextPageRenderCell getCellAt(final int n, final int n2) {
        return this.cellMatrix[n2][n];
    }
    
    public boolean getDoubleHeightFlag(final int n) {
        return this.doubleHeightFlags[n];
    }
    
    public void processLine(final int n) {
        this.doubleHeightFlags[n] = false;
        if (n > 0 && this.doubleHeightFlags[n - 1]) {
            return;
        }
        this.cellMatrix[n][0].reset();
        byte foreground = 7;
        byte background = 0;
        int n2 = 0;
        boolean doubleHeight = false;
        boolean b = false;
        boolean b2 = false;
        for (int i = 0; i < this.teletextPage.getWidth(); ++i) {
            final TeletextPageRenderCell teletextPageRenderCell = this.cellMatrix[n][i];
            TeletextPageRenderCell teletextPageRenderCell2 = null;
            if (i > 0) {
                teletextPageRenderCell2 = this.cellMatrix[n][i - 1];
            }
            final char asciiToCustom = TeletextPage.asciiToCustom(this.teletextPage.getCharacterAt(i, n));
            teletextPageRenderCell.character = asciiToCustom;
            if ((byte)asciiToCustom == 28) {
                background = 0;
            }
            if ((byte)asciiToCustom == 29) {
                background = foreground;
            }
            if ((byte)asciiToCustom == 25) {
                b = false;
            }
            if ((byte)asciiToCustom == 26) {
                b = true;
            }
            if ((byte)asciiToCustom == 12) {
                doubleHeight = false;
            }
            if ((byte)asciiToCustom == 13) {
                doubleHeight = true;
                this.doubleHeightFlags[n] = true;
            }
            if ((byte)asciiToCustom == 9) {}
            if ((byte)asciiToCustom == 8) {}
            if ((byte)asciiToCustom == 30) {
                b2 = true;
            }
            if ((byte)asciiToCustom == 31) {
                b2 = false;
            }
            if ((byte)asciiToCustom == 11) {}
            if ((byte)asciiToCustom == 10) {}
            if ((byte)asciiToCustom == 24) {}
            if ((byte)asciiToCustom == 27) {}
            teletextPageRenderCell.doubleHeight = doubleHeight;
            teletextPageRenderCell.foreground = foreground;
            teletextPageRenderCell.background = background;
            if (b2 && n2 != 0 && TeletextCodes.isCode(asciiToCustom) && TeletextPage.isGraphicsCharacter(teletextPageRenderCell2.character)) {
                teletextPageRenderCell.character = teletextPageRenderCell2.character;
            }
            teletextPageRenderCell.visibleCharacter = teletextPageRenderCell.character;
            if (n2 != 0) {
                if (teletextPageRenderCell.character >= ' ' && teletextPageRenderCell.character <= '?') {
                    final TeletextPageRenderCell teletextPageRenderCell3 = teletextPageRenderCell;
                    teletextPageRenderCell3.visibleCharacter += '`';
                    if (b) {
                        final TeletextPageRenderCell teletextPageRenderCell4 = teletextPageRenderCell;
                        teletextPageRenderCell4.visibleCharacter += '@';
                    }
                }
                else if (teletextPageRenderCell.character >= '`' && teletextPageRenderCell.character <= '\u007f') {
                    final TeletextPageRenderCell teletextPageRenderCell5 = teletextPageRenderCell;
                    teletextPageRenderCell5.visibleCharacter += '@';
                    if (b) {
                        final TeletextPageRenderCell teletextPageRenderCell6 = teletextPageRenderCell;
                        teletextPageRenderCell6.visibleCharacter += '@';
                    }
                }
            }
            if (TeletextCodes.isAlphaCode(asciiToCustom)) {
                n2 = 0;
                foreground = TeletextCodes.codeToColor(asciiToCustom);
            }
            if (TeletextCodes.isGraphCode(asciiToCustom)) {
                n2 = 1;
                foreground = TeletextCodes.codeToColor(asciiToCustom);
            }
        }
    }
    
    public void process() {
        for (int i = 0; i < this.teletextPage.getHeight(); ++i) {
            this.processLine(i);
        }
    }
    
    public int[] getCharacterBoundingBox(final int n, final int n2, final int n3, final int n4) {
        final int[] characterBoundingBox = TeletextFont.getCharacterBoundingBox(n, n2, n3, n4);
        if (this.doubleHeightFlags[n4]) {
            final int[] array = characterBoundingBox;
            final int n5 = 3;
            array[n5] += TeletextFont.getPixelHeight();
        }
        return characterBoundingBox;
    }
    
    public int[] getGraphBoundingBox(final int n, final int n2, final int n3, final int n4) {
        final int[] graphBoundingBox = TeletextFont.getGraphBoundingBox(n, n2, n3, n4);
        final int[] decomposeGraphY = TeletextFont.decomposeGraphY(n2);
        if (this.doubleHeightFlags[decomposeGraphY[0]]) {
            final int[] array = graphBoundingBox;
            final int n5 = 1;
            array[n5] += TeletextFont.getVerticalGraphLimit(decomposeGraphY[1]);
        }
        final int[] decomposeGraphY2 = TeletextFont.decomposeGraphY(n4);
        if (this.doubleHeightFlags[decomposeGraphY2[0]]) {
            final int[] array2 = graphBoundingBox;
            final int n6 = 3;
            array2[n6] += TeletextFont.getVerticalGraphLimit(decomposeGraphY2[1] + 1);
        }
        return graphBoundingBox;
    }
    
    public int[] getCharacterBoundingBox(final int n, final int n2) {
        return this.getCharacterBoundingBox(n, n2, n, n2);
    }
    
    public int[] getGraphBoundingBox(final int n, final int n2) {
        return this.getGraphBoundingBox(n, n2, n, n2);
    }
    
    public int[] getGraphPixelCenter(final int n, final int n2) {
        final int[] array = new int[2];
        final int[] graphBoundingBox = this.getGraphBoundingBox(n, n2);
        array[0] = (graphBoundingBox[0] + graphBoundingBox[2]) / 2;
        array[1] = (graphBoundingBox[1] + graphBoundingBox[3]) / 2;
        return array;
    }
    
    public int[] getCharacterRectangleParameters(final int n, final int n2, final int n3, final int n4) {
        final int[] characterBoundingBox;
        final int[] array = characterBoundingBox = this.getCharacterBoundingBox(n, n2, n3, n4);
        final int n5 = 2;
        characterBoundingBox[n5] -= array[0];
        final int[] array2 = array;
        final int n6 = 3;
        array2[n6] -= array[1];
        return array;
    }
    
    public int[] getGraphRectangleParameters(final int n, final int n2, final int n3, final int n4) {
        final int[] graphBoundingBox;
        final int[] array = graphBoundingBox = this.getGraphBoundingBox(n, n2, n3, n4);
        final int n5 = 2;
        graphBoundingBox[n5] -= array[0];
        final int[] array2 = array;
        final int n6 = 3;
        array2[n6] -= array[1];
        return array;
    }
    
    public int[] getCharacterRectangleParameters(final int n, final int n2) {
        return this.getCharacterRectangleParameters(n, n2, n, n2);
    }
    
    public int[] getGraphRectangleParameters(final int n, final int n2) {
        return this.getGraphRectangleParameters(n, n2, n, n2);
    }
}
