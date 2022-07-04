public class LISPCode {
    private final String code;
    private int openP;
    private int closeP;

    public LISPCode(String code) {
        this.code = code;
    }

    public boolean isValid() {
        int openCount = 0;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (openCount == 0 && c != '(') {
                openP--;
                return false;
            }
            else if (c == '(') {
                openCount++;
                openP++;
            }
            else if (c == ')') {
                openCount--;
                closeP++;
            }
        }
        return openCount == 0;
    }

    public String toString() {
        return "LISPCode(isValid=" + isValid() +
                ", openParenthesis=" + openP +
                ", closeParenthesis=" + closeP + ")";
    }
}
