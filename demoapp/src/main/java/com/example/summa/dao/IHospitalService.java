package com.example.summa.dao;

import com.example.summa.entity.Appointment;
import com.example.summa.myexceptions.PatientNumberNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface IHospitalService {
    Appointment getAppointmentById(int appointmentId) throws SQLException;
    List<Appointment> getAppointmentsForPatient(int patientId) throws SQLException, PatientNumberNotFoundException;
    List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException;
    boolean scheduleAppointment(Appointment appointment) throws SQLException;
    boolean updateAppointment(Appointment appointment) throws SQLException;
    boolean cancelAppointment(int appointmentId) throws SQLException;
}
