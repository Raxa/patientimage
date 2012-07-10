package org.openmrs.module.patientimage;

import org.openmrs.BaseOpenmrsMetadata;

/**
 * Copyright 2012, Raxa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * Java Object for patient image -- stores image data, pageId, patientId
 * @author joman
 */
public class PatientImage extends BaseOpenmrsMetadata {

    //patientImageId will be the pageId
    private Integer patientImageId;
    private int patientId;
    private byte[] imageData;
    
    public PatientImage(){
    }
    
    /**
     * Returns id for PatientImage
     * @return patientImageId
     */
    public Integer getId() {
        return patientImageId;
    }

    /**
     * Sets id for PatientImage
     * @param intgr new id
     */
    public void setId(Integer intgr) {
        patientImageId = intgr;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int pId) {
        patientId = pId;
    }

    /**
     * @return the imageData
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * @param imageData the imageData to set
     */
    public void setImageData(byte[] iData) {
        imageData = new byte[iData.length];
        imageData = iData;
    }
    
}
