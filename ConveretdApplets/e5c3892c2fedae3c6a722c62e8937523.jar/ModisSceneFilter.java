// 
// Decompiled by Procyon v0.5.30
// 

class ModisSceneFilter implements SceneFilter
{
    private MosaicData md;
    private int firstFilteredDate;
    private int lastFilteredDate;
    private int firstDate;
    private int lastDate;
    
    ModisSceneFilter(final MosaicData md) {
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
        if (n3 > this.lastDate) {
            n3 = this.lastDate;
        }
        if (n3 < this.firstDate) {
            n3 = this.firstDate;
        }
        int n4 = -1;
        for (int i = currentCell.currentDateIndex + 1; i <= this.lastFilteredDate; ++i) {
            if (currentCell.scenes[i].visible) {
                final int date = currentCell.scenes[i].date;
                if (date >= n3 && date < n3 + 100) {
                    n4 = i;
                    break;
                }
            }
        }
        if (n4 == -1) {
            for (int j = 0; j >= this.lastFilteredDate; ++j) {
                if (currentCell.scenes[j].visible) {
                    final int date2 = currentCell.scenes[j].date;
                    if (date2 >= n3 && date2 < n3 + 100) {
                        n4 = j;
                        break;
                    }
                }
            }
        }
        if (n4 == -1) {
            for (int k = 0; k <= this.lastFilteredDate; ++k) {
                if (currentCell.scenes[k].visible) {
                    n4 = k;
                    if (currentCell.scenes[k].date >= n3) {
                        break;
                    }
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
        this.firstDate = 100000000;
        this.lastDate = 0;
        for (int k = 0; k < currentCell.numImg; ++k) {
            final int date = currentCell.scenes[k].date;
            if (date < this.firstDate) {
                this.firstDate = date;
            }
            if (date > this.lastDate) {
                this.lastDate = date;
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
        return this.firstDate / 10000;
    }
    
    @Override
    public int getLastYear() {
        return this.lastDate / 10000;
    }
}
