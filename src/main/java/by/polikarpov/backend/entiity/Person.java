package by.polikarpov.backend.entiity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"image", "executor"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username_tg")
    private String usernameTG;

    private String phone;

    @Column(name = "chat_id")
    private long chatId;

    @OneToOne(mappedBy = "person")
    private ImageOfPerson image;

    @OneToOne(mappedBy = "person")
    private Executor executor;

}
