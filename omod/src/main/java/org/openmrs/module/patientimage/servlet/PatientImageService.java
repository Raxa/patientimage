package org.openmrs.module.patientimage.servlet;

import org.openmrs.util.OpenmrsUtil;

public class PatientImageService {
	
	/**
	 * @param patientId int
	 * @param pageId int
	 * @return String path to given image.
	 */
	public static String getImagePath(int patientId, int pageId) {
		return OpenmrsUtil.getApplicationDataDirectory()
		        + patientId + System.getProperty("file.separator") + pageId;
	}
}
