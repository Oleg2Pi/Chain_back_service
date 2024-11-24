package by.polikarpov.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"executor", "activityArea", "userStatus", "workExperience"})
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "information_yourself")
    private String informationYourself;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private Executor executor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_area_id")
    private ActivityArea activityArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_status_id")
    private UserStatus userStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_experience_id")
    private WorkExperience workExperience;

}
