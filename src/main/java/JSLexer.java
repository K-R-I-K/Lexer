import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSLexer {
    private static final String WHITESPACE = "\\s+";
    private static final String COMMENT = "(//.*)|(/\\*([\\s\\S]*?)\\*/)";
    private static final String NUMERIC_CONSTANT = "[-+]?\\d*\\.?\\d+";
    private static final String KEYWORD = "\\b(?:await|break|case|catch|class|const|continue|debugger|" +
            "default|delete|do|else|enum|export|extends|false|finally|for|function|if|implements|import|in|instanceof|" +
            "interface|let|new|null|package|private|protected|public|return|super|switch|static|this|throw|try|true|" +
            "typeof|var|void|while|with|yield)\\b";
    private static final String STRING = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String IDENTIFIER = "[a-zA-Z_$][a-zA-Z0-9_$]*";
    private static final String OPERATOR = "=|\\+\\+|--|==|=~|!=|!==|<=|>=|<<|>>|>>>|\\+|-|\\*|/|%|\\|\\||&&|&|\\||\\^|~|!|<|>|\\?\\?\\?|\\?\\?|\\?|:|\\.|,";
    private static final String PUNCTUATION_MARK = "[\\(\\)\\[\\]\\{\\};]";

    private static final Map<TokenType, String> REG_EX = new HashMap<TokenType, String>();
    static {
        REG_EX.put(TokenType.COMMENT, COMMENT);
        REG_EX.put(TokenType.KEYWORD, KEYWORD);
        REG_EX.put(TokenType.WHITESPACE, WHITESPACE);
        REG_EX.put(TokenType.NUMERIC_CONSTANT, NUMERIC_CONSTANT);
        REG_EX.put(TokenType.STRING, STRING);
        REG_EX.put(TokenType.IDENTIFIER, IDENTIFIER);
        REG_EX.put(TokenType.OPERATOR, OPERATOR);
        REG_EX.put(TokenType.PUNCTUATION_MARK, PUNCTUATION_MARK);
    }
    private static final Pattern PATTERN = Pattern.compile(
            "(" + NUMERIC_CONSTANT + ")" +
                    "|(" + COMMENT + ")" +
                    "|(" + KEYWORD + ")" +
                    "|(" + WHITESPACE + ")" +
                    "|(" + STRING + ")" +
                    "|(" + IDENTIFIER + ")" +
                    "|(" + OPERATOR + ")" +
                    "|(" + PUNCTUATION_MARK + ")" +
                    "|(.+)"
    );
    private String input;
    private Matcher matcher;

    public JSLexer(String input) {
        this.input = input;
        this.matcher = PATTERN.matcher(input);
    }

    public boolean hasNextToken() {
        return matcher.find();
    }

    public Token nextToken() {
        String tmp = matcher.group();
        for (TokenType type : TokenType.values()) {
            if(type == TokenType.ERROR) continue;
            if (tmp.matches(REG_EX.get(type))) {
                return new Token(type, tmp);
            }
        }
        return new Token(TokenType.ERROR, tmp);
    }
}