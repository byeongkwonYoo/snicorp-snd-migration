package snicorp.snd.migration.mapper.sandi;

import snicorp.snd.migration.config.SandiConnMapper;
import snicorp.snd.migration.dto.sandi.Member;

import java.util.List;


@SandiConnMapper("SandiConnMapper")
public interface SandiMemberMapper {


  int deleteUser();

  int insertMember(Member member);

  void resetSeq();


}
