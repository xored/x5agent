/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5.impl;

import com.xored.x5.X5Fact;
import com.xored.x5.X5Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getClientapp <em>Clientapp</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getClient <em>Client</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getBody <em>Body</em>}</li>
 *   <li>{@link com.xored.x5.impl.X5FactImpl#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class X5FactImpl extends X5RequestImpl implements X5Fact {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getClientapp() <em>Clientapp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientapp()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENTAPP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClientapp() <em>Clientapp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientapp()
	 * @generated
	 * @ordered
	 */
	protected String clientapp = CLIENTAPP_EDEFAULT;

	/**
	 * The default value of the '{@link #getClient() <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClient() <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected String client = CLIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final long TIMESTAMP_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected long timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchema() <em>Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected static final String SCHEMA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected String schema = SCHEMA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected EObject body;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> references;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected X5FactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return X5Package.Literals.X5_FACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClientapp() {
		return clientapp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientapp(String newClientapp) {
		String oldClientapp = clientapp;
		clientapp = newClientapp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__CLIENTAPP, oldClientapp, clientapp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClient() {
		return client;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClient(String newClient) {
		String oldClient = client;
		client = newClient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__CLIENT, oldClient, client));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestamp(long newTimestamp) {
		long oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(String newSchema) {
		String oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__SCHEMA, oldSchema, schema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(EObject newBody, NotificationChain msgs) {
		EObject oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__BODY, oldBody, newBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(EObject newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - X5Package.X5_FACT__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - X5Package.X5_FACT__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, X5Package.X5_FACT__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getReferences() {
		if (references == null) {
			references = new EcoreEMap<String,String>(X5Package.Literals.ESTRING_TO_ESTRING_MAP_ENTRY, EStringToEStringMapEntryImpl.class, this, X5Package.X5_FACT__REFERENCES);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case X5Package.X5_FACT__BODY:
				return basicSetBody(null, msgs);
			case X5Package.X5_FACT__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case X5Package.X5_FACT__ID:
				return getId();
			case X5Package.X5_FACT__CLIENTAPP:
				return getClientapp();
			case X5Package.X5_FACT__CLIENT:
				return getClient();
			case X5Package.X5_FACT__TIMESTAMP:
				return getTimestamp();
			case X5Package.X5_FACT__SCHEMA:
				return getSchema();
			case X5Package.X5_FACT__BODY:
				return getBody();
			case X5Package.X5_FACT__REFERENCES:
				if (coreType) return getReferences();
				else return getReferences().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case X5Package.X5_FACT__ID:
				setId((String)newValue);
				return;
			case X5Package.X5_FACT__CLIENTAPP:
				setClientapp((String)newValue);
				return;
			case X5Package.X5_FACT__CLIENT:
				setClient((String)newValue);
				return;
			case X5Package.X5_FACT__TIMESTAMP:
				setTimestamp((Long)newValue);
				return;
			case X5Package.X5_FACT__SCHEMA:
				setSchema((String)newValue);
				return;
			case X5Package.X5_FACT__BODY:
				setBody((EObject)newValue);
				return;
			case X5Package.X5_FACT__REFERENCES:
				((EStructuralFeature.Setting)getReferences()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case X5Package.X5_FACT__ID:
				setId(ID_EDEFAULT);
				return;
			case X5Package.X5_FACT__CLIENTAPP:
				setClientapp(CLIENTAPP_EDEFAULT);
				return;
			case X5Package.X5_FACT__CLIENT:
				setClient(CLIENT_EDEFAULT);
				return;
			case X5Package.X5_FACT__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
			case X5Package.X5_FACT__SCHEMA:
				setSchema(SCHEMA_EDEFAULT);
				return;
			case X5Package.X5_FACT__BODY:
				setBody((EObject)null);
				return;
			case X5Package.X5_FACT__REFERENCES:
				getReferences().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case X5Package.X5_FACT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case X5Package.X5_FACT__CLIENTAPP:
				return CLIENTAPP_EDEFAULT == null ? clientapp != null : !CLIENTAPP_EDEFAULT.equals(clientapp);
			case X5Package.X5_FACT__CLIENT:
				return CLIENT_EDEFAULT == null ? client != null : !CLIENT_EDEFAULT.equals(client);
			case X5Package.X5_FACT__TIMESTAMP:
				return timestamp != TIMESTAMP_EDEFAULT;
			case X5Package.X5_FACT__SCHEMA:
				return SCHEMA_EDEFAULT == null ? schema != null : !SCHEMA_EDEFAULT.equals(schema);
			case X5Package.X5_FACT__BODY:
				return body != null;
			case X5Package.X5_FACT__REFERENCES:
				return references != null && !references.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", clientapp: ");
		result.append(clientapp);
		result.append(", client: ");
		result.append(client);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", schema: ");
		result.append(schema);
		result.append(')');
		return result.toString();
	}

} //X5FactImpl
