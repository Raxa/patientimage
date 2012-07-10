package org.openmrs.module.patientimage.impl;

/**
 * Copyright 2012, Raxa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientimage.PatientImage;
import org.openmrs.module.patientimage.PatientImageService;
import org.openmrs.util.OpenmrsUtil;

/**
 * Implements functions from PatientImageService
 */
public class PatientImageServiceImpl implements PatientImageService {

    protected final Log log = LogFactory.getLog(getClass());
    private static final String IMGDIR = "patientimages";
    // A mapping of patientId to the next potential page
    private Map<Integer, Integer> nextPages;

    private Boolean isValidPatientImage(PatientImage p) {
        Patient patient = Context.getPatientService().getPatient(p.getPatientId());
        //TODO: check if pageId is valid
        if (patient == null) {
            return false;
        }
        return (patient.isPatient());
    }

    private PatientImage saveOrUpdateImage(PatientImage p) {
        if (!isValidPatientImage(p)) {
            log.error("PatientImage is not valid");
            return new PatientImage();
        }
        File imgDir = new File(OpenmrsUtil.getApplicationDataDirectory() + System.getProperty("file.separator") + IMGDIR);
        File patientDir = new File(getPath(p.getPatientId()));
        File img = new File(getPath(p.getId()));
        try {
            if (!imgDir.exists()) {
                FileUtils.forceMkdir(imgDir);
            }
            if (!patientDir.exists()) {
                FileUtils.forceMkdir(patientDir);
            }
        } catch (IOException e) {
            log.error(e);
        }
        try {
            FileOutputStream fos = new FileOutputStream(patientDir + System.getProperty("file.separator") + p.getId() + ".jpg");
            fos.write(p.getImageData());
            fos.close();
        } catch (Exception e) {
            log.error(e);
        }
        return p;
    }

    public PatientImage createImage(PatientImage p){
        return saveOrUpdateImage(p);
    }
    
    public PatientImage createImage(int patientId, byte[] imageData) {
        PatientImage p = new PatientImage();
        //Context.getPatientService().getPatient(patientId).
        // Find the next page to use.
        // Find how many files currently exist.
        // Lazily compute the next page to use and store it.
        int nextPage = 0;
        if (nextPages.containsKey(patientId)) {
        	nextPage = nextPages.get(patientId);
        } else {
        	File imgDir = new File(getPath(patientId));
        	if (imgDir.exists()) {
        		String imgs[] = imgDir.list();
        		nextPage = imgs.length;
        	}
        }
        File newPage;
        do {
        	newPage = new File(getPath(patientId, nextPage));
        	nextPage++;
        } while (newPage.exists()); 

        p.setId(nextPage-1);
        p.setPatientId(patientId);
        p.setImageData(imageData);
        return saveOrUpdateImage(p);
    }

    public PatientImage updateImage(PatientImage p) {
        return saveOrUpdateImage(p);
    }

    public void deleteImage(PatientImage p) {
        if (!isValidPatientImage(p)) {
            System.out.println("not valid0");
            return;
        }
        File img = new File(getPath(p));
        try {
            System.out.println("can write:"+img.canWrite());
            System.out.println("is deleting:" + img.delete());
            System.out.println(img.exists());
        } catch (Exception e) {
        	log.error(e);
        }
    }

    public void onStartup() {
    	nextPages = new HashMap<Integer, Integer>();
    	log.info("Patient Image Service Starting");
    }

    public void onShutdown() {
        log.info("Patient Image Service Stopping");
    }

    public String getPath(int patientId, int pageId) {
        return getPath(patientId) +
        		System.getProperty("file.separator") + pageId + ".jpg";
    }

    public String getPath(int patientId) {
    	return OpenmrsUtil.getApplicationDataDirectory() + System.getProperty("file.separator") + 
                IMGDIR + System.getProperty("file.separator") + patientId;
    }
    public String getPath(PatientImage p) {
        return OpenmrsUtil.getApplicationDataDirectory() + System.getProperty("file.separator") + 
                IMGDIR + System.getProperty("file.separator") + p.getPatientId() + 
                System.getProperty("file.separator") + p.getId() + ".jpg";
    }

    public List<PatientImage> getAllImages(int patientId) {
        File imgDir = new File(getPath(patientId));
        List<PatientImage> pImages = new ArrayList<PatientImage>();
        String imgs[] = imgDir.list();
        for(int i=0; imgs != null && i<imgs.length; i++){
            System.out.println(imgs[i].split("\\.")[0]);
            PatientImage p = getPatientImage(patientId, Integer.parseInt(imgs[i].split("\\.")[0]));
            pImages.add(p);
        }
        return pImages;
    }

    public PatientImage getPatientImage(int patientId, int pageId) {
        PatientImage p = new PatientImage();
        p.setPatientId(patientId);
        p.setId(pageId);
        File f = new File(getPath(p));
        try{
            FileInputStream fis = new FileInputStream(f);
            byte[] imageData = IOUtils.toByteArray(fis);
            fis.read(imageData);
            p.setImageData(imageData);
            fis.close();
            return p;
        }catch(IOException ex){
            log.error("Reading image directory failed with: "+ex.getMessage());
        }
        return new PatientImage();
    }
}
