package com.are.captureintegrasoft;

import com.digitalpersona.onetouch.ui.swing.sample.Enrollment.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureFingerprint
{
	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	
	EnrollmentForm(JFrame owner) {
		super(owner);
	}

	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus();
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("The fingerprint feature set was created.");
			enroller.addFeatures(features);		// Add feature set to template.
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();

			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
					stop();
                                        
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("1")) {
                                            ((MainForm)getOwner()).setTemplate1(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("2")) {
                                            ((MainForm)getOwner()).setTemplate2(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("3")) {
                                            ((MainForm)getOwner()).setTemplate3(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("4")) {
                                            ((MainForm)getOwner()).setTemplate4(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("5")) {
                                            ((MainForm)getOwner()).setTemplate5(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("6")) {
                                            ((MainForm)getOwner()).setTemplate6(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("7")) {
                                            ((MainForm)getOwner()).setTemplate7(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("8")) {
                                            ((MainForm)getOwner()).setTemplate8(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("9")) {
                                            ((MainForm)getOwner()).setTemplate9(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("10")) {
                                            ((MainForm)getOwner()).setTemplate10(enroller.getTemplate());
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("fingerprintDefault")) {
                                            ((MainForm)getOwner()).setTemplatefingerprintDefault(enroller.getTemplate());
                                        }
                                        
					setPrompt("Click Close, and then click Fingerprint Verification.");
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
					updateStatus();
                                        
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("1")) {
                                            ((MainForm)getOwner()).setTemplate1(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("2")) {
                                            ((MainForm)getOwner()).setTemplate2(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("3")) {
                                            ((MainForm)getOwner()).setTemplate3(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("4")) {
                                            ((MainForm)getOwner()).setTemplate4(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("5")) {
                                            ((MainForm)getOwner()).setTemplate5(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("6")) {
                                            ((MainForm)getOwner()).setTemplate6(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("7")) {
                                            ((MainForm)getOwner()).setTemplate7(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("8")) {
                                            ((MainForm)getOwner()).setTemplate8(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("9")) {
                                            ((MainForm)getOwner()).setTemplate9(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("10")) {
                                            ((MainForm)getOwner()).setTemplate10(null);
                                        }
                                        if (((MainForm)getOwner()).getFingerprintPosition().equals("fingerprintDefault")) {
                                            ((MainForm)getOwner()).setFingerprintDefault(null);
                                        }
                                        
					JOptionPane.showMessageDialog(EnrollmentForm.this, "The fingerprint template is not valid. Repeat fingerprint enrollment.", "Fingerprint Enrollment", JOptionPane.ERROR_MESSAGE);
					start();
					break;
			}
		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
	}
	
}
