package event.order;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPayedEvent extends BaseEvent {

    public OrderPayedEvent(int orderId) {
        super(orderId);
    }
}
