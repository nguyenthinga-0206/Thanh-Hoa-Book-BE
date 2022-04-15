package dut.udn.vn.thanhhoabook.model.book;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
