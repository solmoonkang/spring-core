package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {       // Enum 타입은 '==' 연산자를 사용한다.
            return discountFixAmount;
        }
        return 0;
    }
}
