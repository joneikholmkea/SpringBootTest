package j.exp;
import java.util.HashMap;
import java.util.Map;

public class MethodMap {
    private final Map<String, Runnable> methodMap;

    public MethodMap() {
        methodMap = new HashMap<>();
        initMethodMap();
    }

    private void initMethodMap() {
        methodMap.put("method1", this::method1);
        methodMap.put("method2", this::method2);
        methodMap.put("method3", this::method3);
    }

    public void executeMethod(String key) {
        methodMap.get(key).run();
    }

    private void method1() {
        System.out.println("Method 1 executed");
    }

    private void method2() {
        System.out.println("Method 2 executed");
    }

    private void method3() {
        System.out.println("Method 3 executed");
    }

    public static void main(String[] args) {
        MethodMap methodMap = new MethodMap();
        methodMap.executeMethod("method1");
        methodMap.executeMethod("method2");
        methodMap.executeMethod("method3");
    }
}