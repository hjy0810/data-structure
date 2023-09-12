package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 黄建永
 * @version 1.0
 */
public class PolandNotaionDemo {
    public static void main(String[] args) {
        //中缀表达式 15+(2+3)*4-5
        //后缀表达式 15 2 3 + 4 * + 5 -
        String expression = "15+(2+3)*4-5";
        //后缀表达式列表
        List suffixExpression = PolandNotaion.toSuffixExpeession(expression);
        //后缀表达式运算
        System.out.println(PolandNotaion.calculate(suffixExpression));
    }
}

class PolandNotaion {
    //把 中缀表达式字符串 转换成 中缀表达式列表
    public static List<String> toArrayList(String expression) {
        List<String> list = new ArrayList<String>();
        int length = expression.length();
        String charStr = "";//每遍历一个字符，就放入到charStr中
        String numStr = "";//用于存放多位数
        for (int i = 0; i < length; ) {
            charStr = expression.substring(i, i + 1);
            if (!charStr.matches("\\d")) {
                //不是数字直接添加到list中
                list.add(charStr);
                i++;
            } else {
                //是数字，需要考虑多位数的情况
                numStr = "";
                while (i < length && (charStr = expression.substring(i, i + 1)).matches("\\d")) {
                    numStr += charStr;//多位数拼接
                    i++;
                }
                //退出循环后，得到拼接后的多位数
                list.add(numStr);
            }
        }
        return list;
    }

    //转后缀表达式
    public static List toSuffixExpeession(String expression) {
        List list = toArrayList(expression);
        //中缀表达式先转为列表：[15, +, (, 2, +, 3, ), *, 4, -, 5]
        Stack<String> opStack = new Stack<String>();//操作符栈
        ArrayList suffixList = new ArrayList();//用于存放后缀表达式列表
        int length = list.size();
        String item = "";//列表项
        for (int i = 0; i < length; i++) {
            item = list.get(i).toString();
            //根据规则转换
            if (item.matches("\\d+")) {
                suffixList.add(item);
            } else if (item.equals("(")) {
                opStack.add(item);
            } else if (item.equals(")")) {
                //获取操作符栈的栈顶元素
                String stackTop = "";
                while (!(stackTop = opStack.pop()).equals("(")) {
                    suffixList.add(stackTop);
                }
            } else {//操作符
                while (true) {
                    if (opStack.size() == 0) {
                        opStack.push(item);
                        break;
                    } else if (opStack.peek().equals("(")) {
                        opStack.push(item);
                        break;
                    } else if (priority(item) > priority(opStack.peek())) {
                        opStack.push(item);
                        break;
                    } else {
                        //当前运算符优先级小于运算符栈的栈顶运算符的优先级
                        suffixList.add(opStack.pop());
                    }
                }
            }
        }
        while (opStack.size() != 0) {
            suffixList.add(opStack.pop());
        }
        return suffixList;
    }

    //自定义运算符优先级
    public static int priority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else {
            return -1;
        }
    }

    //后缀表达式运算
    public static int calculate(List suffixList) {
        Stack<String> numStack = new Stack<>();
        int length = suffixList.size();
        String item = "";
        int num1;//栈顶元素
        int num2;//次栈顶元素
        int res;//存储中间计算数值
        for (int i = 0; i < length; i++) {
            item = suffixList.get(i).toString();
            if (item.matches("\\d+")) {
                //数字入栈
                numStack.push(item);
            } else {
                //运算符：取出栈顶的两个元素进行运算，并把结果入栈
                num1 = Integer.parseInt(numStack.pop());
                num2 = Integer.parseInt(numStack.pop());
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else if (item.equals("*")) {
                    res = num2 * num1;
                } else {
                    res = num2 - num1;
                }
                numStack.push(res + "");
            }
        }
        //栈中最后剩下的元素就是最终结果
        return Integer.parseInt(numStack.pop());
    }
}
