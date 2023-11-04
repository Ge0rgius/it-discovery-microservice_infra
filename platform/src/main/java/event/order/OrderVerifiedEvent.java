package event.order;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * {@link OrderVerifiedEvent} is generated after order is validated, all
 * the required fields are filled-in and order is ready to pay
 *
 */
public class OrderVerifiedEvent extends BaseEvent {

    public OrderVerifiedEvent(int orderId) {
        super(orderId);
    }
}
