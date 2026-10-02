package com.vaxicare.controller;

import com.vaxicare.dao.AppointmentDAO;
import com.vaxicare.exception.VaxiCareException;
import com.vaxicare.model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * REST Endpoint for Hospital Medical Staff to fetch scheduled appointments.
 */
@WebServlet(name = "AdminAppointmentsServlet", urlPatterns = {"/admin/appointments", "/api/admin/appointments"})
public class AdminAppointmentsServlet extends HttpServlet {
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = resp.getWriter();

        try {
            List<Appointment> list = appointmentDAO.getAllAppointments();
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                Appointment a = list.get(i);
                json.append("{")
                    .append("\"bookingId\":\"BK-").append(a.getAppointmentId()).append("\",")
                    .append("\"patientName\":\"").append(escape(a.getPatientName())).append("\",")
                    .append("\"contact\":\"").append(escape(a.getPatientPhone())).append("\",")
                    .append("\"vaccine\":\"").append(escape(a.getVaccineName())).append("\",")
                    .append("\"centre\":\"").append(escape(a.getCentreName())).append("\",")
                    .append("\"date\":\"").append(a.getAppointmentDate()).append("\",")
                    .append("\"doseNumber\":").append(a.getDoseNumber()).append(",")
                    .append("\"status\":\"").append(escape(a.getStatus())).append("\"")
                    .append("}");
                if (i < list.size() - 1) json.append(",");
            }
            json.append("]");
            out.write(json.toString());
        } catch (VaxiCareException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }
}
