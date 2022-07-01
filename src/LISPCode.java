public class LISPCode {
    private final String code;
    private int openP;
    private int closeP;

    public LISPCode(String code) {
        this.code = code;
    }

    public boolean isValid() {
        int openCount = 0;
        for (int i = 0; i < this.code.length(); i++) {
            char c = this.code.charAt(i);
            if (openCount == 0 && c != '(') {
                this.openP--;
                return false;
            }
            else if (c == '(') {
                openCount++;
                this.openP++;
            }
            else if (c == ')') {
                openCount--;
                this.closeP++;
            }
        }
        return openCount == 0;
    }

    public String toString() {
        return "LISPCode(isValid=" + this.isValid() +
                ", openParenthesis=" + this.openP +
                ", closeParenthesis=" + this.closeP + ")";
    }
}
