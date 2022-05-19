package subway.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, String> {

    Optional<Station> findByName(String name);

    Optional<Station> findById(Long id);
}
