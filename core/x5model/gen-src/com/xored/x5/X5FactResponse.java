/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.xored.x5;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fact Response</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.xored.x5.X5FactResponse#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.xored.x5.X5Package#getX5FactResponse()
 * @model
 * @generated
 */
public interface X5FactResponse extends X5Response {
	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link com.xored.x5.DeliveryStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see com.xored.x5.DeliveryStatus
	 * @see #setStatus(DeliveryStatus)
	 * @see com.xored.x5.X5Package#getX5FactResponse_Status()
	 * @model
	 * @generated
	 */
	DeliveryStatus getStatus();

	/**
	 * Sets the value of the '{@link com.xored.x5.X5FactResponse#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see com.xored.x5.DeliveryStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(DeliveryStatus value);

} // X5FactResponse
