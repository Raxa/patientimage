/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.patientimage.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;
import sun.misc.BASE64Decoder;

public class PatientImageSave extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("<h1>The Patient Image Save Servlet</h1>");
        String imgString = request.getParameter("image_data");
        String imgName = request.getParameter("image_name");
        if (imgString != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(imgString);
            File imgDir = new File(OpenmrsUtil.getApplicationDataDirectory() + "/patient_images");
            if (!imgDir.exists()) {
                FileUtils.forceMkdir(imgDir);
            }
            if (imgName != null && !imgName.isEmpty()) {
                int patientId = Integer.parseInt(imgName);
                PatientService patientService = Context.getPatientService();
                Patient patient = patientService.getPatient(patientId);
                if (patient != null) {
                    FileOutputStream fos = new FileOutputStream(imgDir + "/" + patient.getPatientIdentifier().getIdentifier() + ".jpg");
                    fos.write(decodedBytes);
                    fos.close();
                    PersonAttribute attribute = patient.getAttribute(Context.getPersonService().getPersonAttributeTypeByName("Patient Image"));
                    if (attribute == null) {
                        attribute = new PersonAttribute(Context.getPersonService().getPersonAttributeTypeByName("Patient Image"), "");
                    }
                    attribute.setValue(patient.getPatientIdentifier().getIdentifier() + ".jpg");
                    patient.addAttribute(attribute);
                    patientService.savePatient(patient);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Saves Photo Received from Flex Component";
    }
    // </editor-fold>
}
