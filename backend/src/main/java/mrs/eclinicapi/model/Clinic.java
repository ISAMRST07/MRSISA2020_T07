package mrs.eclinicapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mrs.eclinicapi.generator.IdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Clinic {

    @Id
    @Column(length=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c_seq")
    @GenericGenerator(name = "c_seq",
            strategy = "mrs.eclinicapi.generator.IdGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "C")})
    private String id;

    private String name;
    private String description;
    private String address;

    @Embedded
    private Coordinates coordinates;

    @JsonIgnoreProperties("clinic")
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClinicAdministrator> clinicAdministrator;

    @JsonIgnoreProperties("clinic")
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClinicRoom> clinicRoom = new HashSet<>();

    @JsonIgnoreProperties("clinic")
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Doctor> doctors = new HashSet<>();

    @JsonIgnoreProperties("clinic")
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Nurse> nurses = new HashSet<>();

    @JsonIgnoreProperties("clinic")
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private List<Patient> patients;

    @JsonIgnoreProperties("clinic")
    @ManyToOne
    private ClinicalCentarAdministrator admin;

    @Override
    public String toString() {
        return "Clinic [id=" + id + ", name=" + name + ", clinicAdministrator=" + clinicAdministrator + ", clinicRoom="
                + clinicRoom + ", doctors=" + doctors + ", nurses=" + nurses + ", patients=" + patients + ", admin="
                + admin + "]";
    }
}
