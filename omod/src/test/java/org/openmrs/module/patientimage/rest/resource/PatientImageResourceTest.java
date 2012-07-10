package org.openmrs.module.patientimage.rest.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.util.OpenmrsUtil;

public class PatientImageResourceTest {
	
	public final static int TEST_PATIENT = Integer.MAX_VALUE;
	
	public final static int TEST_PAGE = Integer.MAX_VALUE;
	
	public final static byte TEST_BYTE = 0x12;
	
	// A test file to use for images
	private File testFile;
	
	private File testDir;
	
	// if a file existed, the tests need to fail because they didn't have a place to test with
	private boolean fileExisted;
	
	private boolean dirExisted;
	
	private PatientImageResource r;
	
	private byte[] bytes;
	
	@Before
	public void before() throws IOException {
		r = new PatientImageResource();
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
	
	@After
	public void after() {
		if (!fileExisted)
			testFile.delete();
		if (!dirExisted)
			testDir.delete();
	}
	
	@Test
	public void testImageRetrieval() throws IOException {
		Assert.assertFalse("Test could not find empty test file", fileExisted);
		Assert.assertArrayEquals(bytes, r.retrieve(TEST_PATIENT, TEST_PAGE));
	}
	
	@Test
	public void testImageNotFound() {
		File notFound = new File(OpenmrsUtil.getApplicationDataDirectory() + System.getProperty("file.separator")
		        + TEST_PATIENT + System.getProperty("file.separator") + (TEST_PAGE - 1));
		Assert.assertFalse("Test could not find empty test file", notFound.exists());
		try {
			r.retrieve(TEST_PATIENT, TEST_PAGE - 1);
			Assert.fail("Retrieving a non-existant image should have thrown an exception");
		}
		catch (IOException e) {}
	}
}
