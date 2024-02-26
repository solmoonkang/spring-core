package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        // statefulService1.order("userA", 10000);     // ⚠️ ThreadA: 사용자 A가 10,000원을 주문
        // statefulService2.order("userB", 20000);     // ⚠️ ThreadB: 사용자 B가 10,000원을 주문

        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A가 주문 금액을 조회한다. -> price의 기대값은 10,000원이 나오는 것이지만, 20,000원이 나온다.
        // int price = statefulService1.getPrice();
        System.out.println("userA price = " + userAPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}