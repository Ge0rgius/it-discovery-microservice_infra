package event.payment;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentFailedEvent extends BaseEvent {

    public PaymentFailedEvent(int paymentId) {
        super(paymentId);
    }
}
