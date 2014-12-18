package cn.icodeit.cartman.core.dsl;

/**
 * @author zhoucong
 */
public class ExecutableExpression implements DSLExpression {

    @Override
    public DSLExpression begin(Task task) {
        return null;
    }

    @Override
    public DSLExpression beginAfter(Task task, Task... dependentTasks) {
        return null;
    }

    @Override
    public DSLExpression sync(Object res, DSLExpression expression) {
        return null;
    }

    @Override
    public DSLExpression withContext(DSLContext context) {
        return null;
    }

    @Override
    public DSLExpression run() {
        return null;
    }
}
