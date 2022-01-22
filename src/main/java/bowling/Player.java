package bowling;

import java.util.Objects;
import java.util.Optional;

public class Player {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 3;

    public static final String EXCEPTION_MESSAGE_ENGLISH_NAME = "이름은 영문만 가능합니다.";

    private final String name;

    public Player(String name) {
        validateNameLength(name);
        validateEnglishName(name);
        this.name = toUpperCase(name);
    }

    public String getName() {
        return name;
    }

    private void validateNameLength(String name) {
        Optional.ofNullable(name)
                .map(String::trim)
                .filter(n -> !n.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException(String.format("이름은 %d글자 이상 %d글자 이하만 가능합니다.", MIN_LENGTH, MAX_LENGTH)));
    }

    private void validateEnglishName(String name) {
        int length = name.length();
        for (int i = 0; i < length; i++) {
            if (!(name.charAt(i) >= 65 && name.charAt(i) <= 90) && !(name.charAt(i) >= 97 && name.charAt(i) <= 122)) {
                throw new IllegalArgumentException(EXCEPTION_MESSAGE_ENGLISH_NAME);
            }
        }
    }

    private String toUpperCase(String name) {
        return name.trim().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}