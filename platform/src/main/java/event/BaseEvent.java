package event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public abstract class BaseEvent implements IntegrationEvent {

    private String id;

    private int entityId;

    private LocalDateTime created;

    public BaseEvent(int entityId) {
        this.entityId = entityId;
        id = UUID.randomUUID().toString();
        created = LocalDateTime.now();
    }
}
