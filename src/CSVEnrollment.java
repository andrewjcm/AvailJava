import models.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVEnrollment {
    private final String FILE_NAME;
    private final String PROJECT_DIR;
    private final String SLASH;
    private final HashMap<String, HashMap<String, User>> usersByInsurance = new HashMap<>();

    public CSVEnrollment(String fileName) {
        this.FILE_NAME = fileName;
        this.PROJECT_DIR = System.getProperty("user.dir");
        this.SLASH = this.getDirSlash();
    }

    public void parse() throws IOException {
        readFileContents();
        writeSortedFileContents();
        System.out.println("Output files can be found in " + PROJECT_DIR + SLASH + "output");
    }

    private void writeSortedFileContents() throws IOException {
        for (String key : usersByInsurance.keySet()) {
            List<User> userValues = new ArrayList<>(usersByInsurance.get(key).values());
            userValues.sort(User::compareTo);
            for (User user: userValues) {
                writeToFile(user);
            }
        }
    }

    private void readFileContents() throws IOException {
        String filePath = PROJECT_DIR + SLASH + FILE_NAME;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String record;
        while((record = reader.readLine()) != null) {
            String[] userData = record.split(",");
            if (!isCSVHeader(userData)) {
                User user = new User(userData);
                sortByInsurance(user);
            }
        }
    }

    private void sortByInsurance(User user) {
        if (insuranceExistsInMap(user) && userExistsInInsuranceMap(user)) {
            if (user.compareTo(getExistingUserInInsuranceMap(user)) > 0) {
                addUserToInsuranceMap(user);
            }
        }
        else if (insuranceExistsInMap(user)){
            addUserToInsuranceMap(user);
        }
        else {
            addInsuranceToMap(user);
            addUserToInsuranceMap(user);
        }
    }

    private boolean insuranceExistsInMap(User user) {
        return usersByInsurance.containsKey(user.getInsuranceCompany());
    }

    private void addInsuranceToMap(User user) {
        usersByInsurance.put(user.getInsuranceCompany(), new HashMap<>());
    }

    private boolean userExistsInInsuranceMap(User user) {
        return usersByInsurance.get(user.getInsuranceCompany()).containsKey(user.getUserId());
    }

    private User getExistingUserInInsuranceMap(User user) {
        return usersByInsurance.get(user.getInsuranceCompany()).get(user.getUserId());
    }

    private void addUserToInsuranceMap(User user) {
        usersByInsurance.get(user.getInsuranceCompany()).put(user.getUserId(), user);
    }

    private void writeToFile(User user) throws IOException {
        String filePath = PROJECT_DIR + SLASH + "output" + SLASH + user.getInsuranceCompany();
        FileWriter fw = new FileWriter(filePath + ".csv", true);
        fw.write(user + "\n");
        fw.close();
    }

    private String getDirSlash() {
        String os = System.getProperty("os.name");
        return os.contains("Win") ? "\\" : "/";
    }

    private boolean isCSVHeader(String[] userData){
        return userData[0].equals("userId");
    }
}
