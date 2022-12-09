package subway.domain.controller;

import subway.view.InputView;
import subway.domain.controller.feature.MainFeature;
import subway.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class SubwayController {

    private final Map<MainFeature, Controller> controllers;

    public SubwayController(StationController stationController, LineController lineController,
                            SectionController sectionController, LinePrintController linePrintController) {
        controllers = new HashMap<>();
        controllers.put(MainFeature.STATION_MANAGEMENT, stationController);
        controllers.put(MainFeature.LINE_MANAGEMENT, lineController);
        controllers.put(MainFeature.SECTION_MANAGEMENT, sectionController);
        controllers.put(MainFeature.LINE_PRINT, linePrintController);
        controllers.put(MainFeature.QUIT, () -> {});
    }

    public void run() {
        MainFeature mainFeature;
        do {
            OutputView.printMainFeature();
            mainFeature = InputView.inputMainFeature();
            Controller controller = controllers.get(mainFeature);
            controller.run();
        } while (!mainFeature.isQuit());
    }
}
