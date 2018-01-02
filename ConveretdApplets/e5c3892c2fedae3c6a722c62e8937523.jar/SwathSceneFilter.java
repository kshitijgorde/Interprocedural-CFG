// 
// Decompiled by Procyon v0.5.30
// 

class SwathSceneFilter implements SceneFilter
{
    private MosaicData md;
    private Sensor currSensor;
    private imgViewer applet;
    private int[] cellsInSwathOrder;
    private int[] datesInSwathOrder;
    private int swathIndex;
    private TOC[] tocs;
    private int firstFilteredDate;
    private int lastFilteredDate;
    private boolean enableOnlySelected;
    private boolean onlySelected;
    private boolean useVisibleFlag;
    
    SwathSceneFilter(final MosaicData md, final TOC[] tocs, final Sensor currSensor, final imgViewer applet, final boolean enableOnlySelected) {
        this.cellsInSwathOrder = null;
        this.datesInSwathOrder = null;
        this.tocs = null;
        this.md = md;
        this.tocs = tocs;
        this.currSensor = currSensor;
        this.applet = applet;
        this.enableOnlySelected = enableOnlySelected;
        int n = 0;
        for (int i = 0; i < tocs.length; ++i) {
            if (tocs[i].valid) {
                n += tocs[i].numImg;
            }
        }
        this.cellsInSwathOrder = null;
        this.datesInSwathOrder = null;
        this.swathIndex = 0;
        if (n > 0) {
            this.cellsInSwathOrder = new int[n];
            this.datesInSwathOrder = new int[n];
            final int[] array = new int[tocs.length];
            for (int j = 0; j < n; ++j) {
                int n2 = 99999999;
                int n3 = -99999999;
                int n4 = 0;
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        final int n5 = l * 3 + k;
                        final TOC toc = tocs[n5];
                        if (toc.valid && array[n5] < toc.numImg) {
                            final Metadata metadata = toc.scenes[array[n5]];
                            final int date = metadata.date;
                            if (date < n2) {
                                n4 = n5;
                                n2 = date;
                                n3 = metadata.ulY;
                            }
                            else if (date == n2 && metadata.ulY > n3) {
                                n4 = n5;
                                n3 = metadata.ulY;
                            }
                        }
                    }
                }
                this.cellsInSwathOrder[j] = this.getHash(tocs[n4]);
                this.datesInSwathOrder[j] = array[n4];
                final int[] array2 = array;
                final int n6 = n4;
                ++array2[n6];
            }
        }
    }
    
    private final int getHash(final TOC toc) {
        return (toc.gridCol + 1000 << 16) + toc.gridRow + 1000;
    }
    
    private final int getColumn(final int n) {
        return (n >> 16) - 1000;
    }
    
    private final int getRow(final int n) {
        return (n & 0xFFFF) - 1000;
    }
    
    private final void updateSwathIndex() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        final TOC currentCell = this.md.getCurrentCell();
        final int hash = this.getHash(currentCell);
        final int currentDateIndex = currentCell.currentDateIndex;
        if (this.cellsInSwathOrder[this.swathIndex] != hash || this.datesInSwathOrder[this.swathIndex] != currentDateIndex) {
            int swathIndex;
            for (swathIndex = 0; swathIndex < this.cellsInSwathOrder.length - 1 && (hash != this.cellsInSwathOrder[swathIndex] || currentDateIndex != this.datesInSwathOrder[swathIndex]); ++swathIndex) {}
            this.swathIndex = swathIndex;
        }
    }
    
    @Override
    public void nextDate() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        final boolean userDefinedAreaClosed = this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed();
        this.updateSwathIndex();
        ++this.swathIndex;
        if (this.swathIndex >= this.lastFilteredDate) {
            this.swathIndex = this.lastFilteredDate;
        }
        final int hash = this.getHash(this.md.getCurrentCell());
        while (this.swathIndex < this.lastFilteredDate) {
            final int n = this.cellsInSwathOrder[this.swathIndex];
            if (!this.onlySelected || n == hash) {
                final int n2 = this.datesInSwathOrder[this.swathIndex];
                final int colRowToCell = this.md.colRowToCell(this.getColumn(n), this.getRow(n));
                if (colRowToCell != -1) {
                    final Metadata metadata = this.tocs[colRowToCell].scenes[n2];
                    if (metadata.visible) {
                        if (!userDefinedAreaClosed) {
                            break;
                        }
                        if (this.applet.userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(metadata)) {
                            break;
                        }
                    }
                }
            }
            ++this.swathIndex;
        }
        final int n3 = this.cellsInSwathOrder[this.swathIndex];
        this.md.drawNewDate(this.getColumn(n3), this.getRow(n3), this.datesInSwathOrder[this.swathIndex]);
    }
    
    @Override
    public void prevDate() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        this.updateSwathIndex();
        final boolean userDefinedAreaClosed = this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed();
        --this.swathIndex;
        if (this.swathIndex < this.firstFilteredDate) {
            this.swathIndex = this.firstFilteredDate;
        }
        final int hash = this.getHash(this.md.getCurrentCell());
        while (this.swathIndex > this.firstFilteredDate) {
            final int n = this.cellsInSwathOrder[this.swathIndex];
            if (!this.onlySelected || n == hash) {
                final int n2 = this.datesInSwathOrder[this.swathIndex];
                final int colRowToCell = this.md.colRowToCell(this.getColumn(n), this.getRow(n));
                if (colRowToCell != -1) {
                    final Metadata metadata = this.tocs[colRowToCell].scenes[n2];
                    if (metadata.visible) {
                        if (!userDefinedAreaClosed) {
                            break;
                        }
                        if (this.applet.userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(metadata)) {
                            break;
                        }
                    }
                }
            }
            --this.swathIndex;
        }
        final int n3 = this.cellsInSwathOrder[this.swathIndex];
        this.md.drawNewDate(this.getColumn(n3), this.getRow(n3), this.datesInSwathOrder[this.swathIndex]);
    }
    
    @Override
    public void gotoDate(final int n, final int n2) {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        final int n3 = n * 10000 + n2 * 100;
        int n4 = this.datesInSwathOrder[this.firstFilteredDate];
        int firstFilteredDate = this.firstFilteredDate;
        final boolean userDefinedAreaClosed = this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed();
        final int hash = this.getHash(this.md.getCurrentCell());
        for (int i = this.firstFilteredDate; i <= this.lastFilteredDate; ++i) {
            if (!this.onlySelected || hash == this.cellsInSwathOrder[i]) {
                final int n5 = this.datesInSwathOrder[i];
                final int n6 = this.cellsInSwathOrder[i];
                final int colRowToCell = this.md.colRowToCell(this.getColumn(n6), this.getRow(n6));
                if (colRowToCell != -1) {
                    final Metadata metadata = this.tocs[colRowToCell].scenes[n5];
                    if (metadata.visible && (!userDefinedAreaClosed || this.applet.userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(metadata))) {
                        n4 = n5;
                        firstFilteredDate = i;
                        if (metadata.date >= n3) {
                            break;
                        }
                    }
                }
            }
        }
        final int n7 = this.cellsInSwathOrder[firstFilteredDate];
        this.md.drawNewDate(this.getColumn(n7), this.getRow(n7), n4);
    }
    
    @Override
    public void filter() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        this.onlySelected = false;
        this.useVisibleFlag = false;
        final int numCellsAtResolution = this.currSensor.getNumCellsAtResolution(this.md.pixelSize);
        if (numCellsAtResolution == 0) {
            this.onlySelected = this.enableOnlySelected;
        }
        else if (numCellsAtResolution == 1) {
            this.useVisibleFlag = true;
        }
        final boolean userDefinedAreaClosed = this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed();
        final int hash = this.getHash(this.md.getCurrentCell());
        this.firstFilteredDate = this.cellsInSwathOrder.length - 1;
        this.lastFilteredDate = 0;
        for (int i = 0; i < this.cellsInSwathOrder.length; ++i) {
            final int n = this.cellsInSwathOrder[i];
            if (!this.onlySelected || n == hash) {
                final int n2 = this.datesInSwathOrder[i];
                final int colRowToCell = this.md.colRowToCell(this.getColumn(n), this.getRow(n));
                if (colRowToCell != -1) {
                    final Metadata metadata = this.tocs[colRowToCell].scenes[n2];
                    if (metadata.visible && (!userDefinedAreaClosed || this.applet.userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(metadata))) {
                        this.firstFilteredDate = i;
                        break;
                    }
                }
            }
        }
        for (int j = this.cellsInSwathOrder.length - 1; j >= this.firstFilteredDate; --j) {
            final int n3 = this.cellsInSwathOrder[j];
            if (!this.onlySelected || n3 == hash) {
                final int n4 = this.datesInSwathOrder[j];
                final int colRowToCell2 = this.md.colRowToCell(this.getColumn(n3), this.getRow(n3));
                if (colRowToCell2 != -1) {
                    final Metadata metadata2 = this.tocs[colRowToCell2].scenes[n4];
                    if (metadata2.visible && (!userDefinedAreaClosed || this.applet.userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(metadata2))) {
                        this.lastFilteredDate = j;
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isNextDateAvailable() {
        this.updateSwathIndex();
        return this.swathIndex < this.lastFilteredDate;
    }
    
    @Override
    public boolean isPrevDateAvailable() {
        this.updateSwathIndex();
        return this.swathIndex > this.firstFilteredDate;
    }
    
    @Override
    public void nextDate(final Metadata metadata) {
    }
    
    @Override
    public void prevDate(final Metadata metadata) {
    }
    
    @Override
    public boolean isNextDateAvailable(final Metadata metadata) {
        return false;
    }
    
    @Override
    public boolean isPrevDateAvailable(final Metadata metadata) {
        return false;
    }
    
    @Override
    public void gotoFirstDate() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        final int n = this.datesInSwathOrder[this.firstFilteredDate];
        final int n2 = this.cellsInSwathOrder[this.firstFilteredDate];
        this.md.drawNewDate(this.getColumn(n2), this.getRow(n2), n);
    }
    
    @Override
    public void gotoLastDate() {
        if (this.cellsInSwathOrder == null) {
            return;
        }
        final int n = this.datesInSwathOrder[this.lastFilteredDate];
        final int n2 = this.cellsInSwathOrder[this.lastFilteredDate];
        this.md.drawNewDate(this.getColumn(n2), this.getRow(n2), n);
    }
    
    @Override
    public int getFirstYear() {
        final int n = this.cellsInSwathOrder[0];
        final int colRowToCell = this.md.colRowToCell(this.getColumn(n), this.getRow(n));
        if (colRowToCell != -1) {
            return this.tocs[colRowToCell].scenes[0].date / 10000;
        }
        return 0;
    }
    
    @Override
    public int getLastYear() {
        final int n = this.cellsInSwathOrder[this.cellsInSwathOrder.length - 1];
        final int colRowToCell = this.md.colRowToCell(this.getColumn(n), this.getRow(n));
        if (colRowToCell != -1) {
            final TOC toc = this.tocs[colRowToCell];
            return toc.scenes[toc.numImg - 1].date / 10000;
        }
        return 0;
    }
}
