package mrs.eclinicapi.controller;

import mrs.eclinicapi.dto.OnDisapproveVacationCompleteEvent;
import mrs.eclinicapi.model.VacationRequest;
import mrs.eclinicapi.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/vacationRequest")
public class VacationRequestController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private VacationRequestService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VacationRequest>> getAllVacationRequest() {
        List<VacationRequest> vacationRequest = service.findAll();
        if (vacationRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (VacationRequest v : vacationRequest) {
            System.out.println("getall vacationRequest = " + v);
        }
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }

    @RequestMapping(path = "/user/{id}")
    public ResponseEntity<List<VacationRequest>> getVacationRequestForUser(@PathVariable("id") String id) {
        System.out.println("get vacationRequest for user " + id);

        List<VacationRequest> vacationRequest = service.getVacationRequestForUser(id);
        if (vacationRequest == null) {
            System.out.println("vacationRequest not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(vacationRequest);
        System.out.println("printing vacationRequest user");
        for (VacationRequest d : vacationRequest) {
            System.out.println(d);
        }
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }

    @GetMapping(path = "/approve/{id}")
    public ResponseEntity<VacationRequest> approveVacationRequest(@PathVariable("id") String id) {
        System.out.println("approveVacationRequest for user " + id);

        VacationRequest vac = service.findOne(id);
        if (vac == null) {
            System.out.println("vacationRequest not found");
            System.out.println(id);

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        vac.setStatus("approved");
        vac.setReason("approved");
        System.out.println(vac);

        VacationRequest modified = service.addVacationRequest(vac);

        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @PostMapping(path = "/disapprove/{id}")
    public ResponseEntity<VacationRequest> disapproveVacationRequest(@PathVariable("id") String id,
                                                                     @RequestBody String reason) {
        reason = reason.substring(0, reason.length() - 1);    //brise = koje se na string dodaje iz nekog razloga
        reason = reason.replace('+', ' ');
        System.out.println("disapproveVacationRequest for user " + id);
        System.out.println("disapproveVacationRequest for reason " + reason);
        VacationRequest vac = service.findOne(id);
        if (vac == null) {
            System.out.println("vacationRequest not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        vac.setStatus("disapproved");
        vac.setReason(reason);
        System.out.println(vac);

        VacationRequest modified = service.addVacationRequest(vac);

        eventPublisher.publishEvent(new OnDisapproveVacationCompleteEvent(vac.getUser(), reason));

        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<List<VacationRequest>> getVacationRequestForClinic(@PathVariable("id") String id) {
        System.out.println("get vacationRequest for clinic " + id);

        List<VacationRequest> vacationRequest = service.getVacationRequestForClinic(id);
        if (vacationRequest == null) {
            System.out.println("vacationRequest not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(vacationRequest);
        System.out.println("printing vacationRequest clinic");
        for (VacationRequest d : vacationRequest) {
            System.out.println(d);
        }
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VacationRequest> addVacationRequest(@RequestBody VacationRequest vacationRequest) {
        System.out.println("addVacationRequest = " + vacationRequest);
        VacationRequest newVacation = service.addVacationRequest(vacationRequest);
        if (newVacation == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        System.out.println("newVacation = " + newVacation);
        return new ResponseEntity<>(newVacation, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VacationRequest> modifyVacationRequest(@RequestBody VacationRequest vacationRequest) {
        VacationRequest modified = service.modifyVacationRequest(vacationRequest);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVacationRequest(@PathVariable("id") String id) {
        VacationRequest vacationRequest = service.findOne(id);
        if (vacationRequest == null) {
            return new ResponseEntity<>("vacationRequest not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted vacationRequest", HttpStatus.OK);
    }

}
