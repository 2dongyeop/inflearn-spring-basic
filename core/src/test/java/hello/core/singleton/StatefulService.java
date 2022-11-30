package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태 유지 필드 -> 사용 안하기

    public int order(String name, int price) { /* void -> int */
        System.out.println("name = " + name);
        System.out.println("price = " + price);

//        this.price = price; /* 문제가 되는 코드 -> 사용 안하기 */
        return price;  /* -> 지역 변수로 바로 리턴하기 */
    }

//    public int getPrice() {
//        return price;
//    }
}
