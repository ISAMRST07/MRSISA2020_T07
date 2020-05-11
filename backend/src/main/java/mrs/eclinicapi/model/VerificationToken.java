package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class VerificationToken {
    private static final int EXPIRATION = 1;

    @Id
    @Column(length = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vt_seq")
    @GenericGenerator(name = "vt_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "VT")})
    private String id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private UnregisteredUser user;

    public VerificationToken(final String token, UnregisteredUser user) {
        super();
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }


    private LocalDateTime expiryDate;

    private LocalDateTime calculateExpiryDate(int expiryTimeInDays) {
        return LocalDateTime.now().plusDays(expiryTimeInDays);
    }

}