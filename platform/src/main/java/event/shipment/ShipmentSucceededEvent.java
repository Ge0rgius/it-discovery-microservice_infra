package event.shipment;

import event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShipmentSucceededEvent extends BaseEvent {

    public ShipmentSucceededEvent(int entityId) {
        super(entityId);
    }
}
