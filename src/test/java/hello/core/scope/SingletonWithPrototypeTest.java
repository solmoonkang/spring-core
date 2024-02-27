package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
        int firstCount = clientBean1.logic();
        assertThat(firstCount).isEqualTo(1);

        ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
        int secondCount = clientBean2.logic();
        assertThat(secondCount).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {

        private final PrototypeBean prototypeBean;      // 생성 시점에 이미 주입되어 있다.

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Getter
    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("Prototype.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }
    }
}
