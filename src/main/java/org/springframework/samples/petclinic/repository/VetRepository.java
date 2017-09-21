/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Integer> {
	public Vet findById(int Id);
	public List<Vet> findByLastName(String lastname);
	public List<Vet> findByFirstNameAndLastName(String firstname, String lastname);
	public List<Vet> findByFirstNameOrLastName(String firstname, String lastname);

	/* Lista de veterinarios para la especialidad "radiology" */
	@Query("select distinct v from Vet v join fetch v.specialties s where s.name=:name")
	public List<Vet> findBySpecialityName(@Param("name") String name);

}

