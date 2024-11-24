package by.polikarpov.backend.entiity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"executor"})
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "date_added")
    private Timestamp dateAdded;

    private String description;
    private String file;
    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private Executor executor;

}
