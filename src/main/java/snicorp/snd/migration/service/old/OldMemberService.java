package snicorp.snd.migration.service.old;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import snicorp.snd.migration.dto.old.OldMember;
import snicorp.snd.migration.dto.old.OldMobileMember;
import snicorp.snd.migration.mapper.old.OldMemberMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldMemberService {
    @Resource
    private OldMemberMapper oldMemberMapper;

    public List<OldMember> getOldUser(){
        return oldMemberMapper.getOldUser();
    }
    public OldMobileMember getOldMobileUser(String member_id){
        return oldMemberMapper.getOldMobileUser(member_id);
    }

}