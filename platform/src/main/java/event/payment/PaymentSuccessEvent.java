package event.payment;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentSuccessEvent extends BaseEvent {

    private int orderId;

    public PaymentSuccessEvent(int paymentId, int orderId) {
        super(paymentId);
        this.orderId = orderId;
    }
}
