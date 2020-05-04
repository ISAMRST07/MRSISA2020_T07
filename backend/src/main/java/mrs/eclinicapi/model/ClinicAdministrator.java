package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ClinicAdministrator {


    @Id
@Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ca_seq")
    @GenericGenerator(name = "ca_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "CA")})
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    public Clinic clinic;

}
