package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ComplainType")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ComplainType {

    @Id //신고 유형 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complainType_id", updatable = false)
    private Long complainTypeId;

    @Column(name = "complainType", nullable = false, updatable = false, unique = false) //신고 유형
    private String complainType;

    @Builder
    public ComplainType(Long complainTypeId, String complainType) {
        this.complainTypeId = complainTypeId;
        this.complainType = complainType;
    }
}