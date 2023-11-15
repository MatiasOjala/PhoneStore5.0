package s23.PhoneStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PhoneConditionRepository extends CrudRepository<PhoneCondition, Long> {

	List<PhoneCondition> findByName(String name);


}
