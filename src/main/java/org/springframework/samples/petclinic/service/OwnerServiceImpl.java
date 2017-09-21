package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	OwnerRepository repo;

	@Override
	public Owner findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Owner> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName) {
		return repo.findByFirstNameContainingOrLastNameContaining(firstName, lastName);
	}

	@Override
	public List<Owner> searchOwner(String query) {
		return repo.searchOwner(query);
	}

	@Override
	public List<Owner> findByOrderByLastName() {
		return repo.findByOrderByLastName();
	}


}
