package dut.udn.vn.thanhhoabook.model.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
