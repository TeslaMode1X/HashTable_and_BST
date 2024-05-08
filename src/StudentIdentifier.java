public class StudentIdentifier {
    private int forHash;
    private String passportID;
    private String studentType;

    public StudentIdentifier(int id, String studentType) {
        this.forHash = id;
        this.studentType = studentType;

        StringBuilder sb = new StringBuilder();
        int num = id;

        while (num != 0) {
            int temp = num % 10;
            num /= 10;
            sb.append(temp);
        }

        for (int i = sb.length(); i < 7; i++) {
            sb.append(0);
        }

        this.passportID = sb.reverse().toString();
    }

    public int getForHash() {
        return forHash;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * 17 + forHash;

        return hash;
    }

    @Override
    public String toString() {
        return "{" +
                "id = " + passportID +
                ", Status = " + studentType +
                '}';
    }
}