package proxydemo;

public class Main {
    public static void main(String[] args) {
        ExpensiveObject object = new ExpensiveObjectProxy();
        object.process();
//        18:14:44.305 [main] INFO proxydemo.ExpensiveObjectImpl - Loading initial configuration...
//        18:14:44.308 [main] INFO proxydemo.ExpensiveObjectImpl - processing complete.
        object.process();
//        18:15:06.943 [main] INFO proxydemo.ExpensiveObjectImpl - processing complete.
    }
}
