package models;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private int version;
    private String insuranceCompany;

    public User(String userId, String fullName, int version, String insuranceCompany) {
        this.userId = userId;
        this.firstName = fullName.split(" ")[0];
        this.lastName = fullName.split(" ")[1];
        this.version = version;
        this.insuranceCompany = insuranceCompany;
    }

    public User(String[] userData) {
        this.userId = userData[0];
        this.firstName = userData[1].split(" ")[0];
        this.lastName = userData[1].split(" ")[1];
        this.version = Integer.parseInt(userData[2]);
        this.insuranceCompany = userData[3];
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public boolean equals(User otherUser) {
        return userId.equals(otherUser.userId)
                && insuranceCompany.equals(otherUser.insuranceCompany);
    }

    public boolean isLatest(User otherUser) {
        return compareTo(otherUser) > 0;
    }

    public int compareTo(User otherUser) {
        if (equals(otherUser)){
            return compareVersion(otherUser);
        }
        else {
            return compareName(otherUser);
        }

    }

    private int compareVersion(User otherUser) {
        return Integer.compare(version, otherUser.version);
    }

    private int compareName(User otherUser) {
        if (firstName.equals(otherUser.firstName)) {
            return lastName.compareTo(otherUser.lastName);
        }
        return firstName.compareTo(otherUser.firstName);
    }

    public String toString(){
        return userId + "," +
                firstName + " " +
                lastName + "," +
                version + "," +
                insuranceCompany;
    }
}
