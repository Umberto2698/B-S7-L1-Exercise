package lezione26.payloads.devices;

import lezione26.enums.DeviceState;
import lezione26.enums.DeviceType;
import jakarta.validation.constraints.NotNull;

public record DeviceDTO(
        DeviceState state,
        @NotNull(message = "Insert the type of the device.")
        DeviceType type) {
}
