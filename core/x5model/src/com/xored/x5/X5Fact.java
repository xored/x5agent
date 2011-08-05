/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.xored.x5.X5Fact#getId <em>Id</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getClientapp <em>Clientapp</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getClient <em>Client</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getSchema <em>Schema</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getBody <em>Body</em>}</li>
 *   <li>{@link com.xored.x5.X5Fact#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.xored.x5.X5Package#getX5Fact()
 * @model
 * @generated
 */
public interface X5Fact extends X5Request {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.xored.x5.X5Package#getX5Fact_Id()
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Clientapp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clientapp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clientapp</em>' attribute.
	 * @see #setClientapp(String)
	 * @see com.xored.x5.X5Package#getX5Fact_Clientapp()
	 * @model
	 * @generated
	 */
	String getClientapp();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getClientapp <em>Clientapp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clientapp</em>' attribute.
	 * @see #getClientapp()
	 * @generated
	 */
	void setClientapp(String value);

	/**
	 * Returns the value of the '<em><b>Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client</em>' attribute.
	 * @see #setClient(String)
	 * @see com.xored.x5.X5Package#getX5Fact_Client()
	 * @model
	 * @generated
	 */
	String getClient();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getClient <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client</em>' attribute.
	 * @see #getClient()
	 * @generated
	 */
	void setClient(String value);

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(long)
	 * @see com.xored.x5.X5Package#getX5Fact_Timestamp()
	 * @model
	 * @generated
	 */
	long getTimestamp();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(long value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' attribute.
	 * @see #setSchema(String)
	 * @see com.xored.x5.X5Package#getX5Fact_Schema()
	 * @model
	 * @generated
	 */
	String getSchema();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getSchema <em>Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' attribute.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(String value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(EObject)
	 * @see com.xored.x5.X5Package#getX5Fact_Body()
	 * @model containment="true"
	 * @generated
	 */
	EObject getBody();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5Fact#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(EObject value);

	/**
	 * Returns the value of the '<em><b>References</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' map.
	 * @see com.xored.x5.X5Package#getX5Fact_References()
	 * @model mapType="com.xored.x5.EStringToEStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getReferences();

} // X5Fact
