package subway.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, String> {

    Station findByName(String name);
}
