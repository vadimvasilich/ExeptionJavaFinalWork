


public class People {
    String surname;
    String name;
    String patronymic;
    String birthday;
    String phoneNumber;
    String gender;

    public People(String surname, String name, String patronymic, String birthday, String phoneNumber, String gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return surname + " " +
                name + " " +
                patronymic + " " +
                birthday + " " +
                phoneNumber + " " +
                gender;
    }

}
