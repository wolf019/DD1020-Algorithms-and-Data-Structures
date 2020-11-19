package Labs;

public class lab1_higherGrade_recursive {

    char[] bracketsToCheck;
    char[] validBrackets = { '(', ')', '{', '}', '[', ']' };

    public boolean checkIfBalanced(String in) {

        boolean isBalanced = false;

        bracketsToCheck = in.toCharArray();

        for (int j = 0; j <= 4; j = j + 2) {
            if (bracketsToCheck[0] == validBrackets[j])
                isBalanced = true;
        }

        if (!isBalanced)
            return isBalanced;

        for (int i = 0; i < bracketsToCheck.length; i++) {

            int sort = 0;
            char current;

            current = bracketsToCheck[i];

            for (int j = 0; j <= 4; j = j + 2) {
                if (current == validBrackets[j])
                    sort = j;
            }

            isBalanced = findEndBracket(current, i, sort);
        }

        return isBalanced;
    }

    public boolean findEndBracket(char currentBracketToCheck, int whereAreWe, int sort) {

        boolean isBalanced = true;
        char current = bracketsToCheck[whereAreWe + 1];

        while (isBalanced == true)
        if (bracketsToCheck[current] == validBrackets[sort + 1])
            isBalanced = true;
        else {
            for (int j = 0; j <= 4; j = j + 2) {
                if (current == validBrackets[j])
                    isBalanced = findEndBracket(current, whereAreWe + 1, validBrackets[j]);
            }
        }

        return isBalanced;
    }

    public static void main(String[] args) {

    }
}
