package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service // 이 어노테이션 또한 컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈으로 등록이 됨
@Transactional(readOnly = true) // 트랜잭션, 영속성 컨텍스트로 JPA의 어떤 모든 데이터 변경이나 로직들은 트랜잭션 안에서 실행되어야 하기 때문에
// readOnly = true 경우 데이터의 변경이 없는 읽기 전용 메서드에 사용하는 것으로
// 영속성 컨텍스트를 flush 하지 않기 때문에 약간의 성능 향상이 일어남 (읽기 전용엔 다 적용)
// DB 드라이버가 지원하면 DB에서 성능 향상
@RequiredArgsConstructor // final 있는 필드만 가지고 생성자 만들어 줌
public class MemberService {
    private final MemberRepository memberRepository; // 생성자 주입 방법

    /*
    @Autowired // 스프링이 스프링 빈에 등록되어 있는 MemberRepository를 Injection 해줌 -> Field Injection
    private MemberRepository memberRepository;
     */
    // 사실 @Autowired를 이용한 Injection 방법 (Field Injection)에는 단점이 많다
    // 변경이 어렵다는 문제 등
    // 따라서 생성자 Injection 많이 사용, 생성자가 하나면 생략 가능하다

    /*
    private final MemberRepository memberRepository;
    // final 해두면 생성자 내에서 값 세팅 안하면 에러 터트림 -> 컴파일 시점에 memberRepository를 설정하지 않는 오류 체크 가능
    // (보통 기본 생성자를 추가할 때 발견)

    @Autowired // 요즘은 @Autowired 생략가능 <- 생성자가 하나만 있는 경우
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */

    /*
    // 롬북을 이용하면 생성자 작성 안해도 되는 장점

    @RequiredArgsConstructor // final 있는 필드만 가지고 생성자 만들어 줌
    public class memberService {
        private final MemberRepository memberRepository;

        // 생성자 작성 생략 가능
     */

    // 회원 가입
    @Transactional // 읽기전용이 아닌 경우 readOnly = true 넣으면 DB의 데이터 변경이 일어나지 않음
    // class 전체에 @Transaction 걸면 기본적으로 클래스 내의 public 메서드에 적용됨
    // 메서드에 따로 @Transaction 걸면 해당 메서드는 따로 건 어노테이션을 우선으로 적용됨
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 만약 동시에 DB에 같은 회원명으로 회원가입하여 (멀티 쓰레드 등의 상황)
        // validateDuplicateMember 통과하게 되면 동시에 insert가 되어 문제가 될 수 있음
        // 따라서 DB에서 Member의 name을 unique로 제약 조건을 걸어야 한다

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
