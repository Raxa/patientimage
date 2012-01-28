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
package org.openmrs.module.patientimage.web.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;

import org.openmrs.util.OpenmrsUtil;
import org.openmrs.web.controller.PortletController;

public class PatientImagePortletController extends PortletController {

    @Override
    protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
        PersonService ps = Context.getPersonService();
        if (ps.getPersonAttributeTypeByName("Patient Image") == null) {
            PersonAttributeType pat = new PersonAttributeType();
            pat.setName("Patient Image");
            pat.setFormat("java.lang.String");
            pat.setDescription("Stores the filename for the patient image");
            ps.savePersonAttributeType(pat);
            log.info("Created New Person Attribute: Patient Image");
        } else {
            log.info("Person Attribute: Patient Image already exists");
        }

        String patientId = request.getParameter("patientId");
        if (patientId != null) {
            int id = Integer.parseInt(patientId);
            Patient patient = Context.getPatientService().getPatient(id);
            if (patient != null) {
                PersonAttribute attribute = patient.getAttribute(Context.getPersonService().getPersonAttributeTypeByName("Patient Image"));
                if (attribute != null) {
                    File imgFolder = new File(OpenmrsUtil.getApplicationDataDirectory(), "patient_images");
                    if (imgFolder.exists()) {
                        if (!attribute.getValue().trim().equals("")) {
                            File image = new File(imgFolder, attribute.getValue());
                            if (image.exists()) {
                                model.put("patient_image", image.getName());
                            }
                        }
                    }
                }
            }
        }
    }
}
