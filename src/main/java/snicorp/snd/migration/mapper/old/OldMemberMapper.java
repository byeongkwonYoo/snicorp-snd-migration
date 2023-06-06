package snicorp.snd.migration.mapper.old;

import snicorp.snd.migration.config.OldConnMapper;
import snicorp.snd.migration.dto.old.OldMember;
import snicorp.snd.migration.dto.old.OldMobileMember;

import java.util.List;


@OldConnMapper("OldConnMapper")
public interface OldMemberMapper {

  List<OldMember> getOldUser();

  OldMobileMember getOldMobileUser(String member_id);
}
