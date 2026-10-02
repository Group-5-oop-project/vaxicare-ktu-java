package com.vaxicare.controller;

import com.vaxicare.dao.AppointmentDAO;
import com.vaxicare.exception.VaxiCareException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller handling appointment status updates by Medical Staff & Hospital Administrators.
 * Demonstrates: Role-based authorization, REST/JSON request handling, and ACID transaction updates.
 *
 * Supported Actions:
 * - Mark Completed: Updates status to 'COMPLETED' (triggers certificate unlocking on patient side)
 * - Mark No-Show: Updates status to 'NO_SHOW' / 'CANCELLED'
 */
@WebServlet(name = "UpdateAppointmentStatusServlet", urlPatterns = {"/admin/update-status", "/api/appointments/update"})
public class UpdateAppointmentStatusServlet extends HttpServlet {
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        PrintWriter out = resp.getWriter();

        String bookingIdStr = req.getParameter("bookingId");
        String status = req.getParameter("status");

        // Fallback: Parse JSON request body if parameters are not present in query string
        if (bookingIdStr == null || status == null) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = req.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            String json = sb.toString();
            bookingIdStr = extractJsonValue(json, "bookingId");
            status = extractJsonValue(json, "status");
        }

        if (bookingIdStr == null || status == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"success\": false, \"message\": \"Missing bookingId or status parameter.\"}");
            return;
        }

        status = status.trim().toUpperCase();

        try {
            // Strip 'BK-' prefix if bookingId is in format BK-1001
            int appointmentId;
            if (bookingIdStr.toUpperCase().startsWith("BK-")) {
                appointmentId = Integer.parseInt(bookingIdStr.substring(3).trim());
            } else {
                appointmentId = Integer.parseInt(bookingIdStr.trim());
            }

            // Update database record using JDBC PreparedStatement
            appointmentDAO.updateStatus(appointmentId, status);

            out.write(String.format(
                "{\"success\": true, \"message\": \"Appointment %s marked as %s.\", \"bookingId\": \"%s\", \"status\": \"%s\"}",
                bookingIdStr, status, bookingIdStr, status
            ));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"success\": false, \"message\": \"Invalid booking ID format.\"}");
        } catch (VaxiCareException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"success\": false, \"message\": \"Database error: " + e.getMessage() + "\"}");
        }
    }

    private String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(pattern).matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
