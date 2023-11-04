package event.order;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCompletedEvent extends BaseEvent {

    public OrderCompletedEvent(int orderId) {
        super(orderId);
    }
}
