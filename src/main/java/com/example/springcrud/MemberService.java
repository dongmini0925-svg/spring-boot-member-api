package com.example.springcrud;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(long id, Member updatedMember) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        member.setName(updatedMember.getName());
        member.setAge(updatedMember.getAge());
        return memberRepository.save(member);
    }

    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }
}
