package com.Scalable.doctor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Doctor DoctorID;
     private String appointmentTime;

    private Patient PatientId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Patient getPatientId() {
        return PatientId;
    }

    public void setPatientId(Patient patientId) {
        PatientId = patientId;
    }

    public Doctor getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(Doctor doctorID) {
        DoctorID = doctorID;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }



    public Appointment() {
    }

    public Appointment(Patient patientid, Doctor doctorid, String appointment) {
        this.PatientId = patientid;
        this.DoctorID = doctorid;
        this.appointmentTime = appointment;
    }

    }

