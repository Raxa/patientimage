package org.openmrs.module.patientimage;
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
import java.util.List;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;

/*
 * Interface of CRUD operations for patient images
 * Creates patientimages/<patientid> folder and save each image as pageid
 * Provides CRUD methods for these images
 */
public interface PatientImageService extends OpenmrsService{
    
    /*
     * Gets all PatientImages asoociated with patientId
     */
    @Authorized({"View Patient Images"})
    public List<PatientImage> getAllImages(int patientId);

    /**
     * Gets PatientImage associated with patientId and pageId
     * @param patientId
     * @param pageId
     * @return patient image
     */
    @Authorized({"View Patient Images"})
    public PatientImage getPatientImage(int patientId, int pageId);
    
    /*
     * Creates patient Image in patientimages/<patientid> folder
     * Name of the images is pageid
     */
    @Authorized({"Create Patient Images"})
    public PatientImage createImage(PatientImage p);
        
    /*
     * Creates patient Image in patientimages/<patientid> folder
     * Name of the images is pageid
     */
    @Authorized({"Create Patient Images"})
    public PatientImage createImage(int patientId, byte[] imageData);
        
    /*
     * Edit existing patient image
     */
    @Authorized({"Edit Patient Images"})
    public PatientImage updateImage(PatientImage p);
    
    /*
     * Deletes patient image
     */
    @Authorized({"Delete Patient Images"})
    public void deleteImage(PatientImage p);
    
    /*
     * Returns image path as a string
     */
    @Authorized({"View Patient Images"})
    public String getPath(int patientId, int pageId);
    
    /*
     * Returns patient image directory
     */
    @Authorized({"View Patient Images"})
    public String getPath(int patientId);

    /*
     * Returns image path as a string
     */
    @Authorized({"View Patient Images"})
    public String getPath(PatientImage p);
}
