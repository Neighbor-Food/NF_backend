package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member create(Member member) {
        if (member == null || member.getEmail() == null) {
            throw new RuntimeException("invalid arguments");
        }

        String email = member.getEmail();

        // 중복된 이메일 회원가입 처리
        if (memberRepository.existsByEmail(email)) {
            log.warn("email already exists {}", email);
            throw new RuntimeException("email already exists");
        }

        return memberRepository.save(member);
    }

    public void delete(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            Member regMember = member.get();
            memberRepository.deleteById(regMember.getMember_no());
        } else {
            log.warn("email does not exists");
            throw new RuntimeException("email does not exists");
        }

    }

    // db랑 어케 엮을건지 생각...
    public Member getMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return member.get();
        } else {
            log.warn("member does not exists");
            throw new RuntimeException("member does not exists");
        }
    }

    public Member modify(Member member, String password, String name, String push_email, String bank, String bank_account_number){
        member.setPassword(password);
        member.setName(name);
        member.setPush_email(push_email);
        member.setBank(bank);
        member.setBank_account_number(bank_account_number);
        return memberRepository.save(member);
    }

    public Member getByCredentials(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }
}
