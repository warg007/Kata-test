package Main;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        HashMap<String, String> rome = new HashMap<>();
        rome.put("I", "1");
        rome.put("II", "2");
        rome.put("III", "3");
        rome.put("IV", "4");
        rome.put("V", "5");
        rome.put("VI", "6");
        rome.put("VII", "7");
        rome.put("VIII", "8");
        rome.put("IX", "9");
        rome.put("X", "10");

        class SignException extends Exception {
            SignException(String description) {
                super(description);
            }

        }

        class MixedRomeAndArabic extends Exception {
            MixedRomeAndArabic(String description) {
                super(description);
            }
        }

        class MoreThenZeroException extends Exception {
            MoreThenZeroException(String description) {
                super(description);
            }
        }

        class LengthException extends Exception {
            LengthException(String description) {
                super(description);
            }
        }

        int first = 0;
        int second = 0;
        String x;
        String y;

        while (true) {
            Scanner console = new Scanner(System.in);
            String mathExpression = console.nextLine();
            String[] oneByOne = mathExpression.split(" ");

            if (oneByOne.length == 3) {
                x = oneByOne[0];
                y = oneByOne[2];
                if ((rome.containsKey(x) & rome.containsKey(y)) | (rome.containsValue(x) & rome.containsValue(y))) {
                    if (rome.containsKey(x) & rome.containsKey(y)) {
                        first = Integer.parseInt(rome.get(x));
                        second = Integer.parseInt(rome.get(y));
                    }
                    if (rome.containsValue(x) & rome.containsValue(y)) {
                        first = Integer.parseInt(x);
                        second = Integer.parseInt(y);
                    }
                } else {
                    throw new MixedRomeAndArabic("Некорректно введены значения. Римские цифры от I до X, " +
                            "арабские от 1 до 10. Смешивать нельзя.");
                }
            } else {
                throw new LengthException("Неверный формат ввода данных. Пример: 1 *пробел* - *пробел* 1");
            }


            String sign = oneByOne[1];
            switch (sign) {
                case "+":
                    System.out.println(first + second);
                    break;
                case "-":
                    if ((rome.containsKey(x) & rome.containsKey(y)) & (first < second)) {
                        throw new MoreThenZeroException("Результатом работы калькулятора с римскими числами могут быть" +
                                " только положительные числа.");
                    }
                    System.out.println(first - second);
                    break;
                case "/":
                    System.out.println(first / second);
                    break;
                case "*":
                    System.out.println(first * second);
                    break;
                default:
                    throw new SignException("Указан неверный арифметический знак.");
            }
        }
    }
}

