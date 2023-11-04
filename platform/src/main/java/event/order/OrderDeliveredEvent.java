package event.order;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveredEvent extends BaseEvent {

    public OrderDeliveredEvent(int orderId) {
        super(orderId);
    }
}
