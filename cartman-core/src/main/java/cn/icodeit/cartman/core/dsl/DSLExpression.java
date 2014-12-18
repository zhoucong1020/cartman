package cn.icodeit.cartman.core.dsl;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface DSLExpression {

    DSLExpression begin(Task task);

    DSLExpression beginAfter(Task task, Task... dependentTasks);

    DSLExpression sync(Object res, DSLExpression expression);

    DSLExpression withContext(DSLContext context);

    DSLExpression run();
}
