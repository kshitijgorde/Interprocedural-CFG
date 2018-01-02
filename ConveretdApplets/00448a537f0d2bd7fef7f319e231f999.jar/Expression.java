// 
// Decompiled by Procyon v0.5.30
// 

class Expression
{
    public Evaluator evaluator;
    public double current_value;
    public double last_valid_value;
    public Graphics3D graphics;
    public int point_index;
    public int coordinate_index;
    public int variable_index;
    public int list_size;
    public int[] tokens_list;
    public double[] values_list;
    public boolean is_atomic_independent_variable;
    public int independent_variable_index;
    
    public Expression(final Evaluator new_evaluator, final int new_list_size, final int[] new_tokens_list, final double[] new_values_list) {
        this.current_value = 0.0;
        this.last_valid_value = 0.0;
        this.evaluator = new_evaluator;
        this.graphics = null;
        this.point_index = -1;
        this.coordinate_index = -1;
        this.variable_index = -1;
        this.list_size = new_list_size;
        this.tokens_list = new_tokens_list;
        this.values_list = new_values_list;
        if (2 == this.list_size && 21 == this.tokens_list[0] && this.evaluator.isVariableIndependent((int)this.values_list[0])) {
            this.is_atomic_independent_variable = true;
            this.independent_variable_index = (int)this.values_list[0];
        }
        else {
            this.is_atomic_independent_variable = false;
            this.independent_variable_index = -1;
        }
    }
    
    public void setCoordinate(final Graphics3D new_graphics, final int new_point_index, final int new_coordinate_index) {
        this.graphics = new_graphics;
        this.point_index = new_point_index;
        this.coordinate_index = new_coordinate_index;
        this.variable_index = -1;
    }
    
    public void setVariable(final int new_variable_index) {
        this.graphics = null;
        this.point_index = -1;
        this.coordinate_index = -1;
        this.variable_index = new_variable_index;
    }
    
    public boolean setValue() {
        if (this.variable_index >= 0) {
            this.evaluator.setVariableValue(this.variable_index, this.current_value);
            return true;
        }
        return null == this.graphics || this.point_index < 0 || this.coordinate_index < 0 || this.graphics.setCoordinateValue(this.point_index, this.coordinate_index, this.current_value);
    }
    
    public void setIndependentVariable(final double new_value) {
        if (this.is_atomic_independent_variable) {
            this.current_value = new_value;
            this.evaluator.setVariableValue(this.independent_variable_index, this.current_value);
        }
    }
    
    public double getCurrentValue() {
        return this.current_value;
    }
    
    public boolean isAtomicIndependentVariable() {
        return this.is_atomic_independent_variable;
    }
    
