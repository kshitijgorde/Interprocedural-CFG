// 
// Decompiled by Procyon v0.5.30
// 

class LandsatSceneFilter implements SceneFilter
{
    private MosaicData md;
    private int firstFilteredDate;
    private int lastFilteredDate;
    
    LandsatSceneFilter(final MosaicData md) {
        this.md = md;
    }
    
    @Override
    public void nextDate() {
        this.nextDate(this.md.getCurrentCell());
    }
    
    @Override
    public void nextDate(final Metadata metadata) {
        this.nextDate(this.md.getCellForScene(metadata));
    }
    
    private void nextDate(final TOC toc) {
        if (toc.numImg >= 1) {
            int n;
            for (n = toc.currentDateIndex + 1; n < toc.numImg && !toc.scenes[n].visible; ++n) {}
            if (n < toc.numImg) {
                this.md.drawNewDate(toc.gridCol, toc.gridRow, n);
            }
        }
    }
    
    @Override
    public void prevDate() {
        this.prevDate(this.md.getCurrentCell());
    }
    
    @Override
    public void prevDate(final Metadata metadata) {
        this.prevDate(this.md.getCellForScene(metadata));
    }
    
    private void prevDate(final TOC toc) {
        if (toc.numImg >= 1) {
            int n;
            for (n = toc.currentDateIndex - 1; n >= 0 && !toc.scenes[n].visible; --n) {}
            if (n >= 0) {
                this.md.drawNewDate(toc.gridCol, toc.gridRow, n);
            }
        }
    }
    
    @Override
    public void gotoDate(final int n, final int n2) {
        final TOC currentCell = this.md.getCurrentCell();
        if (!currentCell.valid) {
            return;
        }
        int n3 = n * 10000 + n2 * 100;
        final int lastFilteredDate = this.lastFilteredDate;
        if (n3 > currentCell.scenes[lastFilteredDate].date) {
            n3 = currentCell.scenes[lastFilteredDate].date;
        }
        if (n3 < currentCell.scenes[0].date) {
            n3 = currentCell.scenes[0].date;
        }
        int n4 = 0;
        for (int i = 0; i <= lastFilteredDate; ++i) {
            if (currentCell.scenes[i].visible) {
                n4 = i;
                if (currentCell.scenes[i].date >= n3) {
                    break;
                }
            }
        }
        this.md.drawNewDate(currentCell.gridCol, currentCell.gridRow, n4);
    }
    
    @Override
    public void filter() {
        this.firstFilteredDate = 0;
        this.lastFilteredDate = 0;
        final TOC currentCell = this.md.getCurrentCell();
        if (!currentCell.valid) {
            return;
        }
        this.firstFilteredDate = currentCell.numImg - 1;
        for (int i = 0; i < currentCell.numImg; ++i) {
            if (currentCell.scenes[i].visible) {
                this.firstFilteredDate = i;
                break;
            }
        }
        this.lastFilteredDate = 0;
        for (int j = currentCell.numImg - 1; j >= this.firstFilteredDate; --j) {
            if (currentCell.scenes[j].visible) {
                this.lastFilteredDate = j;
                break;
            }
        }
    }
    
    @Override
    public boolean isNextDateAvailable() {
        return this.md.getCurrentCell().currentDateIndex < this.lastFilteredDate;
    }
    
    @Override
    public boolean isPrevDateAvailable() {
        return this.md.getCurrentCell().currentDateIndex > this.firstFilteredDate;
    }
    
    @Override
    public boolean isNextDateAvailable(final Metadata metadata) {
        final TOC cellForScene = this.md.getCellForScene(metadata);
        for (int i = cellForScene.currentDateIndex + 1; i < cellForScene.numImg; ++i) {
            if (cellForScene.scenes[i].visible) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isPrevDateAvailable(final Metadata metadata) {
        final TOC cellForScene = this.md.getCellForScene(metadata);
        for (int i = cellForScene.currentDateIndex - 1; i >= 0; --i) {
            if (cellForScene.scenes[i].visible) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void gotoFirstDate() {
        final TOC currentCell = this.md.getCurrentCell();
        this.md.drawNewDate(currentCell.gridCol, currentCell.gridRow, this.firstFilteredDate);
    }
    
    @Override
    public void gotoLastDate() {
        final TOC currentCell = this.md.getCurrentCell();
        this.md.drawNewDate(currentCell.gridCol, currentCell.gridRow, this.lastFilteredDate);
    }
    
    @Override
    public int getFirstYear() {
        return this.md.getCurrentCell().scenes[0].date / 10000;
    }
    
    @Override
    public int getLastYear() {
        final TOC currentCell = this.md.getCurrentCell();
        return currentCell.scenes[currentCell.numImg - 1].date / 10000;
    }
}
