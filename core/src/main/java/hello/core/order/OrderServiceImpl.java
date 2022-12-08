package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /*
    @Autowired private MemberRepository memberRepository;
    필드로 의존관계 주입 -> 비추천 방식. 외부에서 테스트하기가 힘듬.
    + NPE로 인해 결국 setter를 만들게 함 -> 이럴거면 setter injection이 낫다.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /* @Autowired(required = false) //주입할 대상이 없어도 동작하게 //setter를 이용해 의존관계 주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired //setter를 이용해 의존관계 주입
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    } */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /* 테스트 용도 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
