package com.xored.x5agent.core;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.xored.sherlock.core.info.Info;
import com.xored.x5agent.core.X5SnapshotProvider;

public class EclipseInfoSnapshotProvider implements X5SnapshotProvider {

	private EObject snapshot;

	@Override
	public String getSchema() {
		return "sherlock.eclipse_info";
	}

	@Override
	public void initialize(Map<String, String> parameters) {
	}

	@Override
	public EObject getSnapshot() {
		if (snapshot == null) {
			snapshot = Info.getEclipse();
			// snapshot = new JsonObject();
			// Gson gson = new Gson();
			// snapshot.addProperty("productId",
			// EclipseInfoProvider.getProductId());
			// snapshot.addProperty("buildId",
			// EclipseInfoProvider.getProductId());
			// snapshot.addProperty("applicationId",
			// EclipseInfoProvider.getApplicationId());
			// snapshot.add("applicationArgs",
			// gson.toJsonTree(EclipseInfoProvider.getApplicationArgs()));
			// String workspace = EclipseInfoProvider.getWorkspaceLocation();
			// snapshot.addProperty("workspaceLocation", workspace);
			//
			// if (workspace != null) {
			// try {
			// File workspaceDir = new File(workspace);
			// snapshot.addProperty("workspacePartitionFreeDiskspace",
			// workspaceDir.getTotalSpace());
			// snapshot.addProperty("workspacePartitionTotalDiskspace",
			// workspaceDir.getUsableSpace());
			// snapshot.addProperty("workspacePartitionUsableDiskspace",
			// workspaceDir.getFreeSpace());
			// } catch (Exception e) {
			// // TODO handle
			// }
			// }
			// snapshot.addProperty("uptime", EclipseInfoProvider.getUptime());
			// JsonArray plugins = new JsonArray();
			// for (Bundle b : EclipseInfoProvider.getPlugins()) {
			// plugins.add(toJson(b));
			// }
			// snapshot.add("plugins", plugins);
			// JsonArray features = new JsonArray();
			// for (IBundleGroup g : EclipseInfoProvider.getFeatures()) {
			// features.add(toJson(g));
			// }
			// snapshot.add("features", features);
			// snapshot.add("preferences",
			// toJson(EclipseInfoProvider.getPreferencesRoot()));
		}
		return snapshot;
	}

	// private JsonElement toJson(Bundle bundle) {
	// JsonObject o = new JsonObject();
	// @SuppressWarnings("unchecked")
	// final Dictionary<String, String> headers = bundle.getHeaders();
	// o.addProperty("name", headers.get(Constants.BUNDLE_NAME));
	// o.addProperty("provider", headers.get(Constants.BUNDLE_VENDOR));
	// o.addProperty("id", bundle.getSymbolicName());
	// o.addProperty("version", headers.get(Constants.BUNDLE_VERSION));
	// return o;
	// }
	//
	// private JsonElement toJson(IBundleGroup group) {
	// JsonObject o = new JsonObject();
	// o.addProperty("id", group.getIdentifier());
	// o.addProperty("version", group.getVersion());
	// o.addProperty("name", group.getName());
	// o.addProperty("provider", group.getProviderName());
	// return o;
	// }
	//
	// private JsonElement toJson(Preferences preferences) {
	// JsonArray array = new JsonArray();
	// List<Preferences> unprocessed = new LinkedList<Preferences>();
	// unprocessed.add(preferences);
	// try {
	// while (unprocessed.size() > 0) {
	// Preferences p = unprocessed.get(0);
	// unprocessed.remove(0);
	//
	// String absolutePath = p.absolutePath();
	// for (String childName : p.childrenNames()) {
	// unprocessed.add(0, p.node(childName));
	// }
	// for (String key : p.keys()) {
	// JsonObject o = new JsonObject();
	// o.addProperty("name", key);
	// o.addProperty("value", p.get(key, ""));
	// o.addProperty("path", absolutePath);
	// array.add(o);
	// }
	// }
	// } catch (Exception e) {
	// // TODO handle
	// }
	// return array;
	// }

	@Override
	public void dispose() {
	}
}
