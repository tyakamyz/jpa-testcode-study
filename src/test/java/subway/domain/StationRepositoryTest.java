package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    private StationRepository stations;

    @Test
    void save() {
        Station expected = new Station("잠실역");
        Station actual = stations.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void findByName() {
        String expected = "잠실역";
        stations.save(new Station(expected));
        String actual = stations.findByName(expected).get().getName();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void identity_name() {
        Station station1 = stations.save(new Station("잠실역"));
        Station station2 = stations.findByName("잠실역").get();

        assertThat(station1).isSameAs(station2);
    }

    @Test
    void identity_id() {
        Station station1 = stations.save(new Station("잠실역"));
        Station station2 = stations.findById(station1.getId()).get();

        assertThat(station1).isSameAs(station2);
    }

    @Test
    void update() {
        Station station1 = stations.save(new Station("잠실역"));
        station1.setName("몽성토성역");
        Station station2 = stations.findByName("몽성토성역").get();

        assertThat(station2).isNotNull();
    }
}