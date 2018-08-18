package svyat.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import svyat.model.BalancedParenthesesChecker;
import svyat.model.BalancedParenthesesCheckerImpl;

@Scanned
public class BalancedParenthesesField extends AbstractSingleFieldType<String> {

    private BalancedParenthesesChecker checker = new BalancedParenthesesCheckerImpl();

    public BalancedParenthesesField(
            @JiraImport CustomFieldValuePersister customFieldValuePersister,
            @JiraImport GenericConfigManager genericConfigManager) {
        super(customFieldValuePersister, genericConfigManager);
    }

    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
    }

    @Override
    protected Object getDbValueFromObject(String object) {
        return getStringFromSingularObject(object);
    }

    @Override
    protected String getObjectFromDbValue(Object dbValue) throws FieldValidationException {
        return getSingularObjectFromString(String.valueOf(dbValue));
    }

    @Override
    public String getStringFromSingularObject(String singularObject) {
        return singularObject;
    }

    @Override
    public String getSingularObjectFromString(String expression) throws FieldValidationException {
        if (expression == null)
            return null;
        if (!checker.isCorrectExpression(expression))
            throw new FieldValidationException("unbalanced parentheses");
        return expression;
    }
}