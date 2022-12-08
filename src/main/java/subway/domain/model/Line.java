package subway.domain.model;

import subway.domain.model.validator.LineValidator;

import java.util.Objects;

public class Line {

    private final String name;

    public Line(String name) {
        LineValidator.validate(name);
        this.name = name;
    }

    public boolean isEqual(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
