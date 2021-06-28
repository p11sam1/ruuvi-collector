package fi.tkgwf.ruuvi;

import fi.tkgwf.ruuvi.config.Config;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class TestFixture {
    public static final String RSSI_BYTE = "B4";

    /**
     * Refer to https://github.com/ruuvi/ruuvi-sensor-protocols for the full specification
     * @return An example message in data format 2, like hcidump would return it.
     */
    public static String getDataFormat3Message() {
//                                     MAC address (6 bytes)
//                                     |                                   Manufacturer ID 0x0499
//                                     |                                   |     Data format definition (3)
//                                     |                                   |     |  Humidity x2
//                                     |                                   |     |  |  Temperature (1st byte: signed integer; 2nd byte: decimals)
//                                     |                                   |     |  |  |     Pressure (2 bytes)
//                                     |                                   |     |  |  |     |     Acceleration-X (2 bytes)
//                                     |                                   |     |  |  |     |     |     Acceleration-Y (2 bytes)
//                                     |                                   |     |  |  |     |     |     |     Acceleration-Z (2 bytes)
//                                     |                                   |     |  |  |     |     |     |     |     Battery voltage (2 bytes)
//                                     |                                   |     |  |  |     |     |     |     |     |     RSSI
//                                     |                                   |     |  |  |     |     |     |     |     |     |
        return "> 04 3E 21 02 01 03 01 FF EE DD CC BB AA 15 02 01 06 11 FF 99 04 03 49 16 0E BE F8 00 05 FF EA 03 E1 0B BF B4";
    }

    public static void setClockToMilliseconds(final Supplier<Long> timestampSupplier) {
        try {
            final Field clock = Config.class.getDeclaredField("timestampProvider");
            clock.setAccessible(true);
            clock.set(null, timestampSupplier);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
