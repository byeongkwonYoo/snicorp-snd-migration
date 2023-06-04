package snicorp.snd.migration.dto.sandi;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member{
    private int userId;
    private String email;
    private String emailVerifiedAt;
    private String password;
    private Timestamp passwordUpdatedAt;
    private String nickname;
    private String name;
    private String mobileNumber;
    private String gender;
    private Boolean isClosed;
    private String memberTyCode;
    private String memberCi;
    private int passFailCnt;
    private String adminNickname;
    private String birthday;
    private String mylgidJoinYn;
    private String affmemcd;
    private String qrValue;
    private String pricertYn;
    private String regId;
    private Timestamp createdAt;
    private String modId;
    private Timestamp updatedAt;

}