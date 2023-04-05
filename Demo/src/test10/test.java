package test10;

public class test {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++){
            if (i == 5){
                Thread.sleep(10000);
            }
            System.out.println(i);
        }
    }
}
