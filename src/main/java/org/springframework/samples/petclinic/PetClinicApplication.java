/*
 * Copyright 2002-2014 the original author or authors.
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

package org.springframework.samples.petclinic;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.OwnerService;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
    }
    
    @Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository, OwnerService ownerservice) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			
			Vet vet = new Vet();
			vet.setFirstName("María");
			vet.setLastName("Pérez");
			log.info("Se ha creado el veterinario");
			
			int idVet = vetRepository.save(vet).getId();
			log.info("Se ha persistido en la BBDD");
			
			Vet nuevoVet = vetRepository.findById(idVet);
			log.info("El nuevo veterinario es " + nuevoVet.toString());
			
			Specialty especialidad = specialityRepository.findFirstByName("dentistry");
			nuevoVet.addSpecialty(especialidad);
			log.info("Hemos añadido una especialidad");
			
			vetRepository.save(nuevoVet);
			log.info("Se ha persistido en la BBDD");
			
			/*
			 * SOLUCIÓN PROFESOR
			log.info("*****************************************************");
		    log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
		    log.info("*****************************************************");

		    log.info("Creamos un objeto Vet");
		    Vet vet = new Vet();
		    vet.setFirstName("Sergio");
		    vet.setLastName("Raposo Vargas");

		    log.info("Persistimos en BBDD");
		    vet = vetRepository.save(vet);

		    log.info("Comprobamos que se ha creado correctamente");
		    Vet vetAux = vetRepository.findOne(vet.getId());
		    log.info(vetAux.toString());

		    log.info("Editamos el objeto y añadimos una Speciality");
		    Specialty s = specialityRepository.findOne(1);
		    vet.addSpecialty(s);
		    vet = vetRepository.save(vet);
		    log.info(vet.toString());

		    log.info("Listamos todos los veterinarios");
		    for(Vet v: vetRepository.findAll()){
		    	log.info("Vet: "+v);
		    }
			 */
			
			log.info("Listado de todos los veterinarios");
			List<Vet> listadoVet = vetRepository.findAll();
			for(Vet v: listadoVet) {
				log.info("Vet: "+v);
			}
			
			log.info("Obtener una lista de Vets filtrando por lastName");
			List<Vet> listadoVetLastName = vetRepository.findByLastName("Ortega");
			for(Vet v: listadoVetLastName) {
				log.info("Vet: "+v);
			}
			
			log.info("Obtener una lista de Vets filtrando por firstname y lastname");
			List<Vet> listadoVetFirstNameAndLastName = vetRepository.findByFirstNameAndLastName("Henry", "Stevens");
			for(Vet v: listadoVetFirstNameAndLastName) {
				log.info("Vet: "+v);
			}
			
			log.info("Obtener una lista de Vets filtrando por firstname o lastname");
			List<Vet> listadoVetFirstNameOrLastName = vetRepository.findByFirstNameOrLastName("Henry", "Jenkins");
			for(Vet v: listadoVetFirstNameOrLastName) {
				log.info("Vet: "+v);
			}
			
			/*Solución profesor
			 * 
			 * 			log.info("Filtramos por lastName=Raposo Vargas");
		    for(Vet v: vetRepository.findByLastName("Raposo Vargas")){
		    	log.info("Vet: "+v);
		    }
		    
		    log.info("Filtramos por lastName=Raposo Vargas y firstName=Sergio");
		    for(Vet v: vetRepository.findByFirstNameAndLastName("Sergio", "Raposo Vargas")){
		    	log.info("Vet: "+v);
		    }
		    
		    log.info("Filtramos por firstName o lastname");
		    for(Vet v: vetRepository.findByFirstNameOrLastName("Sergio", "Sergio")){
		    	log.info("Vet: "+v);
		    }

			*/

			List<Vet> radiologos = vetRepository.findBySpecialityName("radiology");
			for(Vet v: radiologos) {
				log.info("Vet: "+v);
			}
			
			List<Owner> propietario = ownerservice.findByFirstNameContainingOrLastNameContaining("S", "D");
			for(Owner p: propietario) {
				log.info("Owner: "+p);
			}
			
		};
	}
    
}