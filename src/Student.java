public class Student {
    private String firstName;
    private String lastName;
    public Student(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    @Override
    public String toString() {
        return "{" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }
}
