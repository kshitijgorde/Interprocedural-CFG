import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Evaluator
{
    public Vector expressions;
    public Vector variable_names;
    public Vector initial_variable_values;
    public int independent_variables_count;
    public double[] variable_values;
    public double[] last_valid_variable_values;
    public int max_expression_list_size;
    public double[] evaluation_stack;
    public static final int max_arguments_count = 3;
    public double seconds_since_1970;
    
    public Evaluator() {
        this.seconds_since_1970 = System.currentTimeMillis() / 1000.0;
        this.clearAllVariables();
    }
    
    public void clearAllVariables() {
        this.expressions = null;
        this.variable_names = null;
        this.initial_variable_values = null;
        this.independent_variables_count = 0;
        this.variable_values = null;
        this.last_valid_variable_values = null;
        this.max_expression_list_size = 0;
        this.evaluation_stack = null;
    }
    
    public boolean addIndependentVariable(final String name, final double initial_value) {
        if (null == this.variable_names) {
            this.variable_names = new Vector();
        }
        if (null == this.initial_variable_values) {
            this.initial_variable_values = new Vector();
        }
        if (this.independent_variables_count < this.variable_names.size()) {
            return false;
        }
        if (this.getVariableIndex(name) >= 0) {
            return false;
        }
        this.variable_names.addElement(name);
        this.initial_variable_values.addElement(new Double(initial_value));
        this.independent_variables_count = this.variable_names.size();
        return true;
    }
    
    public boolean addDependentVariable(final String name, final double initial_value, final int expression_index) {
        if (null == this.variable_names) {
            this.variable_names = new Vector();
        }
        if (null == this.initial_variable_values) {
            this.initial_variable_values = new Vector();
        }
        int index = this.getVariableIndex(name);
        if (index >= 0) {
            if (!this.isVariableIndependent(index)) {
                return false;
            }
            if (expression_index < 0) {
                return false;
            }
        }
        if (index < 0) {
            this.variable_names.addElement(name);
            this.initial_variable_values.addElement(new Double(initial_value));
            index = this.variable_names.size() - 1;
        }
        if (expression_index >= 0) {
            this.getExpression(expression_index).setVariable(index);
        }
        return true;
    }
    
    public void prepareVariables() {
        this.variable_values = new double[this.variable_names.size()];
        this.last_valid_variable_values = new double[this.variable_names.size()];
        this.setInitialVariableValues();
        this.confirmVariableValues();
    }
    
    public void setInitialVariableValues() {
        for (int index = 0; index < this.initial_variable_values.size(); ++index) {
            this.variable_values[index] = this.initial_variable_values.elementAt(index);
        }
    }
    
    public void resetVariableValues() {
        for (int index = 0; index < this.variable_names.size(); ++index) {
            this.variable_values[index] = this.last_valid_variable_values[index];
        }
    }
    
    public void confirmVariableValues() {
        for (int index = 0; index < this.variable_names.size(); ++index) {
            this.last_valid_variable_values[index] = this.variable_values[index];
        }
    }
    
    public void setVariableValue(final int index, final double value) {
        if (null != this.variable_values) {
            this.variable_values[index] = value;
        }
        else {
            this.initial_variable_values.setElementAt(new Double(value), index);
        }
    }
    
    public double getVariableValue(final int index) {
        if (null != this.variable_values) {
            return this.variable_values[index];
        }
        return this.initial_variable_values.elementAt(index);
    }
    
    public int getVariableIndex(final String name) {
        int found_index = -1;
        for (int index = 0; index < this.variable_names.size(); ++index) {
            if (name.equals(this.variable_names.elementAt(index))) {
                found_index = index;
                break;
            }
        }
        return found_index;
    }
    
    public boolean isVariableIndependent(final int index) {
        return index >= 0 && index < this.independent_variables_count;
    }
    
    public int addExpression(final Expression new_expression) {
        if (null == this.expressions) {
            this.expressions = new Vector();
        }
        this.expressions.addElement(new_expression);
        if (new_expression.list_size > this.max_expression_list_size) {
            this.max_expression_list_size = new_expression.list_size;
            this.evaluation_stack = new double[this.max_expression_list_size + 3];
        }
        return this.expressions.size() - 1;
    }
    
    public Expression getExpression(final int index) {
        return this.expressions.elementAt(index);
    }
    
    public double getExpressionValue(final int index) {
        return this.expressions.elementAt(index).current_value;
    }
    
    public void setExpressionCoordinate(final int index, final Graphics3D graphics, final int point_index, final int coordinate_index) {
        this.getExpression(index).setCoordinate(graphics, point_index, coordinate_index);
    }
    
    public boolean evaluate() {
        boolean valid = true;
        if (null == this.expressions) {
            this.expressions = new Vector();
        }
        this.confirmVariableValues();
        for (int expression_index = 0; expression_index < this.expressions.size(); ++expression_index) {
            Expression expression = this.expressions.elementAt(expression_index);
            expression.confirmValue();
            if (!expression.evaluate()) {
                for (int index = 0; index <= expression_index; ++index) {
                    expression = this.expressions.elementAt(index);
                    expression.resetValue();
                }
                this.resetVariableValues();
                valid = false;
                break;
            }
        }
        return valid;
    }
}
