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
    public int inactive_flag;
    public int if_clause_primitive_min_index;
    public int if_clause_primitive_max_index;
    public int else_clause_primitive_min_index;
    public int else_clause_primitive_max_index;
    public int default_clause_primitive_min_index;
    public int default_clause_primitive_max_index;
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
        this.inactive_flag = 0;
        this.if_clause_primitive_min_index = -1;
        this.if_clause_primitive_max_index = -1;
        this.else_clause_primitive_min_index = -1;
        this.else_clause_primitive_max_index = -1;
        this.default_clause_primitive_min_index = -1;
        this.default_clause_primitive_max_index = -1;
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
        this.inactive_flag = 0;
        this.if_clause_primitive_min_index = -1;
        this.if_clause_primitive_max_index = -1;
        this.else_clause_primitive_min_index = -1;
        this.else_clause_primitive_max_index = -1;
        this.default_clause_primitive_min_index = -1;
        this.default_clause_primitive_max_index = -1;
    }
    
    public void setVariable(final int new_variable_index) {
        this.graphics = null;
        this.point_index = -1;
        this.coordinate_index = -1;
        this.variable_index = new_variable_index;
        this.inactive_flag = 0;
        this.if_clause_primitive_min_index = -1;
        this.if_clause_primitive_max_index = -1;
        this.else_clause_primitive_min_index = -1;
        this.else_clause_primitive_max_index = -1;
        this.default_clause_primitive_min_index = -1;
        this.default_clause_primitive_max_index = -1;
    }
    
    public void setIfCondition(final Graphics3D new_graphics, final int flag, final int if_min_index, final int if_max_index, final int else_min_index, final int else_max_index, final int default_min_index, final int default_max_index) {
        this.graphics = new_graphics;
        this.point_index = -1;
        this.coordinate_index = -1;
        this.variable_index = -1;
        this.inactive_flag = flag;
        this.if_clause_primitive_min_index = if_min_index;
        this.if_clause_primitive_max_index = if_max_index;
        this.else_clause_primitive_min_index = else_min_index;
        this.else_clause_primitive_max_index = else_max_index;
        this.default_clause_primitive_min_index = default_min_index;
        this.default_clause_primitive_max_index = default_max_index;
    }
    
    public boolean setValue() {
        if (this.variable_index >= 0) {
            this.evaluator.setVariableValue(this.variable_index, this.current_value);
            return true;
        }
        if (null != this.graphics && this.point_index >= 0 && this.coordinate_index >= 0) {
            return this.graphics.setCoordinateValue(this.point_index, this.coordinate_index, this.current_value);
        }
        if (null == this.graphics || this.inactive_flag <= 0) {
            return true;
        }
        if (1.0 == this.current_value) {
            this.graphics.clearInactiveFlags(this.if_clause_primitive_min_index, this.if_clause_primitive_max_index, this.inactive_flag);
            this.graphics.setInactiveFlags(this.else_clause_primitive_min_index, this.else_clause_primitive_max_index, this.inactive_flag);
            this.graphics.setInactiveFlags(this.default_clause_primitive_min_index, this.default_clause_primitive_max_index, this.inactive_flag);
            return true;
        }
        if (0.0 == this.current_value) {
            this.graphics.setInactiveFlags(this.if_clause_primitive_min_index, this.if_clause_primitive_max_index, this.inactive_flag);
            this.graphics.clearInactiveFlags(this.else_clause_primitive_min_index, this.else_clause_primitive_max_index, this.inactive_flag);
            this.graphics.setInactiveFlags(this.default_clause_primitive_min_index, this.default_clause_primitive_max_index, this.inactive_flag);
            return true;
        }
        this.graphics.setInactiveFlags(this.if_clause_primitive_min_index, this.if_clause_primitive_max_index, this.inactive_flag);
        this.graphics.setInactiveFlags(this.else_clause_primitive_min_index, this.else_clause_primitive_max_index, this.inactive_flag);
        this.graphics.clearInactiveFlags(this.default_clause_primitive_min_index, this.default_clause_primitive_max_index, this.inactive_flag);
        return true;
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
    
    public int evaluate_function(final double[] stack, final int stack_size, final int index) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        Expression.values_list:[D
        //     4: iload_3         /* index */
        //     5: daload         
        //     6: d2i            
        //     7: sipush          1024
        //    10: idiv           
        //    11: istore          arguments_count
        //    13: aload_0         /* this */
        //    14: getfield        Expression.values_list:[D
        //    17: iload_3         /* index */
        //    18: daload         
        //    19: d2i            
        //    20: sipush          1023
        //    23: iand           
        //    24: istore          function_index
        //    26: iload_2         /* stack_size */
        //    27: iload           arguments_count
        //    29: if_icmpge       34
        //    32: iconst_m1      
        //    33: ireturn        
        //    34: iload           function_index
        //    36: tableswitch {
        //                2: 504
        //                3: 545
        //                4: 583
        //                5: 611
        //                6: 652
        //                7: 699
        //                8: 742
        //                9: 772
        //               10: 830
        //               11: 919
        //               12: 970
        //               13: 1027
        //               14: 1057
        //               15: 1087
        //               16: 1174
        //               17: 1223
        //               18: 1272
        //               19: 1280
        //               20: 1272
        //               21: 1297
        //               22: 1354
        //               23: 1410
        //               24: 1460
        //               25: 1483
        //               26: 1508
        //               27: 1630
        //               28: 1660
        //               29: 1699
        //               30: 1729
        //               31: 1759
        //               32: 1789
        //               33: 1834
        //               34: 1879
        //               35: 1924
        //               36: 1972
        //               37: 2020
        //               38: 2088
        //               39: 2151
        //               40: 2214
        //               41: 2259
        //               42: 2300
        //               43: 2341
        //               44: 2404
        //               45: 2456
        //               46: 2508
        //               47: 2571
        //               48: 2612
        //               49: 2666
        //               50: 2739
        //               51: 2825
        //               52: 2888
        //               53: 2954
        //               54: 2998
        //               55: 3029
        //               56: 3126
        //               57: 3228
        //               58: 3307
        //               59: 3338
        //               60: 3438
        //               61: 3518
        //               62: 3593
        //               63: 3626
        //               64: 3657
        //               65: 3768
        //               66: 3768
        //               67: 3768
        //               68: 3768
        //               69: 3768
        //               70: 3768
        //               71: 3972
        //               72: 3972
        //               73: 3998
        //               74: 4049
        //               75: 4123
        //               76: 4197
        //               77: 4240
        //               78: 4283
        //               79: 4326
        //               80: 4369
        //               81: 3972
        //               82: 4412
        //               83: 4525
        //               84: 4525
        //               85: 4525
        //               86: 4464
        //               87: 4689
        //               88: 4796
        //               89: 4897
        //               90: 5000
        //               91: 5000
        //               92: 5000
        //               93: 5000
        //               94: 5000
        //               95: 5000
        //               96: 5008
        //               97: 5008
        //               98: 5008
        //               99: 5396
        //              100: 5008
        //              101: 5000
        //              102: 5000
        //              103: 5000
        //              104: 5000
        //              105: 5000
        //              106: 5000
        //              107: 5063
        //              108: 5063
        //              109: 5099
        //              110: 5127
        //              111: 5193
        //              112: 5283
        //              113: 3369
        //              114: 5349
        //          default: 5396
        //        }
        //   504: dconst_0       
        //   505: dstore          result
        //   507: iconst_0       
        //   508: istore          argument_index
        //   510: iload           argument_index
        //   512: iload           arguments_count
        //   514: if_icmpge       534
        //   517: dload           result
        //   519: aload_1         /* stack */
        //   520: iinc            stack_size, -1
        //   523: iload_2         /* stack_size */
        //   524: daload         
        //   525: dadd           
        //   526: dstore          result
        //   528: iinc            argument_index, 1
        //   531: goto            510
        //   534: aload_1         /* stack */
        //   535: iload_2         /* stack_size */
        //   536: iinc            stack_size, 1
        //   539: dload           result
        //   541: dastore        
        //   542: goto            5398
        //   545: iload           arguments_count
        //   547: iconst_2       
        //   548: if_icmpeq       553
        //   551: iconst_m1      
        //   552: ireturn        
        //   553: aload_1         /* stack */
        //   554: iinc            stack_size, -1
        //   557: iload_2         /* stack_size */
        //   558: daload         
        //   559: dstore          second_argument
        //   561: aload_1         /* stack */
        //   562: iinc            stack_size, -1
        //   565: iload_2         /* stack_size */
        //   566: daload         
        //   567: dstore          first_argument
        //   569: aload_1         /* stack */
        //   570: iload_2         /* stack_size */
        //   571: iinc            stack_size, 1
        //   574: dload           first_argument
        //   576: dload           second_argument
        //   578: dsub           
        //   579: dastore        
        //   580: goto            5398
        //   583: iload           arguments_count
        //   585: iconst_1       
        //   586: if_icmpeq       591
        //   589: iconst_m1      
        //   590: ireturn        
        //   591: aload_1         /* stack */
        //   592: iinc            stack_size, -1
        //   595: iload_2         /* stack_size */
        //   596: daload         
        //   597: dstore          first_argument
        //   599: aload_1         /* stack */
        //   600: iload_2         /* stack_size */
        //   601: iinc            stack_size, 1
        //   604: dload           first_argument
        //   606: dneg           
        //   607: dastore        
        //   608: goto            5398
        //   611: dconst_1       
        //   612: dstore          result
        //   614: iconst_0       
        //   615: istore          argument_index
        //   617: iload           argument_index
        //   619: iload           arguments_count
        //   621: if_icmpge       641
        //   624: dload           result
        //   626: aload_1         /* stack */
        //   627: iinc            stack_size, -1
        //   630: iload_2         /* stack_size */
        //   631: daload         
        //   632: dmul           
        //   633: dstore          result
        //   635: iinc            argument_index, 1
        //   638: goto            617
        //   641: aload_1         /* stack */
        //   642: iload_2         /* stack_size */
        //   643: iinc            stack_size, 1
        //   646: dload           result
        //   648: dastore        
        //   649: goto            5398
        //   652: iload           arguments_count
        //   654: iconst_2       
        //   655: if_icmpeq       660
        //   658: iconst_m1      
        //   659: ireturn        
        //   660: aload_1         /* stack */
        //   661: iinc            stack_size, -1
        //   664: iload_2         /* stack_size */
        //   665: daload         
        //   666: dstore          second_argument
        //   668: aload_1         /* stack */
        //   669: iinc            stack_size, -1
        //   672: iload_2         /* stack_size */
        //   673: daload         
        //   674: dstore          first_argument
        //   676: dconst_0       
        //   677: dload           second_argument
        //   679: dcmpl          
        //   680: ifne            685
        //   683: iconst_m1      
        //   684: ireturn        
        //   685: aload_1         /* stack */
        //   686: iload_2         /* stack_size */
        //   687: iinc            stack_size, 1
        //   690: dload           first_argument
        //   692: dload           second_argument
        //   694: ddiv           
        //   695: dastore        
        //   696: goto            5398
        //   699: dconst_1       
        //   700: dstore          result
        //   702: iconst_0       
        //   703: istore          argument_index
        //   705: iload           argument_index
        //   707: iload           arguments_count
        //   709: if_icmpge       731
        //   712: aload_1         /* stack */
        //   713: iinc            stack_size, -1
        //   716: iload_2         /* stack_size */
        //   717: daload         
        //   718: dload           result
        //   720: invokestatic    java/lang/Math.pow:(DD)D
        //   723: dstore          result
        //   725: iinc            argument_index, 1
        //   728: goto            705
        //   731: aload_1         /* stack */
        //   732: iload_2         /* stack_size */
        //   733: iinc            stack_size, 1
        //   736: dload           result
        //   738: dastore        
        //   739: goto            5398
        //   742: iload           arguments_count
        //   744: iconst_1       
        //   745: if_icmpeq       750
        //   748: iconst_m1      
        //   749: ireturn        
        //   750: aload_1         /* stack */
        //   751: iinc            stack_size, -1
        //   754: iload_2         /* stack_size */
        //   755: daload         
        //   756: dstore          first_argument
        //   758: aload_1         /* stack */
        //   759: iload_2         /* stack_size */
        //   760: iinc            stack_size, 1
        //   763: dload           first_argument
        //   765: invokestatic    java/lang/Math.abs:(D)D
        //   768: dastore        
        //   769: goto            5398
        //   772: iload           arguments_count
        //   774: iconst_1       
        //   775: if_icmpeq       780
        //   778: iconst_m1      
        //   779: ireturn        
        //   780: aload_1         /* stack */
        //   781: iinc            stack_size, -1
        //   784: iload_2         /* stack_size */
        //   785: daload         
        //   786: dstore          first_argument
        //   788: dload           first_argument
        //   790: dconst_0       
        //   791: dcmpl          
        //   792: ifle            801
        //   795: dconst_1       
        //   796: dstore          result
        //   798: goto            819
        //   801: dload           first_argument
        //   803: dconst_0       
        //   804: dcmpg          
        //   805: ifge            816
        //   808: ldc2_w          -1.0
        //   811: dstore          result
        //   813: goto            819
        //   816: dconst_0       
        //   817: dstore          result
        //   819: aload_1         /* stack */
        //   820: iload_2         /* stack_size */
        //   821: iinc            stack_size, 1
        //   824: dload           result
        //   826: dastore        
        //   827: goto            5398
        //   830: iload           arguments_count
        //   832: iconst_1       
        //   833: if_icmpeq       838
        //   836: iconst_m1      
        //   837: ireturn        
        //   838: aload_1         /* stack */
        //   839: iinc            stack_size, -1
        //   842: iload_2         /* stack_size */
        //   843: daload         
        //   844: dstore          first_argument
        //   846: dload           first_argument
        //   848: ldc2_w          0.5
        //   851: dadd           
        //   852: invokestatic    java/lang/Math.floor:(D)D
        //   855: dstore          result
        //   857: dload           result
        //   859: dload           first_argument
        //   861: ldc2_w          0.5
        //   864: dadd           
        //   865: dcmpl          
        //   866: ifne            908
        //   869: dload           result
        //   871: dconst_0       
        //   872: dcmpl          
        //   873: ifle            885
        //   876: iconst_1       
        //   877: iconst_1       
        //   878: dload           result
        //   880: d2i            
        //   881: iand           
        //   882: if_icmpeq       902
        //   885: dload           result
        //   887: dconst_0       
        //   888: dcmpg          
        //   889: ifge            908
        //   892: iconst_1       
        //   893: iconst_1       
        //   894: dload           result
        //   896: dneg           
        //   897: d2i            
        //   898: iand           
        //   899: if_icmpne       908
        //   902: dload           result
        //   904: dconst_1       
        //   905: dsub           
        //   906: dstore          result
        //   908: aload_1         /* stack */
        //   909: iload_2         /* stack_size */
        //   910: iinc            stack_size, 1
        //   913: dload           result
        //   915: dastore        
        //   916: goto            5398
        //   919: iload           arguments_count
        //   921: iconst_1       
        //   922: if_icmpeq       927
        //   925: iconst_m1      
        //   926: ireturn        
        //   927: aload_1         /* stack */
        //   928: iinc            stack_size, -1
        //   931: iload_2         /* stack_size */
        //   932: daload         
        //   933: dstore          first_argument
        //   935: dload           first_argument
        //   937: dconst_0       
        //   938: dcmpl          
        //   939: iflt            952
        //   942: dload           first_argument
        //   944: invokestatic    java/lang/Math.floor:(D)D
        //   947: dstore          result
        //   949: goto            959
        //   952: dload           first_argument
        //   954: invokestatic    java/lang/Math.ceil:(D)D
        //   957: dstore          result
        //   959: aload_1         /* stack */
        //   960: iload_2         /* stack_size */
        //   961: iinc            stack_size, 1
        //   964: dload           result
        //   966: dastore        
        //   967: goto            5398
        //   970: iload           arguments_count
        //   972: iconst_1       
        //   973: if_icmpeq       978
        //   976: iconst_m1      
        //   977: ireturn        
        //   978: aload_1         /* stack */
        //   979: iinc            stack_size, -1
        //   982: iload_2         /* stack_size */
        //   983: daload         
        //   984: dstore          first_argument
        //   986: dload           first_argument
        //   988: dconst_0       
        //   989: dcmpl          
        //   990: iflt            1006
        //   993: dload           first_argument
        //   995: dload           first_argument
        //   997: invokestatic    java/lang/Math.floor:(D)D
        //  1000: dsub           
        //  1001: dstore          result
        //  1003: goto            1016
        //  1006: dload           first_argument
        //  1008: dload           first_argument
        //  1010: invokestatic    java/lang/Math.ceil:(D)D
        //  1013: dsub           
        //  1014: dstore          result
        //  1016: aload_1         /* stack */
        //  1017: iload_2         /* stack_size */
        //  1018: iinc            stack_size, 1
        //  1021: dload           result
        //  1023: dastore        
        //  1024: goto            5398
        //  1027: iload           arguments_count
        //  1029: iconst_1       
        //  1030: if_icmpeq       1035
        //  1033: iconst_m1      
        //  1034: ireturn        
        //  1035: aload_1         /* stack */
        //  1036: iinc            stack_size, -1
        //  1039: iload_2         /* stack_size */
        //  1040: daload         
        //  1041: dstore          first_argument
        //  1043: aload_1         /* stack */
        //  1044: iload_2         /* stack_size */
        //  1045: iinc            stack_size, 1
        //  1048: dload           first_argument
        //  1050: invokestatic    java/lang/Math.floor:(D)D
        //  1053: dastore        
        //  1054: goto            5398
        //  1057: iload           arguments_count
        //  1059: iconst_1       
        //  1060: if_icmpeq       1065
        //  1063: iconst_m1      
        //  1064: ireturn        
        //  1065: aload_1         /* stack */
        //  1066: iinc            stack_size, -1
        //  1069: iload_2         /* stack_size */
        //  1070: daload         
        //  1071: dstore          first_argument
        //  1073: aload_1         /* stack */
        //  1074: iload_2         /* stack_size */
        //  1075: iinc            stack_size, 1
        //  1078: dload           first_argument
        //  1080: invokestatic    java/lang/Math.ceil:(D)D
        //  1083: dastore        
        //  1084: goto            5398
        //  1087: iconst_1       
        //  1088: iload           arguments_count
        //  1090: if_icmpne       1109
        //  1093: ldc2_w          1.0E-10
        //  1096: dstore          second_argument
        //  1098: aload_1         /* stack */
        //  1099: iinc            stack_size, -1
        //  1102: iload_2         /* stack_size */
        //  1103: daload         
        //  1104: dstore          first_argument
        //  1106: goto            1142
        //  1109: iconst_2       
        //  1110: iload           arguments_count
        //  1112: if_icmpne       1140
        //  1115: aload_1         /* stack */
        //  1116: iinc            stack_size, -1
        //  1119: iload_2         /* stack_size */
        //  1120: daload         
        //  1121: dstore          second_argument
        //  1123: aload_1         /* stack */
        //  1124: iinc            stack_size, -1
        //  1127: iload_2         /* stack_size */
        //  1128: daload         
        //  1129: dstore          first_argument
        //  1131: dload           second_argument
        //  1133: dconst_0       
        //  1134: dcmpg          
        //  1135: ifge            1142
        //  1138: iconst_m1      
        //  1139: ireturn        
        //  1140: iconst_m1      
        //  1141: ireturn        
        //  1142: dload           7
        //  1144: invokestatic    java/lang/Math.abs:(D)D
        //  1147: dload           9
        //  1149: dcmpg          
        //  1150: ifge            1159
        //  1153: dconst_0       
        //  1154: dstore          result
        //  1156: goto            1163
        //  1159: dload           7
        //  1161: dstore          result
        //  1163: aload_1         /* stack */
        //  1164: iload_2         /* stack_size */
        //  1165: iinc            stack_size, 1
        //  1168: dload           result
        //  1170: dastore        
        //  1171: goto            5398
        //  1174: ldc2_w          4.9E-324
        //  1177: dstore          result
        //  1179: iconst_0       
        //  1180: istore          argument_index
        //  1182: iload           argument_index
        //  1184: iload           arguments_count
        //  1186: if_icmpge       1212
        //  1189: aload_1         /* stack */
        //  1190: iinc            stack_size, -1
        //  1193: iload_2         /* stack_size */
        //  1194: daload         
        //  1195: dload           result
        //  1197: dcmpl          
        //  1198: ifle            1206
        //  1201: aload_1         /* stack */
        //  1202: iload_2         /* stack_size */
        //  1203: daload         
        //  1204: dstore          result
        //  1206: iinc            argument_index, 1
        //  1209: goto            1182
        //  1212: aload_1         /* stack */
        //  1213: iload_2         /* stack_size */
        //  1214: iinc            stack_size, 1
        //  1217: dload           result
        //  1219: dastore        
        //  1220: goto            5398
        //  1223: ldc2_w          1.7976931348623157E308
        //  1226: dstore          result
        //  1228: iconst_0       
        //  1229: istore          argument_index
        //  1231: iload           argument_index
        //  1233: iload           arguments_count
        //  1235: if_icmpge       1261
        //  1238: aload_1         /* stack */
        //  1239: iinc            stack_size, -1
        //  1242: iload_2         /* stack_size */
        //  1243: daload         
        //  1244: dload           result
        //  1246: dcmpg          
        //  1247: ifge            1255
        //  1250: aload_1         /* stack */
        //  1251: iload_2         /* stack_size */
        //  1252: daload         
        //  1253: dstore          result
        //  1255: iinc            argument_index, 1
        //  1258: goto            1231
        //  1261: aload_1         /* stack */
        //  1262: iload_2         /* stack_size */
        //  1263: iinc            stack_size, 1
        //  1266: dload           result
        //  1268: dastore        
        //  1269: goto            5398
        //  1272: iload           arguments_count
        //  1274: iconst_1       
        //  1275: if_icmpeq       5398
        //  1278: iconst_m1      
        //  1279: ireturn        
        //  1280: iload           arguments_count
        //  1282: iconst_1       
        //  1283: if_icmpeq       1288
        //  1286: iconst_m1      
        //  1287: ireturn        
        //  1288: aload_1         /* stack */
        //  1289: iload_2         /* stack_size */
        //  1290: iconst_1       
        //  1291: isub           
        //  1292: dconst_0       
        //  1293: dastore        
        //  1294: goto            5398
        //  1297: iload           arguments_count
        //  1299: iconst_1       
        //  1300: if_icmpeq       1305
        //  1303: iconst_m1      
        //  1304: ireturn        
        //  1305: aload_1         /* stack */
        //  1306: iinc            stack_size, -1
        //  1309: iload_2         /* stack_size */
        //  1310: daload         
        //  1311: dstore          first_argument
        //  1313: dload           first_argument
        //  1315: dconst_0       
        //  1316: dcmpl          
        //  1317: ifle            1326
        //  1320: dconst_0       
        //  1321: dstore          result
        //  1323: goto            1343
        //  1326: dload           first_argument
        //  1328: dconst_0       
        //  1329: dcmpg          
        //  1330: ifge            1341
        //  1333: ldc2_w          3.141592653589793
        //  1336: dstore          result
        //  1338: goto            1343
        //  1341: iconst_m1      
        //  1342: ireturn        
        //  1343: aload_1         /* stack */
        //  1344: iload_2         /* stack_size */
        //  1345: iinc            stack_size, 1
        //  1348: dload           11
        //  1350: dastore        
        //  1351: goto            5398
        //  1354: iload           arguments_count
        //  1356: iconst_2       
        //  1357: if_icmpeq       1362
        //  1360: iconst_m1      
        //  1361: ireturn        
        //  1362: aload_1         /* stack */
        //  1363: iinc            stack_size, -1
        //  1366: iload_2         /* stack_size */
        //  1367: daload         
        //  1368: dstore          second_argument
        //  1370: aload_1         /* stack */
        //  1371: iinc            stack_size, -1
        //  1374: iload_2         /* stack_size */
        //  1375: daload         
        //  1376: dstore          first_argument
        //  1378: dconst_0       
        //  1379: dload           second_argument
        //  1381: dcmpl          
        //  1382: ifne            1387
        //  1385: iconst_m1      
        //  1386: ireturn        
        //  1387: aload_1         /* stack */
        //  1388: iload_2         /* stack_size */
        //  1389: iinc            stack_size, 1
        //  1392: dload           first_argument
        //  1394: dload           second_argument
        //  1396: dload           first_argument
        //  1398: dload           second_argument
        //  1400: ddiv           
        //  1401: invokestatic    java/lang/Math.floor:(D)D
        //  1404: dmul           
        //  1405: dsub           
        //  1406: dastore        
        //  1407: goto            5398
        //  1410: iload           arguments_count
        //  1412: iconst_2       
        //  1413: if_icmpeq       1418
        //  1416: iconst_m1      
        //  1417: ireturn        
        //  1418: aload_1         /* stack */
        //  1419: iinc            stack_size, -1
        //  1422: iload_2         /* stack_size */
        //  1423: daload         
        //  1424: dstore          second_argument
        //  1426: aload_1         /* stack */
        //  1427: iinc            stack_size, -1
        //  1430: iload_2         /* stack_size */
        //  1431: daload         
        //  1432: dstore          first_argument
        //  1434: dconst_0       
        //  1435: dload           second_argument
        //  1437: dcmpl          
        //  1438: ifne            1443
        //  1441: iconst_m1      
        //  1442: ireturn        
        //  1443: aload_1         /* stack */
        //  1444: iload_2         /* stack_size */
        //  1445: iinc            stack_size, 1
        //  1448: dload           first_argument
        //  1450: dload           second_argument
        //  1452: ddiv           
        //  1453: invokestatic    java/lang/Math.floor:(D)D
        //  1456: dastore        
        //  1457: goto            5398
        //  1460: iload           arguments_count
        //  1462: ifeq            1467
        //  1465: iconst_m1      
        //  1466: ireturn        
        //  1467: invokestatic    java/lang/Math.random:()D
        //  1470: dstore          result
        //  1472: aload_1         /* stack */
        //  1473: iload_2         /* stack_size */
        //  1474: iinc            stack_size, 1
        //  1477: dload           result
        //  1479: dastore        
        //  1480: goto            5398
        //  1483: iload           arguments_count
        //  1485: iconst_1       
        //  1486: if_icmple       1491
        //  1489: iconst_m1      
        //  1490: ireturn        
        //  1491: iload           arguments_count
        //  1493: iconst_1       
        //  1494: if_icmpne       5398
        //  1497: aload_1         /* stack */
        //  1498: iinc            stack_size, -1
        //  1501: iload_2         /* stack_size */
        //  1502: daload         
        //  1503: dstore          first_argument
        //  1505: goto            5398
        //  1508: iconst_2       
        //  1509: iload           arguments_count
        //  1511: if_icmpne       1591
        //  1514: aload_1         /* stack */
        //  1515: iinc            stack_size, -1
        //  1518: iload_2         /* stack_size */
        //  1519: daload         
        //  1520: dstore          second_argument
        //  1522: aload_1         /* stack */
        //  1523: iinc            stack_size, -1
        //  1526: iload_2         /* stack_size */
        //  1527: daload         
        //  1528: dstore          first_argument
        //  1530: dconst_0       
        //  1531: dload           second_argument
        //  1533: dcmpl          
        //  1534: ifge            1551
        //  1537: dconst_0       
        //  1538: dload           first_argument
        //  1540: dcmpl          
        //  1541: ifgt            1551
        //  1544: dconst_1       
        //  1545: dload           first_argument
        //  1547: dcmpl          
        //  1548: ifne            1553
        //  1551: iconst_m1      
        //  1552: ireturn        
        //  1553: dconst_0       
        //  1554: dload           first_argument
        //  1556: dcmpl          
        //  1557: ifne            1567
        //  1560: aload_1         /* stack */
        //  1561: iload_2         /* stack_size */
        //  1562: iinc            stack_size, 1
        //  1565: dconst_0       
        //  1566: dastore        
        //  1567: dload           second_argument
        //  1569: invokestatic    java/lang/Math.log:(D)D
        //  1572: dload           first_argument
        //  1574: invokestatic    java/lang/Math.log:(D)D
        //  1577: ddiv           
        //  1578: dstore          result
        //  1580: aload_1         /* stack */
        //  1581: iload_2         /* stack_size */
        //  1582: iinc            stack_size, 1
        //  1585: dload           result
        //  1587: dastore        
        //  1588: goto            5398
        //  1591: iconst_1       
        //  1592: iload           arguments_count
        //  1594: if_icmpne       1628
        //  1597: aload_1         /* stack */
        //  1598: iinc            stack_size, -1
        //  1601: iload_2         /* stack_size */
        //  1602: daload         
        //  1603: dstore          second_argument
        //  1605: dconst_0       
        //  1606: dload           second_argument
        //  1608: dcmpl          
        //  1609: iflt            1614
        //  1612: iconst_m1      
        //  1613: ireturn        
        //  1614: aload_1         /* stack */
        //  1615: iload_2         /* stack_size */
        //  1616: iinc            stack_size, 1
        //  1619: dload           second_argument
        //  1621: invokestatic    java/lang/Math.log:(D)D
        //  1624: dastore        
        //  1625: goto            5398
        //  1628: iconst_m1      
        //  1629: ireturn        
        //  1630: iload           arguments_count
        //  1632: iconst_1       
        //  1633: if_icmpeq       1638
        //  1636: iconst_m1      
        //  1637: ireturn        
        //  1638: aload_1         /* stack */
        //  1639: iinc            stack_size, -1
        //  1642: iload_2         /* stack_size */
        //  1643: daload         
        //  1644: dstore          first_argument
        //  1646: aload_1         /* stack */
        //  1647: iload_2         /* stack_size */
        //  1648: iinc            stack_size, 1
        //  1651: dload           first_argument
        //  1653: invokestatic    java/lang/Math.exp:(D)D
        //  1656: dastore        
        //  1657: goto            5398
        //  1660: iload           arguments_count
        //  1662: iconst_1       
        //  1663: if_icmpeq       1668
        //  1666: iconst_m1      
        //  1667: ireturn        
        //  1668: aload_1         /* stack */
        //  1669: iinc            stack_size, -1
        //  1672: iload_2         /* stack_size */
        //  1673: daload         
        //  1674: dstore          first_argument
        //  1676: dconst_0       
        //  1677: dload           first_argument
        //  1679: dcmpl          
        //  1680: ifle            1685
        //  1683: iconst_m1      
        //  1684: ireturn        
        //  1685: aload_1         /* stack */
        //  1686: iload_2         /* stack_size */
        //  1687: iinc            stack_size, 1
        //  1690: dload           first_argument
        //  1692: invokestatic    java/lang/Math.sqrt:(D)D
        //  1695: dastore        
        //  1696: goto            5398
        //  1699: iload           arguments_count
        //  1701: iconst_1       
        //  1702: if_icmpeq       1707
        //  1705: iconst_m1      
        //  1706: ireturn        
        //  1707: aload_1         /* stack */
        //  1708: iinc            stack_size, -1
        //  1711: iload_2         /* stack_size */
        //  1712: daload         
        //  1713: dstore          first_argument
        //  1715: aload_1         /* stack */
        //  1716: iload_2         /* stack_size */
        //  1717: iinc            stack_size, 1
        //  1720: dload           first_argument
        //  1722: invokestatic    java/lang/Math.sin:(D)D
        //  1725: dastore        
        //  1726: goto            5398
        //  1729: iload           arguments_count
        //  1731: iconst_1       
        //  1732: if_icmpeq       1737
        //  1735: iconst_m1      
        //  1736: ireturn        
        //  1737: aload_1         /* stack */
        //  1738: iinc            stack_size, -1
        //  1741: iload_2         /* stack_size */
        //  1742: daload         
        //  1743: dstore          first_argument
        //  1745: aload_1         /* stack */
        //  1746: iload_2         /* stack_size */
        //  1747: iinc            stack_size, 1
        //  1750: dload           first_argument
        //  1752: invokestatic    java/lang/Math.cos:(D)D
        //  1755: dastore        
        //  1756: goto            5398
        //  1759: iload           arguments_count
        //  1761: iconst_1       
        //  1762: if_icmpeq       1767
        //  1765: iconst_m1      
        //  1766: ireturn        
        //  1767: aload_1         /* stack */
        //  1768: iinc            stack_size, -1
        //  1771: iload_2         /* stack_size */
        //  1772: daload         
        //  1773: dstore          first_argument
        //  1775: aload_1         /* stack */
        //  1776: iload_2         /* stack_size */
        //  1777: iinc            stack_size, 1
        //  1780: dload           first_argument
        //  1782: invokestatic    java/lang/Math.tan:(D)D
        //  1785: dastore        
        //  1786: goto            5398
        //  1789: iload           arguments_count
        //  1791: iconst_1       
        //  1792: if_icmpeq       1797
        //  1795: iconst_m1      
        //  1796: ireturn        
        //  1797: aload_1         /* stack */
        //  1798: iinc            stack_size, -1
        //  1801: iload_2         /* stack_size */
        //  1802: daload         
        //  1803: dstore          first_argument
        //  1805: dload           first_argument
        //  1807: invokestatic    java/lang/Math.sin:(D)D
        //  1810: dstore          result
        //  1812: dconst_0       
        //  1813: dload           result
        //  1815: dcmpl          
        //  1816: ifne            1821
        //  1819: iconst_m1      
        //  1820: ireturn        
        //  1821: aload_1         /* stack */
        //  1822: iload_2         /* stack_size */
        //  1823: iinc            stack_size, 1
        //  1826: dconst_1       
        //  1827: dload           result
        //  1829: ddiv           
        //  1830: dastore        
        //  1831: goto            5398
        //  1834: iload           arguments_count
        //  1836: iconst_1       
        //  1837: if_icmpeq       1842
        //  1840: iconst_m1      
        //  1841: ireturn        
        //  1842: aload_1         /* stack */
        //  1843: iinc            stack_size, -1
        //  1846: iload_2         /* stack_size */
        //  1847: daload         
        //  1848: dstore          first_argument
        //  1850: dload           first_argument
        //  1852: invokestatic    java/lang/Math.cos:(D)D
        //  1855: dstore          result
        //  1857: dconst_0       
        //  1858: dload           result
        //  1860: dcmpl          
        //  1861: ifne            1866
        //  1864: iconst_m1      
        //  1865: ireturn        
        //  1866: aload_1         /* stack */
        //  1867: iload_2         /* stack_size */
        //  1868: iinc            stack_size, 1
        //  1871: dconst_1       
        //  1872: dload           result
        //  1874: ddiv           
        //  1875: dastore        
        //  1876: goto            5398
        //  1879: iload           arguments_count
        //  1881: iconst_1       
        //  1882: if_icmpeq       1887
        //  1885: iconst_m1      
        //  1886: ireturn        
        //  1887: aload_1         /* stack */
        //  1888: iinc            stack_size, -1
        //  1891: iload_2         /* stack_size */
        //  1892: daload         
        //  1893: dstore          first_argument
        //  1895: dload           first_argument
        //  1897: invokestatic    java/lang/Math.tan:(D)D
        //  1900: dstore          result
        //  1902: dconst_0       
        //  1903: dload           result
        //  1905: dcmpl          
        //  1906: ifne            1911
        //  1909: iconst_m1      
        //  1910: ireturn        
        //  1911: aload_1         /* stack */
        //  1912: iload_2         /* stack_size */
        //  1913: iinc            stack_size, 1
        //  1916: dconst_1       
        //  1917: dload           result
        //  1919: ddiv           
        //  1920: dastore        
        //  1921: goto            5398
        //  1924: iload           arguments_count
        //  1926: iconst_1       
        //  1927: if_icmpeq       1932
        //  1930: iconst_m1      
        //  1931: ireturn        
        //  1932: aload_1         /* stack */
        //  1933: iinc            stack_size, -1
        //  1936: iload_2         /* stack_size */
        //  1937: daload         
        //  1938: dstore          first_argument
        //  1940: dload           first_argument
        //  1942: dconst_1       
        //  1943: dcmpl          
        //  1944: ifgt            1956
        //  1947: dload           first_argument
        //  1949: ldc2_w          -1.0
        //  1952: dcmpg          
        //  1953: ifge            1958
        //  1956: iconst_m1      
        //  1957: ireturn        
        //  1958: aload_1         /* stack */
        //  1959: iload_2         /* stack_size */
        //  1960: iinc            stack_size, 1
        //  1963: dload           first_argument
        //  1965: invokestatic    java/lang/Math.asin:(D)D
        //  1968: dastore        
        //  1969: goto            5398
        //  1972: iload           arguments_count
        //  1974: iconst_1       
        //  1975: if_icmpeq       1980
        //  1978: iconst_m1      
        //  1979: ireturn        
        //  1980: aload_1         /* stack */
        //  1981: iinc            stack_size, -1
        //  1984: iload_2         /* stack_size */
        //  1985: daload         
        //  1986: dstore          first_argument
        //  1988: dload           first_argument
        //  1990: dconst_1       
        //  1991: dcmpl          
        //  1992: ifgt            2004
        //  1995: dload           first_argument
        //  1997: ldc2_w          -1.0
        //  2000: dcmpg          
        //  2001: ifge            2006
        //  2004: iconst_m1      
        //  2005: ireturn        
        //  2006: aload_1         /* stack */
        //  2007: iload_2         /* stack_size */
        //  2008: iinc            stack_size, 1
        //  2011: dload           first_argument
        //  2013: invokestatic    java/lang/Math.acos:(D)D
        //  2016: dastore        
        //  2017: goto            5398
        //  2020: iconst_1       
        //  2021: iload           arguments_count
        //  2023: if_icmpne       2048
        //  2026: aload_1         /* stack */
        //  2027: iinc            stack_size, -1
        //  2030: iload_2         /* stack_size */
        //  2031: daload         
        //  2032: dstore          first_argument
        //  2034: aload_1         /* stack */
        //  2035: iload_2         /* stack_size */
        //  2036: iinc            stack_size, 1
        //  2039: dload           first_argument
        //  2041: invokestatic    java/lang/Math.atan:(D)D
        //  2044: dastore        
        //  2045: goto            5398
        //  2048: iconst_2       
        //  2049: iload           arguments_count
        //  2051: if_icmpne       2086
        //  2054: aload_1         /* stack */
        //  2055: iinc            stack_size, -1
        //  2058: iload_2         /* stack_size */
        //  2059: daload         
        //  2060: dstore          second_argument
        //  2062: aload_1         /* stack */
        //  2063: iinc            stack_size, -1
        //  2066: iload_2         /* stack_size */
        //  2067: daload         
        //  2068: dstore          first_argument
        //  2070: aload_1         /* stack */
        //  2071: iload_2         /* stack_size */
        //  2072: iinc            stack_size, 1
        //  2075: dload           second_argument
        //  2077: dload           first_argument
        //  2079: invokestatic    java/lang/Math.atan2:(DD)D
        //  2082: dastore        
        //  2083: goto            5398
        //  2086: iconst_m1      
        //  2087: ireturn        
        //  2088: iload           arguments_count
        //  2090: iconst_1       
        //  2091: if_icmpeq       2096
        //  2094: iconst_m1      
        //  2095: ireturn        
        //  2096: aload_1         /* stack */
        //  2097: iinc            stack_size, -1
        //  2100: iload_2         /* stack_size */
        //  2101: daload         
        //  2102: dstore          first_argument
        //  2104: dconst_0       
        //  2105: dload           first_argument
        //  2107: dcmpl          
        //  2108: ifne            2113
        //  2111: iconst_m1      
        //  2112: ireturn        
        //  2113: dconst_1       
        //  2114: dload           first_argument
        //  2116: ddiv           
        //  2117: dstore          result
        //  2119: dload           result
        //  2121: dconst_1       
        //  2122: dcmpl          
        //  2123: ifgt            2135
        //  2126: dload           result
        //  2128: ldc2_w          -1.0
        //  2131: dcmpg          
        //  2132: ifge            2137
        //  2135: iconst_m1      
        //  2136: ireturn        
        //  2137: aload_1         /* stack */
        //  2138: iload_2         /* stack_size */
        //  2139: iinc            stack_size, 1
        //  2142: dload           result
        //  2144: invokestatic    java/lang/Math.asin:(D)D
        //  2147: dastore        
        //  2148: goto            5398
        //  2151: iload           arguments_count
        //  2153: iconst_1       
        //  2154: if_icmpeq       2159
        //  2157: iconst_m1      
        //  2158: ireturn        
        //  2159: aload_1         /* stack */
        //  2160: iinc            stack_size, -1
        //  2163: iload_2         /* stack_size */
        //  2164: daload         
        //  2165: dstore          first_argument
        //  2167: dconst_0       
        //  2168: dload           first_argument
        //  2170: dcmpl          
        //  2171: ifne            2176
        //  2174: iconst_m1      
        //  2175: ireturn        
        //  2176: dconst_1       
        //  2177: dload           first_argument
        //  2179: ddiv           
        //  2180: dstore          result
        //  2182: dload           result
        //  2184: dconst_1       
        //  2185: dcmpl          
        //  2186: ifgt            2198
        //  2189: dload           result
        //  2191: ldc2_w          -1.0
        //  2194: dcmpg          
        //  2195: ifge            2200
        //  2198: iconst_m1      
        //  2199: ireturn        
        //  2200: aload_1         /* stack */
        //  2201: iload_2         /* stack_size */
        //  2202: iinc            stack_size, 1
        //  2205: dload           result
        //  2207: invokestatic    java/lang/Math.acos:(D)D
        //  2210: dastore        
        //  2211: goto            5398
        //  2214: iload           arguments_count
        //  2216: iconst_1       
        //  2217: if_icmpeq       2222
        //  2220: iconst_m1      
        //  2221: ireturn        
        //  2222: aload_1         /* stack */
        //  2223: iinc            stack_size, -1
        //  2226: iload_2         /* stack_size */
        //  2227: daload         
        //  2228: dstore          first_argument
        //  2230: dconst_0       
        //  2231: dload           first_argument
        //  2233: dcmpl          
        //  2234: ifne            2239
        //  2237: iconst_m1      
        //  2238: ireturn        
        //  2239: dconst_1       
        //  2240: dload           first_argument
        //  2242: ddiv           
        //  2243: dstore          result
        //  2245: aload_1         /* stack */
        //  2246: iload_2         /* stack_size */
        //  2247: iinc            stack_size, 1
        //  2250: dload           result
        //  2252: invokestatic    java/lang/Math.atan:(D)D
        //  2255: dastore        
        //  2256: goto            5398
        //  2259: iload           arguments_count
        //  2261: iconst_1       
        //  2262: if_icmpeq       2267
        //  2265: iconst_m1      
        //  2266: ireturn        
        //  2267: aload_1         /* stack */
        //  2268: iinc            stack_size, -1
        //  2271: iload_2         /* stack_size */
        //  2272: daload         
        //  2273: dstore          first_argument
        //  2275: aload_1         /* stack */
        //  2276: iload_2         /* stack_size */
        //  2277: iinc            stack_size, 1
        //  2280: dload           first_argument
        //  2282: invokestatic    java/lang/Math.exp:(D)D
        //  2285: dload           first_argument
        //  2287: dneg           
        //  2288: invokestatic    java/lang/Math.exp:(D)D
        //  2291: dsub           
        //  2292: ldc2_w          2.0
        //  2295: ddiv           
        //  2296: dastore        
        //  2297: goto            5398
        //  2300: iload           arguments_count
        //  2302: iconst_1       
        //  2303: if_icmpeq       2308
        //  2306: iconst_m1      
        //  2307: ireturn        
        //  2308: aload_1         /* stack */
        //  2309: iinc            stack_size, -1
        //  2312: iload_2         /* stack_size */
        //  2313: daload         
        //  2314: dstore          first_argument
        //  2316: aload_1         /* stack */
        //  2317: iload_2         /* stack_size */
        //  2318: iinc            stack_size, 1
        //  2321: dload           first_argument
        //  2323: invokestatic    java/lang/Math.exp:(D)D
        //  2326: dload           first_argument
        //  2328: dneg           
        //  2329: invokestatic    java/lang/Math.exp:(D)D
        //  2332: dadd           
        //  2333: ldc2_w          2.0
        //  2336: ddiv           
        //  2337: dastore        
        //  2338: goto            5398
        //  2341: iload           arguments_count
        //  2343: iconst_1       
        //  2344: if_icmpeq       2349
        //  2347: iconst_m1      
        //  2348: ireturn        
        //  2349: aload_1         /* stack */
        //  2350: iinc            stack_size, -1
        //  2353: iload_2         /* stack_size */
        //  2354: daload         
        //  2355: dstore          first_argument
        //  2357: dload           first_argument
        //  2359: invokestatic    java/lang/Math.exp:(D)D
        //  2362: dload           first_argument
        //  2364: dneg           
        //  2365: invokestatic    java/lang/Math.exp:(D)D
        //  2368: dadd           
        //  2369: dstore          result
        //  2371: dconst_0       
        //  2372: dload           result
        //  2374: dcmpl          
        //  2375: ifne            2380
        //  2378: iconst_m1      
        //  2379: ireturn        
        //  2380: aload_1         /* stack */
        //  2381: iload_2         /* stack_size */
        //  2382: iinc            stack_size, 1
        //  2385: dload           first_argument
        //  2387: invokestatic    java/lang/Math.exp:(D)D
        //  2390: dload           first_argument
        //  2392: dneg           
        //  2393: invokestatic    java/lang/Math.exp:(D)D
        //  2396: dsub           
        //  2397: dload           result
        //  2399: ddiv           
        //  2400: dastore        
        //  2401: goto            5398
        //  2404: iload           arguments_count
        //  2406: iconst_1       
        //  2407: if_icmpeq       2412
        //  2410: iconst_m1      
        //  2411: ireturn        
        //  2412: aload_1         /* stack */
        //  2413: iinc            stack_size, -1
        //  2416: iload_2         /* stack_size */
        //  2417: daload         
        //  2418: dstore          first_argument
        //  2420: dload           first_argument
        //  2422: invokestatic    java/lang/Math.exp:(D)D
        //  2425: dload           first_argument
        //  2427: dneg           
        //  2428: invokestatic    java/lang/Math.exp:(D)D
        //  2431: dsub           
        //  2432: dstore          result
        //  2434: dconst_0       
        //  2435: dload           result
        //  2437: dcmpl          
        //  2438: ifne            2443
        //  2441: iconst_m1      
        //  2442: ireturn        
        //  2443: aload_1         /* stack */
        //  2444: iload_2         /* stack_size */
        //  2445: iinc            stack_size, 1
        //  2448: dconst_1       
        //  2449: dload           result
        //  2451: ddiv           
        //  2452: dastore        
        //  2453: goto            5398
        //  2456: iload           arguments_count
        //  2458: iconst_1       
        //  2459: if_icmpeq       2464
        //  2462: iconst_m1      
        //  2463: ireturn        
        //  2464: aload_1         /* stack */
        //  2465: iinc            stack_size, -1
        //  2468: iload_2         /* stack_size */
        //  2469: daload         
        //  2470: dstore          first_argument
        //  2472: dload           first_argument
        //  2474: invokestatic    java/lang/Math.exp:(D)D
        //  2477: dload           first_argument
        //  2479: dneg           
        //  2480: invokestatic    java/lang/Math.exp:(D)D
        //  2483: dadd           
        //  2484: dstore          result
        //  2486: dconst_0       
        //  2487: dload           result
        //  2489: dcmpl          
        //  2490: ifne            2495
        //  2493: iconst_m1      
        //  2494: ireturn        
        //  2495: aload_1         /* stack */
        //  2496: iload_2         /* stack_size */
        //  2497: iinc            stack_size, 1
        //  2500: dconst_1       
        //  2501: dload           result
        //  2503: ddiv           
        //  2504: dastore        
        //  2505: goto            5398
        //  2508: iload           arguments_count
        //  2510: iconst_1       
        //  2511: if_icmpeq       2516
        //  2514: iconst_m1      
        //  2515: ireturn        
        //  2516: aload_1         /* stack */
        //  2517: iinc            stack_size, -1
        //  2520: iload_2         /* stack_size */
        //  2521: daload         
        //  2522: dstore          first_argument
        //  2524: dload           first_argument
        //  2526: invokestatic    java/lang/Math.exp:(D)D
        //  2529: dload           first_argument
        //  2531: dneg           
        //  2532: invokestatic    java/lang/Math.exp:(D)D
        //  2535: dsub           
        //  2536: dstore          result
        //  2538: dconst_0       
        //  2539: dload           result
        //  2541: dcmpl          
        //  2542: ifne            2547
        //  2545: iconst_m1      
        //  2546: ireturn        
        //  2547: aload_1         /* stack */
        //  2548: iload_2         /* stack_size */
        //  2549: iinc            stack_size, 1
        //  2552: dload           first_argument
        //  2554: invokestatic    java/lang/Math.exp:(D)D
        //  2557: dload           first_argument
        //  2559: dneg           
        //  2560: invokestatic    java/lang/Math.exp:(D)D
        //  2563: dadd           
        //  2564: dload           result
        //  2566: ddiv           
        //  2567: dastore        
        //  2568: goto            5398
        //  2571: iload           arguments_count
        //  2573: iconst_1       
        //  2574: if_icmpeq       2579
        //  2577: iconst_m1      
        //  2578: ireturn        
        //  2579: aload_1         /* stack */
        //  2580: iinc            stack_size, -1
        //  2583: iload_2         /* stack_size */
        //  2584: daload         
        //  2585: dstore          first_argument
        //  2587: aload_1         /* stack */
        //  2588: iload_2         /* stack_size */
        //  2589: iinc            stack_size, 1
        //  2592: dload           first_argument
        //  2594: dconst_1       
        //  2595: dload           first_argument
        //  2597: dload           first_argument
        //  2599: dmul           
        //  2600: dadd           
        //  2601: invokestatic    java/lang/Math.sqrt:(D)D
        //  2604: dadd           
        //  2605: invokestatic    java/lang/Math.log:(D)D
        //  2608: dastore        
        //  2609: goto            5398
        //  2612: iload           arguments_count
        //  2614: iconst_1       
        //  2615: if_icmpeq       2620
        //  2618: iconst_m1      
        //  2619: ireturn        
        //  2620: aload_1         /* stack */
        //  2621: iinc            stack_size, -1
        //  2624: iload_2         /* stack_size */
        //  2625: daload         
        //  2626: dstore          first_argument
        //  2628: dload           first_argument
        //  2630: dload           first_argument
        //  2632: dmul           
        //  2633: dconst_1       
        //  2634: dsub           
        //  2635: dstore          result
        //  2637: dconst_0       
        //  2638: dload           result
        //  2640: dcmpl          
        //  2641: ifle            2646
        //  2644: iconst_m1      
        //  2645: ireturn        
        //  2646: aload_1         /* stack */
        //  2647: iload_2         /* stack_size */
        //  2648: iinc            stack_size, 1
        //  2651: dload           first_argument
        //  2653: dload           result
        //  2655: invokestatic    java/lang/Math.sqrt:(D)D
        //  2658: dadd           
        //  2659: invokestatic    java/lang/Math.log:(D)D
        //  2662: dastore        
        //  2663: goto            5398
        //  2666: iload           arguments_count
        //  2668: iconst_1       
        //  2669: if_icmpeq       2674
        //  2672: iconst_m1      
        //  2673: ireturn        
        //  2674: aload_1         /* stack */
        //  2675: iinc            stack_size, -1
        //  2678: iload_2         /* stack_size */
        //  2679: daload         
        //  2680: dstore          first_argument
        //  2682: dconst_1       
        //  2683: dload           first_argument
        //  2685: dsub           
        //  2686: dconst_0       
        //  2687: dcmpg          
        //  2688: ifle            2700
        //  2691: ldc2_w          -1.0
        //  2694: dload           first_argument
        //  2696: dcmpl          
        //  2697: iflt            2702
        //  2700: iconst_m1      
        //  2701: ireturn        
        //  2702: dconst_1       
        //  2703: dload           first_argument
        //  2705: dadd           
        //  2706: dconst_1       
        //  2707: dload           first_argument
        //  2709: dsub           
        //  2710: ddiv           
        //  2711: dstore          result
        //  2713: dload           result
        //  2715: dconst_0       
        //  2716: dcmpg          
        //  2717: ifgt            2722
        //  2720: iconst_m1      
        //  2721: ireturn        
        //  2722: aload_1         /* stack */
        //  2723: iload_2         /* stack_size */
        //  2724: iinc            stack_size, 1
        //  2727: dload           result
        //  2729: invokestatic    java/lang/Math.sqrt:(D)D
        //  2732: invokestatic    java/lang/Math.log:(D)D
        //  2735: dastore        
        //  2736: goto            5398
        //  2739: iload           arguments_count
        //  2741: iconst_1       
        //  2742: if_icmpeq       2747
        //  2745: iconst_m1      
        //  2746: ireturn        
        //  2747: aload_1         /* stack */
        //  2748: iinc            stack_size, -1
        //  2751: iload_2         /* stack_size */
        //  2752: daload         
        //  2753: dstore          first_argument
        //  2755: dconst_0       
        //  2756: dload           first_argument
        //  2758: dcmpl          
        //  2759: ifne            2764
        //  2762: iconst_m1      
        //  2763: ireturn        
        //  2764: dload           first_argument
        //  2766: dconst_0       
        //  2767: dcmpg          
        //  2768: ifge            2794
        //  2771: dconst_1       
        //  2772: dconst_1       
        //  2773: dload           first_argument
        //  2775: dload           first_argument
        //  2777: dmul           
        //  2778: dadd           
        //  2779: invokestatic    java/lang/Math.sqrt:(D)D
        //  2782: dsub           
        //  2783: dload           first_argument
        //  2785: ddiv           
        //  2786: invokestatic    java/lang/Math.log:(D)D
        //  2789: dstore          result
        //  2791: goto            2814
        //  2794: dconst_1       
        //  2795: dconst_1       
        //  2796: dload           first_argument
        //  2798: dload           first_argument
        //  2800: dmul           
        //  2801: dadd           
        //  2802: invokestatic    java/lang/Math.sqrt:(D)D
        //  2805: dadd           
        //  2806: dload           first_argument
        //  2808: ddiv           
        //  2809: invokestatic    java/lang/Math.log:(D)D
        //  2812: dstore          result
        //  2814: aload_1         /* stack */
        //  2815: iload_2         /* stack_size */
        //  2816: iinc            stack_size, 1
        //  2819: dload           result
        //  2821: dastore        
        //  2822: goto            5398
        //  2825: iload           arguments_count
        //  2827: iconst_1       
        //  2828: if_icmpeq       2833
        //  2831: iconst_m1      
        //  2832: ireturn        
        //  2833: aload_1         /* stack */
        //  2834: iinc            stack_size, -1
        //  2837: iload_2         /* stack_size */
        //  2838: daload         
        //  2839: dstore          first_argument
        //  2841: dconst_1       
        //  2842: dload           first_argument
        //  2844: dload           first_argument
        //  2846: dmul           
        //  2847: dsub           
        //  2848: dstore          result
        //  2850: dload           result
        //  2852: dconst_0       
        //  2853: dcmpg          
        //  2854: iflt            2864
        //  2857: dload           first_argument
        //  2859: dconst_0       
        //  2860: dcmpg          
        //  2861: ifgt            2866
        //  2864: iconst_m1      
        //  2865: ireturn        
        //  2866: aload_1         /* stack */
        //  2867: iload_2         /* stack_size */
        //  2868: iinc            stack_size, 1
        //  2871: dconst_1       
        //  2872: dload           result
        //  2874: invokestatic    java/lang/Math.sqrt:(D)D
        //  2877: dadd           
        //  2878: dload           first_argument
        //  2880: ddiv           
        //  2881: invokestatic    java/lang/Math.log:(D)D
        //  2884: dastore        
        //  2885: goto            5398
        //  2888: iload           arguments_count
        //  2890: iconst_1       
        //  2891: if_icmpeq       2896
        //  2894: iconst_m1      
        //  2895: ireturn        
        //  2896: aload_1         /* stack */
        //  2897: iinc            stack_size, -1
        //  2900: iload_2         /* stack_size */
        //  2901: daload         
        //  2902: dstore          first_argument
        //  2904: dconst_1       
        //  2905: dload           first_argument
        //  2907: dsub           
        //  2908: dconst_0       
        //  2909: dcmpl          
        //  2910: ifne            2915
        //  2913: iconst_m1      
        //  2914: ireturn        
        //  2915: ldc2_w          -1.0
        //  2918: dload           first_argument
        //  2920: dsub           
        //  2921: dconst_1       
        //  2922: dload           first_argument
        //  2924: dsub           
        //  2925: ddiv           
        //  2926: dstore          result
        //  2928: dload           result
        //  2930: dconst_0       
        //  2931: dcmpg          
        //  2932: ifgt            2937
        //  2935: iconst_m1      
        //  2936: ireturn        
        //  2937: aload_1         /* stack */
        //  2938: iload_2         /* stack_size */
        //  2939: iinc            stack_size, 1
        //  2942: dload           result
        //  2944: invokestatic    java/lang/Math.sqrt:(D)D
        //  2947: invokestatic    java/lang/Math.log:(D)D
        //  2950: dastore        
        //  2951: goto            5398
        //  2954: iload           arguments_count
        //  2956: iconst_1       
        //  2957: if_icmpeq       2962
        //  2960: iconst_m1      
        //  2961: ireturn        
        //  2962: aload_1         /* stack */
        //  2963: iinc            stack_size, -1
        //  2966: iload_2         /* stack_size */
        //  2967: daload         
        //  2968: dstore          first_argument
        //  2970: dload           first_argument
        //  2972: ldc2_w          -1.0
        //  2975: dcmpg          
        //  2976: ifgt            2981
        //  2979: iconst_m1      
        //  2980: ireturn        
        //  2981: aload_1         /* stack */
        //  2982: iload_2         /* stack_size */
        //  2983: iinc            stack_size, 1
        //  2986: aload_0         /* this */
        //  2987: dload           first_argument
        //  2989: dconst_1       
        //  2990: dadd           
        //  2991: invokevirtual   Expression.gamma:(D)D
        //  2994: dastore        
        //  2995: goto            5398
        //  2998: iload           arguments_count
        //  3000: iconst_1       
        //  3001: if_icmpeq       3006
        //  3004: iconst_m1      
        //  3005: ireturn        
        //  3006: aload_1         /* stack */
        //  3007: iinc            stack_size, -1
        //  3010: iload_2         /* stack_size */
        //  3011: daload         
        //  3012: dstore          first_argument
        //  3014: aload_1         /* stack */
        //  3015: iload_2         /* stack_size */
        //  3016: iinc            stack_size, 1
        //  3019: aload_0         /* this */
        //  3020: dload           first_argument
        //  3022: invokevirtual   Expression.factorial2:(D)D
        //  3025: dastore        
        //  3026: goto            5398
        //  3029: iload           arguments_count
        //  3031: iconst_2       
        //  3032: if_icmpeq       3037
        //  3035: iconst_m1      
        //  3036: ireturn        
        //  3037: aload_1         /* stack */
        //  3038: iinc            stack_size, -1
        //  3041: iload_2         /* stack_size */
        //  3042: daload         
        //  3043: dstore          second_argument
        //  3045: aload_1         /* stack */
        //  3046: iinc            stack_size, -1
        //  3049: iload_2         /* stack_size */
        //  3050: daload         
        //  3051: dstore          first_argument
        //  3053: dload           first_argument
        //  3055: ldc2_w          -1.0
        //  3058: dcmpg          
        //  3059: ifle            3083
        //  3062: dload           second_argument
        //  3064: ldc2_w          -1.0
        //  3067: dcmpg          
        //  3068: ifle            3083
        //  3071: dload           first_argument
        //  3073: dload           second_argument
        //  3075: dsub           
        //  3076: ldc2_w          -1.0
        //  3079: dcmpg          
        //  3080: ifgt            3085
        //  3083: iconst_m1      
        //  3084: ireturn        
        //  3085: aload_1         /* stack */
        //  3086: iload_2         /* stack_size */
        //  3087: iinc            stack_size, 1
        //  3090: aload_0         /* this */
        //  3091: dload           first_argument
        //  3093: dconst_1       
        //  3094: dadd           
        //  3095: invokevirtual   Expression.logGamma:(D)D
        //  3098: aload_0         /* this */
        //  3099: dload           second_argument
        //  3101: dconst_1       
        //  3102: dadd           
        //  3103: invokevirtual   Expression.logGamma:(D)D
        //  3106: dsub           
        //  3107: aload_0         /* this */
        //  3108: dload           first_argument
        //  3110: dload           second_argument
        //  3112: dsub           
        //  3113: dconst_1       
        //  3114: dadd           
        //  3115: invokevirtual   Expression.logGamma:(D)D
        //  3118: dsub           
        //  3119: invokestatic    java/lang/Math.exp:(D)D
        //  3122: dastore        
        //  3123: goto            5398
        //  3126: iload           arguments_count
        //  3128: ifge            3133
        //  3131: iconst_m1      
        //  3132: ireturn        
        //  3133: dconst_0       
        //  3134: dstore          result
        //  3136: dconst_0       
        //  3137: dstore          second_argument
        //  3139: iconst_0       
        //  3140: istore          argument_index
        //  3142: iload           argument_index
        //  3144: iload           arguments_count
        //  3146: if_icmpge       3194
        //  3149: aload_1         /* stack */
        //  3150: iinc            stack_size, -1
        //  3153: iload_2         /* stack_size */
        //  3154: daload         
        //  3155: dstore          first_argument
        //  3157: dload           first_argument
        //  3159: ldc2_w          -1.0
        //  3162: dcmpg          
        //  3163: ifgt            3168
        //  3166: iconst_m1      
        //  3167: ireturn        
        //  3168: dload           second_argument
        //  3170: dload           first_argument
        //  3172: dadd           
        //  3173: dstore          second_argument
        //  3175: dload           result
        //  3177: aload_0         /* this */
        //  3178: dload           first_argument
        //  3180: dconst_1       
        //  3181: dadd           
        //  3182: invokevirtual   Expression.logGamma:(D)D
        //  3185: dadd           
        //  3186: dstore          result
        //  3188: iinc            argument_index, 1
        //  3191: goto            3142
        //  3194: dload           result
        //  3196: ldc2_w          -1.0
        //  3199: dcmpg          
        //  3200: ifgt            3205
        //  3203: iconst_m1      
        //  3204: ireturn        
        //  3205: aload_1         /* stack */
        //  3206: iload_2         /* stack_size */
        //  3207: iinc            stack_size, 1
        //  3210: aload_0         /* this */
        //  3211: dload           second_argument
        //  3213: dconst_1       
        //  3214: dadd           
        //  3215: invokevirtual   Expression.logGamma:(D)D
        //  3218: dload           result
        //  3220: dsub           
        //  3221: invokestatic    java/lang/Math.exp:(D)D
        //  3224: dastore        
        //  3225: goto            5398
        //  3228: iload           arguments_count
        //  3230: iconst_2       
        //  3231: if_icmpeq       3236
        //  3234: iconst_m1      
        //  3235: ireturn        
        //  3236: aload_1         /* stack */
        //  3237: iinc            stack_size, -1
        //  3240: iload_2         /* stack_size */
        //  3241: daload         
        //  3242: dstore          second_argument
        //  3244: aload_1         /* stack */
        //  3245: iinc            stack_size, -1
        //  3248: iload_2         /* stack_size */
        //  3249: daload         
        //  3250: dstore          first_argument
        //  3252: dload           first_argument
        //  3254: dload           second_argument
        //  3256: dadd           
        //  3257: ldc2_w          -1.0
        //  3260: dcmpg          
        //  3261: ifle            3273
        //  3264: dload           second_argument
        //  3266: ldc2_w          -1.0
        //  3269: dcmpg          
        //  3270: ifgt            3275
        //  3273: iconst_m1      
        //  3274: ireturn        
        //  3275: aload_1         /* stack */
        //  3276: iload_2         /* stack_size */
        //  3277: iinc            stack_size, 1
        //  3280: aload_0         /* this */
        //  3281: dload           first_argument
        //  3283: dload           second_argument
        //  3285: dadd           
        //  3286: dconst_1       
        //  3287: dadd           
        //  3288: invokevirtual   Expression.logGamma:(D)D
        //  3291: aload_0         /* this */
        //  3292: dload           second_argument
        //  3294: dconst_1       
        //  3295: dadd           
        //  3296: invokevirtual   Expression.logGamma:(D)D
        //  3299: dsub           
        //  3300: invokestatic    java/lang/Math.exp:(D)D
        //  3303: dastore        
        //  3304: goto            5398
        //  3307: iload           arguments_count
        //  3309: iconst_1       
        //  3310: if_icmpeq       3315
        //  3313: iconst_m1      
        //  3314: ireturn        
        //  3315: aload_1         /* stack */
        //  3316: iinc            stack_size, -1
        //  3319: iload_2         /* stack_size */
        //  3320: daload         
        //  3321: dstore          first_argument
        //  3323: aload_1         /* stack */
        //  3324: iload_2         /* stack_size */
        //  3325: iinc            stack_size, 1
        //  3328: aload_0         /* this */
        //  3329: dload           first_argument
        //  3331: invokevirtual   Expression.gamma:(D)D
        //  3334: dastore        
        //  3335: goto            5398
        //  3338: iload           arguments_count
        //  3340: iconst_1       
        //  3341: if_icmpeq       3346
        //  3344: iconst_m1      
        //  3345: ireturn        
        //  3346: aload_1         /* stack */
        //  3347: iinc            stack_size, -1
        //  3350: iload_2         /* stack_size */
        //  3351: daload         
        //  3352: dstore          first_argument
        //  3354: aload_1         /* stack */
        //  3355: iload_2         /* stack_size */
        //  3356: iinc            stack_size, 1
        //  3359: aload_0         /* this */
        //  3360: dload           first_argument
        //  3362: invokevirtual   Expression.logGamma:(D)D
        //  3365: dastore        
        //  3366: goto            5398
        //  3369: iload           arguments_count
        //  3371: iconst_2       
        //  3372: if_icmpeq       3377
        //  3375: iconst_m1      
        //  3376: ireturn        
        //  3377: aload_1         /* stack */
        //  3378: iinc            stack_size, -1
        //  3381: iload_2         /* stack_size */
        //  3382: daload         
        //  3383: dstore          second_argument
        //  3385: aload_1         /* stack */
        //  3386: iinc            stack_size, -1
        //  3389: iload_2         /* stack_size */
        //  3390: daload         
        //  3391: dstore          first_argument
        //  3393: aload_0         /* this */
        //  3394: dload           first_argument
        //  3396: dload           second_argument
        //  3398: dadd           
        //  3399: invokevirtual   Expression.gamma:(D)D
        //  3402: dstore          result
        //  3404: dconst_0       
        //  3405: dload           result
        //  3407: dcmpl          
        //  3408: ifne            3413
        //  3411: iconst_m1      
        //  3412: ireturn        
        //  3413: aload_1         /* stack */
        //  3414: iload_2         /* stack_size */
        //  3415: iinc            stack_size, 1
        //  3418: aload_0         /* this */
        //  3419: dload           first_argument
        //  3421: invokevirtual   Expression.gamma:(D)D
        //  3424: aload_0         /* this */
        //  3425: dload           second_argument
        //  3427: invokevirtual   Expression.gamma:(D)D
        //  3430: dmul           
        //  3431: dload           result
        //  3433: ddiv           
        //  3434: dastore        
        //  3435: goto            5398
        //  3438: iload           arguments_count
        //  3440: iconst_3       
        //  3441: if_icmpeq       3446
        //  3444: iconst_m1      
        //  3445: ireturn        
        //  3446: aload_1         /* stack */
        //  3447: iinc            stack_size, -1
        //  3450: iload_2         /* stack_size */
        //  3451: daload         
        //  3452: dstore          second_argument
        //  3454: aload_1         /* stack */
        //  3455: iinc            stack_size, -1
        //  3458: iload_2         /* stack_size */
        //  3459: daload         
        //  3460: dstore          result
        //  3462: aload_1         /* stack */
        //  3463: iinc            stack_size, -1
        //  3466: iload_2         /* stack_size */
        //  3467: daload         
        //  3468: dstore          first_argument
        //  3470: dconst_0       
        //  3471: dload           second_argument
        //  3473: dcmpl          
        //  3474: ifeq            3484
        //  3477: dconst_0       
        //  3478: dload           result
        //  3480: dcmpl          
        //  3481: ifle            3486
        //  3484: iconst_m1      
        //  3485: ireturn        
        //  3486: dload           first_argument
        //  3488: dload           result
        //  3490: invokestatic    java/lang/Math.pow:(DD)D
        //  3493: dstore          first_argument
        //  3495: aload_1         /* stack */
        //  3496: iload_2         /* stack_size */
        //  3497: iinc            stack_size, 1
        //  3500: dload           first_argument
        //  3502: dload           second_argument
        //  3504: dload           first_argument
        //  3506: dload           second_argument
        //  3508: ddiv           
        //  3509: invokestatic    java/lang/Math.floor:(D)D
        //  3512: dmul           
        //  3513: dsub           
        //  3514: dastore        
        //  3515: goto            5398
        //  3518: iload           arguments_count
        //  3520: iconst_2       
        //  3521: if_icmpne       3562
        //  3524: aload_1         /* stack */
        //  3525: iinc            stack_size, -1
        //  3528: iload_2         /* stack_size */
        //  3529: daload         
        //  3530: dstore          second_argument
        //  3532: aload_1         /* stack */
        //  3533: iinc            stack_size, -1
        //  3536: iload_2         /* stack_size */
        //  3537: daload         
        //  3538: dstore          first_argument
        //  3540: aload_1         /* stack */
        //  3541: iload_2         /* stack_size */
        //  3542: iinc            stack_size, 1
        //  3545: aload_0         /* this */
        //  3546: dload           second_argument
        //  3548: invokevirtual   Expression.erf:(D)D
        //  3551: aload_0         /* this */
        //  3552: dload           first_argument
        //  3554: invokevirtual   Expression.erf:(D)D
        //  3557: dsub           
        //  3558: dastore        
        //  3559: goto            5398
        //  3562: iload           arguments_count
        //  3564: iconst_1       
        //  3565: if_icmpne       3591
        //  3568: aload_1         /* stack */
        //  3569: iinc            stack_size, -1
        //  3572: iload_2         /* stack_size */
        //  3573: daload         
        //  3574: dstore          first_argument
        //  3576: aload_1         /* stack */
        //  3577: iload_2         /* stack_size */
        //  3578: iinc            stack_size, 1
        //  3581: aload_0         /* this */
        //  3582: dload           first_argument
        //  3584: invokevirtual   Expression.erf:(D)D
        //  3587: dastore        
        //  3588: goto            5398
        //  3591: iconst_m1      
        //  3592: ireturn        
        //  3593: iload           arguments_count
        //  3595: iconst_1       
        //  3596: if_icmpeq       3601
        //  3599: iconst_m1      
        //  3600: ireturn        
        //  3601: aload_1         /* stack */
        //  3602: iinc            stack_size, -1
        //  3605: iload_2         /* stack_size */
        //  3606: daload         
        //  3607: dstore          first_argument
        //  3609: aload_1         /* stack */
        //  3610: iload_2         /* stack_size */
        //  3611: iinc            stack_size, 1
        //  3614: dconst_1       
        //  3615: aload_0         /* this */
        //  3616: dload           first_argument
        //  3618: invokevirtual   Expression.erf:(D)D
        //  3621: dsub           
        //  3622: dastore        
        //  3623: goto            5398
        //  3626: iload           arguments_count
        //  3628: iconst_1       
        //  3629: if_icmpeq       3634
        //  3632: iconst_m1      
        //  3633: ireturn        
        //  3634: aload_1         /* stack */
        //  3635: iinc            stack_size, -1
        //  3638: iload_2         /* stack_size */
        //  3639: daload         
        //  3640: dstore          first_argument
        //  3642: aload_1         /* stack */
        //  3643: iload_2         /* stack_size */
        //  3644: iinc            stack_size, 1
        //  3647: aload_0         /* this */
        //  3648: dload           first_argument
        //  3650: invokevirtual   Expression.erfi:(D)D
        //  3653: dastore        
        //  3654: goto            5398
        //  3657: iconst_1       
        //  3658: iload           arguments_count
        //  3660: if_icmpne       3679
        //  3663: ldc2_w          1.0E-4
        //  3666: dstore          second_argument
        //  3668: aload_1         /* stack */
        //  3669: iinc            stack_size, -1
        //  3672: iload_2         /* stack_size */
        //  3673: daload         
        //  3674: dstore          first_argument
        //  3676: goto            3712
        //  3679: iconst_2       
        //  3680: iload           arguments_count
        //  3682: if_icmpne       3710
        //  3685: aload_1         /* stack */
        //  3686: iinc            stack_size, -1
        //  3689: iload_2         /* stack_size */
        //  3690: daload         
        //  3691: dstore          second_argument
        //  3693: aload_1         /* stack */
        //  3694: iinc            stack_size, -1
        //  3697: iload_2         /* stack_size */
        //  3698: daload         
        //  3699: dstore          first_argument
        //  3701: dload           second_argument
        //  3703: dconst_0       
        //  3704: dcmpg          
        //  3705: ifge            3712
        //  3708: iconst_m1      
        //  3709: ireturn        
        //  3710: iconst_m1      
        //  3711: ireturn        
        //  3712: aload_1         /* stack */
        //  3713: iinc            stack_size, -1
        //  3716: iload_2         /* stack_size */
        //  3717: daload         
        //  3718: dstore          first_argument
        //  3720: dload           first_argument
        //  3722: ldc2_w          12600.0
        //  3725: dmul           
        //  3726: ldc2_w          0.5
        //  3729: dadd           
        //  3730: invokestatic    java/lang/Math.floor:(D)D
        //  3733: dstore          result
        //  3735: dload           result
        //  3737: dload           first_argument
        //  3739: dsub           
        //  3740: invokestatic    java/lang/Math.abs:(D)D
        //  3743: dload           9
        //  3745: ldc2_w          10.0
        //  3748: dmul           
        //  3749: dcmpg          
        //  3750: ifge            3757
        //  3753: dload           first_argument
        //  3755: dstore          result
        //  3757: aload_1         /* stack */
        //  3758: iload_2         /* stack_size */
        //  3759: iinc            stack_size, 1
        //  3762: dload           result
        //  3764: dastore        
        //  3765: goto            5398
        //  3768: iload_2         /* stack_size */
        //  3769: iload           arguments_count
        //  3771: if_icmpge       3776
        //  3774: iconst_m1      
        //  3775: ireturn        
        //  3776: dconst_1       
        //  3777: dstore          result
        //  3779: iconst_0       
        //  3780: istore          argument_index
        //  3782: iload           argument_index
        //  3784: iload           arguments_count
        //  3786: iconst_1       
        //  3787: isub           
        //  3788: if_icmpge       3956
        //  3791: aload_1         /* stack */
        //  3792: iload_2         /* stack_size */
        //  3793: iload           argument_index
        //  3795: isub           
        //  3796: iconst_2       
        //  3797: isub           
        //  3798: daload         
        //  3799: dstore          first_argument
        //  3801: aload_1         /* stack */
        //  3802: iload_2         /* stack_size */
        //  3803: iload           argument_index
        //  3805: isub           
        //  3806: iconst_1       
        //  3807: isub           
        //  3808: daload         
        //  3809: dstore          second_argument
        //  3811: iload           function_index
        //  3813: tableswitch {
        //              128: 3852
        //              129: 3866
        //              130: 3880
        //              131: 3908
        //              132: 3894
        //              133: 3922
        //          default: 3936
        //        }
        //  3852: dload           first_argument
        //  3854: dload           second_argument
        //  3856: dcmpl          
        //  3857: ifeq            3938
        //  3860: dconst_0       
        //  3861: dstore          result
        //  3863: goto            3938
        //  3866: dload           first_argument
        //  3868: dload           second_argument
        //  3870: dcmpl          
        //  3871: ifne            3938
        //  3874: dconst_0       
        //  3875: dstore          result
        //  3877: goto            3938
        //  3880: dload           first_argument
        //  3882: dload           second_argument
        //  3884: dcmpg          
        //  3885: iflt            3938
        //  3888: dconst_0       
        //  3889: dstore          result
        //  3891: goto            3938
        //  3894: dload           first_argument
        //  3896: dload           second_argument
        //  3898: dcmpg          
        //  3899: ifle            3938
        //  3902: dconst_0       
        //  3903: dstore          result
        //  3905: goto            3938
        //  3908: dload           first_argument
        //  3910: dload           second_argument
        //  3912: dcmpl          
        //  3913: ifgt            3938
        //  3916: dconst_0       
        //  3917: dstore          result
        //  3919: goto            3938
        //  3922: dload           first_argument
        //  3924: dload           second_argument
        //  3926: dcmpl          
        //  3927: ifge            3938
        //  3930: dconst_0       
        //  3931: dstore          result
        //  3933: goto            3938
        //  3936: iconst_m1      
        //  3937: ireturn        
        //  3938: dload           result
        //  3940: ldc2_w          0.5
        //  3943: dcmpg          
        //  3944: ifge            3950
        //  3947: goto            3956
        //  3950: iinc            argument_index, 1
        //  3953: goto            3782
        //  3956: iload_2         /* stack_size */
        //  3957: iload           arguments_count
        //  3959: isub           
        //  3960: istore_2        /* stack_size */
        //  3961: aload_1         /* stack */
        //  3962: iload_2         /* stack_size */
        //  3963: iinc            stack_size, 1
        //  3966: dload           result
        //  3968: dastore        
        //  3969: goto            5398
        //  3972: iload           arguments_count
        //  3974: iconst_1       
        //  3975: if_icmpeq       3980
        //  3978: iconst_m1      
        //  3979: ireturn        
        //  3980: aload_1         /* stack */
        //  3981: iinc            stack_size, -1
        //  3984: iload_2         /* stack_size */
        //  3985: daload         
        //  3986: dstore          first_argument
        //  3988: aload_1         /* stack */
        //  3989: iload_2         /* stack_size */
        //  3990: iinc            stack_size, 1
        //  3993: dconst_1       
        //  3994: dastore        
        //  3995: goto            5398
        //  3998: iload           arguments_count
        //  4000: iconst_1       
        //  4001: if_icmpeq       4006
        //  4004: iconst_m1      
        //  4005: ireturn        
        //  4006: aload_1         /* stack */
        //  4007: iinc            stack_size, -1
        //  4010: iload_2         /* stack_size */
        //  4011: daload         
        //  4012: dstore          first_argument
        //  4014: dload           first_argument
        //  4016: dload           first_argument
        //  4018: ldc2_w          0.5
        //  4021: dadd           
        //  4022: invokestatic    java/lang/Math.floor:(D)D
        //  4025: dcmpl          
        //  4026: ifne            4039
        //  4029: aload_1         /* stack */
        //  4030: iload_2         /* stack_size */
        //  4031: iinc            stack_size, 1
        //  4034: dconst_1       
        //  4035: dastore        
        //  4036: goto            5398
        //  4039: aload_1         /* stack */
        //  4040: iload_2         /* stack_size */
        //  4041: iinc            stack_size, 1
        //  4044: dconst_0       
        //  4045: dastore        
        //  4046: goto            5398
        //  4049: iload           arguments_count
        //  4051: iconst_1       
        //  4052: if_icmpeq       4057
        //  4055: iconst_m1      
        //  4056: ireturn        
        //  4057: aload_1         /* stack */
        //  4058: iinc            stack_size, -1
        //  4061: iload_2         /* stack_size */
        //  4062: daload         
        //  4063: dstore          first_argument
        //  4065: dload           first_argument
        //  4067: ldc2_w          0.5
        //  4070: dadd           
        //  4071: invokestatic    java/lang/Math.floor:(D)D
        //  4074: dstore          result
        //  4076: dload           result
        //  4078: dload           first_argument
        //  4080: dcmpl          
        //  4081: ifne            4103
        //  4084: dload           result
        //  4086: dload           result
        //  4088: ldc2_w          2.0
        //  4091: ddiv           
        //  4092: ldc2_w          0.5
        //  4095: dadd           
        //  4096: invokestatic    java/lang/Math.floor:(D)D
        //  4099: dcmpl          
        //  4100: ifeq            4113
        //  4103: aload_1         /* stack */
        //  4104: iload_2         /* stack_size */
        //  4105: iinc            stack_size, 1
        //  4108: dconst_0       
        //  4109: dastore        
        //  4110: goto            5398
        //  4113: aload_1         /* stack */
        //  4114: iload_2         /* stack_size */
        //  4115: iinc            stack_size, 1
        //  4118: dconst_1       
        //  4119: dastore        
        //  4120: goto            5398
        //  4123: iload           arguments_count
        //  4125: iconst_1       
        //  4126: if_icmpeq       4131
        //  4129: iconst_m1      
        //  4130: ireturn        
        //  4131: aload_1         /* stack */
        //  4132: iinc            stack_size, -1
        //  4135: iload_2         /* stack_size */
        //  4136: daload         
        //  4137: dstore          first_argument
        //  4139: dload           first_argument
        //  4141: ldc2_w          0.5
        //  4144: dadd           
        //  4145: invokestatic    java/lang/Math.floor:(D)D
        //  4148: dstore          result
        //  4150: dload           result
        //  4152: dload           first_argument
        //  4154: dcmpl          
        //  4155: ifne            4177
        //  4158: dload           result
        //  4160: dload           result
        //  4162: ldc2_w          2.0
        //  4165: ddiv           
        //  4166: ldc2_w          0.5
        //  4169: dadd           
        //  4170: invokestatic    java/lang/Math.floor:(D)D
        //  4173: dcmpl          
        //  4174: ifne            4187
        //  4177: aload_1         /* stack */
        //  4178: iload_2         /* stack_size */
        //  4179: iinc            stack_size, 1
        //  4182: dconst_0       
        //  4183: dastore        
        //  4184: goto            5398
        //  4187: aload_1         /* stack */
        //  4188: iload_2         /* stack_size */
        //  4189: iinc            stack_size, 1
        //  4192: dconst_1       
        //  4193: dastore        
        //  4194: goto            5398
        //  4197: iload           arguments_count
        //  4199: iconst_1       
        //  4200: if_icmpeq       4205
        //  4203: iconst_m1      
        //  4204: ireturn        
        //  4205: aload_1         /* stack */
        //  4206: iinc            stack_size, -1
        //  4209: iload_2         /* stack_size */
        //  4210: daload         
        //  4211: dstore          first_argument
        //  4213: dload           first_argument
        //  4215: dconst_0       
        //  4216: dcmpl          
        //  4217: ifle            4230
        //  4220: aload_1         /* stack */
        //  4221: iload_2         /* stack_size */
        //  4222: iinc            stack_size, 1
        //  4225: dconst_1       
        //  4226: dastore        
        //  4227: goto            5398
        //  4230: aload_1         /* stack */
        //  4231: iload_2         /* stack_size */
        //  4232: iinc            stack_size, 1
        //  4235: dconst_0       
        //  4236: dastore        
        //  4237: goto            5398
        //  4240: iload           arguments_count
        //  4242: iconst_1       
        //  4243: if_icmpeq       4248
        //  4246: iconst_m1      
        //  4247: ireturn        
        //  4248: aload_1         /* stack */
        //  4249: iinc            stack_size, -1
        //  4252: iload_2         /* stack_size */
        //  4253: daload         
        //  4254: dstore          first_argument
        //  4256: dload           first_argument
        //  4258: dconst_0       
        //  4259: dcmpg          
        //  4260: ifge            4273
        //  4263: aload_1         /* stack */
        //  4264: iload_2         /* stack_size */
        //  4265: iinc            stack_size, 1
        //  4268: dconst_1       
        //  4269: dastore        
        //  4270: goto            5398
        //  4273: aload_1         /* stack */
        //  4274: iload_2         /* stack_size */
        //  4275: iinc            stack_size, 1
        //  4278: dconst_0       
        //  4279: dastore        
        //  4280: goto            5398
        //  4283: iload           arguments_count
        //  4285: iconst_1       
        //  4286: if_icmpeq       4291
        //  4289: iconst_m1      
        //  4290: ireturn        
        //  4291: aload_1         /* stack */
        //  4292: iinc            stack_size, -1
        //  4295: iload_2         /* stack_size */
        //  4296: daload         
        //  4297: dstore          first_argument
        //  4299: dload           first_argument
        //  4301: dconst_0       
        //  4302: dcmpg          
        //  4303: ifgt            4316
        //  4306: aload_1         /* stack */
        //  4307: iload_2         /* stack_size */
        //  4308: iinc            stack_size, 1
        //  4311: dconst_1       
        //  4312: dastore        
        //  4313: goto            5398
        //  4316: aload_1         /* stack */
        //  4317: iload_2         /* stack_size */
        //  4318: iinc            stack_size, 1
        //  4321: dconst_0       
        //  4322: dastore        
        //  4323: goto            5398
        //  4326: iload           arguments_count
        //  4328: iconst_1       
        //  4329: if_icmpeq       4334
        //  4332: iconst_m1      
        //  4333: ireturn        
        //  4334: aload_1         /* stack */
        //  4335: iinc            stack_size, -1
        //  4338: iload_2         /* stack_size */
        //  4339: daload         
        //  4340: dstore          first_argument
        //  4342: dload           first_argument
        //  4344: dconst_0       
        //  4345: dcmpl          
        //  4346: iflt            4359
        //  4349: aload_1         /* stack */
        //  4350: iload_2         /* stack_size */
        //  4351: iinc            stack_size, 1
        //  4354: dconst_1       
        //  4355: dastore        
        //  4356: goto            5398
        //  4359: aload_1         /* stack */
        //  4360: iload_2         /* stack_size */
        //  4361: iinc            stack_size, 1
        //  4364: dconst_0       
        //  4365: dastore        
        //  4366: goto            5398
        //  4369: iload           arguments_count
        //  4371: iconst_1       
        //  4372: if_icmpeq       4377
        //  4375: iconst_m1      
        //  4376: ireturn        
        //  4377: aload_1         /* stack */
        //  4378: iinc            stack_size, -1
        //  4381: iload_2         /* stack_size */
        //  4382: daload         
        //  4383: dstore          first_argument
        //  4385: dload           first_argument
        //  4387: dconst_1       
        //  4388: dcmpl          
        //  4389: ifne            4402
        //  4392: aload_1         /* stack */
        //  4393: iload_2         /* stack_size */
        //  4394: iinc            stack_size, 1
        //  4397: dconst_1       
        //  4398: dastore        
        //  4399: goto            5398
        //  4402: aload_1         /* stack */
        //  4403: iload_2         /* stack_size */
        //  4404: iinc            stack_size, 1
        //  4407: dconst_0       
        //  4408: dastore        
        //  4409: goto            5398
        //  4412: iload           arguments_count
        //  4414: iconst_1       
        //  4415: if_icmpeq       4420
        //  4418: iconst_m1      
        //  4419: ireturn        
        //  4420: aload_1         /* stack */
        //  4421: iinc            stack_size, -1
        //  4424: iload_2         /* stack_size */
        //  4425: daload         
        //  4426: dstore          first_argument
        //  4428: dload           first_argument
        //  4430: dconst_1       
        //  4431: dcmpl          
        //  4432: ifne            4445
        //  4435: aload_1         /* stack */
        //  4436: iload_2         /* stack_size */
        //  4437: iinc            stack_size, 1
        //  4440: dconst_0       
        //  4441: dastore        
        //  4442: goto            5398
        //  4445: dload           first_argument
        //  4447: dconst_0       
        //  4448: dcmpl          
        //  4449: ifne            4462
        //  4452: aload_1         /* stack */
        //  4453: iload_2         /* stack_size */
        //  4454: iinc            stack_size, 1
        //  4457: dconst_1       
        //  4458: dastore        
        //  4459: goto            5398
        //  4462: iconst_m1      
        //  4463: ireturn        
        //  4464: iload           arguments_count
        //  4466: iconst_2       
        //  4467: if_icmpeq       4472
        //  4470: iconst_m1      
        //  4471: ireturn        
        //  4472: aload_1         /* stack */
        //  4473: iinc            stack_size, -1
        //  4476: iload_2         /* stack_size */
        //  4477: daload         
        //  4478: dstore          second_argument
        //  4480: aload_1         /* stack */
        //  4481: iinc            stack_size, -1
        //  4484: iload_2         /* stack_size */
        //  4485: daload         
        //  4486: dstore          first_argument
        //  4488: dconst_1       
        //  4489: dload           first_argument
        //  4491: dcmpl          
        //  4492: ifne            4506
        //  4495: aload_1         /* stack */
        //  4496: iload_2         /* stack_size */
        //  4497: iinc            stack_size, 1
        //  4500: dload           second_argument
        //  4502: dastore        
        //  4503: goto            5398
        //  4506: dconst_0       
        //  4507: dload           first_argument
        //  4509: dcmpl          
        //  4510: ifne            4523
        //  4513: aload_1         /* stack */
        //  4514: iload_2         /* stack_size */
        //  4515: iinc            stack_size, 1
        //  4518: dconst_1       
        //  4519: dastore        
        //  4520: goto            5398
        //  4523: iconst_m1      
        //  4524: ireturn        
        //  4525: iload_2         /* stack_size */
        //  4526: iload           arguments_count
        //  4528: if_icmpge       4533
        //  4531: iconst_m1      
        //  4532: ireturn        
        //  4533: bipush          82
        //  4535: iload           function_index
        //  4537: if_icmpne       4546
        //  4540: dconst_1       
        //  4541: dstore          result
        //  4543: goto            4549
        //  4546: dconst_0       
        //  4547: dstore          result
        //  4549: iconst_0       
        //  4550: istore          argument_index
        //  4552: iload           argument_index
        //  4554: iload           arguments_count
        //  4556: if_icmpge       4673
        //  4559: aload_1         /* stack */
        //  4560: iload_2         /* stack_size */
        //  4561: iload           argument_index
        //  4563: isub           
        //  4564: iconst_1       
        //  4565: isub           
        //  4566: daload         
        //  4567: dstore          first_argument
        //  4569: iload           function_index
        //  4571: tableswitch {
        //              164: 4596
        //              165: 4618
        //              166: 4640
        //          default: 4665
        //        }
        //  4596: dconst_0       
        //  4597: dload           first_argument
        //  4599: dcmpl          
        //  4600: ifne            4609
        //  4603: dconst_0       
        //  4604: dstore          result
        //  4606: goto            4667
        //  4609: dconst_1       
        //  4610: dload           first_argument
        //  4612: dcmpl          
        //  4613: ifeq            4667
        //  4616: iconst_m1      
        //  4617: ireturn        
        //  4618: dconst_1       
        //  4619: dload           first_argument
        //  4621: dcmpl          
        //  4622: ifne            4631
        //  4625: dconst_1       
        //  4626: dstore          result
        //  4628: goto            4667
        //  4631: dconst_0       
        //  4632: dload           first_argument
        //  4634: dcmpl          
        //  4635: ifeq            4667
        //  4638: iconst_m1      
        //  4639: ireturn        
        //  4640: dconst_1       
        //  4641: dload           first_argument
        //  4643: dcmpl          
        //  4644: ifne            4656
        //  4647: dconst_1       
        //  4648: dload           result
        //  4650: dsub           
        //  4651: dstore          result
        //  4653: goto            4667
        //  4656: dconst_0       
        //  4657: dload           first_argument
        //  4659: dcmpl          
        //  4660: ifeq            4667
        //  4663: iconst_m1      
        //  4664: ireturn        
        //  4665: iconst_m1      
        //  4666: ireturn        
        //  4667: iinc            argument_index, 1
        //  4670: goto            4552
        //  4673: iload_2         /* stack_size */
        //  4674: iload           arguments_count
        //  4676: isub           
        //  4677: istore_2        /* stack_size */
        //  4678: aload_1         /* stack */
        //  4679: iload_2         /* stack_size */
        //  4680: iinc            stack_size, 1
        //  4683: dload           result
        //  4685: dastore        
        //  4686: goto            5398
        //  4689: iload           arguments_count
        //  4691: iconst_2       
        //  4692: if_icmplt       4701
        //  4695: iload           arguments_count
        //  4697: iconst_4       
        //  4698: if_icmple       4703
        //  4701: iconst_m1      
        //  4702: ireturn        
        //  4703: aload_1         /* stack */
        //  4704: iload_2         /* stack_size */
        //  4705: iload           arguments_count
        //  4707: isub           
        //  4708: daload         
        //  4709: dstore          first_argument
        //  4711: dconst_1       
        //  4712: dload           first_argument
        //  4714: dcmpl          
        //  4715: ifne            4731
        //  4718: aload_1         /* stack */
        //  4719: iload_2         /* stack_size */
        //  4720: iload           arguments_count
        //  4722: isub           
        //  4723: iconst_1       
        //  4724: iadd           
        //  4725: daload         
        //  4726: dstore          result
        //  4728: goto            4780
        //  4731: dconst_0       
        //  4732: dload           first_argument
        //  4734: dcmpl          
        //  4735: ifne            4759
        //  4738: iload           arguments_count
        //  4740: iconst_2       
        //  4741: if_icmple       4757
        //  4744: aload_1         /* stack */
        //  4745: iload_2         /* stack_size */
        //  4746: iload           arguments_count
        //  4748: isub           
        //  4749: iconst_2       
        //  4750: iadd           
        //  4751: daload         
        //  4752: dstore          result
        //  4754: goto            4780
        //  4757: iconst_m1      
        //  4758: ireturn        
        //  4759: iload           arguments_count
        //  4761: iconst_3       
        //  4762: if_icmple       4778
        //  4765: aload_1         /* stack */
        //  4766: iload_2         /* stack_size */
        //  4767: iload           arguments_count
        //  4769: isub           
        //  4770: iconst_3       
        //  4771: iadd           
        //  4772: daload         
        //  4773: dstore          result
        //  4775: goto            4780
        //  4778: iconst_m1      
        //  4779: ireturn        
        //  4780: iload_2         /* stack_size */
        //  4781: iload           arguments_count
        //  4783: isub           
        //  4784: istore_2        /* stack_size */
        //  4785: aload_1         /* stack */
        //  4786: iload_2         /* stack_size */
        //  4787: iinc            stack_size, 1
        //  4790: dload           11
        //  4792: dastore        
        //  4793: goto            5398
        //  4796: iload           arguments_count
        //  4798: iconst_1       
        //  4799: iand           
        //  4800: iconst_1       
        //  4801: if_icmpne       4806
        //  4804: iconst_m1      
        //  4805: ireturn        
        //  4806: dconst_0       
        //  4807: dstore          result
        //  4809: iconst_0       
        //  4810: istore          argument_index
        //  4812: iload           argument_index
        //  4814: iload           arguments_count
        //  4816: if_icmpge       4872
        //  4819: aload_1         /* stack */
        //  4820: iload_2         /* stack_size */
        //  4821: iload           arguments_count
        //  4823: isub           
        //  4824: iconst_1       
        //  4825: iadd           
        //  4826: iload           argument_index
        //  4828: iadd           
        //  4829: daload         
        //  4830: dstore          second_argument
        //  4832: aload_1         /* stack */
        //  4833: iload_2         /* stack_size */
        //  4834: iload           arguments_count
        //  4836: isub           
        //  4837: iload           argument_index
        //  4839: iadd           
        //  4840: daload         
        //  4841: dstore          first_argument
        //  4843: dconst_1       
        //  4844: dload           first_argument
        //  4846: dcmpl          
        //  4847: ifne            4857
        //  4850: dload           second_argument
        //  4852: dstore          result
        //  4854: goto            4872
        //  4857: dconst_0       
        //  4858: dload           first_argument
        //  4860: dcmpl          
        //  4861: ifeq            4866
        //  4864: iconst_m1      
        //  4865: ireturn        
        //  4866: iinc            argument_index, 2
        //  4869: goto            4812
        //  4872: iload           argument_index
        //  4874: iload           arguments_count
        //  4876: if_icmplt       4881
        //  4879: iconst_m1      
        //  4880: ireturn        
        //  4881: iload_2         /* stack_size */
        //  4882: iload           arguments_count
        //  4884: isub           
        //  4885: istore_2        /* stack_size */
        //  4886: aload_1         /* stack */
        //  4887: iload_2         /* stack_size */
        //  4888: iinc            stack_size, 1
        //  4891: dload           result
        //  4893: dastore        
        //  4894: goto            5398
        //  4897: iload           arguments_count
        //  4899: iconst_1       
        //  4900: iand           
        //  4901: ifne            4906
        //  4904: iconst_m1      
        //  4905: ireturn        
        //  4906: aload_1         /* stack */
        //  4907: iload_2         /* stack_size */
        //  4908: iload           arguments_count
        //  4910: isub           
        //  4911: daload         
        //  4912: dstore          result
        //  4914: iconst_0       
        //  4915: istore          argument_index
        //  4917: iload           argument_index
        //  4919: iload           arguments_count
        //  4921: iconst_1       
        //  4922: isub           
        //  4923: if_icmpge       4973
        //  4926: aload_1         /* stack */
        //  4927: iload_2         /* stack_size */
        //  4928: iload           arguments_count
        //  4930: isub           
        //  4931: iconst_2       
        //  4932: iadd           
        //  4933: iload           argument_index
        //  4935: iadd           
        //  4936: daload         
        //  4937: dstore          second_argument
        //  4939: aload_1         /* stack */
        //  4940: iload_2         /* stack_size */
        //  4941: iload           arguments_count
        //  4943: isub           
        //  4944: iconst_1       
        //  4945: iadd           
        //  4946: iload           argument_index
        //  4948: iadd           
        //  4949: daload         
        //  4950: dstore          first_argument
        //  4952: dload           result
        //  4954: dload           first_argument
        //  4956: dcmpl          
        //  4957: ifne            4967
        //  4960: dload           second_argument
        //  4962: dstore          result
        //  4964: goto            4973
        //  4967: iinc            argument_index, 2
        //  4970: goto            4917
        //  4973: iload           argument_index
        //  4975: iload           arguments_count
        //  4977: iconst_1       
        //  4978: isub           
        //  4979: if_icmplt       4984
        //  4982: iconst_m1      
        //  4983: ireturn        
        //  4984: iload_2         /* stack_size */
        //  4985: iload           arguments_count
        //  4987: isub           
        //  4988: istore_2        /* stack_size */
        //  4989: aload_1         /* stack */
        //  4990: iload_2         /* stack_size */
        //  4991: iinc            stack_size, 1
        //  4994: dload           result
        //  4996: dastore        
        //  4997: goto            5398
        //  5000: iload           arguments_count
        //  5002: iconst_1       
        //  5003: if_icmpeq       5398
        //  5006: iconst_m1      
        //  5007: ireturn        
        //  5008: iconst_1       
        //  5009: iload           arguments_count
        //  5011: if_icmpne       5025
        //  5014: aload_1         /* stack */
        //  5015: iinc            stack_size, -1
        //  5018: iload_2         /* stack_size */
        //  5019: daload         
        //  5020: dstore          first_argument
        //  5022: goto            5052
        //  5025: iconst_2       
        //  5026: iload           arguments_count
        //  5028: if_icmpne       5050
        //  5031: aload_1         /* stack */
        //  5032: iinc            stack_size, -1
        //  5035: iload_2         /* stack_size */
        //  5036: daload         
        //  5037: dstore          second_argument
        //  5039: aload_1         /* stack */
        //  5040: iinc            stack_size, -1
        //  5043: iload_2         /* stack_size */
        //  5044: daload         
        //  5045: dstore          first_argument
        //  5047: goto            5052
        //  5050: iconst_m1      
        //  5051: ireturn        
        //  5052: aload_1         /* stack */
        //  5053: iload_2         /* stack_size */
        //  5054: iinc            stack_size, 1
        //  5057: dload           7
        //  5059: dastore        
        //  5060: goto            5398
        //  5063: iload           arguments_count
        //  5065: ifeq            5070
        //  5068: iconst_m1      
        //  5069: ireturn        
        //  5070: invokestatic    java/lang/System.currentTimeMillis:()J
        //  5073: l2d            
        //  5074: ldc2_w          1000.0
        //  5077: ddiv           
        //  5078: dstore          result
        //  5080: aload_1         /* stack */
        //  5081: iload_2         /* stack_size */
        //  5082: iinc            stack_size, 1
        //  5085: dload           result
        //  5087: aload_0         /* this */
        //  5088: getfield        Expression.evaluator:LEvaluator;
        //  5091: getfield        Evaluator.seconds_since_1970:D
        //  5094: dsub           
        //  5095: dastore        
        //  5096: goto            5398
        //  5099: iload           arguments_count
        //  5101: ifeq            5106
        //  5104: iconst_m1      
        //  5105: ireturn        
        //  5106: aload_1         /* stack */
        //  5107: iload_2         /* stack_size */
        //  5108: iinc            stack_size, 1
        //  5111: ldc2_w          -2.085978496E9
        //  5114: invokestatic    java/lang/System.currentTimeMillis:()J
        //  5117: l2d            
        //  5118: ldc2_w          1000.0
        //  5121: ddiv           
        //  5122: dadd           
        //  5123: dastore        
        //  5124: goto            5398
        //  5127: iload_2         /* stack_size */
        //  5128: iload           arguments_count
        //  5130: if_icmpge       5135
        //  5133: iconst_m1      
        //  5134: ireturn        
        //  5135: dconst_1       
        //  5136: dstore          result
        //  5138: iconst_0       
        //  5139: istore          argument_index
        //  5141: iload           argument_index
        //  5143: iload           arguments_count
        //  5145: if_icmpge       5177
        //  5148: aload_1         /* stack */
        //  5149: iload_2         /* stack_size */
        //  5150: iload           argument_index
        //  5152: isub           
        //  5153: iconst_1       
        //  5154: isub           
        //  5155: daload         
        //  5156: dstore          first_argument
        //  5158: dconst_0       
        //  5159: dload           first_argument
        //  5161: dcmpl          
        //  5162: ifeq            5171
        //  5165: dconst_0       
        //  5166: dstore          result
        //  5168: goto            5177
        //  5171: iinc            argument_index, 1
        //  5174: goto            5141
        //  5177: iload_2         /* stack_size */
        //  5178: iload           arguments_count
        //  5180: isub           
        //  5181: istore_2        /* stack_size */
        //  5182: aload_1         /* stack */
        //  5183: iload_2         /* stack_size */
        //  5184: iinc            stack_size, 1
        //  5187: dload           result
        //  5189: dastore        
        //  5190: goto            5398
        //  5193: iload_2         /* stack_size */
        //  5194: iload           arguments_count
        //  5196: if_icmpge       5201
        //  5199: iconst_m1      
        //  5200: ireturn        
        //  5201: dconst_1       
        //  5202: dstore          result
        //  5204: aload_1         /* stack */
        //  5205: iload_2         /* stack_size */
        //  5206: iconst_1       
        //  5207: isub           
        //  5208: daload         
        //  5209: dstore          first_argument
        //  5211: iconst_0       
        //  5212: istore          argument_index
        //  5214: iload           argument_index
        //  5216: iload           arguments_count
        //  5218: if_icmpge       5251
        //  5221: aload_1         /* stack */
        //  5222: iload_2         /* stack_size */
        //  5223: iload           argument_index
        //  5225: isub           
        //  5226: iconst_1       
        //  5227: isub           
        //  5228: daload         
        //  5229: dstore          second_argument
        //  5231: dload           second_argument
        //  5233: dload           first_argument
        //  5235: dcmpl          
        //  5236: ifeq            5245
        //  5239: dconst_0       
        //  5240: dstore          result
        //  5242: goto            5251
        //  5245: iinc            argument_index, 1
        //  5248: goto            5214
        //  5251: iconst_1       
        //  5252: iload           arguments_count
        //  5254: if_icmpne       5267
        //  5257: dload           first_argument
        //  5259: dconst_0       
        //  5260: dcmpl          
        //  5261: ifeq            5267
        //  5264: dconst_0       
        //  5265: dstore          result
        //  5267: iload_2         /* stack_size */
        //  5268: iload           arguments_count
        //  5270: isub           
        //  5271: istore_2        /* stack_size */
        //  5272: aload_1         /* stack */
        //  5273: iload_2         /* stack_size */
        //  5274: iinc            stack_size, 1
        //  5277: dload           result
        //  5279: dastore        
        //  5280: goto            5398
        //  5283: iload_2         /* stack_size */
        //  5284: iload           arguments_count
        //  5286: if_icmpge       5291
        //  5289: iconst_m1      
        //  5290: ireturn        
        //  5291: dconst_1       
        //  5292: dstore          result
        //  5294: iconst_0       
        //  5295: istore          argument_index
        //  5297: iload           argument_index
        //  5299: iload           arguments_count
        //  5301: if_icmpge       5333
        //  5304: aload_1         /* stack */
        //  5305: iload_2         /* stack_size */
        //  5306: iload           argument_index
        //  5308: isub           
        //  5309: iconst_1       
        //  5310: isub           
        //  5311: daload         
        //  5312: dstore          first_argument
        //  5314: dload           first_argument
        //  5316: dconst_0       
        //  5317: dcmpg          
        //  5318: ifge            5327
        //  5321: dconst_0       
        //  5322: dstore          result
        //  5324: goto            5333
        //  5327: iinc            argument_index, 1
        //  5330: goto            5297
        //  5333: iload_2         /* stack_size */
        //  5334: iload           arguments_count
        //  5336: isub           
        //  5337: istore_2        /* stack_size */
        //  5338: aload_1         /* stack */
        //  5339: iload_2         /* stack_size */
        //  5340: iinc            stack_size, 1
        //  5343: dload           result
        //  5345: dastore        
        //  5346: goto            5398
        //  5349: iload           arguments_count
        //  5351: iconst_2       
        //  5352: if_icmpeq       5357
        //  5355: iconst_m1      
        //  5356: ireturn        
        //  5357: aload_1         /* stack */
        //  5358: iinc            stack_size, -1
        //  5361: iload_2         /* stack_size */
        //  5362: daload         
        //  5363: dstore          second_argument
        //  5365: aload_1         /* stack */
        //  5366: iinc            stack_size, -1
        //  5369: iload_2         /* stack_size */
        //  5370: daload         
        //  5371: dstore          first_argument
        //  5373: aload_1         /* stack */
        //  5374: iload_2         /* stack_size */
        //  5375: iinc            stack_size, 1
        //  5378: dload           second_argument
        //  5380: dastore        
        //  5381: aload_0         /* this */
        //  5382: getfield        Expression.evaluator:LEvaluator;
        //  5385: dload           first_argument
        //  5387: d2i            
        //  5388: dload           second_argument
        //  5390: invokevirtual   Evaluator.setVariableValue:(ID)V
        //  5393: goto            5398
        //  5396: iconst_m1      
        //  5397: ireturn        
        //  5398: iload_2         /* stack_size */
        //  5399: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  ------------
        //  510    35      6     argument_index   I
        //  507    38      11    result           D
        //  569    14      7     first_argument   D
        //  561    22      9     second_argument  D
        //  599    12      7     first_argument   D
        //  617    35      6     argument_index   I
        //  614    38      11    result           D
        //  676    23      7     first_argument   D
        //  668    31      9     second_argument  D
        //  705    37      6     argument_index   I
        //  702    40      11    result           D
        //  758    14      7     first_argument   D
        //  798    3       11    result           D
        //  813    3       11    result           D
        //  788    42      7     first_argument   D
        //  819    11      11    result           D
        //  846    73      7     first_argument   D
        //  857    62      11    result           D
        //  949    3       11    result           D
        //  935    35      7     first_argument   D
        //  959    11      11    result           D
        //  1003   3       11    result           D
        //  986    41      7     first_argument   D
        //  1016   11      11    result           D
        //  1043   14      7     first_argument   D
        //  1073   14      7     first_argument   D
        //  1106   3       7     first_argument   D
        //  1098   11      9     second_argument  D
        //  1131   9       7     first_argument   D
        //  1123   17      9     second_argument  D
        //  1156   3       11    result           D
        //  1163   11      11    result           D
        //  1182   41      6     argument_index   I
        //  1179   44      11    result           D
        //  1231   41      6     argument_index   I
        //  1228   44      11    result           D
        //  1323   3       11    result           D
        //  1338   3       11    result           D
        //  1313   41      7     first_argument   D
        //  1378   32      7     first_argument   D
        //  1370   40      9     second_argument  D
        //  1434   26      7     first_argument   D
        //  1426   34      9     second_argument  D
        //  1472   11      11    result           D
        //  1505   3       7     first_argument   D
        //  1530   61      7     first_argument   D
        //  1522   69      9     second_argument  D
        //  1580   11      11    result           D
        //  1605   23      9     second_argument  D
        //  1646   14      7     first_argument   D
        //  1676   23      7     first_argument   D
        //  1715   14      7     first_argument   D
        //  1745   14      7     first_argument   D
        //  1775   14      7     first_argument   D
        //  1805   29      7     first_argument   D
        //  1812   22      11    result           D
        //  1850   29      7     first_argument   D
        //  1857   22      11    result           D
        //  1895   29      7     first_argument   D
        //  1902   22      11    result           D
        //  1940   32      7     first_argument   D
        //  1988   32      7     first_argument   D
        //  2034   14      7     first_argument   D
        //  2070   16      7     first_argument   D
        //  2062   24      9     second_argument  D
        //  2104   47      7     first_argument   D
        //  2119   32      11    result           D
        //  2167   47      7     first_argument   D
        //  2182   32      11    result           D
        //  2230   29      7     first_argument   D
        //  2245   14      11    result           D
        //  2275   25      7     first_argument   D
        //  2316   25      7     first_argument   D
        //  2357   47      7     first_argument   D
        //  2371   33      11    result           D
        //  2420   36      7     first_argument   D
        //  2434   22      11    result           D
        //  2472   36      7     first_argument   D
        //  2486   22      11    result           D
        //  2524   47      7     first_argument   D
        //  2538   33      11    result           D
        //  2587   25      7     first_argument   D
        //  2628   38      7     first_argument   D
        //  2637   29      11    result           D
        //  2682   57      7     first_argument   D
        //  2713   26      11    result           D
        //  2791   3       11    result           D
        //  2755   70      7     first_argument   D
        //  2814   11      11    result           D
        //  2841   47      7     first_argument   D
        //  2850   38      11    result           D
        //  2904   50      7     first_argument   D
        //  2928   26      11    result           D
        //  2970   28      7     first_argument   D
        //  3014   15      7     first_argument   D
        //  3053   73      7     first_argument   D
        //  3045   81      9     second_argument  D
        //  3157   37      7     first_argument   D
        //  3142   86      6     argument_index   I
        //  3139   89      9     second_argument  D
        //  3136   92      11    result           D
        //  3252   55      7     first_argument   D
        //  3244   63      9     second_argument  D
        //  3323   15      7     first_argument   D
        //  3354   15      7     first_argument   D
        //  3393   45      7     first_argument   D
        //  3385   53      9     second_argument  D
        //  3404   34      11    result           D
        //  3470   48      7     first_argument   D
        //  3454   64      9     second_argument  D
        //  3462   56      11    result           D
        //  3540   22      7     first_argument   D
        //  3532   30      9     second_argument  D
        //  3576   15      7     first_argument   D
        //  3609   17      7     first_argument   D
        //  3642   15      7     first_argument   D
        //  3676   3       7     first_argument   D
        //  3668   11      9     second_argument  D
        //  3701   9       7     first_argument   D
        //  3693   17      9     second_argument  D
        //  3720   48      7     first_argument   D
        //  3735   33      11    result           D
        //  3801   155     7     first_argument   D
        //  3811   145     9     second_argument  D
        //  3782   190     6     argument_index   I
        //  3779   193     11    result           D
        //  3988   10      7     first_argument   D
        //  4014   35      7     first_argument   D
        //  4065   58      7     first_argument   D
        //  4076   47      11    result           D
        //  4139   58      7     first_argument   D
        //  4150   47      11    result           D
        //  4213   27      7     first_argument   D
        //  4256   27      7     first_argument   D
        //  4299   27      7     first_argument   D
        //  4342   27      7     first_argument   D
        //  4385   27      7     first_argument   D
        //  4428   36      7     first_argument   D
        //  4488   37      7     first_argument   D
        //  4480   45      9     second_argument  D
        //  4543   3       11    result           D
        //  4569   104     7     first_argument   D
        //  4552   137     6     argument_index   I
        //  4549   140     11    result           D
        //  4728   3       11    result           D
        //  4754   3       11    result           D
        //  4775   3       11    result           D
        //  4711   85      7     first_argument   D
        //  4843   29      7     first_argument   D
        //  4832   40      9     second_argument  D
        //  4812   85      6     argument_index   I
        //  4809   88      11    result           D
        //  4952   21      7     first_argument   D
        //  4939   34      9     second_argument  D
        //  4917   83      6     argument_index   I
        //  4914   86      11    result           D
        //  5022   3       7     first_argument   D
        //  5047   3       7     first_argument   D
        //  5039   11      9     second_argument  D
        //  5080   19      11    result           D
        //  5158   19      7     first_argument   D
        //  5141   52      6     argument_index   I
        //  5138   55      11    result           D
        //  5231   20      9     second_argument  D
        //  5214   69      6     argument_index   I
        //  5211   72      7     first_argument   D
        //  5204   79      11    result           D
        //  5314   19      7     first_argument   D
        //  5297   52      6     argument_index   I
        //  5294   55      11    result           D
        //  5373   23      7     first_argument   D
        //  5365   31      9     second_argument  D
        //  0      5400    0     this             LExpression;
        //  0      5400    1     stack            [D
        //  0      5400    2     stack_size       I
        //  0      5400    3     index            I
        //  13     5387    4     arguments_count  I
        //  26     5374    5     function_index   I
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
