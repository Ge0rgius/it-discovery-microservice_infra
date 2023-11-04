package event;

import java.time.LocalDateTime;

public interface IntegrationEvent {

    String getId();

    int getEntityId();

    LocalDateTime getCreated();

    //TODO add source of the event
    //TODO add event type
}
