package snicorp.snd.migration;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import snicorp.snd.migration.dto.old.OldMember;
import snicorp.snd.migration.dto.sandi.Member;
import snicorp.snd.migration.service.old.OldMemberService;
import snicorp.snd.migration.service.sandi.SandiMemberService;

import java.util.List;

@SpringBootApplication
public class SandiMigration   implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Resource
    private OldMemberService oldMemberService;

    @Resource
    private SandiMemberService sandiMemberService;

    @Override
    public void run(String... args) throws Exception {
        log.info("===============================================");
        log.info("=========      migration start      ===========");
        log.info("===============================================");


        log.info("=========== 0.SANDI 회원 데이터 삭제  =============");
        sandiMemberService.deleteUser();
        log.info("=========== 0.SANDI 회원 시퀀스 삭제  =============");
        sandiMemberService.resetSeq();

        log.info("=========== 1.SANDI 기존 회원 로드  =============");

        List<OldMember> oldMemberList = oldMemberService.getOldUser();
        log.info("================ 2.SANDI 기존 회원 로드 완료 ===============");
        log.info("======== 전체 회원수 "+oldMemberList.size()+"명 ===========");

        int i=1;
        for(OldMember oldMember : oldMemberList) {


            boolean isClosed = false;
            if("Y".equals(oldMember.getSecessionAt())){
                isClosed = true;
            }
            if("pengsu@mail.com".equals(oldMember.getMemberId())){
                log.debug("this");
            }
            if(oldMember.getModId() == null){
                oldMember.setModId(oldMember.getRegId());
            }
            if(oldMember.getModDt() == null){
                oldMember.setModDt(oldMember.getRegDt());
            }
            Member member = Member.builder()
                                        .email(oldMember.getMemberId())
                                        .password(oldMember.getPassword())
                                        .passwordUpdatedAt(oldMember.getPasswordChgDt())
                                        .nickname(oldMember.getMemberNcNm())
                                        .name(oldMember.getMemberNm())
                                        .mobileNumber(oldMember.getMoblPhonNo())
                                        .gender(oldMember.getGender())
                                        .isClosed(isClosed)
                                        .memberTyCode(oldMember.getMemberTyCode())
                                        .memberCi(oldMember.getMemberCi())
                                        .passFailCnt(oldMember.getPassFailCnt())
                                        .birthday(oldMember.getBirthDay())
                                        .mylgidJoinYn(oldMember.getMylgidJoinYn())
                                        .affmemcd(oldMember.getAffmemcd())
                                        .qrValue(oldMember.getQrValue())
                                        .pricertYn(oldMember.getPricertYn())
                                        .regId(oldMember.getRegId())
                                        .createdAt(oldMember.getRegDt())
                                        .modId(oldMember.getModId())
                                        .updatedAt(oldMember.getModDt())
                                        .build();

            sandiMemberService.insertMember(member);
            log.info("No "+i+" 회원 데이터 마이그레이션 완료====== 유저아이디= "+member.getUserId()+" 회원 아이디 = "+oldMember.getMemberId());

            i++;
//            break;
        }
        log.info("===============================================");
        log.info("=========      migration end        ===========");
        log.info("===============================================");
    }
}
