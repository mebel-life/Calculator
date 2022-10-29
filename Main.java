import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String exp = scn.nextLine();
        //определяем арифметическое действие
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            System.out.println("ошибка, строка не является математической операцией");
            return; //если не нашли арифметический знак завершаем работу
        }

        String[] data = exp.split(regexActions[actionIndex]);
        // разделяем числа между знаком
        //проверка на один формат, конвертер арабских чисел в число потом
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            }else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            int lendata = data.length;
            if (lendata > 2) {
                System.out.println("ошибка, введите не больше 2-х чисел");
            } else {
                if (a > 10 || a < 0 || b > 10 || b < 0) {
                    System.out.println("ошибка, можно работаь с числами от 1 до 10");
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
                    if (isRoman && c < 1){
                        System.out.println("ошибка, в римской системе исчисления нет отрицательных числе и нуля");
                    }else {

                        if (isRoman) {
                            System.out.println("Ответ= " + converter.intToRoman(c));
                        } else {
                            System.out.println("Ответ= " + c);
                        }
                    }
                }
            }
        }else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}