package com.example.summa.dao;

import com.example.summa.entity.Appointment;
import com.example.summa.myexceptions.PatientNumberNotFoundException;
import com.example.summa.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Appointment appointment = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?");
            preparedStatement.setInt(1, appointmentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setPatientId(resultSet.getInt("patientId"));
                appointment.setDoctorId(resultSet.getInt("doctorId"));
                appointment.setAppointmentDate(resultSet.getString("appointmentDate"));
                appointment.setDescription(resultSet.getString("description"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?");
            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setPatientId(resultSet.getInt("patientId"));
                appointment.setDoctorId(resultSet.getInt("doctorId"));
                appointment.setAppointmentDate(resultSet.getString("appointmentDate"));
                appointment.setDescription(resultSet.getString("description"));
                appointments.add(appointment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?");
            preparedStatement.setInt(1, doctorId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setPatientId(resultSet.getInt("patientId"));
                appointment.setDoctorId(resultSet.getInt("doctorId"));
                appointment.setAppointmentDate(resultSet.getString("appointmentDate"));
                appointment.setDescription(resultSet.getString("description"));
                appointments.add(appointment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Appointment (appoinmentId,patientId, doctorId, appointmentDate, description) VALUES ( ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1,appointment.getAppointmentId());
            preparedStatement.setInt(2, appointment.getPatientId());
            preparedStatement.setInt(3, appointment.getDoctorId());
            preparedStatement.setString(4, appointment.getAppointmentDate().toString());
            preparedStatement.setString(5, appointment.getDescription());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?");
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setString(3, appointment.getAppointmentDate().toString());
            preparedStatement.setString(4, appointment.getDescription());
            preparedStatement.setInt(5, appointment.getAppointmentId());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?");
            preparedStatement.setInt(1, appointmentId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
