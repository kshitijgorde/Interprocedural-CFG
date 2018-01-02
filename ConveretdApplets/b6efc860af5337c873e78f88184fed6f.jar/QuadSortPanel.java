import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class QuadSortPanel extends SortPanel
{
    private static final int MAKE_ARRAY_BUTTON = 0;
    private static final int BUBBLE_BUTTON = 1;
    private static final int INSERTION_BUTTON = 2;
    private static final int SELECTION_BUTTON = 3;
    private String[] showHelp;
    private String[] tryHelp;
    
    public QuadSortPanel(final String[] names, final int first, final Color backgroundColor) {
        this.showHelp = new String[] { "Generates an array with a random set of values.", "Sorts the array using the bubble sort algorithm.", "Sorts the array using the insertion sort algorithm.", "Sorts the array using the selection sort algorithm." };
        this.tryHelp = new String[] { "Generates an array with a random set of values.", "You will be asked to click the pair of bars to swap at each step to sort the array using the bubble sort algorithm.", "You will be asked to click the bar to be inserted and the location at which it should be inserted at each step of the insertion sort algorithm.", "You will be asked to click the pair of bars to swap at each step to sort the array using the selection sort algorithm." };
        super.init(names, this.showHelp, this.tryHelp, first, backgroundColor);
    }
    
    private void bubbleSort() {
        int i;
        for (i = 1; i < 30; ++i) {
            for (int j = 30; j > i; --j) {
                if (super.numericArray[j] < super.numericArray[j - 1]) {
                    this.swap(j, j - 1);
                }
            }
            this.markSorted(i);
        }
        this.markSorted(i);
    }
    
    public void buttonClicked(final int buttonNumber) {
        Screen.interaction.displayMessage("");
        if (buttonNumber == 0) {
            this.makeArray();
        }
        else if (buttonNumber == 1) {
            this.bubbleSort();
        }
        else if (buttonNumber == 2) {
            this.insertionSort();
        }
        else if (buttonNumber == 3) {
            this.selectionSort();
        }
        Screen.canvas.repaint();
    }
    
    private void insertionSort() {
        this.markSorted(1);
        int i;
        for (i = 2; i <= 30; ++i) {
            int j = i - 1;
            while (super.numericArray[i] < super.numericArray[j] && j-- > 1) {}
            if (i > j + 1) {
                super.swapableArray.startRotate(j + 1, i);
                super.swapableList.startRotate(j + 1, i);
                Screen.canvas.awaitMovingCompletion();
                super.swapableList.finishRotate(j + 1, i);
                super.swapableArray.finishRotate(j + 1, i);
            }
            this.markSorted(i);
        }
        this.markSorted(i - 1);
    }
    
    private void selectionSort() {
        int i;
        for (i = 1; i < 30; ++i) {
            int minIndex = i;
            for (int j = i + 1; j <= 30; ++j) {
                if (super.numericArray[j] < super.numericArray[minIndex]) {
                    minIndex = j;
                }
            }
            this.swap(i, minIndex);
            this.markSorted(i);
        }
        this.markSorted(i);
    }
}
