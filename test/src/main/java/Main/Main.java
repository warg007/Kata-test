package Main;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {

        while (true) {

            Scanner console = new Scanner(System.in);
            String mathExpression = console.nextLine();

            System.out.println(calc(mathExpression));
        }
    }

    static class LengthException extends Exception {
        LengthException(String description) {
            super(description);
        }
    }

    static class SignException extends Exception {
        SignException(String description) {
            super(description);
        }

    }

    static class MixedRomeAndArabic extends Exception {
        MixedRomeAndArabic(String description) {
            super(description);
        }
    }

    static class MoreThenZeroException extends Exception {
        MoreThenZeroException(String description) {
            super(description);
        }
    }

    public static String calc(String input) throws Exception {

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

        int first = 0;
        int second = 0;
        boolean isArabic;
        String firstPoint;
        String secondPoint;
        String answer;

        String[] oneByOne = input.split(" ");

        if (oneByOne.length == 3) {
            firstPoint = oneByOne[0];
            secondPoint = oneByOne[2];
            if ((romeKeyMap.containsKey(firstPoint) & romeKeyMap.containsKey(secondPoint)) |
                    (romeKeyMap.containsValue(firstPoint) & romeKeyMap.containsValue(secondPoint))) {
                if (romeKeyMap.containsKey(firstPoint) & romeKeyMap.containsKey(secondPoint)) {
                    first = Integer.parseInt(romeKeyMap.get(firstPoint));
                    second = Integer.parseInt(romeKeyMap.get(secondPoint));
                }
                if (romeKeyMap.containsValue(firstPoint) & romeKeyMap.containsValue(secondPoint)) {
                    first = Integer.parseInt(firstPoint);
                    second = Integer.parseInt(secondPoint);
                }
            } else {
                throw new MixedRomeAndArabic("Некорректно введены значения. Римские цифры от I до X, "
                        + "арабские от 1 до 10. Смешивать нельзя.");
            }
        } else {
            throw new LengthException("Неверный формат ввода данных. Пример: 1 *пробел* - *пробел* 1");
        }

        isArabic = isArabic(romeKeyMap, firstPoint, secondPoint);

        String sign = oneByOne[1];

        switch (sign) {
            case "+":
                if (isArabic) {
                    answer = String.valueOf(first + second);
                } else {
                    answer = convertToRome(first + second);
                }
                break;
            case "-":
                if ((romeKeyMap.containsKey(firstPoint) & romeKeyMap.containsKey(secondPoint)) & (first <= second)) {
                    throw new MoreThenZeroException("Результатом работы калькулятора с римскими числами могут быть"
                            + " только положительные числа.");
                } else if (isArabic) {
                    answer = String.valueOf(first - second);
                } else {
                    answer = convertToRome(first - second);
                }
                break;
            case "/":
                if ((romeKeyMap.containsKey(firstPoint) & romeKeyMap.containsKey(secondPoint)) & ((first / second) <= 0)) {
                    throw new MoreThenZeroException("Результатом работы калькулятора с римскими числами могут быть"
                            + " только положительные числа.");
                }
                if (isArabic) {
                    answer = String.valueOf(first / second);
                } else {
                    answer = convertToRome(first / second);
                }
                break;
            case "*":
                if (isArabic) {
                    answer = String.valueOf(first * second);
                } else {
                    answer = convertToRome(first * second);
                }
                break;
            default:
                throw new SignException("Указан неверный арифметический знак.");
        }
        return answer;
    }

    static String convertToRome(int number) {
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

    static boolean isArabic(HashMap<String, String> romeKeyMap, String firstPoint, String secondPoint) {
        if (romeKeyMap.containsKey(firstPoint) & romeKeyMap.containsKey(secondPoint)) {
            return false;
        } else {
            return true;

        }
    }
}

