package ExeptionJavaFinalWork;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String[] userData = getArrayFromString(getUserDataFromConsole());

        boolean flag = false;
        try {
            isTheDataAmountCorrect(userData);

            isTheSurnameCorrect(userData[0]);
            isTheNameCorrect(userData[1]);
            isThePatronymicCorrect(userData[2]);
            isTheBirthdayCorrect(userData[3]);
            isThePhoneNumberCorrect(userData[4]);
            isTheGenderCorrect(userData[5]);
            flag = true;

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        if (flag) {
            People people = new People(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5]);
            write(people, people.surname + ".txt");
            System.out.println("Данные Пользователя: " + people + " Успешно сохранены");

        } else {
            System.out.println("Что-то пошло не так");
        }

    }

    public static String getUserDataFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите следующие данные (Латиницей), разделенные пробелом: \n" +
                "Фамилия\n" +
                "Имя\n" +
                "Отчество\n" +
                "Дата рождения (В формате: 01.01.1997)\n" +
                "Номер телефона (В формате: 1234567 (7 цифры))\n" +
                "Пол (В формате: символ латиницей f или m)\n" +
                "А затем нажмите Enter.");

        String data = scanner.nextLine();
        scanner.close();
        return data;

    }

    public static String[] getArrayFromString(String str) {
        String[] arr = str.split(" ");
        return arr;
    }

    public static void isTheDataAmountCorrect(String[] arrayData) throws RuntimeException {
        if (arrayData.length < 6) {
            throw new RuntimeException("Введено неверное количество данных: меньше 6)");
        } else if (arrayData.length > 6) {
            throw new RuntimeException("Введено неверное количество данных: больше 6)");
        }
    }

    public static void isTheSurnameCorrect(String surname) throws RuntimeException {
        if (!surname.chars().allMatch(Character::isLetter)) {
            throw new RuntimeException("Ошибка в написании фамилии!");
        }
    }

    public static void isTheNameCorrect(String name) throws RuntimeException {
        if (!name.chars().allMatch(Character::isLetter)) {
            throw new RuntimeException("Ошибка в написании имени!");
        }
    }

    public static void isThePatronymicCorrect(String patronymic) throws RuntimeException {
        if (!patronymic.chars().allMatch(Character::isLetter)) {
            throw new RuntimeException("Ошибка в написании отчества!");
        }
    }

    public static void isTheBirthdayCorrect(String birthday) throws RuntimeException {
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try {
            format.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException("Неверный формат даты рождения");
        }
    }

    public static void isThePhoneNumberCorrect(String phoneNumber) throws RuntimeException {
        if (phoneNumber.length() <= 7) {
            try {
                Integer.parseInt(phoneNumber);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Ошибка в написании номера телефона");
            }
        } else {
            throw new RuntimeException("Номер должен быть не длиннее 7 символов");
        }
    }

    public static void isTheGenderCorrect(String gender) throws RuntimeException {
        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
            throw new RuntimeException("Неверно введен пол!");
        }
    }

    private static void write(People people, String path) {

        try (FileWriter writer = new FileWriter(new File(path), true)) {
            writer.write(people.toString());
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}