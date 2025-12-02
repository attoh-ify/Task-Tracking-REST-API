package com.taskTracking.logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggingInterceptor {
    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        String method = ctx.getMethod().getDeclaringClass().getSimpleName() + "#" + ctx.getMethod().getName();
        System.out.println("[SERVICE]Entering: " + method);
        Object result = ctx.proceed();
        System.out.println("[SERVICE] Exiting: " + method);
        return result;
    }
}
