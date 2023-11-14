package s23.PhoneStore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s23.PhoneStore.domain.Phone;
import s23.PhoneStore.domain.PhoneRepository;

// REST rajapinnan controlleri

@RestController
public class PhoneRestController {
	@Autowired
	PhoneRepository phoneRepository;

	// Palauta lista puhelimista.
	@GetMapping("/phones")
	public Iterable<Phone> getPhones() { // Hae ja palauta puhelimet.
		return phoneRepository.findAll();
	}

	// Lisää uusi puhelin.
	@PostMapping("add")
	Phone newPhone(@RequestBody Phone newPhone) {
		return phoneRepository.save(newPhone);
	}



	// Etsi yksi puhelin ja palauta se.
	@GetMapping("/phones/{id}")
	Optional<Phone> getPhone(@PathVariable Long id) {
		return phoneRepository.findById(id);
	}

}