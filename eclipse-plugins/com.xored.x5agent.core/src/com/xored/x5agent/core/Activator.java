package com.xored.x5agent.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.xored.x5agent.core"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Job job = new Job("Starting X5 Agent...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				X5Agent.Instance.initialize(X5PreferenceStorage.Instance,
						X5AgentRegistry.Instance.getStreams());

				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		X5Agent.Instance.dispose();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(CoreException e) {
		e.printStackTrace();
		// getDefault().getLog().log(e.getStatus());
	}

	public static void logError(String msg) {
		// getDefault().getLog().log(
		// new Status(IStatus.ERROR, PLUGIN_ID, msg, null));
	}

	public static void logError(Throwable t) {
		t.printStackTrace();
		// getDefault().getLog().log(
		// new Status(IStatus.ERROR, PLUGIN_ID, t.getMessage(), t));
	}

	public static void logError(String msg, Throwable t) {
		t.printStackTrace();
		// getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, msg,
		// t));
	}

}
