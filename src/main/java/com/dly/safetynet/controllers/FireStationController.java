package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.repositories.FireStationRepository;
import com.dly.safetynet.services.interfaces.IFireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class FireStationController {
    @Autowired
    private IFireStation fs;
    @Autowired
    private FireStationRepository fireStationRepository;
    /**
     * http://localhost:8080/firestation?stationNumber=<station_number>
     * Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
     * correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
     * couverts par la station numéro 1. La liste doit inclure les informations spécifiques
     * suivantes : prénom, nom, adresse, numéro de téléphone.
     * De plus, elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants
     * (tout individu âgé de 18 ans ou moins) dans la zone desservie.
     */
    @GetMapping(value ="/firestation/stationNumber/{station}")
    public Map<FireStation, List<Person>> getFirestation(@PathVariable("station") String station){
        return fs.personCoverageByFireStation(station);
    }
    //Error creating bean with name 'fireStationService': Unsatisfied dependency expressed through field 'recordService':
    // Error creating bean with name 'medicalRecordService': Unsatisfied dependency expressed through field 'medicalRecordDtoRepository':
    // Error creating bean with name 'medicalRecordDtoRepository' defined in com.dly.safetynet.repositories.MedicalRecordDtoRepository
    // defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration:
    // Not a managed type: class com.dly.safetynet.dto.MedicalRecordDto



//     /**
//     * http://localhost:8080/childAlert?address=<address>
//     * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
//      * La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
//      * membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
//      */
//     /**
//     * http://localhost:8080/phoneAlert?firestation=<firestation_number>
//     * Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de pompiers.
//      * Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
//      */
//
//     /**
//     * http://localhost:8080/fire?address=<address>
//     * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
//      * de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
//      * médicaux (médicaments, posologie et allergies) de chaque personne.
//      */
//
//     /**
//     * http://localhost:8080/flood/stations?stations=<a list of
//     *   station_numbers>
//     * Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper
//      * les personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants,
//      * et faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
//      */
//     /**
//     * http://localhost:8080/personInfolastName=<lastName>
//     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
//      * posologie et allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes
//      * apparaître.
//      */
//     /**
//     * http://localhost:8080/communityEmail?city=<city>
//     * Cette url doit retourner les adresses mail de tous les habitants de la ville.
//     */
//    @GetMapping("/firestation/{stationNumber}=")
//    public void getFireStationById(@PathVariable() Long stationNumber){
//        fireStationRepository.findById(stationNumber);
//    }

}
