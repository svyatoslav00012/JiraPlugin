package svyat.model;

public class BalancedParenthesesCheckerImpl implements BalancedParenthesesChecker {

    @Override
    public boolean isCorrectExpression(String parentheses) {
        int balance = 0;

        for(int i = 0; i < parentheses.length(); ++i){
            char c = parentheses.charAt(i);
            if(c == '(')
                balance ++;
            else if(c == ')')
                balance --;

            if(balance < 0)
                return false;
        }

        if(balance > 0)
            return false;

        return true;
    }

}
