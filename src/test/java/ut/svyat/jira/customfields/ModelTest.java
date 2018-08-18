package ut.svyat.jira.customfields;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import svyat.model.BalancedParenthesesChecker;
import svyat.model.BalancedParenthesesCheckerImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @since 3.5
 */
public class ModelTest {

    private BalancedParenthesesChecker checker;

    @Before
    public void setup() {
        checker = new BalancedParenthesesCheckerImpl();
    }

    @After
    public void tearDown() {
        checker = null;
    }

    @Test
    public void testCorrectSequence1() {
        testCorrect("()");
    }

    @Test
    public void testCorrectSequence2() {
        testCorrect("(()())()");
    }

    @Test
    public void testIncorrectSequence1() {
        testIncorrect(")");
    }

    @Test
    public void testIncorrectSequence2() {
        testIncorrect(")()(");
    }

    @Test
    public void testIncorrectSequence3() {
        testIncorrect("()(())(()");
    }

    private void testCorrect(String correctExpression){
        boolean result = checker.isCorrectExpression(correctExpression);
        assertTrue(result);
    }

    private void testIncorrect(String incorrectExpression){
        boolean result = checker.isCorrectExpression(incorrectExpression);
        assertFalse(result);
    }

}
