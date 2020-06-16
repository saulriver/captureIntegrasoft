package com.are.captureintegrasoft;

import com.digitalpersona.onetouch.ui.swing.sample.Enrollment.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class VerificationForm extends CaptureFingerprint
{
	private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
	
	VerificationForm(JFrame owner) {
		super(owner);
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus(0);
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);

		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
                        DPFPVerificationResult result = null;
                        
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("1")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate1());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("2")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate2());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("3")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate3());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("4")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate4());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("5")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate5());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("6")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate6());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("7")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate7());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("8")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate8());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("9")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate9());
                        }
                        if (((MainForm)getOwner()).getFingerprintPosition().equals("10")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplate10());
                        }
                         if (((MainForm)getOwner()).getFingerprintPosition().equals("fingerprintDefault")) {
                            result = verificator.verify(features, ((MainForm)getOwner()).getTemplatefingerprintDefault());
                        }
			
                        
			updateStatus(result.getFalseAcceptRate());
			if (result.isVerified())
				makeReport("The fingerprint was VERIFIED.");
			else
				makeReport("The fingerprint was NOT VERIFIED.");
		}
	}
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}

}
