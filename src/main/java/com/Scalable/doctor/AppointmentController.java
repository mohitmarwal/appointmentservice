package com.Scalable.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@RestController
@RequestMapping("/Appointments")
@Controller
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id") Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/createappointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody ExampleRequest[] requests) {

        String Doctorid = null;
        String PatientId = null;
        String appointmenttime = null;
        for (ExampleRequest request : requests) {
            Doctorid = request.getString1();
             PatientId = request.getString2();
             appointmenttime = request.getString3();

            // do something with the strings
        }


        RestTemplate restTemplate = new RestTemplate();
        String doctorResourceUrl
                = "http://doctor-service:8082/Doctors/getdoctorbyid/" + Doctorid;
        Doctor doctor = restTemplate
                .getForObject(doctorResourceUrl , Doctor.class);
        String patientResourceUrl
                = "http://patient-service:8081/patients/" + PatientId;
        Patient patient = restTemplate
                .getForObject(patientResourceUrl , Patient.class);

        Appointment appointment = new Appointment(patient,doctor,appointmenttime);
        Appointment createappointment  = appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createappointment);
    }

    @PutMapping("/updateappointment/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appointment) {
        Appointment existingappointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        appointment.setAppointmentTime(appointment.getAppointmentTime());
        appointment.setDoctorID(appointment.getDoctorID());
        appointment.setPatientId(appointment.getPatientId());
        appointment.setAppointmentTime(appointment.getAppointmentTime());
        Appointment updatedappointment = appointmentRepository.save(existingappointment);
        return ResponseEntity.ok(updatedappointment);
    }
    @GetMapping("/hello")
    public String gethello() {
        return "hello world";
    }
    @DeleteMapping("/deleteappointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        appointmentRepository.delete(appointment);
        appointmentRepository.delete(appointment);
        return ResponseEntity.noContent().build();
    }
}

class ExampleRequest implements Serializable {
    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    private String string1;
    private String string2;
    private String string3;

    // getters and setters
}