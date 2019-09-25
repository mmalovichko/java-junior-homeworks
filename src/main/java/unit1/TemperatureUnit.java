package unit1;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public enum TemperatureUnit {
    CELSIUS("C"),
    KELVIN("K"),
    FAHRENHEIT("F");

    String name;

    TemperatureUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String names() {
        return Arrays.stream(TemperatureUnit.values()).map(TemperatureUnit::getName).collect(Collectors.joining(","));
    }

    public static Optional<TemperatureUnit> fromName(String name) {
        for (TemperatureUnit value : TemperatureUnit.values()) {
            if (value.name.equalsIgnoreCase(name)) return Optional.of(value);
        }
        return Optional.empty();
    }
}