    public boolean evaluate() {
        int stack_size = 3;
        final int arguments_count = 0;
        int operators_count = 0;
        final int function_index = 0;
        if (null == this.evaluator) {
            return false;
        }
        final double[] stack = this.evaluator.evaluation_stack;
        if (null == stack) {
            return false;
        }
        for (int index = 0; index < this.list_size; ++index) {
            final int token = this.tokens_list[index];
            if (24 == token) {
                break;
            }
            try {
                switch (token) {
                    case 22: {
                        stack[stack_size++] = this.values_list[index];
                        break;
                    }
                    case 27: {
                        break;
                    }
                    case 28: {
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = -first_argument;
                        break;
                    }
                    case 21: {
                        stack[stack_size++] = this.evaluator.getVariableValue((int)this.values_list[index]);
                        break;
                    }
                    case 7: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = first_argument + second_argument;
                        break;
                    }
                    case 8: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = first_argument - second_argument;
                        break;
                    }
                    case 9: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = first_argument * second_argument;
                        break;
                    }
                    case 10: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        if (0.0 == second_argument) {
                            return false;
                        }
                        stack[stack_size++] = first_argument / second_argument;
                        break;
                    }
                    case 11: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = Math.pow(first_argument, second_argument);
                        break;
                    }
                    case 30: {
                        stack_size = this.evaluate_function(stack, stack_size, index);
                        if (stack_size < 0) {
                            return false;
                        }
                        break;
                    }
                    case 34: {
                        final double first_argument = stack[--stack_size];
                        if (first_argument == 1.0) {
                            stack[stack_size++] = 0.0;
                            break;
                        }
                        if (first_argument == 0.0) {
                            stack[stack_size++] = 1.0;
                            break;
                        }
                        return false;
                    }
                    case 32: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        if (1.0 == first_argument) {
                            stack[stack_size++] = second_argument;
                            break;
                        }
                        if (1.0 == second_argument) {
                            stack[stack_size++] = first_argument;
                            break;
                        }
                        if (0.0 == first_argument) {
                            stack[stack_size++] = 0.0;
                            break;
                        }
                        if (0.0 == second_argument) {
                            stack[stack_size++] = 0.0;
                            break;
                        }
                        return false;
                    }
                    case 33: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        if (0.0 == first_argument) {
                            stack[stack_size++] = second_argument;
                            break;
                        }
                        if (0.0 == second_argument) {
                            stack[stack_size++] = first_argument;
                            break;
                        }
                        if (1.0 == first_argument) {
                            stack[stack_size++] = 1.0;
                            break;
                        }
                        if (1.0 == second_argument) {
                            stack[stack_size++] = 1.0;
                            break;
                        }
                        return false;
                    }
                    case 12: {
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = this.gamma(first_argument + 1.0);
                        break;
                    }
                    case 13: {
                        final double first_argument = stack[--stack_size];
                        if (first_argument <= -2.0) {
                            return false;
                        }
                        stack[stack_size++] = this.factorial2(first_argument);
                        break;
                    }
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19: {
                        stack[stack_size++] = token + 0.5;
                        break;
                    }
                    case 31: {
                        operators_count = (int)this.values_list[index];
                        if (stack_size < 2 * operators_count + 1) {
                            return false;
                        }
                        double result = 1.0;
                        for (int argument_index = 0; argument_index < operators_count; ++argument_index) {
                            final int operator_token = (int)stack[stack_size - operators_count + argument_index];
                            final double first_argument = stack[stack_size - operators_count - argument_index - 2];
                            final double second_argument = stack[stack_size - operators_count - argument_index - 1];
                            switch (operator_token) {
                                case 14: {
                                    if (first_argument != second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                case 15: {
                                    if (first_argument == second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                case 16: {
                                    if (first_argument >= second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                case 18: {
                                    if (first_argument > second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                case 17: {
                                    if (first_argument <= second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                case 19: {
                                    if (first_argument < second_argument) {
                                        result = 0.0;
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    return false;
                                }
                            }
                            if (result < 0.5) {
                                break;
                            }
                        }
                        stack_size = stack_size - 2 * operators_count - 1;
                        stack[stack_size++] = result;
                        break;
                    }
                    case 35: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = second_argument;
                        break;
                    }
                    case 36: {
                        final double second_argument = stack[--stack_size];
                        final double first_argument = stack[--stack_size];
                        stack[stack_size++] = second_argument;
                        this.evaluator.setVariableValue((int)this.values_list[index], second_argument);
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
            catch (ArithmeticException e) {
                return false;
            }
            if (stack_size <= 3) {
                return false;
            }
        }
        if (stack_size != 4) {
            return false;
        }
        this.current_value = stack[--stack_size];
        return !Double.isNaN(this.current_value) && !Double.isInfinite(this.current_value) && this.setValue();
    }
    
    public int evaluate_function(final double[] stack, int stack_size, final int index) {
        final int arguments_count = (int)this.values_list[index] / 1024;
        final int function_index = (int)this.values_list[index] & 0x3FF;
        if (stack_size < arguments_count) {
            return -1;
        }
        switch (function_index) {
            case 1: {
                double result = 0.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    result += stack[--stack_size];
                }
                stack[stack_size++] = result;
                break;
            }
            case 2: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = first_argument - second_argument;
                break;
            }
            case 3: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = -first_argument;
                break;
            }
            case 4: {
                double result = 1.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    result *= stack[--stack_size];
                }
                stack[stack_size++] = result;
                break;
            }
            case 5: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (0.0 == second_argument) {
                    return -1;
                }
                stack[stack_size++] = first_argument / second_argument;
                break;
            }
            case 6: {
                double result = 1.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    result = Math.pow(stack[--stack_size], result);
                }
                stack[stack_size++] = result;
                break;
            }
            case 7: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.abs(first_argument);
                break;
            }
            case 8: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                double result;
                if (first_argument > 0.0) {
                    result = 1.0;
                }
                else if (first_argument < 0.0) {
                    result = -1.0;
                }
                else {
                    result = 0.0;
                }
                stack[stack_size++] = result;
                break;
            }
            case 9: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                double result = Math.floor(first_argument + 0.5);
                if (result == first_argument + 0.5 && ((result > 0.0 && 0x1 == (0x1 & (int)result)) || (result < 0.0 && 0x1 == (0x1 & (int)(-result))))) {
                    --result;
                }
                stack[stack_size++] = result;
                break;
            }
            case 10: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                double result;
                if (first_argument >= 0.0) {
                    result = Math.floor(first_argument);
                }
                else {
                    result = Math.ceil(first_argument);
                }
                stack[stack_size++] = result;
                break;
            }
            case 11: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                double result;
                if (first_argument >= 0.0) {
                    result = first_argument - Math.floor(first_argument);
                }
                else {
                    result = first_argument - Math.ceil(first_argument);
                }
                stack[stack_size++] = result;
                break;
            }
            case 12: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.floor(first_argument);
                break;
            }
            case 13: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.ceil(first_argument);
                break;
            }
            case 14: {
                double second_argument;
                double first_argument;
                if (1 == arguments_count) {
                    second_argument = 1.0E-10;
                    first_argument = stack[--stack_size];
                }
                else {
                    if (2 != arguments_count) {
                        return -1;
                    }
                    second_argument = stack[--stack_size];
                    first_argument = stack[--stack_size];
                    if (second_argument < 0.0) {
                        return -1;
                    }
                }
                double result;
                if (Math.abs(first_argument) < second_argument) {
                    result = 0.0;
                }
                else {
                    result = first_argument;
                }
                stack[stack_size++] = result;
                break;
            }
            case 15: {
                double result = Double.MIN_VALUE;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    if (stack[--stack_size] > result) {
                        result = stack[stack_size];
                    }
                }
                stack[stack_size++] = result;
                break;
            }
            case 16: {
                double result = Double.MAX_VALUE;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    if (stack[--stack_size] < result) {
                        result = stack[stack_size];
                    }
                }
                stack[stack_size++] = result;
                break;
            }
            case 17:
            case 19: {
                if (arguments_count != 1) {
                    return -1;
                }
                break;
            }
            case 18: {
                if (arguments_count != 1) {
                    return -1;
                }
                stack[stack_size - 1] = 0.0;
                break;
            }
            case 20: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                double result;
                if (first_argument > 0.0) {
                    result = 0.0;
                }
                else {
                    if (first_argument >= 0.0) {
                        return -1;
                    }
                    result = 3.141592653589793;
                }
                stack[stack_size++] = result;
                break;
            }
            case 21: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (0.0 == second_argument) {
                    return -1;
                }
                stack[stack_size++] = first_argument - second_argument * Math.floor(first_argument / second_argument);
                break;
            }
            case 22: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (0.0 == second_argument) {
                    return -1;
                }
                stack[stack_size++] = Math.floor(first_argument / second_argument);
                break;
            }
            case 23: {
                if (arguments_count != 0) {
                    return -1;
                }
                final double result = Math.random();
                stack[stack_size++] = result;
                break;
            }
            case 24: {
                if (arguments_count > 1) {
                    return -1;
                }
                if (arguments_count == 1) {
                    final double first_argument = stack[--stack_size];
                    break;
                }
                break;
            }
            case 25: {
                if (2 == arguments_count) {
                    final double second_argument = stack[--stack_size];
                    final double first_argument = stack[--stack_size];
                    if (0.0 >= second_argument || 0.0 > first_argument || 1.0 == first_argument) {
                        return -1;
                    }
                    if (0.0 == first_argument) {
                        stack[stack_size++] = 0.0;
                    }
                    final double result = Math.log(second_argument) / Math.log(first_argument);
                    stack[stack_size++] = result;
                    break;
                }
                else {
                    if (1 != arguments_count) {
                        return -1;
                    }
                    final double second_argument = stack[--stack_size];
                    if (0.0 >= second_argument) {
                        return -1;
                    }
                    stack[stack_size++] = Math.log(second_argument);
                    break;
                }
                break;
            }
            case 26: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.exp(first_argument);
                break;
            }
            case 27: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (0.0 > first_argument) {
                    return -1;
                }
                stack[stack_size++] = Math.sqrt(first_argument);
                break;
            }
            case 28: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.sin(first_argument);
                break;
            }
            case 29: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.cos(first_argument);
                break;
            }
            case 30: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.tan(first_argument);
                break;
            }
            case 31: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.sin(first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = 1.0 / result;
                break;
            }
            case 32: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.cos(first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = 1.0 / result;
                break;
            }
            case 33: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.tan(first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = 1.0 / result;
                break;
            }
            case 34: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument > 1.0 || first_argument < -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.asin(first_argument);
                break;
            }
            case 35: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument > 1.0 || first_argument < -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.acos(first_argument);
                break;
            }
            case 36: {
                if (1 == arguments_count) {
                    final double first_argument = stack[--stack_size];
                    stack[stack_size++] = Math.atan(first_argument);
                    break;
                }
                if (2 == arguments_count) {
                    final double second_argument = stack[--stack_size];
                    final double first_argument = stack[--stack_size];
                    stack[stack_size++] = Math.atan2(second_argument, first_argument);
                    break;
                }
                return -1;
            }
            case 37: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (0.0 == first_argument) {
                    return -1;
                }
                final double result = 1.0 / first_argument;
                if (result > 1.0 || result < -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.asin(result);
                break;
            }
            case 38: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (0.0 == first_argument) {
                    return -1;
                }
                final double result = 1.0 / first_argument;
                if (result > 1.0 || result < -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.acos(result);
                break;
            }
            case 39: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (0.0 == first_argument) {
                    return -1;
                }
                final double result = 1.0 / first_argument;
                stack[stack_size++] = Math.atan(result);
                break;
            }
            case 40: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = (Math.exp(first_argument) - Math.exp(-first_argument)) / 2.0;
                break;
            }
            case 41: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = (Math.exp(first_argument) + Math.exp(-first_argument)) / 2.0;
                break;
            }
            case 42: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.exp(first_argument) + Math.exp(-first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = (Math.exp(first_argument) - Math.exp(-first_argument)) / result;
                break;
            }
            case 43: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.exp(first_argument) - Math.exp(-first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = 1.0 / result;
                break;
            }
            case 44: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.exp(first_argument) + Math.exp(-first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = 1.0 / result;
                break;
            }
            case 45: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.exp(first_argument) - Math.exp(-first_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = (Math.exp(first_argument) + Math.exp(-first_argument)) / result;
                break;
            }
            case 46: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = Math.log(first_argument + Math.sqrt(1.0 + first_argument * first_argument));
                break;
            }
            case 47: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = first_argument * first_argument - 1.0;
                if (0.0 > result) {
                    return -1;
                }
                stack[stack_size++] = Math.log(first_argument + Math.sqrt(result));
                break;
            }
            case 48: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (1.0 - first_argument <= 0.0 || -1.0 >= first_argument) {
                    return -1;
                }
                final double result = (1.0 + first_argument) / (1.0 - first_argument);
                if (result <= 0.0) {
                    return -1;
                }
                stack[stack_size++] = Math.log(Math.sqrt(result));
                break;
            }
            case 49: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (0.0 == first_argument) {
                    return -1;
                }
                double result;
                if (first_argument < 0.0) {
                    result = Math.log((1.0 - Math.sqrt(1.0 + first_argument * first_argument)) / first_argument);
                }
                else {
                    result = Math.log((1.0 + Math.sqrt(1.0 + first_argument * first_argument)) / first_argument);
                }
                stack[stack_size++] = result;
                break;
            }
            case 50: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = 1.0 - first_argument * first_argument;
                if (result < 0.0 || first_argument <= 0.0) {
                    return -1;
                }
                stack[stack_size++] = Math.log((1.0 + Math.sqrt(result)) / first_argument);
                break;
            }
            case 51: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (1.0 - first_argument == 0.0) {
                    return -1;
                }
                final double result = (-1.0 - first_argument) / (1.0 - first_argument);
                if (result <= 0.0) {
                    return -1;
                }
                stack[stack_size++] = Math.log(Math.sqrt(result));
                break;
            }
            case 52: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument <= -1.0) {
                    return -1;
                }
                stack[stack_size++] = this.gamma(first_argument + 1.0);
                break;
            }
            case 53: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = this.factorial2(first_argument);
                break;
            }
            case 54: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (first_argument <= -1.0 || second_argument <= -1.0 || first_argument - second_argument <= -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.exp(this.logGamma(first_argument + 1.0) - this.logGamma(second_argument + 1.0) - this.logGamma(first_argument - second_argument + 1.0));
                break;
            }
            case 55: {
                if (arguments_count < 0) {
                    return -1;
                }
                double result = 0.0;
                double second_argument = 0.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    final double first_argument = stack[--stack_size];
                    if (first_argument <= -1.0) {
                        return -1;
                    }
                    second_argument += first_argument;
                    result += this.logGamma(first_argument + 1.0);
                }
                if (result <= -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.exp(this.logGamma(second_argument + 1.0) - result);
                break;
            }
            case 56: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (first_argument + second_argument <= -1.0 || second_argument <= -1.0) {
                    return -1;
                }
                stack[stack_size++] = Math.exp(this.logGamma(first_argument + second_argument + 1.0) - this.logGamma(second_argument + 1.0));
                break;
            }
            case 57: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = this.gamma(first_argument);
                break;
            }
            case 58: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = this.logGamma(first_argument);
                break;
            }
            case 112: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                final double result = this.gamma(first_argument + second_argument);
                if (0.0 == result) {
                    return -1;
                }
                stack[stack_size++] = this.gamma(first_argument) * this.gamma(second_argument) / result;
                break;
            }
            case 59: {
                if (arguments_count != 3) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double result = stack[--stack_size];
                double first_argument = stack[--stack_size];
                if (0.0 == second_argument || 0.0 > result) {
                    return -1;
                }
                first_argument = Math.pow(first_argument, result);
                stack[stack_size++] = first_argument - second_argument * Math.floor(first_argument / second_argument);
                break;
            }
            case 60: {
                if (arguments_count == 2) {
                    final double second_argument = stack[--stack_size];
                    final double first_argument = stack[--stack_size];
                    stack[stack_size++] = this.erf(second_argument) - this.erf(first_argument);
                    break;
                }
                if (arguments_count == 1) {
                    final double first_argument = stack[--stack_size];
                    stack[stack_size++] = this.erf(first_argument);
                    break;
                }
                return -1;
            }
            case 61: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = 1.0 - this.erf(first_argument);
                break;
            }
            case 62: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = this.erfi(first_argument);
                break;
            }
            case 63: {
                double second_argument;
                if (1 == arguments_count) {
                    second_argument = 1.0E-4;
                    final double first_argument = stack[--stack_size];
                }
                else {
                    if (2 != arguments_count) {
                        return -1;
                    }
                    second_argument = stack[--stack_size];
                    final double first_argument = stack[--stack_size];
                    if (second_argument < 0.0) {
                        return -1;
                    }
                }
                final double first_argument = stack[--stack_size];
                double result = Math.floor(first_argument * 12600.0 + 0.5);
                if (Math.abs(result - first_argument) < second_argument * 10.0) {
                    result = first_argument;
                }
                stack[stack_size++] = result;
                break;
            }
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69: {
                if (stack_size < arguments_count) {
                    return -1;
                }
                double result = 1.0;
                for (int argument_index = 0; argument_index < arguments_count - 1; ++argument_index) {
                    final double first_argument = stack[stack_size - argument_index - 2];
                    final double second_argument = stack[stack_size - argument_index - 1];
                    switch (function_index) {
                        case 64: {
                            if (first_argument != second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        case 65: {
                            if (first_argument == second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        case 66: {
                            if (first_argument >= second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        case 68: {
                            if (first_argument > second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        case 67: {
                            if (first_argument <= second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        case 69: {
                            if (first_argument < second_argument) {
                                result = 0.0;
                                break;
                            }
                            break;
                        }
                        default: {
                            return -1;
                        }
                    }
                    if (result < 0.5) {
                        break;
                    }
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 70:
            case 71:
            case 80: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = 1.0;
                break;
            }
            case 72: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument == Math.floor(first_argument + 0.5)) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 73: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.floor(first_argument + 0.5);
                if (result != first_argument || result != Math.floor(result / 2.0 + 0.5)) {
                    stack[stack_size++] = 0.0;
                    break;
                }
                stack[stack_size++] = 1.0;
                break;
            }
            case 74: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                final double result = Math.floor(first_argument + 0.5);
                if (result != first_argument || result == Math.floor(result / 2.0 + 0.5)) {
                    stack[stack_size++] = 0.0;
                    break;
                }
                stack[stack_size++] = 1.0;
                break;
            }
            case 75: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument > 0.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 76: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument < 0.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 77: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument <= 0.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 78: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument >= 0.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 79: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument == 1.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                stack[stack_size++] = 0.0;
                break;
            }
            case 81: {
                if (arguments_count != 1) {
                    return -1;
                }
                final double first_argument = stack[--stack_size];
                if (first_argument == 1.0) {
                    stack[stack_size++] = 0.0;
                    break;
                }
                if (first_argument == 0.0) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                return -1;
            }
            case 85: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                if (1.0 == first_argument) {
                    stack[stack_size++] = second_argument;
                    break;
                }
                if (0.0 == first_argument) {
                    stack[stack_size++] = 1.0;
                    break;
                }
                return -1;
            }
            case 82:
            case 83:
            case 84: {
                if (stack_size < arguments_count) {
                    return -1;
                }
                double result;
                if (82 == function_index) {
                    result = 1.0;
                }
                else {
                    result = 0.0;
                }
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    final double first_argument = stack[stack_size - argument_index - 1];
                    switch (function_index) {
                        case 82: {
                            if (0.0 == first_argument) {
                                result = 0.0;
                                break;
                            }
                            if (1.0 != first_argument) {
                                return -1;
                            }
                            break;
                        }
                        case 83: {
                            if (1.0 == first_argument) {
                                result = 1.0;
                                break;
                            }
                            if (0.0 != first_argument) {
                                return -1;
                            }
                            break;
                        }
                        case 84: {
                            if (1.0 == first_argument) {
                                result = 1.0 - result;
                                break;
                            }
                            if (0.0 != first_argument) {
                                return -1;
                            }
                            break;
                        }
                        default: {
                            return -1;
                        }
                    }
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 86: {
                if (arguments_count < 2 || arguments_count > 4) {
                    return -1;
                }
                final double first_argument = stack[stack_size - arguments_count];
                double result;
                if (1.0 == first_argument) {
                    result = stack[stack_size - arguments_count + 1];
                }
                else if (0.0 == first_argument) {
                    if (arguments_count <= 2) {
                        return -1;
                    }
                    result = stack[stack_size - arguments_count + 2];
                }
                else {
                    if (arguments_count <= 3) {
                        return -1;
                    }
                    result = stack[stack_size - arguments_count + 3];
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 87: {
                if ((arguments_count & 0x1) == 0x1) {
                    return -1;
                }
                double result = 0.0;
                int argument_index;
                for (argument_index = 0; argument_index < arguments_count; argument_index += 2) {
                    final double second_argument = stack[stack_size - arguments_count + 1 + argument_index];
                    final double first_argument = stack[stack_size - arguments_count + argument_index];
                    if (1.0 == first_argument) {
                        result = second_argument;
                        break;
                    }
                    if (0.0 != first_argument) {
                        return -1;
                    }
                }
                if (argument_index >= arguments_count) {
                    return -1;
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 88: {
                if ((arguments_count & 0x1) == 0x0) {
                    return -1;
                }
                double result = stack[stack_size - arguments_count];
                int argument_index;
                for (argument_index = 0; argument_index < arguments_count - 1; argument_index += 2) {
                    final double second_argument = stack[stack_size - arguments_count + 2 + argument_index];
                    final double first_argument = stack[stack_size - arguments_count + 1 + argument_index];
                    if (result == first_argument) {
                        result = second_argument;
                        break;
                    }
                }
                if (argument_index >= arguments_count - 1) {
                    return -1;
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105: {
                if (arguments_count != 1) {
                    return -1;
                }
                break;
            }
            case 95:
            case 96:
            case 97:
            case 99: {
                double first_argument;
                if (1 == arguments_count) {
                    first_argument = stack[--stack_size];
                }
                else {
                    if (2 != arguments_count) {
                        return -1;
                    }
                    final double second_argument = stack[--stack_size];
                    first_argument = stack[--stack_size];
                }
                stack[stack_size++] = first_argument;
                break;
            }
            case 106:
            case 107: {
                if (arguments_count != 0) {
                    return -1;
                }
                final double result = System.currentTimeMillis() / 1000.0;
                stack[stack_size++] = result - this.evaluator.seconds_since_1970;
                break;
            }
            case 108: {
                if (arguments_count != 0) {
                    return -1;
                }
                stack[stack_size++] = -2.085978496E9 + System.currentTimeMillis() / 1000.0;
                break;
            }
            case 109: {
                if (stack_size < arguments_count) {
                    return -1;
                }
                double result = 1.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    final double first_argument = stack[stack_size - argument_index - 1];
                    if (0.0 != first_argument) {
                        result = 0.0;
                        break;
                    }
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 110: {
                if (stack_size < arguments_count) {
                    return -1;
                }
                double result = 1.0;
                final double first_argument = stack[stack_size - 1];
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    final double second_argument = stack[stack_size - argument_index - 1];
                    if (second_argument != first_argument) {
                        result = 0.0;
                        break;
                    }
                }
                if (1 == arguments_count && first_argument != 0.0) {
                    result = 0.0;
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 111: {
                if (stack_size < arguments_count) {
                    return -1;
                }
                double result = 1.0;
                for (int argument_index = 0; argument_index < arguments_count; ++argument_index) {
                    final double first_argument = stack[stack_size - argument_index - 1];
                    if (first_argument < 0.0) {
                        result = 0.0;
                        break;
                    }
                }
                stack_size -= arguments_count;
                stack[stack_size++] = result;
                break;
            }
            case 113: {
                if (arguments_count != 2) {
                    return -1;
                }
                final double second_argument = stack[--stack_size];
                final double first_argument = stack[--stack_size];
                stack[stack_size++] = second_argument;
                this.evaluator.setVariableValue((int)first_argument, second_argument);
                break;
            }
            default: {
                return -1;
            }
        }
        return stack_size;
    }
    
    public void resetValue() {
        this.current_value = this.last_valid_value;
        this.setValue();
    }
    
    public void confirmValue() {
        this.last_valid_value = this.current_value;
    }
    
    public double erf(final double x) {
        final double x2 = x * x;
        if (x2 < 0.417316) {
            return x * (1.1283791670955126 + x2 * (-0.37612638903183754 + x2 * (0.11283791670955126 + x2 * (-0.026866170645131252 + x2 * (0.005223977625442188 + x2 * (-8.548327023450853E-4 + x2 * 1.2055332981789664E-4))))));
        }
        return ((x > 0.0) ? 1.0 : -1.0) - 0.5641895835477563 / x * Math.exp(-x2) * (1.0 + 1.0 / (-0.9995450369403409 + x * (-1.775762362302549 + x * (-1.131190748119284 + x * (-0.2693430703319502 + x * (0.04984841389825107 + x * (-0.003959278415228862 + x * -9.749847083947127E-5)))))));
    }
    
    public double erfi(final double x) {
        final double x2 = x * x;
        if (x2 < 3.7558439999999997) {
            return x * (1.1283791670955126 + x2 * (0.37612638903183754 + x2 * (0.11283791670955126 + x2 * (0.026866170645131252 + x2 * (0.005223977625442188 + x2 * (8.548327023450853E-4 + x2 * (1.2055332981789664E-4 + x2 * (1.492565035840625E-5 + x2 * 1.6462114365889248E-6))))))));
        }
        return ((x > 0.0) ? 1.0 : -1.0) + 0.5641895835477563 / x * Math.exp(x2) * (1.0 + 0.5 / x2);
    }
    
    public double gamma(final double x) {
        if (x < 0.999999) {
            final double y = Math.sin(3.141592653589793 * (1.0 - x));
            if (0.0 == y) {
                return Double.POSITIVE_INFINITY;
            }
            return 3.141592653589793 * (1.0 - x) / this.gamma(2.0 - x) / y;
        }
        else {
            final double y = x - 0.5 + 0.5772156649015329;
            final double series = -0.033065598370407914 + x * (7.675315151147096 + x * (0.17079593808620658 + x * (-0.053762067160213846 + x * (0.010456709616406415 + x * (-0.0012260922213384849 + x * (7.933850210667055E-5 + x * -2.1735396138730348E-6))))));
            if (series <= 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            return Math.pow(y, x - 0.5) * Math.exp(-y) * 2.5066282746310007 * (1.0 + 1.0 / series);
        }
    }
    
    public double logGamma(final double x) {
        if (x < 0.999999) {
            final double y = Math.sin(3.141592653589793 * (1.0 - x));
            if (y <= 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            return Math.log(3.141592653589793 * (1.0 - x) / y) - this.logGamma(2.0 - x);
        }
        else {
            final double y = x - 0.5 + 0.5772156649015329;
            final double series = -0.033065598370407914 + x * (7.675315151147096 + x * (0.17079593808620658 + x * (-0.053762067160213846 + x * (0.010456709616406415 + x * (-0.0012260922213384849 + x * (7.933850210667055E-5 + x * -2.1735396138730348E-6))))));
            if (series <= 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            return (x - 0.5) * Math.log(y) - y + 0.9189385332046728 + Math.log(1.0 + 1.0 / series);
        }
    }
    
    double factorial2(final double x) {
        return Math.pow(2.0, x / 2.0) * Math.pow(0.6366197723675814, 0.25 * (1.0 - Math.cos(3.141592653589793 * x)) * this.gamma(1.0 + x / 2.0));
    }
}
