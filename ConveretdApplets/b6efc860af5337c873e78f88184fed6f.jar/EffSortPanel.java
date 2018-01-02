import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class EffSortPanel extends SortPanel
{
    private static final Position copyPosition;
    private static final int MAKE_ARRAY_BUTTON = 0;
    private static final int MERGE_BUTTON = 1;
    private static final int QUICK_BUTTON = 2;
    private static final int SHELL_BUTTON = 3;
    private int[] copy;
    private SwapableArray swapableCopy;
    private String[] showHelp;
    private String[] tryHelp;
    
    static {
        copyPosition = new Position(18, 100);
    }
    
    public EffSortPanel(final String[] names, final int first, final Color backgroundColor) {
        this.copy = new int[31];
        this.swapableCopy = new SwapableArray(EffSortPanel.copyPosition, 30);
        this.showHelp = new String[] { "Generates an array with a random set of values.", "Sorts the array using the merge sort algorithm.", "Sorts the array using the quick sort algorithm." };
        this.tryHelp = new String[] { "Generates an array with a random set of values.", "You will be asked to click the bar to move at each step to sort the array using the merge sort algorithm.", "You will be asked to click the pair of bars to swap at each step to sort the array using the quick sort algorithm." };
        super.init(names, this.showHelp, this.tryHelp, first, backgroundColor);
        this.swapableCopy.toggleHeight();
        Screen.canvas.add(this.swapableCopy);
    }
    
    private void ShellSort(final int first, final int last) {
        for (int distance = last / 2; distance > 0; distance /= 2) {
            for (int current = first + distance; current <= last; ++current) {
                for (int position = current; position >= first + distance && super.numericArray[position] < super.numericArray[position - distance]; position -= distance) {
                    this.swap(position, position - distance);
                }
            }
        }
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        this.swapableCopy.init();
        if (buttonNumber == 0) {
            this.makeArray();
        }
        else if (buttonNumber == 1) {
            super.swapableArray.toggleHeight();
            for (int index = 0; index < 30; ++index) {
                this.copy[index] = 0;
            }
            this.swapableCopy.setValues(this.copy, 30);
            this.swapableCopy.show();
            this.mergeSort(1, 30, 1);
            this.swapableCopy.hide();
            super.swapableArray.toggleHeight();
        }
        else if (buttonNumber == 2) {
            this.quickSort(1, 30);
        }
        else if (buttonNumber == 3) {
            this.ShellSort(1, 30);
        }
        Screen.canvas.repaint();
    }
    
    private void doCopy(final int from, final int to) {
        super.swapableArray.doCopy(from, to, this.swapableCopy);
        super.swapableList.nullify(from);
    }
    
    private void merge(final int first, final int middle, final int last) {
        int index = first;
        int left = first;
        int right = middle + 1;
        while (left <= middle) {
            if (right > last) {
                break;
            }
            if (super.numericArray[left] < super.numericArray[right]) {
                this.doCopy(left++, index++);
            }
            else {
                this.doCopy(right++, index++);
            }
        }
        while (left <= middle) {
            this.doCopy(left++, index++);
        }
        while (right <= last) {
            this.doCopy(right++, index++);
        }
    }
    
    private void mergeSort(final int first, final int last, final int level) {
        if (first < last) {
            final int middle = (first + last) / 2;
            this.mergeSort(first, middle, level + 1);
            this.mergeSort(middle + 1, last, level + 1);
            this.merge(first, middle, last);
            final Position[] positions = { SortPanel.ARRAY_POSITION };
            this.swapableCopy.moveTo(positions);
            Screen.canvas.awaitMovingCompletion();
            for (int index = first; index <= last; ++index) {
                super.numericArray[index] = this.copy[index];
                this.copy[index] = 0;
            }
            this.swapableCopy.reposition(EffSortPanel.copyPosition);
            super.swapableList.setValues(super.numericArray, 30);
            for (int index2 = first; index2 <= last; ++index2) {
                if (level == 1) {
                    this.markSorted(index2);
                }
            }
            this.swapableCopy.makeLine(first, last, level);
        }
    }
    
    private void quickSort(final int first, final int last) {
        super.swapableArray.showArrows();
        if (first == last) {
            this.markSorted(first);
        }
        else if (first < last) {
            final int pivot = this.split(first, last);
            this.markSorted(pivot);
            this.quickSort(first, pivot - 1);
            this.quickSort(pivot + 1, last);
        }
        super.swapableArray.hideArrows();
    }
    
    private int split(final int first, final int last) {
        final int key = super.numericArray[first];
        super.swapableArray.setBar(first, last);
        int left = first + 1;
        int right = last;
        do {
            super.swapableArray.positionArrows(left, right);
            while (left <= right) {
                if (key < super.numericArray[left]) {
                    break;
                }
                ++left;
            }
            while (left <= right && key < super.numericArray[right]) {
                --right;
            }
            super.swapableArray.moveArrows(left, right);
            if (left < right) {
                this.swap(left++, right--);
            }
        } while (left <= right);
        this.swap(first, right);
        return right;
    }
}
