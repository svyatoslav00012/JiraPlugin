package it.svyat;

import com.atlassian.jira.functest.framework.Administration;
import com.atlassian.jira.functest.framework.Navigation;
import com.atlassian.jira.functest.framework.SessionFactory;
import com.atlassian.jira.functest.framework.navigator.IssueTypeCondition.IssueType;
import com.atlassian.jira.functest.rule.SkipCacheCheck;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@SkipCacheCheck
@RunWith(AtlassianPluginsTestRunner.class)
public class MyIntegrationTest {

    private static final String USERNAME = "test_username";
    private static final String FIELD_NAME = "Balanced Parentheses Field";
    private static final String FIELD_TYPE = "balanced-parentheses-field";

    private static final String PROJECT_NAME = "Test_Project";
    private static final String PROJECT_KEY = "TPR";
    private static final String PROJECT_LEAD = USERNAME;

    private static final String ISSUE_TYPE = IssueType.TASK.getName();
    private static final String ISSUE_SUMMARY = "TEST_SUMMARY";
    private static final String BALANCED_EXPR = "()()";
    private static final String UNBALANCED_EXPR = "())";
    private static final Map<String, String[]> TEST_PARAMS = getTestParams();
//    @Inject
//    private BalancedParenthesesField balancedParenthesesField;

    @Inject
    private Administration administration;

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private Navigation navigation;

    private static Map<String, String[]> getTestParams() {
        Map<String, String[]> params = new HashMap<>();
        String param_key = FIELD_NAME;
        String[] param_value = {UNBALANCED_EXPR};
        params.put(param_key, param_value);
        return params;
    }

    @Test
    public void testFieldValidationException() {
        administration.usersAndGroups().addUser(USERNAME);
        navigation.login(USERNAME);
        navigation.gotoCustomFields();
        administration.customFields().addCustomField(FIELD_TYPE, FIELD_NAME);
        navigation.gotoAdminSection(Navigation.AdminSection.VIEW_PROJECTS);
        administration.project().addProject(PROJECT_NAME, PROJECT_KEY, PROJECT_LEAD);
        navigation.issue().createIssue(PROJECT_NAME, ISSUE_TYPE, ISSUE_SUMMARY, TEST_PARAMS);
        someAssertThatChecksIfIssueWasntCreated();
    }

    private void someAssertThatChecksIfIssueWasntCreated() {
        /*
        atlas-integration-test -DtestGroups=traditional-integration
        fails with error: No tests found in class. Google didn't help me.
        So, I stuck in that, without googling what should be in this function
         */
    }
}