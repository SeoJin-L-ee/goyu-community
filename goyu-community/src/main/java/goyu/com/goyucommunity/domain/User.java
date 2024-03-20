package goyu.com.goyucommunity.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name = "Unknown";

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "phonenumber", nullable = false, unique = true)
    private String phonenumber = "Unknown";

    @Column(name = "birth", updatable = false)
    private String birth;

    @Column(name = "gender", updatable = false)
    private String gender;

    @Column(name = "college")
    private String college;

    @Column(name = "state", nullable = false)
    private String state = "Unknown";

    @Column(name = "role", nullable = false, unique = true)
    private String role = "Unknown";

    @Builder
    public User(String email, String password, String name, String auth, String nickname, String phonenumber, String birth, String gender, String college, String state, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phonenumber = phonenumber;
        this.birth = birth;
        this.gender = gender;
        this.college = college;
        this.state = state;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }
    // 사용자의 권한 리스트 반환
    // 예제 코드는 사용자 이외의 권환이 없기 때문에 "user"권환을 담아 반환

    @Override
    public String getUsername() {
        return email;
    }
    // 식별할 수 있는 사용자 이름 반환
    // email이 unique하기 때문이 email return

    @Override
    public String getPassword() {
        return password;
    }
    // password는 암호화하여 저장해야함.

    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료 x
    }
    // 계정 만료 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금 x
    }
    // 계정 잠금 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료 x
    }
    // 비밀번호 만료 여부
    @Override
    public boolean isEnabled() {
        return true; // 사용 o
    }
    // 계정 사용 가능 여부

    public User update(String password, String name, String nickname, String phonenumber,
                       String college, String state, String role) {
        this.password = (password != null) ? password : this.password;
        this.name = (name != null) ? name : this.name;
        this.nickname = (nickname != null) ? nickname : this.nickname;
        this.phonenumber = (phonenumber != null) ? phonenumber : this.phonenumber;
        this.college = (college != null) ? college : this.college;
        this.state = (state != null) ? state : this.state;
        this.role = (role != null) ? role : this.role;

        return this;
    }
}
