package dut.udn.vn.thanhhoabook.model.book;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
