package bookstore.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import java.util.Map;

public class XssInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpParameters parameters = actionContext.getParameters();
        for (Map.Entry<String, Parameter> entry : parameters.entrySet()) {
            if (!entry.getValue().isMultiple() && entry.getValue().isDefined()) {
                if (!entry.getValue().getValue().equals(StringEscapeUtils.escapeHtml4(entry.getValue().getValue()))){
                    entry.setValue(new Parameter.Request(entry.getValue().getName(),StringEscapeUtils.escapeHtml4(entry.getValue().getValue())));
                }
            }
        }
        return invocation.invoke();
    }
}
