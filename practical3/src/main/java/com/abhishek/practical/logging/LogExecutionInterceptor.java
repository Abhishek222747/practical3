package com.abhishek.practical.logging;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@InterceptorBean(LogExecution.class)
public class LogExecutionInterceptor implements MethodInterceptor<Object,Object> {
    private static final Logger LOG= LoggerFactory.getLogger(LogExecutionInterceptor.class);

    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context){
        LOG.info("Executing method: "+context.getMethodName());
        try{
            Object result= context.proceed();
            LOG.info("Method executed successfully: "+context.getMethodName());
            return result;
        }catch(Exception e){
            LOG.error("Error in method: "+context.getMethodName(),e);
            throw e;
        }
    }
}
