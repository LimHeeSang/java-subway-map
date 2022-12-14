package subway.domain.service;

import subway.domain.model.Line;
import subway.domain.model.Station;
import subway.domain.model.dto.LineDto;
import subway.domain.repository.LineRepository;
import subway.domain.repository.StationRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LineService {

    private final StationRepository stationRepository;
    private final LineRepository lineRepository;

    public LineService(StationRepository stationRepository, LineRepository lineRepository) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    public void createLine(String lineName, String startStationName, String endStationName) {
        Station startStation = new Station(startStationName);
        Station endStation = new Station(endStationName);
        stationRepository.save(startStation);
        stationRepository.save(endStation);

        Line line = new Line(lineName, new LinkedList<>(Arrays.asList(startStation, endStation)));
        lineRepository.save(line);
    }

    public List<LineDto> getLinesAndStations() {
        List<Line> lines = lineRepository.findAll();
        return mapToDto(lines);
    }

    private List<LineDto> mapToDto(List<Line> lines) {
        return lines.stream()
                .map(Line::toDto)
                .collect(Collectors.toList());
    }

    public void deleteLine(String lineName) {
        lineRepository.deleteByName(lineName);
    }

    public List<String> getLines() {
        List<Line> lines = lineRepository.findAll();
        return mapToName(lines);
    }

    private static List<String> mapToName(List<Line> lines) {
        return lines.stream()
                .map(Line::getName)
                .collect(Collectors.toList());
    }
}
