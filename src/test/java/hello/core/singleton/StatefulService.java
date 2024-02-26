package hello.core.singleton;

public class StatefulService {

    // private int price;          // 상태를 유지하는 필드

    public void problemOrder(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price;     // ⚠️ 해당 부분에서 문제가 발생한다.
    }

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
