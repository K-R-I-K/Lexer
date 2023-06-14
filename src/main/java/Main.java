public class Main {

    public static void main(String[] args) {
        String input = "const numbers = [1, 2, 3, 4, 5];\n" +
                "let sum = 0;\n" +
                "\n" +
                "for (let i = 0; i < numbers.length; i++) {\n" +
                "  sum += numbers[i];\n" +
                "}\n" +
                "\n" +
                "console.log(\"The sum is: \" + sum);\n" +
                "\n" +
                "function multiplyByTwo(number) {\n" +
                "  return number * 2;\n" +
                "}\n" +
                "\n" +
                "const multipliedNumbers = numbers.map(multiplyByTwo);\n" +
                "console.log(\"Multiplied numbers: \" + multipliedNumbers);\n" +
                "\n" +
                "const filteredNumbers = numbers.filter((number) => number % 2 === 0);\n" +
                "console.log(\"Filtered numbers: \" + filteredNumbers);";
        JSLexer lexer = new JSLexer(input);
        printColors();
        print(lexer);
    }

    private static void printColors() {
        for (TokenType type : TokenType.values()) {
            System.out.println(type.getColor() + type + "\u001B[0m");
        }
    }

    private static void print(JSLexer lexer){
        while (lexer.hasNextToken()) {
            Token token = lexer.nextToken();
            System.out.print(token.getType().getColor() + token.getValue());
            //System.out.print(token.getType().getColor()/* + token.getType() + ": "*/ + token.getValue()/* + "\u001B[0m"*/);
        }
    }
}
