package lezione26.exceptions;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(UUID id) {
        super("Nessun elemento con questo id: " + id);
    }
}
