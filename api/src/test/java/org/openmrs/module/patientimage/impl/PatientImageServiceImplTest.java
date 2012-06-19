
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientimage.PatientImage;
import org.openmrs.module.patientimage.PatientImageService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.util.OpenmrsUtil;

/**
 * Testing class for PatientImageServiceImpl
 */
public class PatientImageServiceImplTest extends BaseModuleContextSensitiveTest{

    private PatientImageService s = null;

    public PatientImageServiceImplTest() {
    }

    @Before
    public void setUp() {
        s = Context.getService(PatientImageService.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createImage method, of class PatientImageServiceImpl.
     */
    @Test
    public void testCreateImageShouldCreateImage() {
        int patientId = 2;
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(patientId, imageData);
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
        int patientId = 2;
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(patientId, imageData);
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
        int patientId = 2;
        byte[] imageData = new byte[]{1, 2, 3, 4};
        PatientImage p = s.createImage(patientId, imageData);
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
        
    }
}
