package by.polikarpov.backend.entiity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"person", "resume", "work"})
public class Executor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(mappedBy = "executor")
    private Resume resume;

    @OneToOne(mappedBy = "executor")
    private Work work;
}
