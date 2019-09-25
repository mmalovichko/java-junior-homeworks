package unit1;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemperatureConverter {
    private final Scanner in;
    private final PrintStream out;

    private static final Pattern TEMPERATURE_AND_UNIT = Pattern.compile("(\\d+)(\\D)");
    private static final String EXIT_COMMAND = "exit";
    private static final String EXPECTED_VALUES_EXAMPLE = "25C, 10F, 100K (digits + unit)";
    private static final double KELVIN_FACTOR = 273.15;
    private static final double FAHRENHEIT_COEFFICIENT = 5d/9d;
    private static final double FAHRENHEIT_REVERSE_COEFFICIENT = 9d/5d;
    private static final double FAHRENHEIT_FACTOR = 32;


    public TemperatureConverter() {
        in = new Scanner(System.in);
        out = System.out;
        out.println(String.format("Welcome to Temperature converter. Supported units: %s", TemperatureUnit.names()));
        out.println(String.format("Examples of input: %s", EXPECTED_VALUES_EXAMPLE));
        out.println(String.format("Provide input to convert. To exit input: %s", EXIT_COMMAND));
    }

    public void start() {
        while (in.hasNext()) {
            final String input = in.next();
            if (Objects.equals(input, EXIT_COMMAND)) {
                out.println("Buy buy");
                return;
            }

            final Matcher matcher = TEMPERATURE_AND_UNIT.matcher(input);
            if (matcher.matches() && matcher.groupCount() == 2) {

                final String rawValue = matcher.group(1);
                final String rawUnit = matcher.group(2);

                int value;
                TemperatureUnit unit;

                try {
                    value = Integer.parseInt(rawValue);
                } catch (NumberFormatException e) {
                    out.println(String.format("Can't parse integer number from value %s", rawValue));
                    continue;
                }

                final Optional<TemperatureUnit> parsed = TemperatureUnit.fromName(rawUnit);
                if (!parsed.isPresent()) {
                    out.println(String.format("Can't parse temperature unit from value %s. Expected either one of: %s", rawUnit, TemperatureUnit.names()));
                    continue;
                }
                unit = parsed.get();
                convertAndPrint(value, unit);
            } else {
                out.println(String.format("Invalid input, expected: %s", EXPECTED_VALUES_EXAMPLE));
            }
        }
    }

    private void convertAndPrint(int value, TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS:
                out.println(String.format("{K: %.2f, F: %.2f}", celsiusToKelvin(value), celsiusToFahrenheit(value)));
                break;
            case KELVIN:
                out.println(String.format("{C: %.2f, F: %.2f}", kelvinToCelsius(value), kelvinToFahrenheit(value)));
                break;
            case FAHRENHEIT:
                out.println(String.format("{C: %.2f, K: %.2f}", fahrenheitToCelsius(value), fahrenheitToKelvin(value)));
                break;
        }
    }

    private double celsiusToKelvin(int val) {
        return val + KELVIN_FACTOR;
    }

    private double celsiusToFahrenheit(int val) {
        return (val * FAHRENHEIT_REVERSE_COEFFICIENT) + FAHRENHEIT_FACTOR;
    }

    private double kelvinToCelsius(int val) {
        return val - KELVIN_FACTOR;
    }

    private double kelvinToFahrenheit(int val) {
        return (val - KELVIN_FACTOR) * FAHRENHEIT_REVERSE_COEFFICIENT + FAHRENHEIT_FACTOR;
    }

    private double fahrenheitToCelsius(int val) {
        return (val - FAHRENHEIT_FACTOR) * FAHRENHEIT_COEFFICIENT;
    }

    private double fahrenheitToKelvin(int val) {
        return (val - FAHRENHEIT_FACTOR) * FAHRENHEIT_COEFFICIENT + KELVIN_FACTOR;
    }
}
