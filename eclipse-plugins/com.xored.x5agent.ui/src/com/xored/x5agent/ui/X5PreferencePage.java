package com.xored.x5agent.ui;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.xored.x5agent.core.X5PreferenceStorage;

public class X5PreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Text clientText;

	public X5PreferencePage() {
		super();
	}

	public X5PreferencePage(String title) {
		super(title);
	}

	public X5PreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(c);
		Label clientLabel = new Label(c, SWT.NONE);
		clientLabel.setText("Group name:");
		clientText = new Text(c, SWT.NONE);
		clientText.setText(X5PreferenceStorage.Instance.getClient());
		clientText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String client = clientText.getText();
				if (client == null || client.length() == 0) {
					setErrorMessage("Group name is empty");
					setValid(false);
				} else {
					setErrorMessage(null);
					setValid(true);
				}
			}
		});
		// GridDataFactory.fillDefaults().applyTo(clientText);
		// Label clientAppLabel = new Label(c, SWT.NONE);
		// clientAppLabel.setText("Client application ID:");
		// Text clientAppText = new Text(c, SWT.READ_ONLY);
		// clientAppText.setText(X5PreferenceStorage.Instance.getClientApp());
		// GridDataFactory.fillDefaults().applyTo(clientAppText);
		return c;
	}

	@Override
	protected void performApply() {
		super.performApply();
		X5PreferenceStorage.Instance.setClient(clientText.getText());
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		X5PreferenceStorage.Instance.setDefaultClient();
		clientText.setText(X5PreferenceStorage.Instance.getClient());
	}

}
