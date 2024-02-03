package Main;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {

        HashMap<String, String> romeKeyMap = new HashMap<>();
        romeKeyMap.put("I", "1");
        romeKeyMap.put("II", "2");
        romeKeyMap.put("III", "3");
        romeKeyMap.put("IV", "4");
        romeKeyMap.put("V", "5");
        romeKeyMap.put("VI", "6");
        romeKeyMap.put("VII", "7");
        romeKeyMap.put("VIII", "8");
        romeKeyMap.put("IX", "9");
        romeKeyMap.put("X", "10");


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
                if ((romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) | (romeKeyMap.containsValue(x) & romeKeyMap.containsValue(y))) {
                    if (romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) {
                        first = Integer.parseInt(romeKeyMap.get(x));
                        second = Integer.parseInt(romeKeyMap.get(y));
                    }
                    if (romeKeyMap.containsValue(x) & romeKeyMap.containsValue(y)) {
                        first = Integer.parseInt(x);
                        second = Integer.parseInt(y);
                    }
                } else {
                    throw new MixedRomeAndArabic("Некорректно введены значения. Римские цифры от I до X, " + "арабские от 1 до 10. Смешивать нельзя.");
                }
            } else {
                throw new LengthException("Неверный формат ввода данных. Пример: 1 *пробел* - *пробел* 1");
            }


            String sign = oneByOne[1];
            switch (sign) {
                case "+":
                    if (romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) {
                        System.out.println(convertToRome(first + second));
                        break;
                    }
                    System.out.println(first + second);
                    break;
                case "-":
                    if ((romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) & (first < second)) {
                        throw new MoreThenZeroException("Результатом работы калькулятора с римскими числами могут быть"
                                + " только положительные числа.");
                    }
                    if (romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) {
                        System.out.println(convertToRome(first - second));
                        break;
                    }
                    System.out.println(first - second);
                    break;
                case "/":
                    if ((romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) & ((first / second) <= 0)) {
                        throw new MoreThenZeroException("Результатом работы калькулятора с римскими числами могут быть"
                                + " только положительные числа.");
                    }
                    if ((romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) & ((first / second) > 0)) {
                        System.out.println(convertToRome(first / second));
                        break;
                    }
                    System.out.println(first / second);
                    break;
                case "*":
                    if (romeKeyMap.containsKey(x) & romeKeyMap.containsKey(y)) {
                        System.out.println(convertToRome(first * second));
                        break;
                    }
                    System.out.println(first * second);
                    break;
                default:
                    throw new SignException("Указан неверный арифметический знак.");
            }
        }
    }

    public static String convertToRome(int number) {
        TreeMap<Integer, String> arabicKeyMap = new TreeMap<>();
        arabicKeyMap.put(100, "C");
        arabicKeyMap.put(90, "XC");
        arabicKeyMap.put(50, "L");
        arabicKeyMap.put(40, "XL");
        arabicKeyMap.put(10, "X");
        arabicKeyMap.put(9, "IX");
        arabicKeyMap.put(5, "V");
        arabicKeyMap.put(4, "IV");
        arabicKeyMap.put(1, "I");

        String rome = "";
        int arabicKey;
        do {
            arabicKey = arabicKeyMap.floorKey(number);
            rome += arabicKeyMap.get(arabicKey);
            number -= arabicKey;
        } while (number != 0);
        return rome;
    }


}

