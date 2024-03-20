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
    private Long complainType_id;

    @Column(name = "complainType", nullable = false, updatable = false, unique = false) //신고 유형
    private String complainType;

    @Builder
    public ComplainType(Long complainType_id, String complainType) {
        this.complainType_id = complainType_id;
        this.complainType = complainType;
    }
}