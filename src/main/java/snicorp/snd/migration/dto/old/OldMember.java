package snicorp.snd.migration.dto.old;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OldMember
{
private String memberId;
private String password;
private String memberNm;
private String memberNcNm;
private String profilePicture;
private String moblPhonNo;
private String gender;
private String memberTyCode;
private String mvnCmpnyCode;
private String atmcLoginAt;
private String drmncyAt;
private String drmncyAppnDt;
private String useAt;
private String secessionAt;
private String secessionDt;
private String lastLoginDt;
private String rsaKey;
private String regId;
private Timestamp regDt;
private String modId;
private Timestamp modDt;
private String memberCi;
private String comcertYn;
private String compId;
private String qrValue;
private String pricertYn;
private int passFailCnt;
private String fileGroupNo;
private String addMemberNcNm;
private String birthDay;
private Timestamp passwordChgDt;
private String addFileGroupNo;
private String memberNo;
private String mylgidJoinYn;
private String affmemcd;
private String whTypeCode;
private String codeGrp;
private String tmPushAlrYn;
private String tmPushAlrStTime;
private String tmPushAlrEdTime;
private String tmPushAlrAdmYn;
private String tmPushAlrAdmStTime;
private String tmPushAlrAdmEdTime;
private String admMnlModYn;
}
