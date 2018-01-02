// 
// Decompiled by Procyon v0.5.30
// 

public final class LogicUnit
{
    Currency myCurrency;
    String current_currency;
    double ma;
    double mb;
    boolean isEntered;
    boolean fromEnter;
    boolean first_operation;
    boolean enteringDigit;
    boolean isERules;
    double factor;
    int multiplicator;
    int lastOperation;
    static final int ADD = 1;
    static final int SUBSTRACT = 2;
    static final int DIVIDE = 3;
    static final int MULTIPLY = 4;
    int nbOfDigits;
    int digit;
    DigitList digitList;
    DigitList digitListEuro;
    boolean zeroDigit;
    String lastOperationString;
    Memory myMemory;
    
    public LogicUnit() {
        this.myCurrency = new Currency();
        this.current_currency = "EUR";
        this.isEntered = true;
        this.fromEnter = false;
        this.first_operation = true;
        this.enteringDigit = true;
        this.isERules = false;
        this.factor = 1.0;
        this.multiplicator = 1;
        this.nbOfDigits = 10;
        this.digit = 1;
        this.zeroDigit = false;
        this.lastOperationString = "start";
        this.myMemory = new Memory();
    }
    
    public void OnDigit(final char c) {
        if (this.digit > this.nbOfDigits) {
            return;
        }
        ++this.digit;
        if (!Character.isDigit(c)) {
            return;
        }
        final char c2 = (char)(c - '0');
        if (this.enteringDigit) {
            if (this.factor == 1.0) {
                this.ma = this.ma * this.multiplicator + c2;
                this.multiplicator = 10;
            }
            else {
                this.ma += c2 * this.factor;
                this.factor /= 10.0;
            }
            if (this.ma == 0.0) {
                this.zeroDigit = true;
            }
            this.digitList.setText(String.valueOf(this.ma));
            this.setEuro();
            return;
        }
        this.enteringDigit = true;
        this.ma = 0.0;
        this.OnDigit(c);
    }
    
    public void OnSign() {
        this.ma *= -1.0;
        final String number = this.digitList.number;
        if (number.startsWith("-")) {
            this.digitList.setText(number.substring(1));
        }
        else {
            this.digitList.setText("-" + number);
        }
        this.setEuro();
    }
    
    public void OnAdd() {
        this.lastOperationString = "+";
        this.fromEnter = false;
        this.doOperation(1);
    }
    
    public void OnMultiply() {
        this.lastOperationString = "*";
        this.fromEnter = false;
        this.doOperation(4);
    }
    
    public void OnDivide() {
        this.lastOperationString = "/";
        this.fromEnter = false;
        this.doOperation(3);
    }
    
    public void OnSubstract() {
        this.lastOperationString = "-";
        this.fromEnter = false;
        this.doOperation(2);
    }
    
    public void OnDecimal() {
        if (this.factor == 1.0) {
            this.factor /= 10.0;
        }
    }
    
    private void display(final double n) {
        this.digitList.setText(String.valueOf(n));
        this.setEuro();
    }
    
    private void display(final String text) {
        this.digitList.setText(text);
        this.setEuro();
    }
    
    private void doOperation(int lastOperation) {
        if (this.first_operation) {
            this.first_operation = false;
            this.digitList.setText(String.valueOf(this.ma));
            this.setEuro();
            this.mb = this.ma;
            this.ma = 0.0;
        }
        else {
            if (!this.fromEnter && this.isEntered) {
                this.ma = 0.0;
                this.isEntered = false;
            }
            switch ((lastOperation != this.lastOperation) ? this.lastOperation : lastOperation) {
                case 1: {
                    this.mb += this.ma;
                    this.digitList.setText(String.valueOf(this.mb));
                    this.setEuro();
                    break;
                }
                case 2: {
                    this.mb -= this.ma;
                    this.digitList.setText(String.valueOf(this.mb));
                    this.setEuro();
                    break;
                }
                case 3: {
                    if (this.ma == 0.0 && !this.zeroDigit) {
                        this.ma = 1.0;
                    }
                    this.mb /= this.ma;
                    this.digitList.setText(String.valueOf(this.mb));
                    this.setEuro();
                    break;
                }
                case 4: {
                    if (this.ma == 0.0 && !this.zeroDigit) {
                        this.ma = 1.0;
                    }
                    this.mb *= this.ma;
                    this.digitList.setText(String.valueOf(this.mb));
                    this.setEuro();
                    break;
                }
            }
        }
        this.lastOperation = lastOperation;
        lastOperation = 0;
        this.enteringDigit = false;
        this.factor = 1.0;
        this.multiplicator = 1;
        this.digit = 1;
        this.zeroDigit = false;
    }
    
