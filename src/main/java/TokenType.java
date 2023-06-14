public enum TokenType {
    KEYWORD("\u001B[34m"),
    WHITESPACE("\u001B[30m"),
    COMMENT("\u001B[37m"),
    NUMERIC_CONSTANT("\u001B[31m"),
    STRING("\u001B[32m"),
    IDENTIFIER("\u001B[33m"),

    OPERATOR("\u001B[35m"),
    PUNCTUATION_MARK("\u001B[36m"),
    ERROR("\u001B[38m");

    private String color;

    TokenType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
