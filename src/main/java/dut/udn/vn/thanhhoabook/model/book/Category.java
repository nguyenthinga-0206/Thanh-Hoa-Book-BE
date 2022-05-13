package dut.udn.vn.thanhhoabook.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categoryList")
    @JsonIgnore
    private List<Book> bookList;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userCreateFlag;

    private LocalDateTime timeCreateFlag;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
