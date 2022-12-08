package subway.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subway.domain.model.dto.LineDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LineTest {

    private Line line;

    @BeforeEach
    void setUp() {
        line = new Line("2호선");
        line.createSections(List.of(new Station("교대역"), new Station("강남역"), new Station("역삼역")));
    }

    @Test
    void 노선에_구간을_관리() {
        LineDto lineDto = line.toDto();
        assertThat(lineDto.getLineName()).isEqualTo("2호선");
        assertThat(lineDto.getStations()).containsExactly("교대역", "강남역", "역삼역");
    }

    @Test
    void 노선에_역을_추가() {
        line.addSection(2, new Station("잠실역"));
        LineDto lineDto = line.toDto();

        assertThat(lineDto.getLineName()).isEqualTo("2호선");
        assertThat(lineDto.getStations()).containsExactly("교대역", "강남역", "잠실역", "역삼역");
    }

    @Test
    void 없는_구간번호일_경우_예외발생() {
        assertThatThrownBy(() -> line.addSection(3, new Station("잠실역")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 번호는 없는 구간입니다.");
    }

    @Test
    void 노선에_구간_삭제() {
        line.deleteStation("강남역");
        LineDto lineDto = line.toDto();
        assertThat(lineDto.getStations()).containsExactly("교대역", "역삼역");
    }
}