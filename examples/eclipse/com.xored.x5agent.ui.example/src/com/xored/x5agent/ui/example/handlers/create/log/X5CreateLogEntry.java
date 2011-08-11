package com.xored.x5agent.ui.example.handlers.create.log;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import com.xored.x5agent.ui.example.Activator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class X5CreateLogEntry extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public X5CreateLogEntry() {
		info = new Info();
		info.pluginName = Activator.PLUGIN_ID;
		info.message = "Error";
	}

	private class Info {
		public String pluginName;
		public String message;
	}

	Info info = null;

	@SuppressWarnings("null")
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Dialog errorDialog = new Dialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell()) {
			@Override
			protected Control createDialogArea(Composite parent) {
				Composite content = (Composite) super.createDialogArea(parent);
				((GridLayout) content.getLayout()).numColumns = 2;

				Label pluginLabel = new Label(content, SWT.NONE);
				pluginLabel.setText("Plugin  name:");
				final Combo pluginName = new Combo(content, SWT.BORDER
						| SWT.READ_ONLY);
				Set<String> names = new HashSet<String>();

				IBundleGroupProvider[] providers = Platform
						.getBundleGroupProviders();
				for (IBundleGroupProvider iBundleGroupProvider : providers) {
					IBundleGroup[] groups = iBundleGroupProvider
							.getBundleGroups();
					for (IBundleGroup iBundleGroup : groups) {
						Bundle[] bundles = iBundleGroup.getBundles();
						for (Bundle bundle : bundles) {
							names.add(bundle.getSymbolicName());
						}
					}
				}
				int index = 0;

				names.add(Activator.PLUGIN_ID);
				final String[] namesArray = names.toArray(new String[names
						.size()]);
				for (int i = 0; i < namesArray.length; i++) {
					if (info.pluginName.equals(namesArray[i])) {
						index = i;
						break;
					}
				}
				pluginName.setItems(namesArray);
				pluginName.select(index);
				pluginName.addSelectionListener(new SelectionListener() {
					
					public void widgetSelected(SelectionEvent e) {
						info.pluginName = namesArray[pluginName
														.getSelectionIndex()];						
					}
					
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});

				Label messageLabel = new Label(content, SWT.NONE);
				messageLabel.setText("Message:");
				final Text text = new Text(content, SWT.BORDER);
				text.setText(info.message);
				text.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						info.message = text.getText();
					}
				});
				GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
				return content;
			}

			@Override
			protected void configureShell(Shell newShell) {
				super.configureShell(newShell);
				newShell.setText("Create Error log entry");
			}
		};
		if (errorDialog.open() == Dialog.OK) {
			// String test = null;
			// test.concat(null);
			Bundle bundle = Platform.getBundle(info.pluginName);
			ILog log = InternalPlatform.getDefault().getLog(bundle);
			log.log(new Status(Status.ERROR, info.pluginName, info.message,
					new Exception(info.message)));
		}
		return null;
	}
}
