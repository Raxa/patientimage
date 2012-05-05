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
package org.openmrs.module.patientimage;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;
import org.openmrs.util.OpenmrsUtil;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class PatientImageActivator implements Activator {

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * @see org.openmrs.module.Activator#startup()
     */
    public void startup() {
        log.info("Starting Patient Image Module");
        File imgFolder = new File(OpenmrsUtil.getApplicationDataDirectory(), "/patient_images");
        if (!imgFolder.exists()) {
            try {
                FileUtils.forceMkdir(imgFolder);
                log.info("Created Folder to Store patient_images");
            } catch (IOException ex) {
                log.error(ex);
            }
        } else {
            log.info("Folder for patient_images Already Exists");
        }
    }

    /**
     * @see org.openmrs.module.Activator#shutdown()
     */
    public void shutdown() {
        log.info("Shutting down Patient Image Module");
    }
}
