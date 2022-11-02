import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("введите число");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        System.out.println("Ответ = " + calculator.calc(input));
    }

    static class Calculator {
        public static String calc(String input) {
            Converter converter = new Converter();
            String[] actions = {"+", "-", "/", "*"};
            String[] regexActions = {"\\+", "-", "/", "\\*"};
            //определяем арифметическое действие
            int actionIndex = -1;
            for (int i = 0; i < actions.length; i++) {
                if (input.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }
            }
            if (actionIndex == -1) {
                throw new RuntimeException("Не математическая операция");
                //если не нашли арифметический знак завершаем работу
            }
            String[] data = input.split(regexActions[actionIndex]);
            // разделяем числа между знаком
            //проверка на один формат, конвертер арабских чисел в число
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b;
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                } else {
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }
                int lendata = data.length;
                if (lendata > 2) {
                    throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                } else {
                    if (a > 10 || a <= 0 || b > 10 || b <= 0) {
                        throw new RuntimeException("можно работаь с числами от 1 до 10");
                    } else {
                        int c = 0;
                        switch (actions[actionIndex]) {
                            case "+":
                                c = a + b;
                                break;
                            case "-":
                                c = a - b;
                                break;
                            case "*":
                                c = a * b;
                                break;
                            case "/":
                                c = a / b;
                                break;
                        }
                        if (isRoman && c < 1) {
                            throw new RuntimeException("в римской системе исчисления нет отрицательных числе и нуля");
                        } else {

                            if (isRoman) {

                                String s1 = converter.intToRoman(c);
                                return s1;
                            } else {
                                String s1 = Integer.toString(c);
                                return s1;
                            }
                        }
                    }

                }
            } else {
                throw new RuntimeException("Числа должны быть в одном формате");
            }
        }

    }
}