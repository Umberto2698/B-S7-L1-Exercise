package lezione26.payloads.devices;

import jakarta.validation.constraints.NotEmpty;

public record DeviceUpdateInfoDTO(
        @NotEmpty(message = "The state is required.")
        String state) {
}
