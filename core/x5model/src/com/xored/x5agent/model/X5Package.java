/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5agent.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.xored.x5agent.model.X5Factory
 * @model kind="package"
 * @generated
 */
public interface X5Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://xored.com/x5/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "com.xored.x5";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	X5Package eINSTANCE = com.xored.x5agent.model.impl.X5PackageImpl.init();

	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.X5Request <em>Request</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.X5Request
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Request()
	 * @generated
	 */
	int X5_REQUEST = 0;

	/**
	 * The number of structural features of the '<em>Request</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_REQUEST_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.X5Response <em>Response</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.X5Response
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Response()
	 * @generated
	 */
	int X5_RESPONSE = 1;

	/**
	 * The number of structural features of the '<em>Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_RESPONSE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.impl.X5FactImpl <em>Fact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.impl.X5FactImpl
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Fact()
	 * @generated
	 */
	int X5_FACT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__ID = X5_REQUEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Clientapp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__CLIENTAPP = X5_REQUEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__CLIENT = X5_REQUEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__TIMESTAMP = X5_REQUEST_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__SCHEMA = X5_REQUEST_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__BODY = X5_REQUEST_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>References</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT__REFERENCES = X5_REQUEST_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Fact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT_FEATURE_COUNT = X5_REQUEST_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.impl.EStringToEStringMapEntryImpl <em>EString To EString Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.impl.EStringToEStringMapEntryImpl
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getEStringToEStringMapEntry()
	 * @generated
	 */
	int ESTRING_TO_ESTRING_MAP_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_ESTRING_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_ESTRING_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EString To EString Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_ESTRING_MAP_ENTRY_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.impl.X5FactResponseImpl <em>Fact Response</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.impl.X5FactResponseImpl
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5FactResponse()
	 * @generated
	 */
	int X5_FACT_RESPONSE = 4;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT_RESPONSE__STATUS = X5_RESPONSE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fact Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int X5_FACT_RESPONSE_FEATURE_COUNT = X5_RESPONSE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.xored.x5agent.model.DeliveryStatus <em>Delivery Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.xored.x5agent.model.DeliveryStatus
	 * @see com.xored.x5agent.model.impl.X5PackageImpl#getDeliveryStatus()
	 * @generated
	 */
	int DELIVERY_STATUS = 5;


	/**
	 * Returns the meta object for class '{@link com.xored.x5agent.model.X5Request <em>Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Request</em>'.
	 * @see com.xored.x5agent.model.X5Request
	 * @generated
	 */
	EClass getX5Request();

	/**
	 * Returns the meta object for class '{@link com.xored.x5agent.model.X5Response <em>Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Response</em>'.
	 * @see com.xored.x5agent.model.X5Response
	 * @generated
	 */
	EClass getX5Response();

	/**
	 * Returns the meta object for class '{@link com.xored.x5agent.model.X5Fact <em>Fact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fact</em>'.
	 * @see com.xored.x5agent.model.X5Fact
	 * @generated
	 */
	EClass getX5Fact();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5Fact#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getId()
	 * @see #getX5Fact()
	 * @generated
	 */
	EAttribute getX5Fact_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5Fact#getClientapp <em>Clientapp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clientapp</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getClientapp()
	 * @see #getX5Fact()
	 * @generated
	 */
	EAttribute getX5Fact_Clientapp();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5Fact#getClient <em>Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Client</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getClient()
	 * @see #getX5Fact()
	 * @generated
	 */
	EAttribute getX5Fact_Client();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5Fact#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getTimestamp()
	 * @see #getX5Fact()
	 * @generated
	 */
	EAttribute getX5Fact_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5Fact#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getSchema()
	 * @see #getX5Fact()
	 * @generated
	 */
	EAttribute getX5Fact_Schema();

	/**
	 * Returns the meta object for the containment reference '{@link com.xored.x5agent.model.X5Fact#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getBody()
	 * @see #getX5Fact()
	 * @generated
	 */
	EReference getX5Fact_Body();

	/**
	 * Returns the meta object for the map '{@link com.xored.x5agent.model.X5Fact#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>References</em>'.
	 * @see com.xored.x5agent.model.X5Fact#getReferences()
	 * @see #getX5Fact()
	 * @generated
	 */
	EReference getX5Fact_References();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To EString Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EString To EString Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getEStringToEStringMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToEStringMapEntry()
	 * @generated
	 */
	EAttribute getEStringToEStringMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToEStringMapEntry()
	 * @generated
	 */
	EAttribute getEStringToEStringMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.xored.x5agent.model.X5FactResponse <em>Fact Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fact Response</em>'.
	 * @see com.xored.x5agent.model.X5FactResponse
	 * @generated
	 */
	EClass getX5FactResponse();

	/**
	 * Returns the meta object for the attribute '{@link com.xored.x5agent.model.X5FactResponse#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see com.xored.x5agent.model.X5FactResponse#getStatus()
	 * @see #getX5FactResponse()
	 * @generated
	 */
	EAttribute getX5FactResponse_Status();

	/**
	 * Returns the meta object for enum '{@link com.xored.x5agent.model.DeliveryStatus <em>Delivery Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Delivery Status</em>'.
	 * @see com.xored.x5agent.model.DeliveryStatus
	 * @generated
	 */
	EEnum getDeliveryStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	X5Factory getX5Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.X5Request <em>Request</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.X5Request
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Request()
		 * @generated
		 */
		EClass X5_REQUEST = eINSTANCE.getX5Request();

		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.X5Response <em>Response</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.X5Response
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Response()
		 * @generated
		 */
		EClass X5_RESPONSE = eINSTANCE.getX5Response();

		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.impl.X5FactImpl <em>Fact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.impl.X5FactImpl
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5Fact()
		 * @generated
		 */
		EClass X5_FACT = eINSTANCE.getX5Fact();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT__ID = eINSTANCE.getX5Fact_Id();

		/**
		 * The meta object literal for the '<em><b>Clientapp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT__CLIENTAPP = eINSTANCE.getX5Fact_Clientapp();

		/**
		 * The meta object literal for the '<em><b>Client</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT__CLIENT = eINSTANCE.getX5Fact_Client();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT__TIMESTAMP = eINSTANCE.getX5Fact_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT__SCHEMA = eINSTANCE.getX5Fact_Schema();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference X5_FACT__BODY = eINSTANCE.getX5Fact_Body();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference X5_FACT__REFERENCES = eINSTANCE.getX5Fact_References();

		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.impl.EStringToEStringMapEntryImpl <em>EString To EString Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.impl.EStringToEStringMapEntryImpl
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getEStringToEStringMapEntry()
		 * @generated
		 */
		EClass ESTRING_TO_ESTRING_MAP_ENTRY = eINSTANCE.getEStringToEStringMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_ESTRING_MAP_ENTRY__KEY = eINSTANCE.getEStringToEStringMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_ESTRING_MAP_ENTRY__VALUE = eINSTANCE.getEStringToEStringMapEntry_Value();

		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.impl.X5FactResponseImpl <em>Fact Response</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.impl.X5FactResponseImpl
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getX5FactResponse()
		 * @generated
		 */
		EClass X5_FACT_RESPONSE = eINSTANCE.getX5FactResponse();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute X5_FACT_RESPONSE__STATUS = eINSTANCE.getX5FactResponse_Status();

		/**
		 * The meta object literal for the '{@link com.xored.x5agent.model.DeliveryStatus <em>Delivery Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.xored.x5agent.model.DeliveryStatus
		 * @see com.xored.x5agent.model.impl.X5PackageImpl#getDeliveryStatus()
		 * @generated
		 */
		EEnum DELIVERY_STATUS = eINSTANCE.getDeliveryStatus();

	}

} //X5Package
