package org.openmrs.module.patientimage.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.util.OpenmrsUtil;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = "classpath:TestingApplicationContext.xml")
public class PatientImageControllerTest {/*extends BaseModuleWebContextSensitiveTest {

public final static Integer TEST_PATIENT = Integer.MAX_VALUE;

public final static Integer TEST_PAGE = Integer.MAX_VALUE;

public final static byte TEST_BYTE = 0x12;

// A test file to use for images
private File testFile;

private File testDir;

// if a file existed, the tests need to fail because they didn't have a place to test with
private boolean fileExisted;

private boolean dirExisted;

private PatientImageController controller;

private MockHttpServletRequest request;

private byte[] bytes;

@Before
public void createTestFile() throws IOException {
bytes = new byte[5];
for (int i = 0; i < 5; i++)
bytes[i] = TEST_BYTE;
String dirpath = OpenmrsUtil.getApplicationDataDirectory() + TEST_PATIENT;
String filepath = System.getProperty("file.separator") + TEST_PAGE;
testDir = new File(dirpath);
dirExisted = !testDir.mkdir();
testFile = new File(dirpath + filepath);
fileExisted = !testFile.createNewFile();
if (!fileExisted)
new FileOutputStream(testFile).write(bytes);
}

@Before
public void before() {
controller = new PatientImageController();
request = new MockHttpServletRequest();
}

@After
public void destroyTestFile() {
if (!fileExisted)
testFile.delete();
if (!dirExisted)
testDir.delete();
}

@Test
public void testRetrieve() throws IOException {
Assert.assertFalse("Test could not find empty test file", fileExisted);
ResponseEntity<byte[]> resp = controller.retrieve(TEST_PATIENT.toString(), TEST_PAGE.toString(), request);
Assert.assertEquals(MediaType.IMAGE_JPEG, resp.getHeaders().getContentType());
Assert.assertArrayEquals(bytes, resp.getBody());
}

@Test
public void testNotFound() throws IOException {
File notFound = new File(OpenmrsUtil.getApplicationDataDirectory() + System.getProperty("file.separator")
+ TEST_PATIENT + System.getProperty("file.separator") + (TEST_PAGE - 1));
Assert.assertFalse("Test could not find empty test file", notFound.exists());
ResponseEntity<byte[]> resp = controller.retrieve(TEST_PATIENT.toString(), (TEST_PAGE - 1) + "", request);
Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
}*/
}
