package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LineRepositoryTest {
    @Autowired
    LineRepository lines;

    @Autowired
    private StationRepository stations;

    @Test
    void saveWithLine() {
        Station station = new Station("잠실역");
        station.setLine(new Line("2호선"));

        Station result = stations.save(station);
        stations.flush();
    }

    @Test
    void findByNameWithLine() {
        Station station = stations.findByName("교대역").get();
        assertThat(station).isNotNull();
        assertThat(station.getLine().getName()).isEqualTo("3호선");
    }

    @Test
    void updateWithLine() {
        Station station = stations.findByName("교대역").get();
        station.getLine().setName("2호선");
        stations.flush();

        assertThat(station.getLine().getName()).isEqualTo("2호선");
    }

    @Test
    void removeWithLine() {
        Station station = stations.findByName("교대역").get();
        station.setLine(null);

        assertThat(station.getLine()).isNull();
    }

    @Test
    void findByName() {
        Line line = lines.findByName("3호선").get();
        assertThat(line.getStations()).hasSize(1);
        assertThat(line.getStations().get(0).getName()).isEqualTo("교대역");
    }

    @Test
    void save() {
        Line line = new Line("2호선");
        line.addStation(stations.save(new Station("잠실역")));
        lines.save(line);
        lines.flush();
    }
}