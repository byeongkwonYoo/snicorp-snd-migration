package snicorp.snd.migration.service.sandi;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snicorp.snd.migration.dto.sandi.Member;
import snicorp.snd.migration.dto.sandi.MemberDormancy;
import snicorp.snd.migration.mapper.sandi.SandiMemberMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SandiMemberService {
    @Resource
    private SandiMemberMapper sandiMemberMapper;

    public int deleteUser() {
        return sandiMemberMapper.deleteUser();
    }
    public int deleteUserDormancy() {
        return sandiMemberMapper.deleteUserDormancy();
    }

    public int insertMember(Member member) {
        return sandiMemberMapper.insertMember(member);
    }
    public int insertDormacy(MemberDormancy member) {
        return sandiMemberMapper.insertDormacy(member);
    }

    public void resetSeq() {
        sandiMemberMapper.resetSeq();
    }
}
