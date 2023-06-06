package snicorp.snd.migration.dto.sandi;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDormancy {
    private int userId;
    private String packingData;
    private Timestamp createdAt;
}