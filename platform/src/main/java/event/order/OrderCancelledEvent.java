package event.order;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCancelledEvent extends BaseEvent {

    public OrderCancelledEvent(int orderId) {
        super(orderId);
    }
}
