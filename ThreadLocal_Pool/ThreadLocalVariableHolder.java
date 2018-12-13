package ThreadLocal_Pool;

public class ThreadLocalVariableHolder {
	
    private static ThreadLocal<Integer> variableHolder = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static int getValue() {
        return variableHolder.get();
    }

    public static void remove() {
        variableHolder.remove();
    }

    public static void increment() {
        variableHolder.set(variableHolder.get() + 1);
    }
}