    public void OnEnter() {
        this.fromEnter = true;
        this.isEntered = true;
        this.doOperation(this.lastOperation);
    }
    
    private void setEuro() {
        this.digitListEuro.setText(String.valueOf(this.myCurrency.translate(this.current_currency, new Double(this.digitList.number), "EUR")));
    }
    
    public void setCurrentCurrency(final String current_currency) {
        this.current_currency = current_currency;
        if (this.isERules) {
            this.OnApplyEuroRules(true);
            return;
        }
        this.setEuro();
    }
    
    public void Reset() {
        this.factor = 1.0;
        this.multiplicator = 1;
        this.lastOperation = 0;
        this.first_operation = true;
        this.digit = 1;
        this.ma = 0.0;
        this.mb = 0.0;
        this.isEntered = true;
        this.fromEnter = false;
        this.enteringDigit = true;
        this.lastOperationString = "start";
        this.current_currency = "EUR";
        this.digitList.setText(String.valueOf(0.0));
        this.setEuro();
    }
    
    public void setDisplay(final DigitList digitList) {
        this.digitList = digitList;
    }
    
    public void setEuroDisplay(final DigitList digitListEuro) {
        this.digitListEuro = digitListEuro;
    }
    
    public String getCurrentCurrency() {
        return this.current_currency;
    }
    
    public void OnMemory() {
        final double doubleValue = new Double(this.digitList.number);
        final Memory myMemory = this.myMemory;
        final String current_currency = this.current_currency;
        final double translate = this.myCurrency.translate(this.current_currency, doubleValue, "EUR");
        final String lastOperationString = this.lastOperationString;
        myMemory.value = doubleValue;
        myMemory.currency = current_currency;
        myMemory.valueList.addElement(new element(current_currency, doubleValue, translate, lastOperationString));
        this.enteringDigit = false;
        this.digit = 1;
        this.digitList.memoryLabel.setText("mem");
    }
    
    public void OnMemoryRecall() {
        if (this.digitList.memoryLabel.getText().equals("mem") && true) {
            this.digit = 1;
            this.ma = this.myMemory.value;
            this.current_currency = this.myMemory.currency;
            this.digitList.setText(String.valueOf(this.ma));
            this.setEuro();
        }
    }
    
    public void OnMemoryClear() {
        this.myMemory.value = 0.0;
        this.digitList.memoryLabel.setText("   ");
    }
    
    public Memory getMemory() {
        return this.myMemory;
    }
    
    public void OnCancel() {
        this.ma = 0.0;
        this.digitList.setText(String.valueOf(this.ma));
        this.setEuro();
        this.enteringDigit = true;
        this.factor = 1.0;
        this.multiplicator = 1;
        this.digit = 1;
    }
    
    public void OnPct() {
        if (!this.first_operation) {
            this.ma *= this.mb / 100.0;
            this.digitList.setText(String.valueOf(this.ma));
            this.setEuro();
        }
    }
    
    public void OnChangeRoundValue(final int defaultRoundedValue) {
        DoubleManipulator.setDefaultRoundedValue(defaultRoundedValue);
        this.setEuro();
    }
    
    public void OnApplyEuroRules(final boolean b) {
        if (b) {
            this.isERules = true;
            DoubleManipulator.setDefaultRoundedValue(Currency.getIntRounded(this.current_currency));
            this.setEuro();
            return;
        }
        this.isERules = false;
    }
    
    public void OnChangeRoundPolicy(final int defaultPolicyValue) {
        DoubleManipulator.setDefaultPolicyValue(defaultPolicyValue);
        this.setEuro();
    }
    
    public void OnChangeDirection(final int direction) {
        this.myCurrency.setDirection(direction);
        this.setEuro();
    }
}
