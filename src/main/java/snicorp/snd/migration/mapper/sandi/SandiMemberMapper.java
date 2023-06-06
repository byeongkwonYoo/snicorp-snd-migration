package snicorp.snd.migration.mapper.sandi;

import snicorp.snd.migration.config.SandiConnMapper;
import snicorp.snd.migration.dto.sandi.Member;
import snicorp.snd.migration.dto.sandi.MemberDormancy;

import java.util.List;


@SandiConnMapper("SandiConnMapper")
public interface SandiMemberMapper {


  int deleteUser();
  int deleteUserDormancy();

  int insertMember(Member member);

  int insertDormacy(MemberDormancy member);
  void resetSeq();


}
