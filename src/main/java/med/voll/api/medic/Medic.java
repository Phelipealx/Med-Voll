package med.voll.api.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medic")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Medic(RegisterMedicDTO medicDTO) {
        this.name = medicDTO.name();
        this.email = medicDTO.email();
        this.phone = medicDTO.phone();
        this.crm = medicDTO.crm();
        this.specialty = medicDTO.specialty();
        this.address = new Address(medicDTO.address());
    }
}
