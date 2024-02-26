package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 하나만 생성해준다.
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면, 해당 static 메소드를 통해서만 조회하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private 으로 선언해서, 외부에서 new 키워드를 사용한 객체 생성을 하지 못하도록 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
