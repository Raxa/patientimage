
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
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.PatientService;
import org.openmrs.module.patientimage.PatientImage;
import org.openmrs.module.patientimage.PatientImageService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.util.OpenmrsUtil;

/**
 * Testing class for PatientImageServiceImpl
 */
public class PatientImageServiceImplTest extends BaseModuleContextSensitiveTest{

    private PatientImageService s = null;
    private PatientService ps = null;
    private Patient testPatient = null;
    private static final int TEST_PATIENT_ID = 2;

    @Before
    public void setUp() throws IOException {
    	// Create a test patient
        s = Context.getService(PatientImageService.class);
        s.onStartup();
        // Clean up test_patient directory.
        File testPatientDir = new File(s.getPath(TEST_PATIENT_ID));
        if (testPatientDir.exists()) {
        	delete(testPatientDir);
        }
    }

    @After
    public void tearDown() {
    	s.onShutdown();
    }

    /**
     * Test of createImage method, of class PatientImageServiceImpl.
     */
    @Test
    public void testCreateImageShouldCreateImage() {
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(TEST_PATIENT_ID, imageData);
        File f = new File(s.getPath(p));
        try {
            assert(f.exists());
        } catch (Exception ex) {
            fail("Unable to access file system to test PatientImageService, error:" + ex.getMessage());
        }
    }

    /**
     * Test of updateImage method, of class PatientImageServiceImpl.
     */
    @Test
    public void testUpdateImageShoudChangeImage() {
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(TEST_PATIENT_ID, imageData);
        p.setImageData(new byte[]{4,4,4,4});
        p = s.updateImage(p);
        File f = new File(s.getPath(p));
        try {
            FileInputStream fis = new FileInputStream(f);
            byte[] actualData = new byte[4];
            byte[] expectedData = new byte[]{4,4,4,4};
            fis.read(actualData);
            for(int i=0;i<expectedData.length;i++){
                assertEquals(expectedData[i],actualData[i]);
            }
        } catch (IOException ex) {
            fail("Unable to access file system to test PatientImageService, error:" + ex.getMessage());
        }
    }

    /**
     * Test of deleteImage method, of class PatientImageServiceImpl.
     */
    @Test
    public void testDeleteImageShouldDeleteImage() {
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(TEST_PATIENT_ID, imageData);
        File f = new File(s.getPath(p));
        assert(f.exists());
        s.deleteImage(p);
        System.out.println(f.exists());
        assert(!f.exists());
    }
    
    /**
     * Test of getAllImages method, of class PatientImageServiceImpl
     */
    @Test
    public void testGetAllImages() {
        byte[][] images = new byte[3][4];
        images[0] = new byte[]{1, 2, 3, 4};
        images[1] = new byte[]{4, 3, 2, 1};
        images[2] = new byte[]{5, 5, 5, 5};
        for (int i = 0; i < images.length; i++) {
        	s.createImage(TEST_PATIENT_ID, images[i]);
        }
        
        List<PatientImage> ret = s.getAllImages(TEST_PATIENT_ID);
        assert(ret.size() == images.length);
        for (int i = 0; i < images.length; i++) {
        	// Assert that it's in the returned list, but can't assume order.
        	boolean pass = false;
        	for (PatientImage p : ret) {
        		if (Arrays.equals(p.getImageData(), images[i]));
        			pass = true;
        	}
        	assert(pass);
        }
    }
    
    /**
     * Recursively deletes a file or directory.
     * @param file
     * @throws IOException
     */
    private static void delete(File file)	throws IOException{
    	if(file.isDirectory()) {
    		//directory is empty, then delete it
        	if(file.list().length==0) {
        		file.delete();
        	} else {
        		//list all the directory contents
        		String files[] = file.list();
        		for (String temp : files) {
        			//construct the file structure
        			File fileDelete = new File(file, temp);

            	    //recursive delete
            	    delete(fileDelete);
        		}
     
            	//check the directory again, if empty then delete it
            	if(file.list().length==0){
            		file.delete();
            	}
        	}
    	} else {
    		//if file, then delete it
    		file.delete();
    	}
    }
}
