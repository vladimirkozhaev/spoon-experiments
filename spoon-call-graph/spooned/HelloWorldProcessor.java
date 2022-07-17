

/**
 * outputs hello world by self compile-time introspection
 */
public class HelloWorldProcessor extends spoon.processing.AbstractProcessor<spoon.reflect.code.CtInvocation> {
    @java.lang.Override
    public void process(spoon.reflect.code.CtInvocation element) {
        java.lang.System.out.println(element.getActualTypeArguments());
    }

    java.lang.String msg = "hello world";
}

